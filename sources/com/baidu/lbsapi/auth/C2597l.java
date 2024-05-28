package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.C2589e;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2597l implements C2589e.InterfaceC2590a<String> {

    /* renamed from: a */
    final /* synthetic */ String f4991a;

    /* renamed from: b */
    final /* synthetic */ LBSAuthManager f4992b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2597l(LBSAuthManager lBSAuthManager, String str) {
        this.f4992b = lBSAuthManager;
        this.f4991a = str;
    }

    @Override // com.baidu.lbsapi.auth.C2589e.InterfaceC2590a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo19644a(String str) {
        this.f4992b.m19688a(str, this.f4991a);
    }
}
