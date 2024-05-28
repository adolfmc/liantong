package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.h1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4989h1 implements InterfaceC5014o1 {

    /* renamed from: a */
    private List<C4971b1> f11409a;

    /* renamed from: b */
    private AbstractC4999k0 f11410b;

    /* renamed from: c */
    private AbstractC5026t0 f11411c;

    /* renamed from: d */
    private InterfaceC5014o1 f11412d;

    /* renamed from: e */
    private String f11413e = "";

    /* renamed from: f */
    private String f11414f;

    public C4989h1(String str) {
        this.f11414f = str;
    }

    @Override // com.huawei.hms.hatool.InterfaceC5014o1
    /* renamed from: a */
    public JSONObject mo14424a() {
        String str;
        String str2;
        List<C4971b1> list = this.f11409a;
        if (list == null || list.size() == 0) {
            str = "hmsSdk";
            str2 = "Not have actionEvent to send";
        } else if (this.f11410b == null || this.f11411c == null || this.f11412d == null) {
            str = "hmsSdk";
            str2 = "model in wrong format";
        } else {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("header", this.f11410b.mo14424a());
            JSONObject jSONObject2 = new JSONObject();
            JSONObject mo14424a = this.f11412d.mo14424a();
            mo14424a.put("properties", this.f11411c.mo14424a());
            try {
                mo14424a.put("events_global_properties", new JSONObject(this.f11413e));
            } catch (JSONException unused) {
                mo14424a.put("events_global_properties", this.f11413e);
            }
            jSONObject2.put("events_common", mo14424a);
            JSONArray jSONArray = new JSONArray();
            for (C4971b1 c4971b1 : this.f11409a) {
                JSONObject mo14424a2 = c4971b1.mo14424a();
                if (mo14424a2 != null) {
                    jSONArray.put(mo14424a2);
                } else {
                    C5029v.m14452e("hmsSdk", "custom event is empty,delete this event");
                }
            }
            jSONObject2.put("events", jSONArray);
            try {
                String m14591a = C5008n.m14591a(C5000k1.m14631a(jSONObject2.toString().getBytes("UTF-8")), this.f11414f);
                if (TextUtils.isEmpty(m14591a)) {
                    C5029v.m14452e("hmsSdk", "eventInfo encrypt failed,report over!");
                    return null;
                }
                jSONObject.put("event", m14591a);
                return jSONObject;
            } catch (UnsupportedEncodingException unused2) {
                str = "hmsSdk";
                str2 = "getBitZip(): Unsupported coding : utf-8";
            }
        }
        C5029v.m14452e(str, str2);
        return null;
    }

    /* renamed from: a */
    public void m14685a(AbstractC4999k0 abstractC4999k0) {
        this.f11410b = abstractC4999k0;
    }

    /* renamed from: a */
    public void m14684a(C5001l c5001l) {
        this.f11412d = c5001l;
    }

    /* renamed from: a */
    public void m14683a(AbstractC5026t0 abstractC5026t0) {
        this.f11411c = abstractC5026t0;
    }

    /* renamed from: a */
    public void m14682a(String str) {
        if (str != null) {
            this.f11413e = str;
        }
    }

    /* renamed from: a */
    public void m14681a(List<C4971b1> list) {
        this.f11409a = list;
    }
}
