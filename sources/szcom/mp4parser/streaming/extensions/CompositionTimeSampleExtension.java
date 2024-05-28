package szcom.mp4parser.streaming.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import szcom.mp4parser.streaming.SampleExtension;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CompositionTimeSampleExtension implements SampleExtension {
    public static Map<Integer, CompositionTimeSampleExtension> pool = Collections.synchronizedMap(new HashMap());
    private int ctts;

    public static CompositionTimeSampleExtension create(int i) {
        CompositionTimeSampleExtension compositionTimeSampleExtension = pool.get(Integer.valueOf(i));
        if (compositionTimeSampleExtension == null) {
            CompositionTimeSampleExtension compositionTimeSampleExtension2 = new CompositionTimeSampleExtension();
            compositionTimeSampleExtension2.ctts = i;
            pool.put(Integer.valueOf(i), compositionTimeSampleExtension2);
            return compositionTimeSampleExtension2;
        }
        return compositionTimeSampleExtension;
    }

    public int getCompositionTimeOffset() {
        return this.ctts;
    }
}
