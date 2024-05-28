package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.chooseCity;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/chooseCityResult")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseCityResultJsPlugin extends BaseJSPlugin {
    public static final String TAG = "chooseCityResult";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        String optString;
        String optString2;
        String optString3;
        String optString4;
        if (this.parameterJO == null) {
            return callbackSuccessSync("1");
        }
        try {
            optString = this.parameterJO.optString("provinceCode");
            optString2 = this.parameterJO.optString("provinceName");
            optString3 = this.parameterJO.optString("cityName");
            optString4 = this.parameterJO.optString("cityCode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4)) {
            UserManager.getInstance().saveLocateProvinceCode(optString);
            UserManager.getInstance().saveLocateCityCode(optString4);
            UserManager.getInstance().saveLocateCityName(optString3);
            UserManager.getInstance().saveUserLocateCityName(optString3);
            UserManager.getInstance().saveUserLocateCityTime(0L);
            return callbackSuccessSync("0");
        }
        return callbackSuccessSync("1");
    }
}
