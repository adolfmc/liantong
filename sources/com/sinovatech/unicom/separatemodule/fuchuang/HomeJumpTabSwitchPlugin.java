package com.sinovatech.unicom.separatemodule.fuchuang;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.eventbus.HomeJumpTabEvent;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/homeJumpTabSwitch")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeJumpTabSwitchPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        exe();
        callbackSuccess("0");
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        exe();
        return callbackSuccessSync("0");
    }

    private String exe() {
        String optString = this.parameterJO.optString("navCode");
        JSONObject optJSONObject = this.parameterJO.optJSONObject("parmams");
        if (this.activityContext instanceof MainActivity) {
            HomeJumpTabEvent homeJumpTabEvent = new HomeJumpTabEvent(0);
            homeJumpTabEvent.setNavCode(optString);
            homeJumpTabEvent.setJsonObject(optJSONObject);
            EventBusUtils.post(homeJumpTabEvent);
            return "";
        }
        return "";
    }
}
