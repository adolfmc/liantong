package cn.sharesdk.wework;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.model.BaseMessage;
import cn.sharesdk.wework.model.WKAuthMessage;
import com.mob.MobSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Wework extends Platform {
    public static final String NAME = "Wework";

    /* renamed from: g */
    private static volatile boolean f3301g;

    /* renamed from: a */
    private String f3302a;

    /* renamed from: b */
    private String f3303b;

    /* renamed from: c */
    private String f3304c;

    /* renamed from: d */
    private String f3305d;

    /* renamed from: e */
    private WeworkCore f3306e;

    /* renamed from: f */
    private String f3307f;

    @Override // cn.sharesdk.framework.Platform
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public ShareEvent.C1746a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public void follow(String str) {
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
    public void getFriendList(int i, int i2, String str) {
    }

    @Override // cn.sharesdk.framework.Platform
    public int getPlatformId() {
        return 60;
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
    public void timeline(int i, int i2, String str) {
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f3302a = getDevinfo("AppKey");
        this.f3304c = getDevinfo("AppSecret");
        this.f3303b = getDevinfo("AgentId");
        this.f3305d = getDevinfo("Schema");
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f3302a = getNetworkDevinfo("corp_id", "AppKey");
        this.f3304c = getNetworkDevinfo("app_secret", "AppSecret");
        this.f3303b = getNetworkDevinfo("agent_id", "AgentId");
        this.f3305d = getNetworkDevinfo("app_key", "Schema");
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        return WeworkHelper.m21194a();
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        if (!WeworkHelper.m21194a()) {
            if (this.listener != null) {
                this.listener.onError(this, i, new WeworkClientNotExistException());
            }
            return false;
        } else if (i == 9 || isAuthValid()) {
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("Wework start Auth with Appid:" + this.f3302a + "appSecret:" + this.f3304c + "agentid" + this.f3303b + "schema" + this.f3305d, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("init");
        sb.append(f3301g);
        m21740b2.m21744a(sb.toString(), new Object[0]);
        if (TextUtils.isEmpty(this.f3302a) || TextUtils.isEmpty(this.f3303b) || TextUtils.isEmpty(this.f3305d)) {
            if (this.listener != null) {
                this.listener.onError(this, 1, new Throwable("The params is missing !"));
                return;
            }
            return;
        }
        if (!f3301g) {
            this.f3306e = new WeworkCore(MobSDK.getContext());
            this.f3306e.m21204a(this.f3305d);
            f3301g = true;
        }
        WKAuthMessage.C1885a c1885a = new WKAuthMessage.C1885a();
        c1885a.f3343k = this.f3305d;
        c1885a.f3353l = this.f3302a;
        c1885a.f3354m = this.f3303b;
        c1885a.f3342j = "dd";
        if (WeworkHelper.m21194a()) {
            this.f3306e.m21205a(c1885a, new WKEventHandler() { // from class: cn.sharesdk.wework.Wework.1
                @Override // cn.sharesdk.wework.WKEventHandler
                public void handleResp(BaseMessage baseMessage) {
                    if (baseMessage instanceof WKAuthMessage.Resp) {
                        WKAuthMessage.Resp resp = (WKAuthMessage.Resp) baseMessage;
                        if (resp.f3359n == 1) {
                            if (Wework.this.listener != null) {
                                Wework.this.listener.onCancel(Wework.this, 1);
                            }
                        } else if (resp.f3359n == 2) {
                            if (Wework.this.listener != null) {
                                Wework.this.listener.onError(Wework.this, 1, new Throwable("login fail"));
                            }
                        } else if (resp.f3359n != 0 || TextUtils.isEmpty(resp.f3340j)) {
                        } else {
                            if (!TextUtils.isEmpty(resp.f3340j)) {
                                Wework.this.f3307f = resp.f3340j;
                            }
                            Wework.this.m21222c();
                        }
                    }
                }
            });
        } else if (this.listener != null) {
            this.listener.onError(this, 1, new WeworkClientNotExistException());
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void userInfor(String str) {
        if (str == null || str.length() < 0) {
            if (!TextUtils.isEmpty(this.f2731db.getUserId())) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("UserId", this.f2731db.getUserId());
                this.listener.onComplete(this, 8, hashMap);
                return;
            }
            String str2 = this.f3307f;
            if (str2 != null) {
                m21225a(str2);
                this.f3307f = null;
                return;
            }
            WKAuthMessage.C1885a c1885a = new WKAuthMessage.C1885a();
            c1885a.f3343k = this.f3305d;
            c1885a.f3353l = this.f3302a;
            c1885a.f3354m = this.f3303b;
            c1885a.f3342j = "dd";
            this.f3306e.m21205a(c1885a, new WKEventHandler() { // from class: cn.sharesdk.wework.Wework.2
                @Override // cn.sharesdk.wework.WKEventHandler
                public void handleResp(BaseMessage baseMessage) {
                    if (baseMessage instanceof WKAuthMessage.Resp) {
                        WKAuthMessage.Resp resp = (WKAuthMessage.Resp) baseMessage;
                        if (resp.f3359n == 1) {
                            if (Wework.this.listener != null) {
                                Wework.this.listener.onCancel(Wework.this, 1);
                            }
                        } else if (resp.f3359n == 2) {
                            if (Wework.this.listener != null) {
                                Wework.this.listener.onError(Wework.this, 1, new Throwable("login fail"));
                            }
                        } else if (resp.f3359n != 0 || TextUtils.isEmpty(resp.f3340j)) {
                        } else {
                            if (!TextUtils.isEmpty(resp.f3340j)) {
                                Wework.this.f3307f = resp.f3340j;
                            }
                            Wework.this.m21222c();
                        }
                    }
                }
            });
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("Wework start Share with Appid:" + this.f3302a + "appSecret:" + this.f3304c + "agentid" + this.f3303b + "schema" + this.f3305d, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("init");
        sb.append(f3301g);
        m21740b2.m21744a(sb.toString(), new Object[0]);
        SSDKLog m21740b3 = SSDKLog.m21740b();
        m21740b3.m21744a("Wework ShareParams:" + shareParams.toString(), new Object[0]);
        WeworkHelper m21193a = WeworkHelper.m21193a(getPlatformId());
        if (!f3301g) {
            this.f3306e = new WeworkCore(MobSDK.getContext());
            this.f3306e.m21204a(this.f3305d);
            f3301g = true;
        }
        m21224a(this.f3302a, "appId is null", m21193a);
        m21224a(this.f3303b, "agentId is null", m21193a);
        String packageName = MobSDK.getContext().getPackageName();
        m21224a(packageName, "packageName is null", m21193a);
        String appName = DeviceHelper.getInstance(MobSDK.getContext()).getAppName();
        m21224a(appName, "appName is null", m21193a);
        m21193a.m21184b(this.f3302a);
        m21193a.m21182c(this.f3303b);
        m21193a.m21180d(packageName);
        m21193a.m21178e(appName);
        try {
            if (WeworkHelper.m21194a()) {
                if (shareParams.getShareType() == 1) {
                    m21193a.m21191a(this.listener, this, shareParams, this.f3306e);
                } else if (shareParams.getShareType() == 2) {
                    m21193a.m21186b(this.listener, this, shareParams, this.f3306e);
                } else if (shareParams.getShareType() == 8) {
                    m21193a.m21183c(this.listener, this, shareParams, this.f3306e);
                } else if (shareParams.getShareType() == 6) {
                    m21193a.m21181d(this.listener, this, shareParams, this.f3306e);
                } else if (shareParams.getShareType() == 4) {
                    m21193a.m21179e(this.listener, this, shareParams, this.f3306e);
                } else if (this.listener != null) {
                    this.listener.onError(this, getPlatformId(), new Throwable("Please enter a valid sharing type"));
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new WeworkClientNotExistException());
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    /* renamed from: a */
    private void m21224a(String str, String str2, WeworkHelper weworkHelper) {
        if (!TextUtils.isEmpty(str)) {
            weworkHelper.m21182c(str);
        } else if (this.listener != null) {
            this.listener.onError(this, getPlatformId(), new Throwable(str2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m21222c() {
        WeworkHelper m21193a = WeworkHelper.m21193a(getPlatformId());
        if (!TextUtils.isEmpty(this.f3304c)) {
            m21193a.m21188a(this.f3304c);
        }
        if (!TextUtils.isEmpty(this.f3302a)) {
            m21193a.m21184b(this.f3302a);
        }
        m21193a.m21190a(new AuthorizeListener() { // from class: cn.sharesdk.wework.Wework.3
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                Wework.this.f2731db.putToken(bundle.getString("access_token"));
                try {
                    Wework.this.f2731db.putExpiresIn(ResHelper.parseLong(bundle.getString("expires_in")));
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
                Wework.this.afterRegister(1, null);
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                if (Wework.this.listener != null) {
                    Wework.this.listener.onError(Wework.this, 1, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                if (Wework.this.listener != null) {
                    Wework.this.listener.onCancel(Wework.this, 1);
                }
            }
        });
    }

    /* renamed from: a */
    private void m21225a(String str) {
        WeworkHelper m21193a = WeworkHelper.m21193a(getPlatformId());
        if ((TextUtils.isEmpty(str) || TextUtils.isEmpty(this.f2731db.getToken())) && this.listener != null) {
            this.listener.onError(this, 8, new Throwable("code or token is null"));
        }
        try {
            m21193a.m21192a(this, this.f2731db.getToken(), str, this.listener);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            if (this.listener != null) {
                this.listener.onError(this, 8, th);
            }
        }
    }
}
