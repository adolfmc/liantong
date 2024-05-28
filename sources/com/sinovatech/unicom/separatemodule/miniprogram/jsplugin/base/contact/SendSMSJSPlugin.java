package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/sendSMS")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SendSMSJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            final String join = this.parameterJO.getJSONArray("phoneNumber").join(",");
            final String optString = this.parameterJO.optString("smsBody", "");
            PermissionDialog.show("为了第一时间将关键信息通知到您，需要获取您的短信读/写权限，对于您授权的信息我们竭尽提供安全保护");
            new RxPermissions(this.activityContext).request("android.permission.SEND_SMS").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SendSMSJSPlugin.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + join));
                        intent.putExtra("sms_body", optString);
                        SendSMSJSPlugin.this.activityContext.startActivity(intent);
                        SendSMSJSPlugin.this.callbackSuccess(new JSONObject());
                        return;
                    }
                    SendSMSJSPlugin.this.callbackFail("11", "没有发送短信权限");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SendSMSJSPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                    SendSMSJSPlugin sendSMSJSPlugin = SendSMSJSPlugin.this;
                    sendSMSJSPlugin.callbackFail("10", "程序错误：" + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
