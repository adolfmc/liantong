package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unsigned;
import kotlin.UnsignedUtils;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandom;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.ULongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _URanges.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0087\nø\u0001\u0000¢\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\u0087\nø\u0001\u0000¢\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%H\u0087\bø\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\b*\u00020/H\u0087\bø\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000\u001a\f\u0010J\u001a\u000208*\u000208H\u0007\u001a\f\u0010J\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010K\u001a\u000208*\u0002082\u0006\u0010K\u001a\u00020LH\u0087\u0004\u001a\u0015\u0010K\u001a\u00020>*\u00020>2\u0006\u0010K\u001a\u00020MH\u0087\u0004\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bO\u0010P\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bQ\u0010R\u001a\u001f\u0010N\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a\u001f\u0010N\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006W"}, m1890d2 = {"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/ranges/URangesKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
class URangesKt___URangesKt {
    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    private static final int random(@NotNull UIntRange uIntRange) {
        return _URanges.random(uIntRange, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    private static final long random(@NotNull ULongRange uLongRange) {
        return _URanges.random(uLongRange, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final int random(@NotNull UIntRange random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        try {
            return URandom.nextUInt(random2, random);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final long random(@NotNull ULongRange random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        try {
            return URandom.nextULong(random2, random);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    private static final UInt randomOrNull(@NotNull UIntRange uIntRange) {
        return _URanges.randomOrNull(uIntRange, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    private static final ULong randomOrNull(@NotNull ULongRange uLongRange) {
        return _URanges.randomOrNull(uLongRange, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @Nullable
    public static final UInt randomOrNull(@NotNull UIntRange randomOrNull, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (randomOrNull.isEmpty()) {
            return null;
        }
        return UInt.m24568boximpl(URandom.nextUInt(random, randomOrNull));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @Nullable
    public static final ULong randomOrNull(@NotNull ULongRange randomOrNull, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (randomOrNull.isEmpty()) {
            return null;
        }
        return ULong.m24637boximpl(URandom.nextULong(random, randomOrNull));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: contains-biwQdVI  reason: not valid java name */
    private static final boolean m25420containsbiwQdVI(@NotNull UIntRange contains, UInt uInt) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return uInt != null && contains.m25398containsWZ4Q5Ns(uInt.m24617unboximpl());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: contains-GYNo2lE  reason: not valid java name */
    private static final boolean m25416containsGYNo2lE(@NotNull ULongRange contains, ULong uLong) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return uLong != null && contains.m25400containsVKZWuLQ(uLong.m24686unboximpl());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-68kG9v0  reason: not valid java name */
    public static final boolean m25415contains68kG9v0(@NotNull UIntRange contains, byte b) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.m25398containsWZ4Q5Ns(UInt.m24574constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-ULb-yJY  reason: not valid java name */
    public static final boolean m25418containsULbyJY(@NotNull ULongRange contains, byte b) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.m25400containsVKZWuLQ(ULong.m24643constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-Gab390E  reason: not valid java name */
    public static final boolean m25417containsGab390E(@NotNull ULongRange contains, int i) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.m25400containsVKZWuLQ(ULong.m24643constructorimpl(i & 4294967295L));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-fz5IDCE  reason: not valid java name */
    public static final boolean m25421containsfz5IDCE(@NotNull UIntRange contains, long j) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return ULong.m24643constructorimpl(j >>> 32) == 0 && contains.m25398containsWZ4Q5Ns(UInt.m24574constructorimpl((int) j));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-ZsK3CEQ  reason: not valid java name */
    public static final boolean m25419containsZsK3CEQ(@NotNull UIntRange contains, short s) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.m25398containsWZ4Q5Ns(UInt.m24574constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: contains-uhHAxoY  reason: not valid java name */
    public static final boolean m25422containsuhHAxoY(@NotNull ULongRange contains, short s) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.m25400containsVKZWuLQ(ULong.m24643constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: downTo-Kr8caGY  reason: not valid java name */
    public static final UIntProgression m25425downToKr8caGY(byte b, byte b2) {
        return UIntProgression.Companion.m25397fromClosedRangeNkh28Cs(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(b2 & 255), -1);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: downTo-J1ME1BU  reason: not valid java name */
    public static final UIntProgression m25424downToJ1ME1BU(int i, int i2) {
        return UIntProgression.Companion.m25397fromClosedRangeNkh28Cs(i, i2, -1);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: downTo-eb3DHEI  reason: not valid java name */
    public static final ULongProgression m25426downToeb3DHEI(long j, long j2) {
        return ULongProgression.Companion.m25399fromClosedRange7ftBX0g(j, j2, -1L);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: downTo-5PvTz6A  reason: not valid java name */
    public static final UIntProgression m25423downTo5PvTz6A(short s, short s2) {
        return UIntProgression.Companion.m25397fromClosedRangeNkh28Cs(UInt.m24574constructorimpl(s & 65535), UInt.m24574constructorimpl(s2 & 65535), -1);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final UIntProgression reversed(@NotNull UIntProgression reversed) {
        Intrinsics.checkParameterIsNotNull(reversed, "$this$reversed");
        return UIntProgression.Companion.m25397fromClosedRangeNkh28Cs(reversed.getLast(), reversed.getFirst(), -reversed.getStep());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final ULongProgression reversed(@NotNull ULongProgression reversed) {
        Intrinsics.checkParameterIsNotNull(reversed, "$this$reversed");
        return ULongProgression.Companion.m25399fromClosedRange7ftBX0g(reversed.getLast(), reversed.getFirst(), -reversed.getStep());
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final UIntProgression step(@NotNull UIntProgression step, int i) {
        Intrinsics.checkParameterIsNotNull(step, "$this$step");
        RangesKt.checkStepIsPositive(i > 0, Integer.valueOf(i));
        UIntProgression.Companion companion = UIntProgression.Companion;
        int first = step.getFirst();
        int last = step.getLast();
        if (step.getStep() <= 0) {
            i = -i;
        }
        return companion.m25397fromClosedRangeNkh28Cs(first, last, i);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final ULongProgression step(@NotNull ULongProgression step, long j) {
        Intrinsics.checkParameterIsNotNull(step, "$this$step");
        RangesKt.checkStepIsPositive(j > 0, Long.valueOf(j));
        ULongProgression.Companion companion = ULongProgression.Companion;
        long first = step.getFirst();
        long last = step.getLast();
        if (step.getStep() <= 0) {
            j = -j;
        }
        return companion.m25399fromClosedRange7ftBX0g(first, last, j);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: until-Kr8caGY  reason: not valid java name */
    public static final UIntRange m25429untilKr8caGY(byte b, byte b2) {
        int i = b2 & 255;
        return Intrinsics.compare(i, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(UInt.m24574constructorimpl(i) - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: until-J1ME1BU  reason: not valid java name */
    public static final UIntRange m25428untilJ1ME1BU(int i, int i2) {
        return UnsignedUtils.uintCompare(i2, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(i, UInt.m24574constructorimpl(i2 - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: until-eb3DHEI  reason: not valid java name */
    public static final ULongRange m25430untileb3DHEI(long j, long j2) {
        return UnsignedUtils.ulongCompare(j2, 0L) <= 0 ? ULongRange.Companion.getEMPTY() : new ULongRange(j, ULong.m24643constructorimpl(j2 - ULong.m24643constructorimpl(1 & 4294967295L)), null);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: until-5PvTz6A  reason: not valid java name */
    public static final UIntRange m25427until5PvTz6A(short s, short s2) {
        int i = s2 & 65535;
        return Intrinsics.compare(i, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(UInt.m24574constructorimpl(s & 65535), UInt.m24574constructorimpl(UInt.m24574constructorimpl(i) - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtLeast-J1ME1BU  reason: not valid java name */
    public static final int m25402coerceAtLeastJ1ME1BU(int i, int i2) {
        return UnsignedUtils.uintCompare(i, i2) < 0 ? i2 : i;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtLeast-eb3DHEI  reason: not valid java name */
    public static final long m25404coerceAtLeasteb3DHEI(long j, long j2) {
        return UnsignedUtils.ulongCompare(j, j2) < 0 ? j2 : j;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtLeast-Kr8caGY  reason: not valid java name */
    public static final byte m25403coerceAtLeastKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) < 0 ? b2 : b;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtLeast-5PvTz6A  reason: not valid java name */
    public static final short m25401coerceAtLeast5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) < 0 ? s2 : s;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtMost-J1ME1BU  reason: not valid java name */
    public static final int m25406coerceAtMostJ1ME1BU(int i, int i2) {
        return UnsignedUtils.uintCompare(i, i2) > 0 ? i2 : i;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtMost-eb3DHEI  reason: not valid java name */
    public static final long m25408coerceAtMosteb3DHEI(long j, long j2) {
        return UnsignedUtils.ulongCompare(j, j2) > 0 ? j2 : j;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtMost-Kr8caGY  reason: not valid java name */
    public static final byte m25407coerceAtMostKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) > 0 ? b2 : b;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceAtMost-5PvTz6A  reason: not valid java name */
    public static final short m25405coerceAtMost5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) > 0 ? s2 : s;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-WZ9TVnA  reason: not valid java name */
    public static final int m25411coerceInWZ9TVnA(int i, int i2, int i3) {
        if (UnsignedUtils.uintCompare(i2, i3) <= 0) {
            return UnsignedUtils.uintCompare(i, i2) < 0 ? i2 : UnsignedUtils.uintCompare(i, i3) > 0 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.m24611toStringimpl(i3) + " is less than minimum " + UInt.m24611toStringimpl(i2) + '.');
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-sambcqE  reason: not valid java name */
    public static final long m25413coerceInsambcqE(long j, long j2, long j3) {
        if (UnsignedUtils.ulongCompare(j2, j3) <= 0) {
            return UnsignedUtils.ulongCompare(j, j2) < 0 ? j2 : UnsignedUtils.ulongCompare(j, j3) > 0 ? j3 : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.m24680toStringimpl(j3) + " is less than minimum " + ULong.m24680toStringimpl(j2) + '.');
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-b33U2AM  reason: not valid java name */
    public static final byte m25412coerceInb33U2AM(byte b, byte b2, byte b3) {
        int i = b2 & 255;
        int i2 = b3 & 255;
        if (Intrinsics.compare(i, i2) <= 0) {
            int i3 = b & 255;
            return Intrinsics.compare(i3, i) < 0 ? b2 : Intrinsics.compare(i3, i2) > 0 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.m24542toStringimpl(b3) + " is less than minimum " + UByte.m24542toStringimpl(b2) + '.');
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-VKSA0NQ  reason: not valid java name */
    public static final short m25410coerceInVKSA0NQ(short s, short s2, short s3) {
        int i = s2 & 65535;
        int i2 = s3 & 65535;
        if (Intrinsics.compare(i, i2) <= 0) {
            int i3 = 65535 & s;
            return Intrinsics.compare(i3, i) < 0 ? s2 : Intrinsics.compare(i3, i2) > 0 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.m24775toStringimpl(s3) + " is less than minimum " + UShort.m24775toStringimpl(s2) + '.');
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-wuiCnnA  reason: not valid java name */
    public static final int m25414coerceInwuiCnnA(int i, @NotNull Range<UInt> range) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return ((UInt) RangesKt.coerceIn(UInt.m24568boximpl(i), (ClosedFloatingPointRange<UInt>) range)).m24617unboximpl();
        }
        if (!range.isEmpty()) {
            return UnsignedUtils.uintCompare(i, range.getStart().m24617unboximpl()) < 0 ? range.getStart().m24617unboximpl() : UnsignedUtils.uintCompare(i, range.getEndInclusive().m24617unboximpl()) > 0 ? range.getEndInclusive().m24617unboximpl() : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: coerceIn-JPwROB0  reason: not valid java name */
    public static final long m25409coerceInJPwROB0(long j, @NotNull Range<ULong> range) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return ((ULong) RangesKt.coerceIn(ULong.m24637boximpl(j), (ClosedFloatingPointRange<ULong>) range)).m24686unboximpl();
        }
        if (!range.isEmpty()) {
            return UnsignedUtils.ulongCompare(j, range.getStart().m24686unboximpl()) < 0 ? range.getStart().m24686unboximpl() : UnsignedUtils.ulongCompare(j, range.getEndInclusive().m24686unboximpl()) > 0 ? range.getEndInclusive().m24686unboximpl() : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
    }
}
