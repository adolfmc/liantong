package com.vivo.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import com.vivo.push.util.Utility;
import com.vivo.vms.IPCInvoke;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.vivo.push.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class IPCManager implements ServiceConnection {

    /* renamed from: a */
    private static final Object f21020a = new Object();

    /* renamed from: b */
    private static Map<String, IPCManager> f21021b = new HashMap();

    /* renamed from: c */
    private boolean f21022c;

    /* renamed from: d */
    private String f21023d;

    /* renamed from: e */
    private Context f21024e;

    /* renamed from: g */
    private volatile IPCInvoke f21026g;

    /* renamed from: i */
    private String f21028i;

    /* renamed from: j */
    private Handler f21029j;

    /* renamed from: h */
    private Object f21027h = new Object();

    /* renamed from: f */
    private AtomicInteger f21025f = new AtomicInteger(1);

    private IPCManager(Context context, String str) {
        this.f21023d = null;
        this.f21029j = null;
        this.f21024e = context;
        this.f21028i = str;
        this.f21029j = new Handler(Looper.getMainLooper(), new C10962j(this));
        this.f21023d = PushPackageUtils.m5472a(context);
        if (TextUtils.isEmpty(this.f21023d) || TextUtils.isEmpty(this.f21028i)) {
            Context context2 = this.f21024e;
            LogUtil.m5343c(context2, "init error : push pkgname is " + this.f21023d + " ; action is " + this.f21028i);
            this.f21022c = false;
            return;
        }
        this.f21022c = Utility.m5446a(context, this.f21023d) >= 1260;
        m5655b();
    }

    /* renamed from: a */
    public static IPCManager m5658a(Context context, String str) {
        IPCManager iPCManager = f21021b.get(str);
        if (iPCManager == null) {
            synchronized (f21020a) {
                iPCManager = f21021b.get(str);
                if (iPCManager == null) {
                    iPCManager = new IPCManager(context, str);
                    f21021b.put(str, iPCManager);
                }
            }
        }
        return iPCManager;
    }

    /* renamed from: a */
    public final boolean m5660a() {
        this.f21023d = PushPackageUtils.m5472a(this.f21024e);
        if (TextUtils.isEmpty(this.f21023d)) {
            LogUtil.m5343c(this.f21024e, "push pkgname is null");
            return false;
        }
        this.f21022c = Utility.m5446a(this.f21024e, this.f21023d) >= 1260;
        return this.f21022c;
    }

    /* renamed from: b */
    private void m5655b() {
        int i = this.f21025f.get();
        LogUtil.m5341d("AidlManager", "Enter connect, Connection Status: ".concat(String.valueOf(i)));
        if (i == 4 || i == 2 || i == 3 || i == 5 || !this.f21022c) {
            return;
        }
        m5659a(2);
        if (!m5653c()) {
            m5659a(1);
            LogUtil.m5354a("AidlManager", "bind core service fail");
            return;
        }
        m5651d();
    }

    /* renamed from: c */
    private boolean m5653c() {
        Intent intent = new Intent(this.f21028i);
        intent.setPackage(this.f21023d);
        try {
            return this.f21024e.bindService(intent, this, 1);
        } catch (Exception e) {
            LogUtil.m5353a("AidlManager", "bind core error", e);
            return false;
        }
    }

    /* renamed from: d */
    private void m5651d() {
        this.f21029j.removeMessages(1);
        this.f21029j.sendEmptyMessageDelayed(1, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m5659a(int i) {
        this.f21025f.set(i);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        m5650e();
        this.f21026g = IPCInvoke.Stub.asInterface(iBinder);
        if (this.f21026g == null) {
            LogUtil.m5341d("AidlManager", "onServiceConnected error : aidl must not be null.");
            m5649f();
            this.f21025f.set(1);
            return;
        }
        if (this.f21025f.get() == 2) {
            m5659a(4);
        } else if (this.f21025f.get() != 4) {
            m5649f();
        }
        synchronized (this.f21027h) {
            this.f21027h.notifyAll();
        }
    }

    /* renamed from: e */
    private void m5650e() {
        this.f21029j.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m5649f() {
        try {
            this.f21024e.unbindService(this);
        } catch (Exception e) {
            LogUtil.m5354a("AidlManager", "On unBindServiceException:" + e.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f21026g = null;
        m5659a(1);
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        LogUtil.m5346b("AidlManager", "onBindingDied : ".concat(String.valueOf(componentName)));
    }

    /* renamed from: a */
    public final boolean m5657a(Bundle bundle) {
        m5655b();
        if (this.f21025f.get() == 2) {
            synchronized (this.f21027h) {
                try {
                    this.f21027h.wait(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            int i = this.f21025f.get();
            if (i != 4) {
                LogUtil.m5341d("AidlManager", "invoke error : connect status = ".concat(String.valueOf(i)));
                return false;
            }
            this.f21029j.removeMessages(2);
            this.f21029j.sendEmptyMessageDelayed(2, 30000L);
            this.f21026g.asyncCall(bundle, null);
            return true;
        } catch (Exception e2) {
            LogUtil.m5353a("AidlManager", "invoke error ", e2);
            int i2 = this.f21025f.get();
            LogUtil.m5341d("AidlManager", "Enter disconnect, Connection Status: ".concat(String.valueOf(i2)));
            switch (i2) {
                case 1:
                default:
                    return false;
                case 2:
                    m5650e();
                    m5659a(1);
                    return false;
                case 3:
                    m5659a(1);
                    return false;
                case 4:
                    m5659a(1);
                    m5649f();
                    return false;
            }
        }
    }
}
