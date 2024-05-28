package com.networkbench.agent.impl.p241b;

import android.os.Handler;
import android.os.HandlerThread;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6632b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6231d {

    /* renamed from: d */
    private static final int f15419d = 5;

    /* renamed from: e */
    private static InterfaceC6393e f15420e = C6394f.m10150a();

    /* renamed from: a */
    long f15421a;

    /* renamed from: b */
    private final Handler f15422b;

    /* renamed from: c */
    private final long f15423c;

    public C6231d(HandlerThread handlerThread, long j) {
        this.f15422b = new Handler(handlerThread.getLooper());
        this.f15423c = j;
    }

    /* renamed from: a */
    public long m10936a() {
        return this.f15421a;
    }

    /* renamed from: a */
    public void m10935a(long j) {
        if (this.f15422b != null) {
            m10934b();
            this.f15421a = j;
            this.f15422b.postDelayed(new RunnableC6232a(j), this.f15423c);
            Handler handler = this.f15422b;
            handler.postDelayed(new RunnableC6242l(handler, m10932d(), 5), m10932d() / 2);
        }
    }

    /* renamed from: d */
    private long m10932d() {
        return this.f15423c / 5;
    }

    /* renamed from: b */
    public void m10934b() {
        Handler handler = this.f15422b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.b.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6232a implements Runnable {

        /* renamed from: b */
        private long f15425b;

        RunnableC6232a(long j) {
            this.f15425b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C6369q.m10273a().m10272a(new RunnableC6244n(new C6239i(this.f15425b)));
            } catch (C6632b e) {
                InterfaceC6393e interfaceC6393e = C6231d.f15420e;
                interfaceC6393e.mo10122a("warning the blockinfo:" + e.getMessage());
            } catch (Exception e2) {
                C6231d.f15420e.mo10121a("error notifyBlockEvent", e2);
            }
        }
    }
}
