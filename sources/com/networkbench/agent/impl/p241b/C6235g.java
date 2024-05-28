package com.networkbench.agent.impl.p241b;

import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Printer;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6645n;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6235g implements MessageQueue.IdleHandler {

    /* renamed from: a */
    private static final C6235g f15426a = new C6235g();

    /* renamed from: b */
    private static final InterfaceC6393e f15427b = C6394f.m10150a();

    /* renamed from: c */
    private static final Set<AbstractC6237a> f15428c = Collections.newSetFromMap(new ConcurrentHashMap());

    /* renamed from: d */
    private static Printer f15429d;

    private C6235g() {
        m10923b();
        if (Build.VERSION.SDK_INT >= 23) {
            Looper.getMainLooper().getQueue().addIdleHandler(this);
            return;
        }
        try {
            ((MessageQueue) C6645n.m8879a(Looper.getMainLooper(), "mQueue")).addIdleHandler(this);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15427b;
            interfaceC6393e.mo10115e("LooperMonitor get mQueue failed e:" + e.getMessage());
        }
    }

    /* renamed from: b */
    private static void m10923b() {
        try {
            final Printer printer = (Printer) C6645n.m8879a(Looper.getMainLooper(), "mLogging");
            if (printer != f15429d || f15429d == null) {
                if (f15429d != null) {
                    f15427b.mo10115e("maybe looper printer was replace other!");
                }
                Looper mainLooper = Looper.getMainLooper();
                Printer printer2 = new Printer() { // from class: com.networkbench.agent.impl.b.g.1

                    /* renamed from: a */
                    boolean f15430a = false;

                    /* renamed from: b */
                    boolean f15431b = false;

                    /* JADX WARN: Removed duplicated region for block: B:22:0x0040 A[Catch: Throwable -> 0x0067, TryCatch #0 {Throwable -> 0x0067, blocks: (B:2:0x0000, B:4:0x0004, B:6:0x000b, B:8:0x0015, B:10:0x0019, B:11:0x001e, B:13:0x0026, B:15:0x002c, B:20:0x0038, B:22:0x0040, B:23:0x0058, B:25:0x005c, B:29:0x0064), top: B:32:0x0000 }] */
                    @Override // android.util.Printer
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void println(java.lang.String r7) {
                        /*
                            r6 = this;
                            android.util.Printer r0 = r1     // Catch: java.lang.Throwable -> L67
                            if (r0 == 0) goto L15
                            r0 = 3
                            boolean r0 = com.networkbench.agent.impl.p241b.C6235g.m10926a(r0)     // Catch: java.lang.Throwable -> L67
                            if (r0 == 0) goto L15
                            com.networkbench.agent.impl.f.e r7 = com.networkbench.agent.impl.p241b.C6235g.m10927a()     // Catch: java.lang.Throwable -> L67
                            java.lang.String r0 = "isNetworkbenchPrintlnCrycle(3) return true"
                            r7.mo10122a(r0)     // Catch: java.lang.Throwable -> L67
                            return
                        L15:
                            android.util.Printer r0 = r1     // Catch: java.lang.Throwable -> L67
                            if (r0 == 0) goto L1e
                            android.util.Printer r0 = r1     // Catch: java.lang.Throwable -> L67
                            r0.println(r7)     // Catch: java.lang.Throwable -> L67
                        L1e:
                            boolean r0 = r6.f15430a     // Catch: java.lang.Throwable -> L67
                            r1 = 62
                            r2 = 1
                            r3 = 0
                            if (r0 != 0) goto L58
                            char r0 = r7.charAt(r3)     // Catch: java.lang.Throwable -> L67
                            if (r0 == r1) goto L37
                            char r0 = r7.charAt(r3)     // Catch: java.lang.Throwable -> L67
                            r4 = 60
                            if (r0 != r4) goto L35
                            goto L37
                        L35:
                            r0 = r3
                            goto L38
                        L37:
                            r0 = r2
                        L38:
                            r6.f15431b = r0     // Catch: java.lang.Throwable -> L67
                            r6.f15430a = r2     // Catch: java.lang.Throwable -> L67
                            boolean r0 = r6.f15431b     // Catch: java.lang.Throwable -> L67
                            if (r0 != 0) goto L58
                            com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.p241b.C6235g.m10927a()     // Catch: java.lang.Throwable -> L67
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67
                            r4.<init>()     // Catch: java.lang.Throwable -> L67
                            java.lang.String r5 = "Printer is inValid! x:"
                            r4.append(r5)     // Catch: java.lang.Throwable -> L67
                            r4.append(r7)     // Catch: java.lang.Throwable -> L67
                            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L67
                            r0.mo10115e(r4)     // Catch: java.lang.Throwable -> L67
                        L58:
                            boolean r0 = r6.f15431b     // Catch: java.lang.Throwable -> L67
                            if (r0 == 0) goto L67
                            char r7 = r7.charAt(r3)     // Catch: java.lang.Throwable -> L67
                            if (r7 != r1) goto L63
                            goto L64
                        L63:
                            r2 = r3
                        L64:
                            com.networkbench.agent.impl.p241b.C6235g.m10924a(r2)     // Catch: java.lang.Throwable -> L67
                        L67:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.p241b.C6235g.C62361.println(java.lang.String):void");
                    }
                };
                f15429d = printer2;
                mainLooper.setMessageLogging(printer2);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static boolean m10926a(int i) {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace == null || stackTrace.length <= i || !stackTrace[i].getClassName().contains("networkbench") || !stackTrace[i].getMethodName().equals("println")) {
                return false;
            }
            InterfaceC6393e interfaceC6393e = f15427b;
            interfaceC6393e.mo10122a("isNetworkbenchPrintlnCrycle cycle stackElement:" + stackTrace[i]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.b.g$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class AbstractC6237a {

        /* renamed from: a */
        boolean f15433a = false;

        /* renamed from: c */
        boolean mo10921c() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: d */
        public void mo10920d() {
            this.f15433a = true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: e */
        public void mo10919e() {
            this.f15433a = false;
        }
    }

    /* renamed from: a */
    static void m10924a(boolean z) {
        for (AbstractC6237a abstractC6237a : f15428c) {
            if (abstractC6237a.mo10921c()) {
                if (z) {
                    if (!abstractC6237a.f15433a) {
                        abstractC6237a.mo10920d();
                    }
                } else if (abstractC6237a.f15433a) {
                    abstractC6237a.mo10919e();
                }
            } else if (!z && abstractC6237a.f15433a) {
                abstractC6237a.mo10919e();
            }
        }
    }

    /* renamed from: a */
    public static void m10925a(AbstractC6237a abstractC6237a) {
        synchronized (f15428c) {
            if (!f15428c.contains(abstractC6237a)) {
                f15428c.add(abstractC6237a);
            }
        }
    }

    /* renamed from: b */
    public static void m10922b(AbstractC6237a abstractC6237a) {
        synchronized (f15428c) {
            f15428c.remove(abstractC6237a);
        }
    }

    @Override // android.os.MessageQueue.IdleHandler
    public boolean queueIdle() {
        m10923b();
        return true;
    }
}
