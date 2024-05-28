package com.unionpay;

/* renamed from: com.unionpay.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10913u implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20827a;

    C10913u(UPPayWapActivity uPPayWapActivity) {
        this.f20827a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        UPPayWapActivity.m5975a(this.f20827a, Boolean.parseBoolean(str));
        if (interfaceC10741ab != null) {
            m5970b = UPPayWapActivity.m5970b("0", "success", (String) null);
            interfaceC10741ab.mo5826a(m5970b);
        }
    }
}
