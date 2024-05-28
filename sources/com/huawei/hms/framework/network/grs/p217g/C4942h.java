package com.huawei.hms.framework.network.grs.p217g;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.InterfaceC4919b;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4948b;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/* renamed from: com.huawei.hms.framework.network.grs.g.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4942h {

    /* renamed from: a */
    private final ExecutorService f11299a = ExecutorsUtils.newCachedThreadPool("GRS_RequestController-Task");

    /* renamed from: b */
    private final Map<String, C4948b> f11300b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private final Object f11301c = new Object();

    /* renamed from: d */
    private C4923a f11302d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.framework.network.grs.g.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class CallableC4943a implements Callable<C4937d> {

        /* renamed from: a */
        final /* synthetic */ C4949c f11303a;

        /* renamed from: b */
        final /* synthetic */ String f11304b;

        /* renamed from: c */
        final /* synthetic */ C4925c f11305c;

        CallableC4943a(C4949c c4949c, String str, C4925c c4925c) {
            this.f11303a = c4949c;
            this.f11304b = str;
            this.f11305c = c4925c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public C4937d call() {
            return new C4935c(this.f11303a, C4942h.this.f11302d).m14939a(C4942h.this.f11299a, this.f11304b, this.f11305c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.framework.network.grs.g.h$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC4944b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C4949c f11307a;

        /* renamed from: b */
        final /* synthetic */ String f11308b;

        /* renamed from: c */
        final /* synthetic */ C4925c f11309c;

        /* renamed from: d */
        final /* synthetic */ InterfaceC4919b f11310d;

        RunnableC4944b(C4949c c4949c, String str, C4925c c4925c, InterfaceC4919b interfaceC4919b) {
            this.f11307a = c4949c;
            this.f11308b = str;
            this.f11309c = c4925c;
            this.f11310d = interfaceC4919b;
        }

        @Override // java.lang.Runnable
        public void run() {
            C4942h c4942h = C4942h.this;
            c4942h.m14892a(c4942h.m14888a(this.f11307a, this.f11308b, this.f11309c), this.f11310d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m14892a(C4937d c4937d, InterfaceC4919b interfaceC4919b) {
        if (interfaceC4919b != null) {
            if (c4937d == null) {
                Logger.m15047v("RequestController", "GrsResponse is null");
                interfaceC4919b.mo15031a();
                return;
            }
            Logger.m15047v("RequestController", "GrsResponse is not null");
            interfaceC4919b.mo15030a(c4937d);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0069, code lost:
        if (r2.m14854a() != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
        return null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.huawei.hms.framework.network.grs.p217g.C4937d m14888a(com.huawei.hms.framework.network.grs.p217g.p219k.C4949c r7, java.lang.String r8, com.huawei.hms.framework.network.grs.p215e.C4925c r9) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "request to server with service name is: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "RequestController"
            com.huawei.hms.framework.common.Logger.m15054d(r1, r0)
            com.huawei.hms.framework.network.grs.GrsBaseInfo r0 = r7.m14878b()
            android.content.Context r1 = r7.m14880a()
            r2 = 1
            java.lang.String r0 = r0.getGrsParasKey(r2, r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "request spUrlKey: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "RequestController"
            com.huawei.hms.framework.common.Logger.m15047v(r2, r1)
            java.lang.Object r1 = r6.f11301c
            monitor-enter(r1)
            android.content.Context r2 = r7.m14880a()     // Catch: java.lang.Throwable -> Laa
            boolean r2 = com.huawei.hms.framework.common.NetworkUtil.isNetworkAvailable(r2)     // Catch: java.lang.Throwable -> Laa
            r3 = 0
            if (r2 != 0) goto L49
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            return r3
        L49:
            com.huawei.hms.framework.network.grs.h.d$a r2 = com.huawei.hms.framework.network.grs.p220h.C4954d.m14856a(r0)     // Catch: java.lang.Throwable -> Laa
            java.util.Map<java.lang.String, com.huawei.hms.framework.network.grs.g.k.b> r4 = r6.f11300b     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r4 = r4.get(r0)     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.k.b r4 = (com.huawei.hms.framework.network.grs.p217g.p219k.C4948b) r4     // Catch: java.lang.Throwable -> Laa
            if (r4 == 0) goto L63
            boolean r5 = r4.m14881b()     // Catch: java.lang.Throwable -> Laa
            if (r5 != 0) goto L5e
            goto L63
        L5e:
            java.util.concurrent.Future r7 = r4.m14882a()     // Catch: java.lang.Throwable -> Laa
            goto L8a
        L63:
            if (r2 == 0) goto L6e
            boolean r2 = r2.m14854a()     // Catch: java.lang.Throwable -> Laa
            if (r2 != 0) goto L6c
            goto L6e
        L6c:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            return r3
        L6e:
            java.lang.String r2 = "RequestController"
            java.lang.String r4 = "hitGrsRequestBean == null or request block is released."
            com.huawei.hms.framework.common.Logger.m15054d(r2, r4)     // Catch: java.lang.Throwable -> Laa
            java.util.concurrent.ExecutorService r2 = r6.f11299a     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.h$a r4 = new com.huawei.hms.framework.network.grs.g.h$a     // Catch: java.lang.Throwable -> Laa
            r4.<init>(r7, r8, r9)     // Catch: java.lang.Throwable -> Laa
            java.util.concurrent.Future r7 = r2.submit(r4)     // Catch: java.lang.Throwable -> Laa
            java.util.Map<java.lang.String, com.huawei.hms.framework.network.grs.g.k.b> r8 = r6.f11300b     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.k.b r9 = new com.huawei.hms.framework.network.grs.g.k.b     // Catch: java.lang.Throwable -> Laa
            r9.<init>(r7)     // Catch: java.lang.Throwable -> Laa
            r8.put(r0, r9)     // Catch: java.lang.Throwable -> Laa
        L8a:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r7 = r7.get()     // Catch: java.lang.InterruptedException -> L92 java.util.concurrent.ExecutionException -> L99 java.util.concurrent.CancellationException -> La0
            com.huawei.hms.framework.network.grs.g.d r7 = (com.huawei.hms.framework.network.grs.p217g.C4937d) r7     // Catch: java.lang.InterruptedException -> L92 java.util.concurrent.ExecutionException -> L99 java.util.concurrent.CancellationException -> La0
            return r7
        L92:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find InterruptedException, check others"
            goto La6
        L99:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find ExecutionException, check others"
            goto La6
        La0:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find CancellationException, check others"
        La6:
            com.huawei.hms.framework.common.Logger.m15044w(r8, r9, r7)
            return r3
        Laa:
            r7 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p217g.C4942h.m14888a(com.huawei.hms.framework.network.grs.g.k.c, java.lang.String, com.huawei.hms.framework.network.grs.e.c):com.huawei.hms.framework.network.grs.g.d");
    }

    /* renamed from: a */
    public void m14893a(C4923a c4923a) {
        this.f11302d = c4923a;
    }

    /* renamed from: a */
    public void m14889a(C4949c c4949c, InterfaceC4919b interfaceC4919b, String str, C4925c c4925c) {
        this.f11299a.execute(new RunnableC4944b(c4949c, str, c4925c, interfaceC4919b));
    }

    /* renamed from: a */
    public void m14887a(String str) {
        synchronized (this.f11301c) {
            this.f11300b.remove(str);
        }
    }
}
