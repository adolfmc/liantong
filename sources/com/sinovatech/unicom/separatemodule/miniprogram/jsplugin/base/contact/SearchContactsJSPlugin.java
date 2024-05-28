package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.Context;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

@Route(path = "/MsJSPlugin/searchContacts")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchContactsJSPlugin extends BaseJSPlugin {
    private String permissionContent;
    private String searchPhoneNumber;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (!TextUtils.isEmpty(getCurrentCumpAppId()) && getCurrentCumpAppId().contains("edop_unicom")) {
                this.searchPhoneNumber = this.parameterJO.getString("phoneNumber");
                this.permissionContent = this.parameterJO.optString("permissionContent", "");
                MsLogUtil.m7980d("收到的手机号参数：" + this.searchPhoneNumber);
                this.searchPhoneNumber = ContactManager.filterPhoneNumber(this.searchPhoneNumber);
                MsLogUtil.m7980d("处理之后手机号参数：" + this.searchPhoneNumber);
                PermissionDialog.show(TextUtils.isEmpty(this.permissionContent) ? "为了给您带来更好的服务，需要获取您通讯录权限，用于与您的通讯录好友进行互动，或对通讯录好友进行交费充值，对于您授权的信息，我们竭尽提供安全保护。" : this.permissionContent);
                new RxPermissions(this.activityContext).request("android.permission.READ_CONTACTS").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchContactsJSPlugin.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (bool.booleanValue()) {
                            if (!TextUtils.isEmpty(SearchContactsJSPlugin.this.searchPhoneNumber)) {
                                SearchContactsJSPlugin.this.callbackSuccess(ContactManager.searchContacts(SearchContactsJSPlugin.this.activityContext, SearchContactsJSPlugin.this.searchPhoneNumber));
                                return;
                            }
                            SearchContactsJSPlugin.this.callbackSuccess(ContactManager.searchAllContacts(SearchContactsJSPlugin.this.activityContext));
                            return;
                        }
                        SearchContactsJSPlugin.this.callbackFail("11", "没有通讯录访问权限");
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchContactsJSPlugin.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        PermissionDialog.dimissDialog();
                        SearchContactsJSPlugin searchContactsJSPlugin = SearchContactsJSPlugin.this;
                        searchContactsJSPlugin.callbackFail("10", "程序错误：" + th.toString());
                    }
                });
                return;
            }
            callbackFail("10", "程序错误:只允许联通小程序模式使用");
        } catch (Exception e) {
            e.printStackTrace();
            PermissionDialog.dimissDialog();
            callbackFail("10", "程序错误:" + e.getMessage());
        }
    }
}
