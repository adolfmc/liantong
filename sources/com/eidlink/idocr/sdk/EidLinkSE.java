package com.eidlink.idocr.sdk;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack;
import com.eidlink.idocr.sdk.listener.EidLogCallBack;
import com.eidlink.idocr.sdk.listener.OnEidOpenListener;
import com.eidlink.idocr.sdk.listener.OnGetEidStatusListener;
import com.eidlink.idocr.sdk.listener.OnGetResultListener;
import com.zkteco.zkmulticardreader.ZKMultiCardReader;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface EidLinkSE {
    void eidAuth(String str, OnGetResultListener onGetResultListener);

    void eidAuth(String str, String str2, OnGetResultListener onGetResultListener);

    void eidGetAppeidcode(OnGetResultListener onGetResultListener);

    void eidIsOpen(Context context, OnGetEidStatusListener onGetEidStatusListener);

    void eidIsOpen(Context context, boolean z, OnGetEidStatusListener onGetEidStatusListener);

    void eidSign(String str, OnGetResultListener onGetResultListener);

    void eidSign(String str, String str2, OnGetResultListener onGetResultListener);

    void eidToOpen(OnEidOpenListener onEidOpenListener);

    void getEidLog(EidLogCallBack eidLogCallBack);

    String getUnionpay_vehicle_sn();

    void readCardBT(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, OnGetResultListener onGetResultListener);

    void readIDCard(int i, Intent intent, OnGetResultListener onGetResultListener);

    void readIDCard(int i, Tag tag, OnGetResultListener onGetResultListener);

    void readIDCard(int i, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener);

    void readIDCard(Intent intent, OnGetResultListener onGetResultListener);

    void readIDCard(Tag tag, OnGetResultListener onGetResultListener);

    void readTravel(Tag tag, String str, String str2, String str3, boolean z, EidLinkReadCardCallBack eidLinkReadCardCallBack, OnGetResultListener onGetResultListener);

    void readTravel(Tag tag, String str, String str2, String str3, boolean z, OnGetResultListener onGetResultListener);

    void readTravel(EidLinkReadCardCallBack eidLinkReadCardCallBack, String str, String str2, String str3, boolean z, OnGetResultListener onGetResultListener);

    void readWalletEC(String str, OnGetResultListener onGetResultListener);

    void readWalletEC(String str, String str2, OnGetResultListener onGetResultListener);

    void readWalletEC(String str, String str2, String str3, OnGetResultListener onGetResultListener);

    void release();

    boolean setDeviceType(int i);

    void setDeviceTypeSn(Context context, int i);

    void setGetDataFromSdk(boolean z);

    void setNeedAddress(boolean z);

    void setReadCount(int i);

    void setReadLength(int i);

    void setReadPicture(boolean z);

    void setUnionpaySn(Context context);

    void setUnionpay_vehicle_sn();

    void setUseNewVersion(boolean z);

    String setZKSn(ZKMultiCardReader zKMultiCardReader);
}
