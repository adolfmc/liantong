package com.baidu.cloud.download.base;

import com.baidu.cloud.download.exception.DownloadException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface DownloadResponse {
    void onConnectCanceled();

    void onConnectFailed(DownloadException downloadException);

    void onConnected(long j, long j2, boolean z);

    void onConnecting();

    void onDownloadCanceled();

    void onDownloadCompleted(String str);

    void onDownloadFailed(DownloadException downloadException);

    void onDownloadPaused();

    void onDownloadProgress(long j, long j2, int i);

    void onStarted();
}
