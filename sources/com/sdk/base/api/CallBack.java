package com.sdk.base.api;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface CallBack<T> {
    void onFailed(int i, int i2, String str, String str2);

    void onSuccess(int i, String str, int i2, T t, String str2);
}
