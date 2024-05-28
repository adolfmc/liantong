package com.networkbench.agent.impl.p241b.p242a;

import com.networkbench.agent.impl.crash.C6330i;
import com.networkbench.agent.impl.p241b.C6239i;
import com.networkbench.agent.impl.p241b.C6241k;
import com.networkbench.agent.impl.p241b.C6243m;
import com.networkbench.agent.impl.p241b.RunnableC6242l;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6228a implements Runnable {

    /* renamed from: b */
    private static RunnableC6228a f15408b;

    /* renamed from: a */
    public int f15409a;

    /* renamed from: c */
    private final ScheduledExecutorService f15410c = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: d */
    private ScheduledFuture<?> f15411d;

    /* renamed from: a */
    public static RunnableC6228a m10943a() {
        if (f15408b == null) {
            synchronized (RunnableC6228a.class) {
                if (f15408b == null) {
                    f15408b = new RunnableC6228a();
                }
            }
        }
        return f15408b;
    }

    private RunnableC6228a() {
        C6396h.m10138d(" NBSANRMonitor ....executor!");
    }

    /* renamed from: b */
    public synchronized void m10942b() {
        if (m10941c()) {
            return;
        }
        this.f15411d = this.f15410c.scheduleAtFixedRate(this, 0L, 5000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: c */
    public boolean m10941c() {
        return this.f15411d != null;
    }

    /* renamed from: d */
    public synchronized void m10940d() {
        if (this.f15411d != null) {
            this.f15411d.cancel(true);
            this.f15411d = null;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        C6243m c6243m = new C6243m();
        try {
            if (!c6243m.m10889a(C6638h.m8963w().m9076K()) || this.f15409a == c6243m.f15471c) {
                return;
            }
            this.f15409a = c6243m.f15471c;
            C6239i c6239i = new C6239i(currentTimeMillis);
            c6239i.m10915a(C6638h.m8963w().m9001h());
            c6239i.m10907g();
            c6239i.f15442a = C6330i.m10379a();
            c6239i.m10918a();
            c6239i.m10916a(c6243m.m10884c());
            c6239i.f15444c = c6243m.m10886b();
            c6239i.f15445d = c6243m.m10890a();
            c6239i.m10909e();
            if (c6239i.m10910d()) {
                C6241k.m10902a().m10895b(c6239i);
                C6648q.m8781a(c6239i);
            }
            RunnableC6242l.m10892a().clear();
        } catch (Throwable unused) {
        }
    }
}
