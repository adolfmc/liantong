package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DataEntryUrnBox extends AbstractFullBox {
    public static final String TYPE = "urn ";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private String location;
    private String name;

    static {
        ajc$preClinit();
    }

    public DataEntryUrnBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("DataEntryUrnBox.java", DataEntryUrnBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getName", "szcom.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 40);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLocation", "szcom.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 44);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.DataEntryUrnBox", "", "", "", "java.lang.String"), 67);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.name = IsoTypeReader.readString(byteBuffer);
        this.location = IsoTypeReader.readString(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.put(Utf8.convert(this.name));
        byteBuffer.put((byte) 0);
        byteBuffer.put(Utf8.convert(this.location));
        byteBuffer.put((byte) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.name) + 1 + Utf8.utf8StringLengthInBytes(this.location) + 1;
    }

    public String getLocation() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.location;
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.name;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "DataEntryUrlBox[name=" + getName() + ";location=" + getLocation() + "]";
    }
}
