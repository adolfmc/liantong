package com.vivo.push.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/* renamed from: com.vivo.push.util.ac */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class SettingsCache implements Cache {

    /* renamed from: a */
    private ContentResolver f21180a;

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final boolean mo5409a(Context context) {
        if (Device.m5383b()) {
            this.f21180a = context.getContentResolver();
            return true;
        }
        return false;
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: a */
    public final String mo5408a(String str, String str2) {
        try {
            return Settings.System.getString(this.f21180a, str);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5346b("SettingsCache", "getString error by ".concat(String.valueOf(str)));
            return str2;
        }
    }

    @Override // com.vivo.push.util.Cache
    /* renamed from: b */
    public final void mo5407b(String str, String str2) {
        try {
            Settings.System.putString(this.f21180a, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5346b("SettingsCache", "putString error by ".concat(String.valueOf(str)));
        }
    }
}
