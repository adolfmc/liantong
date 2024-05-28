package szcom.coremedia.iso.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class AppleLosslessSpecificBox extends AbstractFullBox {
    public static final String TYPE = "alac";
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
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private long bitRate;
    private int channels;
    private int historyMult;
    private int initialHistory;
    private int kModifier;
    private long maxCodedFrameSize;
    private long maxSamplePerFrame;
    private long sampleRate;
    private int sampleSize;
    private int unknown1;
    private int unknown2;

    static {
        ajc$preClinit();
    }

    public AppleLosslessSpecificBox() {
        super("alac");
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleLosslessSpecificBox.java", AppleLosslessSpecificBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMaxSamplePerFrame", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 34);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMaxSamplePerFrame", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "maxSamplePerFrame", "", "void"), 38);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getKModifier", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 74);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setKModifier", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "kModifier", "", "void"), 78);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getChannels", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 82);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setChannels", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "channels", "", "void"), 86);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getUnknown2", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 90);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setUnknown2", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "unknown2", "", "void"), 94);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMaxCodedFrameSize", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 98);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMaxCodedFrameSize", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "maxCodedFrameSize", "", "void"), 102);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBitRate", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 106);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBitRate", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "bitRate", "", "void"), 110);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getUnknown1", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 42);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleRate", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 114);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleRate", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "sampleRate", "", "void"), 118);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setUnknown1", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "unknown1", "", "void"), 46);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleSize", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 50);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleSize", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "sampleSize", "", "void"), 54);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getHistoryMult", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 58);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setHistoryMult", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "historyMult", "", "void"), 62);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getInitialHistory", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 66);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setInitialHistory", "szcom.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "initialHistory", "", "void"), 70);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.maxSamplePerFrame = IsoTypeReader.readUInt32(byteBuffer);
        this.unknown1 = IsoTypeReader.readUInt8(byteBuffer);
        this.sampleSize = IsoTypeReader.readUInt8(byteBuffer);
        this.historyMult = IsoTypeReader.readUInt8(byteBuffer);
        this.initialHistory = IsoTypeReader.readUInt8(byteBuffer);
        this.kModifier = IsoTypeReader.readUInt8(byteBuffer);
        this.channels = IsoTypeReader.readUInt8(byteBuffer);
        this.unknown2 = IsoTypeReader.readUInt16(byteBuffer);
        this.maxCodedFrameSize = IsoTypeReader.readUInt32(byteBuffer);
        this.bitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.sampleRate = IsoTypeReader.readUInt32(byteBuffer);
    }

    public long getBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this));
        return this.bitRate;
    }

    public int getChannels() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.channels;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxSamplePerFrame);
        IsoTypeWriter.writeUInt8(byteBuffer, this.unknown1);
        IsoTypeWriter.writeUInt8(byteBuffer, this.sampleSize);
        IsoTypeWriter.writeUInt8(byteBuffer, this.historyMult);
        IsoTypeWriter.writeUInt8(byteBuffer, this.initialHistory);
        IsoTypeWriter.writeUInt8(byteBuffer, this.kModifier);
        IsoTypeWriter.writeUInt8(byteBuffer, this.channels);
        IsoTypeWriter.writeUInt16(byteBuffer, this.unknown2);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxCodedFrameSize);
        IsoTypeWriter.writeUInt32(byteBuffer, this.bitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.sampleRate);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 28L;
    }

    public int getHistoryMult() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.historyMult;
    }

    public int getInitialHistory() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.initialHistory;
    }

    public int getKModifier() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.kModifier;
    }

    public long getMaxCodedFrameSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this));
        return this.maxCodedFrameSize;
    }

    public long getMaxSamplePerFrame() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.maxSamplePerFrame;
    }

    public long getSampleRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this));
        return this.sampleRate;
    }

    public int getSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.sampleSize;
    }

    public int getUnknown1() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.unknown1;
    }

    public int getUnknown2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this));
        return this.unknown2;
    }

    public void setBitRate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, Conversions.intObject(i)));
        this.bitRate = i;
    }

    public void setChannels(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.intObject(i)));
        this.channels = i;
    }

    public void setHistoryMult(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.historyMult = i;
    }

    public void setInitialHistory(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(i)));
        this.initialHistory = i;
    }

    public void setKModifier(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.intObject(i)));
        this.kModifier = i;
    }

    public void setMaxCodedFrameSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(i)));
        this.maxCodedFrameSize = i;
    }

    public void setMaxSamplePerFrame(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.maxSamplePerFrame = i;
    }

    public void setSampleRate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this, Conversions.intObject(i)));
        this.sampleRate = i;
    }

    public void setSampleSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.sampleSize = i;
    }

    public void setUnknown1(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.unknown1 = i;
    }

    public void setUnknown2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, Conversions.intObject(i)));
        this.unknown2 = i;
    }
}
