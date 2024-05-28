package com.uber.autodispose.observers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface AutoDisposingSingleObserver<T> extends SingleObserver<T>, Disposable {
    SingleObserver<? super T> delegateObserver();
}
