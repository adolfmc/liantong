package com.sina.weibo.sdk.web.p312a;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.AccessTokenHelper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.p311b.C7094e;
import com.sina.weibo.sdk.web.InterfaceC7119a;
import com.sina.weibo.sdk.web.p313b.AbstractC7125b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.web.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7120a extends AbstractC7121b {
    public C7120a(Activity activity, InterfaceC7119a interfaceC7119a, AbstractC7125b abstractC7125b) {
        super(activity, interfaceC7119a, abstractC7125b);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        if (Build.VERSION.SDK_INT >= 21) {
            return m8016l(webResourceRequest.getUrl().toString());
        }
        return false;
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return m8016l(str);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        AuthInfo m8020a = this.f18356aA.m8008u().m8020a();
        if (m8020a == null || !str.startsWith(m8020a.getRedirectUrl())) {
            return;
        }
        String m8019r = this.f18356aA.m8008u().m8019r();
        if (!TextUtils.isEmpty(m8019r)) {
            this.f18357aB = this.f18358ax.m8092a(m8019r);
            if (this.f18357aB != null) {
                Bundle m8065g = C7094e.m8065g(str);
                if (m8065g != null) {
                    String string = m8065g.getString("error");
                    String string2 = m8065g.getString("error_code");
                    String string3 = m8065g.getString("error_description");
                    if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
                        Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(m8065g);
                        AccessTokenHelper.writeAccessToken(this.f18359ay, parseAccessToken);
                        this.f18357aB.onComplete(parseAccessToken);
                    } else {
                        this.f18357aB.onError(new UiError(-1, string2, string3));
                    }
                } else {
                    this.f18357aB.onError(new UiError(-1, "bundle is null", "parse url error"));
                }
                this.f18358ax.m8089b(m8019r);
            }
        }
        if (this.f18360az != null) {
            this.f18360az.mo8017q();
        }
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b
    /* renamed from: q */
    public final void mo8010q() {
        super.mo8010q();
        String m8019r = this.f18356aA.m8008u().m8019r();
        if (!TextUtils.isEmpty(m8019r)) {
            this.f18357aB = this.f18358ax.m8092a(m8019r);
            if (this.f18357aB != null) {
                this.f18357aB.onCancel();
            }
            this.f18358ax.m8089b(m8019r);
        }
        if (this.f18360az != null) {
            this.f18360az.mo8017q();
        }
    }

    /* renamed from: l */
    private boolean m8016l(String str) {
        Bundle m8065g;
        AuthInfo m8020a = this.f18356aA.m8008u().m8020a();
        return (m8020a == null || !str.startsWith(m8020a.getRedirectUrl()) || (m8065g = C7094e.m8065g(str)) == null || TextUtils.isEmpty(m8065g.getString("access_token"))) ? false : true;
    }

    @Override // com.sina.weibo.sdk.web.p312a.AbstractC7121b
    /* renamed from: s */
    public final boolean mo8009s() {
        mo8010q();
        return true;
    }
}
