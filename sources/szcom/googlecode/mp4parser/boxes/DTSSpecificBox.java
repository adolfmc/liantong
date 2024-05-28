package szcom.googlecode.mp4parser.boxes;

import com.cjt2325.cameralibrary.CameraInterface;
import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DTSSpecificBox extends AbstractBox {
    public static final String TYPE = "ddts";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_14 = null;
    private static final JoinPoint.StaticPart ajc$tjp_15 = null;
    private static final JoinPoint.StaticPart ajc$tjp_16 = null;
    private static final JoinPoint.StaticPart ajc$tjp_17 = null;
    private static final JoinPoint.StaticPart ajc$tjp_18 = null;
    private static final JoinPoint.StaticPart ajc$tjp_19 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_20 = null;
    private static final JoinPoint.StaticPart ajc$tjp_21 = null;
    private static final JoinPoint.StaticPart ajc$tjp_22 = null;
    private static final JoinPoint.StaticPart ajc$tjp_23 = null;
    private static final JoinPoint.StaticPart ajc$tjp_24 = null;
    private static final JoinPoint.StaticPart ajc$tjp_25 = null;
    private static final JoinPoint.StaticPart ajc$tjp_26 = null;
    private static final JoinPoint.StaticPart ajc$tjp_27 = null;
    private static final JoinPoint.StaticPart ajc$tjp_28 = null;
    private static final JoinPoint.StaticPart ajc$tjp_29 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_30 = null;
    private static final JoinPoint.StaticPart ajc$tjp_31 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    long DTSSamplingFrequency;
    int LBRDurationMod;
    long avgBitRate;
    int channelLayout;
    int coreLFEPresent;
    int coreLayout;
    int coreSize;
    int frameDuration;
    long maxBitRate;
    int multiAssetFlag;
    int pcmSampleDepth;
    int representationType;
    int reserved;
    int reservedBoxPresent;
    int stereoDownmix;
    int streamConstruction;

    static {
        ajc$preClinit();
    }

    public DTSSpecificBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("DTSSpecificBox.java", DTSSpecificBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAvgBitRate", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "long"), 89);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAvgBitRate", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "long", "avgBitRate", "", "void"), 93);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getStreamConstruction", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 129);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setStreamConstruction", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "streamConstruction", "", "void"), 133);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCoreLFEPresent", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 137);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCoreLFEPresent", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "coreLFEPresent", "", "void"), 141);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCoreLayout", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), CameraInterface.TYPE_CAPTURE);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCoreLayout", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "coreLayout", "", "void"), 149);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCoreSize", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 153);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCoreSize", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "coreSize", "", "void"), 157);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getStereoDownmix", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 161);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setStereoDownmix", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "stereoDownmix", "", "void"), 165);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDTSSamplingFrequency", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "long"), 97);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getRepresentationType", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 169);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setRepresentationType", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "representationType", "", "void"), 173);
        ajc$tjp_22 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getChannelLayout", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 177);
        ajc$tjp_23 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setChannelLayout", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "channelLayout", "", "void"), 181);
        ajc$tjp_24 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMultiAssetFlag", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 185);
        ajc$tjp_25 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMultiAssetFlag", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "multiAssetFlag", "", "void"), 189);
        ajc$tjp_26 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLBRDurationMod", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 193);
        ajc$tjp_27 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLBRDurationMod", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "LBRDurationMod", "", "void"), 197);
        ajc$tjp_28 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 201);
        ajc$tjp_29 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "reserved", "", "void"), 205);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDTSSamplingFrequency", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "long", "DTSSamplingFrequency", "", "void"), 101);
        ajc$tjp_30 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReservedBoxPresent", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 209);
        ajc$tjp_31 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReservedBoxPresent", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "reservedBoxPresent", "", "void"), 213);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMaxBitRate", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "long"), 105);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMaxBitRate", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "long", "maxBitRate", "", "void"), 109);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPcmSampleDepth", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 113);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPcmSampleDepth", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "pcmSampleDepth", "", "void"), 117);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFrameDuration", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "", "", "", "int"), 121);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFrameDuration", "szcom.googlecode.mp4parser.boxes.DTSSpecificBox", "int", "frameDuration", "", "void"), 125);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.DTSSamplingFrequency = IsoTypeReader.readUInt32(byteBuffer);
        this.maxBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.avgBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.pcmSampleDepth = IsoTypeReader.readUInt8(byteBuffer);
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.frameDuration = bitReaderBuffer.readBits(2);
        this.streamConstruction = bitReaderBuffer.readBits(5);
        this.coreLFEPresent = bitReaderBuffer.readBits(1);
        this.coreLayout = bitReaderBuffer.readBits(6);
        this.coreSize = bitReaderBuffer.readBits(14);
        this.stereoDownmix = bitReaderBuffer.readBits(1);
        this.representationType = bitReaderBuffer.readBits(3);
        this.channelLayout = bitReaderBuffer.readBits(16);
        this.multiAssetFlag = bitReaderBuffer.readBits(1);
        this.LBRDurationMod = bitReaderBuffer.readBits(1);
        this.reservedBoxPresent = bitReaderBuffer.readBits(1);
        this.reserved = bitReaderBuffer.readBits(5);
    }

    public long getAvgBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.avgBitRate;
    }

    public int getChannelLayout() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_22, this, this));
        return this.channelLayout;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt32(byteBuffer, this.DTSSamplingFrequency);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.avgBitRate);
        IsoTypeWriter.writeUInt8(byteBuffer, this.pcmSampleDepth);
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.writeBits(this.frameDuration, 2);
        bitWriterBuffer.writeBits(this.streamConstruction, 5);
        bitWriterBuffer.writeBits(this.coreLFEPresent, 1);
        bitWriterBuffer.writeBits(this.coreLayout, 6);
        bitWriterBuffer.writeBits(this.coreSize, 14);
        bitWriterBuffer.writeBits(this.stereoDownmix, 1);
        bitWriterBuffer.writeBits(this.representationType, 3);
        bitWriterBuffer.writeBits(this.channelLayout, 16);
        bitWriterBuffer.writeBits(this.multiAssetFlag, 1);
        bitWriterBuffer.writeBits(this.LBRDurationMod, 1);
        bitWriterBuffer.writeBits(this.reservedBoxPresent, 1);
        bitWriterBuffer.writeBits(this.reserved, 5);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 20L;
    }

    public int getCoreLFEPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.coreLFEPresent;
    }

    public int getCoreLayout() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this));
        return this.coreLayout;
    }

    public int getCoreSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this));
        return this.coreSize;
    }

    public long getDTSSamplingFrequency() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.DTSSamplingFrequency;
    }

    @DoNotParseDetail
    public int[] getDashAudioChannelConfiguration() {
        int i;
        int i2;
        int channelLayout = getChannelLayout();
        if ((channelLayout & 1) == 1) {
            i2 = 4;
            i = 1;
        } else {
            i = 0;
            i2 = 0;
        }
        if ((channelLayout & 2) == 2) {
            i += 2;
            i2 = i2 | 1 | 2;
        }
        if ((channelLayout & 4) == 4) {
            i += 2;
            i2 = i2 | 16 | 32;
        }
        if ((channelLayout & 8) == 8) {
            i++;
            i2 |= 8;
        }
        if ((channelLayout & 16) == 16) {
            i++;
            i2 |= 256;
        }
        if ((channelLayout & 32) == 32) {
            i += 2;
            i2 = i2 | 4096 | 16384;
        }
        if ((channelLayout & 64) == 64) {
            i += 2;
            i2 = i2 | 16 | 32;
        }
        if ((channelLayout & 128) == 128) {
            i++;
            i2 |= 8192;
        }
        if ((channelLayout & 256) == 256) {
            i++;
            i2 |= 2048;
        }
        if ((channelLayout & 512) == 512) {
            i += 2;
            i2 = i2 | 64 | 128;
        }
        if ((channelLayout & 1024) == 1024) {
            i += 2;
            i2 = i2 | 512 | 1024;
        }
        if ((channelLayout & 2048) == 2048) {
            i += 2;
            i2 = i2 | 16 | 32;
        }
        if ((channelLayout & 4096) == 4096) {
            i++;
            i2 |= 8;
        }
        if ((channelLayout & 8192) == 8192) {
            i += 2;
            i2 = i2 | 16 | 32;
        }
        if ((channelLayout & 16384) == 16384) {
            i++;
            i2 |= 65536;
        }
        if ((32768 & channelLayout) == 32768) {
            i += 2;
            i2 = 131072 | 32768 | i2;
        }
        if ((65536 & channelLayout) == 65536) {
            i++;
        }
        if ((channelLayout & 131072) == 131072) {
            i += 2;
        }
        return new int[]{i, i2};
    }

    public int getFrameDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.frameDuration;
    }

    public int getLBRDurationMod() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_26, this, this));
        return this.LBRDurationMod;
    }

    public long getMaxBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.maxBitRate;
    }

    public int getMultiAssetFlag() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_24, this, this));
        return this.multiAssetFlag;
    }

    public int getPcmSampleDepth() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.pcmSampleDepth;
    }

    public int getRepresentationType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this));
        return this.representationType;
    }

    public int getReserved() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_28, this, this));
        return this.reserved;
    }

    public int getReservedBoxPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_30, this, this));
        return this.reservedBoxPresent;
    }

    public int getStereoDownmix() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this));
        return this.stereoDownmix;
    }

    public int getStreamConstruction() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.streamConstruction;
    }

    public void setAvgBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.longObject(j)));
        this.avgBitRate = j;
    }

    public void setChannelLayout(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_23, this, this, Conversions.intObject(i)));
        this.channelLayout = i;
    }

    public void setCoreLFEPresent(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.intObject(i)));
        this.coreLFEPresent = i;
    }

    public void setCoreLayout(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, Conversions.intObject(i)));
        this.coreLayout = i;
    }

    public void setCoreSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(i)));
        this.coreSize = i;
    }

    public void setDTSSamplingFrequency(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.longObject(j)));
        this.DTSSamplingFrequency = j;
    }

    public void setFrameDuration(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(i)));
        this.frameDuration = i;
    }

    public void setLBRDurationMod(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_27, this, this, Conversions.intObject(i)));
        this.LBRDurationMod = i;
    }

    public void setMaxBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.longObject(j)));
        this.maxBitRate = j;
    }

    public void setMultiAssetFlag(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_25, this, this, Conversions.intObject(i)));
        this.multiAssetFlag = i;
    }

    public void setPcmSampleDepth(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.pcmSampleDepth = i;
    }

    public void setRepresentationType(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this, Conversions.intObject(i)));
        this.representationType = i;
    }

    public void setReserved(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_29, this, this, Conversions.intObject(i)));
        this.reserved = i;
    }

    public void setReservedBoxPresent(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_31, this, this, Conversions.intObject(i)));
        this.reservedBoxPresent = i;
    }

    public void setStereoDownmix(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, Conversions.intObject(i)));
        this.stereoDownmix = i;
    }

    public void setStreamConstruction(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.intObject(i)));
        this.streamConstruction = i;
    }
}
