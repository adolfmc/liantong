package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.eventbus.JiePinEvent;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/screenshots")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenshotsJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            String optString2 = this.parameterJO.optJSONObject("params").optString("allowAcreenshots");
            if (TextUtils.equals("setScreenshots", optString)) {
                JiePinEvent jiePinEvent = new JiePinEvent(EventBusUtils.EVENT_JIEPIN);
                jiePinEvent.setSecure(TextUtils.equals("1", optString2));
                EventBusUtils.post(jiePinEvent);
                return callbackSuccessSync("操作成功");
            }
            return callbackFailSync("未定义此方法");
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
            return callbackFailSync(e.getMessage());
        }
    }
}
