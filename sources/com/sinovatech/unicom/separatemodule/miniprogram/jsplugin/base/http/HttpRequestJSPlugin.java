package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http;

import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ErrorStatusCodeException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.HostPermissionManager;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.Cookie;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/request")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HttpRequestJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        final String string = this.parameterJO.getString("url");
        final String optString = this.parameterJO.optString("method");
        int optInt = this.parameterJO.optInt("timeout", 10000);
        if (optInt < 5000) {
            optInt = 5000;
        } else if (optInt > 60000) {
            optInt = 60000;
        }
        int i = optInt / 1000;
        String optString2 = this.parameterJO.optString("requestDataType");
        JSONObject optJSONObject = this.parameterJO.optJSONObject("cookies");
        JSONObject optJSONObject2 = this.parameterJO.optJSONObject("header");
        JSONObject optJSONObject3 = this.parameterJO.optJSONObject("data");
        if (optJSONObject3 == null) {
            optJSONObject3 = new JSONObject();
        }
        Map<String, String> transformJSONObjectToMap = transformJSONObjectToMap("header", optJSONObject2);
        handleCookie(string, optJSONObject);
        if (HostPermissionManager.getInstance(this.activityContext).checkHostPermission(getCurrentCumpAppId(), string)) {
            if ("JSON".equals(optString2) || "text/x-json".equals(optString2)) {
                App.getAsyncHttpClient(i, i, i).rxPost(string, "JSON".equals(optString2) ? AsyncHttpClient.RequestBodyWay.JSON : AsyncHttpClient.RequestBodyWay.TextXJSON, null, !(optJSONObject3 instanceof JSONObject) ? optJSONObject3.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject3), transformJSONObjectToMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        MsLogUtil.m7980d("HttpRequestJSPlugin-(" + string + ")" + optString + "返回：" + str);
                        HttpRequestJSPlugin.this.callbackSuccess(str);
                        HttpRequestJSPlugin.this.log_SCE05(null, string);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        MsLogUtil.m7978e("HttpRequestJSPlugin-(" + string + ")" + optString + "报错：" + th.getMessage());
                        HttpRequestJSPlugin.this.handleError(th, string);
                        HttpRequestJSPlugin.this.log_SCE05(th, string);
                    }
                });
                return;
            }
            Map<String, String> transformJSONObjectToMap2 = transformJSONObjectToMap("data", optJSONObject3);
            if ("POST".equalsIgnoreCase(optString)) {
                App.getAsyncHttpClient(i, i, i).rxPost(string, transformJSONObjectToMap2, transformJSONObjectToMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.3
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        MsLogUtil.m7980d("HttpRequestJSPlugin-(" + string + ")" + optString + "返回：" + str);
                        HttpRequestJSPlugin.this.callbackSuccess(str);
                        HttpRequestJSPlugin.this.log_SCE05(null, string);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.4
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        MsLogUtil.m7978e("HttpRequestJSPlugin-(" + string + ")" + optString + "报错：" + th.getMessage());
                        HttpRequestJSPlugin.this.handleError(th, string);
                        HttpRequestJSPlugin.this.log_SCE05(th, string);
                    }
                });
                return;
            } else {
                App.getAsyncHttpClient(i, i, i).rxGet(string, transformJSONObjectToMap2, transformJSONObjectToMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.5
                    @Override // io.reactivex.functions.Consumer
                    public void accept(String str) throws Exception {
                        MsLogUtil.m7980d("HttpRequestJSPlugin-(" + string + ")" + optString + "返回：" + str);
                        HttpRequestJSPlugin.this.callbackSuccess(str);
                        HttpRequestJSPlugin.this.log_SCE05(null, string);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.http.HttpRequestJSPlugin.6
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        MsLogUtil.m7978e("HttpRequestJSPlugin-(" + string + ")" + optString + "报错：" + th.getMessage());
                        HttpRequestJSPlugin.this.handleError(th, string);
                        HttpRequestJSPlugin.this.log_SCE05(th, string);
                    }
                });
                return;
            }
        }
        MsLogUtil.m7977e(CumpLanucher.TAG, "HttpRequestJSPlugin拦截URL：" + string + " 需要到开发者中心授权安全域名");
        handleError(new RuntimeException("需要到开发者中心授权安全域名"), string);
        try {
            CumpLogManager.getInstance(this.activityContext).log_SCE02(CumpLogEnu.APP_SCE_02_01, this.logStartDate, null, getCurrentCumpAppId(), this.logAppName, string, "HttpRequestJSPlugin拦截" + string + ",需要到开发者中心授权安全域名", "", this.logNewAppVersion, CumpLogEnu.interceptStatus_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleError(Throwable th, String str) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", str);
        if (th instanceof ErrorStatusCodeException) {
            ErrorStatusCodeException errorStatusCodeException = (ErrorStatusCodeException) th;
            jSONObject.put("code", errorStatusCodeException.statusCode);
            jSONObject.put("description", errorStatusCodeException.getMessage());
        } else {
            jSONObject.put("code", "");
            jSONObject.put("description", th.getMessage());
        }
        jSONObject.put("appVersion", this.activityContext.getString(2131886969));
        callbackFail(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log_SCE05(Throwable th, String str) {
        try {
            if (th == null) {
                CumpLogManager.getInstance(this.activityContext).log_SCE05(this.logStartDate, new Date(), getCurrentCumpAppId(), this.logAppName, str, "", CumpLogEnu.urlStatus_Success, this.logNewAppVersion, "request");
            } else if (th instanceof ErrorStatusCodeException) {
                String valueOf = String.valueOf(((ErrorStatusCodeException) th).statusCode);
                CumpLogManager.getInstance(this.activityContext).log_SCE05(this.logStartDate, new Date(), getCurrentCumpAppId(), this.logAppName, str, ((ErrorStatusCodeException) th).getMessage(), valueOf, this.logNewAppVersion, "request");
            } else {
                CumpLogManager.getInstance(this.activityContext).log_SCE05(this.logStartDate, new Date(), getCurrentCumpAppId(), this.logAppName, str, th.getMessage(), CumpLogEnu.urlStatus_Fail, this.logNewAppVersion, "request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCookie(String str, JSONObject jSONObject) {
        try {
            App.getAsyncHttpClient().clearCookieFromAddToRequest();
            String host = Uri.parse(str).getHost();
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    App.getAsyncHttpClient().addCookieForAddToReqeust(new Cookie.Builder().name(next).value(jSONObject.getString(next)).domain(host).build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> transformJSONObjectToMap(String str, JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!str.equals("header") || (!next.toLowerCase().contains("cookie") && !next.toLowerCase().contains("content-type") && !next.toLowerCase().contains("user-agent"))) {
                        hashMap.put(next, jSONObject.getString(next));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
