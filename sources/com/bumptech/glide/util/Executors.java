package com.bumptech.glide.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Executors {
    private static final Executor MAIN_THREAD_EXECUTOR = new Executor() { // from class: com.bumptech.glide.util.Executors.1
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.handler.post(runnable);
        }
    };
    private static final Executor DIRECT_EXECUTOR = new Executor() { // from class: com.bumptech.glide.util.Executors.2
        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    };

    private Executors() {
    }

    public static Executor mainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    public static Executor directExecutor() {
        return DIRECT_EXECUTOR;
    }

    @VisibleForTesting
    public static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                return;
            }
            executorService.shutdownNow();
            if (executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                return;
            }
            throw new RuntimeException("Failed to shutdown");
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
