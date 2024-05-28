package szcom.mp4parser.streaming;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface StreamingSample {
    ByteBuffer getContent();

    long getDuration();

    SampleExtension[] getExtensions();
}
