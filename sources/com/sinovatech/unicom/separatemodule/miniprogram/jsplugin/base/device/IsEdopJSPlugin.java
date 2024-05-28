package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/isEdop")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class IsEdopJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        if (!TextUtils.isEmpty(this.webFragment.currentCumpAppId) && this.webFragment.currentCumpAppId.startsWith("edop_unicom")) {
            return callbackSuccessSync(true);
        }
        return callbackSuccessSync(false);
    }
}
