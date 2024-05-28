package com.xiaomi.push;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.aw */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11176aw {

    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f21558a = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.aw$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11177a<T> {

        /* renamed from: a */
        public final Class<? extends T> f21559a;

        /* renamed from: a */
        public final T f21560a;
    }

    static {
        f21558a.put(Boolean.class, Boolean.TYPE);
        f21558a.put(Byte.class, Byte.TYPE);
        f21558a.put(Character.class, Character.TYPE);
        f21558a.put(Short.class, Short.TYPE);
        f21558a.put(Integer.class, Integer.TYPE);
        f21558a.put(Float.class, Float.TYPE);
        f21558a.put(Long.class, Long.TYPE);
        f21558a.put(Double.class, Double.TYPE);
        f21558a.put(Boolean.TYPE, Boolean.TYPE);
        f21558a.put(Byte.TYPE, Byte.TYPE);
        f21558a.put(Character.TYPE, Character.TYPE);
        f21558a.put(Short.TYPE, Short.TYPE);
        f21558a.put(Integer.TYPE, Integer.TYPE);
        f21558a.put(Float.TYPE, Float.TYPE);
        f21558a.put(Long.TYPE, Long.TYPE);
        f21558a.put(Double.TYPE, Double.TYPE);
    }

    /* renamed from: a */
    public static <T> T m4814a(Object obj, String str) {
        try {
            return (T) m4818a((Class<? extends Object>) obj.getClass(), obj, str);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call getField '" + str + "' in " + obj + ", " + e);
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m4817a(Class<? extends Object> cls, String str) {
        try {
            return (T) m4818a(cls, (Object) null, str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Meet exception when call getStaticField '");
            sb.append(str);
            sb.append("' in ");
            sb.append(cls != null ? cls.getSimpleName() : "");
            sb.append(", ");
            sb.append(e);
            Log.w("JavaCalls", sb.toString());
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m4811a(String str, String str2) {
        try {
            return (T) m4818a((Class<? extends Object>) C11479r.m2929a(null, str), (Object) null, str2);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call getStaticField '" + str2 + "' in " + str + ", " + e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Class<? extends java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* renamed from: a */
    public static <T> T m4818a(Class<? extends Object> cls, Object obj, String str) {
        Field field = null;
        while (field == null) {
            try {
                field = cls.getDeclaredField(str);
                field.setAccessible(true);
                continue;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
                continue;
            }
            if (cls == 0) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    /* renamed from: a */
    public static void m4813a(Object obj, String str, Object obj2) {
        try {
            m4805b(obj, str, obj2);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call setField '" + str + "' in " + obj + ", " + e);
        }
    }

    /* renamed from: b */
    public static void m4805b(Object obj, String str, Object obj2) {
        Class<?> cls = obj.getClass();
        Field field = null;
        while (field == null) {
            try {
                field = cls.getDeclaredField(str);
                continue;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
                continue;
            }
            if (cls == null) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        field.set(obj, obj2);
    }

    /* renamed from: a */
    public static <T> T m4812a(Object obj, String str, Object... objArr) {
        try {
            return (T) m4804b(obj, str, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str + "' in " + obj + ", " + e);
            return null;
        }
    }

    /* renamed from: b */
    public static <T> T m4804b(Object obj, String str, Object... objArr) {
        return (T) m4816a(obj.getClass(), str, m4808a(objArr)).invoke(obj, m4807a(objArr));
    }

    /* renamed from: a */
    public static <T> T m4810a(String str, String str2, Object... objArr) {
        try {
            return (T) m4815a(C11479r.m2929a(null, str), str2, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str2 + "' in " + str + ", " + e);
            return null;
        }
    }

    /* renamed from: a */
    private static Method m4816a(Class<?> cls, String str, Class<?>... clsArr) {
        Method m4806a = m4806a(cls.getDeclaredMethods(), str, clsArr);
        if (m4806a == null) {
            if (cls.getSuperclass() != null) {
                return m4816a((Class<?>) cls.getSuperclass(), str, clsArr);
            }
            throw new NoSuchMethodException();
        }
        m4806a.setAccessible(true);
        return m4806a;
    }

    /* renamed from: a */
    private static Method m4806a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str == null) {
            throw new NullPointerException("Method name must not be null.");
        }
        for (Method method : methodArr) {
            if (method.getName().equals(str) && m4809a(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m4809a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        } else if (clsArr2 == null) {
            return clsArr.length == 0;
        } else if (clsArr.length != clsArr2.length) {
            return false;
        } else {
            for (int i = 0; i < clsArr.length; i++) {
                if (clsArr2[i] != null && !clsArr[i].isAssignableFrom(clsArr2[i]) && (!f21558a.containsKey(clsArr[i]) || !f21558a.get(clsArr[i]).equals(f21558a.get(clsArr2[i])))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    public static <T> T m4815a(Class<?> cls, String str, Object... objArr) {
        return (T) m4816a(cls, str, m4808a(objArr)).invoke(null, m4807a(objArr));
    }

    /* renamed from: a */
    private static Class<?>[] m4808a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj != null && (obj instanceof C11177a)) {
                clsArr[i] = ((C11177a) obj).f21559a;
            } else {
                clsArr[i] = obj == null ? null : obj.getClass();
            }
        }
        return clsArr;
    }

    /* renamed from: a */
    private static Object[] m4807a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj != null && (obj instanceof C11177a)) {
                objArr2[i] = ((C11177a) obj).f21560a;
            } else {
                objArr2[i] = obj;
            }
        }
        return objArr2;
    }
}
