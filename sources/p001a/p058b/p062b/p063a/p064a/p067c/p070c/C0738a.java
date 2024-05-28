package p001a.p058b.p062b.p063a.p064a.p067c.p070c;

import android.app.Application;
import android.content.Context;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.ArrayList;
import java.util.Iterator;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.C0719b;
import p001a.p058b.p062b.p063a.p064a.p066b.C0721b;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0738a {

    /* renamed from: a */
    public static final InterfaceC3321a f2253a = C0749a.f2299a;

    /* renamed from: b */
    public static C0738a f2254b = new C0738a();

    /* renamed from: c */
    public Context f2255c;

    /* renamed from: d */
    public RunnableC0740c f2256d;

    /* renamed from: e */
    public final ArrayList<RunnableC0740c> f2257e = new ArrayList<>();

    /* renamed from: f */
    public int f2258f = 0;

    /* renamed from: a */
    public synchronized void m22298a(Context context) {
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        this.f2255c = context;
        this.f2258f++;
        if (this.f2258f == 1) {
            if (this.f2256d == null) {
                APMUploadConfigure aPMUploadConfigure = new APMUploadConfigure("APMPerformanceConfigurationName", "", null);
                C0721b.m22321a(aPMUploadConfigure);
                this.f2256d = new RunnableC0740c(this.f2255c, aPMUploadConfigure);
            }
            this.f2256d.m22287d();
        }
        InterfaceC3321a interfaceC3321a = f2253a;
        StringBuilder m24437a = outline.m24437a("MultiHarvest start one time, instanceNumber now is ");
        m24437a.append(this.f2258f);
        interfaceC3321a.mo17428D(m24437a.toString());
    }

    /* renamed from: a */
    public void m22297a(APMUploadConfigure aPMUploadConfigure) {
        if (!C0721b.m22322a().containsKey(aPMUploadConfigure.getUploadName())) {
            C0721b.m22321a(aPMUploadConfigure);
            InterfaceC3321a interfaceC3321a = f2253a;
            StringBuilder m24437a = outline.m24437a("addUploadCofigure:");
            m24437a.append(aPMUploadConfigure.getUploadName());
            interfaceC3321a.mo17428D(m24437a.toString());
            f2254b.m22296b(aPMUploadConfigure);
            return;
        }
        InterfaceC3321a interfaceC3321a2 = f2253a;
        StringBuilder m24437a2 = outline.m24437a("addUploadCofigure already exists:");
        m24437a2.append(aPMUploadConfigure.getUploadName());
        m24437a2.append(" size:");
        m24437a2.append(C0721b.m22322a().size());
        interfaceC3321a2.mo17428D(m24437a2.toString());
        C0721b.m22321a(aPMUploadConfigure);
    }

    /* renamed from: b */
    public final void m22296b(APMUploadConfigure aPMUploadConfigure) {
        RunnableC0740c runnableC0740c = new RunnableC0740c(this.f2255c, aPMUploadConfigure);
        runnableC0740c.m22287d();
        synchronized (this.f2257e) {
            this.f2257e.add(runnableC0740c);
        }
    }

    /* renamed from: a */
    public synchronized void m22299a() {
        this.f2258f--;
        if (this.f2258f == 0) {
            synchronized (this.f2257e) {
                Iterator<RunnableC0740c> it = this.f2257e.iterator();
                while (it.hasNext()) {
                    RunnableC0740c next = it.next();
                    next.m22286e();
                    next.f2264c.m22285a();
                    next.f2264c.m22278b();
                }
                this.f2257e.clear();
            }
            C0719b.m22326a().shutdown();
            C0719b.f2186c = C0719b.f2184a;
            C0721b.m22322a().clear();
            C0721b.m22316b().clear();
            RunnableC0740c runnableC0740c = this.f2256d;
            if (runnableC0740c != null) {
                runnableC0740c.m22286e();
                this.f2256d.f2264c.m22285a();
                this.f2256d.f2264c.m22278b();
                this.f2256d = null;
            }
        }
        InterfaceC3321a interfaceC3321a = f2253a;
        StringBuilder m24437a = outline.m24437a("MultiHarvest stop one time, instanceNumber now is ");
        m24437a.append(this.f2258f);
        interfaceC3321a.mo17428D(m24437a.toString());
    }
}
