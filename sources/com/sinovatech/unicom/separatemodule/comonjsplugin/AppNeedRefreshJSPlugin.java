package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/AppNeedRefresh")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AppNeedRefreshJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            App.appNeedRefreshList.add(this.parameterJO.optString("page"));
            callbackSuccess("配置成功");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("配置失败");
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return callbackFailSync("不支持同步方法");
    }
}
