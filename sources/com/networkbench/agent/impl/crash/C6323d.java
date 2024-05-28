package com.networkbench.agent.impl.crash;

import android.os.Process;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p241b.C6229b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6653u;
import java.lang.Thread;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6323d {

    /* renamed from: a */
    private static HashSet<InterfaceC6327f> f15919a = new HashSet<>();

    /* renamed from: b */
    private static Thread.UncaughtExceptionHandler f15920b = null;

    /* renamed from: c */
    private static final InterfaceC6393e f15921c = C6394f.m10150a();

    /* renamed from: d */
    private static volatile boolean f15922d = false;

    /* renamed from: a */
    public static void m10408a(InterfaceC6327f interfaceC6327f) {
        m10402e();
        if (interfaceC6327f != null) {
            f15919a.add(interfaceC6327f);
        }
    }

    /* renamed from: e */
    private static void m10402e() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == null || !(defaultUncaughtExceptionHandler instanceof C6324a)) {
            f15920b = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(C6324a.m10401a());
            f15921c.mo10122a("Registered tingyun crash handler");
        }
    }

    /* renamed from: a */
    private static String m10407a(Thread thread) {
        return thread.getClass().getName() + "[name=" + thread.getName() + ", id=" + thread.getId() + ", pid=" + Process.myPid() + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.crash.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6324a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a */
        private static C6324a f15923a = new C6324a();

        private C6324a() {
        }

        /* renamed from: a */
        static C6324a m10401a() {
            return f15923a;
        }

        /* renamed from: b */
        private boolean m10399b() {
            return C6653u.m8703f() == 1;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (C6323d.f15922d) {
                C6323d.f15921c.mo10115e("crash is reporting,stop report new crash");
                return;
            }
            try {
                if (!m10399b()) {
                    C6323d.f15921c.mo10122a("init failed ,set feature 0");
                    C6653u.m8730b(0);
                }
            } catch (Throwable unused) {
                C6323d.f15921c.mo10115e("process error");
            }
            if (C6459b.m9936d()) {
                C6321c.f15902a = C6330i.m10379a();
                boolean unused2 = C6323d.f15922d = true;
                try {
                    C6229b.m10939a().m10938b().mo10928d();
                    if (!C6323d.f15919a.isEmpty()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        Iterator it = C6323d.f15919a.iterator();
                        while (it.hasNext()) {
                            ((InterfaceC6327f) it.next()).mo10382a(thread, th, TimeUnit.SECONDS.convert(currentTimeMillis, TimeUnit.MILLISECONDS));
                        }
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        /* renamed from: a */
        private void m10400a(Thread thread, Throwable th) {
            if (C6323d.f15920b != null) {
                InterfaceC6393e interfaceC6393e = C6323d.f15921c;
                interfaceC6393e.mo10115e("execute user UncaughtExceptionHandler,handler class is " + C6323d.f15920b.getClass().getName());
                C6323d.f15920b.uncaughtException(thread, th);
            }
        }
    }
}
