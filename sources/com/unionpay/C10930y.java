package com.unionpay;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10930y extends WebChromeClient {

    /* renamed from: a */
    final /* synthetic */ WebViewJavascriptBridge f20846a;

    private C10930y(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.f20846a = webViewJavascriptBridge;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C10930y(WebViewJavascriptBridge webViewJavascriptBridge, byte b) {
        this(webViewJavascriptBridge);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.cancel();
        Toast.makeText(this.f20846a.mContext, str2, 0).show();
        return true;
    }
}
