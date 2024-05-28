package szcom.mp4parser.streaming.extensions;

import szcom.mp4parser.streaming.TrackExtension;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TrackIdTrackExtension implements TrackExtension {
    private long trackId;

    public TrackIdTrackExtension(long j) {
        this.trackId = 1L;
        this.trackId = j;
    }

    public long getTrackId() {
        return this.trackId;
    }
}
