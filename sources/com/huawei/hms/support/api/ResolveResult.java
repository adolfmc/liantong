package com.huawei.hms.support.api;

import com.huawei.hms.support.api.client.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ResolveResult<T> extends Result {
    private T entity;

    public ResolveResult() {
        this.entity = null;
    }

    public T getValue() {
        return this.entity;
    }

    public ResolveResult(T t) {
        this.entity = t;
    }
}
