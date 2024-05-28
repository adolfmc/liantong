package szcom.googlecode.mp4parser.authoring.tracks;

import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.DTSSpecificBox;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DTSTrackImpl extends AbstractTrack {
    private static final int BUFFER = 67108864;
    int bcCoreBitRate;
    int bcCoreChannelMask;
    int bcCoreMaxSampleRate;
    int bitrate;
    int channelCount;
    int channelMask;
    int codecDelayAtMaxFs;
    int coreBitRate;
    int coreChannelMask;
    int coreFramePayloadInBytes;
    int coreMaxSampleRate;
    boolean coreSubStreamPresent;
    private int dataOffset;
    private DataSource dataSource;
    DTSSpecificBox ddts;
    int extAvgBitrate;
    int extFramePayloadInBytes;
    int extPeakBitrate;
    int extSmoothBuffSize;
    boolean extensionSubStreamPresent;
    int frameSize;
    boolean isVBR;
    private String lang;
    int lbrCodingPresent;
    int lsbTrimPercent;
    int maxSampleRate;
    int numExtSubStreams;
    int numFramesTotal;
    int numSamplesOrigAudioAtMaxFs;
    SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    int sampleSize;
    int samplerate;
    private List<Sample> samples;
    int samplesPerFrame;
    int samplesPerFrameAtMaxFs;
    TrackMetaData trackMetaData;
    String type;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class LookAhead {
        ByteBuffer buffer;
        long bufferStartPos;
        private final int corePresent;
        long dataEnd;
        DataSource dataSource;
        int inBufferPos = 0;
        long start;

        LookAhead(DataSource dataSource, long j, long j2, int i) {
            this.dataSource = dataSource;
            this.bufferStartPos = j;
            this.dataEnd = j2 + j;
            this.corePresent = i;
            fillBuffer();
        }

        private void discardByte() {
            this.inBufferPos++;
        }

        private void discardNext4AndMarkStart() {
            long j = this.bufferStartPos;
            int i = this.inBufferPos;
            this.start = j + i;
            this.inBufferPos = i + 4;
        }

        private void discardQWord() {
            this.inBufferPos += 4;
        }

        private void fillBuffer() {
            System.err.println("Fill Buffer");
            DataSource dataSource = this.dataSource;
            long j = this.bufferStartPos;
            this.buffer = dataSource.map(j, Math.min(this.dataEnd - j, 67108864L));
        }

        private ByteBuffer getSample() {
            long j = this.start;
            long j2 = this.bufferStartPos;
            if (j >= j2) {
                this.buffer.position((int) (j - j2));
                ByteBuffer slice = this.buffer.slice();
                slice.limit((int) (this.inBufferPos - (this.start - this.bufferStartPos)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }

        private boolean nextFourEquals(byte b, byte b2, byte b3, byte b4) {
            int limit = this.buffer.limit();
            int i = this.inBufferPos;
            if (limit - i >= 4) {
                return this.buffer.get(i) == b && this.buffer.get(this.inBufferPos + 1) == b2 && this.buffer.get(this.inBufferPos + 2) == b3 && this.buffer.get(this.inBufferPos + 3) == b4;
            } else if (this.bufferStartPos + i + 4 < this.dataSource.size()) {
                return false;
            } else {
                throw new EOFException();
            }
        }

        private boolean nextFourEquals0x64582025() {
            return nextFourEquals((byte) 100, (byte) 88, (byte) 32, (byte) 37);
        }

        private boolean nextFourEquals0x64582025orEof() {
            return nextFourEqualsOrEof((byte) 100, (byte) 88, (byte) 32, (byte) 37);
        }

        private boolean nextFourEquals0x7FFE8001() {
            return nextFourEquals(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean nextFourEquals0x7FFE8001orEof() {
            return nextFourEqualsOrEof(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean nextFourEqualsOrEof(byte b, byte b2, byte b3, byte b4) {
            int limit = this.buffer.limit();
            int i = this.inBufferPos;
            if (limit - i >= 4) {
                if ((this.bufferStartPos + i) % 1048576 == 0) {
                    PrintStream printStream = System.err;
                    StringBuilder sb = new StringBuilder();
                    sb.append(((this.bufferStartPos + this.inBufferPos) / 1024) / 1024);
                    printStream.println(sb.toString());
                }
                return this.buffer.get(this.inBufferPos) == b && this.buffer.get(this.inBufferPos + 1) == b2 && this.buffer.get(this.inBufferPos + 2) == b3 && this.buffer.get(this.inBufferPos + 3) == b4;
            }
            long j = this.bufferStartPos;
            long j2 = this.dataEnd;
            if (i + j + 4 > j2) {
                return j + ((long) i) == j2;
            }
            this.bufferStartPos = this.start;
            this.inBufferPos = 0;
            fillBuffer();
            return nextFourEquals0x7FFE8001();
        }

        public ByteBuffer findNextStart() {
            while (true) {
                try {
                    if (this.corePresent == 1) {
                        if (nextFourEquals0x7FFE8001()) {
                            break;
                        }
                        discardByte();
                    } else if (nextFourEquals0x64582025()) {
                        break;
                    } else {
                        discardByte();
                    }
                } catch (EOFException unused) {
                    return null;
                }
                return null;
            }
            discardNext4AndMarkStart();
            while (true) {
                if (this.corePresent == 1) {
                    if (nextFourEquals0x7FFE8001orEof()) {
                        break;
                    }
                    discardQWord();
                } else if (nextFourEquals0x64582025orEof()) {
                    break;
                } else {
                    discardQWord();
                }
                return null;
            }
            return getSample();
        }
    }

    public DTSTrackImpl(DataSource dataSource) {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.frameSize = 0;
        this.dataOffset = 0;
        this.ddts = new DTSSpecificBox();
        this.isVBR = false;
        this.coreSubStreamPresent = false;
        this.extensionSubStreamPresent = false;
        this.numExtSubStreams = 0;
        this.coreMaxSampleRate = 0;
        this.coreBitRate = 0;
        this.coreChannelMask = 0;
        this.coreFramePayloadInBytes = 0;
        this.extAvgBitrate = 0;
        this.extPeakBitrate = 0;
        this.extSmoothBuffSize = 0;
        this.extFramePayloadInBytes = 0;
        this.maxSampleRate = 0;
        this.lbrCodingPresent = 0;
        this.numFramesTotal = 0;
        this.samplesPerFrameAtMaxFs = 0;
        this.numSamplesOrigAudioAtMaxFs = 0;
        this.channelMask = 0;
        this.codecDelayAtMaxFs = 0;
        this.bcCoreMaxSampleRate = 0;
        this.bcCoreBitRate = 0;
        this.bcCoreChannelMask = 0;
        this.lsbTrimPercent = 0;
        this.type = "none";
        this.lang = "eng";
        this.dataSource = dataSource;
        parse();
    }

    public DTSTrackImpl(DataSource dataSource, String str) {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.frameSize = 0;
        this.dataOffset = 0;
        this.ddts = new DTSSpecificBox();
        this.isVBR = false;
        this.coreSubStreamPresent = false;
        this.extensionSubStreamPresent = false;
        this.numExtSubStreams = 0;
        this.coreMaxSampleRate = 0;
        this.coreBitRate = 0;
        this.coreChannelMask = 0;
        this.coreFramePayloadInBytes = 0;
        this.extAvgBitrate = 0;
        this.extPeakBitrate = 0;
        this.extSmoothBuffSize = 0;
        this.extFramePayloadInBytes = 0;
        this.maxSampleRate = 0;
        this.lbrCodingPresent = 0;
        this.numFramesTotal = 0;
        this.samplesPerFrameAtMaxFs = 0;
        this.numSamplesOrigAudioAtMaxFs = 0;
        this.channelMask = 0;
        this.codecDelayAtMaxFs = 0;
        this.bcCoreMaxSampleRate = 0;
        this.bcCoreBitRate = 0;
        this.bcCoreChannelMask = 0;
        this.lsbTrimPercent = 0;
        this.type = "none";
        this.lang = "eng";
        this.lang = str;
        this.dataSource = dataSource;
        parse();
    }

    private List<Sample> generateSamples(DataSource dataSource, int i, long j, int i2) {
        LookAhead lookAhead = new LookAhead(dataSource, i, j, i2);
        ArrayList arrayList = new ArrayList();
        while (true) {
            final ByteBuffer findNextStart = lookAhead.findNextStart();
            if (findNextStart == null) {
                System.err.println("all samples found");
                return arrayList;
            }
            arrayList.add(new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.DTSTrackImpl.1
                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public ByteBuffer asByteBuffer() {
                    return findNextStart;
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public long getSize() {
                    return findNextStart.rewind().remaining();
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public void writeTo(WritableByteChannel writableByteChannel) {
                    writableByteChannel.write((ByteBuffer) findNextStart.rewind());
                }
            });
        }
    }

    private int getBitRate(int i) {
        switch (i) {
            case 0:
                return 32;
            case 1:
                return 56;
            case 2:
                return 64;
            case 3:
                return 96;
            case 4:
                return 112;
            case 5:
                return 128;
            case 6:
                return 192;
            case 7:
                return 224;
            case 8:
                return 256;
            case 9:
                return 320;
            case 10:
                return 384;
            case 11:
                return 448;
            case 12:
                return 512;
            case 13:
                return 576;
            case 14:
                return 640;
            case 15:
                return 768;
            case 16:
                return 960;
            case 17:
                return 1024;
            case 18:
                return 1152;
            case 19:
                return 1280;
            case 20:
                return 1344;
            case 21:
                return 1408;
            case 22:
                return 1411;
            case 23:
                return 1472;
            case 24:
                return MediaRecorderBase.VIDEO_BITRATE_MEDIUM;
            case 25:
                return -1;
            default:
                throw new IOException("Unknown bitrate value");
        }
    }

    private int getSampleRate(int i) {
        switch (i) {
            case 1:
                return 8000;
            case 2:
                return 16000;
            case 3:
                return 32000;
            case 4:
            case 5:
            case 9:
            case 10:
            default:
                throw new IOException("Unknown Sample Rate");
            case 6:
                return 11025;
            case 7:
                return 22050;
            case 8:
                return 44100;
            case 11:
                return 12000;
            case 12:
                return 24000;
            case 13:
                return 48000;
        }
    }

    private void parse() {
        if (!readVariables()) {
            throw new IOException();
        }
        this.sampleDescriptionBox = new SampleDescriptionBox();
        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(this.type);
        audioSampleEntry.setChannelCount(this.channelCount);
        audioSampleEntry.setSampleRate(this.samplerate);
        audioSampleEntry.setDataReferenceIndex(1);
        audioSampleEntry.setSampleSize(16);
        audioSampleEntry.addBox(this.ddts);
        this.sampleDescriptionBox.addBox(audioSampleEntry);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setLanguage(this.lang);
        this.trackMetaData.setTimescale(this.samplerate);
    }

    private boolean parseAuprhdr(int i, ByteBuffer byteBuffer) {
        int i2;
        byteBuffer.get();
        short s = byteBuffer.getShort();
        this.maxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.numFramesTotal = byteBuffer.getInt();
        this.samplesPerFrameAtMaxFs = byteBuffer.getShort();
        this.numSamplesOrigAudioAtMaxFs = (byteBuffer.get() << 32) | (byteBuffer.getInt() & 65535);
        this.channelMask = byteBuffer.getShort();
        this.codecDelayAtMaxFs = byteBuffer.getShort();
        if ((s & 3) == 3) {
            this.bcCoreMaxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.bcCoreBitRate = byteBuffer.getShort();
            this.bcCoreChannelMask = byteBuffer.getShort();
            i2 = 28;
        } else {
            i2 = 21;
        }
        if ((s & 4) > 0) {
            this.lsbTrimPercent = byteBuffer.get();
            i2++;
        }
        if ((s & 8) > 0) {
            this.lbrCodingPresent = 1;
        }
        while (i2 < i) {
            byteBuffer.get();
            i2++;
        }
        return true;
    }

    private boolean parseCoressmd(int i, ByteBuffer byteBuffer) {
        this.coreMaxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.coreBitRate = byteBuffer.getShort();
        this.coreChannelMask = byteBuffer.getShort();
        this.coreFramePayloadInBytes = byteBuffer.getInt();
        for (int i2 = 11; i2 < i; i2++) {
            byteBuffer.get();
        }
        return true;
    }

    private void parseDtshdhdr(int i, ByteBuffer byteBuffer) {
        int i2;
        byteBuffer.getInt();
        byteBuffer.get();
        byteBuffer.getInt();
        byteBuffer.get();
        short s = byteBuffer.getShort();
        byteBuffer.get();
        this.numExtSubStreams = byteBuffer.get();
        if ((s & 1) == 1) {
            this.isVBR = true;
        }
        if ((s & 8) == 8) {
            this.coreSubStreamPresent = true;
        }
        if ((s & 16) == 16) {
            this.extensionSubStreamPresent = true;
            i2 = this.numExtSubStreams + 1;
        } else {
            i2 = 0;
        }
        this.numExtSubStreams = i2;
        for (int i3 = 14; i3 < i; i3++) {
            byteBuffer.get();
        }
    }

    private boolean parseExtssmd(int i, ByteBuffer byteBuffer) {
        int i2;
        this.extAvgBitrate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        if (this.isVBR) {
            this.extPeakBitrate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.extSmoothBuffSize = byteBuffer.getShort();
            i2 = 8;
        } else {
            this.extFramePayloadInBytes = byteBuffer.getInt();
            i2 = 7;
        }
        while (i2 < i) {
            byteBuffer.get();
            i2++;
        }
        return true;
    }

    /*  JADX ERROR: Failed to decode insn: 0x045D: UNKNOWN(0x55E5), method: szcom.googlecode.mp4parser.authoring.tracks.DTSTrackImpl.readVariables():boolean
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x045D: UNKNOWN(0x55E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    private boolean readVariables() {
        /*
            Method dump skipped, instructions count: 1310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.tracks.DTSTrackImpl.readVariables():boolean");
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
        return this.sampleDurations;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
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
