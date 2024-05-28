package cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import cn.sharesdk.framework.SSDKWebViewClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.authorize.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AuthorizeWebviewClient extends SSDKWebViewClient {
    protected WebAuthorizeActivity activity;
    public AuthorizeListener listener;
    protected String redirectUri;

    protected abstract void onComplete(String str);

    public AuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        this.activity = webAuthorizeActivity;
        AuthorizeHelper helper = webAuthorizeActivity.getHelper();
        this.redirectUri = helper.getRedirectUri();
        this.listener = helper.getAuthorizeListener();
    }

    @Override // cn.sharesdk.framework.SSDKWebViewClient, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.activity.getHelper().getAuthorizeListener();
        this.activity.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
