package com.example.asus.detectionandalign.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4290b {
    /* renamed from: a */
    public static int m15957a(String str, Activity activity) {
        if (m15960a()) {
            try {
                Class<?> loadClass = activity.getClassLoader().loadClass("android.os.SystemProperties");
                return ((Integer) loadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(loadClass, new String(str), new Integer(0))).intValue();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return 0;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return 0;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                return 0;
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
                return 0;
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m15960a() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    /* renamed from: a */
    public static boolean m15959a(Activity activity) {
        return m15957a("ro.miui.notch", activity) == 1 || m15958a((Context) activity) || m15955c(activity) || m15956b(activity);
    }

    /* renamed from: a */
    public static boolean m15958a(Context context) {
        try {
            try {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                    return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
                } catch (NoSuchMethodException unused) {
                    Log.e("BangAdaptive", "hasNotchAtHuawei NoSuchMethodException");
                    return false;
                } catch (Exception unused2) {
                    Log.e("BangAdaptive", "hasNotchAtHuawei Exception");
                    return false;
                }
            } catch (ClassNotFoundException unused3) {
                Log.e("BangAdaptive", "hasNotchAtHuawei ClassNotFoundException");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m15956b(Context context) {
        try {
            try {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("android.util.FtFeature");
                    return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
                } catch (NoSuchMethodException unused) {
                    Log.e("BangAdaptive", "hasNotchAtVivo NoSuchMethodException");
                    return false;
                } catch (Exception unused2) {
                    Log.e("BangAdaptive", "hasNotchAtVivo Exception");
                    return false;
                }
            } catch (ClassNotFoundException unused3) {
                Log.e("BangAdaptive", "hasNotchAtVivo ClassNotFoundException");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m15955c(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
}
