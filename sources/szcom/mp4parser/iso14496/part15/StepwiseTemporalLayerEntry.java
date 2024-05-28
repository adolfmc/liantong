package szcom.mp4parser.iso14496.part15;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class StepwiseTemporalLayerEntry extends GroupEntry {
    public static final String TYPE = "stsa";

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        return ByteBuffer.allocate(0);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        return 37;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
    }
}
