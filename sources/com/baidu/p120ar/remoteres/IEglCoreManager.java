package com.baidu.p120ar.remoteres;

import android.content.Context;
import com.baidu.p120ar.callback.ICallback;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.ihttp.IProgressCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.remoteres.IEglCoreManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IEglCoreManager {
    void prepareEglCore(ICallback iCallback, IProgressCallback iProgressCallback, ICallbackWith<IDuMixResLoadTask> iCallbackWith);

    void release();

    void setup(Context context);
}
