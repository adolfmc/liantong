package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.Unsigned;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, m1890d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.random.URandomKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class URandom {
    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final int nextUInt(@NotNull Random nextUInt) {
        Intrinsics.checkParameterIsNotNull(nextUInt, "$this$nextUInt");
        return UInt.m24574constructorimpl(nextUInt.nextInt());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: nextUInt-qCasIEU  reason: not valid java name */
    public static final int m25394nextUIntqCasIEU(@NotNull Random nextUInt, int i) {
        Intrinsics.checkParameterIsNotNull(nextUInt, "$this$nextUInt");
        return m25393nextUInta8DCA5k(nextUInt, 0, i);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: nextUInt-a8DCA5k  reason: not valid java name */
    public static final int m25393nextUInta8DCA5k(@NotNull Random nextUInt, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(nextUInt, "$this$nextUInt");
        m25388checkUIntRangeBoundsJ1ME1BU(i, i2);
        return UInt.m24574constructorimpl(nextUInt.nextInt(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final int nextUInt(@NotNull Random nextUInt, @NotNull UIntRange range) {
        Intrinsics.checkParameterIsNotNull(nextUInt, "$this$nextUInt");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (!range.isEmpty()) {
            return UnsignedUtils.uintCompare(range.getLast(), -1) < 0 ? m25393nextUInta8DCA5k(nextUInt, range.getFirst(), UInt.m24574constructorimpl(range.getLast() + 1)) : UnsignedUtils.uintCompare(range.getFirst(), 0) > 0 ? UInt.m24574constructorimpl(m25393nextUInta8DCA5k(nextUInt, UInt.m24574constructorimpl(range.getFirst() - 1), range.getLast()) + 1) : nextUInt(nextUInt);
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + range);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final long nextULong(@NotNull Random nextULong) {
        Intrinsics.checkParameterIsNotNull(nextULong, "$this$nextULong");
        return ULong.m24643constructorimpl(nextULong.nextLong());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: nextULong-V1Xi4fY  reason: not valid java name */
    public static final long m25395nextULongV1Xi4fY(@NotNull Random nextULong, long j) {
        Intrinsics.checkParameterIsNotNull(nextULong, "$this$nextULong");
        return m25396nextULongjmpaWc(nextULong, 0L, j);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: nextULong-jmpaW-c  reason: not valid java name */
    public static final long m25396nextULongjmpaWc(@NotNull Random nextULong, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(nextULong, "$this$nextULong");
        m25389checkULongRangeBoundseb3DHEI(j, j2);
        return ULong.m24643constructorimpl(nextULong.nextLong(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final long nextULong(@NotNull Random nextULong, @NotNull ULongRange range) {
        Intrinsics.checkParameterIsNotNull(nextULong, "$this$nextULong");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        } else if (UnsignedUtils.ulongCompare(range.getLast(), -1L) < 0) {
            return m25396nextULongjmpaWc(nextULong, range.getFirst(), ULong.m24643constructorimpl(range.getLast() + ULong.m24643constructorimpl(1 & 4294967295L)));
        } else {
            if (UnsignedUtils.ulongCompare(range.getFirst(), 0L) > 0) {
                long j = 1 & 4294967295L;
                return ULong.m24643constructorimpl(m25396nextULongjmpaWc(nextULong, ULong.m24643constructorimpl(range.getFirst() - ULong.m24643constructorimpl(j)), range.getLast()) + ULong.m24643constructorimpl(j));
            }
            return nextULong(nextULong);
        }
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: nextUBytes-EVgfTAA  reason: not valid java name */
    public static final byte[] m25390nextUBytesEVgfTAA(@NotNull Random nextUBytes, @NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkParameterIsNotNull(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random nextUBytes, int i) {
        Intrinsics.checkParameterIsNotNull(nextUBytes, "$this$nextUBytes");
        return UByteArray.m24551constructorimpl(nextUBytes.nextBytes(i));
    }

    /* renamed from: nextUBytes-Wvrt4B4$default  reason: not valid java name */
    public static /* synthetic */ byte[] m25392nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m24557getSizeimpl(bArr);
        }
        return m25391nextUBytesWvrt4B4(random, bArr, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: nextUBytes-Wvrt4B4  reason: not valid java name */
    public static final byte[] m25391nextUBytesWvrt4B4(@NotNull Random nextUBytes, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkParameterIsNotNull(array, "array");
        nextUBytes.nextBytes(array, i, i2);
        return array;
    }

    @Unsigned
    /* renamed from: checkUIntRangeBounds-J1ME1BU  reason: not valid java name */
    public static final void m25388checkUIntRangeBoundsJ1ME1BU(int i, int i2) {
        if (!(UnsignedUtils.uintCompare(i2, i) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m24568boximpl(i), UInt.m24568boximpl(i2)).toString());
        }
    }

    @Unsigned
    /* renamed from: checkULongRangeBounds-eb3DHEI  reason: not valid java name */
    public static final void m25389checkULongRangeBoundseb3DHEI(long j, long j2) {
        if (!(UnsignedUtils.ulongCompare(j2, j) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m24637boximpl(j), ULong.m24637boximpl(j2)).toString());
        }
    }
}
