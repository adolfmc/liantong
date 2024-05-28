package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleCoverBox extends AppleDataBox {
    private static final int IMAGE_TYPE_JPG = 13;
    private static final int IMAGE_TYPE_PNG = 14;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private byte[] data;

    static {
        ajc$preClinit();
    }

    public AppleCoverBox() {
        super("covr", 1);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleCoverBox.java", AppleCoverBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCoverData", "szcom.googlecode.mp4parser.boxes.apple.AppleCoverBox", "", "", "", "[B"), 21);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setJpg", "szcom.googlecode.mp4parser.boxes.apple.AppleCoverBox", "[B", "data", "", "void"), 25);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPng", "szcom.googlecode.mp4parser.boxes.apple.AppleCoverBox", "[B", "data", "", "void"), 29);
    }

    private void setImageData(byte[] bArr, int i) {
        this.data = bArr;
        this.dataType = i;
    }

    public byte[] getCoverData() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.data;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return this.data.length;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        this.data = new byte[byteBuffer.limit()];
        byteBuffer.get(this.data);
    }

    public void setJpg(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, bArr));
        setImageData(bArr, 13);
    }

    public void setPng(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, bArr));
        setImageData(bArr, 14);
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected byte[] writeData() {
        return this.data;
    }
}
