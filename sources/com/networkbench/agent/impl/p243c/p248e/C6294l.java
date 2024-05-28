package com.networkbench.agent.impl.p243c.p248e;

import android.os.Looper;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.p243c.p247d.C6274f;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6294l {

    /* renamed from: d */
    protected static final InterfaceC6393e f15753d = C6394f.m10150a();

    /* renamed from: e */
    protected C6291i f15754e = new C6291i();

    /* renamed from: a */
    public void mo10539a(String str, String str2) {
        if (m10534d()) {
            this.f15754e.m10589a(C6295m.EnumC6301f.pageLoading);
            C6291i c6291i = this.f15754e;
            c6291i.enterMethod(new NBSTraceUnit(str + str2, C6295m.EnumC6300e.OTHER.m10531a()));
            m10535c(str);
        }
    }

    /* renamed from: b */
    public void mo10538b() {
        if (m10534d()) {
            this.f15754e.exitMethod();
        }
    }

    /* renamed from: b */
    public void mo10537b(String str) {
        if (m10534d()) {
            C6291i c6291i = this.f15754e;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onStart", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: c */
    public void mo10536c() {
        if (m10534d()) {
            this.f15754e.exitMethod();
        }
    }

    /* renamed from: a */
    public void mo10540a(String str) {
        if (m10534d()) {
            C6291i c6291i = this.f15754e;
            c6291i.enterMethod(new NBSTraceUnit(str + "#onResume", C6295m.EnumC6300e.OTHER.m10531a()));
        }
    }

    /* renamed from: a */
    public C6293k mo10541a() {
        if (m10534d()) {
            this.f15754e.exitMethod();
            return this.f15754e.m10590a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m10535c(String str) {
        try {
            Looper.myQueue().addIdleHandler(C6274f.m10696a(str, this.f15754e.f15732a));
        } catch (Exception e) {
            f15753d.mo10121a("Looper.myQueue().addIdleHandler(new NBSActivityIdleHandler(nbsSlowStartEngine))  has an error ", e);
        }
    }

    /* renamed from: d */
    protected boolean m10534d() {
        return C6638h.m8963w().m9037ai();
    }

    /* renamed from: d */
    public void m10533d(String str) {
        this.f15754e.m10588a(str);
    }
}
