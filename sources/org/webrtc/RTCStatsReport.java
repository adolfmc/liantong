package org.webrtc;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RTCStatsReport {
    private final Map<String, RTCStats> stats;
    private final long timestampUs;

    public RTCStatsReport(long j, Map<String, RTCStats> map) {
        this.timestampUs = j;
        this.stats = map;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public Map<String, RTCStats> getStatsMap() {
        return this.stats;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ timestampUs: ");
        sb.append(this.timestampUs);
        sb.append(", stats: [\n");
        boolean z = true;
        for (RTCStats rTCStats : this.stats.values()) {
            if (!z) {
                sb.append(",\n");
            }
            sb.append(rTCStats);
            z = false;
        }
        sb.append(" ] }");
        return sb.toString();
    }

    @CalledByNative
    private static RTCStatsReport create(long j, Map map) {
        return new RTCStatsReport(j, map);
    }
}
