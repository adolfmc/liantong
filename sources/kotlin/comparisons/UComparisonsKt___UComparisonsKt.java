package kotlin.comparisons;

import com.example.asus.detectionandalign.animation.C4280b;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unsigned;
import kotlin.UnsignedUtils;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _UComparisons.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\"\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a+\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\"\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\"\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a+\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\"\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0005\u001a+\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\b\u001a\"\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000b\u001a+\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\r\u001a\"\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0010\u001a+\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0012\u001a\"\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0015\u001a+\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, m1890d2 = {"maxOf", "Lkotlin/UByte;", "a", C4280b.f10047a, "maxOf-Kr8caGY", "(BB)B", "c", "maxOf-b33U2AM", "(BBB)B", "Lkotlin/UInt;", "maxOf-J1ME1BU", "(II)I", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "maxOf-sambcqE", "(JJJ)J", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "maxOf-VKSA0NQ", "(SSS)S", "minOf", "minOf-Kr8caGY", "minOf-b33U2AM", "minOf-J1ME1BU", "minOf-WZ9TVnA", "minOf-eb3DHEI", "minOf-sambcqE", "minOf-5PvTz6A", "minOf-VKSA0NQ", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/comparisons/UComparisonsKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UComparisonsKt___UComparisonsKt {
    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: maxOf-J1ME1BU  reason: not valid java name */
    public static final int m25365maxOfJ1ME1BU(int i, int i2) {
        return UnsignedUtils.uintCompare(i, i2) >= 0 ? i : i2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: maxOf-eb3DHEI  reason: not valid java name */
    public static final long m25370maxOfeb3DHEI(long j, long j2) {
        return UnsignedUtils.ulongCompare(j, j2) >= 0 ? j : j2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: maxOf-Kr8caGY  reason: not valid java name */
    public static final byte m25366maxOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) >= 0 ? b : b2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: maxOf-5PvTz6A  reason: not valid java name */
    public static final short m25364maxOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) >= 0 ? s : s2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: maxOf-WZ9TVnA  reason: not valid java name */
    private static final int m25368maxOfWZ9TVnA(int i, int i2, int i3) {
        return _UComparisons.m25365maxOfJ1ME1BU(i, _UComparisons.m25365maxOfJ1ME1BU(i2, i3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: maxOf-sambcqE  reason: not valid java name */
    private static final long m25371maxOfsambcqE(long j, long j2, long j3) {
        return _UComparisons.m25370maxOfeb3DHEI(j, _UComparisons.m25370maxOfeb3DHEI(j2, j3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: maxOf-b33U2AM  reason: not valid java name */
    private static final byte m25369maxOfb33U2AM(byte b, byte b2, byte b3) {
        return _UComparisons.m25366maxOfKr8caGY(b, _UComparisons.m25366maxOfKr8caGY(b2, b3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: maxOf-VKSA0NQ  reason: not valid java name */
    private static final short m25367maxOfVKSA0NQ(short s, short s2, short s3) {
        return _UComparisons.m25364maxOf5PvTz6A(s, _UComparisons.m25364maxOf5PvTz6A(s2, s3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: minOf-J1ME1BU  reason: not valid java name */
    public static final int m25373minOfJ1ME1BU(int i, int i2) {
        return UnsignedUtils.uintCompare(i, i2) <= 0 ? i : i2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: minOf-eb3DHEI  reason: not valid java name */
    public static final long m25378minOfeb3DHEI(long j, long j2) {
        return UnsignedUtils.ulongCompare(j, j2) <= 0 ? j : j2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: minOf-Kr8caGY  reason: not valid java name */
    public static final byte m25374minOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) <= 0 ? b : b2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    /* renamed from: minOf-5PvTz6A  reason: not valid java name */
    public static final short m25372minOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) <= 0 ? s : s2;
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: minOf-WZ9TVnA  reason: not valid java name */
    private static final int m25376minOfWZ9TVnA(int i, int i2, int i3) {
        return _UComparisons.m25373minOfJ1ME1BU(i, _UComparisons.m25373minOfJ1ME1BU(i2, i3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: minOf-sambcqE  reason: not valid java name */
    private static final long m25379minOfsambcqE(long j, long j2, long j3) {
        return _UComparisons.m25378minOfeb3DHEI(j, _UComparisons.m25378minOfeb3DHEI(j2, j3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: minOf-b33U2AM  reason: not valid java name */
    private static final byte m25377minOfb33U2AM(byte b, byte b2, byte b3) {
        return _UComparisons.m25374minOfKr8caGY(b, _UComparisons.m25374minOfKr8caGY(b2, b3));
    }

    @SinceKotlin(version = "1.3")
    @Unsigned
    @InlineOnly
    /* renamed from: minOf-VKSA0NQ  reason: not valid java name */
    private static final short m25375minOfVKSA0NQ(short s, short s2, short s3) {
        return _UComparisons.m25372minOf5PvTz6A(s, _UComparisons.m25372minOf5PvTz6A(s2, s3));
    }
}
