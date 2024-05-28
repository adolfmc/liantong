package com.sinovatech.unicom.separatemodule.esim;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getEsimSignData")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetEsimSignDataJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String[] esimParams = EsimUtil.getEsimParams(this.activityContext, this.f18589wv, "", "", "", "");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("arg1", esimParams[0]);
            jSONObject.put("arg2", esimParams[1]);
            callbackSuccess(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
