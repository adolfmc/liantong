package com.sinovatech.unicom.separatemodule.gamecenter;

import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGetEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.NickNameEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.function.FlowGetDataFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterFloorFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.HotGetDataFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.KingKongDistrictFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.NickNameFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.RecentlyGamesFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.function.RecommendGamesFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCentersDataManager {
    private static final String TAG = "GameCentersDataManager";

    private GameCentersDataManager() {
    }

    public static GameCentersDataManager getInstance() {
        return GameCenterDataManagerInstance.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class GameCenterDataManagerInstance {
        private static final GameCentersDataManager INSTANCE = new GameCentersDataManager();

        private GameCenterDataManagerInstance() {
        }
    }

    public ObservableSubscribeProxy<GamesEntity> getGameCenterFloor(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "10009");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterFloorFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<GamesEntity> postKingKongDistrict(FragmentActivity fragmentActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "diamond");
        hashMap.put("clientVersion", fragmentActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.kingKongDistrict(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new KingKongDistrictFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(fragmentActivity));
    }

    public ObservableSubscribeProxy<FlowGetEntity> postFlowGet(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "popularGames");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("isHtml", "h5");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new FlowGetDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<HotGetEntity> getHotList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "100010");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new HotGetDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<Object> postBannerList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersDataManager$OxzRBG6WFP-NafRvJKAu7zTQT_Y
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCentersDataManager.lambda$postBannerList$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$postBannerList$0(String str) throws Exception {
        UIUtils.logD(TAG, str);
        return new Object();
    }

    public ObservableSubscribeProxy<String> commonLog(AppCompatActivity appCompatActivity, Map<String, String> map) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.commonLog(), map).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<String> getGameApp(AppCompatActivity appCompatActivity, Map<String, String> map) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), map).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<Map<String, String>> getFloorUrl(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryMoreUrl");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersDataManager$EhqdvsS1WVN2o2UcUaEjBcPTgro
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCentersDataManager.lambda$getFloorUrl$1((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getFloorUrl$1(String str) throws Exception {
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

    public ObservableSubscribeProxy<Map<String, String>> getHuabaoUrl(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "queryGameInfo");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        hashMap.put("propCode", "10010");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApp(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersDataManager$wxmPcILljlqTwwUp5LcePmVT07Q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GameCentersDataManager.lambda$getHuabaoUrl$2((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getHuabaoUrl$2(String str) throws Exception {
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

    public ObservableSubscribeProxy<GamesEntity> postRecentlyList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "recently");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecentlyGamesFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<GamesEntity> postRecommendList(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "guessYouLike");
        hashMap.put("clientVersion", appCompatActivity.getString(2131886969).split("@")[1]);
        hashMap.put("deviceType", "Android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGameApps(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendGamesFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public ObservableSubscribeProxy<NickNameEntity> postNickName(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("type", "2");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getNickName(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new NickNameFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }
}
