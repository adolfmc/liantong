package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.f0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3580f0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8444e;

    /* renamed from: f */
    public final C3726x f8445f;

    public C3580f0(Context context, C3726x c3726x) {
        super(true, false);
        this.f8444e = context;
        this.f8445f = c3726x;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        SharedPreferences sharedPreferences = this.f8445f.f8900e;
        Map m17314a = C3572d3.m17314a(this.f8444e);
        if (m17314a != null) {
            jSONObject.put("oaid", new JSONObject(m17314a));
            return true;
        }
        return false;
    }
}
