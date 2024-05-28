package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unsigned;
import kotlin.UnsignedUtils;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m1890d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "UStringsKt")
/* renamed from: kotlin.text.UStringsKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UStrings {
    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: toString-LxnNnR4  reason: not valid java name */
    public static final String m25436toStringLxnNnR4(byte b, int i) {
        String num = Integer.toString(b & 255, CharsKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: toString-olVBNx4  reason: not valid java name */
    public static final String m25438toStringolVBNx4(short s, int i) {
        String num = Integer.toString(s & 65535, CharsKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: toString-V7xB4Y4  reason: not valid java name */
    public static final String m25437toStringV7xB4Y4(int i, int i2) {
        String l = Long.toString(i & 4294967295L, CharsKt.checkRadix(i2));
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: toString-JSWoG40  reason: not valid java name */
    public static final String m25435toStringJSWoG40(long j, int i) {
        return UnsignedUtils.ulongToString(j, CharsKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final byte toUByte(@NotNull String toUByte) {
        Intrinsics.checkParameterIsNotNull(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte);
        if (uByteOrNull != null) {
            return uByteOrNull.m24548unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final byte toUByte(@NotNull String toUByte, int i) {
        Intrinsics.checkParameterIsNotNull(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte, i);
        if (uByteOrNull != null) {
            return uByteOrNull.m24548unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final short toUShort(@NotNull String toUShort) {
        Intrinsics.checkParameterIsNotNull(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort);
        if (uShortOrNull != null) {
            return uShortOrNull.m24781unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final short toUShort(@NotNull String toUShort, int i) {
        Intrinsics.checkParameterIsNotNull(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort, i);
        if (uShortOrNull != null) {
            return uShortOrNull.m24781unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final int toUInt(@NotNull String toUInt) {
        Intrinsics.checkParameterIsNotNull(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt);
        if (uIntOrNull != null) {
            return uIntOrNull.m24617unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final int toUInt(@NotNull String toUInt, int i) {
        Intrinsics.checkParameterIsNotNull(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt, i);
        if (uIntOrNull != null) {
            return uIntOrNull.m24617unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final long toULong(@NotNull String toULong) {
        Intrinsics.checkParameterIsNotNull(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong);
        if (uLongOrNull != null) {
            return uLongOrNull.m24686unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    public static final long toULong(@NotNull String toULong, int i) {
        Intrinsics.checkParameterIsNotNull(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong, i);
        if (uLongOrNull != null) {
            return uLongOrNull.m24686unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull) {
        Intrinsics.checkParameterIsNotNull(toUByteOrNull, "$this$toUByteOrNull");
        return toUByteOrNull(toUByteOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUByteOrNull, "$this$toUByteOrNull");
        UInt uIntOrNull = toUIntOrNull(toUByteOrNull, i);
        if (uIntOrNull != null) {
            int m24617unboximpl = uIntOrNull.m24617unboximpl();
            if (UnsignedUtils.uintCompare(m24617unboximpl, UInt.m24574constructorimpl(255)) > 0) {
                return null;
            }
            return UByte.m24501boximpl(UByte.m24507constructorimpl((byte) m24617unboximpl));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull) {
        Intrinsics.checkParameterIsNotNull(toUShortOrNull, "$this$toUShortOrNull");
        return toUShortOrNull(toUShortOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUShortOrNull, "$this$toUShortOrNull");
        UInt uIntOrNull = toUIntOrNull(toUShortOrNull, i);
        if (uIntOrNull != null) {
            int m24617unboximpl = uIntOrNull.m24617unboximpl();
            if (UnsignedUtils.uintCompare(m24617unboximpl, UInt.m24574constructorimpl(65535)) > 0) {
                return null;
            }
            return UShort.m24734boximpl(UShort.m24740constructorimpl((short) m24617unboximpl));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull) {
        Intrinsics.checkParameterIsNotNull(toUIntOrNull, "$this$toUIntOrNull");
        return toUIntOrNull(toUIntOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(i);
        int length = toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char charAt = toUIntOrNull.charAt(0);
        int i3 = 1;
        if (charAt >= '0') {
            i3 = 0;
        } else if (length == 1 || charAt != '+') {
            return null;
        }
        int m24574constructorimpl = UInt.m24574constructorimpl(i);
        int i4 = 119304647;
        while (i3 < length) {
            int digitOf = CharsKt.digitOf(toUIntOrNull.charAt(i3), i);
            if (digitOf < 0) {
                return null;
            }
            if (UnsignedUtils.uintCompare(i2, i4) > 0) {
                if (i4 != 119304647) {
                    return null;
                }
                i4 = UnsignedUtils.m24800uintDivideJ1ME1BU(-1, m24574constructorimpl);
                if (UnsignedUtils.uintCompare(i2, i4) > 0) {
                    return null;
                }
            }
            int m24574constructorimpl2 = UInt.m24574constructorimpl(i2 * m24574constructorimpl);
            int m24574constructorimpl3 = UInt.m24574constructorimpl(UInt.m24574constructorimpl(digitOf) + m24574constructorimpl2);
            if (UnsignedUtils.uintCompare(m24574constructorimpl3, m24574constructorimpl2) < 0) {
                return null;
            }
            i3++;
            i2 = m24574constructorimpl3;
        }
        return UInt.m24568boximpl(i2);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull) {
        Intrinsics.checkParameterIsNotNull(toULongOrNull, "$this$toULongOrNull");
        return toULongOrNull(toULongOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull, int i) {
        int digitOf;
        Intrinsics.checkParameterIsNotNull(toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(i);
        int length = toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        long j = -1;
        int i2 = 0;
        char charAt = toULongOrNull.charAt(0);
        if (charAt < '0') {
            if (length == 1 || charAt != '+') {
                return null;
            }
            i2 = 1;
        }
        long m24643constructorimpl = ULong.m24643constructorimpl(i);
        long j2 = 0;
        long j3 = 512409557603043100L;
        while (i2 < length) {
            if (CharsKt.digitOf(toULongOrNull.charAt(i2), i) < 0) {
                return null;
            }
            if (UnsignedUtils.ulongCompare(j2, j3) > 0) {
                if (j3 != 512409557603043100L) {
                    return null;
                }
                j3 = UnsignedUtils.m24802ulongDivideeb3DHEI(j, m24643constructorimpl);
                if (UnsignedUtils.ulongCompare(j2, j3) > 0) {
                    return null;
                }
            }
            long m24643constructorimpl2 = ULong.m24643constructorimpl(j2 * m24643constructorimpl);
            long m24643constructorimpl3 = ULong.m24643constructorimpl(ULong.m24643constructorimpl(UInt.m24574constructorimpl(digitOf) & 4294967295L) + m24643constructorimpl2);
            if (UnsignedUtils.ulongCompare(m24643constructorimpl3, m24643constructorimpl2) < 0) {
                return null;
            }
            i2++;
            j2 = m24643constructorimpl3;
            j = -1;
        }
        return ULong.m24637boximpl(j2);
    }
}
