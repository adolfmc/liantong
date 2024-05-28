package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/openPhoneDefaultBrowser")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class openPhoneDefaultBrowserJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject jSONObject = new JSONObject();
        try {
            String optString = this.parameterJO.optString("url");
            if (!TextUtils.isEmpty(optString)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(Html.fromHtml(optString).toString()));
                try {
                    this.activityContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    intent.setComponent(null);
                    this.activityContext.startActivity(intent);
                }
            } else {
                jSONObject.put("state", "10");
                jSONObject.put("msg", "请检查传入参数");
                callbackSuccess(jSONObject);
            }
        } catch (Exception e2) {
            jSONObject.put("state", "11");
            jSONObject.put("msg", "打开浏览器失败");
            callbackSuccess(jSONObject);
            e2.printStackTrace();
        }
    }
}
