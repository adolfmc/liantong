package szorg.mp4parser.aspectj.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Aspects14 {
    private static final String ASPECTOF = "aspectOf";
    private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final Object[] EMPTY_OBJECT_ARRAY;
    private static final String HASASPECT = "hasAspect";
    private static final Class[] PEROBJECT_CLASS_ARRAY;
    private static final Class[] PERTYPEWITHIN_CLASS_ARRAY;
    static Class class$java$lang$Class;
    static Class class$java$lang$Object;

    static {
        Class[] clsArr = new Class[1];
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        clsArr[0] = cls;
        PEROBJECT_CLASS_ARRAY = clsArr;
        Class[] clsArr2 = new Class[1];
        Class cls2 = class$java$lang$Class;
        if (cls2 == null) {
            cls2 = class$("java.lang.Class");
            class$java$lang$Class = cls2;
        }
        clsArr2[0] = cls2;
        PERTYPEWITHIN_CLASS_ARRAY = clsArr2;
        EMPTY_OBJECT_ARRAY = new Object[0];
    }

    public static Object aspectOf(Class cls) {
        try {
            return getSingletonOrThreadAspectOf(cls).invoke(null, EMPTY_OBJECT_ARRAY);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static Object aspectOf(Class cls, Class cls2) {
        try {
            return getPerTypeWithinAspectOf(cls).invoke(null, cls2);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    public static Object aspectOf(Class cls, Object obj) {
        try {
            return getPerObjectAspectOf(cls).invoke(null, obj);
        } catch (InvocationTargetException e) {
            throw new NoAspectBoundException(cls.getName(), e);
        } catch (Exception e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        }
    }

    private static Method checkAspectOf(Method method, Class cls) {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".aspectOf(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    private static Method checkHasAspect(Method method, Class cls) {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".hasAspect(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static Method getPerObjectAspectOf(Class cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerObjectHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", PEROBJECT_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinAspectOf(Class cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method getPerTypeWithinHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", PERTYPEWITHIN_CLASS_ARRAY), cls);
    }

    private static Method getSingletonOrThreadAspectOf(Class cls) {
        return checkAspectOf(cls.getDeclaredMethod("aspectOf", EMPTY_CLASS_ARRAY), cls);
    }

    private static Method getSingletonOrThreadHasAspect(Class cls) {
        return checkHasAspect(cls.getDeclaredMethod("hasAspect", EMPTY_CLASS_ARRAY), cls);
    }

    public static boolean hasAspect(Class cls) {
        try {
            return ((Boolean) getSingletonOrThreadHasAspect(cls).invoke(null, EMPTY_OBJECT_ARRAY)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class cls, Class cls2) {
        try {
            return ((Boolean) getPerTypeWithinHasAspect(cls).invoke(null, cls2)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasAspect(Class cls, Object obj) {
        try {
            return ((Boolean) getPerObjectHasAspect(cls).invoke(null, obj)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
