package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode;

import android.content.Context;
import android.content.Intent;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.capture.CapuActivity2;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/scanCode")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScanCodeJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            new RxPermissions(this.activityContext).request("android.permission.CAMERA").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode.ScanCodeJSPlugin.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        Intent intent = new Intent(ScanCodeJSPlugin.this.activityContext, CapuActivity2.class);
                        intent.putExtra("source", "ScanCodeJSPlugin");
                        new AvoidOnResult(ScanCodeJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode.ScanCodeJSPlugin.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                if (i == -1) {
                                    try {
                                        String stringExtra = intent2.getStringExtra("SCAN_RESULT");
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("result", stringExtra);
                                        ScanCodeJSPlugin.this.callbackSuccess(jSONObject);
                                        return;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        ScanCodeJSPlugin scanCodeJSPlugin = ScanCodeJSPlugin.this;
                                        scanCodeJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                                        return;
                                    }
                                }
                                ScanCodeJSPlugin.this.callbackFail("11", "用户主动取消了或者没有扫描到结果");
                            }
                        });
                        return;
                    }
                    ScanCodeJSPlugin.this.callbackFail("12", "用户没有开启摄像头权限");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode.ScanCodeJSPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                    ScanCodeJSPlugin scanCodeJSPlugin = ScanCodeJSPlugin.this;
                    scanCodeJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }
}
