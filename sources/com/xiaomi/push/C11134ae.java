package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.ae */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11134ae {

    /* renamed from: a */
    private static volatile C11134ae f21454a;

    /* renamed from: a */
    private SharedPreferences f21455a;

    /* renamed from: a */
    private ScheduledThreadPoolExecutor f21458a = new ScheduledThreadPoolExecutor(1);

    /* renamed from: a */
    private Map<String, ScheduledFuture> f21457a = new HashMap();

    /* renamed from: a */
    private Object f21456a = new Object();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ae$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractRunnableC11137a implements Runnable {
        /* renamed from: a */
        public abstract String mo2289a();
    }

    /* renamed from: com.xiaomi.push.ae$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class RunnableC11138b implements Runnable {

        /* renamed from: a */
        AbstractRunnableC11137a f21463a;

        /* renamed from: a */
        void mo4923a() {
        }

        /* renamed from: b */
        void mo4922b() {
        }

        public RunnableC11138b(AbstractRunnableC11137a abstractRunnableC11137a) {
            this.f21463a = abstractRunnableC11137a;
        }

        @Override // java.lang.Runnable
        public void run() {
            mo4923a();
            this.f21463a.run();
            mo4922b();
        }
    }

    /* renamed from: a */
    public static C11134ae m4937a(Context context) {
        if (f21454a == null) {
            synchronized (C11134ae.class) {
                if (f21454a == null) {
                    f21454a = new C11134ae(context);
                }
            }
        }
        return f21454a;
    }

    private C11134ae(Context context) {
        this.f21455a = context.getSharedPreferences("mipush_extra", 0);
    }

    /* renamed from: a */
    public boolean m4934a(AbstractRunnableC11137a abstractRunnableC11137a, int i) {
        return m4933a(abstractRunnableC11137a, i, 0);
    }

    /* renamed from: a */
    public boolean m4933a(AbstractRunnableC11137a abstractRunnableC11137a, int i, int i2) {
        return m4932a(abstractRunnableC11137a, i, i2, false);
    }

    /* renamed from: a */
    public boolean m4932a(AbstractRunnableC11137a abstractRunnableC11137a, int i, int i2, final boolean z) {
        if (abstractRunnableC11137a == null || m4936a(abstractRunnableC11137a) != null) {
            return false;
        }
        final String m4926a = m4926a(abstractRunnableC11137a.mo2289a());
        RunnableC11138b runnableC11138b = new RunnableC11138b(abstractRunnableC11137a) { // from class: com.xiaomi.push.ae.1
            @Override // com.xiaomi.push.C11134ae.RunnableC11138b
            /* renamed from: a */
            void mo4923a() {
                super.mo4923a();
            }

            @Override // com.xiaomi.push.C11134ae.RunnableC11138b
            /* renamed from: b */
            void mo4922b() {
                if (z) {
                    return;
                }
                C11134ae.this.f21455a.edit().putLong(m4926a, System.currentTimeMillis()).commit();
            }
        };
        if (!z) {
            long abs = Math.abs(System.currentTimeMillis() - this.f21455a.getLong(m4926a, 0L)) / 1000;
            if (abs < i - i2) {
                i2 = (int) (i - abs);
            }
        }
        try {
            ScheduledFuture<?> scheduleAtFixedRate = this.f21458a.scheduleAtFixedRate(runnableC11138b, i2, i, TimeUnit.SECONDS);
            synchronized (this.f21456a) {
                this.f21457a.put(abstractRunnableC11137a.mo2289a(), scheduleAtFixedRate);
            }
            return true;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return true;
        }
    }

    /* renamed from: a */
    public void m4928a(Runnable runnable) {
        m4927a(runnable, 0);
    }

    /* renamed from: a */
    public void m4927a(Runnable runnable, int i) {
        this.f21458a.schedule(runnable, i, TimeUnit.SECONDS);
    }

    /* renamed from: a */
    public boolean m4935a(AbstractRunnableC11137a abstractRunnableC11137a) {
        return m4924b(abstractRunnableC11137a, 0);
    }

    /* renamed from: b */
    public boolean m4924b(AbstractRunnableC11137a abstractRunnableC11137a, int i) {
        if (abstractRunnableC11137a == null || m4936a(abstractRunnableC11137a) != null) {
            return false;
        }
        ScheduledFuture<?> schedule = this.f21458a.schedule(new RunnableC11138b(abstractRunnableC11137a) { // from class: com.xiaomi.push.ae.2
            @Override // com.xiaomi.push.C11134ae.RunnableC11138b
            /* renamed from: b */
            void mo4922b() {
                synchronized (C11134ae.this.f21456a) {
                    C11134ae.this.f21457a.remove(this.f21463a.mo2289a());
                }
            }
        }, i, TimeUnit.SECONDS);
        synchronized (this.f21456a) {
            this.f21457a.put(abstractRunnableC11137a.mo2289a(), schedule);
        }
        return true;
    }

    /* renamed from: a */
    private ScheduledFuture m4936a(AbstractRunnableC11137a abstractRunnableC11137a) {
        ScheduledFuture scheduledFuture;
        synchronized (this.f21456a) {
            scheduledFuture = this.f21457a.get(abstractRunnableC11137a.mo2289a());
        }
        return scheduledFuture;
    }

    /* renamed from: a */
    public boolean m4925a(String str) {
        synchronized (this.f21456a) {
            ScheduledFuture scheduledFuture = this.f21457a.get(str);
            if (scheduledFuture == null) {
                return false;
            }
            this.f21457a.remove(str);
            return scheduledFuture.cancel(false);
        }
    }

    /* renamed from: a */
    private static String m4926a(String str) {
        return "last_job_time" + str;
    }
}
