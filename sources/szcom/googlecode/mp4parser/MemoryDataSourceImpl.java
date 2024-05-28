package szcom.googlecode.mp4parser;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MemoryDataSourceImpl implements DataSource {
    ByteBuffer data;

    public MemoryDataSourceImpl(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    public MemoryDataSourceImpl(byte[] bArr) {
        this.data = ByteBuffer.wrap(bArr);
    }

    @Override // szcom.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j, long j2) {
        int position = this.data.position();
        this.data.position(CastUtils.l2i(j));
        ByteBuffer slice = this.data.slice();
        slice.limit(CastUtils.l2i(j2));
        this.data.position(position);
        return slice;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long position() {
        return this.data.position();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public void position(long j) {
        this.data.position(CastUtils.l2i(j));
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) {
        if (this.data.remaining() != 0 || byteBuffer.remaining() == 0) {
            int min = Math.min(byteBuffer.remaining(), this.data.remaining());
            if (byteBuffer.hasArray()) {
                byteBuffer.put(this.data.array(), this.data.position(), min);
                ByteBuffer byteBuffer2 = this.data;
                byteBuffer2.position(byteBuffer2.position() + min);
            } else {
                byte[] bArr = new byte[min];
                this.data.get(bArr);
                byteBuffer.put(bArr);
            }
            return min;
        }
        return -1;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long size() {
        return this.data.capacity();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        return writableByteChannel.write((ByteBuffer) ((ByteBuffer) this.data.position(CastUtils.l2i(j))).slice().limit(CastUtils.l2i(j2)));
    }
}
