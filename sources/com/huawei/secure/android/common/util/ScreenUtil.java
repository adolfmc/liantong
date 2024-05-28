package com.huawei.secure.android.common.util;

import android.app.Activity;
import android.view.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ScreenUtil {

    /* renamed from: a */
    private static final String f12151a = "ScreenUtil";

    /* renamed from: b */
    private static final int f12152b = 524288;

    /* renamed from: com.huawei.secure.android.common.util.ScreenUtil$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C5123a implements PrivilegedAction {

        /* renamed from: a */
        Method f12153a;

        public C5123a(Method method) {
            this.f12153a = method;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            Method method = this.f12153a;
            if (method == null) {
                return null;
            }
            method.setAccessible(true);
            return null;
        }
    }

    /* renamed from: a */
    private static void m13798a(Activity activity, int i) {
        if (activity != null && !activity.isFinishing()) {
            activity.getWindow().addFlags(i);
        } else {
            LogsUtil.m13819e("", "activity is null");
        }
    }

    /* renamed from: b */
    private static void m13797b(Activity activity, int i) {
        if (activity != null && !activity.isFinishing()) {
            activity.getWindow().clearFlags(i);
        } else {
            LogsUtil.m13819e("", "activity is null");
        }
    }

    public static void disableScreenshots(Activity activity) {
        m13798a(activity, 8192);
    }

    public static void enableScreenshots(Activity activity) {
        m13797b(activity, 8192);
    }

    public static void hideOverlayWindows(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        try {
            Window window = activity.getWindow();
            Method declaredMethod = Class.forName("android.view.Window").getDeclaredMethod("addPrivateFlags", Integer.TYPE);
            AccessController.doPrivileged(new C5123a(declaredMethod));
            declaredMethod.invoke(window, 524288);
        } catch (ClassNotFoundException unused) {
            LogsUtil.m13819e("ScreenUtil", "hideOverlayWindows ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogsUtil.m13819e("ScreenUtil", "hideOverlayWindows IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            LogsUtil.m13819e("ScreenUtil", "hideOverlayWindows NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            LogsUtil.m13819e("ScreenUtil", "hideOverlayWindows InvocationTargetException");
        }
    }
}
