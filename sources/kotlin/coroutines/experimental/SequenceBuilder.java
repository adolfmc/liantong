package kotlin.coroutines.experimental;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SequenceBuilder.kt */
@SinceKotlin(version = "1.1")
@RestrictsSuspension
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001f\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0012"}, m1890d2 = {"Lkotlin/coroutines/experimental/SequenceBuilder;", "T", "", "()V", "yield", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "yieldAll", "elements", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "iterator", "", "(Ljava/util/Iterator;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "sequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class SequenceBuilder<T> {
    @Nullable
    public abstract Object yield(T t, @NotNull Coroutines<? super Unit> coroutines);

    @Nullable
    public abstract Object yieldAll(@NotNull Iterator<? extends T> it, @NotNull Coroutines<? super Unit> coroutines);

    @Nullable
    public final Object yieldAll(@NotNull Iterable<? extends T> iterable, @NotNull Coroutines<? super Unit> coroutines) {
        Object yieldAll;
        return (!((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) && (yieldAll = yieldAll(iterable.iterator(), coroutines)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? yieldAll : Unit.INSTANCE;
    }

    @Nullable
    public final Object yieldAll(@NotNull Sequence<? extends T> sequence, @NotNull Coroutines<? super Unit> coroutines) {
        Object yieldAll = yieldAll(sequence.iterator(), coroutines);
        return yieldAll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? yieldAll : Unit.INSTANCE;
    }
}
