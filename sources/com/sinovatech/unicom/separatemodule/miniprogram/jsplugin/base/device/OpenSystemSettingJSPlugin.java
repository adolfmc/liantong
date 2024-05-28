package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/openSystemSetting")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OpenSystemSettingJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("type");
        if ("systemLocation".equals(optString)) {
            try {
                this.activityContext.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("systemWifiList".equals(optString)) {
            try {
                this.activityContext.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.activityContext.getPackageName(), null));
            try {
                this.activityContext.startActivity(intent);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }
}
