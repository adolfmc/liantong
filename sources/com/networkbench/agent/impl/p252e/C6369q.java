package com.networkbench.agent.impl.p252e;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.q */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6369q {

    /* renamed from: b */
    private static C6369q f16041b;

    /* renamed from: a */
    private ExecutorService f16042a;

    /* renamed from: a */
    public static C6369q m10273a() {
        if (f16041b == null) {
            synchronized (C6369q.class) {
                if (f16041b == null) {
                    f16041b = new C6369q();
                }
            }
        }
        return f16041b;
    }

    private C6369q() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int i = availableProcessors <= 4 ? 2 : availableProcessors;
        this.f16042a = new ThreadPoolExecutor(i, i, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    /* renamed from: a */
    public void m10272a(Runnable runnable) {
        this.f16042a.execute(runnable);
    }

    /* renamed from: b */
    public void m10271b() {
        this.f16042a.shutdownNow();
        f16041b = null;
    }

    /* renamed from: c */
    public static synchronized boolean m10270c() {
        synchronized (C6369q.class) {
            return f16041b == null;
        }
    }
}
