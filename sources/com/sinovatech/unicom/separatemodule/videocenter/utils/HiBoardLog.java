package com.sinovatech.unicom.separatemodule.videocenter.utils;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HiBoardLog {
    private AppCompatActivity activityContext;
    private Gson gson = new Gson();

    public HiBoardLog(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
        try {
            LogToFile.init(appCompatActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hiBoardLogShow(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        if (!TextUtils.isEmpty(str2)) {
            replace = "related";
        }
        param.put("category", replace);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "客户端展示-->" + json);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogShow(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$bty1ujWkwccYoul17rO0YwLAbBM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "客户端展示-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$dYO_DqreK7LF1KGw2lj5jFobWJY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogShow$1(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogShow$1(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logE("火山日志错误：", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogPlay(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        param.put("category", !TextUtils.isEmpty(str2) ? "related" : replace);
        param.put("position", str4);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "视频播放-->" + ("groupId=" + str + "|fromGid=" + str2 + "|category=" + replace + "|position=" + str4));
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogPlay(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$3_4Dh7SNTwWs8XvIk76XwdR8KrI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "视频播放-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$9T9ktDTjdNa_zR9TV3PTDTke2HY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogPlay$3(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogPlay$3(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("火山日志完成", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogOver(String str, String str2, String str3, String str4, String str5, String str6) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        param.put("category", !TextUtils.isEmpty(str2) ? "related" : replace);
        param.put("percent", str4.equals("0") ? "1" : str4);
        param.put("duration", str5.equals("0") ? "1" : str5);
        param.put("position", str6);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        String str7 = "groupId=" + str + "|fromGid=" + str2 + "|category=" + replace + "|percent=" + str4 + "duration=" + str5 + "position=" + str6;
        if (param.get("percent").equals("0")) {
            return;
        }
        UIUtils.logD("火山日志上报", "视频结束-->" + str7);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogOver(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$pOhHvV7iyKqV6cNUJv-niqo5xMc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "视频结束-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$Vl6iLZ-cOmQgEaTzHwr-aHKAdBA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogOver$5(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogOver$5(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logE("火山日志错误：", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogAutoPlay(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        param.put("category", !TextUtils.isEmpty(str2) ? "related" : replace);
        param.put("position", str4);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "视频播放自动-->" + ("groupId=" + str + "|fromGid=" + str2 + "|category=" + replace + "|position=" + str4));
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogAutoPlay(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$gU5-KcT1Dm8PYJ1Lf_iolExXEyA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "视频播放自动-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$X1J98v7_xPBHBc_v4TkxCwPGOj0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogAutoPlay$7(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogAutoPlay$7(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("火山日志完成", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogAutoOver(String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str)) {
            UIUtils.logD("火山日志上报", "groupId为空拦截：");
            return;
        }
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        param.put("category", !TextUtils.isEmpty(str2) ? "related" : replace);
        param.put("percent", str4.equals("0") ? "1" : str4);
        param.put("duration", str5.equals("0") ? "1" : str5);
        param.put("position", str6);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "视频结束自动-->" + ("groupId=" + str + "|fromGid=" + str2 + "|category=" + replace + "|percent=" + str4 + "|duration=" + str5 + "position=" + str6));
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogAutoOver(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$9k3QlZXmLQJ_a9F9LpwZCkm41sQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "视频结束自动-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$ik5ZyAPTLgou5gnt3EaMl1En_ns
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogAutoOver$9(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogAutoOver$9(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logE("火山日志错误：", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogClick(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        if (!TextUtils.isEmpty(str2)) {
            replace = "related";
        }
        param.put("category", replace);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "视频点击-->" + json);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogClick(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$96_dcMZ89zOH_feMmg_DXMYEic4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "视频点击-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$kce2HUbx7h4beUVaLxsGEQERuPI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogClick$11(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogClick$11(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("火山日志完成", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    public void hiBoardLogStay(String str, String str2, String str3, String str4, String str5) {
        if (str3 == null) {
            str3 = "";
        }
        String replace = str3.replace("拦截", "");
        Map<String, String> param = getParam();
        param.put("groupId", str);
        if (!TextUtils.isEmpty(str2)) {
            param.put("fromGid", str2);
        }
        if (!TextUtils.isEmpty(str2)) {
            replace = "related";
        }
        param.put("category", replace);
        param.put("stayTime", str5);
        Gson gson = this.gson;
        final String json = !(gson instanceof Gson) ? gson.toJson(param) : NBSGsonInstrumentation.toJson(gson, param);
        UIUtils.logD("火山日志上报", "停留时长-->" + json);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(URLSet.hiBoardLogStay(), json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$eDaKRCD7gXAris8DbXin1mQMNIA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("火山日志完成", "停留时长-->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.utils.-$$Lambda$HiBoardLog$EaAzraF8lM12Gevssnf5XO1x9rY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HiBoardLog.lambda$hiBoardLogStay$13(json, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLogStay$13(String str, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("火山日志完成", th.getMessage());
        LogToFile.writeToFile(str + "|错误信息：" + th.getMessage());
    }

    private Map<String, String> getParam() {
        HashMap hashMap = new HashMap();
        hashMap.put("dt", DeviceHelper.getDeviceModel());
        hashMap.put("os", "Android");
        hashMap.put("osVersion", DeviceHelper.getDeviceOSVersion().replace("android", ""));
        hashMap.put("clientVersion", UIUtils.getAppVersionName(this.activityContext));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBranD());
        hashMap.put("eventTime", (System.currentTimeMillis() / 1000) + "");
        return hashMap;
    }
}
