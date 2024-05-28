package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.i0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3601i0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8498e;

    /* renamed from: f */
    public final C3726x f8499f;

    /* renamed from: g */
    public final C3735y f8500g;

    public C3601i0(Context context, C3726x c3726x, C3735y c3735y) {
        super(true, false);
        this.f8498e = context;
        this.f8499f = c3726x;
        this.f8500g = c3735y;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    @SuppressLint({"HardwareIds"})
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        String[] m17274d;
        jSONObject.put("build_serial", C3710v0.m17086c(this.f8498e));
        C3735y.m17019a(jSONObject, "aliyun_uuid", this.f8499f.f8897b.getAliyunUdid());
        if (this.f8499f.f8897b.isMacEnable()) {
            String m17104a = C3710v0.m17104a(this.f8498e);
            SharedPreferences sharedPreferences = this.f8499f.f8900e;
            String string = sharedPreferences.getString("mac_address", null);
            if (!TextUtils.isEmpty(m17104a)) {
                if (!TextUtils.equals(string, m17104a)) {
                    C3535a.m17350a(sharedPreferences, "mac_address", m17104a);
                }
                jSONObject.put("mc", m17104a);
            } else if (!TextUtils.isEmpty(string)) {
                jSONObject.put("mc", string);
            }
        }
        C3735y.m17019a(jSONObject, "udid", ((C3603i2) this.f8500g.f8939g).m17273e());
        JSONArray m17272f = ((C3603i2) this.f8500g.f8939g).m17272f();
        if (C3710v0.m17094a(m17272f)) {
            jSONObject.put("udid_list", m17272f);
        }
        C3735y.m17019a(jSONObject, "serial_number", ((C3603i2) this.f8500g.f8939g).m17275c());
        if (this.f8500g.m17005h() && (m17274d = ((C3603i2) this.f8500g.f8939g).m17274d()) != null) {
            JSONArray jSONArray = new JSONArray();
            for (String str : m17274d) {
                jSONArray.put(new JSONObject().put("sim_serial_number", str));
            }
            jSONObject.put("sim_serial_number", jSONArray);
        }
        if (C3536a0.m17343b(this.f8498e)) {
            ((C3603i2) this.f8500g.f8939g).m17271g();
            throw null;
        }
        return true;
    }
}
