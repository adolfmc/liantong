package p001a.p058b.p062b.p063a.p064a.p067c.p070c;

import android.content.Context;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.C0762h;
import p001a.p058b.p062b.p063a.p064a.p066b.C0721b;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p075g.EnumC0761b;
import p001a.p058b.p062b.p063a.p064a.p081k.C0774f;
import p001a.p058b.p062b.p063a.p064a.p081k.ThreadFactoryC0772d;

/* renamed from: a.b.b.a.a.c.c.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RunnableC0740c implements Runnable {

    /* renamed from: a */
    public static final UAQ f2262a = UAQ.getInstance();

    /* renamed from: b */
    public static final InterfaceC3321a f2263b = C0749a.f2299a;

    /* renamed from: c */
    public final C0742e f2264c;

    /* renamed from: d */
    public ScheduledExecutorService f2265d = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryC0772d("HarvestTimer"));

    /* renamed from: e */
    public ScheduledFuture f2266e;

    /* renamed from: f */
    public long f2267f;

    /* renamed from: g */
    public long f2268g;

    /* renamed from: h */
    public Context f2269h;

    /* renamed from: i */
    public String f2270i;

    /* renamed from: j */
    public APMUploadConfigure f2271j;

    public RunnableC0740c(Context context, APMUploadConfigure aPMUploadConfigure) {
        this.f2269h = context;
        this.f2270i = aPMUploadConfigure.getUploadName();
        this.f2271j = aPMUploadConfigure;
        this.f2264c = new C0742e(context, aPMUploadConfigure);
    }

    /* renamed from: a */
    public final void m22290a() {
        long currentTimeMillis = this.f2267f == 0 ? -1L : System.currentTimeMillis() - this.f2267f;
        if (1000 + currentTimeMillis < this.f2268g && currentTimeMillis != -1) {
            InterfaceC3321a interfaceC3321a = f2263b;
            interfaceC3321a.mo17428D("HarvestTimer: Tick is too soon (" + currentTimeMillis + " delta) Last tick time: " + this.f2267f + " . Skipping.");
            return;
        }
        InterfaceC3321a interfaceC3321a2 = f2263b;
        StringBuilder m24437a = outline.m24437a("================= Tick Begin for ");
        m24437a.append(this.f2271j.getUploadName());
        m24437a.append(" =====================");
        interfaceC3321a2.mo17428D(m24437a.toString());
        InterfaceC3321a interfaceC3321a3 = f2263b;
        interfaceC3321a3.mo17428D("HarvestTimer: time since last tick: " + currentTimeMillis);
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            m22289b();
        } catch (Exception e) {
            f2263b.mo17426a("HarvestTimer: Exception in timer tick: ", e);
            C0735a.m22302a(e);
        }
        this.f2267f = currentTimeMillis2;
        InterfaceC3321a interfaceC3321a4 = f2263b;
        StringBuilder m24437a2 = outline.m24437a("================= Tick End  for ");
        m24437a2.append(this.f2271j.getUploadName());
        m24437a2.append(" =====================");
        interfaceC3321a4.mo17428D(m24437a2.toString());
    }

    /* renamed from: b */
    public final void m22289b() {
        long j;
        EnumC0761b enumC0761b = EnumC0761b.STARTED;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.f2264c.m22282a(this.f2271j);
        } catch (Exception e) {
            f2263b.mo17426a("HarvestTimer: Exception in harvest execute: ", e);
            C0735a.m22302a(e);
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        if (enumC0761b == EnumC0761b.STARTED) {
            EnumC0761b enumC0761b2 = EnumC0761b.STOPPED;
            j = currentTimeMillis2 - currentTimeMillis;
        } else {
            j = -1;
        }
        InterfaceC3321a interfaceC3321a = f2263b;
        interfaceC3321a.mo17428D("HarvestTimer tick took " + j + "ms");
    }

    /* renamed from: c */
    public final void m22288c() {
        long interval4g;
        if (this.f2271j == null) {
            this.f2268g = -1L;
            return;
        }
        if (C0774f.m22233d(this.f2269h)) {
            InterfaceC3321a interfaceC3321a = f2263b;
            StringBuilder m24437a = outline.m24437a("getPeriod for name:");
            m24437a.append(this.f2271j.getUploadName());
            interfaceC3321a.mo17428D(m24437a.toString());
            interval4g = this.f2271j.getIntervalWifi();
        } else {
            interval4g = C0774f.m22234c(this.f2269h) ? this.f2271j.getInterval4g() : f2262a.getConfig().getDataReportPeriod();
        }
        this.f2268g = interval4g;
    }

    /* renamed from: d */
    public void m22287d() {
        InterfaceC3321a interfaceC3321a = f2263b;
        StringBuilder m24437a = outline.m24437a("HarvestTimer: Start a harvestTimer, uploadName:");
        m24437a.append(this.f2271j.getUploadName());
        interfaceC3321a.mo17428D(m24437a.toString());
        if (this.f2266e != null) {
            f2263b.warning("HarvestTimer: Attempting to start while already running");
            return;
        }
        m22288c();
        if (this.f2268g <= 0) {
            f2263b.error("HarvestTimer: Refusing to start with a period <= 0 ms");
            return;
        }
        InterfaceC3321a interfaceC3321a2 = f2263b;
        StringBuilder m24437a2 = outline.m24437a("HarvestTimer: Starting with a period of ");
        m24437a2.append(this.f2268g);
        m24437a2.append("ms");
        interfaceC3321a2.mo17428D(m24437a2.toString());
        System.currentTimeMillis();
        this.f2266e = this.f2265d.scheduleAtFixedRate(this, 0L, this.f2268g, TimeUnit.MILLISECONDS);
        if (this.f2271j.getUploadName().equals("APMPerformanceConfigurationName")) {
            C0762h.m22249a();
        }
    }

    /* renamed from: e */
    public void m22286e() {
        if (this.f2266e != null) {
            InterfaceC3321a interfaceC3321a = f2263b;
            StringBuilder m24437a = outline.m24437a("HarvestTimer: Stop a harvestTimer when period is ");
            m24437a.append(this.f2268g);
            m24437a.append("ms");
            interfaceC3321a.mo17428D(m24437a.toString());
            this.f2266e.cancel(true);
            this.f2266e = null;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            try {
                m22290a();
                this.f2271j = C0721b.m22322a() != null ? C0721b.m22322a().get(this.f2270i) : null;
                long j = this.f2268g;
                m22288c();
                InterfaceC3321a interfaceC3321a = f2263b;
                StringBuilder sb = new StringBuilder();
                sb.append("period lasPeriod:");
                sb.append(j);
                sb.append(" now:");
                sb.append(this.f2268g);
                interfaceC3321a.mo17428D(sb.toString());
                if (this.f2268g <= 0) {
                    m22286e();
                } else if (j != this.f2268g) {
                    this.f2266e.cancel(true);
                    this.f2266e = this.f2265d.scheduleAtFixedRate(this, this.f2268g, this.f2268g, TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                f2263b.mo17426a("HarvestTimer: Exception in timer tick: ", e);
                C0735a.m22302a(e);
            }
        }
    }
}
