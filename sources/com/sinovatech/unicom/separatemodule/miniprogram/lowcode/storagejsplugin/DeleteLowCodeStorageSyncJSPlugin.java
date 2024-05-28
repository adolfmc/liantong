package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.JSStorageBox;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/deleteLowCodeStorageSync")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DeleteLowCodeStorageSyncJSPlugin extends BaseJSPlugin {
    private boolean isSuccess = false;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
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
            JSStorageBox.delete(this.webFragment.currentCumpAppEntity.getAppId(), string);
            this.isSuccess = true;
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            return jSONObject;
        }
    }
}
