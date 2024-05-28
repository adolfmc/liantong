package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.entity.UserServiceOrderEntity;
import com.sinovatech.unicom.separatemodule.user.function.UserOrderServiceFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserServiceOrder {
    private static ManagerUserServiceOrder managerUserServiceOrder;
    private final String TAG = "ManagerUserServiceOrder";
    private final String defaultData = "{\n    \"code\":\"0000\",\n    \"datas\":[\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556451623.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=2\",\n            \"status\":\"待付款\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556454147.png \",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=3\",\n            \"status\":\"进行中\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556456511.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=5\",\n            \"status\":\"待评价\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230905/png/1693907898134.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=1\",\n            \"status\":\"全部\"\n        }\n    ],\n    \"message\":\"我的订单与服务记录\"\n}";

    private String getDefaultData() {
        return "{\n    \"code\":\"0000\",\n    \"datas\":[\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556451623.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=2\",\n            \"status\":\"待付款\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556454147.png \",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=3\",\n            \"status\":\"进行中\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230901/png/1693556456511.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=5\",\n            \"status\":\"待评价\"\n        },\n        {\n            \"icon\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230905/png/1693907898134.png\",\n            \"url\":\"https://img.client.10010.com/wodedingdan2/index.html#/wddd?orderType=1\",\n            \"status\":\"全部\"\n        }\n    ],\n    \"message\":\"我的订单与服务记录\"\n}";
    }

    public static ManagerUserServiceOrder getInstance() {
        if (managerUserServiceOrder == null) {
            synchronized (ManagerUserServiceOrder.class) {
                if (managerUserServiceOrder == null) {
                    managerUserServiceOrder = new ManagerUserServiceOrder();
                }
            }
        }
        return managerUserServiceOrder;
    }

    public ObservableSubscribeProxy<UserServiceOrderEntity> getUserServiceOrder(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getVersion());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getServiceOrderUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new UserOrderServiceFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public void saveCache(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CacheDataCenter.getInstance().saveServiceOrderData(str);
    }

    private String getCache() {
        return CacheDataCenter.getInstance().getServiceOrderData();
    }

    public UserServiceOrderEntity cacheData() {
        try {
            String cache = getCache();
            if (!TextUtils.isEmpty(cache) && cache.startsWith("{")) {
                UserServiceOrderEntity userServiceOrderEntity = new UserServiceOrderEntity();
                JSONObject jSONObject = new JSONObject(cache);
                String optString = jSONObject.optString("myOrderTip");
                if (!TextUtils.isEmpty(optString)) {
                    userServiceOrderEntity.setMyOrderTip(optString);
                }
                String optString2 = jSONObject.optString("myOrderSwitch");
                if (!TextUtils.isEmpty(optString2)) {
                    userServiceOrderEntity.setMyOrderSwitch(optString2);
                }
                String optString3 = jSONObject.optString("message");
                if (!TextUtils.isEmpty(optString3)) {
                    userServiceOrderEntity.setMessage(optString3);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("datas");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    UserServiceOrderEntity.Data data = new UserServiceOrderEntity.Data();
                    data.setIcon(optJSONObject.optString("icon"));
                    data.setStatus(optJSONObject.optString("status"));
                    data.setNum("");
                    data.setUrl(optJSONObject.optString("url"));
                    arrayList.add(data);
                }
                userServiceOrderEntity.setDataList(arrayList);
                return userServiceOrderEntity;
            }
            MsLogUtil.m7980d("数据返回为空");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserServiceOrderEntity getDefaultEntity() {
        try {
            String defaultData = getDefaultData();
            if (!TextUtils.isEmpty(defaultData) && defaultData.startsWith("{")) {
                UserServiceOrderEntity userServiceOrderEntity = new UserServiceOrderEntity();
                JSONObject jSONObject = new JSONObject(defaultData);
                userServiceOrderEntity.setMyOrderTip("");
                userServiceOrderEntity.setMyOrderSwitch("1");
                String optString = jSONObject.optString("message");
                if (!TextUtils.isEmpty(optString)) {
                    userServiceOrderEntity.setMessage(optString);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("datas");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    UserServiceOrderEntity.Data data = new UserServiceOrderEntity.Data();
                    data.setIcon(optJSONObject.optString("icon"));
                    data.setStatus(optJSONObject.optString("status"));
                    data.setNum("");
                    data.setUrl(optJSONObject.optString("url"));
                    arrayList.add(data);
                }
                userServiceOrderEntity.setDataList(arrayList);
                return userServiceOrderEntity;
            }
            MsLogUtil.m7980d("数据返回为空");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
