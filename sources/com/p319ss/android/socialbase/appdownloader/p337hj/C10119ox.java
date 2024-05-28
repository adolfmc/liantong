package com.p319ss.android.socialbase.appdownloader.p337hj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10093ko;
import com.p319ss.android.socialbase.appdownloader.p336h.C10110mb;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;

/* renamed from: com.ss.android.socialbase.appdownloader.hj.ox */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10119ox implements IDownloadLaunchHandler {

    /* renamed from: mb */
    private List<Integer> f19518mb;

    /* renamed from: ox */
    private BroadcastReceiver f19519ox;

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler
    public List<String> getResumeMimeTypes() {
        return C10085b.m6933b();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler
    public void onLaunchResume(final List<DownloadInfo> list, final int i) {
        if (DownloadUtils.isMainThread()) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.hj.ox.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        C10119ox.this.m6768mb(list, i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            m6768mb(list, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m6768mb(List<DownloadInfo> list, int i) {
        if (list == null || list.isEmpty()) {
            return;
        }
        InterfaceC10093ko m6819je = C10112hj.m6786x().m6819je();
        if (m6819je != null) {
            m6819je.mo6877mb(list);
        }
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext == null) {
            return;
        }
        boolean isWifi = DownloadUtils.isWifi(appContext);
        for (DownloadInfo downloadInfo : list) {
            m6775mb(appContext, downloadInfo, isWifi, i);
        }
        List<Integer> list2 = this.f19518mb;
        if (list2 == null || list2.isEmpty() || this.f19519ox != null) {
            return;
        }
        this.f19519ox = new BroadcastReceiver() { // from class: com.ss.android.socialbase.appdownloader.hj.ox.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                final Context applicationContext = context.getApplicationContext();
                if (DownloadUtils.isWifi(applicationContext)) {
                    Logger.m6475d("LaunchResume", "onReceive : wifi connected !!!");
                    DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.hj.ox.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (C10119ox.this.f19518mb != null && !C10119ox.this.f19518mb.isEmpty()) {
                                    Integer[] numArr = new Integer[C10119ox.this.f19518mb.size()];
                                    C10119ox.this.f19518mb.toArray(numArr);
                                    C10119ox.this.f19518mb.clear();
                                    for (Integer num : numArr) {
                                        DownloadInfo downloadInfo2 = Downloader.getInstance(applicationContext).getDownloadInfo(num.intValue());
                                        if (downloadInfo2 != null && (downloadInfo2.getRealStatus() == -5 || (downloadInfo2.getRealStatus() == -2 && downloadInfo2.isPauseReserveOnWifi()))) {
                                            C10119ox.this.m6775mb(applicationContext, downloadInfo2, true, 2);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    try {
                        applicationContext.unregisterReceiver(C10119ox.this.f19519ox);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    C10119ox.this.f19519ox = null;
                }
            }
        };
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            appContext.registerReceiver(this.f19519ox, intentFilter);
        } catch (Throwable th) {
            th.printStackTrace();
            this.f19519ox = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0132  */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m6775mb(android.content.Context r17, com.p319ss.android.socialbase.downloader.model.DownloadInfo r18, boolean r19, int r20) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p337hj.C10119ox.m6775mb(android.content.Context, com.ss.android.socialbase.downloader.model.DownloadInfo, boolean, int):void");
    }

    /* renamed from: mb */
    private void m6769mb(DownloadInfo downloadInfo, Context context) {
        DownloadSetting obtain = DownloadSetting.obtain(downloadInfo.getId());
        int optInt = obtain.optInt("paused_resume_max_count", 0);
        double optDouble = obtain.optDouble("paused_resume_max_hours", 72.0d);
        int pausedResumeCount = downloadInfo.getPausedResumeCount();
        if (pausedResumeCount < optInt && ((double) (System.currentTimeMillis() - downloadInfo.getLastDownloadTime())) < optDouble * 3600000.0d) {
            AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
            if (notificationItem == null) {
                notificationItem = new C10110mb(context, downloadInfo.getId(), downloadInfo.getTitle(), downloadInfo.getSavePath(), downloadInfo.getName(), downloadInfo.getExtra());
                DownloadNotificationManager.getInstance().addNotification(notificationItem);
            } else {
                notificationItem.updateNotificationItem(downloadInfo);
            }
            notificationItem.setTotalBytes(downloadInfo.getTotalBytes());
            notificationItem.setCurBytes(downloadInfo.getCurBytes());
            notificationItem.refreshStatus(downloadInfo.getStatus(), null, false, false);
            downloadInfo.setPausedResumeCount(pausedResumeCount + 1);
            downloadInfo.updateSpData();
        }
    }

    /* renamed from: mb */
    private boolean m6770mb(DownloadInfo downloadInfo) {
        if (DownloadSetting.obtain(downloadInfo.getId()).optBugFix("uninstall_can_not_resume_for_force_task", false)) {
            return DownloadUtils.isFileDownloaded(downloadInfo, false, downloadInfo.getMd5());
        }
        return downloadInfo.isDownloaded();
    }
}
