package com.alipay.p100a.p101a;

import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1908d implements InterfaceC1913i, InterfaceC1914j {
    @Override // com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final Object mo21145a(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
