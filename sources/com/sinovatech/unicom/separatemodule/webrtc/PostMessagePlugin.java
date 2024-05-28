package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;

@Route(path = "/MsJSPlugin/postMessage")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PostMessagePlugin extends BaseJSPlugin {
    private static final String TAG = "PostMessagePlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        this.webFragment.postEventToJS(MsJSEvent.onHiddenWebView, this.parameterJO);
    }
}
