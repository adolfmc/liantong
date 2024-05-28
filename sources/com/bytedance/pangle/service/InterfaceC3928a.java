package com.bytedance.pangle.service;

import android.content.Intent;
import android.os.IBinder;
import com.bytedance.pangle.plugin.Plugin;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.service.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3928a {
    void attach(Plugin plugin);

    IBinder onBind(Intent intent);

    void onCreate();

    void onDestroy();

    int onStartCommand(Intent intent, int i, int i2);

    boolean onUnbind(Intent intent);
}
