package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.HostPermissionManager;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/navigateTo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NavigateToJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        final String string = this.parameterJO.getString("target");
        final JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
        final boolean optBoolean = this.parameterJO.optBoolean("navigationBarHidden", false);
        boolean optBoolean2 = this.parameterJO.optBoolean("isNeedLogin", false);
        if (HostPermissionManager.getInstance(this.activityContext).checkHostPermission(getCurrentCumpAppId(), string)) {
            if (optBoolean2 && !App.hasLogined()) {
                new AvoidOnResult(this.activityContext).startForResult(new Intent(this.activityContext, LoginBindActivity.class), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.NavigateToJSPlugin.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        if (App.hasLogined()) {
                            NavigateToJSPlugin.this.webFragment.ignoreLifeEventToJS = true;
                            NavigateToJSPlugin.this.nativateTo(string, optJSONObject, optBoolean);
                            return;
                        }
                        NavigateToJSPlugin.this.webFragment.ignoreLifeEventToJS = false;
                    }
                });
                return;
            }
            nativateTo(string, optJSONObject, optBoolean);
            return;
        }
        String str = CumpLanucher.TAG;
        MsLogUtil.m7977e(str, "NavigateToJSPlugin拦截URL：" + string + " 需要到开发者中心授权安全域名");
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" 跳转链接非法，请到开发者中心授权");
        UIUtils.toastLong(sb.toString());
        try {
            CumpLogManager cumpLogManager = CumpLogManager.getInstance(this.activityContext);
            String str2 = CumpLogEnu.APP_SCE_02_02;
            Date date = this.logStartDate;
            String currentCumpAppId = getCurrentCumpAppId();
            String str3 = this.logAppName;
            cumpLogManager.log_SCE02(str2, date, null, currentCumpAppId, str3, string, "NavigateToJSPlugin拦截URL" + string + ",需要到开发者中心授权安全域名", "", this.logNewAppVersion, CumpLogEnu.interceptStatus_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nativateTo(String str, JSONObject jSONObject, boolean z) {
        if (IntentManager.liveHandleLocal(this.activityContext, "", str, jSONObject)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle("");
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(!z);
        webParamsEntity.setYule(false);
        webParamsEntity.setReferer(getCurrentURL());
        if (str.startsWith("ms_unicom") || str.startsWith("https://ms_unicom") || str.startsWith("http://ms_unicom") || str.startsWith("edop_unicom") || str.startsWith("https://edop_unicom") || str.startsWith("http://edop_unicom")) {
            webParamsEntity.setType("UnicomMiniProgram");
        }
        if (jSONObject != null) {
            Map<String, Object> transformJSONObjectToMap = transformJSONObjectToMap(jSONObject);
            String uuid = UUID.randomUUID().toString();
            App.navigateParamsCacheMap.put(uuid, transformJSONObjectToMap);
            webParamsEntity.setNavigateParamsUUID(uuid);
        }
        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        this.activityContext.startActivity(intent);
    }

    private Map<String, Object> transformJSONObjectToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.get(next));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
