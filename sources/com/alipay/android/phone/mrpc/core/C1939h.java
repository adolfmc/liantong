package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1939h extends AbstractC1955w {

    /* renamed from: a */
    private Context f3410a;

    public C1939h(Context context) {
        this.f3410a = context;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC1955w
    /* renamed from: a */
    public final <T> T mo21063a(Class<T> cls, C1927aa c1927aa) {
        return (T) new C1956x(new C1940i(this, c1927aa)).m21061a(cls);
    }
}
