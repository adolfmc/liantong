package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@SinceKotlin(version = "1.1")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, m1890d2 = {"Lkotlin/coroutines/experimental/Continuation;", "T", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: kotlin.coroutines.experimental.Continuation */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface Coroutines<T> {
    @NotNull
    CoroutineContext getContext();

    void resume(T t);

    void resumeWithException(@NotNull Throwable th);
}
