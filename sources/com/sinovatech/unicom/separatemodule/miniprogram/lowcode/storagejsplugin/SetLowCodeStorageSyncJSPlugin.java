package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/setLowCodeStorageSync")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SetLowCodeStorageSyncJSPlugin extends BaseJSPlugin {
    private boolean isSuccess = false;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject storage = setStorage();
        if (this.isSuccess) {
            storage.put("status", "success");
            return callbackSuccessSync(storage);
        }
        storage.put("status", "fail");
        return callbackFailSync(storage);
    }

    private JSONObject setStorage() throws Exception {
        this.isSuccess = false;
        JSONObject jSONObject = new JSONObject();
        try {
            String string = this.parameterJO.getString("key");
            jSONObject.put("key", string);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put("msg", "key为空");
                return jSONObject;
            } else if (this.webFragment.currentCumpAppEntity != null && "2".equals(this.webFragment.currentCumpAppEntity.getPublishMethod()) && !TextUtils.isEmpty(this.webFragment.currentCumpAppEntity.getHomePageKey())) {
                String appId = this.webFragment.currentCumpAppEntity.getAppId();
                JSONObject jSONObject2 = this.parameterJO;
                LowcodeJSStorageBox.put(appId, string, !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                this.isSuccess = true;
                return jSONObject;
            } else {
                jSONObject.put("msg", "只允许低代码小程序调用");
                return jSONObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            return jSONObject;
        }
    }
}
