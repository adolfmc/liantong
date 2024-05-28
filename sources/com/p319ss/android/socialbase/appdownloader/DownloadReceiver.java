package com.p319ss.android.socialbase.appdownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;

/* renamed from: com.ss.android.socialbase.appdownloader.DownloadReceiver */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class DownloadReceiver extends BroadcastReceiver {

    /* renamed from: mb */
    private static final String f19444mb = "DownloadReceiver";

    /* renamed from: ox */
    private Handler f19445ox = new Handler(Looper.getMainLooper());

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        InterfaceC10088b m6814mb = C10112hj.m6786x().m6814mb();
        if (action.equals("android.intent.action.BOOT_COMPLETED") && (m6814mb == null || m6814mb.mo6874mb())) {
            if (Logger.debug()) {
                Logger.m6463v(f19444mb, "Received broadcast intent for android.intent.action.BOOT_COMPLETED");
            }
            m6937mb(context, action);
        } else if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
            if (Logger.debug()) {
                Logger.m6463v(f19444mb, "Received broadcast intent for android.intent.action.MEDIA_MOUNTED");
            }
            m6937mb(context, action);
        } else if (action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_REPLACED")) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    Uri data = intent.getData();
                    if (data == null) {
                        return;
                    }
                    String schemeSpecificPart = data.getSchemeSpecificPart();
                    InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
                    if (m6792ox != null) {
                        m6792ox.mo6880mb(context, schemeSpecificPart);
                    }
                    List<DownloadInfo> successedDownloadInfosWithMimeType = Downloader.getInstance(context).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive");
                    if (successedDownloadInfosWithMimeType != null) {
                        for (final DownloadInfo downloadInfo : successedDownloadInfosWithMimeType) {
                            if (downloadInfo != null && C10085b.m6905mb(downloadInfo, schemeSpecificPart)) {
                                IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
                                if (downloadNotificationEventListener != null && DownloadUtils.isProcessNameSame(downloadNotificationEventListener.getNotifyProcessName())) {
                                    downloadNotificationEventListener.onNotificationEvent(9, downloadInfo, schemeSpecificPart, "");
                                }
                                AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
                                if (notificationItem != null) {
                                    notificationItem.updateNotification(null, false);
                                }
                                if (DownloadSetting.obtain(downloadInfo.getId()).optInt("install_queue_enable", 0) == 1) {
                                    C10174ww.m6487mb().m6481mb(downloadInfo, schemeSpecificPart);
                                }
                                DownloadReceiver.this.f19445ox.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadReceiver.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadReceiver.1.1.1
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                try {
                                                    if (downloadInfo.isSavePathRedirected()) {
                                                        DownloadUtils.clearAntiHijackDir(downloadInfo);
                                                    }
                                                } catch (Throwable th) {
                                                    th.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                }, 1000L);
                                return;
                            }
                        }
                    }
                }
            });
        }
    }

    /* renamed from: mb */
    private void m6937mb(final Context context, final String str) {
        if (DownloadComponentManager.needAutoRefreshUnSuccessTask()) {
            this.f19445ox.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent(context, DownloadHandlerService.class);
                        intent.setAction(str);
                        context.startService(intent);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, 2000L);
        }
    }
}
