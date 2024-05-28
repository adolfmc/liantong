package com.sinovatech.unicom.basic.p315ui.manager;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.example.asus.detectionandalign.ActionConfig;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.example.asus.detectionandalign.LivenessConfig;
import com.example.asus.detectionandalign.listener.ResultListener;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.adapter.SimplePermissionAdapter;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerGZT */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerGZT implements ResultListener {
    private AppCompatActivity activityContext;
    private DetectionAuthentic authentic;
    private String imgType;
    private boolean isFont;
    private String method;
    private String params;
    private String urlInfo;
    private PBWebView webView;
    private int totalActions = 3;
    private int timeoutMs = 10;
    private int totalPictures = 3;
    private LivenessConfig livenessConfig = new LivenessConfig();
    private ActionConfig actionConfig = new ActionConfig();

    public ManagerGZT(AppCompatActivity appCompatActivity, PBWebView pBWebView) {
        this.activityContext = appCompatActivity;
        this.webView = pBWebView;
        this.authentic = DetectionAuthentic.getInstance(appCompatActivity, this);
        ArrayList arrayList = new ArrayList(Arrays.asList("eye", "mouth", "headUp", "headR", "headL"));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(Double.valueOf(0.5d), Double.valueOf(0.1d), Double.valueOf(0.2d), Double.valueOf(0.7d), Double.valueOf(0.7d)));
        this.actionConfig.setLivenessParam(0.5f);
        this.actionConfig.setEnfiladeParam(0.7f);
        this.actionConfig.setIsFixAction(false);
        this.actionConfig.setIsActionLivenessCheck(false);
        this.actionConfig.setIsVerifyAnimation(false);
        this.actionConfig.setIsTailor(true);
        this.actionConfig.setIsPreposition(true);
        this.actionConfig.setFixActionList(arrayList);
        this.actionConfig.setActionThresholdList(arrayList2);
    }

    public void start(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("msg"));
            this.method = jSONObject2.optString("method");
            this.imgType = jSONObject2.optString("imgType");
            this.params = jSONObject2.optString("params");
            this.urlInfo = jSONObject.optString("url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PermissionDialog.show("刷脸认证为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermission("android.permission.CAMERA", new SimplePermissionAdapter() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerGZT.1
            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionOk(Permission permission) {
                super.onPermissionOk(permission);
                try {
                    PermissionDialog.dimissDialog();
                    if (!"captureIdCard".equals(ManagerGZT.this.method)) {
                        if ("captureAction".equals(ManagerGZT.this.method)) {
                            ManagerGZT.this.starActivieLive();
                            StatisticsUploadUtils.uploadBeiDong(ManagerGZT.this.activityContext, "web100002", "活体认证-国政通", "展示", "0", "captureAction", ManagerGZT.this.urlInfo, "", "", ",", "");
                        }
                    } else {
                        StatisticsUploadUtils.upload(ManagerGZT.this.activityContext, "web100001", "活体认证-国政通", "展示", "0", "captureIdCard", ManagerGZT.this.urlInfo);
                        if ("1".equals(ManagerGZT.this.imgType)) {
                            ManagerGZT.this.starIDCardFront();
                        } else if ("2".equals(ManagerGZT.this.imgType)) {
                            ManagerGZT.this.startIDCardBack();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionDenied(Permission permission) {
                super.onPermissionDenied(permission);
                PermissionDialog.dimissDialog();
                UIUtils.toast("需要开启摄像头权限");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void starIDCardFront() {
        try {
            this.isFont = true;
            this.authentic.autenticateToShotIdCard(this.activityContext, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startIDCardBack() {
        try {
            this.isFont = false;
            this.authentic.autenticateToShotIdCard(this.activityContext, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void starActivieLive() {
        try {
            this.authentic.autenticateToCaptureAction(this.activityContext, this.totalActions, this.timeoutMs, this.actionConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.example.asus.detectionandalign.listener.ResultListener
    public void onFaceImageCaptured(String str) {
        final HashMap hashMap = new HashMap();
        hashMap.put("params", this.params);
        try {
            hashMap.put("data", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerGZT.2
            @Override // java.lang.Runnable
            public void run() {
                ManagerGZT.this.webView.post(ManagerGZT.this.urlInfo, hashMap);
            }
        });
        StatisticsUploadUtils.upload(this.activityContext, "web100003", "活体认证-国政通", "展示", "0", "活体回调状态", this.urlInfo);
    }

    @Override // com.example.asus.detectionandalign.listener.ResultListener
    public void captureSuccess(Bitmap bitmap) {
        final HashMap hashMap = new HashMap();
        hashMap.put("params", this.params);
        if (this.isFont) {
            hashMap.put("imgType", "1");
        } else {
            hashMap.put("imgType", "2");
        }
        try {
            hashMap.put("data", FileTools.bitmapToBase642(FileTools.rotaingImageView(bitmap)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerGZT.3
            @Override // java.lang.Runnable
            public void run() {
                ManagerGZT.this.webView.post(ManagerGZT.this.urlInfo, hashMap);
            }
        });
        StatisticsUploadUtils.upload(this.activityContext, "web100004", "活体认证-国政通", "展示", "0", "活体回调状态", this.urlInfo);
    }

    @Override // com.example.asus.detectionandalign.listener.ResultListener
    public void onSDKUsingFail(final String str, final String str2) {
        new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerGZT.4
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("params", ManagerGZT.this.params);
                hashMap.put("errorMessage", str);
                hashMap.put("errorCode", str2);
                ManagerGZT.this.webView.post(ManagerGZT.this.urlInfo, hashMap);
            }
        });
        StatisticsUploadUtils.upload(this.activityContext, "web100005", "活体认证-国政通", "展示", "0", "识别失败", this.urlInfo);
    }
}
