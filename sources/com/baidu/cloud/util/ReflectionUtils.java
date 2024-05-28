package com.baidu.cloud.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReflectionUtils {
    private static final String SECRECT_KEY = "K\u007f@xpU+#HZX\"K!\\d";

    public static String getSecrectKey() {
        return new String(Base64Utils.decode(new String(encrypt("K\u007f@xpU+#HZX\"K!\\d".getBytes()))));
    }

    private static byte[] encrypt(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 18);
        }
        return bArr;
    }

    public static Object newInstance(ClassLoader classLoader, String str) {
        try {
            return newInstance(classLoader.loadClass(str), new Class[0], new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newInstance(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return newInstance(Class.forName(str), clsArr, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newInstance(Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newInstance(String str, Object... objArr) {
        try {
            return newInstance(Class.forName(str), objArr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newInstance(Class<?> cls, Object... objArr) {
        if (objArr != null) {
            try {
                if (objArr.length != 0) {
                    int length = objArr.length;
                    Class[] clsArr = new Class[length];
                    for (int i = 0; i < length; i++) {
                        clsArr[i] = objArr[i].getClass();
                    }
                    return newInstance(cls, clsArr, objArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return cls.newInstance();
    }

    public static Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                return declaredMethod.invoke(null, objArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getStaticFieldValue(String str, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setStaticFieldValue(String str, String str2, Object obj) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object invokeMethod(Object obj, String str) {
        return invokeMethod(obj, str, null, null);
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        Method declaredMethod = getDeclaredMethod(obj, str, clsArr);
        if (declaredMethod != null) {
            declaredMethod.setAccessible(true);
            try {
                return declaredMethod.invoke(obj, objArr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Object invokeMethod(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(obj, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        Field declaredField = getDeclaredField(obj, str);
        if (declaredField != null) {
            declaredField.setAccessible(true);
            try {
                declaredField.set(obj, obj2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setFieldValue(String str, String str2, Object obj, Object obj2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setElFieldValue(Object obj, String str, Object obj2) {
        String[] split = str.split("[.]");
        int length = split.length - 1;
        for (int i = 0; i < length; i++) {
            obj = getFieldValue(obj, split[i]);
            if (obj == null) {
                return;
            }
        }
        setFieldValue(obj, split[length], obj2);
    }

    @Deprecated
    public static Object getElFieldValue(Object obj, String str) {
        for (String str2 : str.split("[.]")) {
            obj = getFieldValue(obj, str2);
            if (obj == null) {
                return null;
            }
        }
        return obj;
    }

    public static Object getFieldValue(Object obj, String str) {
        Field declaredField = getDeclaredField(obj, str);
        if (declaredField != null) {
            declaredField.setAccessible(true);
            try {
                return declaredField.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Object getFieldValue(String str, Object obj, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Method getDeclaredMethod(Object obj, String str, Class<?>... clsArr) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static Field getDeclaredField(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static Type getTypeByClass(Class cls) {
        Type[] genericInterfaces;
        for (Type type : cls.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                return getType((ParameterizedType) type);
            }
            if (type instanceof Class) {
                return getTypeByClass((Class) type);
            }
        }
        return null;
    }

    private static Type getType(ParameterizedType parameterizedType) {
        Type type = parameterizedType.getActualTypeArguments()[0];
        return type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type;
    }
}
