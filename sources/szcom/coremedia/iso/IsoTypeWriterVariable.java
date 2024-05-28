package szcom.coremedia.iso;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class IsoTypeWriterVariable {
    public static void write(long j, ByteBuffer byteBuffer, int i) {
        if (i == 8) {
            IsoTypeWriter.writeUInt64(byteBuffer, j);
            return;
        }
        switch (i) {
            case 1:
                IsoTypeWriter.writeUInt8(byteBuffer, (int) (j & 255));
                return;
            case 2:
                IsoTypeWriter.writeUInt16(byteBuffer, (int) (j & 65535));
                return;
            case 3:
                IsoTypeWriter.writeUInt24(byteBuffer, (int) (j & 16777215));
                return;
            case 4:
                IsoTypeWriter.writeUInt32(byteBuffer, j);
                return;
            default:
                throw new RuntimeException("I don't know how to read " + i + " bytes");
        }
    }
}
