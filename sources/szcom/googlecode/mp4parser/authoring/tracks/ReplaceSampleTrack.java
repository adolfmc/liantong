package szcom.googlecode.mp4parser.authoring.tracks;

import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.List;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ReplaceSampleTrack extends AbstractTrack {
    Track origTrack;
    private Sample sampleContent;
    private long sampleNumber;
    private List<Sample> samples;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class ReplaceASingleEntryList extends AbstractList<Sample> {
        private ReplaceASingleEntryList() {
        }

        /* synthetic */ ReplaceASingleEntryList(ReplaceSampleTrack replaceSampleTrack, ReplaceASingleEntryList replaceASingleEntryList) {
            this();
        }

        @Override // java.util.AbstractList, java.util.List
        public Sample get(int i) {
            return ReplaceSampleTrack.this.sampleNumber == ((long) i) ? ReplaceSampleTrack.this.sampleContent : ReplaceSampleTrack.this.origTrack.getSamples().get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return ReplaceSampleTrack.this.origTrack.getSamples().size();
        }
    }

    public ReplaceSampleTrack(Track track, long j, ByteBuffer byteBuffer) {
        super("replace(" + track.getName() + ")");
        this.origTrack = track;
        this.sampleNumber = j;
        this.sampleContent = new SampleImpl(byteBuffer);
        this.samples = new ReplaceASingleEntryList(this, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.origTrack.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.origTrack.getCompositionTimeEntries();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.origTrack.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.origTrack.getSampleDependencies();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.origTrack.getSampleDescriptionBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSampleDurations() {
        return this.origTrack.getSampleDurations();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.origTrack.getSubsampleInformationBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSyncSamples() {
        return this.origTrack.getSyncSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.origTrack.getTrackMetaData();
    }
}
