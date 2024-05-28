package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpRequestFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpRequestFactory */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpRequestFactory implements IHttpRequestFactory {
    private volatile HttpRequestExecutor mExecutor;

    @Override // com.baidu.p120ar.ihttp.IHttpRequestFactory
    public IHttpRequest newRequest() {
        HttpRequestFacade httpRequestFacade = new HttpRequestFacade(this);
        httpRequestFacade.setCharset(HttpDefaultConfig.CHARSET);
        httpRequestFacade.setConnectionTimeout(2000);
        httpRequestFacade.setReadTimeout(30000);
        httpRequestFacade.setUseCache(false);
        httpRequestFacade.addHeaders(HttpDefaultConfig.COMMON_HEADERS);
        return httpRequestFacade;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequestFactory
    public void release() {
        if (this.mExecutor != null) {
            this.mExecutor.shutdown();
            this.mExecutor = null;
        }
    }

    public HttpRequestExecutor getExecutor() {
        if (this.mExecutor == null) {
            synchronized (this) {
                if (this.mExecutor == null) {
                    this.mExecutor = new HttpRequestExecutor(2, 5, 1000, 60L);
                }
            }
        }
        return this.mExecutor;
    }
}
