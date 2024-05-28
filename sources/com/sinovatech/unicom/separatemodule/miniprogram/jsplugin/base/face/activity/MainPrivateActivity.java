package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.megvii.lv5.sdk.bean.MegliveLocalFileInfo;
import com.megvii.lv5.sdk.listener.GetConfigCallback;
import com.megvii.lv5.sdk.listener.MegliveRequestFinishCallback;
import com.megvii.lv5.sdk.manager.LivenessTypeE;
import com.megvii.lv5.sdk.manager.MegLiveDetectPrivateConfig;
import com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener;
import com.megvii.lv5.sdk.manager.MegLivePrivateManager;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.entity.FaceV3Entity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpCallBackListener;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainPrivateActivity extends AppCompatActivity {
    private static final String TAG = "FaceV3JSPlugin_MainPrivateActivity";
    private String GET_LISENCE_AND_CONFIG_URL;
    private LivenessTypeE LivenessType;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private String biz_token;
    private String flashColorCount;
    private String flashLivenessTimeout;
    private String initiativeFlashColorCount;
    private String initiativeLivenessActionCount;
    private String initiativeLivenessFlashTimeout;
    private String initiativeLivenessTimeout;
    private String livenessActionCount;
    private String livenessTimeout;
    private String modelPath;
    private String type;
    private ArrayList<Integer> livenessMeglliveTypesArrays = null;
    private FaceV3Entity faceV3Entity = null;
    private boolean isUse = true;
    private boolean goOnDetectFinish = false;
    private boolean goOnLivenessFileCallback = false;

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        try {
            if (Build.VERSION.SDK_INT != 26) {
                setRequestedOrientation(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.activityContext = this;
        this.modelPath = saveAssets("faceidmodel.bin", "model");
        this.GET_LISENCE_AND_CONFIG_URL = URLSet.getFaceV3Url();
        Intent intent = getIntent();
        this.type = intent.getStringExtra("type");
        this.biz_token = intent.getStringExtra("biz_token");
        if (this.type.equals("1")) {
            this.LivenessType = LivenessTypeE.Meglive;
            this.livenessTimeout = intent.getStringExtra("livenessTimeout");
            this.livenessActionCount = intent.getStringExtra("livenessActionCount");
        } else if (this.type.equals("2")) {
            this.LivenessType = LivenessTypeE.Flash;
            this.flashLivenessTimeout = intent.getStringExtra("flashLivenessTimeout");
            this.flashColorCount = intent.getStringExtra("flashColorCount");
        } else if (this.type.equals("3")) {
            this.LivenessType = LivenessTypeE.Initiative_Flash;
            this.livenessMeglliveTypesArrays = intent.getIntegerArrayListExtra("livenessMeglliveTypesArrays");
            this.initiativeLivenessTimeout = intent.getStringExtra("initiativeLivenessTimeout");
            this.initiativeLivenessFlashTimeout = intent.getStringExtra("initiativeLivenessFlashTimeout");
            this.initiativeFlashColorCount = intent.getStringExtra("initiativeFlashColorCount");
            this.initiativeLivenessActionCount = intent.getStringExtra("initiativeLivenessActionCount");
        }
        startDetect();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void startDetect() {
        MegLiveDetectPrivateConfig megLiveDetectPrivateConfig = new MegLiveDetectPrivateConfig();
        megLiveDetectPrivateConfig.setUrl(this.GET_LISENCE_AND_CONFIG_URL);
        megLiveDetectPrivateConfig.setModelPath(this.modelPath);
        megLiveDetectPrivateConfig.setBiztoken(this.biz_token);
        try {
            if (this.type.equals("1")) {
                try {
                    megLiveDetectPrivateConfig.setLivenessTimeout(Integer.parseInt(this.livenessTimeout));
                } catch (Exception unused) {
                }
                int parseInt = Integer.parseInt(this.livenessActionCount);
                if (parseInt > 0 && parseInt <= 3) {
                    megLiveDetectPrivateConfig.setLivenessActionCount(parseInt);
                }
            } else if (this.type.equals("2")) {
                try {
                    megLiveDetectPrivateConfig.setFlashLivenessTimeout(Integer.parseInt(this.flashLivenessTimeout));
                } catch (Exception unused2) {
                }
                megLiveDetectPrivateConfig.setFlashColorCount(Integer.parseInt(this.flashColorCount));
            } else if (this.type.equals("3")) {
                int[] iArr = new int[0];
                if (this.livenessMeglliveTypesArrays != null && this.livenessMeglliveTypesArrays.size() > 0) {
                    int[] iArr2 = new int[this.livenessMeglliveTypesArrays.size()];
                    for (int i = 0; i < this.livenessMeglliveTypesArrays.size(); i++) {
                        iArr2[i] = this.livenessMeglliveTypesArrays.get(i).intValue();
                    }
                    megLiveDetectPrivateConfig.setLivenessMegliveTypes(iArr2);
                }
                if (!TextUtils.isEmpty(this.initiativeLivenessTimeout)) {
                    megLiveDetectPrivateConfig.setInitiativeLivenessTimeout(Integer.parseInt(this.initiativeLivenessTimeout));
                }
                if (!TextUtils.isEmpty(this.initiativeLivenessFlashTimeout)) {
                    megLiveDetectPrivateConfig.setInitiativeLivenessFlashTimeout(Integer.parseInt(this.initiativeLivenessFlashTimeout));
                }
                if (!TextUtils.isEmpty(this.initiativeFlashColorCount)) {
                    megLiveDetectPrivateConfig.setInitiativeFlashColorCount(Integer.parseInt(this.initiativeFlashColorCount));
                }
                if (!TextUtils.isEmpty(this.initiativeLivenessActionCount)) {
                    megLiveDetectPrivateConfig.setInitiativeLivenessActionCount(Integer.valueOf(Integer.parseInt(this.initiativeLivenessActionCount)));
                }
            }
        } catch (Exception unused3) {
        }
        megLiveDetectPrivateConfig.setLivenessType(this.LivenessType);
        megLiveDetectPrivateConfig.setMode(1);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        MsLogUtil.m7979d(TAG + format + "faceidv5-", "开始做活体startDetect");
        MegLivePrivateManager.getInstance().startDetect(this.activityContext, megLiveDetectPrivateConfig, new GetConfigCallback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity.1
            @Override // com.megvii.lv5.sdk.listener.GetConfigCallback
            public void onGetConfig(String str, final MegliveRequestFinishCallback megliveRequestFinishCallback) {
                HttpPrivateUtil.getLisenceAndConfigPrivate(MainPrivateActivity.this.activityContext, MainPrivateActivity.this.GET_LISENCE_AND_CONFIG_URL, str, MainPrivateActivity.this.biz_token, new HttpCallBackListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity.1.1
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpCallBackListener
                    public void onSuccess(String str2) {
                        MsLogUtil.m7979d(MainPrivateActivity.TAG, "onGetConfig -->onSuccess: responseBody = " + str2);
                        String str3 = null;
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("data", new JSONObject(str2));
                            jSONObject.put("success", true);
                            jSONObject.put("code", "00000");
                            jSONObject.put("message", (Object) null);
                            jSONObject.put("messageDetail", (Object) null);
                            str3 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        megliveRequestFinishCallback.onFinish(str3);
                    }

                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpCallBackListener
                    public void onFailure(int i2, String str2) {
                        MsLogUtil.m7979d(MainPrivateActivity.TAG, "onGetConfig-->onFailure: statusCode = " + i2);
                        MsLogUtil.m7979d(MainPrivateActivity.TAG, "onGetConfig-->onFailure: responseBody = " + str2);
                        MainPrivateActivity mainPrivateActivity = MainPrivateActivity.this;
                        mainPrivateActivity.handleResult(false, "10", "接口请求失败:" + str2, null);
                    }
                });
            }
        }, new MegLiveDetectPrivateListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity.2
            @Override // com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener
            public void onPreDetectFinish(int i2, String str) {
                MsLogUtil.m7979d(MainPrivateActivity.TAG + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "faceidv5-", "第一步预处理onPreDetectFinish : errorCode=" + i2 + ",errorMessage=" + str);
                if (i2 != 1000) {
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    MainPrivateActivity.this.handleResult(false, String.valueOf(i2), str, null);
                }
            }

            @Override // com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener
            public void onDetectFinish(int i2, String str, String str2, byte[] bArr) {
                MsLogUtil.m7979d(MainPrivateActivity.TAG + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "faceidv5-", "onDetectFinish 最后一步: errorCode=" + i2);
                MainPrivateActivity.this.goOnDetectFinish = true;
                if (i2 != 1000) {
                    MainPrivateActivity.this.faceV3Entity = null;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    MainPrivateActivity.this.handleResult(false, String.valueOf(i2), str, null);
                    return;
                }
                String zhuanHuanString = HttpPrivateUtil.zhuanHuanString(bArr);
                if (MainPrivateActivity.this.faceV3Entity == null) {
                    MainPrivateActivity.this.faceV3Entity = new FaceV3Entity();
                }
                MainPrivateActivity.this.faceV3Entity.setStatus("0000");
                if (!TextUtils.isEmpty(str)) {
                    MainPrivateActivity.this.faceV3Entity.setMsg(str);
                } else {
                    MainPrivateActivity.this.faceV3Entity.setMsg("");
                }
                if (!TextUtils.isEmpty(str2)) {
                    MainPrivateActivity.this.faceV3Entity.setBizToken(str2);
                } else {
                    MainPrivateActivity.this.faceV3Entity.setBizToken("");
                }
                MainPrivateActivity.this.faceV3Entity.setData(zhuanHuanString);
                if (MainPrivateActivity.this.goOnLivenessFileCallback) {
                    MainPrivateActivity mainPrivateActivity = MainPrivateActivity.this;
                    mainPrivateActivity.handleResult(true, "", "", mainPrivateActivity.faceV3Entity);
                }
            }

            @Override // com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener
            public void onLivenessFileCallback(String str) {
                MsLogUtil.m7979d(MainPrivateActivity.TAG + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "faceidv-", "onLivenessFileCallback 获取到地址路径: livenessFilePath=" + str);
                MainPrivateActivity.this.goOnLivenessFileCallback = true;
                if (MainPrivateActivity.this.faceV3Entity == null) {
                    MainPrivateActivity.this.faceV3Entity = new FaceV3Entity();
                }
                if (!TextUtils.isEmpty(str)) {
                    MainPrivateActivity.this.faceV3Entity.setLivenessFilePath(str);
                }
                if (MainPrivateActivity.this.goOnDetectFinish) {
                    MainPrivateActivity mainPrivateActivity = MainPrivateActivity.this;
                    mainPrivateActivity.handleResult(true, "", "", mainPrivateActivity.faceV3Entity);
                }
            }

            @Override // com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener
            public void onLivenessLocalFileCallBack(MegliveLocalFileInfo megliveLocalFileInfo) {
                String filePath = megliveLocalFileInfo.getFilePath();
                String scrrenFilePath = megliveLocalFileInfo.getScrrenFilePath();
                MsLogUtil.m7979d("FaceV3JSPlugin_MainPrivateActivityfaceidv5", "结果回调地址 onLivenessLocalFileCallBack : filePath=" + filePath);
                MsLogUtil.m7979d("FaceV3JSPlugin_MainPrivateActivityfaceidv5", "结果回调地址 onLivenessLocalFileCallBack : scrrenFilePath=" + scrrenFilePath);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x007b A[Catch: Exception -> 0x0077, TRY_LEAVE, TryCatch #3 {Exception -> 0x0077, blocks: (B:44:0x0073, B:48:0x007b), top: B:56:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String saveAssets(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "megvii"
            java.io.File r1 = r5.getExternalFilesDir(r1)
            r0.<init>(r1, r7)
            boolean r7 = r0.exists()
            r1 = 0
            if (r7 != 0) goto L19
            boolean r7 = r0.mkdirs()
            if (r7 != 0) goto L19
            return r1
        L19:
            java.io.File r7 = new java.io.File
            r7.<init>(r0, r6)
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            android.content.res.AssetManager r3 = r5.getAssets()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            java.io.InputStream r6 = r3.open(r6)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
        L2f:
            int r3 = r6.read(r0)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6f
            r4 = -1
            if (r3 == r4) goto L3b
            r4 = 0
            r2.write(r0, r4, r3)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6f
            goto L2f
        L3b:
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6f
            r2.close()     // Catch: java.lang.Exception -> L48
            if (r6 == 0) goto L4c
            r6.close()     // Catch: java.lang.Exception -> L48
            goto L4c
        L48:
            r6 = move-exception
            r6.printStackTrace()
        L4c:
            return r7
        L4d:
            r7 = move-exception
            goto L5a
        L4f:
            r7 = move-exception
            goto L71
        L51:
            r7 = move-exception
            r6 = r1
            goto L5a
        L54:
            r7 = move-exception
            r2 = r1
            goto L71
        L57:
            r7 = move-exception
            r6 = r1
            r2 = r6
        L5a:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L6f
            if (r2 == 0) goto L65
            r2.close()     // Catch: java.lang.Exception -> L63
            goto L65
        L63:
            r6 = move-exception
            goto L6b
        L65:
            if (r6 == 0) goto L6e
            r6.close()     // Catch: java.lang.Exception -> L63
            goto L6e
        L6b:
            r6.printStackTrace()
        L6e:
            return r1
        L6f:
            r7 = move-exception
            r1 = r6
        L71:
            if (r2 == 0) goto L79
            r2.close()     // Catch: java.lang.Exception -> L77
            goto L79
        L77:
            r6 = move-exception
            goto L7f
        L79:
            if (r1 == 0) goto L82
            r1.close()     // Catch: java.lang.Exception -> L77
            goto L82
        L7f:
            r6.printStackTrace()
        L82:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity.saveAssets(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(boolean z, String str, String str2, FaceV3Entity faceV3Entity) {
        if (this.isUse) {
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            MsLogUtil.m7979d("FaceV3JSPlugin_MainPrivateActivityfaceidv5", "handleResult 准备将数据回传-" + format);
            this.isUse = false;
            Intent intent = new Intent();
            try {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                }
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                if (z && faceV3Entity != null && !TextUtils.isEmpty(faceV3Entity.getData()) && !TextUtils.isEmpty(faceV3Entity.getLivenessFilePath())) {
                    intent.putExtra("livenessFilePath", faceV3Entity.getLivenessFilePath());
                    intent.putExtra("status", faceV3Entity.getStatus());
                    intent.putExtra("msg", faceV3Entity.getMsg());
                    intent.putExtra("bizToken", faceV3Entity.getBizToken());
                    HttpPrivateUtil.faceV3Data = faceV3Entity.getData();
                    setResult(-1, intent);
                } else {
                    intent.putExtra("status", str);
                    intent.putExtra("msg", str2);
                    setResult(0, intent);
                }
                finish();
            } catch (Exception e) {
                intent.putExtra("status", "10");
                intent.putExtra("msg", "活体V3异常：" + e.getMessage());
                setResult(0, intent);
                finish();
            }
        }
    }
}
