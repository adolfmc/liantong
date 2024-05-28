package com.uber.autodispose;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface SingleSubscribeProxy<T> {
    Disposable subscribe();

    Disposable subscribe(BiConsumer<? super T, ? super Throwable> biConsumer);

    Disposable subscribe(Consumer<? super T> consumer);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2);

    void subscribe(SingleObserver<T> singleObserver);

    @CheckReturnValue
    <E extends SingleObserver<? super T>> E subscribeWith(E e);

    @CheckReturnValue
    TestObserver<T> test();

    @CheckReturnValue
    TestObserver<T> test(boolean z);
}
