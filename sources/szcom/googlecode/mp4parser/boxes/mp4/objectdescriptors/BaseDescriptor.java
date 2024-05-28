package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;

@Descriptor(tags = {0})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class BaseDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    int sizeBytes;
    int sizeOfInstance;
    int tag;

    abstract int getContentSize();

    public int getSize() {
        return getContentSize() + getSizeSize() + 1;
    }

    public int getSizeSize() {
        int contentSize = getContentSize();
        int i = 0;
        while (true) {
            if (contentSize <= 0 && i >= this.sizeBytes) {
                return i;
            }
            contentSize >>>= 7;
            i++;
        }
    }

    public int getTag() {
        return this.tag;
    }

    public final void parse(int i, ByteBuffer byteBuffer) {
        this.tag = i;
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.sizeOfInstance = readUInt8 & 127;
        int i2 = 1;
        while ((readUInt8 >>> 7) == 1) {
            readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
            i2++;
            this.sizeOfInstance = (this.sizeOfInstance << 7) | (readUInt8 & 127);
        }
        this.sizeBytes = i2;
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(this.sizeOfInstance);
        parseDetail(slice);
        byteBuffer.position(byteBuffer.position() + this.sizeOfInstance);
    }

    public abstract void parseDetail(ByteBuffer byteBuffer);

    public abstract ByteBuffer serialize();

    public String toString() {
        return "BaseDescriptor{tag=" + this.tag + ", sizeOfInstance=" + this.sizeOfInstance + '}';
    }

    public void writeSize(ByteBuffer byteBuffer, int i) {
        int sizeSize;
        byte b;
        int position = byteBuffer.position();
        int i2 = 0;
        while (true) {
            if (i <= 0 && i2 >= this.sizeBytes) {
                byteBuffer.position(position + getSizeSize());
                return;
            }
            i2++;
            if (i > 0) {
                sizeSize = (getSizeSize() + position) - i2;
                b = (byte) (i & 127);
            } else {
                sizeSize = (getSizeSize() + position) - i2;
                b = Byte.MIN_VALUE;
            }
            byteBuffer.put(sizeSize, b);
            i >>>= 7;
        }
    }
}
