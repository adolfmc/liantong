package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getCrossDomainStorageSync")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetCrossDomainStorageJSPlugin extends StorageJSPlugin {
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        Object obj = "";
        try {
            String str = JSStorageBox.get(getDefaultCrossDomainID(), this.parameterJO.getString("key"));
            if (!TextUtils.isEmpty(str)) {
                obj = new JSONObject(str).get("value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(obj);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject jSONObject = new JSONObject();
        try {
            String string = this.parameterJO.getString("key");
            jSONObject.put("status", "fail");
            jSONObject.put("key", string);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put("msg", "key为空");
                callbackFail(jSONObject);
                return;
            }
            String str = JSStorageBox.get(getDefaultCrossDomainID(), string);
            if (!TextUtils.isEmpty(str)) {
                callbackSuccess(new JSONObject(str).get("value"));
            } else {
                callbackSuccess("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            callbackFail(jSONObject);
        }
    }
}
