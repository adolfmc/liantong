package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

@Route(path = "/MsJSPlugin/chooseContact")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseContactJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您通讯录权限，用于与您的通讯录好友进行互动，或对通讯录好友进行交费充值，对于您授权的信息，我们竭尽提供安全保护。");
            new RxPermissions(this.activityContext).request("android.permission.READ_CONTACTS").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ChooseContactJSPlugin.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        new AvoidOnResult(ChooseContactJSPlugin.this.activityContext).startForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ChooseContactJSPlugin.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent) {
                                if (i == -1 && intent != null && intent.getData() != null) {
                                    try {
                                        ChooseContactJSPlugin.this.callbackSuccess(ContactManager.chooseContact(ChooseContactJSPlugin.this.activityContext, intent.getData()));
                                        return;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        ChooseContactJSPlugin chooseContactJSPlugin = ChooseContactJSPlugin.this;
                                        chooseContactJSPlugin.callbackFail("10", "程序错误:" + e.getMessage());
                                        return;
                                    }
                                }
                                ChooseContactJSPlugin.this.callbackFail("12", "没有选择联系人");
                            }
                        });
                        return;
                    }
                    ChooseContactJSPlugin.this.callbackFail("11", "没有通讯录访问权限");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ChooseContactJSPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    ChooseContactJSPlugin chooseContactJSPlugin = ChooseContactJSPlugin.this;
                    chooseContactJSPlugin.callbackFail("10", "程序错误:" + th.getMessage());
                    PermissionDialog.dimissDialog();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            PermissionDialog.dimissDialog();
            callbackFail("10", "程序错误:" + e.getMessage());
        }
    }
}
