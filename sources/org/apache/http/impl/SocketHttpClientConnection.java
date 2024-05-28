package org.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.http.HttpInetConnection;
import org.apache.http.impl.p452io.SocketInputBuffer;
import org.apache.http.impl.p452io.SocketOutputBuffer;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean open;
    private Socket socket = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public void assertNotOpen() {
        if (this.open) {
            throw new IllegalStateException("Connection is already open");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.AbstractHttpClientConnection
    public void assertOpen() {
        if (!this.open) {
            throw new IllegalStateException("Connection is not open");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketInputBuffer(socket, i, httpParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketOutputBuffer(socket, i, httpParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bind(Socket socket, HttpParams httpParams) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.socket = socket;
        int socketBufferSize = HttpConnectionParams.getSocketBufferSize(httpParams);
        init(createSessionInputBuffer(socket, socketBufferSize, httpParams), createSessionOutputBuffer(socket, socketBufferSize, httpParams), httpParams);
        this.open = true;
    }

    @Override // org.apache.http.HttpConnection
    public boolean isOpen() {
        return this.open;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Socket getSocket() {
        return this.socket;
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        Socket socket = this.socket;
        if (socket != null) {
            return socket.getLocalAddress();
        }
        return null;
    }

    @Override // org.apache.http.HttpInetConnection
    public int getLocalPort() {
        Socket socket = this.socket;
        if (socket != null) {
            return socket.getLocalPort();
        }
        return -1;
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        Socket socket = this.socket;
        if (socket != null) {
            return socket.getInetAddress();
        }
        return null;
    }

    @Override // org.apache.http.HttpInetConnection
    public int getRemotePort() {
        Socket socket = this.socket;
        if (socket != null) {
            return socket.getPort();
        }
        return -1;
    }

    @Override // org.apache.http.HttpConnection
    public void setSocketTimeout(int i) {
        assertOpen();
        Socket socket = this.socket;
        if (socket != null) {
            try {
                socket.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    @Override // org.apache.http.HttpConnection
    public int getSocketTimeout() {
        Socket socket = this.socket;
        if (socket != null) {
            try {
                return socket.getSoTimeout();
            } catch (SocketException e) {
                return -1;
            }
        }
        return -1;
    }

    @Override // org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        this.open = false;
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    @Override // org.apache.http.HttpConnection
    public void close() throws IOException {
        if (!this.open) {
            return;
        }
        this.open = false;
        doFlush();
        try {
            try {
                this.socket.shutdownOutput();
            } catch (UnsupportedOperationException e) {
            }
        } catch (IOException e2) {
        }
        try {
            this.socket.shutdownInput();
        } catch (IOException e3) {
        }
        this.socket.close();
    }
}
