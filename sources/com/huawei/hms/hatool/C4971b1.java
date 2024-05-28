package com.huawei.hms.hatool;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.b1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4971b1 implements InterfaceC5014o1 {

    /* renamed from: a */
    private String f11362a;

    /* renamed from: b */
    private String f11363b;

    /* renamed from: c */
    private String f11364c;

    /* renamed from: d */
    private String f11365d;

    /* renamed from: e */
    private String f11366e;

    /* renamed from: f */
    private String f11367f;

    @Override // com.huawei.hms.hatool.InterfaceC5014o1
    /* renamed from: a */
    public JSONObject mo14424a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.f11362a);
        jSONObject.put("eventtime", this.f11365d);
        jSONObject.put("event", this.f11363b);
        jSONObject.put("event_session_name", this.f11366e);
        jSONObject.put("first_session_event", this.f11367f);
        if (TextUtils.isEmpty(this.f11364c)) {
            return null;
        }
        jSONObject.put("properties", new JSONObject(this.f11364c));
        return jSONObject;
    }

    /* renamed from: a */
    public void m14792a(String str) {
        this.f11364c = str;
    }

    /* renamed from: a */
    public void m14791a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f11363b = jSONObject.optString("event");
        this.f11364c = jSONObject.optString("properties");
        this.f11364c = C5008n.m14592a(this.f11364c, C5013o0.m14565d().m14571a());
        this.f11362a = jSONObject.optString("type");
        this.f11365d = jSONObject.optString("eventtime");
        this.f11366e = jSONObject.optString("event_session_name");
        this.f11367f = jSONObject.optString("first_session_event");
    }

    /* renamed from: b */
    public String m14790b() {
        return this.f11365d;
    }

    /* renamed from: b */
    public void m14789b(String str) {
        this.f11363b = str;
    }

    /* renamed from: c */
    public String m14788c() {
        return this.f11362a;
    }

    /* renamed from: c */
    public void m14787c(String str) {
        this.f11365d = str;
    }

    /* renamed from: d */
    public JSONObject m14786d() {
        JSONObject mo14424a = mo14424a();
        mo14424a.put("properties", C5008n.m14590b(this.f11364c, C5013o0.m14565d().m14571a()));
        return mo14424a;
    }

    /* renamed from: d */
    public void m14785d(String str) {
        this.f11362a = str;
    }

    /* renamed from: e */
    public void m14784e(String str) {
        this.f11367f = str;
    }

    /* renamed from: f */
    public void m14783f(String str) {
        this.f11366e = str;
    }
}
