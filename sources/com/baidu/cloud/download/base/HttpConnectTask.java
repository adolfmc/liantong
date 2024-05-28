package com.baidu.cloud.download.base;

import com.baidu.cloud.download.exception.DownloadException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface HttpConnectTask extends Runnable {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnConnectListener {
        void onConnectCanceled();

        void onConnectFailed(DownloadException downloadException);

        void onConnectPaused();

        void onConnected(long j, long j2, boolean z);

        void onConnecting();
    }

    void cancel();

    boolean isCanceled();

    boolean isConnected();

    boolean isConnecting();

    boolean isFailed();

    boolean isPaused();

    void pause();

    @Override // java.lang.Runnable
    void run();
}
