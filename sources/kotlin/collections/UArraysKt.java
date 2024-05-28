package kotlin.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Annotations;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unsigned;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: UArraysKt.kt */
@Annotations(level = DeprecationLevel.HIDDEN, message = "Provided for binary compatibility")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u001e\u0010&\u001a\u00020'*\u00020\u00052\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u001e\u0010&\u001a\u00020+*\u00020\t2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u001e\u0010&\u001a\u00020.*\u00020\f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u001e\u0010&\u001a\u000201*\u00020\u000f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020'05*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020+05*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020.05*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020105*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, m1890d2 = {"Lkotlin/collections/UArraysKt;", "", "()V", "contentEquals", "", "Lkotlin/UByteArray;", "other", "contentEquals-kdPth3s", "([B[B)Z", "Lkotlin/UIntArray;", "contentEquals-ctEhBpI", "([I[I)Z", "Lkotlin/ULongArray;", "contentEquals-us8wMrg", "([J[J)Z", "Lkotlin/UShortArray;", "contentEquals-mazbYpA", "([S[S)Z", "contentHashCode", "", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode--ajY-9A", "([I)I", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-rL5Bavg", "([S)I", "contentToString", "", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "random", "Lkotlin/UByte;", "Lkotlin/random/Random;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UInt;", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULong;", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UShort;", "random-s5X_as8", "([SLkotlin/random/Random;)S", "toTypedArray", "", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UArraysKt {
    public static final UArraysKt INSTANCE = new UArraysKt();

    private UArraysKt() {
    }

    @JvmStatic
    @Unsigned
    /* renamed from: random-2D5oskM  reason: not valid java name */
    public static final int m24832random2D5oskM(@NotNull int[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (UIntArray.m24628isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UIntArray.m24625getimpl(random, random2.nextInt(UIntArray.m24626getSizeimpl(random)));
    }

    @JvmStatic
    @Unsigned
    /* renamed from: random-JzugnMA  reason: not valid java name */
    public static final long m24833randomJzugnMA(@NotNull long[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (ULongArray.m24697isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return ULongArray.m24694getimpl(random, random2.nextInt(ULongArray.m24695getSizeimpl(random)));
    }

    @JvmStatic
    @Unsigned
    /* renamed from: random-oSF2wD8  reason: not valid java name */
    public static final byte m24834randomoSF2wD8(@NotNull byte[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (UByteArray.m24559isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UByteArray.m24556getimpl(random, random2.nextInt(UByteArray.m24557getSizeimpl(random)));
    }

    @JvmStatic
    @Unsigned
    /* renamed from: random-s5X_as8  reason: not valid java name */
    public static final short m24835randoms5X_as8(@NotNull short[] random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (UShortArray.m24792isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UShortArray.m24789getimpl(random, random2.nextInt(UShortArray.m24790getSizeimpl(random)));
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentEquals-ctEhBpI  reason: not valid java name */
    public static final boolean m24820contentEqualsctEhBpI(@NotNull int[] contentEquals, @NotNull int[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentEquals-us8wMrg  reason: not valid java name */
    public static final boolean m24823contentEqualsus8wMrg(@NotNull long[] contentEquals, @NotNull long[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentEquals-kdPth3s  reason: not valid java name */
    public static final boolean m24821contentEqualskdPth3s(@NotNull byte[] contentEquals, @NotNull byte[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentEquals-mazbYpA  reason: not valid java name */
    public static final boolean m24822contentEqualsmazbYpA(@NotNull short[] contentEquals, @NotNull short[] other) {
        Intrinsics.checkParameterIsNotNull(contentEquals, "$this$contentEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Arrays.equals(contentEquals, other);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentHashCode--ajY-9A  reason: not valid java name */
    public static final int m24824contentHashCodeajY9A(@NotNull int[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentHashCode-QwZRm1k  reason: not valid java name */
    public static final int m24826contentHashCodeQwZRm1k(@NotNull long[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentHashCode-GBYM_sE  reason: not valid java name */
    public static final int m24825contentHashCodeGBYM_sE(@NotNull byte[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @Unsigned
    /* renamed from: contentHashCode-rL5Bavg  reason: not valid java name */
    public static final int m24827contentHashCoderL5Bavg(@NotNull short[] contentHashCode) {
        Intrinsics.checkParameterIsNotNull(contentHashCode, "$this$contentHashCode");
        return Arrays.hashCode(contentHashCode);
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: contentToString--ajY-9A  reason: not valid java name */
    public static final String m24828contentToStringajY9A(@NotNull int[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt.joinToString$default(UIntArray.m24618boximpl(contentToString), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: contentToString-QwZRm1k  reason: not valid java name */
    public static final String m24830contentToStringQwZRm1k(@NotNull long[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt.joinToString$default(ULongArray.m24687boximpl(contentToString), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: contentToString-GBYM_sE  reason: not valid java name */
    public static final String m24829contentToStringGBYM_sE(@NotNull byte[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt.joinToString$default(UByteArray.m24549boximpl(contentToString), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: contentToString-rL5Bavg  reason: not valid java name */
    public static final String m24831contentToStringrL5Bavg(@NotNull short[] contentToString) {
        Intrinsics.checkParameterIsNotNull(contentToString, "$this$contentToString");
        return CollectionsKt.joinToString$default(UShortArray.m24782boximpl(contentToString), ", ", "[", "]", 0, null, null, 56, null);
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: toTypedArray--ajY-9A  reason: not valid java name */
    public static final UInt[] m24836toTypedArrayajY9A(@NotNull int[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m24626getSizeimpl = UIntArray.m24626getSizeimpl(toTypedArray);
        UInt[] uIntArr = new UInt[m24626getSizeimpl];
        for (int i = 0; i < m24626getSizeimpl; i++) {
            uIntArr[i] = UInt.m24568boximpl(UIntArray.m24625getimpl(toTypedArray, i));
        }
        return uIntArr;
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: toTypedArray-QwZRm1k  reason: not valid java name */
    public static final ULong[] m24838toTypedArrayQwZRm1k(@NotNull long[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m24695getSizeimpl = ULongArray.m24695getSizeimpl(toTypedArray);
        ULong[] uLongArr = new ULong[m24695getSizeimpl];
        for (int i = 0; i < m24695getSizeimpl; i++) {
            uLongArr[i] = ULong.m24637boximpl(ULongArray.m24694getimpl(toTypedArray, i));
        }
        return uLongArr;
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: toTypedArray-GBYM_sE  reason: not valid java name */
    public static final UByte[] m24837toTypedArrayGBYM_sE(@NotNull byte[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m24557getSizeimpl = UByteArray.m24557getSizeimpl(toTypedArray);
        UByte[] uByteArr = new UByte[m24557getSizeimpl];
        for (int i = 0; i < m24557getSizeimpl; i++) {
            uByteArr[i] = UByte.m24501boximpl(UByteArray.m24556getimpl(toTypedArray, i));
        }
        return uByteArr;
    }

    @JvmStatic
    @Unsigned
    @NotNull
    /* renamed from: toTypedArray-rL5Bavg  reason: not valid java name */
    public static final UShort[] m24839toTypedArrayrL5Bavg(@NotNull short[] toTypedArray) {
        Intrinsics.checkParameterIsNotNull(toTypedArray, "$this$toTypedArray");
        int m24790getSizeimpl = UShortArray.m24790getSizeimpl(toTypedArray);
        UShort[] uShortArr = new UShort[m24790getSizeimpl];
        for (int i = 0; i < m24790getSizeimpl; i++) {
            uShortArr[i] = UShort.m24734boximpl(UShortArray.m24789getimpl(toTypedArray, i));
        }
        return uShortArr;
    }
}
