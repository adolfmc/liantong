package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.vivo.push.util.ae */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SpCache implements Cache {

    /* renamed from: a */
    private static String f21182a = "SpCache";

    /* renamed from: b */
    private static String f21183b = "com.vivo.push.cache";

    /* renamed from: c */
    private SharedPreferences f21184c;

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final boolean mo5409a(Context context) {
        if (this.f21184c == null) {
            this.f21184c = context.getSharedPreferences(f21183b, 0);
            return true;
        }
        return true;
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final String mo5408a(String str, String str2) {
        String string = this.f21184c.getString(str, str2);
        String str3 = f21182a;
        LogUtil.m5341d(str3, "getString " + str + " is " + string);
        return string;
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: b */
    public final void mo5407b(String str, String str2) {
        SharedPreferences.Editor edit = this.f21184c.edit();
        if (edit != null) {
            edit.putString(str, str2);
            BaseSharePreference.m5421a(edit);
            LogUtil.m5341d(f21182a, "putString by ".concat(String.valueOf(str)));
            return;
        }
        LogUtil.m5346b(f21182a, "putString error by ".concat(String.valueOf(str)));
    }

    /* renamed from: a */
    public final void m5451a() {
        SharedPreferences.Editor edit = this.f21184c.edit();
        if (edit != null) {
            edit.clear();
            BaseSharePreference.m5421a(edit);
        }
        LogUtil.m5341d(f21182a, "system cache is cleared");
    }
}
