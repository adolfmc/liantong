package com.bytedance.applog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.bytedance.applog.v3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3713v3<SERVICE, RESULT> {

    /* renamed from: a */
    public final CountDownLatch f8865a = new CountDownLatch(1);

    /* renamed from: b */
    public final Intent f8866b;

    /* renamed from: c */
    public final InterfaceC3715b<SERVICE, RESULT> f8867c;

    /* renamed from: d */
    public final Context f8868d;

    /* renamed from: com.bytedance.applog.v3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC3714a implements ServiceConnection {

        /* renamed from: a */
        public final CountDownLatch f8869a;

        /* renamed from: b */
        public final InterfaceC3715b<SERVICE, RESULT> f8870b;
        @Nullable

        /* renamed from: c */
        public SERVICE f8871c;

        public ServiceConnectionC3714a(C3713v3 c3713v3, CountDownLatch countDownLatch, InterfaceC3715b<SERVICE, RESULT> interfaceC3715b) {
            this.f8869a = countDownLatch;
            this.f8870b = interfaceC3715b;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String str = C3630m3.f8586j;
            C3578e3.m17305a(str, "ServiceBlockBinder#onServiceConnected " + componentName, null);
            try {
                this.f8871c = this.f8870b.mo16998a(iBinder);
                this.f8869a.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            String str = C3630m3.f8586j;
            C3578e3.m17305a(str, "ServiceBlockBinder#onServiceDisconnected" + componentName, null);
            try {
                this.f8869a.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.v3$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3715b<T, RESULT> {
        /* renamed from: a */
        T mo16998a(IBinder iBinder);

        /* renamed from: a */
        RESULT mo16997a(T t);
    }

    public C3713v3(Context context, Intent intent, InterfaceC3715b<SERVICE, RESULT> interfaceC3715b) {
        this.f8868d = context;
        this.f8866b = intent;
        this.f8867c = interfaceC3715b;
    }

    /* renamed from: a */
    public RESULT m17071a() {
        Throwable th;
        C3713v3<SERVICE, RESULT>.ServiceConnectionC3714a serviceConnectionC3714a;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return null;
        }
        try {
            serviceConnectionC3714a = new ServiceConnectionC3714a(this, this.f8865a, this.f8867c);
            this.f8868d.bindService(this.f8866b, serviceConnectionC3714a, 1);
            this.f8865a.await();
            try {
                return this.f8867c.mo16997a((InterfaceC3715b<SERVICE, RESULT>) serviceConnectionC3714a.f8871c);
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    return null;
                } finally {
                    m17070a(serviceConnectionC3714a);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            serviceConnectionC3714a = null;
        }
    }

    /* renamed from: a */
    public final void m17070a(C3713v3<SERVICE, RESULT>.ServiceConnectionC3714a serviceConnectionC3714a) {
        if (serviceConnectionC3714a != null) {
            try {
                this.f8868d.unbindService(serviceConnectionC3714a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
