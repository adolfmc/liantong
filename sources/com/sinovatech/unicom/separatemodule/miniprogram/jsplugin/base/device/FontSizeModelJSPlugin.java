package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.FontSizeUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/fontSizeModel")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FontSizeModelJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        boolean z = App.getSharePreferenceUtil().getBoolean("CareHome");
        if (FontSizeUtil.isBigFont() || z) {
            return callbackSuccessSync("1");
        }
        return callbackSuccessSync("0");
    }
}
