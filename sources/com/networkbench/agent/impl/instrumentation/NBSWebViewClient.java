package com.networkbench.agent.impl.instrumentation;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6524j;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSWebViewClient extends WebViewClient {
    private boolean isAddJavascript = false;
    private String mainPageUrl;

    @Deprecated
    /* renamed from: a */
    protected void mo9870a() {
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        this.mainPageUrl = str;
        C6396h.m10138d("onPageStarted: " + str);
        if (C6459b.m9934f() && webView.getTag(NBSWebLoadInstrument.WEBVIEW_TAG) == null) {
            webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
            webView.setTag(NBSWebLoadInstrument.WEBVIEW_TAG, Integer.valueOf(NBSWebLoadInstrument.WEBVIEW_TAG));
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        super.onPageFinished(webView, str);
        C6396h.m10138d("onPageFinished, str:" + str);
        if (C6459b.m9934f()) {
            NBSAppAgent.setWebViewUrlRealAddress("");
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (C6459b.m9934f() && Build.VERSION.SDK_INT < 23) {
            try {
                if (Harvest.isWebView_enabled()) {
                    InterfaceC6393e interfaceC6393e = C6638h.f17124y;
                    interfaceC6393e.mo10122a("onReceivedError below23 errorcode:" + i + ", description:" + str + ", failingUrl:" + str2);
                    C6524j.m9542a(webView, i, str, str2);
                }
            } catch (Exception unused) {
                C6638h.f17124y.mo10115e("onReceivedError processErrorBelow23 error!");
            }
        }
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (C6459b.m9934f() && C6638h.m8963w().m9063X()) {
            try {
                if (Harvest.isWebView_enabled()) {
                    C6638h.f17124y.mo10122a("onReceivedError up 23 ");
                    C6524j.m9540a(webView, webResourceRequest, webResourceError, this.mainPageUrl);
                }
            } catch (Exception unused) {
                C6638h.f17124y.mo10115e("onReceivedError processErrorUp23 error!");
            }
        }
        super.onReceivedError(webView, webResourceRequest, webResourceError);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(23)
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        if (C6459b.m9934f() && C6638h.m8963w().m9063X()) {
            try {
                if (Harvest.isWebView_enabled()) {
                    C6638h.f17124y.mo10122a("onReceivedHttpError up 23 ");
                    C6524j.m9539a(webView, webResourceRequest, webResourceResponse, this.mainPageUrl);
                }
            } catch (Exception unused) {
                C6638h.f17124y.mo10115e("onReceivedHttpError processHttpErrorUp23 error!");
            }
        }
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (C6459b.m9934f() && C6638h.m8963w().m9063X()) {
            try {
                if (Harvest.isWebView_enabled()) {
                    C6638h.f17124y.mo10122a("onReceivedSslError up 23 ");
                    C6524j.m9541a(webView, sslErrorHandler, sslError, this.mainPageUrl);
                }
            } catch (Exception unused) {
                C6638h.f17124y.mo10115e("onReceivedSslError processSslError error!");
            }
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
}
