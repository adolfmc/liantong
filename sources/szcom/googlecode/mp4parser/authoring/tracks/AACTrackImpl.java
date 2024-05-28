package szcom.googlecode.mp4parser.authoring.tracks;

import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AACTrackImpl extends AbstractTrack {
    static Map<Integer, String> audioObjectTypes = new HashMap();
    public static Map<Integer, Integer> samplingFrequencyIndexMap;
    long avgBitRate;
    int bufferSizeDB;
    private DataSource dataSource;
    long[] decTimes;
    AdtsHeader firstHeader;
    private String lang;
    long maxBitRate;
    SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    TrackMetaData trackMetaData;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class AdtsHeader {
        int bufferFullness;
        int channelconfig;
        int copyrightStart;
        int copyrightedStream;
        int frameLength;
        int home;
        int layer;
        int mpegVersion;
        int numAacFramesPerAdtsFrame;
        int original;
        int profile;
        int protectionAbsent;
        int sampleFrequencyIndex;
        int sampleRate;

        AdtsHeader() {
        }

        int getSize() {
            return (this.protectionAbsent == 0 ? 2 : 0) + 7;
        }
    }

    static {
        audioObjectTypes.put(1, "AAC Main");
        audioObjectTypes.put(2, "AAC LC (Low Complexity)");
        audioObjectTypes.put(3, "AAC SSR (Scalable Sample Rate)");
        audioObjectTypes.put(4, "AAC LTP (Long Term Prediction)");
        audioObjectTypes.put(5, "SBR (Spectral Band Replication)");
        audioObjectTypes.put(6, "AAC Scalable");
        audioObjectTypes.put(7, "TwinVQ");
        audioObjectTypes.put(8, "CELP (Code Excited Linear Prediction)");
        audioObjectTypes.put(9, "HXVC (Harmonic Vector eXcitation Coding)");
        audioObjectTypes.put(10, "Reserved");
        audioObjectTypes.put(11, "Reserved");
        audioObjectTypes.put(12, "TTSI (Text-To-Speech Interface)");
        audioObjectTypes.put(13, "Main Synthesis");
        audioObjectTypes.put(14, "Wavetable Synthesis");
        audioObjectTypes.put(15, "General MIDI");
        audioObjectTypes.put(16, "Algorithmic Synthesis and Audio Effects");
        audioObjectTypes.put(17, "ER (Error Resilient) AAC LC");
        audioObjectTypes.put(18, "Reserved");
        audioObjectTypes.put(19, "ER AAC LTP");
        audioObjectTypes.put(20, "ER AAC Scalable");
        audioObjectTypes.put(21, "ER TwinVQ");
        audioObjectTypes.put(22, "ER BSAC (Bit-Sliced Arithmetic Coding)");
        audioObjectTypes.put(23, "ER AAC LD (Low Delay)");
        audioObjectTypes.put(24, "ER CELP");
        audioObjectTypes.put(25, "ER HVXC");
        audioObjectTypes.put(26, "ER HILN (Harmonic and Individual Lines plus Noise)");
        audioObjectTypes.put(27, "ER Parametric");
        audioObjectTypes.put(28, "SSC (SinuSoidal Coding)");
        audioObjectTypes.put(29, "PS (Parametric Stereo)");
        audioObjectTypes.put(30, "MPEG Surround");
        audioObjectTypes.put(31, "(Escape value)");
        audioObjectTypes.put(32, "Layer-1");
        audioObjectTypes.put(33, "Layer-2");
        audioObjectTypes.put(34, "Layer-3");
        audioObjectTypes.put(35, "DST (Direct Stream Transfer)");
        audioObjectTypes.put(36, "ALS (Audio Lossless)");
        audioObjectTypes.put(37, "SLS (Scalable LosslesS)");
        audioObjectTypes.put(38, "SLS non-core");
        audioObjectTypes.put(39, "ER AAC ELD (Enhanced Low Delay)");
        audioObjectTypes.put(40, "SMR (Symbolic Music Representation) Simple");
        audioObjectTypes.put(41, "SMR Main");
        audioObjectTypes.put(42, "USAC (Unified Speech and Audio Coding) (no SBR)");
        audioObjectTypes.put(43, "SAOC (Spatial Audio Object Coding)");
        audioObjectTypes.put(44, "LD MPEG Surround");
        audioObjectTypes.put(45, "USAC");
        samplingFrequencyIndexMap = new HashMap();
        samplingFrequencyIndexMap.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(44100, 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(24000, 6);
        samplingFrequencyIndexMap.put(22050, 7);
        samplingFrequencyIndexMap.put(16000, 8);
        samplingFrequencyIndexMap.put(12000, 9);
        samplingFrequencyIndexMap.put(11025, 10);
        samplingFrequencyIndexMap.put(8000, 11);
        samplingFrequencyIndexMap.put(0, 96000);
        samplingFrequencyIndexMap.put(1, 88200);
        samplingFrequencyIndexMap.put(2, 64000);
        samplingFrequencyIndexMap.put(3, 48000);
        samplingFrequencyIndexMap.put(4, 44100);
        samplingFrequencyIndexMap.put(5, 32000);
        samplingFrequencyIndexMap.put(6, 24000);
        samplingFrequencyIndexMap.put(7, 22050);
        samplingFrequencyIndexMap.put(8, 16000);
        samplingFrequencyIndexMap.put(9, 12000);
        samplingFrequencyIndexMap.put(10, 11025);
        samplingFrequencyIndexMap.put(11, 8000);
    }

    public AACTrackImpl(DataSource dataSource) {
        this(dataSource, "eng");
    }

    public AACTrackImpl(DataSource dataSource, String str) {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.lang = "eng";
        this.lang = str;
        this.dataSource = dataSource;
        this.samples = new ArrayList();
        this.firstHeader = readSamples(dataSource);
        double d = this.firstHeader.sampleRate / 1024.0d;
        double size = this.samples.size() / d;
        LinkedList linkedList = new LinkedList();
        Iterator<Sample> it = this.samples.iterator();
        long j = 0;
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            int size2 = (int) it.next().getSize();
            j += size2;
            linkedList.add(Integer.valueOf(size2));
            while (linkedList.size() > d) {
                linkedList.pop();
            }
            if (linkedList.size() == ((int) d)) {
                Iterator it2 = linkedList.iterator();
                while (it2.hasNext()) {
                    i += ((Integer) it2.next()).intValue();
                }
                double size3 = ((i * 8.0d) / linkedList.size()) * d;
                if (size3 > this.maxBitRate) {
                    this.maxBitRate = (int) size3;
                }
            }
        }
        this.avgBitRate = (int) ((j * 8) / size);
        this.bufferSizeDB = MediaRecorderBase.VIDEO_BITRATE_MEDIUM;
        this.sampleDescriptionBox = new SampleDescriptionBox();
        AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.TYPE3);
        audioSampleEntry.setChannelCount(this.firstHeader.channelconfig == 7 ? 8 : this.firstHeader.channelconfig);
        audioSampleEntry.setSampleRate(this.firstHeader.sampleRate);
        audioSampleEntry.setDataReferenceIndex(1);
        audioSampleEntry.setSampleSize(16);
        ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
        ESDescriptor eSDescriptor = new ESDescriptor();
        eSDescriptor.setEsId(0);
        SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
        sLConfigDescriptor.setPredefined(2);
        eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
        DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
        decoderConfigDescriptor.setObjectTypeIndication(64);
        decoderConfigDescriptor.setStreamType(5);
        decoderConfigDescriptor.setBufferSizeDB(this.bufferSizeDB);
        decoderConfigDescriptor.setMaxBitRate(this.maxBitRate);
        decoderConfigDescriptor.setAvgBitRate(this.avgBitRate);
        AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
        audioSpecificConfig.setOriginalAudioObjectType(2);
        audioSpecificConfig.setSamplingFrequencyIndex(this.firstHeader.sampleFrequencyIndex);
        audioSpecificConfig.setChannelConfiguration(this.firstHeader.channelconfig);
        decoderConfigDescriptor.setAudioSpecificInfo(audioSpecificConfig);
        eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
        eSDescriptorBox.setEsDescriptor(eSDescriptor);
        audioSampleEntry.addBox(eSDescriptorBox);
        this.sampleDescriptionBox.addBox(audioSampleEntry);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setLanguage(str);
        this.trackMetaData.setVolume(1.0f);
        this.trackMetaData.setTimescale(this.firstHeader.sampleRate);
        this.decTimes = new long[this.samples.size()];
        Arrays.fill(this.decTimes, 1024L);
    }

    private AdtsHeader readADTSHeader(DataSource dataSource) {
        AdtsHeader adtsHeader = new AdtsHeader();
        ByteBuffer allocate = ByteBuffer.allocate(7);
        while (allocate.position() < 7) {
            if (dataSource.read(allocate) == -1) {
                return null;
            }
        }
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer((ByteBuffer) allocate.rewind());
        if (bitReaderBuffer.readBits(12) == 4095) {
            adtsHeader.mpegVersion = bitReaderBuffer.readBits(1);
            adtsHeader.layer = bitReaderBuffer.readBits(2);
            adtsHeader.protectionAbsent = bitReaderBuffer.readBits(1);
            adtsHeader.profile = bitReaderBuffer.readBits(2) + 1;
            adtsHeader.sampleFrequencyIndex = bitReaderBuffer.readBits(4);
            adtsHeader.sampleRate = samplingFrequencyIndexMap.get(Integer.valueOf(adtsHeader.sampleFrequencyIndex)).intValue();
            bitReaderBuffer.readBits(1);
            adtsHeader.channelconfig = bitReaderBuffer.readBits(3);
            adtsHeader.original = bitReaderBuffer.readBits(1);
            adtsHeader.home = bitReaderBuffer.readBits(1);
            adtsHeader.copyrightedStream = bitReaderBuffer.readBits(1);
            adtsHeader.copyrightStart = bitReaderBuffer.readBits(1);
            adtsHeader.frameLength = bitReaderBuffer.readBits(13);
            adtsHeader.bufferFullness = bitReaderBuffer.readBits(11);
            adtsHeader.numAacFramesPerAdtsFrame = bitReaderBuffer.readBits(2) + 1;
            if (adtsHeader.numAacFramesPerAdtsFrame == 1) {
                if (adtsHeader.protectionAbsent == 0) {
                    dataSource.read(ByteBuffer.allocate(2));
                }
                return adtsHeader;
            }
            throw new IOException("This muxer can only work with 1 AAC frame per ADTS frame");
        }
        throw new IOException("Expected Start Word 0xfff");
    }

    private AdtsHeader readSamples(DataSource dataSource) {
        AdtsHeader adtsHeader = null;
        while (true) {
            AdtsHeader readADTSHeader = readADTSHeader(dataSource);
            if (readADTSHeader == null) {
                return adtsHeader;
            }
            if (adtsHeader == null) {
                adtsHeader = readADTSHeader;
            }
            final long position = dataSource.position();
            final long size = readADTSHeader.frameLength - readADTSHeader.getSize();
            this.samples.add(new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.AACTrackImpl.1
                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public ByteBuffer asByteBuffer() {
                    try {
                        return AACTrackImpl.this.dataSource.map(position, size);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public long getSize() {
                    return size;
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public void writeTo(WritableByteChannel writableByteChannel) {
                    AACTrackImpl.this.dataSource.transferTo(position, size, writableByteChannel);
                }
            });
            dataSource.position((dataSource.position() + readADTSHeader.frameLength) - readADTSHeader.getSize());
        }
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
        return this.decTimes;
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
        return "AACTrackImpl{sampleRate=" + this.firstHeader.sampleRate + ", channelconfig=" + this.firstHeader.channelconfig + '}';
    }
}
