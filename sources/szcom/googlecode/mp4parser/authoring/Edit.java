package szcom.googlecode.mp4parser.authoring;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Edit {
    private double mediaRate;
    private long mediaTime;
    private double segmentDuration;
    private long timeScale;

    public Edit(long j, long j2, double d, double d2) {
        this.timeScale = j2;
        this.segmentDuration = d2;
        this.mediaTime = j;
        this.mediaRate = d;
    }

    public double getMediaRate() {
        return this.mediaRate;
    }

    public long getMediaTime() {
        return this.mediaTime;
    }

    public double getSegmentDuration() {
        return this.segmentDuration;
    }

    public long getTimeScale() {
        return this.timeScale;
    }
}
