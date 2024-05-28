package com.sinovatech.unicom.common;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class YinSiDialogUtils {
    private static final String TAG = "YinSiDialogUtils";

    public static void changeYinSiDialog(AppCompatActivity appCompatActivity) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("version", appCompatActivity.getString(2131886969));
            ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getYinSiDialogUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.common.YinSiDialogUtils.3
                @Override // io.reactivex.functions.Function
                public String apply(@NonNull String str) throws Exception {
                    if (TextUtils.isEmpty(str)) {
                        return "";
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (TextUtils.equals("200", jSONObject.optString("code"))) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("result");
                        if (optJSONObject != null) {
                            ConfigManager.setYinSiDialogCode(optJSONObject.optString("timestamp"), optJSONObject.optString("agreeText"));
                            return "";
                        }
                        App.getSharePreferenceUtil().putString(ConfigManager.unicom_yinsi_login_agree, "");
                        return "";
                    }
                    return "";
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.common.YinSiDialogUtils.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.common.YinSiDialogUtils.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, e.getMessage());
        }
    }
}
