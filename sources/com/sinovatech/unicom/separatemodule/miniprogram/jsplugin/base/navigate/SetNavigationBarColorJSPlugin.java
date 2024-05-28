package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import android.graphics.Color;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpTheme;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/setNavigationBarColor")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SetNavigationBarColorJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if ((this.webFragment instanceof WebFragment) && this.parameterJO != null) {
                String optString = this.parameterJO.optString("backgroundColor", "#99EAEAEA");
                String optString2 = this.parameterJO.optString("lineColor", "#FFEAEAEA");
                final String optString3 = this.parameterJO.optString("iconTheme", "black");
                final int parseColor = Color.parseColor(optString);
                final int parseColor2 = Color.parseColor(optString2);
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.SetNavigationBarColorJSPlugin.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SetNavigationBarColorJSPlugin.this.webFragment instanceof WebFragment) {
                            CumpTheme cumpTheme = new CumpTheme();
                            cumpTheme.setNavBarBackgroundColor(parseColor);
                            cumpTheme.setNavBarLineColor(parseColor2);
                            cumpTheme.setNavBarIconTheme(optString3);
                            ((WebFragment) SetNavigationBarColorJSPlugin.this.webFragment).setNavigationBarColor(cumpTheme);
                        }
                    }
                });
            }
            callbackSuccess(new JSONObject());
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
