package com.sinovatech.unicom.separatemodule.esim;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.euicc.server.IOpenEUICC;
import com.euicc.server.IServiceBinder;
import com.euicc.server.model.EUICCDeviceInfo;
import com.euicc.server.model.EUICCInfo;
import com.euicc.server.model.IOpenEUICCCalbcak;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p314po.OEMInfoEntity;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerCommonWatch {
    private AppCompatActivity context;
    private IOpenEUICC iOpenMultiSim;
    private IServiceBinder iServiceBinder;
    private boolean isDownLoadESMI;
    private String packageName;
    private String prilCode_2;
    private PBWebView webView;
    private Handler webViewHandler;
    private ServiceConnection conn = new ServiceConnection() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            try {
                ManagerCommonWatch.this.iServiceBinder = IServiceBinder.Stub.asInterface(iBinder);
                ManagerCommonWatch.this.iOpenMultiSim = IOpenEUICC.Stub.asInterface(ManagerCommonWatch.this.iServiceBinder.getServiceBinder("com.sinovatech.unicom.ui"));
                ManagerCommonWatch.this.iOpenMultiSim.registerCallback(ManagerCommonWatch.this.mEuiccCallback);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            StatisticsUploadUtils.uoloadPVWatch("common", "onServiceConnected是否成功:" + z);
            if (z) {
                if (ManagerCommonWatch.this.isDownLoadESMI) {
                    ManagerCommonWatch.this.successDwonLoadEsmi();
                } else {
                    ManagerCommonWatch.this.webView.loadURL("javascript:backConnectResult('1')");
                }
            } else if (ManagerCommonWatch.this.isDownLoadESMI) {
                ManagerCommonWatch.this.FailDwonLoadEsmi();
            } else {
                ManagerCommonWatch.this.webView.loadURL("javascript:backConnectResult('0')");
                ManagerCommonWatch.this.hasCallBack = true;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                try {
                    ManagerCommonWatch.this.iOpenMultiSim.unRegisterCallback(ManagerCommonWatch.this.mEuiccCallback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                ManagerCommonWatch.this.iOpenMultiSim = null;
            }
        }
    };
    private IOpenEUICCCalbcak.Stub mEuiccCallback = new IOpenEUICCCalbcak.Stub() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.4
        @Override // com.euicc.server.model.IOpenEUICCCalbcak
        public void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException {
            String str;
            String str2;
            final JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ResultCode", eUICCDeviceInfo.getResultCode());
                jSONObject.put("DeviceType", eUICCDeviceInfo.getDeviceType());
                jSONObject.put("DeviceID", eUICCDeviceInfo.getDeviceID());
                jSONObject.put("DeviceIMEI", eUICCDeviceInfo.getDeviceIMEI());
                jSONObject.put("DeviceSerialNumber", eUICCDeviceInfo.getDeviceSerialNumber());
                jSONObject.put("ProductName", eUICCDeviceInfo.getProductName());
                jSONObject.put("EID", eUICCDeviceInfo.getEID());
                String str3 = "";
                String str4 = "";
                if (eUICCDeviceInfo.getDeviceType() == 1) {
                    List<EUICCInfo> simInfoList = eUICCDeviceInfo.getSimInfoList();
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
                } else if (eUICCDeviceInfo.getDeviceType() == 2) {
                    EUICCInfo activeSimProfileInfo = eUICCDeviceInfo.getActiveSimProfileInfo();
                    if (activeSimProfileInfo != null) {
                        jSONObject.put("IMSI", activeSimProfileInfo.getIMSI());
                        jSONObject.put("ICCID", activeSimProfileInfo.getICCID());
                        str4 = activeSimProfileInfo.getIMSI();
                        str3 = activeSimProfileInfo.getICCID();
                    }
                    str = str3;
                    str2 = str4;
                } else {
                    jSONObject.put("IMSI", "");
                    jSONObject.put("ICCID", "");
                    str = "";
                    str2 = "";
                }
                try {
                    String[] esimParams = EsimUtil.getEsimParams(ManagerCommonWatch.this.context, ManagerCommonWatch.this.webView, eUICCDeviceInfo.getEID(), eUICCDeviceInfo.getDeviceID(), str, str2);
                    jSONObject.put("arg1", esimParams[0]);
                    jSONObject.put("arg2", esimParams[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                StatisticsUploadUtils.uoloadPVWatch("common", "Callback是否成功:" + eUICCDeviceInfo.getResultCode());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            ManagerCommonWatch.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.4.1
                @Override // java.lang.Runnable
                public void run() {
                    PBWebView pBWebView = ManagerCommonWatch.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:try{backMultiSimInfo(");
                    JSONObject jSONObject2 = jSONObject;
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    sb.append(")}catch(ex){}");
                    pBWebView.evaluateJavascript(sb.toString(), null);
                    ManagerCommonWatch.this.hasCallBack = true;
                }
            });
            ManagerCommonWatch.this.disconnectEUICCervice();
        }
    };
    private boolean hasCallBack = false;

    public ManagerCommonWatch(AppCompatActivity appCompatActivity, final PBWebView pBWebView, Handler handler) {
        this.context = appCompatActivity;
        this.webView = pBWebView;
        this.webViewHandler = handler;
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.1
            @Override // java.lang.Runnable
            public void run() {
                if (ManagerCommonWatch.this.hasCallBack) {
                    return;
                }
                ManagerCommonWatch.this.hasCallBack = true;
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
            if (optJSONObject == null) {
                return;
            }
            final String optString = optJSONObject.optString("method");
            this.packageName = optJSONObject.optString("packageName");
            try {
                if (TextUtils.isEmpty(this.packageName)) {
                    this.packageName = App.getInfoEntity().getPakeageName();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            optJSONObject.optString("oemName");
            final String optString2 = optJSONObject.optString("args");
            this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.2
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    if (optString.equals("connectHWService")) {
                        ManagerCommonWatch managerCommonWatch = ManagerCommonWatch.this;
                        managerCommonWatch.bindService(managerCommonWatch.packageName);
                    } else if (optString.equals("getMultiSimInfo")) {
                        ManagerCommonWatch.this.getEUICCInfo();
                    } else if (optString.equals("getDownloadEsim")) {
                        String str2 = "";
                        if (!TextUtils.isEmpty(optString2)) {
                            try {
                                str2 = new JSONObject(optString2).optString("arg1");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        ManagerCommonWatch.this.getDownloadEUICC(str2);
                    } else if (optString.equals("getDownloadEsimUnicom")) {
                        ManagerCommonWatch.this.getDownloadEsimUnicom(optString2);
                    } else if (optString.equals("disconnectHWService")) {
                        ManagerCommonWatch.this.disconnectEUICCervice();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService(String str) {
        try {
            disconnectEUICCervice();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, "com.euicc.server.IRemoteService"));
            boolean bindService = this.context.bindService(intent, this.conn, 1);
            if (!bindService) {
                if (this.isDownLoadESMI) {
                    FailDwonLoadEsmi();
                } else {
                    this.webView.loadURL("javascript:backConnectResult('0')");
                    this.hasCallBack = true;
                    disconnectEUICCervice();
                }
            }
            StatisticsUploadUtils.uoloadPVWatch("common", "bindService是否成功:" + bindService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getEUICCInfo() {
        try {
            this.iOpenMultiSim.getAttachedDeviceEUICCInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDownloadEUICC(String str) {
        try {
            try {
                StatisticsUploadUtils.uoloadPVWatch("common", "getDownloadEUICC开始下载");
                this.iOpenMultiSim.downloadEUICCProfile(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectEUICCervice();
        }
    }

    @JavascriptInterface
    public void getDownloadEsimUnicom(String str) {
        try {
            StatisticsUploadUtils.uoloadPVWatch("common", "getDownloadEsimUnicom开始下载");
            disconnectEUICCervice();
            this.isDownLoadESMI = true;
            this.prilCode_2 = str;
            bindService(this.packageName);
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
                UIUtils.toastCenterLong("开始下载卡数据");
                this.iOpenMultiSim.downloadEUICCProfile(this.prilCode_2);
                this.webView.loadURL("javascript:backDownloadEsimResult('1')");
                this.isDownLoadESMI = false;
                StatisticsUploadUtils.uoloadPVWatch("common", "getDownloadEsimUnicom成功下载");
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerCommonWatch.this.disconnectEUICCervice();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                handler = new Handler();
                runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerCommonWatch.this.disconnectEUICCervice();
                    }
                };
            }
            handler.postDelayed(runnable, 500L);
        } catch (Throwable th) {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.5
                @Override // java.lang.Runnable
                public void run() {
                    ManagerCommonWatch.this.disconnectEUICCervice();
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
                StatisticsUploadUtils.uoloadPVWatch("common", "getDownloadEsimUnicom失败下载");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectEUICCervice();
        }
    }

    @JavascriptInterface
    public void getDownloadEsimCommon(final String str) {
        try {
            try {
                OEMInfoEntity infoEntity = App.getInfoEntity();
                if (infoEntity == null) {
                    infoEntity = new OEMInfoEntity();
                }
                bindService(infoEntity.getPakeageName());
                this.webViewHandler.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.esim.ManagerCommonWatch.6
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ManagerCommonWatch.this.getDownloadEUICC(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            disconnectEUICCervice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectEUICCervice() {
        try {
            this.iOpenMultiSim.unRegisterCallback(this.mEuiccCallback);
            this.context.unbindService(this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
