package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Ranges.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, m1890d2 = {"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(JJ)V", "getEndInclusive", "()Ljava/lang/Long;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class LongRange extends LongProgression implements Range<Long> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final LongRange EMPTY = new LongRange(1, 0);

    public LongRange(long j, long j2) {
        super(j, j2, 1L);
    }

    @Override // kotlin.ranges.Range
    public /* synthetic */ boolean contains(Long l) {
        return contains(l.longValue());
    }

    @Override // kotlin.ranges.Range
    @NotNull
    public Long getStart() {
        return Long.valueOf(getFirst());
    }

    @Override // kotlin.ranges.Range
    @NotNull
    public Long getEndInclusive() {
        return Long.valueOf(getLast());
    }

    public boolean contains(long j) {
        return getFirst() <= j && j <= getLast();
    }

    @Override // kotlin.ranges.LongProgression, kotlin.ranges.Range
    public boolean isEmpty() {
        return getFirst() > getLast();
    }

    @Override // kotlin.ranges.LongProgression
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LongRange) {
            if (!isEmpty() || !((LongRange) obj).isEmpty()) {
                LongRange longRange = (LongRange) obj;
                if (getFirst() != longRange.getFirst() || getLast() != longRange.getLast()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.LongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (int) ((31 * (getFirst() ^ (getFirst() >>> 32))) + (getLast() ^ (getLast() >>> 32)));
    }

    @Override // kotlin.ranges.LongProgression
    @NotNull
    public String toString() {
        return getFirst() + ".." + getLast();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Ranges.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m1890d2 = {"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final LongRange getEMPTY() {
            return LongRange.EMPTY;
        }
    }
}
