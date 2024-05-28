package com.alipay.sdk.app;

import com.alipay.sdk.util.C2042e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C1996h implements C2042e.InterfaceC2043a {

    /* renamed from: a */
    final /* synthetic */ PayTask f3582a;

    @Override // com.alipay.sdk.util.C2042e.InterfaceC2043a
    /* renamed from: a */
    public void mo20694a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1996h(PayTask payTask) {
        this.f3582a = payTask;
    }

    @Override // com.alipay.sdk.util.C2042e.InterfaceC2043a
    /* renamed from: b */
    public void mo20693b() {
        this.f3582a.dismissLoading();
    }
}
