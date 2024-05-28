package com.alipay.p100a.p101a;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.TreeMap;
import org.json.alipay.C13436b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1911g implements InterfaceC1913i, InterfaceC1914j {
    @Override // com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final Object mo21145a(Object obj) {
        TreeMap treeMap = new TreeMap();
        Class<?> cls = obj.getClass();
        while (true) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (cls.equals(Object.class)) {
                return treeMap;
            }
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object obj2 = null;
                    if (field != null && obj != null && !"this$0".equals(field.getName())) {
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        Object obj3 = field.get(obj);
                        if (obj3 != null) {
                            field.setAccessible(isAccessible);
                            obj2 = C1910f.m21148b(obj3);
                        }
                    }
                    if (obj2 != null) {
                        treeMap.put(field.getName(), obj2);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        if (obj.getClass().equals(C13436b.class)) {
            C13436b c13436b = (C13436b) obj;
            Class cls = (Class) type;
            Object newInstance = cls.newInstance();
            while (!cls.equals(Object.class)) {
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null && declaredFields.length > 0) {
                    for (Field field : declaredFields) {
                        String name = field.getName();
                        Type genericType = field.getGenericType();
                        if (c13436b.m219b(name)) {
                            field.setAccessible(true);
                            field.set(newInstance, C1909e.m21151a(c13436b.m221a(name), genericType));
                        }
                    }
                }
                cls = cls.getSuperclass();
            }
            return newInstance;
        }
        return null;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return true;
    }
}
