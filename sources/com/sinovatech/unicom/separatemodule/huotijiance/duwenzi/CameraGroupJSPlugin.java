package com.sinovatech.unicom.separatemodule.huotijiance.duwenzi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity;
import com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FlymePermissionUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/cameraGroup")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CameraGroupJSPlugin extends BaseJSPlugin {
    private static final String TAG = "cameraGroupJSPlugin";
    private HuoTiEntity huoTiEntity;
    private String videoSize;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String jSONObject;
        try {
            String optString = this.parameterJO.optString("type");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            if (TextUtils.equals("recordFaceVideo", optString)) {
                HuoTiEntity huoTiEntity = new HuoTiEntity();
                String optString2 = optJSONObject.optString("readContent", "");
                String optString3 = optJSONObject.optString("minDuration", "1");
                String optString4 = optJSONObject.optString("maxDuration", "6");
                String optString5 = optJSONObject.optString("uploadUrl", "");
                String optString6 = optJSONObject.optString("name", "");
                String optString7 = optJSONObject.optString(DetectionAuthentic.FRAME, "");
                String optString8 = optJSONObject.optString("waterMark", "");
                boolean optBoolean = optJSONObject.optBoolean("isUpLoad", true);
                String str = "请使用前置摄像头，录制30秒视频\n录制时请匀速朗读一遍下方内容";
                String optString9 = optJSONObject.optString("hintText", "请使用前置摄像头，录制30秒视频\n录制时请匀速朗读一遍下方内容");
                if (!TextUtils.isEmpty(optString9)) {
                    str = optString9;
                }
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("formData");
                huoTiEntity.setUpLoad(optBoolean);
                huoTiEntity.setReadNumber(optString2);
                huoTiEntity.setMinDuration(optString3);
                huoTiEntity.setMaxDuration(optString4);
                huoTiEntity.setUrl(optString5);
                huoTiEntity.setFrame(optString7);
                huoTiEntity.setName(optString6);
                huoTiEntity.setWater(optString8);
                if (optJSONObject2 == null) {
                    jSONObject = "";
                } else {
                    jSONObject = !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2);
                }
                huoTiEntity.setFormData(jSONObject);
                huoTiEntity.setPortrait(true);
                huoTiEntity.setHintText(str);
                takeVideo(huoTiEntity);
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    private void takeVideo(final HuoTiEntity huoTiEntity) {
        try {
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.1
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    Intent intent;
                    PermissionDialog.dimissDialog();
                    if (!FlymePermissionUtil.isCameraCanUse() || !FlymePermissionUtil.isHasPermission(CameraGroupJSPlugin.this.activityContext)) {
                        CameraGroupJSPlugin.this.backResult(1, "用户取消授权");
                        CameraGroupJSPlugin.this.showToast("为保证您正常、安全的使用中国联通APP视频录制功能，需要获取您的相机、麦克风以及存储卡权限，请您到 手机－设置－应用管理－权限管理 中开启相关权限后，再使用此功能。");
                        return;
                    }
                    String stringAddDefaultData = App.getSharePreferenceUtil().getStringAddDefaultData("video_water_is_open", "1");
                    if (TextUtils.isEmpty(huoTiEntity.getWater()) || "2".equals(stringAddDefaultData)) {
                        intent = new Intent(CameraGroupJSPlugin.this.activityContext, HuoTiDuWenZiActivity.class);
                    } else {
                        intent = new Intent(CameraGroupJSPlugin.this.activityContext, UnicomWaterMarkActivity.class);
                    }
                    intent.putExtra("entity", huoTiEntity);
                    new AvoidOnResult(CameraGroupJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.1.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            if (i != -1 || intent2 == null) {
                                CameraGroupJSPlugin.this.backResult(1, "取消上传");
                                return;
                            }
                            CameraGroupJSPlugin.this.huoTiEntity = (HuoTiEntity) intent2.getParcelableExtra("entity");
                            String stringExtra = intent2.getStringExtra("videoUrl");
                            CameraGroupJSPlugin.this.videoSize = intent2.getStringExtra("videoSize");
                            if (TextUtils.isEmpty(stringExtra)) {
                                return;
                            }
                            if (CameraGroupJSPlugin.this.huoTiEntity != null) {
                                PvCurrencyLogUtils.pvHuoTiFaceVideoBDLog("上传视频开始", TextUtils.isEmpty(CameraGroupJSPlugin.this.huoTiEntity.getUrl()) ? "" : CameraGroupJSPlugin.this.huoTiEntity.getUrl(), CameraGroupJSPlugin.this.videoSize, "成功");
                            }
                            CameraGroupJSPlugin.this.upLoadVideo(CameraGroupJSPlugin.this.huoTiEntity, stringExtra);
                        }
                    });
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    PermissionDialog.dimissDialog();
                    CameraGroupJSPlugin.this.backResult(1, "用户取消授权");
                    CameraGroupJSPlugin.this.showToast("为保证您正常、安全的使用中国联通APP视频录制功能，需要获取您的相机、麦克风以及存储卡权限，请您到 手机－设置－应用管理－权限管理 中开启相关权限后，再使用此功能。");
                }
            });
        } catch (Exception e) {
            callbackFail(e.getMessage());
            showToast("数据解析错误" + e.getMessage());
            UIUtils.logE(TAG, "onJsAlert: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadVideo(HuoTiEntity huoTiEntity, String str) {
        new HuoTiWenZiUpload(this.activityContext, huoTiEntity, str, new MyAsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.2
            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
                try {
                    UIUtils.logD("lln", "content==" + str2 + "----statusCode" + i);
                    String str3 = "";
                    String str4 = "";
                    if (200 == i) {
                        JSONObject jSONObject = new JSONObject(str2);
                        str3 = jSONObject.optString("respCode");
                        str4 = jSONObject.optString("respDesc");
                        if (TextUtils.equals("0000", str3)) {
                            CameraGroupJSPlugin.this.backResult(0, str2);
                            CameraGroupJSPlugin.this.showToast("上传成功");
                            return;
                        }
                    }
                    CameraGroupJSPlugin cameraGroupJSPlugin = CameraGroupJSPlugin.this;
                    cameraGroupJSPlugin.backResult(1, "code = " + str3 + " 上传失败 =" + str4);
                } catch (Exception e) {
                    CameraGroupJSPlugin.this.backResult(1, e.getMessage());
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
            public void onSuccess(int i, String str2, String str3) {
                super.onSuccess(i, str2, str3);
                try {
                    UIUtils.logD("lln", "content==" + str2 + "----statusCode" + i);
                    if (200 != i || TextUtils.isEmpty(str2) || !str2.equals("活体文字录制成功")) {
                        CameraGroupJSPlugin.this.backResult(1, "录制失败");
                    } else {
                        CameraGroupJSPlugin.this.backResult(0, "录制成功", str3);
                    }
                } catch (Exception e) {
                    CameraGroupJSPlugin.this.backResult(1, e.getMessage());
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
                th.printStackTrace();
                CameraGroupJSPlugin.this.backResult(1, th.getLocalizedMessage());
                CameraGroupJSPlugin cameraGroupJSPlugin = CameraGroupJSPlugin.this;
                cameraGroupJSPlugin.showToast("上传失败" + str2);
                UIUtils.logD("lln", "content333==" + str2 + "----error" + th);
            }

            @Override // com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
            }
        }).compressionOne();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void backResult(int i, String str, String str2) {
        final JSONObject jSONObject = new JSONObject();
        if (i != 0) {
            try {
                if (TextUtils.isEmpty(str)) {
                    str = "参数无效或参数为空";
                }
            } catch (Exception e) {
                UIUtils.logE(e.getMessage());
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("msg", str);
        jSONObject.put("resultCode", String.valueOf(i));
        jSONObject.put("resultInfo", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        if (i == 0 && !TextUtils.isEmpty(str2)) {
            jSONObject.put("videoUrl", str2);
            PvCurrencyLogUtils.pvHuoTiFaceVideoBDLog("录制视频结束", str2, this.videoSize, "成功");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.3
                @Override // java.lang.Runnable
                public void run() {
                    CameraGroupJSPlugin.this.callbackSuccess(jSONObject);
                }
            });
            return;
        }
        PvCurrencyLogUtils.pvHuoTiFaceVideoBDLog("录制视频结束", "", "", str);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.4
            @Override // java.lang.Runnable
            public void run() {
                CameraGroupJSPlugin.this.callbackFail(jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void backResult(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        if (i != 0) {
            try {
                if (TextUtils.isEmpty(str)) {
                    str = "参数无效或参数为空";
                }
            } catch (Exception e) {
                UIUtils.logE(e.getMessage());
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("msg", str);
        jSONObject.put("resultCode", String.valueOf(i));
        jSONObject.put("resultInfo", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        if (i == 0) {
            HuoTiEntity huoTiEntity = this.huoTiEntity;
            if (huoTiEntity != null) {
                PvCurrencyLogUtils.pvHuoTiFaceVideoBDLog("上传视频结束", TextUtils.isEmpty(huoTiEntity.getUrl()) ? "" : this.huoTiEntity.getUrl(), this.videoSize, "成功");
            }
            callbackSuccess(jSONObject);
            return;
        }
        HuoTiEntity huoTiEntity2 = this.huoTiEntity;
        if (huoTiEntity2 != null) {
            String url = TextUtils.isEmpty(huoTiEntity2.getUrl()) ? "" : this.huoTiEntity.getUrl();
            if (TextUtils.isEmpty(url)) {
                str = "视频上传Url为空";
            }
            PvCurrencyLogUtils.pvHuoTiFaceVideoBDLog("上传视频结束", url, this.videoSize, str);
        }
        callbackFail(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(final String str) {
        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.CameraGroupJSPlugin.5
            @Override // java.lang.Runnable
            public void run() {
                UIUtils.toast(str);
            }
        });
    }
}
