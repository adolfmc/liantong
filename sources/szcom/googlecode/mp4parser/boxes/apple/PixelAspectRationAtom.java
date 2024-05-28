package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class PixelAspectRationAtom extends AbstractBox {
    public static final String TYPE = "pasp";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private int hSpacing;
    private int vSpacing;

    static {
        ajc$preClinit();
    }

    public PixelAspectRationAtom() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("PixelAspectRationAtom.java", PixelAspectRationAtom.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "gethSpacing", "szcom.googlecode.mp4parser.boxes.apple.PixelAspectRationAtom", "", "", "", "int"), 35);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "sethSpacing", "szcom.googlecode.mp4parser.boxes.apple.PixelAspectRationAtom", "int", "hSpacing", "", "void"), 39);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getvSpacing", "szcom.googlecode.mp4parser.boxes.apple.PixelAspectRationAtom", "", "", "", "int"), 43);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setvSpacing", "szcom.googlecode.mp4parser.boxes.apple.PixelAspectRationAtom", "int", "vSpacing", "", "void"), 47);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.hSpacing = byteBuffer.getInt();
        this.vSpacing = byteBuffer.getInt();
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.hSpacing);
        byteBuffer.putInt(this.vSpacing);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 8L;
    }

    public int gethSpacing() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.hSpacing;
    }

    public int getvSpacing() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.vSpacing;
    }

    public void sethSpacing(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.hSpacing = i;
    }

    public void setvSpacing(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.vSpacing = i;
    }
}
