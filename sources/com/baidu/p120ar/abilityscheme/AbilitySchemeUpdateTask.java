package com.baidu.p120ar.abilityscheme;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.RequestParamsBuilder;
import com.baidu.p120ar.utils.UrlUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.abilityscheme.AbilitySchemeUpdateTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilitySchemeUpdateTask {
    private static final String TAG = "AbilitySchemeUpdateTask";
    private ICallbackWith<String> mFailCallback;
    private IHttpRequest mRequest;

    public void setFailCallback(ICallbackWith<String> iCallbackWith) {
        this.mFailCallback = iCallbackWith;
    }

    public void fetch(Context context, String str, String str2, final ICallbackWith<AbilitySchemeClassification> iCallbackWith) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest == null) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = UrlUtils.getAbilitySchemeFetchUrl();
        }
        newRequest.setMethod("POST").setUrl(str2).setBody(getPostParams(context, str));
        newRequest.enqueue(new IHttpCallback() { // from class: com.baidu.ar.abilityscheme.AbilitySchemeUpdateTask.1
            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onResponse(IHttpResponse iHttpResponse) {
                if (!iHttpResponse.isSuccess() || iCallbackWith == null) {
                    return;
                }
                try {
                    String content = iHttpResponse.getContent();
                    String str3 = AbilitySchemeUpdateTask.TAG;
                    ARLog.m20421d(str3, "response: " + content);
                    AbilitySchemeClassification parseSchemeConf = AbilitySchemeUpdateTask.this.parseSchemeConf(content);
                    if (parseSchemeConf != null) {
                        iCallbackWith.run(parseSchemeConf);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onFail(HttpException httpException) {
                String str3 = AbilitySchemeUpdateTask.TAG;
                ARLog.m20421d(str3, "" + httpException.getMessage());
            }
        });
        this.mRequest = newRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbilitySchemeClassification parseSchemeConf(String str) {
        if (TextUtils.isEmpty(str)) {
            if (this.mFailCallback != null) {
                notifyFail("response is empty");
            }
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("err_num", -1);
            if (optInt == 0 && jSONObject.has("data")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null && optJSONObject.has("classification")) {
                    AbilitySchemeClassification abilitySchemeClassification = new AbilitySchemeClassification();
                    abilitySchemeClassification.scheme = optJSONObject.getJSONObject("classification");
                    abilitySchemeClassification.classificationId = optJSONObject.optString("classification_id", "default");
                    if (!checkClassificationFormat(abilitySchemeClassification.scheme) && this.mFailCallback != null) {
                        ARLog.m20419e(TAG, str);
                        notifyFail("config format is error. see Logcat filter: " + TAG);
                    }
                    return abilitySchemeClassification;
                }
            } else if (this.mFailCallback != null) {
                String string = jSONObject.has("err_msg") ? jSONObject.getString("err_msg") : "";
                notifyFail("err_num: " + optInt + " " + string);
            }
        } catch (JSONException e) {
            if (this.mFailCallback != null) {
                notifyFail(e.getMessage());
            }
            e.printStackTrace();
        }
        return null;
    }

    private void notifyFail(String str) {
        ICallbackWith<String> iCallbackWith = this.mFailCallback;
        iCallbackWith.run("fetch ability scheme config fail. " + str);
    }

    private JSONObject getPostParams(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            RequestParamsBuilder.appendSignInfo(jSONObject);
            RequestParamsBuilder.appendUserInfo(context, jSONObject);
            RequestParamsBuilder.appendSystemInfo(context, jSONObject);
            jSONObject.put("device_platform", "");
            jSONObject.put("os_version", Build.VERSION.SDK_INT);
            jSONObject.put("device_type", Build.BRAND);
            jSONObject.put("device_id", RequestParamsBuilder.getUUID(context));
            jSONObject.put("app_version", ARSDKInfo.getVersionCode());
            jSONObject.put("manufacture", Build.MANUFACTURER);
            jSONObject.put("hardware", Build.HARDWARE);
            jSONObject.put("board", Build.BOARD);
            jSONObject.put("classification_id", str);
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("params: ");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            ARLog.m20421d(str2, sb.toString());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private boolean checkClassificationFormat(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (jSONObject == null || !jSONObject.has("common") || (optJSONObject = jSONObject.optJSONObject("common")) == null || !optJSONObject.has("cpu_score")) {
            return false;
        }
        Object opt = optJSONObject.opt("cpu_score");
        return opt instanceof String ? Integer.parseInt((String) opt) > 0 : (opt instanceof Number) && Integer.parseInt(opt.toString()) > 0;
    }

    public void cancel() {
        this.mFailCallback = null;
        IHttpRequest iHttpRequest = this.mRequest;
        if (iHttpRequest != null) {
            iHttpRequest.cancel();
            this.mRequest = null;
        }
    }
}
