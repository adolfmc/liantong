package szcom.mp4parser.streaming;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class WriteOnlyBox implements Box {
    private Container parent;
    private final String type;

    public WriteOnlyBox(String str) {
        this.type = str;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getOffset() {
        throw new RuntimeException("It's a´write only box");
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public Container getParent() {
        return this.parent;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public String getType() {
        return this.type;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        throw new RuntimeException("It's a´write only box");
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public void setParent(Container container) {
        this.parent = container;
    }
}
