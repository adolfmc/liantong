package com.sinovatech.unicom.separatemodule.esim;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.euicc.server.IOpenEUICC;
import com.euicc.server.IServiceBinder;
import com.huawei.multisimservice.IOpenMultiSim;
import com.huawei.multisimservice.IServiceBinder;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHuaweiWatch {
    private static final String BONE_PACKAGE_NAME = "com.huawei.bone";
    private static final String CLASS_NAME_MULTI_SIM_SERVICE = "com.huawei.multisimservice.MultiSimService";
    private static final String HEALTH_PACKEAGE_NAME = "com.huawei.health";
    private boolean binRet1;
    private boolean binRet2;
    private AppCompatActivity context;
    private int deviceType1_2;
    private int deviceType2_2;
    private IOpenEUICC iOpenMultiSim;
    private IServiceBinder iServiceBinder;
    private boolean isDownLoadESMI;
    private IOpenMultiSim mService2;
    private String prilCode_2;
    private int resultCode1_2;
    private int resultCode2_2;
    private PBWebView webView;
    private Handler webViewHandler;
    private int linkCode = 1;
    private String packageName = HEALTH_PACKEAGE_NAME;
    private ServiceConnection mConnection2 = new ServiceConnection() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                ManagerHuaweiWatch.this.mService2 = IOpenMultiSim.Stub.asInterface(IServiceBinder.Stub.asInterface(iBinder).getServiceBinder("com.sinovatech.unicom.ui"));
                ManagerHuaweiWatch.this.mService2.registerCallback(ManagerHuaweiWatch.this.mCallback2);
                UIUtils.logD("onServiceConnected", "华为手表连接成功true");
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            if (z) {
                if (ManagerHuaweiWatch.this.isDownLoadESMI) {
                    ManagerHuaweiWatch.this.successDwonLoadEsmi();
                } else {
                    ManagerHuaweiWatch.this.webView.loadURL("javascript:backConnectResult('1')");
                }
            } else if (ManagerHuaweiWatch.this.isDownLoadESMI) {
                ManagerHuaweiWatch.this.FailDwonLoadEsmi();
            } else {
                ManagerHuaweiWatch.this.webView.loadURL("javascript:backConnectResult('0')");
                ManagerHuaweiWatch.this.hasCallBack = true;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ManagerHuaweiWatch.this.mService2 = null;
        }
    };
    private IOpenMultiSimCalbcak.Stub mCallback2 = new IOpenMultiSimCalbcak.Stub() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.4
        @Override // com.huawei.multisimservice.model.IOpenMultiSimCalbcak
        public void getDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) {
            Handler handler;
            Runnable runnable;
            String str;
            String str2;
            final JSONObject jSONObject = new JSONObject();
            try {
                try {
                    jSONObject.put("ResultCode", multiSimDeviceInfo.getResultCode());
                    jSONObject.put("DeviceType", multiSimDeviceInfo.getDeviceType());
                    jSONObject.put("DeviceID", multiSimDeviceInfo.getDeviceID());
                    jSONObject.put("DeviceIMEI", multiSimDeviceInfo.getDeviceIMEI());
                    jSONObject.put("DeviceSerialNumber", multiSimDeviceInfo.getDeviceSerialNumber());
                    jSONObject.put("ProductName", multiSimDeviceInfo.getProductName());
                    jSONObject.put("EID", multiSimDeviceInfo.getEID());
                    String str3 = "";
                    String str4 = "";
                    if (multiSimDeviceInfo.getDeviceType() == 1) {
                        List<SimInfo> simInfoList = multiSimDeviceInfo.getSimInfoList();
                        if (simInfoList != null && simInfoList.size() > 0) {
                            jSONObject.put("IMSI", simInfoList.get(0).getIMSI());
                            jSONObject.put("ICCID", simInfoList.get(0).getICCID());
                            String imsi = simInfoList.get(0).getIMSI();
                            str4 = imsi;
                            str3 = simInfoList.get(0).getICCID();
                        } else {
                            jSONObject.put("IMSI", "");
                            jSONObject.put("ICCID", "");
                        }
                        str = str3;
                        str2 = str4;
                    } else {
                        if (multiSimDeviceInfo.getDeviceType() == 2) {
                            if (multiSimDeviceInfo.getActiveSimProfileInfo() != null) {
                                jSONObject.put("IMSI", multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI());
                                jSONObject.put("ICCID", multiSimDeviceInfo.getActiveSimProfileInfo().getICCID());
                                String imsi2 = multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI();
                                str = multiSimDeviceInfo.getActiveSimProfileInfo().getICCID();
                                str2 = imsi2;
                            } else {
                                jSONObject.put("IMSI", "");
                                jSONObject.put("ICCID", "");
                            }
                        } else {
                            jSONObject.put("IMSI", "");
                            jSONObject.put("ICCID", "");
                        }
                        str = "";
                        str2 = "";
                    }
                    try {
                        String[] esimParams = EsimUtil.getEsimParams(ManagerHuaweiWatch.this.context, ManagerHuaweiWatch.this.webView, multiSimDeviceInfo.getEID(), multiSimDeviceInfo.getDeviceID(), str, str2);
                        jSONObject.put("arg1", esimParams[0]);
                        jSONObject.put("arg2", esimParams[1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler = ManagerHuaweiWatch.this.webViewHandler;
                    runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = ManagerHuaweiWatch.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:try{backMultiSimInfo(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")}catch(ex){}");
                            pBWebView.evaluateJavascript(sb.toString(), null);
                            ManagerHuaweiWatch.this.hasCallBack = true;
                        }
                    };
                } catch (Exception e2) {
                    e2.printStackTrace();
                    handler = ManagerHuaweiWatch.this.webViewHandler;
                    runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = ManagerHuaweiWatch.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:try{backMultiSimInfo(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")}catch(ex){}");
                            pBWebView.evaluateJavascript(sb.toString(), null);
                            ManagerHuaweiWatch.this.hasCallBack = true;
                        }
                    };
                }
                handler.post(runnable);
                ManagerHuaweiWatch.this.disconnectHWService2();
            } catch (Throwable th) {
                ManagerHuaweiWatch.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PBWebView pBWebView = ManagerHuaweiWatch.this.webView;
                        StringBuilder sb = new StringBuilder();
                        sb.append("javascript:try{backMultiSimInfo(");
                        JSONObject jSONObject2 = jSONObject;
                        sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                        sb.append(")}catch(ex){}");
                        pBWebView.evaluateJavascript(sb.toString(), null);
                        ManagerHuaweiWatch.this.hasCallBack = true;
                    }
                });
                ManagerHuaweiWatch.this.disconnectHWService2();
                throw th;
            }
        }
    };
    private boolean hasCallBack = false;

    public ManagerHuaweiWatch(AppCompatActivity appCompatActivity, final PBWebView pBWebView, Handler handler) {
        this.context = appCompatActivity;
        this.webView = pBWebView;
        this.webViewHandler = handler;
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.1
            @Override // java.lang.Runnable
            public void run() {
                if (ManagerHuaweiWatch.this.hasCallBack) {
                    return;
                }
                ManagerHuaweiWatch.this.hasCallBack = true;
                pBWebView.loadURL("javascript:try{backMultiSimInfo(3)}catch(ex){}");
            }
        }, 25000L);
    }

    public void start(String str) {
        try {
            if (!JSWhiteUtil.isWhite(this.webView.getUrl())) {
                this.webView.loadURL("javascript:try{backMultiSimInfo(4)}catch(ex){}");
                return;
            }
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("msg");
            final String optString = optJSONObject.optString("method");
            optJSONObject.optString("oemName");
            final String optString2 = optJSONObject.optString("args");
            if (DeviceHelper.isAvilible(BONE_PACKAGE_NAME)) {
                this.packageName = BONE_PACKAGE_NAME;
            }
            if (DeviceHelper.isAvilible(HEALTH_PACKEAGE_NAME)) {
                this.packageName = HEALTH_PACKEAGE_NAME;
            }
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.2
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    if (optString.equals("connectHWService")) {
                        ManagerHuaweiWatch managerHuaweiWatch = ManagerHuaweiWatch.this;
                        managerHuaweiWatch.connectHWService2(managerHuaweiWatch.packageName);
                    } else if (optString.equals("getMultiSimInfo")) {
                        ManagerHuaweiWatch.this.getMultiSimInfo2();
                    } else if (optString.equals("getDownloadEsim")) {
                        String str2 = "";
                        if (!TextUtils.isEmpty(optString2)) {
                            try {
                                str2 = new JSONObject(optString2).optString("arg1");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ManagerHuaweiWatch.this.getDownloadEsim2(str2);
                    } else if (optString.equals("getDownloadEsimUnicom")) {
                        ManagerHuaweiWatch.this.getDownloadEsimUnicom(optString2);
                    } else if (optString.equals("disconnectHWService")) {
                        ManagerHuaweiWatch.this.disconnectHWService2();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectHWService2(String str) {
        disconnectHWService2();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, CLASS_NAME_MULTI_SIM_SERVICE));
        intent.putExtras(new Bundle());
        if (this.context.bindService(intent, this.mConnection2, 1)) {
            return;
        }
        try {
            if (this.isDownLoadESMI) {
                FailDwonLoadEsmi();
            } else {
                this.webView.loadURL("javascript:backConnectResult('0')");
                this.hasCallBack = true;
                disconnectHWService2();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMultiSimInfo2() {
        try {
            this.mService2.getAttachedDeviceMultiSimInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDownloadEsim2(String str) {
        Handler handler;
        Runnable runnable;
        try {
            try {
                this.mService2.downloadESimProfile(str);
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiWatch$PnmnkSjXrg_7mT8UUK2JFY4oH8Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManagerHuaweiWatch.this.disconnectHWService2();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiWatch$PnmnkSjXrg_7mT8UUK2JFY4oH8Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManagerHuaweiWatch.this.disconnectHWService2();
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
        } catch (Throwable th) {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiWatch$PnmnkSjXrg_7mT8UUK2JFY4oH8Q
                @Override // java.lang.Runnable
                public final void run() {
                    ManagerHuaweiWatch.this.disconnectHWService2();
                }
            }, 500L);
            throw th;
        }
    }

    @JavascriptInterface
    public void getDownloadEsimUnicom(String str) {
        try {
            if (this.mService2 != null) {
                disconnectHWService2();
            }
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.5
                @Override // java.lang.Runnable
                public void run() {
                    UIUtils.toastCenterLong("开始下载卡数据(华为手表)");
                }
            });
            this.isDownLoadESMI = true;
            this.prilCode_2 = str;
            connectHWService2(this.packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void successDwonLoadEsmi() {
        Handler handler;
        Runnable runnable;
        try {
            try {
                this.mService2.downloadESimProfile(this.prilCode_2);
                this.webView.loadURL("javascript:backDownloadEsimResult('1')");
                UIUtils.logD("onServiceConnected", "华为手表开始下载");
                this.isDownLoadESMI = false;
                UIUtils.logD("onServiceConnected", "华为手表断开连接");
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.6
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerHuaweiWatch.this.disconnectHWService2();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                disconnectHWService2();
                UIUtils.logD("onServiceConnected", "华为手表断开连接");
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.6
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerHuaweiWatch.this.disconnectHWService2();
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
        } catch (Throwable th) {
            UIUtils.logD("onServiceConnected", "华为手表断开连接");
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiWatch.6
                @Override // java.lang.Runnable
                public void run() {
                    ManagerHuaweiWatch.this.disconnectHWService2();
                }
            }, 500L);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FailDwonLoadEsmi() {
        try {
            try {
                this.webView.loadURL("javascript:backDownloadEsimResult('0')");
                this.isDownLoadESMI = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectHWService2();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectHWService2() {
        try {
            this.mService2.unRegisterCallback(this.mCallback2);
            this.context.unbindService(this.mConnection2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
