package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HttpPrivateUtil {
    public static String faceV3Data = "";

    public static void getLisenceAndConfigPrivate(AppCompatActivity appCompatActivity, String str, String str2, String str3, final HttpCallBackListener httpCallBackListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("biz_token", str3);
            jSONObject.put("data", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil.3
            @Override // io.reactivex.functions.Function
            public String apply(String str4) throws Exception {
                return str4;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str4) throws Exception {
                if (HttpCallBackListener.this != null) {
                    if (!TextUtils.isEmpty(str4)) {
                        HttpCallBackListener.this.onSuccess(str4);
                    } else {
                        HttpCallBackListener.this.onFailure(-1, "");
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                HttpCallBackListener httpCallBackListener2 = HttpCallBackListener.this;
                if (httpCallBackListener2 != null) {
                    httpCallBackListener2.onFailure(-1, th.getMessage());
                }
            }
        });
    }

    public static String zhuanHuanString(byte[] bArr) {
        return (bArr == null || bArr.length <= 0) ? "" : Base64.encodeToString(bArr, 0).replaceAll("[\\s*\t\n\r]", "");
    }
}
