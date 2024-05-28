package com.p189cn.froad.clouddecodingsdk.core;

import com.eidlink.idocr.sdk.EidLinkSE;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.FFTeIDSEFactory */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FFTeIDSEFactory {
    public static EidLinkSE geteIDSE(EidlinkInitParams eidlinkInitParams, OnEidInitListener onEidInitListener) {
        return FFTeIDSE.getInstance(eidlinkInitParams, onEidInitListener);
    }
}
