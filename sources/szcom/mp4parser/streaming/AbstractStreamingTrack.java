package szcom.mp4parser.streaming;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.TrackHeaderBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractStreamingTrack implements StreamingTrack {
    protected SampleDescriptionBox stsd;
    protected BlockingQueue<StreamingSample> samples = new ArrayBlockingQueue(1000);
    protected HashMap<Class<? extends TrackExtension>, TrackExtension> trackExtensions = new HashMap<>();
    protected TrackHeaderBox tkhd = new TrackHeaderBox();

    public AbstractStreamingTrack() {
        this.tkhd.setTrackId(1L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // szcom.mp4parser.streaming.StreamingTrack
    public void addTrackExtension(TrackExtension trackExtension) {
        this.trackExtensions.put(trackExtension.getClass(), trackExtension);
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public BlockingQueue<StreamingSample> getSamples() {
        return this.samples;
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public <T extends TrackExtension> T getTrackExtension(Class<T> cls) {
        return (T) this.trackExtensions.get(cls);
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public TrackHeaderBox getTrackHeaderBox() {
        return this.tkhd;
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public boolean hasMoreSamples() {
        return false;
    }

    @Override // szcom.mp4parser.streaming.StreamingTrack
    public void removeTrackExtension(Class<? extends TrackExtension> cls) {
        this.trackExtensions.remove(cls);
    }
}
