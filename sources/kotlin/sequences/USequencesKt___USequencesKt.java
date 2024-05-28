package kotlin.sequences;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unsigned;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _USequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m1890d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/sequences/USequencesKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
class USequencesKt___USequencesKt {
    @SinceKotlin(version = "1.3")
    @Unsigned
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Sequence<UInt> sum) {
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
    public static final long sumOfULong(@NotNull Sequence<ULong> sum) {
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
    public static final int sumOfUByte(@NotNull Sequence<UByte> sum) {
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
    public static final int sumOfUShort(@NotNull Sequence<UShort> sum) {
        Intrinsics.checkParameterIsNotNull(sum, "$this$sum");
        int i = 0;
        for (UShort uShort : sum) {
            i = UInt.m24574constructorimpl(i + UInt.m24574constructorimpl(uShort.m24781unboximpl() & 65535));
        }
        return i;
    }
}
