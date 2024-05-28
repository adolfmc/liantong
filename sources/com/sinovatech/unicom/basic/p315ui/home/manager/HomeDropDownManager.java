package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.p314po.AdvertiseEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeDropDownManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeDropDownManager {
    private OnDropDownStateListener stateListener;
    private int showGameHeaderFlag = 0;
    private String advertiseTargetURL = "";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeDropDownManager$OnDropDownStateListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDropDownStateListener {
        void onState(int i, String str);
    }

    public void setStateListener(OnDropDownStateListener onDropDownStateListener) {
        this.stateListener = onDropDownStateListener;
    }

    public void loadDropDownState(AppCompatActivity appCompatActivity) {
        this.advertiseTargetURL = "";
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "topAdGet");
        hashMap.put("provinceCode", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("version", App.getInstance().getString(2131886969));
        if (App.hasLogined()) {
            hashMap.put("loginType", "Y");
            hashMap.put("cityCode", UserManager.getInstance().getUserAreaid());
            hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        } else {
            hashMap.put("loginType", "N");
            hashMap.put("cityCode", UserManager.getInstance().getLocateCityCode());
            hashMap.put("mobile", "");
        }
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getDataFromService(), hashMap, 1, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, AdvertiseEntity>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeDropDownManager.3
            @Override // io.reactivex.functions.Function
            public AdvertiseEntity apply(String str) throws Exception {
                UIUtils.logD("首页配置", "apply: " + str);
                if (str == null || str.isEmpty()) {
                    HomeDropDownManager.this.showGameHeaderFlag = 0;
                    if (HomeDropDownManager.this.stateListener != null) {
                        HomeDropDownManager.this.stateListener.onState(HomeDropDownManager.this.showGameHeaderFlag, "");
                        return null;
                    }
                    return null;
                }
                AdvertiseEntity advertiseEntity = new AdvertiseEntity();
                JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                for (int i = 0; i < 1; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("id");
                    String optString2 = jSONObject.optString("title");
                    String optString3 = jSONObject.optString("imageUrl");
                    String optString4 = jSONObject.optString("url");
                    advertiseEntity.setAdvertiseId(optString);
                    advertiseEntity.setAdvertiseTitle(optString2);
                    advertiseEntity.setAdvertiseImageURL(optString3);
                    advertiseEntity.setAdvertiseTargetURL(optString4);
                    advertiseEntity.setNeedLogin(true);
                }
                return advertiseEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<AdvertiseEntity>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeDropDownManager.1
            @Override // io.reactivex.functions.Consumer
            public void accept(AdvertiseEntity advertiseEntity) throws Exception {
                if (advertiseEntity == null) {
                    HomeDropDownManager.this.showGameHeaderFlag = 0;
                    if (HomeDropDownManager.this.stateListener != null) {
                        HomeDropDownManager.this.stateListener.onState(HomeDropDownManager.this.showGameHeaderFlag, "");
                        return;
                    }
                    return;
                }
                HomeDropDownManager.this.advertiseTargetURL = advertiseEntity.getAdvertiseTargetURL();
                if (HomeDropDownManager.this.advertiseTargetURL != null && !HomeDropDownManager.this.advertiseTargetURL.isEmpty() && HomeDropDownManager.this.advertiseTargetURL.contains("gametype=")) {
                    String[] split = HomeDropDownManager.this.advertiseTargetURL.split("gametype=");
                    if (split[1] != null && !split[1].isEmpty()) {
                        if (split[1].equals("native")) {
                            HomeDropDownManager.this.showGameHeaderFlag = 2;
                        } else if (split[1].equals("miniprogram")) {
                            if (App.hasLogined()) {
                                HomeDropDownManager.this.showGameHeaderFlag = 3;
                            } else {
                                HomeDropDownManager.this.showGameHeaderFlag = 0;
                            }
                        } else if (split[1].equals("off")) {
                            HomeDropDownManager.this.showGameHeaderFlag = 0;
                        } else {
                            HomeDropDownManager.this.showGameHeaderFlag = 1;
                        }
                    }
                }
                if (HomeDropDownManager.this.stateListener != null) {
                    HomeDropDownManager.this.stateListener.onState(HomeDropDownManager.this.showGameHeaderFlag, HomeDropDownManager.this.advertiseTargetURL);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeDropDownManager.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UIUtils.logD("请求错误", "accept: " + th.getMessage());
                HomeDropDownManager.this.showGameHeaderFlag = 0;
                if (HomeDropDownManager.this.stateListener != null) {
                    HomeDropDownManager.this.stateListener.onState(HomeDropDownManager.this.showGameHeaderFlag, "");
                }
                th.printStackTrace();
            }
        });
    }

    public ObservableSubscribeProxy<Map<String, String>> getHaiBao(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "1000230070");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeDropDownManager$pYpA4FkxA_5Wfh7Be_ylmlteqVM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return HomeDropDownManager.lambda$getHaiBao$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getHaiBao$0(String str) throws Exception {
        JSONObject optJSONObject;
        UIUtils.logD("rxPost", "获取分享数据==>" + str);
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        if ("0000".equals(jSONObject.optString("code")) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            String optString = optJSONObject.optString("poster");
            if (optString.startsWith("[")) {
                JSONArray jSONArray = new JSONArray(optString);
                if (jSONArray.length() > 0) {
                    hashMap.put("shareImgUrl", jSONArray.optJSONObject(0).optString("deploy_game"));
                    hashMap.put("shareUrl", jSONArray.optJSONObject(0).optString("gameUrl"));
                }
            }
        }
        return hashMap;
    }
}
