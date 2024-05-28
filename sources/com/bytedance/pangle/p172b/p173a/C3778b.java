package com.bytedance.pangle.p172b.p173a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.b.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3778b {
    /* renamed from: a */
    public static Field m16969a(Class<?> cls, String str) {
        Field declaredField = cls.getDeclaredField(str);
        if (declaredField != null && !declaredField.isAccessible()) {
            declaredField.setAccessible(true);
        }
        return declaredField;
    }

    /* renamed from: a */
    public static Method m16968a(Class<?> cls, String str, Class<?>... clsArr) {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (declaredMethod != null && !declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod;
    }

    /* renamed from: a */
    public static Constructor m16967a(Class<?> cls, Class<?>... clsArr) {
        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
        if (declaredConstructor != null && !declaredConstructor.isAccessible()) {
            declaredConstructor.setAccessible(true);
        }
        return declaredConstructor;
    }
}
