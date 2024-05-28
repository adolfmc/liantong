package com.networkbench.agent.impl.plugin;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.Harvester;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6396h;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6591i implements Runnable {

    /* renamed from: c */
    public static RunnableC6591i f16895c = new RunnableC6591i();

    /* renamed from: b */
    Harvester f16897b;

    /* renamed from: d */
    private ScheduledFuture<?> f16898d;

    /* renamed from: e */
    private final ScheduledExecutorService f16899e = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: a */
    int f16896a = 60000;

    /* renamed from: a */
    public void m9307a(int i) {
        this.f16896a = i;
    }

    /* renamed from: a */
    public void m9306a(Harvester harvester) {
        this.f16897b = harvester;
    }

    /* renamed from: a */
    public static RunnableC6591i m9308a() {
        return f16895c;
    }

    private RunnableC6591i() {
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            C6396h.m10137e("PluginTimer     sendPluginData......");
            this.f16897b.sendPluginData();
        }
    }

    /* renamed from: b */
    public boolean m9305b() {
        return this.f16898d != null;
    }

    /* renamed from: c */
    public void m9304c() {
        if (m9305b()) {
            return;
        }
        Harvest.getInstance().getHarvestData().getPluginData().m10741c();
        C6396h.m10137e("创建PluginTimer 任务循环线程池......");
        this.f16898d = this.f16899e.scheduleAtFixedRate(this, 0L, this.f16896a * 1000, TimeUnit.MILLISECONDS);
    }

    /* renamed from: d */
    public void m9303d() {
        ScheduledFuture<?> scheduledFuture = this.f16898d;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f16898d = null;
            C6369q.m10273a().m10271b();
        }
    }

    /* renamed from: e */
    public void m9302e() {
        try {
            this.f16899e.schedule(new Runnable() { // from class: com.networkbench.agent.impl.plugin.i.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        C6396h.m10137e("PluginTimer  tickNow  sendPluginData......");
                        RunnableC6591i.this.f16897b.sendPluginData();
                    }
                }
            }, 0L, TimeUnit.SECONDS);
        } catch (Throwable unused) {
        }
    }
}
