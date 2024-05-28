package com.networkbench.agent.impl.p241b;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6242l implements Runnable {

    /* renamed from: b */
    private static ConcurrentLinkedQueue<StackTraceElement[]> f15463b = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    public int f15464a;

    /* renamed from: c */
    private Handler f15465c;

    /* renamed from: d */
    private long f15466d;

    /* renamed from: a */
    public static ConcurrentLinkedQueue<StackTraceElement[]> m10892a() {
        return f15463b;
    }

    public RunnableC6242l(Handler handler, long j, int i) {
        this.f15465c = handler;
        this.f15466d = j;
        this.f15464a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        m10891b();
        if (f15463b.size() < this.f15464a) {
            this.f15465c.postDelayed(this, this.f15466d);
        }
    }

    /* renamed from: b */
    public void m10891b() {
        if (f15463b.size() >= this.f15464a) {
            f15463b.poll();
        }
        f15463b.add(Looper.getMainLooper().getThread().getStackTrace());
    }
}
