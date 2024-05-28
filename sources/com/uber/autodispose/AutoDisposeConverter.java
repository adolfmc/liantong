package com.uber.autodispose;

import io.reactivex.CompletableConverter;
import io.reactivex.FlowableConverter;
import io.reactivex.MaybeConverter;
import io.reactivex.ObservableConverter;
import io.reactivex.SingleConverter;
import io.reactivex.parallel.ParallelFlowableConverter;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface AutoDisposeConverter<T> extends CompletableConverter<CompletableSubscribeProxy>, FlowableConverter<T, FlowableSubscribeProxy<T>>, MaybeConverter<T, MaybeSubscribeProxy<T>>, ObservableConverter<T, ObservableSubscribeProxy<T>>, SingleConverter<T, SingleSubscribeProxy<T>>, ParallelFlowableConverter<T, ParallelFlowableSubscribeProxy<T>> {
}
