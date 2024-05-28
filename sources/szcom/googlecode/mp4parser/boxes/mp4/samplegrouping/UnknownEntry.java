package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.Hex;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class UnknownEntry extends GroupEntry {
    private ByteBuffer content;
    private String type;

    public UnknownEntry(String str) {
        this.type = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnknownEntry unknownEntry = (UnknownEntry) obj;
        ByteBuffer byteBuffer = this.content;
        return byteBuffer == null ? unknownEntry.content == null : byteBuffer.equals(unknownEntry.content);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        return this.content.duplicate();
    }

    public ByteBuffer getContent() {
        return this.content;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        ByteBuffer byteBuffer = this.content;
        if (byteBuffer != null) {
            return byteBuffer.hashCode();
        }
        return 0;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
        this.content = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public void setContent(ByteBuffer byteBuffer) {
        this.content = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public String toString() {
        ByteBuffer duplicate = this.content.duplicate();
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        return "UnknownEntry{content=" + Hex.encodeHex(bArr) + '}';
    }
}
