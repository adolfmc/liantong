package com.bytedance.pangle.util;

import android.support.annotation.Keep;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MethodUtils {
    public static final Class<?>[] EMPTY_CLASS_ARRAY;
    private static Map<String, Method> sMethodCache = new HashMap();
    private static final HashMap<Class<?>, Class<?>> sPrimitiveToWrapperMap;

    static {
        HashMap<Class<?>, Class<?>> hashMap = new HashMap<>();
        sPrimitiveToWrapperMap = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        sPrimitiveToWrapperMap.put(Byte.TYPE, Byte.class);
        sPrimitiveToWrapperMap.put(Character.TYPE, Character.class);
        sPrimitiveToWrapperMap.put(Short.TYPE, Short.class);
        sPrimitiveToWrapperMap.put(Integer.TYPE, Integer.class);
        sPrimitiveToWrapperMap.put(Long.TYPE, Long.class);
        sPrimitiveToWrapperMap.put(Double.TYPE, Double.class);
        sPrimitiveToWrapperMap.put(Float.TYPE, Float.class);
        sPrimitiveToWrapperMap.put(Void.TYPE, Void.class);
        EMPTY_CLASS_ARRAY = new Class[0];
    }

    private static String getKey(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString());
        sb.append("#");
        sb.append(str);
        sb.append("#");
        sb.append(cls.getClassLoader() != null ? Integer.valueOf(cls.getClassLoader().hashCode()) : "");
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.toString());
                sb.append("#");
            }
        } else {
            sb.append(Void.class.toString());
        }
        return sb.toString();
    }

    public static Method getAccessibleMethod(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        Method[] declaredMethods;
        String key = getKey(cls, str, clsArr);
        synchronized (sMethodCache) {
            method = sMethodCache.get(key);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        while (cls != null) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
            if (method == null) {
                try {
                    Method method2 = method;
                    for (Method method3 : cls.getDeclaredMethods()) {
                        try {
                            if (method3 != null && TextUtils.equals(method3.getName(), str)) {
                                Class<?>[] parameterTypes = method3.getParameterTypes();
                                if (clsArr != null && parameterTypes != null && clsArr.length == parameterTypes.length) {
                                    boolean z = true;
                                    for (int i = 0; i < clsArr.length; i++) {
                                        if (!isAssignableFrom(clsArr[i], parameterTypes[i])) {
                                            z = false;
                                        }
                                    }
                                    if (z) {
                                        method2 = method3;
                                    }
                                }
                            }
                        } catch (Throwable unused2) {
                            method = method2;
                        }
                    }
                    method = method2;
                } catch (Throwable unused3) {
                }
            }
            if (method != null) {
                method.setAccessible(true);
                synchronized (sMethodCache) {
                    sMethodCache.put(key, method);
                }
                return method;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static boolean isAssignableFrom(Class<?> cls, Class<?> cls2) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (cls.isPrimitive() && !cls2.isPrimitive()) {
            cls = sPrimitiveToWrapperMap.get(cls);
        }
        if (cls2.isPrimitive() && !cls.isPrimitive()) {
            cls2 = sPrimitiveToWrapperMap.get(cls2);
        }
        return cls2.isAssignableFrom(cls);
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) {
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, clsArr);
        if (matchingAccessibleConstructor != null) {
            return (T) matchingAccessibleConstructor.newInstance(objArr);
        }
        return null;
    }

    public static Object invokeStaticMethod(Class cls, String str, Object... objArr) {
        return invokeStaticMethod(cls, str, objArr, toClass(objArr));
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) {
        return invokeMethod(obj, str, objArr, toClass(objArr));
    }

    public static Object invokeStaticMethod(Class cls, String str, Object[] objArr, Class<?>[] clsArr) {
        Method accessibleMethod = getAccessibleMethod(cls, str, clsArr);
        if (accessibleMethod != null) {
            return accessibleMethod.invoke(null, objArr);
        }
        return null;
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        Method accessibleMethod = getAccessibleMethod(obj.getClass(), str, clsArr);
        if (accessibleMethod != null) {
            return accessibleMethod.invoke(obj, objArr);
        }
        throw new NoSuchMethodException(str);
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Class<?>[] toClass(Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
            }
            return clsArr;
        }
        return EMPTY_CLASS_ARRAY;
    }
}
