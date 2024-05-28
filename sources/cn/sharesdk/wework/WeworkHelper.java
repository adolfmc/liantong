package cn.sharesdk.wework;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.model.WKMediaFile;
import cn.sharesdk.wework.model.WKMediaImage;
import cn.sharesdk.wework.model.WKMediaLink;
import cn.sharesdk.wework.model.WKMediaText;
import cn.sharesdk.wework.model.WKMediaVideo;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeworkHelper {

    /* renamed from: a */
    private static volatile WeworkHelper f3323a;

    /* renamed from: b */
    private String f3324b;

    /* renamed from: c */
    private String f3325c;

    /* renamed from: d */
    private String f3326d;

    /* renamed from: e */
    private String f3327e;

    /* renamed from: f */
    private String f3328f;

    /* renamed from: g */
    private SSDKNetworkHelper f3329g = SSDKNetworkHelper.getInstance();

    /* renamed from: h */
    private int f3330h;

    public WeworkHelper(int i) {
        this.f3330h = i;
    }

    /* renamed from: a */
    public static WeworkHelper m21193a(int i) {
        synchronized (WeworkHelper.class) {
            if (f3323a == null) {
                synchronized (WeworkHelper.class) {
                    if (f3323a == null) {
                        f3323a = new WeworkHelper(i);
                    }
                }
            }
        }
        return f3323a;
    }

    /* renamed from: a */
    public static boolean m21194a() {
        return AppUtils.m21718a("com.tencent.wework", 0);
    }

    /* renamed from: a */
    public void m21188a(String str) {
        this.f3324b = str;
    }

    /* renamed from: b */
    public void m21184b(String str) {
        this.f3325c = str;
    }

    /* renamed from: c */
    public void m21182c(String str) {
        this.f3326d = str;
    }

    /* renamed from: d */
    public void m21180d(String str) {
        this.f3327e = str;
    }

    /* renamed from: e */
    public void m21178e(String str) {
        this.f3328f = str;
    }

    /* renamed from: a */
    public void m21190a(AuthorizeListener authorizeListener) {
        if (!TextUtils.isEmpty(this.f3325c) && !TextUtils.isEmpty(this.f3324b)) {
            m21187a(this.f3325c, this.f3324b, authorizeListener);
        } else if (authorizeListener != null) {
            authorizeListener.onError(new Throwable("appId or secret is null"));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.wework.b$1] */
    /* renamed from: a */
    private void m21187a(final String str, final String str2, final AuthorizeListener authorizeListener) {
        new Thread() { // from class: cn.sharesdk.wework.b.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                    arrayList.add(new KVPair<>("corpid", str));
                    arrayList.add(new KVPair<>("corpsecret", str2));
                    HashMap fromJson = new Hashon().fromJson(WeworkHelper.this.f3329g.httpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken", arrayList, "cgi-bin/gettoken", WeworkHelper.this.f3330h));
                    if (fromJson.containsKey("errcode") && ((Integer) fromJson.get("errcode")).intValue() != 0) {
                        if (authorizeListener != null) {
                            authorizeListener.onError(new Throwable(new Hashon().fromHashMap(fromJson)));
                        }
                    } else if (fromJson.containsKey("access_token")) {
                        String valueOf = String.valueOf(fromJson.get("access_token"));
                        if (!TextUtils.isEmpty(valueOf)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("access_token", valueOf);
                            bundle.putString("expires_in", String.valueOf(fromJson.get("expires_in")));
                            if (authorizeListener != null) {
                                authorizeListener.onComplete(bundle);
                            }
                        } else if (authorizeListener != null) {
                            authorizeListener.onError(new Throwable("Authorize token is empty"));
                        }
                    }
                } catch (Exception e) {
                    SSDKLog.m21740b().m21742a(e);
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [cn.sharesdk.wework.b$2] */
    /* renamed from: a */
    public void m21192a(final Platform platform, final String str, final String str2, final PlatformActionListener platformActionListener) {
        new Thread() { // from class: cn.sharesdk.wework.b.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ArrayList<KVPair<String>> arrayList = new ArrayList<>();
                    arrayList.add(new KVPair<>("access_token", str));
                    arrayList.add(new KVPair<>("code", str2));
                    String httpGet = WeworkHelper.this.f3329g.httpGet("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo", arrayList, "/cgi-bin/user/getuserinfo", WeworkHelper.this.f3330h);
                    if (TextUtils.isEmpty(httpGet)) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(platform, 8, new Throwable());
                            return;
                        }
                        return;
                    }
                    HashMap<String, Object> fromJson = new Hashon().fromJson(httpGet);
                    if (fromJson.containsKey("errcode") && ((Integer) fromJson.get("errcode")).intValue() != 0) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(platform, 8, new Throwable(new Hashon().fromHashMap(fromJson)));
                            return;
                        }
                        return;
                    }
                    String valueOf = String.valueOf(fromJson.get("UserId"));
                    if (!TextUtils.isEmpty(valueOf)) {
                        platform.getDb().putUserId(valueOf);
                    }
                    HashMap<String, Object> hashMap = new HashMap<>();
                    if (!TextUtils.isEmpty(valueOf)) {
                        hashMap.put("UserId", valueOf);
                        platformActionListener.onComplete(platform, 8, hashMap);
                        return;
                    }
                    platformActionListener.onComplete(platform, 8, fromJson);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                    PlatformActionListener platformActionListener2 = platformActionListener;
                    if (platformActionListener2 != null) {
                        platformActionListener2.onError(platform, 8, th);
                    }
                }
            }
        }.start();
    }

    /* renamed from: a */
    public void m21191a(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, WeworkCore weworkCore) {
        try {
            WKMediaText wKMediaText = new WKMediaText(shareParams.getText());
            wKMediaText.f3347c = this.f3327e;
            wKMediaText.f3346b = this.f3328f;
            wKMediaText.f3353l = this.f3325c;
            wKMediaText.f3354m = this.f3326d;
            weworkCore.m21206a(wKMediaText, 1);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("Junk business WeChat Text sharing: " + e, new Object[0]);
        }
    }

    /* renamed from: b */
    public void m21186b(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, WeworkCore weworkCore) {
        try {
            WKMediaImage wKMediaImage = new WKMediaImage();
            wKMediaImage.f3365p = shareParams.getText();
            wKMediaImage.f3364k = shareParams.getImagePath();
            wKMediaImage.f3347c = this.f3327e;
            wKMediaImage.f3346b = this.f3328f;
            wKMediaImage.f3353l = this.f3325c;
            wKMediaImage.f3354m = this.f3326d;
            weworkCore.m21206a(wKMediaImage, 2);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("Junk business WeChat photo sharing: " + e, new Object[0]);
        }
    }

    /* renamed from: c */
    public void m21183c(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, WeworkCore weworkCore) {
        try {
            WKMediaFile wKMediaFile = new WKMediaFile();
            wKMediaFile.f3365p = shareParams.getText();
            wKMediaFile.f3364k = shareParams.getFilePath();
            wKMediaFile.f3347c = this.f3327e;
            wKMediaFile.f3346b = this.f3328f;
            wKMediaFile.f3353l = this.f3325c;
            wKMediaFile.f3354m = this.f3326d;
            weworkCore.m21206a(wKMediaFile, 3);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("Junk business WeChat file sharing: " + e, new Object[0]);
        }
    }

    /* renamed from: d */
    public void m21181d(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, WeworkCore weworkCore) {
        try {
            WKMediaVideo wKMediaVideo = new WKMediaVideo();
            wKMediaVideo.f3365p = shareParams.getText();
            wKMediaVideo.f3364k = shareParams.getFilePath();
            wKMediaVideo.f3347c = this.f3327e;
            wKMediaVideo.f3346b = this.f3328f;
            wKMediaVideo.f3353l = this.f3325c;
            wKMediaVideo.f3354m = this.f3326d;
            weworkCore.m21206a(wKMediaVideo, 4);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("Junk business WeChat Video sharing: " + e, new Object[0]);
        }
    }

    /* renamed from: e */
    public void m21179e(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, WeworkCore weworkCore) {
        try {
            WKMediaLink wKMediaLink = new WKMediaLink();
            wKMediaLink.f3369k = shareParams.getImageUrl();
            wKMediaLink.f3368j = shareParams.getUrl();
            wKMediaLink.f3372r = shareParams.getTitle();
            wKMediaLink.f3373s = shareParams.getText();
            wKMediaLink.f3347c = this.f3327e;
            wKMediaLink.f3346b = this.f3328f;
            wKMediaLink.f3353l = this.f3325c;
            wKMediaLink.f3354m = this.f3326d;
            weworkCore.m21206a(wKMediaLink, 5);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("Junk business WeChat Video sharing: " + e, new Object[0]);
        }
    }
}
