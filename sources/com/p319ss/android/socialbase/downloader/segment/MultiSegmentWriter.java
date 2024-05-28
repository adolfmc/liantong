package com.p319ss.android.socialbase.downloader.segment;

import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.ss.android.socialbase.downloader.segment.MultiSegmentWriter */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MultiSegmentWriter {
    private static final boolean DEBUG = false;
    private static final int MIN_CACHE_BYTES = 65536;
    private static final int MIN_CACHE_TIME_MS = 100;
    private static final int MIN_SYNC_STEP_BYTE = 65536;
    private static final long MIN_SYNC_TIME_MS = 500;
    private static final String TAG = "MultiSegmentWriter";
    private final IDownloadRunnableCallback callback;
    private final DownloadInfo downloadInfo;
    private BaseException exception;
    private final boolean hasSyncStrategy;
    private final boolean isMonitorRw;
    private final IBufferPool pool;
    private final DownloadSetting setting;
    private final long syncIntervalMsBg;
    private final long syncIntervalMsFg;
    private long syncTimeNs;
    private final List<SegmentOutput> outputs = new LinkedList();
    private final List<SegmentOutput> doneOutputs = new ArrayList();
    private volatile boolean threadDirty = false;
    private volatile boolean paused = false;
    private volatile boolean canceled = false;
    private volatile long lastSyncBytes = 0;
    private volatile long lastSyncTimestamp = 0;
    private final IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
    private final AppStatusManager appStatusManager = AppStatusManager.getInstance();

    private boolean isNeedSync(long j, long j2) {
        return j > 65536 && j2 > 500;
    }

    /*  JADX ERROR: Failed to decode insn: 0x0441: CONST_METHOD_TYPE r255, method: com.ss.android.socialbase.downloader.segment.MultiSegmentWriter.loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0441: CONST_METHOD_TYPE r255'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    void loopAndWrite(com.p319ss.android.socialbase.downloader.segment.IInput r31) throws com.p319ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instructions count: 1185
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.segment.MultiSegmentWriter.loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiSegmentWriter(DownloadInfo downloadInfo, IDownloadRunnableCallback iDownloadRunnableCallback, IBufferPool iBufferPool) {
        this.downloadInfo = downloadInfo;
        this.callback = iDownloadRunnableCallback;
        this.pool = iBufferPool;
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
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assignOutput(SegmentOutput segmentOutput) {
        synchronized (this) {
            this.outputs.add(segmentOutput);
        }
    }

    private void checkAndSync(long j, boolean z) throws IOException {
        long j2 = j - this.lastSyncTimestamp;
        if (this.hasSyncStrategy) {
            if (j2 > (this.appStatusManager.isAppForeground() ? this.syncIntervalMsFg : this.syncIntervalMsBg)) {
                flushAndSync();
                this.lastSyncTimestamp = j;
                return;
            }
            return;
        }
        long curBytes = this.downloadInfo.getCurBytes() - this.lastSyncBytes;
        if (z || isNeedSync(curBytes, j2)) {
            flushAndSync();
            this.lastSyncTimestamp = j;
        }
    }

    public long getLastSyncBytes() {
        return this.lastSyncBytes;
    }

    private void outputDone(IOutput iOutput) {
        synchronized (this) {
            this.doneOutputs.add((SegmentOutput) iOutput);
        }
    }

    private void flushAndSync() throws IOException {
        boolean z = this.isMonitorRw;
        long nanoTime = z ? System.nanoTime() : 0L;
        DownloadInfo downloadInfo = this.downloadInfo;
        IDownloadCache iDownloadCache = this.downloadCache;
        List<SegmentOutput> list = this.outputs;
        List<SegmentOutput> list2 = this.doneOutputs;
        Map<Long, Segment> segmentMap = iDownloadCache.getSegmentMap(downloadInfo.getId());
        if (segmentMap == null) {
            segmentMap = new HashMap<>(4);
        }
        synchronized (this) {
            flush(list);
            sync(list);
            updateSegmentToMap(list, segmentMap);
            if (list2.size() > 0) {
                close(list2);
                list.removeAll(list2);
                list2.clear();
            }
        }
        if (1 != 0) {
            downloadInfo.updateRealDownloadTime(true);
            iDownloadCache.updateSegments(downloadInfo.getId(), segmentMap);
            iDownloadCache.updateDownloadInfo(downloadInfo);
            this.lastSyncBytes = downloadInfo.getCurBytes();
        }
        if (z) {
            this.syncTimeNs += System.nanoTime() - nanoTime;
        }
    }

    private void flush(List<SegmentOutput> list) throws IOException {
        for (SegmentOutput segmentOutput : list) {
            segmentOutput.flush();
        }
    }

    private void sync(List<SegmentOutput> list) throws IOException {
        for (SegmentOutput segmentOutput : list) {
            segmentOutput.sync();
        }
    }

    private void close(List<SegmentOutput> list) {
        for (SegmentOutput segmentOutput : list) {
            segmentOutput.close();
        }
    }

    private void updateSegmentToMap(List<SegmentOutput> list, Map<Long, Segment> map) {
        for (SegmentOutput segmentOutput : list) {
            Segment segment = segmentOutput.getSegment();
            Segment segment2 = map.get(Long.valueOf(segment.getStartOffset()));
            if (segment2 == null) {
                map.put(Long.valueOf(segment.getStartOffset()), new Segment(segment));
            } else {
                segment2.setCurrentOffset(segment.getCurrentOffset());
                segment2.setEndOffset(segment.getEndOffset());
            }
        }
    }

    public void cancel() {
        this.canceled = true;
        this.threadDirty = true;
    }

    public void pause() {
        this.paused = true;
        this.threadDirty = true;
    }
}
