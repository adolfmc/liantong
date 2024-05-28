package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IProgressCallback;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.net.HttpURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.http.HttpCall */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpCall implements IHttpCall {
    private IHttpCallback mCallback;
    private HttpURLConnection mConnection;
    private volatile boolean mIsCanceled;
    private Object mLock;
    private IProgressCallback mProgressCallback;
    private HttpRequestInfo mRequest;
    private HttpResponse mResponse;

    public HttpCall(HttpRequestInfo httpRequestInfo) {
        this(httpRequestInfo, null);
    }

    public HttpCall(HttpRequestInfo httpRequestInfo, IHttpCallback iHttpCallback) {
        this.mLock = new Object();
        this.mIsCanceled = false;
        this.mRequest = httpRequestInfo;
        this.mCallback = iHttpCallback;
        this.mConnection = null;
        this.mResponse = null;
    }

    public void setProgressCallback(IProgressCallback iProgressCallback) {
        this.mProgressCallback = iProgressCallback;
    }

    @Override // com.baidu.p120ar.http.IHttpCall
    public HttpResponse getResponse() {
        return this.mResponse;
    }

    @Override // com.baidu.p120ar.http.IHttpCall
    public void cancel() {
        if (this.mIsCanceled) {
            return;
        }
        this.mCallback = null;
        this.mProgressCallback = null;
        synchronized (this.mLock) {
            this.mIsCanceled = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0111  */
    @Override // com.baidu.p120ar.http.IHttpCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.p120ar.http.HttpResponse execute() throws com.baidu.p120ar.ihttp.HttpException {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.http.HttpCall.execute():com.baidu.ar.http.HttpResponse");
    }

    @Override // java.lang.Runnable
    public void run() {
        HttpURLConnection httpURLConnection;
        HttpResponse execute;
        try {
            try {
                execute = execute();
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (HttpException e) {
                    e = e;
                    if (0 == 0 && 0 == 0 && this.mCallback != null) {
                        this.mCallback.onFail(e);
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                }
            } catch (HttpException e2) {
                e = e2;
            }
            synchronized (this.mLock) {
                try {
                    boolean z = this.mIsCanceled;
                    if (!z && this.mCallback != null) {
                        this.mCallback.onResponse(execute);
                    }
                    HttpURLConnection httpURLConnection2 = this.mConnection;
                    if (httpURLConnection2 == null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } finally {
            httpURLConnection = this.mConnection;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
