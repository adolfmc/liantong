package com.p319ss.android.socialbase.appdownloader.p340u;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.ox */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10164ox {
    /* renamed from: mb */
    public static void m6509mb(DownloadInfo downloadInfo) {
        m6508ox(downloadInfo);
    }

    /* renamed from: ox */
    private static void m6508ox(final DownloadInfo downloadInfo) {
        final Context appContext = DownloadComponentManager.getAppContext();
        boolean z = true;
        if (((downloadInfo.isAutoResumed() && !downloadInfo.isShowNotificationForNetworkResumed()) || C10085b.m6891ox(downloadInfo.getExtra()) || TextUtils.isEmpty(downloadInfo.getMimeType()) || !downloadInfo.getMimeType().equals("application/vnd.android.package-archive")) && DownloadSetting.obtain(downloadInfo.getId()).optInt("auto_install_when_resume", 0) != 1) {
            z = false;
        }
        final int m6916mb = z ? C10085b.m6916mb(appContext, downloadInfo.getId(), false) : 2;
        DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.u.ox.1
            @Override // java.lang.Runnable
            public void run() {
                InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
                IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(appContext).getDownloadNotificationEventListener(downloadInfo.getId());
                if (m6792ox == null && downloadNotificationEventListener == null) {
                    return;
                }
                File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                if (file.exists()) {
                    try {
                        PackageInfo m6906mb = C10085b.m6906mb(downloadInfo, file);
                        if (m6906mb != null) {
                            String packageName = (m6916mb == 1 || TextUtils.isEmpty(downloadInfo.getPackageName())) ? m6906mb.packageName : downloadInfo.getPackageName();
                            if (m6792ox != null) {
                                m6792ox.mo6883mb(downloadInfo.getId(), 1, packageName, -3, downloadInfo.getDownloadTime());
                            }
                            if (downloadNotificationEventListener != null) {
                                downloadNotificationEventListener.onNotificationEvent(1, downloadInfo, packageName, "");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
