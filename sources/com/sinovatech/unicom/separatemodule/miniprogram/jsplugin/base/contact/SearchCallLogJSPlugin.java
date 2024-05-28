package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.Context;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

@Route(path = "/MsJSPlugin/searchCallLog")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchCallLogJSPlugin extends BaseJSPlugin {
    private String searchCallNumber;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() {
        try {
            if (!TextUtils.isEmpty(getCurrentCumpAppId()) && getCurrentCumpAppId().contains("edop_unicom")) {
                this.searchCallNumber = this.parameterJO.getString("callNumber");
                MsLogUtil.m7980d("收到的手机号参数：" + this.searchCallNumber);
                this.searchCallNumber = ContactManager.filterPhoneNumber(this.searchCallNumber);
                MsLogUtil.m7980d("处理之后手机号参数：" + this.searchCallNumber);
                PermissionDialog.show("为您提供通话详单查询服务，需要获取您的通话记录信息，对于您授权提供的信息，我们会竭尽全力提供安全保护。");
                new RxPermissions(this.activityContext).request("android.permission.READ_CALL_LOG").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchCallLogJSPlugin.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (bool.booleanValue()) {
                            if (!TextUtils.isEmpty(SearchCallLogJSPlugin.this.searchCallNumber)) {
                                SearchCallLogJSPlugin.this.callbackSuccess(ContactManager.searchCallLogs(SearchCallLogJSPlugin.this.activityContext, SearchCallLogJSPlugin.this.searchCallNumber));
                                return;
                            }
                            SearchCallLogJSPlugin.this.callbackSuccess(ContactManager.searchAllCallLogs(SearchCallLogJSPlugin.this.activityContext));
                            return;
                        }
                        SearchCallLogJSPlugin.this.callbackFail("11", "没有通话记录访问权限");
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchCallLogJSPlugin.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) {
                        PermissionDialog.dimissDialog();
                        SearchCallLogJSPlugin searchCallLogJSPlugin = SearchCallLogJSPlugin.this;
                        searchCallLogJSPlugin.callbackFail("10", "程序错误:" + th.toString());
                    }
                });
            } else {
                callbackFail("10", "程序错误:只允许联通小程序模式使用");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误:" + e.getMessage());
        }
    }
}
