package cn.sharesdk.tencent.p099qq;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.tencent.p099qq.utils.QQHelper;
import com.mob.MobSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qq.QQ */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1849QQ extends Platform {
    public static final String NAME = "QQ";

    /* renamed from: a */
    private String f3154a;

    /* renamed from: b */
    private boolean f3155b = true;

    /* renamed from: c */
    private boolean f3156c;

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
        return 24;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 2;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3154a = getDevinfo("AppId");
        this.f3156c = "true".equals(getDevinfo("BypassApproval"));
        String str2 = this.f3154a;
        if (str2 == null || str2.length() <= 0) {
            this.f3154a = getDevinfo("QZone", "AppId");
            String str3 = this.f3154a;
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            copyDevinfo("QZone", NAME);
            this.f3154a = getDevinfo("AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3154a = getNetworkDevinfo("app_id", "AppId");
        String str = this.f3154a;
        if (str == null || str.length() <= 0) {
            this.f3154a = getNetworkDevinfo(6, "app_id", "AppId");
            String str2 = this.f3154a;
            if (str2 == null || str2.length() <= 0) {
                return;
            }
            copyNetworkDevinfo(6, 24);
            this.f3154a = getNetworkDevinfo("app_id", "AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        QQHelper m21426a = QQHelper.m21426a(this);
        m21426a.m21421a(this.f3154a);
        return m21426a.m21417b();
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        SSDKLog.m21740b().m21744a("ShareSDK", "QQ doAuthorize ");
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("QQ start Authorize with Appid:" + this.f3154a, new Object[0]);
        final QQHelper m21426a = QQHelper.m21426a(this);
        m21426a.m21421a(this.f3154a);
        m21426a.m21418a(strArr);
        m21426a.m21424a(new AuthorizeListener() { // from class: cn.sharesdk.tencent.qq.QQ.1
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                if (C1849QQ.this.listener != null) {
                    C1849QQ.this.listener.onError(C1849QQ.this, 1, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                C1849QQ.this.f2731db.putToken(string2);
                C1849QQ.this.f2731db.putTokenSecret("");
                try {
                    C1849QQ.this.f2731db.putExpiresIn(ResHelper.parseLong(string3));
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21737b(th);
                }
                C1849QQ.this.f2731db.putUserId(string);
                String string4 = bundle.getString("pf");
                String string5 = bundle.getString("pfkey");
                String string6 = bundle.getString("pay_token");
                C1849QQ.this.f2731db.put("pf", string4);
                C1849QQ.this.f2731db.put("pfkey", string5);
                C1849QQ.this.f2731db.put("pay_token", string6);
                m21426a.m21415b(string);
                m21426a.m21410d(string2);
                m21426a.m21427a();
                C1849QQ.this.afterRegister(1, null);
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                if (C1849QQ.this.listener != null) {
                    C1849QQ.this.listener.onCancel(C1849QQ.this, 1);
                }
            }
        }, isSSODisable());
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        QQHelper m21426a = QQHelper.m21426a(this);
        if (m21426a.m21417b() && this.f3156c && i == 9) {
            SSDKLog.m21740b().m21744a("ShareSDK", "QQ checkAuthorize qq.isQQInstalled() return true ");
            return true;
        } else if (isAuthValid() || (i == 9 && obj != null && (obj instanceof Platform.ShareParams) && !((Platform.ShareParams) obj).isShareTencentWeibo())) {
            m21426a.m21421a(this.f3154a);
            m21426a.m21415b(this.f2731db.getUserId());
            m21426a.m21410d(this.f2731db.getToken());
            SSDKLog.m21740b().m21744a("ShareSDK", "QQ checkAuthorize isAuthValid return true ");
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(final Platform.ShareParams shareParams) {
        String str;
        String str2;
        SSDKLog.m21740b().m21744a("ShareSDK", " QQ doShare ");
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("QQ start Share with Appid:" + this.f3154a, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        m21740b2.m21744a("QQ ShareParams:" + shareParams.toString(), new Object[0]);
        QQHelper m21426a = QQHelper.m21426a(this);
        if (this.f3156c && m21426a.m21417b()) {
            try {
                m21426a.m21425a(this, shareParams, this.listener);
                SSDKLog.m21740b().m21744a("ShareSDK", " QQ byPassShare ");
                return;
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
                SSDKLog m21740b3 = SSDKLog.m21740b();
                m21740b3.m21744a("ShareSDK", " QQ doShare catch: " + th);
                return;
            }
        }
        String title = shareParams.getTitle();
        String text = shareParams.getText();
        String imagePath = shareParams.getImagePath();
        String imageUrl = shareParams.getImageUrl();
        String musicUrl = shareParams.getMusicUrl();
        String titleUrl = shareParams.getTitleUrl();
        if (TextUtils.isEmpty(titleUrl)) {
            titleUrl = shareParams.getUrl();
        }
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        int hidden = shareParams.getHidden();
        String qQMiniProgramAppid = shareParams.getQQMiniProgramAppid();
        String qQMiniProgramPath = shareParams.getQQMiniProgramPath();
        String qQMiniProgramType = shareParams.getQQMiniProgramType();
        int shareType = shareParams.getShareType();
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(text) && TextUtils.isEmpty(imagePath) && TextUtils.isEmpty(imageUrl) && TextUtils.isEmpty(musicUrl) && TextUtils.isEmpty(qQMiniProgramAppid)) {
            if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable("qq share must have one param at least"));
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(titleUrl)) {
            str = titleUrl;
        } else {
            String shortLintk = getShortLintk(titleUrl, false);
            shareParams.setTitleUrl(shortLintk);
            str = shortLintk;
        }
        if (TextUtils.isEmpty(text)) {
            str2 = text;
        } else {
            String shortLintk2 = getShortLintk(text, false);
            shareParams.setText(shortLintk2);
            str2 = shortLintk2;
        }
        m21426a.m21419a(title, str, str2, imagePath, imageUrl, musicUrl, new PlatformActionListener() { // from class: cn.sharesdk.tencent.qq.QQ.2
            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onError(Platform platform, int i, Throwable th2) {
                if (C1849QQ.this.listener != null) {
                    C1849QQ.this.listener.onError(C1849QQ.this, 9, th2);
                }
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                HashMap<String, Object> hashMap2 = new HashMap<>();
                hashMap2.put("ShareParams", shareParams);
                if (C1849QQ.this.listener != null) {
                    C1849QQ.this.listener.onComplete(C1849QQ.this, 9, hashMap2);
                }
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onCancel(Platform platform, int i) {
                if (C1849QQ.this.listener != null) {
                    C1849QQ.this.listener.onCancel(C1849QQ.this, 9);
                }
            }
        }, isShareTencentWeibo, hidden, qQMiniProgramAppid, qQMiniProgramPath, qQMiniProgramType, shareType);
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
        if (str == null || str.length() < 0) {
            str = this.f2731db.getUserId();
        }
        if (str == null || str.length() < 0) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new RuntimeException("qq account is null"));
                return;
            }
            return;
        }
        try {
            HashMap<String, Object> m21408e = QQHelper.m21426a(this).m21408e(str);
            if (m21408e != null && m21408e.size() > 0) {
                if (!m21408e.containsKey("ret")) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                        return;
                    }
                    return;
                } else if (((Integer) m21408e.get("ret")).intValue() != 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(m21408e)));
                        return;
                    }
                    return;
                } else {
                    if (str == this.f2731db.getUserId()) {
                        this.f2731db.put("nickname", String.valueOf(m21408e.get("nickname")));
                        if (m21408e.containsKey("figureurl_qq_2")) {
                            this.f2731db.put("icon", String.valueOf(m21408e.get("figureurl_qq_2")));
                        } else if (m21408e.containsKey("figureurl_qq_1")) {
                            this.f2731db.put("icon", String.valueOf(m21408e.get("figureurl_qq_1")));
                        }
                        if (m21408e.containsKey("figureurl_2")) {
                            this.f2731db.put("iconQzone", String.valueOf(m21408e.get("figureurl_2")));
                        } else if (m21408e.containsKey("figureurl_1")) {
                            this.f2731db.put("iconQzone", String.valueOf(m21408e.get("figureurl_1")));
                        } else if (m21408e.containsKey("figureurl")) {
                            this.f2731db.put("iconQzone", String.valueOf(m21408e.get("figureurl")));
                        }
                        this.f2731db.put("secretType", String.valueOf(m21408e.get("is_yellow_vip")));
                        if (String.valueOf(m21408e.get("is_yellow_vip")).equals("1")) {
                            this.f2731db.put("snsUserLevel", String.valueOf(m21408e.get("level")));
                        }
                        String valueOf = String.valueOf(m21408e.get("gender"));
                        int stringRes = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_gender_male");
                        int stringRes2 = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_gender_female");
                        if (valueOf.equals(MobSDK.getContext().getString(stringRes))) {
                            this.f2731db.put("gender", "0");
                        } else if (valueOf.equals(MobSDK.getContext().getString(stringRes2))) {
                            this.f2731db.put("gender", "1");
                        } else {
                            this.f2731db.put("gender", "2");
                        }
                    }
                    if (this.listener != null) {
                        if (this.f2731db.get("userTags") != null) {
                            m21408e.put("userTags", this.f2731db.get("userTags"));
                        }
                        this.listener.onComplete(this, 8, m21408e);
                        return;
                    }
                    return;
                }
            }
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable());
            }
        } catch (Throwable th) {
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
        String titleUrl = shareParams.getTitleUrl();
        c1746a.f2817c.add(titleUrl);
        c1746a.f2815a = this.f3154a;
        String text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            c1746a.f2816b = text;
        }
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        if (!TextUtils.isEmpty(imagePath)) {
            c1746a.f2819e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            c1746a.f2818d.add(imageUrl);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", titleUrl);
        hashMap2.put("imageLocalUrl", imagePath);
        hashMap2.put("summary", text);
        hashMap2.put("appName", DeviceHelper.getInstance(MobSDK.getContext()).getAppName());
        c1746a.f2821g = hashMap2;
        return c1746a;
    }

    @Override // cn.sharesdk.framework.Platform
    public String uploadImageToFileServer(String str) {
        return super.uploadImageToFileServer(str);
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return this.f3155b;
    }
}
