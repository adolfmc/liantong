package com.alipay.p100a.p101a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import org.json.alipay.C13435a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1915k implements InterfaceC1913i {
    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        if (obj.getClass().equals(C13435a.class)) {
            C13435a c13435a = (C13435a) obj;
            HashSet hashSet = new HashSet();
            Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
            for (int i = 0; i < c13435a.m226a(); i++) {
                hashSet.add(C1909e.m21151a(c13435a.m225a(i), cls));
            }
            return hashSet;
        }
        return null;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
