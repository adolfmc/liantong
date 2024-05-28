package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity;
import com.sinovatech.unicom.separatemodule.idcard.newaction.utils.NewCamera2Utils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/cardCamera")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CameraJSPlugin extends BaseJSPlugin {
    private static final String TAG = "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.camera.CameraJSPlugin";
    private String url = "";
    private String logTitle = "";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.f18589wv != null && !TextUtils.isEmpty(this.f18589wv.getUrl())) {
                this.url = this.f18589wv.getUrl();
            }
            if (this.parameterJO != null) {
                this.logTitle = this.parameterJO.optString("logTitle", "");
            }
        } catch (Exception unused) {
        }
        try {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.camera.CameraJSPlugin.1
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        PvCurrencyLogUtils.pvLogDJCamera2Api("展示", "新方法", "新方法");
                        Intent intent = new Intent(CameraJSPlugin.this.activityContext, PhotographActivity.class);
                        intent.putExtra("url", CameraJSPlugin.this.url);
                        intent.putExtra("serviceName", CameraJSPlugin.this.logTitle);
                        new AvoidOnResult(CameraJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.camera.CameraJSPlugin.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                Activity activity = CameraJSPlugin.this.activityContext;
                                if (i == -1 && NewCamera2Utils.photoData != null) {
                                    String encodeToString = Base64.encodeToString(NewCamera2Utils.photoData, 0);
                                    PvCurrencyLogUtils.pvLogDJCamera2Api("识别成功", "新方法", "新方法");
                                    CameraJSPlugin.this.callbackSucces(encodeToString);
                                    return;
                                }
                                Activity activity2 = CameraJSPlugin.this.activityContext;
                                if (i == 0) {
                                    PvCurrencyLogUtils.pvLogDJCamera2Api("识别失败(用户主动返回)", "新方法", "新方法");
                                    CameraJSPlugin.this.callbackFile("用户取消扫描");
                                    return;
                                }
                                PvCurrencyLogUtils.pvLogDJCamera2Api("识别失败(方法失败)", "新方法", "新方法");
                                CameraJSPlugin.this.callbackErrorFile("识别失败，请重试！");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.toastLong("拍照错误");
                        CameraJSPlugin.this.callbackErrorFile("拍照错误");
                    }
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        if (permissionArr.length <= 0 || !TextUtils.equals("android.permission.CAMERA", permissionArr[0].permissionName)) {
                            return;
                        }
                        UIUtils.toast("需要开启摄像头权限");
                        CameraJSPlugin.this.callbackErrorQuanXian("用户未开相机权限");
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                        UIUtils.toast("需要开启摄像头权限");
                        CameraJSPlugin.this.callbackErrorQuanXian("用户未开相机权限");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            LogUtil.m7987e(str, "异常：" + e.getMessage());
            callbackErrorFile("拍照错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSucces(String str) {
        try {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("status", "0000");
                jSONObject.put("data", str);
                jSONObject.put("msg", "识别成功");
                callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            NewCamera2Utils.photoData = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFile(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", "20");
            jSONObject.put("msg", str);
            callbackFail(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackErrorFile(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", "10");
            jSONObject.put("msg", str);
            callbackFail(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackErrorQuanXian(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", "30");
            jSONObject.put("msg", str);
            callbackFail(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
