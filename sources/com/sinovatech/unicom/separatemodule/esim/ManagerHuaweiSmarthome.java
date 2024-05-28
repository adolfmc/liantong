package com.sinovatech.unicom.separatemodule.esim;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.multisimservice.IOpenMultiSim2;
import com.huawei.multisimservice.IServiceBinder;
import com.huawei.multisimservice.model.IOpenMultiSimCallback2;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHuaweiSmarthome {
    private static final String BONE_PACKAGE_NAME = "com.huawei.bone";
    private static final String CLASS_NAME_MULTI_SIM_SERVICE = "com.huawei.multisimservice.MultiSimService";
    public static final String DONGFENG_PACKEAGE_NAME = "com.huawei.smarthome";
    private static final String HEALTH_PACKEAGE_NAME = "com.huawei.health";
    private boolean binRet1;
    private boolean binRet2;
    private AppCompatActivity context;
    private int deviceType1_2;
    private int deviceType2_2;
    private boolean isDongfeng;
    private boolean isDownLoadESMI;
    private IOpenMultiSim2 mService2;
    private String prilCode_2;
    private int resultCode1_2;
    private int resultCode2_2;
    private PBWebView webView;
    private Handler webViewHandler;
    private int linkCode = 1;
    private String packageName = DONGFENG_PACKEAGE_NAME;
    private String watchInfo = "东风5G手机壳";
    private ServiceConnection mConnection2 = new ServiceConnection() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                ManagerHuaweiSmarthome.this.mService2 = IOpenMultiSim2.Stub.asInterface(IServiceBinder.Stub.asInterface(iBinder).getServiceBinder("com.sinovatech.unicom.ui"));
                ManagerHuaweiSmarthome.this.mService2.registerCallback(ManagerHuaweiSmarthome.this.mCallback2);
                UIUtils.logD("onServiceConnected", ManagerHuaweiSmarthome.this.watchInfo + true);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            if (z) {
                if (ManagerHuaweiSmarthome.this.isDownLoadESMI) {
                    ManagerHuaweiSmarthome.this.successDwonLoadEsmi();
                } else {
                    ManagerHuaweiSmarthome.this.webView.loadURL("javascript:backConnectResult('1')");
                }
            } else if (ManagerHuaweiSmarthome.this.isDownLoadESMI) {
                ManagerHuaweiSmarthome.this.FailDwonLoadEsmi();
            } else {
                ManagerHuaweiSmarthome.this.webView.loadURL("javascript:backConnectResult('0')");
                ManagerHuaweiSmarthome.this.hasCallBack = true;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ManagerHuaweiSmarthome.this.mService2 = null;
        }
    };
    private IOpenMultiSimCallback2.Stub mCallback2 = new IOpenMultiSimCallback2.Stub() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.4
        @Override // com.huawei.multisimservice.model.IOpenMultiSimCallback2
        public void getDeviceMultiSimInfoAndPhoneNumber(MultiSimDeviceInfo multiSimDeviceInfo, String str) throws RemoteException {
            Handler handler;
            Runnable runnable;
            String str2;
            String str3;
            final JSONObject jSONObject = new JSONObject();
            try {
                try {
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("ResultCode", multiSimDeviceInfo.getResultCode());
                    jSONObject.put("DeviceType", multiSimDeviceInfo.getDeviceType());
                    jSONObject.put("DeviceID", multiSimDeviceInfo.getDeviceID());
                    jSONObject.put("DeviceIMEI", multiSimDeviceInfo.getDeviceIMEI());
                    jSONObject.put("DeviceSerialNumber", multiSimDeviceInfo.getDeviceSerialNumber());
                    jSONObject.put("ProductName", multiSimDeviceInfo.getProductName());
                    jSONObject.put("EID", multiSimDeviceInfo.getEID());
                    String str4 = "";
                    String str5 = "";
                    if (multiSimDeviceInfo.getDeviceType() == 1) {
                        List<SimInfo> simInfoList = multiSimDeviceInfo.getSimInfoList();
                        if (simInfoList != null && simInfoList.size() > 0) {
                            jSONObject.put("IMSI", simInfoList.get(0).getIMSI());
                            jSONObject.put("ICCID", simInfoList.get(0).getICCID());
                            String imsi = simInfoList.get(0).getIMSI();
                            str5 = imsi;
                            str4 = simInfoList.get(0).getICCID();
                        } else {
                            jSONObject.put("IMSI", "");
                            jSONObject.put("ICCID", "");
                        }
                        str2 = str4;
                        str3 = str5;
                    } else {
                        if (multiSimDeviceInfo.getDeviceType() == 2) {
                            if (multiSimDeviceInfo.getActiveSimProfileInfo() != null) {
                                jSONObject.put("IMSI", multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI());
                                jSONObject.put("ICCID", multiSimDeviceInfo.getActiveSimProfileInfo().getICCID());
                                String imsi2 = multiSimDeviceInfo.getActiveSimProfileInfo().getIMSI();
                                str2 = multiSimDeviceInfo.getActiveSimProfileInfo().getICCID();
                                str3 = imsi2;
                            } else {
                                jSONObject.put("IMSI", "");
                                jSONObject.put("ICCID", "");
                            }
                        } else {
                            jSONObject.put("IMSI", "");
                            jSONObject.put("ICCID", "");
                        }
                        str2 = "";
                        str3 = "";
                    }
                    try {
                        String[] esimParams = EsimUtil.getEsimParams(ManagerHuaweiSmarthome.this.context, ManagerHuaweiSmarthome.this.webView, multiSimDeviceInfo.getEID(), multiSimDeviceInfo.getDeviceID(), str2, str3);
                        jSONObject.put("arg1", esimParams[0]);
                        jSONObject.put("arg2", esimParams[1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler = ManagerHuaweiSmarthome.this.webViewHandler;
                    runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = ManagerHuaweiSmarthome.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:try{backMultiSimInfo(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")}catch(ex){}");
                            pBWebView.evaluateJavascript(sb.toString(), null);
                            ManagerHuaweiSmarthome.this.hasCallBack = true;
                        }
                    };
                } catch (Throwable th) {
                    ManagerHuaweiSmarthome.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PBWebView pBWebView = ManagerHuaweiSmarthome.this.webView;
                            StringBuilder sb = new StringBuilder();
                            sb.append("javascript:try{backMultiSimInfo(");
                            JSONObject jSONObject2 = jSONObject;
                            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            sb.append(")}catch(ex){}");
                            pBWebView.evaluateJavascript(sb.toString(), null);
                            ManagerHuaweiSmarthome.this.hasCallBack = true;
                        }
                    });
                    ManagerHuaweiSmarthome.this.disconnectHWService2();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                handler = ManagerHuaweiSmarthome.this.webViewHandler;
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PBWebView pBWebView = ManagerHuaweiSmarthome.this.webView;
                        StringBuilder sb = new StringBuilder();
                        sb.append("javascript:try{backMultiSimInfo(");
                        JSONObject jSONObject2 = jSONObject;
                        sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                        sb.append(")}catch(ex){}");
                        pBWebView.evaluateJavascript(sb.toString(), null);
                        ManagerHuaweiSmarthome.this.hasCallBack = true;
                    }
                };
            }
            handler.post(runnable);
            ManagerHuaweiSmarthome.this.disconnectHWService2();
        }
    };
    private boolean hasCallBack = false;

    public ManagerHuaweiSmarthome(AppCompatActivity appCompatActivity, final PBWebView pBWebView, Handler handler) {
        this.context = appCompatActivity;
        this.webView = pBWebView;
        this.webViewHandler = handler;
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.1
            @Override // java.lang.Runnable
            public void run() {
                if (ManagerHuaweiSmarthome.this.hasCallBack) {
                    return;
                }
                ManagerHuaweiSmarthome.this.hasCallBack = true;
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
            this.isDongfeng = false;
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("msg");
            final String optString = optJSONObject.optString("method");
            optJSONObject.optString("oemName");
            final String optString2 = optJSONObject.optString("args");
            this.packageName = optJSONObject.optString("packageName");
            try {
                if (TextUtils.isEmpty(this.packageName)) {
                    this.packageName = App.getInfoEntity().getPakeageName();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.2
                @Override // java.lang.Runnable
                public void run() {
                    StatisticsUploadUtils.uoloadPVWatch("smartHome", optString + ManagerHuaweiSmarthome.this.packageName);
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    if (optString.equals("connectHWService")) {
                        ManagerHuaweiSmarthome.this.startApp();
                    } else if (optString.equals("getMultiSimInfo")) {
                        ManagerHuaweiSmarthome.this.getMultiSimInfo2();
                    } else if (optString.equals("getDownloadEsim")) {
                        String str2 = "";
                        if (!TextUtils.isEmpty(optString2)) {
                            try {
                                str2 = new JSONObject(optString2).optString("arg1");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        ManagerHuaweiSmarthome.this.getDownloadEsim2(str2);
                    } else if (optString.equals("getDownloadEsimUnicom")) {
                        ManagerHuaweiSmarthome.this.getDownloadEsimUnicom(optString2);
                    } else if (optString.equals("disconnectHWService")) {
                        ManagerHuaweiSmarthome.this.disconnectHWService2();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startApp() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(DONGFENG_PACKEAGE_NAME, "com.huawei.esimsubscriptionsdk.view.activities.InformedConsentActivity"));
            new AvoidOnResult(this.context).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiSmarthome$8TOSjVirAUdeaCYVkf5fRfnXhns
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent2) {
                    ManagerHuaweiSmarthome.lambda$startApp$0(ManagerHuaweiSmarthome.this, i, intent2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("没有安装此应用");
        }
    }

    public static /* synthetic */ void lambda$startApp$0(ManagerHuaweiSmarthome managerHuaweiSmarthome, int i, Intent intent) {
        if (i == 0) {
            managerHuaweiSmarthome.connectHWService2(managerHuaweiSmarthome.packageName);
        } else {
            managerHuaweiSmarthome.webView.loadURL("javascript:backConnectResult('0')");
        }
    }

    private void connectHWService2(String str) {
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
                this.mService2.downloadESimProfileAndPhonenumber(str, "", "", UserManager.getInstance().getCurrentPhoneNumber());
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiSmarthome$9jWtHIDPy5m7t-oFsHJA5ESLrvI
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManagerHuaweiSmarthome.this.disconnectHWService2();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiSmarthome$9jWtHIDPy5m7t-oFsHJA5ESLrvI
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManagerHuaweiSmarthome.this.disconnectHWService2();
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
        } catch (Throwable th) {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.-$$Lambda$ManagerHuaweiSmarthome$9jWtHIDPy5m7t-oFsHJA5ESLrvI
                @Override // java.lang.Runnable
                public final void run() {
                    ManagerHuaweiSmarthome.this.disconnectHWService2();
                }
            }, 500L);
            throw th;
        }
    }

    @JavascriptInterface
    public void getDownloadEsimUnicom(String str) {
        StatisticsUploadUtils.uoloadPVWatch("smartHome", this.watchInfo + "回调下载开始");
        try {
            if (this.mService2 != null) {
                disconnectHWService2();
            }
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.5
                @Override // java.lang.Runnable
                public void run() {
                    UIUtils.toastCenterLong("开始下载卡数据");
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
                StatisticsUploadUtils.uoloadPVWatch("smartHome", this.watchInfo + "回调下载成功");
                this.mService2.downloadESimProfileAndPhonenumber(this.prilCode_2, "", "", UserManager.getInstance().getCurrentPhoneNumber());
                this.webView.loadURL("javascript:backDownloadEsimResult('1')");
                UIUtils.logD("onServiceConnected", "华为手表开始下载");
                this.isDownLoadESMI = false;
                UIUtils.logD("onServiceConnected", "华为手表断开连接");
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.6
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerHuaweiSmarthome.this.disconnectHWService2();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                disconnectHWService2();
                UIUtils.logD("onServiceConnected", "华为手表断开连接");
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.6
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerHuaweiSmarthome.this.disconnectHWService2();
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
        } catch (Throwable th) {
            UIUtils.logD("onServiceConnected", "华为手表断开连接");
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerHuaweiSmarthome.6
                @Override // java.lang.Runnable
                public void run() {
                    ManagerHuaweiSmarthome.this.disconnectHWService2();
                }
            }, 500L);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FailDwonLoadEsmi() {
        try {
            try {
                StatisticsUploadUtils.uoloadPVWatch("smartHome", this.watchInfo + "回调下载失败");
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
