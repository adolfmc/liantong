package kotlin.internal;

import com.example.asus.detectionandalign.animation.C4280b;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m1890d2 = {"differenceModulo", "Lkotlin/UInt;", "a", C4280b.f10047a, "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.internal.UProgressionUtilKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UProgressionUtil {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m25380differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int m24801uintRemainderJ1ME1BU = UnsignedUtils.m24801uintRemainderJ1ME1BU(i, i3);
        int m24801uintRemainderJ1ME1BU2 = UnsignedUtils.m24801uintRemainderJ1ME1BU(i2, i3);
        return UInt.m24574constructorimpl(UnsignedUtils.uintCompare(m24801uintRemainderJ1ME1BU, m24801uintRemainderJ1ME1BU2) >= 0 ? m24801uintRemainderJ1ME1BU - m24801uintRemainderJ1ME1BU2 : UInt.m24574constructorimpl(m24801uintRemainderJ1ME1BU - m24801uintRemainderJ1ME1BU2) + i3);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m25381differenceModulosambcqE(long j, long j2, long j3) {
        long m24803ulongRemaindereb3DHEI = UnsignedUtils.m24803ulongRemaindereb3DHEI(j, j3);
        long m24803ulongRemaindereb3DHEI2 = UnsignedUtils.m24803ulongRemaindereb3DHEI(j2, j3);
        return ULong.m24643constructorimpl(UnsignedUtils.ulongCompare(m24803ulongRemaindereb3DHEI, m24803ulongRemaindereb3DHEI2) >= 0 ? m24803ulongRemaindereb3DHEI - m24803ulongRemaindereb3DHEI2 : ULong.m24643constructorimpl(m24803ulongRemaindereb3DHEI - m24803ulongRemaindereb3DHEI2) + j3);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m25383getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return UnsignedUtils.uintCompare(i, i2) >= 0 ? i2 : UInt.m24574constructorimpl(i2 - m25380differenceModuloWZ9TVnA(i2, i, UInt.m24574constructorimpl(i3)));
        } else if (i3 < 0) {
            return UnsignedUtils.uintCompare(i, i2) <= 0 ? i2 : UInt.m24574constructorimpl(i2 + m25380differenceModuloWZ9TVnA(i, i2, UInt.m24574constructorimpl(-i3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m25382getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (i > 0) {
            return UnsignedUtils.ulongCompare(j, j2) >= 0 ? j2 : ULong.m24643constructorimpl(j2 - m25381differenceModulosambcqE(j2, j, ULong.m24643constructorimpl(j3)));
        } else if (i < 0) {
            return UnsignedUtils.ulongCompare(j, j2) <= 0 ? j2 : ULong.m24643constructorimpl(j2 + m25381differenceModulosambcqE(j, j2, ULong.m24643constructorimpl(-j3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
