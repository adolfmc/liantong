package com.p319ss.android.downloadlib.addownload.p325b;

import android.content.Context;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.C10071ww;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.C9904lz;
import com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.b.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9847mb implements InterfaceC9845hj {

    /* renamed from: mb */
    private static InterfaceC9906b f18915mb;

    /* renamed from: mb */
    public static InterfaceC9906b m7699mb() {
        return f18915mb;
    }

    @Override // com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9845hj
    /* renamed from: mb */
    public boolean mo7691mb(final C9837ox c9837ox, int i, final InterfaceC9843b interfaceC9843b) {
        DownloadInfo m6947ox;
        if (c9837ox == null || c9837ox.m7787kk() || !m7696mb(c9837ox) || (m6947ox = C10071ww.m6960mb((Context) null).m6947ox(c9837ox.mo7478mb())) == null) {
            return false;
        }
        long m7535mb = C9904lz.m7535mb(m6947ox.getId(), m6947ox.getCurBytes(), m6947ox.getTotalBytes());
        long totalBytes = m6947ox.getTotalBytes();
        if (m7535mb <= 0 || totalBytes <= 0 || totalBytes > m7698mb(c9837ox.mo7479m())) {
            return false;
        }
        f18915mb = new InterfaceC9906b() { // from class: com.ss.android.downloadlib.addownload.b.mb.1
            @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
            /* renamed from: mb */
            public void mo7530mb() {
                InterfaceC9906b unused = C9847mb.f18915mb = null;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("pause_optimise_type", "apk_size");
                    jSONObject.putOpt("pause_optimise_action", "confirm");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, c9837ox);
            }

            @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
            /* renamed from: ox */
            public void mo7529ox() {
                InterfaceC9906b unused = C9847mb.f18915mb = null;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("pause_optimise_type", "apk_size");
                    jSONObject.putOpt("pause_optimise_action", "cancel");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, c9837ox);
                interfaceC9843b.mo7599mb(c9837ox);
            }
        };
        TTDelegateActivity.m7719mb(c9837ox, String.format("该下载任务仅需%s，即将下载完成，是否继续下载？", m7697mb(totalBytes - m7535mb)), "继续", "暂停");
        c9837ox.m7763o(true);
        return true;
    }

    /* renamed from: mb */
    private int m7698mb(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_apk_size", 100) * 1024 * 1024;
    }

    /* renamed from: mb */
    private boolean m7696mb(InterfaceC9836mb interfaceC9836mb) {
        return C10049hj.m7071mb(interfaceC9836mb).optInt("pause_optimise_apk_size_switch", 0) == 1 && interfaceC9836mb.mo7492e();
    }

    /* renamed from: mb */
    private static String m7697mb(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j >= 1073741824) {
            return (j / 1073741824) + "G";
        } else if (j >= 1048576) {
            return (j / 1048576) + "M";
        } else {
            return decimalFormat.format(((float) j) / 1048576.0f) + "M";
        }
    }
}
