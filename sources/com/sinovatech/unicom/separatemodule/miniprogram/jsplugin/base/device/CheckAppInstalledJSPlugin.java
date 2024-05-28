package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/checkAppInstalled")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CheckAppInstalledJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        PackageInfo packageInfo;
        try {
            packageInfo = this.activityContext.getPackageManager().getPackageInfo(this.parameterJO.optString("androidKey"), 0);
        } catch (Throwable th) {
            MsLogUtil.m7980d("CheckAppInstalledJSPlugin-IsPkgInstalled：" + th.getMessage());
            packageInfo = null;
        }
        if (packageInfo != null) {
            return callbackSuccessSync(true);
        }
        return callbackSuccessSync(false);
    }
}
