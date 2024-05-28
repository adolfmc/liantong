package szcom.googlecode.mp4parser.boxes.piff;

import java.nio.ByteBuffer;
import java.util.UUID;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.UUIDConverter;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class UuidBasedProtectionSystemSpecificHeaderBox extends AbstractFullBox {
    public static byte[] USER_TYPE;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    ProtectionSpecificHeader protectionSpecificHeader;
    UUID systemId;

    static {
        ajc$preClinit();
        USER_TYPE = new byte[]{-48, -118, 79, 24, 16, -13, 74, -126, -74, -56, 50, -40, -85, -95, -125, -45};
    }

    public UuidBasedProtectionSystemSpecificHeaderBox() {
        super("uuid", USER_TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("UuidBasedProtectionSystemSpecificHeaderBox.java", UuidBasedProtectionSystemSpecificHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSystemId", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.util.UUID"), 67);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSystemId", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "java.util.UUID", "systemId", "", "void"), 71);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSystemIdString", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 75);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProtectionSpecificHeader", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader"), 79);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProtectionSpecificHeaderString", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 83);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProtectionSpecificHeader", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader", "protectionSpecificHeader", "", "void"), 87);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.piff.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 92);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr);
        this.systemId = UUIDConverter.convert(bArr);
        CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.protectionSpecificHeader = ProtectionSpecificHeader.createFor(this.systemId, byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt64(byteBuffer, this.systemId.getMostSignificantBits());
        IsoTypeWriter.writeUInt64(byteBuffer, this.systemId.getLeastSignificantBits());
        ByteBuffer data = this.protectionSpecificHeader.getData();
        data.rewind();
        IsoTypeWriter.writeUInt32(byteBuffer, data.limit());
        byteBuffer.put(data);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.protectionSpecificHeader.getData().limit() + 24;
    }

    public ProtectionSpecificHeader getProtectionSpecificHeader() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.protectionSpecificHeader;
    }

    public String getProtectionSpecificHeaderString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.protectionSpecificHeader.toString();
    }

    public UUID getSystemId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.systemId;
    }

    public String getSystemIdString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.systemId.toString();
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public byte[] getUserType() {
        return USER_TYPE;
    }

    public void setProtectionSpecificHeader(ProtectionSpecificHeader protectionSpecificHeader) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, protectionSpecificHeader));
        this.protectionSpecificHeader = protectionSpecificHeader;
    }

    public void setSystemId(UUID uuid) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, uuid));
        this.systemId = uuid;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return "UuidBasedProtectionSystemSpecificHeaderBox{systemId=" + this.systemId.toString() + ", dataSize=" + this.protectionSpecificHeader.getData().limit() + '}';
    }
}
