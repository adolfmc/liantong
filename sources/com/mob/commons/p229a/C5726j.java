package com.mob.commons.p229a;

import com.mob.commons.C5782c;
import com.mob.commons.C5849j;
import com.mob.commons.C5855l;
import com.mob.commons.C5857m;
import com.mob.commons.InterfaceC5854k;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5726j extends AbstractRunnableC5704c {

    /* renamed from: c */
    private static InterfaceC5854k f14112c;

    public C5726j() {
        super("p", 0L, null, 0L);
        m12775a(0);
        m12757c();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (m12754f()) {
            m12699l();
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(C5857m.m12226a("004g1bi1hd"), "PVMT");
        hashMap.put(C5857m.m12226a("008 baHbgdgRbgbd[d"), this.f14066a);
        if (!C5849j.m12264a().f14403a.get()) {
            hashMap.putAll(C5849j.m12264a().m12249c());
            C5849j.m12264a().f14403a.compareAndSet(false, true);
        }
        C5782c.m12489a().m12488a(System.currentTimeMillis(), hashMap);
    }

    /* renamed from: l */
    private static synchronized boolean m12699l() {
        synchronized (C5726j.class) {
            if (f14112c == null) {
                f14112c = new InterfaceC5854k() { // from class: com.mob.commons.a.j.1
                    @Override // com.mob.commons.InterfaceC5854k
                    /* renamed from: a */
                    public void mo12147a(boolean z, boolean z2, long j) {
                        if (z) {
                            C5731l.m12681a().m12678a(0L, C5726j.class, new Object[]{-1, Long.valueOf(System.currentTimeMillis())}, 0);
                        }
                    }
                };
                C5855l.m12246a().m12244a(f14112c);
                return true;
            }
            return false;
        }
    }
}
