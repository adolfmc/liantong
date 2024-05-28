package szcom.googlecode.mp4parser.authoring.tracks.mjpeg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import szcom.coremedia.iso.Hex;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Edit;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ObjectDescriptorFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OneJpegPerIframe extends AbstractTrack {
    File[] jpegs;
    long[] sampleDurations;
    SampleDescriptionBox stsd;
    long[] syncSamples;
    TrackMetaData trackMetaData;

    public OneJpegPerIframe(String str, File[] fileArr, Track track) {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.jpegs = fileArr;
        if (track.getSyncSamples().length != fileArr.length) {
            throw new RuntimeException("Number of sync samples doesn't match the number of stills (" + track.getSyncSamples().length + " vs. " + fileArr.length + ")");
        }
        BufferedImage read = ImageIO.read(fileArr[0]);
        this.trackMetaData.setWidth(read.getWidth());
        this.trackMetaData.setHeight(read.getHeight());
        this.trackMetaData.setTimescale(track.getTrackMetaData().getTimescale());
        long[] sampleDurations = track.getSampleDurations();
        long[] syncSamples = track.getSyncSamples();
        this.sampleDurations = new long[syncSamples.length];
        long j = 0;
        boolean z = true;
        long j2 = 0;
        int i = 1;
        for (int i2 = 1; i2 < sampleDurations.length; i2++) {
            if (i < syncSamples.length && i2 == syncSamples[i]) {
                this.sampleDurations[i - 1] = j2;
                i++;
                j2 = 0;
            }
            j2 += sampleDurations[i2];
        }
        long[] jArr = this.sampleDurations;
        jArr[jArr.length - 1] = j2;
        this.stsd = new SampleDescriptionBox();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE1);
        this.stsd.addBox(visualSampleEntry);
        ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
        eSDescriptorBox.setData(ByteBuffer.wrap(Hex.decodeHex("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102")));
        eSDescriptorBox.setEsDescriptor((ESDescriptor) ObjectDescriptorFactory.createFrom(-1, ByteBuffer.wrap(Hex.decodeHex("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102"))));
        visualSampleEntry.addBox(eSDescriptorBox);
        this.syncSamples = new long[fileArr.length];
        int i3 = 0;
        while (true) {
            long[] jArr2 = this.syncSamples;
            if (i3 >= jArr2.length) {
                break;
            }
            int i4 = i3 + 1;
            jArr2[i3] = i4;
            i3 = i4;
        }
        boolean z2 = true;
        double d = 0.0d;
        for (Edit edit : track.getEdits()) {
            if (edit.getMediaTime() == -1 && !z) {
                throw new RuntimeException("Cannot accept edit list for processing (1)");
            }
            if (edit.getMediaTime() >= 0 && !z2) {
                throw new RuntimeException("Cannot accept edit list for processing (2)");
            }
            if (edit.getMediaTime() == -1) {
                d += edit.getSegmentDuration();
            } else {
                d -= edit.getMediaTime() / edit.getTimeScale();
                z2 = false;
                z = false;
            }
        }
        if (track.getCompositionTimeEntries() != null && track.getCompositionTimeEntries().size() > 0) {
            int[] blowupCompositionTimes = CompositionTimeToSample.blowupCompositionTimes(track.getCompositionTimeEntries());
            for (int i5 = 0; i5 < blowupCompositionTimes.length && i5 < 50; i5++) {
                blowupCompositionTimes[i5] = (int) (blowupCompositionTimes[i5] + j);
                j += track.getSampleDurations()[i5];
            }
            Arrays.sort(blowupCompositionTimes);
            d += blowupCompositionTimes[0] / track.getTrackMetaData().getTimescale();
        }
        if (d < 0.0d) {
            getEdits().add(new Edit((long) ((-d) * getTrackMetaData().getTimescale()), getTrackMetaData().getTimescale(), 1.0d, getDuration() / getTrackMetaData().getTimescale()));
        } else if (d > 0.0d) {
            getEdits().add(new Edit(-1L, getTrackMetaData().getTimescale(), 1.0d, d));
            getEdits().add(new Edit(0L, getTrackMetaData().getTimescale(), 1.0d, getDuration() / getTrackMetaData().getTimescale()));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "vide";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.sampleDurations;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return new AbstractList<Sample>() { // from class: szcom.googlecode.mp4parser.authoring.tracks.mjpeg.OneJpegPerIframe.1
            @Override // java.util.AbstractList, java.util.List
            public Sample get(final int i) {
                return new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.mjpeg.OneJpegPerIframe.1.1
                    ByteBuffer sample = null;

                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public ByteBuffer asByteBuffer() {
                        if (this.sample == null) {
                            try {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.jpegs[i], "r");
                                this.sample = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        return this.sample;
                    }

                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public long getSize() {
                        return OneJpegPerIframe.this.jpegs[i].length();
                    }

                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public void writeTo(WritableByteChannel writableByteChannel) {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.jpegs[i], "r");
                        randomAccessFile.getChannel().transferTo(0L, randomAccessFile.length(), writableByteChannel);
                        randomAccessFile.close();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return OneJpegPerIframe.this.jpegs.length;
            }
        };
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.syncSamples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
