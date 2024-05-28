package com.sinovatech.unicom.basic.p315ui.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.p318ui.GlideRequest;
import com.sinovatech.unicom.separatemodule.cropimg.CuttingImageActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ImageUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.io.File;
import java.util.Locale;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomHuaBaoActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private String businessCode;
    private String headUrl;
    private String huabaoIconUrl;
    private ImageView huabaoIv;
    private int maxSize;
    private String provider;
    private String shareTitle;
    private String shareURL;
    private String crop = "yes";
    private int compressSize = -999;
    private String loading = "yes";
    private int compressCount = 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity$CreateHuaBaoListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CreateHuaBaoListener {
        void onResult(boolean z, String str);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 64);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    static /* synthetic */ int access$1808(CustomHuaBaoActivity customHuaBaoActivity) {
        int i = customHuaBaoActivity.compressCount;
        customHuaBaoActivity.compressCount = i + 1;
        return i;
    }

    private void initView() {
        ((TextView) findViewById(2131298800)).setText("自定义画报");
        this.huabaoIv = (ImageView) findViewById(2131297237);
        findViewById(2131296473).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CustomHuaBaoActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        findViewById(2131298449).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    if (!ScreenShotUtils.LongWebviewCapturePicture.isEmpty()) {
                        Uri fromFile = Uri.fromFile(new File(ScreenShotUtils.LongWebviewCapturePicture));
                        ShareLogUtil.shareUploadData(CustomHuaBaoActivity.this, CustomHuaBaoActivity.this.provider, CustomHuaBaoActivity.this.shareTitle, CustomHuaBaoActivity.this.businessCode, CustomHuaBaoActivity.this.huabaoIconUrl, "1", "3", "");
                        CustomHuaBaoActivity.this.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", fromFile));
                        UIUtils.toast("图片保存完成,快去分享给好友吧~");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.toastCenter("图片保存异常");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        findViewById(2131299278).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    CustomHuaBaoActivity.this.takeGallery();
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.toastCenter("选择图片异常:" + e.getMessage());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        findViewById(2131298284).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CacheDataCenter.getInstance().saveCustomHaiBao("");
                CustomHuaBaoActivity customHuaBaoActivity = CustomHuaBaoActivity.this;
                customHuaBaoActivity.createHuaBao(customHuaBaoActivity, customHuaBaoActivity.provider, CustomHuaBaoActivity.this.shareTitle, CustomHuaBaoActivity.this.huabaoIconUrl, CustomHuaBaoActivity.this.shareURL, CustomHuaBaoActivity.this.businessCode, CustomHuaBaoActivity.this.headUrl);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createHuaBao(Activity activity, String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            if (!str3.contains("http://") && !str3.contains("https://")) {
                Bitmap stringtoBitmap = ImageMergeUtil.stringtoBitmap(str3);
                if (stringtoBitmap != null) {
                    createShortUrl(stringtoBitmap);
                }
            }
            GlideApp.with(activity).asBitmap().load(str3).into((GlideRequest<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.5
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    CustomHuaBaoActivity.this.createShortUrl(bitmap);
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
    public void createShortUrl(final Bitmap bitmap) {
        final String longUrl = ShareLogUtil.getLongUrl(this.shareURL, this.provider, this.headUrl, this.shareTitle, this.businessCode, "3");
        UIUtils.logD("ShareLogUtil", "画报longUrl = " + longUrl);
        ShareLogUtil.createShortLinkUrl(this, longUrl, new ShareLogUtil.ShortUrlListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.6
            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil.ShortUrlListener
            public void onShortUrlListener(boolean z, String str) {
                if (z) {
                    CustomHuaBaoActivity customHuaBaoActivity = CustomHuaBaoActivity.this;
                    customHuaBaoActivity.synthesisHuaBao(customHuaBaoActivity, customHuaBaoActivity.provider, CustomHuaBaoActivity.this.shareTitle, CustomHuaBaoActivity.this.businessCode, str, CustomHuaBaoActivity.this.shareURL, bitmap, new CreateHuaBaoListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.6.1
                        @Override // com.sinovatech.unicom.basic.p315ui.share.CustomHuaBaoActivity.CreateHuaBaoListener
                        public void onResult(boolean z2, String str2) {
                            ShowImageUtils.showRoundImageView(CustomHuaBaoActivity.this, ScreenShotUtils.LongWebviewCapturePicture, CustomHuaBaoActivity.this.huabaoIv, UIUtils.dip2px(7.0f));
                        }
                    });
                    return;
                }
                CustomHuaBaoActivity customHuaBaoActivity2 = CustomHuaBaoActivity.this;
                customHuaBaoActivity2.synthesisHuaBao(customHuaBaoActivity2, customHuaBaoActivity2.provider, CustomHuaBaoActivity.this.shareTitle, CustomHuaBaoActivity.this.businessCode, longUrl, CustomHuaBaoActivity.this.shareURL, bitmap, new CreateHuaBaoListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.6.2
                    @Override // com.sinovatech.unicom.basic.p315ui.share.CustomHuaBaoActivity.CreateHuaBaoListener
                    public void onResult(boolean z2, String str2) {
                        ShowImageUtils.showRoundImageView(CustomHuaBaoActivity.this, ScreenShotUtils.LongWebviewCapturePicture, CustomHuaBaoActivity.this.huabaoIv, UIUtils.dip2px(7.0f));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity$7] */
    public void synthesisHuaBao(final Activity activity, String str, String str2, String str3, final String str4, String str5, final Bitmap bitmap, final CreateHuaBaoListener createHuaBaoListener) {
        try {
            new AsyncTask<String, Integer, Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.7

                /* renamed from: pd */
                CustomePorgressDialog f18426pd;

                @Override // android.os.AsyncTask
                protected void onPreExecute() {
                    super.onPreExecute();
                    this.f18426pd = new CustomePorgressDialog(activity);
                    this.f18426pd.setMessage("正在生成画报 请稍候");
                    this.f18426pd.setCanceledOnTouchOutside(false);
                    this.f18426pd.setCancelable(false);
                    this.f18426pd.show();
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
                        UIUtils.logE("", e.getLocalizedMessage());
                        return bitmap2;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Bitmap bitmap2) {
                    super.onPostExecute((AsyncTaskC78707) bitmap2);
                    CustomePorgressDialog customePorgressDialog = this.f18426pd;
                    if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
                        this.f18426pd.dismiss();
                    }
                    createHuaBaoListener.onResult(true, str4);
                    if (bitmap2 == null) {
                        UIUtils.toast("图片生成失败,请重试");
                    } else if (bitmap2 != null) {
                        bitmap2.isRecycled();
                    }
                }
            }.execute(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeGallery() {
        this.crop = "yes";
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        new AvoidOnResult(this).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.8
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                if (i == -1) {
                    try {
                        if (intent2.getData() != null) {
                            String filePathWithUri = ImageUtil.getFilePathWithUri(CustomHuaBaoActivity.this, intent2.getData());
                            MsLogUtil.m7980d("从本地相册选择 imageUri：" + intent2.getData());
                            MsLogUtil.m7980d("从本地相册选择 imagePath：" + filePathWithUri);
                            if (!filePathWithUri.endsWith(".gif") && !filePathWithUri.endsWith(".GIF")) {
                                CustomHuaBaoActivity.this.compress(filePathWithUri, 3, CustomHuaBaoActivity.this.compressSize);
                                return;
                            }
                            ImageUtil.fileToBase64(new File(filePathWithUri));
                            UIUtils.toastCenter("动图不支持");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compress(String str, int i, int i2) {
        if (i2 == -999) {
            try {
                if ("yes".equalsIgnoreCase(this.crop)) {
                    MsLogUtil.m7980d("不压缩裁剪");
                    crop(str);
                } else {
                    MsLogUtil.m7980d("不压缩返回给前端");
                    String fileToBase64 = ImageUtil.fileToBase64(new File(str));
                    CacheDataCenter.getInstance().saveCustomHaiBao(fileToBase64);
                    createHuaBao(this, this.provider, this.shareTitle, fileToBase64, this.shareURL, this.businessCode, this.headUrl);
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                UIUtils.toastCenter("图片处理异常");
                return;
            }
        }
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this);
        customePorgressDialog.setMessage("正在压缩...");
        this.maxSize = 500;
        if (i2 >= 0 && i2 <= 50) {
            this.maxSize = 50;
        } else if (i2 > 50 && i2 < 500) {
            this.maxSize = i2;
        }
        Luban.compress(this, new File(str)).setMaxSize(this.maxSize).setCompressFormat(Bitmap.CompressFormat.JPEG).putGear(i).launch(new OnCompressListener() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.9
            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onStart() {
                if ("yes".equalsIgnoreCase(CustomHuaBaoActivity.this.loading)) {
                    customePorgressDialog.show();
                }
                MsLogUtil.m7980d("Luban压缩 onStart");
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onSuccess(File file) {
                try {
                    if (customePorgressDialog.isShowing()) {
                        customePorgressDialog.cancel();
                    }
                    int[] computeSize = CustomHuaBaoActivity.this.computeSize(file);
                    String format = String.format(Locale.CHINA, "Luban onSuccess压缩后参数：%d*%d, %dk", Integer.valueOf(computeSize[0]), Integer.valueOf(computeSize[1]), Long.valueOf(file.length() >> 10));
                    MsLogUtil.m7980d("Luban压缩 onSuccess " + file.getAbsolutePath() + " " + format);
                    if ("yes".equalsIgnoreCase(CustomHuaBaoActivity.this.crop)) {
                        CustomHuaBaoActivity.this.crop(file.getAbsolutePath());
                    } else if (file.length() > CustomHuaBaoActivity.this.maxSize * 1024 && CustomHuaBaoActivity.this.compressCount < 2) {
                        CustomHuaBaoActivity.access$1808(CustomHuaBaoActivity.this);
                        CustomHuaBaoActivity.this.compress(file.getPath(), 4, CustomHuaBaoActivity.this.maxSize);
                    } else {
                        String fileToBase642 = ImageUtil.fileToBase64(file);
                        CacheDataCenter.getInstance().saveCustomHaiBao(fileToBase642);
                        CustomHuaBaoActivity.this.createHuaBao(CustomHuaBaoActivity.this, CustomHuaBaoActivity.this.provider, CustomHuaBaoActivity.this.shareTitle, fileToBase642, CustomHuaBaoActivity.this.shareURL, CustomHuaBaoActivity.this.businessCode, CustomHuaBaoActivity.this.headUrl);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    MsLogUtil.m7980d("Luban压缩 onSuccess发生错误 " + e2.getMessage());
                }
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onError(Throwable th) {
                if (customePorgressDialog.isShowing()) {
                    customePorgressDialog.cancel();
                }
                MsLogUtil.m7980d("Luban压缩 onError" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] computeSize(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void crop(String str) {
        final File file = new File(getChooseImageCacheDir() + File.separator + "output_crop_image.jpg");
        if (file.exists()) {
            file.delete();
        }
        new AvoidOnResult(this).startForResult(CuttingImageActivity.prepare().aspectX(720).aspectY(1000).maxWidth(720).inputPath(str).outputPath(file.getAbsolutePath()).getIntent(this), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.share.CustomHuaBaoActivity.10
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                try {
                    CustomHuaBaoActivity.this.crop = "no";
                    if (i == -1 && intent != null) {
                        CustomHuaBaoActivity.this.compress(file.getAbsolutePath(), 4, CustomHuaBaoActivity.this.compressSize);
                    } else {
                        UIUtils.toastCenter("裁剪取消");
                    }
                } catch (Exception e) {
                    MsLogUtil.m7980d("裁剪错误" + e.getMessage());
                }
            }
        });
    }

    private String getChooseImageCacheDir() {
        File file = new File(getExternalCacheDir().getAbsolutePath() + File.separator + "haibao_img");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
