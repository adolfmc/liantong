package com.bytedance.applog;

import android.content.Context;
import android.telephony.TelephonyManager;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.l0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3619l0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8543e;

    public C3619l0(Context context) {
        super(true, false);
        this.f8543e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        C3735y.m17019a(jSONObject, "sim_region", ((TelephonyManager) this.f8543e.getSystemService("phone")).getSimCountryIso());
        return true;
    }
}
