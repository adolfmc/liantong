package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.Unsigned;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000bH\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m1890d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "sortArray-GBYM_sE", "([B)V", "sortArray--ajY-9A", "([I)V", "sortArray-QwZRm1k", "([J)V", "sortArray-rL5Bavg", "([S)V", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.collections.UArraySortingKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UArraySorting {
    @Unsigned
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m24809partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m24556getimpl = UByteArray.m24556getimpl(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = m24556getimpl & 255;
                if (Intrinsics.compare(UByteArray.m24556getimpl(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m24556getimpl(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m24556getimpl2 = UByteArray.m24556getimpl(bArr, i);
                UByteArray.m24561setVurrAj0(bArr, i, UByteArray.m24556getimpl(bArr, i2));
                UByteArray.m24561setVurrAj0(bArr, i2, m24556getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @Unsigned
    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m24813quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m24809partition4UcCI2c = m24809partition4UcCI2c(bArr, i, i2);
        int i3 = m24809partition4UcCI2c - 1;
        if (i < i3) {
            m24813quickSort4UcCI2c(bArr, i, i3);
        }
        if (m24809partition4UcCI2c < i2) {
            m24813quickSort4UcCI2c(bArr, m24809partition4UcCI2c, i2);
        }
    }

    @Unsigned
    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m24810partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m24789getimpl = UShortArray.m24789getimpl(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = m24789getimpl & 65535;
                if (Intrinsics.compare(UShortArray.m24789getimpl(sArr, i) & 65535, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m24789getimpl(sArr, i2) & 65535, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m24789getimpl2 = UShortArray.m24789getimpl(sArr, i);
                UShortArray.m24794set01HTLdE(sArr, i, UShortArray.m24789getimpl(sArr, i2));
                UShortArray.m24794set01HTLdE(sArr, i2, m24789getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @Unsigned
    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m24814quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m24810partitionAa5vz7o = m24810partitionAa5vz7o(sArr, i, i2);
        int i3 = m24810partitionAa5vz7o - 1;
        if (i < i3) {
            m24814quickSortAa5vz7o(sArr, i, i3);
        }
        if (m24810partitionAa5vz7o < i2) {
            m24814quickSortAa5vz7o(sArr, m24810partitionAa5vz7o, i2);
        }
    }

    @Unsigned
    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m24811partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m24625getimpl = UIntArray.m24625getimpl(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedUtils.uintCompare(UIntArray.m24625getimpl(iArr, i), m24625getimpl) < 0) {
                i++;
            }
            while (UnsignedUtils.uintCompare(UIntArray.m24625getimpl(iArr, i2), m24625getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m24625getimpl2 = UIntArray.m24625getimpl(iArr, i);
                UIntArray.m24630setVXSXFK8(iArr, i, UIntArray.m24625getimpl(iArr, i2));
                UIntArray.m24630setVXSXFK8(iArr, i2, m24625getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @Unsigned
    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m24815quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m24811partitionoBK06Vg = m24811partitionoBK06Vg(iArr, i, i2);
        int i3 = m24811partitionoBK06Vg - 1;
        if (i < i3) {
            m24815quickSortoBK06Vg(iArr, i, i3);
        }
        if (m24811partitionoBK06Vg < i2) {
            m24815quickSortoBK06Vg(iArr, m24811partitionoBK06Vg, i2);
        }
    }

    @Unsigned
    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m24808partitionnroSd4(long[] jArr, int i, int i2) {
        long m24694getimpl = ULongArray.m24694getimpl(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedUtils.ulongCompare(ULongArray.m24694getimpl(jArr, i), m24694getimpl) < 0) {
                i++;
            }
            while (UnsignedUtils.ulongCompare(ULongArray.m24694getimpl(jArr, i2), m24694getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m24694getimpl2 = ULongArray.m24694getimpl(jArr, i);
                ULongArray.m24699setk8EXiF4(jArr, i, ULongArray.m24694getimpl(jArr, i2));
                ULongArray.m24699setk8EXiF4(jArr, i2, m24694getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @Unsigned
    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m24812quickSortnroSd4(long[] jArr, int i, int i2) {
        int m24808partitionnroSd4 = m24808partitionnroSd4(jArr, i, i2);
        int i3 = m24808partitionnroSd4 - 1;
        if (i < i3) {
            m24812quickSortnroSd4(jArr, i, i3);
        }
        if (m24808partitionnroSd4 < i2) {
            m24812quickSortnroSd4(jArr, m24808partitionnroSd4, i2);
        }
    }

    @Unsigned
    /* renamed from: sortArray-GBYM_sE  reason: not valid java name */
    public static final void m24817sortArrayGBYM_sE(@NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m24813quickSort4UcCI2c(array, 0, UByteArray.m24557getSizeimpl(array) - 1);
    }

    @Unsigned
    /* renamed from: sortArray-rL5Bavg  reason: not valid java name */
    public static final void m24819sortArrayrL5Bavg(@NotNull short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m24814quickSortAa5vz7o(array, 0, UShortArray.m24790getSizeimpl(array) - 1);
    }

    @Unsigned
    /* renamed from: sortArray--ajY-9A  reason: not valid java name */
    public static final void m24816sortArrayajY9A(@NotNull int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m24815quickSortoBK06Vg(array, 0, UIntArray.m24626getSizeimpl(array) - 1);
    }

    @Unsigned
    /* renamed from: sortArray-QwZRm1k  reason: not valid java name */
    public static final void m24818sortArrayQwZRm1k(@NotNull long[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m24812quickSortnroSd4(array, 0, ULongArray.m24695getSizeimpl(array) - 1);
    }
}
