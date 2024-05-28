package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IProgressCallback;
import com.baidu.p120ar.utils.ARLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpRequestExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpRequestExecutor {
    private int mCorePoolSize;
    private ExecutorService mExecutorService;
    private long mKeepAliveSeconds;
    private int mMaxPoolSize;
    private int mQueueCapacity;

    public HttpRequestExecutor(int i, int i2, int i3, long j) {
        this.mCorePoolSize = i;
        this.mMaxPoolSize = i2;
        this.mQueueCapacity = i3;
        this.mKeepAliveSeconds = j;
    }

    public IHttpCall enqueue(HttpRequestInfo httpRequestInfo, IHttpCallback iHttpCallback, IProgressCallback iProgressCallback) {
        HttpCall httpCall = new HttpCall(httpRequestInfo, iHttpCallback);
        httpCall.setProgressCallback(iProgressCallback);
        getExecutorService().submit(httpCall);
        return httpCall;
    }

    public IHttpCall execute(HttpRequestInfo httpRequestInfo, IProgressCallback iProgressCallback) throws HttpException {
        HttpCall httpCall = new HttpCall(httpRequestInfo);
        httpCall.setProgressCallback(iProgressCallback);
        httpCall.execute();
        return httpCall;
    }

    public IHttpCall callOnFail(HttpException httpException, IHttpCallback iHttpCallback) {
        FailCall failCall = new FailCall(httpException, iHttpCallback);
        getExecutorService().submit(failCall);
        return failCall;
    }

    private ExecutorService getExecutorService() {
        if (this.mExecutorService == null) {
            this.mExecutorService = new ThreadPoolExecutor(this.mCorePoolSize, this.mMaxPoolSize, this.mKeepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue(this.mQueueCapacity), Executors.defaultThreadFactory(), new RejectedExecutionHandler() { // from class: com.baidu.ar.http.HttpRequestExecutor.1
                @Override // java.util.concurrent.RejectedExecutionHandler
                public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                    ARLog.m20419e("HttpRequestExecutor", "请求队列已满，请求被丢弃");
                }
            });
        }
        return this.mExecutorService;
    }

    public void shutdown() {
        ExecutorService executorService = this.mExecutorService;
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
