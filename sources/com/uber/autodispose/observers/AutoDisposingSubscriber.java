package com.uber.autodispose.observers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface AutoDisposingSubscriber<T> extends FlowableSubscriber<T>, Disposable, Subscription {
    Subscriber<? super T> delegateSubscriber();
}
