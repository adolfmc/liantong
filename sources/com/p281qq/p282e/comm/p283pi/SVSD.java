package com.p281qq.p282e.comm.p283pi;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.SVSD */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface SVSD {
    IBinder onBind(Intent intent);

    void onConfigurationChanged(Configuration configuration);

    void onCreate();

    void onDestroy();

    void onLowMemory();

    void onRebind(Intent intent);

    int onStartCommand(Intent intent, int i, int i2);

    void onTaskRemoved(Intent intent);

    void onTrimMemory(int i);

    boolean onUnbind(Intent intent);
}
