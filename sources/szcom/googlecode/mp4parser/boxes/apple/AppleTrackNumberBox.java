package szcom.googlecode.mp4parser.boxes.apple;

import com.example.asus.detectionandalign.animation.C4280b;
import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleTrackNumberBox extends AppleDataBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;

    /* renamed from: a */
    int f27667a;

    /* renamed from: b */
    int f27668b;

    static {
        ajc$preClinit();
    }

    public AppleTrackNumberBox() {
        super("trkn", 0);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleTrackNumberBox.java", AppleTrackNumberBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getA", "szcom.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", "int"), 16);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setA", "szcom.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "int", "a", "", "void"), 20);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getB", "szcom.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", "int"), 24);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setB", "szcom.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "int", C4280b.f10047a, "", "void"), 28);
    }

    public int getA() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.f27667a;
    }

    public int getB() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.f27668b;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return 8;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        this.f27667a = byteBuffer.getInt();
        this.f27668b = byteBuffer.getInt();
    }

    public void setA(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.f27667a = i;
    }

    public void setB(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.f27668b = i;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected byte[] writeData() {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(this.f27667a);
        allocate.putInt(this.f27668b);
        return allocate.array();
    }
}
