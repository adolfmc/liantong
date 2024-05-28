package szcom.googlecode.mp4parser.authoring.tracks;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.adobe.ActionMessageFormat0SampleEntryBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class Amf0Track extends AbstractTrack {
    SortedMap<Long, byte[]> rawSamples;
    private TrackMetaData trackMetaData;

    public Amf0Track(Map<Long, byte[]> map) {
        super(ActionMessageFormat0SampleEntryBox.TYPE);
        this.rawSamples = new TreeMap<Long, byte[]>() { // from class: szcom.googlecode.mp4parser.authoring.tracks.Amf0Track.1
        };
        this.trackMetaData = new TrackMetaData();
        this.rawSamples = new TreeMap(map);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setTimescale(1000L);
        this.trackMetaData.setLanguage("eng");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "data";
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        SampleDescriptionBox sampleDescriptionBox = new SampleDescriptionBox();
        ActionMessageFormat0SampleEntryBox actionMessageFormat0SampleEntryBox = new ActionMessageFormat0SampleEntryBox();
        actionMessageFormat0SampleEntryBox.setDataReferenceIndex(1);
        sampleDescriptionBox.addBox(actionMessageFormat0SampleEntryBox);
        return sampleDescriptionBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        LinkedList linkedList = new LinkedList(this.rawSamples.keySet());
        Collections.sort(linkedList);
        long[] jArr = new long[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++) {
            jArr[i] = ((Long) linkedList.get(i)).longValue() - 0;
        }
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        LinkedList linkedList = new LinkedList();
        for (byte[] bArr : this.rawSamples.values()) {
            linkedList.add(new SampleImpl(ByteBuffer.wrap(bArr)));
        }
        return linkedList;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
