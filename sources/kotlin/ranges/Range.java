package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0016R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, m1890d2 = {"Lkotlin/ranges/ClosedRange;", "T", "", "", "endInclusive", "getEndInclusive", "()Ljava/lang/Comparable;", "start", "getStart", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: kotlin.ranges.ClosedRange */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface Range<T extends Comparable<? super T>> {
    boolean contains(@NotNull T t);

    @NotNull
    T getEndInclusive();

    @NotNull
    T getStart();

    boolean isEmpty();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Range.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1889k = 3, m1888mv = {1, 1, 16})
    /* renamed from: kotlin.ranges.ClosedRange$DefaultImpls */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(Range<T> range, @NotNull T value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            return value.compareTo(range.getStart()) >= 0 && value.compareTo(range.getEndInclusive()) <= 0;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(Range<T> range) {
            return range.getStart().compareTo(range.getEndInclusive()) > 0;
        }
    }
}
