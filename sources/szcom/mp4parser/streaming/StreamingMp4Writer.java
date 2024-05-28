package szcom.mp4parser.streaming;

import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface StreamingMp4Writer extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void write();
}
