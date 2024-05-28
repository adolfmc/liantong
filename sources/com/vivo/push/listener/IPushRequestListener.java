package com.vivo.push.listener;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IPushRequestListener<T, V> {
    void onFail(V v);

    void onSuccess(T t);
}
