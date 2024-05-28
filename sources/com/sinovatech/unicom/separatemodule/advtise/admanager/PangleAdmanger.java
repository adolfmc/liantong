package com.sinovatech.unicom.separatemodule.advtise.admanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdLoadType;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.bean.VideoCacheEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.chuanshanjia.ParamsID;
import com.sinovatech.unicom.separatemodule.chuanshanjia.TTAdManagerHolder;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PangleAdmanger extends AbstractAdManager {
    private static PangleAdmanger admanger;
    private WeakReference<AppCompatActivity> activityContext;
    private AdConfigEntity entity;
    private boolean isClick;
    private TTNativeExpressAd mBannerTTad;
    private IAdInterface.IAdClickListener onComplete;
    private Disposable timeOutSubscribe;
    private String uuid;
    private TTRewardVideoAd mttRewardVideoAd = null;
    private final Map<String, VideoCacheEntity> videoCacheMap = new HashMap();
    private final TTAdNative mTTAdNative = TTAdManagerHolder.get().createAdNative(App.getInstance());

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onDestroy() {
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onPause() {
    }

    private PangleAdmanger() {
    }

    public static PangleAdmanger getInstance(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity) {
        if (admanger == null) {
            synchronized (PangleAdmanger.class) {
                if (admanger == null) {
                    admanger = new PangleAdmanger();
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
    public void loadBanner(final IAdInterface.IBannerAdCallBack iBannerAdCallBack) {
        MsLogUtil.m7979d("loadBanner", "entity:" + this.entity.toString());
        AdSlot build = new AdSlot.Builder().setCodeId(this.entity.getCodeId()).setSupportDeepLink(true).setExpressViewAcceptedSize((float) (this.entity.getScale() * 360.0d), (float) (((double) ((this.entity.getBannerHeight() * 360) / this.entity.getBannerWidth())) * this.entity.getScale())).setNativeAdType(1).setAdCount(1).setImageAcceptedSize(this.entity.getBannerWidth(), this.entity.getBannerHeight()).setIsAutoPlay(false).build();
        uploadCollectSdk(App.webviewTitle, "BUNativeExpressBannerView", "0");
        this.mTTAdNative.loadBannerExpressAd(build, new TTAdNative.NativeExpressAdListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                MsLogUtil.m7979d("bannerAdview", i + "===" + str);
                iBannerAdCallBack.onResult(11, null);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.size() == 0) {
                    return;
                }
                PangleAdmanger.this.mBannerTTad = list.get(0);
                PangleAdmanger.this.mBannerTTad.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.1.1
                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdClicked(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdShow(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderFail(View view, String str, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderSuccess(View view, float f, float f2) {
                        iBannerAdCallBack.onResult(10, view);
                    }
                });
                PangleAdmanger.this.mBannerTTad.setDislikeCallback((Activity) PangleAdmanger.this.activityContext.get(), new TTAdDislike.DislikeInteractionCallback() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.1.2
                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onCancel() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onShow() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onSelected(int i, String str, boolean z) {
                        iBannerAdCallBack.onClose();
                    }
                });
                PangleAdmanger.this.mBannerTTad.render();
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadDraw(final IAdInterface.IDrawCallBack iDrawCallBack) {
        AdSlot build = new AdSlot.Builder().setCodeId(this.entity.getCodeId()).setSupportDeepLink(true).setAdCount(1).setImageAcceptedSize(720, 1280).build();
        uploadCollectSdk(App.webviewTitle, "BUAdSlotAdTypeFeed", "0");
        this.mTTAdNative.loadExpressDrawFeedAd(build, new TTAdNative.NativeExpressAdListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.2
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                Log.e("lzh", "loadExpressDrawFeedAd---onError: code==" + i + "--message==" + str);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                TTNativeExpressAd tTNativeExpressAd = list.get(0);
                tTNativeExpressAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.2.1
                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdClicked(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdShow(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderFail(View view, String str, int i) {
                        Log.e("lzh", "loadExpressDrawFeedAd---onError: code==" + i + "--message==" + str);
                        iDrawCallBack.onCallBack(null);
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderSuccess(View view, float f, float f2) {
                        Log.d("lzh", "loadExpressDrawFeedAd---onRenderSuccess: width==" + f + "--height==" + f2);
                        iDrawCallBack.onCallBack(view);
                    }
                });
                tTNativeExpressAd.render();
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadVido(String str, final String str2, final IAdInterface.IVideoCallBack iVideoCallBack) {
        VideoCacheEntity videoCacheEntity = this.videoCacheMap.get(this.entity.getCodeId());
        if (videoCacheEntity != null && !this.entity.isPrepareLoad()) {
            this.videoCacheMap.remove(this.entity.getCodeId());
            if (System.currentTimeMillis() - videoCacheEntity.getTime() < 3600000) {
                TTRewardVideoAd rewardVideoAd = videoCacheEntity.getRewardVideoAd();
                String orderId = videoCacheEntity.getOrderId();
                if (this.activityContext.get() != null) {
                    rewardVideoAd.showRewardVideoAd(this.activityContext.get(), TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                    initRewordListener(rewardVideoAd, orderId, iVideoCallBack);
                    uploadPV("激励视频_使用缓存播放", "");
                    return;
                }
                iVideoCallBack.onResult(12, "资源被回收");
                uploadPV("激励视频_使用缓存播放", "资源回收");
                return;
            }
        }
        int timeOutSecond = this.entity.getTimeOutSecond();
        if (timeOutSecond < 5) {
            timeOutSecond = 5;
        }
        this.timeOutSubscribe = Observable.timer(timeOutSecond, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.-$$Lambda$PangleAdmanger$SufZx88ZGAYo0Zg5_0LGT97r2Gs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PangleAdmanger.lambda$loadVido$0(PangleAdmanger.this, iVideoCallBack, (Long) obj);
            }
        });
        this.uuid = UUID.randomUUID().toString();
        int i = this.entity.getDirection() == 1 ? 2 : 1;
        AdSlot.Builder builder = new AdSlot.Builder();
        if (this.entity.isPrepareLoad()) {
            builder.setAdLoadType(TTAdLoadType.PRELOAD);
        } else {
            builder.setAdLoadType(TTAdLoadType.LOAD);
        }
        uploadPV("激励视频_入口", str2);
        uploadCollectSdk(App.webviewTitle, "BUNativeExpressRewardedVideoAd", "1");
        setStartUISetting();
        AdSlot build = builder.setCodeId(this.entity.getCodeId()).setSupportDeepLink(true).setMediaExtra(str).setExpressViewAcceptedSize(UIUtils.px2dp(UIUtils.getScreenWidth(App.getInstance())), UIUtils.px2dp(UIUtils.getScreenHeight(App.getInstance()))).setUserID(ParamsID.AppId).setOrientation(i).build();
        this.mttRewardVideoAd = null;
        this.mTTAdNative.loadRewardVideoAd(build, new TTAdNative.RewardVideoAdListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.3
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i2, String str3) {
                if (!"1".equals(PangleAdmanger.this.entity.getSubFlag())) {
                    if (i2 == 20001) {
                        UIUtils.toast("广告正在路上，请稍后再试【c20001】");
                    } else {
                        UIUtils.toast("广告加载异常，请稍后再试【c40004】");
                    }
                }
                UIUtils.logD("loadRewardVideoAd", "--" + str3);
                IAdInterface.IVideoCallBack iVideoCallBack2 = iVideoCallBack;
                iVideoCallBack2.onResult(12, str3 + i2);
                PangleAdmanger pangleAdmanger = PangleAdmanger.this;
                pangleAdmanger.uploadPV("激励视频_播放失败", str3 + i2);
                PangleAdmanger.this.timeOutSubscribe.dispose();
                PangleAdmanger.this.setEndUISetting();
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoCached(TTRewardVideoAd tTRewardVideoAd) {
                iVideoCallBack.onResult(14, "缓存完成");
                if (!PangleAdmanger.this.entity.isPrepareLoad()) {
                    if (PangleAdmanger.this.mttRewardVideoAd != null) {
                        PangleAdmanger.this.mttRewardVideoAd.showRewardVideoAd((Activity) PangleAdmanger.this.activityContext.get(), TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                    }
                    PangleAdmanger.this.timeOutSubscribe.dispose();
                } else {
                    VideoCacheEntity videoCacheEntity2 = new VideoCacheEntity();
                    videoCacheEntity2.setOrderId(str2);
                    videoCacheEntity2.setTime(System.currentTimeMillis());
                    videoCacheEntity2.setRewardVideoAd(PangleAdmanger.this.mttRewardVideoAd);
                    PangleAdmanger.this.videoCacheMap.put(PangleAdmanger.this.entity.getCodeId(), videoCacheEntity2);
                    PangleAdmanger.this.uploadPV("激励视频_缓存完成", "");
                    UIUtils.logD("loadRewardVideoAd", "--缓存完成");
                    PangleAdmanger.this.setEndUISetting();
                }
                PangleAdmanger.this.uploadPV("激励视频_返回量", "");
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoCached() {
                iVideoCallBack.onResult(14, "缓存完成");
                if (!PangleAdmanger.this.entity.isPrepareLoad()) {
                    if (PangleAdmanger.this.mttRewardVideoAd != null) {
                        PangleAdmanger.this.mttRewardVideoAd.showRewardVideoAd((Activity) PangleAdmanger.this.activityContext.get(), TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                    }
                    PangleAdmanger.this.timeOutSubscribe.dispose();
                } else {
                    VideoCacheEntity videoCacheEntity2 = new VideoCacheEntity();
                    videoCacheEntity2.setOrderId(str2);
                    videoCacheEntity2.setTime(System.currentTimeMillis());
                    videoCacheEntity2.setRewardVideoAd(PangleAdmanger.this.mttRewardVideoAd);
                    PangleAdmanger.this.videoCacheMap.put(PangleAdmanger.this.entity.getCodeId(), videoCacheEntity2);
                    PangleAdmanger.this.uploadPV("激励视频_缓存完成", "");
                    UIUtils.logD("loadRewardVideoAd", "--缓存完成");
                    PangleAdmanger.this.setEndUISetting();
                }
                PangleAdmanger.this.uploadPV("激励视频_返回量", "");
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoAdLoad(TTRewardVideoAd tTRewardVideoAd) {
                PangleAdmanger.this.mttRewardVideoAd = tTRewardVideoAd;
                String str3 = (String) tTRewardVideoAd.getMediaExtraInfo().get("request_id");
                PangleAdmanger pangleAdmanger = PangleAdmanger.this;
                pangleAdmanger.initRewordListener(pangleAdmanger.mttRewardVideoAd, str2, iVideoCallBack);
            }
        });
    }

    public static /* synthetic */ void lambda$loadVido$0(PangleAdmanger pangleAdmanger, IAdInterface.IVideoCallBack iVideoCallBack, Long l) throws Exception {
        if (!pangleAdmanger.timeOutSubscribe.isDisposed() && !pangleAdmanger.entity.isPrepareLoad()) {
            iVideoCallBack.onResult(12, "timeOut");
            pangleAdmanger.uploadPV("激励视频_超时", "");
        }
        pangleAdmanger.timeOutSubscribe.dispose();
    }

    private void setStartUISetting() {
        DisplayMetrics displayMetrics = App.getInstance().getResources().getDisplayMetrics();
        displayMetrics.density = CustomDensityHandler.sNonCompatDensity;
        displayMetrics.scaledDensity = CustomDensityHandler.sNonCompatScaleDensity;
        displayMetrics.densityDpi = CustomDensityHandler.sNonCompattargetDensityDpi;
        DisplayMetrics displayMetrics2 = this.activityContext.get().getResources().getDisplayMetrics();
        displayMetrics2.density = CustomDensityHandler.sNonCompatDensity;
        displayMetrics2.scaledDensity = CustomDensityHandler.sNonCompatScaleDensity;
        displayMetrics2.densityDpi = CustomDensityHandler.sNonCompattargetDensityDpi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndUISetting() {
        CustomDensityHandler.setCustomDensity(this.activityContext.get(), App.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRewordListener(final TTRewardVideoAd tTRewardVideoAd, final String str, final IAdInterface.IVideoCallBack iVideoCallBack) {
        tTRewardVideoAd.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.4
            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onAdVideoBarClick() {
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onRewardVerify(boolean z, int i, String str2, int i2, String str3) {
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onSkippedVideo() {
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onRewardArrived(boolean z, int i, Bundle bundle) {
                iVideoCallBack.onResult(13, str);
                PangleAdmanger.this.uploadPV("激励视频_发放积分", "");
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onAdShow() {
                PangleAdmanger.this.uploadPV("激励视频_展示", "");
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onAdClose() {
                PangleAdmanger.this.mttRewardVideoAd = null;
                iVideoCallBack.onResult(15, str);
                PangleAdmanger.this.uploadPV("激励视频_回调前端", (String) tTRewardVideoAd.getMediaExtraInfo().get("request_id"));
                PangleAdmanger.this.setEndUISetting();
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onVideoComplete() {
                UIUtils.logD("loadRewardVideoAd", "--onVideoComplete");
                PangleAdmanger.this.uploadPV("激励视频_播放完成", (String) tTRewardVideoAd.getMediaExtraInfo().get("request_id"));
                PangleAdmanger.this.setEndUISetting();
            }

            @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
            public void onVideoError() {
                if (!"1".equals(PangleAdmanger.this.entity.getSubFlag())) {
                    UIUtils.toast("广告加载异常，请稍后再试【c40004】");
                }
                iVideoCallBack.onResult(12, "视频播放过程出错");
                PangleAdmanger.this.uploadPV("激励视频_播放失败", (String) tTRewardVideoAd.getMediaExtraInfo().get("request_id"));
                PangleAdmanger.this.timeOutSubscribe.dispose();
                PangleAdmanger.this.setEndUISetting();
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadSplash(final ViewGroup viewGroup, long j, final IAdInterface.ISplashAdCallBack iSplashAdCallBack, final IAdInterface.IAdClickListener iAdClickListener) {
        String codeId2;
        this.onComplete = iAdClickListener;
        this.isClick = false;
        if ((new Random().nextInt(101) <= this.entity.getPercentNum() && this.entity.getPercentNum() != 0) || this.entity.getPercentNum() == 100) {
            codeId2 = this.entity.getCodeId();
        } else {
            codeId2 = this.entity.getCodeId2();
        }
        if (TextUtils.isEmpty(codeId2)) {
            codeId2 = ParamsID.kaipingId;
        }
        this.entity.setCodeId(codeId2);
        uploadPV("开屏广告_入口", "");
        uploadCollectSdk("开屏广告", "BUSplashAd", "0");
        this.mTTAdNative.loadSplashAd(new AdSlot.Builder().setCodeId(codeId2).setSupportDeepLink(true).setImageAcceptedSize(UIUtils.getScreenWidth(App.getInstance()), (UIUtils.getScreenHeight(App.getInstance()) - UIUtils.dip2px(100.0f)) + UIUtils.getStatusBarHeight(this.activityContext.get())).build(), new TTAdNative.SplashAdListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.5
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            @MainThread
            public void onError(int i, String str) {
                iSplashAdCallBack.onResult(11, PangleAdmanger.this.entity.getAdType());
                PangleAdmanger.this.uploadPV("开屏广告_错误", str);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener
            @MainThread
            public void onTimeout() {
                iSplashAdCallBack.onResult(11, PangleAdmanger.this.entity.getAdType());
                PangleAdmanger.this.uploadPV("开屏广告_错误", "timeout");
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener
            @MainThread
            public void onSplashAdLoad(TTSplashAd tTSplashAd) {
                if (tTSplashAd == null) {
                    iSplashAdCallBack.onResult(11, PangleAdmanger.this.entity.getAdType());
                    PangleAdmanger.this.uploadPV("开屏广告_错误", "开屏广告为空");
                    return;
                }
                View splashView = tTSplashAd.getSplashView();
                viewGroup.removeAllViews();
                viewGroup.addView(splashView);
                tTSplashAd.setSplashInteractionListener(new TTSplashAd.AdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.5.1
                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdClicked(View view, int i) {
                        PangleAdmanger.this.uploadPV("开屏广告_点击", "");
                        PangleAdmanger.this.isClick = true;
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdShow(View view, int i) {
                        iSplashAdCallBack.onResult(10, PangleAdmanger.this.entity.getAdType());
                        PangleAdmanger.this.uploadPV("开屏广告_展示", "");
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdSkip() {
                        iAdClickListener.onAdClick();
                        PangleAdmanger.this.uploadPV("开屏广告_跳过", "");
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdTimeOver() {
                        iAdClickListener.onAdClick();
                    }
                });
            }
        }, 3500);
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadInteraction(final IAdInterface.IInteractionCallBack iInteractionCallBack) {
        this.mTTAdNative.loadNativeExpressAd(new AdSlot.Builder().setCodeId(this.entity.getCodeId()).setSupportDeepLink(true).setAdCount(1).setExpressViewAcceptedSize(this.entity.getBannerWidth(), 0.0f).build(), new TTAdNative.NativeExpressAdListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.6
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                MsLogUtil.m7977e("lzh", "loadInteractionExpressAd---onError: code==" + i + "--message==" + str);
                iInteractionCallBack.onResult(11, null);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.size() == 0) {
                    return;
                }
                list.get(0).setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.6.1
                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdClicked(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdShow(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderFail(View view, String str, int i) {
                        Log.e("lzh", "loadInteractionExpressAd---onError: code==" + i + "--message==" + str);
                        iInteractionCallBack.onResult(11, null);
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderSuccess(View view, float f, float f2) {
                        MsLogUtil.m7979d("lzh", "v:" + f + "v1:" + f2);
                        iInteractionCallBack.onResult(10, view);
                    }
                });
                list.get(0).setDislikeCallback((Activity) PangleAdmanger.this.activityContext.get(), new TTAdDislike.DislikeInteractionCallback() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger.6.2
                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onCancel() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onShow() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onSelected(int i, String str, boolean z) {
                        iInteractionCallBack.onClose();
                    }
                });
                list.get(0).render();
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onResume() {
        if (this.isClick) {
            this.isClick = false;
            IAdInterface.IAdClickListener iAdClickListener = this.onComplete;
            if (iAdClickListener != null) {
                iAdClickListener.onAdClick();
            }
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    protected void uploadPV(String str, String str2) {
        String str3;
        String str4 = System.currentTimeMillis() + "";
        try {
            str3 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            str3 = str4;
        }
        StatisticsUploadUtils.uploadRealTime(App.getInstance(), "sliulan0002", this.entity.getChannelName(), str, this.entity.getCodeId(), "穿山甲广告", "", str3, this.uuid, str2);
    }

    private void uploadCollectSdk(String str, String str2, String str3) {
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext.get(), "S2ndpage1214", str, "穿山甲广告", "", str2, str3);
    }
}
