package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.a0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4965a0 implements InterfaceRunnableC4984g {

    /* renamed from: a */
    private Context f11341a = AbstractC5020q0.m14526i();

    /* renamed from: b */
    private String f11342b;

    /* renamed from: c */
    private JSONObject f11343c;

    /* renamed from: d */
    private String f11344d;

    /* renamed from: e */
    private String f11345e;

    /* renamed from: f */
    private String f11346f;

    /* renamed from: g */
    private String f11347g;

    /* renamed from: h */
    private Boolean f11348h;

    public C4965a0(String str, JSONObject jSONObject, String str2, String str3, long j) {
        this.f11342b = str;
        this.f11343c = jSONObject;
        this.f11344d = str2;
        this.f11345e = str3;
        this.f11346f = String.valueOf(j);
        if (AbstractC5038z.m14412i(str2, "oper")) {
            C5016p0 m14426a = C5036y.m14428a().m14426a(str2, j);
            this.f11347g = m14426a.m14560a();
            this.f11348h = Boolean.valueOf(m14426a.m14556b());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        C5029v.m14455c("hmsSdk", "Begin to run EventRecordTask...");
        int m14528h = AbstractC5020q0.m14528h();
        int m14807k = AbstractC4966a1.m14807k(this.f11344d, this.f11345e);
        if (C4973c0.m14778a(this.f11341a, "stat_v2_1", m14528h * 1048576)) {
            C5029v.m14455c("hmsSdk", "stat sp file reach max limited size, discard new event");
            C4978e.m14760a().m14755a("", "alltype");
            return;
        }
        C4971b1 c4971b1 = new C4971b1();
        c4971b1.m14789b(this.f11342b);
        c4971b1.m14792a(this.f11343c.toString());
        c4971b1.m14785d(this.f11345e);
        c4971b1.m14787c(this.f11346f);
        c4971b1.m14783f(this.f11347g);
        Boolean bool = this.f11348h;
        c4971b1.m14784e(bool == null ? null : String.valueOf(bool));
        try {
            JSONObject m14786d = c4971b1.m14786d();
            String m14584a = AbstractC5010n1.m14584a(this.f11344d, this.f11345e);
            String m14767a = C4975d.m14767a(this.f11341a, "stat_v2_1", m14584a, "");
            try {
                jSONArray = !TextUtils.isEmpty(m14767a) ? new JSONArray(m14767a) : new JSONArray();
            } catch (JSONException unused) {
                C5029v.m14453d("hmsSdk", "Cached data corrupted: stat_v2_1");
                jSONArray = new JSONArray();
            }
            jSONArray.put(m14786d);
            C4975d.m14763b(this.f11341a, "stat_v2_1", m14584a, jSONArray.toString());
            if (jSONArray.toString().length() > m14807k * 1024) {
                C4978e.m14760a().m14755a(this.f11344d, this.f11345e);
            }
        } catch (JSONException unused2) {
            C5029v.m14452e("hmsSdk", "eventRecord toJson error! The record failed.");
        }
    }
}
