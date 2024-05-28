package cn.sharesdk.wechat.favorite;

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
public class WechatFavorite extends Platform {
    public static final String NAME = "WechatFavorite";

    /* renamed from: a */
    private String f3228a;

    /* renamed from: b */
    private String f3229b;

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
        return 37;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return false;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3228a = getDevinfo("AppId");
        this.f3229b = getDevinfo("AppSecret");
        String str2 = this.f3228a;
        if (str2 == null || str2.length() <= 0) {
            this.f3228a = getDevinfo("Wechat", "AppId");
            String str3 = this.f3228a;
            if (str3 != null && str3.length() > 0) {
                copyDevinfo("Wechat", NAME);
                this.f3228a = getDevinfo("AppId");
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3228a = getDevinfo("WechatMoments", "AppId");
            String str4 = this.f3228a;
            if (str4 == null || str4.length() <= 0) {
                return;
            }
            copyDevinfo("WechatMoments", NAME);
            this.f3228a = getDevinfo("AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3228a = getNetworkDevinfo("app_id", "AppId");
        this.f3229b = getNetworkDevinfo("app_secret", "AppSecret");
        String str = this.f3228a;
        if (str == null || str.length() <= 0) {
            this.f3228a = getNetworkDevinfo(22, "app_id", "AppId");
            String str2 = this.f3228a;
            if (str2 != null && str2.length() > 0) {
                copyNetworkDevinfo(22, 37);
                this.f3228a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            } else {
                this.f3228a = getNetworkDevinfo(23, "app_id", "AppId");
                String str3 = this.f3228a;
                if (str3 != null && str3.length() > 0) {
                    copyNetworkDevinfo(23, 37);
                    this.f3228a = getNetworkDevinfo("app_id", "AppId");
                    SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            }
        }
        String str4 = this.f3229b;
        if (str4 == null || str4.length() <= 0) {
            this.f3229b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            String str5 = this.f3229b;
            if (str5 != null && str5.length() > 0) {
                copyNetworkDevinfo(22, 37);
                this.f3229b = getNetworkDevinfo("app_secret", "AppSecret");
                SSDKLog.m21740b().m21744a("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
                return;
            }
            this.f3229b = getNetworkDevinfo(23, "app_secret", "AppSecret");
            String str6 = this.f3229b;
            if (str6 == null || str6.length() <= 0) {
                return;
            }
            copyNetworkDevinfo(23, 37);
            this.f3229b = getNetworkDevinfo("app_secret", "AppSecret");
            SSDKLog.m21740b().m21744a("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        if (TextUtils.isEmpty(this.f3228a) || TextUtils.isEmpty(this.f3229b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3228a);
        if (!m21279a.m21247c()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
            }
        } else if (!m21279a.m21241d()) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
            }
        } else {
            WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 37);
            wXAuthHelper.m21310a(this.f3228a, this.f3229b);
            WechatHandler wechatHandler = new WechatHandler(this);
            wechatHandler.m21282a(wXAuthHelper);
            wechatHandler.m21284a(new AuthorizeListener() { // from class: cn.sharesdk.wechat.favorite.WechatFavorite.1
                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onError(Throwable th) {
                    if (WechatFavorite.this.listener != null) {
                        WechatFavorite.this.listener.onError(WechatFavorite.this, 1, th);
                    }
                }

                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onComplete(Bundle bundle) {
                    WechatFavorite.this.afterRegister(1, null);
                }

                @Override // cn.sharesdk.framework.authorize.AuthorizeListener
                public void onCancel() {
                    if (WechatFavorite.this.listener != null) {
                        WechatFavorite.this.listener.onCancel(WechatFavorite.this, 1);
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
    private boolean m21338c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 37);
        wXAuthHelper.m21310a(this.f3228a, this.f3229b);
        return wXAuthHelper.m21317a();
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3228a);
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
        } else if (i == 9 || isAuthValid() || m21338c()) {
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3228a);
        return m21279a.m21247c();
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("WechatFavorite start Share with Appid:" + this.f3228a + "appSecret:" + this.f3229b, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("WechatFavorite ShareParams:");
        sb.append(shareParams.toString());
        m21740b2.m21744a(sb.toString(), new Object[0]);
        shareParams.set("scene", 2);
        WechatHelper m21279a = WechatHelper.m21279a();
        m21279a.m21242c(this.f3228a);
        WechatHandler wechatHandler = new WechatHandler(this);
        wechatHandler.m21285a(shareParams, this.listener);
        try {
            m21279a.m21243c(wechatHandler);
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
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
        if (TextUtils.isEmpty(this.f3228a) || TextUtils.isEmpty(this.f3229b)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
                return;
            }
            return;
        }
        WXAuthHelper wXAuthHelper = new WXAuthHelper(this, 37);
        wXAuthHelper.m21310a(this.f3228a, this.f3229b);
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
}
