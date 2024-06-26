package org.java_websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface WebSocket {
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_WSS_PORT = 443;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum READYSTATE {
        NOT_YET_CONNECTED,
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum Role {
        CLIENT,
        SERVER
    }

    void close();

    void close(int i);

    void close(int i, String str);

    void closeConnection(int i, String str);

    Draft getDraft();

    InetSocketAddress getLocalSocketAddress();

    READYSTATE getReadyState();

    InetSocketAddress getRemoteSocketAddress();

    boolean hasBufferedData();

    boolean isClosed();

    boolean isClosing();

    boolean isConnecting();

    boolean isFlushAndClose();

    boolean isOpen();

    void send(String str) throws NotYetConnectedException;

    void send(ByteBuffer byteBuffer) throws IllegalArgumentException, NotYetConnectedException;

    void send(byte[] bArr) throws IllegalArgumentException, NotYetConnectedException;

    void sendFrame(Framedata framedata);
}
