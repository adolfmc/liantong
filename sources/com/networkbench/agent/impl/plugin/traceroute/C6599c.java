package com.networkbench.agent.impl.plugin.traceroute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.traceroute.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6599c {

    /* renamed from: b */
    private static C6599c f16919b;

    /* renamed from: a */
    private ExecutorService f16920a = Executors.newSingleThreadExecutor();

    /* renamed from: a */
    public static C6599c m9288a() {
        if (f16919b == null) {
            synchronized (C6599c.class) {
                if (f16919b == null) {
                    f16919b = new C6599c();
                }
            }
        }
        return f16919b;
    }

    private C6599c() {
    }

    /* renamed from: a */
    public void m9287a(Runnable runnable) {
        this.f16920a.execute(runnable);
    }

    /* renamed from: b */
    public void m9286b() {
        this.f16920a.shutdownNow();
        f16919b = null;
    }

    /* renamed from: c */
    public static synchronized boolean m9285c() {
        synchronized (C6599c.class) {
            return f16919b == null;
        }
    }
}
