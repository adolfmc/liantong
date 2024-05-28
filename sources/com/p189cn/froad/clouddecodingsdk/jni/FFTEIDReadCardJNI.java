package com.p189cn.froad.clouddecodingsdk.jni;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.bean.EidlinkResult;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;
import com.eidlink.idocr.sdk.listener.OnGetResultListener;
import com.froad.libloadso.LoadSOUtils;
import com.froad.libreadcard.constants.ReadCardType;
import com.p189cn.froad.clouddecodingsdk.core.CommoneIDSEFactory;
import com.p189cn.froad.clouddecodingsdk.core.DeviceInfo;
import com.p189cn.froad.clouddecodingsdk.core.EidFFTInitParams;
import com.p189cn.froad.clouddecodingsdk.p190a.C4165a;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.AppExecutors;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.DeviceUtil;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.FCharUtils;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FFTEIDReadCardJNI {
    public static final int FFT = 1;
    public static final int IDCARD = 1;
    public static final int QUERY_CARD_FAILED = 50000005;
    public static final int QUERY_CARD_SUCCESS = 20000002;
    public static final int READ_CARD_ENC_INFO_SUCCESS = 30000004;
    public static final int READ_CARD_FAILED = 90000009;
    public static final int READ_CARD_START = 10000001;
    public static final int READ_CARD_SUCCESS = 30000003;
    private static final String TAG = "FFTEIDReadCardJNI";
    public static final int TRAVELCARD = 2;
    public static final int ThirdInstitute = 2;
    private static boolean loadRes;
    private String appid;
    private Context context;
    private int currentCount;
    private int currentReadType;
    private String encFileName;
    private String fftReqID;
    private String fftdeviceID;
    private volatile boolean isReading;
    private String licName;
    private String licSm3Value;
    private OnGetResultListener mOnGetResultListener;
    private OnGetResultListener mQueryListener;
    private Handler mainHandler;
    private Object nfcTransfer;
    private String readCardInfo;
    private long startTime;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI$SingletonHolder */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final FFTEIDReadCardJNI INSTANCE = new FFTEIDReadCardJNI();

        private SingletonHolder() {
        }
    }

    static {
        TMKeyLog.m16310d(TAG, "System.loadLibrary start");
        try {
            System.loadLibrary("fft_eid_read_card_V2_0");
            loadRes = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        TMKeyLog.m16310d(TAG, "System.loadLibrary>>>loadRes:" + loadRes);
        if (!loadRes) {
            loadRes = LoadSOUtils.customLoadLibrary(LoadSOUtils.getSoFilePath());
            TMKeyLog.m16310d(TAG, "LoadSOUtils.customLoadLibrary>>>loadRes:" + loadRes);
            if (!loadRes) {
                return;
            }
        }
        nativeInit();
    }

    private FFTEIDReadCardJNI() {
        this.currentCount = 0;
        this.currentReadType = 1;
        this.readCardInfo = "";
        this.isReading = false;
        this.licSm3Value = "";
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public static /* synthetic */ int access$608(FFTEIDReadCardJNI fFTEIDReadCardJNI) {
        int i = fFTEIDReadCardJNI.currentCount;
        fFTEIDReadCardJNI.currentCount = i + 1;
        return i;
    }

    public static byte[] dataStr(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        if (str.length() % 2 != 0) {
            str = str + "0";
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (indexOfPos(charArray[i2 + 1]) | (indexOfPos(charArray[i2]) << 4));
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0069 A[LOOP:0: B:17:0x0065->B:19:0x0069, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String dealDeviceIDIndex(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 != 0) goto L2b
            java.lang.String r0 = "&"
            boolean r0 = r9.contains(r0)
            if (r0 == 0) goto L2b
            java.lang.String r0 = "&"
            java.lang.String[] r9 = r9.split(r0)
            int r0 = r9.length
            r3 = 2
            if (r0 != r3) goto L2b
            r0 = 0
            r0 = r9[r0]
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L2b
            r0 = 1
            r9 = r9[r0]
            long r3 = java.lang.Long.parseLong(r9)
            goto L2c
        L2b:
            r3 = r1
        L2c:
            java.lang.String r9 = java.lang.Long.toHexString(r3)
            java.lang.String r9 = r9.toUpperCase()
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 < 0) goto L3e
            goto L42
        L3e:
            r0 = 1
            long r1 = r3 + r0
        L42:
            android.content.Context r0 = r7.context
            com.cn.froad.clouddecodingsdk.a.a r0 = com.p189cn.froad.clouddecodingsdk.p190a.C4165a.m16326a(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            java.lang.String r8 = "&"
            r3.append(r8)
            r3.append(r1)
            java.lang.String r8 = r3.toString()
            java.lang.String r1 = "curFFTDeviceIdIndex"
            r0.m16316b(r1, r8)
            int r8 = r9.length()
        L65:
            r0 = 16
            if (r8 >= r0) goto L7d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "0"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            int r8 = r8 + 1
            goto L65
        L7d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.dealDeviceIDIndex(java.lang.String, java.lang.String):java.lang.String");
    }

    public static byte[] getDeviceInfo() {
        return dataStr(DeviceInfo.getfftDeviceInfo(SingletonHolder.INSTANCE.context, SingletonHolder.INSTANCE.startTime, System.currentTimeMillis() - SingletonHolder.INSTANCE.startTime));
    }

    public static FFTEIDReadCardJNI getJNI() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getQuerySendData(ReadCardType readCardType) {
        this.startTime = System.currentTimeMillis();
        TMKeyLog.m16310d(TAG, "getQuerySendData>>>startTime:" + this.startTime);
        String timeDeviceType = DeviceUtil.getTimeDeviceType(this.startTime, "0");
        String str = "01";
        if (readCardType == ReadCardType.TRAVEL_CARD) {
            str = "02";
        } else if (readCardType == ReadCardType.E_CARD_NFC) {
            str = "03";
        }
        this.fftdeviceID = DeviceUtil.getDeviceID(this.context);
        boolean nativeIsReading = nativeIsReading();
        TMKeyLog.m16310d(TAG, "getQuerySendData>>>nativeIsReading:" + nativeIsReading);
        if (!nativeIsReading || TextUtils.isEmpty(this.fftReqID)) {
            this.fftReqID = DeviceUtil.getFFTReqID(str, this.fftdeviceID, this.startTime);
        }
        TMKeyLog.m16310d(TAG, "getQuerySendData>>>fftReqID:" + this.fftReqID);
        String str2 = FCharUtils.hexStr2LV(str) + FCharUtils.hexStr2LV(FCharUtils.string2HexStr(this.appid)) + FCharUtils.hexStr2LV(this.fftReqID);
        String str3 = this.fftdeviceID;
        TMKeyLog.m16310d(TAG, "DeviceUtil.androidType:" + DeviceUtil.androidType);
        String packageName = this.context.getPackageName();
        if ("AU".equals(DeviceUtil.androidType)) {
            String str4 = "0000000000000000";
            Context context = this.context;
            if (context != null) {
                str4 = dealDeviceIDIndex(this.fftdeviceID, C4165a.m16326a(context).m16322a("curFFTDeviceIdIndex", ""));
            }
            str3 = str3 + str4;
            packageName = "com.froad.readcard.otherdev";
        }
        TMKeyLog.m16310d(TAG, "tempFftDeviceID:" + str3);
        String str5 = str2 + FCharUtils.hexStr2LV(str3) + FCharUtils.hexStr2LV(timeDeviceType) + FCharUtils.hexStr2LV(FCharUtils.string2HexStr(DeviceUtil.getDeviceIndex(DeviceUtil.androidType))) + FCharUtils.hexStr2LV(this.licSm3Value) + FCharUtils.hexStr2LV(FCharUtils.string2HexStr(packageName));
        TMKeyLog.m16310d(TAG, "querySendData:" + str5);
        return str5;
    }

    public static byte indexOfPos(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static native void nativeInit();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeQueryReadEvn(String str);

    public native int getCurrentType();

    public native String getSM3Hex(String str);

    public native int nativeInitParams(Context context, String str, String str2, int i, int i2, int i3, String str3, String str4);

    public void nativeInitParams(EidlinkInitParams eidlinkInitParams, OnEidInitListener onEidInitListener) {
        int i;
        TMKeyLog.m16310d(TAG, "nativeinitParams() called with: initParams = [" + eidlinkInitParams + "]");
        if (eidlinkInitParams == null || eidlinkInitParams.context == null) {
            i = 15929348;
        } else {
            this.licName = "";
            this.encFileName = "";
            if (eidlinkInitParams instanceof EidFFTInitParams) {
                EidFFTInitParams eidFFTInitParams = (EidFFTInitParams) eidlinkInitParams;
                this.licName = eidFFTInitParams.getLicName();
                this.encFileName = eidFFTInitParams.getEncFileName();
            }
            TMKeyLog.m16310d(TAG, "nativeInitParams>>>licName:" + this.licName + ">>>encFileName:" + this.encFileName);
            if (TextUtils.isEmpty(eidlinkInitParams.f9735ip)) {
                i = 15929345;
            } else if (!TextUtils.isEmpty(eidlinkInitParams.appid)) {
                Context applicationContext = eidlinkInitParams.getContext().getApplicationContext();
                this.context = applicationContext;
                String str = eidlinkInitParams.appid;
                this.appid = str;
                int nativeInitParams = nativeInitParams(applicationContext, str, eidlinkInitParams.f9735ip, eidlinkInitParams.port, 1, eidlinkInitParams.envIdCode, this.licName, this.encFileName);
                TMKeyLog.m16310d(TAG, "nativeInitParams>>>initRes:" + nativeInitParams);
                if (onEidInitListener != null) {
                    if (nativeInitParams == 1) {
                        onEidInitListener.onSuccess();
                        return;
                    } else {
                        onEidInitListener.onFailed(15929351);
                        return;
                    }
                }
                return;
            } else {
                i = 15929346;
            }
        }
        onEidInitListener.onFailed(i);
    }

    public native boolean nativeIsReading();

    public native void nativeRelease();

    public void queryReadEvn(final ReadCardType readCardType, OnGetResultListener onGetResultListener) {
        TMKeyLog.m16310d(TAG, "queryReadEvn() called with: cardType = [" + readCardType + "] isFFTEnable : " + CommoneIDSEFactory.isFFTEnable());
        if (this.isReading) {
            TMKeyLog.m16310d(TAG, "queryReadEvn: isReading " + this.isReading);
            return;
        }
        this.isReading = true;
        if (CommoneIDSEFactory.isFFTEnable()) {
            setCurrentType(1);
            this.mQueryListener = onGetResultListener;
            AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.4
                @Override // java.lang.Runnable
                public void run() {
                    FFTEIDReadCardJNI fFTEIDReadCardJNI = FFTEIDReadCardJNI.this;
                    fFTEIDReadCardJNI.nativeQueryReadEvn(fFTEIDReadCardJNI.getQuerySendData(readCardType));
                }
            });
        } else if (CommoneIDSEFactory.isThirdInstituteEnable()) {
            setCurrentType(2);
        }
    }

    public native void readIDCard(Object obj, String str, int i);

    public native void readPassPort(Object obj, String str, String str2, String str3, String str4);

    public void release() {
        nativeRelease();
    }

    public void sendMessage(final int i, final String str, final int i2) {
        TMKeyLog.m16310d(TAG, "sendMessage>>>what:" + i + ">>>errorCode:" + i2 + ">>>errCodeHex:" + FCharUtils.int2HexStr(i2));
        this.mainHandler.post(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.1
            @Override // java.lang.Runnable
            public void run() {
                EidlinkResult eidlinkResult;
                String str2;
                if (FFTEIDReadCardJNI.this.mOnGetResultListener != null) {
                    int i3 = i;
                    if (i3 == 30000004) {
                        FFTEIDReadCardJNI.this.isReading = false;
                        eidlinkResult = new EidlinkResult();
                        eidlinkResult.setReqId("");
                        str2 = FFTEIDReadCardJNI.this.readCardInfo;
                    } else if (i3 == 30000003) {
                        FFTEIDReadCardJNI.this.isReading = false;
                        eidlinkResult = new EidlinkResult(FFTEIDReadCardJNI.this.fftReqID);
                        str2 = "";
                    } else if (i3 == 90000009) {
                        TMKeyLog.m16310d(FFTEIDReadCardJNI.TAG, "sendMessage>>>currentCount:" + FFTEIDReadCardJNI.this.currentCount);
                        if (FFTEIDReadCardJNI.this.nfcTransfer == null || FFTEIDReadCardJNI.this.currentCount >= 2 || FFTEIDReadCardJNI.this.currentReadType != 1) {
                            FFTEIDReadCardJNI.this.isReading = false;
                            FFTEIDReadCardJNI.this.mOnGetResultListener.onFailed(i2, str);
                            FFTEIDReadCardJNI.this.mOnGetResultListener = null;
                        } else {
                            FFTEIDReadCardJNI.access$608(FFTEIDReadCardJNI.this);
                            AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    FFTEIDReadCardJNI fFTEIDReadCardJNI = FFTEIDReadCardJNI.this;
                                    fFTEIDReadCardJNI.readIDCard(fFTEIDReadCardJNI.nfcTransfer, FFTEIDReadCardJNI.this.getQuerySendData(ReadCardType.ID_CARD), FFTEIDReadCardJNI.this.currentCount);
                                }
                            });
                        }
                    } else if (i3 == 10000001) {
                        FFTEIDReadCardJNI.this.mOnGetResultListener.onStart();
                    }
                    eidlinkResult.setData(str2);
                    FFTEIDReadCardJNI.this.mOnGetResultListener.onSuccess(eidlinkResult);
                    FFTEIDReadCardJNI.this.mOnGetResultListener = null;
                }
                if (FFTEIDReadCardJNI.this.mQueryListener != null) {
                    int i4 = i;
                    if (i4 == 20000002) {
                        FFTEIDReadCardJNI.this.mQueryListener.onSuccess(new EidlinkResult("路由成功"));
                    } else if (i4 != 50000005) {
                        return;
                    } else {
                        if (!CommoneIDSEFactory.isThirdInstituteEnable()) {
                            FFTEIDReadCardJNI.this.setCurrentType(1);
                        }
                        FFTEIDReadCardJNI.this.isReading = false;
                        FFTEIDReadCardJNI.this.mQueryListener.onFailed(i2, "FFT_路由失败");
                    }
                    FFTEIDReadCardJNI.this.mQueryListener = null;
                }
            }
        });
    }

    public native void setCurrentType(int i);

    public native void setDecodeMode(boolean z, boolean z2, boolean z3);

    public void setLicSm3Value(String str) {
        this.licSm3Value = str;
    }

    public native void setLogLevel(int i);

    public void setReadCardInfo(String str) {
        this.readCardInfo = str;
    }

    public native void setReadLength(int i);

    public native String sm2EncHex(byte[] bArr, byte[] bArr2, int i);

    public void startRead(final Object obj, OnGetResultListener onGetResultListener) {
        TMKeyLog.m16310d(TAG, "startRead");
        this.mOnGetResultListener = onGetResultListener;
        this.nfcTransfer = obj;
        this.currentCount = 0;
        this.currentReadType = 1;
        AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.2
            @Override // java.lang.Runnable
            public void run() {
                FFTEIDReadCardJNI fFTEIDReadCardJNI = FFTEIDReadCardJNI.this;
                fFTEIDReadCardJNI.readIDCard(obj, fFTEIDReadCardJNI.getQuerySendData(ReadCardType.ID_CARD), FFTEIDReadCardJNI.this.currentCount);
            }
        });
    }

    public void startRead(final Object obj, OnGetResultListener onGetResultListener, final String str, final String str2, final String str3) {
        TMKeyLog.m16310d(TAG, "startRead>>>cardnum:" + str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            if (onGetResultListener != null) {
                onGetResultListener.onFailed(15929349, "FFT_三要素信息为空");
                return;
            }
            return;
        }
        this.mOnGetResultListener = onGetResultListener;
        this.currentReadType = 2;
        AppExecutors.getAppExecutors().postSingleThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI.3
            @Override // java.lang.Runnable
            public void run() {
                FFTEIDReadCardJNI fFTEIDReadCardJNI = FFTEIDReadCardJNI.this;
                fFTEIDReadCardJNI.readPassPort(obj, fFTEIDReadCardJNI.getQuerySendData(ReadCardType.TRAVEL_CARD), str, str2, str3);
            }
        });
    }

    public void stopReadCard() {
        TMKeyLog.m16310d(TAG, "stopReadCard");
        this.isReading = false;
        this.currentCount = 0;
        setCurrentType(1);
    }
}
