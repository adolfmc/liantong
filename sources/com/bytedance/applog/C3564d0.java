package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.d0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3564d0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8405e;

    public C3564d0(Context context) {
        super(true, true);
        this.f8405e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        C3735y.m17019a(jSONObject, "access", C3730x2.m17033a(this.f8405e));
        return true;
    }
}
