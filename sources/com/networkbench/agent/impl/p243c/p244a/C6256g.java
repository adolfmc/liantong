package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSUnit;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.com.google.gson.JsonArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6256g extends C6293k {

    /* renamed from: a */
    public int f15565a;

    /* renamed from: b */
    public int f15566b;

    /* renamed from: c */
    public int f15567c;

    /* renamed from: d */
    public int f15568d;

    public C6256g(NBSTraceUnit nBSTraceUnit, C6295m.EnumC6301f enumC6301f) {
        super(nBSTraceUnit, enumC6301f);
        this.f15565a = 0;
        this.f15566b = 0;
        this.f15567c = 0;
        this.f15568d = 0;
    }

    @Override // com.networkbench.agent.impl.p243c.p248e.C6293k
    /* renamed from: a */
    public JsonArray mo10565a(NBSUnit nBSUnit) {
        NBSTraceUnit nBSTraceUnit = (NBSTraceUnit) nBSUnit;
        JsonArray mo10565a = super.mo10565a(nBSUnit);
        if (nBSTraceUnit.segmentType == C6295m.EnumC6300e.NETWORK.m10531a() && nBSTraceUnit.segmentParams != null) {
            m10792a(nBSTraceUnit.segmentParams.m10061c().m10952s());
        }
        return mo10565a;
    }

    /* renamed from: a */
    public void m10792a(int i) {
        this.f15565a++;
        if (i >= 400 || i == -1) {
            this.f15566b++;
            if (i > 600 || i == -1) {
                this.f15568d++;
            } else {
                this.f15567c++;
            }
        }
    }
}
