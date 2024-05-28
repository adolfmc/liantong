package szcom.googlecode.mp4parser.authoring.tracks;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.EC3SpecificBox;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class EC3TrackImpl extends AbstractTrack {
    private static final long MAX_FRAMES_PER_MMAP = 20;
    private List<BitStreamInfo> bitStreamInfos;
    private int bitrate;
    private final DataSource dataSource;
    private long[] decodingTimes;
    private int frameSize;
    SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    TrackMetaData trackMetaData;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class BitStreamInfo extends EC3SpecificBox.Entry {
        public int bitrate;
        public int chanmap;
        public int frameSize;
        public int samplerate;
        public int strmtyp;
        public int substreamid;

        @Override // szcom.googlecode.mp4parser.boxes.EC3SpecificBox.Entry
        public String toString() {
            return "BitStreamInfo{frameSize=" + this.frameSize + ", substreamid=" + this.substreamid + ", bitrate=" + this.bitrate + ", samplerate=" + this.samplerate + ", strmtyp=" + this.strmtyp + ", chanmap=" + this.chanmap + '}';
        }
    }

    public EC3TrackImpl(DataSource dataSource) {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.bitStreamInfos = new LinkedList();
        this.dataSource = dataSource;
        boolean z = false;
        while (!z) {
            BitStreamInfo readVariables = readVariables();
            if (readVariables == null) {
                throw new IOException();
            }
            for (BitStreamInfo bitStreamInfo : this.bitStreamInfos) {
                if (readVariables.strmtyp != 1 && bitStreamInfo.substreamid == readVariables.substreamid) {
                    z = true;
                }
            }
            if (!z) {
                this.bitStreamInfos.add(readVariables);
            }
        }
        if (this.bitStreamInfos.size() == 0) {
            throw new IOException();
        }
        int i = this.bitStreamInfos.get(0).samplerate;
        this.sampleDescriptionBox = new SampleDescriptionBox();
        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.TYPE9);
        audioSampleEntry.setChannelCount(2);
        long j = i;
        audioSampleEntry.setSampleRate(j);
        audioSampleEntry.setDataReferenceIndex(1);
        audioSampleEntry.setSampleSize(16);
        EC3SpecificBox eC3SpecificBox = new EC3SpecificBox();
        int[] iArr = new int[this.bitStreamInfos.size()];
        int[] iArr2 = new int[this.bitStreamInfos.size()];
        for (BitStreamInfo bitStreamInfo2 : this.bitStreamInfos) {
            if (bitStreamInfo2.strmtyp == 1) {
                int i2 = bitStreamInfo2.substreamid;
                iArr[i2] = iArr[i2] + 1;
                iArr2[bitStreamInfo2.substreamid] = ((bitStreamInfo2.chanmap >> 5) & 255) | ((bitStreamInfo2.chanmap >> 6) & 256);
            }
        }
        for (BitStreamInfo bitStreamInfo3 : this.bitStreamInfos) {
            if (bitStreamInfo3.strmtyp != 1) {
                EC3SpecificBox.Entry entry = new EC3SpecificBox.Entry();
                entry.fscod = bitStreamInfo3.fscod;
                entry.bsid = bitStreamInfo3.bsid;
                entry.bsmod = bitStreamInfo3.bsmod;
                entry.acmod = bitStreamInfo3.acmod;
                entry.lfeon = bitStreamInfo3.lfeon;
                entry.reserved = 0;
                entry.num_dep_sub = iArr[bitStreamInfo3.substreamid];
                entry.chan_loc = iArr2[bitStreamInfo3.substreamid];
                entry.reserved2 = 0;
                eC3SpecificBox.addEntry(entry);
            }
            this.bitrate += bitStreamInfo3.bitrate;
            this.frameSize += bitStreamInfo3.frameSize;
        }
        eC3SpecificBox.setDataRate(this.bitrate / 1000);
        audioSampleEntry.addBox(eC3SpecificBox);
        this.sampleDescriptionBox.addBox(audioSampleEntry);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setTimescale(j);
        this.trackMetaData.setVolume(1.0f);
        dataSource.position(0L);
        this.samples = readSamples();
        this.decodingTimes = new long[this.samples.size()];
        Arrays.fill(this.decodingTimes, 1536L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Sample> readSamples() {
        int l2i = CastUtils.l2i((this.dataSource.size() - this.dataSource.position()) / this.frameSize);
        ArrayList arrayList = new ArrayList(l2i);
        for (int i = 0; i < l2i; i++) {
            final int i2 = this.frameSize * i;
            arrayList.add(new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.EC3TrackImpl.1
                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public ByteBuffer asByteBuffer() {
                    try {
                        return EC3TrackImpl.this.dataSource.map(i2, EC3TrackImpl.this.frameSize);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public long getSize() {
                    return EC3TrackImpl.this.frameSize;
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public void writeTo(WritableByteChannel writableByteChannel) {
                    EC3TrackImpl.this.dataSource.transferTo(i2, EC3TrackImpl.this.frameSize, writableByteChannel);
                }
            });
        }
        return arrayList;
    }

    private BitStreamInfo readVariables() {
        int readBits;
        int i;
        int i2;
        long position = this.dataSource.position();
        ByteBuffer allocate = ByteBuffer.allocate(200);
        this.dataSource.read(allocate);
        allocate.rewind();
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(allocate);
        if (bitReaderBuffer.readBits(16) != 2935) {
            return null;
        }
        BitStreamInfo bitStreamInfo = new BitStreamInfo();
        bitStreamInfo.strmtyp = bitReaderBuffer.readBits(2);
        bitStreamInfo.substreamid = bitReaderBuffer.readBits(3);
        bitStreamInfo.frameSize = (bitReaderBuffer.readBits(11) + 1) * 2;
        bitStreamInfo.fscod = bitReaderBuffer.readBits(2);
        int i3 = -1;
        if (bitStreamInfo.fscod == 3) {
            i3 = bitReaderBuffer.readBits(2);
            readBits = 3;
        } else {
            readBits = bitReaderBuffer.readBits(2);
        }
        switch (readBits) {
            case 0:
                i = 1;
                break;
            case 1:
                i = 2;
                break;
            case 2:
                i = 3;
                break;
            case 3:
                i = 6;
                break;
            default:
                i = 0;
                break;
        }
        bitStreamInfo.frameSize *= 6 / i;
        bitStreamInfo.acmod = bitReaderBuffer.readBits(3);
        bitStreamInfo.lfeon = bitReaderBuffer.readBits(1);
        bitStreamInfo.bsid = bitReaderBuffer.readBits(5);
        bitReaderBuffer.readBits(5);
        if (1 == bitReaderBuffer.readBits(1)) {
            bitReaderBuffer.readBits(8);
        }
        if (bitStreamInfo.acmod == 0) {
            bitReaderBuffer.readBits(5);
            if (1 == bitReaderBuffer.readBits(1)) {
                bitReaderBuffer.readBits(8);
            }
        }
        if (1 == bitStreamInfo.strmtyp && 1 == bitReaderBuffer.readBits(1)) {
            bitStreamInfo.chanmap = bitReaderBuffer.readBits(16);
        }
        if (1 == bitReaderBuffer.readBits(1)) {
            if (bitStreamInfo.acmod > 2) {
                bitReaderBuffer.readBits(2);
            }
            if (1 == (bitStreamInfo.acmod & 1) && bitStreamInfo.acmod > 2) {
                bitReaderBuffer.readBits(3);
                bitReaderBuffer.readBits(3);
            }
            if ((bitStreamInfo.acmod & 4) > 0) {
                bitReaderBuffer.readBits(3);
                bitReaderBuffer.readBits(3);
            }
            if (1 == bitStreamInfo.lfeon && 1 == bitReaderBuffer.readBits(1)) {
                bitReaderBuffer.readBits(5);
            }
            if (bitStreamInfo.strmtyp == 0) {
                if (1 == bitReaderBuffer.readBits(1)) {
                    bitReaderBuffer.readBits(6);
                }
                if (bitStreamInfo.acmod == 0 && 1 == bitReaderBuffer.readBits(1)) {
                    bitReaderBuffer.readBits(6);
                }
                if (1 == bitReaderBuffer.readBits(1)) {
                    bitReaderBuffer.readBits(6);
                }
                int readBits2 = bitReaderBuffer.readBits(2);
                if (1 == readBits2) {
                    bitReaderBuffer.readBits(5);
                } else if (2 == readBits2) {
                    bitReaderBuffer.readBits(12);
                } else if (3 == readBits2) {
                    int readBits3 = bitReaderBuffer.readBits(5);
                    if (1 == bitReaderBuffer.readBits(1)) {
                        bitReaderBuffer.readBits(5);
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(4);
                        }
                        if (1 == bitReaderBuffer.readBits(1)) {
                            if (1 == bitReaderBuffer.readBits(1)) {
                                bitReaderBuffer.readBits(4);
                            }
                            if (1 == bitReaderBuffer.readBits(1)) {
                                bitReaderBuffer.readBits(4);
                            }
                        }
                    }
                    if (1 == bitReaderBuffer.readBits(1)) {
                        bitReaderBuffer.readBits(5);
                        if (1 == bitReaderBuffer.readBits(1)) {
                            bitReaderBuffer.readBits(7);
                            if (1 == bitReaderBuffer.readBits(1)) {
                                bitReaderBuffer.readBits(8);
                            }
                        }
                    }
                    for (int i4 = 0; i4 < readBits3 + 2; i4++) {
                        bitReaderBuffer.readBits(8);
                    }
                    bitReaderBuffer.byteSync();
                }
                if (bitStreamInfo.acmod < 2) {
                    if (1 == bitReaderBuffer.readBits(1)) {
                        bitReaderBuffer.readBits(14);
                    }
                    if (bitStreamInfo.acmod == 0 && 1 == bitReaderBuffer.readBits(1)) {
                        bitReaderBuffer.readBits(14);
                    }
                    if (1 == bitReaderBuffer.readBits(1)) {
                        if (readBits == 0) {
                            bitReaderBuffer.readBits(5);
                        } else {
                            for (int i5 = 0; i5 < i; i5++) {
                                if (1 == bitReaderBuffer.readBits(1)) {
                                    bitReaderBuffer.readBits(5);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (1 == bitReaderBuffer.readBits(1)) {
            bitStreamInfo.bsmod = bitReaderBuffer.readBits(3);
        }
        switch (bitStreamInfo.fscod) {
            case 0:
                i2 = 48000;
                bitStreamInfo.samplerate = i2;
                break;
            case 1:
                i2 = 44100;
                bitStreamInfo.samplerate = i2;
                break;
            case 2:
                i2 = 32000;
                bitStreamInfo.samplerate = i2;
                break;
            case 3:
                switch (i3) {
                    case 0:
                        i2 = 24000;
                        bitStreamInfo.samplerate = i2;
                        break;
                    case 1:
                        i2 = 22050;
                        bitStreamInfo.samplerate = i2;
                        break;
                    case 2:
                        i2 = 16000;
                        bitStreamInfo.samplerate = i2;
                        break;
                    case 3:
                        i2 = 0;
                        bitStreamInfo.samplerate = i2;
                        break;
                }
        }
        if (bitStreamInfo.samplerate == 0) {
            return null;
        }
        bitStreamInfo.bitrate = (int) ((bitStreamInfo.samplerate / 1536.0d) * bitStreamInfo.frameSize * 8.0d);
        this.dataSource.position(position + bitStreamInfo.frameSize);
        return bitStreamInfo;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.dataSource.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "soun";
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
        return this.decodingTimes;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
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

    public String toString() {
        return "EC3TrackImpl{bitrate=" + this.bitrate + ", bitStreamInfos=" + this.bitStreamInfos + '}';
    }
}
