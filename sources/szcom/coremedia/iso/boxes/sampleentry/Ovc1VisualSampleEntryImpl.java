package szcom.coremedia.iso.boxes.sampleentry;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class Ovc1VisualSampleEntryImpl extends AbstractSampleEntry {
    public static final String TYPE = "ovc1";
    private byte[] vc1Content;

    public Ovc1VisualSampleEntryImpl() {
        super(TYPE);
        this.vc1Content = new byte[0];
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.position(6);
        IsoTypeWriter.writeUInt16(allocate, this.dataReferenceIndex);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writableByteChannel.write(ByteBuffer.wrap(this.vc1Content));
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        int i = 16;
        if (!this.largeBox && this.vc1Content.length + 16 < 4294967296L) {
            i = 8;
        }
        return i + this.vc1Content.length + 8;
    }

    public byte[] getVc1Content() {
        return this.vc1Content;
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(j));
        dataSource.read(allocate);
        allocate.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(allocate);
        this.vc1Content = new byte[allocate.remaining()];
        allocate.get(this.vc1Content);
    }

    public void setVc1Content(byte[] bArr) {
        this.vc1Content = bArr;
    }
}
