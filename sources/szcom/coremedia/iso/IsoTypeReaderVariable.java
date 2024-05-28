package szcom.coremedia.iso;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class IsoTypeReaderVariable {
    public static long read(ByteBuffer byteBuffer, int i) {
        int readUInt8;
        if (i != 8) {
            switch (i) {
                case 1:
                    readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
                    break;
                case 2:
                    readUInt8 = IsoTypeReader.readUInt16(byteBuffer);
                    break;
                case 3:
                    readUInt8 = IsoTypeReader.readUInt24(byteBuffer);
                    break;
                case 4:
                    return IsoTypeReader.readUInt32(byteBuffer);
                default:
                    throw new RuntimeException("I don't know how to read " + i + " bytes");
            }
            return readUInt8;
        }
        return IsoTypeReader.readUInt64(byteBuffer);
    }
}
