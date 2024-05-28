package com.sinovatech.unicom.separatemodule.gamecenter;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.function.FlowGetResultFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterBannerFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterFloorFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterFloorIconFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterHotFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterSignInFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.RecentlyGamesFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.RecommendGamesFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterDataManager {
    private static final String TAG = "GameCenterDataManager";

    private GameCenterDataManager() {
    }

    public static GameCenterDataManager getInstance() {
        return GameCenterDataManagerInstance.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class GameCenterDataManagerInstance {
        private static final GameCenterDataManager INSTANCE = new GameCenterDataManager();

        private GameCenterDataManagerInstance() {
        }
    }

    public ObservableSubscribeProxy<GamesEntity> getGameBannerData(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "100011");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterBannerFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<GamesEntity> getGameCenterFloor(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "10009");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterFloorFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<GamesEntity> getGameCenterFloorIcon(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "1000230063");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterFloorIconFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<Map<String, String>> getHuabaoUrl(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "10010");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterDataManager$MtXffEP1HcScpWe9KhoU-HeaFrA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCenterDataManager.lambda$getHuabaoUrl$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getHuabaoUrl$0(String str) throws Exception {
        JSONObject optJSONObject;
        UIUtils.logD("rxPost", "获取分享数据==>" + str);
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        if ("0000".equals(jSONObject.optString("code")) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            String optString = optJSONObject.optString("advertisement");
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

    public ObservableSubscribeProxy<Map<String, String>> getHaiBao(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "1000230070");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterDataManager$-07hq0j7GbKN16-l3_ORr7yhPmM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCenterDataManager.lambda$getHaiBao$1((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getHaiBao$1(String str) throws Exception {
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

    public ObservableSubscribeProxy<Map<String, String>> getFloorUrl(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryMoreUrl");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterDataManager$BfUS2xYlaA4v7R6t70hx9PmyQhQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCenterDataManager.lambda$getFloorUrl$2((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getFloorUrl$2(String str) throws Exception {
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        if ("0000".equals(jSONObject.optString("code"))) {
            CacheDataCenter.getInstance().setMapUrl(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            hashMap.put("boutique_url", optJSONObject.optString("boutique_url"));
            hashMap.put("recreational_url", optJSONObject.optString("recreational_url"));
            hashMap.put("recently_url", optJSONObject.optString("recently_url"));
            hashMap.put("task_center_url", optJSONObject.optString("task_center_url"));
            hashMap.put("search_url", optJSONObject.optString("search_url"));
            hashMap.put("sign_rule_url", optJSONObject.optString("sign_rule_url"));
            hashMap.put("flow_rule_url", optJSONObject.optString("flow_rule_url"));
        }
        return hashMap;
    }

    public ObservableSubscribeProxy<GamesEntity> postRecommendList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "boutique");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("details", "1");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendGamesFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<GamesEntity> postRecentlyList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "recently");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecentlyGamesFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<SignInHistoryEntity> postGameSignInHistory(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "signin_history");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.gameSignInHistory(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterSignInFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<SignInResultEntity> postGameSignIn(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "signin");
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.gameSignInHistory(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterDataManager$gfA0NTcufNmFOq0YUUFJn0dLcgI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCenterDataManager.lambda$postGameSignIn$3((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SignInResultEntity lambda$postGameSignIn$3(String str) throws Exception {
        UIUtils.logD("test", str);
        SignInResultEntity signInResultEntity = new SignInResultEntity();
        JSONObject jSONObject = new JSONObject(str);
        signInResultEntity.setCode(jSONObject.optString("respCode"));
        signInResultEntity.setCurrentIntegral(jSONObject.optString("currentIntegral"));
        signInResultEntity.setTomorrowIntegral(jSONObject.optString("tomorrowIntegral"));
        signInResultEntity.setRespDesc(jSONObject.optString("respDesc"));
        return signInResultEntity;
    }

    public ObservableSubscribeProxy<GamesEntity> postNewGames(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "newGameRandom");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getNewGames(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterDataManager$4oHCBgfXzZQxevJ2k7tBrzekHm4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCenterDataManager.lambda$postNewGames$4((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ GamesEntity lambda$postNewGames$4(String str) throws Exception {
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setName(optJSONObject.optString("name"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setResourceId(optJSONObject.optString("resourceId"));
                    gamesDataEntity.setResource_id(optJSONObject.optString("resource_id"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    gamesDataEntity.setCompany(optJSONObject.optString("company"));
                    gamesDataEntity.setSmallImage(optJSONObject.optString("smallImage"));
                    gamesDataEntity.setFreeFlow(optJSONObject.optString("freeFlow"));
                    gamesDataEntity.setGameIap(optJSONObject.optString("gameIap"));
                    gamesDataEntity.setQqMark(optJSONObject.optString("qqMark"));
                    gamesDataEntity.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    gamesDataEntity.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    gamesDataEntity.setGameLabel(optJSONObject.optString("gameLabel"));
                    gamesDataEntity.setSlogan(optJSONObject.optString("slogan"));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gamesEntity;
    }

    public ObservableSubscribeProxy<HotGamesEntity> getHotList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "popularGames");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterHotFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<FlowGetResultEntity> flowGet(AppCompatActivity appCompatActivity, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "flowGet");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("gameId", str);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new FlowGetResultFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<String> commonLog(AppCompatActivity appCompatActivity, Map<String, String> map) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.commonLog(), map).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }
}
