package com.networkbench.agent.impl.plugin.p274e;

import android.content.Context;
import com.networkbench.agent.impl.crash.C6321c;
import com.networkbench.agent.impl.crash.C6332j;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.com.google.gson.JsonArray;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6560b extends AbstractC6566h {

    /* renamed from: a */
    public static final String f16783a = "crashSceneInfo";

    /* renamed from: g */
    private C6332j f16784g;

    /* renamed from: h */
    private C6321c f16785h;

    @Override // com.networkbench.agent.impl.plugin.p274e.AbstractC6566h
    /* renamed from: a */
    protected void mo9378a() {
    }

    public C6560b(C6321c c6321c, Context context) {
        super(EnumC6558e.after_crash);
        this.f16785h = c6321c;
        this.f16784g = new C6332j(context, "crashSceneInfo");
    }

    /* renamed from: b */
    public void m9386b() {
        ArrayList<AbstractC6590h> arrayList = new ArrayList();
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            if (abstractC6590h.f16891j) {
                if (!abstractC6590h.f16889h.m10736a()) {
                    arrayList.add(abstractC6590h);
                } else {
                    InterfaceC6393e interfaceC6393e = f16798d;
                    interfaceC6393e.mo10122a("crash add extension data: key:" + abstractC6590h.f16889h.f15617e + ", pluginClassName:" + abstractC6590h.getClass().getName());
                    this.f16785h.m10417b().put(abstractC6590h.f16889h.f15617e, C6563e.m9385a(abstractC6590h));
                }
            }
        }
        if (arrayList.size() > 0) {
            JsonArray jsonArray = new JsonArray();
            for (AbstractC6590h abstractC6590h2 : arrayList) {
                jsonArray.add(abstractC6590h2.asJson());
            }
            InterfaceC6393e interfaceC6393e2 = f16798d;
            interfaceC6393e2.mo10122a("crash add independence data into:" + jsonArray.toString());
            this.f16784g.mo10375a(String.valueOf(System.currentTimeMillis()), jsonArray.toString());
        }
    }
}
