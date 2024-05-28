package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TimeSource.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u001b\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m1890d2 = {"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@ExperimentalTime
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class TimeMark {
    public abstract double elapsedNow();

    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo25439plusLRDsOJo(double d) {
        return new AdjustedTimeMark(this, d, null);
    }

    @NotNull
    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public TimeMark m25490minusLRDsOJo(double d) {
        return mo25439plusLRDsOJo(Duration.m25483unaryMinusimpl(d));
    }

    public final boolean hasPassedNow() {
        return !Duration.m25463isNegativeimpl(elapsedNow());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m25463isNegativeimpl(elapsedNow());
    }
}
