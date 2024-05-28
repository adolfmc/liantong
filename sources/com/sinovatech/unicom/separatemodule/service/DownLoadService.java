package com.sinovatech.unicom.separatemodule.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.sinovatech.unicom.common.DownloaderVideo;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DownLoadService extends Service {
    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            new DownloaderVideo(intent.getStringExtra("advCode"), intent.getStringExtra("imageUrl")).start();
        } else {
            stopSelf();
        }
        return super.onStartCommand(intent, i, i2);
    }
}
