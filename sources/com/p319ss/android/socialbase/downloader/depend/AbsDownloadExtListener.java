package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.socialbase.downloader.depend.AbsDownloadExtListener */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbsDownloadExtListener extends AbsDownloadListener implements IDownloadExtListener {
    private static final String TAG = "AbsDownloadExtListener";

    public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        String str = TAG;
        Logger.m6475d(str, " onWaitingDownloadCompleteHandler -- " + downloadInfo.getName());
    }
}
