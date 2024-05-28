package szcom.googlecode.mp4parser;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import szcom.googlecode.mp4parser.util.Logger;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FileDataSourceImpl implements DataSource {
    private static Logger LOG = Logger.getLogger(FileDataSourceImpl.class);

    /* renamed from: fc */
    FileChannel f27656fc;
    String filename;

    public FileDataSourceImpl(File file) {
        this.f27656fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    public FileDataSourceImpl(String str) {
        File file = new File(str);
        this.f27656fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    public FileDataSourceImpl(FileChannel fileChannel) {
        this.f27656fc = fileChannel;
        this.filename = "unknown";
    }

    public FileDataSourceImpl(FileChannel fileChannel, String str) {
        this.f27656fc = fileChannel;
        this.filename = str;
    }

    @Override // szcom.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f27656fc.close();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized ByteBuffer map(long j, long j2) {
        Logger logger = LOG;
        logger.logDebug(String.valueOf(j) + " " + j2);
        return this.f27656fc.map(FileChannel.MapMode.READ_ONLY, j, j2);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long position() {
        return this.f27656fc.position();
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized void position(long j) {
        this.f27656fc.position(j);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized int read(ByteBuffer byteBuffer) {
        return this.f27656fc.read(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long size() {
        return this.f27656fc.size();
    }

    public String toString() {
        return this.filename;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public synchronized long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        return this.f27656fc.transferTo(j, j2, writableByteChannel);
    }
}
