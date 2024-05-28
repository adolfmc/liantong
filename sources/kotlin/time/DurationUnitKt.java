package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"kotlin/time/DurationUnitKt__DurationUnitJvmKt", "kotlin/time/DurationUnitKt__DurationUnitKt"}, m1889k = 4, m1888mv = {1, 1, 16}, m1886xi = 1)
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class DurationUnitKt extends DurationUnit {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @Metadata(m1892bv = {1, 0, 3}, m1889k = 3, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TimeUnit.values().length];

        static {
            $EnumSwitchMapping$0[TimeUnit.NANOSECONDS.ordinal()] = 1;
            $EnumSwitchMapping$0[TimeUnit.MICROSECONDS.ordinal()] = 2;
            $EnumSwitchMapping$0[TimeUnit.MILLISECONDS.ordinal()] = 3;
            $EnumSwitchMapping$0[TimeUnit.SECONDS.ordinal()] = 4;
            $EnumSwitchMapping$0[TimeUnit.MINUTES.ordinal()] = 5;
            $EnumSwitchMapping$0[TimeUnit.HOURS.ordinal()] = 6;
            $EnumSwitchMapping$0[TimeUnit.DAYS.ordinal()] = 7;
        }
    }

    private DurationUnitKt() {
    }
}
