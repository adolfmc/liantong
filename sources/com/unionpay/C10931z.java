package com.unionpay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.unionpay.utils.C10923j;

@NBSInstrumented
/* renamed from: com.unionpay.z */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10931z extends NBSWebViewClient {

    /* renamed from: a */
    final /* synthetic */ WebViewJavascriptBridge f20847a;

    private C10931z(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.f20847a = webViewJavascriptBridge;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C10931z(WebViewJavascriptBridge webViewJavascriptBridge, byte b) {
        this(webViewJavascriptBridge);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        C10923j.m5830a("test", "onPageFinished");
        super.onPageFinished(webView, str);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        boolean z;
        C10923j.m5830a("uppay", "shouldOverrideUrlLoading：" + str);
        z = this.f20847a.mAllowScheme;
        if (z && !TextUtils.isEmpty(str) && !str.startsWith("http")) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                this.f20847a.mContext.startActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}
