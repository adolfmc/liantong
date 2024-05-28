package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;
import java.util.UUID;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.util.UUIDConverter;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencSampleEncryptionInformationGroupEntry extends GroupEntry {
    public static final String TYPE = "seig";
    private boolean isEncrypted;
    private byte ivSize;
    private UUID kid;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleEncryptionInformationGroupEntry cencSampleEncryptionInformationGroupEntry = (CencSampleEncryptionInformationGroupEntry) obj;
        if (this.isEncrypted == cencSampleEncryptionInformationGroupEntry.isEncrypted && this.ivSize == cencSampleEncryptionInformationGroupEntry.ivSize) {
            UUID uuid = this.kid;
            return uuid == null ? cencSampleEncryptionInformationGroupEntry.kid == null : uuid.equals(cencSampleEncryptionInformationGroupEntry.kid);
        }
        return false;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        byte[] bArr;
        ByteBuffer allocate = ByteBuffer.allocate(20);
        IsoTypeWriter.writeUInt24(allocate, this.isEncrypted ? 1 : 0);
        if (this.isEncrypted) {
            IsoTypeWriter.writeUInt8(allocate, this.ivSize);
            bArr = UUIDConverter.convert(this.kid);
        } else {
            bArr = new byte[17];
        }
        allocate.put(bArr);
        allocate.rewind();
        return allocate;
    }

    public byte getIvSize() {
        return this.ivSize;
    }

    public UUID getKid() {
        return this.kid;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        int i = (((this.isEncrypted ? 7 : 19) * 31) + this.ivSize) * 31;
        UUID uuid = this.kid;
        return i + (uuid != null ? uuid.hashCode() : 0);
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
        this.isEncrypted = IsoTypeReader.readUInt24(byteBuffer) == 1;
        this.ivSize = (byte) IsoTypeReader.readUInt8(byteBuffer);
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr);
        this.kid = UUIDConverter.convert(bArr);
    }

    public void setEncrypted(boolean z) {
        this.isEncrypted = z;
    }

    public void setIvSize(int i) {
        this.ivSize = (byte) i;
    }

    public void setKid(UUID uuid) {
        this.kid = uuid;
    }

    public String toString() {
        return "CencSampleEncryptionInformationGroupEntry{isEncrypted=" + this.isEncrypted + ", ivSize=" + ((int) this.ivSize) + ", kid=" + this.kid + '}';
    }
}
