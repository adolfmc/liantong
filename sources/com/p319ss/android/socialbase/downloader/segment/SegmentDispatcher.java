package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.DownloadHttpException;
import com.p319ss.android.socialbase.downloader.exception.RetryThrowable;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.HttpResponse;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.network.DownloadDnsManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.DownloadWatchDog;
import com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import com.p319ss.android.socialbase.downloader.utils.DownloadStenographer;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentDispatcher */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SegmentDispatcher implements DownloadDnsManager.Callback, ISegmentCallback {
    private static final int READ_WATCH_TIME = 2000;
    private static final String TAG = "SegmentDispatcher";
    private final BufferQueue bufferQueue;
    private long connectTimeout;
    private final boolean debug;
    private final DownloadInfo downloadInfo;
    private BaseException failedException;
    private HttpResponse firstBackupUrlHttpResponse;
    private final IDownloadRunnableCallback hostCallback;
    private volatile boolean isAllContentDownloaded;
    private long lastReconnectTime;
    private HttpResponse mainUrlHttpResponse;
    private float poorSpeedRatio;
    private long readTimeout;
    private int reconnectCount;
    private final SegmentStrategy strategy;
    private long totalLength;
    private int urlIndex;
    private final MultiSegmentWriter writer;
    private volatile boolean canceled = false;
    private volatile boolean paused = false;
    private final List<SegmentReader> readers = new ArrayList();
    private final List<UrlRecord> urlRecords = new ArrayList();
    private volatile boolean needWaitDnsResolve = true;
    private final LinkedList<Segment> toDispatchSegments = new LinkedList<>();
    private final List<Segment> dispatchedSegments = new ArrayList();
    private final Object firstConnectionLock = new Object();
    private volatile boolean allReaderFailed = false;
    private final DownloadWatchDog.IWatcher connectWatcher = new DownloadWatchDog.IWatcher() { // from class: com.ss.android.socialbase.downloader.segment.SegmentDispatcher.1
        private int watchTimes;

        @Override // com.p319ss.android.socialbase.downloader.thread.DownloadWatchDog.IWatcher
        public long onScheduleWatch() {
            if (SegmentDispatcher.this.canceled || SegmentDispatcher.this.paused) {
                return -1L;
            }
            synchronized (SegmentDispatcher.this) {
                if (SegmentDispatcher.this.mainUrlHttpResponse == null && SegmentDispatcher.this.firstBackupUrlHttpResponse == null) {
                    long j = SegmentDispatcher.this.connectTimeout;
                    if (j <= 0) {
                        return -1L;
                    }
                    this.watchTimes++;
                    SegmentReader findEarliestConnectTimeoutReader = SegmentDispatcher.this.findEarliestConnectTimeoutReader(false, System.currentTimeMillis(), j);
                    if (findEarliestConnectTimeoutReader != null) {
                        Log.i(SegmentDispatcher.TAG, "connectWatcher: switchUrl and reconnect");
                        SegmentDispatcher.this.trySwitchNextUrlForReader(findEarliestConnectTimeoutReader);
                        findEarliestConnectTimeoutReader.reconnect();
                        return ((this.watchTimes / SegmentDispatcher.this.urlRecords.size()) + 1) * j;
                    }
                    return j;
                }
                return -1L;
            }
        }
    };
    private final DownloadWatchDog.IWatcher readWatcher = new DownloadWatchDog.IWatcher() { // from class: com.ss.android.socialbase.downloader.segment.SegmentDispatcher.2
        @Override // com.p319ss.android.socialbase.downloader.thread.DownloadWatchDog.IWatcher
        public long onScheduleWatch() {
            return SegmentDispatcher.this.scheduleWatchRead();
        }
    };
    private final DownloadWatchDog watchDog = new DownloadWatchDog();
    private final DownloadStenographer stenographer = new DownloadStenographer();

    public SegmentDispatcher(@NonNull DownloadInfo downloadInfo, @NonNull SegmentStrategy segmentStrategy, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.downloadInfo = downloadInfo;
        this.strategy = segmentStrategy;
        this.bufferQueue = new BufferQueue(this.strategy.getBufferCount(), this.strategy.getBufferSize());
        this.hostCallback = iDownloadRunnableCallback;
        this.writer = new MultiSegmentWriter(downloadInfo, iDownloadRunnableCallback, this.bufferQueue);
        this.debug = DownloadSetting.obtain(downloadInfo.getId()).optInt("debug") == 1;
    }

    public boolean downloadSegments(List<Segment> list) throws BaseException, InterruptedException {
        try {
            initUrlRecords();
            initSegments(list);
            dispatchReadThread();
            initWatchDog();
            initDns();
            long currentTimeMillis = System.currentTimeMillis();
            waitFirstConnection();
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            this.downloadInfo.increaseAllConnectTime(currentTimeMillis2);
            this.downloadInfo.setFirstSpeedTime(currentTimeMillis2);
            if (!this.paused && !this.canceled) {
                this.hostCallback.checkSpaceOverflow(this.totalLength);
                initWatchDog2();
                writeSegments();
                return true;
            }
            if (!this.paused && !this.canceled) {
                Logger.m6469i(TAG, "finally pause");
                pause();
            }
            this.watchDog.release();
            return true;
        } finally {
            if (!this.paused && !this.canceled) {
                Logger.m6469i(TAG, "finally pause");
                pause();
            }
            this.watchDog.release();
        }
    }

    private void initSegments(List<Segment> list) {
        this.totalLength = this.downloadInfo.getTotalBytes();
        if (this.totalLength <= 0) {
            this.totalLength = this.downloadInfo.getExpectFileLength();
            Logger.m6469i(TAG, "initSegments: getExpectFileLength = " + this.totalLength);
        }
        synchronized (this) {
            this.toDispatchSegments.clear();
            if (list != null && !list.isEmpty()) {
                for (Segment segment : list) {
                    arrangeSegmentLocked(this.toDispatchSegments, new Segment(segment), false);
                }
                fixSegmentsLocked(this.toDispatchSegments);
                checkDownloadedBytesLocked(this.toDispatchSegments);
                Logger.m6469i(TAG, "initSegments: totalLength = " + this.totalLength);
            }
            arrangeSegmentLocked(this.toDispatchSegments, new Segment(0L, -1L), false);
            Logger.m6469i(TAG, "initSegments: totalLength = " + this.totalLength);
        }
    }

    private void waitFirstConnection() throws BaseException, InterruptedException {
        BaseException baseException;
        synchronized (this.firstConnectionLock) {
            if (this.mainUrlHttpResponse == null && this.firstBackupUrlHttpResponse == null) {
                this.firstConnectionLock.wait();
            }
        }
        if (this.mainUrlHttpResponse == null && this.firstBackupUrlHttpResponse == null && (baseException = this.failedException) != null) {
            throw baseException;
        }
    }

    private void writeSegments() throws BaseException {
        try {
            this.writer.loopAndWrite(this.bufferQueue);
        } catch (StreamClosedException unused) {
        } catch (BaseException e) {
            Logger.m6472e(TAG, "dispatchSegments: loopAndWrite e = " + e);
            onError(e);
            throw e;
        }
        if (this.paused || this.canceled) {
            return;
        }
        try {
            synchronized (this) {
                while (!this.toDispatchSegments.isEmpty()) {
                    Segment poll = this.toDispatchSegments.poll();
                    if (poll != null) {
                        arrangeSegmentLocked(this.dispatchedSegments, poll, true);
                    }
                }
                checkDownloadedBytesLocked(this.dispatchedSegments);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.allReaderFailed && this.failedException != null) {
            Logger.m6472e(TAG, "dispatchSegments: loopAndWrite  failedException = " + this.failedException);
            throw this.failedException;
        }
        if (this.downloadInfo.getCurBytes() != this.downloadInfo.getTotalBytes()) {
            DownloadMonitorHelper.monitorSegmentsError(this.downloadInfo, this.dispatchedSegments);
        }
        Logger.m6469i(TAG, "dispatchSegments::download finished");
    }

    private void checkDownloadedBytesLocked(List<Segment> list) {
        long downloadedBytes = SegmentUtils.getDownloadedBytes(list);
        Logger.m6469i(TAG, "checkDownloadBytes: getCurBytes = " + this.downloadInfo.getCurBytes() + ", totalBytes = " + this.downloadInfo.getTotalBytes() + ", downloadedBytes = " + downloadedBytes);
        if (downloadedBytes > this.downloadInfo.getTotalBytes() && this.downloadInfo.getTotalBytes() > 0) {
            downloadedBytes = this.downloadInfo.getTotalBytes();
        }
        if (this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes() || this.downloadInfo.getCurBytes() == downloadedBytes) {
            return;
        }
        this.downloadInfo.setCurBytes(downloadedBytes);
    }

    private void dispatchReadThread() {
        int i;
        if (this.totalLength <= 0 || this.needWaitDnsResolve) {
            i = 1;
        } else {
            i = this.strategy.getThreadCount();
            int segmentMinInitSize = (int) (this.totalLength / this.strategy.getSegmentMinInitSize());
            if (i > segmentMinInitSize) {
                i = segmentMinInitSize;
            }
        }
        Logger.m6469i(TAG, "dispatchReadThread: totalLength = " + this.totalLength + ", threadCount = " + i);
        if (i <= 0) {
            i = 1;
        }
        synchronized (this) {
            do {
                if (this.readers.size() >= i) {
                    break;
                }
                if (!this.paused && !this.canceled) {
                    dispatchReadThreadOnce(obtainUrl());
                }
                return;
            } while (!this.strategy.segmentOneByOne());
        }
    }

    private void initUrlRecords() {
        this.urlRecords.add(new UrlRecord(this.downloadInfo.getUrl(), true));
        List<String> backUpUrls = this.downloadInfo.getBackUpUrls();
        if (backUpUrls != null) {
            for (String str : backUpUrls) {
                if (!TextUtils.isEmpty(str)) {
                    this.urlRecords.add(new UrlRecord(str, false));
                }
            }
        }
        this.strategy.updateUrlCount(this.urlRecords.size());
    }

    private void initWatchDog() {
        SegmentStrategy segmentStrategy = this.strategy;
        this.connectTimeout = segmentStrategy.getConnectTimeout();
        this.readTimeout = segmentStrategy.getReadTimeout();
        this.poorSpeedRatio = segmentStrategy.getPoorSpeedRatio();
        int i = this.reconnectCount;
        if (i > 0) {
            this.watchDog.addWatcher(this.connectWatcher, i);
        }
    }

    private void initWatchDog2() {
        if (this.readTimeout > 0) {
            this.lastReconnectTime = System.currentTimeMillis();
            this.watchDog.addWatcher(this.readWatcher, 0L);
        }
    }

    private void initDns() {
        List<String> backUpUrls;
        int ipStrategy = this.strategy.getIpStrategy();
        if (ipStrategy <= 0) {
            this.needWaitDnsResolve = false;
            dispatchReadThread();
            return;
        }
        DownloadDnsManager downloadDnsManager = DownloadDnsManager.getInstance();
        downloadDnsManager.resolveDnsAsync(this.downloadInfo.getUrl(), this, 2000L);
        if (ipStrategy <= 2 || (backUpUrls = this.downloadInfo.getBackUpUrls()) == null) {
            return;
        }
        for (String str : backUpUrls) {
            if (!TextUtils.isEmpty(str)) {
                downloadDnsManager.resolveDnsAsync(str, this, 2000L);
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.network.DownloadDnsManager.Callback
    public void onDnsResolved(String str, List<InetAddress> list) {
        if (this.paused || this.canceled) {
            return;
        }
        List<UrlRecord> list2 = null;
        try {
            list2 = assembleIpAddress(str, list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this) {
            if (list2 != null) {
                addIpListLocked(str, list2);
            }
            this.needWaitDnsResolve = false;
            this.strategy.updateUrlCount(this.urlRecords.size());
            Log.i(TAG, "onDnsResolved: dispatchReadThread");
            dispatchReadThread();
        }
    }

    private void addIpListLocked(String str, List<UrlRecord> list) {
        int indexOfUrl;
        if (this.debug) {
            Iterator<UrlRecord> it = list.iterator();
            while (it.hasNext()) {
                Log.i(TAG, "addIpListLocked: urlRecord = " + it.next());
            }
        }
        int ipStrategy = this.strategy.getIpStrategy();
        if ((ipStrategy == 1 || ipStrategy == 3) && (indexOfUrl = indexOfUrl(str)) >= 0 && indexOfUrl < this.urlRecords.size()) {
            this.urlRecords.addAll(indexOfUrl + 1, list);
        } else {
            this.urlRecords.addAll(list);
        }
    }

    private int indexOfUrl(String str) {
        int size = this.urlRecords.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(this.urlRecords.get(i).url, str)) {
                return i;
            }
        }
        return -1;
    }

    private List<UrlRecord> assembleIpAddress(String str, List<InetAddress> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (InetAddress inetAddress : list) {
            if (inetAddress != null) {
                String hostAddress = inetAddress.getHostAddress();
                if (!TextUtils.isEmpty(hostAddress)) {
                    if (this.debug) {
                        Log.i(TAG, "onDnsResolved: ip = " + hostAddress);
                    }
                    UrlRecord urlRecord = new UrlRecord(str, hostAddress);
                    LinkedList linkedList = (LinkedList) linkedHashMap.get(urlRecord.ipFamily);
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        linkedHashMap.put(urlRecord.ipFamily, linkedList);
                    }
                    linkedList.add(urlRecord);
                    i++;
                }
            }
        }
        if (i > 0) {
            ArrayList arrayList = new ArrayList();
            do {
                z = false;
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    LinkedList linkedList2 = (LinkedList) entry.getValue();
                    if (linkedList2 != null && !linkedList2.isEmpty()) {
                        arrayList.add((UrlRecord) linkedList2.pollFirst());
                        i--;
                        z = true;
                    }
                }
                if (i <= 0) {
                    break;
                }
            } while (z);
            return arrayList;
        }
        return null;
    }

    private UrlRecord obtainUrl() {
        UrlRecord urlRecord;
        synchronized (this) {
            int size = this.urlIndex % this.urlRecords.size();
            if (this.strategy.urlBalance()) {
                this.urlIndex++;
            }
            urlRecord = this.urlRecords.get(size);
        }
        return urlRecord;
    }

    private void switchToNextUrl() {
        synchronized (this) {
            this.urlIndex++;
        }
    }

    private void arrangeSegmentLocked(List<Segment> list, Segment segment, boolean z) {
        long startOffset = segment.getStartOffset();
        int size = list.size();
        int i = 0;
        while (i < size && startOffset >= list.get(i).getStartOffset()) {
            i++;
        }
        list.add(i, segment);
        if (z) {
            segment.setIndex(size);
        }
    }

    private void fixSegmentsLocked(List<Segment> list) {
        Segment segment = list.get(0);
        long startOffset = segment.getStartOffset();
        if (startOffset > 0) {
            Segment segment2 = new Segment(0L, startOffset - 1);
            Log.w(TAG, "fixSegmentsLocked: first = " + segment + ", add new first = " + segment2);
            arrangeSegmentLocked(list, segment2, true);
        }
        Iterator<Segment> it = list.iterator();
        if (it.hasNext()) {
            Segment next = it.next();
            while (it.hasNext()) {
                Segment next2 = it.next();
                if (next.getEndOffset() < next2.getStartOffset() - 1) {
                    Logger.m6460w(TAG, "fixSegment: segment = " + next + ", new end = " + (next2.getStartOffset() - 1));
                    next.setEndOffset(next2.getStartOffset() - 1);
                }
                next = next2;
            }
        }
        Segment segment3 = list.get(list.size() - 1);
        long totalBytes = this.downloadInfo.getTotalBytes();
        if (totalBytes <= 0 || (segment3.getEndOffset() != -1 && segment3.getEndOffset() < totalBytes - 1)) {
            Logger.m6460w(TAG, "fixSegment: last segment = " + segment3 + ", new end=-1");
            segment3.setEndOffset(-1L);
        }
    }

    private void dispatchReadThreadOnce(UrlRecord urlRecord) {
        SegmentReader segmentReader = new SegmentReader(this.downloadInfo, this, this.bufferQueue, urlRecord, this.readers.size());
        this.readers.add(segmentReader);
        segmentReader.setFuture(DownloadComponentManager.getChunkDownloadThreadExecutorService().submit(segmentReader));
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void onReaderRun(SegmentReader segmentReader) {
        if (this.debug) {
            Logger.m6469i(TAG, "onReaderRun, threadIndex = " + segmentReader.threadIndex);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public Segment obtainSegment(SegmentReader segmentReader, UrlRecord urlRecord) {
        if (this.canceled || this.paused) {
            return null;
        }
        synchronized (this) {
            Segment obtainSegmentLocked = obtainSegmentLocked(segmentReader, urlRecord);
            if (obtainSegmentLocked != null) {
                obtainSegmentLocked.increaseCompetitor();
                if (obtainSegmentLocked.getCompetitor() > 1) {
                    return new Segment(obtainSegmentLocked);
                }
            }
            return obtainSegmentLocked;
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void unObtainSegment(SegmentReader segmentReader, Segment segment) {
        synchronized (this) {
            segment.decreaseCompetitor();
        }
    }

    private Segment obtainSegmentLocked(SegmentReader segmentReader, UrlRecord urlRecord) {
        while (!this.toDispatchSegments.isEmpty()) {
            Segment poll = this.toDispatchSegments.poll();
            if (poll != null) {
                arrangeSegmentLocked(this.dispatchedSegments, poll, true);
                if (getRemainReadBytes(poll) > 0 || this.totalLength <= 0) {
                    return poll;
                }
            }
        }
        clearCoveredSegmentLocked();
        Segment obtainChildSegmentFromMaxRemain = obtainChildSegmentFromMaxRemain(segmentReader, urlRecord);
        if (obtainChildSegmentFromMaxRemain != null && getRemainReadBytes(obtainChildSegmentFromMaxRemain) > 0) {
            arrangeSegmentLocked(this.dispatchedSegments, obtainChildSegmentFromMaxRemain, true);
            return obtainChildSegmentFromMaxRemain;
        }
        Segment obtainSegmentWhenNoNewSegment = obtainSegmentWhenNoNewSegment();
        if (obtainSegmentWhenNoNewSegment != null) {
            return obtainSegmentWhenNoNewSegment;
        }
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void onSegmentConnected(SegmentReader segmentReader, Segment segment, UrlRecord urlRecord, HttpResponse httpResponse) throws BaseException, RetryThrowable {
        synchronized (this) {
            if (this.canceled || this.paused) {
                throw new StreamClosedException("connected");
            }
            checkSegmentHttpResponseLocked(segmentReader, segment, urlRecord, httpResponse);
            segmentReader.setFailed(false);
            if (this.totalLength <= 0) {
                this.totalLength = this.downloadInfo.getTotalBytes();
                if (this.totalLength <= 0) {
                    this.totalLength = httpResponse.getTotalLength();
                }
                dispatchReadThread();
            } else if (this.strategy.segmentOneByOne()) {
                dispatchReadThread();
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void applySegment(SegmentReader segmentReader, Segment segment) throws BaseException {
        synchronized (this) {
            applySegmentLocked(segmentReader, segment);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void unApplySegment(SegmentReader segmentReader, Segment segment) {
        synchronized (this) {
            if (segment.owner == segmentReader) {
                Logger.m6469i(TAG, "unApplySegment " + segment);
                segment.setCurrentOffsetRead(segmentReader.getCurSegmentReadOffset());
                segment.owner = null;
                segmentReader.updateReadBytes();
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public IOutput createOutput(SegmentReader segmentReader, Segment segment) throws BaseException {
        IOutput stub;
        synchronized (this) {
            SegmentOutput segmentOutput = new SegmentOutput(this.downloadInfo, this.bufferQueue, segment);
            this.writer.assignOutput(segmentOutput);
            stub = segmentOutput.getStub();
        }
        return stub;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void onSegmentRetry(SegmentReader segmentReader, UrlRecord urlRecord, Segment segment, BaseException baseException, int i, int i2) {
        DownloadUtils.isResponseCodeError(baseException);
        int errorCode = baseException.getErrorCode();
        if (((errorCode == 1047 || errorCode == 1074 || errorCode == 1055) ? true : true) || i >= i2) {
            trySwitchNextUrlForReader(segmentReader);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void onSegmentFailed(SegmentReader segmentReader, UrlRecord urlRecord, Segment segment, BaseException baseException) {
        synchronized (this) {
            Logger.m6472e(TAG, "onSegmentFailed: segment = " + segment + ", e = " + baseException);
            segmentReader.setFailed(true);
            if (segmentReader.threadIndex == 0) {
                this.failedException = baseException;
            }
            if (isAllReaderFailedLocked()) {
                if (this.failedException == null) {
                    this.failedException = baseException;
                }
                this.allReaderFailed = true;
                onError(this.failedException);
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.ISegmentCallback
    public void onReaderExit(SegmentReader segmentReader) {
        Logger.m6469i(TAG, "onReaderExit: threadIndex = " + segmentReader.threadIndex);
        synchronized (this) {
            segmentReader.setExited(true);
            this.readers.remove(segmentReader);
            clearCoveredSegmentLocked();
            if (this.readers.isEmpty()) {
                onComplete();
            } else if (isAllContentDownloaded()) {
                Log.i(TAG, "onReaderExit: allContentDownloaded");
                for (SegmentReader segmentReader2 : this.readers) {
                    segmentReader2.close();
                }
                onComplete();
            }
        }
    }

    private void onError(BaseException baseException) {
        Logger.m6472e(TAG, "onError, e = " + baseException);
        this.failedException = baseException;
        this.bufferQueue.close();
        synchronized (this) {
            for (SegmentReader segmentReader : this.readers) {
                segmentReader.close();
            }
        }
    }

    private void onComplete() {
        Logger.m6469i(TAG, "onComplete");
        this.bufferQueue.close();
        synchronized (this.firstConnectionLock) {
            this.firstConnectionLock.notify();
        }
    }

    private boolean isAllReaderFailedLocked() {
        for (SegmentReader segmentReader : this.readers) {
            if (!segmentReader.isFailed()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean trySwitchNextUrlForReader(SegmentReader segmentReader) {
        synchronized (this) {
            UrlRecord findNextUrlLocked = findNextUrlLocked(segmentReader);
            if (findNextUrlLocked == null) {
                return false;
            }
            return segmentReader.switchUrlRecord(findNextUrlLocked);
        }
    }

    private UrlRecord findNextUrlLocked(SegmentReader segmentReader) {
        UrlRecord urlRecord;
        Iterator<UrlRecord> it = this.urlRecords.iterator();
        UrlRecord urlRecord2 = null;
        while (true) {
            if (!it.hasNext()) {
                urlRecord = null;
                break;
            }
            urlRecord = it.next();
            if (urlRecord != segmentReader.urlRecord && !urlRecord.isCurrentFailed()) {
                if (urlRecord2 == null) {
                    urlRecord2 = urlRecord;
                }
                if (urlRecord.getCurrentUsers() <= 0) {
                    break;
                }
            }
        }
        if (this.strategy.urlBalance()) {
            if (urlRecord != null) {
                return urlRecord;
            }
            if (this.strategy.urlBalanceStrictly()) {
                return null;
            }
            return urlRecord2;
        }
        return urlRecord2;
    }

    private void checkSegmentHttpResponseLocked(SegmentReader segmentReader, Segment segment, UrlRecord urlRecord, HttpResponse httpResponse) throws BaseException, RetryThrowable {
        SegmentReader segmentReader2 = segment.owner;
        if (segmentReader2 != null && segmentReader2 != segmentReader) {
            throw new SegmentApplyException(1, "segment already has an owner");
        }
        if (segmentReader.getStartOffsetInConnection() != segment.getCurrentOffsetRead()) {
            throw new SegmentApplyException(5, "applySegment");
        }
        if (!httpResponse.acceptPartial()) {
            if (segment.getCurrentOffsetRead() > 0) {
                int i = httpResponse.responseCode;
                throw new DownloadHttpException(1004, i, "1: response code error : " + httpResponse.responseCode + " segment=" + segment);
            }
            Logger.m6472e(TAG, "parseHttpResponse: segment.getCurrentOffsetRead = " + segment.getCurrentOffsetRead());
            if (!httpResponse.isResponseDataFromBegin()) {
                int i2 = httpResponse.responseCode;
                throw new DownloadHttpException(1004, i2, "2: response code error : " + httpResponse.responseCode + " segment=" + segment);
            }
        }
        if (urlRecord.isMainUrl) {
            if (this.mainUrlHttpResponse == null) {
                this.mainUrlHttpResponse = httpResponse;
                synchronized (this.firstConnectionLock) {
                    this.firstConnectionLock.notify();
                }
                IDownloadRunnableCallback iDownloadRunnableCallback = this.hostCallback;
                if (iDownloadRunnableCallback != null) {
                    iDownloadRunnableCallback.handleFirstConnection(urlRecord.url, httpResponse.connection, segment.getCurrentOffsetRead());
                }
                long totalLength = httpResponse.getTotalLength();
                if (totalLength > 0) {
                    for (Segment segment2 : this.dispatchedSegments) {
                        if (segment2.getEndOffset() <= 0 || segment2.getEndOffset() > totalLength - 1) {
                            segment2.setEndOffset(totalLength - 1);
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        validateHttpResponse(httpResponse);
        if (this.firstBackupUrlHttpResponse == null) {
            this.firstBackupUrlHttpResponse = httpResponse;
            if (this.downloadInfo.getTotalBytes() <= 0) {
                long totalLength2 = httpResponse.getTotalLength();
                Logger.m6469i(TAG, "checkSegmentHttpResponse:len=" + totalLength2 + ",url=" + urlRecord.url);
                this.downloadInfo.setTotalBytes(totalLength2);
            }
            synchronized (this.firstConnectionLock) {
                this.firstConnectionLock.notify();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
        if ((r8.getCurrentOffsetRead() - r21.getCurrentOffsetRead()) < (r12 / 2)) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void applySegmentLocked(com.p319ss.android.socialbase.downloader.segment.SegmentReader r20, com.p319ss.android.socialbase.downloader.segment.Segment r21) throws com.p319ss.android.socialbase.downloader.segment.SegmentApplyException {
        /*
            Method dump skipped, instructions count: 778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.segment.SegmentDispatcher.applySegmentLocked(com.ss.android.socialbase.downloader.segment.SegmentReader, com.ss.android.socialbase.downloader.segment.Segment):void");
    }

    private void validateHttpResponse(HttpResponse httpResponse) throws BaseException {
        HttpResponse httpResponse2 = this.mainUrlHttpResponse;
        if (httpResponse2 == null && (httpResponse2 = this.firstBackupUrlHttpResponse) == null) {
            return;
        }
        long totalLength = httpResponse.getTotalLength();
        long totalLength2 = httpResponse2.getTotalLength();
        if (totalLength != totalLength2) {
            String str = "total len not equals,len=" + totalLength + ",sLen=" + totalLength2 + ",code=" + httpResponse.responseCode + ",sCode=" + httpResponse2.responseCode + ",range=" + httpResponse.getContentRange() + ",sRange = " + httpResponse2.getContentRange() + ",url = " + httpResponse.url + ",sUrl=" + httpResponse2.url;
            Logger.m6472e(TAG, str);
            if (totalLength > 0 && totalLength2 > 0) {
                throw new BaseException(1074, str);
            }
        }
        String etag = httpResponse.getEtag();
        String etag2 = httpResponse2.getEtag();
        if (TextUtils.equals(etag, etag2)) {
            return;
        }
        String str2 = "etag not equals with main url, etag = " + etag + ", mainEtag = " + etag2;
        Logger.m6472e(TAG, str2);
        if (!TextUtils.isEmpty(etag) && !TextUtils.isEmpty(etag2) && !etag.equalsIgnoreCase(etag2)) {
            throw new BaseException(1074, str2);
        }
    }

    public void cancel() {
        Logger.m6469i(TAG, "cancel");
        this.canceled = true;
        synchronized (this) {
            for (SegmentReader segmentReader : this.readers) {
                segmentReader.close();
            }
        }
        this.writer.cancel();
        this.bufferQueue.close();
    }

    public void pause() {
        Logger.m6469i(TAG, "pause1");
        this.paused = true;
        synchronized (this) {
            for (SegmentReader segmentReader : this.readers) {
                segmentReader.close();
            }
        }
        this.writer.pause();
        this.bufferQueue.close();
    }

    private int indexOfSegmentLocked(long j) {
        int size = this.dispatchedSegments.size();
        for (int i = 0; i < size; i++) {
            Segment segment = this.dispatchedSegments.get(i);
            if (segment.getStartOffset() == j) {
                return i;
            }
            if (segment.getStartOffset() > j) {
                return -1;
            }
        }
        return -1;
    }

    private void clearCoveredSegmentLocked() {
        int size;
        if (this.totalLength > 0 && (size = this.dispatchedSegments.size()) > 1) {
            int i = 0;
            ArrayList<Segment> arrayList = null;
            int i2 = 1;
            while (i2 < size) {
                Segment segment = this.dispatchedSegments.get(i);
                Segment segment2 = this.dispatchedSegments.get(i2);
                if (segment.getCurrentOffsetRead() > segment2.getStartOffset() && segment2.getDownloadBytes() <= 0 && segment2.owner == null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(segment2);
                    if (this.debug) {
                        Log.w(TAG, "clearCovered, covered = " + segment2 + ", prev = " + segment);
                    }
                    i2++;
                } else {
                    if (segment2.getCurrentOffsetRead() > segment.getCurrentOffsetRead()) {
                        i++;
                    }
                    i2++;
                }
            }
            if (arrayList != null) {
                for (Segment segment3 : arrayList) {
                    this.dispatchedSegments.remove(segment3);
                    for (SegmentReader segmentReader : this.readers) {
                        if (segmentReader.curSegment == segment3) {
                            if (this.debug) {
                                Log.w(TAG, "clearCoveredSegmentLocked: reconnect, segment = " + segment3 + ", threadIndex = " + segmentReader.threadIndex);
                            }
                            segmentReader.reconnect(true);
                        }
                    }
                }
            }
        }
    }

    private boolean isAllContentDownloaded() {
        long j = this.totalLength;
        if (j <= 0) {
            this.isAllContentDownloaded = false;
            return false;
        }
        synchronized (this) {
            long firstOffset = SegmentUtils.getFirstOffset(this.dispatchedSegments);
            Logger.m6469i(TAG, "isAllContentDownloaded: firstOffset = " + firstOffset);
            if (firstOffset >= j) {
                this.isAllContentDownloaded = true;
                return true;
            }
            this.isAllContentDownloaded = false;
            return false;
        }
    }

    private Segment obtainChildSegmentFromMaxRemain(SegmentReader segmentReader, UrlRecord urlRecord) {
        long j;
        long j2;
        int size = this.dispatchedSegments.size();
        long j3 = -1;
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            long unconfirmedRemainBytes = getUnconfirmedRemainBytes(i2, size);
            if (unconfirmedRemainBytes > j3) {
                i = i2;
                j3 = unconfirmedRemainBytes;
            }
        }
        long segmentMinSize = this.strategy.getSegmentMinSize();
        long segmentMaxSize = this.strategy.getSegmentMaxSize();
        if (i < 0 || j3 <= segmentMinSize) {
            return null;
        }
        Segment segment = this.dispatchedSegments.get(i);
        int ratioSegmentStrategy = this.dispatchedSegments.size() < this.readers.size() ? 2 : this.strategy.getRatioSegmentStrategy();
        if (ratioSegmentStrategy == 1) {
            SegmentReader segmentReader2 = segment.owner;
            if (segmentReader2 != null) {
                long currentTimeMillis = System.currentTimeMillis();
                long j4 = currentTimeMillis - 4000;
                long recentDownloadSpeed = segmentReader2.getRecentDownloadSpeed(j4, currentTimeMillis);
                long recentDownloadSpeed2 = segmentReader.getRecentDownloadSpeed(j4, currentTimeMillis);
                float f = (recentDownloadSpeed <= 0 || recentDownloadSpeed2 <= 0) ? -1.0f : ((float) recentDownloadSpeed2) / ((float) (recentDownloadSpeed + recentDownloadSpeed2));
                if (f == -1.0f) {
                    long readBytes = segmentReader2.getReadBytes();
                    long readBytes2 = segmentReader.getReadBytes();
                    if (readBytes > 0 && readBytes2 > 0) {
                        f = ((float) readBytes2) / ((float) (readBytes + readBytes2));
                    }
                }
                if (f > 0.0f) {
                    float f2 = f * 0.9f;
                    long j5 = ((float) j3) * f2;
                    if (j5 < segmentMinSize) {
                        j5 = segmentMinSize;
                    }
                    if (segmentMaxSize <= 0 || j5 <= segmentMaxSize) {
                        j2 = 2;
                    } else {
                        j5 = segmentMaxSize;
                        j2 = 2;
                    }
                    long j6 = segmentMinSize / j2;
                    long j7 = j3 - j6;
                    if (j5 > j7) {
                        j5 = j7;
                    } else if (j5 < j6) {
                        j5 = j6;
                    }
                    Segment segment2 = new Segment(segment.getCurrentOffsetRead() + (j3 - j5), segment.getEndOffset());
                    Logger.m6469i(TAG, "obtainSegment: parent = " + segment + ", child = " + segment2 + ", maxRemainBytes = " + j3 + ", childLength = " + j5 + ", ratio = " + f2 + ", threadIndex = " + segmentReader.threadIndex);
                    return segment2;
                }
            }
        } else if (ratioSegmentStrategy == 2) {
            float downloadRatio = getDownloadRatio(segmentReader, urlRecord);
            long curBytes = ((float) (this.totalLength - this.downloadInfo.getCurBytes())) * downloadRatio;
            if (curBytes < segmentMinSize) {
                curBytes = segmentMinSize;
            }
            if (segmentMaxSize <= 0 || curBytes <= segmentMaxSize) {
                segmentMaxSize = curBytes;
                j = 2;
            } else {
                j = 2;
            }
            long j8 = segmentMinSize / j;
            long j9 = j3 - j8;
            if (segmentMaxSize > j9) {
                j8 = j9;
            } else if (segmentMaxSize >= j8) {
                j8 = segmentMaxSize;
            }
            Segment segment3 = new Segment(segment.getCurrentOffsetRead() + (j3 - j8), segment.getEndOffset());
            Logger.m6469i(TAG, "obtainSegment: parent = " + segment + ", child = " + segment3 + ", maxRemainBytes = " + j3 + ", childLength = " + j8 + ", ratio = " + downloadRatio + ", threadIndex = " + segmentReader.threadIndex);
            return segment3;
        }
        Segment segment4 = new Segment(segment.getCurrentOffsetRead() + (j3 / 2), segment.getEndOffset());
        Logger.m6469i(TAG, "obtainSegment: parent = " + segment + ",child = " + segment4);
        return segment4;
    }

    private float getDownloadRatio(SegmentReader segmentReader, UrlRecord urlRecord) {
        long readBytes = segmentReader.getReadBytes();
        int size = this.readers.size();
        if (size <= 1) {
            size = this.strategy.getThreadCount();
        }
        if (readBytes <= 0) {
            float mainRatio = this.strategy.getMainRatio();
            if (mainRatio <= 0.0f || mainRatio >= 1.0f) {
                mainRatio = 1.0f / size;
            }
            return segmentReader.threadIndex == 0 ? mainRatio : size > 1 ? (1.0f - mainRatio) / (size - 1) : 1.0f / size;
        }
        long totalReadBytes = getTotalReadBytes();
        return totalReadBytes > readBytes ? ((float) readBytes) / ((float) totalReadBytes) : 1.0f / size;
    }

    private long getTotalReadBytes() {
        long j = 0;
        for (SegmentReader segmentReader : this.readers) {
            j += segmentReader.getReadBytes();
        }
        return j;
    }

    private Segment obtainSegmentWhenNoNewSegment() {
        int i = 0;
        while (true) {
            Segment obtainLeastCompetitorSegment = obtainLeastCompetitorSegment();
            if (obtainLeastCompetitorSegment == null) {
                return null;
            }
            SegmentReader segmentReader = obtainLeastCompetitorSegment.owner;
            if (segmentReader == null) {
                return obtainLeastCompetitorSegment;
            }
            if (obtainLeastCompetitorSegment.getCompetitor() >= 2) {
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            markProgress(currentTimeMillis);
            if (currentTimeMillis - segmentReader.readStartTime > 2000 && isDownloadSpeedPoor(segmentReader, currentTimeMillis - 2000, currentTimeMillis, 500L, 1.0d)) {
                if (this.debug) {
                    Log.i(TAG, "obtainSegmentWhenNoNewSegment: isDownloadSpeedPoor segment = " + obtainLeastCompetitorSegment + ", owner.threadIndex = " + segmentReader.threadIndex);
                }
                return obtainLeastCompetitorSegment;
            }
            int i2 = i + 1;
            if (i > 2) {
                if (this.debug) {
                    Log.i(TAG, "obtainSegmentWhenNoNewSegment: waitCount > 2, return segment = " + obtainLeastCompetitorSegment);
                }
                return obtainLeastCompetitorSegment;
            }
            try {
                synchronized (this) {
                    wait(500L);
                }
                i = i2;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    private Segment obtainLeastCompetitorSegment() {
        int competitor;
        Segment segment = null;
        int i = Integer.MAX_VALUE;
        for (Segment segment2 : this.dispatchedSegments) {
            if (getRemainReadBytes(segment2) > 0 && (competitor = segment2.getCompetitor()) < i) {
                segment = segment2;
                i = competitor;
            }
        }
        return segment;
    }

    private long getUnconfirmedRemainBytes(int i, int i2) {
        Segment segment = this.dispatchedSegments.get(i);
        long remainReadBytes = getRemainReadBytes(segment);
        int i3 = i + 1;
        Segment segment2 = i3 < i2 ? this.dispatchedSegments.get(i3) : null;
        if (segment2 == null) {
            return remainReadBytes;
        }
        long startOffset = segment2.getStartOffset() - segment.getCurrentOffsetRead();
        return remainReadBytes == -1 ? startOffset : Math.min(remainReadBytes, startOffset);
    }

    private long getRemainReadBytes(Segment segment) {
        long remainReadBytes = segment.getRemainReadBytes();
        if (remainReadBytes == -1) {
            long j = this.totalLength;
            return j > 0 ? j - segment.getCurrentOffsetRead() : remainReadBytes;
        }
        return remainReadBytes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SegmentReader findEarliestConnectTimeoutReader(boolean z, long j, long j2) {
        SegmentReader segmentReader = null;
        for (SegmentReader segmentReader2 : this.readers) {
            if (segmentReader2.threadIndex != 0 || z) {
                if (segmentReader2.connectStartTime > 0 && segmentReader2.connectEndTime <= 0 && j - segmentReader2.connectStartTime > j2) {
                    if (segmentReader == null) {
                        segmentReader = segmentReader2;
                    } else if (segmentReader2.connectStartTime < segmentReader.connectStartTime) {
                        segmentReader = segmentReader2;
                    }
                }
            }
        }
        return segmentReader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long scheduleWatchRead() {
        if (this.canceled || this.paused) {
            return -1L;
        }
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            markProgress(currentTimeMillis);
            long readTimeout = this.strategy.getReadTimeout();
            if (readTimeout > 0) {
                long j = this.lastReconnectTime;
                if (j > 0 && currentTimeMillis - j > readTimeout && findPoorReadThreadAndReconnect(currentTimeMillis, readTimeout)) {
                    this.lastReconnectTime = currentTimeMillis;
                    this.reconnectCount++;
                }
            }
        }
        return 2000L;
    }

    private void markProgress(long j) {
        this.stenographer.markProgress(this.downloadInfo.getCurBytes(), j);
        for (SegmentReader segmentReader : this.readers) {
            segmentReader.markProgress(j);
        }
    }

    private boolean isDownloadSpeedPoor(SegmentReader segmentReader, long j, long j2, long j3, double d) {
        if (segmentReader.readStartTime > 0) {
            long recentDownloadSpeed = this.stenographer.getRecentDownloadSpeed(j, j2);
            int size = this.readers.size();
            long j4 = size > 0 ? recentDownloadSpeed / size : recentDownloadSpeed;
            long recentDownloadSpeed2 = segmentReader.getRecentDownloadSpeed(j, j2);
            if (recentDownloadSpeed2 < j3 || recentDownloadSpeed2 < j4 * d) {
                Log.i(TAG, "isDownloadSpeedPoor: totalSpeed = " + recentDownloadSpeed + ", threadAvgSpeed = " + j4 + ", poorSpeed = " + j3 + ", speed = " + recentDownloadSpeed2 + ",threadIndex = " + segmentReader.threadIndex);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean findPoorReadThreadAndReconnect(long j, long j2) {
        long j3 = j - j2;
        long recentDownloadSpeed = this.stenographer.getRecentDownloadSpeed(j3, j);
        int size = this.readers.size();
        if (size > 0) {
            recentDownloadSpeed /= size;
        }
        SegmentReader findPoorReadThread = findPoorReadThread(j3, j, Math.max(10.0f, ((float) recentDownloadSpeed) * this.poorSpeedRatio), size / 2);
        if (findPoorReadThread != null) {
            trySwitchNextUrlForReader(findPoorReadThread);
            Logger.m6460w(TAG, "handlePoorReadThread: reconnect for poor speed, threadIndex = " + findPoorReadThread.threadIndex);
            findPoorReadThread.reconnect();
            return true;
        }
        SegmentReader findEarliestConnectTimeoutReader = findEarliestConnectTimeoutReader(true, j, j2);
        if (findEarliestConnectTimeoutReader != null) {
            trySwitchNextUrlForReader(findEarliestConnectTimeoutReader);
            Logger.m6460w(TAG, "handlePoorReadThread: reconnect for connect timeout, threadIndex = " + findEarliestConnectTimeoutReader.threadIndex);
            findEarliestConnectTimeoutReader.reconnect();
            return true;
        }
        return false;
    }

    private SegmentReader findPoorReadThread(long j, long j2, long j3, int i) {
        int i2 = 0;
        long j4 = Long.MAX_VALUE;
        SegmentReader segmentReader = null;
        for (SegmentReader segmentReader2 : this.readers) {
            long j5 = 0;
            if (segmentReader2.readStartTime > 0) {
                i2++;
                if (segmentReader2.readStartTime < j) {
                    long recentDownloadSpeed = segmentReader2.getRecentDownloadSpeed(j, j2);
                    if (this.debug) {
                        Log.i(TAG, "findPoorReadThread: speed = " + recentDownloadSpeed + ", threadIndex = " + segmentReader2.threadIndex);
                        j5 = 0;
                    }
                    if (recentDownloadSpeed >= j5 && recentDownloadSpeed < j4) {
                        segmentReader = segmentReader2;
                        j4 = recentDownloadSpeed;
                    }
                }
            }
        }
        if (segmentReader == null || i2 < i || j4 >= j3) {
            return null;
        }
        Logger.m6469i(TAG, "findPoorReadThread: ----------- minSpeed = " + j4 + ", threadIndex = " + segmentReader.threadIndex);
        return segmentReader;
    }
}
