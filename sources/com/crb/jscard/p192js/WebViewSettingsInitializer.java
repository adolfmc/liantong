package com.crb.jscard.p192js;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* renamed from: com.crb.jscard.js.WebViewSettingsInitializer */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class WebViewSettingsInitializer {
    private WebView mWebView;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.crb.jscard.js.WebViewSettingsInitializer$Holder */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Holder {
        private static WebViewSettingsInitializer INSTANCE = new WebViewSettingsInitializer();

        private Holder() {
        }
    }

    public static WebViewSettingsInitializer getInstance() {
        return Holder.INSTANCE;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public WebView createWebView(WebView webView) {
        this.mWebView = webView;
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setDrawingCacheEnabled(true);
        webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.crb.jscard.js.WebViewSettingsInitializer.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                NBSActionInstrumentation.onLongClickEventEnter(view, this);
                NBSActionInstrumentation.onLongClickEventExit();
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() { // from class: com.crb.jscard.js.WebViewSettingsInitializer.2
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        WebSettings settings = webView.getSettings();
        String userAgentString = settings.getUserAgentString();
        settings.setUserAgentString(userAgentString + "Latte");
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        if (!NetUtil.m32a()) {
            settings.setCacheMode(1);
        } else {
            settings.setCacheMode(-1);
        }
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setTextZoom(100);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        return webView;
    }

    private WebViewSettingsInitializer() {
    }
}
