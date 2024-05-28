package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/chooseFile")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseFileJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String str = "*/*";
            if (this.parameterJO != null) {
                String optString = this.parameterJO.optString("mediaType");
                if ("image".equals(optString)) {
                    str = "image/*";
                } else if ("video".equals(optString)) {
                    str = "video/*";
                } else if ("file".equals(optString)) {
                    str = "*/*";
                }
            }
            chooseFile(str);
            this.webFragment.addLifeListener("/MsJSPlugin/chooseFile", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.ChooseFileJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                public void onDestory() {
                    try {
                        FileTools.delteDirectory(new File(SDCardUtil.getOwnFileUrl("uritofilecache")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }

    private void chooseFile(final String str) throws Exception {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.ChooseFileJSPlugin.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType(str);
                    intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                    intent.addFlags(1);
                    intent.addFlags(2);
                    new AvoidOnResult(ChooseFileJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.ChooseFileJSPlugin.2.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            String str2;
                            String str3;
                            if (i == -1) {
                                try {
                                    if (intent2.getClipData() != null) {
                                        ClipData clipData = intent2.getClipData();
                                        JSONArray jSONArray = new JSONArray();
                                        for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
                                            Uri uri = clipData.getItemAt(i2).getUri();
                                            if (Build.VERSION.SDK_INT >= 19) {
                                                ChooseFileJSPlugin.this.activityContext.getContentResolver().takePersistableUriPermission(uri, intent2.getFlags() & 3);
                                            }
                                            try {
                                                str2 = ChooseFileJSPlugin.this.activityContext.getContentResolver().getType(uri);
                                            } catch (Exception unused) {
                                                str2 = "";
                                            }
                                            Log.d(i2 + "当前选中的文件类型是：", str2);
                                            File uri2File = UriFileUtils.uri2File(ChooseFileJSPlugin.this.activityContext, uri);
                                            JSONObject jSONObject = new JSONObject();
                                            jSONObject.put("filePath", uri2File.getAbsolutePath());
                                            jSONObject.put("fileSize", String.valueOf(uri2File.length()));
                                            jSONObject.put("mimeType", str2);
                                            jSONArray.put(jSONObject);
                                        }
                                        ChooseFileJSPlugin.this.callbackSuccess(jSONArray);
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ChooseFileJSPlugin chooseFileJSPlugin = ChooseFileJSPlugin.this;
                                    chooseFileJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                                    return;
                                }
                            }
                            if (i == -1 && intent2.getData() != null) {
                                Uri data = intent2.getData();
                                if (Build.VERSION.SDK_INT >= 19) {
                                    ChooseFileJSPlugin.this.activityContext.getContentResolver().takePersistableUriPermission(data, intent2.getFlags() & 3);
                                }
                                try {
                                    str3 = ChooseFileJSPlugin.this.activityContext.getContentResolver().getType(data);
                                } catch (Exception unused2) {
                                    str3 = "";
                                }
                                Log.d("当前选中的文件类型是：", str3);
                                File uri2File2 = UriFileUtils.uri2File(ChooseFileJSPlugin.this.activityContext, data);
                                JSONArray jSONArray2 = new JSONArray();
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("filePath", uri2File2.getAbsolutePath());
                                jSONObject2.put("fileSize", String.valueOf(uri2File2.length()));
                                jSONObject2.put("mimeType", str3);
                                jSONArray2.put(jSONObject2);
                                ChooseFileJSPlugin.this.callbackSuccess(jSONArray2);
                                return;
                            }
                            ChooseFileJSPlugin.this.callbackFail("11", "用户主动取消并返回");
                        }
                    });
                    return;
                }
                ChooseFileJSPlugin.this.callbackFail("12", "用户没有访问相册/文件系统的权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.ChooseFileJSPlugin.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PermissionDialog.dimissDialog();
                ChooseFileJSPlugin chooseFileJSPlugin = ChooseFileJSPlugin.this;
                chooseFileJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
            }
        });
    }
}
