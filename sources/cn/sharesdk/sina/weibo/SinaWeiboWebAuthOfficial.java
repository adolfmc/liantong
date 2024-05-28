package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import android.widget.LinearLayout;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
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
public class SinaWeiboWebAuthOfficial extends FakeActivity {
    private String appkey;
    private IWBAPI iwbapi;
    private AuthorizeListener listener;
    private String permissions;
    private String redirectUrl;

    public SinaWeiboWebAuthOfficial(String str, String str2, String str3, AuthorizeListener authorizeListener) {
        this.appkey = str;
        this.redirectUrl = str2;
        this.permissions = str3;
        this.listener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        super.onCreate();
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onCreate ");
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a("SinaWeiboOfficialAuth onCreate exception " + e.getMessage());
        }
        try {
            AuthInfo authInfo = new AuthInfo(this.activity, this.appkey, this.redirectUrl, this.permissions);
            this.iwbapi = WBAPIFactory.createWBAPI(this.activity);
            this.iwbapi.registerApp(this.activity, authInfo, new SdkListener() { // from class: cn.sharesdk.sina.weibo.SinaWeiboWebAuthOfficial.1
                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitSuccess() {
                    SinaWeibo.initFlag = true;
                    SinaWeiboWebAuthOfficial.this.loginManager();
                }

                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitFailure(Exception exc) {
                    if (SinaWeiboWebAuthOfficial.this.listener != null) {
                        SinaWeiboWebAuthOfficial.this.listener.onError(exc);
                    }
                    SSDKLog m21740b2 = SSDKLog.m21740b();
                    m21740b2.m21744a("SinaWeiboShareOfficial", "WeiboInitFailure " + exc);
                }
            });
        } catch (Throwable th) {
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21744a("SinaWeiboWebAuthOfficial", "onCreate AuthInfo " + th);
            AuthorizeListener authorizeListener = this.listener;
            if (authorizeListener != null && authorizeListener != null) {
                authorizeListener.onError(new Throwable("SinaWeibo SDK init failed"));
            }
            finish();
        }
        try {
            if (SinaWeibo.initFlag) {
                loginManager();
            }
            SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onCreate loginManager() ");
        } catch (Throwable th2) {
            if (this.listener != null) {
                this.listener.onError(new Throwable("Authorize catch: " + th2));
            }
            SSDKLog m21740b3 = SSDKLog.m21740b();
            m21740b3.m21743a("SinaWeiboWebAuthOfficial onCreate catch: " + th2);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginManager() {
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onCreate ");
        IWBAPI iwbapi = this.iwbapi;
        if (iwbapi != null) {
            iwbapi.authorizeWeb(this.activity, new WbAuthListener() { // from class: cn.sharesdk.sina.weibo.SinaWeiboWebAuthOfficial.2
                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onComplete(Oauth2AccessToken oauth2AccessToken) {
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", oauth2AccessToken.getAccessToken());
                    bundle.putString("expires_in", String.valueOf(oauth2AccessToken.getExpiresTime()));
                    bundle.putString("refresh_token", oauth2AccessToken.getRefreshToken());
                    bundle.putString("username", oauth2AccessToken.getScreenName());
                    bundle.putString("uid", oauth2AccessToken.getUid());
                    SinaWeiboWebAuthOfficial.this.listener.onComplete(bundle);
                    SinaWeiboWebAuthOfficial.this.finish();
                }

                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onError(UiError uiError) {
                    SinaWeiboWebAuthOfficial.this.listener.onError(new Throwable("errorCode: " + uiError.errorCode + " errorMessage: " + uiError.errorMessage + " errorDetail: " + uiError.errorDetail));
                    SinaWeiboWebAuthOfficial.this.finish();
                }

                @Override // com.sina.weibo.sdk.auth.WbAuthListener
                public void onCancel() {
                    SinaWeiboWebAuthOfficial.this.listener.onCancel();
                    SinaWeiboWebAuthOfficial.this.finish();
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

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        super.onResume();
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onResume");
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        super.onPause();
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onPause");
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        super.onStop();
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onStop");
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m21740b().m21743a("SinaWeiboWebAuthOfficial onDestroy");
    }
}
