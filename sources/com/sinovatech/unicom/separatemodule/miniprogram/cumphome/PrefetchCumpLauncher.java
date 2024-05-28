package com.sinovatech.unicom.separatemodule.miniprogram.cumphome;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntityParser;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpResponse;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.CumpResouceUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PrefetchCumpLauncher {
    public static final String PrefetchStatus_Complete = "complete";
    public static String TAG = "PrefetchCumpLauncher";
    public static PrefetchCumpLauncher instance;
    private Context context;
    private String publishTypeParam = "2";
    private Box<CumpEntity> cumpBox = CumpResouceUtils.getCumpBox();

    public static synchronized PrefetchCumpLauncher getInstance(Context context) {
        PrefetchCumpLauncher prefetchCumpLauncher;
        synchronized (PrefetchCumpLauncher.class) {
            if (instance == null) {
                synchronized (PrefetchCumpLauncher.class) {
                    if (instance == null) {
                        instance = new PrefetchCumpLauncher(context);
                    }
                }
            }
            prefetchCumpLauncher = instance;
        }
        return prefetchCumpLauncher;
    }

    private PrefetchCumpLauncher(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAllPrefetchStatus() {
        App.getInstance().prefetchCumpStatus = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllPrefetchStatus(List<String> list, String str) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                updatePrefetchStatus(list.get(i), str);
            }
        }
    }

    public void updatePrefetchStatus(String str, String str2) {
        App.getInstance().prefetchCumpStatus.put(str, str2);
    }

    public String getPrefetchStatus(String str) {
        String str2 = App.getInstance().prefetchCumpStatus.get(str);
        return TextUtils.isEmpty(str2) ? "" : str2;
    }

    public void prefetchCumpConfig(String str) {
        try {
            MsLogUtil.m7979d(TAG, "预加载小程序 start " + str);
            clearAllPrefetchStatus();
            if (TextUtils.isEmpty(str)) {
                MsLogUtil.m7979d(TAG, "预加载小程序 complete");
                return;
            }
            String[] split = str.split(",");
            final ArrayList arrayList = new ArrayList();
            String str2 = "";
            for (int i = 0; i < split.length; i++) {
                if (split[i].startsWith("edop_unicom_")) {
                    String string = App.getSharePreferenceUtil().getString("HomeCumpAppIdConfig");
                    if (TextUtils.isEmpty(string)) {
                        string = URLSet.getHomeTuiJianDefUrlAndCumpUrl("appId");
                    }
                    String string2 = App.getSharePreferenceUtil().getString("WoDeXiaoHeiTiaoCumpAppIdConfig");
                    if (TextUtils.isEmpty(string2)) {
                        string2 = URLSet.getWodexiaoheitiaoCumpAppId();
                    }
                    if (!split[i].equals(string) && !split[i].equals(string2)) {
                        str2 = str2 + split[i].replace("edop_unicom_", "") + ",";
                        arrayList.add(split[i]);
                    } else {
                        MsLogUtil.m7979d(TAG, "预加载小程序 配置了首页ID或者小黑条ID忽略(" + split[i] + ")");
                    }
                }
            }
            if (TextUtils.isEmpty(str2)) {
                MsLogUtil.m7979d(TAG, "预加载小程序 没有有效ID 流程结束 ");
                return;
            }
            if (str2.endsWith(",")) {
                str2 = str2.substring(0, str2.length() - 1);
            }
            if (URLEnvironmentConfig.isForPublish()) {
                this.publishTypeParam = "2";
            } else {
                this.publishTypeParam = App.getSharePreferenceUtil().getBoolean("HomeCumpPublishType") ? "1" : "2";
            }
            String replace = URLSet.getEdopPrefetchConfigUrl().replace("{publishType}", this.publishTypeParam).replace("{appVersion}", this.context.getString(2131886969)).replace("{appIdList}", str2);
            MsLogUtil.m7979d(TAG, "预加载小程序拼接接口：" + replace);
            App.getAsyncHttpClient(5, 5, 5, 5).rxGet(replace, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).flatMap(new Function<String, ObservableSource<Object>>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher.3
                @Override // io.reactivex.functions.Function
                public ObservableSource<Object> apply(String str3) throws Exception {
                    String str4 = PrefetchCumpLauncher.TAG;
                    MsLogUtil.m7979d(str4, "预加载小程序 接口返回：" + str3);
                    JSONObject jSONObject = new JSONObject(str3);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("response").getJSONObject("head");
                    String optString = jSONObject2.optString("respCode", "");
                    String optString2 = jSONObject2.optString("respMsg", "");
                    JSONObject jSONObject3 = jSONObject.getJSONObject("response").getJSONObject("body");
                    if ("0000".equals(optString)) {
                        JSONArray jSONArray = jSONObject3.getJSONArray("appBasicInfoList");
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            JSONObject jSONObject4 = jSONArray.getJSONObject(i2);
                            String string3 = jSONObject4.getString("rsp_code");
                            String string4 = jSONObject4.getString("appId");
                            if (!PrefetchCumpLauncher.PrefetchStatus_Complete.equals(PrefetchCumpLauncher.this.getPrefetchStatus(string4))) {
                                if ("0000".equals(string3)) {
                                    String str5 = PrefetchCumpLauncher.TAG;
                                    MsLogUtil.m7979d(str5, "预加载小程序 解析appId:" + string4 + " --------------------start--------------------------------");
                                    CumpEntityParser.parse(PrefetchCumpLauncher.TAG, new CumpResponse(), jSONObject4, PrefetchCumpLauncher.this.cumpBox, PrefetchCumpLauncher.this.publishTypeParam);
                                    String str6 = PrefetchCumpLauncher.TAG;
                                    MsLogUtil.m7979d(str6, "预加载小程序 解析appId:" + string4 + " ---------------------end-------------------------------");
                                } else {
                                    CumpResouceUtils.deleteApp(string4);
                                    String str7 = PrefetchCumpLauncher.TAG;
                                    MsLogUtil.m7979d(str7, "预加载小程序 解析appId:" + string4 + " res_code：" + string3);
                                }
                            } else {
                                String str8 = PrefetchCumpLauncher.TAG;
                                MsLogUtil.m7979d(str8, "预加载小程序 解析appId:" + string4 + " 此ID用户已经主动访问过，忽略不解析");
                            }
                        }
                        MsLogUtil.m7979d(PrefetchCumpLauncher.TAG, "预加载小程序 循环解析完成");
                        return Observable.just(new Object());
                    }
                    String str9 = PrefetchCumpLauncher.TAG;
                    MsLogUtil.m7979d(str9, "预加载小程序 预加载接口异常 " + optString + " " + optString2);
                    return Observable.error(new RuntimeException(optString + " " + optString2));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    PrefetchCumpLauncher.this.updateAllPrefetchStatus(arrayList, PrefetchCumpLauncher.PrefetchStatus_Complete);
                    MsLogUtil.m7979d(PrefetchCumpLauncher.TAG, "预加载小程序 complete");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str3 = PrefetchCumpLauncher.TAG;
                    MsLogUtil.m7979d(str3, "预加载小程序 consumer onError " + th.toString());
                    PrefetchCumpLauncher.this.clearAllPrefetchStatus();
                    MsLogUtil.m7979d(PrefetchCumpLauncher.TAG, "预加载小程序 complete");
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, "预加载小程序 最外层catch 运行错误：" + e.toString());
            clearAllPrefetchStatus();
            MsLogUtil.m7979d(TAG, "预加载小程序 complete");
        }
    }
}
