package com.sina.weibo.sdk.web.p312a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.p311b.C7094e;
import com.sina.weibo.sdk.web.InterfaceC7119a;
import com.sina.weibo.sdk.web.p313b.AbstractC7125b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.web.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7123d extends AbstractC7121b {
    public C7123d(Activity activity, InterfaceC7119a interfaceC7119a, AbstractC7125b abstractC7125b) {
        super(activity, interfaceC7119a, abstractC7125b);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("sinaweibo://browser/close")) {
            return false;
        }
        Bundle m8064h = C7094e.m8064h(str);
        if (m8064h != null) {
            String string = m8064h.getString("code");
            String string2 = m8064h.getString("msg");
            if ("0".equals(string)) {
                m8014m(string2);
            } else {
                m8013n(string2);
            }
        } else {
            m8013n("bundle is null!!!");
        }
        if (this.f18360az != null) {
            this.f18360az.mo8017q();
            return true;
        }
        return true;
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, android.webkit.WebViewClient
    @TargetApi(21)
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        if (TextUtils.isEmpty(uri) || !uri.startsWith("sinaweibo://browser/close")) {
            return false;
        }
        Bundle m8064h = C7094e.m8064h(uri);
        if (m8064h != null) {
            String string = m8064h.getString("code");
            String string2 = m8064h.getString("msg");
            if (TextUtils.isEmpty(string)) {
                m8012o("code is null!!!");
            } else if ("0".equals(string)) {
                m8014m(string2);
            } else {
                m8013n(string2);
            }
        } else {
            m8013n("bundle is null!!!");
        }
        if (this.f18360az != null) {
            this.f18360az.mo8017q();
            return true;
        }
        return true;
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b
    /* renamed from: q */
    public final void mo8010q() {
        m8012o("cancel share!!!");
        if (this.f18360az != null) {
            this.f18360az.mo8017q();
        }
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b
    /* renamed from: p */
    public final void mo8011p(String str) {
        m8013n(str);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b
    /* renamed from: s */
    public final boolean mo8009s() {
        mo8010q();
        return true;
    }
}
