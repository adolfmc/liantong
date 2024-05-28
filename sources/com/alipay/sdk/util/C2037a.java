package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2037a {

    /* renamed from: a */
    private static final String f3849a = "00:00:00:00:00:00";

    /* renamed from: e */
    private static C2037a f3850e;

    /* renamed from: b */
    private String f3851b;

    /* renamed from: c */
    private String f3852c;

    /* renamed from: d */
    private String f3853d;

    /* renamed from: a */
    public static C2037a m20728a(Context context) {
        if (f3850e == null) {
            f3850e = new C2037a(context);
        }
        return f3850e;
    }

    private C2037a(Context context) {
        try {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
                m20724b(telephonyManager.getDeviceId());
                m20727a(telephonyManager.getSubscriberId());
                this.f3853d = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (!TextUtils.isEmpty(this.f3853d)) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!TextUtils.isEmpty(this.f3853d)) {
                    return;
                }
            }
            this.f3853d = "00:00:00:00:00:00";
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.f3853d)) {
                this.f3853d = "00:00:00:00:00:00";
            }
            throw th;
        }
    }

    /* renamed from: a */
    public String m20729a() {
        if (TextUtils.isEmpty(this.f3851b)) {
            this.f3851b = "000000000000000";
        }
        return this.f3851b;
    }

    /* renamed from: b */
    public String m20726b() {
        if (TextUtils.isEmpty(this.f3852c)) {
            this.f3852c = "000000000000000";
        }
        return this.f3852c;
    }

    /* renamed from: a */
    public void m20727a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.f3851b = str;
    }

    /* renamed from: b */
    public void m20724b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 48 || bytes[i] > 57) {
                    bytes[i] = 48;
                }
            }
            str = (new String(bytes) + "000000000000000").substring(0, 15);
        }
        this.f3852c = str;
    }

    /* renamed from: c */
    public String m20723c() {
        String str = m20726b() + "|";
        String m20729a = m20729a();
        if (TextUtils.isEmpty(m20729a)) {
            return str + "000000000000000";
        }
        return str + m20729a;
    }

    /* renamed from: d */
    public String m20721d() {
        return this.f3853d;
    }

    /* renamed from: b */
    public static EnumC2041d m20725b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return EnumC2041d.m20707a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return EnumC2041d.WIFI;
            }
            return EnumC2041d.NONE;
        } catch (Exception unused) {
            return EnumC2041d.NONE;
        }
    }

    /* renamed from: c */
    public static String m20722c(Context context) {
        return m20728a(context).m20723c().substring(0, 8);
    }

    /* renamed from: d */
    public static String m20720d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
