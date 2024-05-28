package com.github.lzyzsd.jsbridge;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BridgeWebViewClient extends NBSWebViewClient {
    private BridgeWebView webView;

    public BridgeWebViewClient(BridgeWebView bridgeWebView) {
        this.webView = bridgeWebView;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (str.startsWith("yy://return/")) {
            this.webView.handlerReturnData(str);
            return true;
        } else if (str.startsWith("yy://")) {
            this.webView.flushMessageQueue();
            return true;
        } else {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        super.onPageFinished(webView, str);
        BridgeUtil.webViewLoadLocalJs(webView, "WebViewJavascriptBridge.js");
        if (this.webView.getStartupMessage() != null) {
            for (Message message : this.webView.getStartupMessage()) {
                this.webView.dispatchMessage(message);
            }
            this.webView.setStartupMessage(null);
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }
}
