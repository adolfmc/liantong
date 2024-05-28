package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ah */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11145ah implements InterfaceC11150ai {

    /* renamed from: a */
    private static boolean f21475a;

    /* renamed from: a */
    private Context f21477a;

    /* renamed from: a */
    private ServiceConnection f21478a;

    /* renamed from: a */
    private volatile int f21476a = 0;

    /* renamed from: a */
    private volatile String f21480a = null;

    /* renamed from: b */
    private volatile boolean f21482b = false;

    /* renamed from: b */
    private volatile String f21481b = null;

    /* renamed from: a */
    private final Object f21479a = new Object();

    public C11145ah(Context context) {
        this.f21477a = context;
        m4909a();
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public boolean mo4862a() {
        return f21475a;
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public String mo4863a() {
        m4902a("getOAID");
        return this.f21480a;
    }

    /* renamed from: a */
    private void m4909a() {
        boolean z;
        this.f21478a = new ServiceConnectionC11147a();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        try {
            z = this.f21477a.bindService(intent, this.f21478a, 1);
        } catch (Exception unused) {
            z = false;
        }
        this.f21476a = z ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4901b() {
        ServiceConnection serviceConnection = this.f21478a;
        if (serviceConnection != null) {
            try {
                this.f21477a.unbindService(serviceConnection);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m4902a(String str) {
        if (this.f21476a != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f21479a) {
            try {
                AbstractC11049b.m5282a("huawei's " + str + " wait...");
                this.f21479a.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.ah$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC11147a implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private ServiceConnectionC11147a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            new Thread(new Runnable() { // from class: com.xiaomi.push.ah.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        C11145ah.this.f21480a = C11149b.m4900a(iBinder);
                        C11145ah.this.f21482b = C11149b.m4899a(iBinder);
                        C11145ah.this.m4901b();
                        C11145ah.this.f21476a = 2;
                        synchronized (C11145ah.this.f21479a) {
                            try {
                                C11145ah.this.f21479a.notifyAll();
                            } catch (Exception unused) {
                            }
                        }
                    } catch (Exception unused2) {
                        C11145ah.this.m4901b();
                        C11145ah.this.f21476a = 2;
                        synchronized (C11145ah.this.f21479a) {
                            try {
                                C11145ah.this.f21479a.notifyAll();
                            } catch (Exception unused3) {
                            }
                        }
                    } catch (Throwable th) {
                        C11145ah.this.m4901b();
                        C11145ah.this.f21476a = 2;
                        synchronized (C11145ah.this.f21479a) {
                            try {
                                C11145ah.this.f21479a.notifyAll();
                            } catch (Exception unused4) {
                            }
                            throw th;
                        }
                    }
                }
            }).start();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ah$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C11149b {
        /* renamed from: a */
        static String m4900a(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: a */
        static boolean m4899a(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: a */
    public static boolean m4908a(Context context) {
        boolean z;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 128);
            z = (packageInfo.applicationInfo.flags & 1) != 0;
            f21475a = packageInfo.versionCode >= 20602000;
        } catch (Exception unused) {
        }
        return z;
    }
}
