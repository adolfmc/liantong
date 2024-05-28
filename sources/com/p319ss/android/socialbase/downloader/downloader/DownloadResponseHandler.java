package com.p319ss.android.socialbase.downloader.downloader;

import android.os.SystemClock;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.p319ss.android.socialbase.downloader.impls.DownloadCache;
import com.p319ss.android.socialbase.downloader.impls.DownloadProxy;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache;
import com.p319ss.android.socialbase.downloader.reader.AsyncStreamReader;
import com.p319ss.android.socialbase.downloader.reader.IStreamReader;
import com.p319ss.android.socialbase.downloader.reader.SyncStreamReader;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadResponseHandler {
    private static final boolean DEBUG = false;
    public static final int MIN_SYNC_STEP_BYTE = 65536;
    public static final long MIN_SYNC_TIME_MS = 500;
    private static String TAG = "ResponseHandler";
    private final AppStatusManager appStatusManager;
    private final boolean bugfixCancelThenUpdate;
    private final IDownloadRunnableCallback callback;
    private volatile boolean canceled;
    private long curOffset;
    private long debugReadTimeNs;
    private long debugSyncTimeNs;
    private long debugTotalTimeNs;
    private long debugWriteTimeNs;
    private DownloadCache downloadCache;
    private final DownloadChunk downloadChunk;
    private volatile long downloadChunkContentLen;
    private final DownloadInfo downloadInfo;
    private volatile long endOffset;
    private BaseException exception;
    private long handleStartOffset;
    private final boolean hasSyncStrategy;
    private final IDownloadHttpConnection httpConnection;
    private final boolean isMonitorRw;
    private RandomAccessOutputStream outputStream;
    private volatile boolean paused;
    private boolean rwConcurrent;
    private final DownloadSetting setting;
    private ISqlDownloadCache sqlDownloadCache;
    private final long syncIntervalMsBg;
    private final long syncIntervalMsFg;
    private final String url;
    boolean openLimitSpeed = false;
    private volatile long lastSyncBytes = 0;
    private volatile long lastSyncTimestamp = 0;
    private IDownloadCache customCache = DownloadComponentManager.getDownloadCache();

    private boolean isNeedSync(long j, long j2) {
        return j > 65536 && j2 > 500;
    }

    /*  JADX ERROR: Failed to decode insn: 0x0444: UNKNOWN(0xEEE6), method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0444: UNKNOWN(0xEEE6)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x046A: UNKNOWN(0xEEEB), method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x046A: UNKNOWN(0xEEEB)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0476: UNKNOWN(0xEEEF), method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0476: UNKNOWN(0xEEEF)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04A6: UNKNOWN(0xEEF8), method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04A6: UNKNOWN(0xEEF8)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04C6: CONST_METHOD_TYPE r238, method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04C6: CONST_METHOD_TYPE r238'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04C8: CONST_STRING r0, method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        java.lang.IllegalArgumentException: newPosition > limit: (29622384 > 15131192)
        	at java.nio.Buffer.createPositionException(Buffer.java:269)
        	at java.nio.Buffer.position(Buffer.java:244)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x05B2: UNKNOWN(0xEF3F), method: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05B2: UNKNOWN(0xEF3F)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    public void handleResponse() throws com.p319ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instructions count: 1526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void");
    }

    public DownloadResponseHandler(DownloadInfo downloadInfo, String str, IDownloadHttpConnection iDownloadHttpConnection, DownloadChunk downloadChunk, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.downloadInfo = downloadInfo;
        this.url = str;
        IDownloadCache iDownloadCache = this.customCache;
        if (iDownloadCache instanceof DefaultDownloadCache) {
            DefaultDownloadCache defaultDownloadCache = (DefaultDownloadCache) iDownloadCache;
            this.downloadCache = defaultDownloadCache.getDownloadCache();
            this.sqlDownloadCache = defaultDownloadCache.getSqlDownloadCache();
        }
        this.httpConnection = iDownloadHttpConnection;
        this.downloadChunk = downloadChunk;
        this.callback = iDownloadRunnableCallback;
        this.curOffset = downloadChunk.getCurrentOffset();
        this.handleStartOffset = this.curOffset;
        if (downloadChunk.isHostChunk()) {
            this.downloadChunkContentLen = downloadChunk.getContentLength();
        } else {
            this.downloadChunkContentLen = downloadChunk.getRetainLength(false);
        }
        this.endOffset = downloadChunk.getEndOffset();
        this.appStatusManager = AppStatusManager.getInstance();
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.hasSyncStrategy = this.setting.optInt("sync_strategy", 0) == 1;
        if (this.hasSyncStrategy) {
            this.syncIntervalMsFg = Math.max(this.setting.optInt("sync_interval_ms_fg", 5000), 500L);
            this.syncIntervalMsBg = Math.max(this.setting.optInt("sync_interval_ms_bg", 1000), 500L);
        } else {
            this.syncIntervalMsFg = 0L;
            this.syncIntervalMsBg = 0L;
        }
        this.isMonitorRw = this.setting.optInt("monitor_rw") == 1;
        this.bugfixCancelThenUpdate = DownloadExpSwitchCode.isSwitchEnable(65536);
    }

    public long getCurOffset() {
        return this.curOffset;
    }

    private boolean isStoppedStatus() {
        return this.paused || this.canceled;
    }

    public void pause() {
        if (this.paused) {
            return;
        }
        this.paused = true;
        cancelConnection();
    }

    public void cancel() {
        if (this.canceled) {
            return;
        }
        synchronized (this.callback) {
            this.canceled = true;
        }
        cancelConnection();
    }

    private void cancelConnection() {
        ExecutorService cPUThreadExecutor;
        if (this.httpConnection == null || (cPUThreadExecutor = DownloadComponentManager.getCPUThreadExecutor()) == null) {
            return;
        }
        cPUThreadExecutor.execute(new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DownloadResponseHandler.this.httpConnection.end();
                } catch (Throwable unused) {
                }
            }
        });
    }

    private boolean canReuseConnection() {
        return this.downloadInfo.isNeedReuseFirstConnection() && this.downloadChunk.isReuseingFirstConnection();
    }

    public void setChunkOffset(long j, long j2, long j3) {
        this.curOffset = j;
        this.handleStartOffset = j;
        this.endOffset = j2;
        this.downloadChunkContentLen = j3;
    }

    public void setEndOffset(long j, long j2) {
        this.endOffset = j;
        this.downloadChunkContentLen = j2;
    }

    private IStreamReader createStreamReader(InputStream inputStream) {
        int writeBufferSize = DownloadComponentManager.getWriteBufferSize();
        if (this.setting.optInt("rw_concurrent", 0) == 1 && this.downloadInfo.getChunkCount() == 1 && this.downloadInfo.getTotalBytes() > 20971520) {
            try {
                AsyncStreamReader asyncStreamReader = new AsyncStreamReader(inputStream, writeBufferSize, this.setting.optInt("rw_concurrent_max_buffer_count", 4));
                this.rwConcurrent = true;
                return asyncStreamReader;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        SyncStreamReader syncStreamReader = new SyncStreamReader(inputStream, writeBufferSize);
        this.rwConcurrent = false;
        return syncStreamReader;
    }

    private void checkAndSync(boolean z) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = uptimeMillis - this.lastSyncTimestamp;
        if (this.hasSyncStrategy) {
            if (j > (this.appStatusManager.isAppForeground() ? this.syncIntervalMsFg : this.syncIntervalMsBg)) {
                sync();
                this.lastSyncTimestamp = uptimeMillis;
                return;
            }
            return;
        }
        long j2 = this.curOffset - this.lastSyncBytes;
        if (z || isNeedSync(j2, j)) {
            sync();
            this.lastSyncTimestamp = uptimeMillis;
        }
    }

    public long getLastSyncBytes() {
        return this.lastSyncBytes;
    }

    private void sync() {
        boolean z;
        long nanoTime = this.isMonitorRw ? System.nanoTime() : 0L;
        try {
            this.outputStream.flushAndSync();
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            this.downloadInfo.updateRealDownloadTime(true);
            boolean z2 = this.downloadInfo.getChunkCount() > 1;
            IDownloadProxy iDownloadProxy = DownloadProxy.get(DownloadUtils.needNotifyDownloaderProcess());
            if (z2) {
                updateDownloadChunk(this.sqlDownloadCache);
                if (iDownloadProxy != null) {
                    iDownloadProxy.updateDownloadInfo(this.downloadInfo);
                } else {
                    this.sqlDownloadCache.OnDownloadTaskProgress(this.downloadInfo.getId(), this.downloadInfo.getCurBytes());
                }
            } else if (iDownloadProxy != null) {
                iDownloadProxy.updateDownloadInfo(this.downloadInfo);
            } else {
                this.sqlDownloadCache.OnDownloadTaskProgress(this.downloadChunk.getId(), this.curOffset);
            }
            this.lastSyncBytes = this.curOffset;
        }
        if (this.isMonitorRw) {
            this.debugSyncTimeNs += System.nanoTime() - nanoTime;
        }
    }

    private void updateDownloadChunk(IDownloadCache iDownloadCache) {
        IDownloadProxy iDownloadProxy;
        DownloadChunk downloadChunk;
        DownloadChunk downloadChunk2;
        if (iDownloadCache == null) {
            return;
        }
        boolean z = iDownloadCache instanceof SqlDownloadCache;
        if (z) {
            IDownloadProxy iDownloadProxy2 = DownloadProxy.get(DownloadUtils.needNotifyDownloaderProcess());
            if (iDownloadProxy2 == null) {
                return;
            }
            iDownloadProxy = iDownloadProxy2;
        } else {
            iDownloadProxy = null;
        }
        if (this.downloadChunk.isHostChunk()) {
            downloadChunk = this.downloadChunk.getFirstReuseChunk();
        } else {
            downloadChunk = this.downloadChunk;
        }
        if (downloadChunk != null) {
            downloadChunk.setCurrentOffset(this.curOffset);
            if (z && iDownloadProxy != null) {
                iDownloadProxy.updateSubDownloadChunk(downloadChunk.getId(), downloadChunk.getChunkIndex(), downloadChunk.getHostChunkIndex(), this.curOffset);
                downloadChunk2 = downloadChunk;
            } else {
                downloadChunk2 = downloadChunk;
                iDownloadCache.updateSubDownloadChunk(downloadChunk.getId(), downloadChunk.getChunkIndex(), downloadChunk.getHostChunkIndex(), this.curOffset);
            }
            if (downloadChunk2.canRefreshCurOffsetForReuseChunk()) {
                boolean z2 = false;
                if (downloadChunk2.hasNoBytesDownload()) {
                    long nextChunkCurOffset = downloadChunk2.getNextChunkCurOffset();
                    if (nextChunkCurOffset > this.curOffset) {
                        if (z && iDownloadProxy != null) {
                            iDownloadProxy.updateDownloadChunk(downloadChunk2.getId(), downloadChunk2.getHostChunkIndex(), nextChunkCurOffset);
                        } else {
                            iDownloadCache.updateDownloadChunk(downloadChunk2.getId(), downloadChunk2.getHostChunkIndex(), nextChunkCurOffset);
                        }
                        z2 = true;
                    }
                }
                if (z2) {
                    return;
                }
                if (z && iDownloadProxy != null) {
                    iDownloadProxy.updateDownloadChunk(downloadChunk2.getId(), downloadChunk2.getHostChunkIndex(), this.curOffset);
                } else {
                    iDownloadCache.updateDownloadChunk(downloadChunk2.getId(), downloadChunk2.getHostChunkIndex(), this.curOffset);
                }
            }
        } else if (this.downloadChunk.isHostChunk()) {
            if (z && iDownloadProxy != null) {
                iDownloadProxy.updateDownloadChunk(this.downloadChunk.getId(), this.downloadChunk.getChunkIndex(), this.curOffset);
            } else {
                iDownloadCache.updateDownloadChunk(this.downloadChunk.getId(), this.downloadChunk.getChunkIndex(), this.curOffset);
            }
        }
    }
}
