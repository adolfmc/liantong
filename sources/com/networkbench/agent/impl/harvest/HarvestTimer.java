package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p265k.C6489a;
import com.networkbench.agent.impl.p267m.C6496c;
import com.networkbench.agent.impl.p267m.C6500f;
import com.networkbench.agent.impl.util.C6638h;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestTimer implements Runnable {
    private static final long DEFAULT_HARVEST_PERIOD = 60000;
    private static final long HARVEST_PERIOD_LEEWAY = 1000;
    private static final long NEVER_TICKED = -1;

    /* renamed from: a */
    ThreadPoolExecutor f16274a;

    /* renamed from: b */
    protected final Harvester f16275b;

    /* renamed from: c */
    protected long f16276c;
    private long startTimeMs;
    private ScheduledFuture<?> tickFuture;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final InterfaceC6393e log = C6394f.m10150a();
    private long period = 60000;
    private C6496c userActions = new C6496c(C6638h.m8963w().m9076K());

    public long getStartTimeMs() {
        return this.startTimeMs;
    }

    public HarvestTimer(Harvester harvester) {
        this.f16275b = harvester;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            try {
                tickIfReady();
                if (C6459b.m9935e()) {
                    C6396h.m10130l("useraction  HarvestTimer gather  begin !!");
                    this.userActions.mo9739b();
                    C6500f.m9742a().mo9739b();
                }
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = this.log;
                interfaceC6393e.mo10116d("HarvestTimer: Exception in timer tick: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void tickIfReady() {
        long timeSinceLastTick = timeSinceLastTick();
        if (1000 + timeSinceLastTick >= this.period || timeSinceLastTick == -1) {
            long now = now();
            try {
                m9984a();
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = this.log;
                interfaceC6393e.mo10116d("HarvestTimer: Exception in timer tick: " + e.getMessage());
                e.printStackTrace();
            }
            this.f16276c = now;
        }
    }

    /* renamed from: a */
    protected void m9984a() {
        C6489a c6489a = new C6489a();
        c6489a.m9790a();
        try {
            this.f16275b.m9975f();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = this.log;
            interfaceC6393e.mo10116d("HarvestTimer: Exception in harvest execute: " + th.getMessage());
        }
        if (this.f16275b.isDisabled()) {
            stop();
        }
        c6489a.m9789b();
    }

    public void start() {
        if (isRunning()) {
            this.log.mo10115e("HarvestTimer: Attempting to start while already running");
        } else if (this.period <= 0) {
            this.log.mo10116d("HarvestTimer: Refusing to start with a period of 0 ms");
        } else {
            this.startTimeMs = System.currentTimeMillis();
            this.tickFuture = this.scheduler.scheduleAtFixedRate(this, 0L, this.period, TimeUnit.MILLISECONDS);
            this.f16275b.start();
        }
    }

    public void stop() {
        if (!isRunning()) {
            this.log.mo10115e("HarvestTimer: Attempting to stop when not running");
            return;
        }
        if (Harvest.isUser_action_enabled()) {
            this.userActions.mo9741a(Harvest.getInstance().getHarvestConnection());
        }
        this.startTimeMs = 0L;
        this.f16275b.stop();
        this.tickFuture.cancel(true);
        this.tickFuture = null;
    }

    public void tickNow() {
        try {
            this.scheduler.schedule(new Runnable() { // from class: com.networkbench.agent.impl.harvest.HarvestTimer.1
                @Override // java.lang.Runnable
                public void run() {
                    this.m9984a();
                }
            }, 0L, TimeUnit.SECONDS);
        } catch (Throwable unused) {
        }
    }

    public boolean isRunning() {
        return this.tickFuture != null;
    }

    public void setPeriod(long j) {
        this.period = j;
    }

    public long timeSinceLastTick() {
        if (this.f16276c == 0) {
            return -1L;
        }
        return now() - this.f16276c;
    }

    public long timeSinceStart() {
        if (this.startTimeMs == 0) {
            return 0L;
        }
        return now() - this.startTimeMs;
    }

    public long getPeriod() {
        return this.period;
    }

    private long now() {
        return System.currentTimeMillis();
    }
}
