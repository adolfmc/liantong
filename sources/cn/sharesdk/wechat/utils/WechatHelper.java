package cn.sharesdk.wechat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.ShareBypassApproval;
import cn.sharesdk.framework.utils.ShareSDKFileProvider;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.utils.WXLaunchMiniProgram;
import com.mob.MobSDK;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatHelper {

    /* renamed from: a */
    private static WechatHelper f3291a;

    /* renamed from: b */
    private WechatCore f3292b = new WechatCore();

    /* renamed from: c */
    private WechatHandler f3293c;

    /* renamed from: d */
    private String f3294d;

    /* renamed from: e */
    private String f3295e;

    /* renamed from: f */
    private boolean f3296f;

    /* renamed from: g */
    private int f3297g;

    /* renamed from: a */
    public static WechatHelper m21279a() {
        if (f3291a == null) {
            f3291a = new WechatHelper();
        }
        return f3291a;
    }

    /* renamed from: a */
    public void m21260a(String str) {
        this.f3295e = str;
    }

    /* renamed from: b */
    public void m21248b(String str) {
        this.f3294d = str;
    }

    /* renamed from: a */
    public void m21257a(boolean z) {
        this.f3296f = z;
    }

    /* renamed from: a */
    public void m21278a(int i) {
        this.f3297g = i;
    }

    private WechatHelper() {
    }

    /* renamed from: a */
    public void m21263a(WechatHandler wechatHandler) throws Throwable {
        this.f3293c = wechatHandler;
        AuthReq authReq = new AuthReq();
        authReq.f3253a = "snsapi_userinfo";
        authReq.f3254b = "sharesdk_wechat_auth";
        this.f3292b.m21296a((WechatReq) authReq, false);
    }

    /* renamed from: b */
    public void m21249b(WechatHandler wechatHandler) throws Throwable {
        this.f3293c = wechatHandler;
        SubscribeMessageReq subscribeMessageReq = new SubscribeMessageReq();
        Platform.ShareParams m21286a = wechatHandler.m21286a();
        String valueOf = String.valueOf(m21286a.getWxTemplateid());
        String valueOf2 = String.valueOf(m21286a.getWxReserved());
        int scence = m21286a.getScence();
        subscribeMessageReq.f3268b = valueOf;
        subscribeMessageReq.f3267a = scence;
        subscribeMessageReq.f3269c = valueOf2;
        this.f3292b.m21297a(subscribeMessageReq);
    }

    /* renamed from: b */
    public boolean m21256b() {
        return this.f3292b.m21302a();
    }

    /* renamed from: a */
    public void m21262a(WechatHandler wechatHandler, Platform.ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        Platform m21281b = wechatHandler.m21281b();
        String str = ((Integer) shareParams.get("scene", Integer.class)).intValue() == 1 ? "com.tencent.mm.ui.tools.ShareToTimeLineUI" : "com.tencent.mm.ui.tools.ShareImgUI";
        ShareBypassApproval shareBypassApproval = new ShareBypassApproval();
        shareBypassApproval.m21682a("com.tencent.mm", str);
        shareBypassApproval.m21685a(shareParams, m21281b);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ShareParams", shareParams);
        platformActionListener.onComplete(m21281b, 9, hashMap);
    }

    /* renamed from: c */
    public void m21243c(WechatHandler wechatHandler) throws Throwable {
        File parentFile;
        Platform m21281b = wechatHandler.m21281b();
        Platform.ShareParams m21286a = wechatHandler.m21286a();
        PlatformActionListener m21280c = wechatHandler.m21280c();
        int shareType = m21286a.getShareType();
        if (shareType == 11 && m21240e() < 620756993) {
            shareType = 4;
        }
        String title = m21286a.getTitle();
        String text = m21286a.getText();
        int scence = m21286a.getScence();
        String imagePath = m21286a.getImagePath();
        String imageFileProviderPath = m21286a.getImageFileProviderPath();
        String imageUrl = m21286a.getImageUrl();
        Bitmap imageData = m21286a.getImageData();
        String musicUrl = m21286a.getMusicUrl();
        String url = m21286a.getUrl();
        String filePath = m21286a.getFilePath();
        String extInfo = m21286a.getExtInfo();
        switch (shareType) {
            case 1:
                m21258a(title, text, scence, wechatHandler);
                return;
            case 2:
                if (imagePath != null && imagePath.length() > 0) {
                    m21254b(MobSDK.getContext(), title, text, imagePath, scence, wechatHandler);
                    return;
                } else if (imageFileProviderPath != null && imageFileProviderPath.length() > 0) {
                    m21274a(MobSDK.getContext(), title, text, imageFileProviderPath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21275a(MobSDK.getContext(), title, text, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    String downloadBitmap = BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl);
                    try {
                        if (!TextUtils.isEmpty(downloadBitmap) && (parentFile = new File(downloadBitmap).getParentFile()) != null && parentFile.isDirectory()) {
                            File file = new File(parentFile.getAbsolutePath(), ".nomedia");
                            if (!file.exists() || !file.isFile()) {
                                file.createNewFile();
                            }
                        }
                    } catch (Throwable th) {
                        SSDKLog m21740b = SSDKLog.m21740b();
                        m21740b.m21744a("when share iamge wechat that create nomedia catch " + th, new Object[0]);
                    }
                    m21254b(MobSDK.getContext(), title, text, downloadBitmap, scence, wechatHandler);
                    return;
                } else {
                    m21254b(MobSDK.getContext(), title, text, "", scence, wechatHandler);
                    return;
                }
            case 3:
            case 10:
            default:
                if (m21280c != null) {
                    m21280c.onError(m21281b, 9, new IllegalArgumentException("shareType = " + shareType));
                    return;
                }
                return;
            case 4:
                String shortLintk = m21281b.getShortLintk(url, false);
                wechatHandler.m21286a().setUrl(shortLintk);
                if (imagePath != null && imagePath.length() > 0) {
                    m21252b(MobSDK.getContext(), title, text, shortLintk, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21253b(MobSDK.getContext(), title, text, shortLintk, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21252b(MobSDK.getContext(), title, text, shortLintk, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21252b(MobSDK.getContext(), title, text, shortLintk, "", scence, wechatHandler);
                    return;
                }
            case 5:
                String shortLintk2 = m21281b.getShortLintk(musicUrl + " " + url, false);
                String str = shortLintk2.split(" ")[0];
                String str2 = shortLintk2.split(" ")[1];
                if (imagePath != null && imagePath.length() > 0) {
                    m21270a(MobSDK.getContext(), title, text, str, str2, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21271a(MobSDK.getContext(), title, text, str, str2, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21270a(MobSDK.getContext(), title, text, str, str2, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21270a(MobSDK.getContext(), title, text, str, str2, "", scence, wechatHandler);
                    return;
                }
            case 6:
                String shortLintk3 = m21281b.getShortLintk(url, false);
                wechatHandler.m21286a().setUrl(shortLintk3);
                if (imagePath != null && imagePath.length() > 0) {
                    m21272a(MobSDK.getContext(), title, text, shortLintk3, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21273a(MobSDK.getContext(), title, text, shortLintk3, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21272a(MobSDK.getContext(), title, text, shortLintk3, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21272a(MobSDK.getContext(), title, text, shortLintk3, "", scence, wechatHandler);
                    return;
                }
            case 7:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_APP");
                }
                if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_APP");
                }
                if (imagePath != null && imagePath.length() > 0) {
                    m21250b(MobSDK.getContext(), title, text, filePath, extInfo, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21251b(MobSDK.getContext(), title, text, filePath, extInfo, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21250b(MobSDK.getContext(), title, text, filePath, extInfo, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21250b(MobSDK.getContext(), title, text, filePath, extInfo, "", scence, wechatHandler);
                    return;
                }
            case 8:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_FILE");
                }
                if (imagePath != null && imagePath.length() > 0) {
                    m21244c(MobSDK.getContext(), title, text, filePath, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21245c(MobSDK.getContext(), title, text, filePath, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21244c(MobSDK.getContext(), title, text, filePath, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21244c(MobSDK.getContext(), title, text, m21261a(new File(filePath)), "", scence, wechatHandler);
                    return;
                }
            case 9:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_EMOJI");
                }
                if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SHARE_EMOJI");
                }
                if (imagePath != null && imagePath.length() > 0) {
                    m21246c(MobSDK.getContext(), title, text, imagePath, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21246c(MobSDK.getContext(), title, text, new NetworkHelper().downloadCache(MobSDK.getContext(), imageUrl, "images", true, null), scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21255b(MobSDK.getContext(), title, text, imageData, scence, wechatHandler);
                    return;
                } else {
                    m21246c(MobSDK.getContext(), title, text, "", scence, wechatHandler);
                    return;
                }
            case 11:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_WXMINIPROGRAM");
                }
                if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_WXMINIPROGRAM");
                }
                if (TextUtils.isEmpty(this.f3294d)) {
                    m21280c.onError(m21281b, 9, new Throwable("checkArgs fail, UserName or Path is invalid"));
                    return;
                }
                String shortLintk4 = m21281b.getShortLintk(url, false);
                wechatHandler.m21286a().setUrl(shortLintk4);
                if (imagePath != null && imagePath.length() > 0) {
                    m21268a(MobSDK.getContext(), shortLintk4, this.f3294d, this.f3295e, title, text, imagePath, scence, wechatHandler);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m21269a(MobSDK.getContext(), shortLintk4, this.f3294d, this.f3295e, title, text, imageData, scence, wechatHandler);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m21268a(MobSDK.getContext(), shortLintk4, this.f3294d, this.f3295e, title, text, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, wechatHandler);
                    return;
                } else {
                    m21268a(MobSDK.getContext(), shortLintk4, this.f3294d, this.f3295e, title, text, "", scence, wechatHandler);
                    return;
                }
            case 12:
                if (TextUtils.isEmpty(this.f3294d) || TextUtils.isEmpty(this.f3295e)) {
                    m21280c.onError(m21281b, 9, new Throwable("checkArgs fail, UserName or Path is invalid"));
                    return;
                } else {
                    m21259a(this.f3294d, this.f3295e);
                    return;
                }
        }
    }

    /* renamed from: a */
    private void m21258a(String str, String str2, int i, WechatHandler wechatHandler) throws Throwable {
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = str2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str2;
        m21265a(wXMediaMessage, "text", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21274a(Context context, String str, String str2, String str3, int i, WechatHandler wechatHandler) throws Throwable {
        WXImageObject wXImageObject = new WXImageObject();
        if (m21239f()) {
            if (m21238g()) {
                String m21261a = m21261a(new File(str3));
                wXImageObject.imagePath = m21261a;
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21743a("ShareSDK share file with FileProvider path is: " + m21261a);
            }
        } else if (wechatHandler.m21280c() != null) {
            wechatHandler.m21280c().onError(wechatHandler.m21281b(), 9, new Throwable("Wecaht Version is not new"));
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        if (i != 0) {
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.thumbData = m21267a(context, str3, false);
        m21265a(wXMediaMessage, "img", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21254b(Context context, String str, String str2, String str3, int i, WechatHandler wechatHandler) throws Throwable {
        WXImageObject wXImageObject = new WXImageObject();
        wXImageObject.imagePath = m21261a(new File(str3));
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        if (i != 0) {
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.thumbData = m21267a(context, str3, false);
        m21265a(wXMediaMessage, "img", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21275a(Context context, String str, String str2, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXImageObject wXImageObject = new WXImageObject();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXImageObject.imageData = byteArrayOutputStream.toByteArray();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        if (i != 0) {
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.thumbData = m21276a(context, bitmap, false);
        m21265a(wXMediaMessage, "img", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21270a(Context context, String str, String str2, String str3, String str4, String str5, int i, WechatHandler wechatHandler) throws Throwable {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = m21267a(context, str5, false);
        m21265a(wXMediaMessage, "music", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21271a(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = m21276a(context, bitmap, false);
        m21265a(wXMediaMessage, "music", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21272a(Context context, String str, String str2, String str3, String str4, int i, WechatHandler wechatHandler) throws Throwable {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = m21267a(context, str4, false);
        m21265a(wXMediaMessage, "video", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21273a(Context context, String str, String str2, String str3, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = m21276a(context, bitmap, false);
        m21265a(wXMediaMessage, "video", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21252b(Context context, String str, String str2, String str3, String str4, int i, WechatHandler wechatHandler) throws Throwable {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (str4 != null && new File(str4).exists()) {
            wXMediaMessage.thumbData = m21267a(context, str4, false);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            }
            if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > 32768");
            }
        }
        m21265a(wXMediaMessage, "webpage", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21253b(Context context, String str, String str2, String str3, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (bitmap != null && !bitmap.isRecycled()) {
            wXMediaMessage.thumbData = m21276a(context, bitmap, false);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            }
            if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > 32768");
            }
        }
        m21265a(wXMediaMessage, "webpage", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21250b(Context context, String str, String str2, String str3, String str4, String str5, int i, WechatHandler wechatHandler) throws Throwable {
        WXAppExtendObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = m21267a(context, str5, false);
        m21265a(wXMediaMessage, "appdata", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21251b(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXAppExtendObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = m21276a(context, bitmap, false);
        m21265a(wXMediaMessage, "appdata", i, wechatHandler);
    }

    /* renamed from: c */
    private void m21244c(Context context, String str, String str2, String str3, String str4, int i, WechatHandler wechatHandler) throws Throwable {
        WXFileObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        if (!TextUtils.isEmpty(str)) {
            wXMediaMessage.title = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.mediaObject = wXFileObject;
        if (!TextUtils.isEmpty(str4)) {
            wXMediaMessage.thumbData = m21267a(context, str4, false);
        }
        m21265a(wXMediaMessage, "filedata", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21269a(Context context, String str, String str2, String str3, String str4, String str5, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        String[] split;
        String str6;
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = str;
        if (!TextUtils.isEmpty(str2) && str2.endsWith("@app")) {
            wXMiniProgramObject.userName = str2;
        } else {
            wXMiniProgramObject.userName = str2 + "@app";
        }
        if (!TextUtils.isEmpty(str3)) {
            if (str3.split("\\?").length > 1) {
                str6 = split[0] + ".html?" + split[1];
            } else {
                str6 = split[0] + ".html";
            }
            wXMiniProgramObject.path = str6;
            wXMiniProgramObject.withShareTicket = this.f3296f;
            wXMiniProgramObject.miniprogramType = this.f3297g;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str4;
        wXMediaMessage.mediaObject = wXMiniProgramObject;
        wXMediaMessage.description = str5;
        if (bitmap != null && !bitmap.isRecycled()) {
            wXMediaMessage.thumbData = m21276a(context, bitmap, true);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            }
            if (wXMediaMessage.thumbData.length > 131072) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > 131072");
            }
        }
        m21265a(wXMediaMessage, "webpage", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21268a(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, WechatHandler wechatHandler) throws Throwable {
        String[] split;
        String str7;
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.miniprogramType = this.f3297g;
        wXMiniProgramObject.webpageUrl = str;
        if (!TextUtils.isEmpty(str2) && str2.endsWith("@app")) {
            wXMiniProgramObject.userName = str2;
        } else {
            wXMiniProgramObject.userName = str2 + "@app";
        }
        if (!TextUtils.isEmpty(str3)) {
            if (str3.split("\\?").length > 1) {
                str7 = split[0] + ".html?" + split[1];
            } else {
                str7 = split[0] + ".html";
            }
            wXMiniProgramObject.path = str7;
            wXMiniProgramObject.withShareTicket = this.f3296f;
            wXMiniProgramObject.miniprogramType = this.f3297g;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str4;
        wXMediaMessage.mediaObject = wXMiniProgramObject;
        wXMediaMessage.description = str5;
        wXMediaMessage.thumbData = m21267a(context, str6, true);
        m21265a(wXMediaMessage, "miniProgram", i, wechatHandler);
    }

    /* renamed from: a */
    private void m21259a(String str, String str2) throws Throwable {
        WXLaunchMiniProgram.C1872a c1872a = new WXLaunchMiniProgram.C1872a();
        c1872a.f3280a = str;
        c1872a.f3281b = str2;
        c1872a.f3282c = this.f3297g;
        this.f3292b.m21290b(c1872a);
    }

    /* renamed from: c */
    private void m21245c(Context context, String str, String str2, String str3, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXFileObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = m21276a(context, bitmap, false);
        m21265a(wXMediaMessage, "filedata", i, wechatHandler);
    }

    /* renamed from: c */
    private void m21246c(Context context, String str, String str2, String str3, int i, WechatHandler wechatHandler) throws Throwable {
        WXEmojiObject wXEmojiObject = new WXEmojiObject();
        if (Build.VERSION.SDK_INT >= 24) {
            wXEmojiObject.emojiPath = m21261a(new File(str3));
        } else {
            wXEmojiObject.emojiPath = str3;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m21267a(context, str3, false);
        m21265a(wXMediaMessage, "emoji", i, wechatHandler);
    }

    /* renamed from: b */
    private void m21255b(Context context, String str, String str2, Bitmap bitmap, int i, WechatHandler wechatHandler) throws Throwable {
        WXEmojiObject wXEmojiObject = new WXEmojiObject();
        byte[] m21276a = m21276a(context, bitmap, false);
        wXEmojiObject.emojiData = m21276a;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m21276a;
        m21265a(wXMediaMessage, "emoji", i, wechatHandler);
    }

    /* renamed from: a */
    private byte[] m21267a(Context context, String str, boolean z) throws Throwable {
        if (!new File(str).exists()) {
            throw new FileNotFoundException();
        }
        return m21277a(context, BitmapHelper.getBitmap(str), BitmapHelper.getBmpFormat(str), z);
    }

    /* renamed from: a */
    private byte[] m21276a(Context context, Bitmap bitmap, boolean z) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
        return m21277a(context, bitmap, Bitmap.CompressFormat.PNG, z);
    }

    /* renamed from: a */
    private byte[] m21277a(Context context, Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        int i = z ? 131072 : 32768;
        while (length > i) {
            bitmap = m21266a(bitmap, length / i);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, 100, byteArrayOutputStream2);
            byteArrayOutputStream2.flush();
            byteArrayOutputStream2.close();
            byteArray = byteArrayOutputStream2.toByteArray();
            length = byteArray.length;
        }
        return byteArray;
    }

    /* renamed from: a */
    private Bitmap m21266a(Bitmap bitmap, double d) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d);
        return Bitmap.createScaledBitmap(bitmap, (int) (width / sqrt), (int) (height / sqrt), true);
    }

    /* renamed from: c */
    public boolean m21242c(String str) {
        return this.f3292b.m21295a(str);
    }

    /* renamed from: a */
    public boolean m21264a(WechatHandlerActivity wechatHandlerActivity) {
        return this.f3292b.m21298a(wechatHandlerActivity, this.f3293c);
    }

    /* renamed from: c */
    public boolean m21247c() {
        return this.f3292b.m21292b();
    }

    /* renamed from: d */
    public boolean m21241d() {
        return this.f3292b.m21288c();
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: e */
    public final int m21240e() {
        if (new Wechat().isClientValid()) {
            try {
                return AppUtils.m21713c("com.tencent.mm", 128).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
            } catch (Exception e) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("WechatHelper getWXAppSupportAPI() get from metaData failed : " + e, new Object[0]);
                return 0;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private void m21265a(WXMediaMessage wXMediaMessage, String str, int i, WechatHandler wechatHandler) throws Throwable {
        Class<?> cls;
        DeviceHelper deviceHelper = DeviceHelper.getInstance(MobSDK.getContext());
        String str2 = deviceHelper.getPackageName() + ".wxapi.WXEntryActivity";
        try {
            cls = Class.forName(str2);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            cls = null;
        }
        if (cls != null && !WechatHandlerActivity.class.isAssignableFrom(cls)) {
            new Throwable(str2 + " does not extend from " + WechatHandlerActivity.class.getName()).printStackTrace();
        }
        SendMessageReq sendMessageReq = new SendMessageReq();
        sendMessageReq.f3298e = str + System.currentTimeMillis();
        sendMessageReq.f3243a = wXMediaMessage;
        sendMessageReq.f3244b = i;
        this.f3293c = wechatHandler;
        this.f3292b.m21296a(sendMessageReq, wXMediaMessage.mediaObject instanceof WXMiniProgramObject);
    }

    /* renamed from: f */
    public boolean m21239f() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
            SSDKLog.m21740b().m21738b("wechat versionName ==>> " + str);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            str = "0";
        }
        String[] split = str.split("_")[0].split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = ResHelper.parseInt(split[i]);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
                iArr[i] = 0;
            }
        }
        return iArr.length >= 3 && ((iArr[0] == 7 && iArr[1] == 0 && iArr[2] >= 13) || iArr[0] >= 8);
    }

    /* renamed from: g */
    public boolean m21238g() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* renamed from: a */
    public String m21261a(File file) {
        String str;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            str = MobSDK.getContext().getPackageName();
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("get packagename is catch: " + th, new Object[0]);
            str = null;
        }
        if (str != null) {
            Context context = MobSDK.getContext();
            Uri m21729a = ShareSDKFileProvider.m21729a(context, str + ".cn.sharesdk.ShareSDKFileProvider", file);
            MobSDK.getContext().grantUriPermission("com.tencent.mm", m21729a, 1);
            return m21729a.toString();
        }
        return null;
    }
}
