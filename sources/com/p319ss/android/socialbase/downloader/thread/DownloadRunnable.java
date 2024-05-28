package com.p319ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.constants.ByteInvalidRetryStatus;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.constants.RunStatus;
import com.p319ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.p319ss.android.socialbase.downloader.downloader.DownloadStatusHandler;
import com.p319ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.p319ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.exception.DownloadFileExistException;
import com.p319ss.android.socialbase.downloader.exception.DownloadOnlyWifiException;
import com.p319ss.android.socialbase.downloader.exception.DownloadOutOfSpaceException;
import com.p319ss.android.socialbase.downloader.exception.DownloadPauseReserveWifiException;
import com.p319ss.android.socialbase.downloader.exception.DownloadRetryNeedlessException;
import com.p319ss.android.socialbase.downloader.exception.RetryCheckStatus;
import com.p319ss.android.socialbase.downloader.exception.RetryThrowable;
import com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadEngine;
import com.p319ss.android.socialbase.downloader.impls.RetryDelayTimeParamCalculator;
import com.p319ss.android.socialbase.downloader.impls.RetryScheduler;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.DeviceBandwidthSampler;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.NetTrafficManager;
import com.p319ss.android.socialbase.downloader.network.NetworkQuality;
import com.p319ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool;
import com.p319ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.segment.Segment;
import com.p319ss.android.socialbase.downloader.segment.SegmentDispatcher;
import com.p319ss.android.socialbase.downloader.segment.SegmentStrategy;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.p319ss.android.socialbase.downloader.utils.DownloadSettingsUtils;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.downloader.thread.DownloadRunnable */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadRunnable implements IDownloadRunnableCallback, Runnable {
    private static final int MAX_RESET_RETAIN_RETRY_TIMES_COUNT = 3;
    private static final String TAG = "DownloadRunnable";
    private boolean acceptPartial;
    private boolean canResumeFromCache;
    private long curBytesNeedCheckSpaceOverFlow;
    private IDownloadDiskSpaceHandler diskSpaceHandler;
    private final IDownloadCache downloadCache;
    private DownloadInfo downloadInfo;
    private final DownloadTask downloadTask;
    private volatile BaseException errorException;
    private String existTargetFileName;
    private IDownloadHttpConnection firstGetConnection;
    private IDownloadHeadHttpConnection firstHeadConnection;
    private volatile DownloadResponseHandler firstHttpResponseHandler;
    private IDownloadForbiddenHandler forbiddenHandler;
    private final IChunkAdjustCalculator globalChunkAdjustCalculator;
    private final IChunkCntCalculator globalChunkCalculator;
    private final AtomicBoolean isAlive;
    private boolean isChunked;
    private boolean isResponseFromBegin;
    private boolean isSingleChunk;
    private Future mFuture;
    private long prepareDownloadTime;
    private AtomicInteger retainRetryTimes;
    private IRetryDelayTimeCalculator retryDelayTimeCalculator;
    private final DownloadSetting setting;
    private final DownloadStatusHandler statusHandler;
    private IChunkAdjustCalculator taskChunkAdjustCalculator;
    private IChunkCntCalculator taskChunkCalculator;
    private volatile boolean isTriedFixRangeNotSatisfiable = false;
    private final ArrayList<DownloadChunkRunnable> downloadChunkRunnableList = new ArrayList<>();
    private volatile RunStatus runStatus = RunStatus.RUN_STATUS_NONE;
    private volatile int bytesRetryCount = 5;
    private boolean needJumpToStart = false;
    private boolean firstHeadConnectionFailed = false;
    private boolean needCheckIfModified = false;
    private int resetRetainRetryTimesCount = 0;
    private volatile SegmentDispatcher segmentDispatcher = null;

    private boolean checkNeedRetryDelay() {
        return false;
    }

    public DownloadRunnable(DownloadTask downloadTask, Handler handler) {
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
            this.taskChunkCalculator = downloadTask.getChunkStrategy();
            this.taskChunkAdjustCalculator = downloadTask.getChunkAdjustCalculator();
            this.forbiddenHandler = downloadTask.getForbiddenHandler();
            this.diskSpaceHandler = downloadTask.getDiskSpaceHandler();
            this.retryDelayTimeCalculator = getRetryDelayTimeCalculator(downloadTask);
            this.setting = DownloadSetting.obtain(this.downloadInfo.getId());
        } else {
            this.setting = DownloadSetting.obtainGlobal();
        }
        updateRetainRetryTimes();
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.globalChunkCalculator = DownloadComponentManager.getChunkCntCalculator();
        this.globalChunkAdjustCalculator = DownloadComponentManager.getChunkAdjustCalculator();
        this.statusHandler = new DownloadStatusHandler(downloadTask, handler);
        this.isAlive = new AtomicBoolean(true);
    }

    private void updateRetainRetryTimes() {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo == null) {
            return;
        }
        int retryCount = downloadInfo.getRetryCount() - this.downloadInfo.getCurRetryTime();
        if (retryCount < 0) {
            retryCount = 0;
        }
        AtomicInteger atomicInteger = this.retainRetryTimes;
        if (atomicInteger == null) {
            this.retainRetryTimes = new AtomicInteger(retryCount);
        } else {
            atomicInteger.set(retryCount);
        }
    }

    public void pause() {
        this.runStatus = RunStatus.RUN_STATUS_PAUSE;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.pause();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.pause();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_PAUSE;
            endDownloadRunnable();
        }
        try {
            Iterator it = ((ArrayList) this.downloadChunkRunnableList.clone()).iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable downloadChunkRunnable = (DownloadChunkRunnable) it.next();
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.pause();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancel() {
        this.runStatus = RunStatus.RUN_STATUS_CANCELED;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.cancel();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.cancel();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_CANCELED;
            endDownloadRunnable();
        }
        cancelAllChunkRunnable();
    }

    public DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    private int calculateChunkCount(long j, List<DownloadChunk> list) {
        int i;
        int calculateChunkCount;
        if (isMultiChunkDownloadAvailable()) {
            if (!this.canResumeFromCache) {
                IChunkCntCalculator iChunkCntCalculator = this.taskChunkCalculator;
                if (iChunkCntCalculator != null) {
                    calculateChunkCount = iChunkCntCalculator.calculateChunkCount(j);
                } else {
                    calculateChunkCount = this.globalChunkCalculator.calculateChunkCount(j);
                }
                NetworkQuality currentNetworkQuality = NetTrafficManager.getInstance().getCurrentNetworkQuality();
                Logger.m6475d(TAG, String.format("NetworkQuality is : %s", currentNetworkQuality.name()));
                this.downloadInfo.setNetworkQuality(currentNetworkQuality.name());
                IChunkAdjustCalculator iChunkAdjustCalculator = this.taskChunkAdjustCalculator;
                if (iChunkAdjustCalculator != null) {
                    i = iChunkAdjustCalculator.calculateChunkCount(calculateChunkCount, currentNetworkQuality);
                } else {
                    i = this.globalChunkAdjustCalculator.calculateChunkCount(calculateChunkCount, currentNetworkQuality);
                }
            } else if (list != null) {
                i = list.size();
            } else {
                i = this.downloadInfo.getChunkCount();
            }
            if (i <= 0) {
                i = 1;
            }
        } else {
            i = 1;
        }
        if (Logger.debug()) {
            Logger.m6475d(TAG, String.format("chunk count : %s for %s contentLen:%s", String.valueOf(i), this.downloadInfo.getName(), String.valueOf(j)));
        }
        return i;
    }

    private boolean checkTaskStatusValid() {
        int status = this.downloadInfo.getStatus();
        if (status == 1 || this.downloadInfo.canSkipStatusHandler()) {
            return true;
        }
        if (status == -2 || status == -4) {
            return false;
        }
        onError(new BaseException(1000, "The download Task can't start, because its status is not prepare:" + status));
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x010e, code lost:
        if (r9.setting.optBugFix("fix_file_exist_update_download_info") != false) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0104  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkTaskCache() throws com.p319ss.android.socialbase.downloader.exception.DownloadFileExistException {
        /*
            Method dump skipped, instructions count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadRunnable.checkTaskCache():void");
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadComponentManager.onDownloadTaskStart(this.downloadTask, 3);
        try {
            DeviceBandwidthSampler.getInstance().startSampling();
            runInner();
            DeviceBandwidthSampler.getInstance().stopSampling();
            DownloadComponentManager.onDownloadTaskFinish(this.downloadTask, 3);
        } catch (Throwable th) {
            DeviceBandwidthSampler.getInstance().stopSampling();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e A[LOOP:0: B:26:0x005e->B:56:0x005e, LOOP_START] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void runInner() {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadRunnable.runInner():void");
    }

    private void downloadInner() {
        boolean z;
        long j;
        List<DownloadChunk> downloadChunk;
        try {
            this.runStatus = RunStatus.RUN_STATUS_NONE;
            this.downloadInfo.updateStartDownloadTime();
            this.downloadInfo.resetRealStartDownloadTime();
            long currentTimeMillis = System.currentTimeMillis();
            this.downloadInfo.setFirstSpeedTime(-1L);
            try {
                checkTaskCache();
                z = false;
            } catch (DownloadFileExistException e) {
                Logger.m6475d(TAG, "file exist " + e.getExistTargetFileName());
                this.existTargetFileName = e.getExistTargetFileName();
                z = true;
            }
            if (!this.needJumpToStart) {
                this.statusHandler.onStart();
            }
            this.needJumpToStart = false;
            if (checkIsStoppedByUser()) {
                return;
            }
            if (!TextUtils.isEmpty(this.existTargetFileName) && z) {
                if (this.downloadInfo.isExpiredRedownload()) {
                    this.needCheckIfModified = DownloadUtils.cacheExpired(this.downloadInfo);
                }
                if (!this.needCheckIfModified) {
                    finishWithFileExist();
                    return;
                }
            }
            while (!checkIsStoppedByUser()) {
                try {
                    try {
                        checkSavePathValid();
                        checkHasAnotherSameTask();
                        checkWifiTaskValid();
                        downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
                        checkTaskCanResume();
                    } catch (BaseException e2) {
                        Logger.m6460w(TAG, "downloadInner: baseException = " + e2);
                        if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                            if (e2.getErrorCode() != 1025 && e2.getErrorCode() != 1009) {
                                if (canRetry(e2)) {
                                    if (DownloadUtils.isHttpDataDirtyError(e2)) {
                                        clearCurrentDownloadData();
                                        j = 0;
                                    } else {
                                        j = 0;
                                    }
                                    if (onRetry(e2, j) == RetryCheckStatus.RETURN) {
                                        closeConnection();
                                        return;
                                    }
                                    closeConnection();
                                } else {
                                    onError(e2);
                                }
                            }
                            this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
                            closeConnection();
                            return;
                        }
                    } catch (RetryThrowable e3) {
                        Logger.m6460w(TAG, "downloadInner: retry throwable for " + e3.getErrorMsg());
                        if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                            if (this.retainRetryTimes != null && this.retainRetryTimes.get() > 0) {
                                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
                                this.downloadInfo.setStatus(5);
                            } else if (this.retainRetryTimes == null) {
                                onError(new BaseException(1043, "retry for Throwable, but retain retry time is NULL, last error is" + e3.getErrorMsg()));
                            } else if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                                this.downloadInfo.setStatus(5);
                                this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                            } else {
                                onError(new BaseException(1018, String.format("retry for Throwable, but retry Time %s all used, last error is %s", String.valueOf(this.downloadInfo.getRetryCount()), e3.getErrorMsg())));
                            }
                            closeConnection();
                        }
                    }
                } catch (DownloadFileExistException unused) {
                    finishWithFileExist();
                }
                if (downloadSegments()) {
                    Logger.m6469i(TAG, "downloadSegments return");
                    closeConnection();
                    return;
                }
                String connectionUrl = this.downloadInfo.getConnectionUrl();
                if (checkIsStoppedByUser()) {
                    closeConnection();
                    return;
                }
                long firstOffset = this.canResumeFromCache ? DownloadUtils.getFirstOffset(this.downloadInfo) : 0L;
                DownloadChunk createFirstDownloadChunk = createFirstDownloadChunk(this.downloadInfo, firstOffset);
                List<HttpHeader> extraHeaders = getExtraHeaders(createFirstDownloadChunk);
                DownloadUtils.addThrottleNetSpeed(extraHeaders, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(extraHeaders, this.downloadInfo);
                this.downloadInfo.setPreconnectLevel(0);
                long currentTimeMillis2 = System.currentTimeMillis();
                try {
                    doFirstConnect(connectionUrl, extraHeaders, firstOffset);
                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - currentTimeMillis2);
                    if (checkIsStoppedByUser()) {
                        closeConnection();
                        return;
                    }
                    long totalBytes = this.downloadInfo.getTotalBytes();
                    checkSpaceOverflow(totalBytes);
                    int calculateChunkCount = calculateChunkCount(totalBytes, downloadChunk);
                    if (checkIsStoppedByUser()) {
                        closeConnection();
                        return;
                    } else if (calculateChunkCount <= 0) {
                        throw new BaseException(1032, "chunkCount is 0");
                    } else {
                        this.isSingleChunk = calculateChunkCount == 1;
                        if (this.isSingleChunk) {
                            if (this.firstGetConnection == null) {
                                try {
                                    currentTimeMillis2 = System.currentTimeMillis();
                                    createFirstGetConnection(connectionUrl, extraHeaders);
                                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - currentTimeMillis2);
                                } finally {
                                }
                            }
                            if (checkIsStoppedByUser()) {
                                closeConnection();
                                return;
                            }
                            this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - currentTimeMillis);
                            resetRetainRetryTimes();
                            handleResponseWithSingleChunk(createFirstDownloadChunk, connectionUrl, this.firstGetConnection);
                        } else {
                            if (!this.downloadInfo.isNeedReuseFirstConnection()) {
                                closeFirstConnection();
                            }
                            if (checkIsStoppedByUser()) {
                                closeConnection();
                                return;
                            }
                            resetRetainRetryTimes();
                            this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - currentTimeMillis);
                            if (this.canResumeFromCache) {
                                handleResponseWithMultiChunkFromResume(calculateChunkCount, downloadChunk);
                            } else {
                                handleResponseMultiChunkFromBegin(totalBytes, calculateChunkCount);
                            }
                        }
                        closeConnection();
                        return;
                    }
                } finally {
                }
            }
        } finally {
            endDownloadRunnable();
        }
    }

    private void finishWithFileExist() {
        Logger.m6475d(TAG, "finishWithFileExist");
        if (DownloadSetting.obtainGlobal().optBugFix("fix_end_for_file_exist_error", true)) {
            if (this.existTargetFileName.equals(this.downloadInfo.getName())) {
                this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
            } else {
                this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
            }
        } else if (this.existTargetFileName.equals(this.downloadInfo.getTargetFilePath())) {
            this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
        } else {
            this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
        }
    }

    private boolean downloadSegments() throws BaseException, InterruptedException {
        if (!this.downloadInfo.isExpiredRedownload() && this.downloadInfo.getChunkCount() == 1 && this.downloadInfo.getThrottleNetSpeed() <= 0) {
            JSONObject optJSONObject = DownloadSetting.obtain(this.downloadInfo.getId()).optJSONObject("segment_config");
            List<Segment> segments = this.downloadCache.getSegments(this.downloadInfo.getId());
            if (this.downloadInfo.getCurBytes() > 0) {
                if (segments == null || segments.isEmpty()) {
                    return false;
                }
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
            }
            if (optJSONObject == null) {
                return false;
            }
            this.segmentDispatcher = new SegmentDispatcher(this.downloadInfo, SegmentStrategy.from(optJSONObject), this);
            if (checkIsStoppedByUser()) {
                Logger.m6469i(TAG, "downloadSegments: is stopped by user");
                if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                    this.segmentDispatcher.cancel();
                } else {
                    this.segmentDispatcher.pause();
                }
                return true;
            }
            return this.segmentDispatcher.downloadSegments(segments);
        }
        return false;
    }

    private void resetRetainRetryTimes() {
        if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt("reset_retain_retry_times", 0) != 1 || this.resetRetainRetryTimesCount >= 3) {
            return;
        }
        this.retainRetryTimes.set(this.downloadInfo.isBackUpUrlUsed() ? this.downloadInfo.getBackUpUrlRetryCount() : this.downloadInfo.getRetryCount());
        this.resetRetainRetryTimesCount++;
    }

    private void doFirstConnect(String str, List<HttpHeader> list, long j) throws BaseException, RetryThrowable {
        createFirstHeadConnection(str, list, j);
        IDownloadHeadHttpConnection iDownloadHeadHttpConnection = this.firstHeadConnection;
        if (iDownloadHeadHttpConnection != null) {
            try {
                handleFirstConnection(str, iDownloadHeadHttpConnection, j);
            } catch (Throwable unused) {
                this.firstHeadConnectionFailed = true;
            }
        }
        if (this.firstHeadConnection == null || this.firstHeadConnectionFailed) {
            createFirstGetConnection(str, list);
            handleFirstConnection(str, this.firstGetConnection, j);
        }
    }

    private void endDownloadRunnable() {
        boolean z;
        boolean z2;
        Logger.m6475d(TAG, "endDownloadRunnable::runStatus=" + this.runStatus);
        boolean z3 = (this.runStatus == RunStatus.RUN_STATUS_PAUSE || this.runStatus == RunStatus.RUN_STATUS_CANCELED) ? false : true;
        try {
            z = doTaskStatusHandle();
            z2 = false;
        } catch (Exception e) {
            if (e instanceof BaseException) {
                this.statusHandler.onError((BaseException) e);
            } else {
                this.statusHandler.onError(new BaseException(1046, e));
            }
            z = true;
            z2 = true;
        }
        if (z || z2) {
            this.isAlive.set(false);
            if (z3) {
                try {
                    AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
                    if (downloadEngine != null) {
                        downloadEngine.removeDownloadRunnable(this);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    IDownloadMonitorDepend monitorDepend = this.downloadTask.getMonitorDepend();
                    DownloadInfo downloadInfo = this.downloadInfo;
                    BaseException baseException = new BaseException(1014, DownloadUtils.getErrorMsgWithTagPrefix(th, "removeDownloadRunnable"));
                    DownloadInfo downloadInfo2 = this.downloadInfo;
                    DownloadMonitorHelper.monitorSendWithTaskMonitor(monitorDepend, downloadInfo, baseException, downloadInfo2 != null ? downloadInfo2.getStatus() : 0);
                    return;
                }
            }
            return;
        }
        this.needJumpToStart = true;
        Logger.m6475d(TAG, "jump to restart");
    }

    private void createFirstHeadConnection(String str, List<HttpHeader> list, long j) throws BaseException, RetryThrowable {
        FakeDownloadHeadHttpConnection cachedHeadConnection;
        boolean z = true;
        if (this.downloadInfo.getChunkCount() == 1 && (cachedHeadConnection = DownloadConnectionPool.getInstance().getCachedHeadConnection(str, list)) != null) {
            this.firstHeadConnection = cachedHeadConnection;
            this.downloadInfo.setPreconnectLevel(1);
        }
        if (this.firstHeadConnection == null && !this.firstHeadConnectionFailed && this.downloadInfo.isHeadConnectionAvailable()) {
            try {
                int optInt = this.setting.optInt("net_lib_strategy");
                if (this.setting.optInt("monitor_download_connect", 0) <= 0) {
                    z = false;
                }
                this.firstHeadConnection = DownloadComponentManager.downloadWithHeadConnection(str, list, optInt, z, this.downloadInfo);
            } catch (Throwable th) {
                this.downloadInfo.setHeadConnectionException(DownloadUtils.getThrowableMsg(th));
            }
        }
    }

    private void createFirstGetConnection(String str, List<HttpHeader> list) throws BaseException, RetryThrowable {
        if (this.firstGetConnection != null) {
            return;
        }
        FakeDownloadHttpConnection cachedDownloadConnection = this.downloadInfo.getChunkCount() == 1 ? DownloadConnectionPool.getInstance().getCachedDownloadConnection(str, list) : null;
        try {
            if (cachedDownloadConnection != null) {
                setHttpResponseStatus(this.firstGetConnection);
                this.downloadInfo.setPreconnectLevel(2);
                this.firstGetConnection = cachedDownloadConnection;
            } else {
                try {
                    this.firstGetConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), str, null, list, this.setting.optInt("net_lib_strategy"), this.setting.optInt("monitor_download_connect", 0) > 0, this.downloadInfo);
                } catch (BaseException e) {
                    throw e;
                } catch (Throwable th) {
                    if (this.downloadInfo.isExpiredRedownload() && DownloadUtils.isResponseCode304Error(th) && DownloadUtils.hasDownloadCacheHeader(list)) {
                        Logger.m6475d(TAG, "dcache=execepiton responseCode=304 lastModified not changed, use local file.. old cacheControl=" + this.downloadInfo.getCacheControl());
                        long parserMaxAge = DownloadUtils.parserMaxAge(this.downloadInfo.getCacheControl());
                        if (parserMaxAge <= 0) {
                            parserMaxAge = DownloadSetting.obtain(this.downloadInfo.getId()).optInt("default_304_max_age", 300);
                        }
                        this.downloadInfo.setCacheExpiredTime(System.currentTimeMillis() + (parserMaxAge * 1000));
                        throw new DownloadFileExistException(this.existTargetFileName);
                    } else if (DownloadUtils.isResponseCode416Error(th)) {
                        handleResponseCodeError("", "http code 416");
                    } else if (DownloadUtils.isResponseCode412Error(th)) {
                        handleResponseCodeError("", "http code 412");
                    } else {
                        DownloadUtils.parseException(th, "CreateFirstConnection");
                    }
                }
            }
            if (this.firstGetConnection == null) {
                throw new BaseException(1022, new IOException("download can't continue, firstConnection is null"));
            }
        } finally {
            setHttpResponseStatus(this.firstGetConnection);
        }
    }

    private void closeFirstHeadConnection() {
        IDownloadHeadHttpConnection iDownloadHeadHttpConnection = this.firstHeadConnection;
        if (iDownloadHeadHttpConnection != null) {
            iDownloadHeadHttpConnection.cancel();
            this.firstHeadConnection = null;
        }
    }

    private void closeFirstConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.firstGetConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.end();
            this.firstGetConnection = null;
        }
    }

    private void closeConnection() {
        closeFirstHeadConnection();
        closeFirstConnection();
    }

    public static DownloadChunk createFirstDownloadChunk(DownloadInfo downloadInfo, long j) {
        return new DownloadChunk.Builder(downloadInfo.getId()).chunkIndex(-1).startOffset(0L).oldOffset(j).currentOffset(j).endOffset(0L).contentLength(downloadInfo.getTotalBytes() - j).build();
    }

    private List<HttpHeader> getExtraHeaders(DownloadChunk downloadChunk) {
        List<HttpHeader> addRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), downloadChunk);
        if (this.downloadInfo.isExpiredRedownload() && this.needCheckIfModified && this.downloadInfo.getLastModified() != null) {
            addRangeHeader.add(new HttpHeader("if-modified-since", this.downloadInfo.getLastModified()));
            addRangeHeader.add(new HttpHeader("download-tc21-1-15", "download-tc21-1-15"));
            String str = TAG;
            Logger.m6475d(str, "dcache::add head IF_MODIFIED_SINCE=" + this.downloadInfo.getLastModified());
        }
        return addRangeHeader;
    }

    private void handleFirstResponse() throws BaseException {
        if (this.firstHttpResponseHandler != null) {
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                this.downloadInfo.setStatus(-4);
                this.firstHttpResponseHandler.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                this.downloadInfo.setStatus(-2);
                this.firstHttpResponseHandler.pause();
            } else {
                this.firstHttpResponseHandler.handleResponse();
            }
        }
    }

    private boolean isStoppedStatus() {
        return this.runStatus == RunStatus.RUN_STATUS_CANCELED || this.runStatus == RunStatus.RUN_STATUS_PAUSE;
    }

    private boolean checkIsStoppedByUser() {
        if (isStoppedStatus() || this.downloadInfo.getStatus() == -2) {
            if (isStoppedStatus()) {
                return true;
            }
            if (this.downloadInfo.getStatus() == -2) {
                this.runStatus = RunStatus.RUN_STATUS_PAUSE;
                return true;
            } else if (this.downloadInfo.getStatus() == -4) {
                this.runStatus = RunStatus.RUN_STATUS_CANCELED;
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean doTaskStatusHandle() {
        if (this.runStatus == RunStatus.RUN_STATUS_ERROR) {
            this.statusHandler.onError(this.errorException);
        } else if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
            this.statusHandler.onCancel();
        } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
            this.statusHandler.onPause();
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_RIGHT_NOW) {
            try {
                this.statusHandler.onCompleteForFileExist();
            } catch (BaseException e) {
                this.statusHandler.onError(e);
            }
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_FOR_FILE_EXIST) {
            try {
                this.statusHandler.onCompleteForFileExist(this.existTargetFileName);
            } catch (BaseException e2) {
                this.statusHandler.onError(e2);
            }
        } else if (this.runStatus == RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET) {
            this.statusHandler.onRetry(this.errorException, false);
            return false;
        } else if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
            return true;
        } else {
            if (this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY && !isAllChunkDownloadComplete()) {
                Logger.m6475d(TAG, "doTaskStatusHandle retryDelay");
                startRetryDelayAlarm();
                return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY;
            }
            try {
                if (!checkCompletedByteValid()) {
                    return false;
                }
                this.statusHandler.onCompleted();
                RetryScheduler.getInstance().scheduleRetryWhenHasTaskSucceed();
            } catch (Throwable th) {
                onError(new BaseException(1008, DownloadUtils.getErrorMsgWithTagPrefix(th, "doTaskStatusHandle onComplete")));
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isAllChunkDownloadComplete() {
        /*
            r7 = this;
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r7.downloadInfo
            int r0 = r0.getChunkCount()
            r1 = 0
            r2 = 1
            if (r0 > r2) goto L28
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r7.downloadInfo
            long r3 = r0.getCurBytes()
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L27
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r7.downloadInfo
            long r3 = r0.getCurBytes()
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r7.downloadInfo
            long r5 = r0.getTotalBytes()
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L27
            return r2
        L27:
            return r1
        L28:
            com.ss.android.socialbase.downloader.downloader.IDownloadCache r0 = r7.downloadCache
            com.ss.android.socialbase.downloader.model.DownloadInfo r3 = r7.downloadInfo
            int r3 = r3.getId()
            java.util.List r0 = r0.getDownloadChunk(r3)
            if (r0 == 0) goto L57
            int r3 = r0.size()
            if (r3 > r2) goto L3d
            goto L57
        L3d:
            java.util.Iterator r0 = r0.iterator()
        L41:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L56
            java.lang.Object r3 = r0.next()
            com.ss.android.socialbase.downloader.model.DownloadChunk r3 = (com.p319ss.android.socialbase.downloader.model.DownloadChunk) r3
            if (r3 == 0) goto L55
            boolean r3 = r3.hasNoBytesDownload()
            if (r3 != 0) goto L41
        L55:
            return r1
        L56:
            return r2
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadRunnable.isAllChunkDownloadComplete():boolean");
    }

    private boolean checkCompletedByteValid() {
        if (this.downloadInfo.isChunked()) {
            DownloadInfo downloadInfo = this.downloadInfo;
            downloadInfo.setTotalBytes(downloadInfo.getCurBytes());
        }
        String str = TAG;
        Logger.m6469i(str, "checkCompletedByteValid: downloadInfo.getCurBytes() = " + this.downloadInfo.getCurBytes() + ",  downloadInfo.getTotalBytes() = " + this.downloadInfo.getTotalBytes());
        if (this.downloadInfo.getCurBytes() > 0) {
            if (this.downloadInfo.isIgnoreDataVerify()) {
                return true;
            }
            if (this.downloadInfo.getTotalBytes() > 0 && this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes()) {
                return true;
            }
        }
        this.downloadInfo.setByteInvalidRetryStatus(ByteInvalidRetryStatus.BYTE_INVALID_RETRY_STATUS_RESTART);
        this.downloadInfo.reset();
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        return false;
    }

    private void startRetryDelayAlarm() {
        this.runStatus = RunStatus.RUN_STATUS_NONE;
    }

    private long getDelayTime() {
        return this.retryDelayTimeCalculator.calculateRetryDelayTime(this.downloadInfo.getCurRetryTimeInTotal(), this.downloadInfo.getTotalRetryCount());
    }

    private void checkHasAnotherSameTask() throws RetryThrowable, BaseException {
        AbsDownloadEngine downloadEngine;
        int id = this.downloadInfo.getId();
        int downloadId = DownloadComponentManager.getDownloadId(this.downloadInfo);
        if (this.downloadInfo.isDownloaded() && !this.downloadInfo.isExpiredRedownload() && !this.needCheckIfModified) {
            throw new BaseException(1009, "file has downloaded");
        }
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(downloadId);
        if (downloadInfo == null || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null || downloadInfo.getId() == id || !downloadInfo.equalsTask(this.downloadInfo)) {
            return;
        }
        if (downloadEngine.isDownloading(downloadInfo.getId())) {
            this.downloadCache.removeDownloadTaskData(id);
            throw new BaseException(1025, "another same task is downloading");
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(downloadId);
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.downloadCache.removeDownloadTaskData(downloadId);
        if (downloadInfo == null || !downloadInfo.isBreakpointAvailable()) {
            return;
        }
        this.downloadInfo.copyFromCacheData(downloadInfo, false);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (downloadChunk != null) {
            for (DownloadChunk downloadChunk2 : downloadChunk) {
                downloadChunk2.setId(id);
                this.downloadCache.addDownloadChunk(downloadChunk2);
            }
        }
        throw new RetryThrowable("retry task because id generator changed");
    }

    private void handleResponseWithMultiChunkFromResume(int i, List<DownloadChunk> list) throws BaseException {
        if (list.size() != i) {
            throw new BaseException(1033, new IllegalArgumentException());
        }
        handleResponseWithMultiChunk(list, this.downloadInfo.getTotalBytes());
    }

    private void handleResponseMultiChunkFromBegin(long j, int i) throws BaseException {
        long j2 = j / i;
        int id = this.downloadInfo.getId();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        long j3 = 0;
        while (i2 < i) {
            DownloadChunk build = new DownloadChunk.Builder(id).chunkIndex(i2).startOffset(j3).oldOffset(j3).currentOffset(j3).endOffset(i2 == i + (-1) ? 0L : (j3 + j2) - 1).build();
            arrayList.add(build);
            this.downloadCache.addDownloadChunk(build);
            j3 += j2;
            i2++;
        }
        this.downloadInfo.setChunkCount(i);
        this.downloadCache.updateChunkCount(id, i);
        handleResponseWithMultiChunk(arrayList, j);
    }

    private void handleResponseWithMultiChunk(List<DownloadChunk> list, long j) throws BaseException {
        long endOffset;
        for (DownloadChunk downloadChunk : list) {
            if (downloadChunk != null) {
                if (downloadChunk.getEndOffset() == 0) {
                    endOffset = j - downloadChunk.getCurrentOffset();
                } else {
                    endOffset = (downloadChunk.getEndOffset() - downloadChunk.getCurrentOffset()) + 1;
                }
                if (endOffset > 0) {
                    downloadChunk.setContentLength(endOffset);
                    if (this.downloadInfo.isNeedReuseFirstConnection() && this.firstGetConnection != null && (!this.downloadInfo.isHeadConnectionAvailable() || this.firstHeadConnectionFailed)) {
                        if (downloadChunk.getChunkIndex() == 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this.firstGetConnection, this));
                        } else if (downloadChunk.getChunkIndex() > 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                        }
                    } else {
                        this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                    }
                }
            }
        }
        if (DownloadExpSwitchCode.isSwitchEnable(64)) {
            ArrayList arrayList = new ArrayList(this.downloadChunkRunnableList.size());
            Iterator<DownloadChunkRunnable> it = this.downloadChunkRunnableList.iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable next = it.next();
                if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                    next.cancel();
                } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                    next.pause();
                } else {
                    arrayList.add(next);
                }
            }
            try {
                List<Future> executeFutureTasks = DefaultDownloadEngine.executeFutureTasks(arrayList);
                for (Runnable runnable = (Runnable) arrayList.remove(0); runnable != null; runnable = DefaultDownloadEngine.getUnstartedTask(executeFutureTasks)) {
                    if (checkIsStoppedByUser()) {
                        return;
                    }
                    runnable.run();
                }
                if (executeFutureTasks == null || executeFutureTasks.isEmpty()) {
                    return;
                }
                for (Future future : executeFutureTasks) {
                    if (future != null && !future.isDone()) {
                        try {
                            future.get();
                        } catch (Throwable unused) {
                        }
                    }
                }
                return;
            } catch (Throwable unused2) {
                return;
            }
        }
        ArrayList arrayList2 = new ArrayList(this.downloadChunkRunnableList.size());
        Iterator<DownloadChunkRunnable> it2 = this.downloadChunkRunnableList.iterator();
        while (it2.hasNext()) {
            DownloadChunkRunnable next2 = it2.next();
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                next2.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                next2.pause();
            } else {
                arrayList2.add(Executors.callable(next2));
            }
        }
        if (checkIsStoppedByUser()) {
            return;
        }
        try {
            DefaultDownloadEngine.invokeFutureTasks(arrayList2);
        } catch (InterruptedException e) {
            throw new BaseException(1020, e);
        }
    }

    private void handleResponseWithSingleChunk(DownloadChunk downloadChunk, String str, IDownloadHttpConnection iDownloadHttpConnection) throws BaseException {
        downloadChunk.setContentLength(this.downloadInfo.getTotalBytes() - downloadChunk.getCurrentOffset());
        this.downloadInfo.setChunkCount(1);
        this.downloadCache.updateChunkCount(this.downloadInfo.getId(), 1);
        this.firstHttpResponseHandler = new DownloadResponseHandler(this.downloadInfo, str, iDownloadHttpConnection, downloadChunk, this);
        handleFirstResponse();
    }

    private boolean isMultiChunkDownloadAvailable() {
        DownloadInfo downloadInfo = this.downloadInfo;
        return (downloadInfo == null || downloadInfo.isExpiredRedownload() || (this.canResumeFromCache && this.downloadInfo.getChunkCount() <= 1) || this.downloadInfo.isChunkDowngradeRetryUsed() || !this.acceptPartial || this.isChunked) ? false : true;
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void checkSpaceOverflow(long j) throws BaseException {
        int i;
        long length;
        long j2;
        long availableSpaceBytes;
        DownloadSetting obtain;
        long j3;
        long j4 = j;
        String tempPath = this.downloadInfo.getTempPath();
        String tempName = this.downloadInfo.getTempName();
        if (DownloadUtils.isChunkedTask(j)) {
            return;
        }
        RandomAccessOutputStream createOutputStream = DownloadUtils.createOutputStream(this.downloadInfo, tempPath, tempName, -1);
        try {
            length = new File(tempPath, tempName).length();
            j2 = j4 - length;
            availableSpaceBytes = DownloadUtils.getAvailableSpaceBytes(tempPath);
            obtain = DownloadSetting.obtain(this.downloadInfo.getId());
        } catch (Throwable th) {
            th = th;
            i = 1;
        }
        try {
            if (obtain.optInt("space_fill_part_download", 0) == 1) {
                this.curBytesNeedCheckSpaceOverFlow = 0L;
                if (j2 <= 0) {
                    j2 = this.downloadInfo.getTotalBytes() - this.downloadInfo.getCurBytes();
                }
                if (availableSpaceBytes < j2) {
                    Logger.m6460w(TAG, "checkSpaceOverflow: contentLength = " + DownloadUtils.byteToMb(j) + "MB, downloaded = " + DownloadUtils.byteToMb(length) + "MB, required = " + DownloadUtils.byteToMb(j2) + "MB, available = " + DownloadUtils.byteToMb(availableSpaceBytes) + "MB");
                    int i2 = (availableSpaceBytes > 0L ? 1 : (availableSpaceBytes == 0L ? 0 : -1));
                    if (i2 > 0) {
                        int optInt = obtain.optInt("space_fill_min_keep_mb", 100);
                        if (optInt > 0) {
                            j3 = availableSpaceBytes - (optInt * 1048576);
                            Logger.m6460w(TAG, "checkSpaceOverflow: minKeep = " + optInt + "MB, canDownload = " + DownloadUtils.byteToMb(j3) + "MB");
                            if (j3 <= 0) {
                                throw new DownloadOutOfSpaceException(availableSpaceBytes, j2);
                            }
                            this.curBytesNeedCheckSpaceOverFlow = this.downloadInfo.getCurBytes() + j3;
                            j4 = j;
                        } else {
                            j3 = availableSpaceBytes;
                            j4 = j;
                        }
                        if (length >= j4 || j3 + length > j4) {
                        }
                    } else if (obtain.optInt("download_when_space_negative", 0) != 1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("availableSpace ");
                        sb.append(i2 == 0 ? "=" : "<");
                        sb.append(" 0");
                        throw new BaseException(1052, sb.toString());
                    }
                    createOutputStream.setLength(j4);
                    DownloadUtils.safeClose(createOutputStream);
                }
            } else {
                int i3 = (availableSpaceBytes > 0L ? 1 : (availableSpaceBytes == 0L ? 0 : -1));
                if (i3 <= 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("availableSpace ");
                    sb2.append(i3 == 0 ? "=" : "<");
                    sb2.append(" 0");
                    throw new BaseException(1052, sb2.toString());
                } else if (availableSpaceBytes < j2) {
                    throw new DownloadOutOfSpaceException(availableSpaceBytes, j2);
                }
            }
            createOutputStream.setLength(j4);
            DownloadUtils.safeClose(createOutputStream);
        } catch (Throwable th2) {
            th = th2;
            i = 1;
            Closeable[] closeableArr = new Closeable[i];
            closeableArr[0] = createOutputStream;
            DownloadUtils.safeClose(closeableArr);
            throw th;
        }
    }

    private void checkSpaceOverflowInProgress() throws BaseException {
        long j;
        int optInt;
        try {
            j = DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getTempPath());
        } catch (BaseException unused) {
            j = 0;
        }
        Logger.m6469i(TAG, "checkSpaceOverflowInProgress: available = " + DownloadUtils.byteToMb(j) + "MB");
        if (j > 0) {
            long totalBytes = this.downloadInfo.getTotalBytes() - this.downloadInfo.getCurBytes();
            if (j < totalBytes && (optInt = DownloadSetting.obtain(this.downloadInfo.getId()).optInt("space_fill_min_keep_mb", 100)) > 0) {
                long j2 = j - (optInt * 1048576);
                Logger.m6469i(TAG, "checkSpaceOverflowInProgress: minKeep  = " + optInt + "MB, canDownload = " + DownloadUtils.byteToMb(j2) + "MB");
                if (j2 <= 0) {
                    this.curBytesNeedCheckSpaceOverFlow = 0L;
                    throw new DownloadOutOfSpaceException(j, totalBytes);
                } else {
                    this.curBytesNeedCheckSpaceOverFlow = this.downloadInfo.getCurBytes() + j2 + 1048576;
                    return;
                }
            }
        }
        this.curBytesNeedCheckSpaceOverFlow = 0L;
    }

    private void checkWifiTaskValid() throws DownloadRetryNeedlessException {
        if (this.downloadInfo.isOnlyWifi() && !DownloadUtils.checkPermission(DownloadComponentManager.getAppContext(), "android.permission.ACCESS_NETWORK_STATE")) {
            throw new DownloadRetryNeedlessException(1019, String.format("download task need permission:%s", "android.permission.ACCESS_NETWORK_STATE"));
        }
        if (!this.downloadInfo.isDownloadWithWifiValid()) {
            throw new DownloadOnlyWifiException();
        }
        if (!this.downloadInfo.isPauseReserveWithWifiValid()) {
            throw new DownloadPauseReserveWifiException();
        }
    }

    private void checkSavePathValid() throws BaseException {
        if (TextUtils.isEmpty(this.downloadInfo.getSavePath())) {
            throw new BaseException(1028, "download savePath can not be empty");
        }
        if (TextUtils.isEmpty(this.downloadInfo.getName())) {
            throw new BaseException(1029, "download name can not be empty");
        }
        File file = new File(this.downloadInfo.getSavePath());
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs || file.exists()) {
                return;
            }
            int i = 0;
            if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt("opt_mkdir_failed", 0) == 1) {
                while (!mkdirs) {
                    int i2 = i + 1;
                    if (i >= 3) {
                        break;
                    }
                    try {
                        Thread.sleep(10L);
                        mkdirs = file.mkdirs();
                        i = i2;
                    } catch (InterruptedException unused) {
                    }
                }
                if (mkdirs || file.exists()) {
                    return;
                }
                if (DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getSavePath()) < 16384) {
                    throw new BaseException(1006, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
                }
                throw new BaseException(1030, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
            }
            throw new BaseException(1030, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
        } else if (file.isDirectory()) {
        } else {
            if (DownloadSettingsUtils.isOptimizeSavePath(this.downloadInfo)) {
                file.delete();
                if (file.mkdirs() || file.exists()) {
                    return;
                }
                throw new BaseException(1031, "download savePath is not directory:path=" + this.downloadInfo.getSavePath());
            }
            throw new BaseException(1031, "download savePath is not a directory:" + this.downloadInfo.getSavePath());
        }
    }

    private boolean isResponseCodeError(int i, String str, String str2) {
        if (i == 412) {
            return true;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2) || !(this.isResponseFromBegin || this.acceptPartial)) {
            return (i == 201 || i == 416) && this.downloadInfo.getCurBytes() > 0;
        }
        return true;
    }

    private void handleResponseCodeError(String str, String str2) throws RetryThrowable {
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.canResumeFromCache = false;
        this.downloadInfo.resetDataForEtagEndure(str);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        throw new RetryThrowable(str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0140, code lost:
        com.p319ss.android.socialbase.downloader.utils.DownloadUtils.deleteAllDownloadFiles(r20.downloadInfo);
     */
    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleFirstConnection(java.lang.String r21, com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection r22, long r23) throws com.p319ss.android.socialbase.downloader.exception.BaseException, com.p319ss.android.socialbase.downloader.exception.RetryThrowable {
        /*
            Method dump skipped, instructions count: 822
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadRunnable.handleFirstConnection(java.lang.String, com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection, long):void");
    }

    public boolean isAlive() {
        return this.isAlive.get();
    }

    public int getDownloadId() {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo != null) {
            return downloadInfo.getId();
        }
        return 0;
    }

    public void prepareDownload() {
        this.prepareDownloadTime = System.currentTimeMillis();
        this.statusHandler.onPrepare();
    }

    private void checkTaskCanResume() {
        long curByte = DownloadUtils.getCurByte(this.downloadInfo);
        long curBytes = this.downloadInfo.getCurBytes();
        if (curByte != curBytes) {
            String str = TAG;
            Logger.m6460w(str, "checkTaskCanResume: offset = " + curByte + ", curBytes = " + curBytes);
        }
        this.downloadInfo.setCurBytes(curByte);
        this.canResumeFromCache = curByte > 0;
        if (this.canResumeFromCache || this.needCheckIfModified) {
            return;
        }
        Logger.m6469i(TAG, "checkTaskCanResume: deleteAllDownloadFiles");
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean onProgress(long j) throws BaseException {
        if (this.curBytesNeedCheckSpaceOverFlow > 0 && this.downloadInfo.getCurBytes() > this.curBytesNeedCheckSpaceOverFlow) {
            checkSpaceOverflowInProgress();
        }
        return this.statusHandler.onProgress(j);
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onCompleted(DownloadChunkRunnable downloadChunkRunnable) {
        if (this.isSingleChunk) {
            return;
        }
        synchronized (this) {
            this.downloadChunkRunnableList.remove(downloadChunkRunnable);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean canRetry(BaseException baseException) {
        if (this.segmentDispatcher == null || !DownloadUtils.isNetworkError(baseException) || this.retainRetryTimes.get() >= this.downloadInfo.getRetryCount()) {
            if (DownloadUtils.isResponseCodeError(baseException)) {
                if (this.isSingleChunk && !this.isTriedFixRangeNotSatisfiable) {
                    DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
                    this.isTriedFixRangeNotSatisfiable = true;
                }
                return true;
            }
            AtomicInteger atomicInteger = this.retainRetryTimes;
            if ((atomicInteger == null || atomicInteger.get() <= 0) && !this.downloadInfo.hasNextBackupUrl()) {
                if (baseException == null) {
                    return false;
                }
                if ((baseException.getErrorCode() != 1011 && (baseException.getCause() == null || !(baseException.getCause() instanceof SSLHandshakeException))) || !this.downloadInfo.canReplaceHttpForRetry()) {
                    return false;
                }
            }
            return !(baseException instanceof DownloadRetryNeedlessException);
        }
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onError(BaseException baseException) {
        String str = TAG;
        Logger.m6475d(str, "onError:" + baseException.getMessage());
        this.runStatus = RunStatus.RUN_STATUS_ERROR;
        this.errorException = baseException;
        cancelAllChunkRunnable();
    }

    private void clearCurrentDownloadData() {
        String str = TAG;
        Logger.m6460w(str, "clearCurrentDownloadData::" + Log.getStackTraceString(new Throwable()));
        try {
            this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
            this.downloadCache.removeSegments(this.downloadInfo.getId());
            DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
            this.canResumeFromCache = false;
            this.downloadInfo.resetDataForEtagEndure("");
            this.downloadCache.updateDownloadInfo(this.downloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onChunkDowngradeRetry(BaseException baseException) {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo != null) {
            downloadInfo.setChunkDowngradeRetryUsed(true);
        }
        onAllChunkRetryWithReset(baseException, false);
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onAllChunkRetryWithReset(BaseException baseException, boolean z) {
        Logger.m6475d(TAG, "onAllChunkRetryWithReset");
        this.runStatus = RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET;
        this.errorException = baseException;
        cancelAllChunkRunnable();
        if (z ? handleRetryTime(baseException) : false) {
            return;
        }
        clearCurrentDownloadData();
    }

    private void cancelAllChunkRunnable() {
        try {
            Iterator it = ((ArrayList) this.downloadChunkRunnableList.clone()).iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable downloadChunkRunnable = (DownloadChunkRunnable) it.next();
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.cancel();
                }
            }
        } catch (Throwable th) {
            String str = TAG;
            Logger.m6469i(str, "cancelAllChunkRunnable: " + th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleForbiddenCallback(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.downloadInfo.setForbiddenBackupUrls(list, this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER);
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDiskSpaceCallback() {
        AbsDownloadEngine downloadEngine;
        if (checkIsStoppedByUser() || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null) {
            return;
        }
        downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onSingleChunkRetry(DownloadChunk downloadChunk, BaseException baseException, long j) {
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && (baseException.getErrorCode() == 1047 || DownloadUtils.isInsufficientSpaceError(baseException))) {
            return onRetry(baseException, j);
        }
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (handleRetryTime(baseException)) {
            return RetryCheckStatus.RETURN;
        }
        this.statusHandler.onSingleChunkRetry(downloadChunk, baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && this.downloadInfo.isNeedRetryDelay()) {
            long delayTime = getDelayTime();
            if (delayTime > 0) {
                String str = TAG;
                Logger.m6469i(str, "onSingleChunkRetry with delay time " + delayTime);
                try {
                    Thread.sleep(delayTime);
                } catch (Throwable th) {
                    String str2 = TAG;
                    Logger.m6460w(str2, "onSingleChunkRetry:" + th.getMessage());
                }
            }
        }
        return RetryCheckStatus.CONTINUE;
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onRetry(BaseException baseException, long j) {
        long totalBytes;
        long j2;
        boolean z;
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && baseException.getErrorCode() == 1047) {
            if (this.forbiddenHandler == null || this.downloadInfo.isForbiddenRetryed()) {
                if (handleRetryTime(baseException)) {
                    return RetryCheckStatus.RETURN;
                }
                z = false;
            } else {
                AbsDownloadForbiddenCallback absDownloadForbiddenCallback = new AbsDownloadForbiddenCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.1
                    @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback, com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
                    public void onCallback(List<String> list) {
                        super.onCallback(list);
                        DownloadRunnable.this.handleForbiddenCallback(list);
                    }
                };
                boolean onForbidden = this.forbiddenHandler.onForbidden(absDownloadForbiddenCallback);
                this.downloadInfo.setForbiddenRetryed();
                if (!onForbidden) {
                    z = false;
                } else if (!absDownloadForbiddenCallback.hasCallback()) {
                    cancelAllChunkRunnable();
                    this.statusHandler.handleWaitingAsyncHandler();
                    this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                    return RetryCheckStatus.RETURN;
                } else {
                    z = true;
                }
            }
        } else if (DownloadUtils.isInsufficientSpaceError(baseException)) {
            if (this.diskSpaceHandler == null) {
                onError(baseException);
                return RetryCheckStatus.RETURN;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback = new IDownloadDiskSpaceCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.2
                @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback
                public void onDiskCleaned() {
                    synchronized (DownloadRunnable.this) {
                        atomicBoolean.set(true);
                        DownloadRunnable.this.handleDiskSpaceCallback();
                    }
                }
            };
            if (baseException instanceof DownloadOutOfSpaceException) {
                DownloadOutOfSpaceException downloadOutOfSpaceException = (DownloadOutOfSpaceException) baseException;
                j2 = downloadOutOfSpaceException.getAvaliableSpaceBytes();
                totalBytes = downloadOutOfSpaceException.getRequiredSpaceBytes();
            } else {
                totalBytes = this.downloadInfo.getTotalBytes();
                j2 = -1;
            }
            synchronized (this) {
                if (this.diskSpaceHandler.cleanUpDisk(j2, totalBytes, iDownloadDiskSpaceCallback)) {
                    if (!DownloadSetting.obtain(this.downloadInfo.getId()).optBugFix("not_delete_when_clean_space", false)) {
                        checkCompletedByteValid();
                    }
                    if (!atomicBoolean.get()) {
                        if (this.runStatus != RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                            this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                            cancelAllChunkRunnable();
                            this.statusHandler.handleWaitingAsyncHandler();
                        }
                        return RetryCheckStatus.RETURN;
                    } else if (handleRetryTime(baseException)) {
                        return RetryCheckStatus.RETURN;
                    } else {
                        z = true;
                    }
                } else if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                    return RetryCheckStatus.RETURN;
                } else {
                    onError(baseException);
                    return RetryCheckStatus.RETURN;
                }
            }
        } else {
            if (handleRetryTime(baseException)) {
                return RetryCheckStatus.RETURN;
            }
            z = false;
        }
        if (!z && checkNeedRetryDelay()) {
            cancelAllChunkRunnable();
        }
        this.statusHandler.onRetry(baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY ? RetryCheckStatus.RETURN : RetryCheckStatus.CONTINUE;
    }

    private boolean handleRetryTime(BaseException baseException) {
        AtomicInteger atomicInteger = this.retainRetryTimes;
        boolean z = true;
        if (atomicInteger != null) {
            if (atomicInteger.get() <= 0 || (baseException != null && baseException.getErrorCode() == 1070)) {
                if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                    this.retainRetryTimes.set(this.downloadInfo.getBackUpUrlRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                } else if (baseException != null && ((baseException.getErrorCode() == 1011 || (baseException.getCause() != null && (baseException.getCause() instanceof SSLHandshakeException))) && this.downloadInfo.canReplaceHttpForRetry())) {
                    this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                    this.downloadInfo.setHttpsToHttpRetryUsed(true);
                } else {
                    onError(new BaseException(baseException.getErrorCode(), String.format("retry for exception, but current retry time : %s , retry Time %s all used, last error is %s", String.valueOf(this.retainRetryTimes), String.valueOf(this.downloadInfo.getRetryCount()), baseException.getErrorMessage())));
                    return true;
                }
                z = false;
            }
            if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && z) {
                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
            }
            return false;
        }
        onError(new BaseException(1043, "retry for exception, but retain retry time is null, last error is :" + baseException.getErrorMessage()));
        return true;
    }

    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public synchronized DownloadChunk getUnCompletedSubChunk(int i) {
        DownloadChunk unCompletedSubChunk;
        if (this.downloadInfo.getChunkCount() < 2) {
            return null;
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
        if (downloadChunk != null && !downloadChunk.isEmpty()) {
            for (int i2 = 0; i2 < downloadChunk.size(); i2++) {
                DownloadChunk downloadChunk2 = downloadChunk.get(i2);
                if (downloadChunk2 != null && (unCompletedSubChunk = getUnCompletedSubChunk(downloadChunk2, i)) != null) {
                    return unCompletedSubChunk;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    @Override // com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setHttpResponseStatus(com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L1a
            int r2 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L16
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r1.downloadInfo     // Catch: java.lang.Throwable -> L16
            r0.setHttpStatusCode(r2)     // Catch: java.lang.Throwable -> L16
            com.ss.android.socialbase.downloader.model.DownloadInfo r0 = r1.downloadInfo     // Catch: java.lang.Throwable -> L16
            java.lang.String r2 = com.p319ss.android.socialbase.downloader.utils.DownloadHttpUtils.httpCodeToMessage(r2)     // Catch: java.lang.Throwable -> L16
            r0.setHttpStatusMessage(r2)     // Catch: java.lang.Throwable -> L16
            r2 = 1
            goto L1b
        L16:
            r2 = move-exception
            r2.printStackTrace()
        L1a:
            r2 = 0
        L1b:
            if (r2 != 0) goto L2a
            com.ss.android.socialbase.downloader.model.DownloadInfo r2 = r1.downloadInfo
            r0 = -1
            r2.setHttpStatusCode(r0)
            com.ss.android.socialbase.downloader.model.DownloadInfo r2 = r1.downloadInfo
            java.lang.String r0 = ""
            r2.setHttpStatusMessage(r0)
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadRunnable.setHttpResponseStatus(com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection):void");
    }

    private DownloadChunk getUnCompletedSubChunk(DownloadChunk downloadChunk, int i) {
        DownloadChunk downloadChunk2 = null;
        if (downloadChunk.isHostChunk()) {
            long retainLength = downloadChunk.getRetainLength(true);
            Logger.m6475d(TAG, "reuseChunk retainLen:" + retainLength + " chunkIndex:" + i);
            boolean z = false;
            if (!downloadChunk.hasChunkDivided() && retainLength > DownloadConstants.MIN_CHUNK_REUSE_SIZE && this.downloadInfo.isNeedReuseChunkRunnable()) {
                List<DownloadChunk> divideChunkForReuse = downloadChunk.divideChunkForReuse(this.downloadInfo.getChunkCount(), this.downloadInfo.getTotalBytes());
                if (divideChunkForReuse != null) {
                    for (DownloadChunk downloadChunk3 : divideChunkForReuse) {
                        this.downloadCache.addSubDownloadChunk(downloadChunk3);
                    }
                }
                z = true;
            } else if (downloadChunk.hasChunkDivided()) {
                z = true;
            }
            if (z && downloadChunk.hasChunkDivided()) {
                for (int i2 = 1; i2 < downloadChunk.getSubChunkList().size(); i2++) {
                    DownloadChunk downloadChunk4 = downloadChunk.getSubChunkList().get(i2);
                    if (downloadChunk4 != null) {
                        Logger.m6475d(TAG, "check can checkUnCompletedChunk -- chunkIndex:" + downloadChunk4.getChunkIndex() + " currentOffset:" + downloadChunk4.getCurrentOffset() + "  startOffset:" + downloadChunk4.getStartOffset() + " contentLen:" + downloadChunk4.getContentLength());
                        if (downloadChunk4.getChunkIndex() < 0 || (!downloadChunk4.hasNoBytesDownload() && !downloadChunk4.isDownloading())) {
                            downloadChunk2 = downloadChunk4;
                            break;
                        }
                    }
                }
                if (downloadChunk2 != null) {
                    long currentOffset = downloadChunk.getCurrentOffset();
                    Logger.m6475d(TAG, "unComplete chunk " + downloadChunk.getChunkIndex() + " curOffset:" + currentOffset + " reuseChunk chunkIndex:" + i + " for subChunk:" + downloadChunk2.getChunkIndex());
                    this.downloadCache.updateSubDownloadChunkIndex(downloadChunk2.getId(), downloadChunk2.getChunkIndex(), downloadChunk2.getHostChunkIndex(), i);
                    downloadChunk2.setChunkIndex(i);
                    downloadChunk2.setDownloading(true);
                }
                return downloadChunk2;
            }
            return null;
        }
        return null;
    }

    private IRetryDelayTimeCalculator getRetryDelayTimeCalculator(DownloadTask downloadTask) {
        IRetryDelayTimeCalculator retryDelayTimeCalculator = downloadTask.getRetryDelayTimeCalculator();
        if (retryDelayTimeCalculator != null) {
            return retryDelayTimeCalculator;
        }
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            String retryDelayTimeArray = downloadInfo.getRetryDelayTimeArray();
            if (!TextUtils.isEmpty(retryDelayTimeArray)) {
                return new RetryDelayTimeParamCalculator(retryDelayTimeArray);
            }
        }
        return DownloadComponentManager.getRetryDelayTimeCalculator();
    }

    public Future getFuture() {
        return this.mFuture;
    }

    public void setFuture(Future future) {
        this.mFuture = future;
    }

    public void setThrottleNetSpeed(long j) {
        IDownloadHttpConnection iDownloadHttpConnection = this.firstGetConnection;
        if (iDownloadHttpConnection != null && (iDownloadHttpConnection instanceof AbsDownloadHttpConnection)) {
            try {
                ((AbsDownloadHttpConnection) iDownloadHttpConnection).setThrottleNetSpeedWhenRunning(j);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
