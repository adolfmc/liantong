package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/isFullScreen")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class IsFullScreenJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        boolean z = false;
        try {
            if (this.webFragment instanceof WebFragment) {
                if (((WebFragment) this.webFragment).titleLayout.getVisibility() != 0) {
                    z = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(Boolean.valueOf(z));
    }
}
