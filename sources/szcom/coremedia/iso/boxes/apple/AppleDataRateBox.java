package szcom.coremedia.iso.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleDataRateBox extends AbstractFullBox {
    public static final String TYPE = "rmdr";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private long dataRate;

    static {
        ajc$preClinit();
    }

    public AppleDataRateBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleDataRateBox.java", AppleDataRateBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataRate", "szcom.coremedia.iso.boxes.apple.AppleDataRateBox", "", "", "", "long"), 53);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.dataRate = IsoTypeReader.readUInt32(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.dataRate);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 8L;
    }

    public long getDataRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.dataRate;
    }
}
