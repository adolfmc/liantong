package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformHelper;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.AuthorizeWebviewClient;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.framework.authorize.WebAuthorizeActivity;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.C1809a;
import cn.sharesdk.sina.weibo.utils.SinaWeiboAuthorizeWebviewClient;
import cn.sharesdk.sina.weibo.utils.SinaWeiboSSOProcessor;
import cn.sharesdk.sina.weibo.utils.WebSharePage;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.sina.weibo.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Weibo extends PlatformHelper {

    /* renamed from: b */
    private static Weibo f2995b;

    /* renamed from: c */
    private String f2996c;

    /* renamed from: d */
    private String f2997d;

    /* renamed from: e */
    private String f2998e;

    /* renamed from: f */
    private String f2999f;

    /* renamed from: g */
    private String[] f3000g;

    /* renamed from: h */
    private SSDKNetworkHelper f3001h;

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getAuthorizeUrl() {
        return "";
    }

    /* renamed from: a */
    public static synchronized Weibo m21649a(Platform platform) {
        Weibo weibo;
        synchronized (Weibo.class) {
            if (f2995b == null) {
                f2995b = new Weibo(platform);
            }
            weibo = f2995b;
        }
        return weibo;
    }

    private Weibo(Platform platform) {
        super(platform);
        this.f3000g = new String[]{"follow_app_official_microblog"};
        this.f3001h = SSDKNetworkHelper.getInstance();
    }

    /* renamed from: a */
    public void m21643a(String str, String str2) {
        this.f2996c = str;
        this.f2997d = str2;
    }

    /* renamed from: a */
    public void m21644a(String str) {
        this.f2998e = str;
    }

    /* renamed from: a */
    public void m21639a(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.f3000g = strArr;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public AuthorizeWebviewClient getAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        return new SinaWeiboAuthorizeWebviewClient(webAuthorizeActivity);
    }

    @Override // cn.sharesdk.framework.PlatformHelper, cn.sharesdk.framework.authorize.AuthorizeHelper
    public SSOProcessor getSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        SinaWeiboSSOProcessor sinaWeiboSSOProcessor = new SinaWeiboSSOProcessor(sSOAuthorizeActivity);
        sinaWeiboSSOProcessor.m21871a(32973);
        sinaWeiboSSOProcessor.m21597a(this.f2996c, this.f2998e, this.f3000g);
        return sinaWeiboSSOProcessor;
    }

    /* renamed from: a */
    private String m21641a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return m21640a(objArr, str, 0, objArr.length);
    }

    /* renamed from: a */
    private String m21640a(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i3 * 16);
        if (objArr[i] != null) {
            sb.append(objArr[i]);
        }
        while (true) {
            i++;
            if (i < i2) {
                sb.append(str);
                if (objArr[i] != null) {
                    sb.append(objArr[i]);
                }
            } else {
                return sb.toString();
            }
        }
    }

    /* renamed from: a */
    public void m21647a(AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            m21648a(authorizeListener);
            return;
        }
        new SinaWeiboOfficialAuth(this.f2996c, this.f2998e, m21641a(this.f3000g, ","), authorizeListener).show(MobSDK.getContext(), null);
        SSDKLog.m21740b().m21743a("SinaWeibo SDK Client doAuthorize ");
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public String getRedirectUri() {
        return TextUtils.isEmpty(this.f2998e) ? "https://api.weibo.com/oauth2/default.html" : this.f2998e;
    }

    /* renamed from: b */
    public String m21634b(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("client_id", this.f2996c));
        arrayList.add(new KVPair<>("client_secret", this.f2997d));
        arrayList.add(new KVPair<>("redirect_uri", this.f2998e));
        arrayList.add(new KVPair<>("grant_type", "authorization_code"));
        arrayList.add(new KVPair<>("code", str));
        String httpPost = this.f3001h.httpPost("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", m21842c());
        ShareSDK.logApiEvent("/oauth2/access_token", m21842c());
        return httpPost;
    }

    /* renamed from: a */
    public boolean m21652a() {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("client_id", this.f2996c));
        arrayList.add(new KVPair<>("client_secret", this.f2997d));
        arrayList.add(new KVPair<>("redirect_uri", this.f2998e));
        arrayList.add(new KVPair<>("grant_type", "refresh_token"));
        arrayList.add(new KVPair<>("refresh_token", this.f2890a.getDb().get("refresh_token")));
        try {
            String httpPost = this.f3001h.httpPost("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", m21842c());
            if (TextUtils.isEmpty(httpPost) || httpPost.contains("error") || httpPost.contains("error_code")) {
                return false;
            }
            HashMap fromJson = new Hashon().fromJson(httpPost);
            String valueOf = String.valueOf(fromJson.get("uid"));
            String valueOf2 = String.valueOf(fromJson.get("expires_in"));
            this.f2999f = String.valueOf(fromJson.get("access_token"));
            String valueOf3 = String.valueOf(fromJson.get("refresh_token"));
            String valueOf4 = String.valueOf(fromJson.get("remind_in"));
            this.f2890a.getDb().putUserId(valueOf);
            this.f2890a.getDb().putExpiresIn(Long.valueOf(valueOf2).longValue());
            this.f2890a.getDb().putToken(this.f2999f);
            this.f2890a.getDb().put("refresh_token", valueOf3);
            this.f2890a.getDb().put("remind_in", valueOf4);
            return true;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return false;
        }
    }

    /* renamed from: c */
    public void m21631c(String str) {
        this.f2999f = str;
    }

    /* renamed from: d */
    public HashMap<String, Object> m21627d(String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        String str2 = this.f2999f;
        if (str2 != null) {
            arrayList.add(new KVPair<>("access_token", str2));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        String httpGet = this.f3001h.httpGet("https://api.weibo.com/2/users/show.json", arrayList, "/2/users/show.json", m21842c());
        if (httpGet != null) {
            return new Hashon().fromJson(httpGet);
        }
        return null;
    }

    /* renamed from: b */
    public boolean m21638b() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.sina.weibo");
        intent.setType("image/*");
        ResolveInfo m21719a = AppUtils.m21719a(intent, 0);
        if (m21719a == null) {
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setPackage("com.sina.weibog3");
            intent2.setType("image/*");
            m21719a = AppUtils.m21719a(intent2, 0);
        }
        return m21719a != null;
    }

    /* renamed from: a */
    private void m21648a(AuthorizeListener authorizeListener) {
        new SinaWeiboWebAuthOfficial(this.f2996c, this.f2998e, m21641a(this.f3000g, ","), authorizeListener).show(MobSDK.getContext(), null);
        SSDKLog.m21740b().m21743a("SinaWeibo SDK Web showWebAuthActivity ");
    }

    /* renamed from: a */
    public void m21650a(final Platform.ShareParams shareParams, final PlatformActionListener platformActionListener) {
        File file;
        if (shareParams == null) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.f2890a, 9, new Throwable("Please set params"));
                return;
            }
            return;
        }
        if (shareParams.getImageData() == null && TextUtils.isEmpty(shareParams.getImagePath()) && !TextUtils.isEmpty(shareParams.getImageUrl())) {
            try {
                File file2 = new File(BitmapHelper.downloadBitmap(MobSDK.getContext(), shareParams.getImageUrl()));
                if (file2.exists()) {
                    shareParams.setImagePath(file2.getAbsolutePath());
                }
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
        if (shareParams.getImageArray() != null && shareParams.getImageArray().length > 0) {
            try {
                List<String> asList = Arrays.asList(shareParams.getImageArray());
                String[] strArr = new String[asList.size()];
                int i = 0;
                for (String str : asList) {
                    if (str.startsWith("http")) {
                        str = BitmapHelper.downloadBitmap(MobSDK.getContext(), str);
                    }
                    if (new File(str).exists() && str.startsWith("/data/")) {
                        File file3 = new File(ResHelper.getCachePath(MobSDK.getContext(), "images"), System.currentTimeMillis() + file.getName());
                        String absolutePath = file3.getAbsolutePath();
                        file3.createNewFile();
                        if (ResHelper.copyFile(str, absolutePath)) {
                            str = file3.getAbsolutePath();
                        }
                    }
                    strArr[i] = str;
                    i++;
                }
                shareParams.setImageArray(strArr);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
            }
        }
        String text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(getPlatform().getShortLintk(text, false));
        }
        new SinaWeiboShareOfficial(this.f2996c, this.f2998e, m21641a(this.f3000g, ","), shareParams, new AuthorizeListener() { // from class: cn.sharesdk.sina.weibo.a.1
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th3) {
                PlatformActionListener platformActionListener2 = platformActionListener;
                if (platformActionListener2 != null) {
                    platformActionListener2.onError(Weibo.this.f2890a, 9, th3);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(Weibo.this.f2890a, 9, hashMap);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                PlatformActionListener platformActionListener2 = platformActionListener;
                if (platformActionListener2 != null) {
                    platformActionListener2.onCancel(Weibo.this.f2890a, 9);
                }
            }
        }).show(MobSDK.getContext(), null);
    }

    /* renamed from: b */
    public void m21636b(final Platform.ShareParams shareParams, final PlatformActionListener platformActionListener) {
        String str;
        if (TextUtils.isEmpty(shareParams.getUrl())) {
            str = shareParams.getText();
        } else {
            str = shareParams.getText() + " " + shareParams.getUrl();
        }
        if (!TextUtils.isEmpty(str)) {
            shareParams.setText(this.f2890a.getShortLintk(str, false));
        } else {
            int stringRes = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_weibo_upload_content");
            if (stringRes > 0) {
                shareParams.setText(MobSDK.getContext().getResources().getString(stringRes));
            }
        }
        AuthorizeListener authorizeListener = new AuthorizeListener() { // from class: cn.sharesdk.sina.weibo.a.2
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                PlatformActionListener platformActionListener2 = platformActionListener;
                if (platformActionListener2 != null) {
                    platformActionListener2.onError(Weibo.this.f2890a, 9, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(Weibo.this.f2890a, 9, hashMap);
                }
                if (bundle != null) {
                    String string = bundle.getString("uid");
                    String string2 = bundle.getString("access_token");
                    String string3 = bundle.getString("expire_in");
                    if (!TextUtils.isEmpty(string2)) {
                        Weibo.this.f2999f = string2;
                        Weibo.this.f2890a.getDb().putToken(Weibo.this.f2999f);
                    }
                    Weibo.this.f2890a.getDb().putUserId(string);
                    try {
                        Weibo.this.f2890a.getDb().putExpiresIn(ResHelper.parseLong(string3));
                    } catch (Throwable unused) {
                    }
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                PlatformActionListener platformActionListener2 = platformActionListener;
                if (platformActionListener2 != null) {
                    platformActionListener2.onCancel(Weibo.this.f2890a, 9);
                }
            }
        };
        WebSharePage webSharePage = new WebSharePage();
        webSharePage.setAppKey(this.f2996c, this.f2999f);
        webSharePage.setShareParams(shareParams);
        webSharePage.setListener(authorizeListener);
        webSharePage.show(MobSDK.getContext(), null);
    }

    /* renamed from: e */
    public HashMap<String, Object> m21625e(String str) throws Throwable {
        boolean z;
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        arrayList.add(new KVPair<>("access_token", this.f2999f));
        try {
            ResHelper.parseLong(str);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        String httpPost = this.f3001h.httpPost("https://api.weibo.com/2/friendships/create.json", arrayList, "/2/friendships/create.json", m21842c());
        if (httpPost != null) {
            return new Hashon().fromJson(httpPost);
        }
        return null;
    }

    /* renamed from: a */
    public HashMap<String, Object> m21651a(int i, int i2, String str) throws Throwable {
        boolean z;
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        try {
            ResHelper.parseLong(str);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        arrayList.add(new KVPair<>("count", String.valueOf(i)));
        arrayList.add(new KVPair<>("page", String.valueOf(i2)));
        String httpGet = this.f3001h.httpGet("https://api.weibo.com/2/statuses/user_timeline.json", arrayList, "/2/statuses/user_timeline.json", m21842c());
        if (httpGet != null) {
            return new Hashon().fromJson(httpGet);
        }
        return null;
    }

    /* renamed from: b */
    public HashMap<String, Object> m21637b(int i, int i2, String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        String str2 = this.f2999f;
        if (str2 != null) {
            arrayList.add(new KVPair<>("access_token", str2));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        arrayList.add(new KVPair<>("count", String.valueOf(i)));
        arrayList.add(new KVPair<>("cursor", String.valueOf(i2)));
        String httpGet = this.f3001h.httpGet("https://api.weibo.com/2/friendships/friends.json", arrayList, "/2/friendships/friends.json", m21842c());
        if (httpGet != null) {
            return new Hashon().fromJson(httpGet);
        }
        return null;
    }

    /* renamed from: c */
    public HashMap<String, Object> m21633c(int i, int i2, String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        String str2 = this.f2999f;
        if (str2 != null) {
            arrayList.add(new KVPair<>("access_token", str2));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        arrayList.add(new KVPair<>("count", String.valueOf(i)));
        arrayList.add(new KVPair<>("page", String.valueOf(i2)));
        String httpGet = this.f3001h.httpGet("https://api.weibo.com/2/friendships/friends/bilateral.json", arrayList, "/2/friendships/friends/bilateral.json", m21842c());
        if (httpGet != null) {
            return new Hashon().fromJson(httpGet);
        }
        return null;
    }

    /* renamed from: d */
    public HashMap<String, Object> m21629d(int i, int i2, String str) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("source", this.f2996c));
        String str2 = this.f2999f;
        if (str2 != null) {
            arrayList.add(new KVPair<>("access_token", str2));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair<>("uid", str));
        } else {
            arrayList.add(new KVPair<>("screen_name", str));
        }
        arrayList.add(new KVPair<>("count", String.valueOf(i)));
        arrayList.add(new KVPair<>("cursor", String.valueOf(i2)));
        String httpGet = this.f3001h.httpGet("https://api.weibo.com/2/friendships/followers.json", arrayList, "/2/friendships/followers.json", m21842c());
        if (httpGet != null) {
            return new Hashon().fromJson(httpGet);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public HashMap<String, Object> m21642a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
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
        arrayList.add(new KVPair<>("source", this.f2996c));
        String str4 = this.f2999f;
        if (str4 != null) {
            arrayList.add(new KVPair<>("access_token", str4));
        }
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            KVPair kVPair2 = null;
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                kVPair2 = new KVPair(entry2.getKey(), entry2.getValue());
            }
            kVPair = kVPair2;
        }
        try {
            if ("GET".equals(str2.toUpperCase())) {
                str3 = new NetworkHelper().httpGet(str, arrayList, null, null);
            } else {
                str3 = "POST".equals(str2.toUpperCase()) ? new NetworkHelper().httpPost(str, arrayList, kVPair, (ArrayList<KVPair<String>>) null, (NetworkHelper.NetworkTimeOut) null) : null;
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

    /* renamed from: d */
    public boolean m21630d() {
        Intent intent = new Intent();
        intent.setAction("com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER");
        String packageName = MobSDK.getContext().getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", this.f2996c);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", C1809a.m21612a(MobSDK.getContext(), packageName));
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
        MobSDK.getContext().sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
        return true;
    }
}
