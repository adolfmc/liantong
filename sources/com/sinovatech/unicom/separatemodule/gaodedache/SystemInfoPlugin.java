package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.common.DeviceHelper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SystemInfoPlugin extends YHXXJSPlugin {
    private JSONObject originConfigJO;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.originConfigJO = new JSONObject(str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", DeviceHelper.getDeviceModel());
            jSONObject.put("version", getLocalVersion(appCompatActivity));
            jSONObject.put("brand", DeviceHelper.getDeviceBrand());
            jSONObject.put("system", DeviceHelper.getDeviceOSVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        callBack(webView, this.originConfigJO, YHXXJSPlugin.STATUS_SUCCESS, jSONObject);
    }

    private String getLocalVersion(AppCompatActivity appCompatActivity) {
        try {
            return appCompatActivity.getApplicationContext().getPackageManager().getPackageInfo(appCompatActivity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
