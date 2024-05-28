package szcom.googlecode.mp4parser.util;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ByteBufferByteChannel implements ByteChannel {
    ByteBuffer byteBuffer;

    public ByteBufferByteChannel(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (this.byteBuffer.remaining() <= 0) {
            return -1;
        }
        byteBuffer.put((ByteBuffer) this.byteBuffer.duplicate().limit(this.byteBuffer.position() + byteBuffer.remaining()));
        ByteBuffer byteBuffer2 = this.byteBuffer;
        byteBuffer2.position(byteBuffer2.position() + remaining);
        return remaining;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        this.byteBuffer.put(byteBuffer);
        return remaining;
    }
}
