package com.bytedance.applog;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.j0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3606j0 extends AbstractC3692t {

    /* renamed from: e */
    public final C3726x f8520e;

    public C3606j0(C3726x c3726x, C3735y c3735y) {
        super(true, false, false);
        this.f8520e = c3726x;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        SharedPreferences sharedPreferences = this.f8520e.f8900e;
        String string = sharedPreferences.getString("bd_did", null);
        C3735y.m17019a(jSONObject, "bd_did", string);
        String string2 = sharedPreferences.getString("install_id", null);
        String string3 = sharedPreferences.getString("ssid", null);
        C3735y.m17019a(jSONObject, "install_id", string2);
        C3735y.m17019a(jSONObject, "ssid", string3);
        long j = 0;
        long j2 = sharedPreferences.getLong("register_time", 0L);
        if ((C3735y.m17007f(string2) && (C3735y.m17007f(null) || C3735y.m17007f(string))) || j2 == 0) {
            j = j2;
        } else {
            sharedPreferences.edit().putLong("register_time", 0L).apply();
        }
        jSONObject.put("register_time", j);
        return true;
    }
}
