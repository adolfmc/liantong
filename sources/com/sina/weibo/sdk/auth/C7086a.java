package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.C7061a;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.net.C7105h;
import com.sina.weibo.sdk.p311b.C7089a;
import com.sina.weibo.sdk.p311b.C7092c;
import com.sina.weibo.sdk.web.WebActivity;
import com.sina.weibo.sdk.web.p313b.C7124a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.auth.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7086a {

    /* renamed from: d */
    public WbAuthListener f18300d;

    /* renamed from: a */
    public final void m8094a(Activity activity) {
        C7092c.m8072a("WBSsoTag", "startClientAuth()");
        try {
            C7089a.C7090a m8083e = C7089a.m8083e(activity);
            Intent intent = new Intent();
            if (m8083e == null) {
                intent.setClassName("com.sina.weibo", "com.sina.weibo.SSOActivity");
            } else {
                intent.setClassName(m8083e.packageName, m8083e.f18303ag);
            }
            AuthInfo m8107a = C7061a.m8107a();
            intent.putExtra("appKey", m8107a.getAppKey());
            intent.putExtra("redirectUri", m8107a.getRedirectUrl());
            intent.putExtra("scope", m8107a.getScope());
            intent.putExtra("packagename", m8107a.getPackageName());
            intent.putExtra("key_hash", m8107a.getHash());
            intent.putExtra("_weibo_command_type", 3);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            intent.putExtra("_weibo_transaction", sb.toString());
            if (activity == null) {
                this.f18300d.onError(new UiError(-1, "activity is null", ""));
            } else if (C7089a.m8087a(activity, intent)) {
                activity.startActivityForResult(intent, 32973);
                C7092c.m8072a("WBSsoTag", "start SsoActivity ");
            } else {
                this.f18300d.onError(new UiError(-2, "your app is illegal", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            C7092c.m8071b("WBSsoTag", e.getMessage());
            this.f18300d.onError(new UiError(-3, "occur exception", e.getMessage()));
        }
    }

    /* renamed from: b */
    public final void m8093b(Activity activity) {
        C7105h c7105h = new C7105h();
        AuthInfo m8107a = C7061a.m8107a();
        if (m8107a == null) {
            return;
        }
        c7105h.put("client_id", m8107a.getAppKey());
        c7105h.put("redirect_uri", m8107a.getRedirectUrl());
        c7105h.put("scope", m8107a.getScope());
        c7105h.put("packagename", m8107a.getPackageName());
        c7105h.put("key_hash", m8107a.getHash());
        c7105h.put("response_type", "code");
        c7105h.put("version", "0041005000");
        c7105h.put("luicode", "10000360");
        c7105h.put("lfid", "OP_" + m8107a.getAppKey());
        Oauth2AccessToken readAccessToken = AccessTokenHelper.readAccessToken(activity);
        if (readAccessToken != null) {
            String accessToken = readAccessToken.getAccessToken();
            if (!TextUtils.isEmpty(readAccessToken.getAccessToken())) {
                c7105h.put("trans_token", accessToken);
                c7105h.put("trans_access_token", accessToken);
            }
        }
        String str = "https://open.weibo.cn/oauth2/authorize?" + c7105h.m8049g();
        if (this.f18300d != null) {
            C7087b m8090b = C7087b.m8090b();
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            String sb2 = sb.toString();
            m8090b.m8091a(sb2, this.f18300d);
            Intent intent = new Intent(activity, WebActivity.class);
            C7124a c7124a = new C7124a(m8107a, str, sb2);
            Bundle bundle = new Bundle();
            c7124a.writeToBundle(bundle);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }
}
