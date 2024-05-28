package io.reactivex.internal.fuseable;

import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ScalarCallable<T> extends Callable<T> {
    @Override // java.util.concurrent.Callable
    T call();
}
