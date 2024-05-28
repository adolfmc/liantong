package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.instrumentation.MetricEventListener;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6291i implements MetricEventListener {

    /* renamed from: a */
    public C6292j f15732a;

    /* renamed from: a */
    public C6292j m10589a(C6295m.EnumC6301f enumC6301f) {
        this.f15732a = C6292j.m10578a(enumC6301f.name(), enumC6301f);
        NBSTraceEngine.registerListener(this);
        return this.f15732a;
    }

    /* renamed from: a */
    public C6293k m10590a() {
        C6292j c6292j = this.f15732a;
        if (c6292j == null) {
            return null;
        }
        return c6292j.m10573c();
    }

    @Override // com.networkbench.agent.impl.instrumentation.MetricEventListener
    public void enterMethod(NBSTraceUnit nBSTraceUnit) {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = this.f15732a) != null) {
            c6292j.m10583a(nBSTraceUnit);
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.MetricEventListener
    public void exitMethod() {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = this.f15732a) != null) {
            c6292j.m10572d();
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.MetricEventListener
    public void exitMethodCustom(String str) {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = this.f15732a) != null) {
            c6292j.m10580a(str);
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.MetricEventListener
    public void addNetworkToSegment(C6410a c6410a) {
        C6292j c6292j = this.f15732a;
        if (c6292j == null) {
            return;
        }
        c6292j.m10584a(c6410a);
    }

    /* renamed from: b */
    public boolean m10587b() {
        return C6638h.m8963w().m9038ah() && C6653u.m8708e();
    }

    @Override // com.networkbench.agent.impl.instrumentation.MetricEventListener
    public void asyncEnterMethod(NBSTraceUnit nBSTraceUnit) {
        C6292j c6292j = this.f15732a;
        if (c6292j == null) {
            return;
        }
        c6292j.m10575b(nBSTraceUnit);
    }

    /* renamed from: a */
    public void m10588a(String str) {
        C6292j c6292j = this.f15732a;
        if (c6292j == null) {
            return;
        }
        c6292j.m10577b().f15745e = str;
    }
}
