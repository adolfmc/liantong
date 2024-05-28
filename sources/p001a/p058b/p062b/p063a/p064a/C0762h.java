package p001a.p058b.p062b.p063a.p064a;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0738a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p081k.ThreadFactoryC0772d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0762h {

    /* renamed from: a */
    public static final InterfaceC3321a f2356a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2357b = UAQ.getInstance();

    /* renamed from: c */
    public static final ScheduledExecutorService f2358c = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryC0772d("TaskQueue"));

    /* renamed from: d */
    public static final ConcurrentLinkedQueue<Object> f2359d = new ConcurrentLinkedQueue<>();

    /* renamed from: e */
    public static final Runnable f2360e = new RunnableC0759g();

    /* renamed from: f */
    public static Future f2361f;

    /* renamed from: a */
    public static void m22249a() {
        if (f2361f == null) {
            f2361f = f2358c.scheduleAtFixedRate(f2360e, 0L, 1000L, TimeUnit.MILLISECONDS);
            f2356a.mo17428D("TaskQueue start");
        }
    }

    /* renamed from: a */
    public static void m22248a(Object obj) {
        if (C0738a.f2254b.f2258f < 0 || f2357b.isDisableCollect() || f2361f == null) {
            return;
        }
        f2359d.add(obj);
    }

    /* renamed from: b */
    public static void m22247b() {
        Future future = f2361f;
        if (future != null) {
            future.cancel(true);
            f2361f = null;
            f2356a.mo17428D("TaskQueue stop");
        }
    }
}
