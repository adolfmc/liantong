package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.megvii.livenesslib.LivenessActivity;
import com.megvii.livenesslib.util.Constant;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.idcard.IDCardScanActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerFace */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerFace {
    private AppCompatActivity activityContext;
    private String imgType;
    private String method;
    private String serviceName;
    private PBWebView webView;

    public ManagerFace(AppCompatActivity appCompatActivity, PBWebView pBWebView) {
        this.activityContext = appCompatActivity;
        this.webView = pBWebView;
    }

    public void start(String str) {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).optString("msg"));
            this.method = jSONObject.optString("method");
            this.imgType = jSONObject.optString("imgType");
            this.serviceName = App.webviewTitle;
            String[] strArr = null;
            String str2 = "";
            if ("captureIdCard".equals(this.method)) {
                str2 = "为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。";
                strArr = new String[]{"android.permission.CAMERA"};
            } else if ("captureAction".equals(this.method)) {
                str2 = "为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。";
                strArr = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
            }
            if (strArr != null) {
                PermissionDialog.show(str2);
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build(strArr), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFace.1
                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onAllPermissionOk(Permission[] permissionArr) {
                        try {
                            PermissionDialog.dimissDialog();
                            if (!"captureIdCard".equals(ManagerFace.this.method)) {
                                if ("captureAction".equals(ManagerFace.this.method)) {
                                    ManagerFace.this.starActivieLive();
                                    StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100002", "活体认证-face++", "展示", "0", ManagerFace.this.serviceName, "", "", "", ",", "");
                                }
                            } else {
                                StatisticsUploadUtils.upload(ManagerFace.this.activityContext, "web100001", "ocr-face++", "展示", "0", ManagerFace.this.serviceName, "");
                                if ("1".equals(ManagerFace.this.imgType)) {
                                    ManagerFace.this.starIDCardFront();
                                } else if ("2".equals(ManagerFace.this.imgType)) {
                                    ManagerFace.this.startIDCardBack();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
                            if ("captureIdCard".equals(ManagerFace.this.method)) {
                                StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100001", "ocr-face++", "识别失败", "未开相机/音频/存储卡权限", ManagerFace.this.serviceName, "", "", "", ",", "");
                            } else {
                                StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100002", "活体认证-face++", "认证失败", "未开相机/音频/存储卡权限", ManagerFace.this.serviceName, "", "", "", ",", "");
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7978e(e.getMessage());
                            UIUtils.toast("需要开启摄像头/录音/存储卡权限");
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("启动刷脸认证/OCR认证错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void starIDCardFront() {
        Intent intent = new Intent(this.activityContext, IDCardScanActivity.class);
        intent.putExtra("side", 0);
        intent.putExtra("isvertical", true);
        new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFace.2
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                AppCompatActivity unused = ManagerFace.this.activityContext;
                if (i == -1) {
                    StatisticsUploadUtils.upload(ManagerFace.this.activityContext, "web100001", "ocr-face++", "识别成功", "0", ManagerFace.this.serviceName, "");
                    String encodeToString = Base64.encodeToString(intent2.getByteArrayExtra("idcardImg"), 0);
                    PBWebView pBWebView = ManagerFace.this.webView;
                    pBWebView.loadURL("javascript:validataFaceImg('" + encodeToString + "')");
                    return;
                }
                StatisticsUploadUtils.upload(ManagerFace.this.activityContext, "web100001", "ocr-face++", "识别失败", "用户取消证件扫描", ManagerFace.this.serviceName, "");
                ManagerFace.this.webView.loadURL("javascript:try{validataFaceImgFail('用户取消扫描');}catch(err){}");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startIDCardBack() {
        Intent intent = new Intent(this.activityContext, IDCardScanActivity.class);
        intent.putExtra("side", 1);
        intent.putExtra("isvertical", true);
        new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFace.3
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                AppCompatActivity unused = ManagerFace.this.activityContext;
                if (i == -1) {
                    StatisticsUploadUtils.upload(ManagerFace.this.activityContext, "web100001", "ocr-face++", "识别成功", "0", ManagerFace.this.serviceName, "");
                    String encodeToString = Base64.encodeToString(intent2.getByteArrayExtra("idcardImg"), 0);
                    PBWebView pBWebView = ManagerFace.this.webView;
                    pBWebView.loadURL("javascript:validataFaceImg('" + encodeToString + "')");
                    return;
                }
                StatisticsUploadUtils.upload(ManagerFace.this.activityContext, "web100001", "ocr-face++", "识别失败", "用户取消证件扫描", ManagerFace.this.serviceName, "");
                ManagerFace.this.webView.loadURL("javascript:try{validataFaceImgFail('用户取消扫描');}catch(err){}");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void starActivieLive() {
        PermissionDialog.show("刷脸认证为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFace.4
            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onAllPermissionOk(Permission[] permissionArr) {
                String str;
                String str2;
                String str3;
                PermissionDialog.dimissDialog();
                str = "";
                String str4 = "";
                try {
                    if (ManagerFace.this.webView != null) {
                        str = TextUtils.isEmpty(ManagerFace.this.webView.getTitle()) ? "" : ManagerFace.this.webView.getTitle();
                        if (!TextUtils.isEmpty(ManagerFace.this.webView.getUrl())) {
                            str4 = ManagerFace.this.webView.getUrl();
                        }
                    }
                    str2 = str;
                    str3 = str4;
                } catch (Exception e) {
                    MsLogUtil.m7978e("SDK调用软电话获取参数异常：" + e.getMessage());
                    str2 = str;
                    str3 = "";
                }
                TYCJBoxManager.getInstance().collectClickSdk(ManagerFace.this.activityContext, "S2ndpage1214", str2, "人脸识别", str3, "com.megvii.kas.livenessdetection", "1");
                new AvoidOnResult(ManagerFace.this.activityContext).startForResult(new Intent(ManagerFace.this.activityContext, LivenessActivity.class), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFace.4.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        try {
                            AppCompatActivity unused = ManagerFace.this.activityContext;
                            if (i == -1) {
                                String str5 = Constant.message;
                                if (TextUtils.isEmpty(str5)) {
                                    ManagerFace.this.webView.loadURL("javascript:try{validataFaceFail();}catch(err){}");
                                    StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100002", "活体认证-face++", "认证失败", "图片信息为空", "captureAction", "", "", "", ",", "");
                                } else {
                                    UIUtils.logD("face++", "face++返回活体数据：成功" + str5);
                                    PBWebView pBWebView = ManagerFace.this.webView;
                                    pBWebView.loadURL("javascript:validataFaceVedio('" + str5 + "')");
                                    StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100002", "活体认证-face++", "认证成功", "0", "captureAction", "", "", "", ",", "");
                                }
                            } else {
                                String stringExtra = intent != null ? intent.getStringExtra("resultString") : "取消认证";
                                ManagerFace.this.webView.loadURL("javascript:try{validataFaceFail();}catch(err){}");
                                UIUtils.logD("face++", "face++返回活体数据：失败");
                                StatisticsUploadUtils.uploadBeiDong(ManagerFace.this.activityContext, "web100002", "活体认证-face++", "认证失败", stringExtra, "captureAction", "", "", "", ",", "");
                            }
                            Constant.message = "";
                        } catch (Exception e2) {
                            Constant.message = "";
                            UIUtils.logE(e2.getMessage());
                        }
                    }
                });
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
    }
}
