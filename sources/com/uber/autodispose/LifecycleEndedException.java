package com.uber.autodispose;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class LifecycleEndedException extends OutsideLifecycleException {
    public LifecycleEndedException() {
        this("Lifecycle has ended!");
    }

    public LifecycleEndedException(String str) {
        super(str);
    }
}
