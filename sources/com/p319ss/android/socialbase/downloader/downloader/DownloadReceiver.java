package com.p319ss.android.socialbase.downloader.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationService;

/* renamed from: com.ss.android.socialbase.downloader.downloader.DownloadReceiver */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadReceiver extends BroadcastReceiver {
    private static final String TAG = "DownloadReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (Logger.debug()) {
                Logger.m6463v(TAG, "Received broadcast intent for android.net.conn.CONNECTIVITY_CHANGE");
            }
            autoRefreshUnsuccessDownloadTask(context, action);
        } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED") || action.equals("android.intent.action.MEDIA_REMOVED") || action.equals("android.intent.action.MEDIA_BAD_REMOVAL") || action.equals("android.intent.action.MEDIA_EJECT")) {
            forceStopAllDownloadTask(context, action);
        }
    }

    private void autoRefreshUnsuccessDownloadTask(final Context context, final String str) {
        if (DownloadComponentManager.needAutoRefreshUnSuccessTask()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent(context, DownloadNotificationService.class);
                        intent.setAction(str);
                        context.startService(intent);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, 2000L);
        }
    }

    private void forceStopAllDownloadTask(Context context, String str) {
        try {
            Intent intent = new Intent(context, DownloadNotificationService.class);
            intent.setAction(str);
            context.startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
