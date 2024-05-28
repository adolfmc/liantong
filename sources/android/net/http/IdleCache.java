package android.net.http;

import android.os.Process;
import android.os.SystemClock;
import org.apache.http.HttpHost;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class IdleCache {
    private static final int CHECK_INTERVAL = 2000;
    private static final int EMPTY_CHECK_MAX = 5;
    private static final int IDLE_CACHE_MAX = 8;
    private static final int TIMEOUT = 6000;
    private Entry[] mEntries = new Entry[8];
    private int mCount = 0;
    private IdleReaper mThread = null;
    private int mCached = 0;
    private int mReused = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    public class Entry {
        Connection mConnection;
        HttpHost mHost;
        long mTimeout;

        Entry() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdleCache() {
        for (int i = 0; i < 8; i++) {
            this.mEntries[i] = new Entry();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r8.mThread != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
        r9 = new android.net.http.IdleCache.IdleReaper(r8, null);
        r8.mThread = r9;
        r9.start();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
        r6.mHost = r9;
        r6.mConnection = r10;
        r6.mTimeout = r4 + 6000;
        r8.mCount++;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean cacheConnection(org.apache.http.HttpHost r9, android.net.http.Connection r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.mCount     // Catch: java.lang.Throwable -> L3e
            r1 = 8
            r2 = 0
            r3 = 1
            if (r0 >= r1) goto L3c
            long r4 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L3e
            r0 = r2
        Lf:
            if (r0 >= r1) goto L3c
            android.net.http.IdleCache$Entry[] r6 = r8.mEntries     // Catch: java.lang.Throwable -> L3e
            r6 = r6[r0]     // Catch: java.lang.Throwable -> L3e
            org.apache.http.HttpHost r7 = r6.mHost     // Catch: java.lang.Throwable -> L3e
            if (r7 != 0) goto L39
            r6.mHost = r9     // Catch: java.lang.Throwable -> L3e
            r6.mConnection = r10     // Catch: java.lang.Throwable -> L3e
            r9 = 6000(0x1770, double:2.9644E-320)
            long r4 = r4 + r9
            r6.mTimeout = r4     // Catch: java.lang.Throwable -> L3e
            int r9 = r8.mCount     // Catch: java.lang.Throwable -> L3e
            int r9 = r9 + r3
            r8.mCount = r9     // Catch: java.lang.Throwable -> L3e
            android.net.http.IdleCache$IdleReaper r9 = r8.mThread     // Catch: java.lang.Throwable -> L3e
            if (r9 != 0) goto L37
            android.net.http.IdleCache$IdleReaper r9 = new android.net.http.IdleCache$IdleReaper     // Catch: java.lang.Throwable -> L3e
            r10 = 0
            r9.<init>()     // Catch: java.lang.Throwable -> L3e
            r8.mThread = r9     // Catch: java.lang.Throwable -> L3e
            r9.start()     // Catch: java.lang.Throwable -> L3e
        L37:
            r2 = r3
            goto L3c
        L39:
            int r0 = r0 + 1
            goto Lf
        L3c:
            monitor-exit(r8)
            return r2
        L3e:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.IdleCache.cacheConnection(org.apache.http.HttpHost, android.net.http.Connection):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
        r5 = r2.mConnection;
        r2.mHost = null;
        r2.mConnection = null;
        r4.mCount--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
        r1 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.net.http.Connection getConnection(org.apache.http.HttpHost r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r4.mCount     // Catch: java.lang.Throwable -> L2d
            r1 = 0
            if (r0 <= 0) goto L2b
            r0 = 0
        L8:
            r2 = 8
            if (r0 >= r2) goto L2b
            android.net.http.IdleCache$Entry[] r2 = r4.mEntries     // Catch: java.lang.Throwable -> L2d
            r2 = r2[r0]     // Catch: java.lang.Throwable -> L2d
            org.apache.http.HttpHost r3 = r2.mHost     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L28
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L28
            android.net.http.Connection r5 = r2.mConnection     // Catch: java.lang.Throwable -> L2d
            r2.mHost = r1     // Catch: java.lang.Throwable -> L2d
            r2.mConnection = r1     // Catch: java.lang.Throwable -> L2d
            int r0 = r4.mCount     // Catch: java.lang.Throwable -> L2d
            int r0 = r0 + (-1)
            r4.mCount = r0     // Catch: java.lang.Throwable -> L2d
            r1 = r5
            goto L2b
        L28:
            int r0 = r0 + 1
            goto L8
        L2b:
            monitor-exit(r4)
            return r1
        L2d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.IdleCache.getConnection(org.apache.http.HttpHost):android.net.http.Connection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clear() {
        for (int i = 0; this.mCount > 0 && i < 8; i++) {
            Entry entry = this.mEntries[i];
            if (entry.mHost != null) {
                entry.mHost = null;
                entry.mConnection.closeConnection();
                entry.mConnection = null;
                this.mCount--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void clearIdle() {
        if (this.mCount > 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            for (int i = 0; i < 8; i++) {
                Entry entry = this.mEntries[i];
                if (entry.mHost != null && uptimeMillis > entry.mTimeout) {
                    entry.mHost = null;
                    entry.mConnection.closeConnection();
                    entry.mConnection = null;
                    this.mCount--;
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    private class IdleReaper extends Thread {
        private IdleReaper() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("IdleReaper");
            Process.setThreadPriority(10);
            synchronized (IdleCache.this) {
                int i = 0;
                while (i < 5) {
                    try {
                        IdleCache.this.wait(2000L);
                    } catch (InterruptedException e) {
                    }
                    if (IdleCache.this.mCount == 0) {
                        i++;
                    } else {
                        IdleCache.this.clearIdle();
                        i = 0;
                    }
                }
                IdleCache.this.mThread = null;
            }
        }
    }
}
