package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class OmaDrmAccessUnitFormatBox extends AbstractFullBox {
    public static final String TYPE = "odaf";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private byte allBits;
    private int initVectorLength;
    private int keyIndicatorLength;
    private boolean selectiveEncryption;

    static {
        ajc$preClinit();
    }

    public OmaDrmAccessUnitFormatBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("OmaDrmAccessUnitFormatBox.java", OmaDrmAccessUnitFormatBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isSelectiveEncryption", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "boolean"), 46);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getKeyIndicatorLength", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 50);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getInitVectorLength", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 54);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setInitVectorLength", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "int", "initVectorLength", "", "void"), 58);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setKeyIndicatorLength", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "int", "keyIndicatorLength", "", "void"), 62);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAllBits", "szcom.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "byte", "allBits", "", "void"), 66);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.allBits = (byte) IsoTypeReader.readUInt8(byteBuffer);
        this.selectiveEncryption = (this.allBits & 128) == 128;
        this.keyIndicatorLength = IsoTypeReader.readUInt8(byteBuffer);
        this.initVectorLength = IsoTypeReader.readUInt8(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt8(byteBuffer, this.allBits);
        IsoTypeWriter.writeUInt8(byteBuffer, this.keyIndicatorLength);
        IsoTypeWriter.writeUInt8(byteBuffer, this.initVectorLength);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 7L;
    }

    public int getInitVectorLength() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.initVectorLength;
    }

    public int getKeyIndicatorLength() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.keyIndicatorLength;
    }

    public boolean isSelectiveEncryption() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.selectiveEncryption;
    }

    public void setAllBits(byte b) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.byteObject(b)));
        this.allBits = b;
        this.selectiveEncryption = (b & 128) == 128;
    }

    public void setInitVectorLength(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.initVectorLength = i;
    }

    public void setKeyIndicatorLength(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, Conversions.intObject(i)));
        this.keyIndicatorLength = i;
    }
}
