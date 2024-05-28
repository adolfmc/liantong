package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class GroupEntry {
    public abstract ByteBuffer get();

    public abstract String getType();

    public abstract void parse(ByteBuffer byteBuffer);

    public int size() {
        return get().limit();
    }
}
