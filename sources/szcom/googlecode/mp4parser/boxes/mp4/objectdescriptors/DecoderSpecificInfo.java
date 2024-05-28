package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import java.util.Arrays;
import szcom.coremedia.iso.Hex;
import szcom.coremedia.iso.IsoTypeWriter;

@Descriptor(tags = {5})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DecoderSpecificInfo extends BaseDescriptor {
    byte[] bytes;

    public DecoderSpecificInfo() {
        this.tag = 5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.bytes, ((DecoderSpecificInfo) obj).bytes);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        return this.bytes.length;
    }

    public int hashCode() {
        byte[] bArr = this.bytes;
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        this.bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.bytes);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, this.tag);
        writeSize(allocate, getContentSize());
        allocate.put(this.bytes);
        return (ByteBuffer) allocate.rewind();
    }

    public void setData(byte[] bArr) {
        this.bytes = bArr;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderSpecificInfo");
        sb.append("{bytes=");
        byte[] bArr = this.bytes;
        sb.append(bArr == null ? "null" : Hex.encodeHex(bArr));
        sb.append('}');
        return sb.toString();
    }
}
