package com.baidu.cloud.download.core;

import android.os.Handler;
import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.base.DownloadStatus;
import com.baidu.cloud.download.base.DownloadStatusDelivery;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.cloud.download.utils.LogUtils;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownloadStatusDeliveryImpl implements DownloadStatusDelivery {
    private static final String TAG = "DownloadStatusDelivery";
    private Executor mDownloadStatusPoster;

    public DownloadStatusDeliveryImpl(final Handler handler) {
        this.mDownloadStatusPoster = new Executor() { // from class: com.baidu.cloud.download.core.DownloadStatusDeliveryImpl.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    @Override // com.baidu.cloud.download.base.DownloadStatusDelivery
    public void post(DownloadStatus downloadStatus) {
        this.mDownloadStatusPoster.execute(new DownloadStatusDeliveryRunnable(downloadStatus));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class DownloadStatusDeliveryRunnable implements Runnable {
        private final DownloadCallback mCallBack;
        private final DownloadStatus mDownloadStatus;

        public DownloadStatusDeliveryRunnable(DownloadStatus downloadStatus) {
            this.mDownloadStatus = downloadStatus;
            this.mCallBack = this.mDownloadStatus.getCallBack();
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.mDownloadStatus.getStatus()) {
                case 102:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_CONNECTING");
                    this.mCallBack.onConnecting();
                    return;
                case 103:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_CONNECTED length: " + this.mDownloadStatus.getLength() + " acceptRanges: " + this.mDownloadStatus.isAcceptRanges());
                    this.mCallBack.onConnected(this.mDownloadStatus.getLength(), this.mDownloadStatus.isAcceptRanges());
                    return;
                case 104:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_PROGRESS finished: " + this.mDownloadStatus.getFinished() + " length: " + this.mDownloadStatus.getLength() + " percent: " + this.mDownloadStatus.getPercent());
                    this.mCallBack.onProgress(this.mDownloadStatus.getFinished(), this.mDownloadStatus.getLength(), this.mDownloadStatus.getPercent());
                    return;
                case 105:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_COMPLETED Path:" + this.mDownloadStatus.getSavedPath());
                    if (this.mDownloadStatus.getCalledCompleted()) {
                        return;
                    }
                    this.mDownloadStatus.setCalledCompleted(true);
                    this.mCallBack.onCompleted(this.mDownloadStatus.getSavedPath());
                    return;
                case 106:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_PAUSED");
                    this.mCallBack.onDownloadPaused();
                    return;
                case 107:
                    LogUtils.m20098d("DownloadStatusDelivery", "STATUS_CANCELED");
                    this.mCallBack.onDownloadCanceled();
                    return;
                case 108:
                    LogUtils.m20097e("DownloadStatusDelivery", "STATUS_FAILED error: " + this.mDownloadStatus.getException().getCause());
                    this.mCallBack.onFailed((DownloadException) this.mDownloadStatus.getException());
                    return;
                default:
                    return;
            }
        }
    }
}
