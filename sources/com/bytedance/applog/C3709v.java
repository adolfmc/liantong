package com.bytedance.applog;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.v */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3709v extends AbstractC3692t {

    /* renamed from: e */
    public final C3726x f8857e;

    public C3709v(C3726x c3726x) {
        super(true, false);
        this.f8857e = c3726x;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        String m17313a = C3572d3.m17313a(this.f8857e.f8900e);
        if (TextUtils.isEmpty(m17313a)) {
            return false;
        }
        jSONObject.put("cdid", m17313a);
        return true;
    }
}
