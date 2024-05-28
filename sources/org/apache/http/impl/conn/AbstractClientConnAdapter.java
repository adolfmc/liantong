package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractClientConnAdapter implements ManagedClientConnection {
    private volatile ClientConnectionManager connManager;
    private volatile OperatedClientConnection wrappedConnection;
    private final Thread executionThread = Thread.currentThread();
    private volatile boolean markedReusable = false;
    private volatile boolean aborted = false;
    private volatile long duration = Long.MAX_VALUE;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractClientConnAdapter(ClientConnectionManager clientConnectionManager, OperatedClientConnection operatedClientConnection) {
        this.connManager = clientConnectionManager;
        this.wrappedConnection = operatedClientConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void detach() {
        this.wrappedConnection = null;
        this.connManager = null;
        this.duration = Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperatedClientConnection getWrappedConnection() {
        return this.wrappedConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientConnectionManager getManager() {
        return this.connManager;
    }

    protected final void assertNotAborted() throws InterruptedIOException {
        if (this.aborted) {
            throw new InterruptedIOException("Connection has been shut down.");
        }
    }

    protected final void assertValid(OperatedClientConnection operatedClientConnection) {
        if (operatedClientConnection == null) {
            throw new IllegalStateException("No wrapped connection.");
        }
    }

    @Override // org.apache.http.HttpConnection
    public boolean isOpen() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection == null) {
            return false;
        }
        return wrappedConnection.isOpen();
    }

    @Override // org.apache.http.HttpConnection
    public boolean isStale() {
        OperatedClientConnection wrappedConnection;
        if (this.aborted || (wrappedConnection = getWrappedConnection()) == null) {
            return true;
        }
        return wrappedConnection.isStale();
    }

    @Override // org.apache.http.HttpConnection
    public void setSocketTimeout(int i) {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        wrappedConnection.setSocketTimeout(i);
    }

    @Override // org.apache.http.HttpConnection
    public int getSocketTimeout() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getSocketTimeout();
    }

    @Override // org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getMetrics();
    }

    @Override // org.apache.http.HttpClientConnection
    public void flush() throws IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        wrappedConnection.flush();
    }

    @Override // org.apache.http.HttpClientConnection
    public boolean isResponseAvailable(int i) throws IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.isResponseAvailable(i);
    }

    @Override // org.apache.http.HttpClientConnection
    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.receiveResponseEntity(httpResponse);
    }

    @Override // org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        return wrappedConnection.receiveResponseHeader();
    }

    @Override // org.apache.http.HttpClientConnection
    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.sendRequestEntity(httpEntityEnclosingRequest);
    }

    @Override // org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        assertNotAborted();
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.sendRequestHeader(httpRequest);
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getLocalAddress();
    }

    @Override // org.apache.http.HttpInetConnection
    public int getLocalPort() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getLocalPort();
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getRemoteAddress();
    }

    @Override // org.apache.http.HttpInetConnection
    public int getRemotePort() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getRemotePort();
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public boolean isSecure() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.isSecure();
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public SSLSession getSSLSession() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (isOpen()) {
            Socket socket = wrappedConnection.getSocket();
            if (socket instanceof SSLSocket) {
                return ((SSLSocket) socket).getSession();
            }
            return null;
        }
        return null;
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void markReusable() {
        this.markedReusable = true;
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void unmarkReusable() {
        this.markedReusable = false;
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public boolean isMarkedReusable() {
        return this.markedReusable;
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void setIdleDuration(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.duration = timeUnit.toMillis(j);
        } else {
            this.duration = -1L;
        }
    }

    @Override // org.apache.http.conn.ConnectionReleaseTrigger
    public void releaseConnection() {
        if (this.connManager != null) {
            this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
        }
    }

    @Override // org.apache.http.conn.ConnectionReleaseTrigger
    public void abortConnection() {
        if (this.aborted) {
            return;
        }
        this.aborted = true;
        unmarkReusable();
        try {
            shutdown();
        } catch (IOException e) {
        }
        if (this.executionThread.equals(Thread.currentThread())) {
            releaseConnection();
        }
    }
}
