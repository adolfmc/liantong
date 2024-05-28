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
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.b.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9849ox implements InterfaceC9845hj {

    /* renamed from: mb */
    private static InterfaceC9906b f18919mb;

    /* renamed from: mb */
    public static InterfaceC9906b m7694mb() {
        return f18919mb;
    }

    @Override // com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9845hj
    /* renamed from: mb */
    public boolean mo7691mb(final C9837ox c9837ox, int i, final InterfaceC9843b interfaceC9843b) {
        DownloadInfo m6947ox;
        if (c9837ox == null || c9837ox.m7788kg() || !m7692mb(c9837ox) || (m6947ox = C10071ww.m6960mb((Context) null).m6947ox(c9837ox.mo7478mb())) == null) {
            return false;
        }
        long curBytes = m6947ox.getCurBytes();
        long totalBytes = m6947ox.getTotalBytes();
        if (curBytes > 0 && totalBytes > 0) {
            int m7536mb = C9904lz.m7536mb(m6947ox.getId(), (int) ((curBytes * 100) / totalBytes));
            if (m7536mb > m7693mb(c9837ox.mo7479m())) {
                f18919mb = new InterfaceC9906b() { // from class: com.ss.android.downloadlib.addownload.b.ox.1
                    @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
                    /* renamed from: mb */
                    public void mo7530mb() {
                        InterfaceC9906b unused = C9849ox.f18919mb = null;
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.putOpt("pause_optimise_type", "download_percent");
                            jSONObject.putOpt("pause_optimise_action", "confirm");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, c9837ox);
                    }

                    @Override // com.p319ss.android.downloadlib.addownload.p327mb.InterfaceC9906b
                    /* renamed from: ox */
                    public void mo7529ox() {
                        InterfaceC9906b unused = C9849ox.f18919mb = null;
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.putOpt("pause_optimise_type", "download_percent");
                            jSONObject.putOpt("pause_optimise_action", "cancel");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, c9837ox);
                        interfaceC9843b.mo7599mb(c9837ox);
                    }
                };
                TTDelegateActivity.m7711ox(c9837ox, String.format("已下载%s%%，即将下载完成，是否继续下载？", Integer.valueOf(m7536mb)), "继续", "暂停");
                c9837ox.m7780lc(true);
                return true;
            }
        }
        return false;
    }

    /* renamed from: mb */
    private int m7693mb(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_download_percent", 50);
    }

    /* renamed from: mb */
    private boolean m7692mb(InterfaceC9836mb interfaceC9836mb) {
        return C10049hj.m7071mb(interfaceC9836mb).optInt("pause_optimise_download_percent_switch", 0) == 1 && interfaceC9836mb.mo7492e();
    }
}
