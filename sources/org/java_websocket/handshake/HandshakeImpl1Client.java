package org.java_websocket.handshake;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
    private String resourcedescriptor;

    @Override // org.java_websocket.handshake.ClientHandshakeBuilder
    public void setResourceDescriptor(String str) throws IllegalArgumentException {
        this.resourcedescriptor = str;
    }

    @Override // org.java_websocket.handshake.ClientHandshake
    public String getResourceDescriptor() {
        return this.resourcedescriptor;
    }
}
