package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getLowCodeStorageSync")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetLowCodeStorageSyncJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        Object obj = "";
        try {
            String str = LowcodeJSStorageBox.get(this.webFragment.currentCumpAppEntity.getAppId(), this.parameterJO.getString("key"));
            if (!TextUtils.isEmpty(str)) {
                obj = new JSONObject(str).get("value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(obj);
    }
}
