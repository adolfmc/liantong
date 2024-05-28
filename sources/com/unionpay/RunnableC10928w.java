package com.unionpay;

import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;

@NBSInstrumented
/* renamed from: com.unionpay.w */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10928w implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f20842a;

    /* renamed from: b */
    final /* synthetic */ WebViewJavascriptBridge f20843b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10928w(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.f20843b = webViewJavascriptBridge;
        this.f20842a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WebView webView = this.f20843b.mWebView;
        String str = this.f20842a;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
    }
}
