package org.java_websocket.drafts;

import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Draft_17 extends Draft_10 {
    @Override // org.java_websocket.drafts.Draft_10, org.java_websocket.drafts.Draft
    public Draft.HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException {
        if (readVersion(clientHandshake) == 13) {
            return Draft.HandshakeState.MATCHED;
        }
        return Draft.HandshakeState.NOT_MATCHED;
    }

    @Override // org.java_websocket.drafts.Draft_10, org.java_websocket.drafts.Draft
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) {
        super.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        clientHandshakeBuilder.put("Sec-WebSocket-Version", "13");
        return clientHandshakeBuilder;
    }

    @Override // org.java_websocket.drafts.Draft_10, org.java_websocket.drafts.Draft
    public Draft copyInstance() {
        return new Draft_17();
    }
}
