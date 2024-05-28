package com.huawei.hmf.tasks.p212a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hmf.tasks.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C4810a {

    /* renamed from: a */
    private static final C4810a f10817a = new C4810a();

    /* renamed from: c */
    private static final int f10818c;

    /* renamed from: d */
    private static final int f10819d;

    /* renamed from: e */
    private static final int f10820e;

    /* renamed from: b */
    private final Executor f10821b = new ExecutorC4811a((byte) 0);

    /* renamed from: com.huawei.hmf.tasks.a.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class ExecutorC4811a implements Executor {
        private ExecutorC4811a() {
        }

        /* synthetic */ ExecutorC4811a(byte b) {
            this();
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f10818c = availableProcessors;
        f10819d = availableProcessors + 1;
        f10820e = (f10818c * 2) + 1;
    }

    /* renamed from: a */
    public static ExecutorService m15374a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(f10819d, f10820e, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* renamed from: b */
    public static Executor m15373b() {
        return f10817a.f10821b;
    }
}
