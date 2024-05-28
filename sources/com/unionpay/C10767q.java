package com.unionpay;

/* renamed from: com.unionpay.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10767q implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20696a;

    C10767q(UPPayWapActivity uPPayWapActivity) {
        this.f20696a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        String m6013a = UPPayAssistEx.m6013a(this.f20696a);
        if (interfaceC10741ab != null) {
            m5970b = UPPayWapActivity.m5970b("0", "success", m6013a);
            interfaceC10741ab.mo5826a(m5970b);
        }
    }
}
