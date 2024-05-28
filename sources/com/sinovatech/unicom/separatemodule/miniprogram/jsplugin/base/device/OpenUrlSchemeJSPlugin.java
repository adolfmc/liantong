package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/openUrlScheme")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OpenUrlSchemeJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("scheme");
            String whiteList = new ConfigManager(this.activityContext).getWhiteList();
            boolean z = false;
            String[] strArr = new String[0];
            if (!TextUtils.isEmpty(whiteList)) {
                strArr = whiteList.split(",");
            }
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (string.startsWith(strArr[i])) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(string));
                    this.activityContext.startActivity(intent);
                    callbackSuccess("13", "拉起成功", null);
                    return;
                } catch (ActivityNotFoundException unused) {
                    callbackSuccess("12", "拉起失败，手机没有安装该app", null);
                    return;
                }
            }
            callbackSuccess("11", "传入的urlScheme不在白名单，请联系中国联通app开发人员", null);
        } catch (Exception e) {
            callbackFail("10", "程序异常 " + e.getMessage());
        }
    }
}
