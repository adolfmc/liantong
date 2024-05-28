package com.alipay.security.mobile.module.p112b;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2089d {

    /* renamed from: a */
    private static C2089d f3991a = new C2089d();

    private C2089d() {
    }

    /* renamed from: a */
    public static C2089d m20510a() {
        return f3991a;
    }

    /* renamed from: a */
    private static String m20508a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    /* renamed from: a */
    public static boolean m20509a(Context context) {
        boolean z;
        int length;
        try {
            if (!Build.HARDWARE.contains("goldfish") && !Build.PRODUCT.contains("sdk") && !Build.FINGERPRINT.contains("generic")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    String deviceId = telephonyManager.getDeviceId();
                    if (deviceId != null && (length = deviceId.length()) != 0) {
                        for (int i = 0; i < length; i++) {
                            if (!Character.isWhitespace(deviceId.charAt(i)) && deviceId.charAt(i) != '0') {
                                z = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    if (z) {
                        return true;
                    }
                }
                return C2081a.m20577a(Settings.Secure.getString(context.getContentResolver(), "android_id"));
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m20507b() {
        return "android";
    }

    /* renamed from: c */
    public static boolean m20506c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m20505d() {
        return Build.BOARD;
    }

    /* renamed from: e */
    public static String m20504e() {
        return Build.BRAND;
    }

    /* renamed from: f */
    public static String m20503f() {
        return Build.DEVICE;
    }

    /* renamed from: g */
    public static String m20502g() {
        return Build.DISPLAY;
    }

    /* renamed from: h */
    public static String m20501h() {
        return Build.VERSION.INCREMENTAL;
    }

    /* renamed from: i */
    public static String m20500i() {
        return Build.MANUFACTURER;
    }

    /* renamed from: j */
    public static String m20499j() {
        return Build.MODEL;
    }

    /* renamed from: k */
    public static String m20498k() {
        return Build.PRODUCT;
    }

    /* renamed from: l */
    public static String m20497l() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: m */
    public static String m20496m() {
        return Build.VERSION.SDK;
    }

    /* renamed from: n */
    public static String m20495n() {
        return Build.TAGS;
    }

    /* renamed from: o */
    public static String m20494o() {
        return m20508a("ro.kernel.qemu", "0");
    }
}
