package com.unionpay;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10929x implements InterfaceC10741ab {

    /* renamed from: a */
    final /* synthetic */ WebViewJavascriptBridge f20844a;

    /* renamed from: b */
    private final String f20845b;

    public C10929x(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.f20844a = webViewJavascriptBridge;
        this.f20845b = str;
    }

    @Override // com.unionpay.InterfaceC10741ab
    /* renamed from: a */
    public final void mo5826a(String str) {
        this.f20844a._callbackJs(this.f20845b, str);
    }
}
