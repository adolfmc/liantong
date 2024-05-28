package szcom.googlecode.mp4parser;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface DataSource extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    ByteBuffer map(long j, long j2);

    long position();

    void position(long j);

    int read(ByteBuffer byteBuffer);

    long size();

    long transferTo(long j, long j2, WritableByteChannel writableByteChannel);
}
