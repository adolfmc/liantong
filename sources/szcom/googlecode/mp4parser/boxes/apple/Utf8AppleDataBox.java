package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class Utf8AppleDataBox extends AppleDataBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    String value;

    static {
        ajc$preClinit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Utf8AppleDataBox(String str) {
        super(str, 1);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("Utf8AppleDataBox.java", Utf8AppleDataBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getValue", "szcom.googlecode.mp4parser.boxes.apple.Utf8AppleDataBox", "", "", "", "java.lang.String"), 21);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setValue", "szcom.googlecode.mp4parser.boxes.apple.Utf8AppleDataBox", "java.lang.String", "value", "", "void"), 30);
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return this.value.getBytes(Charset.forName("UTF-8")).length;
    }

    public String getValue() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        if (!isParsed()) {
            parseDetails();
        }
        return this.value;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        this.value = IsoTypeReader.readString(byteBuffer, byteBuffer.remaining());
    }

    public void setValue(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.value = str;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    @DoNotParseDetail
    public byte[] writeData() {
        return Utf8.convert(this.value);
    }
}
