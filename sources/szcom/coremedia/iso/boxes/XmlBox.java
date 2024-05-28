package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class XmlBox extends AbstractFullBox {
    public static final String TYPE = "xml ";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    String xml;

    static {
        ajc$preClinit();
    }

    public XmlBox() {
        super(TYPE);
        this.xml = "";
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("XmlBox.java", XmlBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getXml", "szcom.coremedia.iso.boxes.XmlBox", "", "", "", "java.lang.String"), 20);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setXml", "szcom.coremedia.iso.boxes.XmlBox", "java.lang.String", "xml", "", "void"), 24);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.XmlBox", "", "", "", "java.lang.String"), 46);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.xml = IsoTypeReader.readString(byteBuffer, byteBuffer.remaining());
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(Utf8.convert(this.xml));
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.xml) + 4;
    }

    public String getXml() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.xml;
    }

    public void setXml(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.xml = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "XmlBox{xml='" + this.xml + "'}";
    }
}
