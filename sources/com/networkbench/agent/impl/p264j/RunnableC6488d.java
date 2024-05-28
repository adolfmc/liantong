package com.networkbench.agent.impl.p264j;

import android.app.ActivityManager;
import android.content.Context;
import android.location.LocationListener;
import android.os.Debug;
import android.os.Process;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.tracing.TraceLifecycleAware;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.j.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6488d implements TraceLifecycleAware, Runnable {

    /* renamed from: a */
    private static final long f16398a = 100;

    /* renamed from: c */
    private static final float f16400c = 1024.0f;

    /* renamed from: h */
    private static RunnableC6488d f16403h;

    /* renamed from: f */
    private String f16407f;

    /* renamed from: g */
    private LocationListener f16408g;

    /* renamed from: j */
    private final ActivityManager f16409j;

    /* renamed from: k */
    private final EnumMap<EnumC6486b, Collection<C6485a>> f16410k = new EnumMap<>(EnumC6486b.class);

    /* renamed from: l */
    private final AtomicBoolean f16411l = new AtomicBoolean(false);

    /* renamed from: m */
    private final ScheduledExecutorService f16412m = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: n */
    private ScheduledFuture<?> f16413n;

    /* renamed from: o */
    private Long f16414o;

    /* renamed from: p */
    private Long f16415p;

    /* renamed from: q */
    private RandomAccessFile f16416q;

    /* renamed from: r */
    private RandomAccessFile f16417r;

    /* renamed from: s */
    private Context f16418s;

    /* renamed from: b */
    private static final int[] f16399b = {Process.myPid()};

    /* renamed from: d */
    private static final InterfaceC6393e f16401d = C6394f.m10150a();

    /* renamed from: e */
    private static final ReentrantLock f16402e = new ReentrantLock();

    /* renamed from: i */
    private static boolean f16404i = false;

    /* renamed from: t */
    private static long f16405t = 0;

    /* renamed from: u */
    private static long f16406u = 0;

    @Override // com.networkbench.agent.impl.tracing.TraceLifecycleAware
    public void onExitMethod() {
    }

    /* renamed from: a */
    public static long m9808a() {
        if (System.currentTimeMillis() - f16406u > 1000) {
            return 0L;
        }
        return f16405t;
    }

    private RunnableC6488d(Context context) {
        this.f16409j = (ActivityManager) context.getSystemService("activity");
        this.f16410k.put((EnumMap<EnumC6486b, Collection<C6485a>>) EnumC6486b.MEMORY, (EnumC6486b) new ArrayList());
        this.f16410k.put((EnumMap<EnumC6486b, Collection<C6485a>>) EnumC6486b.CPU, (EnumC6486b) new ArrayList());
        this.f16418s = context;
    }

    /* renamed from: a */
    public static void m9807a(Context context) {
        f16402e.lock();
        if (f16403h == null) {
            f16403h = new RunnableC6488d(context);
        }
        f16402e.unlock();
    }

    /* renamed from: b */
    public String m9802b() {
        String str = this.f16407f;
        if (str != null) {
            return str;
        }
        this.f16407f = this.f16418s.getPackageName();
        return this.f16407f;
    }

    /* renamed from: c */
    public static void m9801c() {
        f16402e.lock();
        RunnableC6488d runnableC6488d = f16403h;
        if (runnableC6488d == null) {
            return;
        }
        runnableC6488d.m9794j();
        f16402e.unlock();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f16411l.get()) {
                m9793k();
            }
        } catch (Exception e) {
            f16401d.mo10121a("Caught exception while running the sampler", e);
        }
    }

    /* renamed from: j */
    private void m9794j() {
        if (this.f16411l.get()) {
            return;
        }
        m9792l();
        this.f16411l.set(true);
        this.f16413n = this.f16412m.scheduleAtFixedRate(this, 0L, 100L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    private void m9803a(boolean z) {
        if (this.f16411l.get()) {
            f16402e.lock();
            this.f16411l.set(false);
            this.f16413n.cancel(z);
            m9791m();
            f16402e.unlock();
        }
    }

    /* renamed from: d */
    public static boolean m9800d() {
        RunnableC6488d runnableC6488d = f16403h;
        if (runnableC6488d == null) {
            return false;
        }
        return !runnableC6488d.f16413n.isDone();
    }

    /* renamed from: e */
    public static void m9799e() {
        RunnableC6488d runnableC6488d = f16403h;
        if (runnableC6488d == null) {
            return;
        }
        runnableC6488d.m9803a(false);
    }

    /* renamed from: f */
    public static void m9798f() {
        RunnableC6488d runnableC6488d = f16403h;
        if (runnableC6488d == null) {
            return;
        }
        runnableC6488d.m9803a(true);
    }

    /* renamed from: k */
    private void m9793k() {
        C6485a m9797g = m9797g();
        f16402e.lock();
        if (m9797g != null) {
            m9805a(EnumC6486b.MEMORY).add(m9797g);
        }
        C6485a m9796h = m9796h();
        if (m9796h != null) {
            m9805a(EnumC6486b.CPU).add(m9796h);
        }
        f16402e.unlock();
    }

    /* renamed from: l */
    private void m9792l() {
        for (Collection<C6485a> collection : this.f16410k.values()) {
            collection.clear();
        }
    }

    /* renamed from: g */
    public static C6485a m9797g() {
        Debug.MemoryInfo[] processMemoryInfo;
        int totalPss;
        RunnableC6488d runnableC6488d = f16403h;
        if (runnableC6488d != null && (processMemoryInfo = runnableC6488d.f16409j.getProcessMemoryInfo(f16399b)) != null && processMemoryInfo.length > 0 && (totalPss = processMemoryInfo[0].getTotalPss()) >= 0) {
            C6485a c6485a = new C6485a(EnumC6486b.MEMORY);
            c6485a.m9818b(new BigDecimal(totalPss / 1024.0f).divide(new BigDecimal(1), 0, 4).longValue());
            return c6485a;
        }
        return null;
    }

    /* renamed from: h */
    public C6485a m9796h() {
        long m9804a;
        long m9804a2;
        if (f16404i) {
            return null;
        }
        try {
            if (this.f16416q != null && this.f16417r != null) {
                this.f16416q.seek(0L);
                this.f16417r.seek(0L);
                m9804a = m9804a(this.f16416q.readLine().trim(), 2, 3, 4, 5, 6, 7, 8);
                m9804a2 = m9804a(this.f16417r.readLine().trim(), 13, 14);
                if (this.f16414o != null && this.f16415p == null) {
                    this.f16414o = Long.valueOf(m9804a);
                    this.f16415p = Long.valueOf(m9804a2);
                    return null;
                }
                C6485a c6485a = new C6485a(EnumC6486b.CPU);
                long longValue = new BigDecimal(((m9804a2 - this.f16415p.longValue()) * 100.0d) / (m9804a - this.f16414o.longValue())).divide(new BigDecimal(1), 0, 4).longValue();
                c6485a.m9818b(longValue);
                f16405t = longValue;
                f16406u = System.currentTimeMillis();
                this.f16414o = Long.valueOf(m9804a);
                this.f16415p = Long.valueOf(m9804a2);
                return c6485a;
            }
            this.f16416q = new RandomAccessFile("/proc/stat", "r");
            this.f16417r = new RandomAccessFile("/proc/" + f16399b[0] + "/stat", "r");
            m9804a = m9804a(this.f16416q.readLine().trim(), 2, 3, 4, 5, 6, 7, 8);
            m9804a2 = m9804a(this.f16417r.readLine().trim(), 13, 14);
            if (this.f16414o != null) {
            }
            C6485a c6485a2 = new C6485a(EnumC6486b.CPU);
            long longValue2 = new BigDecimal(((m9804a2 - this.f16415p.longValue()) * 100.0d) / (m9804a - this.f16414o.longValue())).divide(new BigDecimal(1), 0, 4).longValue();
            c6485a2.m9818b(longValue2);
            f16405t = longValue2;
            f16406u = System.currentTimeMillis();
            this.f16414o = Long.valueOf(m9804a);
            this.f16415p = Long.valueOf(m9804a2);
            return c6485a2;
        } catch (Exception unused) {
            f16404i = true;
            return null;
        }
    }

    /* renamed from: a */
    private long m9804a(String str, int... iArr) {
        int lastIndexOf = str.lastIndexOf(" ");
        int i = 0;
        long j = 0;
        int i2 = 0;
        int i3 = 1;
        while (i <= lastIndexOf && i2 != iArr.length && i != lastIndexOf) {
            int indexOf = str.indexOf(" ", i) + 1;
            int indexOf2 = str.indexOf(" ", indexOf);
            if (i3 != iArr[i2]) {
                i3++;
                i = indexOf2;
            } else {
                j += Long.valueOf(str.substring(indexOf, indexOf2)).longValue();
                i2++;
                i3++;
                i = indexOf2;
            }
        }
        return j;
    }

    /* renamed from: m */
    private void m9791m() {
        this.f16414o = null;
        this.f16415p = null;
        RandomAccessFile randomAccessFile = this.f16417r;
        if (randomAccessFile == null || this.f16416q == null) {
            return;
        }
        try {
            randomAccessFile.close();
            this.f16416q.close();
            this.f16417r = null;
            this.f16416q = null;
        } catch (IOException unused) {
        }
    }

    /* renamed from: a */
    private Collection<C6485a> m9805a(EnumC6486b enumC6486b) {
        return this.f16410k.get(enumC6486b);
    }

    /* renamed from: i */
    public Map<EnumC6486b, Collection<C6485a>> m9795i() {
        EnumMap enumMap = new EnumMap((EnumMap) f16403h.f16410k);
        for (EnumC6486b enumC6486b : f16403h.f16410k.keySet()) {
            enumMap.put((EnumMap) enumC6486b, (EnumC6486b) new ArrayList(f16403h.f16410k.get(enumC6486b)));
        }
        return Collections.unmodifiableMap(enumMap);
    }

    /* renamed from: a */
    public Collection<C6485a> m9806a(HarvestableType harvestableType) {
        return Collections.unmodifiableCollection(this.f16410k.get(harvestableType));
    }

    @Override // com.networkbench.agent.impl.tracing.TraceLifecycleAware
    public void onEnterMethod() {
        if (this.f16411l.get()) {
            return;
        }
        m9801c();
    }
}
