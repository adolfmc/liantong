package com.uber.autodispose;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.TestSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface FlowableSubscribeProxy<T> {
    Disposable subscribe();

    Disposable subscribe(Consumer<? super T> consumer);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action);

    Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Subscription> consumer3);

    void subscribe(Subscriber<T> subscriber);

    @CheckReturnValue
    <E extends Subscriber<? super T>> E subscribeWith(E e);

    @CheckReturnValue
    TestSubscriber<T> test();

    @CheckReturnValue
    TestSubscriber<T> test(long j);

    @CheckReturnValue
    TestSubscriber<T> test(long j, boolean z);
}
