package com.sina.weibo.sdk.openapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.C7061a;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenHelper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.C7086a;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.p311b.C7089a;
import com.sina.weibo.sdk.p311b.C7092c;
import com.sina.weibo.sdk.share.C7113e;
import com.sina.weibo.sdk.share.ShareTransActivity;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.web.p313b.C7128d;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.openapi.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7106a implements IWBAPI {
    private Context mContext;

    /* renamed from: r */
    private C7086a f18327r = new C7086a();

    /* renamed from: s */
    private C7113e f18328s = new C7113e();

    public C7106a(Context context) {
        this.mContext = context;
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void setLoggerEnable(boolean z) {
        C7092c.setLoggerEnable(z);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void registerApp(Context context, AuthInfo authInfo) {
        registerApp(context, authInfo, null);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void registerApp(Context context, AuthInfo authInfo, SdkListener sdkListener) {
        C7061a.m8105a(authInfo, sdkListener);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final boolean isWBAppInstalled() {
        return C7061a.m8106a(this.mContext);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final boolean isWBAppSupportMultipleImage() {
        return C7061a.m8104b(this.mContext);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void authorize(Activity activity, WbAuthListener wbAuthListener) {
        C7086a c7086a = this.f18327r;
        C7092c.m8072a("WBSsoTag", "authorize()");
        if (wbAuthListener == null) {
            throw new RuntimeException("listener can not be null.");
        }
        c7086a.f18300d = wbAuthListener;
        if (C7061a.m8106a(activity)) {
            if (C7089a.m8083e(activity) != null) {
                c7086a.m8094a(activity);
                return;
            }
        }
        c7086a.m8093b(activity);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void authorizeClient(Activity activity, WbAuthListener wbAuthListener) {
        C7086a c7086a = this.f18327r;
        C7092c.m8072a("WBSsoTag", "authorizeClient()");
        if (wbAuthListener == null) {
            throw new RuntimeException("listener can not be null.");
        }
        c7086a.f18300d = wbAuthListener;
        c7086a.m8094a(activity);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void authorizeWeb(Activity activity, WbAuthListener wbAuthListener) {
        C7086a c7086a = this.f18327r;
        C7092c.m8072a("WBSsoTag", "authorizeWeb()");
        if (wbAuthListener == null) {
            throw new RuntimeException("listener can not be null.");
        }
        c7086a.f18300d = wbAuthListener;
        c7086a.m8093b(activity);
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void authorizeCallback(Activity activity, int i, int i2, Intent intent) {
        C7086a c7086a = this.f18327r;
        C7092c.m8072a("WBSsoTag", "authorizeCallback()");
        if (c7086a.f18300d != null) {
            if (32973 != i) {
                c7086a.f18300d.onError(new UiError(-7, "request code is error", "requestCode is error"));
            } else if (i2 != -1) {
                if (i2 == 0) {
                    c7086a.f18300d.onCancel();
                } else {
                    c7086a.f18300d.onError(new UiError(-6, "result code is error", "result code is error"));
                }
            } else if (intent != null) {
                String stringExtra = intent.getStringExtra("error");
                String stringExtra2 = intent.getStringExtra("error_type");
                String stringExtra3 = intent.getStringExtra("error_description");
                if (TextUtils.isEmpty(stringExtra) && TextUtils.isEmpty(stringExtra2) && TextUtils.isEmpty(stringExtra3)) {
                    Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(intent.getExtras());
                    if (parseAccessToken != null) {
                        AccessTokenHelper.writeAccessToken(activity, parseAccessToken);
                        c7086a.f18300d.onComplete(parseAccessToken);
                        return;
                    }
                    c7086a.f18300d.onError(new UiError(-4, "oauth2AccessToken is null", "oauth2AccessToken is null"));
                } else if ("access_denied".equals(stringExtra) || "OAuthAccessDeniedException".equals(stringExtra)) {
                    c7086a.f18300d.onCancel();
                } else {
                    c7086a.f18300d.onError(new UiError(-5, stringExtra2, stringExtra3));
                }
            }
        }
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void shareMessage(Activity activity, WeiboMultiMessage weiboMultiMessage, boolean z) {
        AuthInfo m8107a;
        C7113e c7113e = this.f18328s;
        if (activity != null) {
            if (C7061a.m8106a(activity) || !z) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - c7113e.f18340D >= 5000) {
                    c7113e.f18340D = currentTimeMillis;
                    if (z) {
                        C7113e.m8032a(activity, weiboMultiMessage);
                        return;
                    }
                    C7089a.C7090a m8083e = C7089a.m8083e(activity);
                    if (C7061a.m8106a(activity) && m8083e != null) {
                        C7089a.C7090a m8083e2 = C7089a.m8083e(activity);
                        boolean z2 = false;
                        if (m8083e2 != null && m8083e2.f18304ah > 10000) {
                            z2 = true;
                        }
                        if (z2) {
                            C7113e.m8032a(activity, weiboMultiMessage);
                            return;
                        }
                    }
                    if (activity == null || (m8107a = C7061a.m8107a()) == null) {
                        return;
                    }
                    C7128d c7128d = new C7128d(m8107a);
                    c7128d.setContext(activity);
                    c7128d.f18364aE = weiboMultiMessage;
                    c7128d.packageName = activity.getPackageName();
                    Oauth2AccessToken readAccessToken = AccessTokenHelper.readAccessToken(activity);
                    if (readAccessToken != null) {
                        String accessToken = readAccessToken.getAccessToken();
                        if (!TextUtils.isEmpty(readAccessToken.getAccessToken())) {
                            c7128d.f18367ae = accessToken;
                        }
                    }
                    Bundle bundle = new Bundle();
                    c7128d.writeToBundle(bundle);
                    Intent intent = new Intent(activity, ShareTransActivity.class);
                    intent.putExtra("start_flag", 1001);
                    intent.putExtra("start_web_activity", "com.sina.weibo.sdk.web.WebActivity");
                    intent.putExtras(bundle);
                    activity.startActivityForResult(intent, 10001);
                }
            }
        }
    }

    @Override // com.sina.weibo.sdk.openapi.IWBAPI
    public final void doResultIntent(Intent intent, WbShareCallback wbShareCallback) {
        Bundle extras;
        if (intent == null || wbShareCallback == null || (extras = intent.getExtras()) == null) {
            return;
        }
        try {
            int i = extras.getInt("_weibo_resp_errcode", -1);
            switch (i) {
                case 0:
                    wbShareCallback.onComplete();
                    return;
                case 1:
                    wbShareCallback.onCancel();
                    return;
                case 2:
                    wbShareCallback.onError(new UiError(i, extras.getString("_weibo_resp_errstr"), "error from weibo client!"));
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            wbShareCallback.onError(new UiError(-1, e.getMessage(), e.getMessage()));
        }
    }
}
