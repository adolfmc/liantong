package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/alert")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AlertJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("title");
        String optString2 = this.parameterJO.optString("msg");
        String optString3 = this.parameterJO.optString("cancel");
        CustomDialogManager.show(this.activityContext, optString, optString2, !TextUtils.isEmpty(optString3), optString3, this.parameterJO.optString("confirm"), false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device.AlertJSPlugin.1
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                AlertJSPlugin.this.callbackSuccess("");
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                AlertJSPlugin.this.callbackFail("");
            }
        });
    }
}
