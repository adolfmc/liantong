package szcom.googlecode.mp4parser.authoring;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Sample {
    ByteBuffer asByteBuffer();

    long getSize();

    void writeTo(WritableByteChannel writableByteChannel);
}
