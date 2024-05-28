package szcom.coremedia.iso.boxes.mdat;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.DataSource;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class MediaDataBox implements Box {
    public static final String TYPE = "mdat";
    private DataSource dataSource;
    private long offset;
    Container parent;
    private long size;

    private static void transfer(DataSource dataSource, long j, long j2, WritableByteChannel writableByteChannel) {
        long j3 = 0;
        while (j3 < j2) {
            j3 += dataSource.transferTo(j + j3, Math.min(67076096L, j2 - j3), writableByteChannel);
        }
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        transfer(this.dataSource, this.offset, this.size, writableByteChannel);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getOffset() {
        return this.offset;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public Container getParent() {
        return this.parent;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getSize() {
        return this.size;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public String getType() {
        return TYPE;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        this.offset = dataSource.position() - byteBuffer.remaining();
        this.dataSource = dataSource;
        this.size = byteBuffer.remaining() + j;
        dataSource.position(dataSource.position() + j);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void setParent(Container container) {
        this.parent = container;
    }

    public String toString() {
        return "MediaDataBox{size=" + this.size + '}';
    }
}
