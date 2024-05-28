package szcom.coremedia.iso.boxes.sampleentry;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AmrSpecificBox extends AbstractBox {
    public static final String TYPE = "damr";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private int decoderVersion;
    private int framesPerSample;
    private int modeChangePeriod;
    private int modeSet;
    private String vendor;

    static {
        ajc$preClinit();
    }

    public AmrSpecificBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AmrSpecificBox.java", AmrSpecificBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getVendor", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 46);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDecoderVersion", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 50);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getModeSet", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 54);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getModeChangePeriod", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 58);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFramesPerSample", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 62);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContent", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 84);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 92);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        this.vendor = IsoFile.bytesToFourCC(bArr);
        this.decoderVersion = IsoTypeReader.readUInt8(byteBuffer);
        this.modeSet = IsoTypeReader.readUInt16(byteBuffer);
        this.modeChangePeriod = IsoTypeReader.readUInt8(byteBuffer);
        this.framesPerSample = IsoTypeReader.readUInt8(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, byteBuffer));
        byteBuffer.put(IsoFile.fourCCtoBytes(this.vendor));
        IsoTypeWriter.writeUInt8(byteBuffer, this.decoderVersion);
        IsoTypeWriter.writeUInt16(byteBuffer, this.modeSet);
        IsoTypeWriter.writeUInt8(byteBuffer, this.modeChangePeriod);
        IsoTypeWriter.writeUInt8(byteBuffer, this.framesPerSample);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 9L;
    }

    public int getDecoderVersion() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.decoderVersion;
    }

    public int getFramesPerSample() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.framesPerSample;
    }

    public int getModeChangePeriod() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.modeChangePeriod;
    }

    public int getModeSet() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.modeSet;
    }

    public String getVendor() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.vendor;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return "AmrSpecificBox[vendor=" + getVendor() + ";decoderVersion=" + getDecoderVersion() + ";modeSet=" + getModeSet() + ";modeChangePeriod=" + getModeChangePeriod() + ";framesPerSample=" + getFramesPerSample() + "]";
    }
}
