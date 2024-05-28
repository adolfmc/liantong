package com.baidu.p120ar.remoteres;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.remoteres.IDuMixResLoadTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IDuMixResLoadTask {
    boolean canRetry();

    String getError();

    void retry();
}
