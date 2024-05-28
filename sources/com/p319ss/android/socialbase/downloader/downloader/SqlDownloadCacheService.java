package com.p319ss.android.socialbase.downloader.downloader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadCache;

/* renamed from: com.ss.android.socialbase.downloader.downloader.SqlDownloadCacheService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SqlDownloadCacheService extends Service {
    private static final String TAG = "SqlDownloadCacheService";

    public static void startServiceAndBind(Context context, ServiceConnection serviceConnection) {
        if (context != null) {
            try {
                Intent intent = new Intent(context, SqlDownloadCacheService.class);
                if (serviceConnection != null) {
                    context.bindService(intent, serviceConnection, 1);
                }
                context.startService(intent);
            } catch (Throwable th) {
                Log.w(TAG, "startServiceAndBind fail", th);
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(getApplicationContext());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ISqlDownloadCache iSqlDownloadCache;
        IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
        if (downloadCache instanceof DefaultDownloadCache) {
            iSqlDownloadCache = ((DefaultDownloadCache) downloadCache).getSqlDownloadCache();
        } else {
            iSqlDownloadCache = downloadCache instanceof ISqlDownloadCache ? (ISqlDownloadCache) downloadCache : null;
        }
        if (iSqlDownloadCache instanceof IBinder) {
            return (IBinder) iSqlDownloadCache;
        }
        return new Binder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        if (DownloadComponentManager.notAutoRebootService()) {
            return 2;
        }
        return onStartCommand;
    }
}
