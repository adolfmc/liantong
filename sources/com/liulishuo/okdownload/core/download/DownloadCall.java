package com.liulishuo.okdownload.core.download;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.NamedRunnable;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DownloadCall extends NamedRunnable implements Comparable<DownloadCall> {
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload Block", false));
    static final int MAX_COUNT_RETRY_FOR_PRECONDITION_FAILED = 1;
    private static final String TAG = "DownloadCall";
    public final boolean asyncExecuted;
    @NonNull
    final ArrayList<DownloadChain> blockChainList;
    @Nullable
    volatile DownloadCache cache;
    volatile boolean canceled;
    volatile Thread currentThread;
    volatile boolean finishing;
    @NonNull
    private final DownloadStore store;
    public final DownloadTask task;

    @Override // com.liulishuo.okdownload.core.NamedRunnable
    public void interrupted(InterruptedException interruptedException) {
    }

    private DownloadCall(DownloadTask downloadTask, boolean z, @NonNull DownloadStore downloadStore) {
        this(downloadTask, z, new ArrayList(), downloadStore);
    }

    DownloadCall(DownloadTask downloadTask, boolean z, @NonNull ArrayList<DownloadChain> arrayList, @NonNull DownloadStore downloadStore) {
        super("download call: " + downloadTask.getId());
        this.task = downloadTask;
        this.asyncExecuted = z;
        this.blockChainList = arrayList;
        this.store = downloadStore;
    }

    public static DownloadCall create(DownloadTask downloadTask, boolean z, @NonNull DownloadStore downloadStore) {
        return new DownloadCall(downloadTask, z, downloadStore);
    }

    public boolean cancel() {
        synchronized (this) {
            if (this.canceled) {
                return false;
            }
            if (this.finishing) {
                return false;
            }
            this.canceled = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            OkDownload.with().downloadDispatcher().flyingCanceled(this);
            DownloadCache downloadCache = this.cache;
            if (downloadCache != null) {
                downloadCache.setUserCanceled();
            }
            Object[] array = this.blockChainList.toArray();
            if (array != null && array.length != 0) {
                for (Object obj : array) {
                    if (obj instanceof DownloadChain) {
                        ((DownloadChain) obj).cancel();
                    }
                }
            } else if (this.currentThread != null) {
                Util.m13741d(TAG, "interrupt thread with cancel operation because of chains are not running " + this.task.getId());
                this.currentThread.interrupt();
            }
            if (downloadCache != null) {
                downloadCache.getOutputStream().cancelAsync();
            }
            Util.m13741d(TAG, "cancel task " + this.task.getId() + " consume: " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
            return true;
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isFinishing() {
        return this.finishing;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x014e A[LOOP:0: B:3:0x0013->B:38:0x014e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0163 A[EDGE_INSN: B:74:0x0163->B:45:0x0163 ?: BREAK  , SYNTHETIC] */
    @Override // com.liulishuo.okdownload.core.NamedRunnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.okdownload.core.download.DownloadCall.execute():void");
    }

    private void inspectTaskStart() {
        this.store.onTaskStart(this.task.getId());
        OkDownload.with().callbackDispatcher().dispatch().taskStart(this.task);
    }

    private void inspectTaskEnd(DownloadCache downloadCache, @NonNull EndCause endCause, @Nullable Exception exc) {
        if (endCause == EndCause.CANCELED) {
            throw new IllegalAccessError("can't recognize cancelled on here");
        }
        synchronized (this) {
            if (this.canceled) {
                return;
            }
            this.finishing = true;
            this.store.onTaskEnd(this.task.getId(), endCause, exc);
            if (endCause == EndCause.COMPLETED) {
                this.store.markFileClear(this.task.getId());
                OkDownload.with().processFileStrategy().completeProcessStream(downloadCache.getOutputStream(), this.task);
            }
            OkDownload.with().callbackDispatcher().dispatch().taskEnd(this.task, endCause, exc);
        }
    }

    DownloadCache createCache(@NonNull BreakpointInfo breakpointInfo) {
        return new DownloadCache(OkDownload.with().processFileStrategy().createProcessStream(this.task, breakpointInfo, this.store));
    }

    int getPriority() {
        return this.task.getPriority();
    }

    void start(DownloadCache downloadCache, BreakpointInfo breakpointInfo) throws InterruptedException {
        int blockCount = breakpointInfo.getBlockCount();
        ArrayList arrayList = new ArrayList(breakpointInfo.getBlockCount());
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < blockCount; i++) {
            BlockInfo block = breakpointInfo.getBlock(i);
            if (!Util.isCorrectFull(block.getCurrentOffset(), block.getContentLength())) {
                Util.resetBlockIfDirty(block);
                DownloadChain createChain = DownloadChain.createChain(i, this.task, breakpointInfo, downloadCache, this.store);
                arrayList.add(createChain);
                arrayList2.add(Integer.valueOf(createChain.getBlockIndex()));
            }
        }
        if (this.canceled) {
            return;
        }
        downloadCache.getOutputStream().setRequireStreamBlocks(arrayList2);
        startBlocks(arrayList);
    }

    @Override // com.liulishuo.okdownload.core.NamedRunnable
    public void finished() {
        OkDownload.with().downloadDispatcher().finish(this);
        Util.m13741d(TAG, "call is finished " + this.task.getId());
    }

    void startBlocks(List<DownloadChain> list) throws InterruptedException {
        ArrayList arrayList = new ArrayList(list.size());
        try {
            for (DownloadChain downloadChain : list) {
                arrayList.add(submitChain(downloadChain));
            }
            this.blockChainList.addAll(list);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Future future = (Future) it.next();
                if (!future.isDone()) {
                    try {
                        future.get();
                    } catch (CancellationException | ExecutionException unused) {
                    }
                }
            }
        } finally {
            this.blockChainList.removeAll(list);
        }
    }

    @NonNull
    BreakpointLocalCheck createLocalCheck(@NonNull BreakpointInfo breakpointInfo, long j) {
        return new BreakpointLocalCheck(this.task, breakpointInfo, j);
    }

    @NonNull
    BreakpointRemoteCheck createRemoteCheck(@NonNull BreakpointInfo breakpointInfo) {
        return new BreakpointRemoteCheck(this.task, breakpointInfo);
    }

    void setInfoToTask(@NonNull BreakpointInfo breakpointInfo) {
        DownloadTask.TaskHideWrapper.setBreakpointInfo(this.task, breakpointInfo);
    }

    void assembleBlockAndCallbackFromBeginning(@NonNull BreakpointInfo breakpointInfo, @NonNull BreakpointRemoteCheck breakpointRemoteCheck, @NonNull ResumeFailedCause resumeFailedCause) {
        Util.assembleBlock(this.task, breakpointInfo, breakpointRemoteCheck.getInstanceLength(), breakpointRemoteCheck.isAcceptRange());
        OkDownload.with().callbackDispatcher().dispatch().downloadFromBeginning(this.task, breakpointInfo, resumeFailedCause);
    }

    Future<?> submitChain(DownloadChain downloadChain) {
        return EXECUTOR.submit(downloadChain);
    }

    public boolean equalsTask(@NonNull DownloadTask downloadTask) {
        return this.task.equals(downloadTask);
    }

    @Nullable
    public File getFile() {
        return this.task.getFile();
    }

    @Override // java.lang.Comparable
    @SuppressFBWarnings(justification = "This special case is just for task priority", value = {"Eq"})
    public int compareTo(@NonNull DownloadCall downloadCall) {
        return downloadCall.getPriority() - getPriority();
    }
}
