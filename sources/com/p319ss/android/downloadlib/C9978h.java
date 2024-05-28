package com.p319ss.android.downloadlib;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.InterfaceC9819mb;
import com.p319ss.android.download.api.config.InterfaceC9793e;
import com.p319ss.android.download.api.config.InterfaceC9794g;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.config.InterfaceC9801ko;
import com.p319ss.android.download.api.config.InterfaceC9804lz;
import com.p319ss.android.download.api.config.InterfaceC9809ox;
import com.p319ss.android.download.api.config.InterfaceC9811ww;
import com.p319ss.android.download.api.config.InterfaceC9812x;
import com.p319ss.android.download.api.model.C9830mb;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9939ww;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.p329b.C9950b;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.utils.C10069ww;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.p319ss.android.socialbase.appdownloader.p340u.C10151h;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.ss.android.downloadlib.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9978h implements InterfaceC9819mb {
    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7265mb(@NonNull InterfaceC9804lz interfaceC9804lz) {
        C9940x.m7357mb(interfaceC9804lz);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7266mb(@NonNull InterfaceC9801ko interfaceC9801ko) {
        C9940x.m7358mb(interfaceC9801ko);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7267mb(@NonNull InterfaceC9800je interfaceC9800je) {
        C9940x.m7359mb(interfaceC9800je);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7263mb(@NonNull InterfaceC9811ww interfaceC9811ww) {
        C9940x.m7355mb(interfaceC9811ww);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7262mb(@NonNull InterfaceC9812x interfaceC9812x) {
        C9940x.m7354mb(interfaceC9812x);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7261mb(@NonNull C9830mb c9830mb) {
        C9940x.m7353mb(c9830mb);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7259mb(String str) {
        C9940x.m7351mb(str);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7264mb(@NonNull final InterfaceC9809ox interfaceC9809ox) {
        C9940x.m7356mb(interfaceC9809ox);
        AppStatusManager.getInstance().setInnerAppStatusChangeCaller(new AppStatusManager.InnerAppStatusChangeCaller() { // from class: com.ss.android.downloadlib.h.1
            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.InnerAppStatusChangeCaller
            public boolean isAppInBackground() {
                return interfaceC9809ox.mo7935mb();
            }
        });
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7260mb(DownloaderBuilder downloaderBuilder) {
        if (downloaderBuilder.getNotificationClickCallback() == null) {
            downloaderBuilder.notificationClickCallback(new INotificationClickCallback() { // from class: com.ss.android.downloadlib.h.2
                @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenSuccess(DownloadInfo downloadInfo) {
                    return false;
                }

                @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) {
                    DownloadSetting obtain = DownloadSetting.obtain(downloadInfo.getId());
                    if (obtain.optInt("notification_opt_2") == 1) {
                        if (downloadInfo.getStatus() == -2) {
                            DownloadHandlerService.m6941mb(C9940x.getContext(), downloadInfo, C10112hj.m6786x().m6792ox(), Downloader.getInstance(C9940x.getContext()).getDownloadNotificationEventListener(downloadInfo.getId()));
                        }
                        return true;
                    }
                    boolean m7258mb = m7258mb(downloadInfo);
                    if (obtain.optInt("disable_delete_dialog", 0) == 1) {
                        return true;
                    }
                    return m7258mb;
                }

                /* renamed from: mb */
                private boolean m7258mb(DownloadInfo downloadInfo) {
                    String m7380mb;
                    InterfaceC9794g m7366l = C9940x.m7366l();
                    if (m7366l != null) {
                        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
                        if (m7442mb != null && m7442mb.mo7494b()) {
                            m7380mb = DownloadSetting.obtain(downloadInfo.getId()).optString("ad_notification_jump_url", null);
                        } else {
                            m7380mb = C9939ww.m7380mb(downloadInfo);
                        }
                        if (TextUtils.isEmpty(m7380mb)) {
                            return false;
                        }
                        return m7366l.m7950mb(C9940x.getContext(), m7380mb);
                    }
                    return false;
                }

                @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenInstalled(DownloadInfo downloadInfo) {
                    if (downloadInfo == null) {
                        return false;
                    }
                    C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
                    if (m7442mb != null) {
                        C10032mb.m7126mb(m7442mb);
                    } else {
                        C10069ww.m6971ox(C9940x.getContext(), downloadInfo.getPackageName());
                    }
                    DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                    return true;
                }
            });
        }
        downloaderBuilder.addDownloadCompleteHandler(new C9950b());
        Downloader.initOrCover(downloaderBuilder, true);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public InterfaceC9819mb mo7268mb(InterfaceC9793e interfaceC9793e) {
        C9940x.m7360mb(interfaceC9793e);
        return this;
    }

    @Override // com.p319ss.android.download.api.InterfaceC9819mb
    /* renamed from: mb */
    public void mo7269mb() {
        if (!C9940x.m7376df()) {
            C9971b.m7285mb().m7284mb("ttdownloader init error");
        }
        C9940x.m7352mb(C9971b.m7285mb());
        try {
            C10112hj.m6786x().m6789ox(C9940x.m7374g());
        } catch (Exception e) {
            e.printStackTrace();
        }
        C10112hj.m6786x().m6800mb(C9998mb.m7215mb());
        C9982hj.m7254mb().m7249ox(new Runnable() { // from class: com.ss.android.downloadlib.h.3
            @Override // java.lang.Runnable
            public void run() {
                C10152hj.m6568mb("");
                if (C10152hj.m6566o()) {
                    DownloadComponentManager.setNotAutoRebootService(true);
                }
                if (DownloadSetting.obtainGlobal().optInt("disable_security_init", 1) == 1) {
                    C10151h.m6586mb(C9940x.getContext());
                }
            }
        });
    }
}
