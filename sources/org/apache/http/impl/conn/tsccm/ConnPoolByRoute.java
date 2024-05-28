package org.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ConnPoolByRoute extends AbstractConnPool {
    private final ConnPerRoute connPerRoute;
    protected Queue<BasicPoolEntry> freeConnections;
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    protected final int maxTotalConnections;
    protected final ClientConnectionOperator operator;
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
    protected Queue<WaitingThread> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, HttpParams httpParams) {
        if (clientConnectionOperator == null) {
            throw new IllegalArgumentException("Connection operator may not be null");
        }
        this.operator = clientConnectionOperator;
        this.freeConnections = createFreeConnQueue();
        this.waitingThreads = createWaitingThreadQueue();
        this.routeToPool = createRouteToPoolMap();
        this.maxTotalConnections = ConnManagerParams.getMaxTotalConnections(httpParams);
        this.connPerRoute = ConnManagerParams.getMaxConnectionsPerRoute(httpParams);
    }

    protected Queue<BasicPoolEntry> createFreeConnQueue() {
        return new LinkedList();
    }

    protected Queue<WaitingThread> createWaitingThreadQueue() {
        return new LinkedList();
    }

    protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        return new HashMap();
    }

    protected RouteSpecificPool newRouteSpecificPool(HttpRoute httpRoute) {
        return new RouteSpecificPool(httpRoute, this.connPerRoute.getMaxForRoute(httpRoute));
    }

    protected WaitingThread newWaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        return new WaitingThread(condition, routeSpecificPool);
    }

    protected RouteSpecificPool getRoutePool(HttpRoute httpRoute, boolean z) {
        this.poolLock.lock();
        try {
            RouteSpecificPool routeSpecificPool = this.routeToPool.get(httpRoute);
            if (routeSpecificPool == null && z) {
                routeSpecificPool = newRouteSpecificPool(httpRoute);
                this.routeToPool.put(httpRoute, routeSpecificPool);
            }
            return routeSpecificPool;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getConnectionsInPool(HttpRoute httpRoute) {
        this.poolLock.lock();
        try {
            RouteSpecificPool routePool = getRoutePool(httpRoute, false);
            return routePool != null ? routePool.getEntryCount() : 0;
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public PoolEntryRequest requestPoolEntry(final HttpRoute httpRoute, final Object obj) {
        final WaitingThreadAborter waitingThreadAborter = new WaitingThreadAborter();
        return new PoolEntryRequest() { // from class: org.apache.http.impl.conn.tsccm.ConnPoolByRoute.1
            @Override // org.apache.http.impl.conn.tsccm.PoolEntryRequest
            public void abortRequest() {
                ConnPoolByRoute.this.poolLock.lock();
                try {
                    waitingThreadAborter.abort();
                } finally {
                    ConnPoolByRoute.this.poolLock.unlock();
                }
            }

            @Override // org.apache.http.impl.conn.tsccm.PoolEntryRequest
            public BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
                return ConnPoolByRoute.this.getEntryBlocking(httpRoute, obj, j, timeUnit, waitingThreadAborter);
            }
        };
    }

    protected BasicPoolEntry getEntryBlocking(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit, WaitingThreadAborter waitingThreadAborter) throws ConnectionPoolTimeoutException, InterruptedException {
        Date date;
        BasicPoolEntry basicPoolEntry = null;
        if (j <= 0) {
            date = null;
        } else {
            date = new Date(System.currentTimeMillis() + timeUnit.toMillis(j));
        }
        this.poolLock.lock();
        try {
            RouteSpecificPool routePool = getRoutePool(httpRoute, true);
            WaitingThread waitingThread = null;
            while (basicPoolEntry == null) {
                if (this.isShutDown) {
                    throw new IllegalStateException("Connection pool shut down.");
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Total connections kept alive: " + this.freeConnections.size());
                    this.log.debug("Total issued connections: " + this.issuedConnections.size());
                    this.log.debug("Total allocated connection: " + this.numConnections + " out of " + this.maxTotalConnections);
                }
                basicPoolEntry = getFreeEntry(routePool, obj);
                if (basicPoolEntry != null) {
                    break;
                }
                boolean z = routePool.getCapacity() > 0;
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Available capacity: " + routePool.getCapacity() + " out of " + routePool.getMaxEntries() + " [" + httpRoute + "][" + obj + "]");
                }
                if (z && this.numConnections < this.maxTotalConnections) {
                    basicPoolEntry = createEntry(routePool, this.operator);
                } else if (z && !this.freeConnections.isEmpty()) {
                    deleteLeastUsedEntry();
                    basicPoolEntry = createEntry(routePool, this.operator);
                } else {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Need to wait for connection [" + httpRoute + "][" + obj + "]");
                    }
                    if (waitingThread == null) {
                        waitingThread = newWaitingThread(this.poolLock.newCondition(), routePool);
                        waitingThreadAborter.setWaitingThread(waitingThread);
                    }
                    routePool.queueThread(waitingThread);
                    this.waitingThreads.add(waitingThread);
                    boolean await = waitingThread.await(date);
                    routePool.removeThread(waitingThread);
                    this.waitingThreads.remove(waitingThread);
                    if (!await && date != null && date.getTime() <= System.currentTimeMillis()) {
                        throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
                    }
                }
            }
            return basicPoolEntry;
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit) {
        HttpRoute plannedRoute = basicPoolEntry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Freeing connection [" + plannedRoute + "][" + basicPoolEntry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            if (this.isShutDown) {
                closeConnection(basicPoolEntry.getConnection());
                return;
            }
            this.issuedConnections.remove(basicPoolEntry.getWeakRef());
            RouteSpecificPool routePool = getRoutePool(plannedRoute, true);
            if (z) {
                routePool.freeEntry(basicPoolEntry);
                this.freeConnections.add(basicPoolEntry);
                this.idleConnHandler.add(basicPoolEntry.getConnection(), j, timeUnit);
            } else {
                routePool.dropEntry();
                this.numConnections--;
            }
            notifyWaitingThread(routePool);
        } finally {
            this.poolLock.unlock();
        }
    }

    protected BasicPoolEntry getFreeEntry(RouteSpecificPool routeSpecificPool, Object obj) {
        this.poolLock.lock();
        boolean z = false;
        BasicPoolEntry basicPoolEntry = null;
        while (!z) {
            try {
                basicPoolEntry = routeSpecificPool.allocEntry(obj);
                if (basicPoolEntry != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Getting free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                    }
                    this.freeConnections.remove(basicPoolEntry);
                    if (!this.idleConnHandler.remove(basicPoolEntry.getConnection())) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Closing expired free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                        }
                        closeConnection(basicPoolEntry.getConnection());
                        routeSpecificPool.dropEntry();
                        this.numConnections--;
                    } else {
                        this.issuedConnections.add(basicPoolEntry.getWeakRef());
                        z = true;
                    }
                } else {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("No free connections [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                    }
                    z = true;
                }
            } finally {
                this.poolLock.unlock();
            }
        }
        return basicPoolEntry;
    }

    protected BasicPoolEntry createEntry(RouteSpecificPool routeSpecificPool, ClientConnectionOperator clientConnectionOperator) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Creating new connection [" + routeSpecificPool.getRoute() + "]");
        }
        BasicPoolEntry basicPoolEntry = new BasicPoolEntry(clientConnectionOperator, routeSpecificPool.getRoute(), this.refQueue);
        this.poolLock.lock();
        try {
            routeSpecificPool.createdEntry(basicPoolEntry);
            this.numConnections++;
            this.issuedConnections.add(basicPoolEntry.getWeakRef());
            return basicPoolEntry;
        } finally {
            this.poolLock.unlock();
        }
    }

    protected void deleteEntry(BasicPoolEntry basicPoolEntry) {
        HttpRoute plannedRoute = basicPoolEntry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Deleting connection [" + plannedRoute + "][" + basicPoolEntry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            closeConnection(basicPoolEntry.getConnection());
            RouteSpecificPool routePool = getRoutePool(plannedRoute, true);
            routePool.deleteEntry(basicPoolEntry);
            this.numConnections--;
            if (routePool.isUnused()) {
                this.routeToPool.remove(plannedRoute);
            }
            this.idleConnHandler.remove(basicPoolEntry.getConnection());
        } finally {
            this.poolLock.unlock();
        }
    }

    protected void deleteLeastUsedEntry() {
        try {
            this.poolLock.lock();
            BasicPoolEntry remove = this.freeConnections.remove();
            if (remove != null) {
                deleteEntry(remove);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete.");
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    protected void handleLostEntry(HttpRoute httpRoute) {
        this.poolLock.lock();
        try {
            RouteSpecificPool routePool = getRoutePool(httpRoute, true);
            routePool.dropEntry();
            if (routePool.isUnused()) {
                this.routeToPool.remove(httpRoute);
            }
            this.numConnections--;
            notifyWaitingThread(routePool);
        } finally {
            this.poolLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006f A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #0 {all -> 0x0079, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0016, B:9:0x0038, B:21:0x006f, B:10:0x003d, B:12:0x0045, B:14:0x004d, B:15:0x0054, B:16:0x005d, B:18:0x0065), top: B:27:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void notifyWaitingThread(org.apache.http.impl.conn.tsccm.RouteSpecificPool r4) {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.poolLock
            r0.lock()
            if (r4 == 0) goto L3d
            boolean r0 = r4.hasThread()     // Catch: java.lang.Throwable -> L79
            if (r0 == 0) goto L3d
            org.apache.commons.logging.Log r0 = r3.log     // Catch: java.lang.Throwable -> L79
            boolean r0 = r0.isDebugEnabled()     // Catch: java.lang.Throwable -> L79
            if (r0 == 0) goto L38
            org.apache.commons.logging.Log r0 = r3.log     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r1.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "Notifying thread waiting on pool ["
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L79
            org.apache.http.conn.routing.HttpRoute r2 = r4.getRoute()     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "]"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L79
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L79
            r0.debug(r1)     // Catch: java.lang.Throwable -> L79
        L38:
            org.apache.http.impl.conn.tsccm.WaitingThread r4 = r4.nextThread()     // Catch: java.lang.Throwable -> L79
            goto L6d
        L3d:
            java.util.Queue<org.apache.http.impl.conn.tsccm.WaitingThread> r4 = r3.waitingThreads     // Catch: java.lang.Throwable -> L79
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L79
            if (r4 != 0) goto L5d
            org.apache.commons.logging.Log r4 = r3.log     // Catch: java.lang.Throwable -> L79
            boolean r4 = r4.isDebugEnabled()     // Catch: java.lang.Throwable -> L79
            if (r4 == 0) goto L54
            org.apache.commons.logging.Log r4 = r3.log     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = "Notifying thread waiting on any pool"
            r4.debug(r0)     // Catch: java.lang.Throwable -> L79
        L54:
            java.util.Queue<org.apache.http.impl.conn.tsccm.WaitingThread> r4 = r3.waitingThreads     // Catch: java.lang.Throwable -> L79
            java.lang.Object r4 = r4.remove()     // Catch: java.lang.Throwable -> L79
            org.apache.http.impl.conn.tsccm.WaitingThread r4 = (org.apache.http.impl.conn.tsccm.WaitingThread) r4     // Catch: java.lang.Throwable -> L79
            goto L6d
        L5d:
            org.apache.commons.logging.Log r4 = r3.log     // Catch: java.lang.Throwable -> L79
            boolean r4 = r4.isDebugEnabled()     // Catch: java.lang.Throwable -> L79
            if (r4 == 0) goto L6c
            org.apache.commons.logging.Log r4 = r3.log     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = "Notifying no-one, there are no waiting threads"
            r4.debug(r0)     // Catch: java.lang.Throwable -> L79
        L6c:
            r4 = 0
        L6d:
            if (r4 == 0) goto L72
            r4.wakeup()     // Catch: java.lang.Throwable -> L79
        L72:
            java.util.concurrent.locks.Lock r4 = r3.poolLock
            r4.unlock()
            return
        L79:
            r4 = move-exception
            java.util.concurrent.locks.Lock r0 = r3.poolLock
            r0.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(org.apache.http.impl.conn.tsccm.RouteSpecificPool):void");
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void deleteClosedConnections() {
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> it = this.freeConnections.iterator();
            while (it.hasNext()) {
                BasicPoolEntry next = it.next();
                if (!next.getConnection().isOpen()) {
                    it.remove();
                    deleteEntry(next);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void shutdown() {
        this.poolLock.lock();
        try {
            super.shutdown();
            Iterator<BasicPoolEntry> it = this.freeConnections.iterator();
            while (it.hasNext()) {
                it.remove();
                closeConnection(it.next().getConnection());
            }
            Iterator<WaitingThread> it2 = this.waitingThreads.iterator();
            while (it2.hasNext()) {
                it2.remove();
                it2.next().wakeup();
            }
            this.routeToPool.clear();
        } finally {
            this.poolLock.unlock();
        }
    }
}
