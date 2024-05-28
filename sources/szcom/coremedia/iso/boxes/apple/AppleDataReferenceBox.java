package szcom.coremedia.iso.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleDataReferenceBox extends AbstractFullBox {
    public static final String TYPE = "rdrf";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private String dataReference;
    private int dataReferenceSize;
    private String dataReferenceType;

    static {
        ajc$preClinit();
    }

    public AppleDataReferenceBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleDataReferenceBox.java", AppleDataReferenceBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataReferenceSize", "szcom.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "long"), 63);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataReferenceType", "szcom.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 67);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataReference", "szcom.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 71);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.dataReferenceType = IsoTypeReader.read4cc(byteBuffer);
        this.dataReferenceSize = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.dataReference = IsoTypeReader.readString(byteBuffer, this.dataReferenceSize);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(IsoFile.fourCCtoBytes(this.dataReferenceType));
        IsoTypeWriter.writeUInt32(byteBuffer, this.dataReferenceSize);
        byteBuffer.put(Utf8.convert(this.dataReference));
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.dataReferenceSize + 12;
    }

    public String getDataReference() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.dataReference;
    }

    public long getDataReferenceSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.dataReferenceSize;
    }

    public String getDataReferenceType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.dataReferenceType;
    }
}
