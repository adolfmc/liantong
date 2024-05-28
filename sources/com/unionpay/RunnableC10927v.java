package com.unionpay;

/* renamed from: com.unionpay.v */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10927v implements Runnable {

    /* renamed from: a */
    final /* synthetic */ InterfaceC10740aa f20838a;

    /* renamed from: b */
    final /* synthetic */ String f20839b;

    /* renamed from: c */
    final /* synthetic */ InterfaceC10741ab f20840c;

    /* renamed from: d */
    final /* synthetic */ WebViewJavascriptBridge f20841d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10927v(WebViewJavascriptBridge webViewJavascriptBridge, InterfaceC10740aa interfaceC10740aa, String str, InterfaceC10741ab interfaceC10741ab) {
        this.f20841d = webViewJavascriptBridge;
        this.f20838a = interfaceC10740aa;
        this.f20839b = str;
        this.f20840c = interfaceC10741ab;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC10740aa interfaceC10740aa = this.f20838a;
        if (interfaceC10740aa != null) {
            interfaceC10740aa.mo5871a(this.f20839b, this.f20840c);
        }
    }
}
