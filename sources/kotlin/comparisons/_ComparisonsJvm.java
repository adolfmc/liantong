package kotlin.comparisons;

import com.example.asus.detectionandalign.animation.C4280b;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000(\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\b\u001a!\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\b\u001a!\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\fH\u0087\b\u001a!\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a-\u0010\u000e\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u000e\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\b\u001a!\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\b\u001a!\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\b\u001a!\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\fH\u0087\b\u001a!\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b¨\u0006\u000f"}, m1890d2 = {"maxOf", "T", "", "a", C4280b.f10047a, "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "minOf", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/comparisons/ComparisonsKt")
/* renamed from: kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
class _ComparisonsJvm extends Comparisons {
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a, @NotNull T b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return a.compareTo(b) >= 0 ? a : b;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte b, byte b2) {
        return (byte) Math.max((int) b, (int) b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short s, short s2) {
        return (short) Math.max((int) s, (int) s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int i, int i2) {
        return Math.max(i, i2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long j, long j2) {
        return Math.max(j, j2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float f, float f2) {
        return Math.max(f, f2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double d, double d2) {
        return Math.max(d, d2);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a, @NotNull T b, @NotNull T c) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        Intrinsics.checkParameterIsNotNull(c, "c");
        return (T) ComparisonsKt.maxOf(a, ComparisonsKt.maxOf(b, c));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte b, byte b2, byte b3) {
        return (byte) Math.max((int) b, Math.max((int) b2, (int) b3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short s, short s2, short s3) {
        return (short) Math.max((int) s, Math.max((int) s2, (int) s3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int i, int i2, int i3) {
        return Math.max(i, Math.max(i2, i3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long j, long j2, long j3) {
        return Math.max(j, Math.max(j2, j3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float f, float f2, float f3) {
        return Math.max(f, Math.max(f2, f3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double d, double d2, double d3) {
        return Math.max(d, Math.max(d2, d3));
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a, @NotNull T b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return a.compareTo(b) <= 0 ? a : b;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte b, byte b2) {
        return (byte) Math.min((int) b, (int) b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short s, short s2) {
        return (short) Math.min((int) s, (int) s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int i, int i2) {
        return Math.min(i, i2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long j, long j2) {
        return Math.min(j, j2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float f, float f2) {
        return Math.min(f, f2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double d, double d2) {
        return Math.min(d, d2);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a, @NotNull T b, @NotNull T c) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        Intrinsics.checkParameterIsNotNull(c, "c");
        return (T) ComparisonsKt.minOf(a, ComparisonsKt.minOf(b, c));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte b, byte b2, byte b3) {
        return (byte) Math.min((int) b, Math.min((int) b2, (int) b3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short s, short s2, short s3) {
        return (short) Math.min((int) s, Math.min((int) s2, (int) s3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int i, int i2, int i3) {
        return Math.min(i, Math.min(i2, i3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long j, long j2, long j3) {
        return Math.min(j, Math.min(j2, j3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float f, float f2, float f3) {
        return Math.min(f, Math.min(f2, f3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double d, double d2, double d3) {
        return Math.min(d, Math.min(d2, d3));
    }
}
