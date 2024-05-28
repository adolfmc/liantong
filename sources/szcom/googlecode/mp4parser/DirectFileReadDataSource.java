package szcom.googlecode.mp4parser;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DirectFileReadDataSource implements DataSource {
    private static final int TRANSFER_SIZE = 8192;
    private String filename;
    private RandomAccessFile raf;

    public DirectFileReadDataSource(File file) {
        this.raf = new RandomAccessFile(file, "r");
        this.filename = file.getName();
    }

    @Override // szcom.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.raf.close();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j, long j2) {
        this.raf.seek(j);
        byte[] bArr = new byte[CastUtils.l2i(j2)];
        this.raf.readFully(bArr);
        return ByteBuffer.wrap(bArr);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long position() {
        return this.raf.getFilePointer();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public void position(long j) {
        this.raf.seek(j);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        byte[] bArr = new byte[8192];
        int i = 0;
        int i2 = 0;
        while (i < remaining) {
            i2 = this.raf.read(bArr, 0, Math.min(remaining - i, 8192));
            if (i2 < 0) {
                break;
            }
            i += i2;
            byteBuffer.put(bArr, 0, i2);
        }
        if (i2 >= 0 || i != 0) {
            return i;
        }
        return -1;
    }

    public int readAllInOnce(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        int read = this.raf.read(bArr);
        byteBuffer.put(bArr, 0, read);
        return read;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long size() {
        return this.raf.length();
    }

    public String toString() {
        return this.filename;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        return writableByteChannel.write(map(j, j2));
    }
}
