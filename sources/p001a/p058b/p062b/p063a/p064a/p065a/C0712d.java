package p001a.p058b.p062b.p063a.p064a.p065a;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.crashes.InterfaceC3317d;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p075g.C0760a;
import p001a.p058b.p062b.p063a.p064a.p075g.EnumC0761b;
import p001a.p058b.p062b.p063a.p064a.p081k.ThreadFactoryC0772d;

/* renamed from: a.b.b.a.a.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0712d {

    /* renamed from: d */
    public static InterfaceC3317d f2154d;

    /* renamed from: f */
    public static ExecutorService f2156f;

    /* renamed from: g */
    public Thread.UncaughtExceptionHandler f2157g;

    /* renamed from: h */
    public boolean f2158h;

    /* renamed from: a */
    public static final InterfaceC3321a f2151a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2152b = UAQ.getInstance();

    /* renamed from: c */
    public static final C0712d f2153c = new C0712d();

    /* renamed from: e */
    public static final AtomicBoolean f2155e = new AtomicBoolean(false);

    @NBSInstrumented
    /* renamed from: a.b.b.a.a.a.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class RunnableC0713a implements Runnable {

        /* renamed from: a */
        public final C0710b f2159a;

        public RunnableC0713a(C0710b c0710b) {
            this.f2159a = c0710b;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0198 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v11, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r2v25 */
        /* JADX WARN: Type inference failed for: r2v26 */
        /* JADX WARN: Type inference failed for: r2v28, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r2v9, types: [java.io.InputStream] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 449
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p065a.C0712d.RunnableC0713a.run():void");
        }
    }

    /* renamed from: a.b.b.a.a.a.d$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0714b implements Thread.UncaughtExceptionHandler {

        /* renamed from: a */
        public final AtomicBoolean f2160a = new AtomicBoolean(false);

        public /* synthetic */ C0714b(RunnableC0711c runnableC0711c) {
        }

        /* renamed from: a */
        public final void m22331a(Thread thread, Throwable th) {
            if (C0712d.this.f2157g != null) {
                InterfaceC3321a interfaceC3321a = C0712d.f2151a;
                StringBuilder m24437a = outline.m24437a("Chaining crash reporting duties to ");
                m24437a.append(C0712d.this.f2157g.getClass().getSimpleName());
                interfaceC3321a.mo17428D(m24437a.toString());
                C0712d.this.f2157g.uncaughtException(thread, th);
            }
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            long j;
            if (!this.f2160a.compareAndSet(false, true)) {
                C0760a.f2350a.m22252a("Supportability/AgentHealth/Recursion/UncaughtExceptionHandler");
            } else if (C0712d.f2152b.getConfig().isReportCrashes() && C0712d.f2153c.f2158h) {
                try {
                    EnumC0761b enumC0761b = EnumC0761b.STARTED;
                    long currentTimeMillis = System.currentTimeMillis();
                    InterfaceC3321a interfaceC3321a = C0712d.f2151a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("A crash has been detected in ");
                    sb.append(thread.getStackTrace()[0].getClassName());
                    sb.append(" and will be reported ASAP.");
                    interfaceC3321a.mo17428D(sb.toString());
                    C0710b c0710b = new C0710b(th);
                    C0712d c0712d = C0712d.f2153c;
                    C0712d.f2154d.mo17436b(c0710b);
                    C0712d.f2156f.submit(new RunnableC0713a(c0710b));
                    InterfaceC3321a interfaceC3321a2 = C0712d.f2151a;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Crash collection took ");
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (enumC0761b == EnumC0761b.STARTED) {
                        EnumC0761b enumC0761b2 = EnumC0761b.STOPPED;
                        j = currentTimeMillis2 - currentTimeMillis;
                    } else {
                        j = -1;
                    }
                    sb2.append(j);
                    sb2.append("ms");
                    interfaceC3321a2.mo17428D(sb2.toString());
                    m22331a(thread, th);
                } catch (Throwable unused) {
                    m22331a(thread, th);
                }
            } else {
                C0712d.f2151a.mo17428D("A crash has been detected but crash reporting is disabled!");
                m22331a(thread, th);
            }
        }
    }

    public C0712d() {
        f2156f = Executors.newCachedThreadPool(new ThreadFactoryC0772d("CrashReporter"));
        this.f2158h = false;
    }

    /* renamed from: a */
    public final void m22336a() {
        InterfaceC3321a interfaceC3321a = f2151a;
        StringBuilder m24437a = outline.m24437a("reportSavedCrashes, size=");
        m24437a.append(f2154d.count());
        interfaceC3321a.mo17428D(m24437a.toString());
        for (C0710b c0710b : f2154d.mo17437L()) {
            f2156f.submit(new RunnableC0713a(c0710b));
        }
    }

    /* renamed from: b */
    public final void m22334b() {
        InterfaceC3321a interfaceC3321a;
        String str;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == null) {
            interfaceC3321a = f2151a;
            str = "Installing UAQ crash handler.";
        } else if (defaultUncaughtExceptionHandler instanceof C0714b) {
            f2151a.mo17428D("UAQ crash handler already installed.");
            return;
        } else {
            this.f2157g = defaultUncaughtExceptionHandler;
            interfaceC3321a = f2151a;
            StringBuilder m24437a = outline.m24437a("Installing UAQ crash handler and chaining ");
            m24437a.append(this.f2157g.getClass().getName());
            str = m24437a.toString();
        }
        interfaceC3321a.mo17428D(str);
        Thread.setDefaultUncaughtExceptionHandler(new C0714b(null));
    }
}
