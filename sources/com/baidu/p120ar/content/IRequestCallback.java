package com.baidu.p120ar.content;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.content.IRequestCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRequestCallback<T> {
    void onFail(int i, String str);

    void onResponse(T t);
}
