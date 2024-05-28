package kotlin.collections;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unsigned;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _UCollections.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m1890d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/collections/UCollectionsKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> toUByteArray) {
        Intrinsics.checkParameterIsNotNull(toUByteArray, "$this$toUByteArray");
        byte[] m24550constructorimpl = UByteArray.m24550constructorimpl(toUByteArray.size());
        int i = 0;
        for (UByte uByte : toUByteArray) {
            UByteArray.m24561setVurrAj0(m24550constructorimpl, i, uByte.m24548unboximpl());
            i++;
        }
        return m24550constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> toUIntArray) {
        Intrinsics.checkParameterIsNotNull(toUIntArray, "$this$toUIntArray");
        int[] m24619constructorimpl = UIntArray.m24619constructorimpl(toUIntArray.size());
        int i = 0;
        for (UInt uInt : toUIntArray) {
            UIntArray.m24630setVXSXFK8(m24619constructorimpl, i, uInt.m24617unboximpl());
            i++;
        }
        return m24619constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> toULongArray) {
        Intrinsics.checkParameterIsNotNull(toULongArray, "$this$toULongArray");
        long[] m24688constructorimpl = ULongArray.m24688constructorimpl(toULongArray.size());
        int i = 0;
        for (ULong uLong : toULongArray) {
            ULongArray.m24699setk8EXiF4(m24688constructorimpl, i, uLong.m24686unboximpl());
            i++;
        }
        return m24688constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> toUShortArray) {
        Intrinsics.checkParameterIsNotNull(toUShortArray, "$this$toUShortArray");
        short[] m24783constructorimpl = UShortArray.m24783constructorimpl(toUShortArray.size());
        int i = 0;
        for (UShort uShort : toUShortArray) {
            UShortArray.m24794set01HTLdE(m24783constructorimpl, i, uShort.m24781unboximpl());
            i++;
        }
        return m24783constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Iterable<UInt> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UInt uInt : sum) {
            i = UInt.m24574constructorimpl(i + uInt.m24617unboximpl());
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Iterable<ULong> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        long j = 0;
        for (ULong uLong : sum) {
            j = ULong.m24643constructorimpl(j + uLong.m24686unboximpl());
        }
        return j;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Iterable<UByte> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UByte uByte : sum) {
            i = UInt.m24574constructorimpl(i + UInt.m24574constructorimpl(uByte.m24548unboximpl() & 255));
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Iterable<UShort> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UShort uShort : sum) {
            i = UInt.m24574constructorimpl(i + UInt.m24574constructorimpl(uShort.m24781unboximpl() & 65535));
        }
        return i;
    }
}
