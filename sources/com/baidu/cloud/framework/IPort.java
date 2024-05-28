package com.baidu.cloud.framework;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IPort<T> {
    void onConfigure(Object obj);

    void onFrame(T t);

    void onPortLinked();

    void onPortUnlinked();
}
