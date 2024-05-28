package com.sinovatech.unicom.separatemodule.miniprogram.cumphome;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getEdopAppVersionInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetEdopAppVersionInfoJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.webFragment != null && this.webFragment.currentCumpAppEntity != null) {
                jSONObject.put("publishType", this.webFragment.currentCumpAppEntity.getServerPublishType());
                jSONObject.put("officialVersionNum", this.webFragment.currentCumpAppEntity.getOfficialVersionNum());
                jSONObject.put("trialVersionNum", this.webFragment.currentCumpAppEntity.getTrialVersionNum());
                jSONObject.put("timestamp", this.webFragment.currentCumpAppEntity.getTimestamp());
                jSONObject.put("officialVersion", this.webFragment.currentCumpAppEntity.getOfficialVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(jSONObject);
    }
}
