package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getHiddenWebViewUrl")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetHiddenWebUrlPlugin extends BaseJSPlugin {
    private static final String TAG = "GetHiddenWebUrlPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", RtcWebInstance.getInstance().getUrl());
        callbackSuccess(jSONObject);
    }
}
