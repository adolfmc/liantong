package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/navigateParams")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NavigateParamsJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        Map<String, Object> map = this.webFragment.navigateParams;
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        }
        callbackSuccess(jSONObject);
    }
}
