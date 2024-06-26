package io.socket.engineio.client;

import io.socket.emitter.Emitter;
import io.socket.engineio.parser.Packet;
import io.socket.engineio.parser.Parser;
import io.socket.thread.EventThread;
import io.socket.utf8.UTF8Exception;
import java.util.Map;
import okhttp3.Call;
import okhttp3.WebSocket;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class Transport extends Emitter {
    public static final String EVENT_CLOSE = "close";
    public static final String EVENT_DRAIN = "drain";
    public static final String EVENT_ERROR = "error";
    public static final String EVENT_OPEN = "open";
    public static final String EVENT_PACKET = "packet";
    public static final String EVENT_REQUEST_HEADERS = "requestHeaders";
    public static final String EVENT_RESPONSE_HEADERS = "responseHeaders";
    protected Call.Factory callFactory;
    protected String hostname;
    public String name;
    protected String path;
    protected int port;
    public Map<String, String> query;
    public ReadyState readyState;
    protected boolean secure;
    protected Socket socket;
    protected String timestampParam;
    protected boolean timestampRequests;
    protected WebSocket.Factory webSocketFactory;
    public boolean writable;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Options {
        public Call.Factory callFactory;
        public String hostname;
        public String path;
        public Map<String, String> query;
        public boolean secure;
        protected Socket socket;
        public String timestampParam;
        public boolean timestampRequests;
        public WebSocket.Factory webSocketFactory;
        public int port = -1;
        public int policyPort = -1;
    }

    protected abstract void doClose();

    protected abstract void doOpen();

    protected abstract void write(Packet[] packetArr) throws UTF8Exception;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum ReadyState {
        OPENING,
        OPEN,
        CLOSED,
        PAUSED;

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public Transport(Options options) {
        this.path = options.path;
        this.hostname = options.hostname;
        this.port = options.port;
        this.secure = options.secure;
        this.query = options.query;
        this.timestampParam = options.timestampParam;
        this.timestampRequests = options.timestampRequests;
        this.socket = options.socket;
        this.webSocketFactory = options.webSocketFactory;
        this.callFactory = options.callFactory;
    }

    public Transport onError(String str, Exception exc) {
        emit("error", new EngineIOException(str, exc));
        return this;
    }

    public Transport open() {
        EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.Transport.1
            @Override // java.lang.Runnable
            public void run() {
                if (Transport.this.readyState == ReadyState.CLOSED || Transport.this.readyState == null) {
                    Transport.this.readyState = ReadyState.OPENING;
                    Transport.this.doOpen();
                }
            }
        });
        return this;
    }

    public Transport close() {
        EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.Transport.2
            @Override // java.lang.Runnable
            public void run() {
                if (Transport.this.readyState == ReadyState.OPENING || Transport.this.readyState == ReadyState.OPEN) {
                    Transport.this.doClose();
                    Transport.this.onClose();
                }
            }
        });
        return this;
    }

    public void send(final Packet[] packetArr) {
        EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.Transport.3
            @Override // java.lang.Runnable
            public void run() {
                if (Transport.this.readyState == ReadyState.OPEN) {
                    try {
                        Transport.this.write(packetArr);
                        return;
                    } catch (UTF8Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                throw new RuntimeException("Transport not open");
            }
        });
    }

    public void onOpen() {
        this.readyState = ReadyState.OPEN;
        this.writable = true;
        emit("open", new Object[0]);
    }

    public void onData(String str) {
        onPacket(Parser.decodePacket(str));
    }

    public void onData(byte[] bArr) {
        onPacket(Parser.decodePacket(bArr));
    }

    public void onPacket(Packet packet) {
        emit("packet", packet);
    }

    public void onClose() {
        this.readyState = ReadyState.CLOSED;
        emit("close", new Object[0]);
    }
}
