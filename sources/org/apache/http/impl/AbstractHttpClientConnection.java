package org.apache.http.impl;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.p452io.HttpRequestWriter;
import org.apache.http.impl.p452io.HttpResponseParser;
import org.apache.http.impl.p452io.SocketInputBuffer;
import org.apache.http.p453io.HttpMessageParser;
import org.apache.http.p453io.HttpMessageWriter;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private SessionInputBuffer inbuffer = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageParser responseParser = null;
    private HttpMessageWriter requestWriter = null;
    private HttpConnectionMetricsImpl metrics = null;
    private final EntitySerializer entityserializer = createEntitySerializer();
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();

    protected abstract void assertOpen() throws IllegalStateException;

    protected EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }

    protected EntitySerializer createEntitySerializer() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    protected HttpResponseFactory createHttpResponseFactory() {
        return new DefaultHttpResponseFactory();
    }

    protected HttpMessageParser createResponseParser(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new HttpResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    protected HttpMessageWriter createRequestWriter(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpRequestWriter(sessionOutputBuffer, null, httpParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        if (sessionInputBuffer == null) {
            throw new IllegalArgumentException("Input session buffer may not be null");
        }
        if (sessionOutputBuffer == null) {
            throw new IllegalArgumentException("Output session buffer may not be null");
        }
        this.inbuffer = sessionInputBuffer;
        this.outbuffer = sessionOutputBuffer;
        this.responseParser = createResponseParser(sessionInputBuffer, createHttpResponseFactory(), httpParams);
        this.requestWriter = createRequestWriter(sessionOutputBuffer, httpParams);
        this.metrics = new HttpConnectionMetricsImpl(sessionInputBuffer.getMetrics(), sessionOutputBuffer.getMetrics());
    }

    @Override // org.apache.http.HttpClientConnection
    public boolean isResponseAvailable(int i) throws IOException {
        assertOpen();
        return this.inbuffer.isDataAvailable(i);
    }

    @Override // org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        assertOpen();
        this.requestWriter.write(httpRequest);
        this.metrics.incrementRequestCount();
    }

    @Override // org.apache.http.HttpClientConnection
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    @Override // org.apache.http.HttpClientConnection
    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    @Override // org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        assertOpen();
        HttpResponse httpResponse = (HttpResponse) this.responseParser.parse();
        if (httpResponse.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
        return httpResponse;
    }

    @Override // org.apache.http.HttpClientConnection
    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        assertOpen();
        httpResponse.setEntity(this.entitydeserializer.deserialize(this.inbuffer, httpResponse));
    }

    @Override // org.apache.http.HttpConnection
    public boolean isStale() {
        if (isOpen()) {
            try {
                SessionInputBuffer sessionInputBuffer = this.inbuffer;
                if (sessionInputBuffer instanceof SocketInputBuffer) {
                    return ((SocketInputBuffer) sessionInputBuffer).isStale();
                }
                sessionInputBuffer.isDataAvailable(1);
                return false;
            } catch (IOException e) {
                return true;
            }
        }
        return true;
    }

    @Override // org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
