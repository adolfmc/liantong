package szcom.googlecode.mp4parser;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AbstractContainerBox extends BasicContainer implements Box {
    protected boolean largeBox;
    private long offset;
    Container parent;
    protected String type;

    public AbstractContainerBox(String str) {
        this.type = str;
    }

    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        writeContainer(writableByteChannel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer getHeader() {
        ByteBuffer wrap;
        if (this.largeBox || getSize() >= 4294967296L) {
            byte[] bArr = new byte[16];
            bArr[3] = 1;
            bArr[4] = this.type.getBytes()[0];
            bArr[5] = this.type.getBytes()[1];
            bArr[6] = this.type.getBytes()[2];
            bArr[7] = this.type.getBytes()[3];
            wrap = ByteBuffer.wrap(bArr);
            wrap.position(8);
            IsoTypeWriter.writeUInt64(wrap, getSize());
        } else {
            wrap = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, this.type.getBytes()[0], this.type.getBytes()[1], this.type.getBytes()[2], this.type.getBytes()[3]});
            IsoTypeWriter.writeUInt32(wrap, getSize());
        }
        wrap.rewind();
        return wrap;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getOffset() {
        return this.offset;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public Container getParent() {
        return this.parent;
    }

    public long getSize() {
        long containerSize = getContainerSize();
        return containerSize + ((this.largeBox || 8 + containerSize >= 4294967296L) ? 16 : 8);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public String getType() {
        return this.type;
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer
    public void initContainer(DataSource dataSource, long j, BoxParser boxParser) {
        this.dataSource = dataSource;
        this.parsePosition = dataSource.position();
        this.startPosition = this.parsePosition - ((this.largeBox || 8 + j >= 4294967296L) ? 16 : 8);
        dataSource.position(dataSource.position() + j);
        this.endPosition = dataSource.position();
        this.boxParser = boxParser;
    }

    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        this.offset = dataSource.position() - byteBuffer.remaining();
        this.largeBox = byteBuffer.remaining() == 16;
        initContainer(dataSource, j, boxParser);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void setParent(Container container) {
        this.parent = container;
    }
}
