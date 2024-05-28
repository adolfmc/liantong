package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard;

import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/hideKeyboard")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HideKeyboardJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (this.activityContext != null) {
            Intent intent = new Intent(this.activityContext, KeyboardActivity.class);
            intent.putExtra("action", "hide");
            this.activityContext.startActivity(intent);
        }
    }
}
