package com.baidu.p120ar.steploading;

import com.baidu.p120ar.callback.ICallbackWith;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.IStepLoading */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IStepLoading {
    void cancel();

    void retry();

    void setLoadErrorListener(ICallbackWith<IStepLoading> iCallbackWith);
}
