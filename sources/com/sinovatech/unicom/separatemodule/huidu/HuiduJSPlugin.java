package com.sinovatech.unicom.separatemodule.huidu;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/huidu")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuiduJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            if ("getHeaders".equals(this.parameterJO.optString("type"))) {
                String str = ManagerHuiDu.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("HuiduJSPlugin ");
                JSONObject jSONObject = this.parameterJO;
                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                MsLogUtil.m7979d(str, sb.toString());
                String optString = this.parameterJO.optString("url");
                String huiduHeader = ManagerHuiDu.getInstance().getHuiduHeader(Uri.parse(optString).getHost());
                if (TextUtils.isEmpty(huiduHeader)) {
                    return "";
                }
                String str2 = ManagerHuiDu.TAG;
                MsLogUtil.m7979d(str2, "HuiduJSPlugin调用命中域名白名单，返回灰度header header=" + huiduHeader + " url=" + optString);
                return callbackSuccessSync(huiduHeader);
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            String str3 = ManagerHuiDu.TAG;
            MsLogUtil.m7979d(str3, "HuiduJSPlugin错误：" + e.getMessage());
            return "";
        }
    }
}
