package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class UnknownBox extends AbstractBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    ByteBuffer data;

    static {
        ajc$preClinit();
    }

    public UnknownBox(String str) {
        super(str);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("UnknownBox.java", UnknownBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getData", "szcom.coremedia.iso.boxes.UnknownBox", "", "", "", "java.nio.ByteBuffer"), 52);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setData", "szcom.coremedia.iso.boxes.UnknownBox", "java.nio.ByteBuffer", "data", "", "void"), 56);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.UnknownBox", "", "", "", "java.lang.String"), 61);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        this.data.rewind();
        byteBuffer.put(this.data);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.data.limit();
    }

    public ByteBuffer getData() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.data;
    }

    public void setData(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, byteBuffer));
        this.data = byteBuffer;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return String.valueOf(getClass().getName()) + "[" + getType() + "]@" + Integer.toHexString(hashCode());
    }
}
