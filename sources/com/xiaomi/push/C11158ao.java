package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.security.MessageDigest;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ao */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11158ao implements InterfaceC11150ai {

    /* renamed from: a */
    private static boolean f21522a;

    /* renamed from: a */
    private Context f21524a;

    /* renamed from: a */
    private ServiceConnection f21525a;

    /* renamed from: a */
    private volatile int f21523a = 0;

    /* renamed from: a */
    private volatile C11160a f21526a = null;

    /* renamed from: a */
    private final Object f21527a = new Object();

    public C11158ao(Context context) {
        this.f21524a = context;
        m4876a();
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public boolean mo4862a() {
        return f21522a;
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public String mo4863a() {
        m4867a("getOAID");
        if (this.f21526a == null) {
            return null;
        }
        return this.f21526a.f21530b;
    }

    /* renamed from: a */
    private void m4876a() {
        boolean z;
        this.f21525a = new ServiceConnectionC11161b();
        Intent intent = new Intent();
        intent.setClassName("com.heytap.openid", "com.heytap.openid.IdentifyService");
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        try {
            z = this.f21524a.bindService(intent, this.f21525a, 1);
        } catch (Exception unused) {
            z = false;
        }
        this.f21523a = z ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4865b() {
        ServiceConnection serviceConnection = this.f21525a;
        if (serviceConnection != null) {
            try {
                this.f21524a.unbindService(serviceConnection);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m4867a(String str) {
        if (this.f21523a != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f21527a) {
            try {
                AbstractC11049b.m5282a("oppo's " + str + " wait...");
                this.f21527a.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.ao$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC11161b implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private ServiceConnectionC11161b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            if (C11158ao.this.f21526a != null) {
                return;
            }
            new Thread(new Runnable() { // from class: com.xiaomi.push.ao.b.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String packageName = C11158ao.this.f21524a.getPackageName();
                        String m4866b = C11158ao.this.m4866b();
                        C11160a c11160a = new C11160a();
                        c11160a.f21530b = C11163c.m4864a(iBinder, packageName, m4866b, "OUID");
                        C11158ao.this.f21526a = c11160a;
                        C11158ao.this.m4865b();
                        C11158ao.this.f21523a = 2;
                        synchronized (C11158ao.this.f21527a) {
                            try {
                                C11158ao.this.f21527a.notifyAll();
                            } catch (Exception unused) {
                            }
                        }
                    } catch (Exception unused2) {
                        C11158ao.this.m4865b();
                        C11158ao.this.f21523a = 2;
                        synchronized (C11158ao.this.f21527a) {
                            try {
                                C11158ao.this.f21527a.notifyAll();
                            } catch (Exception unused3) {
                            }
                        }
                    } catch (Throwable th) {
                        C11158ao.this.m4865b();
                        C11158ao.this.f21523a = 2;
                        synchronized (C11158ao.this.f21527a) {
                            try {
                                C11158ao.this.f21527a.notifyAll();
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
    /* renamed from: com.xiaomi.push.ao$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11160a {

        /* renamed from: a */
        String f21529a;

        /* renamed from: b */
        String f21530b;

        /* renamed from: c */
        String f21531c;

        /* renamed from: d */
        String f21532d;

        private C11160a() {
            this.f21529a = null;
            this.f21530b = null;
            this.f21531c = null;
            this.f21532d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public String m4866b() {
        try {
            Signature[] signatureArr = this.f21524a.getPackageManager().getPackageInfo(this.f21524a.getPackageName(), 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest.digest(signatureArr[0].toByteArray())) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ao$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C11163c {
        /* renamed from: a */
        static String m4864a(IBinder iBinder, String str, String str2, String str3) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                obtain.writeString(str);
                obtain.writeString(str2);
                obtain.writeString(str3);
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: a */
    public static boolean m4875a(Context context) {
        long j;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 128);
            if (packageInfo != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    j = packageInfo.getLongVersionCode();
                } else {
                    j = packageInfo.versionCode;
                }
                boolean z = (packageInfo.applicationInfo.flags & 1) != 0;
                f21522a = j >= 1;
                if (z) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
