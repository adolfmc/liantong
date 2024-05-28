package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11455i {

    /* renamed from: a */
    private static String f23315a = null;

    /* renamed from: a */
    private static boolean f23317a = false;

    /* renamed from: b */
    private static String f23319b = null;

    /* renamed from: c */
    private static String f23320c = "";

    /* renamed from: d */
    private static String f23321d;

    /* renamed from: e */
    private static String f23322e;

    /* renamed from: f */
    private static final String f23323f = String.valueOf((char) 2);

    /* renamed from: a */
    private static final String[] f23318a = {"--", "a-", "u-", "v-", "o-", "g-", "d-"};

    /* renamed from: a */
    private static final Set<String> f23316a = new HashSet();

    /* renamed from: a */
    private static double m3053a(double d) {
        int i = 1;
        while (true) {
            double d2 = i;
            if (d2 >= d) {
                return d2;
            }
            i <<= 1;
        }
    }

    @Deprecated
    /* renamed from: a */
    public static String m3050a(Context context) {
        return null;
    }

    @Deprecated
    /* renamed from: c */
    public static String m3037c(Context context) {
        return null;
    }

    @Deprecated
    /* renamed from: d */
    public static String m3034d(Context context) {
        return null;
    }

    @Deprecated
    /* renamed from: e */
    public static String m3033e(Context context) {
        return null;
    }

    @Deprecated
    /* renamed from: f */
    public static String m3032f(Context context) {
        return "";
    }

    @Deprecated
    /* renamed from: j */
    private static String m3028j(Context context) {
        return "";
    }

    static {
        f23316a.add("com.xiaomi.xmsf");
        f23316a.add("com.xiaomi.finddevice");
        f23316a.add("com.miui.securitycenter");
        f23317a = true;
    }

    /* renamed from: a */
    private static String m3051a(int i) {
        if (i > 0) {
            String[] strArr = f23318a;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return f23318a[0];
    }

    /* renamed from: a */
    public static boolean m3044a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = f23318a;
            if (i >= strArr.length) {
                return false;
            }
            if (str.startsWith(strArr[i])) {
                return true;
            }
            i++;
        }
    }

    /* renamed from: a */
    public static synchronized String m3046a(Context context, boolean z) {
        String str;
        synchronized (C11455i.class) {
            if (f23321d == null) {
                String m3041b = m3041b(context);
                int i = 1;
                switch (1) {
                    case 1:
                        String str2 = "";
                        if (!C11469j.m2958d()) {
                            str2 = z ? m3037c(context) : m3028j(context);
                        }
                        String m3050a = m3050a(context);
                        if (!(Build.VERSION.SDK_INT < 26)) {
                            if (m3039b(str2)) {
                                if (!m3039b(m3050a)) {
                                }
                            }
                        }
                        m3041b = str2 + m3041b + m3050a;
                        break;
                    case 2:
                        String m4879b = C11157an.m4882a(context).m4879b();
                        if (!TextUtils.isEmpty(m4879b)) {
                            m3041b = m4879b + m3041b;
                            i = 2;
                            break;
                        }
                    case 3:
                    case 4:
                        String mo4863a = C11157an.m4882a(context).mo4863a();
                        if (!TextUtils.isEmpty(mo4863a) && !mo4863a.startsWith("00000000-0000-0000-0000-000000000000")) {
                            i = 4;
                            m3041b = mo4863a;
                            break;
                        }
                        break;
                    case 5:
                        if (!TextUtils.isEmpty(m3041b)) {
                            i = 5;
                            break;
                        }
                    case 6:
                        m3041b = m3027k(context);
                        i = 6;
                        break;
                    default:
                        m3041b = "";
                        break;
                }
                AbstractC11049b.m5274b("devid rule select:" + i);
                if (i == 3) {
                    f23321d = m3041b;
                } else {
                    f23321d = m3051a(i) + C11184bb.m4748b(m3041b);
                }
            }
            str = f23321d;
        }
        return str;
    }

    /* renamed from: b */
    private static boolean m3039b(String str) {
        if (str == null) {
            return true;
        }
        String trim = str.trim();
        return trim.length() == 0 || trim.equalsIgnoreCase("null") || trim.equalsIgnoreCase("unknown");
    }

    /* renamed from: b */
    public static String m3041b(Context context) {
        if (f23319b != null || !f23317a) {
            return f23319b;
        }
        f23317a = m3036c(context);
        if (f23317a) {
            try {
                f23319b = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable th) {
                AbstractC11049b.m5282a("failure to get androidId: " + th);
            }
            return f23319b;
        }
        return null;
    }

    /* renamed from: g */
    public static synchronized String m3031g(Context context) {
        synchronized (C11455i.class) {
            if (f23322e != null) {
                return f23322e;
            }
            String m3041b = m3041b(context);
            String m3050a = m3050a(context);
            f23322e = C11184bb.m4748b(m3041b + m3050a);
            return f23322e;
        }
    }

    /* renamed from: h */
    public static synchronized String m3030h(Context context) {
        String m4748b;
        synchronized (C11455i.class) {
            String m3041b = m3041b(context);
            m4748b = C11184bb.m4748b(m3041b + ((String) null));
        }
        return m4748b;
    }

    /* renamed from: i */
    public static String m3029i(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    @TargetApi(17)
    /* renamed from: a */
    public static int m3056a() {
        Object m4810a = C11176aw.m4810a("android.os.UserHandle", "myUserId", new Object[0]);
        if (m4810a == null) {
            return -1;
        }
        return ((Integer) Integer.class.cast(m4810a)).intValue();
    }

    /* renamed from: a */
    public static String m3055a() {
        return m3052a(m3043b()) + "GB";
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0052, code lost:
        if (r0 == null) goto L36;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m3043b() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r1 = r1.exists()
            r2 = 0
            if (r1 == 0) goto L55
            r1 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L51
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L51
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L51
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L51
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            if (r3 != 0) goto L41
            java.lang.String r3 = "\\s+"
            java.lang.String[] r1 = r1.split(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            if (r1 == 0) goto L41
            int r3 = r1.length     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            r4 = 2
            if (r3 < r4) goto L41
            r3 = 1
            r4 = r1[r3]     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            boolean r4 = android.text.TextUtils.isDigitsOnly(r4)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            if (r4 == 0) goto L41
            r1 = r1[r3]     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
            r2 = r1
        L41:
            r0.close()     // Catch: java.io.IOException -> L55
            goto L55
        L45:
            r1 = move-exception
            goto L4b
        L47:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L4b:
            if (r0 == 0) goto L50
            r0.close()     // Catch: java.io.IOException -> L50
        L50:
            throw r1
        L51:
            r0 = r1
        L52:
            if (r0 == 0) goto L55
            goto L41
        L55:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11455i.m3043b():int");
    }

    /* renamed from: a */
    private static float m3052a(int i) {
        float f = ((((((i + 102400) / 524288) + 1) * 512) * 1024) / 1024.0f) / 1024.0f;
        double d = f;
        return d > 0.5d ? (float) Math.ceil(d) : f;
    }

    /* renamed from: b */
    public static String m3042b() {
        double m3053a = m3053a(((m3045a(Environment.getDataDirectory()) / 1024.0d) / 1024.0d) / 1024.0d);
        return m3053a + "GB";
    }

    /* renamed from: a */
    private static long m3045a(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    /* renamed from: c */
    public static String m3038c() {
        return m3043b() + "KB";
    }

    /* renamed from: d */
    public static String m3035d() {
        long m3045a = m3045a(Environment.getDataDirectory());
        return (m3045a / 1024) + "KB";
    }

    /* renamed from: a */
    public static boolean m3049a(Context context) {
        Intent m2947a = C11472m.m2947a(context, (BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"), (String) null, (Handler) null);
        if (m2947a != null) {
            int intExtra = m2947a.getIntExtra("status", -1);
            return intExtra == 2 || intExtra == 5;
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m3040b(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null || powerManager.isScreenOn();
    }

    /* renamed from: a */
    public static boolean m3054a() {
        return m3056a() <= 0;
    }

    /* renamed from: a */
    public static boolean m3047a(Context context, String str) {
        PackageInfo packageInfo = (PackageInfo) C11176aw.m4812a((Object) context.getPackageManager(), "getPackageInfoAsUser", str, 0, 999);
        return packageInfo == null || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 8388608) != 8388608;
    }

    /* renamed from: c */
    private static boolean m3036c(Context context) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            return true;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            if (packageInfo != null && packageInfo.applicationInfo != null && packageInfo.applicationInfo.metaData != null && packageInfo.applicationInfo.metaData.containsKey("supportGetAndroidID")) {
                boolean z = packageInfo.applicationInfo.metaData.getBoolean("supportGetAndroidID", true);
                AbstractC11049b.m5272b("DeviceInfo", "Get supportGetAndroidID from app metaData: " + z);
                return z;
            }
        } catch (Exception e) {
            AbstractC11049b.m5269c("DeviceInfo", "Check supportGetAndroidID from app metaData error: " + e.getMessage());
        }
        try {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(context.getPackageName(), "com.xiaomi.push.service.XMPushService");
            intent.setComponent(componentName);
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(componentName, 128);
            if (serviceInfo != null && serviceInfo.metaData != null && serviceInfo.metaData.containsKey("supportGetAndroidID")) {
                AbstractC11049b.m5272b("DeviceInfo", "The metaData of XMPushService contains key supportGetAndroidID,so return false.");
                return false;
            }
        } catch (Exception e2) {
            AbstractC11049b.m5269c("DeviceInfo", "Check supportGetAndroidID from XMPushService metaData error: " + e2.getMessage());
        }
        AbstractC11049b.m5272b("DeviceInfo", "Not configure the metaData key of supportGetAndroidID，return true by default.");
        return true;
    }

    /* renamed from: k */
    private static String m3027k(Context context) {
        String string = context.getSharedPreferences("device_info", 0).getString("default_id", null);
        if (TextUtils.isEmpty(string)) {
            String m3026l = m3026l(context);
            m3048a(context, m3026l);
            return m3026l;
        }
        return string;
    }

    /* renamed from: a */
    private static void m3048a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("device_info", 0);
        if (TextUtils.isEmpty(sharedPreferences.getString("default_id", null))) {
            sharedPreferences.edit().putString("default_id", str).apply();
        } else {
            AbstractC11049b.m5282a("default_id exist,do not change it.");
        }
    }

    /* renamed from: l */
    private static String m3026l(Context context) {
        String str = Build.BRAND;
        String m2955a = C11470k.m2955a();
        int i = Build.VERSION.SDK_INT;
        String str2 = Build.VERSION.RELEASE;
        String str3 = Build.VERSION.INCREMENTAL;
        int m3056a = m3056a();
        String packageName = context.getPackageName();
        long currentTimeMillis = System.currentTimeMillis();
        String m4758a = C11184bb.m4758a(16);
        return C11180ay.m4797a(str + "_" + m2955a + "_" + i + "_" + str2 + "_" + str3 + "_" + m3056a + "_" + packageName + "_" + currentTimeMillis + "_" + m4758a);
    }
}
