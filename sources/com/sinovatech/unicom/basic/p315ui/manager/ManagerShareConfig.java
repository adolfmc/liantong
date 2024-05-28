package com.sinovatech.unicom.basic.p315ui.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wework.Wework;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.WeWorkConfigEntity;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerShareConfig */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerShareConfig {
    public static final String TAG = "ManagerShareConfig";
    private static ManagerShareConfig instance;
    private final Map<String, WeWorkConfigEntity> configMap = new HashMap();
    private String displayModeStr;

    private ManagerShareConfig() {
    }

    public static ManagerShareConfig getInstance() {
        if (instance == null) {
            instance = new ManagerShareConfig();
        }
        return instance;
    }

    public void getUnicomWorker() {
        if (App.hasLogined()) {
            App.getAsyncHttpClient().post(URLSet.isUnicomWorker(), new HashMap(), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerShareConfig.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    super.onSuccess(i, str);
                    if (str != null) {
                        try {
                            if (str.isEmpty()) {
                                return;
                            }
                            UIUtils.logD(ManagerShareConfig.TAG, str);
                            JSONObject jSONObject = new JSONObject(str);
                            if ("200".equals(jSONObject.optString("status"))) {
                                CacheDataCenter.getInstance().setIsUnicomWorker(String.valueOf(jSONObject.optInt("data")));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            UIUtils.logD(ManagerShareConfig.TAG, str + "=====" + e.getMessage());
                        }
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    UIUtils.logD(ManagerShareConfig.TAG, str + "=====" + th.getMessage());
                    CacheDataCenter.getInstance().setIsUnicomWorker(String.valueOf(0));
                }
            });
        }
    }

    public void loadData() {
        getUnicomWorker();
    }

    public void setWeWorkConfig(WeWorkConfigEntity weWorkConfigEntity) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("AppKey", weWorkConfigEntity.getAppKey());
            hashMap.put("AppSecret", weWorkConfigEntity.getAppSecret());
            hashMap.put("AgentId", weWorkConfigEntity.getAgentId());
            hashMap.put("Schema", weWorkConfigEntity.getShareSchema());
            ShareSDK.setPlatformDevInfo(Wework.NAME, hashMap);
            MsLogUtil.m7979d(TAG, ShareSDK.getPlatform(Wework.NAME).getName());
            MsLogUtil.m7979d(TAG, "AppKey=" + weWorkConfigEntity.getAppKey());
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public ObservableSubscribeProxy<String> getWeworkConfig(AppCompatActivity appCompatActivity) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getWeWorkUrl(), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerShareConfig.2
            @Override // io.reactivex.functions.Function
            public String apply(@NonNull String str) throws Exception {
                MsLogUtil.m7979d(ManagerShareConfig.TAG, "-----" + str);
                JSONObject jSONObject = new JSONObject(str);
                if ("0000".equals(jSONObject.optString("status"))) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    ManagerShareConfig.this.displayModeStr = optJSONObject.optString("displayModeStr");
                    ManagerShareConfig.this.setWeWorkMap(optJSONObject.optJSONArray("resultList"));
                    ShareManager.notifyQuDaoAdapter(ManagerShareConfig.this.getShowWeWorkSize());
                }
                return str;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public void setWeWorkMap(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        this.configMap.clear();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("appKey");
            String optString2 = optJSONObject.optString("agentId");
            String optString3 = optJSONObject.optString("appSecret");
            String optString4 = optJSONObject.optString("shareSchema");
            String optString5 = optJSONObject.optString("chineseName");
            String optString6 = optJSONObject.optString("displayMode");
            WeWorkConfigEntity weWorkConfigEntity = new WeWorkConfigEntity();
            weWorkConfigEntity.setAppKey(optString);
            weWorkConfigEntity.setAgentId(optString2);
            weWorkConfigEntity.setAppSecret(optString3);
            weWorkConfigEntity.setShareSchema(optString4);
            weWorkConfigEntity.setChineseName(optString5);
            weWorkConfigEntity.setDisplayMode(optString6);
            this.configMap.put(optString6, weWorkConfigEntity);
        }
    }

    public Map<String, WeWorkConfigEntity> getWeWorkMap() {
        return this.configMap;
    }

    public List<String> getShowWeWorkSize() {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(this.displayModeStr)) {
            if (this.displayModeStr.equals("0")) {
                arrayList.add(this.displayModeStr);
            } else if (this.displayModeStr.equals("1")) {
                arrayList.add(this.displayModeStr);
            } else if (this.displayModeStr.equals("2")) {
                arrayList.add("0");
                arrayList.add("1");
            } else {
                arrayList.clear();
            }
        }
        return arrayList;
    }
}
