package com.baidu.p120ar.steploading;

import com.baidu.p120ar.callback.ICallback;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.IError;
import com.baidu.p120ar.pipeline.AbstractChannelHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.CallbackHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CallbackHandler<T> extends AbstractChannelHandler<T, T> {
    private ICallback mCallback;
    private IError mErrorBack;

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void doCancel() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallbackHandler(ICallback iCallback, IError iError) {
        this.mCallback = iCallback;
        this.mErrorBack = iError;
    }

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void run(T t, ICallbackWith<T> iCallbackWith, IError iError) {
        ICallback iCallback = this.mCallback;
        if (iCallback != null) {
            iCallback.run();
        }
    }

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void onError(int i, String str, IError iError) {
        IError iError2 = this.mErrorBack;
        if (iError2 != null) {
            iError2.onError(i, str, null);
        }
    }
}
