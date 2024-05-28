package com.sinovatech.unicom.separatemodule.recentmenu;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.BusinessEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.function.CollectionTabFunction;
import com.sinovatech.unicom.separatemodule.recentmenu.function.DeleteItemFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecentCustomManager {
    public static final String ADD = "addCollect";
    public static final String CANCEL = "cancelCollect";
    public static final String FIRST_OBJECTTYPE = "1";
    public static final String MORE_OBJECTTYPE = "2";
    public static final String QUERY = "queryCollect";
    private static final String TAG = "RecentCustomManager";
    private AppCompatActivity activityContext;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface CollectActionType {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface CollectObjectType {
    }

    public RecentCustomManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void getTeseYewu(Consumer<BusinessEntity> consumer, Consumer<Throwable> consumer2) {
        HashMap hashMap = new HashMap();
        hashMap.put("objectType", "2");
        String params = getParams("listAppBusiness", hashMap);
        MsLogUtil.m7979d(TAG, "特色业务入参----" + params);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), params).subscribeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$RecentCustomManager$IePm_ap1oOFqYemyv8YfXBrCcwg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return RecentCustomManager.lambda$getTeseYewu$0(RecentCustomManager.this, (String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, consumer2);
    }

    public static /* synthetic */ BusinessEntity lambda$getTeseYewu$0(RecentCustomManager recentCustomManager, String str) throws Exception {
        MsLogUtil.m7979d(TAG, "特色业务返回----" + str);
        return recentCustomManager.paserTeseEntity(str);
    }

    public BusinessEntity paserTeseEntity(String str) {
        BusinessEntity businessEntity = new BusinessEntity();
        try {
            RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str);
            if (recentStateEntity.isSuccess() && !TextUtils.isEmpty(recentStateEntity.getBody())) {
                JSONObject jSONObject = new JSONObject(recentStateEntity.getBody());
                businessEntity.setBusinessName(jSONObject.optString("businessName"));
                JSONArray optJSONArray = jSONObject.optJSONArray("appList");
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(RecentMiniEntity.getInstance(optJSONArray.optJSONObject(i)));
                    }
                    businessEntity.setAppList(arrayList);
                }
                CacheDataCenter.getInstance().setXialaTeseData(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessEntity;
    }

    public void getCollect(String str, String str2, Consumer<CollectEntity> consumer, Consumer<Throwable> consumer2) {
        getCollect(str, str2, "", "", consumer, consumer2);
    }

    public void getCollect(final String str, final String str2, final String str3, final String str4, Consumer<CollectEntity> consumer, Consumer<Throwable> consumer2) {
        HashMap hashMap = new HashMap();
        hashMap.put("objectType", str);
        hashMap.put("pageNumber", str2);
        hashMap.put("categoryId", str3);
        hashMap.put("searchContent", str4);
        String params = getParams("listCollect", hashMap);
        MsLogUtil.m7979d(TAG, "我的收藏入参----" + params);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), params).subscribeOn(Schedulers.m1934io()).map(new Function<String, CollectEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.1
            @Override // io.reactivex.functions.Function
            public CollectEntity apply(String str5) throws Exception {
                MsLogUtil.m7979d(RecentCustomManager.TAG, "获取我的收藏返回----" + str5);
                RecentCustomManager recentCustomManager = RecentCustomManager.this;
                return recentCustomManager.parserCollectEntity(str5, str + "_" + str3, str2, str4);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, consumer2);
    }

    public CollectEntity parserCollectEntity(String str, String str2) {
        return parserCollectEntity(str, str2, "", "");
    }

    public CollectEntity parserCollectEntity(String str, String str2, String str3, String str4) {
        RecentMiniEntity recentMiniEntity;
        int miniEntityType;
        CollectEntity collectEntity = new CollectEntity();
        try {
            RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str);
            if (recentStateEntity.isSuccess() && !TextUtils.isEmpty(recentStateEntity.getBody())) {
                JSONObject jSONObject = new JSONObject(recentStateEntity.getBody());
                collectEntity.setSuccess(true);
                collectEntity.setCollectName(jSONObject.optString("collectName"));
                collectEntity.setCollectCount(jSONObject.optString("collectCount"));
                collectEntity.setCollectPageCount(jSONObject.optString("collectPageCount"));
                JSONArray optJSONArray = jSONObject.optJSONArray("appList");
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null && (miniEntityType = RecentBoxManager.miniEntityType((recentMiniEntity = RecentMiniEntity.getInstance(optJSONObject)))) >= 0) {
                            recentMiniEntity.setType(miniEntityType);
                            arrayList.add(recentMiniEntity);
                        }
                    }
                    collectEntity.setAppList(arrayList);
                }
                if (TextUtils.equals("1", str3) && TextUtils.isEmpty(str4)) {
                    CacheDataCenter.getInstance().setXialaCollectData(str, str2);
                }
            } else {
                collectEntity.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collectEntity;
    }

    public void handSOrtCollect(List<RecentMiniEntity> list, final Consumer<Boolean> consumer) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                JSONObject jSONObject = new JSONObject();
                RecentMiniEntity recentMiniEntity = list.get(i);
                jSONObject.put("appId", recentMiniEntity.getAppId());
                jSONObject.put("sortNumber", String.valueOf(i));
                jSONObject.put("productId", recentMiniEntity.getProductId());
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("sortList", jSONArray);
        String params = getParams("handSortCollect", hashMap);
        MsLogUtil.m7979d(TAG, "手动收藏排序入参----" + params);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), params).subscribeOn(Schedulers.m1934io()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.3
            @Override // io.reactivex.functions.Function
            public Boolean apply(String str) throws Exception {
                MsLogUtil.m7979d(RecentCustomManager.TAG, "手动收藏排序返回----" + str);
                return Boolean.valueOf(RecentStateEntity.getInstance(str).isSuccess());
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                try {
                    consumer.accept(false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void deleteOrAddMenu(String str, RecentMiniEntity recentMiniEntity, Consumer<RecentStateEntity> consumer) {
        deleteOrAddMenu(str, recentMiniEntity.getAppId(), recentMiniEntity.getProductId(), consumer);
    }

    public void deleteOrAddMenu(final String str, String str2, String str3, final Consumer<RecentStateEntity> consumer) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str2);
        hashMap.put("productId", str3);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), getParams(str, hashMap)).subscribeOn(Schedulers.m1934io()).map(new Function<String, RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.5
            @Override // io.reactivex.functions.Function
            public RecentStateEntity apply(String str4) throws Exception {
                MsLogUtil.m7979d(RecentCustomManager.TAG, "我的收藏----" + str4);
                RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str4);
                if (RecentCustomManager.ADD.equals(str)) {
                    if (TextUtils.equals("0111", recentStateEntity.getRespCode())) {
                        recentStateEntity.setRespMsg("当前业务暂不支持收藏");
                    } else if (TextUtils.equals("0107", recentStateEntity.getRespCode())) {
                        recentStateEntity.setRespMsg("收藏数量已上限，收藏失败。");
                    } else if (TextUtils.equals("0000", recentStateEntity.getRespCode())) {
                        recentStateEntity.setRespMsg("收藏成功");
                    } else {
                        recentStateEntity.setRespMsg("收藏失败,请稍后再试");
                    }
                }
                return recentStateEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                try {
                    RecentStateEntity recentStateEntity = new RecentStateEntity();
                    recentStateEntity.setRespCode("9999");
                    recentStateEntity.setRespMsg("收藏失败,请稍后再试");
                    consumer.accept(recentStateEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void queryCollectMenu(String str, String str2, Consumer<JSONObject> consumer, Consumer<Throwable> consumer2) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("productId", str2);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), getParams(QUERY, hashMap)).subscribeOn(Schedulers.m1934io()).map(new Function<String, JSONObject>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.6
            @Override // io.reactivex.functions.Function
            public JSONObject apply(String str3) throws Exception {
                MsLogUtil.m7979d(RecentCustomManager.TAG, "我的收藏----" + str3);
                return new JSONObject(RecentStateEntity.getInstance(str3).getBody());
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, consumer2);
    }

    public ObservableSubscribeProxy<List<CollectionTabEntity>> getTabTypeData() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), getParams("getCollectCategoryList", new HashMap())).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new CollectionTabFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Boolean> abilityVerify(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        hashMap.put("intCode", "ZD20220316160326");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), getParams("abilityVerify", hashMap)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager.7
            @Override // io.reactivex.functions.Function
            public Boolean apply(String str2) throws Exception {
                try {
                    RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str2);
                    if (recentStateEntity.isSuccess() && TextUtils.equals("1", new JSONObject(recentStateEntity.getBody()).optString("isApply"))) {
                        return true;
                    }
                } catch (Exception e) {
                    MsLogUtil.m7978e("校验H5原生能力异常:" + e.getMessage());
                }
                return false;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Boolean> batchCancelCollect(List<RecentMiniEntity> list) {
        Map<String, Object> hashMap = new HashMap<>();
        JSONArray jSONArray = new JSONArray();
        for (RecentMiniEntity recentMiniEntity : list) {
            jSONArray.put(recentMiniEntity.getProductId());
        }
        hashMap.put("productList", jSONArray);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getXiaoChengxuUrl(), getParams("batchCancelCollect", hashMap)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new DeleteItemFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    private String getParams(String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject3.put("interNo", str);
            jSONObject3.put("seq", uuid());
            jSONObject3.put("reqTime", String.valueOf(System.currentTimeMillis()));
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    jSONObject4.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject4.put("appVersion", this.activityContext.getString(2131886969));
            jSONObject2.put("head", jSONObject3);
            jSONObject2.put("body", jSONObject4);
            jSONObject.put("request", jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
