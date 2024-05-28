package p001a.p058b.p062b.p063a.p064a.p074f;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import p001a.p058b.p062b.p063a.p064a.C0762h;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0728e;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0729f;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p081k.ThreadFactoryC0772d;

/* renamed from: a.b.b.a.a.f.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RunnableC0757b implements Runnable {

    /* renamed from: b */
    public static Context f2332b;

    /* renamed from: e */
    public static RunnableC0757b f2335e;

    /* renamed from: i */
    public ScheduledFuture f2339i;

    /* renamed from: j */
    public C0728e f2340j;

    /* renamed from: m */
    public float f2343m;

    /* renamed from: n */
    public float f2344n;

    /* renamed from: o */
    public float f2345o;

    /* renamed from: p */
    public float f2346p;

    /* renamed from: q */
    public float f2347q;

    /* renamed from: r */
    public float f2348r;

    /* renamed from: s */
    public float f2349s;

    /* renamed from: a */
    public static final InterfaceC3321a f2331a = C0749a.f2299a;

    /* renamed from: c */
    public static final Long f2333c = 10000L;

    /* renamed from: d */
    public static final ReentrantLock f2334d = new ReentrantLock();

    /* renamed from: f */
    public static double f2336f = 0.0d;

    /* renamed from: k */
    public double f2341k = 0.0d;

    /* renamed from: l */
    public double f2342l = 0.0d;

    /* renamed from: g */
    public final ScheduledExecutorService f2337g = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryC0772d("SamplerCommon"));

    /* renamed from: h */
    public final AtomicBoolean f2338h = new AtomicBoolean(false);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.b.b.a.a.f.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C0758a extends PhoneStateListener {
        /* renamed from: a */
        public static int m22253a() {
            return 0;
        }
    }

    /* renamed from: e */
    public static int m22254e() {
        try {
            return ((ActivityManager) f2332b.getSystemService("activity")).getProcessMemoryInfo(C0756a.f2319a)[0].getTotalPss();
        } catch (Exception e) {
            f2331a.error("Exception found when getTotalPss");
            C0735a.m22302a(e);
            return 0;
        }
    }

    /* renamed from: a */
    public final void m22260a() {
        f2334d.lock();
        try {
            if (!this.f2338h.get()) {
                this.f2339i = this.f2337g.scheduleWithFixedDelay(this, f2333c.longValue(), f2333c.longValue(), TimeUnit.MILLISECONDS);
                this.f2338h.set(true);
            }
        } finally {
            f2334d.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0127  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m22259a(int r19) {
        /*
            Method dump skipped, instructions count: 632
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p074f.RunnableC0757b.m22259a(int):void");
    }

    /* renamed from: a */
    public final void m22258a(boolean z) {
        f2334d.lock();
        try {
            try {
                if (this.f2338h.get()) {
                    this.f2338h.set(false);
                    if (this.f2339i != null) {
                        this.f2339i.cancel(z);
                    }
                    f2331a.mo17428D("SamplerCommon canceled");
                }
            } catch (Exception e) {
                f2331a.mo17426a("Caught error while Sampler stop: ", e);
            }
        } finally {
            f2334d.unlock();
        }
    }

    /* renamed from: b */
    public final void m22257b() {
        f2334d.lock();
        try {
            this.f2340j = new C0728e();
            this.f2340j.f2227c = System.currentTimeMillis();
            m22259a(0);
            m22255d();
            m22256c();
            C0728e c0728e = this.f2340j;
            if (C0729f.f2230c.getConfig().isThingsMonitor()) {
                C0762h.m22248a(c0728e);
            }
        } finally {
            f2334d.unlock();
        }
    }

    /* renamed from: c */
    public final void m22256c() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) f2332b.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = true;
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected())) {
            this.f2340j.m22311a("networkState", 0);
            this.f2340j.m22311a("networkDbmStrength", 2);
            return;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1) {
            this.f2340j.m22311a("networkState", 1);
            this.f2340j.m22311a("networkDbmStrength", Integer.valueOf(((WifiManager) f2332b.getSystemService("wifi")).getConnectionInfo().getRssi()));
            return;
        }
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 0) {
            z = false;
        }
        if (z) {
            this.f2340j.m22311a("networkState", 2);
            C0758a.m22253a();
            this.f2340j.m22311a("networkDbmStrength", 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.Object, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r2v10, types: [a.b.b.a.a.c.a.e] */
    /* renamed from: d */
    public final void m22255d() {
        FileReader fileReader;
        FileReader fileReader2;
        C0728e c0728e;
        String str;
        float f;
        FileReader fileReader3 = null;
        try {
            try {
                try {
                    fileReader2 = new FileReader("/proc/meminfo");
                } catch (FileNotFoundException e) {
                    e = e;
                } catch (IOException e2) {
                    e = e2;
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader2);
                    boolean z = false;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (readLine.contains("MemTotal:")) {
                            String[] split = readLine.split(" ");
                            this.f2343m = Float.valueOf(split[split.length - 2]).floatValue() * 1024.0f;
                            c0728e = this.f2340j;
                            str = "totalMemByte";
                            f = this.f2343m;
                        } else if (readLine.contains("MemAvailable:")) {
                            z = true;
                            String[] split2 = readLine.split(" ");
                            this.f2344n = Float.valueOf(split2[split2.length - 2]).floatValue() * 1024.0f;
                            c0728e = this.f2340j;
                            str = "freeMemByte";
                            f = this.f2344n;
                        } else if (readLine.contains("MemFree:")) {
                            String[] split3 = readLine.split(" ");
                            this.f2347q = Float.valueOf(split3[split3.length - 2]).floatValue() * 1024.0f;
                        } else if (readLine.contains("Buffers:")) {
                            String[] split4 = readLine.split(" ");
                            this.f2348r = Float.valueOf(split4[split4.length - 2]).floatValue() * 1024.0f;
                        } else if (readLine.startsWith("Cached:")) {
                            String[] split5 = readLine.split(" ");
                            this.f2349s = Float.valueOf(split5[split5.length - 2]).floatValue() * 1024.0f;
                        } else if (readLine.contains("Active:")) {
                            String[] split6 = readLine.split(" ");
                            this.f2345o = Float.valueOf(split6[split6.length - 2]).floatValue() * 1024.0f;
                            c0728e = this.f2340j;
                            str = "activeMemByte";
                            f = this.f2345o;
                        } else if (readLine.contains("Inactive:")) {
                            String[] split7 = readLine.split(" ");
                            this.f2346p = Float.valueOf(split7[split7.length - 2]).floatValue() * 1024.0f;
                            c0728e = this.f2340j;
                            str = "inActiveMemByte";
                            f = this.f2346p;
                        }
                        c0728e.m22311a(str, Float.valueOf(f));
                    }
                    if (!z) {
                        this.f2344n = this.f2347q + this.f2348r + this.f2349s;
                        this.f2340j.m22311a("freeMemByte", Float.valueOf(this.f2344n));
                    }
                    int m22254e = m22254e();
                    InterfaceC3321a interfaceC3321a = f2331a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Current app memory usage appMemoryUsage: ");
                    sb.append(m22254e);
                    sb.append("KB");
                    interfaceC3321a.mo17428D(sb.toString());
                    int i = m22254e * 1024;
                    this.f2342l = i / this.f2343m;
                    this.f2340j.m22311a("appMemUsagePercentage", Double.valueOf(this.f2342l));
                    ?? r2 = this.f2340j;
                    ?? valueOf = Integer.valueOf(i);
                    r2.m22311a("appUsedMemByte", valueOf);
                    fileReader2.close();
                    fileReader3 = valueOf;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileReader3 = fileReader2;
                    f2331a.error("/proc/meminfo file not found when get meminfo");
                    C0735a.m22302a(e);
                    fileReader = fileReader3;
                    if (fileReader3 == null) {
                        return;
                    }
                    fileReader.close();
                    fileReader3 = fileReader;
                } catch (IOException e4) {
                    e = e4;
                    fileReader3 = fileReader2;
                    f2331a.error("IOException found when get meminfo");
                    C0735a.m22302a(e);
                    if (fileReader3 != null) {
                        fileReader = fileReader3;
                        fileReader.close();
                        fileReader3 = fileReader;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileReader3 = fileReader2;
                    if (fileReader3 != null) {
                        try {
                            fileReader3.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f2338h.get()) {
                m22257b();
            }
        } catch (Exception e) {
            f2331a.mo17426a("Caught error while Sampler run: ", e);
            C0735a.m22302a(e);
        }
    }
}
