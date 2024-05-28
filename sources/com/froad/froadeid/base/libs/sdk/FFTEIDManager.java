package com.froad.froadeid.base.libs.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.froad.eid.unify.bean.IDSEConfig;
import com.froad.eid.unify.manager.GlobalBeanManager;
import com.froad.eid.unify.utils.StringUtil;
import com.froad.froadeid.base.libs.core.ReadInfoType;
import com.froad.froadeid.base.libs.utils.CommonUtils;
import com.froad.libloadso.LoadSOUtils;
import com.froad.libreadcard.constants.ReadCardStatus;
import com.froad.libreadcard.constants.ReadCardType;
import com.froad.libreadcard.manager.NFCReadManager;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FFTEIDManager implements IFFTEID {
    private final String TAG;
    private String binName;
    private String eAppId;
    private String eIp;
    private int eKeyIndex;
    private int ePort;
    private int eidSdkProEnv;
    private boolean isReadIDInfoFinger;
    private boolean isReadIDInfoImg;
    private boolean isReadIDInfoText;
    private String licName;
    private ReadCardType readType;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final FFTEIDManager INSTANCE = new FFTEIDManager();

        private SingletonHolder() {
        }
    }

    private FFTEIDManager() {
        this.TAG = "FFTEIDManager";
        this.readType = ReadCardType.ID_CARD;
        this.eidSdkProEnv = 0;
        this.eIp = "";
        this.ePort = 9989;
        this.eAppId = "";
        this.eKeyIndex = 26814;
        this.licName = "";
        this.binName = "";
        this.isReadIDInfoImg = true;
        this.isReadIDInfoText = true;
        this.isReadIDInfoFinger = false;
    }

    private void checkReadCard(ReadCardType readCardType, Activity activity, ReadCardInfoCallBack readCardInfoCallBack, String str, String str2, String str3) {
        ReadCardStatus readCardStatus;
        ReadInfoType readInfoType;
        String str4;
        String str5;
        if (activity == null) {
            if (readCardInfoCallBack != null) {
                readCardInfoCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.REQID, "F30003", "读卡接口参数设置错误");
            }
        } else if (Build.VERSION.SDK_INT < 19) {
            if (readCardInfoCallBack != null) {
                readCardInfoCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.REQID, "F30004", "系统版本太低，不支持云识读功能");
            }
        } else {
            NFCReadManager.getInstance().initNfc(activity.getApplicationContext());
            if (NFCReadManager.getInstance().isSupportNFC()) {
                if (NFCReadManager.getInstance().isEnableNFC()) {
                    if (!GlobalBeanManager.getInstance().isReading()) {
                        GlobalBeanManager.getInstance().setReading(true);
                        startNfcRead(readCardType, activity, readCardInfoCallBack, str, str2, str3);
                        return;
                    }
                    TMKeyLog.m16309e("FFTEIDManager", "readType : " + readCardType + " GlobalBeanManager.getInstance().isReading() ");
                    return;
                } else if (readCardInfoCallBack == null) {
                    return;
                } else {
                    readCardStatus = ReadCardStatus.FAILED;
                    readInfoType = ReadInfoType.REQID;
                    str4 = "F30006";
                    str5 = "NFC功能未开启";
                }
            } else if (readCardInfoCallBack == null) {
                return;
            } else {
                readCardStatus = ReadCardStatus.FAILED;
                readInfoType = ReadInfoType.REQID;
                str4 = "F30005";
                str5 = "设备不支持NFC功能";
            }
            readCardInfoCallBack.readResult(readCardStatus, readInfoType, str4, str5);
        }
    }

    public static IFFTEID getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void setPrintLog(boolean z) {
        TMKeyLog.setPrintLog(z);
    }

    private void startNfcRead(final ReadCardType readCardType, Activity activity, final ReadCardInfoCallBack readCardInfoCallBack, final String str, final String str2, final String str3) {
        TMKeyLog.m16310d("FFTEIDManager", "startNfcRead() called with: readType = [" + readCardType + "], activity = [" + activity + "], cardInfoCallBack = [" + readCardInfoCallBack + "]");
        NFCReadManager.getInstance().setActivity(activity).setCallBack(new ReadCardInfoCallBack() { // from class: com.froad.froadeid.base.libs.sdk.FFTEIDManager.1
            @Override // com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack
            public void readResult(ReadCardStatus readCardStatus, ReadInfoType readInfoType, String str4, String str5) {
                TMKeyLog.m16310d("FFTEIDManager", "startNfcRead>>>state:" + readCardStatus + ">>>errCode:" + str4 + ">>>msg:" + str5);
                if (readCardStatus == ReadCardStatus.SUCCESS) {
                    NFCReadManager.getInstance().setCallBack(readCardInfoCallBack).setTravelInfo(str, str2, str3).setCardType(readCardType);
                } else {
                    readCardInfoCallBack.readResult(readCardStatus, readInfoType, str4, str5);
                }
            }
        });
        if (readCardType != ReadCardType.BANK_CARD) {
            NFCReadManager.getInstance().initEid();
        } else {
            NFCReadManager.getInstance().setCallBack(readCardInfoCallBack).setTravelInfo(str, str2, str3).setCardType(readCardType);
        }
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public String getSDKVersion() {
        return "2.0.3";
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void init(Context context) {
        String str = this.eidSdkProEnv == 1 ? "eidsdk_release.properties" : "eidsdk.properties";
        if (CommonUtils.isAssetsFileExists(context, str)) {
            if (StringUtil.isEmpty(this.eIp) || StringUtil.isEmpty(this.eAppId)) {
                InputStream inputStream = null;
                try {
                    try {
                        Properties properties = new Properties();
                        inputStream = context.getAssets().open(str);
                        properties.load(new InputStreamReader(inputStream, "utf-8"));
                        String property = properties.getProperty("fnEidIp", "");
                        int parseInt = Integer.parseInt(properties.getProperty("fnEidPort", "0"));
                        String property2 = properties.getProperty("fnAppId", "");
                        int parseInt2 = Integer.parseInt(properties.getProperty("fnEidkeyNum", "0"));
                        if (StringUtil.isNotEmpty(property) && StringUtil.isNotEmpty(property2)) {
                            GlobalBeanManager.getInstance().setIDSEConfig(new IDSEConfig.Builder().m15916ip(property).port(parseInt).appId(property2).pubkeyIndex(parseInt2).build());
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException unused) {
                        throw new IllegalArgumentException("please check " + str + "info");
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void nfcReadCard(Intent intent) {
        NFCReadManager.getInstance().onNewIntent(intent);
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public IFFTEID setDecodeParams(IDSEConfig iDSEConfig) {
        this.eIp = iDSEConfig.getIp();
        this.ePort = iDSEConfig.getPort();
        this.eAppId = iDSEConfig.getAppId();
        this.eKeyIndex = iDSEConfig.getPubkeyIndex();
        this.licName = iDSEConfig.getLicName();
        this.binName = iDSEConfig.getBinName();
        GlobalBeanManager.getInstance().setIDSEConfig(iDSEConfig);
        return this;
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public IFFTEID setPropertiesFileEnv(int i) {
        this.eidSdkProEnv = i;
        return this;
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void setReadIDInfoImg(boolean z) {
        this.isReadIDInfoImg = z;
        GlobalBeanManager.getInstance().setReadPhoto(z);
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void setReadSoPath(String str) {
        LoadSOUtils.setSoFilePath(str);
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void startReadCard(ReadCardType readCardType, Activity activity, ReadCardInfoCallBack readCardInfoCallBack) {
        checkReadCard(readCardType, activity, readCardInfoCallBack, "", "", "");
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void startReadCard(ReadCardType readCardType, Activity activity, ReadCardInfoCallBack readCardInfoCallBack, String str, String str2, String str3) {
        checkReadCard(readCardType, activity, readCardInfoCallBack, str, str2, str3);
    }

    @Override // com.froad.froadeid.base.libs.sdk.IFFTEID
    public void stopReadCard() {
        GlobalBeanManager.getInstance().setReading(false);
        FFTEIDReadCardJNI.getJNI().stopReadCard();
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        NFCReadManager.getInstance().disableReader();
    }
}
