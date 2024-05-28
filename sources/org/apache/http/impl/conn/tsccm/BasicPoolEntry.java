package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicPoolEntry extends AbstractPoolEntry {
    private final BasicPoolEntryRef reference;

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute, ReferenceQueue<Object> referenceQueue) {
        super(clientConnectionOperator, httpRoute);
        if (httpRoute == null) {
            throw new IllegalArgumentException("HTTP route may not be null");
        }
        this.reference = new BasicPoolEntryRef(this, referenceQueue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final OperatedClientConnection getConnection() {
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final HttpRoute getPlannedRoute() {
        return this.route;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BasicPoolEntryRef getWeakRef() {
        return this.reference;
    }
}
