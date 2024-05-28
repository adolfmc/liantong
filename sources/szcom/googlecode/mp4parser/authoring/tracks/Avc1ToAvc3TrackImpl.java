package szcom.googlecode.mp4parser.authoring.tracks;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriterVariable;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.MemoryDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.WrappingTrack;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Path;
import szcom.mp4parser.iso14496.part15.AvcConfigurationBox;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Avc1ToAvc3TrackImpl extends WrappingTrack {
    AvcConfigurationBox avcC;
    List<Sample> samples;
    SampleDescriptionBox stsd;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class ReplaceSyncSamplesList extends AbstractList<Sample> {
        List<Sample> parentSamples;

        public ReplaceSyncSamplesList(List<Sample> list) {
            this.parentSamples = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public Sample get(int i) {
            if (Arrays.binarySearch(Avc1ToAvc3TrackImpl.this.getSyncSamples(), i + 1) >= 0) {
                final int lengthSizeMinusOne = Avc1ToAvc3TrackImpl.this.avcC.getLengthSizeMinusOne() + 1;
                final ByteBuffer allocate = ByteBuffer.allocate(lengthSizeMinusOne);
                final Sample sample = this.parentSamples.get(i);
                return new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.ReplaceSyncSamplesList.1
                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public ByteBuffer asByteBuffer() {
                        int i2 = 0;
                        for (byte[] bArr : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSets()) {
                            i2 += lengthSizeMinusOne + bArr.length;
                        }
                        for (byte[] bArr2 : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSetExts()) {
                            i2 += lengthSizeMinusOne + bArr2.length;
                        }
                        for (byte[] bArr3 : Avc1ToAvc3TrackImpl.this.avcC.getPictureParameterSets()) {
                            i2 += lengthSizeMinusOne + bArr3.length;
                        }
                        ByteBuffer allocate2 = ByteBuffer.allocate(CastUtils.l2i(sample.getSize()) + i2);
                        for (byte[] bArr4 : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSets()) {
                            IsoTypeWriterVariable.write(bArr4.length, allocate2, lengthSizeMinusOne);
                            allocate2.put(bArr4);
                        }
                        for (byte[] bArr5 : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSetExts()) {
                            IsoTypeWriterVariable.write(bArr5.length, allocate2, lengthSizeMinusOne);
                            allocate2.put(bArr5);
                        }
                        for (byte[] bArr6 : Avc1ToAvc3TrackImpl.this.avcC.getPictureParameterSets()) {
                            IsoTypeWriterVariable.write(bArr6.length, allocate2, lengthSizeMinusOne);
                            allocate2.put(bArr6);
                        }
                        allocate2.put(sample.asByteBuffer());
                        return (ByteBuffer) allocate2.rewind();
                    }

                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public long getSize() {
                        int i2 = 0;
                        for (byte[] bArr : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSets()) {
                            i2 += lengthSizeMinusOne + bArr.length;
                        }
                        for (byte[] bArr2 : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSetExts()) {
                            i2 += lengthSizeMinusOne + bArr2.length;
                        }
                        for (byte[] bArr3 : Avc1ToAvc3TrackImpl.this.avcC.getPictureParameterSets()) {
                            i2 += lengthSizeMinusOne + bArr3.length;
                        }
                        return sample.getSize() + i2;
                    }

                    @Override // szcom.googlecode.mp4parser.authoring.Sample
                    public void writeTo(WritableByteChannel writableByteChannel) {
                        for (byte[] bArr : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSets()) {
                            IsoTypeWriterVariable.write(bArr.length, (ByteBuffer) allocate.rewind(), lengthSizeMinusOne);
                            writableByteChannel.write((ByteBuffer) allocate.rewind());
                            writableByteChannel.write(ByteBuffer.wrap(bArr));
                        }
                        for (byte[] bArr2 : Avc1ToAvc3TrackImpl.this.avcC.getSequenceParameterSetExts()) {
                            IsoTypeWriterVariable.write(bArr2.length, (ByteBuffer) allocate.rewind(), lengthSizeMinusOne);
                            writableByteChannel.write((ByteBuffer) allocate.rewind());
                            writableByteChannel.write(ByteBuffer.wrap(bArr2));
                        }
                        for (byte[] bArr3 : Avc1ToAvc3TrackImpl.this.avcC.getPictureParameterSets()) {
                            IsoTypeWriterVariable.write(bArr3.length, (ByteBuffer) allocate.rewind(), lengthSizeMinusOne);
                            writableByteChannel.write((ByteBuffer) allocate.rewind());
                            writableByteChannel.write(ByteBuffer.wrap(bArr3));
                        }
                        sample.writeTo(writableByteChannel);
                    }
                };
            }
            return this.parentSamples.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parentSamples.size();
        }
    }

    public Avc1ToAvc3TrackImpl(Track track) {
        super(track);
        if (!VisualSampleEntry.TYPE3.equals(track.getSampleDescriptionBox().getSampleEntry().getType())) {
            throw new RuntimeException("Only avc1 tracks can be converted to avc3 tracks");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        track.getSampleDescriptionBox().getBox(Channels.newChannel(byteArrayOutputStream));
        this.stsd = (SampleDescriptionBox) Path.getPath(new IsoFile(new MemoryDataSourceImpl(byteArrayOutputStream.toByteArray())), SampleDescriptionBox.TYPE);
        ((VisualSampleEntry) this.stsd.getSampleEntry()).setType(VisualSampleEntry.TYPE4);
        this.avcC = (AvcConfigurationBox) Path.getPath((AbstractContainerBox) this.stsd, "avc./avcC");
        this.samples = new ReplaceSyncSamplesList(track.getSamples());
    }

    @Override // szcom.googlecode.mp4parser.authoring.WrappingTrack, szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // szcom.googlecode.mp4parser.authoring.WrappingTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }
}
