package com.bytedance.applog;

import android.content.Context;
import android.telephony.TelephonyManager;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.z */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3743z extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8964e;

    /* renamed from: f */
    public final C3735y f8965f;

    public C3743z(Context context, C3735y c3735y) {
        super(false, false);
        this.f8964e = context;
        this.f8965f = c3735y;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        TelephonyManager telephonyManager = (TelephonyManager) this.f8964e.getSystemService("phone");
        if (telephonyManager != null) {
            C3735y.m17019a(jSONObject, "carrier", telephonyManager.getNetworkOperatorName());
            C3735y.m17019a(jSONObject, "mcc_mnc", telephonyManager.getNetworkOperator());
        }
        C3735y.m17019a(jSONObject, "clientudid", ((C3603i2) this.f8965f.f8939g).m17282a());
        C3735y.m17019a(jSONObject, "openudid", ((C3603i2) this.f8965f.f8939g).m17277a(true));
        if (C3536a0.m17343b(this.f8964e)) {
            jSONObject.remove("google_aid");
        }
        return true;
    }
}
