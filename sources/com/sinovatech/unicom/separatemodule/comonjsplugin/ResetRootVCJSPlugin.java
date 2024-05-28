package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.LaunchModeUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/resetRootVC")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ResetRootVCJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            LaunchModeUtils.changeAppNorml(this.activityContext, new LaunchModeUtils.OnChangeLaunchModeListener() { // from class: com.sinovatech.unicom.separatemodule.comonjsplugin.ResetRootVCJSPlugin.1
                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                public void onSuccess() {
                    UIUtils.relaunchApp();
                }

                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                public void onFail(String str) {
                    ResetRootVCJSPlugin.this.callbackFail(str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            LaunchModeUtils.changeAppNorml(this.activityContext, new LaunchModeUtils.OnChangeLaunchModeListener() { // from class: com.sinovatech.unicom.separatemodule.comonjsplugin.ResetRootVCJSPlugin.2
                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                public void onSuccess() {
                    UIUtils.relaunchApp();
                }

                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                public void onFail(String str) {
                    ResetRootVCJSPlugin.this.callbackFail(str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackFailSync(true);
    }
}
