package com.p319ss.android.downloadlib.p329b;

import android.support.annotation.WorkerThread;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10093ko;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.b.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9952hj implements InterfaceC10093ko, IDownloadCacheSyncStatusListener {
    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10093ko
    /* renamed from: mb */
    public void mo6877mb(List<DownloadInfo> list) {
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onStart() {
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10093ko
    /* renamed from: mb */
    public void mo6878mb(DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return;
        }
        m7336mb(downloadInfo, downloadInfo.getRealStatus(), z);
    }

    @WorkerThread
    /* renamed from: mb */
    public void m7336mb(DownloadInfo downloadInfo, int i, boolean z) {
        C9923u.m7451mb().m7438ox();
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            return;
        }
        try {
            if (z) {
                m7442mb.m7820b(downloadInfo.getFailedResumeCount());
            } else if (m7442mb.m7768nf() == -1) {
                return;
            } else {
                m7442mb.m7820b(-1);
            }
            C9926ww.m7430mb().m7429mb(m7442mb);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
            jSONObject.put("url", downloadInfo.getUrl());
            jSONObject.put("download_time", downloadInfo.getDownloadTime());
            jSONObject.put("download_status", i);
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            int i2 = 1;
            jSONObject.put("only_wifi", downloadInfo.isOnlyWifi() ? 1 : 0);
            jSONObject.put("chunk_count", downloadInfo.getChunkCount());
            if (!z) {
                i2 = 2;
            }
            jSONObject.put("launch_resumed", i2);
            jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
            AdEventHandler.m7315mb().m7300mb("embeded_ad", "download_uncompleted", jSONObject, m7442mb);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onSuccess() {
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.b.hj.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo;
                int spIntVal;
                C9923u.m7451mb().m7438ox();
                for (C9837ox c9837ox : C9923u.m7451mb().m7455b().values()) {
                    int mo7479m = c9837ox.mo7479m();
                    if (mo7479m != 0) {
                        DownloadSetting obtain = DownloadSetting.obtain(mo7479m);
                        if (obtain.optInt("notification_opt_2") == 1 && (downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(mo7479m)) != null) {
                            if (C10050jb.m7031ox(c9837ox) && !C10050jb.m7062b(c9837ox.mo7489h())) {
                                int spIntVal2 = downloadInfo.getSpIntVal("restart_notify_open_app_count");
                                if (spIntVal2 < obtain.optInt("noti_open_restart_times", 1)) {
                                    C9962ww.m7325mb().m7327h(c9837ox);
                                    downloadInfo.setSpValue("restart_notify_open_app_count", String.valueOf(spIntVal2 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -2) {
                                int spIntVal3 = downloadInfo.getSpIntVal("restart_notify_continue_count");
                                if (spIntVal3 < obtain.optInt("noti_continue_restart_times", 1)) {
                                    C9962ww.m7325mb().m7322mb(c9837ox);
                                    downloadInfo.setSpValue("restart_notify_continue_count", String.valueOf(spIntVal3 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -3 && DownloadUtils.isFileDownloaded(downloadInfo) && !C10050jb.m7031ox(c9837ox) && (spIntVal = downloadInfo.getSpIntVal("restart_notify_install_count")) < obtain.optInt("noti_install_restart_times", 1)) {
                                C9962ww.m7325mb().m7329b(c9837ox);
                                downloadInfo.setSpValue("restart_notify_install_count", String.valueOf(spIntVal + 1));
                            }
                        }
                    }
                }
            }
        }, 5000L);
    }
}
