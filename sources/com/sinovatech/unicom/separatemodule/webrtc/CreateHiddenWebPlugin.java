package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/createHiddenWebView")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CreateHiddenWebPlugin extends BaseJSPlugin {
    private static final String TAG = "CreateHiddenWebPlugin";
    private PBWebView webView;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("action");
        Log.d(TAG, "========action=" + optString);
        String optString2 = this.parameterJO.optString("url");
        RtcWebInstance.getInstance().setContext(this.activityContext);
        RtcWebInstance.getInstance().setUrl(optString2);
    }
}
