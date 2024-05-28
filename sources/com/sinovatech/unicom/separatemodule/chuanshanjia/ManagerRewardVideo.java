package com.sinovatech.unicom.separatemodule.chuanshanjia;

import android.app.Activity;
import android.support.p086v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.jsplugin.VideoHandler;
import com.sinovatech.unicom.separatemodule.advtise.utils.AdConfigParser;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import io.reactivex.disposables.Disposable;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerRewardVideo {
    private static ManagerRewardVideo mrv;
    public WeakReference<AppCompatActivity> activityContext;
    private boolean isBalckUser;
    private boolean isTimeOut;
    private boolean isVideoShow;
    private long lastTimeClock;
    private TTNativeExpressAd mBannerTTad;
    private TTRewardVideoAd mttRewardVideoAd;
    private boolean onRewardVerify;

    /* renamed from: pd */
    private CustomePorgressDialog f18514pd;
    private String requestId;
    private boolean showBannerFlag;
    private boolean showXXLFlag;
    private Disposable timeOutSubscribe;
    private String uuid;
    private WebView webView;
    private Map<String, TTRewardVideoAd> rewardVideoAdMap = new HashMap();
    private Map<String, ConfigEntity> cacheEntityMap = new HashMap();

    private boolean isVerfyBanner(int i, int i2) {
        return (i == 600 && i2 == 300) || (i == 600 && i2 == 400) || ((i == 600 && i2 == 500) || ((i == 600 && i2 == 260) || ((i == 600 && i2 == 90) || ((i == 600 && i2 == 150) || ((i == 640 && i2 == 100) || ((i == 690 && i2 == 388) || ((i == 375 && i2 == 284) || ((i == 375 && i2 == 275) || (i == 375 && i2 == 126)))))))));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class UPTYPE {
        public static final int BANNERCLICK = 13;
        public static final int BANNERDOWNLOAD = 14;
        public static final int BANNERERROR = 15;
        public static final int BANNERPV = 11;
        public static final int BANNERSHOW = 12;
        public static final int BLACKLISTUSER = 22;
        public static final int SKIP = 24;
        public static final int TIMEOUT = 23;
        public static final int VIDEOCLICK = 2;
        public static final int VIDEOCOMPLETE = 3;
        public static final int VIDEODOWNLOAD = 7;
        public static final int VIDEOFAIL = 6;
        public static final int VIDEOFAJIDFEN = 4;
        public static final int VIDEOFAJIFEN = 8;
        public static final int VIDEOFAJIFENSHIBAI = 10;
        public static final int VIDEOHUIDIAO = 9;
        public static final int VIDEOLINGJIANGLI = 5;
        public static final int VIDEOPV = 0;
        public static final int VIDEOSHOW = 1;
        public static final int XXLCLICK = 18;
        public static final int XXLCLOSE = 21;
        public static final int XXLDOWNLOAD = 19;
        public static final int XXLERROR = 20;
        public static final int XXLPV = 16;
        public static final int XXLSHOW = 17;

        private UPTYPE() {
        }
    }

    public static synchronized ManagerRewardVideo getInstance() {
        ManagerRewardVideo managerRewardVideo;
        synchronized (ManagerRewardVideo.class) {
            if (mrv == null) {
                synchronized (ManagerRewardVideo.class) {
                    if (mrv == null) {
                        mrv = new ManagerRewardVideo();
                    }
                }
            }
            managerRewardVideo = mrv;
        }
        return managerRewardVideo;
    }

    private ManagerRewardVideo() {
    }

    public void handleJS(AppCompatActivity appCompatActivity, WebView webView, String str) {
        this.activityContext = new WeakReference<>(appCompatActivity);
        this.webView = webView;
        ConfigEntity entity = getEntity(str);
        entity.setRequestConfig(str);
        uploadPv("入口启动", entity);
        this.isBalckUser = App.toutiaoBalck;
        if ("isToutiaoBlackList".equals(entity.getAction())) {
            String str2 = "javascript:UnicomCSJCoinAd.getUserIsBalckList.browserInfoCallback(" + this.isBalckUser + ")";
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
            } else {
                webView.loadUrl(str2);
            }
            uploadPv("黑名单用户", entity);
        }
        if ("showBannerAd".equals(entity.getAction())) {
            if (entity.getHeight() == 0 && entity.getScale() < 0.5d) {
                try {
                    String str3 = "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError(" + URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"top is null\"}", "UTF-8") + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str3);
                    } else {
                        webView.loadUrl(str3);
                    }
                    return;
                } catch (Exception e) {
                    e.getMessage();
                    return;
                }
            }
            this.showBannerFlag = true;
            if (System.currentTimeMillis() - this.lastTimeClock < 200) {
                return;
            }
            this.lastTimeClock = System.currentTimeMillis();
            UIUtils.logD("showBannerAd", "configEntity---" + entity);
            UIUtils.logD("showBannerAd", "time---" + System.currentTimeMillis());
            if (this.isBalckUser) {
                uploadPv(22, entity);
                try {
                    String str4 = "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError(" + URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"blacklistUser\"}", "UTF-8") + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str4);
                    } else {
                        webView.loadUrl(str4);
                    }
                } catch (Exception e2) {
                    e2.getMessage();
                }
            } else {
                uploadPv(11, entity);
                initBanner(entity);
            }
        }
        if ("showXXLAd".equals(entity.getAction())) {
            this.showXXLFlag = true;
            if (this.isBalckUser) {
                try {
                    String str5 = "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadError(" + URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"blacklistUser\"}", "UTF-8") + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str5);
                    } else {
                        webView.loadUrl(str5);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                uploadPv(16, entity);
                initXinxiLiu(entity);
            }
        }
        if ("showVideoAd".equals(entity.getAction())) {
            AdConfigEntity adConfigEntity = new AdConfigEntity();
            try {
                adConfigEntity = AdConfigParser.parser(new JSONObject(str));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            adConfigEntity.setAdType("PANGLE");
            adConfigEntity.setAction("showVideoAd");
            adConfigEntity.setOldVersion(true);
            new VideoHandler(this.activityContext.get(), adConfigEntity, AdFactory.getAd(this.activityContext.get(), adConfigEntity), webView).exec();
        }
        if ("hideBannerAd".equals(entity.getAction())) {
            this.showBannerFlag = false;
            TTNativeExpressAd tTNativeExpressAd = this.mBannerTTad;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.destroy();
            }
            if (this.activityContext.get() instanceof WebDetailActivity) {
                ((WebDetailActivity) this.activityContext.get()).showToutiaoBanner(new ToutiaoEvent(EventBusUtils.EVENT_toutiao_Banner_Hide, new Object()));
            }
        }
        if ("hideXXLAd".equals(entity.getAction())) {
            this.showXXLFlag = false;
            if (this.activityContext.get() instanceof WebDetailActivity) {
                ((WebDetailActivity) this.activityContext.get()).showToutiaoBanner(new ToutiaoEvent(EventBusUtils.EVENT_toutiao_xinxiliu_Hide, new Object()));
            }
        }
        if ("getBrowserInfo".equals(entity.getAction())) {
            String str6 = "{\"width\":" + UIUtils.px2dp(appCompatActivity, UIUtils.getScreenWidth((Activity) appCompatActivity)) + ",\"height\":" + UIUtils.px2dp(appCompatActivity, webView.getMeasuredHeight()) + "}";
            try {
                str6 = URLEncoder.encode(str6, "UTF-8");
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
            }
            String str7 = "javascript:UnicomCSJCoinAd.getBrowserInfo.browserInfoCallback(" + str6 + ")";
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str7);
            } else {
                webView.loadUrl(str7);
            }
        }
    }

    private void initBanner(final ConfigEntity configEntity) {
        TTAdNative createAdNative = TTAdManagerHolder.get().createAdNative(this.activityContext.get());
        AdSlot build = new AdSlot.Builder().setCodeId(configEntity.getCodeId()).setSupportDeepLink(true).setExpressViewAcceptedSize((float) (configEntity.getScale() * 360.0d), (float) (((configEntity.getBannerHeight() * 360) / configEntity.getBannerWidth()) * configEntity.getScale())).setNativeAdType(1).setAdCount(1).setImageAcceptedSize(configEntity.getBannerWidth(), configEntity.getBannerHeight()).setIsAutoPlay(false).build();
        uploadCollectSdk(App.webviewTitle, "BUNativeExpressBannerView", "0");
        createAdNative.loadBannerExpressAd(build, new TTAdNative.NativeExpressAdListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                try {
                    configEntity.setMessage(str);
                    ManagerRewardVideo.this.uploadPv(15, configEntity);
                    String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + i + "\"}", "UTF-8");
                    WebView webView = ManagerRewardVideo.this.webView;
                    String str2 = "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError(" + encode + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                    } else {
                        webView.loadUrl(str2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.size() == 0) {
                    return;
                }
                ManagerRewardVideo.this.mBannerTTad = list.get(0);
                ManagerRewardVideo.this.mBannerTTad.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.1.1
                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdShow(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdClicked(View view, int i) {
                        ManagerRewardVideo.this.uploadPv(13, configEntity);
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderFail(View view, String str, int i) {
                        configEntity.setMessage(str);
                        ManagerRewardVideo.this.uploadPv(15, configEntity);
                        try {
                            String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + i + "\"}", "UTF-8");
                            WebView webView = ManagerRewardVideo.this.webView;
                            String str2 = "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError(" + encode + ")";
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                            } else {
                                webView.loadUrl(str2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderSuccess(View view, float f, float f2) {
                        UIUtils.logD("BANNER_SHOW", "BANNER_SUCCESS" + view.getWidth());
                        if (!ManagerRewardVideo.this.showBannerFlag) {
                            ManagerRewardVideo.this.uploadPv("banner_展示过程隐藏", configEntity);
                            return;
                        }
                        ManagerRewardVideo.this.uploadPv(12, configEntity);
                        ToutiaoEvent toutiaoEvent = new ToutiaoEvent(EventBusUtils.EVENT_toutiao_Banner_Show, view);
                        toutiaoEvent.setHeight(configEntity.getHeight());
                        toutiaoEvent.setBannerWidth(configEntity.getBannerWidth());
                        toutiaoEvent.setBannerHeight(configEntity.getBannerHeight());
                        toutiaoEvent.setScale(configEntity.getScale());
                        toutiaoEvent.setAction(configEntity.getAction());
                        if (ManagerRewardVideo.this.activityContext.get() instanceof WebDetailActivity) {
                            ((WebDetailActivity) ManagerRewardVideo.this.activityContext.get()).showToutiaoBanner(toutiaoEvent);
                        }
                        if ("hideBannerAd".equals(configEntity.getAction())) {
                            WebView webView = ManagerRewardVideo.this.webView;
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError('timeout)");
                                return;
                            } else {
                                webView.loadUrl("javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadError('timeout)");
                                return;
                            }
                        }
                        WebView webView2 = ManagerRewardVideo.this.webView;
                        if (webView2 instanceof Object) {
                            NBSWebLoadInstrument.loadUrl((Object) webView2, "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadSuccess()");
                        } else {
                            webView2.loadUrl("javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onLoadSuccess()");
                        }
                    }
                });
                ManagerRewardVideo.this.mBannerTTad.setDislikeCallback(ManagerRewardVideo.this.activityContext.get(), new TTAdDislike.DislikeInteractionCallback() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.1.2
                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onCancel() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onShow() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onSelected(int i, String str, boolean z) {
                        ToutiaoEvent toutiaoEvent = new ToutiaoEvent(EventBusUtils.EVENT_toutiao_Banner_Hide, null);
                        if (ManagerRewardVideo.this.activityContext.get() instanceof WebDetailActivity) {
                            ((WebDetailActivity) ManagerRewardVideo.this.activityContext.get()).showToutiaoBanner(toutiaoEvent);
                        }
                        try {
                            String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + str + "\"}", "UTF-8");
                            WebView webView = ManagerRewardVideo.this.webView;
                            String str2 = "javascript:UnicomCSJCoinAd.showToutiaoBannerAd.onHideManually(" + encode + ")";
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                            } else {
                                webView.loadUrl(str2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                ManagerRewardVideo.this.mBannerTTad.render();
                if (configEntity.getAdIntervals() != 0) {
                    if (configEntity.getAdIntervals() > 30) {
                        ManagerRewardVideo.this.mBannerTTad.setSlideIntervalTime(configEntity.getAdIntervals() * 1000);
                    } else {
                        ManagerRewardVideo.this.mBannerTTad.setSlideIntervalTime(30000);
                    }
                }
                ManagerRewardVideo.this.mBannerTTad.setDownloadListener(new TTAppDownloadListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.1.3
                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadActive(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadFailed(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadPaused(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onIdle() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onInstalled(String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadFinished(long j, String str, String str2) {
                        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                        if (sharePreferenceUtil.getBoolean("chuanshanjia3_" + str)) {
                            return;
                        }
                        SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
                        sharePreferenceUtil2.putBoolean("chuanshanjia3_" + str, true);
                        ManagerRewardVideo.this.uploadPv(14, configEntity);
                    }
                });
            }
        });
    }

    private void initXinxiLiu(final ConfigEntity configEntity) {
        setStartUISetting();
        AdSlot build = new AdSlot.Builder().setCodeId(configEntity.getCodeId()).setSupportDeepLink(true).setAdCount(1).setExpressViewAcceptedSize((float) (configEntity.getScale() * 360.0d), (float) (((configEntity.getBannerHeight() * 360) / configEntity.getBannerWidth()) * configEntity.getScale())).setImageAcceptedSize(600, 600).build();
        TTAdNative createAdNative = TTAdManagerHolder.get().createAdNative(this.activityContext.get());
        uploadCollectSdk(App.webviewTitle, "BUAdSlotAdTypeFeed", "0");
        createAdNative.loadNativeExpressAd(build, new TTAdNative.NativeExpressAdListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.2
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                ManagerRewardVideo.this.setEndUISetting();
                configEntity.setMessage(str);
                ManagerRewardVideo.this.uploadPv(20, configEntity);
                try {
                    String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + i + "\"}", "UTF-8");
                    WebView webView = ManagerRewardVideo.this.webView;
                    String str2 = "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadError(" + encode + ")";
                    if (webView instanceof Object) {
                        NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                    } else {
                        webView.loadUrl(str2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.size() == 0) {
                    ManagerRewardVideo.this.setEndUISetting();
                    return;
                }
                TTNativeExpressAd tTNativeExpressAd = list.get(0);
                tTNativeExpressAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.2.1
                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdShow(View view, int i) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onAdClicked(View view, int i) {
                        ManagerRewardVideo.this.uploadPv(18, configEntity);
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderFail(View view, String str, int i) {
                        configEntity.setMessage(str);
                        ManagerRewardVideo.this.uploadPv(20, configEntity);
                        try {
                            String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + i + "\"}", "UTF-8");
                            WebView webView = ManagerRewardVideo.this.webView;
                            String str2 = "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadError(" + encode + ")";
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                            } else {
                                webView.loadUrl(str2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ManagerRewardVideo.this.setEndUISetting();
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
                    public void onRenderSuccess(View view, float f, float f2) {
                        ManagerRewardVideo.this.setEndUISetting();
                        if (!ManagerRewardVideo.this.showXXLFlag) {
                            ManagerRewardVideo.this.uploadPv("xxl_展示过程隐藏", configEntity);
                            return;
                        }
                        ManagerRewardVideo.this.uploadPv(17, configEntity);
                        ToutiaoEvent toutiaoEvent = new ToutiaoEvent(EventBusUtils.EVENT_toutiao_xinxiliu_Show, view);
                        toutiaoEvent.setHeight(configEntity.getHeight());
                        toutiaoEvent.setScale(configEntity.getScale());
                        toutiaoEvent.setBannerWidth(configEntity.getBannerWidth());
                        toutiaoEvent.setBannerHeight(configEntity.getBannerHeight());
                        toutiaoEvent.setAction(configEntity.getAction());
                        if ("hideXXLAd".equals(configEntity.getAction())) {
                            WebView webView = ManagerRewardVideo.this.webView;
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadError('timeout')");
                            } else {
                                webView.loadUrl("javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadError('timeout')");
                            }
                        } else {
                            WebView webView2 = ManagerRewardVideo.this.webView;
                            if (webView2 instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView2, "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadSuccess()");
                            } else {
                                webView2.loadUrl("javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onLoadSuccess()");
                            }
                        }
                        if (ManagerRewardVideo.this.activityContext.get() instanceof WebDetailActivity) {
                            ((WebDetailActivity) ManagerRewardVideo.this.activityContext.get()).showToutiaoBanner(toutiaoEvent);
                        }
                    }
                });
                tTNativeExpressAd.render();
                tTNativeExpressAd.setDownloadListener(new TTAppDownloadListener() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.2.2
                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadActive(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadFailed(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadFinished(long j, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onDownloadPaused(long j, long j2, String str, String str2) {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onIdle() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAppDownloadListener
                    public void onInstalled(String str, String str2) {
                    }
                });
                tTNativeExpressAd.setDislikeCallback(ManagerRewardVideo.this.activityContext.get(), new TTAdDislike.DislikeInteractionCallback() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.ManagerRewardVideo.2.3
                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onCancel() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onShow() {
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onSelected(int i, String str, boolean z) {
                        ToutiaoEvent toutiaoEvent = new ToutiaoEvent(EventBusUtils.EVENT_toutiao_xinxiliu_Hide, null);
                        if (ManagerRewardVideo.this.activityContext.get() instanceof WebDetailActivity) {
                            ((WebDetailActivity) ManagerRewardVideo.this.activityContext.get()).showToutiaoBanner(toutiaoEvent);
                        }
                        try {
                            String encode = URLEncoder.encode("{\"status\":\"failed\",\"errorMessage\":\"" + i + "\"}", "UTF-8");
                            WebView webView = ManagerRewardVideo.this.webView;
                            String str2 = "javascript:UnicomCSJCoinAd.showToutiaoXXLAd.onHideManually(" + encode + ")";
                            if (webView instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                            } else {
                                webView.loadUrl(str2);
                            }
                            ManagerRewardVideo.this.uploadPv(21, configEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
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

    private ConfigEntity getEntity(String str) {
        ManagerRewardVideo managerRewardVideo;
        ConfigEntity configEntity = new ConfigEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("action");
            String optString2 = jSONObject.optString("codeId");
            String optString3 = jSONObject.optString("channel");
            String optString4 = jSONObject.optString("screenId");
            String optString5 = jSONObject.optString("gameId");
            String optString6 = jSONObject.optString("gameSceneId");
            boolean optBoolean = jSONObject.optBoolean("rewards");
            int optInt = jSONObject.optInt("timeOut");
            JSONObject optJSONObject = jSONObject.optJSONObject("style");
            int i = 600;
            int i2 = 90;
            try {
                if (optJSONObject != null) {
                    configEntity.setHeight(optJSONObject.optInt("top"));
                    Double valueOf = Double.valueOf(optJSONObject.optDouble("scale"));
                    configEntity.setScale(Double.isNaN(valueOf.doubleValue()) ? 1.0d : valueOf.doubleValue());
                    int optInt2 = optJSONObject.optInt("height");
                    int optInt3 = optJSONObject.optInt("width");
                    if (optInt2 == 0 || optInt3 == 0) {
                        managerRewardVideo = this;
                    } else {
                        i = optInt3;
                        i2 = optInt2;
                        managerRewardVideo = this;
                    }
                    if (managerRewardVideo.isVerfyBanner(i, i2)) {
                        configEntity.setBannerHeight(i2);
                        configEntity.setBannerWidth(i);
                    } else {
                        UIUtils.toast("请填写合法的宽高");
                    }
                } else {
                    configEntity.setBannerHeight(90);
                    configEntity.setBannerWidth(600);
                }
                configEntity.setAction(optString);
                configEntity.setCodeId(optString2);
                configEntity.setChannel(optString3);
                configEntity.setScreenId(optString4);
                configEntity.setGameId(optString5);
                configEntity.setGameSceneId(optString6);
                configEntity.setRewards(optBoolean);
                configEntity.setTimeOutSecond(optInt);
                configEntity.setDirection(jSONObject.optInt("direction"));
                configEntity.setAcId(jSONObject.optString("acId"));
                configEntity.setTaskId(jSONObject.optString("taskId"));
                configEntity.setRemark(jSONObject.optString("remark"));
                configEntity.setAdIntervals(jSONObject.optInt("adIntervals"));
                configEntity.setChannelName(jSONObject.optString("channelName"));
                configEntity.setUnWantedToast(jSONObject.optBoolean("unWantedToast"));
                configEntity.setUnWantedToast2(jSONObject.optBoolean("unWantedToast2"));
                configEntity.setAccountChannel(jSONObject.optString("accountChannel"));
                configEntity.setAccountUserName(jSONObject.optString("accountUserName"));
                configEntity.setAccountPassword(jSONObject.optString("accountPassword"));
                configEntity.setAccountToken(jSONObject.optString("accountToken"));
                configEntity.setPrepareLoad("YES".equalsIgnoreCase(jSONObject.optString("isPrepareLoad")));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return configEntity;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return configEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadPv(int i, ConfigEntity configEntity) {
        String str;
        String str2;
        switch (i) {
            case 0:
                str = "激励视频_入口";
                break;
            case 1:
                str = "激励视频_展示";
                break;
            case 2:
                str = "激励视频_点击";
                break;
            case 3:
                str = "激励视频_播放完成";
                break;
            case 4:
                str = "激励视频_发放积分";
                break;
            case 5:
                str = "激励视频_领取奖励";
                break;
            case 6:
                str = "激励视频_播放失败";
                break;
            case 7:
                str = "激励视频_下载完成";
                break;
            case 8:
                str = "激励视频_发送积分成功";
                break;
            case 9:
                str = "激励视频_回调前端";
                break;
            case 10:
                str = "激励视频_发送积分失败";
                break;
            case 11:
                str = "banner_入口";
                break;
            case 12:
                str = "banner_展示";
                break;
            case 13:
                str = "banner_点击";
                break;
            case 14:
                str = "banner_下载完成";
                break;
            case 15:
                str = "banner_失败";
                break;
            case 16:
                str = "信息流_入口";
                break;
            case 17:
                str = "信息流_展示";
                break;
            case 18:
                str = "信息流_点击";
                break;
            case 19:
                str = "信息流_下载完成";
                break;
            case 20:
                str = "信息流_失败";
                break;
            case 21:
                str = "信息流_关闭";
                break;
            case 22:
                str = "拒绝广告用户";
                break;
            case 23:
                str = "激励视频超时";
                break;
            case 24:
                str = "激励视频跳过";
                break;
            default:
                str = "";
                break;
        }
        String str3 = System.currentTimeMillis() + "";
        try {
            str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            str2 = str3;
        }
        StatisticsUploadUtils.uploadRealTime(App.getInstance(), "sliulan0002", configEntity.getChannelName(), str, configEntity.getCodeId(), "", this.requestId, str2, this.uuid, configEntity.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadPv(String str, ConfigEntity configEntity) {
        String str2;
        String str3 = System.currentTimeMillis() + "";
        try {
            str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            str2 = str3;
        }
        StatisticsUploadUtils.uploadRealTime(App.getInstance(), "sliulanStep0002", configEntity.getChannelName(), str, configEntity.getCodeId(), configEntity.getRequestConfig(), this.requestId, str2, this.uuid, configEntity.getMessage());
    }

    private void uploadCollectSdk(String str, String str2, String str3) {
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext.get(), "S2ndpage1214", str, "穿山甲广告", "", "com.bytedance.sdk.openadsdk", "0");
    }
}
