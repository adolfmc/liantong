package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.googlecode.mp4parser.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Box {
    void getBox(WritableByteChannel writableByteChannel);

    long getOffset();

    Container getParent();

    long getSize();

    String getType();

    void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser);

    void setParent(Container container);
}
