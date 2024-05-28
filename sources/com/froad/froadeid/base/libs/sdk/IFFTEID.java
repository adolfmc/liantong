package com.froad.froadeid.base.libs.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.froad.eid.unify.bean.IDSEConfig;
import com.froad.libreadcard.constants.ReadCardType;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IFFTEID {
    public static final String KEY_ENCIDINFO = "encIdInfo";
    public static final String KEY_REQID = "reqid";

    String getSDKVersion();

    @Deprecated
    void init(Context context);

    void nfcReadCard(Intent intent);

    IFFTEID setDecodeParams(IDSEConfig iDSEConfig);

    @Deprecated
    IFFTEID setPropertiesFileEnv(int i);

    void setReadIDInfoImg(boolean z);

    void setReadSoPath(String str);

    void startReadCard(ReadCardType readCardType, Activity activity, ReadCardInfoCallBack readCardInfoCallBack);

    void startReadCard(ReadCardType readCardType, Activity activity, ReadCardInfoCallBack readCardInfoCallBack, String str, String str2, String str3);

    void stopReadCard();
}
