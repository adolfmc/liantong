package szcom.googlecode.mp4parser.contentprotection;

import java.nio.ByteBuffer;
import java.util.UUID;
import szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GenericHeader extends ProtectionSpecificHeader {
    public static UUID PROTECTION_SYSTEM_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    ByteBuffer data;

    static {
        ProtectionSpecificHeader.uuidRegistry.put(PROTECTION_SYSTEM_ID, GenericHeader.class);
    }

    @Override // szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader
    public ByteBuffer getData() {
        return this.data;
    }

    @Override // szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader
    public UUID getSystemId() {
        return PROTECTION_SYSTEM_ID;
    }

    @Override // szcom.googlecode.mp4parser.boxes.piff.ProtectionSpecificHeader
    public void parse(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }
}
