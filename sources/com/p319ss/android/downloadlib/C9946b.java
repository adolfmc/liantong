package com.p319ss.android.downloadlib;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9887hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.p328ox.RunnableC9936ox;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10048h;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10100ww;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9946b implements InterfaceC10100ww {

    /* renamed from: mb */
    private static String f19178mb = "b";

    /* renamed from: ox */
    private Handler f19179ox = new Handler(Looper.getMainLooper());

    /* renamed from: mb */
    private void m7339mb(@NonNull DownloadInfo downloadInfo) {
        if (C10049hj.m7064u(downloadInfo.getId())) {
            C9982hj.m7254mb().m7249ox(new RunnableC9936ox(downloadInfo));
        }
    }

    /* renamed from: mb */
    private void m7338mb(final DownloadInfo downloadInfo, final C9837ox c9837ox) {
        final long m7047mb = C10050jb.m7047mb(Environment.getDataDirectory(), -1L);
        long min = Math.min(524288000L, C10050jb.m7048mb(Environment.getDataDirectory()) / 10);
        final long totalBytes = downloadInfo.getTotalBytes();
        final double d = (totalBytes * 2.5d) + min;
        if (m7047mb > -1 && totalBytes > -1) {
            double d2 = m7047mb;
            if (d2 < d && d - d2 > C9887hj.m7577ox()) {
                C9887hj.m7592mb(downloadInfo.getId());
            }
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.b.3
            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                if (!C10050jb.m7031ox(c9837ox)) {
                    long j = m7047mb;
                    if (j <= -1 || totalBytes <= -1 || j >= d) {
                        return;
                    }
                    AdEventHandler.m7315mb().m7298mb("clean_space_install", C9887hj.m7581mb("install_no_enough_space"), c9837ox);
                    if (C9887hj.m7582mb(downloadInfo, ((long) d) - m7047mb)) {
                        AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                        c9837ox.m7782ko(true);
                        return;
                    }
                    return;
                }
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
            }
        });
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10100ww
    /* renamed from: mb */
    public void mo6869mb(DownloadInfo downloadInfo, BaseException baseException, int i) {
        final DownloadModel m7449mb;
        if (downloadInfo == null) {
            return;
        }
        if (i == -1 && baseException != null) {
            JSONObject jSONObject = new JSONObject();
            C10048h.m7089b(downloadInfo, jSONObject);
            C9998mb.m7199mb(jSONObject, downloadInfo);
            C10070x.m6968mb("download_failed", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            return;
        }
        try {
            if (i != -1) {
                if (i == -3) {
                    C9998mb.m7205mb(downloadInfo, m7442mb);
                    return;
                } else if (i == 2001) {
                    C9998mb.m7215mb().m7204mb(downloadInfo, m7442mb, 2001);
                    return;
                } else if (i == 11) {
                    C9998mb.m7215mb().m7204mb(downloadInfo, m7442mb, 2000);
                    if (m7442mb.m7812fb()) {
                        return;
                    }
                    m7338mb(downloadInfo, m7442mb);
                    return;
                } else {
                    return;
                }
            }
            BaseException baseException2 = null;
            if (baseException != null) {
                if (DownloadSetting.obtain(downloadInfo.getId()).optInt("toast_without_network", 0) == 1 && baseException.getErrorCode() == 1049) {
                    this.f19179ox.post(new Runnable() { // from class: com.ss.android.downloadlib.b.1
                        @Override // java.lang.Runnable
                        public void run() {
                            C9940x.m7377b().mo7905mb(5, C9940x.getContext(), null, "无网络，请检查网络设置", null, 0);
                        }
                    });
                }
                if (DownloadUtils.isInsufficientSpaceError(baseException)) {
                    if (C9940x.m7368je() != null) {
                        C9940x.m7368je().m7937mb(m7442mb.mo7474ox());
                    }
                    AdEventHandler.m7315mb().m7303mb("download_failed_for_space", m7442mb);
                    if (!m7442mb.m7798i()) {
                        AdEventHandler.m7315mb().m7303mb("download_can_restart", m7442mb);
                        m7339mb(downloadInfo);
                    }
                    if ((C9940x.m7368je() == null || !C9940x.m7368je().m7940hj()) && (m7449mb = C9923u.m7451mb().m7449mb(m7442mb.mo7474ox())) != null && m7449mb.isShowToast()) {
                        final DownloadSetting obtain = DownloadSetting.obtain(downloadInfo.getId());
                        if (obtain.optInt("show_no_enough_space_toast", 0) == 1) {
                            this.f19179ox.post(new Runnable() { // from class: com.ss.android.downloadlib.b.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    C9940x.m7377b().mo7905mb(2, C9940x.getContext(), m7449mb, obtain.optString("no_enough_space_toast_text", "您的存储空间不足，请清理后再试"), null, 0);
                                }
                            });
                        }
                    }
                }
                baseException2 = new BaseException(baseException.getErrorCode(), C10050jb.m7045mb(baseException.getMessage(), C9940x.m7364lz().optInt("exception_msg_length", 500)));
            }
            AdEventHandler.m7315mb().m7295ox(downloadInfo, baseException2);
            C9992ko.m7236mb().m7230mb(downloadInfo, baseException, "");
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "onAppDownloadMonitorSend");
        }
    }
}
