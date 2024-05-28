package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/pageLocationIsTab")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PageLocationIsTabJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        if (this.activityContext instanceof MainActivity) {
            return callbackSuccessSync(true);
        }
        return callbackSuccessSync(false);
    }
}
