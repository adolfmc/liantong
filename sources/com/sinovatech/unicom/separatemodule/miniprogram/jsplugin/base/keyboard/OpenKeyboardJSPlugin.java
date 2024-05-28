package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/openKeyboard")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OpenKeyboardJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("emoji");
        if (this.activityContext != null) {
            Intent intent = new Intent(this.activityContext, KeyboardActivity.class);
            intent.putExtra("action", "open");
            intent.putExtra("msg", optString);
            new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.OpenKeyboardJSPlugin.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    Activity activity = OpenKeyboardJSPlugin.this.activityContext;
                    if (i != -1 || intent2 == null) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("text", intent2.getStringExtra("text"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    OpenKeyboardJSPlugin.this.callbackSuccess(jSONObject);
                }
            });
        }
    }
}
