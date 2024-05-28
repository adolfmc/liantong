package com.sinovatech.unicom.separatemodule.gdt;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/GDTInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GDTInfoJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("type");
        if (((optString.hashCode() == 1224362472 && optString.equals("GDTGuiYinInfo")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        try {
            callbackSuccess(App.getSharePreferenceUtil().getString("guangdiantongguiyinxinxi"));
        } catch (Exception unused) {
            callbackFail("获取信息失败");
        }
    }
}
