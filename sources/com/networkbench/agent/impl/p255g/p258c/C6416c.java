package com.networkbench.agent.impl.p255g.p258c;

import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.p255g.InterfaceC6403a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.c.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6416c implements InterfaceC6415b {

    /* renamed from: a */
    private final EnumC6421f f16222a;

    /* renamed from: b */
    private final ArrayList<InterfaceC6403a> f16223b = new ArrayList<>();

    public C6416c(EnumC6421f enumC6421f) {
        this.f16222a = enumC6421f;
    }

    @Override // com.networkbench.agent.impl.p255g.p258c.InterfaceC6415b
    /* renamed from: a */
    public EnumC6421f mo10025a() {
        return this.f16222a;
    }

    @Override // com.networkbench.agent.impl.p255g.p258c.InterfaceC6415b
    /* renamed from: a */
    public void mo10022a(InterfaceC6403a interfaceC6403a) {
        synchronized (this.f16223b) {
            if (interfaceC6403a != null) {
                this.f16223b.add(interfaceC6403a);
            }
        }
    }

    @Override // com.networkbench.agent.impl.p255g.p258c.InterfaceC6415b
    /* renamed from: b */
    public Collection<InterfaceC6403a> mo10024b() {
        synchronized (this.f16223b) {
            if (this.f16223b.size() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(this.f16223b);
            this.f16223b.clear();
            return arrayList;
        }
    }
}
