package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.nfc;

import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/nfcOpen")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NfcOpenJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.activityContext.getPackageManager().hasSystemFeature("android.hardware.nfc")) {
                this.activityContext.startActivity(new Intent("android.settings.NFC_SETTINGS"));
            } else {
                callbackFail("当前设备不支持NFC功能");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("NFC调用系统设置异常:" + e.getMessage());
        }
    }
}
