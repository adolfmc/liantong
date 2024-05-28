package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RollRecoveryEntry extends GroupEntry {
    public static final String TYPE = "roll";
    private short rollDistance;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.rollDistance == ((RollRecoveryEntry) obj).rollDistance;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort(this.rollDistance);
        allocate.rewind();
        return allocate;
    }

    public short getRollDistance() {
        return this.rollDistance;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        return this.rollDistance;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
        this.rollDistance = byteBuffer.getShort();
    }

    public void setRollDistance(short s) {
        this.rollDistance = s;
    }
}
