package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6250b {

    /* renamed from: c */
    private static final InterfaceC6393e f15518c = C6394f.m10150a();

    /* renamed from: a */
    public static ConcurrentHashMap<String, C6249a> f15516a = new ConcurrentHashMap<>();

    /* renamed from: b */
    public static List<C6249a> f15517b = new CopyOnWriteArrayList();

    /* renamed from: a */
    public static synchronized void m10857a(C6410a c6410a) {
        synchronized (C6250b.class) {
            if (C6638h.m8963w().m9038ah()) {
                if (f15516a.size() == 0) {
                    return;
                }
                for (C6249a c6249a : f15516a.values()) {
                    c6249a.f15501b.m10851a(c6410a);
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m10856a(String str) {
        synchronized (C6250b.class) {
            if (f15516a.get(str) != null) {
                C6249a c6249a = f15516a.get(str);
                if (!c6249a.f15500a) {
                    return;
                }
                f15516a.remove(c6249a);
            }
            f15516a.put(str, new C6249a(str, Harvest.currentActivityName));
        }
    }

    /* renamed from: a */
    public static synchronized void m10855a(String str, String str2, Map<String, Object> map) {
        synchronized (C6250b.class) {
            C6249a c6249a = f15516a.get(str);
            if (c6249a != null) {
                if (System.currentTimeMillis() - c6249a.m10864a() > 60000) {
                    f15518c.mo10122a("trace 超时60秒, 数据清除...");
                } else if (!c6249a.f15500a) {
                    c6249a.f15501b.m10853a(System.currentTimeMillis());
                    c6249a.m10862a((Map) map);
                    c6249a.m10863a(str2);
                    f15517b.add(c6249a);
                    c6249a.f15500a = true;
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m10858a() {
        synchronized (C6250b.class) {
            for (C6249a c6249a : f15517b) {
                Harvest.getInstance().getHarvestData().getNbsEventActions().mo10631a((HarvestableArray) c6249a);
            }
            f15517b.clear();
        }
    }
}
