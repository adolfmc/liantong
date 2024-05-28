package cn.sharesdk.tencent.qzone;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.ShareBypassApproval;
import cn.sharesdk.tencent.qzone.utils.QQClientNotExistException;
import cn.sharesdk.tencent.qzone.utils.QZoneHelper;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QZone extends Platform {
    public static final String NAME = "QZone";

    /* renamed from: a */
    private String f3192a;

    /* renamed from: b */
    private boolean f3193b;

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
        return 6;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 2;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return true;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3192a = getDevinfo("AppId");
        this.f3193b = "true".equals(getDevinfo("BypassApproval"));
        String str2 = this.f3192a;
        if (str2 == null || str2.length() <= 0) {
            this.f3192a = getDevinfo("QQ", "AppId");
            String str3 = this.f3192a;
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            copyDevinfo("QQ", NAME);
            this.f3192a = getDevinfo("AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3192a = getNetworkDevinfo("app_id", "AppId");
        String str = this.f3192a;
        if (str == null || str.length() <= 0) {
            this.f3192a = getNetworkDevinfo(24, "app_id", "AppId");
            String str2 = this.f3192a;
            if (str2 == null || str2.length() <= 0) {
                return;
            }
            copyNetworkDevinfo(24, 6);
            this.f3192a = getNetworkDevinfo("app_id", "AppId");
            SSDKLog.m21740b().m21744a("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        QZoneHelper m21379a = QZoneHelper.m21379a(this);
        m21379a.m21375a(this.f3192a);
        return m21379a.m21364d();
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        final QZoneHelper m21379a = QZoneHelper.m21379a(this);
        m21379a.m21375a(this.f3192a);
        m21379a.m21371a(strArr);
        m21379a.m21378a(new AuthorizeListener() { // from class: cn.sharesdk.tencent.qzone.QZone.1
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                if (QZone.this.listener != null) {
                    QZone.this.listener.onError(QZone.this, 1, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                QZone.this.f2731db.putToken(string2);
                QZone.this.f2731db.putTokenSecret("");
                try {
                    QZone.this.f2731db.putExpiresIn(ResHelper.parseLong(string3));
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
                QZone.this.f2731db.putUserId(string);
                String string4 = bundle.getString("pf");
                String string5 = bundle.getString("pfkey");
                String string6 = bundle.getString("pay_token");
                QZone.this.f2731db.put("pf", string4);
                QZone.this.f2731db.put("pfkey", string5);
                QZone.this.f2731db.put("pay_token", string6);
                m21379a.m21368b(string);
                m21379a.m21365c(string2);
                m21379a.m21382a();
                QZone.this.afterRegister(1, null);
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                if (QZone.this.listener != null) {
                    QZone.this.listener.onCancel(QZone.this, 1);
                }
            }
        }, isSSODisable());
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        QZoneHelper m21379a = QZoneHelper.m21379a(this);
        if (m21379a.m21370b() && this.f3193b && i == 9) {
            return true;
        }
        if (isAuthValid() || i == 9) {
            m21379a.m21375a(this.f3192a);
            m21379a.m21368b(this.f2731db.getUserId());
            m21379a.m21365c(this.f2731db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        QZoneHelper m21379a = QZoneHelper.m21379a(this);
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("QZone start Share with Appid:" + this.f3192a + "bypassApproval:" + this.f3193b, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("QZone ShareParams:");
        sb.append(shareParams.toString());
        m21740b2.m21744a(sb.toString(), new Object[0]);
        if (m21379a.m21370b() && this.f3193b) {
            try {
                m21406a(shareParams);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
            }
        } else if (shareParams.isShareTencentWeibo()) {
            m21403b(shareParams);
        } else {
            m21401c(shareParams);
        }
    }

    /* renamed from: a */
    private void m21406a(Platform.ShareParams shareParams) throws Throwable {
        ShareBypassApproval shareBypassApproval = new ShareBypassApproval();
        shareBypassApproval.m21682a("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
        shareBypassApproval.m21685a(shareParams, this);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ShareParams", shareParams);
        this.listener.onComplete(this, 9, hashMap);
    }

    /* renamed from: b */
    private void m21403b(final Platform.ShareParams shareParams) {
        HashMap<String, Object> m21374a;
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        try {
            if (TextUtils.isEmpty(imagePath) && !TextUtils.isEmpty(imageUrl)) {
                shareParams.setImagePath(BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl));
                doShare(shareParams);
            } else if (!isAuthValid()) {
                final PlatformActionListener platformActionListener = getPlatformActionListener();
                setPlatformActionListener(new PlatformActionListener() { // from class: cn.sharesdk.tencent.qzone.QZone.2
                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onError(Platform platform, int i, Throwable th) {
                        PlatformActionListener platformActionListener2 = platformActionListener;
                        if (platformActionListener2 != null) {
                            platformActionListener2.onError(platform, 9, th);
                        }
                    }

                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        QZone.this.setPlatformActionListener(platformActionListener);
                        QZone.this.doShare(shareParams);
                    }

                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onCancel(Platform platform, int i) {
                        PlatformActionListener platformActionListener2 = platformActionListener;
                        if (platformActionListener2 != null) {
                            platformActionListener2.onCancel(platform, 9);
                        }
                    }
                });
                authorize();
            } else {
                String text = shareParams.getText();
                if (TextUtils.isEmpty(text)) {
                    if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable("share params' value of text is empty!"));
                        return;
                    }
                    return;
                }
                String shortLintk = getShortLintk(text, false);
                shareParams.setText(shortLintk);
                QZoneHelper m21379a = QZoneHelper.m21379a(this);
                if (isShareTencentWeibo) {
                    m21374a = m21379a.m21367b(imagePath, shortLintk);
                } else {
                    m21374a = m21379a.m21374a(imagePath, shortLintk);
                }
                if (m21374a == null && this.listener != null) {
                    this.listener.onError(this, 9, new Throwable("response is empty"));
                }
                m21374a.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, m21374a);
                }
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    /* renamed from: c */
    private void m21401c(final Platform.ShareParams shareParams) {
        String str;
        String str2;
        try {
            String imageUrl = shareParams.getImageUrl();
            String imagePath = shareParams.getImagePath();
            if (isClientValid()) {
                String str3 = (TextUtils.isEmpty(imagePath) || !new File(imagePath).exists()) ? imageUrl : imagePath;
                String title = shareParams.getTitle();
                String titleUrl = shareParams.getTitleUrl();
                String site = shareParams.getSite();
                String text = shareParams.getText();
                String filePath = shareParams.getFilePath();
                int shareType = shareParams.getShareType();
                if (TextUtils.isEmpty(text)) {
                    str = text;
                } else {
                    String shortLintk = getShortLintk(text, false);
                    shareParams.setText(shortLintk);
                    str = shortLintk;
                }
                if (TextUtils.isEmpty(titleUrl)) {
                    str2 = titleUrl;
                } else {
                    String shortLintk2 = getShortLintk(titleUrl, false);
                    shareParams.setTitleUrl(shortLintk2);
                    str2 = shortLintk2;
                }
                QZoneHelper.m21379a(this).m21380a(shareType, title, str2, str, str3, site, filePath, shareParams.getQQMiniProgramAppid(), shareParams.getQQMiniProgramPath(), shareParams.getQQMiniProgramType(), shareParams.getImageUrl(), new PlatformActionListener() { // from class: cn.sharesdk.tencent.qzone.QZone.3
                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onError(Platform platform, int i, Throwable th) {
                        if (QZone.this.listener != null) {
                            QZone.this.listener.onError(QZone.this, 9, th);
                        }
                    }

                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        if (QZone.this.listener != null) {
                            hashMap.put("ShareParams", shareParams);
                            QZone.this.listener.onComplete(QZone.this, 9, hashMap);
                        }
                    }

                    @Override // cn.sharesdk.framework.PlatformActionListener
                    public void onCancel(Platform platform, int i) {
                        if (QZone.this.listener != null) {
                            QZone.this.listener.onCancel(QZone.this, 9);
                        }
                    }
                });
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new QQClientNotExistException());
            }
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
            HashMap<String, Object> m21362d = QZoneHelper.m21379a(this).m21362d(str);
            if (m21362d != null && m21362d.size() > 0) {
                if (!m21362d.containsKey("ret")) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                        return;
                    }
                    return;
                } else if (((Integer) m21362d.get("ret")).intValue() != 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(m21362d)));
                        return;
                    }
                    return;
                } else {
                    if (str == this.f2731db.getUserId()) {
                        this.f2731db.put("nickname", String.valueOf(m21362d.get("nickname")));
                        if (m21362d.containsKey("figureurl_qq_2")) {
                            this.f2731db.put("iconQQ", String.valueOf(m21362d.get("figureurl_qq_2")));
                        } else if (m21362d.containsKey("figureurl_qq_1")) {
                            this.f2731db.put("iconQQ", String.valueOf(m21362d.get("figureurl_qq_1")));
                        }
                        if (m21362d.containsKey("figureurl_2")) {
                            this.f2731db.put("icon", String.valueOf(m21362d.get("figureurl_2")));
                        } else if (m21362d.containsKey("figureurl_1")) {
                            this.f2731db.put("icon", String.valueOf(m21362d.get("figureurl_1")));
                        } else if (m21362d.containsKey("figureurl")) {
                            this.f2731db.put("icon", String.valueOf(m21362d.get("figureurl")));
                        }
                        this.f2731db.put("secretType", String.valueOf(m21362d.get("is_yellow_vip")));
                        if (String.valueOf(m21362d.get("is_yellow_vip")).equals("1")) {
                            this.f2731db.put("snsUserLevel", String.valueOf(m21362d.get("level")));
                        }
                        String valueOf = String.valueOf(m21362d.get("gender"));
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
                            m21362d.put("userTags", this.f2731db.get("userTags"));
                        }
                        this.listener.onComplete(this, 8, m21362d);
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
        HashMap<String, Object> m21372a = QZoneHelper.m21379a(this).m21372a(str, str2, hashMap, hashMap2);
        if (m21372a == null || m21372a.size() <= 0) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } else if (!m21372a.containsKey("ret")) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } else if (((Integer) m21372a.get("ret")).intValue() != 0) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(m21372a)));
            }
        } else if (this.listener != null) {
            this.listener.onComplete(this, i, m21372a);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public ShareEvent.C1746a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        ShareEvent.C1746a c1746a = new ShareEvent.C1746a();
        c1746a.f2816b = shareParams.getText();
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        if (imagePath != null) {
            c1746a.f2819e.add(imagePath);
        } else if (hashMap.get("large_url") != null) {
            c1746a.f2818d.add(String.valueOf(hashMap.get("large_url")));
        } else if (hashMap.get("small_url") != null) {
            c1746a.f2818d.add(String.valueOf(hashMap.get("small_url")));
        } else if (imageUrl != null) {
            c1746a.f2818d.add(imageUrl);
        }
        String titleUrl = shareParams.getTitleUrl();
        if (titleUrl != null) {
            c1746a.f2817c.add(titleUrl);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("titleUrl", shareParams.getTitleUrl());
        hashMap2.put("site", shareParams.getSite());
        c1746a.f2821g = hashMap2;
        return c1746a;
    }
}
