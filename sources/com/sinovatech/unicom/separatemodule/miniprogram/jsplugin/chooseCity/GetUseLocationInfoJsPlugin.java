package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.chooseCity;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getUseLocationInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetUseLocationInfoJsPlugin extends BaseJSPlugin {
    public static final String TAG = "chooseCityJsPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            callbackSuccess(getJson());
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return callbackSuccessSync(getJson());
    }

    private JSONObject getJson() {
        JSONObject jSONObject = new JSONObject();
        String locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
        String locateCityCode = UserManager.getInstance().getLocateCityCode();
        try {
            jSONObject.put("provinceCode_1", locateProvinceCode);
            jSONObject.put("cityCode_1", locateCityCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
