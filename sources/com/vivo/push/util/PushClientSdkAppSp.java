package com.vivo.push.util;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.vivo.push.util.z */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushClientSdkAppSp extends BaseSharePreference {

    /* renamed from: b */
    private Context f21245b;

    public PushClientSdkAppSp(Context context) {
        if (context != null) {
            this.f21245b = context;
            m5333a(this.f21245b);
        }
    }

    /* renamed from: a */
    private synchronized void m5333a(Context context) {
        m5422a(context, "com.vivo.push_preferences.appconfig_v1");
    }

    /* renamed from: b */
    public final String m5332b() {
        String obj;
        Context context = this.f21245b;
        String packageName = context.getPackageName();
        Object m5445a = Utility.m5445a(context, packageName, "com.vivo.push.app_id");
        if (m5445a != null) {
            obj = m5445a.toString();
        } else {
            Object m5445a2 = Utility.m5445a(context, packageName, "app_id");
            obj = m5445a2 != null ? m5445a2.toString() : "";
        }
        return TextUtils.isEmpty(obj) ? m5411b("APP_APPID", "") : obj;
    }

    /* renamed from: c */
    public final String m5331c() {
        String obj;
        Context context = this.f21245b;
        String packageName = context.getPackageName();
        Object m5445a = Utility.m5445a(context, packageName, "com.vivo.push.api_key");
        if (m5445a != null) {
            obj = m5445a.toString();
        } else {
            Object m5445a2 = Utility.m5445a(context, packageName, "api_key");
            obj = m5445a2 != null ? m5445a2.toString() : "";
        }
        return TextUtils.isEmpty(obj) ? m5411b("APP_APIKEY", "") : obj;
    }
}
