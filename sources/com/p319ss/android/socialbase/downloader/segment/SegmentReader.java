package com.p319ss.android.socialbase.downloader.segment;

import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.RetryThrowable;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.model.HttpResponse;
import com.p319ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadStenographer;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentReader */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SegmentReader implements Runnable {
    private static final int SEGMENT_APPLY_RETRY_MAX_COUNT = 50;
    private static final int SWITCH_URL_MAX_COUNT = 30;
    private static final String TAG = "SegmentReader";
    private volatile boolean changeSegment;
    private volatile boolean closed;
    volatile long connectEndTime;
    volatile long connectStartTime;
    String curHostIp;
    String curHostRealIp;
    private int curRetryCount;
    volatile Segment curSegment;
    String curUrl;
    private final DownloadInfo downloadInfo;
    private volatile long endOffsetInConnection;
    private boolean exited;
    private boolean failed;
    private BaseException failedException;
    private Future future;
    private final ISegmentCallback host;
    private IDownloadHttpConnection httpConnection;
    private HttpResponse httpResponse;
    private boolean httpsToHttpRetryUsed;
    private long lastConnectStartTime;
    private final IBufferPool pool;
    private volatile long readBytes;
    volatile long readEndTime;
    volatile long readStartTime;
    private volatile boolean reconnect;
    private int retryCount;
    private int segmentApplyRetryTimes;
    private volatile long segmentNewEndOffset;
    private final DownloadSetting setting;
    private long startOffsetInConnection;
    private DownloadStenographer stenographer;
    private int switchUrlTimes;
    private Thread thread;
    private volatile boolean threadDirty;
    final int threadIndex;
    UrlRecord urlRecord;
    private final List<Segment> succeedSegments = new ArrayList();
    private volatile long curSegmentReadOffset = -1;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fa: MOVE  (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5387 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 33
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void loopAndRead(com.p319ss.android.socialbase.downloader.segment.Segment r32) throws com.p319ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instructions count: 1170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.segment.SegmentReader.loopAndRead(com.ss.android.socialbase.downloader.segment.Segment):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SegmentReader(DownloadInfo downloadInfo, SegmentDispatcher segmentDispatcher, IBufferPool iBufferPool, UrlRecord urlRecord, int i) {
        this.downloadInfo = downloadInfo;
        this.host = segmentDispatcher;
        this.pool = iBufferPool;
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.urlRecord = urlRecord;
        this.threadIndex = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x007d, code lost:
        r6.curSegment = null;
        r2 = r6.host;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.segment.SegmentReader.run():void");
    }

    private boolean download(Segment segment) throws BaseException {
        initParams();
        while (true) {
            try {
                try {
                    doConnect(segment);
                    loopAndRead(segment);
                    releaseDownload();
                    return true;
                } catch (SegmentApplyException e) {
                    this.failedException = e;
                    throw e;
                } catch (Throwable th) {
                    Logger.m6472e(TAG, "download: e = " + th + ", threadIndex = " + this.threadIndex + ", reconnect = " + this.reconnect + ", closed = " + this.closed);
                    if (this.closed) {
                        releaseDownload();
                        return false;
                    }
                    if (this.reconnect) {
                        this.reconnect = false;
                        Thread.interrupted();
                        if (this.changeSegment) {
                            this.changeSegment = false;
                            throw new SegmentApplyException(5, "download");
                        }
                    } else {
                        th.printStackTrace();
                        BaseException baseException = null;
                        if (th instanceof BaseException) {
                            baseException = (BaseException) th;
                        } else {
                            try {
                                DownloadUtils.parseException(th, "download");
                            } catch (BaseException e2) {
                                baseException = e2;
                            }
                        }
                        if (baseException == null || !handleFailedAndCheckRetry(segment, baseException)) {
                            releaseDownload();
                            return false;
                        }
                    }
                    releaseDownload();
                }
            } catch (Throwable th2) {
                releaseDownload();
                throw th2;
            }
        }
    }

    private void releaseDownload() {
        this.lastConnectStartTime = this.connectStartTime;
        this.connectStartTime = -1L;
        this.connectEndTime = -1L;
        this.readStartTime = -1L;
        this.readEndTime = -1L;
        closeConnection();
    }

    private void closeConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.httpConnection;
        if (iDownloadHttpConnection != null) {
            try {
                Logger.m6469i(TAG, "closeConnection: thread = " + this.threadIndex);
                iDownloadHttpConnection.end();
                iDownloadHttpConnection.cancel();
            } catch (Throwable unused) {
            }
        }
    }

    private void initParams() {
        this.httpsToHttpRetryUsed = false;
        resetRetryTimes();
    }

    private void resetRetryTimes() {
        this.retryCount = this.urlRecord.isMainUrl ? this.downloadInfo.getRetryCount() : this.downloadInfo.getBackUpUrlRetryCount();
        this.curRetryCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean switchUrlRecord(UrlRecord urlRecord) {
        int i = this.switchUrlTimes;
        if (i >= 30) {
            return false;
        }
        this.switchUrlTimes = i + 1;
        UrlRecord urlRecord2 = this.urlRecord;
        if (urlRecord2 != null) {
            urlRecord2.recordUnUse(this);
        }
        urlRecord.recordUse(this);
        this.urlRecord = urlRecord;
        resetRetryTimes();
        return true;
    }

    int getCurRetryCount() {
        return this.curRetryCount;
    }

    int getRetryCount() {
        return this.retryCount;
    }

    private boolean handleFailedAndCheckRetry(Segment segment, BaseException baseException) {
        Logger.m6472e(TAG, "handleDownloadFailed:  e = " + baseException + ", curRetryCount = " + this.curRetryCount + ", retryCount = " + this.retryCount);
        this.failedException = baseException;
        this.urlRecord.recordFailed();
        this.host.onSegmentRetry(this, this.urlRecord, segment, baseException, this.curRetryCount, this.retryCount);
        int i = this.curRetryCount;
        if (i < this.retryCount) {
            this.curRetryCount = i + 1;
            return true;
        } else if (checkCanUseHttpsToHttpRetry(baseException)) {
            return true;
        } else {
            this.host.onSegmentFailed(this, this.urlRecord, segment, baseException);
            return false;
        }
    }

    private boolean checkCanUseHttpsToHttpRetry(BaseException baseException) {
        if (DownloadUtils.isHttpsError(baseException)) {
            String str = this.urlRecord.url;
            if (TextUtils.isEmpty(str) || !str.startsWith("https") || !this.downloadInfo.isNeedHttpsToHttpRetry() || this.httpsToHttpRetryUsed) {
                return false;
            }
            this.httpsToHttpRetryUsed = true;
            resetRetryTimes();
            return true;
        }
        return false;
    }

    private void doConnect(Segment segment) throws BaseException, RetryThrowable {
        createConnection(segment);
        this.host.onSegmentConnected(this, segment, this.urlRecord, this.httpResponse);
        this.urlRecord.recordSucceed();
    }

    private void createConnection(Segment segment) throws BaseException {
        String str;
        String str2;
        IDownloadHttpConnection downloadWithConnection;
        try {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.connectEndTime = 0L;
                this.connectStartTime = currentTimeMillis;
                this.startOffsetInConnection = segment.getCurrentOffsetRead();
                this.endOffsetInConnection = segment.getEndOffset();
                if (this.endOffsetInConnection > 0 && this.startOffsetInConnection > this.endOffsetInConnection) {
                    throw new SegmentApplyException(6, "createConn, " + segment);
                }
                this.stenographer = new DownloadStenographer();
                List<HttpHeader> addRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), this.startOffsetInConnection, this.endOffsetInConnection);
                addRangeHeader.add(new HttpHeader("Segment-Index", String.valueOf(segment.getIndex())));
                addRangeHeader.add(new HttpHeader("Thread-Index", String.valueOf(this.threadIndex)));
                DownloadUtils.addThrottleNetSpeed(addRangeHeader, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(addRangeHeader, this.downloadInfo);
                str = this.urlRecord.url;
                if (this.httpsToHttpRetryUsed && !TextUtils.isEmpty(str) && str.startsWith("https")) {
                    str = str.replaceFirst("https", "http");
                }
                str2 = this.urlRecord.f19712ip;
                Logger.m6469i(TAG, "createConnectionBegin: url = " + str + ", ip = " + str2 + ", segment = " + segment + ", threadIndex = " + this.threadIndex);
                this.curUrl = str;
                this.curHostIp = str2;
                downloadWithConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), str, str2, addRangeHeader, 0, currentTimeMillis - this.lastConnectStartTime > 3000 && this.setting.optInt("monitor_download_connect") > 0, this.downloadInfo);
            } catch (BaseException e) {
                throw e;
            } catch (Throwable th) {
                DownloadUtils.parseException(th, "createConn");
            }
            if (downloadWithConnection == null) {
                throw new BaseException(1022, new IOException("download can't continue, chunk connection is null"));
            }
            this.httpConnection = downloadWithConnection;
            this.httpResponse = new HttpResponse(str, downloadWithConnection);
            if (this.closed) {
                throw new StreamClosedException("createConn");
            }
            if (downloadWithConnection instanceof AbsDownloadHttpConnection) {
                this.curHostRealIp = ((AbsDownloadHttpConnection) downloadWithConnection).getHostIp();
            }
            Log.i(TAG, "createConnectionSuccess: url = " + str + ", ip = " + str2 + ", hostRealIp = " + this.curHostRealIp + ", threadIndex = " + this.threadIndex);
        } finally {
            this.connectEndTime = System.currentTimeMillis();
        }
    }

    private Buffer probeFirstBuffer(IBufferPool iBufferPool, InputStream inputStream) throws InterruptedException, BaseException, IOException {
        int i;
        Buffer obtain = iBufferPool.obtain();
        try {
            i = inputStream.read(obtain.data);
            try {
                if (i == -1) {
                    throw new BaseException(1073, "probe");
                }
                obtain.size = i;
                if (i == -1) {
                    iBufferPool.recycle(obtain);
                }
                return obtain;
            } catch (Throwable th) {
                th = th;
                if (i == -1) {
                    iBufferPool.recycle(obtain);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            i = -1;
        }
    }

    private long refreshSegmentEndOffset() {
        long j = this.segmentNewEndOffset;
        this.segmentNewEndOffset = 0L;
        if (j <= 0) {
            return Long.MAX_VALUE;
        }
        return j;
    }

    public boolean adjustSegmentEndOffset(long j) {
        long j2 = this.endOffsetInConnection;
        if (j > 0 || j2 <= 0) {
            if (j <= j2 || j2 <= 0) {
                this.segmentNewEndOffset = j;
                this.threadDirty = true;
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateReadBytes() {
        UrlRecord urlRecord = this.urlRecord;
        try {
            synchronized (this.host) {
                long readingBytes = getReadingBytes();
                if (readingBytes > 0) {
                    this.readBytes += readingBytes;
                    urlRecord.increaseDownloadBytes(readingBytes);
                }
                this.curSegmentReadOffset = -1L;
            }
        } catch (Throwable unused) {
        }
    }

    public long getReadBytes() {
        long readingBytes;
        synchronized (this.host) {
            readingBytes = this.readBytes + getReadingBytes();
        }
        return readingBytes;
    }

    public long getReadingBytes() {
        synchronized (this.host) {
            long j = this.curSegmentReadOffset;
            long j2 = this.startOffsetInConnection;
            if (j2 < 0 || j <= j2) {
                return 0L;
            }
            return j - j2;
        }
    }

    public long getCurSegmentReadOffset() {
        return this.curSegmentReadOffset;
    }

    public void close() {
        Logger.m6469i(TAG, "close: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.closed = true;
            this.threadDirty = true;
        }
        closeConnection();
        Future future = this.future;
        if (future != null) {
            this.future = null;
            try {
                future.cancel(true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void reconnect() {
        reconnect(false);
    }

    public void reconnect(boolean z) {
        Logger.m6469i(TAG, "reconnect: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.changeSegment = z;
            this.reconnect = true;
            this.threadDirty = true;
        }
        closeConnection();
        Thread thread = this.thread;
        if (thread != null) {
            try {
                Log.i(TAG, "reconnect: t.interrupt threadIndex = " + this.threadIndex);
                thread.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFuture(Future future) {
        this.future = future;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFailed(boolean z) {
        this.failed = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFailed() {
        return this.failed;
    }

    public boolean isExited() {
        return this.exited;
    }

    public void setExited(boolean z) {
        this.exited = z;
    }

    BaseException getFailedException() {
        return this.failedException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markProgress(long j) {
        long j2 = this.curSegmentReadOffset;
        DownloadStenographer downloadStenographer = this.stenographer;
        if (j2 < 0 || downloadStenographer == null) {
            return;
        }
        Log.i(TAG, "markProgress: curSegmentReadOffset = " + j2 + ", threadIndex = " + this.threadIndex);
        downloadStenographer.markProgress(j2, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getRecentDownloadSpeed(long j, long j2) {
        DownloadStenographer downloadStenographer = this.stenographer;
        if (downloadStenographer == null) {
            return -1L;
        }
        return downloadStenographer.getRecentDownloadSpeed(j, j2);
    }

    long getCurSegmentDownloadSpeed(long j) {
        long j2 = this.readStartTime;
        if (j2 <= 0) {
            return -1L;
        }
        long j3 = j - j2;
        if (j3 <= 0) {
            return -1L;
        }
        long j4 = this.curSegmentReadOffset;
        long j5 = this.startOffsetInConnection;
        if (j5 < 0 || j4 < j5) {
            return 0L;
        }
        return (j4 - j5) / j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getStartOffsetInConnection() {
        return this.startOffsetInConnection;
    }
}
