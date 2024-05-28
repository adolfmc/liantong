package szcom.googlecode.mp4parser.authoring.tracks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.sampleentry.TextSampleEntry;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.threegpp26245.FontTableBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TextTrackImpl extends AbstractTrack {
    SampleDescriptionBox sampleDescriptionBox;
    List<Sample> samples;
    List<Line> subs;
    TrackMetaData trackMetaData;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Line {
        long from;
        String text;

        /* renamed from: to */
        long f27658to;

        public Line(long j, long j2, String str) {
            this.from = j;
            this.f27658to = j2;
            this.text = str;
        }

        public long getFrom() {
            return this.from;
        }

        public String getText() {
            return this.text;
        }

        public long getTo() {
            return this.f27658to;
        }
    }

    public TextTrackImpl() {
        super("subtitles");
        this.trackMetaData = new TrackMetaData();
        this.subs = new LinkedList();
        this.sampleDescriptionBox = new SampleDescriptionBox();
        TextSampleEntry textSampleEntry = new TextSampleEntry(TextSampleEntry.TYPE1);
        textSampleEntry.setDataReferenceIndex(1);
        textSampleEntry.setStyleRecord(new TextSampleEntry.StyleRecord());
        textSampleEntry.setBoxRecord(new TextSampleEntry.BoxRecord());
        this.sampleDescriptionBox.addBox(textSampleEntry);
        FontTableBox fontTableBox = new FontTableBox();
        fontTableBox.setEntries(Collections.singletonList(new FontTableBox.FontRecord(1, "Serif")));
        textSampleEntry.addBox(fontTableBox);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setTimescale(1000L);
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
        return "sbtl";
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        ArrayList<Long> arrayList = new ArrayList();
        long j = 0;
        for (Line line : this.subs) {
            long j2 = line.from - j;
            int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i > 0) {
                arrayList.add(Long.valueOf(j2));
            } else if (i < 0) {
                throw new Error("Subtitle display times may not intersect");
            }
            arrayList.add(Long.valueOf(line.f27658to - line.from));
            j = line.f27658to;
        }
        long[] jArr = new long[arrayList.size()];
        int i2 = 0;
        for (Long l : arrayList) {
            jArr[i2] = l.longValue();
            i2++;
        }
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public synchronized List<Sample> getSamples() {
        if (this.samples == null) {
            this.samples = new ArrayList();
            long j = 0;
            for (Line line : this.subs) {
                int i = ((line.from - j) > 0L ? 1 : ((line.from - j) == 0L ? 0 : -1));
                if (i > 0) {
                    this.samples.add(new SampleImpl(ByteBuffer.wrap(new byte[2])));
                } else if (i < 0) {
                    throw new Error("Subtitle display times may not intersect");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(line.text.getBytes("UTF-8").length);
                    dataOutputStream.write(line.text.getBytes("UTF-8"));
                    dataOutputStream.close();
                    this.samples.add(new SampleImpl(ByteBuffer.wrap(byteArrayOutputStream.toByteArray())));
                    j = line.f27658to;
                } catch (IOException unused) {
                    throw new Error("VM is broken. Does not support UTF-8");
                }
            }
        }
        return this.samples;
    }

    public List<Line> getSubs() {
        return this.subs;
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
