package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.y */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1957y implements InvocationHandler {

    /* renamed from: a */
    protected InterfaceC1938g f3467a;

    /* renamed from: b */
    protected Class<?> f3468b;

    /* renamed from: c */
    protected C1958z f3469c;

    public C1957y(InterfaceC1938g interfaceC1938g, Class<?> cls, C1958z c1958z) {
        this.f3467a = interfaceC1938g;
        this.f3468b = cls;
        this.f3469c = c1958z;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f3469c.m21060a(method, objArr);
    }
}
