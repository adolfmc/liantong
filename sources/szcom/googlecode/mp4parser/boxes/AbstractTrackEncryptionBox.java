package szcom.googlecode.mp4parser.boxes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.UUID;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractTrackEncryptionBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    int defaultAlgorithmId;
    int defaultIvSize;
    byte[] default_KID;

    static {
        ajc$preClinit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTrackEncryptionBox(String str) {
        super(str);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AbstractTrackEncryptionBox.java", AbstractTrackEncryptionBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultAlgorithmId", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "", "", "", "int"), 24);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultAlgorithmId", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "int", "defaultAlgorithmId", "", "void"), 28);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultIvSize", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "", "", "", "int"), 32);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultIvSize", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "int", "defaultIvSize", "", "void"), 36);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefault_KID", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "", "", "", "java.util.UUID"), 40);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefault_KID", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "java.util.UUID", "uuid", "", "void"), 46);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "java.lang.Object", "o", "", "boolean"), 76);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox", "", "", "", "int"), 90);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.defaultAlgorithmId = IsoTypeReader.readUInt24(byteBuffer);
        this.defaultIvSize = IsoTypeReader.readUInt8(byteBuffer);
        this.default_KID = new byte[16];
        byteBuffer.get(this.default_KID);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractTrackEncryptionBox abstractTrackEncryptionBox = (AbstractTrackEncryptionBox) obj;
        return this.defaultAlgorithmId == abstractTrackEncryptionBox.defaultAlgorithmId && this.defaultIvSize == abstractTrackEncryptionBox.defaultIvSize && Arrays.equals(this.default_KID, abstractTrackEncryptionBox.default_KID);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt24(byteBuffer, this.defaultAlgorithmId);
        IsoTypeWriter.writeUInt8(byteBuffer, this.defaultIvSize);
        byteBuffer.put(this.default_KID);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 24L;
    }

    public int getDefaultAlgorithmId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.defaultAlgorithmId;
    }

    public int getDefaultIvSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.defaultIvSize;
    }

    public UUID getDefault_KID() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        ByteBuffer wrap = ByteBuffer.wrap(this.default_KID);
        wrap.order(ByteOrder.BIG_ENDIAN);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        int i = ((this.defaultAlgorithmId * 31) + this.defaultIvSize) * 31;
        byte[] bArr = this.default_KID;
        return i + (bArr != null ? Arrays.hashCode(bArr) : 0);
    }

    public void setDefaultAlgorithmId(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.defaultAlgorithmId = i;
    }

    public void setDefaultIvSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.defaultIvSize = i;
    }

    public void setDefault_KID(UUID uuid) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, uuid));
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        this.default_KID = wrap.array();
    }
}
