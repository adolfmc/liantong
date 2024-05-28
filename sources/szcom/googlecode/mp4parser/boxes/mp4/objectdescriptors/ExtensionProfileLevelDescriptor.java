package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.Hex;

@Descriptor(tags = {19})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ExtensionProfileLevelDescriptor extends BaseDescriptor {
    byte[] bytes;

    public ExtensionProfileLevelDescriptor() {
        this.tag = 19;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        if (getSize() > 0) {
            this.bytes = new byte[getSize()];
            byteBuffer.get(this.bytes);
        }
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExtensionDescriptor");
        sb.append("{bytes=");
        byte[] bArr = this.bytes;
        sb.append(bArr == null ? "null" : Hex.encodeHex(bArr));
        sb.append('}');
        return sb.toString();
    }
}
