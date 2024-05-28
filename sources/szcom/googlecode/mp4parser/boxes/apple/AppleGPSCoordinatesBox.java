package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleGPSCoordinatesBox extends AbstractBox {
    private static final int DEFAULT_LANG = 5575;
    public static final String TYPE = "©xyz";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    String coords;
    int lang;

    static {
        ajc$preClinit();
    }

    public AppleGPSCoordinatesBox() {
        super(TYPE);
        this.lang = DEFAULT_LANG;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleGPSCoordinatesBox.java", AppleGPSCoordinatesBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getValue", "szcom.googlecode.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "", "", "", "java.lang.String"), 22);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setValue", "szcom.googlecode.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "java.lang.String", "iso6709String", "", "void"), 26);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "", "", "", "java.lang.String"), 52);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        int i = byteBuffer.getShort();
        this.lang = byteBuffer.getShort();
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        this.coords = Utf8.convert(bArr);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.putShort((short) this.coords.length());
        byteBuffer.putShort((short) this.lang);
        byteBuffer.put(Utf8.convert(this.coords));
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.coords) + 4;
    }

    public String getValue() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.coords;
    }

    public void setValue(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.lang = DEFAULT_LANG;
        this.coords = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "AppleGPSCoordinatesBox[" + this.coords + "]";
    }
}
