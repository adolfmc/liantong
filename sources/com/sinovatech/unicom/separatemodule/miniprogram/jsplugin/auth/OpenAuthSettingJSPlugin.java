package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpAuthRecordActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/openAuthSetting")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OpenAuthSettingJSPlugin extends BaseJSPlugin {
    private Dialog authDialog;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (!TextUtils.isEmpty(getCurrentCumpAppId()) && CumpLanucher.getInstance(this.activityContext).getAppInfoFromBox(getCurrentCumpAppId()) != null) {
                Intent intent = new Intent(this.activityContext, CumpAuthRecordActivity.class);
                intent.putExtra("appId", getCurrentCumpAppId());
                new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.OpenAuthSettingJSPlugin.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent2) {
                        OpenAuthSettingJSPlugin.this.callbackSuccess(new JSONObject());
                    }
                });
            } else {
                callbackFail("10", "找不到小程序信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
