package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.common.DeviceHelper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceIdPlugin extends YHXXJSPlugin {
    private String deviceModel;
    private JSONObject originConfigJO;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(AppCompatActivity appCompatActivity, WebView webView, int i, String str) throws JSONException {
        this.originConfigJO = new JSONObject(str);
        this.deviceModel = DeviceHelper.getDeviceID(true);
        callBack(webView, this.originConfigJO, YHXXJSPlugin.STATUS_SUCCESS, this.deviceModel);
    }
}
