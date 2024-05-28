package com.p319ss.android.socialbase.downloader.impls;

import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.constants.DownloadCacheSyncStatus;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadProxy;
import com.p319ss.android.socialbase.downloader.downloader.ISqlDownloadCache;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.p319ss.android.socialbase.downloader.p342db.SqlCacheLoadCompleteCallback;
import com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache;
import com.p319ss.android.socialbase.downloader.segment.Segment;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.WeakDownloadHandler;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultDownloadCache */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultDownloadCache implements IDownloadCache {
    private static final int MSG_RESUME = 1;
    private static final String TAG = "DefaultDownloadCache";
    private WeakDownloadHandler.IHandler IHandler = new WeakDownloadHandler.IHandler() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadCache.1
        @Override // com.p319ss.android.socialbase.downloader.thread.WeakDownloadHandler.IHandler
        public void handleMsg(Message message) {
            if (message.what == 1) {
                DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadCache.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            DefaultDownloadCache.this.resumeUnCompleteTask();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    };
    private final DownloadCache downloadCache = new DownloadCache();
    private volatile boolean hasInitSqlDownloadCache;
    private ISqlDownloadCache sqlDownloadCache;
    private volatile boolean unCompleteTaskResumed;
    private WeakDownloadHandler weakHandler;

    public DefaultDownloadCache() {
        this.weakHandler = null;
        if (DownloadSetting.obtainGlobal().optBugFix("fix_sigbus_downloader_db")) {
            if (DownloadUtils.isMainProcess() || !DownloadComponentManager.supportMultiProc()) {
                this.sqlDownloadCache = new SqlDownloadCache();
            } else {
                this.sqlDownloadCache = DownloadComponentManager.getIndependentHolderCreator().createCache(new DownloadComponentManager.IndependentHolderCreator.OnMainProcessRebindErrorListener() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadCache.2
                    @Override // com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.IndependentHolderCreator.OnMainProcessRebindErrorListener
                    public void onRebindError() {
                        DefaultDownloadCache.this.sqlDownloadCache = new SqlDownloadCache();
                        Log.e(DefaultDownloadCache.TAG, "rebind error,use backup sqlDownloadCache");
                    }
                });
            }
        } else {
            this.sqlDownloadCache = new SqlDownloadCache();
        }
        this.hasInitSqlDownloadCache = false;
        this.weakHandler = new WeakDownloadHandler(Looper.getMainLooper(), this.IHandler);
        init();
    }

    public DownloadCache getDownloadCache() {
        return this.downloadCache;
    }

    public ISqlDownloadCache getSqlDownloadCache() {
        return this.sqlDownloadCache;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo onDownloadTaskStart(int i) {
        DownloadInfo onDownloadTaskStart = this.downloadCache.onDownloadTaskStart(i);
        updateDownloadInfoInDB(onDownloadTaskStart);
        return onDownloadTaskStart;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void init() {
        List<DownloadChunk> list;
        DownloadInfo downloadInfo;
        DownloadComponentManager.onDownloadCacheSyncCallback(DownloadCacheSyncStatus.SYNC_START);
        final SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        final SparseArray<List<DownloadChunk>> sparseArray2 = new SparseArray<>();
        synchronized (this.downloadCache) {
            SparseArray<DownloadInfo> downloadInfoMap = this.downloadCache.getDownloadInfoMap();
            for (int i = 0; i < downloadInfoMap.size(); i++) {
                int keyAt = downloadInfoMap.keyAt(i);
                if (keyAt != 0 && (downloadInfo = downloadInfoMap.get(keyAt)) != null) {
                    sparseArray.put(keyAt, downloadInfo);
                }
            }
            SparseArray<List<DownloadChunk>> chunkListMap = this.downloadCache.getChunkListMap();
            for (int i2 = 0; i2 < chunkListMap.size(); i2++) {
                int keyAt2 = chunkListMap.keyAt(i2);
                if (keyAt2 != 0 && (list = chunkListMap.get(keyAt2)) != null) {
                    sparseArray2.put(keyAt2, new CopyOnWriteArrayList(list));
                }
            }
        }
        this.sqlDownloadCache.init(sparseArray, sparseArray2, new SqlCacheLoadCompleteCallback() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadCache.3
            @Override // com.p319ss.android.socialbase.downloader.p342db.SqlCacheLoadCompleteCallback
            public void callback() {
                synchronized (DefaultDownloadCache.this.downloadCache) {
                    SparseArray<DownloadInfo> downloadInfoMap2 = DefaultDownloadCache.this.downloadCache.getDownloadInfoMap();
                    if (sparseArray != null) {
                        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                            int keyAt3 = sparseArray.keyAt(i3);
                            if (keyAt3 != 0) {
                                downloadInfoMap2.put(keyAt3, (DownloadInfo) sparseArray.get(keyAt3));
                            }
                        }
                    }
                    SparseArray<List<DownloadChunk>> chunkListMap2 = DefaultDownloadCache.this.downloadCache.getChunkListMap();
                    if (sparseArray2 != null) {
                        for (int i4 = 0; i4 < sparseArray2.size(); i4++) {
                            int keyAt4 = sparseArray2.keyAt(i4);
                            if (keyAt4 != 0) {
                                chunkListMap2.put(keyAt4, (List) sparseArray2.get(keyAt4));
                            }
                        }
                    }
                }
                DefaultDownloadCache.this.onDownloadCacheSyncSuccess();
                DefaultDownloadCache.this.resumeUnCompleteTaskMayDelayed();
                DownloadComponentManager.onDownloadCacheSyncCallback(DownloadCacheSyncStatus.SYNC_SUCCESS);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadCacheSyncSuccess() {
        synchronized (this) {
            this.hasInitSqlDownloadCache = true;
            notifyAll();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean ensureDownloadCacheSyncSuccess() {
        if (this.hasInitSqlDownloadCache) {
            return true;
        }
        synchronized (this) {
            if (!this.hasInitSqlDownloadCache) {
                Logger.m6460w(TAG, "ensureDownloadCacheSyncSuccess: waiting start!!!!");
                try {
                    wait(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.m6460w(TAG, "ensureDownloadCacheSyncSuccess: waiting end!!!!");
            }
        }
        return this.hasInitSqlDownloadCache;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean isDownloadCacheSyncSuccess() {
        return this.hasInitSqlDownloadCache;
    }

    public void resumeUnCompleteTaskMayDelayed() {
        long j;
        if (DownloadSetting.obtainGlobal().optBugFix("task_resume_delay")) {
            j = 4000;
        } else {
            j = Build.VERSION.SDK_INT >= 23 ? 1000L : 5000L;
        }
        this.weakHandler.sendMessageDelayed(this.weakHandler.obtainMessage(1), j);
    }

    public void resumeUnCompleteTask() {
        List<String> list;
        ArrayList arrayList;
        DownloadInfo downloadInfo;
        DownloadInfo downloadInfo2;
        if (this.hasInitSqlDownloadCache) {
            if (this.unCompleteTaskResumed) {
                Logger.m6475d(TAG, "resumeUnCompleteTask: has resumed, return!!!");
                return;
            }
            this.unCompleteTaskResumed = true;
            if (DownloadUtils.isMainProcess()) {
                IDownloadLaunchHandler downloadLaunchHandler = DownloadComponentManager.getDownloadLaunchHandler();
                if (downloadLaunchHandler != null) {
                    list = downloadLaunchHandler.getResumeMimeTypes();
                    arrayList = (list == null || list.isEmpty()) ? null : new ArrayList();
                } else {
                    list = null;
                    arrayList = null;
                }
                SparseArray sparseArray = new SparseArray();
                synchronized (this) {
                    SparseArray<DownloadInfo> downloadInfoMap = this.downloadCache.getDownloadInfoMap();
                    for (int i = 0; i < downloadInfoMap.size(); i++) {
                        int keyAt = downloadInfoMap.keyAt(i);
                        if (keyAt != 0 && (downloadInfo2 = downloadInfoMap.get(keyAt)) != null) {
                            sparseArray.put(keyAt, downloadInfo2);
                        }
                    }
                }
                if (sparseArray.size() == 0) {
                    return;
                }
                for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                    int keyAt2 = sparseArray.keyAt(i2);
                    if (keyAt2 != 0 && (downloadInfo = (DownloadInfo) sparseArray.get(keyAt2)) != null) {
                        int realStatus = downloadInfo.getRealStatus();
                        int statusAtDbInit = downloadInfo.getStatusAtDbInit();
                        if (statusAtDbInit >= 1 && statusAtDbInit <= 11) {
                            DownloadMonitorHelper.monitorSendWithGlobalSdkMonitor(DownloadComponentManager.getDownloadMonitorListener(), downloadInfo, null, -5);
                        }
                        if (list != null && arrayList != null && downloadInfo.getMimeType() != null && list.contains(downloadInfo.getMimeType()) && (DownloadSetting.obtain(downloadInfo.getId()).optInt("enable_notification_ui") >= 2 || realStatus != -2 || downloadInfo.isPauseReserveOnWifi())) {
                            downloadInfo.setDownloadFromReserveWifi(false);
                            arrayList.add(downloadInfo);
                        }
                    }
                }
                if (downloadLaunchHandler == null || arrayList == null || arrayList.isEmpty()) {
                    return;
                }
                downloadLaunchHandler.onLaunchResume(arrayList, 1);
            }
        }
    }

    private boolean isPauseReserveOnWifi(DownloadInfo downloadInfo) {
        if (downloadInfo != null && downloadInfo.statusInPause()) {
            return downloadInfo.isPauseReserveOnWifi();
        }
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean cacheExist(int i) {
        return getDownloadInfo(i) != null;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo getDownloadInfo(int i) {
        return this.downloadCache.getDownloadInfo(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadInfo> getDownloadInfoList(String str) {
        return this.downloadCache.getDownloadInfoList(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) {
        return this.downloadCache.getFailedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) {
        return this.downloadCache.getSuccessedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) {
        return this.downloadCache.getUnCompletedDownloadInfosWithMimeType(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadInfo> getAllDownloadInfo() {
        return this.downloadCache.getAllDownloadInfo();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<DownloadChunk> getDownloadChunk(int i) {
        return this.downloadCache.getDownloadChunk(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void removeAllDownloadChunk(int i) {
        this.downloadCache.removeAllDownloadChunk(i);
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.removeAllDownloadChunk(i);
                return;
            } else {
                this.sqlDownloadCache.removeAllDownloadChunk(i);
                return;
            }
        }
        this.sqlDownloadCache.removeAllDownloadChunk(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void addDownloadChunk(DownloadChunk downloadChunk) {
        synchronized (this.downloadCache) {
            this.downloadCache.addDownloadChunk(downloadChunk);
        }
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.addDownloadChunk(downloadChunk);
                return;
            } else {
                this.sqlDownloadCache.addDownloadChunk(downloadChunk);
                return;
            }
        }
        this.sqlDownloadCache.addDownloadChunk(downloadChunk);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void addSubDownloadChunk(DownloadChunk downloadChunk) {
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.addDownloadChunk(downloadChunk);
                return;
            } else {
                this.sqlDownloadCache.addDownloadChunk(downloadChunk);
                return;
            }
        }
        this.sqlDownloadCache.addDownloadChunk(downloadChunk);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void updateDownloadChunk(int i, int i2, long j) {
        this.downloadCache.updateDownloadChunk(i, i2, j);
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.updateDownloadChunk(i, i2, j);
                return;
            } else {
                this.sqlDownloadCache.updateDownloadChunk(i, i2, j);
                return;
            }
        }
        this.sqlDownloadCache.updateDownloadChunk(i, i2, j);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void updateSubDownloadChunk(int i, int i2, int i3, long j) {
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.updateSubDownloadChunk(i, i2, i3, j);
                return;
            } else {
                this.sqlDownloadCache.updateSubDownloadChunk(i, i2, i3, j);
                return;
            }
        }
        this.sqlDownloadCache.updateSubDownloadChunk(i, i2, i3, j);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) {
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.updateSubDownloadChunkIndex(i, i2, i3, i4);
                return;
            } else {
                this.sqlDownloadCache.updateSubDownloadChunkIndex(i, i2, i3, i4);
                return;
            }
        }
        this.sqlDownloadCache.updateSubDownloadChunkIndex(i, i2, i3, i4);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo updateChunkCount(int i, int i2) {
        DownloadInfo updateChunkCount = this.downloadCache.updateChunkCount(i, i2);
        updateDownloadInfoInDB(updateChunkCount);
        return updateChunkCount;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean updateDownloadInfo(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return false;
        }
        boolean updateDownloadInfo = this.downloadCache.updateDownloadInfo(downloadInfo);
        updateDownloadInfoInDB(downloadInfo);
        return updateDownloadInfo;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean removeDownloadInfo(int i) {
        try {
            if (DownloadUtils.needNotifyDownloaderProcess()) {
                IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
                if (iDownloadProxy != null) {
                    iDownloadProxy.removeDownloadInfo(i);
                } else {
                    this.sqlDownloadCache.removeDownloadInfo(i);
                }
            } else {
                this.sqlDownloadCache.removeDownloadInfo(i);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return this.downloadCache.removeDownloadInfo(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean removeDownloadTaskData(int i) {
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.removeDownloadTaskData(i);
            } else {
                this.sqlDownloadCache.removeDownloadTaskData(i);
            }
        } else {
            this.sqlDownloadCache.removeDownloadTaskData(i);
        }
        return this.downloadCache.removeDownloadTaskData(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void clearData() {
        try {
            this.downloadCache.clearData();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (DownloadUtils.needNotifyDownloaderProcess()) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.clearData();
                return;
            } else {
                this.sqlDownloadCache.clearData();
                return;
            }
        }
        this.sqlDownloadCache.clearData();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskConnected(int i, long j, String str, String str2) {
        DownloadInfo OnDownloadTaskConnected = this.downloadCache.OnDownloadTaskConnected(i, j, str, str2);
        updateDownloadInfoInDB(OnDownloadTaskConnected);
        return OnDownloadTaskConnected;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskProgress(int i, long j) {
        DownloadInfo OnDownloadTaskProgress = this.downloadCache.OnDownloadTaskProgress(i, j);
        updateDownloadInfoInDB(OnDownloadTaskProgress, false);
        return OnDownloadTaskProgress;
    }

    private void updateDownloadInfoInDB(DownloadInfo downloadInfo) {
        updateDownloadInfoInDB(downloadInfo, true);
    }

    private void updateDownloadInfoInDB(DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return;
        }
        if (!DownloadUtils.needNotifyDownloaderProcess()) {
            this.sqlDownloadCache.updateDownloadInfo(downloadInfo);
        } else if (z) {
            IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
            if (iDownloadProxy != null) {
                iDownloadProxy.updateDownloadInfo(downloadInfo);
            } else {
                this.sqlDownloadCache.updateDownloadInfo(downloadInfo);
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskError(int i, long j) {
        DownloadInfo OnDownloadTaskError = this.downloadCache.OnDownloadTaskError(i, j);
        syncDownloadInfoFromOtherCache(i, null);
        return OnDownloadTaskError;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskRetry(int i) {
        DownloadInfo OnDownloadTaskRetry = this.downloadCache.OnDownloadTaskRetry(i);
        updateDownloadInfoInDB(OnDownloadTaskRetry);
        return OnDownloadTaskRetry;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskCompleted(int i, long j) {
        DownloadInfo OnDownloadTaskCompleted = this.downloadCache.OnDownloadTaskCompleted(i, j);
        syncDownloadInfoFromOtherCache(i, null);
        return OnDownloadTaskCompleted;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskPause(int i, long j) {
        DownloadInfo OnDownloadTaskPause = this.downloadCache.OnDownloadTaskPause(i, j);
        syncDownloadInfoFromOtherCache(i, null);
        return OnDownloadTaskPause;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskCancel(int i, long j) {
        DownloadInfo OnDownloadTaskCancel = this.downloadCache.OnDownloadTaskCancel(i, j);
        syncDownloadInfoFromOtherCache(i, null);
        return OnDownloadTaskCancel;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskPrepare(int i) {
        DownloadInfo OnDownloadTaskPrepare = this.downloadCache.OnDownloadTaskPrepare(i);
        updateDownloadInfoInDB(OnDownloadTaskPrepare);
        return OnDownloadTaskPrepare;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public DownloadInfo OnDownloadTaskIntercept(int i) {
        DownloadInfo OnDownloadTaskIntercept = this.downloadCache.OnDownloadTaskIntercept(i);
        updateDownloadInfoInDB(OnDownloadTaskIntercept);
        return OnDownloadTaskIntercept;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void syncDownloadInfo(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        this.downloadCache.updateDownloadInfo(downloadInfo);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void syncDownloadChunks(int i, List<DownloadChunk> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.downloadCache.syncDownloadChunks(i, list);
        if (DownloadUtils.isDownloaderProcess()) {
            this.sqlDownloadCache.syncDownloadInfoFromOtherCache(i, list);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) {
        try {
            updateDownloadInfo(this.downloadCache.getDownloadInfo(i));
            if (list == null) {
                list = this.downloadCache.getDownloadChunk(i);
            }
            if (DownloadUtils.needNotifyDownloaderProcess()) {
                IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
                if (iDownloadProxy != null) {
                    iDownloadProxy.syncDownloadInfoFromOtherCache(i, list);
                    return;
                } else {
                    this.sqlDownloadCache.syncDownloadInfoFromOtherCache(i, list);
                    return;
                }
            }
            this.sqlDownloadCache.syncDownloadInfoFromOtherCache(i, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public List<Segment> getSegments(int i) {
        List<Segment> segments = this.downloadCache.getSegments(i);
        return (segments == null || segments.size() == 0) ? this.sqlDownloadCache.getSegments(i) : segments;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public Map<Long, Segment> getSegmentMap(int i) {
        Map<Long, Segment> segmentMap = this.downloadCache.getSegmentMap(i);
        if (segmentMap == null || segmentMap.isEmpty()) {
            Map<Long, Segment> segmentMap2 = this.sqlDownloadCache.getSegmentMap(i);
            this.downloadCache.updateSegments(i, segmentMap2);
            return segmentMap2;
        }
        return segmentMap;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean updateSegments(int i, Map<Long, Segment> map) {
        this.downloadCache.updateSegments(i, map);
        this.sqlDownloadCache.updateSegments(i, map);
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void removeSegments(int i) {
        this.downloadCache.removeSegments(i);
        this.sqlDownloadCache.removeSegments(i);
    }
}
