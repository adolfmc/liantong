package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.DataSource;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ItemProtectionBox extends AbstractContainerBox implements FullBox {
    public static final String TYPE = "ipro";
    private int flags;
    private int version;

    public ItemProtectionBox() {
        super(TYPE);
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        ByteBuffer allocate = ByteBuffer.allocate(6);
        IsoTypeWriter.writeUInt8(allocate, this.version);
        IsoTypeWriter.writeUInt24(allocate, this.flags);
        IsoTypeWriter.writeUInt16(allocate, getBoxes().size());
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writeContainer(writableByteChannel);
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public int getFlags() {
        return this.flags;
    }

    public SchemeInformationBox getItemProtectionScheme() {
        if (getBoxes(SchemeInformationBox.class).isEmpty()) {
            return null;
        }
        return (SchemeInformationBox) getBoxes(SchemeInformationBox.class).get(0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        long containerSize = getContainerSize() + 6;
        return containerSize + ((this.largeBox || containerSize >= 4294967296L) ? 16 : 8);
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public int getVersion() {
        return this.version;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        dataSource.read(allocate);
        allocate.rewind();
        this.version = IsoTypeReader.readUInt8(allocate);
        this.flags = IsoTypeReader.readUInt24(allocate);
        initContainer(dataSource, j - 6, boxParser);
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public void setFlags(int i) {
        this.flags = i;
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public void setVersion(int i) {
        this.version = i;
    }
}