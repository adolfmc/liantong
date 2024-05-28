package com.p189cn.froad.clouddecodingsdk.core;

import com.eidlink.idocr.sdk.EidLinkSE;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.CommoneIDSEFactory */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CommoneIDSEFactory {
    private static boolean mIsFFTEnable;
    private static boolean mThirdInstituteEnable;

    public static EidLinkSE geteIDSEReadCard(EidlinkInitParams eidlinkInitParams, EidlinkInitParams eidlinkInitParams2, OnEidInitListener onEidInitListener) {
        return CommoneIDSE.getInstance(eidlinkInitParams, eidlinkInitParams2, onEidInitListener);
    }

    public static boolean isFFTEnable() {
        return mIsFFTEnable;
    }

    public static boolean isThirdInstituteEnable() {
        return mThirdInstituteEnable;
    }

    public static void setFFTEnable(boolean z) {
        mIsFFTEnable = z;
    }

    public static void setThirdInstituteEnable(boolean z) {
        mThirdInstituteEnable = z;
    }
}
