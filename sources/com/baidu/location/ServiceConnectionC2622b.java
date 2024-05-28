package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ServiceConnectionC2622b implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ LocationClient f5122a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC2622b(LocationClient locationClient) {
        this.f5122a = locationClient;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        boolean z;
        Bundle m19602d;
        this.f5122a.f5090g = new Messenger(iBinder);
        if (this.f5122a.f5090g == null) {
            return;
        }
        this.f5122a.f5088e = true;
        Log.d("baidu_location_client", "baidu location connected ...");
        z = this.f5122a.f5107x;
        if (z) {
            this.f5122a.f5091h.obtainMessage(2).sendToTarget();
            return;
        }
        try {
            Message obtain = Message.obtain((Handler) null, 11);
            obtain.replyTo = this.f5122a.f5092i;
            m19602d = this.f5122a.m19602d();
            obtain.setData(m19602d);
            this.f5122a.f5090g.send(obtain);
            this.f5122a.f5088e = true;
            if (this.f5122a.f5086c != null) {
                this.f5122a.f5077A.booleanValue();
                this.f5122a.f5091h.obtainMessage(4).sendToTarget();
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f5122a.f5090g = null;
        this.f5122a.f5088e = false;
    }
}
