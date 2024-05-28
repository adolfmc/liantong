package com.networkbench.agent.impl.p267m;

import android.content.Context;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6496c extends AbstractC6497d {

    /* renamed from: f */
    private static final Collection<C6502h> f16446f = new CopyOnWriteArrayList();

    /* renamed from: g */
    private boolean f16447g = false;

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo9741a(HarvestConnection harvestConnection) {
        super.mo9741a(harvestConnection);
    }

    public C6496c(Context context) {
        this.f16449b = context;
        this.f16450c = "NBSEventAction";
        this.f16451d = 7;
        this.f16452e = EnumC6455q.USER_ACTION;
    }

    /* renamed from: a */
    public static Collection<C6502h> m9754a() {
        return f16446f;
    }

    /* renamed from: a */
    public static void m9753a(C6502h c6502h) {
        if (c6502h != null) {
            f16446f.add(new C6502h(c6502h));
        }
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: b */
    public void mo9739b() {
        f16448a.mo10122a("timer to handle user actions");
        mo9750e();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("custEvents", C6495b.m9761a().asJsonArray());
        JsonArray jsonArray = new JsonArray();
        for (C6502h c6502h : f16446f) {
            if (c6502h != null) {
                jsonArray.add(c6502h.asJsonArray());
            }
        }
        jsonObject.add("sessions", jsonArray);
        return jsonObject;
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: c */
    protected boolean mo9738c() {
        return f16446f.isEmpty() && C6495b.m9761a().m9756e();
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: d */
    protected void mo9737d() {
        C6495b.m9761a().m9757d();
        f16446f.clear();
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: e */
    protected void mo9750e() {
        C6495b.m9761a().m9758c();
        super.mo9750e();
    }
}
