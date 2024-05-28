package com.baidu.cloud.download.base;

import com.baidu.cloud.download.exception.DownloadException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface DownloadTask extends Runnable {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDownloadListener {
        void onDownloadCanceled();

        void onDownloadCompleted(String str);

        void onDownloadFailed(DownloadException downloadException);

        void onDownloadPaused();

        void onDownloadProgress(long j, long j2);
    }

    void cancel();

    boolean isCanceled();

    boolean isComplete();

    boolean isDownloading();

    boolean isFailed();

    boolean isPaused();

    void pause();

    @Override // java.lang.Runnable
    void run();
}
