package szcom.googlecode.mp4parser.authoring.tracks;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.googlecode.mp4parser.authoring.Edit;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SilenceTrackImpl implements Track {
    long[] decodingTimes;
    String name;
    List<Sample> samples = new LinkedList();
    Track source;

    public SilenceTrackImpl(Track track, long j) {
        this.source = track;
        this.name = j + "ms silence";
        if (!AudioSampleEntry.TYPE3.equals(track.getSampleDescriptionBox().getSampleEntry().getType())) {
            throw new RuntimeException("Tracks of type " + track.getClass().getSimpleName() + " are not supported");
        }
        int l2i = CastUtils.l2i(((getTrackMetaData().getTimescale() * j) / 1000) / 1024);
        this.decodingTimes = new long[l2i];
        Arrays.fill(this.decodingTimes, ((getTrackMetaData().getTimescale() * j) / l2i) / 1000);
        while (true) {
            int i = l2i - 1;
            if (l2i <= 0) {
                return;
            }
            this.samples.add(new SampleImpl((ByteBuffer) ByteBuffer.wrap(new byte[]{33, 16, 4, 96, -116, 28}).rewind()));
            l2i = i;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        long j = 0;
        for (long j2 : this.decodingTimes) {
            j += j2;
        }
        return j;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return this.name;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.source.getSampleDescriptionBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.decodingTimes;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.source.getSampleGroups();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.source.getTrackMetaData();
    }
}
