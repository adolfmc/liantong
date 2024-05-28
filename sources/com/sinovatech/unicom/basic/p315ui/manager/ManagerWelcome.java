package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.util.NetworkUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.AdvertiseEntity;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.DownloaderVideo;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.bean.SplashAdConfigEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.service.DownLoadService;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerWelcome {
    public static boolean haveUpdatedActivities = false;
    public static String headlineTimeData = "";
    private AppCompatActivity activityContext;
    private AdvertiseEntity advertiseEntity;
    private List<LoginAccountEntity> bindAccountList;
    private AdvertiseEntity failAdvertiseEntity = null;
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome$KaipingClickInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface KaipingClickInterface {
        void onTiaozhuan();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome$PerformInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PerformInterface {
        void onJump();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome$WelcomeInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WelcomeInterface {
        void onDisMiss();

        void onFailed();

        void onSmottoSuccess(Drawable drawable);

        void onSuccess(Drawable drawable);

        void onSuccess(String str, String str2, String str3);

        void onToutiaoSuccess(AdvertiseEntity advertiseEntity);

        void onYLHSuccess(AdvertiseEntity advertiseEntity);

        void onZhuongchuanjukanSuccess(AdvertiseEntity advertiseEntity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome$YLHPerformInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface YLHPerformInterface {
        void onJump();
    }

    public ManagerWelcome(AppCompatActivity appCompatActivity) {
        this.advertiseEntity = null;
        this.activityContext = appCompatActivity;
        haveUpdatedActivities = false;
        this.advertiseEntity = null;
    }

    public ObservableSubscribeProxy<SplashAdConfigEntity> loadAdvertise() {
        HashMap hashMap = new HashMap();
        hashMap.put("mobile", this.userManager.getDefaultPhoneNumber());
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("isHomeAdv", "1");
        hashMap.put("provinceCode", this.userManager.getLocateProvinceCode());
        hashMap.put("cityCode", this.userManager.getLocateCityCode());
        hashMap.put("width", "" + UIUtils.getScreenWidth((Activity) this.activityContext));
        hashMap.put("height", "" + UIUtils.getScreenHeight(this.activityContext, UIUtils.ScreenHeightMode.FullScreen));
        hashMap.put("landType", "0");
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("imgIndex ", App.getSharePreferenceUtil().getString("welcom_imgIndex"));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(5, 5, 5).rxPost(URLSet.getAccountListData(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, SplashAdConfigEntity>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.1
            @Override // io.reactivex.functions.Function
            public SplashAdConfigEntity apply(@NonNull String str) throws Exception {
                UIUtils.logD("managerWelcome", "-----" + str);
                SplashAdConfigEntity splashAdConfigEntity = new SplashAdConfigEntity();
                JSONObject jSONObject = new JSONObject(str);
                App.getSharePreferenceUtil().putString("welcom_imgIndex", jSONObject.optString("imgIndex"));
                JSONObject optJSONObject = jSONObject.optJSONObject("adv");
                JSONObject jSONObject2 = optJSONObject.getJSONObject("startup_adv");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("fail_adv");
                splashAdConfigEntity.setSuccessConfigEntity(ManagerWelcome.parseAdConfig(jSONObject2));
                splashAdConfigEntity.setFailConfigEntity(ManagerWelcome.parseAdConfig(optJSONObject2));
                return splashAdConfigEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public void loadData(final WelcomeInterface welcomeInterface, ImageView imageView) {
        ((ObservableSubscribeProxy) Observable.create(new ObservableOnSubscribe<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.5
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                String accountname;
                ManagerWelcome managerWelcome = ManagerWelcome.this;
                managerWelcome.bindAccountList = managerWelcome.userManager.getBindAccountNameList("");
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < ManagerWelcome.this.bindAccountList.size()) {
                    if ("02".equals(((LoginAccountEntity) ManagerWelcome.this.bindAccountList.get(i)).getAccouttype())) {
                        accountname = ((LoginAccountEntity) ManagerWelcome.this.bindAccountList.get(i)).getIntact();
                        if (TextUtils.isEmpty(accountname)) {
                            accountname = ((LoginAccountEntity) ManagerWelcome.this.bindAccountList.get(i)).getAccountname();
                        }
                    } else {
                        accountname = ((LoginAccountEntity) ManagerWelcome.this.bindAccountList.get(i)).getAccountname();
                    }
                    i++;
                    if (i == ManagerWelcome.this.bindAccountList.size()) {
                        sb.append(accountname);
                    } else {
                        sb.append(accountname);
                        sb.append(",");
                    }
                }
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("mobile", sb.toString());
                    hashMap.put("version", ManagerWelcome.this.activityContext.getString(2131886969));
                    hashMap.put("isHomeAdv", "1");
                    hashMap.put("provinceCode", ManagerWelcome.this.userManager.getLocateProvinceCode());
                    hashMap.put("cityCode", ManagerWelcome.this.userManager.getLocateCityCode());
                    hashMap.put("width", "" + UIUtils.getScreenWidth((Activity) ManagerWelcome.this.activityContext));
                    hashMap.put("height", "" + UIUtils.getScreenHeight(ManagerWelcome.this.activityContext, UIUtils.ScreenHeightMode.FullScreen));
                    hashMap.put("landType", "0");
                    hashMap.put("appId", ManagerWelcome.this.userManager.getLoginAppId());
                    hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
                    hashMap.put("imgIndex ", App.getSharePreferenceUtil().getString("welcom_imgIndex"));
                    hashMap.put("isHavShow", App.getSharePreferenceUtil().getString("isHavShow"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                observableEmitter.onNext(App.getAsyncHttpClient(3, 3, 3).syncPost(URLSet.getAccountListData(), hashMap));
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String[]>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.4
            @Override // io.reactivex.functions.Function
            public String[] apply(String str) throws Exception {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    App.getSharePreferenceUtil().putString("welcom_imgIndex", jSONObject.optString("imgIndex"));
                    JSONObject jSONObject2 = jSONObject.getJSONObject("adv").getJSONObject("startup_adv");
                    JSONArray jSONArray = jSONObject2.getJSONArray("advCntList");
                    ManagerWelcome.this.advertiseEntity = ManagerWelcome.this.analsisAdvJson(jSONArray);
                    ManagerWelcome.this.failAdvertiseEntity = ManagerWelcome.this.analsisAdvJson(jSONObject2.getJSONObject("fail_adv").getJSONArray("advCntList"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new String[]{"", ""};
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new C78372(welcomeInterface, imageView), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                welcomeInterface.onFailed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78372 implements Consumer<String[]> {
        final /* synthetic */ WelcomeInterface val$anInterface;
        final /* synthetic */ ImageView val$imageView;

        C78372(WelcomeInterface welcomeInterface, ImageView imageView) {
            this.val$anInterface = welcomeInterface;
            this.val$imageView = imageView;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(String[] strArr) throws Exception {
            StatisticsUploadUtils.uploadRealTime(ManagerWelcome.this.activityContext, "kaipingShow", "启动页-广告", ManagerWelcome.this.advertiseEntity.getWelcomeType(), ManagerWelcome.this.advertiseEntity.getAdvertiseId(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle(), ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL());
            if (ManagerWelcome.this.advertiseEntity == null || !"headline".equals(ManagerWelcome.this.advertiseEntity.getWelcomeType())) {
                if (ManagerWelcome.this.advertiseEntity == null || !"advertising".equals(ManagerWelcome.this.advertiseEntity.getWelcomeType())) {
                    if (ManagerWelcome.this.advertiseEntity == null || !"splashad".equals(ManagerWelcome.this.advertiseEntity.getWelcomeType())) {
                        if (ManagerWelcome.this.advertiseEntity != null && !TextUtils.isEmpty(ManagerWelcome.this.advertiseEntity.getAdvertiseImageURL())) {
                            if (!"2".equals(ManagerWelcome.this.advertiseEntity.getWelcomeType())) {
                                Glide.with(ManagerWelcome.this.activityContext.getApplicationContext()).asDrawable().load(ManagerWelcome.this.advertiseEntity.getAdvertiseImageURL()).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.2.1
                                    @Override // com.bumptech.glide.request.target.Target
                                    public /* bridge */ /* synthetic */ void onResourceReady(@android.support.annotation.NonNull Object obj, @Nullable Transition transition) {
                                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                                    }

                                    public void onResourceReady(@android.support.annotation.NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                                        try {
                                            if (ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL().contains("androidId")) {
                                                AdvertiseEntity advertiseEntity = ManagerWelcome.this.advertiseEntity;
                                                advertiseEntity.setAdvertiseTargetURL(ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL() + "&pvType=kaiping");
                                                StatisticsUploadUtils.uploadRealTime(ManagerWelcome.this.activityContext, "hzyl0001", "开屏广告", "入口曝光", ManagerWelcome.this.advertiseEntity.getAdvertiseId(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle(), ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL());
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        C78372.this.val$anInterface.onSuccess(drawable);
                                        C78372.this.val$imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.2.1.1
                                            @Override // android.view.View.OnClickListener
                                            public void onClick(View view) {
                                                NBSActionInstrumentation.onClickEventEnter(view, this);
                                                Tracker.onClick(view);
                                                if (!TextUtils.isEmpty(ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL())) {
                                                    if (ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL().contains("androidId")) {
                                                        StatisticsUploadUtils.uploadRealTime(ManagerWelcome.this.activityContext, "hzyl0001", "开屏广告", "入口PV", ManagerWelcome.this.advertiseEntity.getAdvertiseId(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle(), ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL());
                                                    }
                                                    C78372.this.val$anInterface.onDisMiss();
                                                    ManagerWelcome.this.activityContext.startActivity(new Intent(ManagerWelcome.this.activityContext, MainActivity.class));
                                                    StatisticsUploadUtils.uploadRealTime(ManagerWelcome.this.activityContext, "10", "启动页-广告", "广告", ManagerWelcome.this.advertiseEntity.getAdvertiseId(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle(), ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL());
                                                    IntentManager.generateIntentAndGo(ManagerWelcome.this.activityContext, ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle(), false, "get");
                                                    ManagerWelcome.this.activityContext.finish();
                                                    NBSActionInstrumentation.onClickEventExit();
                                                    return;
                                                }
                                                NBSActionInstrumentation.onClickEventExit();
                                            }
                                        });
                                    }

                                    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                                    public void onLoadFailed(@Nullable Drawable drawable) {
                                        super.onLoadFailed(drawable);
                                        C78372.this.val$anInterface.onFailed();
                                    }
                                });
                                return;
                            }
                            try {
                                if (UIUtils.checkPermissions("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
                                    DownloaderVideo downloaderVideo = new DownloaderVideo(ManagerWelcome.this.advertiseEntity.getAdvCode(), ManagerWelcome.this.advertiseEntity.getAdvertiseImageURL());
                                    if (downloaderVideo.isCached()) {
                                        this.val$anInterface.onSuccess(downloaderVideo.getPaht(), ManagerWelcome.this.advertiseEntity.getAdvertiseTargetURL(), ManagerWelcome.this.advertiseEntity.getAdvertiseTitle());
                                        return;
                                    }
                                    DownloaderVideo.clearVideo();
                                    if (NetworkUtil.getNetworkType(ManagerWelcome.this.activityContext) == 3) {
                                        Intent intent = new Intent(ManagerWelcome.this.activityContext, DownLoadService.class);
                                        intent.putExtra("advCode", ManagerWelcome.this.advertiseEntity.getAdvCode());
                                        intent.putExtra("imageUrl", ManagerWelcome.this.advertiseEntity.getAdvertiseImageURL());
                                        ManagerWelcome.this.activityContext.startService(intent);
                                    }
                                    this.val$anInterface.onFailed();
                                    return;
                                }
                                this.val$anInterface.onFailed();
                                return;
                            } catch (Exception e) {
                                this.val$anInterface.onFailed();
                                MsLogUtil.m7979d("ManagerWeclcome", e.getMessage());
                                return;
                            }
                        }
                        throw new Exception();
                    }
                    this.val$anInterface.onYLHSuccess(ManagerWelcome.this.advertiseEntity);
                    return;
                }
                this.val$anInterface.onZhuongchuanjukanSuccess(ManagerWelcome.this.advertiseEntity);
                return;
            }
            ManagerWelcome.headlineTimeData = ManagerWelcome.this.advertiseEntity.getAdvCode();
            this.val$anInterface.onToutiaoSuccess(ManagerWelcome.this.advertiseEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdvertiseEntity analsisAdvJson(JSONArray jSONArray) {
        this.advertiseEntity = new AdvertiseEntity();
        if (jSONArray != null && jSONArray.length() > 0) {
            JSONObject optJSONObject = jSONArray.optJSONObject(0);
            this.advertiseEntity.setAdvertiseTitle(optJSONObject.optString("title"));
            this.advertiseEntity.setAdvertiseImageURL(optJSONObject.optString("imgSrc"));
            this.advertiseEntity.setAdvertiseId(optJSONObject.optString("advCode"));
            this.advertiseEntity.setWelcomeType(optJSONObject.optString("welcomeType"));
            this.advertiseEntity.setAdvCode(optJSONObject.optString("advCode"));
            this.advertiseEntity.setAdvertiseTargetURL(optJSONObject.optString("targetUrl"));
            this.advertiseEntity.setCodeId(optJSONObject.optString("onePlaceAndroid"));
            this.advertiseEntity.setCodeId2(optJSONObject.optString("twoPlaceAndroid"));
            try {
                this.advertiseEntity.setPercentNum(Integer.parseInt(optJSONObject.optString("onePlaceValueAndroid")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.advertiseEntity;
    }

    public static AdConfigEntity parseAdConfig(JSONObject jSONObject) {
        AdConfigEntity adConfigEntity = new AdConfigEntity();
        if (jSONObject != null) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("advCntList");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    char c = 0;
                    JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                    adConfigEntity.setAdvertiseTitle(optJSONObject.optString("title"));
                    adConfigEntity.setAdvertiseImageURL(optJSONObject.optString("imgSrc"));
                    adConfigEntity.setAdvertiseId(optJSONObject.optString("advCode"));
                    String optString = optJSONObject.optString("welcomeType");
                    adConfigEntity.setWelcomeType(optString);
                    adConfigEntity.setAdvCode(optJSONObject.optString("advCode"));
                    adConfigEntity.setNoCallUrl(optJSONObject.optString("noCallUrl"));
                    adConfigEntity.setExposureUrl(optJSONObject.optString("exposureUrl"));
                    adConfigEntity.setImageIconSrc(optJSONObject.optString("iconImgSrc"));
                    adConfigEntity.setImageIconType(optJSONObject.optString("iconImgType"));
                    adConfigEntity.setDetailTextString(optJSONObject.optString("iconHotTitle"));
                    switch (optString.hashCode()) {
                        case -2060462300:
                            if (optString.equals("advertising")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1934018358:
                            if (optString.equals("splashad")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1115058732:
                            if (optString.equals("headline")) {
                                break;
                            }
                            c = 65535;
                            break;
                        case -898964491:
                            if (optString.equals("smaato")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 50:
                            if (optString.equals("2")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2000326332:
                            if (optString.equals("jingdong")) {
                                c = 5;
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
                            adConfigEntity.setAdType("PANGLE");
                            break;
                        case 1:
                            adConfigEntity.setAdType("XINTAI");
                            break;
                        case 2:
                            adConfigEntity.setAdType("YLH");
                            break;
                        case 3:
                            adConfigEntity.setAdType("SMATOO");
                            break;
                        case 4:
                            adConfigEntity.setAdType("UNIOCMVIDEO");
                            break;
                        case 5:
                            adConfigEntity.setAdType("JINGDONG");
                            break;
                        default:
                            adConfigEntity.setAdType("UNIOCMPIC");
                            break;
                    }
                    UIUtils.logD("onePlaceValueAndroid", adConfigEntity.getAdType());
                    adConfigEntity.setAdvertiseTargetURL(optJSONObject.optString("targetUrl"));
                    adConfigEntity.setCodeId(optJSONObject.optString("onePlaceAndroid"));
                    adConfigEntity.setCodeId2(optJSONObject.optString("twoPlaceAndroid"));
                    try {
                        adConfigEntity.setPercentNum(Integer.parseInt(optJSONObject.optString("onePlaceValueAndroid")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return adConfigEntity;
    }

    public void loadAdvCache() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("mobile", this.userManager.getDefaultPhoneNumber());
            hashMap.put("version", this.activityContext.getString(2131886969));
            hashMap.put("isHomeAdv", "1");
            hashMap.put("provinceCode", this.userManager.getLocateProvinceCode());
            hashMap.put("cityCode", this.userManager.getLocateCityCode());
            hashMap.put("width", "" + UIUtils.getScreenWidth((Activity) this.activityContext));
            hashMap.put("height", "" + UIUtils.getScreenHeight(this.activityContext, UIUtils.ScreenHeightMode.FullScreen));
            hashMap.put("landType", "0");
            hashMap.put("appId", this.userManager.getLoginAppId());
            hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
            hashMap.put("imgIndex ", App.getSharePreferenceUtil().getString("welcom_imgIndex"));
            hashMap.put("isHavShow", App.getSharePreferenceUtil().getString("isHavShow"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.getAsyncHttpClient().rxGet(URLSet.getAccountListData(), hashMap).subscribeOn(Schedulers.m1934io()).doOnNext(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.7
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONObject("adv").getJSONObject("startup_adv").getJSONArray("advCntList");
                    if (jSONArray == null || jSONArray.length() <= 0) {
                        return;
                    }
                    CacheDataCenter.getInstance().setBackAdvData(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWelcome.6
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
            }
        });
    }

    public void clearAdvCache() {
        CacheDataCenter.getInstance().setBackAdvData("");
    }
}
