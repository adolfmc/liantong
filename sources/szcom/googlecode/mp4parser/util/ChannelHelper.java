package szcom.googlecode.mp4parser.util;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ChannelHelper {
    private static ByteBuffer empty = ByteBuffer.allocate(0).asReadOnlyBuffer();

    public static int readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i) {
        int read;
        int i2 = 0;
        do {
            read = readableByteChannel.read(byteBuffer);
            if (-1 == read) {
                break;
            }
            i2 += read;
        } while (i2 != i);
        if (read != -1) {
            return i2;
        }
        throw new EOFException("End of file. No more boxes.");
    }

    public static void readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer) {
        readFully(readableByteChannel, byteBuffer, byteBuffer.remaining());
    }
}
