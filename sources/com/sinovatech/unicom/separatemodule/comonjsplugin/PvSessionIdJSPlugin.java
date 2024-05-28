package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/PvSessionId")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PvSessionIdJSPlugin extends BaseJSPlugin {
    private static final String TAG = "PvSessionIdJSPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return callbackSuccessSync(App.getPvLogSessionId());
    }
}
