package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.Socket;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.impl.SocketHttpClientConnection;
import org.apache.http.p453io.HttpMessageParser;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultClientConnection extends SocketHttpClientConnection implements OperatedClientConnection {
    private boolean connSecure;
    private volatile boolean shutdown;
    private volatile Socket socket;
    private HttpHost targetHost;
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    private final InterfaceC13042Log headerLog = LogFactory.getLog("org.apache.http.headers");
    private final InterfaceC13042Log wireLog = LogFactory.getLog("org.apache.http.wire");

    @Override // org.apache.http.conn.OperatedClientConnection
    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    @Override // org.apache.http.conn.OperatedClientConnection
    public final boolean isSecure() {
        return this.connSecure;
    }

    @Override // org.apache.http.impl.SocketHttpClientConnection, org.apache.http.conn.OperatedClientConnection
    public final Socket getSocket() {
        return this.socket;
    }

    @Override // org.apache.http.conn.OperatedClientConnection
    public void opening(Socket socket, HttpHost httpHost) throws IOException {
        assertNotOpen();
        this.socket = socket;
        this.targetHost = httpHost;
        if (this.shutdown) {
            socket.close();
            throw new IOException("Connection already shutdown");
        }
    }

    @Override // org.apache.http.conn.OperatedClientConnection
    public void openCompleted(boolean z, HttpParams httpParams) throws IOException {
        assertNotOpen();
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        this.connSecure = z;
        bind(this.socket, httpParams);
    }

    @Override // org.apache.http.impl.SocketHttpClientConnection, org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        this.log.debug("Connection shut down");
        this.shutdown = true;
        super.shutdown();
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    @Override // org.apache.http.impl.SocketHttpClientConnection, org.apache.http.HttpConnection
    public void close() throws IOException {
        this.log.debug("Connection closed");
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.SocketHttpClientConnection
    public SessionInputBuffer createSessionInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        SessionInputBuffer createSessionInputBuffer = super.createSessionInputBuffer(socket, i, httpParams);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionInputBuffer(createSessionInputBuffer, new Wire(this.wireLog));
        }
        return createSessionInputBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.SocketHttpClientConnection
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        SessionOutputBuffer createSessionOutputBuffer = super.createSessionOutputBuffer(socket, i, httpParams);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionOutputBuffer(createSessionOutputBuffer, new Wire(this.wireLog));
        }
        return createSessionOutputBuffer;
    }

    @Override // org.apache.http.impl.AbstractHttpClientConnection
    protected HttpMessageParser createResponseParser(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    @Override // org.apache.http.conn.OperatedClientConnection
    public void update(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        assertOpen();
        if (httpHost == null) {
            throw new IllegalArgumentException("Target host must not be null.");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        if (socket != null) {
            this.socket = socket;
            bind(socket, httpParams);
        }
        this.targetHost = httpHost;
        this.connSecure = z;
    }

    @Override // org.apache.http.impl.AbstractHttpClientConnection, org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        HttpResponse receiveResponseHeader = super.receiveResponseHeader();
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug("<< " + receiveResponseHeader.getStatusLine().toString());
            Header[] allHeaders = receiveResponseHeader.getAllHeaders();
            int length = allHeaders.length;
            for (int i = 0; i < length; i++) {
                this.headerLog.debug("<< " + allHeaders[i].toString());
            }
        }
        return receiveResponseHeader;
    }

    @Override // org.apache.http.impl.AbstractHttpClientConnection, org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        super.sendRequestHeader(httpRequest);
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug(">> " + httpRequest.getRequestLine().toString());
            Header[] allHeaders = httpRequest.getAllHeaders();
            int length = allHeaders.length;
            for (int i = 0; i < length; i++) {
                this.headerLog.debug(">> " + allHeaders[i].toString());
            }
        }
    }
}
