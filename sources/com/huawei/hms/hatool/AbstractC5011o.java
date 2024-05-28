package com.huawei.hms.hatool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.o */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC5011o {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.hatool.o$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5012a extends Exception {
        C5012a(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    private static Object m14579a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        String str2;
        String str3;
        if (cls == null) {
            throw new C5012a("class is null in invokeStaticFun");
        }
        if (clsArr == null) {
            if (objArr != null) {
                throw new C5012a("paramsType is null, but params is not null");
            }
        } else if (objArr == null) {
            throw new C5012a("paramsType or params should be same");
        } else {
            if (clsArr.length != objArr.length) {
                throw new C5012a("paramsType len:" + clsArr.length + " should equal params.len:" + objArr.length);
            }
        }
        try {
            try {
                return cls.getMethod(str, clsArr).invoke(null, objArr);
            } catch (IllegalAccessException unused) {
                str2 = "hmsSdk";
                str3 = "invokeStaticFun(): method invoke Exception!";
                C5029v.m14451f(str2, str3);
                return null;
            } catch (IllegalArgumentException unused2) {
                str2 = "hmsSdk";
                str3 = "invokeStaticFun(): Illegal Argument!";
                C5029v.m14451f(str2, str3);
                return null;
            } catch (InvocationTargetException unused3) {
                str2 = "hmsSdk";
                str3 = "invokeStaticFun(): Invocation Target Exception!";
                C5029v.m14451f(str2, str3);
                return null;
            }
        } catch (NoSuchMethodException unused4) {
            C5029v.m14451f("hmsSdk", "invokeStaticFun(): cls.getMethod(),No Such Method !");
        }
    }

    /* renamed from: a */
    private static Object m14576a(String str, String str2, Class[] clsArr, Object[] objArr) {
        String str3;
        String str4;
        try {
            return m14579a(Class.forName(str), str2, clsArr, objArr);
        } catch (C5012a unused) {
            str3 = "hmsSdk";
            str4 = "invokeStaticFun(): Static function call Exception ";
            C5029v.m14451f(str3, str4);
            return null;
        } catch (ClassNotFoundException unused2) {
            str3 = "hmsSdk";
            str4 = "invokeStaticFun() Not found class!";
            C5029v.m14451f(str3, str4);
            return null;
        }
    }

    /* renamed from: a */
    public static String m14581a() {
        return m14578a("ro.build.version.emui", "");
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: a */
    public static String m14580a(Context context) {
        return context == null ? "" : Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    /* renamed from: a */
    public static String m14578a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String m14577a = m14577a("android.os.SystemProperties", str, str2);
        return TextUtils.isEmpty(m14577a) ? m14577a("com.huawei.android.os.SystemPropertiesEx", str, str2) : m14577a;
    }

    /* renamed from: a */
    private static String m14577a(String str, String str2, String str3) {
        Object m14576a = m14576a(str, "get", new Class[]{String.class, String.class}, new Object[]{str2, str3});
        return m14576a != null ? (String) m14576a : str3;
    }

    /* renamed from: b */
    public static String m14575b() {
        String m14577a = m14577a("com.huawei.android.os.SystemPropertiesEx", "ro.huawei.build.display.id", "");
        C5029v.m14455c("hmsSdk", "SystemPropertiesEx: get rom_ver: " + m14577a);
        if (TextUtils.isEmpty(m14577a)) {
            String str = Build.DISPLAY;
            C5029v.m14455c("hmsSdk", "SystemProperties: get rom_ver: " + str);
            return str;
        }
        return m14577a;
    }

    /* renamed from: b */
    public static String m14574b(Context context) {
        Bundle bundle;
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get("CHANNEL")) == null) {
                return "Unknown";
            }
            String obj2 = obj.toString();
            return obj2.length() > 256 ? "Unknown" : obj2;
        } catch (PackageManager.NameNotFoundException unused) {
            C5029v.m14451f("hmsSdk", "getChannel(): The packageName is not correct!");
            return "Unknown";
        }
    }

    /* renamed from: c */
    public static String m14573c(Context context) {
        return context == null ? "" : context.getPackageName();
    }

    /* renamed from: d */
    public static String m14572d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(m14573c(context), 16384).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            C5029v.m14451f("hmsSdk", "getVersion(): The package name is not correct!");
            return "";
        }
    }
}
