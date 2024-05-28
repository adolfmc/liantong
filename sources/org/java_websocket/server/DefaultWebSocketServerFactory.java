package org.java_websocket.server;

import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.server.WebSocketServer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DefaultWebSocketServerFactory implements WebSocketServer.WebSocketServerFactory {
    @Override // org.java_websocket.server.WebSocketServer.WebSocketServerFactory
    public SocketChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) {
        return socketChannel;
    }

    @Override // org.java_websocket.WebSocketFactory
    public /* bridge */ /* synthetic */ WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List list, Socket socket) {
        return createWebSocket(webSocketAdapter, (List<Draft>) list, socket);
    }

    @Override // org.java_websocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return new WebSocketImpl(webSocketAdapter, draft);
    }

    @Override // org.java_websocket.server.WebSocketServer.WebSocketServerFactory, org.java_websocket.WebSocketFactory
    public WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket) {
        return new WebSocketImpl(webSocketAdapter, list);
    }
}
