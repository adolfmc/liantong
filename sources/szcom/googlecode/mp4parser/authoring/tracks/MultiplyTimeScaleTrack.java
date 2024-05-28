package szcom.googlecode.mp4parser.authoring.tracks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.authoring.Edit;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MultiplyTimeScaleTrack implements Track {
    Track source;
    private int timeScaleFactor;

    public MultiplyTimeScaleTrack(Track track, int i) {
        this.source = track;
        this.timeScaleFactor = i;
    }

    static List<CompositionTimeToSample.Entry> adjustCtts(List<CompositionTimeToSample.Entry> list, int i) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            for (CompositionTimeToSample.Entry entry : list) {
                arrayList.add(new CompositionTimeToSample.Entry(entry.getCount(), entry.getOffset() * i));
            }
            return arrayList;
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return adjustCtts(this.source.getCompositionTimeEntries(), this.timeScaleFactor);
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        return this.source.getDuration() * this.timeScaleFactor;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.source.getEdits();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "timscale(" + this.source.getName() + ")";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.source.getSampleDependencies();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.source.getSampleDescriptionBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        long[] jArr = new long[this.source.getSampleDurations().length];
        for (int i = 0; i < this.source.getSampleDurations().length; i++) {
            jArr[i] = this.source.getSampleDurations()[i] * this.timeScaleFactor;
        }
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.source.getSampleGroups();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.source.getSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.source.getSubsampleInformationBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.source.getSyncSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        TrackMetaData trackMetaData = (TrackMetaData) this.source.getTrackMetaData().clone();
        trackMetaData.setTimescale(this.source.getTrackMetaData().getTimescale() * this.timeScaleFactor);
        return trackMetaData;
    }

    public String toString() {
        return "MultiplyTimeScaleTrack{source=" + this.source + '}';
    }
}
