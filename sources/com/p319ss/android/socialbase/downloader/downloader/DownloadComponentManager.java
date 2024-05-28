package com.p319ss.android.socialbase.downloader.downloader;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.constants.BoundType;
import com.p319ss.android.socialbase.downloader.constants.DownloadCacheSyncStatus;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadSettings;
import com.p319ss.android.socialbase.downloader.depend.IDownloadTaskExecuteListener;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.p319ss.android.socialbase.downloader.depend.ProcessCallback;
import com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler;
import com.p319ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.p319ss.android.socialbase.downloader.impls.DefaultChunkAdjustCalculator;
import com.p319ss.android.socialbase.downloader.impls.DefaultChunkCntCalculator;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadEngine;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadHeadHttpService;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadHttpService;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadServiceHandler;
import com.p319ss.android.socialbase.downloader.impls.DefaultIdGenerator;
import com.p319ss.android.socialbase.downloader.impls.DefaultRetryDelayTimeCalculator;
import com.p319ss.android.socialbase.downloader.impls.DownloadHandleService;
import com.p319ss.android.socialbase.downloader.impls.DownloadProxy;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.monitor.IDownloadMonitorListener;
import com.p319ss.android.socialbase.downloader.monitor.InnerEventListener;
import com.p319ss.android.socialbase.downloader.network.IDownloadDns;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpService;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.DefaultThreadFactory;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadComponentManager {
    public static final int NET_LIB_DEFAULT = 0;
    public static final int NET_LIB_HOST = 1;
    private static volatile Context appContext;
    private static volatile IChunkAdjustCalculator chunkAdjustCalculator;
    private static volatile IChunkCntCalculator chunkCntCalculator;
    private static volatile ExecutorService chunkDownloadExecutor;
    private static volatile ExecutorService cpuThreadExecutor;
    private static volatile ExecutorService dbThreadExecutor;
    private static volatile IDownloadDns defaultDownloadDns;
    private static volatile IDownloadHeadHttpService defaultHeadHttpService;
    private static volatile IDownloadHttpService defaultHttpService;
    private static volatile IDownloadCache downloadCache;
    private static final List<IDownloadCacheSyncStatusListener> downloadCacheSyncStatusListeners;
    private static volatile IDownloadDns downloadDns;
    private static volatile AbsDownloadEngine downloadEngine;
    private static InnerEventListener downloadEventListener;
    private static int downloadExpSwitchCode;
    private static volatile IDownloadLaunchHandler downloadLaunchHandler;
    private static volatile IDownloadMonitorListener downloadMonitorListener;
    private static volatile DownloadReceiver downloadReceiver;
    private static volatile IDownloadServiceHandler downloadServiceHandler;
    private static volatile IDownloadSettings downloadSettings;
    private static final List<IDownloadTaskExecuteListener> downloadTaskExecuteListeners;
    private static final int fixedDBPoolSize;
    private static final int fixedMIXPoolSize;
    private static volatile boolean hasInit;
    private static volatile IDownloadHeadHttpService headHttpService;
    private static volatile IDownloadHttpService httpService;
    private static boolean httpServiceInit;
    private static volatile ITTNetHandler iTTNetHandler;
    private static volatile IDownloadIdGenerator idGenerator;
    private static volatile IDownloadServiceHandler independentDownloadServiceHandler;
    private static volatile IndependentHolderCreator independentHolderCreator;
    private static volatile ExecutorService ioThreadExecutor;
    private static int maxDownloadPoolSize;
    private static volatile ExecutorService mixApkDownloadExecutor;
    private static volatile ExecutorService mixDefaultDownloadExecutor;
    private static volatile ExecutorService mixFrequentDownloadExecutor;
    private static volatile IMonitorConfig monitorConfig;
    private static boolean needAutoRefreshUnSuccessTask;
    private static boolean notAutoRebootService;
    private static volatile INotificationClickCallback notificationClickCallback;
    private static volatile ExecutorService okHttpDispatcherExecutor;
    private static IReserveWifiStatusListener reserveWifiStatusListener;
    private static volatile IRetryDelayTimeCalculator retryDelayTimeCalculator;
    private static int writeBufferSize;
    private static volatile List<ProcessCallback> processCallbacks = new ArrayList();
    private static volatile boolean downloadInMultiProcess = false;
    private static volatile OkHttpClient sOkHttpClient = null;
    private static final List<IDownloadCompleteHandler> downloadCompleteHandlers = new ArrayList();
    private static boolean isReceiverRegistered = false;
    private static final int fixedCPUPoolSize = Runtime.getRuntime().availableProcessors() + 1;
    private static final int fixedIOPoolSize = (Runtime.getRuntime().availableProcessors() * 2) + 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager$IndependentHolderCreator */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface IndependentHolderCreator {

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager$IndependentHolderCreator$OnMainProcessRebindErrorListener */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public interface OnMainProcessRebindErrorListener {
            void onRebindError();
        }

        ISqlDownloadCache createCache(OnMainProcessRebindErrorListener onMainProcessRebindErrorListener);

        IDownloadProxy createProxy();

        IDownloadServiceHandler createServiceHandler();
    }

    public static AlarmManager getAlarmManager() {
        return null;
    }

    public static void setReserveWifiStatusListener(IReserveWifiStatusListener iReserveWifiStatusListener) {
    }

    static {
        int i = fixedCPUPoolSize;
        fixedMIXPoolSize = i;
        fixedDBPoolSize = i;
        writeBufferSize = 8192;
        downloadCacheSyncStatusListeners = new ArrayList();
        downloadTaskExecuteListeners = new ArrayList();
        needAutoRefreshUnSuccessTask = true;
        notAutoRebootService = false;
        hasInit = false;
    }

    private DownloadComponentManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void initComponent(DownloaderBuilder downloaderBuilder) {
        synchronized (DownloadComponentManager.class) {
            if (hasInit) {
                Logger.m6472e("DownloadComponentManager", "component has init");
                return;
            }
            boolean z = downloadInMultiProcess;
            setDownloadBuilder(downloaderBuilder);
            if (downloadCache == null) {
                downloadCache = new DefaultDownloadCache();
            }
            if (downloadServiceHandler == null) {
                downloadServiceHandler = new DefaultDownloadServiceHandler();
            }
            if (independentDownloadServiceHandler == null && independentHolderCreator != null) {
                independentDownloadServiceHandler = independentHolderCreator.createServiceHandler();
            }
            if (idGenerator == null) {
                idGenerator = new DefaultIdGenerator();
            }
            if (downloadEngine == null) {
                downloadEngine = new DefaultDownloadEngine();
            }
            if (chunkCntCalculator == null) {
                chunkCntCalculator = new DefaultChunkCntCalculator();
            }
            if (chunkAdjustCalculator == null) {
                chunkAdjustCalculator = new DefaultChunkAdjustCalculator();
            }
            if (retryDelayTimeCalculator == null) {
                retryDelayTimeCalculator = new DefaultRetryDelayTimeCalculator();
            }
            if (maxDownloadPoolSize <= 0 || maxDownloadPoolSize > fixedCPUPoolSize) {
                maxDownloadPoolSize = fixedCPUPoolSize;
            }
            registerDownloadReceiver();
            if (downloadInMultiProcess && !z && !DownloadUtils.isDownloaderProcess()) {
                DownloadProxy.get(true).startService();
            } else if (DownloadUtils.isMainThread()) {
                ExecutorService iOThreadExecutor = getIOThreadExecutor();
                if (iOThreadExecutor != null) {
                    iOThreadExecutor.execute(new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Context appContext2 = DownloadComponentManager.getAppContext();
                            if (appContext2 != null) {
                                DownloadUtils.getCurProcessName(appContext2);
                            }
                        }
                    });
                }
            } else {
                Context appContext2 = getAppContext();
                if (appContext2 != null) {
                    DownloadUtils.getCurProcessName(appContext2);
                }
            }
            ensureOPPO();
            hasInit = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void coverComponent(DownloaderBuilder downloaderBuilder) {
        synchronized (DownloadComponentManager.class) {
            setDownloadBuilder(downloaderBuilder);
        }
    }

    private static void setDownloadBuilder(DownloaderBuilder downloaderBuilder) {
        if (downloaderBuilder != null) {
            if (downloaderBuilder.getContext() != null) {
                setAppContext(downloaderBuilder.getContext());
            }
            if (downloaderBuilder.getDownloadCache() != null) {
                setDownloadCache(downloaderBuilder.getDownloadCache());
            }
            if (downloaderBuilder.getIdGenerator() != null) {
                setIdGenerator(downloaderBuilder.getIdGenerator());
            }
            if (downloaderBuilder.getChunkCntCalculator() != null) {
                setChunkCntCalculator(downloaderBuilder.getChunkCntCalculator());
            }
            if (downloaderBuilder.getNotificationClickCallback() != null) {
                setNotificationClickCallback(downloaderBuilder.getNotificationClickCallback());
            }
            if (downloaderBuilder.getMaxDownloadPoolSize() != 0) {
                setMaxDownloadPoolSize(downloaderBuilder.getMaxDownloadPoolSize());
            }
            if (downloaderBuilder.getHttpService() != null) {
                setHttpService(downloaderBuilder.getHttpService());
            }
            if (downloaderBuilder.getHeadHttpService() != null) {
                setHeadHttpService(downloaderBuilder.getHeadHttpService());
            }
            if (downloaderBuilder.getDownloadLaunchHandler() != null) {
                setDownloadLaunchHandler(downloaderBuilder.getDownloadLaunchHandler());
            }
            if (downloaderBuilder.getCPUThreadExecutor() != null) {
                setCPUThreadExecutor(downloaderBuilder.getCPUThreadExecutor());
            }
            if (downloaderBuilder.getIOThreadExecutor() != null) {
                setIOThreadExecutor(downloaderBuilder.getIOThreadExecutor());
            }
            if (downloaderBuilder.getMixDefaultDownloadExecutor() != null) {
                setMixDefaultDownloadExecutor(downloaderBuilder.getMixDefaultDownloadExecutor());
            }
            if (downloaderBuilder.getMixFrequentDownloadExecutor() != null) {
                setMixFrequentDownloadExecutor(downloaderBuilder.getMixFrequentDownloadExecutor());
            }
            if (downloaderBuilder.getMixApkDownloadExecutor() != null) {
                setMixApkDownloadExecutor(downloaderBuilder.getMixApkDownloadExecutor());
            }
            if (downloaderBuilder.getDBThreadExecutor() != null) {
                setDBThreadExecutor(downloaderBuilder.getDBThreadExecutor());
            }
            if (downloaderBuilder.getChunkThreadExecutor() != null) {
                setChunkDownloadExecutor(downloaderBuilder.getChunkThreadExecutor());
            }
            if (downloaderBuilder.getOkHttpDispatcherExecutor() != null) {
                setOkHttpDispatcherExecutor(downloaderBuilder.getOkHttpDispatcherExecutor());
            }
            if (!downloaderBuilder.getDownloadCompleteHandlers().isEmpty()) {
                setDownloadCompleteHandlers(downloaderBuilder.getDownloadCompleteHandlers());
            }
            if (downloaderBuilder.getMonitorConfig() != null) {
                monitorConfig = downloaderBuilder.getMonitorConfig();
            }
            if (downloaderBuilder.getWriteBufferSize() > 1024) {
                writeBufferSize = downloaderBuilder.getWriteBufferSize();
            }
            if (downloaderBuilder.getChunkAdjustCalculator() != null) {
                setChunkAdjustCalculator(downloaderBuilder.getChunkAdjustCalculator());
            }
            if (downloaderBuilder.isDownloadInMultiProcess()) {
                downloadInMultiProcess = true;
            }
            if (downloaderBuilder.getDownloadExpSwitch() != 0) {
                downloadExpSwitchCode = downloaderBuilder.getDownloadExpSwitch();
            }
            if (downloaderBuilder.getDownloadSetting() != null) {
                setDownloadSetting(downloaderBuilder.getDownloadSetting());
            }
            if (downloaderBuilder.getDownloadDns() != null) {
                downloadDns = downloaderBuilder.getDownloadDns();
            }
            if (downloaderBuilder.getTTNetHandler() != null) {
                iTTNetHandler = downloaderBuilder.getTTNetHandler();
                if (iTTNetHandler.isTTNetEnable()) {
                    setHttpService(iTTNetHandler.getTTNetDownloadHttpService());
                    setHeadHttpService(iTTNetHandler.getTTNetDownloadHeadHttpService());
                } else {
                    setHttpService(getDefaultHttpService());
                    setHeadHttpService(getDefaultHeadHttpService());
                }
            }
            needAutoRefreshUnSuccessTask(downloaderBuilder.needAutoRefreshUnSuccessTask());
            if (downloaderBuilder.getDownloadMonitorListener() != null) {
                setDownloadMonitorListener(downloaderBuilder.getDownloadMonitorListener());
            }
        }
    }

    private static void registerDownloadReceiver() {
        if (downloadReceiver == null) {
            downloadReceiver = new DownloadReceiver();
        }
        if (isReceiverRegistered) {
            return;
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            appContext.registerReceiver(downloadReceiver, intentFilter);
            isReceiverRegistered = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void unRegisterDownloadReceiver() {
        synchronized (DownloadComponentManager.class) {
            try {
                if (isReceiverRegistered && downloadReceiver != null && appContext != null) {
                    appContext.unregisterReceiver(downloadReceiver);
                    isReceiverRegistered = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void setDownloadInMultiProcess() {
        synchronized (DownloadComponentManager.class) {
            if (downloadInMultiProcess) {
                return;
            }
            downloadInMultiProcess = true;
            Intent intent = new Intent(getAppContext(), DownloadHandleService.class);
            intent.setAction("com.ss.android.downloader.action.MULTI_PROCESS_NOTIFY");
            getAppContext().startService(intent);
            if (!DownloadUtils.isDownloaderProcess()) {
                DownloadProxy.get(true).startService();
            }
        }
    }

    public static synchronized boolean isDownloadInMultiProcess() {
        boolean z;
        synchronized (DownloadComponentManager.class) {
            z = downloadInMultiProcess;
        }
        return z;
    }

    public static IDownloadHttpService getHttpService() {
        return httpService;
    }

    public static void addProcessCallback(ProcessCallback processCallback) {
        if (processCallback == null) {
            return;
        }
        synchronized (processCallbacks) {
            processCallbacks.add(processCallback);
        }
    }

    public static List<ProcessCallback> getProcessCallbacks() {
        List<ProcessCallback> list;
        synchronized (processCallbacks) {
            list = processCallbacks;
        }
        return list;
    }

    public static void addDownloadCompleteHandler(IDownloadCompleteHandler iDownloadCompleteHandler) {
        synchronized (downloadCompleteHandlers) {
            if (iDownloadCompleteHandler != null) {
                if (!downloadCompleteHandlers.contains(iDownloadCompleteHandler)) {
                    downloadCompleteHandlers.add(iDownloadCompleteHandler);
                }
            }
        }
    }

    public static void removeDownloadCompleteHandler(IDownloadCompleteHandler iDownloadCompleteHandler) {
        synchronized (downloadCompleteHandlers) {
            if (iDownloadCompleteHandler != null) {
                if (downloadCompleteHandlers.contains(iDownloadCompleteHandler)) {
                    downloadCompleteHandlers.remove(iDownloadCompleteHandler);
                }
            }
        }
    }

    public static void registerDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        synchronized (downloadCacheSyncStatusListeners) {
            if (iDownloadCacheSyncStatusListener != null) {
                if (!downloadCacheSyncStatusListeners.contains(iDownloadCacheSyncStatusListener)) {
                    downloadCacheSyncStatusListeners.add(iDownloadCacheSyncStatusListener);
                }
            }
        }
    }

    public static void unRegisterDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        synchronized (downloadCacheSyncStatusListeners) {
            if (iDownloadCacheSyncStatusListener != null) {
                if (downloadCacheSyncStatusListeners.contains(iDownloadCacheSyncStatusListener)) {
                    downloadCacheSyncStatusListeners.remove(iDownloadCacheSyncStatusListener);
                }
            }
        }
    }

    public static void registerDownloadTaskExecuteListener(IDownloadTaskExecuteListener iDownloadTaskExecuteListener) {
        synchronized (downloadTaskExecuteListeners) {
            if (iDownloadTaskExecuteListener != null) {
                if (!downloadTaskExecuteListeners.contains(iDownloadTaskExecuteListener)) {
                    downloadTaskExecuteListeners.add(iDownloadTaskExecuteListener);
                }
            }
        }
    }

    public static void unRegisterDownloadTaskExecuteListener(IDownloadTaskExecuteListener iDownloadTaskExecuteListener) {
        synchronized (downloadTaskExecuteListeners) {
            if (iDownloadTaskExecuteListener != null) {
                if (downloadTaskExecuteListeners.contains(iDownloadTaskExecuteListener)) {
                    downloadTaskExecuteListeners.remove(iDownloadTaskExecuteListener);
                }
            }
        }
    }

    public static void onDownloadCacheSyncCallback(DownloadCacheSyncStatus downloadCacheSyncStatus) {
        synchronized (downloadCacheSyncStatusListeners) {
            for (IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener : downloadCacheSyncStatusListeners) {
                if (iDownloadCacheSyncStatusListener != null) {
                    if (downloadCacheSyncStatus == DownloadCacheSyncStatus.SYNC_START) {
                        iDownloadCacheSyncStatusListener.onStart();
                    } else if (downloadCacheSyncStatus == DownloadCacheSyncStatus.SYNC_SUCCESS) {
                        iDownloadCacheSyncStatusListener.onSuccess();
                    }
                }
            }
            if (downloadCacheSyncStatus == DownloadCacheSyncStatus.SYNC_SUCCESS) {
                downloadCacheSyncStatusListeners.clear();
            }
        }
    }

    public static void onDownloadTaskStart(DownloadTask downloadTask, @BoundType int i) {
        synchronized (downloadTaskExecuteListeners) {
            for (IDownloadTaskExecuteListener iDownloadTaskExecuteListener : downloadTaskExecuteListeners) {
                if (iDownloadTaskExecuteListener != null) {
                    iDownloadTaskExecuteListener.onStart(downloadTask, i);
                }
            }
        }
    }

    public static void onDownloadTaskFinish(DownloadTask downloadTask, @BoundType int i) {
        synchronized (downloadTaskExecuteListeners) {
            for (IDownloadTaskExecuteListener iDownloadTaskExecuteListener : downloadTaskExecuteListeners) {
                if (iDownloadTaskExecuteListener != null) {
                    iDownloadTaskExecuteListener.onFinish(downloadTask, i);
                }
            }
        }
    }

    public static IDownloadHeadHttpService getHeadHttpService() {
        return headHttpService;
    }

    public static IDownloadMonitorListener getDownloadMonitorListener() {
        return downloadMonitorListener;
    }

    public static IDownloadHttpConnection downloadWithConnection(boolean z, int i, String str, List<HttpHeader> list) throws Exception {
        return downloadWithConnection(z, i, str, null, list, 0, false, null);
    }

    public static IDownloadHttpConnection downloadWithConnection(boolean z, int i, String str, String str2, List<HttpHeader> list, int i2, boolean z2, DownloadInfo downloadInfo) throws Exception {
        List<HttpHeader> list2;
        int i3;
        IDownloadHttpConnection downloadWithConnection2;
        if (!TextUtils.isEmpty(str2)) {
            List<HttpHeader> arrayList = list == null ? new ArrayList<>() : list;
            arrayList.add(new HttpHeader("ss_d_request_host_ip_114", str2));
            list2 = arrayList;
            i3 = 1;
        } else if (z) {
            list2 = list;
            i3 = i2;
        } else {
            i3 = 2;
            list2 = list;
        }
        int[] downloadNetLibs = getDownloadNetLibs(i3);
        Exception exc = null;
        for (int i4 : downloadNetLibs) {
            try {
                downloadWithConnection2 = downloadWithConnection2(i, str, str2, list2, i4, z2, downloadInfo);
            } catch (Exception e) {
                if (downloadInfo.isExpiredRedownload() && DownloadUtils.isResponseCode304Error(e) && DownloadUtils.hasDownloadCacheHeader(list2)) {
                    Logger.m6476d("dcach::http exception 304, throw excepiton, not retry " + e);
                    throw e;
                }
                exc = e;
            }
            if (downloadWithConnection2 != null) {
                return downloadWithConnection2;
            }
        }
        if (exc == null) {
            return null;
        }
        throw exc;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection downloadWithConnection2(int r11, java.lang.String r12, java.lang.String r13, java.util.List<com.p319ss.android.socialbase.downloader.model.HttpHeader> r14, int r15, boolean r16, com.p319ss.android.socialbase.downloader.model.DownloadInfo r17) throws com.p319ss.android.socialbase.downloader.exception.BaseException, java.io.IOException {
        /*
            r7 = r15
            r0 = 1
            if (r7 != r0) goto L9
            com.ss.android.socialbase.downloader.network.IDownloadHttpService r0 = getHttpService()
            goto Ld
        L9:
            com.ss.android.socialbase.downloader.network.IDownloadHttpService r0 = getDefaultHttpService()
        Ld:
            if (r0 == 0) goto L5c
            r1 = 0
            r8 = 0
            r2 = 0
            if (r16 == 0) goto L26
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L1e java.io.IOException -> L22
            r4 = r14
            r5 = r2
            r2 = r11
            r3 = r12
            goto L2a
        L1e:
            r0 = move-exception
            r5 = r2
            r3 = r12
            goto L49
        L22:
            r0 = move-exception
            r5 = r2
            r3 = r12
            goto L45
        L26:
            r4 = r14
            r5 = r2
            r2 = r11
            r3 = r12
        L2a:
            com.ss.android.socialbase.downloader.network.IDownloadHttpConnection r0 = r0.downloadWithConnection(r11, r12, r14)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            if (r16 == 0) goto L41
            long r1 = java.lang.System.currentTimeMillis()
            long r4 = r1 - r5
            java.lang.String r6 = "get"
            r1 = r0
            r2 = r12
            r3 = r13
            r7 = r15
            r9 = r17
            com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(r1, r2, r3, r4, r6, r7, r8, r9)
        L41:
            return r0
        L42:
            r0 = move-exception
            goto L49
        L44:
            r0 = move-exception
        L45:
            r2 = r0
            throw r2     // Catch: java.lang.Throwable -> L47
        L47:
            r0 = move-exception
            r8 = r2
        L49:
            if (r16 == 0) goto L5b
            long r9 = java.lang.System.currentTimeMillis()
            long r4 = r9 - r5
            java.lang.String r6 = "get"
            r2 = r12
            r3 = r13
            r7 = r15
            r9 = r17
            com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(r1, r2, r3, r4, r6, r7, r8, r9)
        L5b:
            throw r0
        L5c:
            com.ss.android.socialbase.downloader.exception.BaseException r0 = new com.ss.android.socialbase.downloader.exception.BaseException
            r1 = 1022(0x3fe, float:1.432E-42)
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "httpService not exist, netLib = "
            r3.append(r4)
            r3.append(r15)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.downloadWithConnection2(int, java.lang.String, java.lang.String, java.util.List, int, boolean, com.ss.android.socialbase.downloader.model.DownloadInfo):com.ss.android.socialbase.downloader.network.IDownloadHttpConnection");
    }

    public static IDownloadHeadHttpConnection downloadWithHeadConnection(String str, List<HttpHeader> list) throws Exception {
        return downloadWithHeadConnection(str, list, 0, false, null);
    }

    public static IDownloadHeadHttpConnection downloadWithHeadConnection(String str, List<HttpHeader> list, int i, boolean z, DownloadInfo downloadInfo) throws Exception {
        IDownloadHeadHttpConnection downloadWithHeadConnection2;
        Exception e = null;
        for (int i2 : getDownloadNetLibs(i)) {
            try {
                downloadWithHeadConnection2 = downloadWithHeadConnection2(str, list, i2, z, downloadInfo);
            } catch (Exception e2) {
                e = e2;
            }
            if (downloadWithHeadConnection2 != null) {
                return downloadWithHeadConnection2;
            }
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection downloadWithHeadConnection2(java.lang.String r11, java.util.List<com.p319ss.android.socialbase.downloader.model.HttpHeader> r12, int r13, boolean r14, com.p319ss.android.socialbase.downloader.model.DownloadInfo r15) throws com.p319ss.android.socialbase.downloader.exception.BaseException, java.io.IOException {
        /*
            r0 = 1
            if (r13 != r0) goto L8
            com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService r0 = getHeadHttpService()
            goto Lc
        L8:
            com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService r0 = getDefaultHeadHttpService()
        Lc:
            if (r0 == 0) goto L49
            r1 = 0
            r9 = 0
            r2 = 0
            if (r14 == 0) goto L18
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L32
        L18:
            com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection r12 = r0.downloadWithConnection(r11, r12)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L32
            if (r14 == 0) goto L2e
            r4 = 0
            long r0 = java.lang.System.currentTimeMillis()
            long r5 = r0 - r2
            java.lang.String r7 = "head"
            r2 = r12
            r3 = r11
            r8 = r13
            r10 = r15
            com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(r2, r3, r4, r5, r7, r8, r9, r10)
        L2e:
            return r12
        L2f:
            r12 = move-exception
            r8 = r9
            goto L37
        L32:
            r12 = move-exception
            throw r12     // Catch: java.lang.Throwable -> L34
        L34:
            r0 = move-exception
            r8 = r12
            r12 = r0
        L37:
            if (r14 == 0) goto L48
            r14 = 0
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r2
            java.lang.String r6 = "head"
            r2 = r11
            r3 = r14
            r7 = r13
            r9 = r15
            com.p319ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(r1, r2, r3, r4, r6, r7, r8, r9)
        L48:
            throw r12
        L49:
            com.ss.android.socialbase.downloader.exception.BaseException r11 = new com.ss.android.socialbase.downloader.exception.BaseException
            r12 = 1022(0x3fe, float:1.432E-42)
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "httpService not exist, netLib = "
            r15.append(r0)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            r14.<init>(r13)
            r11.<init>(r12, r14)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.downloadWithHeadConnection2(java.lang.String, java.util.List, int, boolean, com.ss.android.socialbase.downloader.model.DownloadInfo):com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection");
    }

    private static int[] getDownloadNetLibs(int i) {
        switch (i) {
            case 1:
                return new int[]{0};
            case 2:
                return new int[]{1};
            case 3:
                return new int[]{0, 1};
            default:
                return new int[]{1, 0};
        }
    }

    public static IDownloadHttpService getDefaultHttpService() {
        if (defaultHttpService == null) {
            synchronized (DownloadComponentManager.class) {
                if (defaultHttpService == null) {
                    defaultHttpService = new DefaultDownloadHttpService();
                }
            }
        }
        return defaultHttpService;
    }

    public static IDownloadHeadHttpService getDefaultHeadHttpService() {
        if (defaultHeadHttpService == null) {
            synchronized (DownloadComponentManager.class) {
                if (defaultHeadHttpService == null) {
                    defaultHeadHttpService = new DefaultDownloadHeadHttpService();
                }
            }
        }
        return defaultHeadHttpService;
    }

    public static synchronized void setDownloadLaunchHandler(IDownloadLaunchHandler iDownloadLaunchHandler) {
        synchronized (DownloadComponentManager.class) {
            if (iDownloadLaunchHandler != null) {
                downloadLaunchHandler = iDownloadLaunchHandler;
                if (downloadCache instanceof DefaultDownloadCache) {
                    ((DefaultDownloadCache) downloadCache).resumeUnCompleteTaskMayDelayed();
                }
            }
        }
    }

    private static void setCPUThreadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            cpuThreadExecutor = executorService;
        }
    }

    private static void setIOThreadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            ioThreadExecutor = executorService;
        }
    }

    private static void setMixDefaultDownloadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            mixDefaultDownloadExecutor = executorService;
        }
    }

    private static void setMixFrequentDownloadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            mixFrequentDownloadExecutor = executorService;
        }
    }

    private static void setMixApkDownloadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            mixApkDownloadExecutor = executorService;
        }
    }

    private static void setDBThreadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            dbThreadExecutor = executorService;
        }
    }

    public static void setChunkDownloadExecutor(ExecutorService executorService) {
        if (executorService != null) {
            chunkDownloadExecutor = executorService;
        }
    }

    public static void setOkHttpDispatcherExecutor(ExecutorService executorService) {
        if (executorService != null) {
            okHttpDispatcherExecutor = executorService;
        }
    }

    private static void setDownloadCompleteHandlers(List<IDownloadCompleteHandler> list) {
        if (downloadCompleteHandlers.isEmpty()) {
            synchronized (downloadCompleteHandlers) {
                downloadCompleteHandlers.addAll(list);
            }
        }
    }

    public static void setNotAutoRebootService(boolean z) {
        notAutoRebootService = z;
    }

    public static boolean notAutoRebootService() {
        return DownloadSetting.obtainGlobal().optInt("switch_not_auto_boot_service", notAutoRebootService ? 1 : 0) > 0;
    }

    public static synchronized IMonitorConfig getMonitorConfig() {
        IMonitorConfig iMonitorConfig;
        synchronized (DownloadComponentManager.class) {
            iMonitorConfig = monitorConfig;
        }
        return iMonitorConfig;
    }

    public static void submitCPUTask(Runnable runnable) {
        submitCPUTask(runnable, false);
    }

    public static void submitIOTask(Runnable runnable) {
        submitIOTask(runnable, false);
    }

    public static void submitCPUTask(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !DownloadUtils.isMainThread()) {
            runnable.run();
        } else {
            getCPUThreadExecutor().execute(runnable);
        }
    }

    public static void submitIOTask(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !DownloadUtils.isMainThread()) {
            runnable.run();
        } else {
            getIOThreadExecutor().execute(runnable);
        }
    }

    public static void submitDBTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (!DownloadUtils.isMainThread()) {
            runnable.run();
        } else {
            getDBThreadExecutorService().execute(runnable);
        }
    }

    public static ExecutorService getCPUThreadExecutor() {
        if (cpuThreadExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (cpuThreadExecutor == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(fixedCPUPoolSize, fixedCPUPoolSize, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("DownloadThreadPool-cpu-fixed", true));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    cpuThreadExecutor = threadPoolExecutor;
                }
            }
        }
        return cpuThreadExecutor;
    }

    public static ExecutorService getIOThreadExecutor() {
        return ioThreadExecutor != null ? ioThreadExecutor : getCPUThreadExecutor();
    }

    public static ExecutorService getMixFrequentThreadExecutor() {
        return mixFrequentDownloadExecutor != null ? mixFrequentDownloadExecutor : getMixDefaultThreadExecutor();
    }

    public static ExecutorService getMixApkThreadExecutor() {
        return mixApkDownloadExecutor != null ? mixApkDownloadExecutor : getMixDefaultThreadExecutor();
    }

    public static ExecutorService getMixDefaultThreadExecutor() {
        if (mixDefaultDownloadExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (mixDefaultDownloadExecutor == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(fixedMIXPoolSize, fixedMIXPoolSize, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("DownloadThreadPool-mix-fixed", true));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    mixDefaultDownloadExecutor = threadPoolExecutor;
                }
            }
        }
        return mixDefaultDownloadExecutor;
    }

    public static ExecutorService getChunkDownloadThreadExecutorService() {
        if (chunkDownloadExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (chunkDownloadExecutor == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(fixedIOPoolSize, fixedIOPoolSize, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("DownloadThreadPool-chunk-fixed", true));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    chunkDownloadExecutor = threadPoolExecutor;
                }
            }
        }
        return chunkDownloadExecutor;
    }

    public static ExecutorService getDBThreadExecutorService() {
        if (dbThreadExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (dbThreadExecutor == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(fixedDBPoolSize, fixedDBPoolSize, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("DownloadThreadPool-db-fixed", true));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    dbThreadExecutor = threadPoolExecutor;
                }
            }
        }
        return dbThreadExecutor;
    }

    public static OkHttpClient getDownloadClient() {
        if (sOkHttpClient == null) {
            synchronized (DownloadComponentManager.class) {
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder createDownloadClientBuilder = createDownloadClientBuilder();
                    sOkHttpClient = !(createDownloadClientBuilder instanceof OkHttpClient.Builder) ? createDownloadClientBuilder.build() : NBSOkHttp3Instrumentation.builderInit(createDownloadClientBuilder);
                }
            }
        }
        return sOkHttpClient;
    }

    public static OkHttpClient.Builder createDownloadClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30000L, TimeUnit.MILLISECONDS).readTimeout(30000L, TimeUnit.MILLISECONDS).writeTimeout(30000L, TimeUnit.MILLISECONDS).retryOnConnectionFailure(true).followRedirects(true).protocols(Collections.singletonList(Protocol.HTTP_1_1));
        if (okHttpDispatcherExecutor != null) {
            builder.dispatcher(new Dispatcher(okHttpDispatcherExecutor));
        }
        return builder;
    }

    public static IDownloadDns getDownloadDns() {
        return downloadDns;
    }

    public static IDownloadDns getDefaultDownloadDns() {
        if (defaultDownloadDns == null) {
            synchronized (DownloadComponentManager.class) {
                if (defaultDownloadDns == null) {
                    defaultDownloadDns = new IDownloadDns() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.2
                        @Override // com.p319ss.android.socialbase.downloader.network.IDownloadDns
                        public List<InetAddress> lookup(String str) throws UnknownHostException {
                            return Dns.SYSTEM.lookup(str);
                        }
                    };
                }
            }
        }
        return defaultDownloadDns;
    }

    public static synchronized IDownloadLaunchHandler getDownloadLaunchHandler() {
        IDownloadLaunchHandler iDownloadLaunchHandler;
        synchronized (DownloadComponentManager.class) {
            iDownloadLaunchHandler = downloadLaunchHandler;
        }
        return iDownloadLaunchHandler;
    }

    public static void setHttpService(IDownloadHttpService iDownloadHttpService) {
        if (iDownloadHttpService != null) {
            httpService = iDownloadHttpService;
        }
        httpServiceInit = httpService != null;
    }

    public static void setHeadHttpService(IDownloadHeadHttpService iDownloadHeadHttpService) {
        if (iDownloadHeadHttpService != null) {
            headHttpService = iDownloadHeadHttpService;
        }
    }

    public static IDownloadCache getDownloadCache() {
        if (downloadCache == null) {
            synchronized (DownloadComponentManager.class) {
                if (downloadCache == null) {
                    downloadCache = new DefaultDownloadCache();
                }
            }
        }
        return downloadCache;
    }

    private static void setDownloadCache(IDownloadCache iDownloadCache) {
        if (iDownloadCache != null) {
            downloadCache = iDownloadCache;
        }
    }

    public static IDownloadServiceHandler getDownloadServiceHandler() {
        if (downloadServiceHandler == null) {
            synchronized (DownloadComponentManager.class) {
                if (downloadServiceHandler == null) {
                    downloadServiceHandler = new DefaultDownloadServiceHandler();
                }
            }
        }
        return downloadServiceHandler;
    }

    public static IDownloadServiceHandler getIndependentDownloadServiceHandler() {
        if (independentDownloadServiceHandler == null) {
            synchronized (DownloadComponentManager.class) {
                if (independentDownloadServiceHandler == null) {
                    independentDownloadServiceHandler = independentHolderCreator.createServiceHandler();
                }
            }
        }
        return independentDownloadServiceHandler;
    }

    public static List<IDownloadCompleteHandler> getDownloadCompleteHandlers() {
        return downloadCompleteHandlers;
    }

    public static IDownloadIdGenerator getIdGenerator() {
        if (idGenerator == null) {
            synchronized (DownloadComponentManager.class) {
                if (idGenerator == null) {
                    idGenerator = new DefaultIdGenerator();
                }
            }
        }
        return idGenerator;
    }

    public static AbsDownloadEngine getDownloadEngine() {
        if (downloadEngine == null) {
            synchronized (DownloadComponentManager.class) {
                if (downloadEngine == null) {
                    downloadEngine = new DefaultDownloadEngine();
                }
            }
        }
        return downloadEngine;
    }

    private static void setIdGenerator(IDownloadIdGenerator iDownloadIdGenerator) {
        if (iDownloadIdGenerator != null) {
            idGenerator = iDownloadIdGenerator;
        }
    }

    private static int getMaxDownloadPoolSize() {
        int i = maxDownloadPoolSize;
        if (i <= 0 || i > fixedCPUPoolSize) {
            maxDownloadPoolSize = fixedCPUPoolSize;
        }
        return maxDownloadPoolSize;
    }

    private static void setMaxDownloadPoolSize(int i) {
        if (i > 0) {
            maxDownloadPoolSize = i;
        }
    }

    private static void setDownloadMonitorListener(IDownloadMonitorListener iDownloadMonitorListener) {
        if (iDownloadMonitorListener != null) {
            downloadMonitorListener = iDownloadMonitorListener;
        }
    }

    public static int getDownloadExpSwitchCode() {
        return downloadExpSwitchCode;
    }

    @NonNull
    public static JSONObject getDownloadSetting() {
        if (downloadSettings == null || downloadSettings.get() == null) {
            return DownloadConstants.EMPTY_JSON;
        }
        return downloadSettings.get();
    }

    public static void ensureOPPO() {
        if (TextUtils.isEmpty(DownloadConstants.LOWER_OPPO)) {
            DownloadConstants.LOWER_OPPO = "oppo";
            DownloadConstants.UPPER_OPPO = DownloadConstants.LOWER_OPPO.toUpperCase();
        }
    }

    public static void setDownloadSetting(IDownloadSettings iDownloadSettings) {
        downloadSettings = iDownloadSettings;
        DownloadSetting.init();
    }

    private static void needAutoRefreshUnSuccessTask(boolean z) {
        needAutoRefreshUnSuccessTask = z;
    }

    public static boolean needAutoRefreshUnSuccessTask() {
        return needAutoRefreshUnSuccessTask;
    }

    public static synchronized int getWriteBufferSize() {
        int i;
        synchronized (DownloadComponentManager.class) {
            i = writeBufferSize;
        }
        return i;
    }

    public static IChunkCntCalculator getChunkCntCalculator() {
        if (chunkCntCalculator == null) {
            synchronized (DownloadComponentManager.class) {
                if (chunkCntCalculator == null) {
                    chunkCntCalculator = new DefaultChunkCntCalculator();
                }
            }
        }
        return chunkCntCalculator;
    }

    private static void setChunkCntCalculator(IChunkCntCalculator iChunkCntCalculator) {
        if (iChunkCntCalculator != null) {
            chunkCntCalculator = iChunkCntCalculator;
        }
    }

    public static void setNotificationClickCallback(INotificationClickCallback iNotificationClickCallback) {
        if (iNotificationClickCallback != null) {
            notificationClickCallback = iNotificationClickCallback;
        }
    }

    public static INotificationClickCallback getNotificationClickCallback() {
        return notificationClickCallback;
    }

    public static IChunkAdjustCalculator getChunkAdjustCalculator() {
        if (chunkAdjustCalculator == null) {
            synchronized (DownloadComponentManager.class) {
                if (chunkAdjustCalculator == null) {
                    chunkAdjustCalculator = new DefaultChunkAdjustCalculator();
                }
            }
        }
        return chunkAdjustCalculator;
    }

    private static void setChunkAdjustCalculator(IChunkAdjustCalculator iChunkAdjustCalculator) {
        if (iChunkAdjustCalculator != null) {
            chunkAdjustCalculator = iChunkAdjustCalculator;
        }
    }

    public static IRetryDelayTimeCalculator getRetryDelayTimeCalculator() {
        if (retryDelayTimeCalculator == null) {
            synchronized (DownloadComponentManager.class) {
                if (retryDelayTimeCalculator == null) {
                    retryDelayTimeCalculator = new DefaultRetryDelayTimeCalculator();
                }
            }
        }
        return retryDelayTimeCalculator;
    }

    public static ITTNetHandler getTTNetHandler() {
        if (iTTNetHandler == null) {
            synchronized (DownloadComponentManager.class) {
                if (iTTNetHandler == null) {
                    iTTNetHandler = new ITTNetHandler.DefaultTTNetHandler();
                }
            }
        }
        return iTTNetHandler;
    }

    public static synchronized Context getAppContext() {
        Context context;
        synchronized (DownloadComponentManager.class) {
            context = appContext;
        }
        return context;
    }

    public static synchronized void setAppContext(Context context) {
        synchronized (DownloadComponentManager.class) {
            if (context != null) {
                if (appContext == null) {
                    appContext = context.getApplicationContext();
                    AppStatusManager.getInstance().init(appContext);
                }
            }
        }
    }

    public static int getDownloadId(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return 0;
        }
        return getDownloadId(downloadInfo.getUrl(), downloadInfo.getSavePath());
    }

    public static int getDownloadId(String str, String str2) {
        IDownloadIdGenerator idGenerator2 = getIdGenerator();
        if (idGenerator2 == null) {
            return 0;
        }
        return idGenerator2.generate(str, str2);
    }

    public static synchronized boolean isHttpServiceInit() {
        boolean z;
        synchronized (DownloadComponentManager.class) {
            z = httpServiceInit;
        }
        return z;
    }

    @NonNull
    public static InnerEventListener getEventListener() {
        if (downloadEventListener == null) {
            downloadEventListener = new InnerEventListener() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.3
                @Override // com.p319ss.android.socialbase.downloader.monitor.InnerEventListener
                public void onEvent(int i, String str, JSONObject jSONObject) {
                }

                @Override // com.p319ss.android.socialbase.downloader.monitor.InnerEventListener
                public void onUnityEvent(int i, String str, JSONObject jSONObject) {
                }
            };
        }
        return downloadEventListener;
    }

    public static IReserveWifiStatusListener getReserveWifiStatusListener() {
        return reserveWifiStatusListener;
    }

    public static void setDownloadEventListener(InnerEventListener innerEventListener) {
        downloadEventListener = innerEventListener;
    }

    public static boolean isInit() {
        return hasInit;
    }

    public static void setIndependentServiceCreator(IndependentHolderCreator independentHolderCreator2) {
        Logger.m6463v("wjd", "setIndependentServiceCreator::creator=" + independentHolderCreator2);
        independentHolderCreator = independentHolderCreator2;
    }

    public static boolean supportMultiProc() {
        StringBuilder sb = new StringBuilder();
        sb.append("supportMultiProc::=");
        sb.append(independentHolderCreator != null);
        Logger.m6463v("wjd", sb.toString());
        return independentHolderCreator != null;
    }

    public static IndependentHolderCreator getIndependentHolderCreator() {
        return independentHolderCreator;
    }
}
