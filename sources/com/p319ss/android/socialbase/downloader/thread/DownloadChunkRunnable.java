package com.p319ss.android.socialbase.downloader.thread;

import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;

/* renamed from: com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadChunkRunnable implements Runnable {
    private static final String TAG = "DownloadChunkRunnable";
    private final IDownloadRunnableCallback callback;
    private volatile boolean canceled;
    private DownloadChunk curDownloadChunk;
    private IDownloadCache downloadCache;
    private DownloadChunk downloadChunk;
    private DownloadInfo downloadInfo;
    private DownloadResponseHandler downloadResponseHandler;
    private final DownloadTask downloadTask;
    private IDownloadHttpConnection httpConnection;
    private boolean isHttpConnectionInject;
    private volatile boolean paused;

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.isHttpConnectionInject = false;
        this.downloadChunk = downloadChunk;
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
        }
        this.callback = iDownloadRunnableCallback;
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.downloadChunk.setChunkRunnable(this);
    }

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadHttpConnection iDownloadHttpConnection, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this(downloadChunk, downloadTask, iDownloadRunnableCallback);
        this.httpConnection = iDownloadHttpConnection;
    }

    public int getChunkIndex() {
        return this.downloadChunk.getChunkIndex();
    }

    private String getUrl() {
        return this.downloadInfo.getConnectionUrl();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r3.curDownloadChunk.setDownloading(false);
     */
    @Override // java.lang.Runnable
    @android.annotation.SuppressLint({"DefaultLocale"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r3 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.ss.android.socialbase.downloader.model.DownloadChunk r0 = r3.downloadChunk
            r3.curDownloadChunk = r0
        L9:
            r0 = 0
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setChunkRunnable(r3)     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.downloadChunkInner(r1)     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L1d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5d
            goto L4d
        L1d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L29
            goto L4d
        L29:
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r1 = r3.callback     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            int r2 = r2.getChunkIndex()     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r1.getUnCompletedSubChunk(r2)     // Catch: java.lang.Throwable -> L5d
            r3.curDownloadChunk = r1     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L4d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L42
            goto L4d
        L42:
            r1 = 50
            java.lang.Thread.sleep(r1)     // Catch: java.lang.Throwable -> L48
            goto L9
        L48:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L5d
            goto L9
        L4d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk
            if (r1 == 0) goto L54
            r1.setDownloading(r0)
        L54:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            return
        L5d:
            r1 = move-exception
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk
            if (r2 == 0) goto L65
            r2.setDownloading(r0)
        L65:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadChunkRunnable.run():void");
    }

    public void refreshResponseHandleOffset(long j, long j2) {
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler == null) {
            return;
        }
        downloadResponseHandler.setEndOffset(j, j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01a7 A[Catch: Throwable -> 0x01c2, BaseException -> 0x01c4, all -> 0x01c7, TRY_LEAVE, TryCatch #4 {all -> 0x01c7, blocks: (B:23:0x0054, B:27:0x005e, B:31:0x0069, B:36:0x00ba, B:38:0x00be, B:46:0x00d5, B:63:0x0101, B:67:0x010b, B:69:0x010f, B:71:0x0113, B:74:0x0120, B:75:0x0135, B:80:0x013f, B:82:0x014e, B:84:0x0155, B:88:0x0161, B:90:0x016c, B:91:0x017b, B:138:0x01e9, B:142:0x01f3, B:144:0x01f9, B:147:0x0202, B:149:0x020a, B:151:0x0210, B:155:0x021b, B:157:0x021f, B:159:0x0227, B:161:0x0238, B:170:0x0262, B:172:0x0268, B:174:0x0275, B:178:0x027d, B:173:0x026f, B:164:0x0247, B:165:0x0255, B:180:0x0288, B:182:0x0290, B:184:0x0298, B:186:0x02a0, B:188:0x02a8, B:191:0x02b1, B:126:0x01cc, B:130:0x01d6, B:133:0x01dd, B:77:0x0137, B:79:0x013e, B:97:0x018a, B:98:0x0198, B:106:0x01a3, B:108:0x01a7, B:116:0x01be, B:117:0x01c1, B:52:0x00e5, B:54:0x00e9), top: B:202:0x01e9, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010b A[Catch: all -> 0x01c7, Throwable -> 0x01ca, BaseException -> 0x01e6, TRY_ENTER, TryCatch #4 {all -> 0x01c7, blocks: (B:23:0x0054, B:27:0x005e, B:31:0x0069, B:36:0x00ba, B:38:0x00be, B:46:0x00d5, B:63:0x0101, B:67:0x010b, B:69:0x010f, B:71:0x0113, B:74:0x0120, B:75:0x0135, B:80:0x013f, B:82:0x014e, B:84:0x0155, B:88:0x0161, B:90:0x016c, B:91:0x017b, B:138:0x01e9, B:142:0x01f3, B:144:0x01f9, B:147:0x0202, B:149:0x020a, B:151:0x0210, B:155:0x021b, B:157:0x021f, B:159:0x0227, B:161:0x0238, B:170:0x0262, B:172:0x0268, B:174:0x0275, B:178:0x027d, B:173:0x026f, B:164:0x0247, B:165:0x0255, B:180:0x0288, B:182:0x0290, B:184:0x0298, B:186:0x02a0, B:188:0x02a8, B:191:0x02b1, B:126:0x01cc, B:130:0x01d6, B:133:0x01dd, B:77:0x0137, B:79:0x013e, B:97:0x018a, B:98:0x0198, B:106:0x01a3, B:108:0x01a7, B:116:0x01be, B:117:0x01c1, B:52:0x00e5, B:54:0x00e9), top: B:202:0x01e9, inners: #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean downloadChunkInner(com.p319ss.android.socialbase.downloader.model.DownloadChunk r31) {
        /*
            Method dump skipped, instructions count: 702
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.thread.DownloadChunkRunnable.downloadChunkInner(com.ss.android.socialbase.downloader.model.DownloadChunk):boolean");
    }

    private void revertDownloadChunk(DownloadChunk downloadChunk, long j) {
        DownloadChunk firstReuseChunk = downloadChunk.isHostChunk() ? downloadChunk.getFirstReuseChunk() : downloadChunk;
        if (firstReuseChunk != null) {
            if (firstReuseChunk.canRefreshCurOffsetForReuseChunk()) {
                this.downloadCache.updateDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getHostChunkIndex(), j);
            }
            firstReuseChunk.setCurrentOffset(j);
            this.downloadCache.updateSubDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getChunkIndex(), firstReuseChunk.getHostChunkIndex(), j);
        } else if (downloadChunk.isHostChunk()) {
            this.downloadCache.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getChunkIndex(), j);
        }
    }

    private void closeConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.httpConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.end();
            this.httpConnection = null;
        }
    }

    private boolean isStoppedStatus() {
        return this.paused || this.canceled;
    }

    public void pause() {
        this.paused = true;
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler != null) {
            downloadResponseHandler.pause();
        }
    }

    public void cancel() {
        this.canceled = true;
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler != null) {
            downloadResponseHandler.cancel();
        }
    }
}
