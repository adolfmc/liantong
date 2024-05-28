package com.p319ss.android.socialbase.downloader.impls;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;

/* renamed from: com.ss.android.socialbase.downloader.impls.DownloadHandleService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadHandleService extends Service {
    private static final String TAG = "DownloadHandleService";

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
        handleIntent(intent);
        return 2;
    }

    private void handleIntent(Intent intent) {
        final int intExtra;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action) || (intExtra = intent.getIntExtra("extra_download_id", 0)) == 0) {
            return;
        }
        if (action.equals("com.ss.android.downloader.action.DOWNLOAD_WAKEUP")) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.DownloadHandleService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DownloadProcessDispatcher.getInstance().retryDelayStart(intExtra);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (action.equals("com.ss.android.downloader.action.PROCESS_NOTIFY")) {
            DownloadProcessDispatcher.getInstance().recordTaskProcessIndependent(intExtra);
        } else if (action.equals("com.ss.android.downloader.action.MULTI_PROCESS_NOTIFY")) {
            DownloadComponentManager.setDownloadInMultiProcess();
        }
    }
}
