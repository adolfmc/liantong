package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.SdkPlusTags;
import cn.sharesdk.framework.p094a.StatisticsLogger;
import cn.sharesdk.framework.p094a.p096b.AuthEvent;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InnerPlatformActionListener implements PlatformActionListener {

    /* renamed from: a */
    private PlatformActionListener f2878a;

    /* renamed from: b */
    private HashMap<Platform, Platform.ShareParams> f2879b = new HashMap<>();

    /* renamed from: c */
    private int f2880c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m21852a(PlatformActionListener platformActionListener) {
        this.f2878a = platformActionListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public PlatformActionListener m21857a() {
        return this.f2878a;
    }

    /* renamed from: a */
    public void m21853a(Platform platform, Platform.ShareParams shareParams) {
        this.f2879b.put(platform, shareParams);
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onError(Platform platform, int i, Throwable th) {
        PlatformActionListener platformActionListener = this.f2878a;
        if (platformActionListener != null) {
            platformActionListener.onError(platform, i, th);
            this.f2878a = null;
            this.f2880c = 0;
        }
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (platform instanceof CustomPlatform) {
            PlatformActionListener platformActionListener = this.f2878a;
            if (platformActionListener != null) {
                platformActionListener.onComplete(platform, i, hashMap);
                this.f2878a = null;
                this.f2880c = 0;
            }
        } else if (i == 1) {
            m21854a(platform, i, hashMap);
        } else if (i == 9) {
            m21845b(platform, i, hashMap);
        } else {
            PlatformActionListener platformActionListener2 = this.f2878a;
            if (platformActionListener2 != null) {
                platformActionListener2.onComplete(platform, i, hashMap);
                if ("Wechat".equals(platform.getName())) {
                    return;
                }
                int i2 = this.f2880c;
                if (i2 == 0 || i2 == i) {
                    this.f2878a = null;
                    this.f2880c = 0;
                }
            }
        }
    }

    @Override // cn.sharesdk.framework.PlatformActionListener
    public void onCancel(Platform platform, int i) {
        PlatformActionListener platformActionListener = this.f2878a;
        if (platformActionListener != null) {
            platformActionListener.onCancel(platform, i);
            this.f2878a = null;
            this.f2880c = 0;
        }
    }

    /* renamed from: b */
    private void m21847b() {
        new Thread(new Runnable() { // from class: cn.sharesdk.framework.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SdkPlusTags.m21865c().m21864d();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }

    /* renamed from: a */
    private void m21854a(Platform platform, final int i, final HashMap<String, Object> hashMap) {
        if (SdkPlusTags.m21865c().m21866b() == null) {
            m21847b();
        }
        final PlatformActionListener platformActionListener = this.f2878a;
        this.f2878a = new PlatformActionListener() { // from class: cn.sharesdk.framework.d.2
            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onComplete(Platform platform2, int i2, HashMap<String, Object> hashMap2) {
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                if (InnerPlatformActionListener.this.f2878a != null) {
                    try {
                        if (ShareSDK.getEnableAuthTag()) {
                            String fromHashMap = new Hashon().fromHashMap(SdkPlusTags.m21865c().m21866b());
                            if (!TextUtils.isEmpty(fromHashMap)) {
                                platform2.getDb().put("userTags", fromHashMap);
                            }
                        }
                    } catch (Exception unused) {
                    }
                    InnerPlatformActionListener.this.f2878a.onComplete(platform2, i, hashMap);
                }
                AuthEvent authEvent = new AuthEvent();
                authEvent.f2787a = platform2.getPlatformId();
                authEvent.f2788b = "TencentWeibo".equals(platform2.getName()) ? platform2.getDb().get("name") : platform2.getDb().getUserId();
                authEvent.f2789c = new Hashon().fromHashMap(hashMap2);
                authEvent.f2790d = InnerPlatformActionListener.this.m21856a(platform2);
                StatisticsLogger m21893a = StatisticsLogger.m21893a();
                if (m21893a != null) {
                    m21893a.m21891a(authEvent);
                }
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onError(Platform platform2, int i2, Throwable th) {
                SSDKLog.m21740b().m21737b(th);
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                if (InnerPlatformActionListener.this.f2878a != null) {
                    InnerPlatformActionListener.this.f2878a.onComplete(platform2, i, hashMap);
                }
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onCancel(Platform platform2, int i2) {
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                if (InnerPlatformActionListener.this.f2878a != null) {
                    InnerPlatformActionListener.this.f2878a.onComplete(platform2, i, hashMap);
                }
            }
        };
        platform.showUser(null);
    }

    /* renamed from: b */
    private void m21845b(Platform platform, int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2;
        Platform platform2;
        Platform.ShareParams remove = this.f2879b.remove(platform);
        if (hashMap != null) {
            remove = (Platform.ShareParams) hashMap.remove("ShareParams");
        }
        try {
            hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            hashMap2 = hashMap;
        }
        if (remove != null) {
            ShareEvent shareEvent = new ShareEvent();
            shareEvent.f2814n = remove.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                try {
                    platform2 = ShareSDK.getPlatform("Wechat");
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21741a(th2, "InnerPlatformActionListener wechat is null", new Object[0]);
                    platform2 = null;
                }
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get("name");
            }
            shareEvent.f2810b = userId;
            shareEvent.f2809a = platform.getPlatformId();
            ShareEvent.C1746a filterShareContent = platform.filterShareContent(remove, hashMap2);
            if (filterShareContent != null) {
                shareEvent.f2811c = filterShareContent.f2815a;
                shareEvent.f2812d = filterShareContent;
            }
            if (platform != null) {
                shareEvent.f2813m = m21846b(platform);
            }
            StatisticsLogger m21893a = StatisticsLogger.m21893a();
            if (m21893a != null) {
                m21893a.m21891a(shareEvent);
            }
        }
        PlatformActionListener platformActionListener = this.f2878a;
        if (platformActionListener != null) {
            try {
                platformActionListener.onComplete(platform, i, hashMap);
                this.f2878a = null;
                this.f2880c = 0;
            } catch (Throwable th3) {
                SSDKLog.m21740b().m21742a(th3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m21855a(Platform platform, final int i, final Object obj) {
        if (SdkPlusTags.m21865c().m21866b() == null) {
            m21847b();
        }
        this.f2880c = i;
        final PlatformActionListener platformActionListener = this.f2878a;
        this.f2878a = new PlatformActionListener() { // from class: cn.sharesdk.framework.d.3
            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onError(Platform platform2, int i2, Throwable th) {
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                if (InnerPlatformActionListener.this.f2878a != null) {
                    InnerPlatformActionListener.this.f2878a.onError(platform2, i2, th);
                }
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onComplete(Platform platform2, int i2, HashMap<String, Object> hashMap) {
                if (ShareSDK.getEnableAuthTag()) {
                    String fromHashMap = new Hashon().fromHashMap(SdkPlusTags.m21865c().m21866b());
                    if (!TextUtils.isEmpty(fromHashMap)) {
                        platform2.getDb().put("userTags", fromHashMap);
                    }
                }
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                platform2.afterRegister(i, obj);
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onCancel(Platform platform2, int i2) {
                InnerPlatformActionListener.this.f2878a = platformActionListener;
                if (InnerPlatformActionListener.this.f2878a != null) {
                    InnerPlatformActionListener.this.f2878a.onCancel(platform2, i);
                }
            }
        };
        platform.doAuthorize(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m21856a(Platform platform) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            return m21851a(platform.getDb(), new String[]{"nickname", "icon", "gender", "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th2) {
            th = th2;
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* renamed from: b */
    private String m21846b(Platform platform) {
        Platform platform2;
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            try {
                platform2 = ShareSDK.getPlatform("Wechat");
            } catch (Throwable th) {
                SSDKLog.m21740b().m21741a(th, "InnerPlatformActionListener getUserDataBrief catch ", new Object[0]);
                platform2 = null;
            }
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return m21851a(db, new String[]{"gender", "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th2) {
            SSDKLog.m21740b().m21737b(th2);
            return null;
        }
    }

    /* renamed from: a */
    private String m21851a(PlatformDb platformDb, String[] strArr) throws Throwable {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                sb2.append('|');
                sb.append('|');
            }
            i++;
            String str2 = platformDb.get(str);
            if (!TextUtils.isEmpty(str2)) {
                sb.append(str2);
                sb2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        SSDKLog.m21740b().m21738b("======UserData: " + sb.toString());
        return sb2.toString();
    }
}
