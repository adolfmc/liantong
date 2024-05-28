package com.baidu.p120ar.ihttp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.IHttpCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IHttpCallback {
    void onFail(HttpException httpException);

    void onResponse(IHttpResponse iHttpResponse);
}
