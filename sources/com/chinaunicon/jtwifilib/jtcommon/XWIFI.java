package com.chinaunicon.jtwifilib.jtcommon;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.core.net.callback.OkCallback;
import com.chinaunicon.jtwifilib.core.net.parser.OkJsonParser;
import com.chinaunicon.jtwifilib.core.net.proxy.OkHttpProxy;
import com.chinaunicon.jtwifilib.core.utils.AesEncryptUtil;
import com.chinaunicon.jtwifilib.core.utils.AppInfo;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.SharedPreferencesHelp;
import com.chinaunicon.jtwifilib.core.utils.StringUtil;
import com.chinaunicon.jtwifilib.jtcommon.model.ErrorCode;
import com.chinaunicon.jtwifilib.jtcommon.model.JtGetIntent;
import com.chinaunicon.jtwifilib.jtcommon.model.JtIntnetInfo;
import com.chinaunicon.jtwifilib.jtcommon.model.JtRank;
import com.chinaunicon.jtwifilib.jtcommon.model.JtSpeedParams;
import com.chinaunicon.jtwifilib.jtcommon.model.JtSpeedUpload;
import com.chinaunicon.jtwifilib.jtcommon.model.ParamsBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.NetLinkManage;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingResBean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtMD5Util;
import com.chinaunicon.jtwifilib.jtcommon.util.JtSpeedClient;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;
import com.chinaunicon.jtwifilib.jtcommon.util.JtWifiClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class XWIFI {
    public static final int END_MAC_AND_SSID = 6;
    public static final int END_NETWORK_STATUS = 8;
    public static final int END_SPEED = 4;
    public static final int END_UPLOAD_SPEED = 10;
    public static final int GET_INTERNET_END = 1;
    public static final int GET_INTERNET_START = 0;
    public static final int SPEEDING = 3;
    public static final int START_MAC_AND_SSID = 5;
    public static final int START_NETWORK_STATUS = 7;
    public static final int START_SPEED = 2;
    public static final int START_UPLOAD_SPEED = 9;
    private String Location_X;
    private String Location_Y;
    private String aveRate;
    private String cityCode;
    private String cityName;
    public ExecutorService executorPing;
    private float jitter;
    private JtIntnetInfo jtIntnetInfo;
    private JtOnSpeedClientListener jtOnSpeedClientListener;
    private JtOnWifiClientSpeedListener jtOnWifiSpeedListener;
    private JtWifiClient jtWifiClient;
    private Activity mContext;
    private String mExtends;
    private String mac;
    private String maxRate;
    Handler mhandler;
    private String minRate;
    private PingResBean netTimeTelay;
    private String privinceCode;
    private String privinceName;
    private String roomId;
    private JtSpeedClient speedClient;
    private List<JtIntnetInfo.WifiSpeedNode> speedUrl;
    private String ssid;
    private String userPhone;
    private final int SUCCESS = 100;
    private final int filed = 101;
    private String speedNode = "";
    private String speedNodeString = "";
    private boolean isMobileNetwork = false;
    private boolean isDownloadUrl = false;
    private int millisecond = 1000;
    private boolean isPing = true;
    private String uniqueIdentification = "";
    private boolean isStart = false;
    private boolean isOnClickStart = false;
    private boolean isAuth = false;
    private boolean isStopClick = false;
    private String wifitag = "wifispeed";

    public XWIFI(Activity activity) {
        this.mContext = activity;
        this.speedClient = new JtSpeedClient(activity);
        this.speedClient.onOnSpeedClientListener(new JtOnSpeedClientListener(activity) { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.1
            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            @RequiresApi(api = 21)
            public void onFinish(int i, int i2, int i3) {
                super.onFinish(i, i2, i3);
                if (XWIFI.this.isDownloadUrl) {
                    if (XWIFI.this.jtOnSpeedClientListener != null) {
                        XWIFI.this.jtOnSpeedClientListener.onFinish(i, i2, i3);
                    }
                    XWIFI.this.stopSpeed();
                    return;
                }
                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                    XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 4, XWIFI.this.maxRate, XWIFI.this.minRate, XWIFI.this.aveRate);
                }
                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                    XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 5);
                }
                XWIFI xwifi = XWIFI.this;
                xwifi.mac = xwifi.getMacAddress();
                XWIFI xwifi2 = XWIFI.this;
                xwifi2.ssid = xwifi2.getSSID();
                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                    XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 6);
                }
                if (XWIFI.this.isNotMobileNetworkStop()) {
                    return;
                }
                XWIFI.this.uploadStart();
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onAverageSpeed(String str) {
                super.onAverageSpeed(str);
                if (XWIFI.this.isDownloadUrl && XWIFI.this.jtOnSpeedClientListener != null) {
                    XWIFI.this.jtOnSpeedClientListener.onAverageSpeed(str);
                }
                XWIFI.this.aveRate = str;
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onMinSpeed(String str) {
                super.onMinSpeed(str);
                if (XWIFI.this.isDownloadUrl && XWIFI.this.jtOnSpeedClientListener != null) {
                    XWIFI.this.jtOnSpeedClientListener.onMinSpeed(str);
                }
                XWIFI.this.minRate = str;
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onMaxSpeed(String str) {
                super.onMaxSpeed(str);
                if (XWIFI.this.isDownloadUrl && XWIFI.this.jtOnSpeedClientListener != null) {
                    XWIFI.this.jtOnSpeedClientListener.onMaxSpeed(str);
                }
                XWIFI.this.maxRate = str;
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onCurrentSpeed(String str) {
                super.onCurrentSpeed(str);
                if (!XWIFI.this.isDownloadUrl || XWIFI.this.jtOnSpeedClientListener == null) {
                    return;
                }
                XWIFI.this.jtOnSpeedClientListener.onCurrentSpeed(str);
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onSpeed(float f, float f2, float f3, float f4) {
                super.onSpeed(f, f2, f3, f4);
                if (XWIFI.this.isDownloadUrl) {
                    if (XWIFI.this.jtOnSpeedClientListener != null) {
                        XWIFI.this.jtOnSpeedClientListener.onSpeed(f, f2, f3, f4);
                    }
                } else if (XWIFI.this.isNotMobileNetworkStop() || XWIFI.this.jtOnWifiSpeedListener == null) {
                } else {
                    XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(f, 3, f2 + "", f3 + "", f4 + "");
                }
            }

            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedClientListener, com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
            public void onFiled(String str) {
                super.onFiled(str);
                if (XWIFI.this.isDownloadUrl) {
                    if (XWIFI.this.jtOnSpeedClientListener != null) {
                        XWIFI.this.jtOnSpeedClientListener.onFiled(str);
                    }
                    XWIFI.this.stopSpeed();
                }
            }
        });
        this.jtWifiClient = new JtWifiClient(activity);
    }

    public void start() {
        if (!this.isAuth) {
            this.isOnClickStart = true;
            return;
        }
        this.uniqueIdentification = StringUtil.getUUID() + "," + System.currentTimeMillis();
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).setUniqueIdentification(this.uniqueIdentification);
        JtUploadLog jtUploadLog = JtUploadLog.getInstance(JtApp.getInstance().getContext());
        JtGsonUtil jtGsonUtil = JtGsonUtil.getInstance();
        jtUploadLog.updateData("1", jtGsonUtil.toJson(new ErrorCode(0, "开始测速," + this.millisecond + "," + this.uniqueIdentification, "", "")), "wifi_speed_start");
        if (isNotMobileNetworkStop()) {
            return;
        }
        if (!JtApp.getInstance().getAppkeySuccess()) {
            JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.jtOnWifiSpeedListener;
            if (jtOnWifiClientSpeedListener != null) {
                jtOnWifiClientSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(100, "请检查APPKey是否正确！", "2009", "鉴权失败，请检查appkey")));
            }
        } else if (this.isStart) {
        } else {
            JtL.m16342e("-------------开始测速---------");
            this.isStart = true;
            this.mhandler = new Handler() { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.2
                @Override // android.os.Handler
                @RequiresApi(api = 21)
                public void handleMessage(@NonNull Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 100:
                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 8);
                            }
                            if (XWIFI.this.isNotMobileNetworkStop()) {
                                return;
                            }
                            XWIFI xwifi = XWIFI.this;
                            xwifi.startSpeed(xwifi.speedUrl);
                            return;
                        case 101:
                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(102, "获取网络信息失败！", "2006", "ping异常")));
                            }
                            XWIFI.this.stop();
                            return;
                        default:
                            return;
                    }
                }
            };
            this.executorPing = Executors.newFixedThreadPool(1);
            JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener2 = this.jtOnWifiSpeedListener;
            if (jtOnWifiClientSpeedListener2 != null) {
                jtOnWifiClientSpeedListener2.onWifiSpeed(0.0f, 0);
            }
            JtGetIntent jtGetIntent = new JtGetIntent();
            jtGetIntent.setAppkey(JtApp.getInstance().getAppKey());
            jtGetIntent.setCityCode(this.cityCode);
            jtGetIntent.setCityName(this.cityName);
            jtGetIntent.setPrivinceName(this.privinceName);
            jtGetIntent.setPrivinceCode(this.privinceCode);
            jtGetIntent.setUuid(StringUtil.getOnly(this.mContext));
            jtGetIntent.setVersion("1.1.2");
            jtGetIntent.setPlatform("android");
            jtGetIntent.setRoomId(this.roomId);
            jtGetIntent.setTransactionID(System.currentTimeMillis() + "");
            if (!TextUtils.isEmpty(this.userPhone)) {
                jtGetIntent.setUserPhone(JtMD5Util.getMD5(this.userPhone));
            }
            jtGetIntent.setLocation_X(this.Location_X);
            jtGetIntent.setLocation_Y(this.Location_Y);
            jtGetIntent.setUniqueIdentification(this.uniqueIdentification);
            jtGetIntent.setmExtends(this.mExtends);
            jtGetIntent.setSpeedType(AppInfo.isWifi(this.mContext) ? "1" : "2");
            String json = JtGsonUtil.getInstance().toJson(jtGetIntent);
            JtL.m16342e("初始化参数：" + json);
            String str = "";
            try {
                str = AesEncryptUtil.encryptString(json);
            } catch (Exception unused) {
                JtUploadLog jtUploadLog2 = JtUploadLog.getInstance(this.mContext);
                jtUploadLog2.saveToDb("1", "XWIFI:" + json + "加密失败", "aesEncryptException");
            }
            JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", json, "wifi_speed_getspeedinfo_params");
            if (isNotMobileNetworkStop()) {
                return;
            }
            OkHttpProxy.post().setCustomRequestBodyEntity(str).url("http://120.52.12.5:18080/zw_interface/api/user/getSpeedInfo").tag(this.wifitag).enqueue(new OkCallback<String>(new OkJsonParser<String>() { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.3
            }) { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.4
                @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
                public void onSuccess(int i, String str2) {
                    JtL.m16342e(str2);
                    if (i != 0) {
                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                            XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(106, "获取测速地址失败,请检查网络！", "2002", "获取测速地址失败")));
                        }
                        XWIFI.this.stop();
                        return;
                    }
                    try {
                        String decryptString = AesEncryptUtil.decryptString(str2);
                        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", decryptString, "wifi_speed_getspeedinfo_result");
                        XWIFI.this.jtIntnetInfo = (JtIntnetInfo) JtGsonUtil.getInstance().fromJson(decryptString, (Class<Object>) JtIntnetInfo.class);
                        JtL.m16342e("返回值：" + decryptString);
                        if ("0".equals(XWIFI.this.jtIntnetInfo.getResult())) {
                            if ("0".equals(XWIFI.this.jtIntnetInfo.getIsUnicomNet())) {
                                if (XWIFI.this.jtIntnetInfo.getWifiSpeedNodeList() != null) {
                                    if (XWIFI.this.jtIntnetInfo.getWifiSpeedNodeList().size() > 0) {
                                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                            XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 1);
                                        }
                                        if (TextUtils.isEmpty(XWIFI.this.jtIntnetInfo.getWifiSpeedNodeList().get(0).getSpeedUrl())) {
                                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                                XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(103, "测速地址不能为空！", "2002", "获取测速地址失败")));
                                            }
                                            XWIFI.this.stop();
                                            return;
                                        }
                                        XWIFI.this.speedUrl = XWIFI.this.jtIntnetInfo.getWifiSpeedNodeList();
                                        int size = XWIFI.this.speedUrl.size();
                                        XWIFI.this.speedNodeString = "";
                                        for (int i2 = 0; i2 < size; i2++) {
                                            if (TextUtils.isEmpty(XWIFI.this.speedNodeString)) {
                                                XWIFI.this.speedNodeString = ((JtIntnetInfo.WifiSpeedNode) XWIFI.this.speedUrl.get(i2)).getSpeedUrl();
                                            } else {
                                                XWIFI xwifi = XWIFI.this;
                                                xwifi.speedNodeString = XWIFI.this.speedNodeString + "," + ((JtIntnetInfo.WifiSpeedNode) XWIFI.this.speedUrl.get(i2)).getSpeedUrl();
                                            }
                                        }
                                        if (XWIFI.this.isPing) {
                                            XWIFI.this.getNetWork();
                                            return;
                                        } else {
                                            XWIFI.this.startSpeed(XWIFI.this.speedUrl);
                                            return;
                                        }
                                    }
                                    if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                        XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(103, "测速地址不能为空！", "2002", "获取测速地址失败")));
                                    }
                                    XWIFI.this.stop();
                                    return;
                                }
                                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                    XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(103, "测速地址不能为空！", "2002", "获取测速地址失败")));
                                }
                                XWIFI.this.stop();
                                return;
                            }
                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(104, "暂不支持异网测速！", "2003", "不支持异网测速")));
                            }
                            XWIFI.this.stop();
                            return;
                        }
                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                            XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(105, "获取测速地址失败！", "2002", "获取测速地址失败")));
                        }
                        XWIFI.this.stop();
                    } catch (Exception unused2) {
                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                            XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(111, "获取测速地址失败，异常！", "2002", "获取测速地址失败")));
                        }
                        XWIFI.this.stop();
                    }
                }

                @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
                public void onFailure(Exception exc) {
                    JtL.m16342e("错误值：" + exc.getMessage());
                    if (XWIFI.this.jtOnWifiSpeedListener != null) {
                        XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(106, "获取测速地址失败,请检查网络！", "2001", "网络异常，获取测速地址失败")));
                    }
                    XWIFI.this.stop();
                    JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(new ErrorCode(106, exc.getMessage(), "2001", "网络异常，获取测速地址失败")), "wifi_speed_filed");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 21)
    public void uploadStart() {
        if (isNotMobileNetworkStop()) {
            return;
        }
        JtSpeedUpload jtSpeedUpload = new JtSpeedUpload();
        jtSpeedUpload.setmExtends(this.mExtends);
        jtSpeedUpload.setSpeedType(AppInfo.isWifi(this.mContext) ? "1" : "2");
        jtSpeedUpload.setCity(this.cityCode);
        jtSpeedUpload.setProvince(this.privinceCode);
        jtSpeedUpload.setProvinceName(this.privinceName);
        jtSpeedUpload.setCityName(this.cityName);
        jtSpeedUpload.setRoomID(this.roomId);
        jtSpeedUpload.setMac(this.mac);
        if (!TextUtils.isEmpty(this.userPhone)) {
            jtSpeedUpload.setUserPhone(JtMD5Util.getMD5(this.userPhone));
        }
        PingResBean pingResBean = this.netTimeTelay;
        if (pingResBean != null) {
            this.jitter = parsFloatToString(pingResBean.getMaxTime()).floatValue() - parsFloatToString(this.netTimeTelay.getMinTime()).floatValue();
            float floatValue = Float.valueOf(new DecimalFormat("#.00").format(this.jitter)).floatValue();
            jtSpeedUpload.setJitter(floatValue + "");
            jtSpeedUpload.setPlp(TextUtils.isEmpty(this.netTimeTelay.getLossPacket()) ? "0.00" : new DecimalFormat("0.00#").format(Float.parseFloat(this.netTimeTelay.getLossPacket())));
            jtSpeedUpload.setNetworkDelay(this.netTimeTelay.getAvgTime());
        }
        jtSpeedUpload.setAveRate(this.aveRate);
        jtSpeedUpload.setMaxRate(this.maxRate);
        jtSpeedUpload.setMinRate(this.minRate);
        jtSpeedUpload.setWanIp(this.jtIntnetInfo.getWanIp());
        jtSpeedUpload.setSpeedNode(this.speedNodeString);
        jtSpeedUpload.setSpeedTime(System.currentTimeMillis() + "");
        jtSpeedUpload.setSpeedLocaltion(this.jtIntnetInfo.getWifiSpeedLocaltion());
        jtSpeedUpload.setSsid(this.ssid);
        jtSpeedUpload.setSignalIntensity(this.jtWifiClient.getCurrentNetworkRssi());
        jtSpeedUpload.setServiceProvider(this.jtIntnetInfo.getServiceProvider());
        jtSpeedUpload.setAppkey(JtApp.getInstance().getAppKey());
        jtSpeedUpload.setTransactionID(System.currentTimeMillis() + "");
        jtSpeedUpload.setUuid(StringUtil.getOnly(this.mContext));
        jtSpeedUpload.setPlatfrom("android");
        jtSpeedUpload.setVersion("1.1.2");
        jtSpeedUpload.setWifiVersion(this.jtWifiClient.getWifiAgreement());
        jtSpeedUpload.setConnectFrequency(this.jtWifiClient.getConnectFrequency());
        jtSpeedUpload.setLinkSpeed(getLinkSpeed());
        jtSpeedUpload.setUniqueIdentification(this.uniqueIdentification);
        String json = JtGsonUtil.getInstance().toJson(jtSpeedUpload);
        JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.jtOnWifiSpeedListener;
        if (jtOnWifiClientSpeedListener != null) {
            jtOnWifiClientSpeedListener.onWifiSpeed(0.0f, 9, json);
        }
        JtL.m16342e("请求参数：" + json);
        String str = "";
        try {
            str = AesEncryptUtil.encryptString(json);
        } catch (Exception unused) {
            JtUploadLog jtUploadLog = JtUploadLog.getInstance(this.mContext);
            jtUploadLog.saveToDb("1", "XWIFI:" + json + "加密失败", "aesEncryptException");
        }
        if (isNotMobileNetworkStop()) {
            return;
        }
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", json, "wifi_speed_reportspeed_params");
        OkHttpProxy.post().setCustomRequestBodyEntity(str).url("http://120.52.12.5:18080/zw_interface/api/user/reportSpeed").tag(this.wifitag).enqueue(new OkCallback<String>(new OkJsonParser<String>() { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.5
        }) { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.6
            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            @RequiresApi(api = 21)
            public void onSuccess(int i, String str2) {
                if (i == 0) {
                    try {
                        String decryptString = AesEncryptUtil.decryptString(str2);
                        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", decryptString, "wifi_speed_reportspeed_result");
                        JtRank jtRank = (JtRank) JtGsonUtil.getInstance().fromJson(decryptString, (Class<Object>) JtRank.class);
                        JtL.m16342e("返回值：" + decryptString);
                        if ("0".equals(jtRank.getResult())) {
                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                XWIFI.this.jtOnWifiSpeedListener.onWifiSpeed(0.0f, 10);
                            }
                            JtSpeedParams jtSpeedParams = new JtSpeedParams();
                            jtSpeedParams.setMac(XWIFI.this.mac);
                            jtSpeedParams.setAveRate(XWIFI.this.aveRate);
                            jtSpeedParams.setMaxRate(XWIFI.this.maxRate);
                            jtSpeedParams.setMinRate(XWIFI.this.minRate);
                            jtSpeedParams.setWanIp(XWIFI.this.jtIntnetInfo.getWanIp());
                            if (XWIFI.this.netTimeTelay != null) {
                                XWIFI.this.jitter = XWIFI.this.parsFloatToString(XWIFI.this.netTimeTelay.getMaxTime()).floatValue() - XWIFI.this.parsFloatToString(XWIFI.this.netTimeTelay.getMinTime()).floatValue();
                                float floatValue2 = Float.valueOf(new DecimalFormat("#.00").format(XWIFI.this.jitter)).floatValue();
                                jtSpeedParams.setJitter(floatValue2 + "");
                                jtSpeedParams.setPlp(TextUtils.isEmpty(XWIFI.this.netTimeTelay.getLossPacket()) ? "0.00" : new DecimalFormat("0.00#").format(Float.parseFloat(XWIFI.this.netTimeTelay.getLossPacket())));
                                jtSpeedParams.setNetworkDelay(XWIFI.this.netTimeTelay.getAvgTime());
                            }
                            jtSpeedParams.setSpeedNode(XWIFI.this.speedNodeString);
                            jtSpeedParams.setSpeedTime(System.currentTimeMillis() + "");
                            jtSpeedParams.setSpeedLocaltion(XWIFI.this.jtIntnetInfo.getWifiSpeedLocaltion());
                            jtSpeedParams.setSsid(XWIFI.this.ssid);
                            jtSpeedParams.setSignalIntensity(XWIFI.this.jtWifiClient.getCurrentNetworkRssi());
                            jtSpeedParams.setServiceProvider(XWIFI.this.jtIntnetInfo.getServiceProvider());
                            jtSpeedParams.setRank(jtRank.getRank());
                            jtSpeedParams.setWifiVersion(XWIFI.this.jtWifiClient.getWifiAgreement());
                            jtSpeedParams.setConnectFrequency(XWIFI.this.jtWifiClient.getConnectFrequency());
                            jtSpeedParams.setLinkSpeed(XWIFI.this.getLinkSpeed());
                            jtSpeedParams.setUniqueIdentification(XWIFI.this.uniqueIdentification);
                            jtSpeedParams.setmExtends(XWIFI.this.mExtends);
                            jtSpeedParams.setSpeedType(AppInfo.isWifi(XWIFI.this.mContext) ? "1" : "2");
                            jtSpeedParams.setCity(XWIFI.this.cityCode);
                            jtSpeedParams.setProvince(XWIFI.this.privinceCode);
                            jtSpeedParams.setProvinceName(XWIFI.this.privinceName);
                            jtSpeedParams.setCityName(XWIFI.this.cityName);
                            if (XWIFI.this.jtOnWifiSpeedListener != null) {
                                XWIFI.this.jtOnWifiSpeedListener.success(jtSpeedParams);
                            }
                            XWIFI.this.stop();
                            return;
                        }
                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                            XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(107, "获取排名失败！", "2008", "获取排名失败")));
                        }
                        XWIFI.this.stop();
                        return;
                    } catch (Exception unused2) {
                        if (XWIFI.this.jtOnWifiSpeedListener != null) {
                            XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(110, "获取排名失败，解析异常！", "2008", "获取排名失败")));
                        }
                        XWIFI.this.stop();
                        return;
                    }
                }
                JtL.m16342e("错误值：" + i);
                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                    XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(108, "获取排名失败，网络异常！", "2008", "获取排名失败")));
                }
                XWIFI.this.stop();
            }

            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            public void onFailure(Exception exc) {
                JtL.m16342e("错误值：" + exc.getMessage());
                if (XWIFI.this.jtOnWifiSpeedListener != null) {
                    XWIFI.this.jtOnWifiSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(108, "获取排名失败，网络异常！", "2007", "网络异常，获取排名失败")));
                }
                JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(new ErrorCode(108, exc.getMessage(), "2007", "网络异常，获取排名失败")), "wifi_speed_filed");
                XWIFI.this.stop();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Float parsFloatToString(String str) {
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (Exception unused) {
            return Float.valueOf(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLinkSpeed() {
        WifiInfo connectionInfo = ((WifiManager) this.mContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        connectionInfo.getLinkSpeed();
        if (connectionInfo == null) {
            return "";
        }
        return connectionInfo.getLinkSpeed() + "";
    }

    public void initSpeed(String str) {
        ParamsBean paramsBean = (ParamsBean) JtGsonUtil.getInstance().fromJson(str, (Class<Object>) ParamsBean.class);
        if (paramsBean != null) {
            this.privinceName = paramsBean.getPrivinceName();
            this.privinceCode = paramsBean.getPrivinceCode();
            this.cityName = paramsBean.getCityName();
            this.cityCode = paramsBean.getCityCode();
            this.userPhone = paramsBean.getUserPhone();
            this.Location_X = paramsBean.getLocation_X();
            this.Location_Y = paramsBean.getLocation_Y();
            setPing(true);
            setRoomId(paramsBean.getRoomID());
            setExtends(paramsBean.getmExtends());
            setMillisecond(TextUtils.isEmpty(paramsBean.getMillisecond()) ? 1000 : Integer.parseInt(paramsBean.getMillisecond()));
            JtApp.getInstance().setAppKey(paramsBean.getAppkey());
            JtApp.getInstance().setOnAuthSuccessListener(new JtOnAuthSuccessListener() { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.7
                @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnAuthSuccessListener
                public void success() {
                    XWIFI.this.isAuth = true;
                    if (XWIFI.this.isOnClickStart) {
                        XWIFI.this.isOnClickStart = false;
                        JtL.m16342e("停止测速" + XWIFI.this.isStopClick);
                        if (XWIFI.this.isStopClick) {
                            return;
                        }
                        XWIFI.this.start();
                    }
                }

                @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnAuthSuccessListener
                public void filed() {
                    XWIFI.this.isAuth = true;
                    if (XWIFI.this.isOnClickStart) {
                        XWIFI.this.isOnClickStart = false;
                        if (XWIFI.this.isStopClick) {
                            return;
                        }
                        XWIFI.this.start();
                    }
                }
            });
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("user", this.userPhone);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("province", this.privinceName);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("city", this.cityName);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("provinceCode", this.privinceCode);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("cityCode", this.cityCode);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("Location_X", this.Location_X);
            SharedPreferencesHelp.getInstance(JtApp.getInstance().getContext(), "uuid").putString("Location_Y", this.Location_Y);
            JtUploadLog.getInstance(JtApp.getInstance().getContext()).setCityInfo(this.privinceName, this.privinceCode, this.cityName, this.cityCode, this.userPhone, this.Location_X, this.Location_Y);
        }
    }

    public void setJtOnSpeedClientListener(JtOnSpeedClientListener jtOnSpeedClientListener) {
        this.jtOnSpeedClientListener = jtOnSpeedClientListener;
    }

    public void startSpeedByUrl(String str) {
        stop();
        this.uniqueIdentification = StringUtil.getUUID() + "," + System.currentTimeMillis();
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).setUniqueIdentification(this.uniqueIdentification);
        this.isDownloadUrl = true;
        this.isStart = true;
        ArrayList arrayList = new ArrayList();
        JtIntnetInfo.WifiSpeedNode wifiSpeedNode = new JtIntnetInfo.WifiSpeedNode();
        wifiSpeedNode.setSpeedUrl(str);
        arrayList.add(wifiSpeedNode);
        this.speedClient.startSpeed(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put("msg", "开始测速");
        hashMap.put("downloadUrl", str);
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(hashMap), "speed_start");
    }

    public void stopSpeed() {
        stop();
        this.isStart = false;
        this.isDownloadUrl = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSpeed(List<JtIntnetInfo.WifiSpeedNode> list) {
        if (isNotMobileNetworkStop()) {
            return;
        }
        this.isDownloadUrl = false;
        this.speedUrl = list;
        JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.jtOnWifiSpeedListener;
        if (jtOnWifiClientSpeedListener != null) {
            jtOnWifiClientSpeedListener.onWifiSpeed(0.0f, 2);
        }
        this.speedClient.startSpeed(list, this.millisecond);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSSID() {
        String wifissid = this.jtWifiClient.getWIFISSID();
        return (wifissid.startsWith("\"") && wifissid.endsWith("\"")) ? wifissid.replaceAll("\"", "") : wifissid;
    }

    private String getIpAddress() {
        return this.jtWifiClient.getIpAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMacAddress() {
        return this.jtWifiClient.getMacAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNetWork() {
        JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.jtOnWifiSpeedListener;
        if (jtOnWifiClientSpeedListener != null) {
            jtOnWifiClientSpeedListener.onWifiSpeed(0.0f, 7);
        }
        JtIntnetInfo jtIntnetInfo = this.jtIntnetInfo;
        if (jtIntnetInfo != null) {
            if (jtIntnetInfo.getWifiSpeedNodeList().size() > 0) {
                this.speedNode = this.jtIntnetInfo.getWifiSpeedNodeList().get(0).getPingIP();
                if (TextUtils.isEmpty(this.speedNode)) {
                    this.speedNode = "";
                }
                HashMap hashMap = new HashMap();
                hashMap.put("msg", this.speedNode);
                JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", JtGsonUtil.getInstance().toJson(hashMap), "PING");
                if (!TextUtils.isEmpty(this.speedNode)) {
                    this.executorPing.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.jtcommon.XWIFI.8
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                XWIFI.this.netTimeTelay = NetLinkManage.getIntance().getNetTimeTelay("www.baidu.com");
                                if (XWIFI.this.netTimeTelay != null) {
                                    JtL.m16342e(JtGsonUtil.getInstance().toJson(XWIFI.this.netTimeTelay));
                                    Message message = new Message();
                                    message.what = 100;
                                    XWIFI.this.mhandler.sendMessage(message);
                                } else {
                                    Message message2 = new Message();
                                    message2.what = 101;
                                    XWIFI.this.mhandler.sendMessage(message2);
                                }
                            } catch (Exception e) {
                                if (e.getMessage().equals("ping停止")) {
                                    return;
                                }
                                Message message3 = new Message();
                                message3.what = 101;
                                XWIFI.this.mhandler.sendMessage(message3);
                            }
                        }
                    });
                    return;
                }
                JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener2 = this.jtOnWifiSpeedListener;
                if (jtOnWifiClientSpeedListener2 != null) {
                    jtOnWifiClientSpeedListener2.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(109, "测速节点不能为空！", "2006", "ping异常")));
                }
                stop();
                return;
            }
            JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener3 = this.jtOnWifiSpeedListener;
            if (jtOnWifiClientSpeedListener3 != null) {
                jtOnWifiClientSpeedListener3.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(109, "测速节点不能为空！", "2006", "ping异常")));
            }
            stop();
        }
    }

    public void setOnWifiSpeedListener(JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener) {
        this.jtOnWifiSpeedListener = jtOnWifiClientSpeedListener;
    }

    public void stop() {
        this.isStopClick = true;
        OkHttpProxy.cancel(this.wifitag);
        NetLinkManage.getIntance().stopPing();
        JtSpeedClient jtSpeedClient = this.speedClient;
        if (jtSpeedClient != null) {
            jtSpeedClient.onDistory();
        }
        this.isStart = false;
        ExecutorService executorService = this.executorPing;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        Handler handler = this.mhandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        JtWifiClient jtWifiClient = this.jtWifiClient;
        if (jtWifiClient != null) {
            jtWifiClient.onDestroy();
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", "测速停止");
            JtUploadLog.getInstance(JtApp.getInstance().getContext()).updateData("1", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "wifi_speed_stop");
        } catch (JSONException e) {
            JtUploadLog jtUploadLog = JtUploadLog.getInstance(JtApp.getInstance().getContext());
            jtUploadLog.updateData("1", "测速停止," + e.getMessage(), "transition_json_error");
        }
        JtApp.getInstance().uploadLog();
        this.uniqueIdentification = "";
        JtUploadLog.getInstance(JtApp.getInstance().getContext()).setUniqueIdentification(this.uniqueIdentification);
    }

    public void onDestroy() {
        stop();
        if (this.jtOnSpeedClientListener != null) {
            this.jtOnSpeedClientListener = null;
        }
        XWIFI unused = Builder.xwifi = null;
    }

    private void setRoomId(String str) {
        this.roomId = str;
    }

    public void setMillisecond(int i) {
        if (i > 3000 || i < 100) {
            this.millisecond = 1000;
        } else {
            this.millisecond = i;
        }
    }

    public void setMobileNetwork(boolean z) {
        this.isMobileNetwork = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNotMobileNetworkStop() {
        if (this.isMobileNetwork || AppInfo.isWifi(this.mContext)) {
            return false;
        }
        JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.jtOnWifiSpeedListener;
        if (jtOnWifiClientSpeedListener != null) {
            jtOnWifiClientSpeedListener.filed(JtGsonUtil.getInstance().toJson(new ErrorCode(112, "当前是移动网络，已停止测速!", "2004", "移动网络环境，已停止测速")));
        }
        stop();
        return true;
    }

    private void setPing(boolean z) {
        this.isPing = z;
    }

    private void setExtends(String str) {
        this.mExtends = str;
    }

    public boolean isDownloadUrl() {
        return this.isDownloadUrl;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private static XWIFI xwifi;
        private Activity activity;
        private JtOnWifiClientSpeedListener onWifiSpeedListener;
        private String params;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setOnWifiSpeedListener(JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener) {
            this.onWifiSpeedListener = jtOnWifiClientSpeedListener;
            return this;
        }

        public Builder initSpeed(String str) {
            this.params = str;
            return this;
        }

        public XWIFI create() {
            if (xwifi == null) {
                xwifi = new XWIFI(this.activity);
            }
            xwifi.initSpeed(this.params);
            JtOnWifiClientSpeedListener jtOnWifiClientSpeedListener = this.onWifiSpeedListener;
            if (jtOnWifiClientSpeedListener != null) {
                xwifi.setOnWifiSpeedListener(jtOnWifiClientSpeedListener);
            }
            return xwifi;
        }
    }
}
