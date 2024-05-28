package com.sinovatech.unicom.separatemodule.advtise.admanager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.p281qq.p282e.ads.rewardvideo.RewardVideoAD;
import com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.ads.splash.SplashAD;
import com.p281qq.p282e.ads.splash.SplashADListener;
import com.p281qq.p282e.comm.util.AdError;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YLHAdmanger extends AbstractAdManager {
    private static YLHAdmanger admanger;
    private WeakReference<AppCompatActivity> activityContext;
    private IAdInterface.IAdClickListener adCompletel;
    private AdConfigEntity entity;
    private boolean isClick;
    private boolean isPause;
    private RewardVideoAD mRewardVideoAd;
    private SplashAD splashAD;

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

    private YLHAdmanger() {
    }

    public static YLHAdmanger getInstance(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity) {
        if (admanger == null) {
            synchronized (YLHAdmanger.class) {
                if (admanger == null) {
                    admanger = new YLHAdmanger();
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
    public void loadVido(String str, final String str2, final IAdInterface.IVideoCallBack iVideoCallBack) {
        uploadPV("激励视频_入口", "");
        if (this.entity.isPrepareLoad()) {
            iVideoCallBack.onResult(12, "优量汇视频暂不支持缓存");
            return;
        }
        this.mRewardVideoAd = new RewardVideoAD(this.activityContext.get(), this.entity.getCodeId(), new RewardVideoADListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.YLHAdmanger.1
            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onADClick() {
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onADExpose() {
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onVideoComplete() {
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onADLoad() {
                if (YLHAdmanger.this.mRewardVideoAd != null) {
                    YLHAdmanger.this.mRewardVideoAd.showAD();
                }
                YLHAdmanger.this.uploadPV("激励视频_返回量", "");
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onVideoCached() {
                iVideoCallBack.onResult(14, "缓存完成");
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onADShow() {
                YLHAdmanger.this.uploadPV("激励视频_展示", "");
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onReward(Map<String, Object> map) {
                iVideoCallBack.onResult(13, str2);
                YLHAdmanger.this.uploadPV("激励视频_发放积分", "");
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onADClose() {
                iVideoCallBack.onResult(15, "播放完成");
                YLHAdmanger.this.uploadPV("激励视频_回调前端", str2);
            }

            @Override // com.p281qq.p282e.ads.rewardvideo.RewardVideoADListener
            public void onError(AdError adError) {
                if (!"1".equals(YLHAdmanger.this.entity.getSubFlag())) {
                    UIUtils.toast("广告加载异常，请稍后再试【y40004】");
                }
                IAdInterface.IVideoCallBack iVideoCallBack2 = iVideoCallBack;
                iVideoCallBack2.onResult(12, adError.getErrorMsg() + "(" + adError.getErrorCode() + ")");
                YLHAdmanger yLHAdmanger = YLHAdmanger.this;
                yLHAdmanger.uploadPV("激励视频_播放失败", adError.getErrorMsg() + "(" + adError.getErrorCode() + ")");
            }
        }, false);
        MsLogUtil.m7979d("mRewardVideoAd", "UserToken" + UserManager.getInstance().getUserToken());
        this.mRewardVideoAd.setServerSideVerificationOptions(new ServerSideVerificationOptions.Builder().setCustomData(str).setUserId(UserManager.getInstance().getCurrentPhoneNumber()).build());
        this.mRewardVideoAd.loadAD();
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface
    public void loadSplash(ViewGroup viewGroup, long j, final IAdInterface.ISplashAdCallBack iSplashAdCallBack, final IAdInterface.IAdClickListener iAdClickListener) {
        this.isClick = false;
        this.isPause = false;
        this.adCompletel = iAdClickListener;
        UIUtils.logD("XinTaiAdmanger", "-----优量汇渠道广告入口");
        String codeId = this.entity.getCodeId();
        if (TextUtils.isEmpty(codeId)) {
            codeId = "2021484417779864";
        }
        this.entity.setCodeId(codeId);
        uploadPV("开屏广告_请求", "");
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext.get(), "S2ndpage1214", "开屏广告", "YLHAdSDK", "", "BUSplashAd", "0");
        this.splashAD = new SplashAD(this.activityContext.get(), codeId, new SplashADListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.admanager.YLHAdmanger.2
            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADExposure() {
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADPresent() {
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADTick(long j2) {
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADDismissed() {
                if (YLHAdmanger.this.isClick && YLHAdmanger.this.isPause) {
                    return;
                }
                iAdClickListener.onAdClick();
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onNoAD(AdError adError) {
                iSplashAdCallBack.onResult(11, YLHAdmanger.this.entity.getAdType());
                YLHAdmanger.this.uploadPV("开屏广告_错误", adError.getErrorMsg());
                UIUtils.logD("managerWelcome", "错误---" + adError.getErrorMsg());
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADClicked() {
                YLHAdmanger.this.uploadPV("开屏广告_点击", "");
                YLHAdmanger.this.isClick = true;
            }

            @Override // com.p281qq.p282e.ads.splash.SplashADListener
            public void onADLoaded(long j2) {
                iSplashAdCallBack.onResult(10, YLHAdmanger.this.entity.getAdType());
                YLHAdmanger.this.uploadPV("开屏广告_展示", "");
                UIUtils.logD("XinTaiAdmanger", "-----优量汇渠道广告成功");
            }
        });
        this.splashAD.preLoad();
        this.splashAD.fetchAndShowIn(viewGroup);
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onPause() {
        this.isPause = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    public void onResume() {
        this.isPause = false;
        if (this.isClick) {
            this.isClick = false;
            this.adCompletel.onAdClick();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.admanager.AbstractAdManager
    protected void uploadPV(String str, String str2) {
        StatisticsUploadUtils.uploadRealTimeBeiDong(App.getInstance(), "s10", this.entity.getChannelName(), str, this.entity.getCodeId(), "优量汇广告", str2);
    }
}
