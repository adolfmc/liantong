package szcom.coremedia.iso.boxes.vodafone;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CoverUriBox extends AbstractFullBox {
    public static final String TYPE = "cvru";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private String coverUri;

    static {
        ajc$preClinit();
    }

    public CoverUriBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("CoverUriBox.java", CoverUriBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCoverUri", "szcom.coremedia.iso.boxes.vodafone.CoverUriBox", "", "", "", "java.lang.String"), 38);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCoverUri", "szcom.coremedia.iso.boxes.vodafone.CoverUriBox", "java.lang.String", "coverUri", "", "void"), 42);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.vodafone.CoverUriBox", "", "", "", "java.lang.String"), 64);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.coverUri = IsoTypeReader.readString(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(Utf8.convert(this.coverUri));
        byteBuffer.put((byte) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.coverUri) + 5;
    }

    public String getCoverUri() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.coverUri;
    }

    public void setCoverUri(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.coverUri = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "CoverUriBox[coverUri=" + getCoverUri() + "]";
    }
}
