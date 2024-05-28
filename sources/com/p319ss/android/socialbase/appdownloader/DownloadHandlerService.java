package com.p319ss.android.socialbase.appdownloader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.ss.android.socialbase.appdownloader.DownloadHandlerService */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class DownloadHandlerService extends Service {

    /* renamed from: mb */
    private static final String f19439mb = "DownloadHandlerService";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (Logger.debug()) {
            Logger.m6475d(f19439mb, "onStartCommand");
        }
        m6940mb(intent);
        stopSelf();
        return 2;
    }

    /* renamed from: mb */
    private boolean m6940mb(Intent intent) {
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return false;
        }
        int intExtra = intent.getIntExtra("extra_click_download_ids", 0);
        intent.getIntExtra("extra_click_download_type", 0);
        InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(this).getDownloadNotificationEventListener(intExtra);
        boolean z = true;
        if (intent.getBooleanExtra("extra_from_notification", false) && DownloadSetting.obtain(intExtra).optInt("notification_opt_2") == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(intExtra);
        }
        DownloadInfo downloadInfo = Downloader.getInstance(this).getDownloadInfo(intExtra);
        if (downloadInfo == null) {
            return false;
        }
        if (action.equals("android.ss.intent.action.DOWNLOAD_CLICK_CONTENT")) {
            m6939mb(downloadInfo, m6792ox, downloadNotificationEventListener);
        } else if (action.equals("android.ss.intent.action.DOWNLOAD_OPEN")) {
            m6941mb(this, downloadInfo, m6792ox, downloadNotificationEventListener);
        } else if (action.equals("android.ss.intent.action.DOWNLOAD_CLICK_BTN")) {
            if (downloadInfo.getStatus() == 0) {
                return false;
            }
            m6941mb(this, downloadInfo, m6792ox, downloadNotificationEventListener);
            if (downloadInfo.isDownloadOverStatus() && DownloadSetting.obtain(intExtra).optInt("no_hide_notification", 0) == 0) {
                if (DownloadSetting.obtain(intExtra).optInt("enable_notification_ui") < 2 || downloadInfo.getStatus() != -1) {
                    z = false;
                }
                if (!z) {
                    DownloadNotificationManager.getInstance().hideNotification(intExtra);
                    DownloadNotificationManager.getInstance().cancelNotification(intExtra);
                }
            }
        } else if (action.equals("android.ss.intent.action.DOWNLOAD_DELETE")) {
            m6938ox(downloadInfo, m6792ox, downloadNotificationEventListener);
        } else if (action.equals("android.ss.intent.action.DOWNLOAD_HIDE")) {
            DownloadNotificationManager.getInstance().hideNotification(intExtra);
        } else if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.MEDIA_MOUNTED")) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadHandlerService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add("application/vnd.android.package-archive");
                        arrayList.add("mime_type_plg");
                        Downloader.getInstance(DownloadComponentManager.getAppContext()).restartAllFailedDownloadTasks(arrayList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return true;
        }
        return false;
    }

    /* renamed from: mb */
    private static void m6942mb(Context context, DownloadInfo downloadInfo) {
        if (DownloadUtils.isWifi(context.getApplicationContext()) && downloadInfo.isPauseReserveOnWifi()) {
            downloadInfo.stopPauseReserveOnWifi();
        }
    }

    /* renamed from: mb */
    private static void m6943mb(Context context, final InterfaceC10090hj interfaceC10090hj, final DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        final IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (interfaceC10090hj == null && downloadNotificationEventListener == null) {
            return;
        }
        DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadHandlerService.2
            @Override // java.lang.Runnable
            public void run() {
                PackageInfo m6906mb;
                try {
                    File file = new File(DownloadInfo.this.getSavePath(), DownloadInfo.this.getName());
                    if (file.exists()) {
                        try {
                            String str = (DownloadComponentManager.getAppContext() == null || (m6906mb = C10085b.m6906mb(DownloadInfo.this, file)) == null) ? "" : m6906mb.packageName;
                            if (interfaceC10090hj != null) {
                                interfaceC10090hj.mo6883mb(DownloadInfo.this.getId(), 3, str, -3, DownloadInfo.this.getDownloadTime());
                            }
                            if (downloadNotificationEventListener != null) {
                                downloadNotificationEventListener.onNotificationEvent(3, DownloadInfo.this, str, "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m6939mb(@android.support.annotation.NonNull com.p319ss.android.socialbase.downloader.model.DownloadInfo r8, com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj r9, com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener r10) {
        /*
            r7 = this;
            int r1 = r8.getId()
            com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher r0 = com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher.getInstance()
            com.ss.android.socialbase.downloader.depend.INotificationClickCallback r0 = r0.getNotificationClickCallback(r1)
            if (r0 == 0) goto L17
            boolean r0 = r0.onClickWhenUnSuccess(r8)     // Catch: java.lang.Throwable -> L13
            goto L18
        L13:
            r0 = move-exception
            r0.printStackTrace()
        L17:
            r0 = 0
        L18:
            if (r0 != 0) goto L53
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity> r2 = com.p319ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.class
            r0.<init>(r7, r2)
            java.lang.String r2 = "extra_click_download_ids"
            r0.putExtra(r2, r1)
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r2)
            r7.startActivity(r0)
            com.ss.android.socialbase.downloader.notification.DownloadNotificationManager r0 = com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager.getInstance()
            r0.hideNotification(r1)
            r8.updateDownloadTime()
            if (r9 == 0) goto L49
            r2 = 7
            java.lang.String r3 = ""
            int r4 = r8.getStatus()
            long r5 = r8.getDownloadTime()
            r0 = r9
            r0.mo6883mb(r1, r2, r3, r4, r5)
        L49:
            if (r10 == 0) goto L53
            r9 = 7
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            r10.onNotificationEvent(r9, r8, r0, r1)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.DownloadHandlerService.m6939mb(com.ss.android.socialbase.downloader.model.DownloadInfo, com.ss.android.socialbase.appdownloader.b.hj, com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener):void");
    }

    /* renamed from: ox */
    private void m6938ox(@NonNull DownloadInfo downloadInfo, InterfaceC10090hj interfaceC10090hj, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        int id = downloadInfo.getId();
        Intent intent = new Intent(this, DownloadTaskDeleteActivity.class);
        intent.putExtra("extra_click_download_ids", id);
        intent.addFlags(268435456);
        startActivity(intent);
        DownloadNotificationManager.getInstance().hideNotification(id);
        downloadInfo.updateDownloadTime();
        if (interfaceC10090hj != null) {
            interfaceC10090hj.mo6883mb(id, 7, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
        }
        if (iDownloadNotificationEventListener != null) {
            iDownloadNotificationEventListener.onNotificationEvent(7, downloadInfo, "", "");
        }
    }

    /* renamed from: mb */
    private static void m6944mb(Context context, int i, boolean z) {
        boolean z2;
        INotificationClickCallback notificationClickCallback;
        if (z && (notificationClickCallback = DownloadProcessDispatcher.getInstance().getNotificationClickCallback(i)) != null) {
            try {
                DownloadInfo downloadInfo = Downloader.getInstance(context).getDownloadInfo(i);
                z2 = downloadInfo != null ? notificationClickCallback.onClickWhenSuccess(downloadInfo) : false;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (z2 && C10085b.m6916mb(context, i, true) == 0) {
                Toast.makeText(context, "Open Fail!", 0).show();
            }
            return;
        }
        z2 = false;
        if (z2) {
            return;
        }
        Toast.makeText(context, "Open Fail!", 0).show();
    }

    /* renamed from: mb */
    public static void m6941mb(Context context, DownloadInfo downloadInfo, InterfaceC10090hj interfaceC10090hj, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        AbsNotificationItem notificationItem;
        int id = downloadInfo.getId();
        INotificationClickCallback notificationClickCallback = DownloadProcessDispatcher.getInstance().getNotificationClickCallback(id);
        if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType()) && notificationClickCallback != null && C10085b.m6913mb(context, downloadInfo) && notificationClickCallback.onClickWhenInstalled(downloadInfo)) {
            return;
        }
        boolean z = false;
        switch (downloadInfo.getStatus()) {
            case -4:
            case -1:
                if (DownloadSetting.obtain(id).optInt("enable_notification_ui") >= 2 && downloadInfo.isOnlyWifi()) {
                    downloadInfo.setOnlyWifi(false);
                }
                Downloader.getInstance(context).restart(id);
                return;
            case -3:
                m6944mb(DownloadComponentManager.getAppContext(), id, true);
                m6943mb(context, interfaceC10090hj, downloadInfo);
                if (DownloadSetting.obtain(id).optInt("notification_click_install_auto_cancel", 1) != 0 || (notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(id)) == null) {
                    z = true;
                } else {
                    notificationItem.recordClickInstall();
                    notificationItem.refreshStatus(-3, null, false, true);
                }
                if (z) {
                    DownloadNotificationManager.getInstance().hideNotification(id);
                    return;
                }
                return;
            case -2:
                if (DownloadProcessDispatcher.getInstance().canResume(id)) {
                    Downloader.getInstance(context).resume(id);
                } else {
                    C10085b.m6903mb(downloadInfo, true, false);
                }
                if (interfaceC10090hj != null) {
                    interfaceC10090hj.mo6883mb(id, 6, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
                }
                if (iDownloadNotificationEventListener != null) {
                    iDownloadNotificationEventListener.onNotificationEvent(6, downloadInfo, "", "");
                    return;
                }
                return;
            case 0:
            default:
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                Downloader.getInstance(context).pause(id);
                m6942mb(context, downloadInfo);
                if (interfaceC10090hj != null) {
                    interfaceC10090hj.mo6883mb(id, 5, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
                }
                if (iDownloadNotificationEventListener != null) {
                    iDownloadNotificationEventListener.onNotificationEvent(5, downloadInfo, "", "");
                    return;
                }
                return;
        }
    }
}
