package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CopyrightBox extends AbstractFullBox {
    public static final String TYPE = "cprt";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private String copyright;
    private String language;

    static {
        ajc$preClinit();
    }

    public CopyrightBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("CopyrightBox.java", CopyrightBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguage", "szcom.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 46);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCopyright", "szcom.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 50);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguage", "szcom.coremedia.iso.boxes.CopyrightBox", "java.lang.String", "language", "", "void"), 54);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCopyright", "szcom.coremedia.iso.boxes.CopyrightBox", "java.lang.String", "copyright", "", "void"), 58);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 81);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.language = IsoTypeReader.readIso639(byteBuffer);
        this.copyright = IsoTypeReader.readString(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeIso639(byteBuffer, this.language);
        byteBuffer.put(Utf8.convert(this.copyright));
        byteBuffer.put((byte) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.copyright) + 7;
    }

    public String getCopyright() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.copyright;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.language;
    }

    public void setCopyright(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.copyright = str;
    }

    public void setLanguage(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, str));
        this.language = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return "CopyrightBox[language=" + getLanguage() + ";copyright=" + getCopyright() + "]";
    }
}
