package com.p319ss.android.socialbase.downloader.downloader;

import android.content.Context;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadSettings;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.p319ss.android.socialbase.downloader.monitor.IDownloadMonitorListener;
import com.p319ss.android.socialbase.downloader.network.IDownloadDns;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloaderBuilder */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloaderBuilder {
    private IChunkAdjustCalculator chunkAdjustCalculator;
    private IChunkCntCalculator chunkCntCalculator;
    private ExecutorService chunkDownloadExecutor;
    private final Context context;
    private ExecutorService cpuThreadExecutor;
    private ExecutorService dbThreadExecutor;
    private IDownloadCache downloadCache;
    private IDownloadDns downloadDns;
    private boolean downloadInMultiProcess;
    private IDownloadLaunchHandler downloadLaunchHandler;
    private IDownloadMonitorListener downloadMonitorListener;
    private IDownloadSettings downloadSetting;
    private IDownloadHeadHttpService headHttpService;
    private IDownloadHttpService httpService;
    private IDownloadIdGenerator idGenerator;
    private ExecutorService ioThreadExecutor;
    private int maxDownloadPoolSize;
    private ExecutorService mixApkDownloadExecutor;
    private ExecutorService mixDefaultDownloadExecutor;
    private ExecutorService mixFrequentDownloadExecutor;
    private IMonitorConfig monitorConfig;
    private INotificationClickCallback notificationClickCallback;
    private ExecutorService okHttpDispatcherExecutor;
    private ITTNetHandler ttNetHandler;
    private int writeBufferSize;
    private List<IDownloadCompleteHandler> downloadCompleteHandlers = new ArrayList();
    private boolean needAutoRefreshUnSuccessTask = true;
    private int downloadExpSwitch = 1056964607;

    public DownloaderBuilder(Context context) {
        this.context = context;
    }

    public DownloaderBuilder writeBufferSize(int i) {
        this.writeBufferSize = i;
        return this;
    }

    public DownloaderBuilder chunkAdjustCalculator(IChunkAdjustCalculator iChunkAdjustCalculator) {
        this.chunkAdjustCalculator = iChunkAdjustCalculator;
        return this;
    }

    public DownloaderBuilder idGenerator(IDownloadIdGenerator iDownloadIdGenerator) {
        this.idGenerator = iDownloadIdGenerator;
        return this;
    }

    public DownloaderBuilder downloadCache(IDownloadCache iDownloadCache) {
        this.downloadCache = iDownloadCache;
        return this;
    }

    public DownloaderBuilder maxDownloadPoolSize(int i) {
        this.maxDownloadPoolSize = i;
        return this;
    }

    public DownloaderBuilder chunkCntCalculator(IChunkCntCalculator iChunkCntCalculator) {
        this.chunkCntCalculator = iChunkCntCalculator;
        return this;
    }

    public DownloaderBuilder httpService(IDownloadHttpService iDownloadHttpService) {
        this.httpService = iDownloadHttpService;
        return this;
    }

    public DownloaderBuilder headHttpService(IDownloadHeadHttpService iDownloadHeadHttpService) {
        this.headHttpService = iDownloadHeadHttpService;
        return this;
    }

    public DownloaderBuilder downloadLaunchHandler(IDownloadLaunchHandler iDownloadLaunchHandler) {
        this.downloadLaunchHandler = iDownloadLaunchHandler;
        return this;
    }

    public DownloaderBuilder cpuThreadExecutor(ExecutorService executorService) {
        this.cpuThreadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder ioThreadExecutor(ExecutorService executorService) {
        this.ioThreadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder mixDefaultDownloadExecutor(ExecutorService executorService) {
        this.mixDefaultDownloadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder mixFrequentDownloadExecutor(ExecutorService executorService) {
        this.mixFrequentDownloadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder mixApkDownloadExecutor(ExecutorService executorService) {
        this.mixApkDownloadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder dbThreadExecutor(ExecutorService executorService) {
        this.dbThreadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder chunkThreadExecutor(ExecutorService executorService) {
        this.chunkDownloadExecutor = executorService;
        return this;
    }

    public DownloaderBuilder okHttpDispatcherExecutor(ExecutorService executorService) {
        this.okHttpDispatcherExecutor = executorService;
        return this;
    }

    public DownloaderBuilder downloadInMultiProcess(boolean z) {
        this.downloadInMultiProcess = z;
        return this;
    }

    public DownloaderBuilder monitorConfig(IMonitorConfig iMonitorConfig) {
        this.monitorConfig = iMonitorConfig;
        return this;
    }

    public DownloaderBuilder downloadExpSwitch(int i) {
        this.downloadExpSwitch = i;
        return this;
    }

    public DownloaderBuilder ttNetHandler(ITTNetHandler iTTNetHandler) {
        this.ttNetHandler = iTTNetHandler;
        return this;
    }

    public DownloaderBuilder addDownloadCompleteHandler(IDownloadCompleteHandler iDownloadCompleteHandler) {
        synchronized (this.downloadCompleteHandlers) {
            if (iDownloadCompleteHandler != null) {
                if (!this.downloadCompleteHandlers.contains(iDownloadCompleteHandler)) {
                    this.downloadCompleteHandlers.add(iDownloadCompleteHandler);
                    return this;
                }
            }
            return this;
        }
    }

    public DownloaderBuilder needAutoRefreshUnSuccessTask(boolean z) {
        this.needAutoRefreshUnSuccessTask = z;
        return this;
    }

    public DownloaderBuilder notificationClickCallback(INotificationClickCallback iNotificationClickCallback) {
        this.notificationClickCallback = iNotificationClickCallback;
        return this;
    }

    public DownloaderBuilder downloadSetting(IDownloadSettings iDownloadSettings) {
        this.downloadSetting = iDownloadSettings;
        return this;
    }

    public DownloaderBuilder downloadMonitorListener(IDownloadMonitorListener iDownloadMonitorListener) {
        this.downloadMonitorListener = iDownloadMonitorListener;
        return this;
    }

    public DownloaderBuilder downloadDns(IDownloadDns iDownloadDns) {
        this.downloadDns = iDownloadDns;
        return this;
    }

    public Context getContext() {
        return this.context;
    }

    public IDownloadCache getDownloadCache() {
        return this.downloadCache;
    }

    public IDownloadIdGenerator getIdGenerator() {
        return this.idGenerator;
    }

    public IDownloadHttpService getHttpService() {
        return this.httpService;
    }

    public IDownloadHeadHttpService getHeadHttpService() {
        return this.headHttpService;
    }

    public IDownloadLaunchHandler getDownloadLaunchHandler() {
        return this.downloadLaunchHandler;
    }

    public ExecutorService getCPUThreadExecutor() {
        return this.cpuThreadExecutor;
    }

    public ExecutorService getIOThreadExecutor() {
        return this.ioThreadExecutor;
    }

    public ExecutorService getMixDefaultDownloadExecutor() {
        return this.mixDefaultDownloadExecutor;
    }

    public ExecutorService getMixFrequentDownloadExecutor() {
        return this.mixFrequentDownloadExecutor;
    }

    public ExecutorService getMixApkDownloadExecutor() {
        return this.mixApkDownloadExecutor;
    }

    public ExecutorService getDBThreadExecutor() {
        return this.dbThreadExecutor;
    }

    public ExecutorService getChunkThreadExecutor() {
        return this.chunkDownloadExecutor;
    }

    public ExecutorService getOkHttpDispatcherExecutor() {
        return this.okHttpDispatcherExecutor;
    }

    public List<IDownloadCompleteHandler> getDownloadCompleteHandlers() {
        return this.downloadCompleteHandlers;
    }

    public int getMaxDownloadPoolSize() {
        return this.maxDownloadPoolSize;
    }

    public IChunkCntCalculator getChunkCntCalculator() {
        return this.chunkCntCalculator;
    }

    public IChunkAdjustCalculator getChunkAdjustCalculator() {
        return this.chunkAdjustCalculator;
    }

    public int getWriteBufferSize() {
        return this.writeBufferSize;
    }

    public boolean isDownloadInMultiProcess() {
        return this.downloadInMultiProcess;
    }

    public IMonitorConfig getMonitorConfig() {
        return this.monitorConfig;
    }

    public int getDownloadExpSwitch() {
        return this.downloadExpSwitch;
    }

    public ITTNetHandler getTTNetHandler() {
        return this.ttNetHandler;
    }

    public boolean needAutoRefreshUnSuccessTask() {
        return this.needAutoRefreshUnSuccessTask;
    }

    public INotificationClickCallback getNotificationClickCallback() {
        return this.notificationClickCallback;
    }

    public IDownloadSettings getDownloadSetting() {
        return this.downloadSetting;
    }

    public IDownloadMonitorListener getDownloadMonitorListener() {
        return this.downloadMonitorListener;
    }

    public IDownloadDns getDownloadDns() {
        return this.downloadDns;
    }

    public Downloader build() {
        return new Downloader(this);
    }
}
