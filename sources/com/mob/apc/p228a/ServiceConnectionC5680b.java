package com.mob.apc.p228a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import com.mob.MobACService;
import com.mob.apc.APCException;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.apc.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ServiceConnectionC5680b implements ServiceConnection {

    /* renamed from: a */
    private static final ThreadPoolExecutor f14006a = new ThreadPoolExecutor(8, 8, 60, TimeUnit.SECONDS, new LinkedBlockingDeque());

    /* renamed from: b */
    private final ConcurrentHashMap<String, AbstractBinderC5684d> f14007b = new ConcurrentHashMap<>();

    /* renamed from: c */
    private final ConcurrentHashMap<String, byte[]> f14008c = new ConcurrentHashMap<>();

    static {
        try {
            f14006a.allowCoreThreadTimeOut(true);
        } catch (Throwable unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            String packageName = componentName.getPackageName();
            C5687f.m12837a().m12834b("[AIDLMessager][onServiceConnected] pkg: %s", packageName);
            this.f14007b.put(packageName, AbstractBinderC5684d.m12841a(iBinder));
            byte[] remove = this.f14008c.remove(packageName);
            if (remove != null) {
                synchronized (remove) {
                    remove.notifyAll();
                }
            }
        } catch (Throwable th) {
            C5687f.m12837a().m12834b("[AIDLMessager][onServiceConnected] exception: %s", th.getMessage());
            C5687f.m12837a().m12835a(th);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        try {
            String packageName = componentName.getPackageName();
            C5687f.m12837a().m12834b("[AIDLMessager][onServiceDisconnected] pkg: %s", packageName);
            this.f14007b.remove(packageName);
        } catch (Throwable th) {
            C5687f.m12837a().m12835a(th);
            C5687f.m12837a().m12834b("[AIDLMessager][onServiceDisconnected] exception: %s", th.getMessage());
        }
    }

    /* renamed from: a */
    public C5677a m12851a(String str, String str2, C5677a c5677a, long j) throws Throwable {
        C5686e c5686e;
        C5687f.m12837a().m12834b("[sendAIDLMessage] pkg: %s, businessID: %s, apcMessage: %s, timeout: %s", str, str2, c5677a, Long.valueOf(j));
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        try {
            Runnable m12852a = m12852a(str, new C5686e(c5677a, str2, j), j, linkedBlockingQueue);
            if (j <= 0) {
                c5686e = linkedBlockingQueue.take();
            } else {
                C5686e poll = linkedBlockingQueue.poll(j, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    f14006a.remove(m12852a);
                }
                c5686e = poll;
            }
            if (c5686e != null) {
                if (c5686e.f14027a != null) {
                    return c5686e.f14027a;
                }
                if (c5686e.f14030d != null) {
                    throw c5686e.f14030d;
                }
            }
            throw new APCException("[sendAIDLMessage] callback is null or timeout.");
        } catch (Throwable th) {
            C5687f.m12837a().m12834b("[sendAIDLMessage] exception: %s", th.getMessage());
            throw new APCException(th);
        }
    }

    /* renamed from: a */
    private Runnable m12852a(final String str, final C5686e c5686e, final long j, final BlockingQueue<C5686e> blockingQueue) {
        Runnable runnable = new Runnable() { // from class: com.mob.apc.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ServiceConnectionC5680b.this.m12854a(str, c5686e);
                    blockingQueue.offer(ServiceConnectionC5680b.this.m12853a(str, c5686e, j));
                } catch (Throwable th) {
                    C5687f.m12837a().m12835a(th);
                }
            }
        };
        f14006a.execute(runnable);
        return runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public C5686e m12853a(String str, C5686e c5686e, long j) throws Throwable {
        boolean z;
        boolean z2;
        C5687f.m12837a().m12834b("[realSendAIDLMessage] pkg: %s, InnerMessage: %s, timeout: %s", str, c5686e, Long.valueOf(j));
        AbstractBinderC5684d abstractBinderC5684d = this.f14007b.get(str);
        if (abstractBinderC5684d != null) {
            try {
                if (abstractBinderC5684d.isBinderAlive()) {
                    C5687f.m12837a().m12834b("[realSendAIDLMessage] serverBinder %s is alive.", str);
                    return abstractBinderC5684d.mo12840a(c5686e);
                }
            } catch (RemoteException e) {
                C5687f.m12837a().m12834b("[realSendAIDLMessage] serverBinder send error: %s %s", str, e.getMessage());
                C5687f.m12837a().m12835a(e);
            }
        }
        Intent intent = new Intent();
        intent.setClassName(str, MobACService.class.getName());
        try {
            C5687f.m12837a().m12836a("check alive, pkg: " + str, new Object[0]);
            C5688b.InterfaceC5689a m12842b = C5682c.m12850a().m12842b();
            if (m12842b != null) {
                z = m12842b.mo11906a(str);
            } else {
                C5687f.m12837a().m12836a("WARNING: mgsRequestListener null, can not check alive", new Object[0]);
                z = false;
            }
            C5687f.m12837a().m12836a("is tgt alv: " + z, new Object[0]);
            if (z) {
                if (Build.VERSION.SDK_INT >= 34) {
                    z2 = C5688b.m12833a().bindService(intent, this, 513);
                } else {
                    z2 = C5688b.m12833a().bindService(intent, this, 1);
                }
            } else {
                C5687f.m12837a().m12836a("can not rebnd acSvc, msg can not be send ", new Object[0]);
                z2 = false;
            }
            C5687f.m12837a().m12834b("[realSendAIDLMessage] rebind service: %s %s", str, Boolean.valueOf(z2));
            if (z2) {
                try {
                    byte[] bArr = this.f14008c.get(str);
                    if (bArr == null) {
                        bArr = new byte[0];
                        this.f14008c.put(str, bArr);
                    }
                    synchronized (bArr) {
                        bArr.wait(j);
                    }
                    AbstractBinderC5684d abstractBinderC5684d2 = this.f14007b.get(str);
                    C5687f.m12837a().m12834b("[realSendAIDLMessage] rebind service binder: %s %s", str, abstractBinderC5684d2);
                    if (abstractBinderC5684d2 == null) {
                        throw new APCException(1001, String.format("service binder %s is null or timeout", str));
                    }
                    try {
                        return abstractBinderC5684d2.mo12840a(c5686e);
                    } catch (RemoteException e2) {
                        throw new APCException(1004, String.format("service binder %s send message RemoteException: %s", str, e2.getMessage()));
                    }
                } catch (Throwable th) {
                    C5687f.m12837a().m12834b("[realSendAIDLMessage] service binder %s send exception: %s", str, th.getMessage());
                    throw new APCException(th);
                }
            }
            throw new APCException(1003, String.format("service %s bind failed", str));
        } catch (Throwable th2) {
            throw new APCException(1002, "service bind exception: " + th2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12854a(String str, C5686e c5686e) {
        C5677a c5677a;
        if (c5686e == null || (c5677a = c5686e.f14027a) == null) {
            return;
        }
        C5688b.InterfaceC5689a m12842b = C5682c.m12850a().m12842b();
        int i = c5677a.f13996a;
        C5687f m12837a = C5687f.m12837a();
        m12837a.m12836a("APCMessageType: " + i, new Object[0]);
        if (i == 1001) {
            C5687f m12837a2 = C5687f.m12837a();
            m12837a2.m12836a("Need GD. busType: 1", new Object[0]);
            if (m12842b != null) {
                m12842b.mo11921a(1, str);
                return;
            }
            return;
        }
        if (i != 1003) {
            if (i == 9004) {
                C5687f m12837a3 = C5687f.m12837a();
                m12837a3.m12836a("Need GD. busType: 2", new Object[0]);
                if (m12842b != null) {
                    m12842b.mo11921a(2, str);
                    return;
                }
                return;
            }
            switch (i) {
                case 1:
                case 2:
                    break;
                default:
                    return;
            }
        }
        C5687f.m12837a().m12836a("No need to call GD.", new Object[0]);
    }
}
