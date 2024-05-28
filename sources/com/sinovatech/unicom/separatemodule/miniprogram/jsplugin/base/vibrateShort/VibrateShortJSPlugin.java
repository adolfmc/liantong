package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.vibrateShort;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;

@Route(path = "/MsJSPlugin/vibrateShort")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VibrateShortJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            int i = "heavy".equals(optString) ? 90 : 30;
            if ("medium".equals(optString)) {
                i = 60;
            }
            ShakeUtils.vibrate(this.activityContext, i);
            callbackSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(false);
        }
    }
}
