package android.net.http;

import android.content.Context;
import android.net.http.RequestQueue;
import android.os.Process;
import android.os.SystemClock;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
class ConnectionThread extends Thread {
    static final int WAIT_TICK = 1000;
    static final int WAIT_TIMEOUT = 5000;
    Connection mConnection;
    private RequestQueue.ConnectionManager mConnectionManager;
    private Context mContext;
    long mCurrentThreadTime;
    private int mId;
    private RequestFeeder mRequestFeeder;
    private volatile boolean mRunning = true;
    long mTotalThreadTime;
    private boolean mWaiting;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionThread(Context context, int i, RequestQueue.ConnectionManager connectionManager, RequestFeeder requestFeeder) {
        this.mContext = context;
        setName("http" + i);
        this.mId = i;
        this.mConnectionManager = connectionManager;
        this.mRequestFeeder = requestFeeder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestStop() {
        synchronized (this.mRequestFeeder) {
            this.mRunning = false;
            this.mRequestFeeder.notify();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(1);
        this.mCurrentThreadTime = 0L;
        this.mTotalThreadTime = 0L;
        while (this.mRunning) {
            if (this.mCurrentThreadTime == -1) {
                this.mCurrentThreadTime = SystemClock.currentThreadTimeMillis();
            }
            Request request = this.mRequestFeeder.getRequest();
            if (request == null) {
                synchronized (this.mRequestFeeder) {
                    this.mWaiting = true;
                    try {
                        this.mRequestFeeder.wait();
                    } catch (InterruptedException e) {
                    }
                    this.mWaiting = false;
                    if (this.mCurrentThreadTime != 0) {
                        this.mCurrentThreadTime = SystemClock.currentThreadTimeMillis();
                    }
                }
            } else {
                Connection connection = this.mConnectionManager.getConnection(this.mContext, request.mHost);
                this.mConnection = connection;
                connection.processRequests(request);
                if (this.mConnection.getCanPersist()) {
                    if (!this.mConnectionManager.recycleConnection(this.mConnection)) {
                        this.mConnection.closeConnection();
                    }
                } else {
                    this.mConnection.closeConnection();
                }
                this.mConnection = null;
                long j = this.mCurrentThreadTime;
                if (j > 0) {
                    long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
                    this.mCurrentThreadTime = currentThreadTimeMillis;
                    this.mTotalThreadTime += currentThreadTimeMillis - j;
                }
            }
        }
    }

    @Override // java.lang.Thread
    public synchronized String toString() {
        Connection connection;
        connection = this.mConnection;
        return "cid " + this.mId + " " + (this.mWaiting ? "w" : "a") + " " + (connection == null ? "" : connection.toString());
    }
}
