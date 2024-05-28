package org.aspectj.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Aspects {
    private static final String ASPECTOF = "aspectOf";
    private static final String HASASPECT = "hasAspect";
    private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final Class[] PEROBJECT_CLASS_ARRAY = {Object.class};
    private static final Class[] PERTYPEWITHIN_CLASS_ARRAY = {Class.class};
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    public static <T> T aspectOf(Class<T> cls) {
        try {
            return (T) getSingletonOrThreadAspectOf(cls).invoke(null, EMPTY_OBJECT_ARRAY);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static <T> T aspectOf(Class<T> cls, Class<?> cls2) {
        try {
            return (T) getPerTypeWithinAspectOf(cls).invoke(null, cls2);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static <T> T aspectOf(Class<T> cls, Object obj) {
        try {
            return (T) getPerObjectAspectOf(cls).invoke(null, obj);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    private static Method checkAspectOf(Method method, Class<?> cls) {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        throw new NoSuchMethodException(cls.getName() + ".aspectOf(..) is not accessible public static");
    }

    private static Method checkHasAspect(Method method, Class cls) {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        throw new NoSuchMethodException(cls.getName() + ".hasAspect(..) is not accessible public static");
    }

    private static Method getPerObjectAspectOf(Class<?> cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerObjectHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinAspectOf(Class<?> cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method getSingletonOrThreadAspectOf(Class<?> cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", EMPTY_CLASS_ARRAY), cls);
    }

    private static Method getSingletonOrThreadHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", EMPTY_CLASS_ARRAY), cls);
    }

    public static boolean hasAspect(Class<?> cls) {
        try {
            return ((Boolean) getSingletonOrThreadHasAspect(cls).invoke(null, EMPTY_OBJECT_ARRAY)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class<?> cls, Class<?> cls2) {
        try {
            return ((Boolean) getPerTypeWithinHasAspect(cls).invoke(null, cls2)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class<?> cls, Object obj) {
        try {
            return ((Boolean) getPerObjectHasAspect(cls).invoke(null, obj)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
