package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.HttpException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.IHttpCall */
/* loaded from: E:\10201592_dexfile_execute.dex */
interface IHttpCall extends Runnable {
    void cancel();

    HttpResponse execute() throws HttpException;

    HttpResponse getResponse();
}
