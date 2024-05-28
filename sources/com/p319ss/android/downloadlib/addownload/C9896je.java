package com.p319ss.android.downloadlib.addownload;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9843b;
import com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.impls.RetryScheduler;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.je */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9896je {

    /* renamed from: mb */
    private static InterfaceC9906b f19034mb;

    /* renamed from: mb */
    public static boolean m7572mb(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 7 || i == 8;
    }

    /* renamed from: mb */
    public static InterfaceC9906b m7573mb() {
        return f19034mb;
    }

    /* renamed from: mb */
    public static void m7570mb(InterfaceC9906b interfaceC9906b) {
        f19034mb = interfaceC9906b;
    }

    /* renamed from: mb */
    public static boolean m7571mb(final C9837ox c9837ox, DownloadInfo downloadInfo, int i, final InterfaceC9843b interfaceC9843b) {
        if (c9837ox == null) {
            C9971b.m7285mb().m7284mb("tryReverseWifi nativeModel null");
            return false;
        } else if (downloadInfo == null) {
            C9971b.m7285mb().m7284mb("tryReverseWifi info null");
            return false;
        } else {
            final int id = downloadInfo.getId();
            boolean m7066ox = C10049hj.m7066ox(c9837ox);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("switch_status", Integer.valueOf(m7066ox ? 1 : 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdEventHandler.m7315mb().m7298mb("pause_reserve_wifi_switch_status", jSONObject, c9837ox);
            if (m7066ox && m7572mb(i) && !DownloadUtils.isWifi(C9940x.getContext()) && !downloadInfo.hasPauseReservedOnWifi()) {
                m7570mb(new InterfaceC9906b() { // from class: com.ss.android.downloadlib.addownload.je.1
                    @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
                    /* renamed from: mb */
                    public void mo7530mb() {
                        C9896je.m7570mb((InterfaceC9906b) null);
                        DownloadInfo downloadInfo2 = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(id);
                        if (downloadInfo2 != null) {
                            downloadInfo2.startPauseReserveOnWifi();
                            RetryScheduler.getInstance().tryStartScheduleRetry(downloadInfo2);
                            AdEventHandler.m7315mb().m7294ox("pause_reserve_wifi_confirm", c9837ox);
                        }
                        interfaceC9843b.mo7599mb(c9837ox);
                    }

                    @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
                    /* renamed from: ox */
                    public void mo7529ox() {
                        C9896je.m7570mb((InterfaceC9906b) null);
                        DownloadInfo downloadInfo2 = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(id);
                        if (downloadInfo2 != null) {
                            downloadInfo2.stopPauseReserveOnWifi();
                        }
                        AdEventHandler.m7315mb().m7294ox("pause_reserve_wifi_cancel", c9837ox);
                        interfaceC9843b.mo7599mb(c9837ox);
                    }
                });
                TTDelegateActivity.m7712ox(c9837ox);
                return true;
            }
            return false;
        }
    }
}
