package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.megvii.livenesslib.FaceEntity;
import com.megvii.livenesslib.LivenessActivity;
import com.megvii.livenesslib.util.Constant;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.FacePlusUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/FacePlusPlus")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FacePlusPlusJSPlugin extends BaseJSPlugin {
    private boolean isSuccessCode = false;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String jSONArray;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("调用了Face++JS：");
            JSONObject jSONObject = this.parameterJO;
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7979d("LoginActivityBraod", sb.toString());
            String optString = this.parameterJO.optString("type");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            Log.d("face++", "type:" + optString);
            if (TextUtils.equals("CheckFaceChaoJiBan", optString)) {
                checkLive();
            } else if (TextUtils.equals("customCheckLive", optString)) {
                FacePlusUtils.getCode();
                String optString2 = optJSONObject.optString("pageTitle");
                String optString3 = optJSONObject.optString("actionCount");
                String optString4 = optJSONObject.optString("otherEnvImgCount");
                JSONArray optJSONArray = optJSONObject.optJSONArray("customActions");
                String optString5 = optJSONObject.optString("showFailToast");
                String optString6 = optJSONObject.optString("needVideo");
                String optString7 = optJSONObject.optString("needNei");
                FaceEntity faceEntity = new FaceEntity();
                faceEntity.setPageTitle(optString2);
                faceEntity.setActionCount(optString3);
                if (optJSONArray == null) {
                    jSONArray = "";
                } else {
                    jSONArray = !(optJSONArray instanceof JSONArray) ? optJSONArray.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray);
                }
                faceEntity.setActionNum(jSONArray);
                faceEntity.setOtherEnvImgCount(optString4);
                faceEntity.setShowFailToast(optString5);
                checkCustomLive(faceEntity, optString6, optString7);
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    public void checkLive() {
        try {
            PermissionDialog.show("刷脸认证为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface.FacePlusPlusJSPlugin.1
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    String str;
                    String str2;
                    String str3;
                    str = "";
                    String str4 = "";
                    try {
                        if (FacePlusPlusJSPlugin.this.f18589wv != null) {
                            str = TextUtils.isEmpty(FacePlusPlusJSPlugin.this.f18589wv.getTitle()) ? "" : FacePlusPlusJSPlugin.this.f18589wv.getTitle();
                            if (!TextUtils.isEmpty(FacePlusPlusJSPlugin.this.f18589wv.getUrl())) {
                                str4 = FacePlusPlusJSPlugin.this.f18589wv.getUrl();
                            }
                        }
                        str2 = str;
                        str3 = str4;
                    } catch (Exception e) {
                        MsLogUtil.m7978e("SDK调用软电话获取参数异常：" + e.getMessage());
                        str2 = str;
                        str3 = "";
                    }
                    TYCJBoxManager.getInstance().collectClickSdk(FacePlusPlusJSPlugin.this.activityContext, "S2ndpage1214", str2, "人脸识别", str3, "com.megvii.kas.livenessdetection", "1");
                    try {
                        PermissionDialog.dimissDialog();
                        StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "展示", "0", "超级办", "", "", "", ",", "");
                        Intent intent = new Intent(FacePlusPlusJSPlugin.this.activityContext, LivenessActivity.class);
                        intent.putExtra("showNodHead", "1");
                        intent.putExtra("actionNumber", "2");
                        new AvoidOnResult(FacePlusPlusJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface.FacePlusPlusJSPlugin.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                try {
                                    Activity activity = FacePlusPlusJSPlugin.this.activityContext;
                                    if (i == -1) {
                                        String str5 = Constant.message;
                                        if (TextUtils.isEmpty(str5)) {
                                            JSONObject backResult = FacePlusPlusJSPlugin.this.backResult(1, "人脸识别失败,请重试!");
                                            StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", "图片信息为空", "超级办", "", "", "", ",", "");
                                            FacePlusPlusJSPlugin.this.callbackFail(backResult);
                                        } else {
                                            FacePlusPlusJSPlugin.this.callbackSuccess(FacePlusPlusJSPlugin.this.backResult(0, str5));
                                            UIUtils.logD("face++", "电子身份证 face++返回活体数据：成功" + str5);
                                            StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证成功", "0", "超级办", "", "", "", ",", "");
                                        }
                                    } else {
                                        String stringExtra = intent2 != null ? intent2.getStringExtra("resultString") : "取消认证";
                                        FacePlusPlusJSPlugin.this.callbackFail(FacePlusPlusJSPlugin.this.backResult(1, "刷脸验证失败"));
                                        UIUtils.logD("face++", "电子身份证 face++返回活体数据：失败");
                                        StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", stringExtra, "超级办", "", "", "", ",", "");
                                    }
                                    Constant.message = "";
                                } catch (Exception e2) {
                                    Constant.message = "";
                                    UIUtils.logE(e2.getMessage());
                                }
                            }
                        });
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        UIUtils.toastLong("启动刷脸认证/OCR认证错误");
                    }
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        if (permissionArr.length > 0) {
                            if (TextUtils.equals("android.permission.CAMERA", permissionArr[0].permissionName)) {
                                UIUtils.toast("需要开启摄像头权限");
                            } else if (TextUtils.equals("android.permission.RECORD_AUDIO", permissionArr[0].permissionName)) {
                                UIUtils.toast("需要开启录音权限");
                            } else if (TextUtils.equals("android.permission.READ_EXTERNAL_STORAGE", permissionArr[0].permissionName) || TextUtils.equals("android.permission.WRITE_EXTERNAL_STORAGE", permissionArr[0].permissionName)) {
                                UIUtils.toast("需要开启存储卡权限");
                            }
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                        UIUtils.toast("需要开启摄像头/录音/存储卡权限");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("启动刷脸认证/OCR认证错误");
        }
    }

    public void checkCustomLive(FaceEntity faceEntity, String str, String str2) {
        try {
            PermissionDialog.show("刷脸认证为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new C92632(faceEntity, str, str2, TextUtils.equals("true", str)));
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("启动刷脸认证/OCR认证错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface.FacePlusPlusJSPlugin$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C92632 implements CheckRequestPermissionsListener {
        final /* synthetic */ FaceEntity val$entity;
        final /* synthetic */ boolean val$isNeedVideo;
        final /* synthetic */ String val$needNei;
        final /* synthetic */ String val$needVideo;

        C92632(FaceEntity faceEntity, String str, String str2, boolean z) {
            this.val$entity = faceEntity;
            this.val$needVideo = str;
            this.val$needNei = str2;
            this.val$isNeedVideo = z;
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onAllPermissionOk(Permission[] permissionArr) {
            String str;
            String str2;
            try {
                if (FacePlusPlusJSPlugin.this.f18589wv != null) {
                    String title = TextUtils.isEmpty(FacePlusPlusJSPlugin.this.f18589wv.getTitle()) ? "" : FacePlusPlusJSPlugin.this.f18589wv.getTitle();
                    if (TextUtils.isEmpty(FacePlusPlusJSPlugin.this.f18589wv.getUrl())) {
                        str = title;
                        str2 = "";
                    } else {
                        str = title;
                        str2 = FacePlusPlusJSPlugin.this.f18589wv.getUrl();
                    }
                } else {
                    str = "";
                    str2 = "";
                }
                TYCJBoxManager.getInstance().collectClickSdk(FacePlusPlusJSPlugin.this.activityContext, "S2ndpage1214", str, "人脸识别", str2, "com.megvii.kas.livenessdetection", "1");
            } catch (Exception e) {
                MsLogUtil.m7978e("SDK调用软电话日志异常：" + e.getMessage());
            }
            try {
                PermissionDialog.dimissDialog();
                StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "展示", "0", this.val$entity.getPageTitle(), "", "", "", ",", "");
                Intent intent = new Intent(FacePlusPlusJSPlugin.this.activityContext, LivenessActivity.class);
                intent.putExtra("faceEntity", this.val$entity);
                intent.putExtra("actionNumber", this.val$entity.getActionCount());
                intent.putExtra("needVideo", this.val$needVideo);
                intent.putExtra("needNei", this.val$needNei);
                intent.putExtra("isCustomCheckLive", "true");
                new AvoidOnResult(FacePlusPlusJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface.FacePlusPlusJSPlugin.2.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent2) {
                        FacePlusUtils.cancleGetCode();
                        try {
                            Activity activity = FacePlusPlusJSPlugin.this.activityContext;
                            if (i == -1) {
                                final String str3 = Constant.message;
                                if (!TextUtils.isEmpty(str3)) {
                                    final JSONObject backResult = FacePlusPlusJSPlugin.this.backResult(0, str3);
                                    String stringExtra = intent2.getStringExtra("addCodeSuccess");
                                    if (!TextUtils.isEmpty(stringExtra) && "Y".equals(stringExtra)) {
                                        FacePlusPlusJSPlugin.this.isSuccessCode = true;
                                    }
                                    if (C92632.this.val$isNeedVideo) {
                                        final String stringExtra2 = intent2.getStringExtra("videoPath");
                                        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.checkface.FacePlusPlusJSPlugin.2.1.1
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                FacePlusPlusJSPlugin.this.waitForWirtenCompleted(new File(stringExtra2));
                                                UIUtils.logD("face++", "电子身份证 face++返回活体数据：成功" + str3);
                                                StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证成功", "0", C92632.this.val$entity.getPageTitle(), "", "", "", ",", "");
                                                FacePlusPlusJSPlugin.this.newLog(C92632.this.val$entity.getPageTitle(), FacePlusPlusJSPlugin.this.isSuccessCode);
                                                FacePlusPlusJSPlugin.this.callbackSuccess(backResult);
                                            }
                                        }, 1000L);
                                    } else {
                                        UIUtils.logD("face++", "电子身份证 face++返回活体数据：成功" + str3);
                                        StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证成功", "0", C92632.this.val$entity.getPageTitle(), "", "", "", ",", "");
                                        FacePlusPlusJSPlugin.this.newLog(C92632.this.val$entity.getPageTitle(), FacePlusPlusJSPlugin.this.isSuccessCode);
                                        FacePlusPlusJSPlugin.this.callbackSuccess(backResult);
                                    }
                                } else {
                                    FacePlusPlusJSPlugin.this.callbackFail(FacePlusPlusJSPlugin.this.backFailResult(1, "人脸识别失败,请重试!"));
                                    StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", "图片信息为空", C92632.this.val$entity.getPageTitle(), "", "", "", ",", "");
                                }
                            } else {
                                String stringExtra3 = intent2 != null ? intent2.getStringExtra("resultString") : "取消认证";
                                FacePlusPlusJSPlugin.this.callbackFail(FacePlusPlusJSPlugin.this.backFailResult(1, stringExtra3));
                                UIUtils.logD("face++", "电子身份证 face++返回活体数据：失败");
                                StatisticsUploadUtils.uploadBeiDong(FacePlusPlusJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", stringExtra3, C92632.this.val$entity.getPageTitle(), "", "", "", ",", "");
                            }
                            Constant.message = "";
                        } catch (Exception e2) {
                            Constant.message = "";
                            UIUtils.logE(e2.getMessage());
                        }
                    }
                });
            } catch (Exception e2) {
                FacePlusUtils.cancleGetCode();
                e2.printStackTrace();
                UIUtils.toastLong("启动刷脸认证/OCR认证错误");
            }
        }

        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
        public void onPermissionDenied(Permission[] permissionArr) {
            try {
                PermissionDialog.dimissDialog();
                FacePlusUtils.cancleGetCode();
                if (permissionArr.length > 0) {
                    if (TextUtils.equals("android.permission.CAMERA", permissionArr[0].permissionName)) {
                        UIUtils.toast("需要开启摄像头权限");
                    } else if (TextUtils.equals("android.permission.RECORD_AUDIO", permissionArr[0].permissionName)) {
                        UIUtils.toast("需要开启录音权限");
                    } else if (TextUtils.equals("android.permission.READ_EXTERNAL_STORAGE", permissionArr[0].permissionName) || TextUtils.equals("android.permission.WRITE_EXTERNAL_STORAGE", permissionArr[0].permissionName)) {
                        UIUtils.toast("需要开启存储卡权限");
                    }
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
                UIUtils.toast("需要开启摄像头/录音/存储卡权限");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForWirtenCompleted(File file) {
        long length;
        if (file.exists()) {
            do {
                length = file.length();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("waitCompleted: ", length + " " + file.length());
            } while (length != file.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject backResult(int i, String str) throws Exception {
        JSONObject jSONObject = new JSONObject();
        if (i != 0 && TextUtils.isEmpty(str)) {
            str = "参数无效或参数为空";
        }
        jSONObject.put("resultCode", String.valueOf(i));
        jSONObject.put("resultInfo", str);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject backFailResult(int i, String str) throws Exception {
        JSONObject jSONObject = new JSONObject();
        if (i != 0 && TextUtils.isEmpty(str)) {
            str = "参数无效或参数为空";
        }
        jSONObject.put("resultCode", String.valueOf(i));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("error", str);
        jSONObject.put("resultInfo", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void newLog(String str, boolean z) {
        StatisticsUploadUtils.uploadBeiDong(this.activityContext, "web100002", "活体认证-face++添加code值", z ? "添加code成功" : "添加code失败", "0", str, DeviceHelper.getDeviceOSVersion(), "", String.valueOf(FacePlusUtils.longtime), FacePlusUtils.jsonString, DeviceHelper.getDeviceID(true), DeviceHelper.getDeviceModel());
    }
}
