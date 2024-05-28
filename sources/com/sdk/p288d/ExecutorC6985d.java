package com.sdk.p288d;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.d.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ExecutorC6985d implements Executor {

    /* renamed from: c */
    public static final ThreadFactory f18110c = new ThreadFactoryC6986a();

    /* renamed from: a */
    public final BlockingQueue<Runnable> f18111a;

    /* renamed from: b */
    public final ThreadPoolExecutor f18112b;

    /* renamed from: com.sdk.d.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ThreadFactoryC6986a implements ThreadFactory {

        /* renamed from: a */
        public final AtomicInteger f18113a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "PriorityExecutor #" + this.f18113a.getAndIncrement());
        }
    }

    public ExecutorC6985d(int i) {
        BlockingQueueC6988f blockingQueueC6988f = new BlockingQueueC6988f();
        this.f18111a = blockingQueueC6988f;
        this.f18112b = new ThreadPoolExecutor(i, 256, 1L, TimeUnit.SECONDS, blockingQueueC6988f, f18110c);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f18112b.execute(runnable);
    }
}
