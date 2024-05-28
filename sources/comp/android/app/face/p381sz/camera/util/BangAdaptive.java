package comp.android.app.face.p381sz.camera.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.BangAdaptive */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BangAdaptive {
    public static final int VIVO_FILLET = 8;
    public static final int VIVO_NOTCH = 32;

    public static int getInt(String str, Activity activity) {
        if (isXiaomi()) {
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

    public static boolean hasNotchAtHuawei(Context context) {
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

    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean hasNotchAtVivo(Context context) {
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

    public static boolean hasNotchScreen(Activity activity) {
        return getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) || hasNotchAtOPPO(activity) || hasNotchAtVivo(activity);
    }

    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }
}
