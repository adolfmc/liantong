package com.huawei.hms.hatool;

import android.os.Build;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.y0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5037y0 extends AbstractC5026t0 {

    /* renamed from: f */
    String f11527f;

    /* renamed from: g */
    String f11528g;

    /* renamed from: h */
    private String f11529h;

    @Override // com.huawei.hms.hatool.InterfaceC5014o1
    /* renamed from: a */
    public JSONObject mo14424a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("_rom_ver", this.f11529h);
        jSONObject.put("_emui_ver", this.f11510a);
        jSONObject.put("_model", Build.MODEL);
        jSONObject.put("_mcc", this.f11527f);
        jSONObject.put("_mnc", this.f11528g);
        jSONObject.put("_package_name", this.f11511b);
        jSONObject.put("_app_ver", this.f11512c);
        jSONObject.put("_lib_ver", "2.2.0.313");
        jSONObject.put("_channel", this.f11513d);
        jSONObject.put("_lib_name", "hianalytics");
        jSONObject.put("_oaid_tracking_flag", this.f11514e);
        return jSONObject;
    }

    /* renamed from: f */
    public void m14423f(String str) {
        this.f11527f = str;
    }

    /* renamed from: g */
    public void m14422g(String str) {
        this.f11528g = str;
    }

    /* renamed from: h */
    public void m14421h(String str) {
        this.f11529h = str;
    }
}
