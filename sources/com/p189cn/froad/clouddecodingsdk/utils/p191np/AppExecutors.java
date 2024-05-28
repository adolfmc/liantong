package com.p189cn.froad.clouddecodingsdk.utils.p191np;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.utils.np.AppExecutors */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AppExecutors {
    private static AppExecutors appExecutors;
    private final Executor mDiskIO;
    private final HanderThreadExecutor mHanderThreadExecutor;
    private final MainThreadExecutor mMainThread;
    private final ScheduledExecutorService mScheduledExecutorService;
    private final Executor mSingle;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.cn.froad.clouddecodingsdk.utils.np.AppExecutors$HanderThreadExecutor */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class HanderThreadExecutor implements Executor {
        private Handler mHandler;
        private HandlerThread mHandlerThread;

        public HanderThreadExecutor() {
            HandlerThread handlerThread = new HandlerThread("HanderThreadExecutor");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }

        public void executeDelayed(@NonNull Runnable runnable, long j) {
            this.mHandler.postDelayed(runnable, j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.cn.froad.clouddecodingsdk.utils.np.AppExecutors$MainThreadExecutor */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler;

        private MainThreadExecutor() {
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }

        public void executeDelayed(@NonNull Runnable runnable, long j) {
            this.mainThreadHandler.postDelayed(runnable, j);
        }

        public void removeCallbacks(@NonNull Runnable runnable) {
            this.mainThreadHandler.removeCallbacks(runnable);
        }
    }

    private AppExecutors() {
        this(new MainThreadExecutor(), new HanderThreadExecutor(), Executors.newCachedThreadPool(), Executors.newScheduledThreadPool(5), Executors.newSingleThreadExecutor());
    }

    private AppExecutors(MainThreadExecutor mainThreadExecutor, HanderThreadExecutor handerThreadExecutor, Executor executor, ScheduledExecutorService scheduledExecutorService, ExecutorService executorService) {
        this.mMainThread = mainThreadExecutor;
        this.mHanderThreadExecutor = handerThreadExecutor;
        this.mDiskIO = executor;
        this.mScheduledExecutorService = scheduledExecutorService;
        this.mSingle = executorService;
    }

    public static AppExecutors getAppExecutors() {
        if (appExecutors == null) {
            synchronized (AppExecutors.class) {
                if (appExecutors == null) {
                    appExecutors = new AppExecutors();
                }
            }
        }
        return appExecutors;
    }

    public void postDiskIOThread(Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }

    public void postHanderThread(Runnable runnable) {
        this.mHanderThreadExecutor.execute(runnable);
    }

    public void postHandlerThreadDelayed(Runnable runnable, long j) {
        this.mHanderThreadExecutor.executeDelayed(runnable, j);
    }

    public void postMainThread(Runnable runnable) {
        this.mMainThread.execute(runnable);
    }

    public void postMainThreadDelayed(Runnable runnable, long j) {
        this.mMainThread.executeDelayed(runnable, j);
    }

    public void postScheduledAtFixedRate(Runnable runnable, long j, long j2) {
        this.mScheduledExecutorService.scheduleAtFixedRate(runnable, j, j2, TimeUnit.MILLISECONDS);
    }

    public void postScheduledExecutorThread(Runnable runnable) {
        this.mScheduledExecutorService.schedule(runnable, 0L, TimeUnit.MILLISECONDS);
    }

    public void postScheduledThreadDelayed(Runnable runnable, long j) {
        this.mScheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    public void postSingleThread(Runnable runnable) {
        this.mSingle.execute(runnable);
    }

    public void removeMainThreadRunnable(Runnable runnable) {
        this.mMainThread.removeCallbacks(runnable);
    }
}
