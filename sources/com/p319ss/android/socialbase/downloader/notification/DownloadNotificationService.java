package com.p319ss.android.socialbase.downloader.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadProxy;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.ThreadWithHandler;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;

/* renamed from: com.ss.android.socialbase.downloader.notification.DownloadNotificationService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadNotificationService extends Service {
    private static final long NOTIFY_TIME_WINDOW = 900;
    private static final String TAG = "DownloadNotificationService";
    private static boolean sAllowStartForeground = true;
    private static boolean sBugFixNonOngoing = false;
    private static boolean sBugfixNotifyTooFast = false;
    private static int sForegroundId = -1;
    private static int sIndependentProcessForegroundId = -1;
    private static volatile long sLastImportantNotifyTimestamp = 0;
    private static volatile long sLastNotifyTimestamp = 0;
    private static long sNotifyTimeWindow = 900;
    private ThreadWithHandler mNotifyThreadHandler;
    private final SparseArray<Notification> pendingImportantNotify = new SparseArray<>(2);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotifyHandlerThread();
        DownloadComponentManager.setAppContext(this);
        DownloadSetting obtainGlobal = DownloadSetting.obtainGlobal();
        int optInt = obtainGlobal.optInt("download_service_foreground", 0);
        if ((optInt == 1 || optInt == 3) && sForegroundId == -1) {
            sForegroundId = 0;
        }
        if ((optInt == 2 || optInt == 3) && sIndependentProcessForegroundId == -1) {
            sIndependentProcessForegroundId = 0;
        }
        sBugFixNonOngoing = obtainGlobal.optBugFix("non_going_notification_foreground", false);
        sBugfixNotifyTooFast = obtainGlobal.optBugFix("notify_too_fast", false);
        sNotifyTimeWindow = obtainGlobal.optLong("notification_time_window", NOTIFY_TIME_WINDOW);
        long j = sNotifyTimeWindow;
        if (j < 0 || j > 1200) {
            sNotifyTimeWindow = NOTIFY_TIME_WINDOW;
        }
    }

    private void createNotifyHandlerThread() {
        if (this.mNotifyThreadHandler == null) {
            this.mNotifyThreadHandler = new ThreadWithHandler("DownloaderNotifyThread");
            this.mNotifyThreadHandler.start();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ThreadWithHandler threadWithHandler = this.mNotifyThreadHandler;
        if (threadWithHandler != null) {
            try {
                threadWithHandler.quit();
            } catch (Throwable unused) {
            }
            this.mNotifyThreadHandler = null;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        handleIntent(intent);
        return 2;
    }

    private void handleIntent(final Intent intent) {
        ThreadWithHandler threadWithHandler;
        if (intent == null) {
            return;
        }
        final String action = intent.getAction();
        if (TextUtils.isEmpty(action) || (threadWithHandler = this.mNotifyThreadHandler) == null) {
            return;
        }
        threadWithHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.1
            @Override // java.lang.Runnable
            public void run() {
                ConnectivityManager connectivityManager;
                NetworkInfo activeNetworkInfo;
                final NotificationManager notificationManager = (NotificationManager) DownloadNotificationService.this.getSystemService("notification");
                final int intExtra = intent.getIntExtra("DOWNLOAD_NOTIFICATION_BUNDLE_EXTRA_ID", 0);
                if (action.equals("android.ss.intent.action.DOWNLOAD_NOTIFICATION_NOTIFY")) {
                    final Notification notification = (Notification) intent.getParcelableExtra("DOWNLOAD_NOTIFICATION_BUNDLE_EXTRA");
                    int intExtra2 = intent.getIntExtra("DOWNLOAD_NOTIFICATION_EXTRA_STATUS", 0);
                    if (intExtra == 0 || notification == null || notificationManager == null) {
                        return;
                    }
                    if (intExtra2 != 4) {
                        if (intExtra2 == -2 || intExtra2 == -3) {
                            if (DownloadNotificationService.sBugfixNotifyTooFast) {
                                DownloadNotificationService.this.doImportantNotify(notificationManager, intExtra, notification);
                            } else if (DownloadNotificationService.this.mNotifyThreadHandler != null) {
                                DownloadNotificationService.this.mNotifyThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                                    }
                                }, intExtra2 == -2 ? 50L : 200L);
                            }
                        } else if (DownloadNotificationService.sBugfixNotifyTooFast) {
                            DownloadNotificationService.this.doImportantNotify(notificationManager, intExtra, notification);
                        } else {
                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                        }
                    } else if (Downloader.getInstance(DownloadComponentManager.getAppContext()).isDownloading(intExtra)) {
                        DownloadInfo downloadInfo = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadInfo(intExtra);
                        if (DownloadNotificationService.sBugfixNotifyTooFast) {
                            if (downloadInfo == null || !downloadInfo.canNotifyProgress() || System.currentTimeMillis() - DownloadNotificationService.sLastImportantNotifyTimestamp <= DownloadNotificationService.sNotifyTimeWindow) {
                                return;
                            }
                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                            downloadInfo.setLastNotifyProgressTime();
                        } else if (downloadInfo == null || !downloadInfo.canNotifyProgress()) {
                        } else {
                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                            downloadInfo.setLastNotifyProgressTime();
                        }
                    }
                } else if (action.equals("android.ss.intent.action.DOWNLOAD_NOTIFICATION_CANCEL")) {
                    if (intExtra != 0) {
                        DownloadNotificationService.this.doCancel(notificationManager, intExtra);
                    }
                } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    try {
                        if (DownloadUtils.checkPermission(DownloadNotificationService.this, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) DownloadNotificationService.this.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                            ArrayList arrayList = new ArrayList();
                            if (!TextUtils.isEmpty(DownloadConstants.MIME_APK)) {
                                arrayList.add(DownloadConstants.MIME_APK);
                            }
                            arrayList.add("mime_type_plg");
                            Context applicationContext = DownloadNotificationService.this.getApplicationContext();
                            if (applicationContext != null) {
                                Downloader.getInstance(applicationContext).restartAllFailedDownloadTasks(arrayList);
                                Downloader.getInstance(applicationContext).restartAllPauseReserveOnWifiDownloadTasks(arrayList);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED") || action.equals("android.intent.action.MEDIA_REMOVED") || action.equals("android.intent.action.MEDIA_BAD_REMOVAL") || action.equals("android.intent.action.MEDIA_EJECT")) {
                    try {
                        Downloader.getInstance(DownloadNotificationService.this).pauseAll();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doImportantNotify(final NotificationManager notificationManager, final int i, Notification notification) {
        synchronized (this.pendingImportantNotify) {
            int indexOfKey = this.pendingImportantNotify.indexOfKey(i);
            if (indexOfKey >= 0 && indexOfKey < this.pendingImportantNotify.size()) {
                this.pendingImportantNotify.setValueAt(indexOfKey, notification);
                return;
            }
            long currentTimeMillis = sNotifyTimeWindow - (System.currentTimeMillis() - sLastNotifyTimestamp);
            if (currentTimeMillis <= 0) {
                currentTimeMillis = 0;
            }
            if (currentTimeMillis > 20000) {
                currentTimeMillis = 20000;
            }
            long currentTimeMillis2 = System.currentTimeMillis() + currentTimeMillis;
            sLastImportantNotifyTimestamp = currentTimeMillis2;
            sLastNotifyTimestamp = currentTimeMillis2;
            if (currentTimeMillis <= 0) {
                doNotify(notificationManager, i, notification);
            } else if (this.mNotifyThreadHandler != null) {
                synchronized (this.pendingImportantNotify) {
                    this.pendingImportantNotify.put(i, notification);
                }
                this.mNotifyThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadNotificationService.this.performImportantNotify(notificationManager, i);
                    }
                }, currentTimeMillis);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performImportantNotify(NotificationManager notificationManager, int i) {
        Notification notification;
        synchronized (this.pendingImportantNotify) {
            notification = this.pendingImportantNotify.get(i);
            this.pendingImportantNotify.remove(i);
        }
        if (notification != null) {
            doNotify(notificationManager, i, notification);
        }
    }

    private boolean needStartForeground(int i, Notification notification) {
        int i2;
        int i3;
        if (!sAllowStartForeground || (i2 = sForegroundId) == i || (i3 = sIndependentProcessForegroundId) == i) {
            return false;
        }
        if (i2 == 0 || i3 == 0) {
            if (sBugFixNonOngoing && (notification.flags & 2) == 0) {
                return false;
            }
            return Build.VERSION.SDK_INT < 26 || !TextUtils.isEmpty(notification.getChannelId());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNotify(NotificationManager notificationManager, int i, Notification notification) {
        if (needStartForeground(i, notification)) {
            try {
                boolean z = true;
                boolean z2 = DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatus(i) == 1 && !DownloadUtils.isDownloaderProcess();
                if ((z2 || sForegroundId != 0) && (!z2 || sIndependentProcessForegroundId != 0)) {
                    z = false;
                }
                if (z) {
                    IDownloadProxy downloadHandler = DownloadProcessDispatcher.getInstance().getDownloadHandler(i);
                    if (downloadHandler.isServiceAlive() && !downloadHandler.isServiceForeground()) {
                        Logger.m6469i(TAG, "doNotify, startForeground, ======== id = " + i + ", isIndependentProcess = " + z2);
                        if (z2) {
                            sIndependentProcessForegroundId = i;
                        } else {
                            sForegroundId = i;
                        }
                        downloadHandler.startForeground(i, notification);
                    } else {
                        Logger.m6469i(TAG, "doNotify: canStartForeground = true, but proxy can not startForeground, isIndependentProcess = " + z2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if ((sForegroundId == i || sIndependentProcessForegroundId == i) && sBugFixNonOngoing && (notification.flags & 2) == 0) {
            doCancel(notificationManager, i);
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (sLastNotifyTimestamp < currentTimeMillis) {
                sLastNotifyTimestamp = currentTimeMillis;
            }
            notificationManager.notify(i, notification);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCancel(NotificationManager notificationManager, int i) {
        boolean z;
        AbsNotificationItem absNotificationItem;
        int id;
        if (sForegroundId == i || sIndependentProcessForegroundId == i) {
            boolean z2 = true;
            if (sForegroundId == i) {
                sForegroundId = 0;
                z = false;
            } else {
                sIndependentProcessForegroundId = 0;
                z = true;
            }
            try {
                IDownloadProxy downloadHandler = DownloadProcessDispatcher.getInstance().getDownloadHandler(i);
                if (!downloadHandler.isServiceForeground()) {
                    sAllowStartForeground = false;
                    Logger.m6460w(TAG, "try to stopForeground when is not Foreground, id = " + i + ", isIndependentProcess = " + z);
                }
                Logger.m6469i(TAG, "doCancel, ========== stopForeground id = " + i + ", isIndependentProcess = " + z);
                downloadHandler.stopForeground(false, true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                notificationManager.cancel(i);
            } catch (Throwable unused) {
            }
            if (sAllowStartForeground) {
                try {
                    SparseArray<AbsNotificationItem> allNotificationItems = DownloadNotificationManager.getInstance().getAllNotificationItems();
                    if (allNotificationItems != null) {
                        for (int size = allNotificationItems.size() - 1; size >= 0; size--) {
                            absNotificationItem = allNotificationItems.valueAt(size);
                            if (absNotificationItem != null && (id = absNotificationItem.getId()) != i && id != sForegroundId && id != sIndependentProcessForegroundId && absNotificationItem.isOngoing()) {
                                if ((DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatus(absNotificationItem.getId()) == 1 && !DownloadUtils.isDownloaderProcess()) == z) {
                                    break;
                                }
                            }
                        }
                    }
                    absNotificationItem = null;
                    if (absNotificationItem != null) {
                        int id2 = absNotificationItem.getId();
                        notificationManager.cancel(id2);
                        if (Downloader.getInstance(this).getStatus(id2) != 1) {
                            z2 = false;
                        }
                        Logger.m6469i(TAG, "doCancel, updateNotification id = " + id2);
                        absNotificationItem.updateNotification(null, z2);
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return;
                }
            }
            return;
        }
        try {
            notificationManager.cancel(i);
        } catch (Throwable unused2) {
        }
    }
}
