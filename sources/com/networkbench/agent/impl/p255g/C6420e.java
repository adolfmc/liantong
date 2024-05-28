package com.networkbench.agent.impl.p255g;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p256a.InterfaceC6406c;
import com.networkbench.agent.impl.p255g.p258c.InterfaceC6415b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6420e {

    /* renamed from: c */
    private static final InterfaceC6393e f16225c = C6394f.m10150a();

    /* renamed from: a */
    final Collection<InterfaceC6415b> f16226a = new ArrayList();

    /* renamed from: b */
    final Collection<InterfaceC6406c> f16227b = new ArrayList();

    /* renamed from: a */
    public void m10019a(InterfaceC6415b interfaceC6415b) {
        if (this.f16226a.contains(interfaceC6415b)) {
            return;
        }
        this.f16226a.add(interfaceC6415b);
    }

    /* renamed from: b */
    public void m10015b(InterfaceC6415b interfaceC6415b) {
        if (this.f16226a.contains(interfaceC6415b)) {
            this.f16226a.remove(interfaceC6415b);
        }
    }

    /* renamed from: a */
    public void m10020a(InterfaceC6406c interfaceC6406c) {
        synchronized (this.f16227b) {
            if (this.f16227b.contains(interfaceC6406c)) {
                InterfaceC6393e interfaceC6393e = f16225c;
                interfaceC6393e.mo10115e("Attempted to add the same MeasurementConsumer " + interfaceC6406c + " multiple times.");
                return;
            }
            this.f16227b.add(interfaceC6406c);
        }
    }

    /* renamed from: b */
    public void m10016b(InterfaceC6406c interfaceC6406c) {
        synchronized (this.f16227b) {
            if (!this.f16227b.contains(interfaceC6406c)) {
                InterfaceC6393e interfaceC6393e = f16225c;
                interfaceC6393e.mo10115e("Attempted to remove MeasurementConsumer " + interfaceC6406c + " which is not registered.");
                return;
            }
            this.f16227b.remove(interfaceC6406c);
        }
    }

    /* renamed from: a */
    public void m10021a() {
        try {
            f16225c.mo10117c("broadcast measurement");
            m10018a(m10017b());
        } catch (Throwable th) {
            f16225c.mo10121a("MeasurementPool broadcastMeasurements  error : ", th);
        }
    }

    /* renamed from: b */
    private List<InterfaceC6403a> m10017b() {
        ArrayList arrayList = new ArrayList();
        for (InterfaceC6415b interfaceC6415b : this.f16226a) {
            Collection<InterfaceC6403a> mo10024b = interfaceC6415b.mo10024b();
            if (mo10024b.size() > 0) {
                arrayList.addAll(mo10024b);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m10018a(List<InterfaceC6403a> list) {
        if (list.size() <= 0) {
            return;
        }
        synchronized (this.f16227b) {
            for (InterfaceC6406c interfaceC6406c : this.f16227b) {
                for (InterfaceC6403a interfaceC6403a : new ArrayList(list)) {
                    if (interfaceC6406c.mo10072a() == interfaceC6403a.mo10069a()) {
                        interfaceC6406c.mo10071a(interfaceC6403a);
                    }
                }
            }
        }
    }
}
