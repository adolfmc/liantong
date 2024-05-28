package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class VideoMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "vmhd";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private int graphicsmode;
    private int[] opcolor;

    static {
        ajc$preClinit();
    }

    public VideoMediaHeaderBox() {
        super(TYPE);
        this.graphicsmode = 0;
        this.opcolor = new int[3];
        setFlags(1);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("VideoMediaHeaderBox.java", VideoMediaHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGraphicsmode", "szcom.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "int"), 39);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getOpcolor", "szcom.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "[I"), 43);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "java.lang.String"), 71);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setOpcolor", "szcom.coremedia.iso.boxes.VideoMediaHeaderBox", "[I", "opcolor", "", "void"), 75);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGraphicsmode", "szcom.coremedia.iso.boxes.VideoMediaHeaderBox", "int", "graphicsmode", "", "void"), 79);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.graphicsmode = IsoTypeReader.readUInt16(byteBuffer);
        this.opcolor = new int[3];
        for (int i = 0; i < 3; i++) {
            this.opcolor[i] = IsoTypeReader.readUInt16(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt16(byteBuffer, this.graphicsmode);
        for (int i : this.opcolor) {
            IsoTypeWriter.writeUInt16(byteBuffer, i);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 12L;
    }

    public int getGraphicsmode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.graphicsmode;
    }

    public int[] getOpcolor() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.opcolor;
    }

    public void setGraphicsmode(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, Conversions.intObject(i)));
        this.graphicsmode = i;
    }

    public void setOpcolor(int[] iArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, iArr));
        this.opcolor = iArr;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "VideoMediaHeaderBox[graphicsmode=" + getGraphicsmode() + ";opcolor0=" + getOpcolor()[0] + ";opcolor1=" + getOpcolor()[1] + ";opcolor2=" + getOpcolor()[2] + "]";
    }
}
