package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/callPhone")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CallPhoneJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("phoneNumber");
            this.activityContext.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + string)));
            callbackSuccess(new JSONObject());
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
