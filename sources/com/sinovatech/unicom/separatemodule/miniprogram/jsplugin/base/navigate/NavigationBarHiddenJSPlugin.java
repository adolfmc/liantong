package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/navigationBarHidden")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NavigationBarHiddenJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            if (this.webFragment instanceof WebFragment) {
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.NavigationBarHiddenJSPlugin.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NavigationBarHiddenJSPlugin.this.webFragment instanceof WebFragment) {
                            ((WebFragment) NavigationBarHiddenJSPlugin.this.webFragment).navigationBarHidden();
                        }
                    }
                });
                return callbackSuccessSync(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("navigationBarHidden接口错误：" + e.getMessage());
        }
        return callbackFailSync(false);
    }
}
