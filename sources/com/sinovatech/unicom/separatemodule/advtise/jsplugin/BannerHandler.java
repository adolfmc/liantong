package com.sinovatech.unicom.separatemodule.advtise.jsplugin;

import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BannerHandler extends AbstractJsHandler {
    public BannerHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, BaseJSPlugin baseJSPlugin) {
        super(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
    }

    @Override // com.sinovatech.unicom.separatemodule.advtise.jsplugin.AbstractJsHandler
    public void exec() {
        this.adInterface.loadBanner(new IAdInterface.IBannerAdCallBack() { // from class: com.sinovatech.unicom.separatemodule.advtise.jsplugin.BannerHandler.1
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
            public void onClose() {
            }

            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
            public void onResult(int i, View view) {
            }
        });
    }
}
