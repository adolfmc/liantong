package com.sinovatech.unicom.separatemodule.wuzhangai;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;

@Route(path = "/MsJSPlugin/UIAccessibilityManager")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UIAccessibilityManagerJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if ("sayText".equals(this.parameterJO.getString("type"))) {
                String optString = this.parameterJO.optString("msg");
                if (UnicomAccessibilityManager.getInstance().isOpen()) {
                    UnicomAccessibilityManager.getInstance().sayText(optString);
                    callbackSuccess("success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            String string = this.parameterJO.getString("type");
            UnicomAccessibilityManager.getInstance().init(this.activityContext);
            UnicomAccessibilityManager.getInstance().setTalkBackListener(new TalkBackListener() { // from class: com.sinovatech.unicom.separatemodule.wuzhangai.UIAccessibilityManagerJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.wuzhangai.TalkBackListener
                public void onTalkBackStatusChanged(boolean z) {
                    try {
                        UIAccessibilityManagerJSPlugin.this.webFragment.postEventToJS(MsJSEvent.UIAccessibilityManagerStatusChange, Boolean.valueOf(z));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if ("getStatus".equals(string)) {
                return callbackSuccessSync(Boolean.valueOf(UnicomAccessibilityManager.getInstance().isOpen()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(false);
    }
}
