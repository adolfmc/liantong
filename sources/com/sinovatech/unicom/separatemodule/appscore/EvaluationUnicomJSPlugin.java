package com.sinovatech.unicom.separatemodule.appscore;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/evaluationUnicom")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EvaluationUnicomJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("type");
        String optString2 = this.parameterJO.optString("number");
        String optString3 = this.parameterJO.optString("sourceType");
        if ("05".equals(optString)) {
            PingjiaDialogManager.jumpAppMarket(this.activityContext);
        } else if ("06".equals(optString)) {
            String str = "";
            char c = 65535;
            switch (optString3.hashCode()) {
                case 48:
                    if (optString3.equals("0")) {
                        c = 0;
                        break;
                    }
                    break;
                case 49:
                    if (optString3.equals("1")) {
                        c = 1;
                        break;
                    }
                    break;
                case 50:
                    if (optString3.equals("2")) {
                        c = 2;
                        break;
                    }
                    break;
                case 51:
                    if (optString3.equals("3")) {
                        c = 3;
                        break;
                    }
                    break;
                case 52:
                    if (optString3.equals("4")) {
                        c = 4;
                        break;
                    }
                    break;
                case 53:
                    if (optString3.equals("5")) {
                        c = 5;
                        break;
                    }
                    break;
                case 54:
                    if (optString3.equals("6")) {
                        c = 6;
                        break;
                    }
                    break;
                case 55:
                    if (optString3.equals("7")) {
                        c = 7;
                        break;
                    }
                    break;
                case 56:
                    if (optString3.equals("8")) {
                        c = '\b';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    str = "设置";
                    break;
                case 1:
                    str = "剩余话费";
                    break;
                case 2:
                    str = "快讯";
                    break;
                case 3:
                    str = "余量查询";
                    break;
                case 4:
                    str = "充值卡充值";
                    break;
                case 5:
                    str = "存费送费";
                    break;
                case 6:
                    str = "备用1";
                    break;
                case 7:
                    str = "备用2";
                    break;
                case '\b':
                    str = "备用3";
                    break;
            }
            PingjiaDialogManager.showGoShiChangDialog(this.activityContext, str);
        } else {
            PingjiaDialogManager.show(this.activityContext, optString, optString2, optString3);
        }
    }
}
