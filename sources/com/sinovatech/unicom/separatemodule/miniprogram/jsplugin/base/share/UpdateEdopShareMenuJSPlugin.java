package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin;

@Route(path = "/MsJSPlugin/updateEdopShareMenu")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UpdateEdopShareMenuJSPlugin extends StorageJSPlugin {
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        this.webFragment.currentEdopShareConfig = this.parameterJO.optJSONObject("shareConfig");
    }
}
