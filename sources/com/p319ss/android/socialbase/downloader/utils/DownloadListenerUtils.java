package com.p319ss.android.socialbase.downloader.utils;

import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadExtListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.utils.DownloadListenerUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadListenerUtils {
    public static void notifyListener(int i, SparseArray<IDownloadListener> sparseArray, boolean z, DownloadInfo downloadInfo, BaseException baseException) {
        SparseArray<IDownloadListener> clone;
        if (!z || sparseArray == null) {
            return;
        }
        try {
            if (sparseArray.size() <= 0) {
                return;
            }
            synchronized (sparseArray) {
                clone = sparseArray.clone();
            }
            for (int i2 = 0; i2 < clone.size(); i2++) {
                IDownloadListener iDownloadListener = clone.get(clone.keyAt(i2));
                if (iDownloadListener != null) {
                    switch (i) {
                        case -7:
                            if (!(iDownloadListener instanceof AbsDownloadListener)) {
                                break;
                            } else {
                                ((AbsDownloadListener) iDownloadListener).onIntercept(downloadInfo);
                                continue;
                            }
                        case -6:
                            iDownloadListener.onFirstSuccess(downloadInfo);
                            continue;
                        case -5:
                        case -2:
                            iDownloadListener.onPause(downloadInfo);
                            continue;
                        case -4:
                            iDownloadListener.onCanceled(downloadInfo);
                            continue;
                        case -3:
                            iDownloadListener.onSuccessed(downloadInfo);
                            continue;
                        case -1:
                            iDownloadListener.onFailed(downloadInfo, baseException);
                            continue;
                        case 0:
                        case 3:
                        case 8:
                        case 9:
                        case 10:
                        default:
                            continue;
                        case 1:
                            iDownloadListener.onPrepare(downloadInfo);
                            continue;
                        case 2:
                            iDownloadListener.onStart(downloadInfo);
                            continue;
                        case 4:
                            iDownloadListener.onProgress(downloadInfo);
                            continue;
                        case 5:
                            iDownloadListener.onRetry(downloadInfo, baseException);
                            continue;
                        case 6:
                            iDownloadListener.onFirstStart(downloadInfo);
                            continue;
                        case 7:
                            iDownloadListener.onRetryDelay(downloadInfo, baseException);
                            continue;
                        case 11:
                            if (iDownloadListener instanceof IDownloadExtListener) {
                                ((IDownloadExtListener) iDownloadListener).onWaitingDownloadCompleteHandler(downloadInfo);
                                break;
                            } else {
                                continue;
                            }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
