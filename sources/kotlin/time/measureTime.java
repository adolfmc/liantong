package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001a0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u001a4\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m1890d2 = {"measureTime", "Lkotlin/time/Duration;", "block", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)D", "measureTimedValue", "Lkotlin/time/TimedValue;", "T", "Lkotlin/time/TimeSource;", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)D", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.time.MeasureTimeKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class measureTime {
    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    public static final double measureTime(@NotNull Functions<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        TimeMark markNow = TimeSource.Monotonic.INSTANCE.markNow();
        block.invoke();
        return markNow.elapsedNow();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    public static final double measureTime(@NotNull TimeSource measureTime, @NotNull Functions<Unit> block) {
        Intrinsics.checkParameterIsNotNull(measureTime, "$this$measureTime");
        Intrinsics.checkParameterIsNotNull(block, "block");
        TimeMark markNow = measureTime.markNow();
        block.invoke();
        return markNow.elapsedNow();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull Functions<? extends T> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return new TimedValue<>(block.invoke(), TimeSource.Monotonic.INSTANCE.markNow().elapsedNow(), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull TimeSource measureTimedValue, @NotNull Functions<? extends T> block) {
        Intrinsics.checkParameterIsNotNull(measureTimedValue, "$this$measureTimedValue");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return new TimedValue<>(block.invoke(), measureTimedValue.markNow().elapsedNow(), null);
    }
}
