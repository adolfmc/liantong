package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/getClipboardData")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetClipboardDataJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        String str = "";
        try {
            ClipboardManager clipboardManager = (ClipboardManager) this.activityContext.getSystemService("clipboard");
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            if (primaryClip != null && primaryClip.getItemCount() > 0) {
                str = primaryClip.getItemAt(0).getText().toString();
                clipboardManager.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync(str);
    }
}
