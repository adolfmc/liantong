package szcom.googlecode.mp4parser.boxes.apple;

import com.example.asus.detectionandalign.animation.C4280b;
import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleDiskNumberBox extends AppleDataBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;

    /* renamed from: a */
    int f27664a;

    /* renamed from: b */
    short f27665b;

    static {
        ajc$preClinit();
    }

    public AppleDiskNumberBox() {
        super("disk", 0);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleDiskNumberBox.java", AppleDiskNumberBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getA", "szcom.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", "int"), 16);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setA", "szcom.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "int", "a", "", "void"), 20);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getB", "szcom.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", "short"), 24);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setB", "szcom.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "short", C4280b.f10047a, "", "void"), 28);
    }

    public int getA() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.f27664a;
    }

    public short getB() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.f27665b;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return 6;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        this.f27664a = byteBuffer.getInt();
        this.f27665b = byteBuffer.getShort();
    }

    public void setA(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.f27664a = i;
    }

    public void setB(short s) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.shortObject(s)));
        this.f27665b = s;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected byte[] writeData() {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.putInt(this.f27664a);
        allocate.putShort(this.f27665b);
        return allocate.array();
    }
}
