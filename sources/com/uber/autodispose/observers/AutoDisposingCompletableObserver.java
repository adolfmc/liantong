package com.uber.autodispose.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface AutoDisposingCompletableObserver extends CompletableObserver, Disposable {
    CompletableObserver delegateObserver();
}
