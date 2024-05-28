package com.unionpay;

import com.unionpay.utils.UPUtils;

/* renamed from: com.unionpay.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10770t implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20699a;

    C10770t(UPPayWapActivity uPPayWapActivity) {
        this.f20699a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        UPUtils.m5866b(this.f20699a, str);
        if (interfaceC10741ab != null) {
            m5970b = UPPayWapActivity.m5970b("0", "success", (String) null);
            interfaceC10741ab.mo5826a(m5970b);
        }
    }
}
