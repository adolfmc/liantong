package com.p319ss.android.socialbase.downloader.network.connectionpool;

import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadConnectionPool {
    public static final int MAX_HOLD_CONNECTION = 3;
    private static final String TAG = "DownloadConnectionPool";
    private final Map<String, FakeDownloadHttpConnection> mCachedDownloadConnections;
    private final Map<String, FakeDownloadHeadHttpConnection> mCachedHeadConnections;
    protected int maxCacheSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putCachedHeadConnections(String str, FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnection) {
        synchronized (this.mCachedHeadConnections) {
            this.mCachedHeadConnections.put(str, fakeDownloadHeadHttpConnection);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxCachedDownloadConnectionSize(int i) {
        this.maxCacheSize = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putCachedDownloadConnections(String str, FakeDownloadHttpConnection fakeDownloadHttpConnection) {
        FakeDownloadHttpConnection fakeDownloadHttpConnection2;
        Map.Entry<String, FakeDownloadHttpConnection> next;
        synchronized (this.mCachedDownloadConnections) {
            if (this.mCachedDownloadConnections.size() == this.maxCacheSize) {
                Iterator<Map.Entry<String, FakeDownloadHttpConnection>> it = this.mCachedDownloadConnections.entrySet().iterator();
                if (it.hasNext() && (next = it.next()) != null) {
                    fakeDownloadHttpConnection2 = this.mCachedDownloadConnections.remove(next.getKey());
                    this.mCachedDownloadConnections.put(str, fakeDownloadHttpConnection);
                }
            }
            fakeDownloadHttpConnection2 = null;
            this.mCachedDownloadConnections.put(str, fakeDownloadHttpConnection);
        }
        if (fakeDownloadHttpConnection2 != null) {
            try {
                fakeDownloadHttpConnection2.end();
            } catch (Throwable unused) {
            }
        }
        Logger.m6469i(TAG, "mCachedConnections size = " + this.mCachedDownloadConnections.size() + ", max size = " + this.maxCacheSize);
    }

    public void releaseDownloadConnection(String str) {
        FakeDownloadHttpConnection remove;
        synchronized (this.mCachedDownloadConnections) {
            remove = this.mCachedDownloadConnections.remove(str);
        }
        if (remove != null) {
            try {
                remove.end();
            } catch (Throwable unused) {
            }
        }
    }

    public void releaseHeadConnection(String str) {
        FakeDownloadHeadHttpConnection remove;
        synchronized (this.mCachedHeadConnections) {
            remove = this.mCachedHeadConnections.remove(str);
        }
        if (remove != null) {
            remove.cancel();
        }
    }

    public FakeDownloadHeadHttpConnection getCachedHeadConnection(String str, List<HttpHeader> list) {
        FakeDownloadHeadHttpConnection remove;
        synchronized (this.mCachedHeadConnections) {
            remove = this.mCachedHeadConnections.remove(str);
        }
        if (remove != null) {
            if (DownloadUtils.isHeaderEqual(remove.getRequestHeaders(), list)) {
                try {
                    remove.joinExecute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (remove.isValid() && remove.isSuccessful()) {
                    return remove;
                }
            }
            try {
                remove.cancel();
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public boolean isHeadConnectionExist(String str) {
        FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnection = this.mCachedHeadConnections.get(str);
        if (fakeDownloadHeadHttpConnection != null) {
            if (fakeDownloadHeadHttpConnection.isRequesting()) {
                return true;
            }
            return fakeDownloadHeadHttpConnection.isValid() && fakeDownloadHeadHttpConnection.isSuccessful();
        }
        return false;
    }

    public FakeDownloadHttpConnection getCachedDownloadConnection(String str, List<HttpHeader> list) {
        FakeDownloadHttpConnection remove;
        synchronized (this.mCachedDownloadConnections) {
            remove = this.mCachedDownloadConnections.remove(str);
        }
        if (remove != null) {
            if (DownloadUtils.isHeaderEqual(remove.getRequestHeaders(), list)) {
                try {
                    remove.joinExecute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (remove.isValid() && remove.isSuccessful()) {
                    return remove;
                }
            }
            try {
                remove.end();
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public boolean isDownloadConnectionExist(String str) {
        FakeDownloadHttpConnection fakeDownloadHttpConnection = this.mCachedDownloadConnections.get(str);
        if (fakeDownloadHttpConnection != null) {
            if (fakeDownloadHttpConnection.isRequesting()) {
                return true;
            }
            return fakeDownloadHttpConnection.isValid() && fakeDownloadHttpConnection.isSuccessful();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool$InstanceHolder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class InstanceHolder {
        private static final DownloadConnectionPool INSTANCE = new DownloadConnectionPool();

        private InstanceHolder() {
        }
    }

    public static DownloadConnectionPool getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private DownloadConnectionPool() {
        this.mCachedHeadConnections = new HashMap();
        this.mCachedDownloadConnections = new LinkedHashMap(3);
        this.maxCacheSize = 3;
    }
}
