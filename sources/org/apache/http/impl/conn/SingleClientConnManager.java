package org.apache.http.impl.conn;

import android.net.TrafficStats;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class SingleClientConnManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    protected boolean alwaysShutDown;
    protected ClientConnectionOperator connOperator;
    protected long connectionExpiresTime;
    protected volatile boolean isShutDown;
    protected long lastReleaseTime;
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    protected ConnAdapter managedConn;
    protected SchemeRegistry schemeRegistry;
    protected PoolEntry uniquePoolEntry;

    public SingleClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry) {
        if (schemeRegistry == null) {
            throw new IllegalArgumentException("Scheme registry must not be null.");
        }
        this.schemeRegistry = schemeRegistry;
        this.connOperator = createConnectionOperator(schemeRegistry);
        this.uniquePoolEntry = new PoolEntry();
        this.managedConn = null;
        this.lastReleaseTime = -1L;
        this.alwaysShutDown = false;
        this.isShutDown = false;
    }

    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    protected final void assertStillUp() throws IllegalStateException {
        if (this.isShutDown) {
            throw new IllegalStateException("Manager is shut down.");
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public final ClientConnectionRequest requestConnection(final HttpRoute httpRoute, final Object obj) {
        return new ClientConnectionRequest() { // from class: org.apache.http.impl.conn.SingleClientConnManager.1
            @Override // org.apache.http.conn.ClientConnectionRequest
            public void abortRequest() {
            }

            @Override // org.apache.http.conn.ClientConnectionRequest
            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
                return SingleClientConnManager.this.getConnection(httpRoute, obj);
            }
        };
    }

    public ManagedClientConnection getConnection(HttpRoute httpRoute, Object obj) {
        boolean z;
        if (httpRoute == null) {
            throw new IllegalArgumentException("Route may not be null.");
        }
        assertStillUp();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Get connection for route " + httpRoute);
        }
        if (this.managedConn != null) {
            revokeConnection();
        }
        closeExpiredConnections();
        boolean z2 = true;
        boolean z3 = false;
        if (this.uniquePoolEntry.connection.isOpen()) {
            RouteTracker routeTracker = this.uniquePoolEntry.tracker;
            z3 = routeTracker == null || !routeTracker.toRoute().equals(httpRoute);
            z = false;
        } else {
            z = true;
        }
        if (!z3) {
            z2 = z;
        } else {
            try {
                this.uniquePoolEntry.shutdown();
            } catch (IOException e) {
                this.log.debug("Problem shutting down connection.", e);
            }
        }
        if (z2) {
            this.uniquePoolEntry = new PoolEntry();
        }
        try {
            Socket socket = this.uniquePoolEntry.connection.getSocket();
            if (socket != null) {
                TrafficStats.tagSocket(socket);
            }
        } catch (IOException e2) {
            this.log.debug("Problem tagging socket.", e2);
        }
        ConnAdapter connAdapter = new ConnAdapter(this.uniquePoolEntry, httpRoute);
        this.managedConn = connAdapter;
        return connAdapter;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
        if (r10 > 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a5, code lost:
        if (r10 <= 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a7, code lost:
        r8.connectionExpiresTime = r12.toMillis(r10) + r8.lastReleaseTime;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b1, code lost:
        r8.connectionExpiresTime = Long.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b4, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    @Override // org.apache.http.conn.ClientConnectionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void releaseConnection(org.apache.http.conn.ManagedClientConnection r9, long r10, java.util.concurrent.TimeUnit r12) {
        /*
            r8 = this;
            r8.assertStillUp()
            boolean r0 = r9 instanceof org.apache.http.impl.conn.SingleClientConnManager.ConnAdapter
            if (r0 == 0) goto Ld1
            org.apache.commons.logging.Log r0 = r8.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L27
            org.apache.commons.logging.Log r0 = r8.log
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Releasing connection "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.debug(r1)
        L27:
            org.apache.http.impl.conn.SingleClientConnManager$ConnAdapter r9 = (org.apache.http.impl.conn.SingleClientConnManager.ConnAdapter) r9
            org.apache.http.impl.conn.AbstractPoolEntry r0 = r9.poolEntry
            if (r0 != 0) goto L2e
            return
        L2e:
            org.apache.http.conn.ClientConnectionManager r0 = r9.getManager()
            if (r0 == 0) goto L3f
            if (r0 != r8) goto L37
            goto L3f
        L37:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Connection not obtained from this manager."
            r9.<init>(r10)
            throw r9
        L3f:
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r2 = 0
            r4 = 0
            org.apache.http.impl.conn.SingleClientConnManager$PoolEntry r5 = r8.uniquePoolEntry     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            org.apache.http.conn.OperatedClientConnection r5 = r5.connection     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            java.net.Socket r5 = r5.getSocket()     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            if (r5 == 0) goto L54
            android.net.TrafficStats.untagSocket(r5)     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
        L54:
            boolean r5 = r9.isOpen()     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            if (r5 == 0) goto L76
            boolean r5 = r8.alwaysShutDown     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            if (r5 != 0) goto L64
            boolean r5 = r9.isMarkedReusable()     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            if (r5 != 0) goto L76
        L64:
            org.apache.commons.logging.Log r5 = r8.log     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            boolean r5 = r5.isDebugEnabled()     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            if (r5 == 0) goto L73
            org.apache.commons.logging.Log r5 = r8.log     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
            java.lang.String r6 = "Released connection open but not reusable."
            r5.debug(r6)     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
        L73:
            r9.shutdown()     // Catch: java.lang.Throwable -> L86 java.io.IOException -> L88
        L76:
            r9.detach()
            r8.managedConn = r4
            long r4 = java.lang.System.currentTimeMillis()
            r8.lastReleaseTime = r4
            int r9 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r9 <= 0) goto Lb1
            goto La7
        L86:
            r5 = move-exception
            goto Lb5
        L88:
            r5 = move-exception
            org.apache.commons.logging.Log r6 = r8.log     // Catch: java.lang.Throwable -> L86
            boolean r6 = r6.isDebugEnabled()     // Catch: java.lang.Throwable -> L86
            if (r6 == 0) goto L98
            org.apache.commons.logging.Log r6 = r8.log     // Catch: java.lang.Throwable -> L86
            java.lang.String r7 = "Exception shutting down released connection."
            r6.debug(r7, r5)     // Catch: java.lang.Throwable -> L86
        L98:
            r9.detach()
            r8.managedConn = r4
            long r4 = java.lang.System.currentTimeMillis()
            r8.lastReleaseTime = r4
            int r9 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r9 <= 0) goto Lb1
        La7:
            long r9 = r12.toMillis(r10)
            long r11 = r8.lastReleaseTime
            long r9 = r9 + r11
            r8.connectionExpiresTime = r9
            goto Lb4
        Lb1:
            r8.connectionExpiresTime = r0
        Lb4:
            return
        Lb5:
            r9.detach()
            r8.managedConn = r4
            long r6 = java.lang.System.currentTimeMillis()
            r8.lastReleaseTime = r6
            int r9 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r9 <= 0) goto Lce
            long r9 = r12.toMillis(r10)
            long r11 = r8.lastReleaseTime
            long r9 = r9 + r11
            r8.connectionExpiresTime = r9
            goto Ld0
        Lce:
            r8.connectionExpiresTime = r0
        Ld0:
            throw r5
        Ld1:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Connection class mismatch, connection not obtained from this manager."
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.SingleClientConnManager.releaseConnection(org.apache.http.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeExpiredConnections() {
        if (System.currentTimeMillis() >= this.connectionExpiresTime) {
            closeIdleConnections(0L, TimeUnit.MILLISECONDS);
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        assertStillUp();
        if (timeUnit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        }
        if (this.managedConn == null && this.uniquePoolEntry.connection.isOpen()) {
            if (this.lastReleaseTime <= System.currentTimeMillis() - timeUnit.toMillis(j)) {
                try {
                    this.uniquePoolEntry.close();
                } catch (IOException e) {
                    this.log.debug("Problem closing idle connection.", e);
                }
            }
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void shutdown() {
        this.isShutDown = true;
        ConnAdapter connAdapter = this.managedConn;
        if (connAdapter != null) {
            connAdapter.detach();
        }
        try {
            try {
                PoolEntry poolEntry = this.uniquePoolEntry;
                if (poolEntry != null) {
                    poolEntry.shutdown();
                }
            } catch (IOException e) {
                this.log.debug("Problem while shutting down manager.", e);
            }
        } finally {
            this.uniquePoolEntry = null;
        }
    }

    protected void revokeConnection() {
        if (this.managedConn == null) {
            return;
        }
        this.log.warn("Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
        this.managedConn.detach();
        try {
            this.uniquePoolEntry.shutdown();
        } catch (IOException e) {
            this.log.debug("Problem while shutting down connection.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    public class PoolEntry extends AbstractPoolEntry {
        protected PoolEntry() {
            super(SingleClientConnManager.this.connOperator, null);
        }

        protected void close() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.close();
            }
        }

        protected void shutdown() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.shutdown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    public class ConnAdapter extends AbstractPooledConnAdapter {
        protected ConnAdapter(PoolEntry poolEntry, HttpRoute httpRoute) {
            super(SingleClientConnManager.this, poolEntry);
            markReusable();
            poolEntry.route = httpRoute;
        }
    }
}
