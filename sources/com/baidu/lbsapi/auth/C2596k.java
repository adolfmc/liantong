package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.C2586c;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2596k implements C2586c.InterfaceC2587a<String> {

    /* renamed from: a */
    final /* synthetic */ String f4989a;

    /* renamed from: b */
    final /* synthetic */ LBSAuthManager f4990b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2596k(LBSAuthManager lBSAuthManager, String str) {
        this.f4990b = lBSAuthManager;
        this.f4989a = str;
    }

    @Override // com.baidu.lbsapi.auth.C2586c.InterfaceC2587a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo19646a(String str) {
        this.f4990b.m19688a(str, this.f4989a);
    }
}
