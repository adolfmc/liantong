package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.x */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1956x {

    /* renamed from: a */
    private InterfaceC1938g f3465a;

    /* renamed from: b */
    private C1958z f3466b = new C1958z(this);

    public C1956x(InterfaceC1938g interfaceC1938g) {
        this.f3465a = interfaceC1938g;
    }

    /* renamed from: a */
    public final InterfaceC1938g m21062a() {
        return this.f3465a;
    }

    /* renamed from: a */
    public final <T> T m21061a(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C1957y(this.f3465a, cls, this.f3466b));
    }
}
