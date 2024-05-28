package szcom.googlecode.mp4parser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MultiFileDataSourceImpl implements DataSource {
    FileChannel[] fcs;
    int index = 0;

    public MultiFileDataSourceImpl(File... fileArr) {
        this.fcs = new FileChannel[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            this.fcs[i] = new FileInputStream(fileArr[i]).getChannel();
        }
    }

    @Override // szcom.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (FileChannel fileChannel : this.fcs) {
            fileChannel.close();
        }
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j, long j2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(CastUtils.l2i(j2));
        transferTo(j, j2, Channels.newChannel(byteArrayOutputStream));
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long position() {
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = this.index;
            if (i >= i2) {
                return j + this.fcs[i2].position();
            }
            j += this.fcs[i].size();
            i++;
        }
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public void position(long j) {
        int i = 0;
        while (true) {
            FileChannel[] fileChannelArr = this.fcs;
            if (i >= fileChannelArr.length) {
                return;
            }
            if (j - fileChannelArr[i].size() < 0) {
                this.fcs[i].position(j);
                this.index = i;
                return;
            }
            j -= this.fcs[i].size();
            i++;
        }
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        int read = this.fcs[this.index].read(byteBuffer);
        if (read != remaining) {
            this.index++;
            return read + read(byteBuffer);
        }
        return read;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long size() {
        long j = 0;
        for (FileChannel fileChannel : this.fcs) {
            j += fileChannel.size();
        }
        return j;
    }

    @Override // szcom.googlecode.mp4parser.DataSource
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        FileChannel[] fileChannelArr;
        if (j2 == 0) {
            return 0L;
        }
        long j3 = 0;
        for (FileChannel fileChannel : this.fcs) {
            long size = fileChannel.size();
            if (j >= j3 && j < j3 + size && j + j2 > j3) {
                long j4 = j - j3;
                long min = Math.min(j2, size - j4);
                fileChannel.transferTo(j4, min, writableByteChannel);
                return min + transferTo(j + min, j2 - min, writableByteChannel);
            }
            j3 += size;
        }
        return 0L;
    }
}
