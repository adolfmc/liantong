package com.alipay.sdk.widget;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.alipay.sdk.widget.WebViewWindow;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.widget.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2079t extends NBSWebViewClient {

    /* renamed from: a */
    final /* synthetic */ WebViewWindow f3983a;

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2079t(WebViewWindow webViewWindow) {
        this.f3983a = webViewWindow;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        WebViewWindow.InterfaceC2056b interfaceC2056b;
        interfaceC2056b = this.f3983a.f3919h;
        if (interfaceC2056b.mo20591b(this.f3983a, str)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        WebViewWindow.InterfaceC2056b interfaceC2056b;
        Tracker.onPageFinished(this, webView, str);
        interfaceC2056b = this.f3983a.f3919h;
        if (interfaceC2056b.mo20586c(this.f3983a, str)) {
            return;
        }
        super.onPageFinished(webView, str);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        WebViewWindow.InterfaceC2056b interfaceC2056b;
        interfaceC2056b = this.f3983a.f3919h;
        if (interfaceC2056b.mo20603a(this.f3983a, i, str, str2)) {
            return;
        }
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        WebViewWindow.InterfaceC2056b interfaceC2056b;
        interfaceC2056b = this.f3983a.f3919h;
        if (interfaceC2056b.mo20602a(this.f3983a, sslErrorHandler, sslError)) {
            return;
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
}
