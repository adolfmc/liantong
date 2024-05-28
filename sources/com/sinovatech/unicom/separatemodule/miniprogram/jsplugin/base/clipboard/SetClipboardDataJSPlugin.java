package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.clipboard;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/setClipboardData")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SetClipboardDataJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            String string = this.parameterJO.getString("data");
            if (!TextUtils.isEmpty(string)) {
                ((ClipboardManager) this.activityContext.getSystemService("clipboard")).setText(string);
                UIUtils.toast("内容已复制");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callbackSuccessSync("");
    }
}
