package com.bytedance.applog;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.r2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3680r2 {

    /* renamed from: a */
    public static final CharSequence f8789a = "amigo";

    /* renamed from: b */
    public static final CharSequence f8790b = "funtouch";

    /* renamed from: a */
    public static String m17135a() {
        String str;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        String str2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.build.version.emui").getInputStream()), 1024);
        } catch (Throwable th) {
            th = th;
            str = null;
        }
        try {
            str2 = bufferedReader.readLine();
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException unused) {
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            str = str2;
            bufferedReader2 = bufferedReader;
            try {
                C3704u2.m17108a("U SHALL NOT PASS!", th);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused2) {
                    }
                }
                return str;
            } catch (Throwable th3) {
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th3;
            }
        }
    }

    /* renamed from: a */
    public static String m17134a(String str) {
        BufferedReader bufferedReader;
        String str2 = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec("getprop " + str);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            try {
                str2 = bufferedReader.readLine();
                exec.destroy();
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                return str2;
            } catch (Throwable unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return str2;
            }
        } catch (Throwable unused4) {
            bufferedReader = null;
        }
    }

    /* renamed from: b */
    public static String m17133b() {
        String str = Build.MANUFACTURER;
        return str == null ? "" : str.trim();
    }

    /* renamed from: b */
    public static boolean m17132b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = m17135a();
        }
        if (TextUtils.isEmpty(str) || !str.toLowerCase().startsWith("emotionui")) {
            if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith("huawei")) {
                return !TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("huawei");
            }
            return true;
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m17131c() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains("oppo");
    }

    /* renamed from: d */
    public static boolean m17130d() {
        return (!TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.contains("Flyme")) || "flyme".equals(Build.USER);
    }

    /* renamed from: e */
    public static boolean m17129e() {
        try {
            return Class.forName("miui.os.Build").getName().length() > 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
