package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface FrameBuilder extends Framedata {
    void setFin(boolean z);

    void setOptcode(Framedata.Opcode opcode);

    void setPayload(ByteBuffer byteBuffer) throws InvalidDataException;

    void setTransferemasked(boolean z);
}
