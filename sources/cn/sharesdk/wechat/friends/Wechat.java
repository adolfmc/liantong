package cn.sharesdk.wechat.friends;

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
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Wechat extends Platform {
    public static final String NAME = "Wechat";

    /* renamed from: a */
    private String f3231a;

    /* renamed from: b */
    private String f3232b;

    /* renamed from: c */
    private boolean f3233c;

    /* renamed from: d */
    private String f3234d;

    /* renamed from: e */
    private String f3235e;

    /* renamed from: f */
    private boolean f3236f;

    /* renamed from: g */
    private int f3237g;

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
        return 22;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3231a = getDevinfo("AppId");
        this.f3232b = getDevinfo("AppSecret");
        this.f3233c = "true".equals(getDevinfo("BypassApproval"));
        this.f3234d = getDevinfo(TextUtils.isEmpty(getDevinfo("UserName")) ? "userName" : "UserName");
        this.f3235e = getDevinfo(TextUtils.isEmpty(getDevinfo("Path")) ? "path" : "Path");
        this.f3236f = "true".equals(getDevinfo("WithShareTicket"));
        try {
            this.f3237g = Integer.valueOf(getDevinfo("MiniprogramType")).intValue();
        } catch (Throwable unused) {
            this.f3237g = 0;
        }
        String str2 = this.f3231a;
        if (str2 == null || str2.length() <= 0) {
            this.f3231a = getDevinfo("WechatMoments", "AppId");
            this.f3233c = "true".equals(getDevinfo("WechatMoments", "BypassApproval"));
            String str3 = this.f3231a;
            if (str3 != null && str3.length() > 0) {
                copyDevinfo("WechatMoments", NAME);
                this.f3231a = getDevinfo("AppId");
                this.f3233c = "true".equals(getDevinfo("BypassApproval"));
                SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3231a = getDevinfo("WechatFavorite", "AppId");
            String str4 = this.f3231a;
            if (str4 == null || str4.length() <= 0) {
                return;
            }
            copyDevinfo("WechatFavorite", NAME);
            this.f3231a = getDevinfo("AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3231a = getNetworkDevinfo("app_id", "AppId");
        this.f3232b = getNetworkDevinfo("app_secret", "AppSecret");
        String str = this.f3231a;
        if (str == null || str.length() <= 0) {
            this.f3231a = getNetworkDevinfo(23, "app_id", "AppId");
            String str2 = this.f3231a;
            if (str2 != null && str2.length() > 0) {
                copyNetworkDevinfo(23, 22);
                this.f3231a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
            } else {
                this.f3231a = getNetworkDevinfo(37, "app_id", "AppId");
                String str3 = this.f3231a;
                if (str3 != null && str3.length() > 0) {
                    copyNetworkDevinfo(37, 22);
                    this.f3231a = getNetworkDevinfo("app_id", "AppId");
                    SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            }
        }
        String str4 = this.f3232b;
        if (str4 == null || str4.length() <= 0) {
            this.f3232b = getNetworkDevinfo(23, "app_secret", "AppSecret");
            String str5 = this.f3232b;
            if (str5 != null && str5.length() > 0) {
                copyNetworkDevinfo(23, 22);
                this.f3232b = getNetworkDevinfo("app_secret", "AppSecret");
                SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3232b = getNetworkDevinfo(37, "app_secret", "AppSecret");
            String str6 = this.f3232b;
            if (str6 == null || str6.length() <= 0) {
                return;
            }
            copyNetworkDevinfo(37, 22);
            this.f3232b = getNetworkDevinfo("app_secret", "AppSecret");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void subscribeAuth(Platform.ShareParams shareParams) {
        if (TextUtils.isEmpty(this.f3231a) || TextUtils.isEmpty(this.f3232b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3231a);
        if (!m21279a.m21247c()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
                return;
            }
            return;
        }
        WechatHandler wechatHandler = new WechatHandler(this);
        wechatHandler.m21285a(shareParams, this.listener);
        try {
            m21279a.m21249b(wechatHandler);
            if (this.listener != null) {
                this.listener.onComplete(this, 9, null);
            }
            SSDKLog.m21740b().m21744a("ShareSDK", "subscribeAuth start on Wechat");
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 1, th);
            }
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        if (TextUtils.isEmpty(this.f3231a) || TextUtils.isEmpty(this.f3232b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3231a);
        if (!m21279a.m21247c()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
                return;
            }
            return;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 22);
        wXAuthHelper.m21310a(this.f3231a, this.f3232b);
        WechatHandler wechatHandler = new WechatHandler(this);
        wechatHandler.m21282a(wXAuthHelper);
        wechatHandler.m21284a(new AuthorizeListener() { // from class: cn.sharesdk.wechat.friends.Wechat.1
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                if (Wechat.this.listener != null) {
                    Wechat.this.listener.onError(Wechat.this, 1, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                Wechat.this.afterRegister(1, null);
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                if (Wechat.this.listener != null) {
                    Wechat.this.listener.onCancel(Wechat.this, 1);
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

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3231a);
        return m21279a.m21247c();
    }

    /* renamed from: c */
    private boolean m21332c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 22);
        wXAuthHelper.m21310a(this.f3231a, this.f3232b);
        return wXAuthHelper.m21317a();
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        if (i == 9 || isAuthValid() || m21332c()) {
            return true;
        }
        if (!TextUtils.isEmpty(getDb().get("refresh_token"))) {
            try {
                WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 22);
                wXAuthHelper.m21310a(this.f3231a, this.f3232b);
                if (wXAuthHelper.m21317a()) {
                    return true;
                }
            } catch (Exception e) {
                SSDKLog.m21740b().m21742a(e);
            }
        }
        innerAuthorize(i, obj);
        return false;
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("Wechat start Share with Appid:" + this.f3231a + "appSecret:" + this.f3232b, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("Wechat ShareParams:");
        sb.append(shareParams.toString());
        m21740b2.m21744a(sb.toString(), new Object[0]);
        if (!isClientValid() && this.listener != null) {
            this.listener.onError(this, 9, new WechatClientNotExistException());
        }
        shareParams.set("scene", 0);
        WechatHelper m21279a = WechatHelper.m21279a();
        this.f3235e = TextUtils.isEmpty(shareParams.getWxPath()) ? this.f3235e : shareParams.getWxPath();
        this.f3234d = TextUtils.isEmpty(shareParams.getWxUserName()) ? this.f3234d : shareParams.getWxUserName();
        this.f3236f = !shareParams.toMap().containsKey("wxWithShareTicket") ? this.f3236f : shareParams.getWxWithShareTicket();
        this.f3237g = !shareParams.toMap().containsKey("wxMiniProgramType") ? this.f3237g : shareParams.getWxMiniProgramType();
        m21279a.m21260a(this.f3235e);
        m21279a.m21248b(this.f3234d);
        m21279a.m21257a(this.f3236f);
        m21279a.m21278a(this.f3237g);
        m21279a.m21242c(this.f3231a);
        WechatHandler wechatHandler = new WechatHandler(this);
        if (this.f3233c) {
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
        if (TextUtils.isEmpty(this.f3231a) || TextUtils.isEmpty(this.f3232b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 22);
        wXAuthHelper.m21310a(this.f3231a, this.f3232b);
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
        return !this.f3233c;
    }
}
