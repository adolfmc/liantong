package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXAuthHelper {

    /* renamed from: a */
    private String f3270a;

    /* renamed from: b */
    private String f3271b;

    /* renamed from: c */
    private SSDKNetworkHelper f3272c = SSDKNetworkHelper.getInstance();

    /* renamed from: d */
    private Platform f3273d;

    /* renamed from: e */
    private int f3274e;

    public WXAuthHelper(Platform platform, int i) {
        this.f3273d = platform;
        this.f3274e = i;
    }

    /* renamed from: a */
    public void m21310a(String str, String str2) {
        this.f3270a = str;
        this.f3271b = str2;
    }

    /* renamed from: a */
    public void m21316a(Bundle bundle, AuthorizeListener authorizeListener) {
        String string = bundle.getString("_wxapi_sendauth_resp_url");
        if (TextUtils.isEmpty(string)) {
            if (authorizeListener != null) {
                authorizeListener.onError(null);
                return;
            }
            return;
        }
        int indexOf = string.indexOf("://oauth?");
        if (indexOf >= 0) {
            string = string.substring(indexOf + 1);
        }
        try {
            m21311a(ResHelper.urlToBundle(string).getString("code"), authorizeListener);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            if (authorizeListener != null) {
                authorizeListener.onError(th);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [cn.sharesdk.wechat.utils.h$1] */
    /* renamed from: a */
    private void m21311a(final String str, final AuthorizeListener authorizeListener) throws Throwable {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("getAuthorizeToken ==>> " + str, new Object[0]);
        new Thread() { // from class: cn.sharesdk.wechat.utils.h.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                    arrayList.add(new KVPair<>("appid", WXAuthHelper.this.f3270a));
                    arrayList.add(new KVPair<>("secret", WXAuthHelper.this.f3271b));
                    arrayList.add(new KVPair<>("code", str));
                    arrayList.add(new KVPair<>("grant_type", "authorization_code"));
                    String httpGet = WXAuthHelper.this.f3272c.httpGet("https://api.weixin.qq.com/sns/oauth2/access_token", arrayList, "/sns/oauth2/access_token", WXAuthHelper.this.f3274e);
                    if (TextUtils.isEmpty(httpGet)) {
                        authorizeListener.onError(new Throwable("Authorize token is empty"));
                    } else if (!httpGet.contains("errcode")) {
                        WXAuthHelper.this.m21312a(httpGet);
                        authorizeListener.onComplete(null);
                    } else if (authorizeListener != null) {
                        authorizeListener.onError(new Throwable(httpGet));
                    }
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public boolean m21317a() {
        String str = this.f3273d.getDb().get("refresh_token");
        if (TextUtils.isEmpty(this.f3270a) || TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("appid", this.f3270a));
        arrayList.add(new KVPair<>("refresh_token", str));
        arrayList.add(new KVPair<>("grant_type", "refresh_token"));
        try {
            String httpGet = this.f3272c.httpGet("https://api.weixin.qq.com/sns/oauth2/refresh_token", arrayList, "/sns/oauth2/refresh_token", this.f3274e);
            if (TextUtils.isEmpty(httpGet) || httpGet.contains("errcode")) {
                return false;
            }
            m21312a(httpGet);
            return true;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.wechat.utils.h$2] */
    /* renamed from: a */
    public void m21315a(final PlatformActionListener platformActionListener) throws Throwable {
        new Thread() { // from class: cn.sharesdk.wechat.utils.h.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                    arrayList.add(new KVPair<>("access_token", WXAuthHelper.this.f3273d.getDb().getToken()));
                    arrayList.add(new KVPair<>("openid", WXAuthHelper.this.f3273d.getDb().get("openid")));
                    arrayList.add(new KVPair<>("lang", "zh_CN"));
                    String httpGet = WXAuthHelper.this.f3272c.httpGet("https://api.weixin.qq.com/sns/userinfo", arrayList, "/sns/userinfo", WXAuthHelper.this.f3274e);
                    if (TextUtils.isEmpty(httpGet)) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(WXAuthHelper.this.f3273d, 8, new Throwable());
                            return;
                        }
                        return;
                    }
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("getUserInfo ==>>" + httpGet, new Object[0]);
                    HashMap<String, Object> fromJson = new Hashon().fromJson(httpGet);
                    if (fromJson.containsKey("errcode") && ((Integer) fromJson.get("errcode")).intValue() != 0) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(WXAuthHelper.this.f3273d, 8, new Throwable(new Hashon().fromHashMap(fromJson)));
                            return;
                        }
                        return;
                    }
                    String valueOf = String.valueOf(fromJson.get("openid"));
                    String valueOf2 = String.valueOf(fromJson.get("nickname"));
                    int parseInt = ResHelper.parseInt(String.valueOf(fromJson.get("sex")));
                    String valueOf3 = String.valueOf(fromJson.get("province"));
                    String valueOf4 = String.valueOf(fromJson.get("city"));
                    String valueOf5 = String.valueOf(fromJson.get("country"));
                    String valueOf6 = String.valueOf(fromJson.get("headimgurl"));
                    String valueOf7 = String.valueOf(fromJson.get("unionid"));
                    WXAuthHelper.this.f3273d.getDb().put("nickname", valueOf2);
                    if (parseInt == 1) {
                        WXAuthHelper.this.f3273d.getDb().put("gender", "0");
                    } else if (parseInt == 2) {
                        WXAuthHelper.this.f3273d.getDb().put("gender", "1");
                    } else {
                        WXAuthHelper.this.f3273d.getDb().put("gender", "2");
                    }
                    WXAuthHelper.this.f3273d.getDb().putUserId(valueOf);
                    WXAuthHelper.this.f3273d.getDb().put("icon", valueOf6);
                    WXAuthHelper.this.f3273d.getDb().put("province", valueOf3);
                    WXAuthHelper.this.f3273d.getDb().put("city", valueOf4);
                    WXAuthHelper.this.f3273d.getDb().put("country", valueOf5);
                    WXAuthHelper.this.f3273d.getDb().put("openid", valueOf);
                    WXAuthHelper.this.f3273d.getDb().put("unionid", valueOf7);
                    if (WXAuthHelper.this.f3273d.getDb().get("userTags") != null) {
                        fromJson.put("userTags", WXAuthHelper.this.f3273d.getDb().get("userTags"));
                    }
                    platformActionListener.onComplete(WXAuthHelper.this.f3273d, 8, fromJson);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21312a(String str) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("wechat getAuthorizeToken ==>>" + str, new Object[0]);
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get("access_token"));
        String valueOf2 = String.valueOf(fromJson.get("refresh_token"));
        String valueOf3 = String.valueOf(fromJson.get("expires_in"));
        this.f3273d.getDb().put("openid", String.valueOf(fromJson.get("openid")));
        this.f3273d.getDb().putExpiresIn(Long.valueOf(valueOf3).longValue());
        this.f3273d.getDb().putToken(valueOf);
        this.f3273d.getDb().put("refresh_token", valueOf2);
    }
}
