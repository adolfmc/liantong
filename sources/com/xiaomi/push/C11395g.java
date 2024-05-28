package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11395g {

    /* renamed from: a */
    private static InterfaceC11396a f22413a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.g$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11396a {
        /* renamed from: a */
        Map<String, String> m3708a(Context context, String str);

        /* renamed from: a */
        boolean m3707a(Context context, String str);

        /* renamed from: b */
        boolean m3706b(Context context, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.g$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum EnumC11397b {
        UNKNOWN(0),
        ALLOWED(1),
        NOT_ALLOWED(2);
        

        /* renamed from: a */
        private final int f22418a;

        EnumC11397b(int i) {
            this.f22418a = i;
        }

        /* renamed from: a */
        public int m3705a() {
            return this.f22418a;
        }
    }

    /* renamed from: a */
    public static String m3717a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception unused) {
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : "1.0";
    }

    /* renamed from: a */
    public static int m3720a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m3724a(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.android.systemui", 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    return 0;
                }
                return applicationInfo.metaData.getInt("SupportForPushVersionCode");
            } catch (PackageManager.NameNotFoundException unused) {
                return 0;
            }
        }
        return 0;
    }

    @TargetApi(19)
    /* renamed from: a */
    public static EnumC11397b m3714a(Context context, String str, boolean z) {
        ApplicationInfo applicationInfo;
        EnumC11397b m3722a;
        if (context == null || TextUtils.isEmpty(str)) {
            return EnumC11397b.UNKNOWN;
        }
        try {
            if (str.equals(context.getPackageName())) {
                applicationInfo = context.getApplicationInfo();
            } else {
                applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            }
            m3722a = m3722a(context, applicationInfo);
        } catch (Throwable th) {
            AbstractC11049b.m5282a("get app op error " + th);
        }
        if (m3722a != EnumC11397b.UNKNOWN) {
            return m3722a;
        }
        Integer num = (Integer) C11176aw.m4817a((Class<? extends Object>) AppOpsManager.class, "OP_POST_NOTIFICATION");
        if (num == null) {
            return EnumC11397b.UNKNOWN;
        }
        Integer num2 = (Integer) C11176aw.m4812a((Object) ((AppOpsManager) context.getSystemService("appops")), "checkOpNoThrow", num, Integer.valueOf(applicationInfo.uid), str);
        int i = (Integer) C11176aw.m4817a((Class<? extends Object>) AppOpsManager.class, "MODE_ALLOWED");
        int i2 = (Integer) C11176aw.m4817a((Class<? extends Object>) AppOpsManager.class, "MODE_IGNORED");
        AbstractC11049b.m5274b(String.format("get app mode %s|%s|%s", num2, i, i2));
        if (i == null) {
            i = 0;
        }
        if (i2 == null) {
            i2 = 1;
        }
        if (num2 != null) {
            return z ? !num2.equals(i2) ? EnumC11397b.ALLOWED : EnumC11397b.NOT_ALLOWED : num2.equals(i) ? EnumC11397b.ALLOWED : EnumC11397b.NOT_ALLOWED;
        }
        return EnumC11397b.UNKNOWN;
    }

    /* renamed from: a */
    private static EnumC11397b m3722a(Context context, ApplicationInfo applicationInfo) {
        Object systemService;
        int i = Build.VERSION.SDK_INT;
        if (applicationInfo == null || i < 24) {
            return EnumC11397b.UNKNOWN;
        }
        Boolean bool = null;
        try {
            if (applicationInfo.packageName.equals(context.getPackageName())) {
                bool = Boolean.valueOf(((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled());
            } else {
                if (i >= 29) {
                    systemService = C11176aw.m4812a(context.getSystemService("notification"), "getService", new Object[0]);
                } else {
                    systemService = context.getSystemService("security");
                }
                if (systemService != null) {
                    bool = (Boolean) C11176aw.m4804b(systemService, "areNotificationsEnabledForPackage", applicationInfo.packageName, Integer.valueOf(applicationInfo.uid));
                }
            }
            if (bool != null) {
                return bool.booleanValue() ? EnumC11397b.ALLOWED : EnumC11397b.NOT_ALLOWED;
            }
        } catch (Exception e) {
            AbstractC11049b.m5282a("are notifications enabled error " + e);
        }
        return EnumC11397b.UNKNOWN;
    }

    /* renamed from: a */
    public static void m3721a(Context context, ApplicationInfo applicationInfo, boolean z) {
        Object systemService;
        int i = Build.VERSION.SDK_INT;
        if (EnumC11397b.ALLOWED != m3722a(context, applicationInfo)) {
            try {
                if (i >= 29) {
                    systemService = C11176aw.m4812a(context.getSystemService("notification"), "getService", new Object[0]);
                } else {
                    systemService = context.getSystemService("security");
                }
                if (systemService != null) {
                    C11176aw.m4804b(systemService, "setNotificationsEnabledForPackage", applicationInfo.packageName, Integer.valueOf(applicationInfo.uid), Boolean.valueOf(z));
                }
            } catch (Exception e) {
                AbstractC11049b.m5282a("set notifications enabled error " + e);
            }
        }
    }

    /* renamed from: a */
    public static boolean m3715a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!C11469j.m2974a()) {
            return context.getPackageName().equals(str);
        }
        InterfaceC11396a interfaceC11396a = f22413a;
        return interfaceC11396a != null && interfaceC11396a.m3707a(context, str);
    }

    /* renamed from: b */
    public static boolean m3711b(Context context, String str) {
        InterfaceC11396a interfaceC11396a = f22413a;
        return interfaceC11396a != null && interfaceC11396a.m3706b(context, str);
    }

    /* renamed from: c */
    public static boolean m3710c(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* renamed from: a */
    public static boolean m3723a(Context context) {
        String m3725a = m3725a();
        if (TextUtils.isEmpty(m3725a) || context == null) {
            return false;
        }
        return m3725a.equals(context.getPackageName());
    }

    /* renamed from: a */
    public static String m3725a() {
        String str;
        if (Build.VERSION.SDK_INT >= 28) {
            str = Application.getProcessName();
        } else {
            str = (String) C11176aw.m4810a("android.app.ActivityThread", "currentProcessName", new Object[0]);
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    /* renamed from: b */
    public static String m3712b(Context context, String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) ? str : packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return str;
        }
    }

    /* renamed from: a */
    private static ApplicationInfo m3719a(Context context, String str) {
        if (str.equals(context.getPackageName())) {
            return context.getApplicationInfo();
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            AbstractC11049b.m5282a("not found app info " + str);
            return null;
        }
    }

    /* renamed from: b */
    public static int m3713b(Context context, String str) {
        ApplicationInfo m3719a = m3719a(context, str);
        if (m3719a != null) {
            int i = m3719a.icon;
            return i == 0 ? m3719a.logo : i;
        }
        return 0;
    }

    /* renamed from: a */
    public static Drawable m3718a(Context context, String str) {
        ApplicationInfo m3719a = m3719a(context, str);
        Drawable drawable = null;
        if (m3719a != null) {
            try {
                drawable = m3719a.loadIcon(context.getPackageManager());
                if (drawable == null) {
                    drawable = m3719a.loadLogo(context.getPackageManager());
                }
            } catch (Exception e) {
                AbstractC11049b.m5282a("get app icon drawable failed, " + e);
            }
        }
        return drawable != null ? drawable : new ColorDrawable(0);
    }

    /* renamed from: d */
    public static boolean m3709d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "freeform_window_state", -1) >= 0) {
                return str.equals(Settings.Secure.getString(context.getContentResolver(), "freeform_package_name"));
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static Map<String, String> m3716a(Context context, String str) {
        InterfaceC11396a interfaceC11396a = f22413a;
        if (interfaceC11396a == null) {
            return null;
        }
        return interfaceC11396a.m3708a(context, str);
    }
}
