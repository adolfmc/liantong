package android.net.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.p452io.ChunkedInputStream;
import org.apache.http.impl.p452io.ContentLengthInputStream;
import org.apache.http.impl.p452io.HttpRequestWriter;
import org.apache.http.impl.p452io.IdentityInputStream;
import org.apache.http.impl.p452io.SocketInputBuffer;
import org.apache.http.impl.p452io.SocketOutputBuffer;
import org.apache.http.p453io.HttpMessageWriter;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class AndroidHttpClientConnection implements HttpInetConnection, org.apache.http.HttpConnection {
    private int maxHeaderCount;
    private int maxLineLength;
    private volatile boolean open;
    private SessionInputBuffer inbuffer = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageWriter requestWriter = null;
    private HttpConnectionMetricsImpl metrics = null;
    private Socket socket = null;
    private final EntitySerializer entityserializer = new EntitySerializer(new StrictContentLengthStrategy());

    public void bind(Socket socket, HttpParams httpParams) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        assertNotOpen();
        socket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(httpParams));
        socket.setSoTimeout(HttpConnectionParams.getSoTimeout(httpParams));
        int linger = HttpConnectionParams.getLinger(httpParams);
        if (linger >= 0) {
            socket.setSoLinger(linger > 0, linger);
        }
        this.socket = socket;
        int socketBufferSize = HttpConnectionParams.getSocketBufferSize(httpParams);
        this.inbuffer = new SocketInputBuffer(socket, socketBufferSize, httpParams);
        this.outbuffer = new SocketOutputBuffer(socket, socketBufferSize, httpParams);
        this.maxHeaderCount = httpParams.getIntParameter("http.connection.max-header-count", -1);
        this.maxLineLength = httpParams.getIntParameter("http.connection.max-line-length", -1);
        this.requestWriter = new HttpRequestWriter(this.outbuffer, null, httpParams);
        this.metrics = new HttpConnectionMetricsImpl(this.inbuffer.getMetrics(), this.outbuffer.getMetrics());
        this.open = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("[");
        if (isOpen()) {
            sb.append(getRemotePort());
        } else {
            sb.append("closed");
        }
        sb.append("]");
        return sb.toString();
    }

    private void assertNotOpen() {
        if (this.open) {
            throw new IllegalStateException("Connection is already open");
        }
    }

    private void assertOpen() {
        if (!this.open) {
            throw new IllegalStateException("Connection is not open");
        }
    }

    @Override // org.apache.http.HttpConnection
    public boolean isOpen() {
        Socket socket;
        return this.open && (socket = this.socket) != null && socket.isConnected();
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

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        assertOpen();
        this.requestWriter.write(httpRequest);
        this.metrics.incrementRequestCount();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        if (httpEntityEnclosingRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        assertOpen();
        if (httpEntityEnclosingRequest.getEntity() == null) {
            return;
        }
        this.entityserializer.serialize(this.outbuffer, httpEntityEnclosingRequest, httpEntityEnclosingRequest.getEntity());
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b4, code lost:
        if (r9 == null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b6, code lost:
        r17.parseHeader(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bb, code lost:
        if (r6 < 200) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00bd, code lost:
        r16.metrics.incrementResponseCount();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c2, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.http.StatusLine parseResponseHeader(android.net.http.Headers r17) throws java.io.IOException, org.apache.http.ParseException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r16.assertOpen()
            org.apache.http.util.CharArrayBuffer r2 = new org.apache.http.util.CharArrayBuffer
            r3 = 64
            r2.<init>(r3)
            org.apache.http.io.SessionInputBuffer r4 = r0.inbuffer
            int r4 = r4.readLine(r2)
            r5 = -1
            if (r4 == r5) goto Lc3
            org.apache.http.message.BasicLineParser r4 = org.apache.http.message.BasicLineParser.DEFAULT
            org.apache.http.message.ParserCursor r6 = new org.apache.http.message.ParserCursor
            int r7 = r2.length()
            r8 = 0
            r6.<init>(r8, r7)
            org.apache.http.StatusLine r4 = r4.parseStatusLine(r2, r6)
            int r6 = r4.getStatusCode()
            r7 = 0
            r9 = r7
            r10 = r8
        L2f:
            if (r2 != 0) goto L37
            org.apache.http.util.CharArrayBuffer r2 = new org.apache.http.util.CharArrayBuffer
            r2.<init>(r3)
            goto L3a
        L37:
            r2.clear()
        L3a:
            org.apache.http.io.SessionInputBuffer r11 = r0.inbuffer
            int r11 = r11.readLine(r2)
            if (r11 == r5) goto Lb4
            int r11 = r2.length()
            r12 = 1
            if (r11 >= r12) goto L4b
            goto Lb4
        L4b:
            char r11 = r2.charAt(r8)
            r13 = 9
            r14 = 32
            if (r11 == r14) goto L57
            if (r11 != r13) goto L97
        L57:
            if (r9 == 0) goto L97
        L5a:
            int r11 = r2.length()
            r15 = r8
        L5f:
            if (r15 >= r11) goto L6f
            char r3 = r2.charAt(r15)
            if (r3 == r14) goto L6a
            if (r3 == r13) goto L6a
            goto L6f
        L6a:
            int r15 = r15 + 1
            r3 = 64
            goto L5f
        L6f:
            int r3 = r0.maxLineLength
            if (r3 <= 0) goto L8b
            int r3 = r9.length()
            int r3 = r3 + r12
            int r11 = r2.length()
            int r3 = r3 + r11
            int r3 = r3 - r15
            int r11 = r0.maxLineLength
            if (r3 > r11) goto L83
            goto L8b
        L83:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Maximum line length limit exceeded"
            r0.<init>(r1)
            throw r0
        L8b:
            r9.append(r14)
            int r3 = r2.length()
            int r3 = r3 - r15
            r9.append(r2, r15, r3)
            goto La1
        L97:
            if (r9 == 0) goto L9c
            r1.parseHeader(r9)
        L9c:
            int r10 = r10 + 1
            r9 = r2
            r2 = r7
        La1:
            int r3 = r0.maxHeaderCount
            if (r3 <= 0) goto Lb0
            if (r10 >= r3) goto La8
            goto Lb0
        La8:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Maximum header count exceeded"
            r0.<init>(r1)
            throw r0
        Lb0:
            r3 = 64
            goto L2f
        Lb4:
            if (r9 == 0) goto Lb9
            r1.parseHeader(r9)
        Lb9:
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 < r1) goto Lc2
            org.apache.http.impl.HttpConnectionMetricsImpl r0 = r0.metrics
            r0.incrementResponseCount()
        Lc2:
            return r4
        Lc3:
            org.apache.http.NoHttpResponseException r0 = new org.apache.http.NoHttpResponseException
            java.lang.String r1 = "The target server failed to respond"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.AndroidHttpClientConnection.parseResponseHeader(android.net.http.Headers):org.apache.http.StatusLine");
    }

    public HttpEntity receiveResponseEntity(Headers headers) {
        assertOpen();
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        long determineLength = determineLength(headers);
        if (determineLength == -2) {
            basicHttpEntity.setChunked(true);
            basicHttpEntity.setContentLength(-1L);
            basicHttpEntity.setContent(new ChunkedInputStream(this.inbuffer));
        } else if (determineLength == -1) {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(-1L);
            basicHttpEntity.setContent(new IdentityInputStream(this.inbuffer));
        } else {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(determineLength);
            basicHttpEntity.setContent(new ContentLengthInputStream(this.inbuffer, determineLength));
        }
        String contentType = headers.getContentType();
        if (contentType != null) {
            basicHttpEntity.setContentType(contentType);
        }
        String contentEncoding = headers.getContentEncoding();
        if (contentEncoding != null) {
            basicHttpEntity.setContentEncoding(contentEncoding);
        }
        return basicHttpEntity;
    }

    private long determineLength(Headers headers) {
        long transferEncoding = headers.getTransferEncoding();
        if (transferEncoding < 0) {
            return transferEncoding;
        }
        long contentLength = headers.getContentLength();
        if (contentLength <= -1) {
            return -1L;
        }
        return contentLength;
    }

    @Override // org.apache.http.HttpConnection
    public boolean isStale() {
        assertOpen();
        try {
            this.inbuffer.isDataAvailable(1);
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    @Override // org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
