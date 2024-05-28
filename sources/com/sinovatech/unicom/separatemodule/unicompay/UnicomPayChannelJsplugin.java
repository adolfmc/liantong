package com.sinovatech.unicom.separatemodule.unicompay;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/unicomPayChannel")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomPayChannelJsplugin extends BaseJSPlugin {
    private static String TAG = "UnicomPayChannelJsplugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                JSONObject jSONObject = this.parameterJO;
                if (!(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).isEmpty()) {
                    String optString = this.parameterJO.optString("action");
                    String optString2 = this.parameterJO.optString("jsonData");
                    String str = TAG;
                    MsLogUtil.m7979d(str, "onExec: " + optString + "===" + optString2);
                    UnicomPaySDK.getInstance().notify(this.activityContext, optString, optString2, new DataCallback() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayChannelJsplugin.1
                        @Override // com.unicom.pay.common.callback.DataCallback
                        public void onResult(String str2) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("data", str2);
                                UnicomPayChannelJsplugin.this.callbackSuccess(jSONObject2);
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m7979d(UnicomPayChannelJsplugin.TAG, e.getMessage());
                            }
                        }
                    });
                    return;
                }
            }
            callbackFail("11", "参数错误");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
        }
    }
}
