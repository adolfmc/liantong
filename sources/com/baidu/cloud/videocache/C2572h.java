package com.baidu.cloud.videocache;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2572h {

    /* renamed from: a */
    private static final Logger f4884a = LoggerFactory.getLogger("ProxyCache");

    /* renamed from: e */
    private final Source f4888e;

    /* renamed from: f */
    private final Cache f4889f;

    /* renamed from: h */
    private volatile boolean f4891h;

    /* renamed from: i */
    private volatile Thread f4892i;

    /* renamed from: j */
    private volatile boolean f4893j;

    /* renamed from: g */
    private final Object f4890g = new Object();

    /* renamed from: b */
    protected final Object f4885b = new Object();

    /* renamed from: d */
    protected volatile int f4887d = -1;

    /* renamed from: c */
    protected final AtomicInteger f4886c = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.videocache.h$oia */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class oia implements Runnable {
        private oia() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C2572h.this.m19787j();
        }
    }

    public C2572h(Source source, Cache cache) {
        this.f4888e = (Source) C2571g.m19807a(source);
        this.f4889f = (Cache) C2571g.m19807a(cache);
    }

    /* renamed from: g */
    private void m19790g() {
        int i = this.f4886c.get();
        if (i < 1) {
            return;
        }
        this.f4886c.set(0);
        throw new C2574j("Error reading source " + i + " times");
    }

    /* renamed from: h */
    private synchronized void m19789h() {
        this.f4891h = (this.f4892i == null || this.f4892i.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.f4893j && !this.f4889f.isCompleted() && !this.f4891h) {
            oia oiaVar = new oia();
            this.f4892i = new Thread(oiaVar, "Source reader for " + this.f4888e);
            this.f4892i.start();
            this.f4891h = true;
        }
    }

    /* renamed from: i */
    private void m19788i() {
        synchronized (this.f4890g) {
            try {
                try {
                    this.f4890g.wait(1000L);
                } catch (InterruptedException e) {
                    throw new C2574j("Waiting source data is interrupted!", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m19787j() {
        long j = -1;
        long j2 = 0;
        try {
            j2 = this.f4889f.available();
            this.f4888e.open(j2);
            j = this.f4888e.length();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = this.f4888e.read(bArr);
                if (read == -1) {
                    m19793d();
                    m19794c();
                    break;
                }
                synchronized (this.f4885b) {
                    if (m19792e()) {
                        return;
                    }
                    this.f4889f.append(bArr, read);
                }
                j2 += read;
                m19800a(j2, j);
            }
        } finally {
            try {
                m19791f();
                m19800a(j2, j);
                this.f4891h = false;
            } finally {
            }
        }
    }

    /* renamed from: a */
    public int mo19797a(byte[] bArr, long j, int i) {
        C2576l.m19782a(bArr, j, i);
        while (!this.f4889f.isCompleted() && this.f4889f.available() < i + j && !this.f4893j) {
            m19789h();
            m19788i();
            m19790g();
        }
        int read = this.f4889f.read(bArr, j, i);
        if (this.f4889f.isCompleted() && this.f4887d != 100) {
            this.f4887d = 100;
            mo19801a(100);
        }
        return read;
    }

    /* renamed from: a */
    protected void mo19801a(int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m19800a(long j, long j2) {
        m19795b(j, j2);
        synchronized (this.f4890g) {
            this.f4890g.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m19798a(Throwable th) {
        if (th instanceof C2566d) {
            f4884a.debug("ProxyCache is interrupted");
        } else {
            f4884a.error("ProxyCache error", th);
        }
    }

    /* renamed from: a */
    public boolean mo19802a() {
        return this.f4891h;
    }

    /* renamed from: b */
    public void mo19796b() {
        synchronized (this.f4885b) {
            Logger logger = f4884a;
            logger.debug("Shutdown proxy for " + this.f4888e);
            try {
                this.f4893j = true;
                this.f4891h = false;
                if (this.f4892i != null) {
                    this.f4892i.interrupt();
                }
                this.f4889f.close();
            } catch (C2574j e) {
                m19798a(e);
            }
        }
    }

    /* renamed from: b */
    protected void m19795b(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        int i2 = i == 0 ? 100 : (int) ((((float) j) / ((float) j2)) * 100.0f);
        boolean z = i2 != this.f4887d;
        if ((i >= 0) && z) {
            mo19801a(i2);
        }
        this.f4887d = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m19794c() {
        this.f4887d = 100;
        mo19801a(this.f4887d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m19793d() {
        synchronized (this.f4885b) {
            if (!m19792e() && this.f4889f.available() == this.f4888e.length()) {
                this.f4889f.complete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public boolean m19792e() {
        return Thread.currentThread().isInterrupted() || this.f4893j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public void m19791f() {
        try {
            this.f4888e.close();
        } catch (C2574j e) {
            m19798a(new C2574j("Error closing source " + this.f4888e, e));
        }
    }
}
