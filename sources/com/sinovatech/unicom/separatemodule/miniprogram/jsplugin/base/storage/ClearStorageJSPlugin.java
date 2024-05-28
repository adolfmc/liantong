package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/clearStorage")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ClearStorageJSPlugin extends StorageJSPlugin {
    private boolean isSuccess = false;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject clearStorage = clearStorage();
        if (this.isSuccess) {
            clearStorage.put("status", "success");
            return callbackSuccessSync(clearStorage);
        }
        clearStorage.put("status", "fail");
        return callbackFailSync(clearStorage);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject clearStorage = clearStorage();
        if (this.isSuccess) {
            clearStorage.put("status", "success");
            callbackSuccess(clearStorage);
            return;
        }
        clearStorage.put("status", "fail");
        callbackFail(clearStorage);
    }

    private JSONObject clearStorage() throws Exception {
        this.isSuccess = false;
        JSONObject jSONObject = new JSONObject();
        try {
            JSStorageBox.clear(getStoragePrefixId());
            this.isSuccess = true;
            jSONObject.put("msg", "清除成功");
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            return jSONObject;
        }
    }
}
