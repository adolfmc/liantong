package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getStorageInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetStorageInfoJSPlugin extends StorageJSPlugin {
    private boolean isSuccess = false;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        JSONObject storageInfo = getStorageInfo();
        if (this.isSuccess) {
            storageInfo.put("status", "success");
            return callbackSuccessSync(storageInfo);
        }
        storageInfo.put("status", "fail");
        return callbackFailSync(storageInfo);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject storageInfo = getStorageInfo();
        if (this.isSuccess) {
            storageInfo.put("status", "success");
            callbackSuccess(storageInfo);
            return;
        }
        storageInfo.put("status", "fail");
        callbackFail(storageInfo);
    }

    private JSONObject getStorageInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(getStoragePrefixId())) {
                List<String> querykeysForAppId = JSStorageBox.querykeysForAppId(getStoragePrefixId());
                long querySizeForAppId = JSStorageBox.querySizeForAppId(getStoragePrefixId());
                this.isSuccess = true;
                jSONObject.put("keys", querykeysForAppId);
                jSONObject.put("currentSize", querySizeForAppId);
                jSONObject.put("limitSize", 10240L);
            } else {
                this.isSuccess = false;
                jSONObject.put("msg", "找不到应用信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                jSONObject.put("msg", "程序错误:" + e.getMessage());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }
}
