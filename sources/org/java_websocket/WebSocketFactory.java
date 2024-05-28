package org.java_websocket;

import java.net.Socket;
import java.util.List;
import org.java_websocket.drafts.Draft;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface WebSocketFactory {
    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket);

    WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket);
}
