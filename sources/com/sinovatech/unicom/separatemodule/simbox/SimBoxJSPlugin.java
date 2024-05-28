package com.sinovatech.unicom.separatemodule.simbox;

import android.content.Context;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

@Route(path = "/MsJSPlugin/simBox")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SimBoxJSPlugin extends BaseJSPlugin {
    String key = "";
    SimBoxManager simBoxManager;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        PermissionDialog.show("sim保险箱:需要获取您的sim卡读取权限，用于安全验证，以便于您在APP内使用5G SIM保险箱卡功能，对于您授权的信息我们竭尽提供安全保护措施。");
        new RxPermissions(this.activityContext).request("android.permission.READ_PHONE_STATE").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.simbox.-$$Lambda$SimBoxJSPlugin$06GY10IWcwAcB6UiJCYIej3zhJY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SimBoxJSPlugin.lambda$onExec$0(SimBoxJSPlugin.this, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.simbox.-$$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public static /* synthetic */ void lambda$onExec$0(SimBoxJSPlugin simBoxJSPlugin, Boolean bool) throws Exception {
        PermissionDialog.dimissDialog();
        if (bool.booleanValue()) {
            if (simBoxJSPlugin.simBoxManager == null) {
                simBoxJSPlugin.simBoxManager = new SimBoxManager(simBoxJSPlugin);
                TYCJBoxManager.getInstance().collectClickSdk(simBoxJSPlugin.activityContext, "S2ndpage1214", App.webviewTitle, "SIM卡保险箱", "", "com.sinovatech.unicom.separatemodule.simbox", "1");
            }
            simBoxJSPlugin.simBoxManager.onExec();
            return;
        }
        UIUtils.toastCenter("未授权读取手机状态权限");
    }
}
