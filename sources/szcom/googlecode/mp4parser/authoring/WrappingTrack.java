package szcom.googlecode.mp4parser.authoring;

import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class WrappingTrack implements Track {
    Track parent;

    public WrappingTrack(Track track) {
        this.parent = track;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.parent.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.parent.getCompositionTimeEntries();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        return this.parent.getDuration();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.parent.getEdits();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.parent.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return String.valueOf(this.parent.getName()) + "'";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.parent.getSampleDependencies();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.parent.getSampleDescriptionBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.parent.getSampleDurations();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.parent.getSampleGroups();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.parent.getSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.parent.getSubsampleInformationBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.parent.getSyncSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.parent.getTrackMetaData();
    }
}
