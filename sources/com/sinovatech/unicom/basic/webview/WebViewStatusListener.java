package com.sinovatech.unicom.basic.webview;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebViewStatusListener {
    public boolean onPageFinished() {
        return false;
    }

    public boolean onPageStarted(String str) {
        return false;
    }

    public void onProgressChanged(int i) {
    }

    public boolean onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        return false;
    }

    public boolean onReceivedTitle(String str) {
        return false;
    }

    public boolean onRecevieError(int i, String str, String str2) {
        return false;
    }

    public boolean onShouldOverrideUrlLoading(String str) {
        return false;
    }

    public void openFileChooserFor5(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
    }
}
