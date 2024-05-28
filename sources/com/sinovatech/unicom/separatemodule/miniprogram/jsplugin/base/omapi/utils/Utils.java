package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Utils {
    @SuppressLint({"StaticFieldLeak"})
    private static Application sApplication;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(@NonNull Context context) {
        init((Application) context.getApplicationContext());
    }

    public static void init(@NonNull Application application) {
        if (sApplication == null) {
            sApplication = application;
        }
    }

    public static Application getApp() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (invoke == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) invoke);
            return sApplication;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    public static boolean isEmpty(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (str == null || str.length() < 1) {
                return true;
            }
        }
        return false;
    }

    public static String reverse(String str) {
        int i = 0;
        if (isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (i < str.length()) {
            int i2 = i + 1;
            if (i2 % 2 == 0) {
                char charAt = str.charAt(i - 1);
                sb.append(str.charAt(i));
                sb.append(charAt);
            }
            i = i2;
        }
        if (str.length() % 2 != 0) {
            sb.append(str.charAt(str.length() - 1));
        }
        return sb.toString();
    }
}
