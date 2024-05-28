package com.sinovatech.unicom.basic.view.decklayout;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.view.HomeWebView;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/homeWebLanjie")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeWebLanjiePlugin extends BaseJSPlugin {
    public static final String TAG = "chooseCityJsPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        callbackSuccess("0");
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        boolean optBoolean = this.parameterJO.optBoolean("isOpenDialog");
        if (this.f18589wv instanceof HomeWebView) {
            ((HomeWebView) this.f18589wv).setOpenDialog(optBoolean);
        }
        return callbackSuccessSync("0");
    }
}
