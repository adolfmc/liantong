package szcom.googlecode.mp4parser.authoring.tracks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
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
public class ChangeTimeScaleTrack implements Track {
    private static final Logger LOG = Logger.getLogger(ChangeTimeScaleTrack.class.getName());
    List<CompositionTimeToSample.Entry> ctts;
    long[] decodingTimes;
    Track source;
    long timeScale;

    public ChangeTimeScaleTrack(Track track, long j, long[] jArr) {
        this.source = track;
        this.timeScale = j;
        double timescale = j / track.getTrackMetaData().getTimescale();
        this.ctts = adjustCtts(track.getCompositionTimeEntries(), timescale);
        this.decodingTimes = adjustTts(track.getSampleDurations(), timescale, jArr, getTimes(track, jArr, j));
    }

    static List<CompositionTimeToSample.Entry> adjustCtts(List<CompositionTimeToSample.Entry> list, double d) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            for (CompositionTimeToSample.Entry entry : list) {
                arrayList.add(new CompositionTimeToSample.Entry(entry.getCount(), (int) Math.round(entry.getOffset() * d)));
            }
            return arrayList;
        }
        return null;
    }

    static long[] adjustTts(long[] jArr, double d, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[jArr.length];
        long j = 0;
        int i = 1;
        while (i <= jArr.length) {
            int i2 = i - 1;
            long round = Math.round(jArr[i2] * d);
            int i3 = i + 1;
            int binarySearch = Arrays.binarySearch(jArr2, i3);
            if (binarySearch >= 0 && jArr3[binarySearch] != j) {
                long j2 = jArr3[binarySearch] - (j + round);
                LOG.finest(String.format("Sample %d %d / %d - correct by %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(jArr3[binarySearch]), Long.valueOf(j2)));
                round += j2;
            }
            j += round;
            jArr4[i2] = round;
            i = i3;
        }
        return jArr4;
    }

    private static long[] getTimes(Track track, long[] jArr, long j) {
        long[] jArr2 = new long[jArr.length];
        int i = 0;
        long j2 = 0;
        int i2 = 1;
        while (true) {
            long j3 = i2;
            if (j3 > jArr[jArr.length - 1]) {
                return jArr2;
            }
            if (j3 == jArr[i]) {
                jArr2[i] = (j2 * j) / track.getTrackMetaData().getTimescale();
                i++;
            }
            j2 += track.getSampleDurations()[i2 - 1];
            i2++;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.ctts;
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
        return this.source.getEdits();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "timeScale(" + this.source.getName() + ")";
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
        return this.decodingTimes;
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
        trackMetaData.setTimescale(this.timeScale);
        return trackMetaData;
    }

    public String toString() {
        return "ChangeTimeScaleTrack{source=" + this.source + '}';
    }
}
