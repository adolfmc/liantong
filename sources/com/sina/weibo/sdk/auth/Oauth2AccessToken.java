package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.p311b.C7092c;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Oauth2AccessToken {
    protected static final String KEY_ACCESS_TOKEN = "access_token";
    protected static final String KEY_EXPIRES_IN = "expires_in";
    protected static final String KEY_REFRESH_TOKEN = "refresh_token";
    protected static final String KEY_SCREEN_NAME = "userName";
    protected static final String KEY_UID = "uid";
    private String mAccessToken;
    private long mExpiresTime;
    private String mRefreshToken;
    private String mScreenName;
    private String mUid;

    public String getUid() {
        return this.mUid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUid(String str) {
        this.mUid = str;
    }

    public void setScreenName(String str) {
        this.mScreenName = str;
    }

    public String getScreenName() {
        return this.mScreenName;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public long getExpiresTime() {
        return this.mExpiresTime;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setExpiresTime(long j) {
        this.mExpiresTime = j;
    }

    public boolean isSessionValid() {
        return !TextUtils.isEmpty(this.mAccessToken) && this.mExpiresTime > 0;
    }

    public static Oauth2AccessToken parseAccessToken(Bundle bundle) {
        if (bundle != null) {
            try {
                Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
                oauth2AccessToken.setUid(bundle.getString("uid"));
                oauth2AccessToken.setScreenName(bundle.getString("userName"));
                oauth2AccessToken.setAccessToken(bundle.getString("access_token"));
                oauth2AccessToken.setRefreshToken(bundle.getString("refresh_token"));
                try {
                    oauth2AccessToken.setExpiresTime(Long.parseLong(bundle.getString("expires_in")) * 1000);
                } catch (Exception e) {
                    C7092c.m8071b("Oauth2AccessToken expires parse error: ", e.getMessage());
                }
                return oauth2AccessToken;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Oauth2AccessToken parseAccessToken(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
            oauth2AccessToken.setUid(jSONObject.optString("uid"));
            oauth2AccessToken.setScreenName(jSONObject.optString("userName"));
            oauth2AccessToken.setAccessToken(jSONObject.optString("access_token"));
            try {
                oauth2AccessToken.setExpiresTime(Long.parseLong(jSONObject.getString("expires_in")) * 1000);
            } catch (Exception e) {
                C7092c.m8071b("Oauth2AccessToken expires parse error: ", e.getMessage());
            }
            oauth2AccessToken.setRefreshToken(jSONObject.optString("refresh_token"));
            return oauth2AccessToken;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
