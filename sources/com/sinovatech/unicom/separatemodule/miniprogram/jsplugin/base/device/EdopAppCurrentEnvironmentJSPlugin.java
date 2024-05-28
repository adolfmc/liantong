package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.fragment.HomeWebFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/edopAppCurrentEnvironment")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EdopAppCurrentEnvironmentJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject jSONObject = new JSONObject();
        if (this.webFragment instanceof WebFragment) {
            jSONObject.put("status", "1");
            jSONObject.put("msg", "独立模式，小程序铺满整个页面");
        } else if (this.webFragment instanceof HomeWebFragment) {
            jSONObject.put("status", "2");
            jSONObject.put("msg", "嵌入模式，小程序和其他业务共存于一个页面");
        }
        return callbackSuccessSync(jSONObject);
    }
}
