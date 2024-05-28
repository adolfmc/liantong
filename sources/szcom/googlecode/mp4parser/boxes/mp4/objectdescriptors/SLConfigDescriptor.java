package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;

@Descriptor(tags = {6})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SLConfigDescriptor extends BaseDescriptor {
    int predefined;

    public SLConfigDescriptor() {
        this.tag = 6;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.predefined == ((SLConfigDescriptor) obj).predefined;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        return 1;
    }

    public int getPredefined() {
        return this.predefined;
    }

    public int hashCode() {
        return this.predefined;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        this.predefined = IsoTypeReader.readUInt8(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, 6);
        writeSize(allocate, getContentSize());
        IsoTypeWriter.writeUInt8(allocate, this.predefined);
        return allocate;
    }

    public void setPredefined(int i) {
        this.predefined = i;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "SLConfigDescriptor{predefined=" + this.predefined + '}';
    }
}
