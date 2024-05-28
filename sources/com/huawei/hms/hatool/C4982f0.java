package com.huawei.hms.hatool;

import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.f0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4982f0 extends AbstractC4999k0 {

    /* renamed from: g */
    private String f11386g = "";

    @Override // com.huawei.hms.hatool.InterfaceC5014o1
    /* renamed from: a */
    public JSONObject mo14424a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protocol_version", "1");
        jSONObject.put("compress_mode", "1");
        jSONObject.put("serviceid", this.f11440d);
        jSONObject.put("appid", this.f11437a);
        jSONObject.put("hmac", this.f11386g);
        jSONObject.put("chifer", this.f11442f);
        jSONObject.put("timestamp", this.f11438b);
        jSONObject.put("servicetag", this.f11439c);
        jSONObject.put("requestid", this.f11441e);
        return jSONObject;
    }

    /* renamed from: g */
    public void m14738g(String str) {
        this.f11386g = str;
    }
}
