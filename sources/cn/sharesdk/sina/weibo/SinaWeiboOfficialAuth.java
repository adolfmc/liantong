package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.SdkListener;
import com.sina.weibo.sdk.openapi.WBAPIFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaWeiboOfficialAuth extends FakeActivity {
    private String appkey;
    private IWBAPI iwbapi;
    private AuthorizeListener listener;
    private String permissions;
    private String redirectUrl;

    public SinaWeiboOfficialAuth(String str, String str2, String str3, AuthorizeListener authorizeListener) {
        this.appkey = str;
        this.redirectUrl = str2;
        this.permissions = str3;
        this.listener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onCreate ");
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a("SinaWeiboOfficialAuth onCreate exception " + e.getMessage());
            AuthorizeListener authorizeListener = this.listener;
            if (authorizeListener != null && authorizeListener != null) {
                authorizeListener.onError(new Throwable("SinaWeibo SDK init failed"));
            }
            finish();
        }
        try {
            AuthInfo authInfo = new AuthInfo(this.activity, this.appkey, this.redirectUrl, this.permissions);
            this.iwbapi = WBAPIFactory.createWBAPI(this.activity);
            this.iwbapi.registerApp(this.activity, authInfo, new SdkListener() { // from class: cn.sharesdk.sina.weibo.SinaWeiboOfficialAuth.1
                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitSuccess() {
                    SinaWeibo.initFlag = true;
                    SinaWeiboOfficialAuth.this.loginManager();
                }

                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitFailure(Exception exc) {
                    if (SinaWeiboOfficialAuth.this.listener != null) {
                        SinaWeiboOfficialAuth.this.listener.onError(exc);
                    }
                    SSDKLog m21740b2 = SSDKLog.m21740b();
                    m21740b2.m21744a("SinaWeiboShareOfficial", "WeiboInitFailure " + exc);
                }
            });
        } catch (Throwable th) {
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21744a("SinaWeiboOfficialAuth", "onCreate AuthInfo " + th);
        }
        try {
            if (SinaWeibo.initFlag) {
                loginManager();
            }
            SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onCreate loginManager() ");
        } catch (Throwable th2) {
            if (this.listener != null) {
                this.listener.onError(new Throwable("Authorize catch: " + th2));
            }
            SSDKLog m21740b3 = SSDKLog.m21740b();
            m21740b3.m21743a("SinaWeiboOfficialAuth onCreate catch: " + th2);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginManager() {
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth loginManager");
        IWBAPI iwbapi = this.iwbapi;
        if (iwbapi != null) {
            iwbapi.authorizeClient(this.activity, new WbAuthListener() { // from class: cn.sharesdk.sina.weibo.SinaWeiboOfficialAuth.2
                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onComplete(Oauth2AccessToken oauth2AccessToken) {
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", oauth2AccessToken.getAccessToken());
                    bundle.putString("expires_in", String.valueOf(oauth2AccessToken.getExpiresTime()));
                    bundle.putString("refresh_token", oauth2AccessToken.getRefreshToken());
                    bundle.putString("username", oauth2AccessToken.getScreenName());
                    bundle.putString("uid", oauth2AccessToken.getUid());
                    SinaWeiboOfficialAuth.this.listener.onComplete(bundle);
                    SinaWeiboOfficialAuth.this.finish();
                }

                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onError(UiError uiError) {
                    SinaWeiboOfficialAuth sinaWeiboOfficialAuth = SinaWeiboOfficialAuth.this;
                    sinaWeiboOfficialAuth.showWebAuthActivity(sinaWeiboOfficialAuth.listener);
                    SinaWeiboOfficialAuth.this.finish();
                }

                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onCancel() {
                    SinaWeiboOfficialAuth.this.listener.onCancel();
                    SinaWeiboOfficialAuth.this.finish();
                }
            });
            return;
        }
        AuthorizeListener authorizeListener = this.listener;
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable("SinaWeibo SDK init failed"));
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWebAuthActivity(AuthorizeListener authorizeListener) {
        new SinaWeiboWebAuthOfficial(this.appkey, this.redirectUrl, this.permissions, authorizeListener).show(MobSDK.getContext(), null);
        SSDKLog.m21740b().m21743a("SinaWeibo SDK Web showWebAuthActivity ");
    }

    private String join(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return join(objArr, str, 0, objArr.length);
    }

    private String join(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i3 * 16);
        if (objArr[i] != null) {
            sb.append(objArr[i]);
        }
        while (true) {
            i++;
            if (i < i2) {
                sb.append(str);
                if (objArr[i] != null) {
                    sb.append(objArr[i]);
                }
            } else {
                return sb.toString();
            }
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IWBAPI iwbapi = this.iwbapi;
        if (iwbapi != null) {
            iwbapi.authorizeCallback(this.activity, i, i2, intent);
        }
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onActivityResult");
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        super.onResume();
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onResume");
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        super.onPause();
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onPause");
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        super.onStop();
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onStop");
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m21740b().m21743a("SinaWeiboOfficialAuth onDestroy");
    }
}
