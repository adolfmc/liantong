package com.alipay.p100a.p102b;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1917a {
    /* renamed from: a */
    public static Class<?> m21142a(Type type) {
        while (!(type instanceof Class)) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalArgumentException("TODO");
            }
            type = ((ParameterizedType) type).getRawType();
        }
        return (Class) type;
    }

    /* renamed from: a */
    public static boolean m21143a(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }
}
