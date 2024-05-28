package com.huawei.hms.push;

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

/* compiled from: RemoteService.java */
/* renamed from: com.huawei.hms.push.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5051p {

    /* renamed from: a */
    private ServiceConnection f11669a;

    /* renamed from: b */
    private Messenger f11670b = null;

    /* compiled from: RemoteService.java */
    /* renamed from: com.huawei.hms.push.p$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class ServiceConnectionC5052a implements ServiceConnection {

        /* renamed from: a */
        final /* synthetic */ Bundle f11671a;

        /* renamed from: b */
        final /* synthetic */ Context f11672b;

        ServiceConnectionC5052a(Bundle bundle, Context context) {
            this.f11671a = bundle;
            this.f11672b = context;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HMSLog.m14110i("RemoteService", "remote service onConnected");
            C5051p.this.f11670b = new Messenger(iBinder);
            Message obtain = Message.obtain();
            obtain.setData(this.f11671a);
            try {
                C5051p.this.f11670b.send(obtain);
            } catch (RemoteException unused) {
                HMSLog.m14110i("RemoteService", "remote service message send failed");
            }
            HMSLog.m14110i("RemoteService", "remote service unbindservice");
            this.f11672b.unbindService(C5051p.this.f11669a);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            HMSLog.m14110i("RemoteService", "remote service onDisconnected");
            C5051p.this.f11670b = null;
        }
    }

    /* renamed from: a */
    public boolean m14200a(Context context, Bundle bundle, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        this.f11669a = new ServiceConnectionC5052a(bundle, applicationContext);
        HMSLog.m14110i("RemoteService", "remote service bind service start");
        return applicationContext.bindService(intent, this.f11669a, 1);
    }
}
