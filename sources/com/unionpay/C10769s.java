package com.unionpay;

import com.unionpay.utils.UPUtils;

/* renamed from: com.unionpay.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10769s implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20698a;

    C10769s(UPPayWapActivity uPPayWapActivity) {
        this.f20698a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        String m5870a = UPUtils.m5870a(this.f20698a, str);
        if (interfaceC10741ab != null) {
            m5970b = UPPayWapActivity.m5970b("0", "success", m5870a);
            interfaceC10741ab.mo5826a(m5970b);
        }
    }
}
