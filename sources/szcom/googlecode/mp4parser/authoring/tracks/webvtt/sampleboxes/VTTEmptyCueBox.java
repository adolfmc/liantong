package szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.mp4parser.streaming.WriteOnlyBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class VTTEmptyCueBox extends WriteOnlyBox {
    public VTTEmptyCueBox() {
        super("vtte");
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt32(allocate, getSize());
        allocate.put(IsoFile.fourCCtoBytes(getType()));
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getSize() {
        return 8L;
    }
}
