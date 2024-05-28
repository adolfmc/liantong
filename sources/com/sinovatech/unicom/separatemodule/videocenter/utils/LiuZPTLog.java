package com.sinovatech.unicom.separatemodule.videocenter.utils;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiuZPTLog {
    private AppCompatActivity activityContext;
    private Gson gson = new Gson();

    public LiuZPTLog(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void setLogByCommon(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        Map<String, String> params = getParams();
        params.put("url", str);
        params.put("urlref", str2);
        params.put("last_page_name", str3);
        params.put("action_type", str4);
        params.put("action_name", str5);
        params.put("action_id", str6);
        params.put("page_name", str7);
        params.put("storey", str9);
        params.put("pb_name", str8);
        if (!TextUtils.isEmpty(str10)) {
            params.put("content_id", str10);
        }
        params.put("position", str11);
        params.put("time_spent", str11);
        params.put("realposition", str12);
        params.put("duration", str13);
        if (!TextUtils.isEmpty(str14)) {
            params.put("tab_id", str14);
        }
        if (!TextUtils.isEmpty(str15)) {
            params.put("tab_name", str15);
        }
        Gson gson = this.gson;
        String json = !(gson instanceof Gson) ? gson.toJson(params) : NBSGsonInstrumentation.toJson(gson, params);
        UIUtils.logD("xcy", "开始上报日志");
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(15, 10, 10).rxPost(URLSet.setLogPoit(), json).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$LiuZPTLog$VzaNW0_EmMCQC4Dirvji6rZ8ar4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                String str17 = (String) obj;
                UIUtils.logD("xcy", "日志上报成功");
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$LiuZPTLog$7MsPPFa_etNNqOihNqkw0Vii_R0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                UIUtils.logD("xcy", "日志上报失败：" + th.getMessage());
            }
        });
    }

    public String getJson(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        LiuZPTLog liuZPTLog;
        Map<String, String> params = getParams();
        params.put("url", str);
        params.put("urlref", str2);
        params.put("last_page_name", str3);
        params.put("action_type", str4);
        params.put("action_name", str5);
        params.put("action_id", str6);
        params.put("page_name", str7);
        params.put("storey", str9);
        params.put("pb_name", str8);
        if (!TextUtils.isEmpty(str10)) {
            params.put("content_id", str10);
        }
        params.put("position", str11);
        params.put("time_spent", str11);
        params.put("realposition", str12);
        params.put("duration", str13);
        if (!TextUtils.isEmpty(str14)) {
            params.put("tab_id", str14);
        }
        if (TextUtils.isEmpty(str15)) {
            liuZPTLog = this;
        } else {
            params.put("tab_name", str15);
            liuZPTLog = this;
        }
        Gson gson = liuZPTLog.gson;
        return !(gson instanceof Gson) ? gson.toJson(params) : NBSGsonInstrumentation.toJson(gson, params);
    }

    private Map<String, String> getParams() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap hashMap = new HashMap();
        hashMap.put("system_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
        hashMap.put("local_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
        hashMap.put("client_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        currentPhoneNumber = (TextUtils.isEmpty(currentPhoneNumber) || currentPhoneNumber.equals("0")) ? "" : "";
        hashMap.put("uid", currentPhoneNumber);
        if (currentPhoneNumber.length() < 11) {
            currentPhoneNumber = RandomStringUtils.randomAlphanumeric(11);
        }
        String substring = currentPhoneNumber.substring(1);
        hashMap.put("cid", "user" + substring);
        hashMap.put("province_code", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("city_code", UserManager.getInstance().getCurrentCityCode());
        hashMap.put("os", "AND");
        hashMap.put("ip", SystemServiceUtils.getLocalIpAddress());
        hashMap.put("channel_id", "3000011091");
        hashMap.put("content_position", "0");
        hashMap.put("app_version", this.activityContext.getResources().getString(2131886969));
        hashMap.put("device_model", DeviceHelper.getDeviceModel());
        hashMap.put("os_version", DeviceHelper.getDeviceOSVersion().replace("android", ""));
        hashMap.put("activity_type", "1");
        return hashMap;
    }
}
