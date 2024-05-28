package com.liulishuo.filedownloader.progress;

import com.liulishuo.filedownloader.CompatListenerAssist;
import com.liulishuo.filedownloader.DownloadTaskAdapter;
import com.liulishuo.okdownload.core.Util;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ProgressAssist {
    static final int CALLBACK_SAFE_MIN_INTERVAL_BYTES = 1;
    static final int NO_ANY_PROGRESS_CALLBACK = -1;
    private static final String TAG = "ProgressAssist";
    private static final long TOTAL_VALUE_IN_CHUNKED_RESOURCE = -1;
    private final int maxProgressCount;
    long callbackMinIntervalBytes = 1;
    final AtomicLong sofarBytes = new AtomicLong(0);
    final AtomicLong incrementBytes = new AtomicLong(0);

    public ProgressAssist(int i) {
        this.maxProgressCount = i;
    }

    public void calculateCallbackMinIntervalBytes(long j) {
        int i = this.maxProgressCount;
        if (i <= 0) {
            this.callbackMinIntervalBytes = -1L;
        } else if (j == -1) {
            this.callbackMinIntervalBytes = 1L;
        } else {
            long j2 = j / i;
            if (j2 <= 0) {
                j2 = 1;
            }
            this.callbackMinIntervalBytes = j2;
        }
        Util.m13741d(TAG, "contentLength: " + j + " callbackMinIntervalBytes: " + this.callbackMinIntervalBytes);
    }

    public void onProgress(DownloadTaskAdapter downloadTaskAdapter, long j, CompatListenerAssist.CompatListenerAssistCallback compatListenerAssistCallback) {
        long addAndGet = this.sofarBytes.addAndGet(j);
        if (canCallbackProgress(j)) {
            compatListenerAssistCallback.progress(downloadTaskAdapter, addAndGet, downloadTaskAdapter.getTotalBytesInLong());
        }
    }

    boolean canCallbackProgress(long j) {
        if (this.callbackMinIntervalBytes == -1) {
            return false;
        }
        long addAndGet = this.incrementBytes.addAndGet(j);
        long j2 = this.callbackMinIntervalBytes;
        if (addAndGet >= j2) {
            this.incrementBytes.addAndGet(-j2);
            return true;
        }
        return false;
    }

    public long getSofarBytes() {
        return this.sofarBytes.get();
    }

    public void clearProgress() {
        Util.m13741d(TAG, "clear progress, sofar: " + this.sofarBytes.get() + " increment: " + this.incrementBytes.get());
        this.sofarBytes.set(0L);
        this.incrementBytes.set(0L);
    }

    public void initSofarBytes(long j) {
        Util.m13741d(TAG, "init sofar: " + j);
        this.sofarBytes.set(j);
    }
}
