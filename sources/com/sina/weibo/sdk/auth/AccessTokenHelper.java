package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sina.weibo.sdk.net.InterfaceC7099c;
import com.sina.weibo.sdk.p310a.C7064b;
import com.sina.weibo.sdk.p310a.C7076e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AccessTokenHelper {
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    public static void writeAccessToken(Context context, Oauth2AccessToken oauth2AccessToken) {
        if (context == null || oauth2AccessToken == null || TextUtils.isEmpty(oauth2AccessToken.getAccessToken())) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("com_weibo_sdk_android", 0).edit();
        edit.putString("uid", oauth2AccessToken.getUid());
        edit.putString("userName", oauth2AccessToken.getScreenName());
        edit.putString("access_token", oauth2AccessToken.getAccessToken());
        edit.putString("refresh_token", oauth2AccessToken.getRefreshToken());
        edit.putLong("expires_in", oauth2AccessToken.getExpiresTime());
        edit.apply();
    }

    public static Oauth2AccessToken readAccessToken(Context context) {
        if (context == null) {
            return null;
        }
        Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com_weibo_sdk_android", 0);
        oauth2AccessToken.setUid(sharedPreferences.getString("uid", ""));
        oauth2AccessToken.setScreenName(sharedPreferences.getString("userName", ""));
        oauth2AccessToken.setAccessToken(sharedPreferences.getString("access_token", ""));
        oauth2AccessToken.setRefreshToken(sharedPreferences.getString("refresh_token", ""));
        oauth2AccessToken.setExpiresTime(sharedPreferences.getLong("expires_in", 0L));
        return oauth2AccessToken;
    }

    public static void refreshAccessToken(final Context context, String str) {
        C7064b c7064b;
        Oauth2AccessToken readAccessToken = readAccessToken(context);
        if (readAccessToken != null) {
            C7076e c7076e = new C7076e(str, readAccessToken, new InterfaceC7099c<String>() { // from class: com.sina.weibo.sdk.auth.AccessTokenHelper.1
                @Override // com.sina.weibo.sdk.net.InterfaceC7099c
                public final void onError(Throwable th) {
                }

                @Override // com.sina.weibo.sdk.net.InterfaceC7099c
                /* renamed from: a */
                public final /* synthetic */ void mo8003a(String str2) {
                    AccessTokenHelper.writeAccessToken(context, Oauth2AccessToken.parseAccessToken(str2));
                }
            });
            c7064b = C7064b.C7065a.f18270K;
            c7064b.m8102a(c7076e);
        }
    }

    public static void clearAccessToken(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("com_weibo_sdk_android", 0).edit();
        edit.clear();
        edit.apply();
    }
}
