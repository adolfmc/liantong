package szcom.coremedia.iso.boxes.vodafone;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class LyricsUriBox extends AbstractFullBox {
    public static final String TYPE = "lrcu";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private String lyricsUri;

    static {
        ajc$preClinit();
    }

    public LyricsUriBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("LyricsUriBox.java", LyricsUriBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLyricsUri", "szcom.coremedia.iso.boxes.vodafone.LyricsUriBox", "", "", "", "java.lang.String"), 39);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLyricsUri", "szcom.coremedia.iso.boxes.vodafone.LyricsUriBox", "java.lang.String", "lyricsUri", "", "void"), 43);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.vodafone.LyricsUriBox", "", "", "", "java.lang.String"), 64);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.lyricsUri = IsoTypeReader.readString(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(Utf8.convert(this.lyricsUri));
        byteBuffer.put((byte) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.lyricsUri) + 5;
    }

    public String getLyricsUri() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.lyricsUri;
    }

    public void setLyricsUri(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.lyricsUri = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "LyricsUriBox[lyricsUri=" + getLyricsUri() + "]";
    }
}
