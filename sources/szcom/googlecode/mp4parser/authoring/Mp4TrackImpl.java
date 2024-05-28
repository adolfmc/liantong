package szcom.googlecode.mp4parser.authoring;

import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.googlecode.mp4parser.BasicContainer;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class Mp4TrackImpl extends AbstractTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private List<CompositionTimeToSample.Entry> compositionTimeEntries;
    private long[] decodingTimes;
    IsoFile[] fragments;
    private String handler;
    private List<SampleDependencyTypeBox.Entry> sampleDependencies;
    private SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    private SubSampleInformationBox subSampleInformationBox;
    private long[] syncSamples;
    TrackBox trackBox;
    private TrackMetaData trackMetaData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02d9  */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v3, types: [void] */
    /* JADX WARN: Type inference failed for: r0v54 */
    /* JADX WARN: Type inference failed for: r0v55 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r19v5, types: [java.util.Iterator] */
    /* JADX WARN: Type inference failed for: r20v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Mp4TrackImpl(java.lang.String r29, szcom.coremedia.iso.boxes.TrackBox r30, szcom.coremedia.iso.IsoFile... r31) {
        /*
            Method dump skipped, instructions count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.Mp4TrackImpl.<init>(java.lang.String, szcom.coremedia.iso.boxes.TrackBox, szcom.coremedia.iso.IsoFile[]):void");
    }

    private Map<GroupEntry, long[]> getSampleGroups(List<SampleGroupDescriptionBox> list, List<SampleGroupDescriptionBox> list2, List<SampleToGroupBox> list3, Map<GroupEntry, long[]> map, long j) {
        for (SampleToGroupBox sampleToGroupBox : list3) {
            int i = 0;
            for (SampleToGroupBox.Entry entry : sampleToGroupBox.getEntries()) {
                if (entry.getGroupDescriptionIndex() > 0) {
                    GroupEntry groupEntry = null;
                    if (entry.getGroupDescriptionIndex() > 65535) {
                        for (SampleGroupDescriptionBox sampleGroupDescriptionBox : list2) {
                            if (sampleGroupDescriptionBox.getGroupingType().equals(sampleToGroupBox.getGroupingType())) {
                                groupEntry = sampleGroupDescriptionBox.getGroupEntries().get((entry.getGroupDescriptionIndex() - 1) & 65535);
                            }
                        }
                    } else {
                        for (SampleGroupDescriptionBox sampleGroupDescriptionBox2 : list) {
                            if (sampleGroupDescriptionBox2.getGroupingType().equals(sampleToGroupBox.getGroupingType())) {
                                groupEntry = sampleGroupDescriptionBox2.getGroupEntries().get(entry.getGroupDescriptionIndex() - 1);
                            }
                        }
                    }
                    GroupEntry groupEntry2 = groupEntry;
                    long[] jArr = map.get(groupEntry2);
                    if (jArr == null) {
                        jArr = new long[0];
                    }
                    long[] jArr2 = jArr;
                    long[] jArr3 = new long[CastUtils.l2i(entry.getSampleCount()) + jArr2.length];
                    System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
                    int i2 = 0;
                    while (true) {
                        long j2 = i2;
                        if (j2 >= entry.getSampleCount()) {
                            break;
                        }
                        jArr3[jArr2.length + i2] = j + i + j2;
                        i2++;
                    }
                    map.put(groupEntry2, jArr3);
                }
                i = (int) (i + entry.getSampleCount());
            }
        }
        return map;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Container parent = this.trackBox.getParent();
        if (parent instanceof BasicContainer) {
            ((BasicContainer) parent).close();
        }
        IsoFile[] isoFileArr = this.fragments;
        if (isoFileArr != null) {
            for (IsoFile isoFile : isoFileArr) {
                isoFile.close();
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.compositionTimeEntries;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.handler;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.sampleDependencies;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSampleDurations() {
        return this.decodingTimes;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.subSampleInformationBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        long[] jArr = this.syncSamples;
        if (jArr == null || jArr.length == this.samples.size()) {
            return null;
        }
        return this.syncSamples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
