package com.bytedance.pangle.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import com.bytedance.pangle.activity.IPluginActivity;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p172b.p174b.C3779a;
import com.bytedance.pangle.wrapper.InterfaceC3956a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3938a {
    /* renamed from: a */
    public static void m16657a(InterfaceC3956a interfaceC3956a, Activity activity) {
        try {
            FieldUtils.writeField(interfaceC3956a, "mTheme", (Object) null);
            FieldUtils.writeField((Object) interfaceC3956a, "mThemeResource", (Object) 0);
            int[] m16659a = m16659a(activity);
            if (m16659a == null) {
                return;
            }
            for (int i : m16659a) {
                if (i != 0) {
                    interfaceC3956a.setWrapperActivityTheme(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static int[] m16659a(Activity activity) {
        Resources.Theme theme = activity.getTheme();
        if (theme == null) {
            return null;
        }
        try {
            if (C3950i.m16617g()) {
                Object readField = FieldUtils.readField(theme, "mThemeImpl");
                if (readField == null) {
                    return null;
                }
                try {
                    Object invokeMethod = MethodUtils.invokeMethod(readField, "getKey", new Object[0]);
                    if (invokeMethod != null) {
                        return (int[]) FieldUtils.readField(invokeMethod, "mResId");
                    }
                } catch (Exception unused) {
                }
                Object invoke = C3779a.m16965a(readField.getClass(), "getKey", new Class[0]).invoke(readField, new Object[0]);
                if (invoke == null) {
                    ZeusLogger.m16794d("Zeus/activity_pangle", "getKey failed!");
                    return null;
                }
                ZeusLogger.m16794d("Zeus/activity_pangle", "getKey success by doubleReflector!");
                return (int[]) C3779a.m16966a(invoke.getClass(), "mResId").get(invoke);
            }
            if (Build.VERSION.SDK_INT > 22) {
                Object invokeMethod2 = MethodUtils.invokeMethod(theme, "getKey", new Object[0]);
                if (invokeMethod2 == null) {
                    return null;
                }
                return (int[]) FieldUtils.readField(invokeMethod2, "mResId");
            }
            String str = (String) MethodUtils.invokeMethod(theme, "getKey", new Object[0]);
            if (str == null) {
                return null;
            }
            String[] split = str.trim().replace("!", "").split(" ");
            int[] iArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                iArr[i] = Integer.parseInt(split[i], 16);
            }
            return iArr;
        } catch (Throwable th) {
            ZeusLogger.m16794d("Zeus/activity_pangle", "getKey exception!" + th.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static void m16658a(IPluginActivity iPluginActivity, Activity activity) {
        try {
            FieldUtils.writeField(iPluginActivity, "mTheme", (Object) null);
            FieldUtils.writeField((Object) iPluginActivity, "mThemeResource", (Object) 0);
            int[] m16659a = m16659a(activity);
            if (m16659a == null) {
                return;
            }
            for (int i : m16659a) {
                if (i != 0) {
                    iPluginActivity.setProxyTheme2Plugin(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
