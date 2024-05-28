package szcom.coremedia.iso.boxes.sampleentry;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AbstractSampleEntry extends AbstractContainerBox implements SampleEntry {
    protected int dataReferenceIndex;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSampleEntry(String str) {
        super(str);
        this.dataReferenceIndex = 1;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public abstract void getBox(WritableByteChannel writableByteChannel);

    @Override // szcom.coremedia.iso.boxes.sampleentry.SampleEntry
    public int getDataReferenceIndex() {
        return this.dataReferenceIndex;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public abstract void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser);

    @Override // szcom.coremedia.iso.boxes.sampleentry.SampleEntry
    public void setDataReferenceIndex(int i) {
        this.dataReferenceIndex = i;
    }
}
