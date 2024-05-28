package com.p319ss.android.downloadlib.p329b;

import android.content.pm.PackageInfo;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.downloadlib.b.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9960ox implements IDownloadCompleteHandler {
    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        PackageInfo m6908mb = C10085b.m6908mb(C9940x.getContext(), downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
        if (m6908mb != null) {
            downloadInfo.setAppVersionCode(m6908mb.versionCode);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        return downloadInfo != null && C10049hj.m7069ox() && downloadInfo.getPackageInfo() == null;
    }
}
