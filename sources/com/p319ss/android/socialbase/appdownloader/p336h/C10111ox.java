package com.p319ss.android.socialbase.appdownloader.p336h;

import android.content.Context;
import com.p319ss.android.socialbase.appdownloader.p340u.C10164ox;
import com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;

/* renamed from: com.ss.android.socialbase.appdownloader.h.ox */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10111ox extends AbsNotificationListener {

    /* renamed from: b */
    private String f19483b;

    /* renamed from: h */
    private String f19484h;

    /* renamed from: hj */
    private String f19485hj;

    /* renamed from: ko */
    private AbsNotificationItem f19486ko;

    /* renamed from: mb */
    private Context f19487mb;

    /* renamed from: ox */
    private int f19488ox;

    /* renamed from: u */
    private String f19489u;

    public C10111ox(Context context, int i, String str, String str2, String str3, String str4) {
        if (context != null) {
            this.f19487mb = context.getApplicationContext();
        } else {
            this.f19487mb = DownloadComponentManager.getAppContext();
        }
        this.f19488ox = i;
        this.f19483b = str;
        this.f19485hj = str2;
        this.f19484h = str3;
        this.f19489u = str4;
    }

    public C10111ox(AbsNotificationItem absNotificationItem) {
        this.f19487mb = DownloadComponentManager.getAppContext();
        this.f19486ko = absNotificationItem;
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener
    public AbsNotificationItem createNotificationItem() {
        Context context;
        if (this.f19486ko == null && (context = this.f19487mb) != null) {
            return new C10110mb(context, this.f19488ox, this.f19483b, this.f19485hj, this.f19484h, this.f19489u);
        }
        return this.f19486ko;
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPrepare(DownloadInfo downloadInfo) {
        if (downloadInfo == null || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        super.onPrepare(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onStart(DownloadInfo downloadInfo) {
        if (downloadInfo == null || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        super.onStart(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onPause(DownloadInfo downloadInfo) {
        if (downloadInfo == null || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        super.onPause(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onProgress(DownloadInfo downloadInfo) {
        if (downloadInfo == null || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        super.onProgress(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onSuccessed(DownloadInfo downloadInfo) {
        if (downloadInfo == null || this.f19487mb == null) {
            return;
        }
        if (downloadInfo.canShowNotification() && (!downloadInfo.isAutoInstallWithoutNotification() || !downloadInfo.isAutoInstall())) {
            super.onSuccessed(downloadInfo);
        }
        if (downloadInfo.isAutoInstall()) {
            C10164ox.m6509mb(downloadInfo);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.AbsNotificationListener, com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
    public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
        if (downloadInfo == null || this.f19487mb == null || !downloadInfo.canShowNotification() || downloadInfo.isAutoInstallWithoutNotification()) {
            return;
        }
        super.onFailed(downloadInfo, baseException);
    }
}
