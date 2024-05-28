package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CleanApertureAtom extends AbstractFullBox {
    public static final String TYPE = "clef";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    double height;
    double width;

    static {
        ajc$preClinit();
    }

    public CleanApertureAtom() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("CleanApertureAtom.java", CleanApertureAtom.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getWidth", "szcom.googlecode.mp4parser.boxes.apple.CleanApertureAtom", "", "", "", "double"), 45);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setWidth", "szcom.googlecode.mp4parser.boxes.apple.CleanApertureAtom", "double", "width", "", "void"), 49);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getHeight", "szcom.googlecode.mp4parser.boxes.apple.CleanApertureAtom", "", "", "", "double"), 53);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setHeight", "szcom.googlecode.mp4parser.boxes.apple.CleanApertureAtom", "double", "height", "", "void"), 57);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.width = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.height = IsoTypeReader.readFixedPoint1616(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.width);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.height);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 12L;
    }

    public double getHeight() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.height;
    }

    public double getWidth() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.width;
    }

    public void setHeight(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.doubleObject(d)));
        this.height = d;
    }

    public void setWidth(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.doubleObject(d)));
        this.width = d;
    }
}
