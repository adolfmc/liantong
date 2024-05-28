package com.baidu.cloud.download.base;

import com.baidu.cloud.download.exception.DownloadException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DownloadCallback {
    public void onCompleted(String str) {
    }

    public void onConnected(long j, boolean z) {
    }

    public void onConnecting() {
    }

    public void onDownloadCanceled() {
    }

    public void onDownloadPaused() {
    }

    public void onFailed(DownloadException downloadException) {
    }

    public void onProgress(long j, long j2, int i) {
    }

    public void onStarted() {
    }
}
