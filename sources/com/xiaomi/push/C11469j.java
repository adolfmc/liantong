package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11469j {

    /* renamed from: a */
    private static volatile int f23343a = 0;

    /* renamed from: a */
    private static Map<String, EnumC11473n> f23344a = null;

    /* renamed from: b */
    private static int f23345b = -1;

    /* renamed from: a */
    public static boolean m2974a() {
        return m2977a() == 1;
    }

    /* renamed from: b */
    public static boolean m2965b() {
        return m2977a() == 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m2977a() {
        /*
            int r0 = com.xiaomi.push.C11469j.f23343a
            if (r0 != 0) goto L47
            r0 = 0
            java.lang.String r1 = "ro.miui.ui.version.code"
            java.lang.String r1 = m2968a(r1)     // Catch: java.lang.Throwable -> L29
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29
            r2 = 1
            if (r1 == 0) goto L21
            java.lang.String r1 = "ro.miui.ui.version.name"
            java.lang.String r1 = m2968a(r1)     // Catch: java.lang.Throwable -> L29
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29
            if (r1 != 0) goto L1f
            goto L21
        L1f:
            r1 = r0
            goto L22
        L21:
            r1 = r2
        L22:
            if (r1 == 0) goto L25
            goto L26
        L25:
            r2 = 2
        L26:
            com.xiaomi.push.C11469j.f23343a = r2     // Catch: java.lang.Throwable -> L29
            goto L31
        L29:
            r1 = move-exception
            java.lang.String r2 = "get isMIUI failed"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5279a(r2, r1)
            com.xiaomi.push.C11469j.f23343a = r0
        L31:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "isMIUI's value is: "
            r0.append(r1)
            int r1 = com.xiaomi.push.C11469j.f23343a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5274b(r0)
        L47:
            int r0 = com.xiaomi.push.C11469j.f23343a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11469j.m2977a():int");
    }

    /* renamed from: a */
    public static String m2976a() {
        int m2935a = C11479r.m2935a();
        return (!m2974a() || m2935a <= 0) ? "" : m2935a < 2 ? "alpha" : m2935a < 3 ? "development" : "stable";
    }

    /* renamed from: a */
    public static String m2968a(String str) {
        try {
            try {
                return (String) C11176aw.m4810a("android.os.SystemProperties", "get", str, "");
            } catch (Exception e) {
                AbstractC11049b.m5268d("fail to get property. " + e);
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m2960c() {
        if (f23345b < 0) {
            f23345b = !m2956e() ? 1 : 0;
        }
        return f23345b > 0;
    }

    /* renamed from: a */
    public static boolean m2972a(Context context) {
        return context != null && m2967a(context.getPackageName());
    }

    /* renamed from: a */
    public static boolean m2967a(String str) {
        return "com.xiaomi.xmsf".equals(str);
    }

    /* renamed from: b */
    public static String m2966b() {
        String m2936a = C11478q.m2936a("ro.miui.region", "");
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("persist.sys.oppo.region", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("ro.oppo.regionmark", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("ro.vendor.oplus.regionmark", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("ro.hw.country", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("ro.csc.countryiso_code", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = m2962b(C11478q.m2936a("ro.product.country.region", ""));
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("gsm.vivo.countrycode", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("persist.sys.oem.region", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("ro.product.locale.region", "");
        }
        if (TextUtils.isEmpty(m2936a)) {
            m2936a = C11478q.m2936a("persist.sys.country", "");
        }
        if (!TextUtils.isEmpty(m2936a)) {
            AbstractC11049b.m5282a("get region from system, region = " + m2936a);
        }
        if (TextUtils.isEmpty(m2936a)) {
            String country = Locale.getDefault().getCountry();
            AbstractC11049b.m5282a("locale.default.country = " + country);
            return country;
        }
        return m2936a;
    }

    /* renamed from: a */
    public static EnumC11473n m2969a(String str) {
        EnumC11473n m2963b = m2963b(str);
        return m2963b == null ? EnumC11473n.Global : m2963b;
    }

    /* renamed from: b */
    private static EnumC11473n m2963b(String str) {
        m2975a();
        return f23344a.get(str.toUpperCase());
    }

    /* renamed from: a */
    private static void m2975a() {
        if (f23344a != null) {
            return;
        }
        f23344a = new HashMap();
        f23344a.put("CN", EnumC11473n.China);
        f23344a.put("FI", EnumC11473n.Europe);
        f23344a.put("SE", EnumC11473n.Europe);
        f23344a.put("NO", EnumC11473n.Europe);
        f23344a.put("FO", EnumC11473n.Europe);
        f23344a.put("EE", EnumC11473n.Europe);
        f23344a.put("LV", EnumC11473n.Europe);
        f23344a.put("LT", EnumC11473n.Europe);
        f23344a.put("BY", EnumC11473n.Europe);
        f23344a.put("MD", EnumC11473n.Europe);
        f23344a.put("UA", EnumC11473n.Europe);
        f23344a.put("PL", EnumC11473n.Europe);
        f23344a.put("CZ", EnumC11473n.Europe);
        f23344a.put("SK", EnumC11473n.Europe);
        f23344a.put("HU", EnumC11473n.Europe);
        f23344a.put("DE", EnumC11473n.Europe);
        f23344a.put("AT", EnumC11473n.Europe);
        f23344a.put("CH", EnumC11473n.Europe);
        f23344a.put("LI", EnumC11473n.Europe);
        f23344a.put("GB", EnumC11473n.Europe);
        f23344a.put("IE", EnumC11473n.Europe);
        f23344a.put("NL", EnumC11473n.Europe);
        f23344a.put("BE", EnumC11473n.Europe);
        f23344a.put("LU", EnumC11473n.Europe);
        f23344a.put("FR", EnumC11473n.Europe);
        f23344a.put("RO", EnumC11473n.Europe);
        f23344a.put("BG", EnumC11473n.Europe);
        f23344a.put("RS", EnumC11473n.Europe);
        f23344a.put("MK", EnumC11473n.Europe);
        f23344a.put("AL", EnumC11473n.Europe);
        f23344a.put("GR", EnumC11473n.Europe);
        f23344a.put("SI", EnumC11473n.Europe);
        f23344a.put("HR", EnumC11473n.Europe);
        f23344a.put("IT", EnumC11473n.Europe);
        f23344a.put("SM", EnumC11473n.Europe);
        f23344a.put("MT", EnumC11473n.Europe);
        f23344a.put("ES", EnumC11473n.Europe);
        f23344a.put("PT", EnumC11473n.Europe);
        f23344a.put("AD", EnumC11473n.Europe);
        f23344a.put("CY", EnumC11473n.Europe);
        f23344a.put("DK", EnumC11473n.Europe);
        f23344a.put("IS", EnumC11473n.Europe);
        f23344a.put("UK", EnumC11473n.Europe);
        f23344a.put("EL", EnumC11473n.Europe);
        f23344a.put("RU", EnumC11473n.Russia);
        f23344a.put("IN", EnumC11473n.India);
    }

    /* renamed from: d */
    public static boolean m2958d() {
        return !EnumC11473n.China.name().equalsIgnoreCase(m2969a(m2966b()).name());
    }

    /* renamed from: a */
    public static int m2973a(Context context) {
        String m2968a = m2968a("ro.miui.ui.version.code");
        if (TextUtils.isEmpty(m2968a) || !TextUtils.isDigitsOnly(m2968a)) {
            return 0;
        }
        return Integer.parseInt(m2968a);
    }

    /* renamed from: c */
    public static String m2961c() {
        return m2968a("ro.miui.ui.version.name");
    }

    /* renamed from: b */
    public static int m2964b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: b */
    private static String m2962b(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("-");
            if (split.length > 0) {
                return split[0];
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m2971a(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.toString() + " " + m2970a(intent.getExtras());
    }

    /* renamed from: a */
    public static String m2970a(Bundle bundle) {
        StringBuilder sb = new StringBuilder("Bundle[");
        if (bundle == null) {
            sb.append("null");
        } else {
            boolean z = true;
            for (String str : bundle.keySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(str);
                sb.append('=');
                Object obj = bundle.get(str);
                if (obj instanceof int[]) {
                    sb.append(Arrays.toString((int[]) obj));
                } else if (obj instanceof byte[]) {
                    sb.append(Arrays.toString((byte[]) obj));
                } else if (obj instanceof boolean[]) {
                    sb.append(Arrays.toString((boolean[]) obj));
                } else if (obj instanceof short[]) {
                    sb.append(Arrays.toString((short[]) obj));
                } else if (obj instanceof long[]) {
                    sb.append(Arrays.toString((long[]) obj));
                } else if (obj instanceof float[]) {
                    sb.append(Arrays.toString((float[]) obj));
                } else if (obj instanceof double[]) {
                    sb.append(Arrays.toString((double[]) obj));
                } else if (obj instanceof String[]) {
                    sb.append(Arrays.toString((String[]) obj));
                } else if (obj instanceof CharSequence[]) {
                    sb.append(Arrays.toString((CharSequence[]) obj));
                } else if (obj instanceof Parcelable[]) {
                    sb.append(Arrays.toString((Parcelable[]) obj));
                } else if (obj instanceof Bundle) {
                    sb.append(m2970a((Bundle) obj));
                } else {
                    sb.append(obj);
                }
                z = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: e */
    public static boolean m2956e() {
        String str;
        try {
            str = C11478q.m2936a("ro.miui.ui.version.code", "");
        } catch (Exception unused) {
            str = "";
        }
        return !TextUtils.isEmpty(str);
    }

    /* renamed from: d */
    public static String m2959d() {
        return m2968a("ro.build.characteristics");
    }

    /* renamed from: e */
    public static String m2957e() {
        return m2968a("ro.product.manufacturer");
    }
}
