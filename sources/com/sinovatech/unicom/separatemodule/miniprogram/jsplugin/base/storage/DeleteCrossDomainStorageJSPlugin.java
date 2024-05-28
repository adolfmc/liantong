package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/deleteCrossDomainStorageSync")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DeleteCrossDomainStorageJSPlugin extends StorageJSPlugin {
    private boolean isSuccess = false;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject deleteStorage = deleteStorage();
        if (this.isSuccess) {
            deleteStorage.put("status", "success");
            return callbackSuccessSync(deleteStorage);
        }
        deleteStorage.put("status", "fail");
        return callbackFailSync(deleteStorage);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject deleteStorage = deleteStorage();
        if (this.isSuccess) {
            deleteStorage.put("status", "success");
            callbackSuccess(deleteStorage);
            return;
        }
        deleteStorage.put("status", "fail");
        callbackFail(deleteStorage);
    }

    private JSONObject deleteStorage() throws Exception {
        this.isSuccess = false;
        JSONObject jSONObject = new JSONObject();
        try {
            String string = this.parameterJO.getString("key");
            jSONObject.put("key", string);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put("msg", "key为空");
                return jSONObject;
            }
            JSStorageBox.delete(getDefaultCrossDomainID(), string);
            this.isSuccess = true;
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            return jSONObject;
        }
    }
}
