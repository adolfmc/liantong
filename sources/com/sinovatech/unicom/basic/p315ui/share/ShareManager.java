package com.sinovatech.unicom.basic.p315ui.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.p099qq.C1849QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p315ui.adapter.ShareAdapter;
import com.sinovatech.unicom.basic.p315ui.share.ShareCreateImg;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.p318ui.GlideRequest;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.ShareManager */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareManager {
    private static final String TAG = "ShareManager";
    private static Handler handler = null;
    private static boolean hasShared = false;
    private static ShareListener listener = null;
    private static String localimg_path = "";
    private static ShareAdapter qudaoAdapter = null;
    public static String wapDownLoadURL = "https://u.10010.cn/download";
    public static String shareIconDefault = URLSet.shareDefaultIcon;
    public static String saveImg = "1";
    public static boolean isLandscape = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareManager$CreateHuaBaoListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CreateHuaBaoListener {
        void onResult(boolean z, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareManager$ShareListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ShareListener {
        void onCancel(String str);

        void onComplete(String str);

        void onError(String str, String str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void showShareDialog(android.app.Activity r25, com.sinovatech.unicom.basic.p315ui.share.WebMenuManager.WebMenuEntity r26, com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener r27) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.share.ShareManager.showShareDialog(android.app.Activity, com.sinovatech.unicom.basic.ui.share.WebMenuManager$WebMenuEntity, com.sinovatech.unicom.basic.ui.share.ShareManager$ShareListener):void");
    }

    public static void ShowShareDialog(Activity activity, String str, String str2, ShareListener shareListener) {
        ShowShareDialog(activity, null, str, str2, shareListener);
    }

    public static void ShowShareDialog(final Activity activity, PBWebView pBWebView, final String str, final String str2, final ShareListener shareListener) {
        if (pBWebView != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString("shareType");
                String optString2 = jSONObject.optString("shareQrcodeURL", "");
                String optString3 = jSONObject.optString("templetImg", "");
                String optString4 = jSONObject.optString("shareURL");
                String optString5 = jSONObject.optString("businessCode");
                String optString6 = jSONObject.optString("businessName");
                String optString7 = jSONObject.optString("provider");
                jSONObject.optString("isNewShare");
                if (TextUtils.equals("image", optString) || TextUtils.equals("longScreenshot", optString)) {
                    ShareCreateImg.createImg(activity, pBWebView, optString3, optString2, optString5, optString6, optString7, optString4, new ShareCreateImg.ShareImgOrScreenshot() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.1
                        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareCreateImg.ShareImgOrScreenshot
                        public void onSuccess() {
                            ShareManager.toShare(activity, str, str2, shareListener);
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                UIUtils.logE(e.getMessage());
            }
        }
        toShare(activity, str, str2, shareListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:1|(7:2|3|4|5|6|(1:10)|11)|(8:(8:20|21|22|(1:24)(1:41)|25|(1:36)|38|39)|21|22|(0)(0)|25|(5:27|29|31|33|36)|38|39)|46|48|49|50|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0175, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0176, code lost:
        r19 = r1;
        r4 = r2;
        r2 = "shortmessage,wechat,wechatmoments,qq,qzone,sinaweibo,wokouling,huabaofenxiang";
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void toShare(android.app.Activity r24, java.lang.String r25, java.lang.String r26, com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener r27) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.share.ShareManager.toShare(android.app.Activity, java.lang.String, java.lang.String, com.sinovatech.unicom.basic.ui.share.ShareManager$ShareListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x020e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void ShowShareDialog(final android.app.Activity r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, final java.lang.String r32, java.lang.String r33, final java.lang.String r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, java.lang.String r38, com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener r39, final java.lang.String r40, final java.lang.String r41, final java.lang.String r42, java.lang.String r43, java.lang.String r44, final java.lang.String r45, java.lang.String r46) {
        /*
            Method dump skipped, instructions count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShowShareDialog(android.app.Activity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.sinovatech.unicom.basic.ui.share.ShareManager$ShareListener, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void share(final Activity activity, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final boolean z, final String str12, final String str13) {
        try {
            if (!"wechat".equalsIgnoreCase(str) && !"wechatmoments".equalsIgnoreCase(str)) {
                share2(activity, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, null, z, str12, str13);
            }
            hasShared = false;
            Observable.timer(1500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.6
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    Glide.with(activity).asBitmap().load(ShareManager.shareIconDefault).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.6.1
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            if (ShareManager.hasShared) {
                                return;
                            }
                            boolean unused = ShareManager.hasShared = true;
                            ShareManager.share2(activity, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, bitmap, z, str12, str13);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            UIUtils.toast("分享图片链接错误，无法正确分享");
                        }
                    });
                }
            });
            Glide.with(activity).asBitmap().load(str5).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.7
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    if (ShareManager.hasShared) {
                        return;
                    }
                    boolean unused = ShareManager.hasShared = true;
                    ShareManager.share2(activity, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, bitmap, z, str12, str13);
                }
            });
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0447 A[Catch: Exception -> 0x04a0, TryCatch #0 {Exception -> 0x04a0, blocks: (B:6:0x000d, B:8:0x0013, B:10:0x0019, B:12:0x001f, B:14:0x0027, B:16:0x005b, B:18:0x0061, B:20:0x0068, B:22:0x0072, B:29:0x008c, B:31:0x009b, B:34:0x00a5, B:36:0x00ae, B:38:0x00b2, B:60:0x012d, B:199:0x0441, B:201:0x0447, B:202:0x0459, B:208:0x047c, B:205:0x046c, B:33:0x00a3, B:28:0x0089, B:39:0x00b7, B:41:0x00c4, B:42:0x00cb, B:44:0x00d8, B:46:0x00e7, B:49:0x00f1, B:51:0x00fa, B:53:0x00fe, B:48:0x00ef, B:54:0x0102, B:56:0x010e, B:57:0x0119, B:59:0x0125, B:61:0x0136, B:63:0x013f, B:65:0x0145, B:67:0x014c, B:69:0x0159, B:88:0x01c0, B:70:0x0160, B:72:0x016d, B:74:0x017c, B:77:0x0186, B:79:0x018f, B:81:0x0193, B:76:0x0184, B:82:0x0197, B:84:0x01a3, B:85:0x01ac, B:87:0x01b8, B:89:0x01c9, B:91:0x01d2, B:93:0x01da, B:101:0x0200, B:103:0x0206, B:105:0x020e, B:109:0x0219, B:111:0x0222, B:113:0x0226, B:115:0x0236, B:122:0x0265, B:116:0x023a, B:118:0x0247, B:119:0x024e, B:121:0x025a, B:123:0x026e, B:125:0x0276, B:127:0x0282, B:140:0x02c2, B:128:0x028e, B:132:0x029c, B:134:0x02a5, B:136:0x02a9, B:139:0x02bf, B:141:0x02cb, B:143:0x02d3, B:145:0x02df, B:147:0x02e8, B:146:0x02e5, B:148:0x02f1, B:150:0x02f9, B:152:0x030c, B:154:0x0314, B:156:0x0331, B:158:0x0338, B:160:0x0345, B:174:0x039e, B:161:0x034c, B:163:0x0359, B:165:0x036b, B:167:0x036f, B:168:0x0373, B:170:0x037f, B:171:0x038a, B:173:0x0396, B:175:0x03a6, B:177:0x03ae, B:179:0x03cb, B:181:0x03d2, B:183:0x03df, B:197:0x0438, B:184:0x03e6, B:186:0x03f3, B:188:0x0405, B:190:0x0409, B:191:0x040d, B:193:0x0419, B:194:0x0424, B:196:0x0430, B:95:0x01e1, B:97:0x01f3, B:98:0x01f8, B:24:0x0082), top: B:213:0x000d, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x046c A[Catch: Exception -> 0x04a0, TryCatch #0 {Exception -> 0x04a0, blocks: (B:6:0x000d, B:8:0x0013, B:10:0x0019, B:12:0x001f, B:14:0x0027, B:16:0x005b, B:18:0x0061, B:20:0x0068, B:22:0x0072, B:29:0x008c, B:31:0x009b, B:34:0x00a5, B:36:0x00ae, B:38:0x00b2, B:60:0x012d, B:199:0x0441, B:201:0x0447, B:202:0x0459, B:208:0x047c, B:205:0x046c, B:33:0x00a3, B:28:0x0089, B:39:0x00b7, B:41:0x00c4, B:42:0x00cb, B:44:0x00d8, B:46:0x00e7, B:49:0x00f1, B:51:0x00fa, B:53:0x00fe, B:48:0x00ef, B:54:0x0102, B:56:0x010e, B:57:0x0119, B:59:0x0125, B:61:0x0136, B:63:0x013f, B:65:0x0145, B:67:0x014c, B:69:0x0159, B:88:0x01c0, B:70:0x0160, B:72:0x016d, B:74:0x017c, B:77:0x0186, B:79:0x018f, B:81:0x0193, B:76:0x0184, B:82:0x0197, B:84:0x01a3, B:85:0x01ac, B:87:0x01b8, B:89:0x01c9, B:91:0x01d2, B:93:0x01da, B:101:0x0200, B:103:0x0206, B:105:0x020e, B:109:0x0219, B:111:0x0222, B:113:0x0226, B:115:0x0236, B:122:0x0265, B:116:0x023a, B:118:0x0247, B:119:0x024e, B:121:0x025a, B:123:0x026e, B:125:0x0276, B:127:0x0282, B:140:0x02c2, B:128:0x028e, B:132:0x029c, B:134:0x02a5, B:136:0x02a9, B:139:0x02bf, B:141:0x02cb, B:143:0x02d3, B:145:0x02df, B:147:0x02e8, B:146:0x02e5, B:148:0x02f1, B:150:0x02f9, B:152:0x030c, B:154:0x0314, B:156:0x0331, B:158:0x0338, B:160:0x0345, B:174:0x039e, B:161:0x034c, B:163:0x0359, B:165:0x036b, B:167:0x036f, B:168:0x0373, B:170:0x037f, B:171:0x038a, B:173:0x0396, B:175:0x03a6, B:177:0x03ae, B:179:0x03cb, B:181:0x03d2, B:183:0x03df, B:197:0x0438, B:184:0x03e6, B:186:0x03f3, B:188:0x0405, B:190:0x0409, B:191:0x040d, B:193:0x0419, B:194:0x0424, B:196:0x0430, B:95:0x01e1, B:97:0x01f3, B:98:0x01f8, B:24:0x0082), top: B:213:0x000d, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void share2(android.app.Activity r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, android.graphics.Bitmap r26, boolean r27, java.lang.String r28, java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 1193
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.share.ShareManager.share2(android.app.Activity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.graphics.Bitmap, boolean, java.lang.String, java.lang.String):void");
    }

    private static void setLiveShare(Platform.ShareParams shareParams, String str) {
        if (shareParams != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    String url = shareParams.getUrl();
                    if (TextUtils.isEmpty(url)) {
                        String titleUrl = shareParams.getTitleUrl();
                        if (!TextUtils.isEmpty(titleUrl) && titleUrl.contains("&liveShareInfo=")) {
                            shareParams.setTitleUrl(titleUrl + getShareChannel(str));
                        }
                    } else if (url.contains("&liveShareInfo=")) {
                        shareParams.setUrl(url + getShareChannel(str));
                    }
                }
            } catch (Exception e) {
                MsLogUtil.m7978e("setLiveShare()" + e.getMessage());
            }
        }
    }

    private static String getShareChannel(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -791770330) {
            if (str.equals("wechat")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 3616) {
            if (str.equals("qq")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 108102557) {
            if (hashCode == 1138523277 && str.equals("wechatmoments")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("qzone")) {
                c = 3;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "3";
            case 3:
                return "4";
            default:
                return "0";
        }
    }

    public static String getShareSDKKey(String str) {
        return str.equalsIgnoreCase(SinaWeibo.NAME) ? "sinaweibo" : str.equalsIgnoreCase(C1849QQ.NAME) ? "qq" : str.equalsIgnoreCase(Wechat.NAME) ? "wechat" : str.equalsIgnoreCase(WechatMoments.NAME) ? "wechatmoments" : str.equalsIgnoreCase(QZone.NAME) ? "qzone" : str.equalsIgnoreCase(ShortMessage.NAME) ? "shortmessage" : str.equalsIgnoreCase("jietufenxiang") ? "jietufenxiang" : "";
    }

    public static void showWokoulingDialog(final Activity activity, final String str, String str2, final String str3, final String str4, final String str5, final String str6) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            attributes.y = (int) (i3 * 0.25d);
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setGravity(48);
            View inflate = activity.getLayoutInflater().inflate(2131493433, (ViewGroup) null);
            EditText editText = (EditText) inflate.findViewById(2131299610);
            editText.setKeyListener(null);
            editText.setClickable(false);
            editText.setText(str);
            ((LinearLayout) inflate.findViewById(2131299611)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        if (!DeviceHelper.isWeixinInstall()) {
                            UIUtils.toast("您还没有安装微信");
                        } else {
                            Intent intent = new Intent();
                            ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                            intent.setAction("android.intent.action.MAIN");
                            intent.addCategory("android.intent.category.LAUNCHER");
                            intent.addFlags(268435456);
                            intent.setComponent(componentName);
                            activity.startActivityForResult(intent, 0);
                            if (dialog != null && dialog.isShowing()) {
                                dialog.cancel();
                            }
                            String substring = str.substring(str.indexOf("『") + 1, str.indexOf("』"));
                            HashMap hashMap = new HashMap();
                            hashMap.put("step", "1");
                            hashMap.put("mobileA", str3);
                            hashMap.put("shareUrl", str6);
                            hashMap.put("businessName", str4);
                            hashMap.put("businessCode", str5);
                            hashMap.put("shareType", "1");
                            hashMap.put("woCommand", substring);
                            hashMap.put("shareCompleteUrls", App.originalUrl);
                            ShareLogUtil.upLoadSharedByLog(hashMap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.10
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    if (i4 == 4 && keyEvent.getRepeatCount() == 0) {
                        dialogInterface.cancel();
                        return true;
                    }
                    return false;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.sinovatech.unicom.basic.ui.share.ShareManager$11] */
    public static void createHuaBao(final Activity activity, final String str, final String str2, final String str3, final String str4, final String str5, final Bitmap bitmap, final CreateHuaBaoListener createHuaBaoListener) {
        try {
            new AsyncTask<String, Integer, Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.11

                /* renamed from: pd */
                CustomePorgressDialog f18433pd;

                @Override // android.os.AsyncTask
                protected void onPreExecute() {
                    super.onPreExecute();
                    this.f18433pd = new CustomePorgressDialog(activity);
                    this.f18433pd.setMessage("正在生成画报 请稍候");
                    this.f18433pd.setCanceledOnTouchOutside(false);
                    this.f18433pd.setCancelable(false);
                    this.f18433pd.show();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Bitmap doInBackground(String... strArr) {
                    Bitmap bitmap2 = null;
                    try {
                        new QrCodeUtil();
                        bitmap2 = ImageMergeUtil.createSnapshotWithBottomLogo(activity, bitmap, QrCodeUtil.createCode(activity, str4));
                        ScreenShotUtils.saveImageToGallery(activity, bitmap2);
                        return bitmap2;
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.logE("ShareManager", e.getLocalizedMessage());
                        return bitmap2;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Bitmap bitmap2) {
                    CustomePorgressDialog customePorgressDialog;
                    super.onPostExecute((AsyncTaskC790511) bitmap2);
                    Activity activity2 = activity;
                    if (activity2 != null && !activity2.isFinishing() && (customePorgressDialog = this.f18433pd) != null && customePorgressDialog.isShowing()) {
                        this.f18433pd.dismiss();
                    }
                    if (bitmap2 != null) {
                        if (bitmap2 != null) {
                            bitmap2.isRecycled();
                        }
                        CreateHuaBaoListener createHuaBaoListener2 = createHuaBaoListener;
                        if (createHuaBaoListener2 != null) {
                            createHuaBaoListener2.onResult(true, str4);
                            return;
                        } else if (TextUtils.equals("0", ShareManager.saveImg)) {
                            activity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(ScreenShotUtils.LongWebviewCapturePicture))));
                            ShareLogUtil.shareUploadData(activity, str, str2, str3, str5, "1", "2", "");
                            UIUtils.toast("图片保存完成,快去分享给好友吧~");
                            ShareManager.saveImg = "1";
                            return;
                        } else {
                            Intent intent = new Intent(activity, SaveShareImgActivity.class);
                            intent.putExtra("mobileA", str);
                            intent.putExtra("businessName", str2);
                            intent.putExtra("businessCode", str3);
                            intent.putExtra("oldShareUrl", str5);
                            activity.startActivity(intent);
                            return;
                        }
                    }
                    UIUtils.toast("图片生成失败,请重试");
                    CreateHuaBaoListener createHuaBaoListener3 = createHuaBaoListener;
                    if (createHuaBaoListener3 != null) {
                        createHuaBaoListener3.onResult(false, "");
                    }
                }
            }.execute(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shareHuaBao(final Activity activity, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final CreateHuaBaoListener createHuaBaoListener) {
        boolean z = true;
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!SoulPermission.getInstance().checkSinglePermission((String) it.next()).isGranted()) {
                    z = false;
                    break;
                }
            }
            if (!z) {
                CustomDialogManager.show(activity, "存储权限申请", "画报分享为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。", true, "取消", "确定", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.12
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onBackKeyDown() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onClickCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onShow() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onClickOk() {
                        try {
                            new RxPermissions(activity).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.12.1
                                @Override // io.reactivex.functions.Consumer
                                public void accept(Boolean bool) throws Exception {
                                    if (bool.booleanValue()) {
                                        ShareManager.createHuaBao(activity, str, str2, str3, str4, str5, str6, createHuaBaoListener);
                                    } else {
                                        UIUtils.toast("您已拒绝该权限 请您到 手机－设置－应用管理－权限管理 中开启。");
                                    }
                                }
                            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.12.2
                                @Override // io.reactivex.functions.Consumer
                                public void accept(Throwable th) throws Exception {
                                }
                            });
                        } catch (Exception e) {
                            UIUtils.logE(e.getMessage());
                        }
                    }
                });
            } else {
                createHuaBao(activity, str, str2, str3, str4, str5, str6, createHuaBaoListener);
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createHuaBao(final Activity activity, final String str, final String str2, String str3, final String str4, final String str5, final String str6, final CreateHuaBaoListener createHuaBaoListener) {
        try {
            if (!str3.contains("http://") && !str3.contains("https://")) {
                Bitmap stringtoBitmap = ImageMergeUtil.stringtoBitmap(str3);
                if (stringtoBitmap != null) {
                    goCreateHuaBao(activity, str, str2, "", str4, str5, stringtoBitmap, str6, createHuaBaoListener);
                }
            }
            GlideApp.with(activity).asBitmap().load(str3).into((GlideRequest<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.13
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    ShareManager.goCreateHuaBao(activity, str, str2, "", str4, str5, bitmap, str6, createHuaBaoListener);
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    UIUtils.toast("分享图片链接错误，无法正确分享");
                }
            });
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void goCreateHuaBao(final Activity activity, final String str, final String str2, String str3, final String str4, final String str5, final Bitmap bitmap, String str6, final CreateHuaBaoListener createHuaBaoListener) {
        try {
            final String longUrl = ShareLogUtil.getLongUrl(str4, str, str6, str2, str5, "2");
            UIUtils.logD("ShareLogUtil", "画报longUrl = " + longUrl);
            ShareLogUtil.createShortLinkUrl(activity, longUrl, new ShareLogUtil.ShortUrlListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareManager.14
                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil.ShortUrlListener
                public void onShortUrlListener(boolean z, String str7) {
                    if (z) {
                        ShareManager.createHuaBao(activity, str, str2, str5, str7, str4, bitmap, createHuaBaoListener);
                    } else {
                        ShareManager.createHuaBao(activity, str, str2, str5, longUrl, str4, bitmap, createHuaBaoListener);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("分享图片链接错误，无法正确分享");
        }
    }

    public static void notifyQuDaoAdapter(List<String> list) {
        ShareAdapter shareAdapter = qudaoAdapter;
        if (shareAdapter != null) {
            shareAdapter.addData(list);
        }
    }
}
