package com.sinovatech.unicom.separatemodule.login.fengkong;

import android.annotation.SuppressLint;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/loginResultToken")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginResultTokenJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    @SuppressLint({"CheckResult"})
    public void onExec() throws Exception {
        exec(false);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return exec(true);
    }

    private String exec(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("前端传入--");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7979d("setStrongLoginResult", sb.toString());
        if (this.activityContext instanceof WebDetailActivity) {
            ((WebDetailActivity) this.activityContext).setStrongLoginResult(this.parameterJO);
        }
        if (!z) {
            callbackSuccess("设置成功");
        }
        return callbackSuccessSync("设置成功");
    }
}
