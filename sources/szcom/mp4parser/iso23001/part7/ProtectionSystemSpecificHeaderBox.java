package szcom.mp4parser.iso23001.part7;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
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
public class ProtectionSystemSpecificHeaderBox extends AbstractFullBox {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static byte[] OMA2_SYSTEM_ID = null;
    public static byte[] PLAYREADY_SYSTEM_ID = null;
    public static final String TYPE = "pssh";
    public static byte[] WIDEVINE;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    byte[] content;
    List<UUID> keyIds;
    byte[] systemId;

    static {
        ajc$preClinit();
        OMA2_SYSTEM_ID = UUIDConverter.convert(UUID.fromString("A2B55680-6F43-11E0-9A3F-0002A5D5C51B"));
        WIDEVINE = UUIDConverter.convert(UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"));
        PLAYREADY_SYSTEM_ID = UUIDConverter.convert(UUID.fromString("9A04F079-9840-4286-AB92-E65BE0885F95"));
    }

    public ProtectionSystemSpecificHeaderBox() {
        super(TYPE);
        this.keyIds = new ArrayList();
    }

    public ProtectionSystemSpecificHeaderBox(byte[] bArr, byte[] bArr2) {
        super(TYPE);
        this.keyIds = new ArrayList();
        this.content = bArr2;
        this.systemId = bArr;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ProtectionSystemSpecificHeaderBox.java", ProtectionSystemSpecificHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getKeyIds", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "java.util.List"), 50);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setKeyIds", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "java.util.List", "keyIds", "", "void"), 54);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSystemId", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 61);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSystemId", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "systemId", "", "void"), 65);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContent", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 70);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setContent", "szcom.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "content", "", "void"), 74);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.systemId = new byte[16];
        byteBuffer.get(this.systemId);
        if (getVersion() > 0) {
            int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
            while (true) {
                int i = l2i - 1;
                if (l2i <= 0) {
                    break;
                }
                byte[] bArr = new byte[16];
                byteBuffer.get(bArr);
                this.keyIds.add(UUIDConverter.convert(bArr));
                l2i = i;
            }
        }
        IsoTypeReader.readUInt32(byteBuffer);
        this.content = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.content);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(this.systemId, 0, 16);
        if (getVersion() > 0) {
            IsoTypeWriter.writeUInt32(byteBuffer, this.keyIds.size());
            for (UUID uuid : this.keyIds) {
                byteBuffer.put(UUIDConverter.convert(uuid));
            }
        }
        IsoTypeWriter.writeUInt32(byteBuffer, this.content.length);
        byteBuffer.put(this.content);
    }

    public byte[] getContent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.content;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long length = this.content.length + 24;
        return getVersion() > 0 ? length + 4 + (this.keyIds.size() * 16) : length;
    }

    public List<UUID> getKeyIds() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.keyIds;
    }

    public byte[] getSystemId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.systemId;
    }

    public void setContent(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, bArr));
        this.content = bArr;
    }

    public void setKeyIds(List<UUID> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, list));
        this.keyIds = list;
    }

    public void setSystemId(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, bArr));
        this.systemId = bArr;
    }
}
