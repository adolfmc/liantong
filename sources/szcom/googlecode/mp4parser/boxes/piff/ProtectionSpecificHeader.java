package szcom.googlecode.mp4parser.boxes.piff;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import szcom.coremedia.iso.Hex;
import szcom.googlecode.mp4parser.contentprotection.GenericHeader;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class ProtectionSpecificHeader {
    public static Map<UUID, Class<? extends ProtectionSpecificHeader>> uuidRegistry = new HashMap();

    public static ProtectionSpecificHeader createFor(UUID uuid, ByteBuffer byteBuffer) {
        ProtectionSpecificHeader newInstance;
        Class<? extends ProtectionSpecificHeader> cls = uuidRegistry.get(uuid);
        if (cls != null) {
            try {
                newInstance = cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            newInstance = null;
        }
        if (newInstance == null) {
            newInstance = new GenericHeader();
        }
        newInstance.parse(byteBuffer);
        return newInstance;
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("somebody called equals on me but that's not supposed to happen.");
    }

    public abstract ByteBuffer getData();

    public abstract UUID getSystemId();

    public abstract void parse(ByteBuffer byteBuffer);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProtectionSpecificHeader");
        sb.append("{data=");
        ByteBuffer duplicate = getData().duplicate();
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        sb.append(Hex.encodeHex(bArr));
        sb.append('}');
        return sb.toString();
    }
}
