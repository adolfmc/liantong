package com.sinovatech.unicom.separatemodule.collect;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/unicomGetTransId")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomGetTransIdJSPlugin extends BaseJSPlugin {
    private final String TAG = "UnicomGetTransIdJSPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            JSONObject jSONObject = this.parameterJO;
            String optString = jSONObject.optString("action");
            MsLogUtil.m7979d("UnicomGetTransIdJSPlugin", optString);
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            char c = 65535;
            int hashCode = optString.hashCode();
            if (hashCode != -30200403) {
                if (hashCode != 1906604833) {
                    if (hashCode == 1984473568 && optString.equals("setCall")) {
                        c = 2;
                    }
                } else if (optString.equals("setTransId")) {
                    c = 1;
                }
            } else if (optString.equals("getTransId")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    callH5(UnicomCollectManager.getInstance().getTransId());
                    return;
                case 1:
                    UnicomCollectManager.getInstance().setTransId(jSONObject.optString("id"));
                    return;
                case 2:
                    UnicomCollectManager.getInstance().setTransId("");
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callH5(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (TextUtils.isEmpty(str)) {
                callbackFail(jSONObject);
            } else {
                jSONObject.put("id", str);
                callbackSuccess(jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
