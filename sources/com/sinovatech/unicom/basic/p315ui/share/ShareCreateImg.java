package com.sinovatech.unicom.basic.p315ui.share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.ShareCreateImg */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareCreateImg {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareCreateImg$ShareImgOrScreenshot */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ShareImgOrScreenshot {
        void onSuccess();
    }

    public static void createImg(Activity activity, PBWebView pBWebView, String str, String str2, String str3, String str4, String str5, String str6, ShareImgOrScreenshot shareImgOrScreenshot) {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(activity).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe(new C78851(str, activity, shareImgOrScreenshot, str2, str3, pBWebView, str4, str6, str5), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PermissionDialog.dimissDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.ShareCreateImg$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78851 implements Consumer<Boolean> {
        final /* synthetic */ Activity val$activityContext;
        final /* synthetic */ String val$businessCode;
        final /* synthetic */ String val$businessName;
        final /* synthetic */ ShareImgOrScreenshot val$callback;
        final /* synthetic */ String val$mobileA;
        final /* synthetic */ String val$shareQrcodeURL;
        final /* synthetic */ String val$shareUrl;
        final /* synthetic */ String val$templetImg;
        final /* synthetic */ PBWebView val$webView;

        C78851(String str, Activity activity, ShareImgOrScreenshot shareImgOrScreenshot, String str2, String str3, PBWebView pBWebView, String str4, String str5, String str6) {
            this.val$templetImg = str;
            this.val$activityContext = activity;
            this.val$callback = shareImgOrScreenshot;
            this.val$shareQrcodeURL = str2;
            this.val$businessCode = str3;
            this.val$webView = pBWebView;
            this.val$businessName = str4;
            this.val$shareUrl = str5;
            this.val$mobileA = str6;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Boolean bool) throws Exception {
            PermissionDialog.dimissDialog();
            if (bool.booleanValue()) {
                try {
                    if (!TextUtils.isEmpty(this.val$templetImg)) {
                        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.val$activityContext);
                        customePorgressDialog.setMessage("加载中 请稍候");
                        customePorgressDialog.setCanceledOnTouchOutside(false);
                        customePorgressDialog.setCancelable(false);
                        if (this.val$activityContext != null && !this.val$activityContext.isFinishing() && !this.val$activityContext.isDestroyed() && !customePorgressDialog.isShowing()) {
                            customePorgressDialog.show();
                        }
                        if (!this.val$templetImg.startsWith("http://") && !this.val$templetImg.startsWith("https://")) {
                            final Bitmap stringtoBitmap = ImageMergeUtil.stringtoBitmap(this.val$templetImg);
                            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    CustomePorgressDialog customePorgressDialog2;
                                    if (stringtoBitmap != null) {
                                        ScreenShotUtils.SaveBitmapToFile(C78851.this.val$activityContext, stringtoBitmap);
                                        if (C78851.this.val$activityContext != null && !C78851.this.val$activityContext.isFinishing() && !C78851.this.val$activityContext.isDestroyed() && (customePorgressDialog2 = customePorgressDialog) != null && customePorgressDialog2.isShowing()) {
                                            customePorgressDialog.dismiss();
                                        }
                                        C78851.this.val$callback.onSuccess();
                                    }
                                }
                            }, 100L);
                            return;
                        }
                        Glide.with(this.val$activityContext).asBitmap().load(this.val$templetImg).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.2
                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.manager.LifecycleListener
                            public void onStart() {
                                super.onStart();
                            }

                            public void onResourceReady(@NonNull final Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ScreenShotUtils.SaveBitmapToFile(C78851.this.val$activityContext, bitmap);
                                        if (C78851.this.val$activityContext != null && !C78851.this.val$activityContext.isFinishing() && !C78851.this.val$activityContext.isDestroyed() && customePorgressDialog != null && customePorgressDialog.isShowing()) {
                                            customePorgressDialog.dismiss();
                                        }
                                        C78851.this.val$callback.onSuccess();
                                    }
                                }, 100L);
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.2.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (C78851.this.val$activityContext == null || C78851.this.val$activityContext.isFinishing() || C78851.this.val$activityContext.isDestroyed() || customePorgressDialog == null || !customePorgressDialog.isShowing()) {
                                            return;
                                        }
                                        customePorgressDialog.dismiss();
                                    }
                                }, 100L);
                            }
                        });
                        return;
                    } else if (TextUtils.isEmpty(this.val$shareQrcodeURL) && TextUtils.isEmpty(this.val$businessCode)) {
                        ShareCreateImg.createBitmap(this.val$activityContext, this.val$webView, BitmapFactory.decodeResource(this.val$activityContext.getResources(), 2131231921), this.val$callback);
                        return;
                    } else if (TextUtils.isEmpty(this.val$businessCode) && !TextUtils.isEmpty(this.val$shareQrcodeURL)) {
                        Glide.with(this.val$activityContext).asBitmap().load(this.val$shareQrcodeURL).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.3
                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                            }

                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                ShareCreateImg.createBitmap(C78851.this.val$activityContext, C78851.this.val$webView, bitmap, C78851.this.val$callback);
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                                ShareCreateImg.createBitmap(C78851.this.val$activityContext, C78851.this.val$webView, BitmapFactory.decodeResource(C78851.this.val$activityContext.getResources(), 2131231921), C78851.this.val$callback);
                            }
                        });
                        return;
                    } else if (!TextUtils.isEmpty(this.val$businessCode) && !TextUtils.isEmpty(this.val$businessName) && !TextUtils.isEmpty(this.val$shareUrl) && !TextUtils.isEmpty(this.val$mobileA)) {
                        ShareLogUtil.createShortLinkUrl(this.val$activityContext, ShareLogUtil.getLongUrl(this.val$shareUrl, this.val$mobileA, "", this.val$businessName, this.val$businessCode, "4"), new ShareLogUtil.ShortUrlListener() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.1.4
                            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil.ShortUrlListener
                            public void onShortUrlListener(boolean z, String str) {
                                if (z) {
                                    new QrCodeUtil();
                                    try {
                                        ShareCreateImg.createBitmap(C78851.this.val$activityContext, C78851.this.val$webView, QrCodeUtil.createCode(C78851.this.val$activityContext, str, 261, 261), C78851.this.val$callback);
                                        return;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        MsLogUtil.m7979d("截图分享短地址", e.getMessage());
                                        return;
                                    }
                                }
                                ShareCreateImg.createBitmap(C78851.this.val$activityContext, C78851.this.val$webView, BitmapFactory.decodeResource(C78851.this.val$activityContext.getResources(), 2131231921), C78851.this.val$callback);
                            }
                        });
                        return;
                    } else {
                        ShareCreateImg.createBitmap(this.val$activityContext, this.val$webView, BitmapFactory.decodeResource(this.val$activityContext.getResources(), 2131231921), this.val$callback);
                        return;
                    }
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                    return;
                }
            }
            UIUtils.toast("未开启存储卡权限");
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sinovatech.unicom.basic.ui.share.ShareCreateImg$3] */
    public static void createBitmap(final Activity activity, final WebView webView, final Bitmap bitmap, final ShareImgOrScreenshot shareImgOrScreenshot) {
        new AsyncTask<String, Integer, String>() { // from class: com.sinovatech.unicom.basic.ui.share.ShareCreateImg.3

            /* renamed from: b */
            Bitmap f18430b;

            /* renamed from: pd */
            CustomePorgressDialog f18431pd;

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    this.f18431pd = new CustomePorgressDialog(activity);
                    this.f18431pd.setMessage("努力加载中 请稍候");
                    this.f18431pd.setCanceledOnTouchOutside(false);
                    this.f18431pd.setCancelable(false);
                    if (activity != null && !activity.isFinishing() && !activity.isDestroyed() && this.f18431pd != null && !this.f18431pd.isShowing()) {
                        this.f18431pd.show();
                    }
                    this.f18430b = ScreenShotUtils.createSnapshotWithBottomLogo(activity, webView, bitmap, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(String... strArr) {
                try {
                    ScreenShotUtils.SaveBitmapToFile(activity, this.f18430b);
                    if (activity == null || activity.isFinishing() || activity.isDestroyed() || this.f18431pd == null || !this.f18431pd.isShowing()) {
                        return null;
                    }
                    this.f18431pd.dismiss();
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                super.onPostExecute((AsyncTaskC78933) str);
                try {
                    if (activity != null && !activity.isFinishing() && !activity.isDestroyed() && this.f18431pd != null && this.f18431pd.isShowing()) {
                        this.f18431pd.dismiss();
                    }
                    shareImgOrScreenshot.onSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(new String[0]);
    }
}
