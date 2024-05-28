package com.baidu.p120ar.pipeline;

import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.IError;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.ErrorChannelHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ErrorChannelHandler<T> extends AbstractChannelHandler<T, Void> {
    private IErrorChannel mRealChannel;

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    protected void doCancel() {
    }

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    protected void run(T t, ICallbackWith<Void> iCallbackWith, IError iError) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorChannelHandler(IErrorChannel iErrorChannel) {
        this.mRealChannel = iErrorChannel;
    }

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    protected void onError(int i, String str, IError iError) {
        IErrorChannel iErrorChannel;
        if (isCanceled() || (iErrorChannel = this.mRealChannel) == null) {
            return;
        }
        iErrorChannel.onError(i, str, iError);
    }
}
