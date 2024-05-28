package com.baidu.location;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface LLSInterface {
    double getVersion();

    IBinder onBind(Intent intent);

    void onCreate(Context context);

    void onDestroy();

    int onStartCommand(Intent intent, int i, int i2);

    void onTaskRemoved(Intent intent);

    boolean onUnBind(Intent intent);
}
