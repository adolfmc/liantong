package com.baidu.platform.comjni;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class NativeComponent extends JNIBaseApi {
    protected volatile long mNativePointer;

    public abstract long create();

    public abstract int dispose();

    protected void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
