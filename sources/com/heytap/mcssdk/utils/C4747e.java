package com.heytap.mcssdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.heytap.mcssdk.PushService;

/* renamed from: com.heytap.mcssdk.utils.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4747e {

    /* renamed from: a */
    private static final String f10715a = "shared_msg_sdk";

    /* renamed from: b */
    private static final String f10716b = "hasDefaultChannelCreated";

    /* renamed from: c */
    private static final String f10717c = "decryptTag";

    /* renamed from: d */
    private Context f10718d;

    /* renamed from: e */
    private SharedPreferences f10719e;

    /* renamed from: f */
    private Object f10720f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.heytap.mcssdk.utils.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4749a {

        /* renamed from: a */
        static C4747e f10721a = new C4747e();

        private C4749a() {
        }
    }

    private C4747e() {
        this.f10720f = new Object();
        Context context = PushService.getInstance().getContext();
        if (context != null) {
            this.f10718d = m15472a(context);
        }
        Context context2 = this.f10718d;
        if (context2 != null) {
            this.f10719e = context2.getSharedPreferences(f10715a, 0);
        }
    }

    /* renamed from: a */
    private Context m15472a(Context context) {
        boolean m15511a = C4743a.m15511a();
        C4746d.m15494b("fbeVersion is " + m15511a);
        return (!m15511a || Build.VERSION.SDK_INT < 24) ? context.getApplicationContext() : context.createDeviceProtectedStorageContext();
    }

    /* renamed from: c */
    public static C4747e m15468c() {
        return C4749a.f10721a;
    }

    /* renamed from: d */
    private SharedPreferences m15467d() {
        SharedPreferences sharedPreferences = this.f10719e;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        synchronized (this.f10720f) {
            if (this.f10719e != null || this.f10718d == null) {
                return this.f10719e;
            }
            this.f10719e = this.f10718d.getSharedPreferences(f10715a, 0);
            return this.f10719e;
        }
    }

    /* renamed from: a */
    public void m15471a(String str) {
        SharedPreferences m15467d = m15467d();
        if (m15467d != null) {
            m15467d.edit().putString(f10717c, str).commit();
        }
    }

    /* renamed from: a */
    public void m15470a(boolean z) {
        SharedPreferences m15467d = m15467d();
        if (m15467d != null) {
            m15467d.edit().putBoolean(f10716b, z).commit();
        }
    }

    /* renamed from: a */
    public boolean m15473a() {
        SharedPreferences m15467d = m15467d();
        if (m15467d != null) {
            return m15467d.getBoolean(f10716b, false);
        }
        return false;
    }

    /* renamed from: b */
    public String m15469b() {
        SharedPreferences m15467d = m15467d();
        return m15467d != null ? m15467d.getString(f10717c, "DES") : "DES";
    }
}
