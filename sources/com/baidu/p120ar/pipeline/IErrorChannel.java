package com.baidu.p120ar.pipeline;

import com.baidu.p120ar.callback.IError;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.IErrorChannel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IErrorChannel {
    void onError(int i, String str, IError iError);
}
