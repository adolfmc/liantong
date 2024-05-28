package com.p189cn.froad.clouddecodingsdk.core;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import com.eidlink.idocr.sdk.EidLinkSE;
import com.eidlink.idocr.sdk.EidLinkSEFactory;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack;
import com.eidlink.idocr.sdk.listener.EidLogCallBack;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;
import com.eidlink.idocr.sdk.listener.OnEidOpenListener;
import com.eidlink.idocr.sdk.listener.OnGetEidStatusListener;
import com.eidlink.idocr.sdk.listener.OnGetResultListener;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.AppExecutors;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.FCharUtils;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import com.zkteco.zkmulticardreader.ZKMultiCardReader;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.CommoneIDSE */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CommoneIDSE implements EidLinkSE {
    private static final String TAG = "CommoneIDSE";
    private static final EidLinkSE eIDSE = new CommoneIDSE();
    private static EidLinkSE ststandby;
    private static EidLinkSE traget;

    private CommoneIDSE() {
    }

    public static EidLinkSE getInstance(final EidlinkInitParams eidlinkInitParams, final EidlinkInitParams eidlinkInitParams2, final OnEidInitListener onEidInitListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("eidlinkInitParams == null ");
        sb.append(eidlinkInitParams == null);
        TMKeyLog.m16310d(TAG, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("standbyParams == null ");
        sb2.append(eidlinkInitParams2 == null);
        TMKeyLog.m16310d(TAG, sb2.toString());
        if (eidlinkInitParams == null && eidlinkInitParams2 == null) {
            throw new IllegalArgumentException("Configuration is not correct!");
        }
        if (eidlinkInitParams == null) {
            CommoneIDSEFactory.setThirdInstituteEnable(false);
            eidlinkInitParams = new EidlinkInitParams(eidlinkInitParams2.context, "", "", 0, 0);
        } else {
            CommoneIDSEFactory.setThirdInstituteEnable(true);
        }
        if (FCharUtils.findJavaClass("com.eidlink.jni.EIDReadCardJNI")) {
            AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.core.CommoneIDSE.1
                @Override // java.lang.Runnable
                public void run() {
                    EidLinkSE unused = CommoneIDSE.traget = EidLinkSEFactory.getEidLinkSE(EidlinkInitParams.this, new OnEidInitListener() { // from class: com.cn.froad.clouddecodingsdk.core.CommoneIDSE.1.1
                        @Override // com.eidlink.idocr.sdk.listener.OnEidInitListener
                        public void onFailed(int i) {
                            TMKeyLog.m16310d(CommoneIDSE.TAG, "EID onFailed() called with: i = [" + i + "]");
                        }

                        @Override // com.eidlink.idocr.sdk.listener.OnEidInitListener
                        public void onSuccess() {
                            TMKeyLog.m16310d(CommoneIDSE.TAG, "EID onSuccess() called");
                        }
                    });
                }
            });
        }
        if (eidlinkInitParams2 != null) {
            CommoneIDSEFactory.setFFTEnable(true);
            AppExecutors.getAppExecutors().postDiskIOThread(new Runnable() { // from class: com.cn.froad.clouddecodingsdk.core.CommoneIDSE.2
                @Override // java.lang.Runnable
                public void run() {
                    EidLinkSE unused = CommoneIDSE.ststandby = FFTeIDSEFactory.geteIDSE(EidlinkInitParams.this, onEidInitListener);
                    TMKeyLog.m16310d(CommoneIDSE.TAG, "getInstance>>>ststandby:" + CommoneIDSE.ststandby.hashCode());
                }
            });
        } else {
            CommoneIDSEFactory.setFFTEnable(false);
        }
        return eIDSE;
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidAuth(String str, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidAuth(str, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidAuth(String str, String str2, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidAuth(str, str2, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidGetAppeidcode(OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidGetAppeidcode(onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidIsOpen(Context context, OnGetEidStatusListener onGetEidStatusListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidIsOpen(context, onGetEidStatusListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidIsOpen(Context context, boolean z, OnGetEidStatusListener onGetEidStatusListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidIsOpen(context, z, onGetEidStatusListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidSign(String str, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidSign(str, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidSign(String str, String str2, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidSign(str, str2, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void eidToOpen(OnEidOpenListener onEidOpenListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.eidToOpen(onEidOpenListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void getEidLog(EidLogCallBack eidLogCallBack) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.getEidLog(eidLogCallBack);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public String getUnionpay_vehicle_sn() {
        EidLinkSE eidLinkSE = traget;
        return eidLinkSE != null ? eidLinkSE.getUnionpay_vehicle_sn() : "";
    }

    public boolean isStandBy() {
        TMKeyLog.m16310d(TAG, "isStandBy>>>ststandby:" + ststandby + ">>>isFFTEnable:" + CommoneIDSEFactory.isFFTEnable() + ">>>getCurrentType:" + FFTEIDReadCardJNI.getJNI().getCurrentType());
        return ststandby != null && CommoneIDSEFactory.isFFTEnable() && FFTEIDReadCardJNI.getJNI().getCurrentType() == 1;
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readCardBT(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readCardBT(i, eidLinkReadCardCallBack, str, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, Intent intent, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE;
        if (!isStandBy()) {
            eidLinkSE = traget;
            if (eidLinkSE == null) {
                return;
            }
        } else if (i != 1) {
            if (onGetResultListener != null) {
                onGetResultListener.onFailed(15925264, "FFT_未检测到正确的证件");
                return;
            }
            return;
        } else {
            eidLinkSE = ststandby;
        }
        eidLinkSE.readIDCard(i, intent, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, Tag tag, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE;
        if (!isStandBy()) {
            eidLinkSE = traget;
            if (eidLinkSE == null) {
                return;
            }
        } else if (i != 1) {
            if (onGetResultListener != null) {
                onGetResultListener.onFailed(15925264, "FFT_未检测到正确的证件");
                return;
            }
            return;
        } else {
            eidLinkSE = ststandby;
        }
        eidLinkSE.readIDCard(i, tag, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE;
        TMKeyLog.m16310d(TAG, "readIDCard>>>i:" + i + ">>>EidLinkReadCardCallBack:" + eidLinkReadCardCallBack.hashCode() + ">>>OnGetResultListener:" + onGetResultListener.hashCode());
        if (!isStandBy()) {
            eidLinkSE = traget;
            if (eidLinkSE == null) {
                return;
            }
        } else if (i != 1) {
            onGetResultListener.onFailed(15925264, "FFT_未检测到正确的证件");
            return;
        } else {
            eidLinkSE = ststandby;
        }
        eidLinkSE.readIDCard(i, eidLinkReadCardCallBack, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(Intent intent, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE;
        if (isStandBy()) {
            eidLinkSE = ststandby;
        } else {
            eidLinkSE = traget;
            if (eidLinkSE == null) {
                return;
            }
        }
        eidLinkSE.readIDCard(intent, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readIDCard(Tag tag, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE;
        if (isStandBy()) {
            eidLinkSE = ststandby;
        } else {
            eidLinkSE = traget;
            if (eidLinkSE == null) {
                return;
            }
        }
        eidLinkSE.readIDCard(tag, onGetResultListener);
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(Tag tag, String str, String str2, String str3, boolean z, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener) {
        if (isStandBy()) {
            ststandby.readTravel(tag, str, str2, str3, z, eidLinkReadCardCallBack, onGetResultListener);
            return;
        }
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readTravel(tag, str, str2, str3, z, eidLinkReadCardCallBack, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(Tag tag, String str, String str2, String str3, boolean z, OnGetResultListener onGetResultListener) {
        if (isStandBy()) {
            ststandby.readTravel(tag, str, str2, str3, z, onGetResultListener);
            return;
        }
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readTravel(tag, str, str2, str3, z, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readTravel(EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, String str2, String str3, boolean z, OnGetResultListener onGetResultListener) {
        if (isStandBy()) {
            ststandby.readTravel(eidLinkReadCardCallBack, str, str2, str3, z, onGetResultListener);
            return;
        }
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readTravel(eidLinkReadCardCallBack, str, str2, str3, z, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readWalletEC(str, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, String str2, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readWalletEC(str, str2, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void readWalletEC(String str, String str2, String str3, OnGetResultListener onGetResultListener) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.readWalletEC(str, str2, str3, onGetResultListener);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void release() {
        if (isStandBy()) {
            ststandby.release();
        }
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.release();
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public boolean setDeviceType(int i) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            return eidLinkSE.setDeviceType(i);
        }
        return false;
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setDeviceTypeSn(Context context, int i) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setDeviceTypeSn(context, i);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setGetDataFromSdk(boolean z) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setGetDataFromSdk(z);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setNeedAddress(boolean z) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setNeedAddress(z);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadCount(int i) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setReadCount(i);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadLength(int i) {
        if (isStandBy()) {
            ststandby.setReadLength(i);
        }
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setReadLength(i);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setReadPicture(boolean z) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setReadPicture(z);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUnionpaySn(Context context) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setUnionpaySn(context);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUnionpay_vehicle_sn() {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setUnionpay_vehicle_sn();
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public void setUseNewVersion(boolean z) {
        EidLinkSE eidLinkSE = traget;
        if (eidLinkSE != null) {
            eidLinkSE.setUseNewVersion(z);
        }
    }

    @Override // com.eidlink.idocr.sdk.EidLinkSE
    public String setZKSn(ZKMultiCardReader zKMultiCardReader) {
        EidLinkSE eidLinkSE = traget;
        return eidLinkSE != null ? eidLinkSE.setZKSn(zKMultiCardReader) : "";
    }
}
