package com.networkbench.agent.impl.util;

import com.networkbench.agent.impl.socket.p278a.C6601a;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.n */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6645n {
    /* renamed from: a */
    public static <C, F> F m8877a(Field field, C c) throws C6632b {
        if (field == null) {
            C6631a.m9136a(field);
            return null;
        } else if (field != null) {
            field.setAccessible(true);
            try {
                return (F) field.get(c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                throw new C6632b("Unable to get value of field", th);
            }
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public static <C, T> void m8876a(Field field, C c, T t) throws C6632b {
        if (field == null) {
            C6631a.m9136a(field);
            return;
        }
        field.setAccessible(true);
        try {
            field.set(c, t);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            throw new C6632b("Unable to set field", th);
        }
    }

    /* renamed from: a */
    public static Field m8883a(Class<?> cls, Class<?> cls2) throws C6632b {
        Field[] declaredFields = cls.getDeclaredFields();
        Field field = null;
        for (int i = 0; i < declaredFields.length; i++) {
            if (cls2.isAssignableFrom(declaredFields[i].getType())) {
                if (field != null) {
                    throw new C6632b("Field is ambiguous: " + field.getName() + ", " + declaredFields[i].getName());
                }
                field = declaredFields[i];
            }
        }
        if (field == null) {
            C6631a.m9136a(field);
            throw new C6632b("Could not find field matching type: " + cls2.getName());
        }
        field.setAccessible(true);
        return field;
    }

    /* renamed from: a */
    public static Field m8882a(Class cls, Class cls2, boolean z) throws C6601a {
        Field[] declaredFields = cls.getDeclaredFields();
        Field field = null;
        for (int i = 0; i < declaredFields.length; i++) {
            if (cls2.isAssignableFrom(declaredFields[i].getType())) {
                if (field != null) {
                    throw new C6601a("Field is ambiguous: " + field.getName() + ", " + declaredFields[i].getName());
                }
                field = declaredFields[i];
            }
        }
        if (field != null) {
            field.setAccessible(true);
        } else if (z) {
            throw new C6601a("Could not find field matching type: " + cls2.getName());
        }
        return field;
    }

    /* renamed from: b */
    public static Object m8874b(Field field, Object obj) {
        if (field != null && field != null) {
            field.setAccessible(true);
            try {
                return field.get(obj);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static void m8881a(Class<?> cls, Object obj, Object obj2) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fields[i].set(obj2, fields[i].get(obj));
        }
    }

    /* renamed from: a */
    public static void m8878a(AccessibleObject accessibleObject, AccessibleObject[] accessibleObjectArr) {
        C6631a.m9136a(accessibleObject);
        if (accessibleObject != null) {
            accessibleObject.setAccessible(true);
        }
        if (accessibleObjectArr == null || accessibleObjectArr.length <= 0) {
            return;
        }
        m8875a(accessibleObjectArr);
    }

    /* renamed from: a */
    public static void m8875a(AccessibleObject[] accessibleObjectArr) {
        C6631a.m9136a(accessibleObjectArr);
        for (AccessibleObject accessibleObject : accessibleObjectArr) {
            C6631a.m9136a(accessibleObject);
            if (accessibleObject != null) {
                accessibleObject.setAccessible(true);
            }
        }
    }

    /* renamed from: a */
    public static <T> T m8879a(Object obj, String str) throws Exception {
        Field m8880a = m8880a((Class) obj.getClass(), str);
        if (m8880a == null) {
            return null;
        }
        m8880a.setAccessible(true);
        return (T) m8880a.get(obj);
    }

    /* renamed from: a */
    private static Field m8880a(Class cls, String str) throws Exception {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Class superclass = cls.getSuperclass();
            if (superclass == null) {
                throw e;
            }
            return m8880a(superclass, str);
        }
    }
}
