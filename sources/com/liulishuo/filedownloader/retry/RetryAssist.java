package com.liulishuo.filedownloader.retry;

import android.support.annotation.NonNull;
import com.liulishuo.okdownload.DownloadTask;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RetryAssist {
    @NonNull
    final AtomicInteger retriedTimes = new AtomicInteger(0);
    final int retryTimes;

    public RetryAssist(int i) {
        this.retryTimes = i;
    }

    public void doRetry(@NonNull DownloadTask downloadTask) {
        if (this.retriedTimes.incrementAndGet() > this.retryTimes) {
            throw new RuntimeException("retry has exceeded limit");
        }
        downloadTask.enqueue(downloadTask.getListener());
    }

    public boolean canRetry() {
        return this.retriedTimes.get() < this.retryTimes;
    }

    public int getRetriedTimes() {
        return this.retriedTimes.get();
    }
}
