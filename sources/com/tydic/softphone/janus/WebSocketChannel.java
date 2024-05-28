package com.tydic.softphone.janus;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WebSocketChannel {
    private static final String TAG = "WebSocketChannel";
    private boolean connected;
    private WebSocket webSocket;
    private WebSocketCallback webSocketCallback;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface WebSocketCallback {
        void onClosed();

        void onFailure();

        void onMessage(String str);

        void onOpen();
    }

    public void connect(String str) {
        this.webSocket = NBSOkHttp3Instrumentation.init().newWebSocket(new Request.Builder().header("Sec-WebSocket-Protocol", "janus-protocol").url(str).build(), new WebSocketHandler());
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void sendMessage(String str) {
        if (this.webSocket != null && this.connected) {
            String str2 = TAG;
            Log.d(str2, "send==>>" + str);
            this.webSocket.send(str);
            return;
        }
        Log.e(TAG, "send failed socket not connected");
    }

    public void close() {
        WebSocket webSocket = this.webSocket;
        if (webSocket != null) {
            webSocket.close(1000, "manual close");
            this.webSocket = null;
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class WebSocketHandler extends WebSocketListener {
        private WebSocketHandler() {
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            WebSocketChannel.this.connected = true;
            Log.d(WebSocketChannel.TAG, "onOpen");
            if (WebSocketChannel.this.webSocketCallback != null) {
                WebSocketChannel.this.webSocketCallback.onOpen();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            String str2 = WebSocketChannel.TAG;
            Log.d(str2, "onMessage " + webSocket.request().url().toString() + " " + str);
            if (WebSocketChannel.this.webSocketCallback != null) {
                WebSocketChannel.this.webSocketCallback.onMessage(str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            String str2 = WebSocketChannel.TAG;
            Log.d(str2, "onClosed " + str);
            WebSocketChannel.this.connected = false;
            if (WebSocketChannel.this.webSocketCallback != null) {
                WebSocketChannel.this.webSocketCallback.onClosed();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            String str = WebSocketChannel.TAG;
            Log.d(str, "onFailure " + th.getMessage());
            WebSocketChannel.this.connected = false;
            if (WebSocketChannel.this.webSocketCallback != null) {
                WebSocketChannel.this.webSocketCallback.onClosed();
            }
            WebSocketChannel.this.webSocketCallback.onFailure();
        }
    }

    public void setWebSocketCallback(WebSocketCallback webSocketCallback) {
        this.webSocketCallback = webSocketCallback;
    }
}
