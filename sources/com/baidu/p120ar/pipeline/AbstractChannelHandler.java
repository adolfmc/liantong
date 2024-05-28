package com.baidu.p120ar.pipeline;

import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.IError;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.AbstractChannelHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractChannelHandler<InT, OutT> implements IChannel<InT, OutT> {
    private int mErrCode;
    private String mErrMsg;
    private boolean mHasErr;
    private boolean mIsCanceled;
    private boolean mIsEnd;
    private AbstractChannelHandler<OutT, ?> mNext;
    private OutT mOut;
    private boolean mPassErr;

    protected abstract void doCancel();

    protected abstract void run(InT r1, ICallbackWith<OutT> iCallbackWith, IError iError);

    public final void execute(InT r3) {
        if (this.mIsCanceled) {
            return;
        }
        run(r3, new ICallbackWith<OutT>() { // from class: com.baidu.ar.pipeline.AbstractChannelHandler.1
            @Override // com.baidu.p120ar.callback.ICallbackWith
            public void run(OutT outt) {
                AbstractChannelHandler.this.mIsEnd = true;
                AbstractChannelHandler.this.mOut = outt;
                if (AbstractChannelHandler.this.mIsCanceled || AbstractChannelHandler.this.mNext == null) {
                    return;
                }
                AbstractChannelHandler.this.mNext.execute(outt);
            }
        }, new IError() { // from class: com.baidu.ar.pipeline.AbstractChannelHandler.2
            @Override // com.baidu.p120ar.callback.IError
            public void onError(int i, String str, Exception exc) {
                AbstractChannelHandler.this.processError(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordError(int i, String str) {
        this.mPassErr = true;
        this.mErrCode = i;
        this.mErrMsg = str;
    }

    protected final void processError(int i, String str) {
        this.mIsEnd = true;
        this.mHasErr = true;
        onError(i, str, new IError() { // from class: com.baidu.ar.pipeline.AbstractChannelHandler.3
            @Override // com.baidu.p120ar.callback.IError
            public void onError(int i2, String str2, Exception exc) {
                AbstractChannelHandler.this.recordError(i2, str2);
                if (AbstractChannelHandler.this.mIsCanceled || AbstractChannelHandler.this.mNext == null) {
                    return;
                }
                AbstractChannelHandler.this.mNext.processError(i2, str2);
            }
        });
    }

    protected void onError(int i, String str, IError iError) {
        if (iError != null) {
            iError.onError(i, str, null);
        }
    }

    private void checkNextRun() {
        AbstractChannelHandler<OutT, ?> abstractChannelHandler;
        if (this.mIsCanceled || !this.mIsEnd || (abstractChannelHandler = this.mNext) == null) {
            return;
        }
        if (this.mPassErr) {
            abstractChannelHandler.processError(this.mErrCode, this.mErrMsg);
        } else if (this.mHasErr) {
        } else {
            abstractChannelHandler.execute(this.mOut);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.baidu.p120ar.pipeline.IChannel
    public <T> IChannel<OutT, T> next(AbstractChannelHandler<OutT, T> abstractChannelHandler) {
        this.mNext = abstractChannelHandler;
        checkNextRun();
        return abstractChannelHandler;
    }

    @Override // com.baidu.p120ar.pipeline.IChannel
    public IChannel<OutT, Void> fail(IErrorChannel iErrorChannel) {
        ErrorChannelHandler errorChannelHandler = new ErrorChannelHandler(iErrorChannel);
        this.mNext = errorChannelHandler;
        checkNextRun();
        return errorChannelHandler;
    }

    @Override // com.baidu.p120ar.callback.ICancellable
    public void cancel() {
        this.mIsCanceled = true;
        doCancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCanceled() {
        return this.mIsCanceled;
    }
}
