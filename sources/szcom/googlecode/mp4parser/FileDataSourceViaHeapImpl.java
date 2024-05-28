package szcom.googlecode.mp4parser;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Logger;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FileDataSourceViaHeapImpl implements DataSource {
    private static Logger LOG = Logger.getLogger(FileDataSourceViaHeapImpl.class);

    /* renamed from: fc */
    FileChannel f27657fc;
    String filename;

    public FileDataSourceViaHeapImpl(File file) {
        this.f27657fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    public FileDataSourceViaHeapImpl(String str) {
        File file = new File(str);
        this.f27657fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    public FileDataSourceViaHeapImpl(FileChannel fileChannel) {
        this.f27657fc = fileChannel;
        this.filename = "unknown";
    }

    public FileDataSourceViaHeapImpl(FileChannel fileChannel, String str) {
        this.f27657fc = fileChannel;
        this.filename = str;
    }

    @Override // szcom.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f27657fc.close();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized ByteBuffer map(long j, long j2) {
        ByteBuffer allocate;
        allocate = ByteBuffer.allocate(CastUtils.l2i(j2));
        this.f27657fc.read(allocate, j);
        return (ByteBuffer) allocate.rewind();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long position() {
        return this.f27657fc.position();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized void position(long j) {
        this.f27657fc.position(j);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized int read(ByteBuffer byteBuffer) {
        return this.f27657fc.read(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long size() {
        return this.f27657fc.size();
    }

    public String toString() {
        return this.filename;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        return this.f27657fc.transferTo(j, j2, writableByteChannel);
    }
}
