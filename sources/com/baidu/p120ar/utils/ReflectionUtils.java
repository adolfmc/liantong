package com.baidu.p120ar.utils;

import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ReflectionUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReflectionUtils {
    private static final String TAG = "ReflectionUtils";

    public static boolean isClassExist(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, false, classLoader) != null;
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "isClassExist() class " + str + " is not exist!!!");
            return false;
        }
    }

    public static Object getNewInstance(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "getNewInstance() className = " + str + " error!!!");
            return null;
        }
    }

    public static Object getNewInstance(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return getNewInstance(getConstructor(str, clsArr), objArr);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "getNewInstance(params) className = " + str + " error!!!");
            return null;
        }
    }

    public static Object getSingleInstance(String str, String str2) {
        try {
            return Class.forName(str).getDeclaredMethod(str2, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "getSingleInstance() className = " + str + " error!!!");
            return null;
        }
    }

    public static Object getNewInstance(Constructor<?> constructor, Object... objArr) {
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "getNewInstance() error!!!");
            return null;
        }
    }

    public static Constructor<?> getConstructor(String str, Class<?>... clsArr) {
        try {
            return Class.forName(str).getDeclaredConstructor(clsArr);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "getSingleInstance() className = " + str + " error!!!");
            return null;
        }
    }

    public static Object invokeMethod(Object obj, String str, String str2, Object obj2, String str3) {
        try {
            return Class.forName(str).getDeclaredMethod(str2, Class.forName(str3)).invoke(obj, obj2);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "invokeMethod() className = " + str + " && methodName = " + str2 + " error!!!");
            return null;
        }
    }

    public static Object invokeMethod(Object obj, String str, String str2, Object obj2, Class<?> cls) {
        try {
            return Class.forName(str).getDeclaredMethod(str2, cls).invoke(obj, obj2);
        } catch (Exception unused) {
            ARLog.m20417i("ReflectionUtils", "invokeMethod() className = " + str + " && methodName = " + str2 + " error!!!");
            return null;
        }
    }
}
