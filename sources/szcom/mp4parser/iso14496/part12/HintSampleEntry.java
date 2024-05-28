package szcom.mp4parser.iso14496.part12;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class HintSampleEntry extends AbstractSampleEntry {
    protected byte[] data;

    public HintSampleEntry(String str) {
        super(str);
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.position(6);
        IsoTypeWriter.writeUInt16(allocate, this.dataReferenceIndex);
        allocate.rewind();
        writableByteChannel.write(allocate);
        writableByteChannel.write(ByteBuffer.wrap(this.data));
    }

    public byte[] getData() {
        return this.data;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        int i = 8;
        long length = this.data.length + 8;
        return length + ((this.largeBox || 8 + length >= 4294967296L) ? 16 : 16);
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        dataSource.read(allocate);
        allocate.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(allocate);
        this.data = new byte[CastUtils.l2i(j - 8)];
        dataSource.read(ByteBuffer.wrap(this.data));
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }
}
