package cn.sharesdk.wechat.moments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXAuthHelper;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatHandler;
import cn.sharesdk.wechat.utils.WechatHelper;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatMoments extends Platform {
    public static final String NAME = "WechatMoments";

    /* renamed from: a */
    private String f3239a;

    /* renamed from: b */
    private String f3240b;

    /* renamed from: c */
    private boolean f3241c;

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getPlatformId() {
        return 23;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3239a = getDevinfo("AppId");
        this.f3240b = getDevinfo("AppSecret");
        this.f3241c = "true".equals(getDevinfo("BypassApproval"));
        String str2 = this.f3239a;
        if (str2 == null || str2.length() <= 0) {
            this.f3239a = getDevinfo("Wechat", "AppId");
            this.f3241c = "true".equals(getDevinfo("Wechat", "BypassApproval"));
            String str3 = this.f3239a;
            if (str3 != null && str3.length() > 0) {
                copyDevinfo("Wechat", NAME);
                this.f3239a = getDevinfo("AppId");
                this.f3241c = "true".equals(getDevinfo("BypassApproval"));
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3239a = getDevinfo("WechatFavorite", "AppId");
            String str4 = this.f3239a;
            if (str4 == null || str4.length() <= 0) {
                return;
            }
            copyDevinfo("WechatFavorite", NAME);
            this.f3239a = getDevinfo("AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3239a = getNetworkDevinfo("app_id", "AppId");
        this.f3240b = getNetworkDevinfo("app_secret", "AppSecret");
        String str = this.f3239a;
        if (str == null || str.length() <= 0) {
            this.f3239a = getNetworkDevinfo(22, "app_id", "AppId");
            String str2 = this.f3239a;
            if (str2 != null && str2.length() > 0) {
                copyNetworkDevinfo(22, 23);
                this.f3239a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            } else {
                this.f3239a = getNetworkDevinfo(37, "app_id", "AppId");
                String str3 = this.f3239a;
                if (str3 != null && str3.length() > 0) {
                    copyNetworkDevinfo(23, 23);
                    this.f3239a = getNetworkDevinfo("app_id", "AppId");
                    SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            }
        }
        String str4 = this.f3240b;
        if (str4 == null || str4.length() <= 0) {
            this.f3240b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            String str5 = this.f3240b;
            if (str5 != null && str5.length() > 0) {
                copyNetworkDevinfo(22, 23);
                this.f3240b = getNetworkDevinfo("app_secret", "AppSecret");
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3240b = getNetworkDevinfo(37, "app_secret", "AppSecret");
            String str6 = this.f3240b;
            if (str6 == null || str6.length() <= 0) {
                return;
            }
            copyNetworkDevinfo(23, 23);
            this.f3240b = getNetworkDevinfo("app_secret", "AppSecret");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        if (TextUtils.isEmpty(this.f3239a) || TextUtils.isEmpty(this.f3240b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3239a);
        if (!m21279a.m21247c()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
            }
        } else if (!m21279a.m21241d()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
            }
        } else {
            WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 23);
            wXAuthHelper.m21310a(this.f3239a, this.f3240b);
            WechatHandler wechatHandler = new WechatHandler(this);
            wechatHandler.m21282a(wXAuthHelper);
            wechatHandler.m21284a(new AuthorizeListener() { // from class: cn.sharesdk.wechat.moments.WechatMoments.1
                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onError(Throwable th) {
                    if (WechatMoments.this.listener != null) {
                        WechatMoments.this.listener.onError(WechatMoments.this, 1, th);
                    }
                }

                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onComplete(Bundle bundle) {
                    WechatMoments.this.afterRegister(1, null);
                }

                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onCancel() {
                    if (WechatMoments.this.listener != null) {
                        WechatMoments.this.listener.onCancel(WechatMoments.this, 1);
                    }
                }
            });
            try {
                m21279a.m21263a(wechatHandler);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 1, th);
                }
            }
        }
    }

    /* renamed from: c */
    private boolean m21326c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 23);
        wXAuthHelper.m21310a(this.f3239a, this.f3240b);
        return wXAuthHelper.m21317a();
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3239a);
        if (!m21279a.m21247c()) {
            if (this.listener != null) {
                this.listener.onError(this, i, new WechatClientNotExistException());
            }
            return false;
        } else if (!m21279a.m21241d()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
            }
            return false;
        } else if (i == 9 || isAuthValid() || m21326c()) {
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3239a);
        return m21279a.m21247c() && m21279a.m21241d();
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("WechatMoments start Share with Appid:" + this.f3239a + "appSecret:" + this.f3240b, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("WechatMoments ShareParams:");
        sb.append(shareParams.toString());
        m21740b2.m21744a(sb.toString(), new Object[0]);
        shareParams.set("scene", 1);
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3239a);
        WechatHandler wechatHandler = new WechatHandler(this);
        if (this.f3241c) {
            try {
                m21279a.m21262a(wechatHandler, shareParams, this.listener);
                return;
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                    return;
                }
                return;
            }
        }
        wechatHandler.m21285a(shareParams, this.listener);
        try {
            m21279a.m21243c(wechatHandler);
            if (this.listener == null || !m21279a.m21256b()) {
                return;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            if (this.listener != null) {
                this.listener.onComplete(this, 9, hashMap);
            }
        } catch (Throwable th2) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th2);
            }
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void userInfor(String str) {
        if (TextUtils.isEmpty(this.f3239a) || TextUtils.isEmpty(this.f3240b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 23);
        wXAuthHelper.m21310a(this.f3239a, this.f3240b);
        try {
            wXAuthHelper.m21315a(this.listener);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            if (this.listener != null) {
                this.listener.onError(this, 8, th);
            }
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public ShareEvent.C1746a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        ShareEvent.C1746a c1746a = new ShareEvent.C1746a();
        String text = shareParams.getText();
        c1746a.f2816b = text;
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        Bitmap imageData = shareParams.getImageData();
        if (!TextUtils.isEmpty(imageUrl)) {
            c1746a.f2818d.add(imageUrl);
        } else if (imagePath != null) {
            c1746a.f2819e.add(imagePath);
        } else if (imageData != null) {
            c1746a.f2820f.add(imageData);
        }
        String url = shareParams.getUrl();
        if (url != null) {
            c1746a.f2817c.add(url);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", url);
        hashMap2.put("extInfo", null);
        hashMap2.put("content", text);
        hashMap2.put("image", c1746a.f2818d);
        hashMap2.put("musicFileUrl", url);
        c1746a.f2821g = hashMap2;
        return c1746a;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return !this.f3241c;
    }
}
