package p000;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
  synthetic
 */
/* renamed from: g0 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class StatusBarUtil {
    /* renamed from: a */
    public static void m2020a(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            if (i >= 19) {
                activity.getWindow().setFlags(67108864, 67108864);
                return;
            }
            return;
        }
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    /* renamed from: b */
    public static boolean m2015b(Object obj, boolean z) {
        Window window;
        if (obj instanceof Activity) {
            window = ((Activity) obj).getWindow();
        } else {
            window = obj instanceof Window ? (Window) obj : null;
        }
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m2019a(Activity activity, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(activity.getResources().getColor(i));
        }
    }

    /* renamed from: a */
    public static void m2017a(Object obj, boolean z) {
        Window window;
        if (obj instanceof Activity) {
            window = ((Activity) obj).getWindow();
        } else {
            window = obj instanceof Window ? (Window) obj : null;
        }
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Class<?> cls3 = Integer.TYPE;
                Method method = cls.getMethod("setExtraFlags", cls3, cls3);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                if (Build.VERSION.SDK_INT < 23 || !RomUtils.m2040e()) {
                    return;
                }
                if (z) {
                    window.getDecorView().setSystemUiVisibility(9216);
                } else {
                    window.getDecorView().setSystemUiVisibility(1280);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m2018a(Activity activity, boolean z, boolean z2) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            if (Constant.f24100a == -1) {
                Constant.f24100a = RomUtils.m2044a();
            }
            int i2 = Constant.f24100a;
            if (i2 == 1) {
                m2017a(activity, z);
            } else if (i2 != 2) {
                if (i2 != 3) {
                    return;
                }
                m2016a((Object) activity, z, z2);
            } else if (i > 23) {
                m2016a((Object) activity, z, z2);
            } else {
                m2015b(activity, z);
            }
        }
    }

    @RequiresApi(api = 23)
    /* renamed from: a */
    public static void m2016a(Object obj, boolean z, boolean z2) {
        View decorView;
        if (obj instanceof Activity) {
            decorView = ((Activity) obj).getWindow().getDecorView();
        } else {
            decorView = obj instanceof Window ? ((Window) obj).getDecorView() : null;
        }
        if (decorView == null) {
            return;
        }
        if (z) {
            if (z2) {
                decorView.setSystemUiVisibility(9216);
            } else {
                decorView.setSystemUiVisibility(8192);
            }
        } else if (z2) {
            decorView.setSystemUiVisibility(1280);
        } else {
            decorView.setSystemUiVisibility(256);
        }
    }
}
