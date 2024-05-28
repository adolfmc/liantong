package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry poolEntry;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPooledConnAdapter(ClientConnectionManager clientConnectionManager, AbstractPoolEntry abstractPoolEntry) {
        super(clientConnectionManager, abstractPoolEntry.connection);
        this.poolEntry = abstractPoolEntry;
    }

    protected final void assertAttached() {
        if (this.poolEntry == null) {
            throw new IllegalStateException("Adapter is detached.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.conn.AbstractClientConnAdapter
    public void detach() {
        super.detach();
        this.poolEntry = null;
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public HttpRoute getRoute() {
        assertAttached();
        if (this.poolEntry.tracker == null) {
            return null;
        }
        return this.poolEntry.tracker.toRoute();
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        assertAttached();
        this.poolEntry.open(httpRoute, httpContext, httpParams);
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        assertAttached();
        this.poolEntry.tunnelTarget(z, httpParams);
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        assertAttached();
        this.poolEntry.tunnelProxy(httpHost, z, httpParams);
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        assertAttached();
        this.poolEntry.layerProtocol(httpContext, httpParams);
    }

    @Override // org.apache.http.HttpConnection
    public void close() throws IOException {
        if (this.poolEntry != null) {
            this.poolEntry.shutdownEntry();
        }
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection != null) {
            wrappedConnection.close();
        }
    }

    @Override // org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        if (this.poolEntry != null) {
            this.poolEntry.shutdownEntry();
        }
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection != null) {
            wrappedConnection.shutdown();
        }
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public Object getState() {
        assertAttached();
        return this.poolEntry.getState();
    }

    @Override // org.apache.http.conn.ManagedClientConnection
    public void setState(Object obj) {
        assertAttached();
        this.poolEntry.setState(obj);
    }
}
