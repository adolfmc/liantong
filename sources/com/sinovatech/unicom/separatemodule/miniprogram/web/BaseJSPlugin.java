package com.sinovatech.unicom.separatemodule.miniprogram.web;

import android.app.Activity;
import android.util.Base64;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSPermissionManager;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import java.net.URLEncoder;
import java.util.Date;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseJSPlugin implements IProvider {
    public String action;
    public Activity activityContext;
    public String callbackId;
    public Date logStartDate;
    public JSONObject originConfigJO;
    public JSONObject parameterJO;
    public BaseWebFragment webFragment;

    /* renamed from: wv */
    public WebView f18589wv;
    public Boolean SingletonPattern = false;
    public String logAppName = "";
    public String logNewAppVersion = "";

    public abstract void onExec() throws Exception;

    public String onExecSync() throws Exception {
        return null;
    }

    public void exec(Activity activity, BaseWebFragment baseWebFragment, WebView webView, String str) throws Exception {
        this.activityContext = activity;
        this.webFragment = baseWebFragment;
        this.f18589wv = webView;
        this.originConfigJO = new JSONObject(str);
        this.action = this.originConfigJO.getString("action");
        this.callbackId = this.originConfigJO.optString("callbackId");
        this.parameterJO = this.originConfigJO.optJSONObject("parameter");
        this.logStartDate = new Date();
        CumpEntity appInfoFromBox = CumpLanucher.getInstance(activity).getAppInfoFromBox(getCurrentCumpAppId());
        if (appInfoFromBox != null) {
            this.logAppName = appInfoFromBox.getAppName();
            this.logNewAppVersion = appInfoFromBox.getOfficialVersion();
        }
        if (JSPermissionManager.getInstance(activity).checkJSPermission(getCurrentCumpAppId(), baseWebFragment.currentH5Id, this.action, getCurrentURL())) {
            if (JSPermissionManager.getInstance(activity).isOnlyGrantEdop(getCurrentCumpAppId(), this.action)) {
                if (UserAuthScopeManager.getInstance(activity).checkUserAuth(UserManager.getInstance().getCurrentPhoneNumber(), getCurrentCumpAppId(), baseWebFragment.currentH5Id, this.action)) {
                    onExec();
                    return;
                }
                callbackFail("20", "用户未授权使用" + this.action + "权限 需要引导用户二次授权");
                return;
            }
            callbackFail("10", "程序错误:只允许联通小程序模式使用");
            return;
        }
        callbackFail(this.action + "能力未授权 请到开发者中心申请");
    }

    public String execSync(Activity activity, BaseWebFragment baseWebFragment, WebView webView, String str) throws Exception {
        this.activityContext = activity;
        this.webFragment = baseWebFragment;
        this.f18589wv = webView;
        this.originConfigJO = new JSONObject(str);
        this.action = this.originConfigJO.getString("action");
        this.callbackId = this.originConfigJO.optString("callbackId");
        this.parameterJO = this.originConfigJO.optJSONObject("parameter");
        this.logStartDate = new Date();
        CumpEntity appInfoFromBox = CumpLanucher.getInstance(activity).getAppInfoFromBox(getCurrentCumpAppId());
        if (appInfoFromBox != null) {
            this.logAppName = appInfoFromBox.getAppName();
            this.logNewAppVersion = appInfoFromBox.getOfficialVersion();
        }
        if (JSPermissionManager.getInstance(activity).checkJSPermission(getCurrentCumpAppId(), baseWebFragment.currentH5Id, this.action, getCurrentURL())) {
            if (JSPermissionManager.getInstance(activity).isOnlyGrantEdop(getCurrentCumpAppId(), this.action)) {
                if (UserAuthScopeManager.getInstance(activity).checkUserAuth(UserManager.getInstance().getCurrentPhoneNumber(), getCurrentCumpAppId(), baseWebFragment.currentH5Id, this.action)) {
                    return onExecSync();
                }
                return callbackFailSync("20", "用户未授权使用" + this.action + "权限");
            }
            return callbackFailSync("10", "只允许联通小程序模式使用");
        }
        return this.action + "能力未授权 请到开发者中心申请";
    }

    public void callbackSuccess(Object obj) {
        callbackFromNative("success", obj);
    }

    public void callbackSuccess(String str, String str2, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", str);
            jSONObject2.put("msg", str2);
            if (jSONObject != null) {
                jSONObject2.put("data", jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        callbackSuccess(jSONObject2);
    }

    public void callbackFail(Object obj) {
        callbackFromNative("fail", obj);
    }

    public void callbackFail(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callbackFail(jSONObject);
    }

    public String callbackFailSync(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackFromNativeSync("fail", jSONObject);
    }

    private void callbackFromNative(String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("data", obj);
            if (this.originConfigJO != null) {
                this.originConfigJO.put("parameter", jSONObject);
                JSONObject jSONObject2 = this.originConfigJO;
                String encodeToString = Base64.encodeToString(URLEncoder.encode(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), "utf-8").replaceAll("\\+", "%20").getBytes("utf-8"), 2);
                this.f18589wv.evaluateJavascript("javascript:MsJSBridge.callbackFromNative(\"" + encodeToString + "\")", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String callbackFromNativeSync(String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("data", obj);
            return Base64.encodeToString(URLEncoder.encode(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "utf-8").replaceAll("\\+", "%20").getBytes("utf-8"), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String callbackSuccessSync(Object obj) {
        return callbackFromNativeSync("success", obj);
    }

    public String callbackFailSync(Object obj) {
        return callbackFromNativeSync("fail", obj);
    }

    public String getCurrentCumpAppId() {
        try {
            return this.webFragment instanceof BaseWebFragment ? this.webFragment.currentCumpAppId : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getCurrentURL() {
        try {
            return this.webFragment instanceof BaseWebFragment ? this.webFragment.currentURLForPlugin : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
