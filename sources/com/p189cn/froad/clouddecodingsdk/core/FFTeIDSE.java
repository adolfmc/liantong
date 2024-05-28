package com.p189cn.froad.clouddecodingsdk.core;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcB;
import com.eidlink.idocr.sdk.EidLinkSE;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack;
import com.eidlink.idocr.sdk.listener.EidLogCallBack;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;
import com.eidlink.idocr.sdk.listener.OnEidOpenListener;
import com.eidlink.idocr.sdk.listener.OnGetEidStatusListener;
import com.eidlink.idocr.sdk.listener.OnGetResultListener;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.AppExecutors;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import com.zkteco.zkmulticardreader.ZKMultiCardReader;
import java.io.IOException;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.FFTeIDSE */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FFTeIDSE implements EidLinkSE {
    private static final String TAG = "FFTeIDSE";
    private static FFTeIDSE mFFTeIDSE = new FFTeIDSE();

    private FFTeIDSE() {
    }

    public static EidLinkSE getInstance(final EidlinkInitParams eidlinkInitParams, final OnEidInitListener onEidInitListener) {
        AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.core.FFTeIDSE.1
            @Override // java.lang.Runnable
            public void run() {
                FFTEIDReadCardJNI.getJNI().nativeInitParams(EidlinkInitParams.this, onEidInitListener);
            }
        });
        return mFFTeIDSE;
    }

    public static String getSDKVersion() {
        return "2.0.0";
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidAuth(String str, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidAuth(String str, String str2, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidGetAppeidcode(OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidIsOpen(Context context, OnGetEidStatusListener onGetEidStatusListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidIsOpen(Context context, boolean z, OnGetEidStatusListener onGetEidStatusListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidSign(String str, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidSign(String str, String str2, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidToOpen(OnEidOpenListener onEidOpenListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void getEidLog(EidLogCallBack eidLogCallBack) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public String getUnionpay_vehicle_sn() {
        return null;
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readCardBT(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, Intent intent, OnGetResultListener onGetResultListener) {
        readIDCard(intent, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, Tag tag, OnGetResultListener onGetResultListener) {
        readIDCard(tag, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener) {
        FFTEIDReadCardJNI.getJNI().startRead(eidLinkReadCardCallBack, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(Intent intent, OnGetResultListener onGetResultListener) {
        if ("android.nfc.action.TECH_DISCOVERED".equals(intent.getAction())) {
            Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
            TMKeyLog.m16310d(TAG, "onNewIntent: " + tag);
            readIDCard(tag, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(Tag tag, final OnGetResultListener onGetResultListener) {
        NfcB nfcB = NfcB.get(tag);
        if (nfcB != null && !nfcB.isConnected()) {
            try {
                nfcB.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (nfcB == null || !nfcB.isConnected()) {
            AppExecutors.getAppExecutors().postMainThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.core.FFTeIDSE.2
                @Override // java.lang.Runnable
                public void run() {
                    OnGetResultListener onGetResultListener2 = onGetResultListener;
                    if (onGetResultListener2 != null) {
                        onGetResultListener2.onFailed(15925264, "FFT_未检测到正确的证件");
                    }
                }
            });
        } else {
            FFTEIDReadCardJNI.getJNI().startRead(nfcB, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(Tag tag, String str, String str2, String str3, boolean z, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener) {
        readTravel(eidLinkReadCardCallBack, str, str2, str3, z, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(Tag tag, String str, String str2, String str3, boolean z, final OnGetResultListener onGetResultListener) {
        IsoDep isoDep = IsoDep.get(tag);
        if (isoDep != null && !isoDep.isConnected()) {
            try {
                isoDep.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isoDep == null || !isoDep.isConnected()) {
            AppExecutors.getAppExecutors().postMainThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.core.FFTeIDSE.3
                @Override // java.lang.Runnable
                public void run() {
                    OnGetResultListener onGetResultListener2 = onGetResultListener;
                    if (onGetResultListener2 != null) {
                        onGetResultListener2.onFailed(15925264, "FFT_未检测到正确的证件");
                    }
                }
            });
            return;
        }
        isoDep.setTimeout(15000);
        FFTEIDReadCardJNI.getJNI().startRead(isoDep, onGetResultListener, str, str2, str3);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, String str2, String str3, boolean z, OnGetResultListener onGetResultListener) {
        FFTEIDReadCardJNI.getJNI().startRead(eidLinkReadCardCallBack, onGetResultListener, str, str2, str3);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, String str2, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, String str2, String str3, OnGetResultListener onGetResultListener) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void release() {
        FFTEIDReadCardJNI.getJNI().release();
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public boolean setDeviceType(int i) {
        return false;
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setDeviceTypeSn(Context context, int i) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setGetDataFromSdk(boolean z) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setNeedAddress(boolean z) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadCount(int i) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadLength(int i) {
        FFTEIDReadCardJNI.getJNI().setReadLength(i);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadPicture(boolean z) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUnionpaySn(Context context) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUnionpay_vehicle_sn() {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUseNewVersion(boolean z) {
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public String setZKSn(ZKMultiCardReader zKMultiCardReader) {
        return null;
    }
}
