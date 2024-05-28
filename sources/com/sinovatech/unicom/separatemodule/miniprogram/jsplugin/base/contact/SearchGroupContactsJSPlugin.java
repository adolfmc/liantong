package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.annotation.SuppressLint;
import android.content.Context;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.ContectUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

@Route(path = "/MsJSPlugin/getAddressBookByGroup")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchGroupContactsJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            new RxPermissions(this.activityContext).request("android.permission.READ_CONTACTS").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchGroupContactsJSPlugin.1
                @Override // io.reactivex.functions.Consumer
                @SuppressLint({"CheckResult"})
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        Observable.create(new ObservableOnSubscribe<JSONArray>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchGroupContactsJSPlugin.1.2
                            @Override // io.reactivex.ObservableOnSubscribe
                            public void subscribe(@NotNull ObservableEmitter<JSONArray> observableEmitter) throws Exception {
                                observableEmitter.onNext(new ContectUtil(SearchGroupContactsJSPlugin.this.activityContext).getGroupContect());
                                observableEmitter.onComplete();
                            }
                        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONArray>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchGroupContactsJSPlugin.1.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(JSONArray jSONArray) throws Exception {
                                SearchGroupContactsJSPlugin.this.callbackSuccess(jSONArray);
                            }
                        });
                    } else {
                        SearchGroupContactsJSPlugin.this.callbackFail("11", "没有通讯录访问权限");
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.SearchGroupContactsJSPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                    SearchGroupContactsJSPlugin searchGroupContactsJSPlugin = SearchGroupContactsJSPlugin.this;
                    searchGroupContactsJSPlugin.callbackFail("10", "程序错误：" + th.toString());
                }
            });
        } catch (Exception e) {
            callbackFail("10", "程序错误：" + e.toString());
        }
    }
}
