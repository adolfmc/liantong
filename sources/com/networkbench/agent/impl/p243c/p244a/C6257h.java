package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.p245b.C6262a;
import com.networkbench.agent.impl.p254f.C6396h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6257h extends C6262a {
    public C6257h() {
        this.f15600b = "userActions";
        this.f15601c = "actions";
    }

    /* renamed from: a */
    public void m10791a(C6253e c6253e) {
        try {
            if (c6253e.m10831c() >= 18000) {
                return;
            }
            if (this.f15599a.size() >= Harvest.getInstance().getConfiguration().getUserActions()) {
                this.f15599a.remove(0);
            }
            this.f15599a.add(c6253e);
        } catch (Exception e) {
            C6396h.m10132j("NBSEventActions add() has an error : " + e);
        }
    }
}
