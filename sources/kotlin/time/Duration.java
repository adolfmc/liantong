package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Duration.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u008d\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\u0086\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\u0086\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\u0086\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\u0086\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, m1890d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@ExperimentalTime
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Duration implements Comparable<Duration> {
    private final double value;
    public static final Companion Companion = new Companion(null);
    private static final double ZERO = m25442constructorimpl(0.0d);
    private static final double INFINITE = m25442constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m25440boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static double m25442constructorimpl(double d) {
        return d;
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m25443divLRDsOJo(double d, double d2) {
        return d / d2;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m25446equalsimpl(double d, @Nullable Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).m25485unboximpl()) == 0;
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m25447equalsimpl0(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m25460hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    @PublishedApi
    public static /* synthetic */ void hoursComponent$annotations() {
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m25463isNegativeimpl(double d) {
        return d < ((double) 0);
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m25464isPositiveimpl(double d) {
        return d > ((double) 0);
    }

    @PublishedApi
    public static /* synthetic */ void minutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    /* renamed from: precision-impl  reason: not valid java name */
    private static final int m25467precisionimpl(double d, double d2) {
        if (d2 < 1) {
            return 3;
        }
        if (d2 < 10) {
            return 2;
        }
        return d2 < ((double) 100) ? 1 : 0;
    }

    @PublishedApi
    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m25484compareToLRDsOJo(double d) {
        return m25441compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object obj) {
        return m25446equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m25460hashCodeimpl(this.value);
    }

    @NotNull
    public String toString() {
        return m25480toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ double m25485unboximpl() {
        return this.value;
    }

    private /* synthetic */ Duration(double d) {
        this.value = d;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Duration duration) {
        return m25484compareToLRDsOJo(duration.m25485unboximpl());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Duration.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m1890d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double convert(double d, @NotNull TimeUnit sourceUnit, @NotNull TimeUnit targetUnit) {
            Intrinsics.checkParameterIsNotNull(sourceUnit, "sourceUnit");
            Intrinsics.checkParameterIsNotNull(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(d, sourceUnit, targetUnit);
        }
    }

    /* renamed from: unaryMinus-impl  reason: not valid java name */
    public static final double m25483unaryMinusimpl(double d) {
        return m25442constructorimpl(-d);
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final double m25466plusLRDsOJo(double d, double d2) {
        return m25442constructorimpl(d + d2);
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final double m25465minusLRDsOJo(double d, double d2) {
        return m25442constructorimpl(d - d2);
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m25469timesimpl(double d, int i) {
        return m25442constructorimpl(d * i);
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m25468timesimpl(double d, double d2) {
        return m25442constructorimpl(d * d2);
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m25445divimpl(double d, int i) {
        return m25442constructorimpl(d / i);
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m25444divimpl(double d, double d2) {
        return m25442constructorimpl(d / d2);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m25462isInfiniteimpl(double d) {
        return Double.isInfinite(d);
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m25461isFiniteimpl(double d) {
        return (Double.isInfinite(d) || Double.isNaN(d)) ? false : true;
    }

    /* renamed from: getAbsoluteValue-impl  reason: not valid java name */
    public static final double m25448getAbsoluteValueimpl(double d) {
        return m25463isNegativeimpl(d) ? m25483unaryMinusimpl(d) : d;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m25441compareToLRDsOJo(double d, double d2) {
        return Double.compare(d, d2);
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m25473toComponentsimpl(double d, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m25450getInDaysimpl(d)), Integer.valueOf(m25449getHoursComponentimpl(d)), Integer.valueOf(m25457getMinutesComponentimpl(d)), Integer.valueOf(m25459getSecondsComponentimpl(d)), Integer.valueOf(m25458getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m25472toComponentsimpl(double d, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m25451getInHoursimpl(d)), Integer.valueOf(m25457getMinutesComponentimpl(d)), Integer.valueOf(m25459getSecondsComponentimpl(d)), Integer.valueOf(m25458getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m25471toComponentsimpl(double d, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m25454getInMinutesimpl(d)), Integer.valueOf(m25459getSecondsComponentimpl(d)), Integer.valueOf(m25458getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m25470toComponentsimpl(double d, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Long.valueOf((long) m25456getInSecondsimpl(d)), Integer.valueOf(m25458getNanosecondsComponentimpl(d)));
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m25449getHoursComponentimpl(double d) {
        return (int) (m25451getInHoursimpl(d) % 24);
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m25457getMinutesComponentimpl(double d) {
        return (int) (m25454getInMinutesimpl(d) % 60);
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m25459getSecondsComponentimpl(double d) {
        return (int) (m25456getInSecondsimpl(d) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m25458getNanosecondsComponentimpl(double d) {
        return (int) (m25455getInNanosecondsimpl(d) % 1.0E9d);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m25474toDoubleimpl(double d, @NotNull TimeUnit unit) {
        TimeUnit storageUnit;
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        storageUnit = DurationKt.getStorageUnit();
        return DurationUnitKt.convertDurationUnit(d, storageUnit, unit);
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m25477toLongimpl(double d, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (long) m25474toDoubleimpl(d, unit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m25475toIntimpl(double d, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (int) m25474toDoubleimpl(d, unit);
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m25450getInDaysimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m25451getInHoursimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m25454getInMinutesimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m25456getInSecondsimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m25453getInMillisecondsimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m25452getInMicrosecondsimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m25455getInNanosecondsimpl(double d) {
        return m25474toDoubleimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m25479toLongNanosecondsimpl(double d) {
        return m25477toLongimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m25478toLongMillisecondsimpl(double d) {
        return m25477toLongimpl(d, TimeUnit.MILLISECONDS);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m25480toStringimpl(double d) {
        TimeUnit timeUnit;
        int i;
        String formatToExactDecimals;
        if (m25462isInfiniteimpl(d)) {
            return String.valueOf(d);
        }
        if (d == 0.0d) {
            return "0s";
        }
        double m25455getInNanosecondsimpl = m25455getInNanosecondsimpl(m25448getAbsoluteValueimpl(d));
        boolean z = false;
        if (m25455getInNanosecondsimpl < 1.0E-6d) {
            timeUnit = TimeUnit.SECONDS;
            i = 0;
            z = true;
        } else if (m25455getInNanosecondsimpl < 1) {
            timeUnit = TimeUnit.NANOSECONDS;
            i = 7;
        } else if (m25455getInNanosecondsimpl < 1000.0d) {
            timeUnit = TimeUnit.NANOSECONDS;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 1000000.0d) {
            timeUnit = TimeUnit.MICROSECONDS;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 1.0E9d) {
            timeUnit = TimeUnit.MILLISECONDS;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 1.0E12d) {
            timeUnit = TimeUnit.SECONDS;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 6.0E13d) {
            timeUnit = TimeUnit.MINUTES;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 3.6E15d) {
            timeUnit = TimeUnit.HOURS;
            i = 0;
        } else if (m25455getInNanosecondsimpl < 8.64E20d) {
            timeUnit = TimeUnit.DAYS;
            i = 0;
        } else {
            timeUnit = TimeUnit.DAYS;
            i = 0;
            z = true;
        }
        double m25474toDoubleimpl = m25474toDoubleimpl(d, timeUnit);
        StringBuilder sb = new StringBuilder();
        if (z) {
            formatToExactDecimals = formatToDecimals.formatScientific(m25474toDoubleimpl);
        } else if (i > 0) {
            formatToExactDecimals = formatToDecimals.formatUpToDecimals(m25474toDoubleimpl, i);
        } else {
            formatToExactDecimals = formatToDecimals.formatToExactDecimals(m25474toDoubleimpl, m25467precisionimpl(d, Math.abs(m25474toDoubleimpl)));
        }
        sb.append(formatToExactDecimals);
        sb.append(DurationUnitKt.shortName(timeUnit));
        return sb.toString();
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m25482toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m25481toStringimpl(d, timeUnit, i);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m25481toStringimpl(double d, @NotNull TimeUnit unit, int i) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + i).toString());
        } else if (m25462isInfiniteimpl(d)) {
            return String.valueOf(d);
        } else {
            double m25474toDoubleimpl = m25474toDoubleimpl(d, unit);
            StringBuilder sb = new StringBuilder();
            sb.append(Math.abs(m25474toDoubleimpl) < 1.0E14d ? formatToDecimals.formatToExactDecimals(m25474toDoubleimpl, RangesKt.coerceAtMost(i, 12)) : formatToDecimals.formatScientific(m25474toDoubleimpl));
            sb.append(DurationUnitKt.shortName(unit));
            return sb.toString();
        }
    }

    @NotNull
    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m25476toIsoStringimpl(double d) {
        StringBuilder sb = new StringBuilder();
        if (m25463isNegativeimpl(d)) {
            sb.append('-');
        }
        sb.append("PT");
        double m25448getAbsoluteValueimpl = m25448getAbsoluteValueimpl(d);
        int m25451getInHoursimpl = (int) m25451getInHoursimpl(m25448getAbsoluteValueimpl);
        int m25457getMinutesComponentimpl = m25457getMinutesComponentimpl(m25448getAbsoluteValueimpl);
        int m25459getSecondsComponentimpl = m25459getSecondsComponentimpl(m25448getAbsoluteValueimpl);
        int m25458getNanosecondsComponentimpl = m25458getNanosecondsComponentimpl(m25448getAbsoluteValueimpl);
        boolean z = true;
        boolean z2 = m25451getInHoursimpl != 0;
        boolean z3 = (m25459getSecondsComponentimpl == 0 && m25458getNanosecondsComponentimpl == 0) ? false : true;
        if (m25457getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m25451getInHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(m25457getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(m25459getSecondsComponentimpl);
            if (m25458getNanosecondsComponentimpl != 0) {
                sb.append('.');
                String padStart = StringsKt.padStart(String.valueOf(m25458getNanosecondsComponentimpl), 9, '0');
                if (m25458getNanosecondsComponentimpl % 1000000 == 0) {
                    sb.append((CharSequence) padStart, 0, 3);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else if (m25458getNanosecondsComponentimpl % 1000 == 0) {
                    sb.append((CharSequence) padStart, 0, 6);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else {
                    sb.append(padStart);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
