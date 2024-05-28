package com.sinovatech.unicom.separatemodule.dianzishenfenzheng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.anicert.verification.lib_identify.identification.CtidAuthService;
import cn.anicert.verification.lib_identify.identification.IctidAuthService;
import cn.anicert.verification.lib_identify.third.CtidNum;
import cn.anicert.verification.lib_identify.third.Result;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.megvii.livenesslib.LivenessActivity;
import com.megvii.livenesslib.util.Constant;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/NetIDCard")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NetIDCardJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            String optString2 = optJSONObject.optString("appId");
            String optString3 = optJSONObject.optString("organizeId");
            String optString4 = optJSONObject.optString("ctid");
            String optString5 = optJSONObject.optString("randomNumber");
            String optString6 = optJSONObject.optString("dataType");
            optJSONObject.optString("account");
            if (TextUtils.equals("NetIDCard_AuthorData", optString)) {
                Result<String> authIDCardData = new CtidAuthService(this.activityContext).getAuthIDCardData(optString5, new IctidAuthService.IdCardData(optString4, optString3, optString2, Integer.valueOf(optString6).intValue()));
                if (authIDCardData.code == 0) {
                    callbackSuccess(backResult(authIDCardData.code, authIDCardData.value));
                } else {
                    callbackFail(backResult(1, authIDCardData.msg));
                }
            } else if (TextUtils.equals("openFaceCheckLive", optString)) {
                checkLive(optJSONObject.optString("actionCount"));
            }
        } catch (Exception unused) {
            callbackFail(backResult(1, "请求失败"));
        }
    }

    public void checkLive(final String str) {
        try {
            PermissionDialog.show("刷脸认证为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.dianzishenfenzheng.NetIDCardJSPlugin.1
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        StatisticsUploadUtils.uploadBeiDong(NetIDCardJSPlugin.this.activityContext, "web100002", "活体认证-face++", "展示", "0", "电子身份证", "", "", "", ",", "");
                        try {
                            if (NetIDCardJSPlugin.this.f18589wv != null) {
                                NetIDCardJSPlugin.this.f18589wv.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.dianzishenfenzheng.NetIDCardJSPlugin.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        TYCJBoxManager.getInstance().collectClickSdk(NetIDCardJSPlugin.this.activityContext, "S2ndpage1214", !TextUtils.isEmpty(NetIDCardJSPlugin.this.f18589wv.getTitle()) ? NetIDCardJSPlugin.this.f18589wv.getTitle() : "", "人脸识别", !TextUtils.isEmpty(NetIDCardJSPlugin.this.f18589wv.getUrl()) ? NetIDCardJSPlugin.this.f18589wv.getUrl() : "", "com.megvii.kas.livenessdetection", "1");
                                    }
                                });
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7978e("NetIDCardJSPlugin上传日志参数异常：" + e.getMessage());
                        }
                        Intent intent = new Intent(NetIDCardJSPlugin.this.activityContext, LivenessActivity.class);
                        intent.putExtra("actionNumber", str);
                        new AvoidOnResult(NetIDCardJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.dianzishenfenzheng.NetIDCardJSPlugin.1.2
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent2) {
                                try {
                                    Activity activity = NetIDCardJSPlugin.this.activityContext;
                                    if (i == -1) {
                                        String str2 = Constant.message;
                                        if (TextUtils.isEmpty(str2)) {
                                            NetIDCardJSPlugin.this.callbackFail(NetIDCardJSPlugin.this.backResult(1, "人脸识别失败,请重试!"));
                                            StatisticsUploadUtils.uploadBeiDong(NetIDCardJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", "图片信息为空", "电子身份证", "", "", "", ",", "");
                                        } else {
                                            NetIDCardJSPlugin.this.callbackSuccess(NetIDCardJSPlugin.this.backResult(0, str2));
                                            UIUtils.logD("face++", "电子身份证 face++返回活体数据：成功" + str2);
                                            StatisticsUploadUtils.uploadBeiDong(NetIDCardJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证成功", "0", "电子身份证", "", "", "", ",", "");
                                        }
                                    } else {
                                        String stringExtra = intent2 != null ? intent2.getStringExtra("resultString") : "取消认证";
                                        NetIDCardJSPlugin.this.callbackFail(NetIDCardJSPlugin.this.backResult(1, "刷脸验证失败"));
                                        UIUtils.logD("face++", "电子身份证 face++返回活体数据：失败");
                                        StatisticsUploadUtils.uploadBeiDong(NetIDCardJSPlugin.this.activityContext, "web100002", "活体认证-face++", "认证失败", stringExtra, "电子身份证", "", "", "", ",", "");
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

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            String optString2 = optJSONObject.optString("appId");
            String optString3 = optJSONObject.optString("organizeId");
            String optString4 = optJSONObject.optString("ctid");
            String optString5 = optJSONObject.optString("randomNumber");
            String optString6 = optJSONObject.optString("dataType");
            String optString7 = optJSONObject.optString("account");
            try {
                if (this.f18589wv != null) {
                    this.f18589wv.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.dianzishenfenzheng.NetIDCardJSPlugin.2
                        @Override // java.lang.Runnable
                        public void run() {
                            TYCJBoxManager.getInstance().collectClickSdk(NetIDCardJSPlugin.this.activityContext, "S2ndpage1214", !TextUtils.isEmpty(NetIDCardJSPlugin.this.f18589wv.getTitle()) ? NetIDCardJSPlugin.this.f18589wv.getTitle() : "", "电子身份证", !TextUtils.isEmpty(NetIDCardJSPlugin.this.f18589wv.getUrl()) ? NetIDCardJSPlugin.this.f18589wv.getUrl() : "", "com.anxin.teeidentify_lib", "1");
                        }
                    });
                }
            } catch (Exception e) {
                MsLogUtil.m7978e("SDK调用软电话获取参数异常：" + e.getMessage());
            }
            if (TextUtils.equals("NetIDCard_EnrollmentData", optString)) {
                Result<String> enrollmentIDCardData = new CtidAuthService(this.activityContext).getEnrollmentIDCardData(optString5, new IctidAuthService.IdCardData(optString4, optString3, optString2));
                if (enrollmentIDCardData.code == 0) {
                    return callbackSuccessSync(backResult(enrollmentIDCardData.code, enrollmentIDCardData.value));
                }
                return callbackFailSync(backResult(enrollmentIDCardData.code, enrollmentIDCardData.msg));
            } else if (TextUtils.equals("NetIDCard_AuthorData", optString)) {
                Result<String> authIDCardData = new CtidAuthService(this.activityContext).getAuthIDCardData(optString5, new IctidAuthService.IdCardData(optString4, optString3, optString2, Integer.valueOf(optString6).intValue()));
                if (authIDCardData.code == 0) {
                    return callbackSuccessSync(backResult(authIDCardData.code, authIDCardData.value));
                }
                return callbackFailSync(backResult(authIDCardData.code, authIDCardData.msg));
            } else if (TextUtils.equals("NetIDCard_ApplyData", optString)) {
                Result<String> applyData = new CtidAuthService(this.activityContext).getApplyData(new IctidAuthService.ApplyData(Integer.valueOf(optString6).intValue(), optString3, optString2));
                if (applyData.code == 0) {
                    return callbackSuccessSync(backResult(applyData.code, applyData.value));
                }
                return callbackFailSync(backResult(applyData.code, applyData.msg));
            } else if (TextUtils.equals("NetIDCard_QrCheckData", optString)) {
                Result<String> reqQRCodeData = new CtidAuthService(this.activityContext).getReqQRCodeData(optString5, new IctidAuthService.ReqCodeData(optString4, optString3, optString2));
                if (reqQRCodeData.code == 0) {
                    return callbackSuccessSync(backResult(reqQRCodeData.code, reqQRCodeData.value));
                }
                return callbackFailSync(backResult(reqQRCodeData.code, reqQRCodeData.msg));
            } else if (TextUtils.equals("NetIDCard_QrAuthorData", optString)) {
                Result<String> authQRCodeData = new CtidAuthService(this.activityContext).getAuthQRCodeData(optString5, new IctidAuthService.QRCodeData(optString4, optString3, optString2));
                if (authQRCodeData.code == 0) {
                    return callbackSuccessSync(backResult(authQRCodeData.code, authQRCodeData.value));
                }
                return callbackFailSync(backResult(authQRCodeData.code, authQRCodeData.msg));
            } else if (TextUtils.equals("NetIDCard_Info", optString)) {
                Result<CtidNum> ctidNum = CtidAuthService.getCtidNum(optString4);
                if (ctidNum.code == 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("EndingDate", ctidNum.value.validDate);
                    hashMap.put("SerialNumber", ctidNum.value.ctidNum);
                    hashMap.put("resultCode", String.valueOf(ctidNum.code));
                    return callbackSuccessSync(new JSONObject(hashMap));
                }
                return callbackFailSync(backResult(ctidNum.code, ctidNum.msg));
            } else if (TextUtils.equals("NetIDCard_Version", optString)) {
                Result<String> authIDCardDataVer = new CtidAuthService(this.activityContext).getAuthIDCardDataVer();
                if (authIDCardDataVer.code == 0) {
                    return callbackSuccessSync(backResult(authIDCardDataVer.code, authIDCardDataVer.value));
                }
                return callbackFailSync(backResult(authIDCardDataVer.code, authIDCardDataVer.msg));
            } else if (TextUtils.equals("NetIDCard_QrImage", optString)) {
                String optString8 = optJSONObject.optString("qrImgWidth");
                Result<Bitmap> createQRCodeImage = new CtidAuthService(this.activityContext).createQRCodeImage(optJSONObject.optString("qrImgStream"), Float.valueOf(optString8).floatValue(), Integer.valueOf(optJSONObject.optString("qrStreamWidth")).intValue());
                if (createQRCodeImage.code == 0) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    createQRCodeImage.value.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    return callbackSuccessSync(backResult(createQRCodeImage.code, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2)));
                }
                return callbackFailSync(backResult(createQRCodeImage.code, createQRCodeImage.msg));
            } else if (TextUtils.equals("NetIDCard_Delete", optString)) {
                if (!TextUtils.isEmpty(optString7)) {
                    if (new ElectronicIdCardDao(this.activityContext).deleteData(optString7)) {
                        StatisticsUploadUtils.uploadRealTime(this.activityContext, "secard0001", "删除网证", "按钮", "0", "删除成功", "");
                        return callbackSuccessSync(backResult(0, "删除成功"));
                    }
                    StatisticsUploadUtils.uploadRealTime(this.activityContext, "secard0001", "删除网证", "按钮", "0", "删除失败", "");
                    return callbackFailSync(backResult(1, "删除失败"));
                }
                return callbackFailSync(backResult(1, "手机号为空"));
            } else if (TextUtils.equals("NetIDCard_Update", optString)) {
                if (!TextUtils.isEmpty(optString7)) {
                    if (new ElectronicIdCardDao(this.activityContext).updateData(optString7, optString4)) {
                        return callbackSuccessSync(backResult(0, "更新成功"));
                    }
                    return callbackFailSync(backResult(1, "更新失败"));
                }
                return callbackFailSync(backResult(1, "手机号为空"));
            } else if (TextUtils.equals("NetIDCard_Query", optString)) {
                if (!TextUtils.isEmpty(optString7)) {
                    return callbackSuccessSync(backResult(0, new ElectronicIdCardDao(this.activityContext).getJsonData(optString7)));
                }
                return callbackFailSync(backResult(1, "手机号为空"));
            } else {
                return null;
            }
        } catch (Exception e2) {
            return callbackFailSync(backResult(1, "请求失败" + e2.getMessage()));
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
}
