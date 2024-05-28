package com.p343ta.utdid2.p344a.p345a;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10311e {
    /* renamed from: a */
    public static String m6445a() {
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] bytes = C10310d.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = C10310d.getBytes((int) System.nanoTime());
        byte[] bytes3 = C10310d.getBytes(nextInt);
        byte[] bytes4 = C10310d.getBytes(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return C10305b.encodeToString(bArr, 2);
    }

    /* renamed from: a */
    public static String m6444a(Context context) {
        String str = null;
        if (!C10309c.m6446a() && context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
        if (C10315g.m6435a(str)) {
            str = m6443b();
        }
        if (C10315g.m6435a(str)) {
            str = m6442b(context);
        }
        return C10315g.m6435a(str) ? m6445a() : str;
    }

    /* renamed from: b */
    private static String m6442b(Context context) {
        String str = "";
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
        }
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase("a5f5faddde9e9f02") || str.equalsIgnoreCase("8e17f7422b35fbea")) ? "" : str.equalsIgnoreCase("0000000000000000") ? "" : str;
    }

    /* renamed from: b */
    private static String m6443b() {
        String str = C10316h.get("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(str)) {
            str = C10316h.get("ro.sys.aliyun.clouduuid", "");
        }
        return TextUtils.isEmpty(str) ? m6441c() : str;
    }

    /* renamed from: c */
    private static String m6441c() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m6440c(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Exception unused) {
            }
        }
        return C10315g.m6435a(str) ? m6445a() : str;
    }
}
