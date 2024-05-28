package org.java_websocket.server;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class WebSocketServer extends WebSocketAdapter implements Runnable {
    public static int DECODERS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    private List<WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private volatile AtomicBoolean isclosed;
    private int queueinvokes;
    private AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServerFactory wsf;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface WebSocketServerFactory extends WebSocketFactory {
        @Override // org.java_websocket.WebSocketFactory
        WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket);

        @Override // org.java_websocket.WebSocketFactory
        WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket);

        ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException;
    }

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public void onCloseInitiated(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str, boolean z) {
    }

    protected boolean onConnect(SelectionKey selectionKey) {
        return true;
    }

    public abstract void onError(WebSocket webSocket, Exception exc);

    public abstract void onMessage(WebSocket webSocket, String str);

    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    protected void releaseBuffers(WebSocket webSocket) throws InterruptedException {
    }

    public WebSocketServer() throws UnknownHostException {
        this(new InetSocketAddress(80), DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, List<Draft> list) {
        this(inetSocketAddress, DECODERS, list);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list, Collection<WebSocket> collection) {
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        if (inetSocketAddress == null || i < 1 || collection == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (list == null) {
            this.drafts = Collections.emptyList();
        } else {
            this.drafts = list;
        }
        this.address = inetSocketAddress;
        this.connections = collection;
        this.iqueue = new LinkedList();
        this.decoders = new ArrayList(i);
        this.buffers = new LinkedBlockingQueue();
        for (int i2 = 0; i2 < i; i2++) {
            WebSocketWorker webSocketWorker = new WebSocketWorker();
            this.decoders.add(webSocketWorker);
            webSocketWorker.start();
        }
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        }
        new Thread(this).start();
    }

    public void stop(int i) throws IOException, InterruptedException {
        if (this.isclosed.compareAndSet(false, true)) {
            synchronized (this.connections) {
                for (WebSocket webSocket : this.connections) {
                    webSocket.close(1001);
                }
            }
            synchronized (this) {
                if (this.selectorthread != null) {
                    Thread.currentThread();
                    Thread thread = this.selectorthread;
                    if (this.selectorthread != Thread.currentThread()) {
                        this.selectorthread.interrupt();
                        this.selectorthread.join();
                    }
                }
                if (this.decoders != null) {
                    for (WebSocketWorker webSocketWorker : this.decoders) {
                        webSocketWorker.interrupt();
                    }
                }
                if (this.server != null) {
                    this.server.close();
                }
            }
        }
    }

    public void stop() throws IOException, InterruptedException {
        stop(0);
    }

    public Collection<WebSocket> connections() {
        return this.connections;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        ServerSocketChannel serverSocketChannel;
        int port = getAddress().getPort();
        return (port != 0 || (serverSocketChannel = this.server) == null) ? port : serverSocketChannel.socket().getLocalPort();
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0182 A[Catch: RuntimeException -> 0x018a, TRY_ENTER, TryCatch #5 {RuntimeException -> 0x018a, blocks: (B:12:0x0062, B:14:0x006a, B:16:0x007b, B:18:0x0081, B:19:0x0087, B:22:0x008e, B:24:0x0095, B:26:0x009b, B:27:0x009f, B:29:0x00ce, B:31:0x00d4, B:32:0x00da, B:43:0x010d, B:44:0x0110, B:46:0x0112, B:47:0x0115, B:82:0x0182, B:83:0x0185, B:50:0x011a, B:52:0x0120, B:53:0x0126, B:55:0x012e, B:57:0x0134, B:62:0x013e, B:64:0x0146, B:65:0x014e, B:70:0x0169, B:72:0x016f, B:73:0x0172), top: B:104:0x0062 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.run():void");
    }

    protected void allocateBuffers(WebSocket webSocket) throws InterruptedException {
        if (this.queuesize.get() >= (this.decoders.size() * 2) + 1) {
            return;
        }
        this.queuesize.incrementAndGet();
        this.buffers.put(createBuffer());
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(WebSocketImpl.RCVBUF);
    }

    private void queue(WebSocketImpl webSocketImpl) throws InterruptedException {
        if (webSocketImpl.workerThread == null) {
            List<WebSocketWorker> list = this.decoders;
            webSocketImpl.workerThread = list.get(this.queueinvokes % list.size());
            this.queueinvokes++;
        }
        webSocketImpl.workerThread.put(webSocketImpl);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return this.buffers.take();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushBuffer(ByteBuffer byteBuffer) throws InterruptedException {
        if (this.buffers.size() > this.queuesize.intValue()) {
            return;
        }
        this.buffers.put(byteBuffer);
    }

    private void handleIOException(SelectionKey selectionKey, WebSocket webSocket, IOException iOException) {
        SelectableChannel channel;
        onWebsocketError(webSocket, iOException);
        if (webSocket != null) {
            webSocket.closeConnection(1006, iOException.getMessage());
        } else if (selectionKey == null || (channel = selectionKey.channel()) == null || !channel.isOpen()) {
        } else {
            try {
                channel.close();
            } catch (IOException unused) {
            }
            if (WebSocketImpl.DEBUG) {
                PrintStream printStream = System.out;
                printStream.println("Connection closed because of" + iOException);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFatal(WebSocket webSocket, Exception exc) {
        onError(webSocket, exc);
        try {
            stop();
        } catch (IOException e) {
            onError(null, e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            onError(null, e2);
        }
    }

    protected String getFlashSecurityPolicy() {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + getPort() + "\" /></cross-domain-policy>";
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(webSocket, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(webSocket, byteBuffer);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        if (addConnection(webSocket)) {
            onOpen(webSocket, (ClientHandshake) handshakedata);
        }
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.selector.wakeup();
        try {
            if (removeConnection(webSocket)) {
                onClose(webSocket, i, str, z);
            }
            try {
                releaseBuffers(webSocket);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } catch (Throwable th) {
            try {
                releaseBuffers(webSocket);
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    protected boolean removeConnection(WebSocket webSocket) {
        boolean remove;
        synchronized (this.connections) {
            remove = this.connections.remove(webSocket);
        }
        return remove;
    }

    protected boolean addConnection(WebSocket webSocket) {
        boolean add;
        synchronized (this.connections) {
            add = this.connections.add(webSocket);
        }
        return add;
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(webSocket, exc);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWriteDemand(WebSocket webSocket) {
        WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
        try {
            webSocketImpl.key.interestOps(5);
        } catch (CancelledKeyException unused) {
            webSocketImpl.outQueue.clear();
        }
        this.selector.wakeup();
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(webSocket, i, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(webSocket, i, str, z);
    }

    public final void setWebSocketFactory(WebSocketServerFactory webSocketServerFactory) {
        this.wsf = webSocketServerFactory;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    private Socket getSocket(WebSocket webSocket) {
        return ((SocketChannel) ((WebSocketImpl) webSocket).key.channel()).socket();
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getLocalSocketAddress();
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getRemoteSocketAddress();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class WebSocketWorker extends Thread {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();

        public WebSocketWorker() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: org.java_websocket.server.WebSocketServer.WebSocketWorker.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, th);
                }
            });
        }

        public void put(WebSocketImpl webSocketImpl) throws InterruptedException {
            this.iqueue.put(webSocketImpl);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WebSocketImpl webSocketImpl;
            RuntimeException e;
            WebSocketImpl webSocketImpl2 = null;
            while (true) {
                try {
                    try {
                        webSocketImpl = this.iqueue.take();
                    } catch (InterruptedException unused) {
                        return;
                    }
                } catch (RuntimeException e2) {
                    webSocketImpl = webSocketImpl2;
                    e = e2;
                }
                try {
                    ByteBuffer poll = webSocketImpl.inQueue.poll();
                    try {
                        webSocketImpl.decode(poll);
                        WebSocketServer.this.pushBuffer(poll);
                        webSocketImpl2 = webSocketImpl;
                    } catch (Throwable th) {
                        WebSocketServer.this.pushBuffer(poll);
                        throw th;
                    }
                } catch (RuntimeException e3) {
                    e = e3;
                    WebSocketServer.this.handleFatal(webSocketImpl, e);
                    return;
                }
            }
        }
    }
}
