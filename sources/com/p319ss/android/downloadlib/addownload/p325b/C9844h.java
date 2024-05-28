package com.p319ss.android.downloadlib.addownload.p325b;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.b.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9844h implements InterfaceC9845hj {
    @Override // com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9845hj
    /* renamed from: mb */
    public boolean mo7691mb(C9837ox c9837ox, int i, InterfaceC9843b interfaceC9843b) {
        if (c9837ox != null && m7700ox(c9837ox.mo7479m())) {
            if (System.currentTimeMillis() - c9837ox.m7751sa() <= m7701mb(c9837ox.mo7479m())) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("pause_optimise_type", "mistake_click");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, c9837ox);
                return true;
            }
            return false;
        }
        return false;
    }

    /* renamed from: mb */
    private long m7701mb(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_mistake_click_interval", 300);
    }

    /* renamed from: ox */
    private boolean m7700ox(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_mistake_click_interval_switch", 0) == 1;
    }
}
