package com.sinovatech.unicom.separatemodule.advtise.jsplugin;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.utils.AdConfigParser;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/UnicomAdJSPlugin")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomAdJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        AdConfigEntity parser = AdConfigParser.parser(this.parameterJO);
        AdFactory.getAdHandler((AppCompatActivity) this.activityContext, parser, AdFactory.getAd((AppCompatActivity) this.activityContext, parser), this).exec();
    }
}
