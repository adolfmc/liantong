package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadListener */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface IDownloadListener {
    void onCanceled(DownloadInfo downloadInfo);

    void onFailed(DownloadInfo downloadInfo, BaseException baseException);

    void onFirstStart(DownloadInfo downloadInfo);

    void onFirstSuccess(DownloadInfo downloadInfo);

    void onPause(DownloadInfo downloadInfo);

    void onPrepare(DownloadInfo downloadInfo);

    void onProgress(DownloadInfo downloadInfo);

    void onRetry(DownloadInfo downloadInfo, BaseException baseException);

    void onRetryDelay(DownloadInfo downloadInfo, BaseException baseException);

    void onStart(DownloadInfo downloadInfo);

    void onSuccessed(DownloadInfo downloadInfo);
}
