package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.MemoryDataSourceImpl;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MetaBox extends AbstractContainerBox {
    public static final String TYPE = "meta";
    private int flags;
    private boolean isFullBox;
    private int version;

    public MetaBox() {
        super("meta");
        this.isFullBox = true;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        if (this.isFullBox) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            writeVersionAndFlags(allocate);
            writableByteChannel.write((ByteBuffer) allocate.rewind());
        }
        writeContainer(writableByteChannel);
    }

    public int getFlags() {
        return this.flags;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        long containerSize = getContainerSize() + (this.isFullBox ? 4L : 0L);
        return containerSize + ((this.largeBox || containerSize >= 4294967296L) ? 16 : 8);
    }

    public int getVersion() {
        return this.version;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        MemoryDataSourceImpl memoryDataSourceImpl;
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(j));
        dataSource.read(allocate);
        allocate.position(4);
        if (HandlerBox.TYPE.equals(IsoTypeReader.read4cc(allocate))) {
            this.isFullBox = false;
            memoryDataSourceImpl = new MemoryDataSourceImpl((ByteBuffer) allocate.rewind());
        } else {
            this.isFullBox = true;
            parseVersionAndFlags((ByteBuffer) allocate.rewind());
            memoryDataSourceImpl = new MemoryDataSourceImpl(allocate);
            j -= 4;
        }
        initContainer(memoryDataSourceImpl, j, boxParser);
    }

    protected final long parseVersionAndFlags(ByteBuffer byteBuffer) {
        this.version = IsoTypeReader.readUInt8(byteBuffer);
        this.flags = IsoTypeReader.readUInt24(byteBuffer);
        return 4L;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    protected final void writeVersionAndFlags(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt8(byteBuffer, this.version);
        IsoTypeWriter.writeUInt24(byteBuffer, this.flags);
    }
}
