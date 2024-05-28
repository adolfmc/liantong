package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWebViewFullScreen;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/setVidoPortrait")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SetVidoPortraitJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            ManagerWebViewFullScreen.isPortrait = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            ManagerWebViewFullScreen.isPortrait = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(true);
    }
}
