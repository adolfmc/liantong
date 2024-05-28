package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/getLanguageInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetLanguageInfoJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        String language = LanguageUtil.getInstance().getLanguage();
        if (TextUtils.isEmpty(language)) {
            language = LanguageUtil.CHN_CN;
        }
        return callbackSuccessSync(language);
    }
}
