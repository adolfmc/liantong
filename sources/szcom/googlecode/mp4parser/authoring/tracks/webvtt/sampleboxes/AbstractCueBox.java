package szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.mp4parser.streaming.WriteOnlyBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractCueBox extends WriteOnlyBox {
    String content;

    public AbstractCueBox(String str) {
        super(str);
        this.content = "";
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(getSize()));
        IsoTypeWriter.writeUInt32(allocate, getSize());
        allocate.put(IsoFile.fourCCtoBytes(getType()));
        allocate.put(Utf8.convert(this.content));
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }

    public String getContent() {
        return this.content;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getSize() {
        return Utf8.utf8StringLengthInBytes(this.content) + 8;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
