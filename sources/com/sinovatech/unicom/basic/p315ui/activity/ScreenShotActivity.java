package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.p099qq.C1849QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenShotActivity extends Activity {
    private static final String TAG = "ScreenShotActivity";
    public NBSTraceUnit _nbs_trace;
    private ImageButton backBtn;
    private TextView cancel;
    private Activity context;
    private String form;
    private ImageView imageView;
    private String path;
    private LinearLayout pengyouquanLayout;
    private LinearLayout qqLayout;
    private String qrPath;
    private LinearLayout weixinLayout;
    private LinearLayout zoneLayout;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 59);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void initlistener() {
        this.backBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                ScreenShotActivity.this.context.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                ScreenShotActivity.this.context.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.weixinLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (DeviceHelper.isWeixinInstall()) {
                    ScreenShotActivity.this.showShare("wechat");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                UIUtils.toast("您还没有安装微信");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.pengyouquanLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (DeviceHelper.isWeixinInstall()) {
                    ScreenShotActivity.this.showShare("wechatmoments");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                UIUtils.toast("您还没有安装微信");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.qqLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (AppUtils.isAppInstalled("com.tencent.mobileqq")) {
                    ScreenShotActivity.this.showShare("qq");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                UIUtils.toast("列表中未识别到此应用");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.zoneLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (AppUtils.isAppInstalled("com.tencent.mobileqq")) {
                    ScreenShotActivity.this.showShare("qzone");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                UIUtils.toast("列表中未识别到此应用");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showShare(String str) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        Platform platform = null;
        if ("wechat".equalsIgnoreCase(str)) {
            shareParams.setShareType(2);
            shareParams.setImagePath(ScreenShotUtils.LongWebviewCapturePicture);
            platform = ShareSDK.getPlatform(Wechat.NAME);
        } else if ("wechatmoments".equalsIgnoreCase(str)) {
            shareParams.setShareType(2);
            shareParams.setImagePath(ScreenShotUtils.LongWebviewCapturePicture);
            platform = ShareSDK.getPlatform(WechatMoments.NAME);
        } else if ("qq".equalsIgnoreCase(str)) {
            shareParams.setImagePath(ScreenShotUtils.LongWebviewCapturePicture);
            shareParams.setTitle(null);
            shareParams.setTitleUrl(null);
            platform = ShareSDK.getPlatform(C1849QQ.NAME);
        } else if ("qzone".equalsIgnoreCase(str)) {
            shareParams.setImagePath(ScreenShotUtils.LongWebviewCapturePicture);
            shareParams.setTitle(null);
            shareParams.setTitleUrl(null);
            platform = ShareSDK.getPlatform(QZone.NAME);
        }
        platform.share(shareParams);
        platform.setPlatformActionListener(new PlatformActionListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.7
            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onCancel(Platform platform2, int i) {
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onError(Platform platform2, int i, Throwable th) {
            }

            @Override // cn.sharesdk.framework.PlatformActionListener
            public void onComplete(Platform platform2, int i, HashMap<String, Object> hashMap) {
                ScreenShotActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2) {
        Matrix matrix = new Matrix();
        float f = this.context.getResources().getDisplayMetrics().density / CustomDensityHandler.sNonCompatDensity;
        float width = (bitmap.getWidth() / bitmap2.getWidth()) * f;
        matrix.postScale(width, width);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
        Bitmap createBitmap2 = Bitmap.createBitmap(UIUtils.dip2px(this, 360.0f), bitmap.getHeight() + ((int) (createBitmap.getHeight() / f)), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        canvas.drawBitmap(createBitmap, 0.0f, bitmap.getHeight(), (Paint) null);
        return createBitmap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap mergeBitmap2(Bitmap bitmap) {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.context.getResources(), 2131232331);
        float screenWidth = UIUtils.getScreenWidth((Activity) this) / decodeResource.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(screenWidth, screenWidth);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
        float height = (createBitmap.getHeight() / bitmap.getHeight()) * 0.45f;
        Matrix matrix2 = new Matrix();
        matrix2.postScale(height, height);
        Bitmap createBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
        Bitmap createBitmap3 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), createBitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap3);
        canvas.drawBitmap(createBitmap, new Matrix(), null);
        canvas.drawBitmap(createBitmap2, createBitmap.getWidth() * 0.75f, (createBitmap.getHeight() - createBitmap2.getHeight()) / 2, (Paint) null);
        return createBitmap3;
    }

    private void createBitmap() {
        if (TextUtils.isEmpty(this.qrPath)) {
            createBimap(null);
        } else {
            Glide.with(this.context).asBitmap().load(this.qrPath).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.8
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    ScreenShotActivity screenShotActivity = ScreenShotActivity.this;
                    screenShotActivity.createBimap(screenShotActivity.mergeBitmap2(bitmap));
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    ScreenShotActivity.this.createBimap(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createBimap(final Bitmap bitmap) {
        Object obj;
        MsLogUtil.m7979d(TAG, "截图1路径" + this.path);
        MsLogUtil.m7979d(TAG, "截图路径" + Uri.parse(this.path));
        String str = this.path;
        if (Build.VERSION.SDK_INT >= 24 && TextUtils.isEmpty(this.form)) {
            obj = Uri.parse(this.path);
        } else {
            obj = this.path;
        }
        Glide.with(this.context).asBitmap().load(obj).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.activity.ScreenShotActivity.9
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj2, @Nullable Transition transition) {
                onResourceReady((Bitmap) obj2, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NonNull Bitmap bitmap2, @Nullable Transition<? super Bitmap> transition) {
                Bitmap bitmap3 = bitmap;
                if (bitmap3 != null) {
                    ScreenShotActivity.SaveBitmapToFile(ScreenShotActivity.this.mergeBitmap(bitmap2, bitmap3));
                    ScreenShotActivity.this.imageView.setImageBitmap(ScreenShotActivity.this.mergeBitmap(bitmap2, bitmap));
                    return;
                }
                ScreenShotActivity screenShotActivity = ScreenShotActivity.this;
                ScreenShotActivity.SaveBitmapToFile(screenShotActivity.mergeBitmap(bitmap2, BitmapFactory.decodeResource(screenShotActivity.context.getResources(), 2131232332)));
                ImageView imageView = ScreenShotActivity.this.imageView;
                ScreenShotActivity screenShotActivity2 = ScreenShotActivity.this;
                imageView.setImageBitmap(screenShotActivity2.mergeBitmap(bitmap2, BitmapFactory.decodeResource(screenShotActivity2.context.getResources(), 2131232332)));
            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
            }
        });
    }

    public static void SaveBitmapToFile(Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                String GetLongWebviewCaptureFilepath = ScreenShotUtils.GetLongWebviewCaptureFilepath();
                if (!FileTools.fileIsExists(GetLongWebviewCaptureFilepath)) {
                    FileTools.createPath(GetLongWebviewCaptureFilepath);
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(GetLongWebviewCaptureFilepath));
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
                    try {
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (bitmap == null) {
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (bitmap == null) {
                        return;
                    }
                    bitmap.recycle();
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
            bitmap.recycle();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
