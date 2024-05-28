package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FreeBox implements Box {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TYPE = "free";
    ByteBuffer data;
    private long offset;
    private Container parent;
    List<Box> replacers;

    public FreeBox() {
        this.replacers = new LinkedList();
        this.data = ByteBuffer.wrap(new byte[0]);
    }

    public FreeBox(int i) {
        this.replacers = new LinkedList();
        this.data = ByteBuffer.allocate(i);
    }

    public void addAndReplace(Box box) {
        this.data.position(CastUtils.l2i(box.getSize()));
        this.data = this.data.slice();
        this.replacers.add(box);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FreeBox freeBox = (FreeBox) obj;
        return getData() == null ? freeBox.getData() == null : getData().equals(freeBox.getData());
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        for (Box box : this.replacers) {
            box.getBox(writableByteChannel);
        }
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt32(allocate, this.data.limit() + 8);
        allocate.put(TYPE.getBytes());
        allocate.rewind();
        writableByteChannel.write(allocate);
        allocate.rewind();
        this.data.rewind();
        writableByteChannel.write(this.data);
        this.data.rewind();
    }

    public ByteBuffer getData() {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            return (ByteBuffer) byteBuffer.duplicate().rewind();
        }
        return null;
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
        long j = 8;
        for (Box box : this.replacers) {
            j += box.getSize();
        }
        return j + this.data.limit();
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            return byteBuffer.hashCode();
        }
        return 0;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        this.offset = dataSource.position() - byteBuffer.remaining();
        if (j > 1048576) {
            this.data = dataSource.map(dataSource.position(), j);
            dataSource.position(dataSource.position() + j);
            return;
        }
        this.data = ByteBuffer.allocate(CastUtils.l2i(j));
        dataSource.read(this.data);
    }

    public void setData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void setParent(Container container) {
        this.parent = container;
    }
}
