package com.vivo.push.restructure.request;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IPushRequestCallback<T> {
    void onError(int i);

    void onSuccess(T t);
}
