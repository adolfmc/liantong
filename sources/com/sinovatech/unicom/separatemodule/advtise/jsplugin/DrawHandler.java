package com.sinovatech.unicom.separatemodule.advtise.jsplugin;

import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DrawHandler extends AbstractJsHandler {
    public DrawHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, BaseJSPlugin baseJSPlugin) {
        super(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.jsplugin.AbstractJsHandler
    public void exec() {
        this.adInterface.loadDraw(new IAdInterface.IDrawCallBack() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.DrawHandler.1
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IDrawCallBack
            public void onCallBack(View view) {
            }
        });
    }
}
