package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/getAccountIsNetWorkAccount")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetAccountIsNetWorkAccountJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("channelCode");
            if (!App.hasLogined()) {
                callbackFail("11", "当前用户未登录");
            } else {
                GetNetAccessCode.getInstance().getSDKAccessCode((AppCompatActivity) this.activityContext, string, new GetNetAccessCode.GetNetAccessCodeClick() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.GetAccountIsNetWorkAccountJSPlugin.1
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode.GetNetAccessCodeClick
                    public void onGetData(String str) {
                        if (!TextUtils.isEmpty(str)) {
                            GetAccountIsNetWorkAccountJSPlugin.this.callbackSuccess(str);
                        } else {
                            GetAccountIsNetWorkAccountJSPlugin.this.callbackSuccess("0");
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常:" + e.getMessage());
        }
    }
}
