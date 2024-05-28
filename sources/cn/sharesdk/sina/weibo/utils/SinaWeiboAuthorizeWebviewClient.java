package cn.sharesdk.sina.weibo.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.AuthorizeWebviewClient;
import cn.sharesdk.framework.authorize.WebAuthorizeActivity;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.Weibo;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.sina.weibo.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaWeiboAuthorizeWebviewClient extends AuthorizeWebviewClient {

    /* renamed from: a */
    private boolean f3026a;

    /* renamed from: a */
    static /* synthetic */ AuthorizeListener m21602a(SinaWeiboAuthorizeWebviewClient sinaWeiboAuthorizeWebviewClient) {
        return sinaWeiboAuthorizeWebviewClient.listener;
    }

    public SinaWeiboAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        super(webAuthorizeActivity);
    }

    @Override // cn.sharesdk.framework.SSDKWebViewClient, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!TextUtils.isEmpty(this.redirectUri) && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
            return true;
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                try {
                    Intent m21601a = m21601a(substring);
                    m21601a.setPackage("com.android.mms");
                    webView.getContext().startActivity(m21601a);
                } catch (Throwable th) {
                    if (this.listener != null) {
                        this.listener.onError(th);
                    }
                }
            } catch (Throwable unused) {
                webView.getContext().startActivity(m21601a(substring));
            }
            return true;
        } else {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    @Override // cn.sharesdk.framework.SSDKWebViewClient, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.redirectUri) && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                try {
                    Intent m21601a = m21601a(substring);
                    m21601a.setPackage("com.android.mms");
                    webView.getContext().startActivity(m21601a);
                } catch (Throwable th) {
                    if (this.listener != null) {
                        this.listener.onError(th);
                    }
                }
            } catch (Throwable unused) {
                webView.getContext().startActivity(m21601a(substring));
            }
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeWebviewClient
    public void onComplete(String str) {
        if (this.f3026a) {
            return;
        }
        this.f3026a = true;
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        String string = urlToBundle.getString("error");
        String string2 = urlToBundle.getString("error_code");
        if (this.listener != null) {
            if (string == null && string2 == null) {
                String string3 = urlToBundle.getString("code");
                if (TextUtils.isEmpty(string3)) {
                    this.listener.onError(new Throwable("Authorize code is empty"));
                }
                m21603a(this.activity.getHelper().getPlatform(), string3);
            } else if (string.equals("access_denied")) {
                this.listener.onCancel();
            } else {
                int i = 0;
                try {
                    i = ResHelper.parseInt(string2);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
                this.listener.onError(new Throwable(string + " (" + i + ")"));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.sina.weibo.utils.a$1] */
    /* renamed from: a */
    private void m21603a(final Platform platform, final String str) {
        new Thread() { // from class: cn.sharesdk.sina.weibo.utils.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    String m21634b = Weibo.m21649a(platform).m21634b(str);
                    if (m21634b == null) {
                        SinaWeiboAuthorizeWebviewClient.this.listener.onError(new Throwable("Authorize token is empty"));
                        return;
                    }
                    HashMap fromJson = new Hashon().fromJson(m21634b);
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", String.valueOf(fromJson.get("uid")));
                    bundle.putString("remind_in", String.valueOf(fromJson.get("remind_in")));
                    bundle.putString("expires_in", String.valueOf(fromJson.get("expires_in")));
                    bundle.putString("access_token", String.valueOf(fromJson.get("access_token")));
                    SinaWeiboAuthorizeWebviewClient.this.listener.onComplete(bundle);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    private Intent m21601a(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", "");
        intent.setFlags(268435456);
        return intent;
    }
}
