package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image;

import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.hub.media.gallery.MediaStoreActivity;
import com.sinovatech.unicom.hub.media.utils.MediaStoreUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/chooseMedia")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseMediaJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        new RxPermissions(this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseMediaJSPlugin.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    int i = ChooseMediaJSPlugin.this.parameterJO.getInt("count");
                    String string = ChooseMediaJSPlugin.this.parameterJO.getString("mediaType");
                    int i2 = ChooseMediaJSPlugin.this.parameterJO.getInt("compressSize");
                    String string2 = ChooseMediaJSPlugin.this.parameterJO.getString("compress");
                    String string3 = ChooseMediaJSPlugin.this.parameterJO.getString("crop");
                    Intent intent = new Intent(ChooseMediaJSPlugin.this.activityContext, MediaStoreActivity.class);
                    intent.putExtra("count", i);
                    intent.putExtra("mediaStoreType", string);
                    intent.putExtra("crop", string3);
                    intent.putExtra("compress", string2);
                    intent.putExtra("compressSize", i2);
                    new AvoidOnResult(ChooseMediaJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseMediaJSPlugin.1.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i3, Intent intent2) {
                            if (i3 == -1) {
                                try {
                                    if (intent2.getStringExtra("result") != null) {
                                        MsLogUtil.m7979d("CESHI", "选择媒体文件JSPlugin接收返回：" + intent2.getStringExtra("result"));
                                        ChooseMediaJSPlugin.this.callbackSuccess(new JSONObject(intent2.getStringExtra("result")));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ChooseMediaJSPlugin chooseMediaJSPlugin = ChooseMediaJSPlugin.this;
                                    chooseMediaJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                                    return;
                                }
                            }
                            ChooseMediaJSPlugin.this.callbackFail("11", "用户主动取消并返回");
                        }
                    });
                    ChooseMediaJSPlugin.this.webFragment.addLifeListener("/MsJSPlugin/chooseMedia", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseMediaJSPlugin.1.2
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                        public void onDestory() {
                            MediaStoreUtils.clearThumbnailCache(ChooseMediaJSPlugin.this.activityContext, "imagethumbnail");
                        }
                    });
                    return;
                }
                ChooseMediaJSPlugin.this.callbackFail("12", "用户没有访问相册/文件系统的权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseMediaJSPlugin.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ChooseMediaJSPlugin chooseMediaJSPlugin = ChooseMediaJSPlugin.this;
                chooseMediaJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
            }
        });
    }
}
