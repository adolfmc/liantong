package kotlin.time;

import com.sdk.p285a.C6960d;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnitKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0001¨\u0006\u0004"}, m1890d2 = {"shortName", "", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/time/DurationUnitKt")
/* renamed from: kotlin.time.DurationUnitKt__DurationUnitKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
class DurationUnit extends DurationUnitJvm {
    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final String shortName(@NotNull TimeUnit shortName) {
        Intrinsics.checkParameterIsNotNull(shortName, "$this$shortName");
        switch (DurationUnitKt.WhenMappings.$EnumSwitchMapping$0[shortName.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "m";
            case 6:
                return "h";
            case 7:
                return C6960d.f18019d;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
