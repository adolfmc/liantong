package com.baidu.cloud.videocache;

import java.io.OutputStream;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2567e {

    /* renamed from: a */
    private static final Logger f4868a = LoggerFactory.getLogger("Pinger");

    /* renamed from: b */
    private final ExecutorService f4869b = Executors.newSingleThreadExecutor();

    /* renamed from: c */
    private final String f4870c;

    /* renamed from: d */
    private final int f4871d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.videocache.e$oia */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class oia implements Callable {
        private oia() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(C2567e.this.m19824b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2567e(String str, int i) {
        this.f4870c = (String) C2571g.m19807a(str);
        this.f4871d = i;
    }

    /* renamed from: a */
    private List m19829a() {
        try {
            return ProxySelector.getDefault().select(new URI(m19823c()));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m19824b() {
        C2564b c2564b = new C2564b(m19823c());
        try {
            byte[] bytes = "ping ok".getBytes();
            c2564b.open(0L);
            byte[] bArr = new byte[bytes.length];
            c2564b.read(bArr);
            boolean equals = Arrays.equals(bytes, bArr);
            Logger logger = f4868a;
            logger.info("Ping response: `" + new String(bArr) + "`, pinged? " + equals);
            return equals;
        } catch (C2574j e) {
            f4868a.error("Error reading ping response", (Throwable) e);
            return false;
        } finally {
            c2564b.close();
        }
    }

    /* renamed from: c */
    private String m19823c() {
        return String.format(Locale.US, "http://%s:%d/%s", this.f4870c, Integer.valueOf(this.f4871d), "ping");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m19825a(Socket socket) {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m19828a(int i, int i2) {
        C2571g.m19805a(i >= 1);
        C2571g.m19805a(i2 > 0);
        int i3 = i2;
        int i4 = 0;
        while (i4 < i) {
            try {
            } catch (InterruptedException e) {
                e = e;
                f4868a.error("Error pinging server due to unexpected error", e);
            } catch (ExecutionException e2) {
                e = e2;
                f4868a.error("Error pinging server due to unexpected error", e);
            } catch (TimeoutException unused) {
                f4868a.warn("Error pinging server (attempt: " + i4 + ", timeout: " + i3 + "). ");
            }
            if (((Boolean) this.f4869b.submit(new oia()).get(i3, TimeUnit.MILLISECONDS)).booleanValue()) {
                return true;
            }
            i4++;
            i3 *= 2;
        }
        String format = String.format(Locale.US, "Error pinging server (attempts: %d, max timeout: %d).  Default proxies are: %s", Integer.valueOf(i4), Integer.valueOf(i3 / 2), m19829a());
        f4868a.error(format, (Throwable) new C2574j(format));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m19826a(String str) {
        return "ping".equals(str);
    }
}
