package com.baidu.p120ar.remoteres;

import com.baidu.p120ar.ARType;
import com.baidu.p120ar.callback.ICallback;
import com.baidu.p120ar.callback.ICallbackWith;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.remoteres.IDuMixResManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IDuMixResManager {
    void downloadARRes(ARType aRType, ICallback iCallback);

    void setErrorCallback(ICallbackWith<IDuMixResLoadTask> iCallbackWith);
}
