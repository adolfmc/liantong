package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;

@Descriptor(tags = {20})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ProfileLevelIndicationDescriptor extends BaseDescriptor {
    int profileLevelIndicationIndex;

    public ProfileLevelIndicationDescriptor() {
        this.tag = 20;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.profileLevelIndicationIndex == ((ProfileLevelIndicationDescriptor) obj).profileLevelIndicationIndex;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public int getContentSize() {
        return 1;
    }

    public int hashCode() {
        return this.profileLevelIndicationIndex;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        this.profileLevelIndicationIndex = IsoTypeReader.readUInt8(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, 20);
        writeSize(allocate, getContentSize());
        IsoTypeWriter.writeUInt8(allocate, this.profileLevelIndicationIndex);
        return allocate;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "ProfileLevelIndicationDescriptor{profileLevelIndicationIndex=" + Integer.toHexString(this.profileLevelIndicationIndex) + '}';
    }
}
