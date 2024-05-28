package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: UByte.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020FH\u0087\b¢\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020JH\u0087\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\rH\u0087\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020YH\u0016¢\u0006\u0004\bZ\u0010[J\u0013\u0010\\\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b]\u0010\u0005J\u0013\u0010^\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010OJ\u0013\u0010`\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\ba\u0010SJ\u0013\u0010b\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\bc\u0010WJ\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, m1890d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@Unsigned
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m24501boximpl(byte b) {
        return new UByte(b);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private int m24502compareTo7apg3OU(byte b) {
        return m24503compareTo7apg3OU(this.data, b);
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m24507constructorimpl(byte b) {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m24513equalsimpl(byte b, @Nullable Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m24548unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m24514equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m24515hashCodeimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m24536toByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m24537toDoubleimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m24538toFloatimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m24539toIntimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m24540toLongimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m24541toShortimpl(byte b) {
        return (short) (b & 255);
    }

    @InlineOnly
    /* renamed from: toUByte-impl  reason: not valid java name */
    private static final byte m24543toUByteimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m24513equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m24515hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m24542toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m24548unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(UByte uByte) {
        return m24502compareTo7apg3OU(uByte.m24548unboximpl());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UByte.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m1890d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static int m24503compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m24506compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & 65535);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m24505compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedUtils.uintCompare(UInt.m24574constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m24504compareToVKZWuLQ(byte b, long j) {
        return UnsignedUtils.ulongCompare(ULong.m24643constructorimpl(b & 255), j);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m24523plus7apg3OU(byte b, byte b2) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) + UInt.m24574constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m24526plusxj2QHRw(byte b, short s) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) + UInt.m24574constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m24525plusWZ4Q5Ns(byte b, int i) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) + i);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m24524plusVKZWuLQ(byte b, long j) {
        return ULong.m24643constructorimpl(ULong.m24643constructorimpl(b & 255) + j);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m24518minus7apg3OU(byte b, byte b2) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) - UInt.m24574constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m24521minusxj2QHRw(byte b, short s) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) - UInt.m24574constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m24520minusWZ4Q5Ns(byte b, int i) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) - i);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m24519minusVKZWuLQ(byte b, long j) {
        return ULong.m24643constructorimpl(ULong.m24643constructorimpl(b & 255) - j);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m24532times7apg3OU(byte b, byte b2) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) * UInt.m24574constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m24535timesxj2QHRw(byte b, short s) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) * UInt.m24574constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m24534timesWZ4Q5Ns(byte b, int i) {
        return UInt.m24574constructorimpl(UInt.m24574constructorimpl(b & 255) * i);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m24533timesVKZWuLQ(byte b, long j) {
        return ULong.m24643constructorimpl(ULong.m24643constructorimpl(b & 255) * j);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m24509div7apg3OU(byte b, byte b2) {
        return UnsignedUtils.m24800uintDivideJ1ME1BU(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m24512divxj2QHRw(byte b, short s) {
        return UnsignedUtils.m24800uintDivideJ1ME1BU(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m24511divWZ4Q5Ns(byte b, int i) {
        return UnsignedUtils.m24800uintDivideJ1ME1BU(UInt.m24574constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m24510divVKZWuLQ(byte b, long j) {
        return UnsignedUtils.m24802ulongDivideeb3DHEI(ULong.m24643constructorimpl(b & 255), j);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m24528rem7apg3OU(byte b, byte b2) {
        return UnsignedUtils.m24801uintRemainderJ1ME1BU(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m24531remxj2QHRw(byte b, short s) {
        return UnsignedUtils.m24801uintRemainderJ1ME1BU(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m24530remWZ4Q5Ns(byte b, int i) {
        return UnsignedUtils.m24801uintRemainderJ1ME1BU(UInt.m24574constructorimpl(b & 255), i);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m24529remVKZWuLQ(byte b, long j) {
        return UnsignedUtils.m24803ulongRemaindereb3DHEI(ULong.m24643constructorimpl(b & 255), j);
    }

    @InlineOnly
    /* renamed from: inc-impl  reason: not valid java name */
    private static final byte m24516incimpl(byte b) {
        return m24507constructorimpl((byte) (b + 1));
    }

    @InlineOnly
    /* renamed from: dec-impl  reason: not valid java name */
    private static final byte m24508decimpl(byte b) {
        return m24507constructorimpl((byte) (b - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU  reason: not valid java name */
    private static final UIntRange m24527rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m24574constructorimpl(b & 255), UInt.m24574constructorimpl(b2 & 255), null);
    }

    @InlineOnly
    /* renamed from: and-7apg3OU  reason: not valid java name */
    private static final byte m24500and7apg3OU(byte b, byte b2) {
        return m24507constructorimpl((byte) (b & b2));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU  reason: not valid java name */
    private static final byte m24522or7apg3OU(byte b, byte b2) {
        return m24507constructorimpl((byte) (b | b2));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU  reason: not valid java name */
    private static final byte m24547xor7apg3OU(byte b, byte b2) {
        return m24507constructorimpl((byte) (b ^ b2));
    }

    @InlineOnly
    /* renamed from: inv-impl  reason: not valid java name */
    private static final byte m24517invimpl(byte b) {
        return m24507constructorimpl((byte) (~b));
    }

    @InlineOnly
    /* renamed from: toUShort-impl  reason: not valid java name */
    private static final short m24546toUShortimpl(byte b) {
        return UShort.m24740constructorimpl((short) (b & 255));
    }

    @InlineOnly
    /* renamed from: toUInt-impl  reason: not valid java name */
    private static final int m24544toUIntimpl(byte b) {
        return UInt.m24574constructorimpl(b & 255);
    }

    @InlineOnly
    /* renamed from: toULong-impl  reason: not valid java name */
    private static final long m24545toULongimpl(byte b) {
        return ULong.m24643constructorimpl(b & 255);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m24542toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }
}
