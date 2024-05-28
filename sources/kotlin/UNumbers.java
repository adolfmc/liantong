package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0004\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\r\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0004\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\r\u001a\u001f\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0018\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010\u001b\u001a\u001f\u0010\"\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010\u001d\u001a\u001f\u0010\"\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u001f\u001a\u001f\u0010\"\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010!\u001a\u0017\u0010'\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u0017\u0010'\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0007\u001a\u0017\u0010'\u001a\u00020\b*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u0017\u0010'\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b0\u0010)\u001a\u0017\u0010/\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b1\u0010\u0007\u001a\u0017\u0010/\u001a\u00020\b*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b2\u0010,\u001a\u0017\u0010/\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b3\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m1890d2 = {"countLeadingZeroBits", "", "Lkotlin/UByte;", "countLeadingZeroBits-7apg3OU", "(B)I", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits", "countOneBits-7apg3OU", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "rotateLeft", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight", "rotateRight-LxnNnR4", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit", "takeLowestOneBit-7apg3OU", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "UNumbersKt")
/* renamed from: kotlin.UNumbersKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UNumbers {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countOneBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m24711countOneBitsWZ4Q5Ns(int i) {
        return Integer.bitCount(i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countLeadingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m24707countLeadingZeroBitsWZ4Q5Ns(int i) {
        return Integer.numberOfLeadingZeros(i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countTrailingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m24715countTrailingZeroBitsWZ4Q5Ns(int i) {
        return Integer.numberOfTrailingZeros(i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeHighestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m24727takeHighestOneBitWZ4Q5Ns(int i) {
        return UInt.m24574constructorimpl(Integer.highestOneBit(i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeLowestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m24731takeLowestOneBitWZ4Q5Ns(int i) {
        return UInt.m24574constructorimpl(Integer.lowestOneBit(i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateLeft-V7xB4Y4  reason: not valid java name */
    private static final int m24719rotateLeftV7xB4Y4(int i, int i2) {
        return UInt.m24574constructorimpl(Integer.rotateLeft(i, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateRight-V7xB4Y4  reason: not valid java name */
    private static final int m24723rotateRightV7xB4Y4(int i, int i2) {
        return UInt.m24574constructorimpl(Integer.rotateRight(i, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countOneBits-VKZWuLQ  reason: not valid java name */
    private static final int m24710countOneBitsVKZWuLQ(long j) {
        return Long.bitCount(j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countLeadingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m24706countLeadingZeroBitsVKZWuLQ(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countTrailingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m24714countTrailingZeroBitsVKZWuLQ(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeHighestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m24726takeHighestOneBitVKZWuLQ(long j) {
        return ULong.m24643constructorimpl(Long.highestOneBit(j));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeLowestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m24730takeLowestOneBitVKZWuLQ(long j) {
        return ULong.m24643constructorimpl(Long.lowestOneBit(j));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateLeft-JSWoG40  reason: not valid java name */
    private static final long m24717rotateLeftJSWoG40(long j, int i) {
        return ULong.m24643constructorimpl(Long.rotateLeft(j, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateRight-JSWoG40  reason: not valid java name */
    private static final long m24721rotateRightJSWoG40(long j, int i) {
        return ULong.m24643constructorimpl(Long.rotateRight(j, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countOneBits-7apg3OU  reason: not valid java name */
    private static final int m24709countOneBits7apg3OU(byte b) {
        return Integer.bitCount(UInt.m24574constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countLeadingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m24705countLeadingZeroBits7apg3OU(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countTrailingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m24713countTrailingZeroBits7apg3OU(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeHighestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m24725takeHighestOneBit7apg3OU(byte b) {
        return UByte.m24507constructorimpl((byte) Integer.highestOneBit(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeLowestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m24729takeLowestOneBit7apg3OU(byte b) {
        return UByte.m24507constructorimpl((byte) Integer.lowestOneBit(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateLeft-LxnNnR4  reason: not valid java name */
    private static final byte m24718rotateLeftLxnNnR4(byte b, int i) {
        return UByte.m24507constructorimpl(NumbersKt.rotateLeft(b, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateRight-LxnNnR4  reason: not valid java name */
    private static final byte m24722rotateRightLxnNnR4(byte b, int i) {
        return UByte.m24507constructorimpl(NumbersKt.rotateRight(b, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countOneBits-xj2QHRw  reason: not valid java name */
    private static final int m24712countOneBitsxj2QHRw(short s) {
        return Integer.bitCount(UInt.m24574constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countLeadingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m24708countLeadingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfLeadingZeros(s & 65535) - 16;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: countTrailingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m24716countTrailingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeHighestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m24728takeHighestOneBitxj2QHRw(short s) {
        return UShort.m24740constructorimpl((short) Integer.highestOneBit(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: takeLowestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m24732takeLowestOneBitxj2QHRw(short s) {
        return UShort.m24740constructorimpl((short) Integer.lowestOneBit(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateLeft-olVBNx4  reason: not valid java name */
    private static final short m24720rotateLeftolVBNx4(short s, int i) {
        return UShort.m24740constructorimpl(NumbersKt.rotateLeft(s, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Unsigned
    @InlineOnly
    /* renamed from: rotateRight-olVBNx4  reason: not valid java name */
    private static final short m24724rotateRightolVBNx4(short s, int i) {
        return UShort.m24740constructorimpl(NumbersKt.rotateRight(s, i));
    }
}
