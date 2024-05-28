package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SchemeTypeBox extends AbstractFullBox {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TYPE = "schm";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    String schemeType;
    String schemeUri;
    long schemeVersion;

    static {
        ajc$preClinit();
    }

    public SchemeTypeBox() {
        super(TYPE);
        this.schemeType = "    ";
        this.schemeUri = null;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SchemeTypeBox.java", SchemeTypeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSchemeType", "szcom.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 44);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSchemeVersion", "szcom.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "long"), 48);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSchemeUri", "szcom.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 52);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSchemeType", "szcom.coremedia.iso.boxes.SchemeTypeBox", "java.lang.String", "schemeType", "", "void"), 56);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSchemeVersion", "szcom.coremedia.iso.boxes.SchemeTypeBox", "int", "schemeVersion", "", "void"), 61);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSchemeUri", "szcom.coremedia.iso.boxes.SchemeTypeBox", "java.lang.String", "schemeUri", "", "void"), 65);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 93);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.schemeType = IsoTypeReader.read4cc(byteBuffer);
        this.schemeVersion = IsoTypeReader.readUInt32(byteBuffer);
        if ((getFlags() & 1) == 1) {
            this.schemeUri = IsoTypeReader.readString(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(IsoFile.fourCCtoBytes(this.schemeType));
        IsoTypeWriter.writeUInt32(byteBuffer, this.schemeVersion);
        if ((getFlags() & 1) == 1) {
            byteBuffer.put(Utf8.convert(this.schemeUri));
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return ((getFlags() & 1) == 1 ? Utf8.utf8StringLengthInBytes(this.schemeUri) + 1 : 0) + 12;
    }

    public String getSchemeType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.schemeType;
    }

    public String getSchemeUri() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.schemeUri;
    }

    public long getSchemeVersion() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.schemeVersion;
    }

    public void setSchemeType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.schemeType = str;
    }

    public void setSchemeUri(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, str));
        this.schemeUri = str;
    }

    public void setSchemeVersion(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, Conversions.intObject(i)));
        this.schemeVersion = i;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return "Schema Type Box[schemeUri=" + this.schemeUri + "; schemeType=" + this.schemeType + "; schemeVersion=" + this.schemeVersion + "; ]";
    }
}
