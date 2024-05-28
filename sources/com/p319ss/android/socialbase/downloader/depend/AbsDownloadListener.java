package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.socialbase.downloader.depend.AbsDownloadListener */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbsDownloadListener implements IDownloadListener {
    private static final String TAG = "AbsDownloadListener";

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPrepare(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onPrepare -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onStart(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onStart -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onProgress(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null || downloadInfo.getTotalBytes() == 0) {
            return;
        }
        Logger.m6475d(TAG, String.format("onProgress %s %.2f%%", downloadInfo.getName(), Float.valueOf((((float) downloadInfo.getCurBytes()) / ((float) downloadInfo.getTotalBytes())) * 100.0f)));
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPause(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onPause -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onSuccessed(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onSuccessed -- " + downloadInfo.getName() + " " + downloadInfo.isSuccessByCache());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = downloadInfo.getName();
        objArr[1] = baseException != null ? baseException.getErrorMessage() : "unkown";
        Logger.m6475d(str, String.format("onFailed on %s because of : %s", objArr));
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onCanceled(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onCanceled -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onFirstStart(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onFirstStart -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onFirstSuccess(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onFirstSuccess -- " + downloadInfo.getName());
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onRetry(DownloadInfo downloadInfo, BaseException baseException) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = downloadInfo.getName();
        objArr[1] = baseException != null ? baseException.getErrorMessage() : "unkown";
        Logger.m6475d(str, String.format("onRetry on %s because of : %s", objArr));
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onRetryDelay(DownloadInfo downloadInfo, BaseException baseException) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = downloadInfo.getName();
        objArr[1] = baseException != null ? baseException.getErrorMessage() : "unkown";
        Logger.m6475d(str, String.format("onRetryDelay on %s because of : %s", objArr));
    }

    public void onIntercept(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onIntercept -- " + downloadInfo.getName());
    }
}
