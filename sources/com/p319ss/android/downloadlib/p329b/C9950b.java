package com.p319ss.android.downloadlib.p329b;

import com.p319ss.android.download.api.config.InterfaceC9807nk;
import com.p319ss.android.download.api.p320b.C9779ox;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;

/* renamed from: com.ss.android.downloadlib.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9950b implements IDownloadCompleteHandler {
    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        InterfaceC9807nk m7344x = C9940x.m7344x();
        if (downloadInfo == null || m7344x == null) {
            return;
        }
        String packageName = downloadInfo.getPackageName();
        String targetFilePath = downloadInfo.getTargetFilePath();
        File m7337mb = m7337mb(packageName, targetFilePath);
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        m7344x.m7943mb(packageName, targetFilePath, m7337mb, m7442mb != null ? C10050jb.m7042mb(m7442mb.mo7483ko()) : null);
        downloadInfo.setMimeType("application/vnd.android.package-archive");
        downloadInfo.setName(m7337mb.getName());
        downloadInfo.setMd5(null);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        if (downloadInfo != null) {
            return C9779ox.m7970mb(DownloadSetting.obtain(downloadInfo.getId()), downloadInfo.getMimeType());
        }
        return false;
    }

    /* renamed from: mb */
    private File m7337mb(String str, String str2) {
        File file = new File(str2);
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            str = name.substring(0, lastIndexOf);
        }
        return new File(file.getParent(), str + ".apk");
    }
}
