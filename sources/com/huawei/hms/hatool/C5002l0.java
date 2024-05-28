package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.l0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5002l0 {

    /* renamed from: a */
    private String f11449a;

    /* renamed from: b */
    private String f11450b;

    /* renamed from: c */
    private String f11451c;

    /* renamed from: d */
    private List<C4971b1> f11452d;

    /* renamed from: e */
    private String f11453e;

    public C5002l0(String str, String str2, String str3, List<C4971b1> list, String str4) {
        this.f11449a = str;
        this.f11450b = str2;
        this.f11451c = str3;
        this.f11452d = list;
        this.f11453e = str4;
    }

    /* renamed from: a */
    private String m14622a(String str, String str2) {
        String str3;
        String m14812f = AbstractC4966a1.m14812f(str, str2);
        if (TextUtils.isEmpty(m14812f)) {
            C5029v.m14460a("hmsSdk", "No report address,TAG : %s,TYPE: %s ", str, str2);
            return "";
        }
        if ("oper".equals(str2)) {
            str3 = "{url}/common/hmshioperqrt";
        } else if ("maint".equals(str2)) {
            str3 = "{url}/common/hmshimaintqrt";
        } else if (!"diffprivacy".equals(str2)) {
            return "";
        } else {
            str3 = "{url}/common/common2";
        }
        return str3.replace("{url}", m14812f);
    }

    /* renamed from: a */
    private byte[] m14623a(C4989h1 c4989h1) {
        String str;
        String str2;
        try {
            JSONObject mo14424a = c4989h1.mo14424a();
            if (mo14424a == null) {
                C5029v.m14452e("hmsSdk", "uploadEvents is null");
                return new byte[0];
            }
            return C5000k1.m14631a(mo14424a.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            str = "hmsSdk";
            str2 = "sendData(): getBytes - Unsupported coding format!!";
            C5029v.m14452e(str, str2);
            return new byte[0];
        } catch (JSONException unused2) {
            str = "hmsSdk";
            str2 = "uploadEvents to json error";
            C5029v.m14452e(str, str2);
            return new byte[0];
        }
    }

    /* renamed from: b */
    private void m14621b() {
        if (C4973c0.m14778a(AbstractC5020q0.m14526i(), "backup_event", 5242880)) {
            C5029v.m14453d("hmsSdk", "backup file reach max limited size, discard new event ");
            return;
        }
        JSONArray m14620c = m14620c();
        String m14583a = AbstractC5010n1.m14583a(this.f11449a, this.f11450b, this.f11453e);
        C5029v.m14455c("hmsSdk", "Update data cached into backup,spKey: " + m14583a);
        C4975d.m14763b(AbstractC5020q0.m14526i(), "backup_event", m14583a, m14620c.toString());
    }

    /* renamed from: c */
    private JSONArray m14620c() {
        JSONArray jSONArray = new JSONArray();
        for (C4971b1 c4971b1 : this.f11452d) {
            try {
                jSONArray.put(c4971b1.m14786d());
            } catch (JSONException unused) {
                C5029v.m14455c("hmsSdk", "handleEvents: json error,Abandon this data");
            }
        }
        return jSONArray;
    }

    /* renamed from: d */
    private C4989h1 m14619d() {
        return C4997k.m14645a(this.f11452d, this.f11449a, this.f11450b, this.f11453e, this.f11451c);
    }

    /* renamed from: a */
    public void m14624a() {
        InterfaceRunnableC4984g c4977d1;
        C4968b0 m14793c;
        String str;
        String str2;
        String m14622a = m14622a(this.f11449a, this.f11450b);
        if (!TextUtils.isEmpty(m14622a) || "preins".equals(this.f11450b)) {
            if (!"_hms_config_tag".equals(this.f11449a) && !"_openness_config_tag".equals(this.f11449a)) {
                m14621b();
            }
            C4989h1 m14619d = m14619d();
            if (m14619d != null) {
                byte[] m14623a = m14623a(m14619d);
                if (m14623a.length == 0) {
                    str = "hmsSdk";
                    str2 = "request body is empty";
                } else {
                    c4977d1 = new C4981f(m14623a, m14622a, this.f11449a, this.f11450b, this.f11453e, this.f11452d);
                    m14793c = C4968b0.m14794b();
                }
            } else {
                c4977d1 = new C4977d1(this.f11452d, this.f11449a, this.f11453e, this.f11450b);
                m14793c = C4968b0.m14793c();
            }
            m14793c.m14795a(c4977d1);
            return;
        }
        str = "hmsSdk";
        str2 = "collectUrl is empty";
        C5029v.m14452e(str, str2);
    }
}
