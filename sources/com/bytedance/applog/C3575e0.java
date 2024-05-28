package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.e0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3575e0 extends AbstractC3692t {

    /* renamed from: e */
    public Context f8428e;

    public C3575e0(Context context) {
        super(true, false);
        this.f8428e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        boolean m17343b = C3536a0.m17343b(this.f8428e);
        if (m17343b) {
            jSONObject.put("new_user_mode", 1);
        }
        if (C3704u2.f8845b || m17343b) {
            C3704u2.m17108a("new user mode = " + m17343b, (Throwable) null);
        }
        return true;
    }
}
