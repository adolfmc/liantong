package org.java_websocket.handshake;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder {
    private short httpstatus;
    private String httpstatusmessage;

    @Override // org.java_websocket.handshake.ServerHandshake
    public String getHttpStatusMessage() {
        return this.httpstatusmessage;
    }

    @Override // org.java_websocket.handshake.ServerHandshake
    public short getHttpStatus() {
        return this.httpstatus;
    }

    @Override // org.java_websocket.handshake.ServerHandshakeBuilder
    public void setHttpStatusMessage(String str) {
        this.httpstatusmessage = str;
    }

    @Override // org.java_websocket.handshake.ServerHandshakeBuilder
    public void setHttpStatus(short s) {
        this.httpstatus = s;
    }
}
