package com.crb.jscard.p192js;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.annotation.RequiresApi;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.crb.jscard.HomeActivity;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;

@NBSInstrumented
/* renamed from: com.crb.jscard.js.CustomWebviewClient */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CustomWebviewClient extends NBSWebViewClient {
    private final String TAG = CustomWebviewClient.class.getSimpleName();
    private Context context;

    public CustomWebviewClient(Context context) {
        this.context = context;
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        super.onPageFinished(webView, str);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    @RequiresApi(api = 21)
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslErrorHandler != null) {
            sslErrorHandler.proceed();
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        C14231v.m72b(this.TAG + 1111, str);
        if (str.startsWith("alipays://platformapi/startApp?")) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setData(Uri.parse(str));
                this.context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                new HomeActivity().m16299a("请确保正确安装支付宝！");
                return true;
            }
        } else if (str.contains("http://tfs.alipayobjects")) {
            new HomeActivity().m16299a("请前往应用商店下载支付宝！");
            return true;
        } else if (!str.startsWith("http:") && !str.startsWith("https:")) {
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(str));
                this.context.startActivity(intent2);
            } catch (Exception unused2) {
            }
            return true;
        } else if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
            return false;
        } else {
            webView.loadUrl(str);
            return false;
        }
    }
}
