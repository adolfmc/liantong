package cn.sharesdk.tencent.qzone.utils;

import android.content.pm.PackageInfo;
import android.os.Build;
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
import com.mob.MobSDK;
import com.mob.tools.RxMob;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qzone.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QZoneHelper extends PlatformHelper {

    /* renamed from: b */
    private static final String[] f3212b = {"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};

    /* renamed from: c */
    private static QZoneHelper f3213c;

    /* renamed from: d */
    private String f3214d;

    /* renamed from: e */
    private String f3215e;

    /* renamed from: f */
    private String f3216f;

    /* renamed from: g */
    private String f3217g;

    /* renamed from: h */
    private SSDKNetworkHelper f3218h;

    /* renamed from: i */
    private String[] f3219i;

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    /* renamed from: a */
    public static QZoneHelper m21379a(Platform platform) {
        if (f3213c == null) {
            f3213c = new QZoneHelper(platform);
        }
        return f3213c;
    }

    private QZoneHelper(Platform platform) {
        super(platform);
        this.f3218h = SSDKNetworkHelper.getInstance();
    }

    /* renamed from: a */
    public void m21375a(String str) {
        this.f3214d = str;
    }

    /* renamed from: a */
    public void m21371a(String[] strArr) {
        this.f3219i = strArr;
    }

    /* renamed from: a */
    public void m21378a(final AuthorizeListener authorizeListener, boolean z) {
        m21844a(new SSOListener() { // from class: cn.sharesdk.tencent.qzone.utils.b.1
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

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getAuthorizeUrl() {
        String redirectUri;
        ShareSDK.logApiEvent("/oauth2.0/authorize", m21842c());
        String m21361e = m21361e();
        try {
            redirectUri = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            redirectUri = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.f3214d + "&redirect_uri=" + redirectUri + "&display=mobile&scope=" + m21361e;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public AuthorizeWebviewClient getAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        return new QZoneAuthorizeWebviewClient(webAuthorizeActivity);
    }

    /* renamed from: e */
    private String m21361e() {
        String[] strArr = this.f3219i;
        if (strArr == null) {
            strArr = f3212b;
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
        QZoneSSOProcessor qZoneSSOProcessor = new QZoneSSOProcessor(sSOAuthorizeActivity);
        qZoneSSOProcessor.m21871a(5656);
        qZoneSSOProcessor.m21354a(this.f3214d, m21361e());
        return qZoneSSOProcessor;
    }

    /* renamed from: a */
    public void m21382a() {
        RxMob.Subscribable create = RxMob.create(new RxMob.OnSubscribe() { // from class: cn.sharesdk.tencent.qzone.utils.QZoneHelper$2
            @Override // com.mob.tools.RxMob.OnSubscribe
            public void call(RxMob.Subscriber subscriber) {
                String str;
                Platform platform;
                ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                str = QZoneHelper.this.f3216f;
                arrayList.add(new KVPair<>("access_token", str));
                arrayList.add(new KVPair<>("unionid", "1"));
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = 10000;
                networkTimeOut.connectionTimeout = 10000;
                SSDKNetworkHelper sSDKNetworkHelper = SSDKNetworkHelper.getInstance();
                try {
                    QZoneHelper.this.f3217g = sSDKNetworkHelper.httpPost("https://graph.qq.com/oauth2.0/me", arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) null, networkTimeOut);
                    subscriber.onCompleted();
                } catch (Throwable unused) {
                    platform = QZoneHelper.this.f2890a;
                    platform.getDb().put("unionid", "");
                    SSDKLog.m21740b().m21744a("qq auth, get unionId fail", new Object[0]);
                }
            }
        });
        create.subscribeOn(RxMob.Thread.NEW_THREAD);
        create.observeOn(RxMob.Thread.IMMEDIATE);
        create.subscribe(new RxMob.Subscriber() { // from class: cn.sharesdk.tencent.qzone.utils.QZoneHelper$3
            @Override // com.mob.tools.RxMob.Subscriber
            public void onCompleted() {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                Platform platform;
                Platform platform2;
                str = QZoneHelper.this.f3217g;
                if (str != null) {
                    str2 = QZoneHelper.this.f3217g;
                    if (str2.length() > 0) {
                        QZoneHelper qZoneHelper = QZoneHelper.this;
                        str3 = qZoneHelper.f3217g;
                        qZoneHelper.f3217g = str3.replace("callback( ", "");
                        QZoneHelper qZoneHelper2 = QZoneHelper.this;
                        str4 = qZoneHelper2.f3217g;
                        qZoneHelper2.f3217g = str4.replace(" );", "");
                        Hashon hashon = new Hashon();
                        str5 = QZoneHelper.this.f3217g;
                        HashMap fromJson = hashon.fromJson(str5);
                        if (fromJson.containsKey("unionid")) {
                            platform2 = QZoneHelper.this.f2890a;
                            platform2.getDb().put("unionid", (String) fromJson.get("unionid"));
                            return;
                        }
                        platform = QZoneHelper.this.f2890a;
                        platform.getDb().put("unionid", "");
                    }
                }
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onError(Throwable th) {
                Platform platform;
                platform = QZoneHelper.this.f2890a;
                platform.getDb().put("unionid", "");
                SSDKLog.m21740b().m21744a("qq auth, get unionId fail", new Object[0]);
            }
        });
    }

    /* renamed from: b */
    public void m21368b(String str) {
        this.f3215e = str;
    }

    /* renamed from: c */
    public void m21365c(String str) {
        this.f3216f = str;
    }

    /* renamed from: d */
    public HashMap<String, Object> m21362d(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("access_token", this.f3216f));
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f3214d));
        arrayList.add(new KVPair<>("openid", this.f3215e));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String httpGet = this.f3218h.httpGet("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, null, "/user/get_simple_userinfo", m21842c());
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    /* renamed from: a */
    public void m21380a(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, PlatformActionListener platformActionListener) throws Throwable {
        String str11;
        String appName = TextUtils.isEmpty(str5) ? DeviceHelper.getInstance(MobSDK.getContext()).getAppName() : str5;
        if (appName.length() > 20) {
            str11 = appName.substring(0, 20) + "...";
        } else {
            str11 = appName;
        }
        m21381a(i, (TextUtils.isEmpty(str) || str.length() <= 200) ? str : str.substring(0, 200), str2, str3, str4, str11, str6, platformActionListener);
    }

    /* renamed from: a */
    public void m21381a(int i, String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) throws Throwable {
        String str7;
        if (!TextUtils.isEmpty(str6)) {
            str7 = "4";
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            str7 = "3";
        } else if (TextUtils.isEmpty(str2)) {
            if (platformActionListener != null) {
                platformActionListener.onError(null, 9, new Throwable("The param of title or titleUrl is null !"));
                return;
            }
            return;
        } else {
            str7 = "1";
        }
        m21373a(str7, str, str2, str3, str4, str5, str6, platformActionListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007b, code lost:
        if (com.mob.tools.utils.ResHelper.copyFile(r19, r7) != false) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0157 A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0170 A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x018b A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01ae A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01de A[Catch: Throwable -> 0x025e, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x023f A[Catch: Throwable -> 0x025e, TRY_LEAVE, TryCatch #2 {Throwable -> 0x025e, blocks: (B:3:0x0008, B:8:0x0016, B:10:0x0021, B:12:0x0028, B:14:0x0030, B:17:0x003a, B:19:0x0045, B:21:0x004d, B:29:0x00e7, B:30:0x00fa, B:32:0x0100, B:34:0x0108, B:36:0x0112, B:38:0x0118, B:40:0x0120, B:42:0x0157, B:43:0x016a, B:45:0x0170, B:46:0x0185, B:48:0x018b, B:49:0x019e, B:51:0x01ae, B:52:0x01c3, B:54:0x01de, B:55:0x01ee, B:59:0x020c, B:61:0x023f, B:9:0x001c, B:25:0x007f), top: B:66:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m21373a(java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, cn.sharesdk.framework.PlatformActionListener r22) {
        /*
            Method dump skipped, instructions count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.qzone.utils.QZoneHelper.m21373a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, cn.sharesdk.framework.PlatformActionListener):void");
    }

    /* renamed from: f */
    private String m21357f(String str) {
        if (Build.VERSION.SDK_INT > 10) {
            try {
                Class<?> cls = ReflectHelper.getClass("android.media.MediaMetadataRetriever");
                Object newInstance = cls.newInstance();
                cls.getMethod("setDataSource", String.class).invoke(newInstance, str);
                return (String) ReflectHelper.invokeInstanceMethod(newInstance, "extractMetadata", 9);
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    /* renamed from: b */
    public boolean m21370b() {
        try {
            PackageInfo m21715b = AppUtils.m21715b("com.qzone", 0);
            if (m21715b == null) {
                return false;
            }
            String[] split = m21715b.versionName.split("\\.");
            int[] iArr = new int[split.length];
            for (int i = 0; i < iArr.length; i++) {
                try {
                    iArr[i] = ResHelper.parseInt(split[i]);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                    iArr[i] = 0;
                }
            }
            return iArr.length > 1 && (iArr[0] >= 4 || iArr[1] >= 1);
        } catch (Throwable th2) {
            SSDKLog.m21740b().m21742a(th2);
            return false;
        }
    }

    /* renamed from: d */
    public boolean m21364d() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            try {
                try {
                    str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.tim", 0).versionName;
                } catch (Throwable unused) {
                    str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.minihd.qq", 0).versionName;
                }
            } catch (Throwable unused2) {
                SSDKLog.m21740b().m21742a(th);
                str = null;
            }
        }
        return !TextUtils.isEmpty(str);
    }

    /* renamed from: a */
    public HashMap<String, Object> m21374a(String str, String str2) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("access_token", this.f3216f));
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f3214d));
        arrayList.add(new KVPair<>("openid", this.f3215e));
        arrayList.add(new KVPair<>("format", "json"));
        if (!TextUtils.isEmpty(str2)) {
            if (str2.length() > 200) {
                str2 = str2.substring(0, 199) + MobSDK.getContext().getString(ResHelper.getStringRes(MobSDK.getContext(), "ssdk_symbol_ellipsis"));
            }
            arrayList.add(new KVPair<>("photodesc", str2));
        }
        arrayList.add(new KVPair<>("mobile", "1"));
        KVPair<String> kVPair = new KVPair<>("picture", str);
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String httpPost = this.f3218h.httpPost("https://graph.qq.com/photo/upload_pic", arrayList, kVPair, arrayList2, "/photo/upload_pic", m21842c());
        if (httpPost == null || httpPost.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpPost);
    }

    /* renamed from: e */
    public HashMap<String, Object> m21359e(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("access_token", str));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String httpGet = this.f3218h.httpGet("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, null, "/oauth2.0/me", m21842c());
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

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public HashMap<String, Object> m21372a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        KVPair kVPair;
        String str3;
        if (str2 == null) {
            return null;
        }
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                arrayList.add(new KVPair<>(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        arrayList.add(new KVPair<>("access_token", this.f3216f));
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f3214d));
        arrayList.add(new KVPair<>("openid", this.f3215e));
        arrayList.add(new KVPair<>("format", "json"));
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            KVPair kVPair2 = null;
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                kVPair2 = new KVPair(entry2.getKey(), entry2.getValue());
            }
            kVPair = kVPair2;
        }
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        try {
            if ("GET".equals(str2.toUpperCase())) {
                str3 = new NetworkHelper().httpGet(str, arrayList, arrayList2, null);
            } else {
                str3 = "POST".equals(str2.toUpperCase()) ? new NetworkHelper().httpPost(str, arrayList, kVPair, arrayList2, (NetworkHelper.NetworkTimeOut) null) : null;
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            str3 = null;
        }
        if (str3 == null || str3.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str3);
    }

    /* renamed from: b */
    public HashMap<String, Object> m21367b(String str, String str2) throws Throwable {
        String httpPost;
        boolean z = !TextUtils.isEmpty(str);
        String str3 = z ? "/t/add_pic_t" : "/t/add_t";
        String str4 = "https://graph.qq.com" + str3;
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f3214d));
        arrayList.add(new KVPair<>("access_token", this.f3216f));
        arrayList.add(new KVPair<>("openid", this.f3215e));
        arrayList.add(new KVPair<>("format", "json"));
        arrayList.add(new KVPair<>("content", str2));
        if (z) {
            httpPost = this.f3218h.httpPost(str4, arrayList, new KVPair<>("pic", str), str3, m21842c());
        } else {
            httpPost = this.f3218h.httpPost(str4, arrayList, str3, m21842c());
        }
        if (httpPost == null || httpPost.length() <= 0) {
            return null;
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(httpPost);
        if (((Integer) fromJson.get("ret")).intValue() == 0) {
            return fromJson;
        }
        throw new Throwable(httpPost);
    }
}
