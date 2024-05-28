package com.liulishuo.okdownload.core.breakpoint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BreakpointStoreOnSQLite implements DownloadStore {
    private static final String TAG = "BreakpointStoreOnSQLite";
    protected final BreakpointSQLiteHelper helper;
    protected final BreakpointStoreOnCache onCache;

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    @Nullable
    public BreakpointInfo getAfterCompleted(int i) {
        return null;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean isOnlyMemoryCache() {
        return false;
    }

    BreakpointStoreOnSQLite(BreakpointSQLiteHelper breakpointSQLiteHelper, BreakpointStoreOnCache breakpointStoreOnCache) {
        this.helper = breakpointSQLiteHelper;
        this.onCache = breakpointStoreOnCache;
    }

    public BreakpointStoreOnSQLite(Context context) {
        this.helper = new BreakpointSQLiteHelper(context.getApplicationContext());
        this.onCache = new BreakpointStoreOnCache(this.helper.loadToCache(), this.helper.loadDirtyFileList(), this.helper.loadResponseFilenameToMap());
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    @Nullable
    public BreakpointInfo get(int i) {
        return this.onCache.get(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    @NonNull
    public BreakpointInfo createAndInsert(@NonNull DownloadTask downloadTask) throws IOException {
        BreakpointInfo createAndInsert = this.onCache.createAndInsert(downloadTask);
        this.helper.insert(createAndInsert);
        return createAndInsert;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onTaskStart(int i) {
        this.onCache.onTaskStart(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onSyncToFilesystemSuccess(@NonNull BreakpointInfo breakpointInfo, int i, long j) throws IOException {
        this.onCache.onSyncToFilesystemSuccess(breakpointInfo, i, j);
        this.helper.updateBlockIncrease(breakpointInfo, i, breakpointInfo.getBlock(i).getCurrentOffset());
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean update(@NonNull BreakpointInfo breakpointInfo) throws IOException {
        boolean update = this.onCache.update(breakpointInfo);
        this.helper.updateInfo(breakpointInfo);
        String filename = breakpointInfo.getFilename();
        Util.m13741d(TAG, "update " + breakpointInfo);
        if (breakpointInfo.isTaskOnlyProvidedParentPath() && filename != null) {
            this.helper.updateFilename(breakpointInfo.getUrl(), filename);
        }
        return update;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onTaskEnd(int i, @NonNull EndCause endCause, @Nullable Exception exc) {
        this.onCache.onTaskEnd(i, endCause, exc);
        if (endCause == EndCause.COMPLETED) {
            this.helper.removeInfo(i);
        }
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public boolean markFileDirty(int i) {
        if (this.onCache.markFileDirty(i)) {
            this.helper.markFileDirty(i);
            return true;
        }
        return false;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public boolean markFileClear(int i) {
        if (this.onCache.markFileClear(i)) {
            this.helper.markFileClear(i);
            return true;
        }
        return false;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public void remove(int i) {
        this.onCache.remove(i);
        this.helper.removeInfo(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public int findOrCreateId(@NonNull DownloadTask downloadTask) {
        return this.onCache.findOrCreateId(downloadTask);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    @Nullable
    public BreakpointInfo findAnotherInfoFromCompare(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
        return this.onCache.findAnotherInfoFromCompare(downloadTask, breakpointInfo);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean isFileDirty(int i) {
        return this.onCache.isFileDirty(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    @Nullable
    public String getResponseFilename(String str) {
        return this.onCache.getResponseFilename(str);
    }

    void close() {
        this.helper.close();
    }

    @NonNull
    public DownloadStore createRemitSelf() {
        return new RemitStoreOnSQLite(this);
    }
}
