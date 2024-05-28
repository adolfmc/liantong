package com.sinovatech.unicom.common;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.entity.GDTEntity;
import com.sinovatech.unicom.common.entity.GTDAndroidEntity;
import com.sinovatech.unicom.common.function.GDTFunction;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GDTLogUtils {
    private static final String TAG = "GDTLogUtils";

    public void getClickInfo(final AppCompatActivity appCompatActivity) {
        App.getSharePreferenceUtil().putString("guangdiantongguiyinxinxi", "");
        try {
            getGDTGuiYin(appCompatActivity).subscribe(new Consumer<GDTEntity>() { // from class: com.sinovatech.unicom.common.GDTLogUtils.1
                @Override // io.reactivex.functions.Consumer
                public void accept(GDTEntity gDTEntity) throws Exception {
                    if (gDTEntity != null) {
                        GDTLogUtils.this.uploadGDT(appCompatActivity, gDTEntity);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.common.GDTLogUtils.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    UIUtils.logD(GDTLogUtils.TAG, th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("广点通归因接口异常 " + e.getMessage());
        }
    }

    public ObservableSubscribeProxy<GDTEntity> getGDTGuiYin(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("muid", MD5Tools.encode(DeviceHelper.getMEID().toLowerCase()).toLowerCase());
            hashMap.put("yacetype", "yace");
            hashMap.put("user_agent", Base64.encodeToString(URLEncoder.encode(DeviceHelper.getUserAgent(), "UTF-8").getBytes(), 0));
            hashMap.put("android_id", MD5Tools.encode(DeviceHelper.getAndroidID()).toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("广点通归因接口参数异常 " + e.getMessage());
        }
        Gson gson = new Gson();
        UIUtils.logD(TAG, !(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getGDTUploadUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GDTFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadGDT(AppCompatActivity appCompatActivity, GDTEntity gDTEntity) {
        if (gDTEntity != null) {
            try {
                String url = gDTEntity.getUrl();
                JSONObject uploadGDTParams = getUploadGDTParams(gDTEntity);
                String jSONObject = !(uploadGDTParams instanceof JSONObject) ? uploadGDTParams.toString() : NBSJSONObjectInstrumentation.toString(uploadGDTParams);
                if (TextUtils.isEmpty(url)) {
                    return;
                }
                ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(url, jSONObject).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.common.GDTLogUtils.5
                    @Override // io.reactivex.functions.Function
                    public String apply(@NonNull String str) throws Exception {
                        return str;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.common.GDTLogUtils.3
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        UIUtils.logD(GDTLogUtils.TAG, str);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.common.GDTLogUtils.4
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        UIUtils.logD(GDTLogUtils.TAG, th.getMessage());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7978e("广点通归因上报接口异常 " + e.getMessage());
            }
        }
    }

    private JSONObject getUploadGDTParams(GDTEntity gDTEntity) {
        JSONObject jSONObject = new JSONObject();
        if (gDTEntity != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject2 = new JSONObject();
                GTDAndroidEntity entity = gDTEntity.getEntity();
                if (entity != null) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("hash_imei", entity.getMuid());
                    jSONObject3.put("hash_android_id", entity.getHashAndroidId());
                    jSONObject2.put("user_id", jSONObject3);
                }
                jSONArray.put(jSONObject2);
                jSONObject.put("actions", jSONArray);
                jSONObject2.put("action_time", System.currentTimeMillis() / 1000);
                jSONObject2.put("action_type", "ACTIVATE_APP");
                jSONObject2.put("action_param", new JSONObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }
}
