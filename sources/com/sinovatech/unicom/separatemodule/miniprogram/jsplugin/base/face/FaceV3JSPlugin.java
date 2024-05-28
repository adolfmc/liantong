package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.entity.FaceV3Entity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/faceV3Detect")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FaceV3JSPlugin extends BaseJSPlugin {
    private static final String TAG = "FaceV3JSPlugin";
    private String biz_token;
    private String businessName;
    private String flashColorCount;
    private String flashLivenessTimeout;
    private Handler handler;
    private String initiativeFlashColorCount;
    private String initiativeLivenessActionCount;
    private String initiativeLivenessFlashTimeout;
    private String initiativeLivenessTimeout;
    private String livenessActionCount;
    private String livenessMegliveType;
    private JSONArray livenessMegliveTypes;
    private ArrayList<Integer> livenessMeglliveTypesArrays = new ArrayList<>();
    private String livenessTimeout;
    private String type;
    private String urlApp;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String str;
        String str2;
        try {
            try {
                if (this.f18589wv != null) {
                    String title = TextUtils.isEmpty(this.f18589wv.getTitle()) ? "" : this.f18589wv.getTitle();
                    if (TextUtils.isEmpty(this.f18589wv.getUrl())) {
                        str = title;
                        str2 = "";
                    } else {
                        str = title;
                        str2 = this.f18589wv.getUrl();
                    }
                } else {
                    str = "";
                    str2 = "";
                }
                TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", str, "人脸识别", str2, "com.megvii.lv5.sdk", "1");
            } catch (Exception e) {
                MsLogUtil.m7978e("SDK调用日志异常：" + e.getMessage());
            }
            if (this.parameterJO != null) {
                this.type = this.parameterJO.optString("type", "");
                this.biz_token = this.parameterJO.optString("biz_token", "");
                this.livenessTimeout = this.parameterJO.optString("livenessTimeout", "");
                this.livenessActionCount = this.parameterJO.optString("livenessActionCount", "");
                this.flashLivenessTimeout = this.parameterJO.optString("flashLivenessTimeout", "");
                this.flashColorCount = this.parameterJO.optString("flashColorCount", "");
                this.businessName = this.parameterJO.optString("businessName", "");
                this.livenessMegliveType = this.parameterJO.optString("livenessMegliveType", "");
                this.livenessMegliveTypes = this.parameterJO.optJSONArray("livenessMegliveTypes");
                this.initiativeLivenessTimeout = this.parameterJO.optString("initiativeLivenessTimeout", "");
                this.initiativeLivenessFlashTimeout = this.parameterJO.optString("initiativeLivenessFlashTimeout", "");
                this.initiativeFlashColorCount = this.parameterJO.optString("initiativeFlashColorCount", "");
                this.initiativeLivenessActionCount = this.parameterJO.optString("initiativeLivenessActionCount", "");
            }
            this.handler = new Handler();
            if (TextUtils.isEmpty(this.businessName)) {
                callbackErrorFile("11", "businessName参数异常");
                return;
            }
            if (!TextUtils.isEmpty(this.type) && ("1".equals(this.type) || "2".equals(this.type) || "3".equals(this.type) || "4".equals(this.type))) {
                if (TextUtils.isEmpty(this.biz_token) || this.biz_token.length() > 30) {
                    this.biz_token = "default-token";
                }
                String str3 = TAG;
                MsLogUtil.m7979d(str3, "biz_token = " + this.biz_token);
                if (this.type.equals("1")) {
                    try {
                        if (TextUtils.isEmpty(this.livenessTimeout) || Integer.parseInt(this.livenessTimeout) < 5 || Integer.parseInt(this.livenessTimeout) > 60) {
                            this.livenessTimeout = "10";
                        }
                    } catch (Exception unused) {
                        this.livenessTimeout = "10";
                    }
                    if (TextUtils.isEmpty(this.livenessActionCount) || (!this.livenessActionCount.equals("1") && !this.livenessActionCount.equals("2") && !this.livenessActionCount.equals("3"))) {
                        this.livenessActionCount = "1";
                    }
                    PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                    SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1
                        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                        public void onAllPermissionOk(Permission[] permissionArr) {
                            try {
                                PermissionDialog.dimissDialog();
                                try {
                                    if (FaceV3JSPlugin.this.f18589wv != null) {
                                        FaceV3JSPlugin.this.urlApp = FaceV3JSPlugin.this.f18589wv.getUrl();
                                    }
                                } catch (Exception unused2) {
                                    FaceV3JSPlugin.this.urlApp = "";
                                }
                                String str4 = FaceV3JSPlugin.this.businessName;
                                String str5 = FaceV3JSPlugin.this.urlApp;
                                JSONObject jSONObject = FaceV3JSPlugin.this.parameterJO;
                                PvCurrencyLogUtils.pvXuanCaiV3(str4, str5, "展示", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "");
                                Intent intent = new Intent(FaceV3JSPlugin.this.activityContext, MainPrivateActivity.class);
                                intent.putExtra("type", FaceV3JSPlugin.this.type);
                                intent.putExtra("biz_token", FaceV3JSPlugin.this.biz_token);
                                if (FaceV3JSPlugin.this.type.equals("1")) {
                                    intent.putExtra("livenessTimeout", FaceV3JSPlugin.this.livenessTimeout);
                                    intent.putExtra("livenessActionCount", FaceV3JSPlugin.this.livenessActionCount);
                                } else if (FaceV3JSPlugin.this.type.equals("2")) {
                                    intent.putExtra("flashLivenessTimeout", FaceV3JSPlugin.this.flashLivenessTimeout);
                                    intent.putExtra("flashColorCount", FaceV3JSPlugin.this.flashColorCount);
                                } else if (FaceV3JSPlugin.this.type.equals("3")) {
                                    intent.putIntegerArrayListExtra("livenessMeglliveTypesArrays", FaceV3JSPlugin.this.livenessMeglliveTypesArrays);
                                    intent.putExtra("initiativeLivenessTimeout", FaceV3JSPlugin.this.initiativeLivenessTimeout);
                                    intent.putExtra("initiativeLivenessFlashTimeout", FaceV3JSPlugin.this.initiativeLivenessFlashTimeout);
                                    intent.putExtra("initiativeFlashColorCount", FaceV3JSPlugin.this.initiativeFlashColorCount);
                                    intent.putExtra("initiativeLivenessActionCount", FaceV3JSPlugin.this.initiativeLivenessActionCount);
                                }
                                new AvoidOnResult(FaceV3JSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1.1
                                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                    public void onActivityResult(int i, Intent intent2) {
                                        try {
                                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                            MsLogUtil.m7979d(FaceV3JSPlugin.TAG + format + "faceidv5", "JSPLUGIN 收到数据");
                                            if (intent2 == null) {
                                                String str6 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str6, "炫彩活体数据失败：status:10 msg:炫彩活体没有data数据");
                                                FaceV3JSPlugin.this.callbackErrorFile("10", "炫彩活体没有data数据");
                                            } else if (i == -1) {
                                                String stringExtra = intent2.getStringExtra("livenessFilePath");
                                                String stringExtra2 = intent2.getStringExtra("status");
                                                String stringExtra3 = intent2.getStringExtra("msg");
                                                String stringExtra4 = intent2.getStringExtra("bizToken");
                                                String str7 = HttpPrivateUtil.faceV3Data;
                                                FaceV3Entity faceV3Entity = new FaceV3Entity();
                                                faceV3Entity.setBizToken(stringExtra4);
                                                faceV3Entity.setStatus(stringExtra2);
                                                faceV3Entity.setMsg(stringExtra3);
                                                faceV3Entity.setData(str7);
                                                faceV3Entity.setLivenessFilePath(stringExtra);
                                                String str8 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str8, "炫彩活体数据成功：livenessFilePath:" + stringExtra);
                                                String str9 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str9, "炫彩活体数据成功：status:" + stringExtra2);
                                                String str10 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str10, "炫彩活体数据成功：msg:" + stringExtra3);
                                                String str11 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str11, "炫彩活体数据成功：bizToken:" + stringExtra4);
                                                FaceV3JSPlugin.this.callbackSucces(faceV3Entity);
                                            } else {
                                                String stringExtra5 = intent2.getStringExtra("status");
                                                String stringExtra6 = intent2.getStringExtra("msg");
                                                String str12 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str12, "炫彩活体数据失败：status:" + stringExtra5 + " msg:" + stringExtra6);
                                                FaceV3JSPlugin.this.callbackErrorFile(stringExtra5, stringExtra6);
                                            }
                                        } catch (Exception e2) {
                                            UIUtils.logE(e2.getMessage());
                                            FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                            faceV3JSPlugin.callbackErrorFile("10", "活体类返回异常:" + e2.getMessage());
                                        }
                                    }
                                });
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                UIUtils.toastLong("拍照错误");
                                FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                faceV3JSPlugin.callbackErrorFile("10", "活体调用异常：" + e2.getMessage());
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
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            } catch (Exception e2) {
                                MsLogUtil.m7978e(e2.getMessage());
                                UIUtils.toast("需要开启摄像头权限");
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            }
                        }
                    });
                    return;
                } else if (this.type.equals("2")) {
                    try {
                        if (TextUtils.isEmpty(this.flashLivenessTimeout) || Integer.parseInt(this.flashLivenessTimeout) < 60 || Integer.parseInt(this.flashLivenessTimeout) > 180) {
                            this.flashLivenessTimeout = "60";
                        }
                    } catch (Exception unused2) {
                        this.flashLivenessTimeout = "60";
                    }
                    if (TextUtils.isEmpty(this.flashColorCount) || (!this.flashColorCount.equals("4") && !this.flashColorCount.equals("6") && !this.flashColorCount.equals("8"))) {
                        this.flashColorCount = "4";
                    }
                    PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                    SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1
                        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                        public void onAllPermissionOk(Permission[] permissionArr) {
                            try {
                                PermissionDialog.dimissDialog();
                                try {
                                    if (FaceV3JSPlugin.this.f18589wv != null) {
                                        FaceV3JSPlugin.this.urlApp = FaceV3JSPlugin.this.f18589wv.getUrl();
                                    }
                                } catch (Exception unused22) {
                                    FaceV3JSPlugin.this.urlApp = "";
                                }
                                String str4 = FaceV3JSPlugin.this.businessName;
                                String str5 = FaceV3JSPlugin.this.urlApp;
                                JSONObject jSONObject = FaceV3JSPlugin.this.parameterJO;
                                PvCurrencyLogUtils.pvXuanCaiV3(str4, str5, "展示", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "");
                                Intent intent = new Intent(FaceV3JSPlugin.this.activityContext, MainPrivateActivity.class);
                                intent.putExtra("type", FaceV3JSPlugin.this.type);
                                intent.putExtra("biz_token", FaceV3JSPlugin.this.biz_token);
                                if (FaceV3JSPlugin.this.type.equals("1")) {
                                    intent.putExtra("livenessTimeout", FaceV3JSPlugin.this.livenessTimeout);
                                    intent.putExtra("livenessActionCount", FaceV3JSPlugin.this.livenessActionCount);
                                } else if (FaceV3JSPlugin.this.type.equals("2")) {
                                    intent.putExtra("flashLivenessTimeout", FaceV3JSPlugin.this.flashLivenessTimeout);
                                    intent.putExtra("flashColorCount", FaceV3JSPlugin.this.flashColorCount);
                                } else if (FaceV3JSPlugin.this.type.equals("3")) {
                                    intent.putIntegerArrayListExtra("livenessMeglliveTypesArrays", FaceV3JSPlugin.this.livenessMeglliveTypesArrays);
                                    intent.putExtra("initiativeLivenessTimeout", FaceV3JSPlugin.this.initiativeLivenessTimeout);
                                    intent.putExtra("initiativeLivenessFlashTimeout", FaceV3JSPlugin.this.initiativeLivenessFlashTimeout);
                                    intent.putExtra("initiativeFlashColorCount", FaceV3JSPlugin.this.initiativeFlashColorCount);
                                    intent.putExtra("initiativeLivenessActionCount", FaceV3JSPlugin.this.initiativeLivenessActionCount);
                                }
                                new AvoidOnResult(FaceV3JSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1.1
                                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                    public void onActivityResult(int i, Intent intent2) {
                                        try {
                                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                            MsLogUtil.m7979d(FaceV3JSPlugin.TAG + format + "faceidv5", "JSPLUGIN 收到数据");
                                            if (intent2 == null) {
                                                String str6 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str6, "炫彩活体数据失败：status:10 msg:炫彩活体没有data数据");
                                                FaceV3JSPlugin.this.callbackErrorFile("10", "炫彩活体没有data数据");
                                            } else if (i == -1) {
                                                String stringExtra = intent2.getStringExtra("livenessFilePath");
                                                String stringExtra2 = intent2.getStringExtra("status");
                                                String stringExtra3 = intent2.getStringExtra("msg");
                                                String stringExtra4 = intent2.getStringExtra("bizToken");
                                                String str7 = HttpPrivateUtil.faceV3Data;
                                                FaceV3Entity faceV3Entity = new FaceV3Entity();
                                                faceV3Entity.setBizToken(stringExtra4);
                                                faceV3Entity.setStatus(stringExtra2);
                                                faceV3Entity.setMsg(stringExtra3);
                                                faceV3Entity.setData(str7);
                                                faceV3Entity.setLivenessFilePath(stringExtra);
                                                String str8 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str8, "炫彩活体数据成功：livenessFilePath:" + stringExtra);
                                                String str9 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str9, "炫彩活体数据成功：status:" + stringExtra2);
                                                String str10 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str10, "炫彩活体数据成功：msg:" + stringExtra3);
                                                String str11 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str11, "炫彩活体数据成功：bizToken:" + stringExtra4);
                                                FaceV3JSPlugin.this.callbackSucces(faceV3Entity);
                                            } else {
                                                String stringExtra5 = intent2.getStringExtra("status");
                                                String stringExtra6 = intent2.getStringExtra("msg");
                                                String str12 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str12, "炫彩活体数据失败：status:" + stringExtra5 + " msg:" + stringExtra6);
                                                FaceV3JSPlugin.this.callbackErrorFile(stringExtra5, stringExtra6);
                                            }
                                        } catch (Exception e2) {
                                            UIUtils.logE(e2.getMessage());
                                            FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                            faceV3JSPlugin.callbackErrorFile("10", "活体类返回异常:" + e2.getMessage());
                                        }
                                    }
                                });
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                UIUtils.toastLong("拍照错误");
                                FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                faceV3JSPlugin.callbackErrorFile("10", "活体调用异常：" + e2.getMessage());
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
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            } catch (Exception e2) {
                                MsLogUtil.m7978e(e2.getMessage());
                                UIUtils.toast("需要开启摄像头权限");
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            }
                        }
                    });
                    return;
                } else {
                    if (this.type.equals("3")) {
                        try {
                            if (TextUtils.isEmpty(this.initiativeLivenessTimeout) || Integer.parseInt(this.initiativeLivenessTimeout) > 60 || Integer.parseInt(this.initiativeLivenessTimeout) < 10) {
                                this.initiativeLivenessTimeout = "10";
                            }
                        } catch (Exception unused3) {
                            this.initiativeLivenessTimeout = "10";
                        }
                        try {
                            if (TextUtils.isEmpty(this.initiativeLivenessFlashTimeout) || Integer.parseInt(this.initiativeLivenessFlashTimeout) < 60 || Integer.parseInt(this.initiativeLivenessFlashTimeout) > 180) {
                                this.initiativeLivenessFlashTimeout = "180";
                            }
                        } catch (Exception unused4) {
                            this.initiativeLivenessFlashTimeout = "180";
                        }
                        if (TextUtils.isEmpty(this.initiativeFlashColorCount) || (!this.initiativeFlashColorCount.equals("4") && !this.initiativeFlashColorCount.equals("6") && !this.initiativeFlashColorCount.equals("8"))) {
                            this.initiativeFlashColorCount = "4";
                        }
                        if (isCheckStringPositiveNum(this.livenessMegliveType).booleanValue()) {
                            this.livenessMeglliveTypesArrays.add(Integer.valueOf(Integer.parseInt(this.livenessMegliveType)));
                            this.initiativeLivenessActionCount = "1";
                        } else if (this.livenessMegliveTypes != null && this.livenessMegliveTypes.length() > 0 && this.livenessMegliveTypes.length() < 5) {
                            this.livenessMeglliveTypesArrays = new ArrayList<>();
                            int i = 0;
                            while (true) {
                                if (i >= this.livenessMegliveTypes.length()) {
                                    break;
                                }
                                String string = this.livenessMegliveTypes.getString(i);
                                if (isCheckStringPositiveNum(string).booleanValue()) {
                                    this.livenessMeglliveTypesArrays.add(Integer.valueOf(Integer.parseInt(string)));
                                    i++;
                                } else {
                                    this.livenessMeglliveTypesArrays.clear();
                                    break;
                                }
                            }
                            if (this.livenessMeglliveTypesArrays.size() <= 0) {
                                if (!isCheckStringPositiveNum(this.initiativeLivenessActionCount).booleanValue()) {
                                    this.initiativeLivenessActionCount = "1";
                                }
                            } else {
                                this.initiativeLivenessActionCount = String.valueOf(this.livenessMeglliveTypesArrays.size());
                            }
                        } else if (!isCheckStringPositiveNum(this.initiativeLivenessActionCount).booleanValue()) {
                            this.livenessMeglliveTypesArrays = new ArrayList<>();
                            this.initiativeLivenessActionCount = "1";
                        }
                    }
                    PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                    SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.CAMERA"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1
                        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                        public void onAllPermissionOk(Permission[] permissionArr) {
                            try {
                                PermissionDialog.dimissDialog();
                                try {
                                    if (FaceV3JSPlugin.this.f18589wv != null) {
                                        FaceV3JSPlugin.this.urlApp = FaceV3JSPlugin.this.f18589wv.getUrl();
                                    }
                                } catch (Exception unused22) {
                                    FaceV3JSPlugin.this.urlApp = "";
                                }
                                String str4 = FaceV3JSPlugin.this.businessName;
                                String str5 = FaceV3JSPlugin.this.urlApp;
                                JSONObject jSONObject = FaceV3JSPlugin.this.parameterJO;
                                PvCurrencyLogUtils.pvXuanCaiV3(str4, str5, "展示", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "");
                                Intent intent = new Intent(FaceV3JSPlugin.this.activityContext, MainPrivateActivity.class);
                                intent.putExtra("type", FaceV3JSPlugin.this.type);
                                intent.putExtra("biz_token", FaceV3JSPlugin.this.biz_token);
                                if (FaceV3JSPlugin.this.type.equals("1")) {
                                    intent.putExtra("livenessTimeout", FaceV3JSPlugin.this.livenessTimeout);
                                    intent.putExtra("livenessActionCount", FaceV3JSPlugin.this.livenessActionCount);
                                } else if (FaceV3JSPlugin.this.type.equals("2")) {
                                    intent.putExtra("flashLivenessTimeout", FaceV3JSPlugin.this.flashLivenessTimeout);
                                    intent.putExtra("flashColorCount", FaceV3JSPlugin.this.flashColorCount);
                                } else if (FaceV3JSPlugin.this.type.equals("3")) {
                                    intent.putIntegerArrayListExtra("livenessMeglliveTypesArrays", FaceV3JSPlugin.this.livenessMeglliveTypesArrays);
                                    intent.putExtra("initiativeLivenessTimeout", FaceV3JSPlugin.this.initiativeLivenessTimeout);
                                    intent.putExtra("initiativeLivenessFlashTimeout", FaceV3JSPlugin.this.initiativeLivenessFlashTimeout);
                                    intent.putExtra("initiativeFlashColorCount", FaceV3JSPlugin.this.initiativeFlashColorCount);
                                    intent.putExtra("initiativeLivenessActionCount", FaceV3JSPlugin.this.initiativeLivenessActionCount);
                                }
                                new AvoidOnResult(FaceV3JSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.1.1
                                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                    public void onActivityResult(int i2, Intent intent2) {
                                        try {
                                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                            MsLogUtil.m7979d(FaceV3JSPlugin.TAG + format + "faceidv5", "JSPLUGIN 收到数据");
                                            if (intent2 == null) {
                                                String str6 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str6, "炫彩活体数据失败：status:10 msg:炫彩活体没有data数据");
                                                FaceV3JSPlugin.this.callbackErrorFile("10", "炫彩活体没有data数据");
                                            } else if (i2 == -1) {
                                                String stringExtra = intent2.getStringExtra("livenessFilePath");
                                                String stringExtra2 = intent2.getStringExtra("status");
                                                String stringExtra3 = intent2.getStringExtra("msg");
                                                String stringExtra4 = intent2.getStringExtra("bizToken");
                                                String str7 = HttpPrivateUtil.faceV3Data;
                                                FaceV3Entity faceV3Entity = new FaceV3Entity();
                                                faceV3Entity.setBizToken(stringExtra4);
                                                faceV3Entity.setStatus(stringExtra2);
                                                faceV3Entity.setMsg(stringExtra3);
                                                faceV3Entity.setData(str7);
                                                faceV3Entity.setLivenessFilePath(stringExtra);
                                                String str8 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str8, "炫彩活体数据成功：livenessFilePath:" + stringExtra);
                                                String str9 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str9, "炫彩活体数据成功：status:" + stringExtra2);
                                                String str10 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str10, "炫彩活体数据成功：msg:" + stringExtra3);
                                                String str11 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str11, "炫彩活体数据成功：bizToken:" + stringExtra4);
                                                FaceV3JSPlugin.this.callbackSucces(faceV3Entity);
                                            } else {
                                                String stringExtra5 = intent2.getStringExtra("status");
                                                String stringExtra6 = intent2.getStringExtra("msg");
                                                String str12 = FaceV3JSPlugin.TAG;
                                                MsLogUtil.m7979d(str12, "炫彩活体数据失败：status:" + stringExtra5 + " msg:" + stringExtra6);
                                                FaceV3JSPlugin.this.callbackErrorFile(stringExtra5, stringExtra6);
                                            }
                                        } catch (Exception e2) {
                                            UIUtils.logE(e2.getMessage());
                                            FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                            faceV3JSPlugin.callbackErrorFile("10", "活体类返回异常:" + e2.getMessage());
                                        }
                                    }
                                });
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                UIUtils.toastLong("拍照错误");
                                FaceV3JSPlugin faceV3JSPlugin = FaceV3JSPlugin.this;
                                faceV3JSPlugin.callbackErrorFile("10", "活体调用异常：" + e2.getMessage());
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
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            } catch (Exception e2) {
                                MsLogUtil.m7978e(e2.getMessage());
                                UIUtils.toast("需要开启摄像头权限");
                                FaceV3JSPlugin.this.callbackErrorFile("12", "用户未开相机权限");
                            }
                        }
                    });
                    return;
                }
            }
            callbackErrorFile("11", "type参数异常");
        } catch (Exception e2) {
            MsLogUtil.m7978e(e2.getMessage());
            callbackErrorFile("10", "炫彩活体操作失败：" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSucces(FaceV3Entity faceV3Entity) {
        try {
            String fileStr = getFileStr(faceV3Entity.getLivenessFilePath());
            if (TextUtils.isEmpty(fileStr)) {
                MsLogUtil.m7979d(TAG, "base64String:没值");
            }
            if (!TextUtils.isEmpty(faceV3Entity.getData()) && !TextUtils.isEmpty(faceV3Entity.getLivenessFilePath()) && !TextUtils.isEmpty(fileStr)) {
                final JSONObject jSONObject = new JSONObject();
                jSONObject.put("status", faceV3Entity.getStatus());
                jSONObject.put("msg", faceV3Entity.getMsg());
                jSONObject.put("livenessFilePath", faceV3Entity.getLivenessFilePath());
                jSONObject.put("fileBase64String", fileStr);
                jSONObject.put("delta", faceV3Entity.getData());
                this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String str = FaceV3JSPlugin.this.businessName;
                        String str2 = FaceV3JSPlugin.this.urlApp;
                        JSONObject jSONObject2 = FaceV3JSPlugin.this.parameterJO;
                        PvCurrencyLogUtils.pvXuanCaiV3(str, str2, "成功", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), "");
                        FaceV3JSPlugin.this.callbackSuccess(jSONObject);
                    }
                });
            } else {
                callbackErrorFile("10", "数据异常，有空值");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7977e(TAG, e.getMessage());
            callbackErrorFile("10", "最终返回H5结果异常：" + e.getMessage());
        }
        HttpPrivateUtil.faceV3Data = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackErrorFile(String str, String str2) {
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
            this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.3
                @Override // java.lang.Runnable
                public void run() {
                    String str3 = FaceV3JSPlugin.this.businessName;
                    String str4 = FaceV3JSPlugin.this.urlApp;
                    JSONObject jSONObject2 = FaceV3JSPlugin.this.parameterJO;
                    String jSONObject3 = !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2);
                    JSONObject jSONObject4 = jSONObject;
                    PvCurrencyLogUtils.pvXuanCaiV3(str3, str4, "失败", jSONObject3, !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
                    FaceV3JSPlugin.this.callbackFail(jSONObject);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0098 A[Catch: Exception -> 0x00be, TRY_LEAVE, TryCatch #1 {Exception -> 0x00be, blocks: (B:2:0x0000, B:6:0x0009, B:13:0x0029, B:32:0x0092, B:36:0x0098, B:16:0x002e, B:38:0x009d, B:41:0x00a2, B:42:0x00bd, B:27:0x0071, B:30:0x0076), top: B:47:0x0000, inners: #2, #4, #6 }] */
    /* JADX WARN: Type inference failed for: r0v12, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileStr(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> Lbe
            if (r0 == 0) goto L9
            java.lang.String r6 = ""
            return r6
        L9:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> Lbe
            r0.<init>(r6)     // Catch: java.lang.Exception -> Lbe
            boolean r0 = r0.exists()     // Catch: java.lang.Exception -> Lbe
            if (r0 != 0) goto L17
            java.lang.String r6 = ""
            return r6
        L17:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            int r6 = r1.available()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            byte[] r0 = new byte[r6]     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            r1.read(r0)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            r1.close()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            r1.close()     // Catch: java.io.IOException -> L2d java.lang.Exception -> Lbe
            goto L92
        L2d:
            r6 = move-exception
            java.lang.String r1 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.TAG     // Catch: java.lang.Exception -> Lbe
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbe
            r2.<init>()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r3 = "文件转化成base64字符串:{}"
            r2.append(r3)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Exception -> Lbe
            r2.append(r6)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Exception -> Lbe
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r1, r6)     // Catch: java.lang.Exception -> Lbe
            goto L92
        L4a:
            r6 = move-exception
            r0 = r1
            goto L9d
        L4d:
            r6 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L56
        L52:
            r6 = move-exception
            goto L9d
        L54:
            r6 = move-exception
            r1 = r0
        L56:
            java.lang.String r2 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.TAG     // Catch: java.lang.Throwable -> L52
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52
            r3.<init>()     // Catch: java.lang.Throwable -> L52
            java.lang.String r4 = "文件转化成base64字符串:{}"
            r3.append(r4)     // Catch: java.lang.Throwable -> L52
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L52
            r3.append(r6)     // Catch: java.lang.Throwable -> L52
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L52
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r2, r6)     // Catch: java.lang.Throwable -> L52
            r0.close()     // Catch: java.io.IOException -> L75 java.lang.Exception -> Lbe
            goto L91
        L75:
            r6 = move-exception
            java.lang.String r0 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.TAG     // Catch: java.lang.Exception -> Lbe
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbe
            r2.<init>()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r3 = "文件转化成base64字符串:{}"
            r2.append(r3)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Exception -> Lbe
            r2.append(r6)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Exception -> Lbe
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r0, r6)     // Catch: java.lang.Exception -> Lbe
        L91:
            r0 = r1
        L92:
            int r6 = r0.length     // Catch: java.lang.Exception -> Lbe
            if (r6 >= 0) goto L98
            java.lang.String r6 = ""
            return r6
        L98:
            java.lang.String r6 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil.zhuanHuanString(r0)     // Catch: java.lang.Exception -> Lbe
            return r6
        L9d:
            r0.close()     // Catch: java.io.IOException -> La1 java.lang.Exception -> Lbe
            goto Lbd
        La1:
            r0 = move-exception
            java.lang.String r1 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.TAG     // Catch: java.lang.Exception -> Lbe
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbe
            r2.<init>()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r3 = "文件转化成base64字符串:{}"
            r2.append(r3)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Exception -> Lbe
            r2.append(r0)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Exception -> Lbe
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r1, r0)     // Catch: java.lang.Exception -> Lbe
        Lbd:
            throw r6     // Catch: java.lang.Exception -> Lbe
        Lbe:
            java.lang.String r6 = ""
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.FaceV3JSPlugin.getFileStr(java.lang.String):java.lang.String");
    }

    public static Boolean checkStringPositiveNum(String str) {
        try {
            if (!StringUtils.isBlank(str) && str.matches("^[0-9]*[1-9][0-9]*$")) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Boolean isCheckStringPositiveNum(String str) {
        if (!TextUtils.isEmpty(str) && checkStringPositiveNum(str).booleanValue()) {
            if (!str.equals("1") && !str.equals("2") && !str.equals("3") && !str.equals("4")) {
                return false;
            }
            return true;
        }
        return false;
    }
}
