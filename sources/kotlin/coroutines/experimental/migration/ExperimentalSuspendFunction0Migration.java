package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.experimental.Coroutines;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CoroutinesMigration.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002B!\u0012\u001a\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002¢\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002R%\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m1890d2 = {"Lkotlin/coroutines/experimental/migration/ExperimentalSuspendFunction0Migration;", "R", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "function", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)V", "getFunction", "()Lkotlin/jvm/functions/Function1;", "invoke", "continuation", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
final class ExperimentalSuspendFunction0Migration<R> implements Function1<Coroutines<? super R>, Object> {
    @NotNull
    private final Function1<Continuation<? super R>, Object> function;

    /* JADX WARN: Multi-variable type inference failed */
    public ExperimentalSuspendFunction0Migration(@NotNull Function1<? super Continuation<? super R>, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.function = function;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke((Coroutines) ((Coroutines) obj));
    }

    @NotNull
    public final Function1<Continuation<? super R>, Object> getFunction() {
        return this.function;
    }

    @Nullable
    public Object invoke(@NotNull Coroutines<? super R> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return this.function.invoke(CoroutinesMigrationKt.toContinuation(continuation));
    }
}
