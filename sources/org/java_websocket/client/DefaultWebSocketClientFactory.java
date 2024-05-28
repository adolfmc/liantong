package org.java_websocket.client;

import java.net.Socket;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DefaultWebSocketClientFactory implements WebSocketClient.WebSocketClientFactory {
    private final WebSocketClient webSocketClient;

    @Override // org.java_websocket.client.WebSocketClient.WebSocketClientFactory
    public ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) {
        return selectionKey == null ? socketChannel : socketChannel;
    }

    public DefaultWebSocketClientFactory(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @Override // org.java_websocket.WebSocketFactory
    public WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return new WebSocketImpl(this.webSocketClient, draft);
    }

    @Override // org.java_websocket.WebSocketFactory
    public WebSocket createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket) {
        return new WebSocketImpl(this.webSocketClient, list);
    }
}
