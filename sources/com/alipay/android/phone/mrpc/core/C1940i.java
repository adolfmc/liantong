package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1940i implements InterfaceC1938g {

    /* renamed from: a */
    final /* synthetic */ C1927aa f3411a;

    /* renamed from: b */
    final /* synthetic */ C1939h f3412b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1940i(C1939h c1939h, C1927aa c1927aa) {
        this.f3412b = c1939h;
        this.f3411a = c1927aa;
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1938g
    /* renamed from: a */
    public final String mo21117a() {
        return this.f3411a.m21135a();
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1938g
    /* renamed from: b */
    public final InterfaceC1928ab mo21116b() {
        Context context;
        context = this.f3412b.f3410a;
        return C1944l.m21106a(context.getApplicationContext());
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1938g
    /* renamed from: c */
    public final C1927aa mo21115c() {
        return this.f3411a;
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1938g
    /* renamed from: d */
    public final boolean mo21114d() {
        return this.f3411a.m21132c();
    }
}
