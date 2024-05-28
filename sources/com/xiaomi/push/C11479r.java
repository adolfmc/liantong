package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11479r {

    /* renamed from: a */
    private static Context f23371a;

    /* renamed from: a */
    private static String f23372a;

    /* renamed from: a */
    public static void m2931a(Context context) {
        f23371a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static Context m2934a() {
        return f23371a;
    }

    /* renamed from: a */
    public static int m2935a() {
        try {
            Class<?> m2929a = m2929a(null, "miui.os.Build");
            if (m2929a.getField("IS_STABLE_VERSION").getBoolean(null)) {
                return 3;
            }
            return m2929a.getField("IS_DEVELOPMENT_VERSION").getBoolean(null) ? 2 : 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: a */
    public static boolean m2930a(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2932a() {
        return TextUtils.equals((String) C11176aw.m4810a("android.os.SystemProperties", "get", "sys.boot_completed"), "1");
    }

    /* renamed from: b */
    public static boolean m2927b() {
        try {
            return m2929a(null, "miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(false);
        } catch (ClassNotFoundException unused) {
            AbstractC11049b.m5268d("miui.os.Build ClassNotFound");
            return false;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }

    /* renamed from: a */
    public static synchronized String m2933a() {
        String str;
        synchronized (C11479r.class) {
            if (f23372a != null) {
                return f23372a;
            }
            String str2 = Build.VERSION.INCREMENTAL;
            if (m2935a() <= 0) {
                str = m2928b();
                if (TextUtils.isEmpty(str)) {
                    str = m2926c();
                    if (TextUtils.isEmpty(str)) {
                        str = m2925d();
                        if (TextUtils.isEmpty(str)) {
                            str = String.valueOf(C11478q.m2936a("ro.product.brand", "Android") + "_" + str2);
                        }
                    }
                }
            } else {
                str = str2;
            }
            f23372a = str;
            return str;
        }
    }

    /* renamed from: b */
    private static String m2928b() {
        f23372a = C11478q.m2936a("ro.build.version.emui", "");
        return f23372a;
    }

    /* renamed from: c */
    private static String m2926c() {
        String m2936a = C11478q.m2936a("ro.build.version.opporom", "");
        if (!TextUtils.isEmpty(m2936a) && !m2936a.startsWith("ColorOS_")) {
            f23372a = "ColorOS_" + m2936a;
        }
        return f23372a;
    }

    /* renamed from: d */
    private static String m2925d() {
        String m2936a = C11478q.m2936a("ro.vivo.os.version", "");
        if (!TextUtils.isEmpty(m2936a) && !m2936a.startsWith("FuntouchOS_")) {
            f23372a = "FuntouchOS_" + m2936a;
        }
        return f23372a;
    }

    /* renamed from: a */
    public static Class<?> m2929a(Context context, String str) {
        if (str == null || str.trim().length() == 0) {
            throw new ClassNotFoundException("class is empty");
        }
        boolean z = context != null;
        if (z && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getClassLoader().loadClass(str);
            } catch (Throwable unused) {
            }
        }
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            AbstractC11049b.m5282a(String.format("loadClass fail hasContext= %s, errMsg = %s", Boolean.valueOf(z), th.getLocalizedMessage()));
            throw new ClassNotFoundException("loadClass fail ", th);
        }
    }
}
