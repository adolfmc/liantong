package com.p319ss.android.socialbase.downloader.impls;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler;
import com.p319ss.android.socialbase.downloader.downloader.DownloadService;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultDownloadServiceHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultDownloadServiceHandler extends AbsDownloadServiceHandler {
    private static final String TAG = "DefaultDownloadServiceHandler";

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler
    public void startService(Context context, ServiceConnection serviceConnection) {
        try {
            context.startService(new Intent(context, DownloadService.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler
    public void stopService(Context context, ServiceConnection serviceConnection) {
        context.stopService(new Intent(context, DownloadService.class));
        this.isServiceAlive = false;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void onStartCommandOnMainThread() {
        if (DownloadExpSwitchCode.isSwitchEnable(262144)) {
            this.isServiceAlive = true;
            this.isInvokeStartService = false;
            if (Logger.debug()) {
                Logger.m6475d(TAG, "onStartCommandOnMainThread");
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void onStartCommand(Intent intent, int i, int i2) {
        if (Logger.debug()) {
            Logger.m6475d(TAG, "onStartCommand");
        }
        if (!DownloadExpSwitchCode.isSwitchEnable(262144)) {
            this.isServiceAlive = true;
        }
        resumePendingTask();
    }
}
