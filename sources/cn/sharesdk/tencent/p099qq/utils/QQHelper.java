package cn.sharesdk.tencent.p099qq.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformHelper;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.AuthorizeWebviewClient;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.framework.authorize.WebAuthorizeActivity;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.ShareBypassApproval;
import cn.sharesdk.tencent.p099qq.QQAuthorizeWebviewClient;
import cn.sharesdk.tencent.p099qq.QQSSOProcessor;
import cn.sharesdk.tencent.p099qq.ShareActivity;
import com.mob.MobSDK;
import com.mob.tools.RxMob;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qq.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QQHelper extends PlatformHelper {

    /* renamed from: b */
    private static final String[] f3182b = {"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};

    /* renamed from: c */
    private static QQHelper f3183c;

    /* renamed from: d */
    private String f3184d;

    /* renamed from: e */
    private String[] f3185e;

    /* renamed from: f */
    private String f3186f;

    /* renamed from: g */
    private String f3187g;

    /* renamed from: h */
    private String f3188h;

    /* renamed from: i */
    private String f3189i;

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    /* renamed from: a */
    public static QQHelper m21426a(Platform platform) {
        if (f3183c == null) {
            f3183c = new QQHelper(platform);
        }
        return f3183c;
    }

    private QQHelper(Platform platform) {
        super(platform);
        m21417b();
    }

    /* renamed from: a */
    public void m21421a(String str) {
        this.f3184d = str;
    }

    /* renamed from: a */
    public void m21418a(String[] strArr) {
        this.f3185e = strArr;
    }

    /* renamed from: a */
    public void m21424a(final AuthorizeListener authorizeListener, boolean z) {
        m21844a(new SSOListener() { // from class: cn.sharesdk.tencent.qq.utils.a.1
            @Override // cn.sharesdk.framework.authorize.SSOListener
            public void onFailed(Throwable th) {
                if (th != null) {
                    authorizeListener.onError(th);
                } else {
                    authorizeListener.onError(new Throwable("Unknown Throwable!"));
                }
            }

            @Override // cn.sharesdk.framework.authorize.SSOListener
            public void onComplete(Bundle bundle) {
                authorizeListener.onComplete(bundle);
            }

            @Override // cn.sharesdk.framework.authorize.SSOListener
            public void onCancel() {
                authorizeListener.onCancel();
            }
        });
    }

    /* renamed from: b */
    public void m21415b(String str) {
        this.f3186f = str;
    }

    /* renamed from: c */
    public HashMap<String, Object> m21413c(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("access_token", str));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String httpGet = SSDKNetworkHelper.getInstance().httpGet("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, null, "/oauth2.0/me", m21842c());
        if (httpGet.startsWith("callback")) {
            while (!httpGet.startsWith("{") && httpGet.length() > 0) {
                httpGet = httpGet.substring(1);
            }
            while (!httpGet.endsWith("}") && httpGet.length() > 0) {
                httpGet = httpGet.substring(0, httpGet.length() - 1);
            }
        }
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    /* renamed from: a */
    public void m21427a() {
        RxMob.Subscribable create = RxMob.create(new RxMob.OnSubscribe() { // from class: cn.sharesdk.tencent.qq.utils.QQHelper$2
            @Override // com.mob.tools.RxMob.OnSubscribe
            public void call(RxMob.Subscriber subscriber) {
                String str;
                Platform platform;
                ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                str = QQHelper.this.f3188h;
                arrayList.add(new KVPair<>("access_token", str));
                arrayList.add(new KVPair<>("unionid", "1"));
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = 10000;
                networkTimeOut.connectionTimeout = 10000;
                SSDKNetworkHelper sSDKNetworkHelper = SSDKNetworkHelper.getInstance();
                try {
                    QQHelper.this.f3187g = sSDKNetworkHelper.httpPost("https://graph.qq.com/oauth2.0/me", arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) null, networkTimeOut);
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    th.printStackTrace();
                    platform = QQHelper.this.f2890a;
                    platform.getDb().put("unionid", "");
                    SSDKLog.m21740b().m21744a("qq auth,get unionId fail", new Object[0]);
                }
            }
        });
        create.subscribeOn(RxMob.Thread.NEW_THREAD);
        create.observeOn(RxMob.Thread.IMMEDIATE);
        create.subscribe(new RxMob.Subscriber() { // from class: cn.sharesdk.tencent.qq.utils.QQHelper$3
            @Override // com.mob.tools.RxMob.Subscriber
            public void onCompleted() {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                Platform platform;
                Platform platform2;
                str = QQHelper.this.f3187g;
                if (str != null) {
                    str2 = QQHelper.this.f3187g;
                    if (str2.length() > 0) {
                        QQHelper qQHelper = QQHelper.this;
                        str3 = qQHelper.f3187g;
                        qQHelper.f3187g = str3.replace("callback( ", "");
                        QQHelper qQHelper2 = QQHelper.this;
                        str4 = qQHelper2.f3187g;
                        qQHelper2.f3187g = str4.replace(" );", "");
                        Hashon hashon = new Hashon();
                        str5 = QQHelper.this.f3187g;
                        HashMap fromJson = hashon.fromJson(str5);
                        if (fromJson.containsKey("unionid")) {
                            platform2 = QQHelper.this.f2890a;
                            platform2.getDb().put("unionid", (String) fromJson.get("unionid"));
                            return;
                        }
                        platform = QQHelper.this.f2890a;
                        platform.getDb().put("unionid", "");
                    }
                }
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onError(Throwable th) {
                Platform platform;
                platform = QQHelper.this.f2890a;
                platform.getDb().put("unionid", "");
                SSDKLog.m21740b().m21744a("qq auth,get unionId fail", new Object[0]);
            }
        });
    }

    /* renamed from: d */
    public void m21410d(String str) {
        this.f3188h = str;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getAuthorizeUrl() {
        String redirectUri;
        ShareSDK.logApiEvent("/oauth2.0/authorize", m21842c());
        String m21412d = m21412d();
        try {
            redirectUri = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            redirectUri = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.f3184d + "&redirect_uri=" + redirectUri + "&display=mobile&scope=" + m21412d;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public AuthorizeWebviewClient getAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        return new QQAuthorizeWebviewClient(webAuthorizeActivity);
    }

    /* renamed from: d */
    private String m21412d() {
        String[] strArr = this.f3185e;
        if (strArr == null) {
            strArr = f3182b;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(str);
            i++;
        }
        return sb.toString();
    }

    @Override // cn.sharesdk.framework.PlatformHelper, cn.sharesdk.framework.authorize.AuthorizeHelper
    public SSOProcessor getSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        QQSSOProcessor qQSSOProcessor = new QQSSOProcessor(sSOAuthorizeActivity);
        qQSSOProcessor.m21871a(5656);
        qQSSOProcessor.m21429a(this.f3184d, m21412d(), this.f3189i);
        return qQSSOProcessor;
    }

    /* renamed from: e */
    public HashMap<String, Object> m21408e(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("access_token", this.f3188h));
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f3184d));
        arrayList.add(new KVPair<>("openid", this.f3186f));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String httpGet = SSDKNetworkHelper.getInstance().httpGet("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, null, "/user/get_simple_userinfo", m21842c());
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    /* renamed from: a */
    public void m21425a(Platform platform, Platform.ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        ShareBypassApproval shareBypassApproval = new ShareBypassApproval();
        shareBypassApproval.m21682a(this.f3189i, "com.tencent.mobileqq.activity.JumpActivity");
        shareBypassApproval.m21685a(shareParams, platform);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ShareParams", shareParams);
        platformActionListener.onComplete(platform, 9, hashMap);
    }

    /* renamed from: a */
    public void m21419a(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener, boolean z, int i, String str7, String str8, String str9, int i2) {
        String str10 = str4;
        if (z) {
            m21420a(str, str2, str3, str4, str5, str6, platformActionListener);
            SSDKLog.m21740b().m21744a("ShareSDK", " QQ QQHelper addShare weiboShare ");
        } else if (!m21417b()) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.f2890a, 9, new Throwable("QQClientNotExistException"));
            }
        } else {
            SSDKLog.m21740b().m21744a("ShareSDK", " QQ QQHelper addShare isQQInstalled ");
            if (!TextUtils.isEmpty(str4)) {
                File file = new File(str4);
                if (file.exists() && str4.startsWith("/data/")) {
                    String cachePath = ResHelper.getCachePath(MobSDK.getContext(), "images");
                    String absolutePath = new File(cachePath, System.currentTimeMillis() + file.getName()).getAbsolutePath();
                    if (ResHelper.copyFile(str4, absolutePath)) {
                        SSDKLog m21740b = SSDKLog.m21740b();
                        m21740b.m21744a("ShareSDK", " QQ QQHelper imagePath " + absolutePath);
                        str10 = absolutePath;
                    } else {
                        str10 = null;
                        SSDKLog.m21740b().m21744a("ShareSDK", " QQ QQHelper imagePath is null");
                    }
                }
            }
            Intent intent = new Intent();
            intent.putExtra("title", str);
            intent.putExtra("titleUrl", str2);
            intent.putExtra("summary", str3);
            intent.putExtra("imagePath", str10);
            intent.putExtra("imageUrl", str5);
            intent.putExtra("musicUrl", str6);
            intent.putExtra("appId", this.f3184d);
            intent.putExtra("hidden", i);
            intent.putExtra("mini_program_appid", str7);
            intent.putExtra("mini_program_path", str8);
            intent.putExtra("mini_program_type", str9);
            intent.putExtra("share_type", i2);
            ShareActivity shareActivity = new ShareActivity();
            shareActivity.setPlatformActionListener(this.f2890a, platformActionListener);
            shareActivity.setAppId(this.f3184d);
            shareActivity.show(MobSDK.getContext(), intent);
        }
    }

    /* renamed from: b */
    public boolean m21417b() {
        String str;
        try {
            PackageInfo m21715b = AppUtils.m21715b("com.tencent.mobileqq", 0);
            String str2 = m21715b.versionName;
            this.f3189i = m21715b.packageName;
            str = str2;
        } catch (Throwable th) {
            try {
                try {
                    try {
                        try {
                            PackageInfo m21715b2 = AppUtils.m21715b("com.tencent.tim", 0);
                            str = m21715b2.versionName;
                            this.f3189i = m21715b2.packageName;
                        } catch (Throwable unused) {
                            PackageInfo m21715b3 = AppUtils.m21715b("com.tencent.qqlite", 0);
                            str = m21715b3.versionName;
                            this.f3189i = m21715b3.packageName;
                        }
                    } catch (Throwable unused2) {
                        SSDKLog.m21740b().m21742a(th);
                        str = null;
                    }
                } catch (Throwable unused3) {
                    PackageInfo m21715b4 = AppUtils.m21715b("com.tencent.minihd.qq", 0);
                    str = m21715b4.versionName;
                    this.f3189i = m21715b4.packageName;
                }
            } catch (Throwable unused4) {
                PackageInfo m21715b5 = AppUtils.m21715b("com.tencent.mobileqqi", 0);
                str = m21715b5.versionName;
                this.f3189i = m21715b5.packageName;
            }
        }
        return !TextUtils.isEmpty(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006c A[Catch: Throwable -> 0x00cc, TryCatch #0 {Throwable -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:13:0x0019, B:15:0x006c, B:17:0x0072, B:18:0x007a, B:21:0x009c, B:24:0x00a4, B:26:0x00bb, B:27:0x00c6, B:19:0x008e), top: B:33:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008e A[Catch: Throwable -> 0x00cc, TryCatch #0 {Throwable -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:13:0x0019, B:15:0x006c, B:17:0x0072, B:18:0x007a, B:21:0x009c, B:24:0x00a4, B:26:0x00bb, B:27:0x00c6, B:19:0x008e), top: B:33:0x0002 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m21420a(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, cn.sharesdk.framework.PlatformActionListener r13) {
        /*
            r6 = this;
            r7 = 9
            boolean r8 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> Lcc
            if (r8 == 0) goto L11
            boolean r8 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> Lcc
            if (r8 != 0) goto Lf
            goto L11
        Lf:
            r8 = 0
            goto L12
        L11:
            r8 = 1
        L12:
            if (r8 != 0) goto L17
            java.lang.String r12 = "/t/add_t"
            goto L19
        L17:
            java.lang.String r12 = "/t/add_pic_t"
        L19:
            r4 = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
            r12.<init>()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "https://graph.qq.com"
            r12.append(r0)     // Catch: java.lang.Throwable -> Lcc
            r12.append(r4)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r1 = r12.toString()     // Catch: java.lang.Throwable -> Lcc
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lcc
            r2.<init>()     // Catch: java.lang.Throwable -> Lcc
            com.mob.tools.network.KVPair r12 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "oauth_consumer_key"
            java.lang.String r3 = r6.f3184d     // Catch: java.lang.Throwable -> Lcc
            r12.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            r2.add(r12)     // Catch: java.lang.Throwable -> Lcc
            com.mob.tools.network.KVPair r12 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "access_token"
            java.lang.String r3 = r6.f3188h     // Catch: java.lang.Throwable -> Lcc
            r12.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            r2.add(r12)     // Catch: java.lang.Throwable -> Lcc
            com.mob.tools.network.KVPair r12 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "openid"
            java.lang.String r3 = r6.f3186f     // Catch: java.lang.Throwable -> Lcc
            r12.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            r2.add(r12)     // Catch: java.lang.Throwable -> Lcc
            com.mob.tools.network.KVPair r12 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "format"
            java.lang.String r3 = "json"
            r12.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lcc
            r2.add(r12)     // Catch: java.lang.Throwable -> Lcc
            com.mob.tools.network.KVPair r12 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r0 = "content"
            r12.<init>(r0, r9)     // Catch: java.lang.Throwable -> Lcc
            r2.add(r12)     // Catch: java.lang.Throwable -> Lcc
            if (r8 == 0) goto L8e
            boolean r8 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> Lcc
            if (r8 == 0) goto L7a
            android.content.Context r8 = com.mob.MobSDK.getContext()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r10 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r8, r11)     // Catch: java.lang.Throwable -> Lcc
        L7a:
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r8 = "pic"
            r3.<init>(r8, r10)     // Catch: java.lang.Throwable -> Lcc
            cn.sharesdk.framework.network.SSDKNetworkHelper r0 = cn.sharesdk.framework.network.SSDKNetworkHelper.getInstance()     // Catch: java.lang.Throwable -> Lcc
            int r5 = r6.m21842c()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r8 = r0.httpPost(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lcc
            goto L9a
        L8e:
            cn.sharesdk.framework.network.SSDKNetworkHelper r8 = cn.sharesdk.framework.network.SSDKNetworkHelper.getInstance()     // Catch: java.lang.Throwable -> Lcc
            int r9 = r6.m21842c()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r8 = r8.httpPost(r1, r2, r4, r9)     // Catch: java.lang.Throwable -> Lcc
        L9a:
            if (r8 == 0) goto Ld4
            int r9 = r8.length()     // Catch: java.lang.Throwable -> Lcc
            if (r9 <= 0) goto Ld4
            if (r13 == 0) goto Ld4
            com.mob.tools.utils.Hashon r9 = new com.mob.tools.utils.Hashon     // Catch: java.lang.Throwable -> Lcc
            r9.<init>()     // Catch: java.lang.Throwable -> Lcc
            java.util.HashMap r9 = r9.fromJson(r8)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r10 = "ret"
            java.lang.Object r10 = r9.get(r10)     // Catch: java.lang.Throwable -> Lcc
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch: java.lang.Throwable -> Lcc
            int r10 = r10.intValue()     // Catch: java.lang.Throwable -> Lcc
            if (r10 == 0) goto Lc6
            cn.sharesdk.framework.Platform r9 = r6.f2890a     // Catch: java.lang.Throwable -> Lcc
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Throwable -> Lcc
            r10.<init>(r8)     // Catch: java.lang.Throwable -> Lcc
            r13.onError(r9, r7, r10)     // Catch: java.lang.Throwable -> Lcc
            goto Ld4
        Lc6:
            cn.sharesdk.framework.Platform r8 = r6.f2890a     // Catch: java.lang.Throwable -> Lcc
            r13.onComplete(r8, r7, r9)     // Catch: java.lang.Throwable -> Lcc
            goto Ld4
        Lcc:
            r8 = move-exception
            if (r13 == 0) goto Ld4
            cn.sharesdk.framework.Platform r9 = r6.f2890a
            r13.onError(r9, r7, r8)
        Ld4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.p099qq.utils.QQHelper.m21420a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, cn.sharesdk.framework.PlatformActionListener):void");
    }
}
