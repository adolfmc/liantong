package com.uber.autodispose;

import io.reactivex.annotations.NonNull;
import org.reactivestreams.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ParallelFlowableSubscribeProxy<T> {
    void subscribe(@NonNull Subscriber<? super T>[] subscriberArr);
}
