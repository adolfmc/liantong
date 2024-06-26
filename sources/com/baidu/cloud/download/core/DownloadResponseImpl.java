package com.baidu.cloud.download.core;

import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.base.DownloadResponse;
import com.baidu.cloud.download.base.DownloadStatus;
import com.baidu.cloud.download.base.DownloadStatusDelivery;
import com.baidu.cloud.download.exception.DownloadException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownloadResponseImpl implements DownloadResponse {
    private DownloadStatusDelivery mDelivery;
    private DownloadStatus mDownloadStatus = new DownloadStatus();

    public DownloadResponseImpl(DownloadStatusDelivery downloadStatusDelivery, DownloadCallback downloadCallback) {
        this.mDelivery = downloadStatusDelivery;
        this.mDownloadStatus.setCallBack(downloadCallback);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onStarted() {
        this.mDownloadStatus.setStatus(101);
        this.mDownloadStatus.getCallBack().onStarted();
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onConnecting() {
        this.mDownloadStatus.setStatus(102);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onConnected(long j, long j2, boolean z) {
        this.mDownloadStatus.setTime(j);
        this.mDownloadStatus.setAcceptRanges(z);
        this.mDownloadStatus.setStatus(103);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onConnectFailed(DownloadException downloadException) {
        this.mDownloadStatus.setException(downloadException);
        this.mDownloadStatus.setStatus(108);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onConnectCanceled() {
        this.mDownloadStatus.setStatus(107);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onDownloadProgress(long j, long j2, int i) {
        this.mDownloadStatus.setFinished(j);
        this.mDownloadStatus.setLength(j2);
        this.mDownloadStatus.setPercent(i);
        this.mDownloadStatus.setStatus(104);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onDownloadCompleted(String str) {
        this.mDownloadStatus.setStatus(105);
        this.mDownloadStatus.setSavedPath(str);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onDownloadPaused() {
        this.mDownloadStatus.setStatus(106);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onDownloadCanceled() {
        this.mDownloadStatus.setStatus(107);
        this.mDelivery.post(this.mDownloadStatus);
    }

    @Override // com.baidu.cloud.download.base.DownloadResponse
    public void onDownloadFailed(DownloadException downloadException) {
        this.mDownloadStatus.setException(downloadException);
        this.mDownloadStatus.setStatus(108);
        this.mDelivery.post(this.mDownloadStatus);
    }
}
