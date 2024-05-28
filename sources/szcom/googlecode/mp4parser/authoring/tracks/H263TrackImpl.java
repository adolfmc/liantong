package szcom.googlecode.mp4parser.authoring.tracks;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import szcom.coremedia.iso.Hex;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.MultiFileDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import szcom.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack;
import szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderSpecificInfo;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szcom.googlecode.mp4parser.util.Path;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class H263TrackImpl extends AbstractH26XTrack {
    private static Logger LOG = Logger.getLogger(ESDescriptor.class.getName());
    int BINARY;
    int BINARY_ONLY;
    int GRAYSCALE;
    int RECTANGULAR;
    boolean esdsComplete;
    List<ByteBuffer> esdsStuff;
    int fixed_vop_time_increment;
    List<Sample> samples;
    SampleDescriptionBox stsd;
    int vop_time_increment_resolution;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H263TrackImpl(DataSource dataSource) {
        super(dataSource, false);
        int i;
        int i2;
        char c;
        char c2 = 0;
        this.RECTANGULAR = 0;
        this.BINARY = 1;
        int i3 = 2;
        this.BINARY_ONLY = 2;
        this.GRAYSCALE = 3;
        this.samples = new ArrayList();
        this.esdsStuff = new ArrayList();
        this.esdsComplete = false;
        this.fixed_vop_time_increment = -1;
        this.vop_time_increment_resolution = 0;
        AbstractH26XTrack.LookAhead lookAhead = new AbstractH26XTrack.LookAhead(dataSource);
        List<? extends ByteBuffer> arrayList = new ArrayList<>();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE1);
        this.stsd = new SampleDescriptionBox();
        this.stsd.addBox(visualSampleEntry);
        long j = 0;
        long j2 = -1;
        int i4 = 0;
        while (true) {
            ByteBuffer findNextNal = findNextNal(lookAhead);
            if (findNextNal == null) {
                long[] jArr = this.decodingTimes;
                long[] jArr2 = new long[1];
                jArr2[c2] = this.decodingTimes[this.decodingTimes.length - 1];
                this.decodingTimes = Mp4Arrays.copyOfAndAppend(jArr, jArr2);
                ESDescriptor eSDescriptor = new ESDescriptor();
                eSDescriptor.setEsId(1);
                DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
                decoderConfigDescriptor.setObjectTypeIndication(32);
                decoderConfigDescriptor.setStreamType(4);
                DecoderSpecificInfo decoderSpecificInfo = new DecoderSpecificInfo();
                Sample createSampleObject = createSampleObject(this.esdsStuff);
                byte[] bArr = new byte[CastUtils.l2i(createSampleObject.getSize())];
                createSampleObject.asByteBuffer().get(bArr);
                decoderSpecificInfo.setData(bArr);
                decoderConfigDescriptor.setDecoderSpecificInfo(decoderSpecificInfo);
                eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
                SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
                sLConfigDescriptor.setPredefined(i3);
                eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
                ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
                eSDescriptorBox.setEsDescriptor(eSDescriptor);
                visualSampleEntry.addBox(eSDescriptorBox);
                this.trackMetaData.setTimescale(this.vop_time_increment_resolution);
                return;
            }
            ByteBuffer duplicate = findNextNal.duplicate();
            int readUInt8 = IsoTypeReader.readUInt8(findNextNal);
            if (readUInt8 == 176 || readUInt8 == 181 || readUInt8 == 0 || readUInt8 == 32 || readUInt8 == 178) {
                int i5 = i4;
                if (this.esdsComplete) {
                    i = i5;
                } else {
                    this.esdsStuff.add(duplicate);
                    if (readUInt8 == 32) {
                        i = i5;
                        parse0x20Unit(findNextNal, i, visualSampleEntry);
                    } else {
                        i = i5;
                        if (readUInt8 == 181) {
                            i4 = parse0x05Unit(findNextNal);
                            c2 = 0;
                        }
                    }
                }
                i4 = i;
                c2 = 0;
            } else if (readUInt8 == 179) {
                this.esdsComplete = true;
                int readBits = new BitReaderBuffer(findNextNal).readBits(18);
                j = (readBits & 63) + (((readBits >>> 7) & 63) * 60) + (((readBits >>> 13) & 31) * 60 * 60);
                this.stss.add(Integer.valueOf(this.samples.size() + 1));
                arrayList.add(duplicate);
                c2 = 0;
            } else if (readUInt8 != 182) {
                throw new RuntimeException("Got start code I don't know. Ask Sebastian via mp4parser mailing list what to do");
            } else {
                BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(findNextNal);
                bitReaderBuffer.readBits(2);
                while (bitReaderBuffer.readBool()) {
                    j++;
                }
                bitReaderBuffer.readBool();
                int i6 = 0;
                while (this.vop_time_increment_resolution >= (1 << i6)) {
                    i6++;
                }
                int readBits2 = bitReaderBuffer.readBits(i6);
                int i7 = i4;
                long j3 = (this.vop_time_increment_resolution * j) + (readBits2 % i2);
                if (j2 != -1) {
                    c = 0;
                    this.decodingTimes = Mp4Arrays.copyOfAndAppend(this.decodingTimes, j3 - j2);
                } else {
                    c = 0;
                }
                System.err.println("Frame increment: " + (j3 - j2) + " vop time increment: " + readBits2 + " last_sync_point: " + j + " time_code: " + j3);
                arrayList.add(duplicate);
                this.samples.add(createSampleObject(arrayList));
                arrayList.clear();
                j2 = j3;
                c2 = c;
                i4 = i7;
            }
            i3 = 2;
        }
    }

    public static void main(String[] strArr) {
        FileDataSourceImpl fileDataSourceImpl = new FileDataSourceImpl("C:\\content\\bbb.h263");
        Movie movie = new Movie();
        movie.addTrack(new H263TrackImpl(fileDataSourceImpl));
        new DefaultMp4Builder().build(movie).writeContainer(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void main1(String[] strArr) {
        File[] listFiles = new File("C:\\dev\\mp4parser\\frames").listFiles();
        Arrays.sort(listFiles);
        Movie movie = new Movie();
        movie.addTrack(new H263TrackImpl(new MultiFileDataSourceImpl(listFiles)));
        new DefaultMp4Builder().build(movie).writeContainer(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void main2(String[] strArr) {
        ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) Path.getPath(new IsoFile("C:\\content\\bbb.mp4"), "/moov[0]/trak[0]/mdia[0]/minf[0]/stbl[0]/stsd[0]/mp4v[0]/esds[0]");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        eSDescriptorBox.getBox(Channels.newChannel(byteArrayOutputStream));
        System.err.println(Hex.encodeHex(byteArrayOutputStream.toByteArray()));
        System.err.println(eSDescriptorBox.getEsDescriptor());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        eSDescriptorBox.getBox(Channels.newChannel(byteArrayOutputStream2));
        System.err.println(Hex.encodeHex(byteArrayOutputStream2.toByteArray()));
    }

    private int parse0x05Unit(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        if (bitReaderBuffer.readBool()) {
            int readBits = bitReaderBuffer.readBits(4);
            bitReaderBuffer.readBits(3);
            return readBits;
        }
        return 0;
    }

    private void parse0x20Unit(ByteBuffer byteBuffer, int i, VisualSampleEntry visualSampleEntry) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        bitReaderBuffer.readBool();
        bitReaderBuffer.readBits(8);
        if (bitReaderBuffer.readBool()) {
            i = bitReaderBuffer.readBits(4);
            bitReaderBuffer.readBits(3);
        }
        if (bitReaderBuffer.readBits(4) == 15) {
            bitReaderBuffer.readBits(8);
            bitReaderBuffer.readBits(8);
        }
        if (bitReaderBuffer.readBool()) {
            bitReaderBuffer.readBits(2);
            bitReaderBuffer.readBool();
            if (bitReaderBuffer.readBool()) {
                throw new RuntimeException("Implemented when needed");
            }
        }
        int readBits = bitReaderBuffer.readBits(2);
        if (readBits == this.GRAYSCALE && i != 1) {
            bitReaderBuffer.readBits(4);
        }
        bitReaderBuffer.readBool();
        this.vop_time_increment_resolution = bitReaderBuffer.readBits(16);
        bitReaderBuffer.readBool();
        if (bitReaderBuffer.readBool()) {
            LOG.info("Fixed Frame Rate");
            int i2 = 0;
            while (this.vop_time_increment_resolution >= (1 << i2)) {
                i2++;
            }
            this.fixed_vop_time_increment = bitReaderBuffer.readBits(i2);
        }
        if (readBits == this.BINARY_ONLY) {
            throw new RuntimeException("Please implmenet me");
        }
        if (readBits == this.RECTANGULAR) {
            bitReaderBuffer.readBool();
            visualSampleEntry.setWidth(bitReaderBuffer.readBits(13));
            bitReaderBuffer.readBool();
            visualSampleEntry.setHeight(bitReaderBuffer.readBits(13));
            bitReaderBuffer.readBool();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // szcom.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack
    public Sample createSampleObject(List<? extends ByteBuffer> list) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[]{0, 0, 1});
        ByteBuffer[] byteBufferArr = new ByteBuffer[list.size() * 2];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 2;
            byteBufferArr[i2] = wrap;
            byteBufferArr[i2 + 1] = list.get(i);
        }
        return new SampleImpl(byteBufferArr);
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
    public List<Sample> getSamples() {
        return this.samples;
    }
}
