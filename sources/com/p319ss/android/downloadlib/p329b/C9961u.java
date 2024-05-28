package com.p319ss.android.downloadlib.p329b;

import android.content.Context;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9992ko;
import com.p319ss.android.downloadlib.C9998mb;
import com.p319ss.android.downloadlib.addownload.C9905mb;
import com.p319ss.android.downloadlib.addownload.C9930ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.b.u */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9961u implements InterfaceC10090hj {

    /* renamed from: mb */
    private Context f19202mb;

    public C9961u(Context context) {
        this.f19202mb = context.getApplicationContext();
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public void mo6880mb(Context context, String str) {
        C9998mb.m7215mb().m7203mb(str);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public void mo6883mb(int i, int i2, String str, int i3, long j) {
        DownloadInfo downloadInfo;
        C9837ox m7442mb;
        Context context = this.f19202mb;
        if (context == null || (downloadInfo = Downloader.getInstance(context).getDownloadInfo(i)) == null || downloadInfo.getStatus() == 0 || (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) == null) {
            return;
        }
        if (i2 == 1) {
            C9998mb.m7205mb(downloadInfo, m7442mb);
            if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
                C9905mb.m7533mb().m7531mb(downloadInfo, m7442mb.mo7474ox(), m7442mb.mo7485je(), m7442mb.mo7489h(), downloadInfo.getTitle(), m7442mb.mo7488hj(), downloadInfo.getTargetFilePath());
            }
        } else if (i2 != 3) {
            switch (i2) {
                case 5:
                    AdEventHandler.m7315mb().m7302mb("download_notification", "download_notification_pause", m7442mb);
                    return;
                case 6:
                    AdEventHandler.m7315mb().m7302mb("download_notification", "download_notification_continue", m7442mb);
                    return;
                case 7:
                    AdEventHandler.m7315mb().m7302mb("download_notification", "download_notification_click", m7442mb);
                    return;
                default:
                    return;
            }
        } else {
            AdEventHandler.m7315mb().m7300mb("download_notification", "download_notification_install", C9998mb.m7194ox(new JSONObject(), downloadInfo), m7442mb);
        }
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public boolean mo6881mb(int i, boolean z) {
        if (C9940x.m7349o() != null) {
            return C9940x.m7349o().m7948mb(z);
        }
        return false;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public void mo6879mb(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        C9992ko.m7236mb().m7231mb(downloadInfo);
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt("report_download_cancel", 1) == 1) {
            AdEventHandler.m7315mb().m7307mb(downloadInfo, new BaseException(1012, ""));
        } else {
            AdEventHandler.m7315mb().m7295ox(downloadInfo, new BaseException(1012, ""));
        }
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public void mo6882mb(int i, int i2, String str, String str2, String str3) {
        DownloadInfo downloadInfo;
        Context context = this.f19202mb;
        if (context == null || (downloadInfo = Downloader.getInstance(context).getDownloadInfo(i)) == null || downloadInfo.getStatus() != -3) {
            return;
        }
        downloadInfo.setPackageName(str2);
        C9930ox.m7423mb().m7422mb(this.f19202mb, downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj
    /* renamed from: mb */
    public boolean mo6884mb() {
        return C9930ox.m7423mb().m7421ox();
    }
}
