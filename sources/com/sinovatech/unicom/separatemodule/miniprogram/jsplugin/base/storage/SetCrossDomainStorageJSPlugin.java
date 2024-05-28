package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/setCrossDomainStorageSync")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SetCrossDomainStorageJSPlugin extends StorageJSPlugin {
    private boolean isSuccess = false;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        super.init(context);
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

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.StorageJSPlugin, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject storage = setStorage();
        if (this.isSuccess) {
            storage.put("status", "success");
            callbackSuccess(storage);
            return;
        }
        storage.put("status", "fail");
        callbackFail(storage);
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
            }
            JSONObject jSONObject2 = this.parameterJO;
            if ((!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).getBytes().length / 1024 > 1024) {
                jSONObject.put("msg", "单条数据不能超过1M");
                return jSONObject;
            }
            JSONObject jSONObject3 = this.parameterJO;
            if (((!(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3)).getBytes().length / 1024) + JSStorageBox.querySizeForAppId(getDefaultCrossDomainID()) > 10240) {
                jSONObject.put("msg", "允许缓存的最大数据量不能超过10M");
                return jSONObject;
            }
            String defaultCrossDomainID = getDefaultCrossDomainID();
            JSONObject jSONObject4 = this.parameterJO;
            JSStorageBox.put(defaultCrossDomainID, string, !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
            UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
            JSONObject jSONObject5 = this.parameterJO;
            unicomCollectManager.refreshSessionId(string, !(jSONObject5 instanceof JSONObject) ? jSONObject5.toString() : NBSJSONObjectInstrumentation.toString(jSONObject5));
            this.isSuccess = true;
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("msg", e.getMessage());
            return jSONObject;
        }
    }
}
