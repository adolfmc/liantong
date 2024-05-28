package com.eidlink.idocr.sdk.listener;

import com.eidlink.idocr.sdk.bean.EidlinkResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class OnGetResultListener {
    public void onApdu() {
    }

    public abstract void onFailed(int i, String str);

    public void onStart() {
    }

    public abstract void onSuccess(EidlinkResult eidlinkResult);
}
