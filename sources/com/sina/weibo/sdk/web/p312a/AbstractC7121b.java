package com.sina.weibo.sdk.web.p312a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.sina.weibo.sdk.auth.C7087b;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.share.ShareTransActivity;
import com.sina.weibo.sdk.web.InterfaceC7119a;
import com.sina.weibo.sdk.web.p313b.AbstractC7125b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sina.weibo.sdk.web.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC7121b extends NBSWebViewClient {

    /* renamed from: aA */
    protected AbstractC7125b f18356aA;

    /* renamed from: aB */
    protected WbAuthListener f18357aB;

    /* renamed from: ax */
    protected C7087b f18358ax = C7087b.m8090b();

    /* renamed from: ay */
    protected Activity f18359ay;

    /* renamed from: az */
    protected InterfaceC7119a f18360az;

    /* renamed from: p */
    public void mo8011p(String str) {
    }

    /* renamed from: q */
    public void mo8010q() {
    }

    /* renamed from: s */
    public boolean mo8009s() {
        return false;
    }

    public AbstractC7121b(Activity activity, InterfaceC7119a interfaceC7119a, AbstractC7125b abstractC7125b) {
        this.f18359ay = activity;
        this.f18360az = interfaceC7119a;
        this.f18356aA = abstractC7125b;
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
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        InterfaceC7119a interfaceC7119a = this.f18360az;
        if (interfaceC7119a != null) {
            webResourceError.getErrorCode();
            webResourceError.getDescription().toString();
            webResourceRequest.getUrl();
            interfaceC7119a.mo8018p();
        }
    }

    /* renamed from: a */
    private void m8015a(int i, String str) {
        Bundle extras = this.f18359ay.getIntent().getExtras();
        if (extras == null) {
            return;
        }
        Intent intent = new Intent(this.f18359ay, ShareTransActivity.class);
        intent.setAction("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY");
        intent.putExtras(extras);
        intent.putExtra("_weibo_resp_errcode", i);
        intent.putExtra("_weibo_resp_errstr", str);
        this.f18359ay.setResult(-1, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: m */
    public final void m8014m(String str) {
        m8015a(0, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: n */
    public final void m8013n(String str) {
        m8015a(2, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: o */
    public final void m8012o(String str) {
        m8015a(1, str);
    }
}
