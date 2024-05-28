package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ULong.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001mB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\u0087\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\u0087\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\u0087\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020OH\u0087\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020SH\u0087\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\rH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bZ\u0010\u0005J\u0010\u0010[\u001a\u00020\\H\u0087\b¢\u0006\u0004\b]\u0010^J\u000f\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\ba\u0010bJ\u0013\u0010c\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\bd\u0010MJ\u0013\u0010e\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\bf\u0010XJ\u0013\u0010g\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\bh\u0010\u0005J\u0013\u0010i\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\bj\u0010^J\u001b\u0010k\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bl\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006n"}, m1890d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@Unsigned
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ULong m24637boximpl(long j) {
        return new ULong(j);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private int m24639compareToVKZWuLQ(long j) {
        return m24640compareToVKZWuLQ(this.data, j);
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m24643constructorimpl(long j) {
        return j;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m24649equalsimpl(long j, @Nullable Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).m24686unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m24650equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m24651hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m24674toByteimpl(long j) {
        return (byte) j;
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m24677toIntimpl(long j) {
        return (int) j;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m24678toLongimpl(long j) {
        return j;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m24679toShortimpl(long j) {
        return (short) j;
    }

    @InlineOnly
    /* renamed from: toULong-impl  reason: not valid java name */
    private static final long m24683toULongimpl(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return m24649equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m24651hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m24680toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m24686unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(ULong uLong) {
        return m24639compareToVKZWuLQ(uLong.m24686unboximpl());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ULong.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m1890d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
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
    private static final int m24638compareTo7apg3OU(long j, byte b) {
        return UnsignedUtils.ulongCompare(j, m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m24642compareToxj2QHRw(long j, short s) {
        return UnsignedUtils.ulongCompare(j, m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m24641compareToWZ4Q5Ns(long j, int i) {
        return UnsignedUtils.ulongCompare(j, m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static int m24640compareToVKZWuLQ(long j, long j2) {
        return UnsignedUtils.ulongCompare(j, j2);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final long m24659plus7apg3OU(long j, byte b) {
        return m24643constructorimpl(j + m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final long m24662plusxj2QHRw(long j, short s) {
        return m24643constructorimpl(j + m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final long m24661plusWZ4Q5Ns(long j, int i) {
        return m24643constructorimpl(j + m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m24660plusVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j + j2);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final long m24654minus7apg3OU(long j, byte b) {
        return m24643constructorimpl(j - m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final long m24657minusxj2QHRw(long j, short s) {
        return m24643constructorimpl(j - m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final long m24656minusWZ4Q5Ns(long j, int i) {
        return m24643constructorimpl(j - m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m24655minusVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j - j2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final long m24670times7apg3OU(long j, byte b) {
        return m24643constructorimpl(j * m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final long m24673timesxj2QHRw(long j, short s) {
        return m24643constructorimpl(j * m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final long m24672timesWZ4Q5Ns(long j, int i) {
        return m24643constructorimpl(j * m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m24671timesVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j * j2);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final long m24645div7apg3OU(long j, byte b) {
        return UnsignedUtils.m24802ulongDivideeb3DHEI(j, m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final long m24648divxj2QHRw(long j, short s) {
        return UnsignedUtils.m24802ulongDivideeb3DHEI(j, m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final long m24647divWZ4Q5Ns(long j, int i) {
        return UnsignedUtils.m24802ulongDivideeb3DHEI(j, m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m24646divVKZWuLQ(long j, long j2) {
        return UnsignedUtils.m24802ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final long m24664rem7apg3OU(long j, byte b) {
        return UnsignedUtils.m24803ulongRemaindereb3DHEI(j, m24643constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final long m24667remxj2QHRw(long j, short s) {
        return UnsignedUtils.m24803ulongRemaindereb3DHEI(j, m24643constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final long m24666remWZ4Q5Ns(long j, int i) {
        return UnsignedUtils.m24803ulongRemaindereb3DHEI(j, m24643constructorimpl(i & 4294967295L));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m24665remVKZWuLQ(long j, long j2) {
        return UnsignedUtils.m24803ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: inc-impl  reason: not valid java name */
    private static final long m24652incimpl(long j) {
        return m24643constructorimpl(j + 1);
    }

    @InlineOnly
    /* renamed from: dec-impl  reason: not valid java name */
    private static final long m24644decimpl(long j) {
        return m24643constructorimpl(j - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ  reason: not valid java name */
    private static final ULongRange m24663rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    @InlineOnly
    /* renamed from: shl-impl  reason: not valid java name */
    private static final long m24668shlimpl(long j, int i) {
        return m24643constructorimpl(j << i);
    }

    @InlineOnly
    /* renamed from: shr-impl  reason: not valid java name */
    private static final long m24669shrimpl(long j, int i) {
        return m24643constructorimpl(j >>> i);
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ  reason: not valid java name */
    private static final long m24636andVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j & j2);
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ  reason: not valid java name */
    private static final long m24658orVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j | j2);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ  reason: not valid java name */
    private static final long m24685xorVKZWuLQ(long j, long j2) {
        return m24643constructorimpl(j ^ j2);
    }

    @InlineOnly
    /* renamed from: inv-impl  reason: not valid java name */
    private static final long m24653invimpl(long j) {
        return m24643constructorimpl(~j);
    }

    @InlineOnly
    /* renamed from: toUByte-impl  reason: not valid java name */
    private static final byte m24681toUByteimpl(long j) {
        return UByte.m24507constructorimpl((byte) j);
    }

    @InlineOnly
    /* renamed from: toUShort-impl  reason: not valid java name */
    private static final short m24684toUShortimpl(long j) {
        return UShort.m24740constructorimpl((short) j);
    }

    @InlineOnly
    /* renamed from: toUInt-impl  reason: not valid java name */
    private static final int m24682toUIntimpl(long j) {
        return UInt.m24574constructorimpl((int) j);
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m24676toFloatimpl(long j) {
        return (float) UnsignedUtils.ulongToDouble(j);
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m24675toDoubleimpl(long j) {
        return UnsignedUtils.ulongToDouble(j);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m24680toStringimpl(long j) {
        return UnsignedUtils.ulongToString(j);
    }
}
