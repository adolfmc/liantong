package com.uber.autodispose;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ObservableSubscribeProxy<T> {
    Disposable subscribe();

    Disposable subscribe(Consumer<? super T> consumer);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Disposable> consumer3);

    void subscribe(Observer<T> observer);

    @CheckReturnValue
    <E extends Observer<? super T>> E subscribeWith(E e);

    @CheckReturnValue
    TestObserver<T> test();

    @CheckReturnValue
    TestObserver<T> test(boolean z);
}
