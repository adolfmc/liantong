package p001a.p058b.p062b.p063a.p064a.p074f;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p078i.C0764a;
import p001a.p058b.p062b.p063a.p064a.p081k.ThreadFactoryC0772d;

/* renamed from: a.b.b.a.a.f.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0756a {

    /* renamed from: a */
    public static final int[] f2319a = {Process.myPid()};

    /* renamed from: b */
    public static final InterfaceC3321a f2320b = C0749a.f2299a;

    /* renamed from: c */
    public static final ReentrantLock f2321c;

    /* renamed from: d */
    public static C0756a f2322d;

    /* renamed from: e */
    public static boolean f2323e;

    /* renamed from: f */
    public static Long f2324f;

    /* renamed from: g */
    public static Long f2325g;

    /* renamed from: h */
    public static RandomAccessFile f2326h;

    /* renamed from: i */
    public static RandomAccessFile f2327i;

    /* renamed from: j */
    public final EnumMap<C0764a.EnumC0765a, Collection<C0764a>> f2328j = new EnumMap<>(C0764a.EnumC0765a.class);

    /* renamed from: k */
    public final AtomicBoolean f2329k;

    /* renamed from: l */
    public ScheduledFuture f2330l;

    static {
        UAQ.getInstance();
        f2321c = new ReentrantLock();
        f2323e = false;
    }

    public C0756a(Context context) {
        Executors.newSingleThreadScheduledExecutor(new ThreadFactoryC0772d("Sampler"));
        this.f2329k = new AtomicBoolean(false);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        this.f2328j.put((EnumMap<C0764a.EnumC0765a, Collection<C0764a>>) C0764a.EnumC0765a.MEMORY, (C0764a.EnumC0765a) new ArrayList());
        this.f2328j.put((EnumMap<C0764a.EnumC0765a, Collection<C0764a>>) C0764a.EnumC0765a.CPU, (C0764a.EnumC0765a) new ArrayList());
    }

    /* renamed from: a */
    public final void m22262a() {
        f2324f = null;
        f2325g = null;
        RandomAccessFile randomAccessFile = f2327i;
        if (randomAccessFile == null || f2326h == null) {
            return;
        }
        try {
            randomAccessFile.close();
            f2326h.close();
            f2327i = null;
            f2326h = null;
        } catch (IOException e) {
            f2320b.mo17426a("Exception hit while resetting CPU sampler: ", e);
            C0735a.m22302a(e);
        }
    }

    /* renamed from: a */
    public final void m22261a(boolean z) {
        f2321c.lock();
        try {
            try {
                if (this.f2329k.get()) {
                    this.f2329k.set(false);
                    if (this.f2330l != null) {
                        this.f2330l.cancel(z);
                    }
                    m22262a();
                    f2320b.mo17428D("Sampler canceled");
                }
            } catch (Exception e) {
                f2320b.mo17426a("Caught error while Sampler stop: ", e);
            }
        } finally {
            f2321c.unlock();
        }
    }
}
