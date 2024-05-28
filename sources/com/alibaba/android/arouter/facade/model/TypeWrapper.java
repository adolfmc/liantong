package com.alibaba.android.arouter.facade.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TypeWrapper<T> {
    protected final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected TypeWrapper() {
    }

    public Type getType() {
        return this.type;
    }
}
