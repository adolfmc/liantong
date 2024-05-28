package com.p319ss.android.downloadlib.addownload.model;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.model.ko */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9920ko {

    /* renamed from: mb */
    private static volatile C9920ko f19113mb;

    private C9920ko() {
    }

    /* renamed from: mb */
    public static C9920ko m7461mb() {
        if (f19113mb == null) {
            synchronized (C9917hj.class) {
                if (f19113mb == null) {
                    f19113mb = new C9920ko();
                }
            }
        }
        return f19113mb;
    }

    /* renamed from: mb */
    public void m7460mb(int i, int i2, C9837ox c9837ox) {
        if (c9837ox == null) {
            return;
        }
        DownloadSetting obtain = DownloadSetting.obtain(c9837ox.mo7479m());
        if (obtain.optInt("report_api_hijack", 0) == 0) {
            return;
        }
        int i3 = i2 - i;
        if (i <= 0 || i3 <= obtain.optInt("check_api_hijack_version_code_diff", 500)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version_code_diff", i3);
            jSONObject.put("installed_version_code", i2);
            jSONObject.put("hijack_type", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7293ox("api_hijack", jSONObject, c9837ox);
    }
}
