package com.xiaomi.push.service;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: com.xiaomi.push.service.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11605n {

    /* renamed from: a */
    private static long f23701a;

    /* renamed from: b */
    private static long f23702b;

    /* renamed from: c */
    private static long f23703c;

    /* renamed from: a */
    private final C11607a f23704a;

    /* renamed from: a */
    private final C11609c f23705a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.n$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractRunnableC11608b implements Runnable {

        /* renamed from: a */
        protected int f23707a;

        public AbstractRunnableC11608b(int i) {
            this.f23707a = i;
        }
    }

    static {
        f23701a = SystemClock.elapsedRealtime() > 0 ? SystemClock.elapsedRealtime() : 0L;
        f23702b = f23701a;
    }

    /* renamed from: a */
    static synchronized long m2475a() {
        long j;
        synchronized (C11605n.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > f23702b) {
                f23701a += elapsedRealtime - f23702b;
            }
            f23702b = elapsedRealtime;
            j = f23701a;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.n$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11611d {

        /* renamed from: a */
        int f23718a;

        /* renamed from: a */
        long f23719a;

        /* renamed from: a */
        AbstractRunnableC11608b f23720a;

        /* renamed from: a */
        final Object f23721a = new Object();

        /* renamed from: a */
        boolean f23722a;

        /* renamed from: b */
        private long f23723b;

        C11611d() {
        }

        /* renamed from: a */
        void m2443a(long j) {
            synchronized (this.f23721a) {
                this.f23723b = j;
            }
        }

        /* renamed from: a */
        public boolean m2444a() {
            boolean z;
            synchronized (this.f23721a) {
                z = !this.f23722a && this.f23719a > 0;
                this.f23722a = true;
            }
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.n$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11609c extends Thread {

        /* renamed from: b */
        private boolean f23712b;

        /* renamed from: c */
        private boolean f23713c;

        /* renamed from: a */
        private volatile long f23708a = 0;

        /* renamed from: a */
        private volatile boolean f23710a = false;

        /* renamed from: b */
        private long f23711b = 50;

        /* renamed from: a */
        private C11610a f23709a = new C11610a();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.push.service.n$c$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static final class C11610a {

            /* renamed from: a */
            private int f23714a;

            /* renamed from: a */
            private C11611d[] f23715a;

            /* renamed from: b */
            private int f23716b;

            /* renamed from: c */
            private int f23717c;

            private C11610a() {
                this.f23714a = 256;
                this.f23715a = new C11611d[this.f23714a];
                this.f23716b = 0;
                this.f23717c = 0;
            }

            /* renamed from: a */
            public C11611d m2457a() {
                return this.f23715a[0];
            }

            /* renamed from: a */
            public boolean m2455a() {
                return this.f23716b == 0;
            }

            /* renamed from: a */
            public void m2449a(C11611d c11611d) {
                C11611d[] c11611dArr = this.f23715a;
                int length = c11611dArr.length;
                int i = this.f23716b;
                if (length == i) {
                    C11611d[] c11611dArr2 = new C11611d[i * 2];
                    System.arraycopy(c11611dArr, 0, c11611dArr2, 0, i);
                    this.f23715a = c11611dArr2;
                }
                C11611d[] c11611dArr3 = this.f23715a;
                int i2 = this.f23716b;
                this.f23716b = i2 + 1;
                c11611dArr3[i2] = c11611d;
                m2446c();
            }

            /* renamed from: a */
            public boolean m2453a(int i) {
                for (int i2 = 0; i2 < this.f23716b; i2++) {
                    if (this.f23715a[i2].f23718a == i) {
                        return true;
                    }
                }
                return false;
            }

            /* renamed from: a */
            public void m2454a(int i) {
                for (int i2 = 0; i2 < this.f23716b; i2++) {
                    if (this.f23715a[i2].f23718a == i) {
                        this.f23715a[i2].m2444a();
                    }
                }
                m2448b();
            }

            /* renamed from: a */
            public void m2452a(int i, AbstractRunnableC11608b abstractRunnableC11608b) {
                for (int i2 = 0; i2 < this.f23716b; i2++) {
                    if (this.f23715a[i2].f23720a == abstractRunnableC11608b) {
                        this.f23715a[i2].m2444a();
                    }
                }
                m2448b();
            }

            /* renamed from: b */
            public void m2447b(int i) {
                int i2;
                if (i < 0 || i >= (i2 = this.f23716b)) {
                    return;
                }
                C11611d[] c11611dArr = this.f23715a;
                int i3 = i2 - 1;
                this.f23716b = i3;
                c11611dArr[i] = c11611dArr[i3];
                c11611dArr[this.f23716b] = null;
                m2445c(i);
            }

            /* renamed from: c */
            private void m2446c() {
                int i = this.f23716b - 1;
                int i2 = (i - 1) / 2;
                while (this.f23715a[i].f23719a < this.f23715a[i2].f23719a) {
                    C11611d[] c11611dArr = this.f23715a;
                    C11611d c11611d = c11611dArr[i];
                    c11611dArr[i] = c11611dArr[i2];
                    c11611dArr[i2] = c11611d;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            /* renamed from: c */
            private void m2445c(int i) {
                int i2 = (i * 2) + 1;
                while (true) {
                    int i3 = this.f23716b;
                    if (i2 >= i3 || i3 <= 0) {
                        return;
                    }
                    int i4 = i2 + 1;
                    if (i4 < i3 && this.f23715a[i4].f23719a < this.f23715a[i2].f23719a) {
                        i2 = i4;
                    }
                    if (this.f23715a[i].f23719a < this.f23715a[i2].f23719a) {
                        return;
                    }
                    C11611d[] c11611dArr = this.f23715a;
                    C11611d c11611d = c11611dArr[i];
                    c11611dArr[i] = c11611dArr[i2];
                    c11611dArr[i2] = c11611d;
                    int i5 = i2;
                    i2 = (i2 * 2) + 1;
                    i = i5;
                }
            }

            /* renamed from: a */
            public void m2456a() {
                this.f23715a = new C11611d[this.f23714a];
                this.f23716b = 0;
            }

            /* renamed from: b */
            public void m2448b() {
                int i = 0;
                while (i < this.f23716b) {
                    if (this.f23715a[i].f23722a) {
                        this.f23717c++;
                        m2447b(i);
                        i--;
                    }
                    i++;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: a */
            public int m2450a(C11611d c11611d) {
                int i = 0;
                while (true) {
                    C11611d[] c11611dArr = this.f23715a;
                    if (i >= c11611dArr.length) {
                        return -1;
                    }
                    if (c11611dArr[i] == c11611d) {
                        return i;
                    }
                    i++;
                }
            }
        }

        C11609c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* JADX WARN: Code restructure failed: missing block: B:53:0x0099, code lost:
            r10.f23708a = android.os.SystemClock.uptimeMillis();
            r10.f23710a = true;
            r2.f23720a.run();
            r10.f23710a = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00aa, code lost:
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00ab, code lost:
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00ac, code lost:
            r10.f23712b = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00af, code lost:
            throw r1;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 188
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11605n.C11609c.run():void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2458a(C11611d c11611d) {
            this.f23709a.m2449a(c11611d);
            notify();
        }

        /* renamed from: a */
        public synchronized void m2464a() {
            this.f23712b = true;
            this.f23709a.m2456a();
            notify();
        }

        /* renamed from: a */
        public boolean m2463a() {
            return this.f23710a && SystemClock.uptimeMillis() - this.f23708a > 600000;
        }
    }

    /* renamed from: com.xiaomi.push.service.n$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class C11607a {

        /* renamed from: a */
        private final C11609c f23706a;

        C11607a(C11609c c11609c) {
            this.f23706a = c11609c;
        }

        protected void finalize() {
            try {
                synchronized (this.f23706a) {
                    this.f23706a.f23713c = true;
                    this.f23706a.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    /* renamed from: b */
    private static synchronized long m2467b() {
        long j;
        synchronized (C11605n.class) {
            j = f23703c;
            f23703c = 1 + j;
        }
        return j;
    }

    public C11605n(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        this.f23705a = new C11609c(str, z);
        this.f23704a = new C11607a(this.f23705a);
    }

    public C11605n(String str) {
        this(str, false);
    }

    public C11605n(boolean z) {
        this("Timer-" + m2467b(), z);
    }

    public C11605n() {
        this(false);
    }

    /* renamed from: a */
    public void m2474a() {
        AbstractC11049b.m5282a("quit. finalizer:" + this.f23704a);
        this.f23705a.m2464a();
    }

    /* renamed from: a */
    public boolean m2471a(int i) {
        boolean m2453a;
        synchronized (this.f23705a) {
            m2453a = this.f23705a.f23709a.m2453a(i);
        }
        return m2453a;
    }

    /* renamed from: a */
    public void m2472a(int i) {
        synchronized (this.f23705a) {
            this.f23705a.f23709a.m2454a(i);
        }
    }

    /* renamed from: b */
    public void m2466b() {
        synchronized (this.f23705a) {
            this.f23705a.f23709a.m2456a();
        }
    }

    /* renamed from: a */
    public void m2470a(int i, AbstractRunnableC11608b abstractRunnableC11608b) {
        synchronized (this.f23705a) {
            this.f23705a.f23709a.m2452a(i, abstractRunnableC11608b);
        }
    }

    /* renamed from: a */
    public boolean m2473a() {
        return this.f23705a.m2463a();
    }

    /* renamed from: a */
    public void m2469a(AbstractRunnableC11608b abstractRunnableC11608b) {
        if (AbstractC11049b.m5295a() < 1 && Thread.currentThread() != this.f23705a) {
            AbstractC11049b.m5268d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        } else {
            abstractRunnableC11608b.run();
        }
    }

    /* renamed from: a */
    public void m2468a(AbstractRunnableC11608b abstractRunnableC11608b, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("delay < 0: " + j);
        }
        m2465b(abstractRunnableC11608b, j);
    }

    /* renamed from: b */
    private void m2465b(AbstractRunnableC11608b abstractRunnableC11608b, long j) {
        synchronized (this.f23705a) {
            if (this.f23705a.f23712b) {
                throw new IllegalStateException("Timer was canceled");
            }
            long m2475a = j + m2475a();
            if (m2475a < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + m2475a);
            }
            C11611d c11611d = new C11611d();
            c11611d.f23718a = abstractRunnableC11608b.f23707a;
            c11611d.f23720a = abstractRunnableC11608b;
            c11611d.f23719a = m2475a;
            this.f23705a.m2458a(c11611d);
        }
    }
}
