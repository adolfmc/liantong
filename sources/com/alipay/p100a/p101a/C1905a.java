package com.alipay.p100a.p101a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.json.alipay.C13435a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1905a implements InterfaceC1913i, InterfaceC1914j {
    @Override // com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final Object mo21145a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Object[]) obj) {
            arrayList.add(C1910f.m21148b(obj2));
        }
        return arrayList;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        if (obj.getClass().equals(C13435a.class)) {
            C13435a c13435a = (C13435a) obj;
            if (type instanceof GenericArrayType) {
                throw new IllegalArgumentException("Does not support generic array!");
            }
            Class<?> componentType = ((Class) type).getComponentType();
            int m226a = c13435a.m226a();
            Object newInstance = Array.newInstance(componentType, m226a);
            for (int i = 0; i < m226a; i++) {
                Array.set(newInstance, i, C1909e.m21151a(c13435a.m225a(i), componentType));
            }
            return newInstance;
        }
        return null;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return cls.isArray();
    }
}
