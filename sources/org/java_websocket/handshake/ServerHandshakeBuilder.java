package org.java_websocket.handshake;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ServerHandshakeBuilder extends HandshakeBuilder, ServerHandshake {
    void setHttpStatus(short s);

    void setHttpStatusMessage(String str);
}
