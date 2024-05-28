package com.networkbench.agent.impl.p268n;

import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6526k {
    /* renamed from: a */
    public static Object m9525a(Field field, Object obj) {
        if (field == null || field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static Field m9526a(Class<?> cls, Class<?> cls2, boolean z) {
        Field[] declaredFields = cls.getDeclaredFields();
        Field field = null;
        for (int i = 0; i < declaredFields.length; i++) {
            if (cls2.isAssignableFrom(declaredFields[i].getType())) {
                field = declaredFields[i];
            }
        }
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }
}
