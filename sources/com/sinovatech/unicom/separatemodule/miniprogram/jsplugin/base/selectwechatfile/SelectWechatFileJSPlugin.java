package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUriUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/chooseWechatFile")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SelectWechatFileJSPlugin extends BaseJSPlugin {
    private static final String TAG = "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.SelectWechatFileJSPlugin";
    private String type;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                this.type = this.parameterJO.optString("type", "all");
            }
            if (this.activityContext != null) {
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.SelectWechatFileJSPlugin.1
                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onAllPermissionOk(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        if (App.getSharePreferenceUtil().getBoolean("getWechat")) {
                            SelectWechatFileJSPlugin.this.goToWechat();
                            return;
                        }
                        Uri parse = Uri.parse(FileUriUtils.changeToUri(FileUriUtils.statusPath));
                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                        intent.addFlags(195);
                        if (Build.VERSION.SDK_INT >= 26) {
                            intent.putExtra("android.provider.extra.INITIAL_URI", parse);
                        }
                        new AvoidOnResult(SelectWechatFileJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.SelectWechatFileJSPlugin.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                Uri data;
                                if (i != -1 || intent2 == null) {
                                    Log.d(SelectWechatFileJSPlugin.TAG, "没有授权");
                                    ToastUtil.showToast("用户取消授权获取微信data目录文件的权限!");
                                } else if (intent2.getData().getPath().substring(intent2.getData().getPath().indexOf(":") + 1, intent2.getData().getPath().length()).equals(FileUriUtils.statusPath) && (data = intent2.getData()) != null) {
                                    App.getInstance().getContentResolver().takePersistableUriPermission(data, intent2.getFlags() & 3);
                                    App.getSharePreferenceUtil().putBoolean("getWechat", true);
                                    SelectWechatFileJSPlugin.this.goToWechat();
                                } else {
                                    ToastUtil.showToast("请准确授权微信data目录!");
                                }
                            }
                        });
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onPermissionDenied(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        ToastUtil.showToast("请开启存储权限,才能使用此功能!");
                    }
                });
            }
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "异常：" + e.getMessage());
            callbackFail("微信选择文件异常：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToWechat() {
        Intent intent = new Intent(this.activityContext, SelectWechatFileActivity.class);
        intent.putExtra("type", this.type);
        new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.SelectWechatFileJSPlugin.2
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                if (i != -1 || intent2 == null) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("status", intent2.getStringExtra("status"));
                    if (!TextUtils.isEmpty(intent2.getStringExtra("result"))) {
                        jSONObject.put("result", new JSONArray(intent2.getStringExtra("result")));
                    } else {
                        jSONObject.put("result", new JSONArray());
                    }
                    jSONObject.put("msg", intent2.getStringExtra("msg"));
                    SelectWechatFileJSPlugin.this.callbackSuccess(jSONObject);
                } catch (Exception e) {
                    SelectWechatFileJSPlugin selectWechatFileJSPlugin = SelectWechatFileJSPlugin.this;
                    selectWechatFileJSPlugin.callbackFail("微信选择文件数据解析异常：" + e.getMessage());
                }
            }
        });
    }
}
