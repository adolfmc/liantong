package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.Coroutines;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CoroutineIntrinsics.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0007"}, m1890d2 = {"interceptContinuationIfNeeded", "Lkotlin/coroutines/experimental/Continuation;", "T", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "continuation", "normalizeContinuation", "kotlin-stdlib-coroutines"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "CoroutineIntrinsics")
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CoroutineIntrinsics {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Coroutines<T> normalizeContinuation(@NotNull Coroutines<? super T> continuation) {
        Coroutines<T> coroutines;
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        CoroutineImpl coroutineImpl = !(continuation instanceof CoroutineImpl) ? null : continuation;
        return (coroutineImpl == null || (coroutines = (Coroutines<T>) coroutineImpl.getFacade()) == null) ? continuation : coroutines;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Coroutines<T> interceptContinuationIfNeeded(@NotNull CoroutineContext context, @NotNull Coroutines<? super T> continuation) {
        Coroutines<T> interceptContinuation;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) context.get(ContinuationInterceptor.Key);
        return (continuationInterceptor == null || (interceptContinuation = continuationInterceptor.interceptContinuation(continuation)) == null) ? continuation : interceptContinuation;
    }
}
