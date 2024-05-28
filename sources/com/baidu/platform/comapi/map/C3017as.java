package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.baidu.platform.comapi.map.C3006ai;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.as */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3017as implements InterfaceSurfaceHolder$Callback2C3005ah {

    /* renamed from: b */
    protected boolean f7751b;

    /* renamed from: c */
    private int f7752c;

    /* renamed from: d */
    private C3018a f7753d;

    /* renamed from: g */
    private WeakReference<SurfaceView> f7756g;

    /* renamed from: e */
    private boolean f7754e = true;

    /* renamed from: f */
    private final WeakReference<C3017as> f7755f = new WeakReference<>(this);

    /* renamed from: a */
    protected InterfaceC3015aq f7750a = null;

    /* renamed from: h */
    private int f7757h = 60;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.as$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C3018a extends Thread {

        /* renamed from: b */
        private WeakReference<C3017as> f7759b;

        /* renamed from: e */
        private boolean f7762e;

        /* renamed from: g */
        private boolean f7764g;

        /* renamed from: m */
        private InterfaceC3015aq f7770m;

        /* renamed from: n */
        private SurfaceHolder f7771n;

        /* renamed from: p */
        private Runnable f7773p;

        /* renamed from: r */
        private long f7775r;

        /* renamed from: c */
        private Object f7760c = new Object();

        /* renamed from: f */
        private boolean f7763f = false;

        /* renamed from: h */
        private boolean f7765h = false;

        /* renamed from: o */
        private ArrayList<Runnable> f7772o = new ArrayList<>();

        /* renamed from: q */
        private AtomicBoolean f7774q = new AtomicBoolean(false);

        /* renamed from: d */
        private boolean f7761d = false;

        /* renamed from: i */
        private int f7766i = 0;

        /* renamed from: j */
        private int f7767j = 0;

        /* renamed from: l */
        private boolean f7769l = true;

        /* renamed from: k */
        private int f7768k = 1;

        public C3018a(WeakReference<C3017as> weakReference) {
            this.f7775r = 0L;
            this.f7759b = weakReference;
            C3017as c3017as = weakReference.get();
            this.f7770m = c3017as.f7750a;
            this.f7771n = c3017as.m17935a();
            this.f7775r = VulkanDetect.getNativeWindow(this.f7771n.getSurface());
            setPriority(10);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r7.f7773p == null) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006a, code lost:
            r0 = r7.f7773p;
            r7.f7773p = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006f, code lost:
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0070, code lost:
            if (r1 <= 0) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
            if (r2 <= 0) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            r1 = java.lang.System.currentTimeMillis();
            r7.f7770m.mo17724a(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x007d, code lost:
            if (r0 == null) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007f, code lost:
            r0.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0082, code lost:
            r0 = ((com.baidu.platform.comapi.map.C3017as) r7.f7758a.f7755f.get()).mo17844e();
            r3 = java.lang.System.currentTimeMillis();
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0098, code lost:
            if (r0 >= 60) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
            if (r0 <= 0) goto L61;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
            r5 = (1000 / r0) - (r3 - r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00a6, code lost:
            if (r5 <= 1) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
            r0 = r7.f7760c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00aa, code lost:
            monitor-enter(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00ab, code lost:
            r7.f7760c.wait(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00b0, code lost:
            monitor-exit(r0);
         */
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void m17919f() throws java.lang.InterruptedException {
            /*
                r7 = this;
            L0:
                boolean r0 = r7.f7761d     // Catch: java.lang.Throwable -> Lc1
                if (r0 != 0) goto Lb9
                java.lang.Object r0 = r7.f7760c     // Catch: java.lang.Throwable -> Lc1
                monitor-enter(r0)     // Catch: java.lang.Throwable -> Lc1
            L7:
                java.lang.Runnable r1 = r7.m17917h()     // Catch: java.lang.Throwable -> Lb6
                if (r1 == 0) goto L11
                r1.run()     // Catch: java.lang.Throwable -> Lb6
                goto L7
            L11:
                boolean r1 = r7.m17918g()     // Catch: java.lang.Throwable -> Lb6
                if (r1 == 0) goto L40
                boolean r1 = r7.f7763f     // Catch: java.lang.Throwable -> Lb6
                r2 = 1
                if (r1 != 0) goto L27
                boolean r1 = r7.f7764g     // Catch: java.lang.Throwable -> Lb6
                if (r1 != 0) goto L27
                r7.f7764g = r2     // Catch: java.lang.Throwable -> Lb6
                java.lang.Object r1 = r7.f7760c     // Catch: java.lang.Throwable -> Lb6
                r1.notifyAll()     // Catch: java.lang.Throwable -> Lb6
            L27:
                com.baidu.platform.comapi.map.as r1 = com.baidu.platform.comapi.map.C3017as.this     // Catch: java.lang.Throwable -> Lb6
                boolean r1 = com.baidu.platform.comapi.map.C3017as.m17934a(r1)     // Catch: java.lang.Throwable -> Lb6
                if (r1 == 0) goto L3a
                boolean r1 = r7.f7765h     // Catch: java.lang.Throwable -> Lb6
                if (r1 != 0) goto L3a
                r7.f7765h = r2     // Catch: java.lang.Throwable -> Lb6
                java.lang.Object r1 = r7.f7760c     // Catch: java.lang.Throwable -> Lb6
                r1.notifyAll()     // Catch: java.lang.Throwable -> Lb6
            L3a:
                java.lang.Object r1 = r7.f7760c     // Catch: java.lang.Throwable -> Lb6
                r1.wait()     // Catch: java.lang.Throwable -> Lb6
                goto L11
            L40:
                boolean r1 = r7.f7761d     // Catch: java.lang.Throwable -> Lb6
                if (r1 == 0) goto L47
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb6
                goto Lb9
            L47:
                com.baidu.platform.comapi.map.as r1 = com.baidu.platform.comapi.map.C3017as.this     // Catch: java.lang.Throwable -> Lb6
                com.baidu.platform.comapi.map.C3017as.m17934a(r1)     // Catch: java.lang.Throwable -> Lb6
                int r1 = r7.f7766i     // Catch: java.lang.Throwable -> Lb6
                int r2 = r7.f7767j     // Catch: java.lang.Throwable -> Lb6
                com.baidu.platform.comapi.map.as r3 = com.baidu.platform.comapi.map.C3017as.this     // Catch: java.lang.Throwable -> Lb6
                r4 = 0
                com.baidu.platform.comapi.map.C3017as.m17933a(r3, r4)     // Catch: java.lang.Throwable -> Lb6
                r7.f7765h = r4     // Catch: java.lang.Throwable -> Lb6
                r7.f7769l = r4     // Catch: java.lang.Throwable -> Lb6
                boolean r3 = r7.f7763f     // Catch: java.lang.Throwable -> Lb6
                if (r3 == 0) goto L64
                boolean r3 = r7.f7764g     // Catch: java.lang.Throwable -> Lb6
                if (r3 == 0) goto L64
                r7.f7764g = r4     // Catch: java.lang.Throwable -> Lb6
            L64:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb6
                java.lang.Runnable r0 = r7.f7773p     // Catch: java.lang.Throwable -> Lc1
                r3 = 0
                if (r0 == 0) goto L6f
                java.lang.Runnable r0 = r7.f7773p     // Catch: java.lang.Throwable -> Lc1
                r7.f7773p = r3     // Catch: java.lang.Throwable -> Lc1
                goto L70
            L6f:
                r0 = r3
            L70:
                if (r1 <= 0) goto L0
                if (r2 <= 0) goto L0
                long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lc1
                com.baidu.platform.comapi.map.aq r3 = r7.f7770m     // Catch: java.lang.Throwable -> Lc1
                r3.mo17724a(r7)     // Catch: java.lang.Throwable -> Lc1
                if (r0 == 0) goto L82
                r0.run()     // Catch: java.lang.Throwable -> Lc1
            L82:
                com.baidu.platform.comapi.map.as r0 = com.baidu.platform.comapi.map.C3017as.this     // Catch: java.lang.Throwable -> Lc1
                java.lang.ref.WeakReference r0 = com.baidu.platform.comapi.map.C3017as.m17932b(r0)     // Catch: java.lang.Throwable -> Lc1
                java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> Lc1
                com.baidu.platform.comapi.map.as r0 = (com.baidu.platform.comapi.map.C3017as) r0     // Catch: java.lang.Throwable -> Lc1
                int r0 = r0.mo17844e()     // Catch: java.lang.Throwable -> Lc1
                long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lc1
                r5 = 60
                if (r0 >= r5) goto L0
                if (r0 <= 0) goto L0
                r5 = 1000(0x3e8, float:1.401E-42)
                int r5 = r5 / r0
                long r5 = (long) r5     // Catch: java.lang.Throwable -> Lc1
                long r3 = r3 - r1
                long r5 = r5 - r3
                r0 = 1
                int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r0 <= 0) goto L0
                java.lang.Object r0 = r7.f7760c     // Catch: java.lang.Throwable -> Lc1
                monitor-enter(r0)     // Catch: java.lang.Throwable -> Lc1
                java.lang.Object r1 = r7.f7760c     // Catch: java.lang.Throwable -> Lb3
                r1.wait(r5)     // Catch: java.lang.Throwable -> Lb3
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb3
                goto L0
            Lb3:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb3
                throw r1     // Catch: java.lang.Throwable -> Lc1
            Lb6:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb6
                throw r1     // Catch: java.lang.Throwable -> Lc1
            Lb9:
                java.lang.String r0 = "VulkanSurfaceView"
                java.lang.String r1 = "destroySurface"
                android.util.Log.i(r0, r1)
                return
            Lc1:
                r0 = move-exception
                java.lang.String r1 = "VulkanSurfaceView"
                java.lang.String r2 = "destroySurface"
                android.util.Log.i(r1, r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.C3017as.C3018a.m17919f():void");
        }

        /* renamed from: g */
        private boolean m17918g() {
            if (this.f7761d) {
                return false;
            }
            return this.f7762e || !this.f7763f || C3017as.this.f7754e || this.f7766i <= 0 || this.f7767j <= 0 || !(this.f7769l || this.f7768k == 1);
        }

        /* renamed from: h */
        private Runnable m17917h() {
            synchronized (this) {
                if (this.f7772o.size() > 0) {
                    return this.f7772o.remove(0);
                }
                return null;
            }
        }

        /* renamed from: a */
        public int m17930a() {
            int i;
            synchronized (this.f7760c) {
                i = this.f7768k;
            }
            return i;
        }

        /* renamed from: a */
        public void m17929a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (this.f7760c) {
                this.f7768k = i;
                if (i == 1) {
                    this.f7760c.notifyAll();
                }
            }
        }

        /* renamed from: a */
        public void m17928a(SurfaceHolder surfaceHolder) {
            synchronized (this.f7760c) {
                long nativeWindow = VulkanDetect.getNativeWindow(surfaceHolder.getSurface());
                if (this.f7775r != nativeWindow) {
                    this.f7775r = nativeWindow;
                    this.f7774q.set(true);
                    this.f7770m.mo17731a(surfaceHolder, 1, 1, 1);
                }
                this.f7763f = true;
                this.f7760c.notifyAll();
            }
        }

        /* renamed from: a */
        public void m17927a(SurfaceHolder surfaceHolder, int i, int i2) {
            synchronized (this.f7760c) {
                this.f7766i = i;
                this.f7767j = i2;
                C3017as.this.f7754e = true;
                this.f7760c.notifyAll();
                while (!this.f7765h && isAlive()) {
                    try {
                        this.f7760c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                this.f7770m.mo17733a(i, i2);
                C3017as.this.f7754e = false;
                this.f7760c.notifyAll();
            }
        }

        /* renamed from: a */
        public void m17926a(Runnable runnable) {
            synchronized (this.f7760c) {
                if (Thread.currentThread() == this) {
                    return;
                }
                this.f7769l = true;
                this.f7773p = runnable;
                this.f7760c.notifyAll();
            }
        }

        /* renamed from: b */
        public void m17925b() {
            synchronized (this.f7760c) {
                this.f7769l = true;
                this.f7760c.notifyAll();
            }
        }

        /* renamed from: b */
        public void m17924b(SurfaceHolder surfaceHolder) {
            synchronized (this.f7760c) {
                this.f7763f = false;
                this.f7760c.notifyAll();
                while (!this.f7764g && isAlive()) {
                    try {
                        this.f7760c.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                this.f7770m.mo17732a(surfaceHolder);
            }
        }

        /* renamed from: b */
        public void m17923b(Runnable runnable) {
            synchronized (this) {
                this.f7772o.add(runnable);
            }
        }

        /* renamed from: c */
        public void m17922c() {
            synchronized (this.f7760c) {
                this.f7762e = true;
            }
        }

        /* renamed from: d */
        public void m17921d() {
            synchronized (this.f7760c) {
                this.f7762e = false;
                this.f7760c.notifyAll();
            }
        }

        /* renamed from: e */
        public void m17920e() {
            synchronized (this.f7760c) {
                this.f7761d = true;
                this.f7760c.notifyAll();
            }
            try {
                join();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("VkThread " + getId());
            try {
                m17919f();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public C3017as(SurfaceView surfaceView) {
        this.f7756g = new WeakReference<>(surfaceView);
    }

    /* renamed from: c */
    private void m17931c() {
        if (this.f7753d != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public Bitmap mo17862a(int i, int i2, int i3, int i4, Object obj, Bitmap.Config config) {
        return null;
    }

    /* renamed from: a */
    public SurfaceHolder m17935a() {
        return this.f7756g.get().getHolder();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17864a(int i) {
        if (i <= 0) {
            return;
        }
        if (i > 60) {
            i = 60;
        }
        this.f7757h = i;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17860a(InterfaceC3015aq interfaceC3015aq) {
        m17931c();
        this.f7750a = interfaceC3015aq;
        this.f7753d = new C3018a(this.f7755f);
        this.f7753d.start();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: a */
    public void mo17856a(Runnable runnable) {
        this.f7753d.m17923b(runnable);
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: b */
    public C3006ai.EnumC3007a mo17854b() {
        return C3006ai.EnumC3007a.VULKAN;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: b */
    public void mo17853b(int i) {
        this.f7752c = i;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: d */
    public void mo17846d(int i) {
        this.f7753d.m17929a(i);
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: e */
    public int mo17844e() {
        return this.f7757h;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: f */
    public int mo17842f() {
        return this.f7752c;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f7753d != null) {
                this.f7753d.m17920e();
            }
        } finally {
            super.finalize();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: g */
    public int mo17840g() {
        return this.f7753d.m17930a();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: h */
    public void mo17838h() {
        this.f7753d.m17925b();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: i */
    public void mo17836i() {
        this.f7753d.m17922c();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: j */
    public void mo17835j() {
        this.f7753d.m17921d();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: k */
    public void mo17834k() {
        if (this.f7751b && this.f7750a != null) {
            C3018a c3018a = this.f7753d;
            int m17930a = c3018a != null ? c3018a.m17930a() : 1;
            this.f7753d = new C3018a(this.f7755f);
            if (m17930a != 1) {
                this.f7753d.m17929a(m17930a);
            }
            this.f7753d.start();
        }
        this.f7751b = false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceSurfaceHolder$Callback2C3005ah
    /* renamed from: l */
    public void mo17833l() {
        C3018a c3018a = this.f7753d;
        if (c3018a != null) {
            c3018a.m17920e();
        }
        this.f7751b = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f7753d.m17927a(surfaceHolder, i2, i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f7753d.m17928a(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f7753d.m17924b(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        C3018a c3018a = this.f7753d;
        if (c3018a != null) {
            c3018a.m17926a(runnable);
        }
    }
}
