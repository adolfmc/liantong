package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface Pool<T> {
    int count();

    T get();

    void release();

    void setMaxSize(int i);
}
