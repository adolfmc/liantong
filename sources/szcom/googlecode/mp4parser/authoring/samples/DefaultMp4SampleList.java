package szcom.googlecode.mp4parser.authoring.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.MovieBox;
import szcom.coremedia.iso.boxes.SampleSizeBox;
import szcom.coremedia.iso.boxes.SampleToChunkBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Logger;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DefaultMp4SampleList extends AbstractList<Sample> {
    private static final Logger LOG = Logger.getLogger(DefaultMp4SampleList.class);
    SoftReference<ByteBuffer>[] cache;
    int[] chunkNumsStartSampleNum;
    long[] chunkOffsets;
    long[] chunkSizes;
    int lastChunk = 0;
    long[][] sampleOffsetsWithinChunks;
    SampleSizeBox ssb;
    Container topLevel;
    TrackBox trackBox;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class SampleImpl implements Sample {
        private int index;

        public SampleImpl(int i) {
            this.index = i;
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public synchronized ByteBuffer asByteBuffer() {
            long j;
            ByteBuffer byteBuffer;
            int chunkForSample = DefaultMp4SampleList.this.getChunkForSample(this.index);
            SoftReference<ByteBuffer> softReference = DefaultMp4SampleList.this.cache[chunkForSample];
            int i = DefaultMp4SampleList.this.chunkNumsStartSampleNum[chunkForSample] - 1;
            long j2 = chunkForSample;
            long[] jArr = DefaultMp4SampleList.this.sampleOffsetsWithinChunks[CastUtils.l2i(j2)];
            j = jArr[this.index - i];
            if (softReference == null || (byteBuffer = softReference.get()) == null) {
                try {
                    byteBuffer = DefaultMp4SampleList.this.topLevel.getByteBuffer(DefaultMp4SampleList.this.chunkOffsets[CastUtils.l2i(j2)], jArr[jArr.length - 1] + DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex((i + jArr.length) - 1));
                    DefaultMp4SampleList.this.cache[chunkForSample] = new SoftReference<>(byteBuffer);
                } catch (IOException e) {
                    StringWriter stringWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(stringWriter));
                    DefaultMp4SampleList.LOG.logError(stringWriter.toString());
                    throw new IndexOutOfBoundsException(e.getMessage());
                }
            }
            return (ByteBuffer) ((ByteBuffer) byteBuffer.duplicate().position(CastUtils.l2i(j))).slice().limit(CastUtils.l2i(DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index)));
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            return DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index);
        }

        public String toString() {
            return "Sample(index: " + this.index + " size: " + DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index) + ")";
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) {
            writableByteChannel.write(asByteBuffer());
        }
    }

    public DefaultMp4SampleList(long j, Container container) {
        int i;
        this.trackBox = null;
        this.cache = null;
        int i2 = 0;
        this.topLevel = container;
        for (TrackBox trackBox : ((MovieBox) container.getBoxes(MovieBox.class).get(0)).getBoxes(TrackBox.class)) {
            if (trackBox.getTrackHeaderBox().getTrackId() == j) {
                this.trackBox = trackBox;
            }
            i2 = 0;
        }
        TrackBox trackBox2 = this.trackBox;
        if (trackBox2 == null) {
            throw new RuntimeException("This MP4 does not contain track " + j);
        }
        this.chunkOffsets = trackBox2.getSampleTableBox().getChunkOffsetBox().getChunkOffsets();
        long[] jArr = this.chunkOffsets;
        this.chunkSizes = new long[jArr.length];
        this.cache = new SoftReference[jArr.length];
        Arrays.fill(this.cache, new SoftReference(null));
        this.sampleOffsetsWithinChunks = new long[this.chunkOffsets.length];
        this.ssb = this.trackBox.getSampleTableBox().getSampleSizeBox();
        List<SampleToChunkBox.Entry> entries = this.trackBox.getSampleTableBox().getSampleToChunkBox().getEntries();
        SampleToChunkBox.Entry[] entryArr = (SampleToChunkBox.Entry[]) entries.toArray(new SampleToChunkBox.Entry[entries.size()]);
        SampleToChunkBox.Entry entry = entryArr[i2];
        long firstChunk = entry.getFirstChunk();
        int l2i = CastUtils.l2i(entry.getSamplesPerChunk());
        int size = size();
        int i3 = l2i;
        int i4 = i2;
        int i5 = i4;
        int i6 = 1;
        int i7 = 1;
        do {
            i4++;
            if (i4 == firstChunk) {
                if (entryArr.length > i6) {
                    SampleToChunkBox.Entry entry2 = entryArr[i6];
                    i5 = i3;
                    i3 = CastUtils.l2i(entry2.getSamplesPerChunk());
                    i6++;
                    firstChunk = entry2.getFirstChunk();
                } else {
                    i5 = i3;
                    i3 = -1;
                    firstChunk = Long.MAX_VALUE;
                }
            }
            this.sampleOffsetsWithinChunks[i4 - 1] = new long[i5];
            i7 += i5;
        } while (i7 <= size);
        this.chunkNumsStartSampleNum = new int[i4 + 1];
        SampleToChunkBox.Entry entry3 = entryArr[i2];
        int i8 = i2;
        long firstChunk2 = entry3.getFirstChunk();
        int i9 = 1;
        int i10 = 1;
        int l2i2 = CastUtils.l2i(entry3.getSamplesPerChunk());
        int i11 = i8;
        while (true) {
            i = i11 + 1;
            this.chunkNumsStartSampleNum[i11] = i9;
            int i12 = l2i2;
            if (i != firstChunk2) {
                l2i2 = i12;
            } else if (entryArr.length > i10) {
                SampleToChunkBox.Entry entry4 = entryArr[i10];
                l2i2 = CastUtils.l2i(entry4.getSamplesPerChunk());
                firstChunk2 = entry4.getFirstChunk();
                i8 = i12;
                i10++;
            } else {
                i8 = i12;
                l2i2 = -1;
                firstChunk2 = Long.MAX_VALUE;
            }
            i9 += i8;
            if (i9 > size) {
                break;
            }
            i11 = i;
        }
        this.chunkNumsStartSampleNum[i] = Integer.MAX_VALUE;
        long j2 = 0;
        int i13 = 0;
        for (int i14 = 1; i14 <= this.ssb.getSampleCount(); i14++) {
            while (i14 == this.chunkNumsStartSampleNum[i13]) {
                i13++;
                j2 = 0;
            }
            long[] jArr2 = this.chunkSizes;
            int i15 = i13 - 1;
            int i16 = i14 - 1;
            jArr2[i15] = jArr2[i15] + this.ssb.getSampleSizeAtIndex(i16);
            this.sampleOffsetsWithinChunks[i15][i14 - this.chunkNumsStartSampleNum[i15]] = j2;
            j2 += this.ssb.getSampleSizeAtIndex(i16);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i) {
        if (i < this.ssb.getSampleCount()) {
            return new SampleImpl(i);
        }
        throw new IndexOutOfBoundsException();
    }

    synchronized int getChunkForSample(int i) {
        int i2 = i + 1;
        if (i2 >= this.chunkNumsStartSampleNum[this.lastChunk] && i2 < this.chunkNumsStartSampleNum[this.lastChunk + 1]) {
            return this.lastChunk;
        } else if (i2 < this.chunkNumsStartSampleNum[this.lastChunk]) {
            this.lastChunk = 0;
            while (this.chunkNumsStartSampleNum[this.lastChunk + 1] <= i2) {
                this.lastChunk++;
            }
            return this.lastChunk;
        } else {
            int i3 = this.lastChunk;
            while (true) {
                this.lastChunk = i3 + 1;
                if (this.chunkNumsStartSampleNum[this.lastChunk + 1] > i2) {
                    return this.lastChunk;
                }
                i3 = this.lastChunk;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return CastUtils.l2i(this.trackBox.getSampleTableBox().getSampleSizeBox().getSampleCount());
    }
}
