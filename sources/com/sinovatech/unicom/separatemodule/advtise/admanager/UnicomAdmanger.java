package com.sinovatech.unicom.separatemodule.advtise.admanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.listener.VideoListener;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.CountDownHelper;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomAdmanger extends AbstractAdManager {
    private static final String TAG = "UnicomAdmanger";
    private static UnicomAdmanger admanger;
    private WeakReference<AppCompatActivity> activityContext;
    int countDownCount;
    private AdConfigEntity entity;
    private Disposable subscribe;
    private boolean videoPlaying = true;

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadBanner(IAdInterface.IBannerAdCallBack iBannerAdCallBack) {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadDraw(IAdInterface.IDrawCallBack iDrawCallBack) {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadInteraction(IAdInterface.IInteractionCallBack iInteractionCallBack) {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onDestroy() {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onPause() {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onResume() {
    }

    private UnicomAdmanger() {
    }

    public static UnicomAdmanger getInstance(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity) {
        if (admanger == null) {
            synchronized (UnicomAdmanger.class) {
                if (admanger == null) {
                    admanger = new UnicomAdmanger();
                }
            }
        }
        admanger.init(appCompatActivity, adConfigEntity);
        return admanger;
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    protected void init(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity) {
        this.entity = adConfigEntity;
        this.activityContext = new WeakReference<>(appCompatActivity);
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadVido(String str, String str2, IAdInterface.IVideoCallBack iVideoCallBack) {
        iVideoCallBack.onResult(12, "不支持的广告类型");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0095, code lost:
        if (r0.equals("JINGDONG") == false) goto L27;
     */
    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadSplash(android.view.ViewGroup r17, long r18, final com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.ISplashAdCallBack r20, final com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IAdClickListener r21) {
        /*
            Method dump skipped, instructions count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.loadSplash(android.view.ViewGroup, long, com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface$ISplashAdCallBack, com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface$IAdClickListener):void");
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C81902 extends SimpleTarget<Drawable> {
        final /* synthetic */ IAdInterface.IAdClickListener val$adComplete;
        final /* synthetic */ IAdInterface.ISplashAdCallBack val$callBack;
        final /* synthetic */ LinearLayout val$detailLayout;
        final /* synthetic */ TextView val$detailTextView;
        final /* synthetic */ ImageView val$imageView;
        final /* synthetic */ ImageView val$mImgBottomDetail;
        final /* synthetic */ TextView val$textView;

        C81902(ImageView imageView, LinearLayout linearLayout, TextView textView, IAdInterface.ISplashAdCallBack iSplashAdCallBack, TextView textView2, ImageView imageView2, IAdInterface.IAdClickListener iAdClickListener) {
            this.val$mImgBottomDetail = imageView;
            this.val$detailLayout = linearLayout;
            this.val$detailTextView = textView;
            this.val$callBack = iSplashAdCallBack;
            this.val$textView = textView2;
            this.val$imageView = imageView2;
            this.val$adComplete = iAdClickListener;
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
        }

        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            UnicomAdmanger unicomAdmanger = UnicomAdmanger.this;
            unicomAdmanger.uploadPV(UnicomAdmanger.this.entity.getWelcomeType() + "开屏广告-展示", "");
            App.getAsyncHttpClient().get(UnicomAdmanger.this.entity.getExposureUrl(), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.2.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    UnicomAdmanger unicomAdmanger2 = UnicomAdmanger.this;
                    unicomAdmanger2.uploadPV(UnicomAdmanger.this.entity.getWelcomeType() + "开屏广告-上报", "成功");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    UnicomAdmanger.this.uploadPV(UnicomAdmanger.this.entity.getWelcomeType() + "开屏广告-上报", "失败" + str);
                }
            });
            this.val$mImgBottomDetail.setVisibility(0);
            UnicomAdmanger.this.changeImageParams(this.val$mImgBottomDetail, this.val$detailLayout, this.val$detailTextView);
            this.val$callBack.onResult(10, UnicomAdmanger.this.entity.getAdType());
            this.val$textView.setVisibility(0);
            this.val$imageView.setImageDrawable(drawable);
            this.val$mImgBottomDetail.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.2.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(UnicomAdmanger.this.entity.getAdvertiseTargetURL()));
                        new AvoidOnResult((Activity) UnicomAdmanger.this.activityContext.get()).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.2.2.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                C81902.this.val$adComplete.onAdClick();
                            }
                        });
                        if (UnicomAdmanger.this.subscribe != null) {
                            UnicomAdmanger.this.subscribe.dispose();
                        }
                    } catch (Exception unused) {
                        UIUtils.logD(UnicomAdmanger.TAG, "点击底部详情");
                        App.exterWelcomURL = UnicomAdmanger.this.entity.getNoCallUrl();
                        C81902.this.val$adComplete.onAdClick();
                        UnicomAdmanger.this.uploadPV("开屏广告-点击", "");
                    }
                    UnicomAdmanger unicomAdmanger2 = UnicomAdmanger.this;
                    unicomAdmanger2.uploadPV(UnicomAdmanger.this.entity.getWelcomeType() + "开屏广告-点击", "");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            LinearLayout linearLayout = this.val$detailLayout;
            final ImageView imageView = this.val$mImgBottomDetail;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.-$$Lambda$UnicomAdmanger$2$zY1s3MVnJjBdGyvyOif3oxDaE3g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    imageView.performClick();
                }
            });
            UnicomAdmanger unicomAdmanger2 = UnicomAdmanger.this;
            unicomAdmanger2.countDown(unicomAdmanger2.countDownCount, this.val$textView, this.val$adComplete);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
            this.val$callBack.onResult(11, UnicomAdmanger.this.entity.getAdType());
            UnicomAdmanger unicomAdmanger = UnicomAdmanger.this;
            unicomAdmanger.uploadPV(UnicomAdmanger.this.entity.getWelcomeType() + "开屏广告-错误", "");
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C81943 extends SimpleTarget<Drawable> {
        final /* synthetic */ IAdInterface.IAdClickListener val$adComplete;
        final /* synthetic */ IAdInterface.ISplashAdCallBack val$callBack;
        final /* synthetic */ LinearLayout val$detailLayout;
        final /* synthetic */ TextView val$detailTextView;
        final /* synthetic */ ImageView val$imageView;
        final /* synthetic */ ImageView val$mImgBottomDetail;
        final /* synthetic */ long val$stayTime;
        final /* synthetic */ TextView val$textView;
        final /* synthetic */ long val$time1;

        C81943(long j, long j2, ImageView imageView, LinearLayout linearLayout, TextView textView, IAdInterface.ISplashAdCallBack iSplashAdCallBack, TextView textView2, ImageView imageView2, IAdInterface.IAdClickListener iAdClickListener) {
            this.val$time1 = j;
            this.val$stayTime = j2;
            this.val$mImgBottomDetail = imageView;
            this.val$detailLayout = linearLayout;
            this.val$detailTextView = textView;
            this.val$callBack = iSplashAdCallBack;
            this.val$textView = textView2;
            this.val$imageView = imageView2;
            this.val$adComplete = iAdClickListener;
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
        }

        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            long currentTimeMillis = this.val$stayTime - (System.currentTimeMillis() - this.val$time1);
            long j = 0;
            if (currentTimeMillis >= 0 && currentTimeMillis <= 1500) {
                j = currentTimeMillis;
            }
            MsLogUtil.m7979d("newStayTIme", "newStayTIme:" + j);
            new Handler().postDelayed(new RunnableC81951(drawable), j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger$3$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class RunnableC81951 implements Runnable {
            final /* synthetic */ Drawable val$resource;

            RunnableC81951(Drawable drawable) {
                this.val$resource = drawable;
            }

            @Override // java.lang.Runnable
            public void run() {
                UnicomAdmanger.this.uploadPV("开屏广告-展示", "");
                C81943.this.val$mImgBottomDetail.setVisibility(0);
                UnicomAdmanger.this.changeImageParams(C81943.this.val$mImgBottomDetail, C81943.this.val$detailLayout, C81943.this.val$detailTextView);
                C81943.this.val$callBack.onResult(10, UnicomAdmanger.this.entity.getAdType());
                C81943.this.val$textView.setVisibility(0);
                C81943.this.val$imageView.setImageDrawable(this.val$resource);
                C81943.this.val$mImgBottomDetail.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.3.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        UIUtils.logD(UnicomAdmanger.TAG, "点击底部详情");
                        UnicomAdmanger.this.uploadPV("开屏广告-点击", "");
                        App.exterWelcomURL = UnicomAdmanger.this.entity.getAdvertiseTargetURL();
                        C81943.this.val$adComplete.onAdClick();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                LinearLayout linearLayout = C81943.this.val$detailLayout;
                final ImageView imageView = C81943.this.val$mImgBottomDetail;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.-$$Lambda$UnicomAdmanger$3$1$btAoz4maqr8E6jkXdIffyUjqIJk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        imageView.performClick();
                    }
                });
                UnicomAdmanger.this.countDown(UnicomAdmanger.this.countDownCount, C81943.this.val$textView, C81943.this.val$adComplete);
            }
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
            this.val$callBack.onResult(11, UnicomAdmanger.this.entity.getAdType());
            UnicomAdmanger.this.uploadPV("开屏广告-错误", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void countDown(int i, final TextView textView, final IAdInterface.IAdClickListener iAdClickListener) {
        try {
            if (this.activityContext.get() == null) {
                iAdClickListener.onAdClick();
            } else {
                this.subscribe = CountDownHelper.countdown(i).subscribe(new Consumer<Integer>() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.4
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Integer num) throws Exception {
                        if (num.intValue() != 0) {
                            if (TextUtils.equals("UNIOCMPIC", UnicomAdmanger.this.entity.getAdType()) || TextUtils.equals("SMATOO", UnicomAdmanger.this.entity.getAdType())) {
                                TextView textView2 = textView;
                                textView2.setText(num + "s | 跳过");
                            } else {
                                TextView textView3 = textView;
                                textView3.setText("跳过 " + num + "s");
                            }
                        }
                        if (num.intValue() <= 0) {
                            iAdClickListener.onAdClick();
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.5
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        iAdClickListener.onAdClick();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    protected void uploadPV(String str, String str2) {
        StatisticsUploadUtils.uploadRealTimeBeiDong(App.getInstance(), "10", "启动页-广告", str, this.entity.getAdvertiseId(), this.entity.getAdvertiseTitle(), this.entity.getAdvertiseTargetURL());
    }

    public void changeImageParams(final ImageView imageView, LinearLayout linearLayout, TextView textView) {
        if (imageView == null) {
            return;
        }
        try {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.setMargins(UIUtils.dip2px(20.0f), 0, UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f));
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(2131230816);
            if (this.activityContext.get() != null && this.entity != null) {
                if (!TextUtils.isEmpty(this.entity.getImageIconSrc())) {
                    GlideApp.with((FragmentActivity) this.activityContext.get()).load(this.entity.getImageIconSrc()).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.6
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                            onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                        }

                        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                            char c;
                            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                            layoutParams2.setMargins(0, 0, 0, UIUtils.dip2px(17.0f));
                            String imageIconType = UnicomAdmanger.this.entity.getImageIconType();
                            switch (imageIconType.hashCode()) {
                                case 49:
                                    if (imageIconType.equals("1")) {
                                        c = 0;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 50:
                                    if (imageIconType.equals("2")) {
                                        c = 1;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 51:
                                    if (imageIconType.equals("3")) {
                                        c = 2;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 52:
                                    if (imageIconType.equals("4")) {
                                        c = 3;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 53:
                                    if (imageIconType.equals("5")) {
                                        c = 4;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    layoutParams2.width = UIUtils.dip2px(178.0f);
                                    layoutParams2.height = UIUtils.dip2px(46.0f);
                                    break;
                                case 1:
                                    layoutParams2.width = UIUtils.dip2px(212.0f);
                                    layoutParams2.height = UIUtils.dip2px(43.0f);
                                    break;
                                case 2:
                                    layoutParams2.width = UIUtils.dip2px(61.0f);
                                    layoutParams2.height = UIUtils.dip2px(61.0f);
                                    layoutParams2.setMargins(0, 0, 0, UIUtils.dip2px(14.0f));
                                    break;
                                case 3:
                                    layoutParams2.width = UIUtils.dip2px(198.0f);
                                    layoutParams2.height = UIUtils.dip2px(43.0f);
                                    break;
                                case 4:
                                    layoutParams2.width = UIUtils.dip2px(210.0f);
                                    layoutParams2.height = UIUtils.dip2px(44.0f);
                                    break;
                                default:
                                    layoutParams2.width = -1;
                                    layoutParams2.height = -2;
                                    layoutParams2.setMargins(UIUtils.dip2px(20.0f), 0, UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f));
                                    MsLogUtil.m7980d("点击热区类型为新增本地没有定义使用默认样式进行展示");
                                    break;
                            }
                            imageView.setLayoutParams(layoutParams2);
                            imageView.setImageDrawable(drawable);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                        }
                    });
                } else if (!TextUtils.isEmpty(this.entity.getDetailTextString())) {
                    imageView.setVisibility(8);
                    linearLayout.setVisibility(0);
                    textView.setText(this.entity.getDetailTextString());
                }
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载点击热区异常:" + e.getMessage());
        }
    }

    public void startVideoKaiping(final IjkVideoView ijkVideoView, final IAdInterface.ISplashAdCallBack iSplashAdCallBack) {
        try {
            AppCompatActivity appCompatActivity = this.activityContext.get();
            if (DeviceHelper.isZhediepingPadMode(appCompatActivity)) {
                iSplashAdCallBack.onResult(10, "");
                return;
            }
            this.videoPlaying = true;
            final Disposable subscribe = Observable.timer(9L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    if (UnicomAdmanger.this.videoPlaying) {
                        iSplashAdCallBack.onResult(10, "");
                        UnicomAdmanger.this.videoPlaying = false;
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.8
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    if (UnicomAdmanger.this.videoPlaying) {
                        iSplashAdCallBack.onResult(10, "");
                        UnicomAdmanger.this.videoPlaying = false;
                    }
                }
            });
            InputStream openRawResource = appCompatActivity.getResources().openRawResource(2131820572);
            File file = new File(appCompatActivity.getFilesDir(), "kaiping_0110.mp4");
            if (!file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[10];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = openRawResource.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();
                openRawResource.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            ijkVideoView.setPlayerConfig(new PlayerConfig.Builder().build());
            ijkVideoView.setScreenScale(0);
            ijkVideoView.setUrl(file.getPath());
            ijkVideoView.setVideoListener(new VideoListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.9
                @Override // com.dueeeke.videoplayer.listener.VideoListener
                public void onInfo(int i, int i2) {
                }

                @Override // com.dueeeke.videoplayer.listener.VideoListener
                public void onComplete() {
                    if (UnicomAdmanger.this.videoPlaying) {
                        iSplashAdCallBack.onResult(10, "");
                        subscribe.dispose();
                        UnicomAdmanger.this.videoPlaying = false;
                    }
                }

                @Override // com.dueeeke.videoplayer.listener.VideoListener
                public void onPrepared() {
                    ijkVideoView.setVisibility(0);
                }

                @Override // com.dueeeke.videoplayer.listener.VideoListener
                public void onError() {
                    if (UnicomAdmanger.this.videoPlaying) {
                        iSplashAdCallBack.onResult(10, "");
                        subscribe.dispose();
                        UnicomAdmanger.this.videoPlaying = false;
                    }
                }
            });
            new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger.10
                @Override // java.lang.Runnable
                public void run() {
                    ijkVideoView.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (this.videoPlaying) {
                iSplashAdCallBack.onResult(10, "");
                this.subscribe.dispose();
                this.videoPlaying = false;
            }
        }
    }
}
