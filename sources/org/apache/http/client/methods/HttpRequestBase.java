package org.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class HttpRequestBase extends AbstractHttpMessage implements HttpUriRequest, AbortableHttpRequest, Cloneable {
    private Lock abortLock = new ReentrantLock();
    private boolean aborted;
    private ClientConnectionRequest connRequest;
    private ConnectionReleaseTrigger releaseTrigger;
    private URI uri;

    public abstract String getMethod();

    @Override // org.apache.http.HttpMessage
    public ProtocolVersion getProtocolVersion() {
        return HttpProtocolParams.getVersion(getParams());
    }

    @Override // org.apache.http.client.methods.HttpUriRequest
    public URI getURI() {
        return this.uri;
    }

    @Override // org.apache.http.HttpRequest
    public RequestLine getRequestLine() {
        String str;
        String method = getMethod();
        ProtocolVersion protocolVersion = getProtocolVersion();
        URI uri = getURI();
        if (uri == null) {
            str = null;
        } else {
            str = uri.toASCIIString();
        }
        return new BasicRequestLine(method, (str == null || str.length() == 0) ? "/" : "/", protocolVersion);
    }

    public void setURI(URI uri) {
        this.uri = uri;
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    public void setConnectionRequest(ClientConnectionRequest clientConnectionRequest) throws IOException {
        this.abortLock.lock();
        try {
            if (this.aborted) {
                throw new IOException("Request already aborted");
            }
            this.releaseTrigger = null;
            this.connRequest = clientConnectionRequest;
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    public void setReleaseTrigger(ConnectionReleaseTrigger connectionReleaseTrigger) throws IOException {
        this.abortLock.lock();
        try {
            if (this.aborted) {
                throw new IOException("Request already aborted");
            }
            this.connRequest = null;
            this.releaseTrigger = connectionReleaseTrigger;
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.methods.AbortableHttpRequest
    public void abort() {
        this.abortLock.lock();
        try {
            if (this.aborted) {
                return;
            }
            this.aborted = true;
            ClientConnectionRequest clientConnectionRequest = this.connRequest;
            ConnectionReleaseTrigger connectionReleaseTrigger = this.releaseTrigger;
            if (clientConnectionRequest != null) {
                clientConnectionRequest.abortRequest();
            }
            if (connectionReleaseTrigger != null) {
                try {
                    connectionReleaseTrigger.abortConnection();
                } catch (IOException e) {
                }
            }
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.HttpUriRequest
    public boolean isAborted() {
        return this.aborted;
    }

    public Object clone() throws CloneNotSupportedException {
        HttpRequestBase httpRequestBase = (HttpRequestBase) super.clone();
        httpRequestBase.abortLock = new ReentrantLock();
        httpRequestBase.aborted = false;
        httpRequestBase.releaseTrigger = null;
        httpRequestBase.connRequest = null;
        httpRequestBase.headergroup = (HeaderGroup) CloneUtils.clone(this.headergroup);
        httpRequestBase.params = (HttpParams) CloneUtils.clone(this.params);
        return httpRequestBase;
    }
}
