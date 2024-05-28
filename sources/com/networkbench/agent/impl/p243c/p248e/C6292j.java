package com.networkbench.agent.impl.p243c.p248e;

import android.os.Looper;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSUnit;
import com.networkbench.agent.impl.p243c.C6268d;
import com.networkbench.agent.impl.p243c.p244a.C6256g;
import com.networkbench.agent.impl.p243c.p247d.InterfaceC6271c;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6292j implements InterfaceC6271c {

    /* renamed from: a */
    public static final long f15733a = 2000;

    /* renamed from: e */
    public static final String f15734e = "pageLoading";

    /* renamed from: h */
    private static final InterfaceC6393e f15735h = C6394f.m10150a();

    /* renamed from: c */
    public C6293k f15737c;

    /* renamed from: f */
    public C6295m.EnumC6301f f15739f;

    /* renamed from: d */
    public volatile boolean f15738d = false;

    /* renamed from: g */
    public boolean f15740g = false;

    /* renamed from: b */
    protected C6268d f15736b = new C6268d();

    protected C6292j(NBSTraceUnit nBSTraceUnit, C6295m.EnumC6301f enumC6301f) {
        this.f15737c = m10582a(nBSTraceUnit, enumC6301f);
        this.f15739f = enumC6301f;
    }

    /* renamed from: a */
    private static C6293k m10582a(NBSTraceUnit nBSTraceUnit, C6295m.EnumC6301f enumC6301f) {
        if (enumC6301f == C6295m.EnumC6301f.eventAction) {
            return new C6256g(nBSTraceUnit, enumC6301f);
        }
        if (enumC6301f == C6295m.EnumC6301f.appstart) {
            return new C6293k(nBSTraceUnit, enumC6301f);
        }
        C6293k c6293k = new C6293k(nBSTraceUnit, enumC6301f);
        c6293k.f15750j.nodeType = 2;
        return c6293k;
    }

    /* renamed from: a */
    public C6268d m10586a() {
        return this.f15736b;
    }

    /* renamed from: b */
    public C6293k m10577b() {
        return this.f15737c;
    }

    /* renamed from: a */
    public static C6292j m10578a(String str, C6295m.EnumC6301f enumC6301f) {
        InterfaceC6393e interfaceC6393e = f15735h;
        interfaceC6393e.mo10122a("startSegment name:" + str);
        NBSTraceUnit nBSTraceUnit = new NBSTraceUnit();
        nBSTraceUnit.metricName = str;
        C6292j c6292j = new C6292j(nBSTraceUnit, enumC6301f);
        c6292j.m10586a().m10731a(nBSTraceUnit, true);
        return c6292j;
    }

    /* renamed from: c */
    public C6293k m10573c() {
        if (this.f15737c == null) {
            return null;
        }
        if (this.f15739f == C6295m.EnumC6301f.eventAction || this.f15739f == C6295m.EnumC6301f.appstart) {
            this.f15738d = true;
        }
        return this.f15737c.m10544o();
    }

    /* renamed from: a */
    public void m10583a(NBSTraceUnit nBSTraceUnit) {
        if (this.f15738d) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            m10575b(nBSTraceUnit);
            return;
        }
        NBSUnit m10728d = m10586a().m10728d();
        if (m10728d == null) {
            return;
        }
        nBSTraceUnit.parentUUID = m10728d.myUUID;
        this.f15737c.m10560b((NBSUnit) nBSTraceUnit);
        m10728d.addChild(nBSTraceUnit);
        m10586a().m10731a(nBSTraceUnit, true);
    }

    /* renamed from: a */
    public void m10584a(C6410a c6410a) {
        try {
            if (C6638h.m8963w().m9038ah()) {
                InterfaceC6393e interfaceC6393e = f15735h;
                interfaceC6393e.mo10116d("addNetworkToSegment  : " + Thread.currentThread().getName() + " id : " + Thread.currentThread().getId());
                if (this.f15738d && m10570f()) {
                    return;
                }
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    f15735h.mo10116d("  不能在主线程增加异步trace... : ");
                    return;
                }
                NBSTraceUnit nBSTraceUnit = (NBSTraceUnit) m10586a().m10728d();
                if (nBSTraceUnit == null) {
                    return;
                }
                nBSTraceUnit.metricName = c6410a.m10061c().m10945z().name();
                nBSTraceUnit.setSegmentParams(c6410a);
                m10581a((NBSUnit) nBSTraceUnit);
                if (this.f15738d && this.f15739f == C6295m.EnumC6301f.pageLoading) {
                    nBSTraceUnit.isPageLoadEnd = true;
                }
                if (this.f15738d && this.f15739f == C6295m.EnumC6301f.eventAction) {
                    this.f15737c.m10568a(System.currentTimeMillis());
                }
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = f15735h;
            interfaceC6393e2.mo10115e("addNetworkToSegment error:" + e.getMessage());
        }
    }

    /* renamed from: b */
    public String m10575b(NBSTraceUnit nBSTraceUnit) {
        if (!C6638h.m8963w().m9038ah() || this.f15738d || Looper.myLooper() == Looper.getMainLooper()) {
            return "";
        }
        NBSUnit m10728d = m10586a().m10728d();
        if (m10728d == null) {
            m10728d = m10586a().m10732a();
        }
        nBSTraceUnit.parentUUID = m10728d.myUUID;
        m10728d.addChild(nBSTraceUnit);
        m10586a().m10731a(nBSTraceUnit, false);
        m10586a().m10728d();
        return m10577b().m10549j().metricName;
    }

    /* renamed from: a */
    public void m10579a(String str, int i) {
        m10583a(new NBSTraceUnit(str, i));
    }

    /* renamed from: d */
    public void m10572d() {
        try {
            NBSUnit m10728d = m10586a().m10728d();
            if (m10728d == null) {
                return;
            }
            if (this.f15738d && m10570f()) {
                return;
            }
            if (!m10571e()) {
                m10581a(m10728d);
                return;
            }
            m10586a().m10730b();
            m10572d();
        } catch (Exception e) {
            f15735h.mo10121a("error exitMethod :", e);
        }
    }

    /* renamed from: a */
    protected void m10581a(NBSUnit nBSUnit) throws Exception {
        if (nBSUnit != null && nBSUnit.metricName.equals("pageLoading") && this.f15737c.m10545n()) {
            return;
        }
        nBSUnit.complete();
        m10574b(nBSUnit);
        m10586a().m10730b();
    }

    /* renamed from: b */
    public void m10574b(NBSUnit nBSUnit) {
        try {
            this.f15737c.m10557c(nBSUnit);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: e */
    protected boolean m10571e() {
        NBSUnit m10728d = m10586a().m10728d();
        return m10728d != null && m10728d.metricName.startsWith("<_TY_C_API>");
    }

    /* renamed from: a */
    public void m10580a(String str) {
        try {
            if (m10571e() && C6638h.m8963w().m9038ah()) {
                if (this.f15738d && m10570f()) {
                    return;
                }
                if (str.equals(m10586a().m10728d().metricName)) {
                    m10581a(m10586a().m10728d());
                    return;
                }
                m10586a().m10730b();
                if (C6638h.m8963w().m8979n()) {
                    C6396h.m10140b("自定义的exitMethod遇到非自定义的trace, 废弃掉此次trace !!!", new Object[0]);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: f */
    protected boolean m10570f() {
        if (this.f15739f == C6295m.EnumC6301f.pageLoading) {
            if (this.f15737c.m10569a() <= 0 || System.currentTimeMillis() - this.f15737c.m10569a() < 2000) {
                return false;
            }
            InterfaceC6393e interfaceC6393e = f15735h;
            interfaceC6393e.mo10116d("checkWaitTime  nbsSlowStartTrace getContentLoadEndTime : " + this.f15737c.m10569a());
            return true;
        } else if (C6653u.m8708e()) {
            return true;
        } else {
            if (System.currentTimeMillis() - this.f15737c.m10549j().exitTimestamp >= 2000) {
                InterfaceC6393e interfaceC6393e2 = f15735h;
                interfaceC6393e2.mo10116d("checkWaitTime()" + (System.currentTimeMillis() - this.f15737c.m10549j().exitTimestamp));
                return true;
            }
            return false;
        }
    }

    @Override // com.networkbench.agent.impl.p243c.p247d.InterfaceC6271c
    /* renamed from: a */
    public void mo10585a(C6295m.EnumC6299d enumC6299d) {
        if (enumC6299d == C6295m.EnumC6299d.queueIdle) {
            if (!this.f15738d) {
                m10576b(enumC6299d);
            }
            this.f15740g = true;
        } else if (enumC6299d == C6295m.EnumC6299d.setPageLoadingEndTime) {
            if (this.f15738d) {
                return;
            }
            m10576b(enumC6299d);
            this.f15740g = true;
            this.f15738d = true;
        } else {
            if (!this.f15740g) {
                m10576b(enumC6299d);
            }
            this.f15738d = true;
        }
    }

    /* renamed from: b */
    public void m10576b(C6295m.EnumC6299d enumC6299d) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.f15737c.m10568a(currentTimeMillis);
            if (Harvest.getInstance().getConfiguration().calcState(this.f15737c.m10543p(), this.f15737c.m10542q()) == 2) {
                NBSTraceUnit nBSTraceUnit = new NBSTraceUnit(enumC6299d.name(), C6295m.EnumC6300e.OTHER.m10531a());
                nBSTraceUnit.entryTimestamp = currentTimeMillis;
                m10583a(nBSTraceUnit);
                m10572d();
                nBSTraceUnit.exitTimestamp = currentTimeMillis;
            }
        } catch (Throwable unused) {
        }
    }
}
