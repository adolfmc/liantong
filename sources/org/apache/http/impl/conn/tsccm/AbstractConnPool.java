package org.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.IdleConnectionHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractConnPool implements RefQueueHandler {
    protected volatile boolean isShutDown;
    protected int numConnections;
    protected ReferenceQueue<Object> refQueue;
    private RefQueueWorker refWorker;
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    protected Set<BasicPoolEntryRef> issuedConnections = new HashSet();
    protected IdleConnectionHandler idleConnHandler = new IdleConnectionHandler();
    protected final Lock poolLock = new ReentrantLock(false);

    public abstract void deleteClosedConnections();

    public abstract void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit);

    protected abstract void handleLostEntry(HttpRoute httpRoute);

    public abstract PoolEntryRequest requestPoolEntry(HttpRoute httpRoute, Object obj);

    public void enableConnectionGC() throws IllegalStateException {
        if (this.refQueue != null) {
            throw new IllegalStateException("Connection GC already enabled.");
        }
        this.poolLock.lock();
        try {
            if (this.numConnections > 0) {
                throw new IllegalStateException("Pool already in use.");
            }
            this.poolLock.unlock();
            ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
            this.refQueue = referenceQueue;
            this.refWorker = new RefQueueWorker(referenceQueue, this);
            Thread thread = new Thread(this.refWorker);
            thread.setDaemon(true);
            thread.setName("RefQueueWorker@" + this);
            thread.start();
        } catch (Throwable th) {
            this.poolLock.unlock();
            throw th;
        }
    }

    public final BasicPoolEntry getEntry(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
        return requestPoolEntry(httpRoute, obj).getPoolEntry(j, timeUnit);
    }

    @Override // org.apache.http.impl.conn.tsccm.RefQueueHandler
    public void handleReference(Reference reference) {
        this.poolLock.lock();
        try {
            if ((reference instanceof BasicPoolEntryRef) && this.issuedConnections.remove(reference)) {
                HttpRoute route = ((BasicPoolEntryRef) reference).getRoute();
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Connection garbage collected. " + route);
                }
                handleLostEntry(route);
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        }
        this.poolLock.lock();
        try {
            this.idleConnHandler.closeIdleConnections(timeUnit.toMillis(j));
        } finally {
            this.poolLock.unlock();
        }
    }

    public void closeExpiredConnections() {
        this.poolLock.lock();
        try {
            this.idleConnHandler.closeExpiredConnections();
        } finally {
            this.poolLock.unlock();
        }
    }

    public void shutdown() {
        this.poolLock.lock();
        try {
            if (this.isShutDown) {
                return;
            }
            RefQueueWorker refQueueWorker = this.refWorker;
            if (refQueueWorker != null) {
                refQueueWorker.shutdown();
            }
            Iterator<BasicPoolEntryRef> it = this.issuedConnections.iterator();
            while (it.hasNext()) {
                it.remove();
                BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next().get();
                if (basicPoolEntry != null) {
                    closeConnection(basicPoolEntry.getConnection());
                }
            }
            this.idleConnHandler.removeAll();
            this.isShutDown = true;
        } finally {
            this.poolLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void closeConnection(OperatedClientConnection operatedClientConnection) {
        if (operatedClientConnection != null) {
            try {
                operatedClientConnection.close();
            } catch (IOException e) {
                this.log.debug("I/O error closing connection", e);
            }
        }
    }
}
