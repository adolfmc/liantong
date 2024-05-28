package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class IdleConnectionHandler {
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    private final Map<HttpConnection, TimeValues> connectionToTimes = new HashMap();

    public void add(HttpConnection httpConnection, long j, TimeUnit timeUnit) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        if (this.log.isDebugEnabled()) {
            this.log.debug("Adding connection at: " + valueOf);
        }
        this.connectionToTimes.put(httpConnection, new TimeValues(valueOf.longValue(), j, timeUnit));
    }

    public boolean remove(HttpConnection httpConnection) {
        TimeValues remove = this.connectionToTimes.remove(httpConnection);
        if (remove != null) {
            return System.currentTimeMillis() <= remove.timeExpires;
        }
        this.log.warn("Removing a connection that never existed!");
        return true;
    }

    public void removeAll() {
        this.connectionToTimes.clear();
    }

    public void closeIdleConnections(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (this.log.isDebugEnabled()) {
            this.log.debug("Checking for connections, idleTimeout: " + currentTimeMillis);
        }
        Iterator<HttpConnection> it = this.connectionToTimes.keySet().iterator();
        while (it.hasNext()) {
            HttpConnection next = it.next();
            Long valueOf = Long.valueOf(this.connectionToTimes.get(next).timeAdded);
            if (valueOf.longValue() <= currentTimeMillis) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Closing connection, connection time: " + valueOf);
                }
                it.remove();
                try {
                    next.close();
                } catch (IOException e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }

    public void closeExpiredConnections() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Checking for expired connections, now: " + currentTimeMillis);
        }
        Iterator<HttpConnection> it = this.connectionToTimes.keySet().iterator();
        while (it.hasNext()) {
            HttpConnection next = it.next();
            TimeValues timeValues = this.connectionToTimes.get(next);
            if (timeValues.timeExpires <= currentTimeMillis) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Closing connection, expired @: " + timeValues.timeExpires);
                }
                it.remove();
                try {
                    next.close();
                } catch (IOException e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    private static class TimeValues {
        private final long timeAdded;
        private final long timeExpires;

        TimeValues(long j, long j2, TimeUnit timeUnit) {
            this.timeAdded = j;
            if (j2 > 0) {
                this.timeExpires = j + timeUnit.toMillis(j2);
            } else {
                this.timeExpires = Long.MAX_VALUE;
            }
        }
    }
}
