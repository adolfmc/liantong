package com.alipay.sdk.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.alipay.sdk.widget.WebViewWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2078s extends WebChromeClient {

    /* renamed from: a */
    final /* synthetic */ WebViewWindow f3982a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2078s(WebViewWindow webViewWindow) {
        this.f3982a = webViewWindow;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        if (i == 100) {
            progressBar4 = this.f3982a.f3916d;
            progressBar4.setVisibility(4);
            return;
        }
        progressBar = this.f3982a.f3916d;
        if (4 == progressBar.getVisibility()) {
            progressBar3 = this.f3982a.f3916d;
            progressBar3.setVisibility(0);
        }
        progressBar2 = this.f3982a.f3916d;
        progressBar2.setProgress(i);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        WebViewWindow.InterfaceC2055a interfaceC2055a;
        interfaceC2055a = this.f3982a.f3918g;
        return interfaceC2055a.mo20600a(this.f3982a, str, str2, str3, jsPromptResult);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        WebViewWindow.InterfaceC2055a interfaceC2055a;
        interfaceC2055a = this.f3982a.f3918g;
        interfaceC2055a.mo20601a(this.f3982a, str);
    }
}
