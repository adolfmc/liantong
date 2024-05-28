package com.huawei.hms.common.api;

import com.huawei.hms.support.api.client.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Response<T extends Result> {
    protected T result;

    public Response() {
    }

    protected T getResult() {
        return this.result;
    }

    public void setResult(T t) {
        this.result = t;
    }

    protected Response(T t) {
        this.result = t;
    }
}
