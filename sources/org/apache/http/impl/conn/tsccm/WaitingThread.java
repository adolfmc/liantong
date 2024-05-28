package org.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.concurrent.locks.Condition;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class WaitingThread {
    private boolean aborted;
    private final Condition cond;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        if (condition == null) {
            throw new IllegalArgumentException("Condition must not be null.");
        }
        this.cond = condition;
        this.pool = routeSpecificPool;
    }

    public final Condition getCondition() {
        return this.cond;
    }

    public final RouteSpecificPool getPool() {
        return this.pool;
    }

    public final Thread getThread() {
        return this.waiter;
    }

    public boolean await(Date date) throws InterruptedException {
        boolean z;
        if (this.waiter != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.waiter);
        }
        if (this.aborted) {
            throw new InterruptedException("Operation interrupted");
        }
        this.waiter = Thread.currentThread();
        try {
            if (date != null) {
                z = this.cond.awaitUntil(date);
            } else {
                this.cond.await();
                z = true;
            }
            if (this.aborted) {
                throw new InterruptedException("Operation interrupted");
            }
            return z;
        } finally {
            this.waiter = null;
        }
    }

    public void wakeup() {
        if (this.waiter == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.cond.signalAll();
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }
}
