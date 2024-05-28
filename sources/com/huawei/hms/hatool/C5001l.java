package com.huawei.hms.hatool;

import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5001l extends AbstractC5025t {

    /* renamed from: b */
    private String f11443b = "";

    /* renamed from: c */
    private String f11444c = "";

    /* renamed from: d */
    private String f11445d = "";

    /* renamed from: e */
    private String f11446e = "";

    /* renamed from: f */
    protected String f11447f = "";

    /* renamed from: g */
    private String f11448g;

    @Override // com.huawei.hms.hatool.InterfaceC5014o1
    /* renamed from: a */
    public JSONObject mo14424a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("androidid", this.f11509a);
        jSONObject.put("oaid", this.f11448g);
        jSONObject.put("uuid", this.f11447f);
        jSONObject.put("upid", this.f11446e);
        jSONObject.put("imei", this.f11443b);
        jSONObject.put("sn", this.f11444c);
        jSONObject.put("udid", this.f11445d);
        return jSONObject;
    }

    /* renamed from: b */
    public void m14630b(String str) {
        this.f11443b = str;
    }

    /* renamed from: c */
    public void m14629c(String str) {
        this.f11448g = str;
    }

    /* renamed from: d */
    public void m14628d(String str) {
        this.f11444c = str;
    }

    /* renamed from: e */
    public void m14627e(String str) {
        this.f11445d = str;
    }

    /* renamed from: f */
    public void m14626f(String str) {
        this.f11446e = str;
    }

    /* renamed from: g */
    public void m14625g(String str) {
        this.f11447f = str;
    }
}
