package com.baidu.cloud.videocache;

import android.content.Context;
import android.net.Uri;
import com.baidu.cloud.videocache.file.C2569a;
import com.baidu.cloud.videocache.file.C2570b;
import com.baidu.cloud.videocache.file.DiskUsage;
import com.baidu.cloud.videocache.file.FileNameGenerator;
import com.baidu.cloud.videocache.headers.HeaderInjector;
import com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpProxyCacheServer {
    private static final Logger LOG = LoggerFactory.getLogger("HttpProxyCacheServer");
    public static final String PROXY_HOST = "127.0.0.1";
    private final Object clientsLock;
    private final Map clientsMap;
    private final oia config;
    private final C2567e pinger;
    private final int port;
    private final ServerSocket serverSocket;
    private final ExecutorService socketProcessor;
    private Thread waitConnectionThread;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Builder {
        private static final long DEFAULT_MAX_SIZE = 536870912;
        private File cacheRoot;
        private DiskUsage diskUsage = new C2570b(536870912);
        private FileNameGenerator fileNameGenerator = new com.baidu.cloud.videocache.file.uwb();
        private HeaderInjector headerInjector = new com.baidu.cloud.videocache.headers.oia();
        private SourceInfoStorage sourceInfoStorage;

        public Builder(Context context) {
            this.sourceInfoStorage = com.baidu.cloud.videocache.sourcestorage.rwd.m19726a(context);
            this.cacheRoot = C2578n.m19778a(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public oia buildConfig() {
            return new oia(this.cacheRoot, this.fileNameGenerator, this.diskUsage, this.sourceInfoStorage, this.headerInjector);
        }

        public HttpProxyCacheServer build() {
            return new HttpProxyCacheServer(buildConfig());
        }

        public Builder cacheDirectory(File file) {
            this.cacheRoot = (File) C2571g.m19807a(file);
            return this;
        }

        public Builder diskUsage(DiskUsage diskUsage) {
            this.diskUsage = (DiskUsage) C2571g.m19807a(diskUsage);
            return this;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.fileNameGenerator = (FileNameGenerator) C2571g.m19807a(fileNameGenerator);
            return this;
        }

        public Builder headerInjector(HeaderInjector headerInjector) {
            this.headerInjector = (HeaderInjector) C2571g.m19807a(headerInjector);
            return this;
        }

        public Builder maxCacheFilesCount(int i) {
            this.diskUsage = new C2569a(i);
            return this;
        }

        public Builder maxCacheSize(long j) {
            this.diskUsage = new C2570b(j);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public final class SocketProcessorRunnable implements Runnable {
        private final Socket socket;

        public SocketProcessorRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpProxyCacheServer.this.processSocket(this.socket);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public final class WaitRequestsRunnable implements Runnable {
        private final CountDownLatch startSignal;

        public WaitRequestsRunnable(CountDownLatch countDownLatch) {
            this.startSignal = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.startSignal.countDown();
            HttpProxyCacheServer.this.waitForRequest();
        }
    }

    public HttpProxyCacheServer(Context context) {
        this(new Builder(context).buildConfig());
    }

    private HttpProxyCacheServer(oia oiaVar) {
        this.clientsLock = new Object();
        this.socketProcessor = Executors.newFixedThreadPool(8);
        this.clientsMap = new ConcurrentHashMap();
        this.config = (oia) C2571g.m19807a(oiaVar);
        try {
            this.serverSocket = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.port = this.serverSocket.getLocalPort();
            C2565c.m19830a("127.0.0.1", this.port);
            startWaitConectionThread();
            this.pinger = new C2567e("127.0.0.1", this.port);
            Logger logger = LOG;
            logger.info("Proxy cache server started. Is it alive? " + isAlive());
        } catch (IOException | InterruptedException e) {
            this.socketProcessor.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    private String appendToProxyUrl(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", "127.0.0.1", Integer.valueOf(this.port), C2576l.m19781b(str));
    }

    private void closeSocket(Socket socket) {
        try {
            if (socket.isClosed()) {
                return;
            }
            socket.close();
        } catch (IOException e) {
            onError(new C2574j("Error closing socket", e));
        }
    }

    private void closeSocketInput(Socket socket) {
        try {
            if (socket.isInputShutdown()) {
                return;
            }
            socket.shutdownInput();
        } catch (SocketException unused) {
            LOG.debug("Releasing input stream… Socket is closed by client.");
        } catch (IOException e) {
            onError(new C2574j("Error closing socket input stream", e));
        }
    }

    private void closeSocketOutput(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                return;
            }
            socket.shutdownOutput();
        } catch (IOException e) {
            LOG.warn("Failed to close socket on proxy side: {}. It seems client have already closed connection.", e.getMessage());
        }
    }

    private File getCacheFile(String str) {
        return new File(this.config.f4906a, this.config.f4907b.generate(str));
    }

    private HttpProxyCacheServerClients getClients(String str) {
        HttpProxyCacheServerClients httpProxyCacheServerClients;
        synchronized (this.clientsLock) {
            httpProxyCacheServerClients = (HttpProxyCacheServerClients) this.clientsMap.get(str);
            if (httpProxyCacheServerClients == null) {
                httpProxyCacheServerClients = new HttpProxyCacheServerClients(str, this.config);
                this.clientsMap.put(str, httpProxyCacheServerClients);
            }
        }
        return httpProxyCacheServerClients;
    }

    private int getClientsCount() {
        int i;
        synchronized (this.clientsLock) {
            i = 0;
            for (HttpProxyCacheServerClients httpProxyCacheServerClients : this.clientsMap.values()) {
                i += httpProxyCacheServerClients.getClientsCount();
            }
        }
        return i;
    }

    private boolean isAlive() {
        return this.pinger.m19828a(3, 70);
    }

    private void onError(Throwable th) {
        LOG.error("HttpProxyCacheServer error", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSocket(Socket socket) {
        Logger logger;
        StringBuilder sb;
        try {
            try {
                nxb m19775a = nxb.m19775a(socket.getInputStream());
                Logger logger2 = LOG;
                logger2.debug("Request to cache proxy:" + m19775a);
                String m19780c = C2576l.m19780c(m19775a.f4902a);
                if (this.pinger.m19826a(m19780c)) {
                    this.pinger.m19825a(socket);
                } else {
                    getClients(m19780c).processRequest(m19775a, socket);
                }
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (C2574j e) {
                e = e;
                onError(new C2574j("Error processing request", e));
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (SocketException unused) {
                LOG.debug("Closing socket… Socket is closed by client.");
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (IOException e2) {
                e = e2;
                onError(new C2574j("Error processing request", e));
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            }
            sb.append("Opened connections: ");
            sb.append(getClientsCount());
            logger.debug(sb.toString());
        } catch (Throwable th) {
            releaseSocket(socket);
            Logger logger3 = LOG;
            logger3.debug("Opened connections: " + getClientsCount());
            throw th;
        }
    }

    private void releaseSocket(Socket socket) {
        closeSocketInput(socket);
        closeSocketOutput(socket);
        closeSocket(socket);
    }

    private void shutdownClients() {
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients httpProxyCacheServerClients : this.clientsMap.values()) {
                httpProxyCacheServerClients.shutdown();
            }
            this.clientsMap.clear();
        }
    }

    private void startWaitConectionThread() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.waitConnectionThread = new Thread(new WaitRequestsRunnable(countDownLatch));
        this.waitConnectionThread.start();
        countDownLatch.await();
    }

    private void touchFileSafely(File file) {
        try {
            this.config.f4908c.touch(file);
        } catch (IOException e) {
            Logger logger = LOG;
            logger.error("Error touching file " + file, (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForRequest() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.serverSocket.accept();
                Logger logger = LOG;
                logger.debug("Accept new socket " + accept);
                this.socketProcessor.submit(new SocketProcessorRunnable(accept));
            } catch (IOException e) {
                onError(new C2574j("Error during waiting connection", e));
                return;
            }
        }
    }

    public void clearCache() {
        try {
            Util.cleanDirectory(this.config.f4906a);
        } catch (Throwable th) {
            LOG.error("Error clear cache ", th);
        }
    }

    public long getCacheSize() {
        try {
            return Util.calculateFileSize(this.config.f4906a);
        } catch (Throwable th) {
            LOG.error("Error get cache size ", th);
            return 0L;
        }
    }

    public com.baidu.cloud.videocache.file.oia getFileCache(String str) {
        try {
            return new com.baidu.cloud.videocache.file.oia(getCacheFile(str), this.config.f4908c);
        } catch (Exception e) {
            Logger logger = LOG;
            logger.error("Error create file cache " + str, (Throwable) e);
            return null;
        }
    }

    public String getProxyUrl(String str) {
        boolean z;
        switch (Util.analyzeMediaType(str)) {
            case 1:
            case 2:
                z = false;
                break;
            default:
                z = true;
                break;
        }
        return getProxyUrl(str, z);
    }

    public String getProxyUrl(String str, boolean z) {
        if (!z || !isCached(str)) {
            return isAlive() ? appendToProxyUrl(str) : str;
        }
        File cacheFile = getCacheFile(str);
        touchFileSafely(cacheFile);
        return Uri.fromFile(cacheFile).toString();
    }

    public String getTempCacheFilePath(String str) {
        return getCacheFile(str).getAbsolutePath() + ".download";
    }

    public boolean isCached(String str) {
        C2571g.m19806a(str, "Url can't be null!");
        return getCacheFile(str).exists();
    }

    public boolean isReadingInProgress(String str) {
        try {
            HttpProxyCacheServerClients clients = getClients(str);
            if (clients != null) {
                return clients.isReadingInProgress(str);
            }
            return false;
        } catch (C2574j e) {
            Logger logger = LOG;
            logger.error("Error access cache reading status " + str, (Throwable) e);
            return false;
        }
    }

    public void registerCacheListener(CacheListener cacheListener, String str) {
        C2571g.m19803a(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).registerCacheListener(cacheListener);
            } catch (C2574j e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }

    public void shutdown() {
        LOG.info("Shutdown proxy server");
        shutdownClients();
        this.config.f4909d.release();
        this.waitConnectionThread.interrupt();
        try {
            if (this.serverSocket.isClosed()) {
                return;
            }
            this.serverSocket.close();
        } catch (IOException e) {
            onError(new C2574j("Error shutting down proxy server", e));
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        C2571g.m19807a(cacheListener);
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients httpProxyCacheServerClients : this.clientsMap.values()) {
                httpProxyCacheServerClients.unregisterCacheListener(cacheListener);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener, String str) {
        C2571g.m19803a(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).unregisterCacheListener(cacheListener);
            } catch (C2574j e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }
}
