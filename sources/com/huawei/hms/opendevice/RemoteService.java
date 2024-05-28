package com.huawei.hms.opendevice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.huawei.hms.support.log.HMSLog;

/* renamed from: com.huawei.hms.opendevice.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RemoteService {

    /* renamed from: a */
    private ServiceConnection f11552a;

    /* renamed from: b */
    private Messenger f11553b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RemoteService.java */
    /* renamed from: com.huawei.hms.opendevice.m$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC5043a implements ServiceConnection {

        /* renamed from: a */
        final /* synthetic */ Bundle f11554a;

        /* renamed from: b */
        final /* synthetic */ Context f11555b;

        ServiceConnectionC5043a(Bundle bundle, Context context) {
            this.f11554a = bundle;
            this.f11555b = context;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HMSLog.m14110i("RemoteService", "remote service onConnected");
            RemoteService.this.f11553b = new Messenger(iBinder);
            Message obtain = Message.obtain();
            obtain.setData(this.f11554a);
            try {
                RemoteService.this.f11553b.send(obtain);
            } catch (RemoteException unused) {
                HMSLog.m14110i("RemoteService", "remote service message send failed");
            }
            HMSLog.m14110i("RemoteService", "remote service unbindservice");
            this.f11555b.unbindService(RemoteService.this.f11552a);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            HMSLog.m14110i("RemoteService", "remote service onDisconnected");
            RemoteService.this.f11553b = null;
        }
    }

    /* renamed from: a */
    public boolean m14360a(Context context, Bundle bundle, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        this.f11552a = new ServiceConnectionC5043a(bundle, applicationContext);
        HMSLog.m14110i("RemoteService", "remote service bind service start");
        return applicationContext.bindService(intent, this.f11552a, 1);
    }
}
