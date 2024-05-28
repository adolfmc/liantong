package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.home.view.HomeBWJingZhunView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.entity.UserActivityEntity;
import com.sinovatech.unicom.separatemodule.user.function.UserActFuction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserAct {
    private AppCompatActivity activityContext;

    public ManagerUserAct(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<UserActivityEntity> getUserAct() {
        HashMap hashMap = new HashMap();
        hashMap.put("appSerial", System.currentTimeMillis() + "");
        hashMap.put("city", UserManager.getInstance().getCurrentCityCode());
        hashMap.put("province", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("appId", DeviceHelper.getDeviceID(false));
        hashMap.put("channelCode", "900001");
        hashMap.put("goodsId", HomeBWJingZhunView.resumeId);
        hashMap.put("jingdu", App.getSharePreferenceUtil().getString("lat"));
        hashMap.put("weidu", App.getSharePreferenceUtil().getString("long"));
        hashMap.put("businessType", "all");
        hashMap.put("requestType", "myPage");
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap);
        MsLogUtil.m7979d("我的活动", "getUserAct: " + URLSet.getActivityUrl());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getActivityUrl(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new UserActFuction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public UserActivityEntity returnData() {
        UserActivityEntity userActivityEntity;
        UserActivityEntity userActivityEntity2;
        try {
            String userActData = CacheDataCenter.getInstance().getUserActData();
            UserActivityEntity userActivityEntity3 = new UserActivityEntity();
            JSONObject jSONObject = new JSONObject(userActData);
            if ("0000".equals(jSONObject.optString("respCode"))) {
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                JSONArray optJSONArray2 = jSONObject.optJSONArray("activity");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (optJSONArray != null) {
                    int i = 0;
                    while (i < optJSONArray.length()) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        UserActivityEntity.Result result = new UserActivityEntity.Result();
                        String optString = optJSONObject.optString("isLogin");
                        String optString2 = optJSONObject.optString("goodsUrl");
                        String optString3 = optJSONObject.optString("goodsId");
                        String optString4 = optJSONObject.optString("actId");
                        String optString5 = optJSONObject.optString("viceTitle");
                        String optString6 = optJSONObject.optString("actType");
                        String optString7 = optJSONObject.optString("id");
                        String optString8 = optJSONObject.optString("title");
                        JSONArray jSONArray = optJSONArray;
                        String optString9 = optJSONObject.optString("imgSrc");
                        UserActivityEntity userActivityEntity4 = userActivityEntity3;
                        String optString10 = optJSONObject.optString("goodsType");
                        result.setActId(optString4);
                        result.setIsLogin(optString);
                        result.setGoodsUrl(optString2);
                        result.setGoodsId(optString3);
                        result.setViceTitle(optString5);
                        result.setActType(optString6);
                        result.setId(optString7);
                        result.setTitle(optString8);
                        result.setImgSrc(optString9);
                        result.setGoodsType(optString10);
                        arrayList.add(result);
                        i++;
                        optJSONArray = jSONArray;
                        userActivityEntity3 = userActivityEntity4;
                    }
                    userActivityEntity = userActivityEntity3;
                } else {
                    userActivityEntity = userActivityEntity3;
                }
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        UserActivityEntity.Activity activity = new UserActivityEntity.Activity();
                        String optString11 = optJSONObject2.optString("actId");
                        String optString12 = optJSONObject2.optString("goodsUrl");
                        String optString13 = optJSONObject2.optString("imgSrc");
                        String optString14 = optJSONObject2.optString("title");
                        String optString15 = optJSONObject2.optString("viceTitle");
                        activity.setActId(optString11);
                        activity.setGoodsUrl(optString12);
                        activity.setImgSrc(optString13);
                        activity.setTitle(optString14);
                        activity.setViceTitle(optString15);
                        arrayList2.add(activity);
                    }
                    userActivityEntity2 = userActivityEntity;
                } else {
                    userActivityEntity2 = userActivityEntity;
                }
                userActivityEntity2.setActivity(arrayList2);
                userActivityEntity2.setResult(arrayList);
                return userActivityEntity2;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
