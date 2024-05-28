package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IHttpCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.FailCall */
/* loaded from: E:\10201592_dexfile_execute.dex */
class FailCall implements IHttpCall {
    private IHttpCallback mCallback;
    private HttpException mException;
    private Object mLock = new Object();
    private volatile boolean mIsCanceled = false;

    @Override // com.baidu.p120ar.http.IHttpCall
    public HttpResponse getResponse() {
        return null;
    }

    public FailCall(HttpException httpException, IHttpCallback iHttpCallback) {
        this.mException = httpException;
        this.mCallback = iHttpCallback;
    }

    @Override // com.baidu.p120ar.http.IHttpCall
    public HttpResponse execute() throws HttpException {
        synchronized (this.mLock) {
            if (this.mIsCanceled) {
                return null;
            }
            throw this.mException;
        }
    }

    @Override // com.baidu.p120ar.http.IHttpCall
    public void cancel() {
        if (this.mIsCanceled) {
            return;
        }
        synchronized (this.mLock) {
            Boolean bool = true;
            this.mIsCanceled = bool.booleanValue();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.mLock) {
            if (this.mIsCanceled) {
                return;
            }
            IHttpCallback iHttpCallback = this.mCallback;
            if (iHttpCallback != null) {
                iHttpCallback.onFail(this.mException);
            }
        }
    }
}
