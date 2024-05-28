package com.networkbench.agent.impl.p243c.p247d;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSUnit;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6277i {

    /* renamed from: a */
    public static int f15654a = 8000;

    /* renamed from: b */
    private static Map<String, Set<C6275g>> f15655b = new ConcurrentHashMap();

    /* renamed from: a */
    public static void m10680a() {
        f15655b.clear();
    }

    /* renamed from: a */
    public static void m10677a(C6275g c6275g, String str) {
        if (c6275g == null || str == null) {
            return;
        }
        if (m10675a(str)) {
            f15655b.get(str).add(c6275g);
        } else {
            f15655b.put(str, m10678a(c6275g));
        }
    }

    /* renamed from: a */
    public static boolean m10675a(String str) {
        return f15655b.containsKey(str);
    }

    /* renamed from: a */
    private static Set<C6275g> m10678a(C6275g c6275g) {
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        copyOnWriteArraySet.add(c6275g);
        return copyOnWriteArraySet;
    }

    /* renamed from: b */
    public static List<HarvestableArray> m10671b() {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        for (Map.Entry<String, Set<C6275g>> entry : f15655b.entrySet()) {
            Set<C6275g> value = entry.getValue();
            if (m10669b(value)) {
                arrayList2.add(entry.getKey().toString());
                C6275g m10672a = m10672a(value);
                if (m10672a != null && !m10672a.m10686j()) {
                    arrayList.add(m10672a);
                }
            }
        }
        for (String str : arrayList2) {
            f15655b.remove(str);
        }
        return arrayList;
    }

    /* renamed from: a */
    private static C6275g m10672a(Set<C6275g> set) {
        C6275g m10668c = m10668c(set);
        if (m10668c != null) {
            m10670b(m10668c);
            long m10689g = m10668c.m10689g();
            long m10666e = m10666e(set);
            long m10665f = m10665f(set);
            if (m10665f == 0) {
                m10665f = m10666e;
            }
            return new C6275g(3, m10666e - m10689g, m10665f - m10689g, m10668c.m10693c(), m10674a(m10667d(set), m10689g, m10665f, m10666e));
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m10669b(Set<C6275g> set) {
        for (C6275g c6275g : set) {
            if (System.currentTimeMillis() - c6275g.m10688h() >= 0 && System.currentTimeMillis() - c6275g.m10688h() < f15654a) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    private static void m10670b(C6275g c6275g) {
        c6275g.m10690f();
    }

    /* renamed from: c */
    private static C6275g m10668c(Set<C6275g> set) {
        C6275g c6275g = null;
        if (set.size() > 0) {
            for (C6275g c6275g2 : set) {
                if (c6275g == null) {
                    c6275g = c6275g2;
                }
                if (c6275g.m10689g() > c6275g2.m10689g()) {
                    c6275g = c6275g2;
                }
            }
        }
        return c6275g;
    }

    /* renamed from: d */
    private static List<C6293k> m10667d(Set<C6275g> set) {
        ArrayList arrayList = new ArrayList();
        if (set.size() > 0) {
            for (C6275g c6275g : set) {
                arrayList.add(c6275g.m10695a());
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    private static long m10666e(Set<C6275g> set) {
        long j = 0;
        if (set.size() > 0) {
            for (C6275g c6275g : set) {
                if (j < c6275g.m10688h()) {
                    j = c6275g.m10688h();
                }
            }
        }
        return j;
    }

    /* renamed from: f */
    private static long m10665f(Set<C6275g> set) {
        long j = 0;
        if (set.size() > 0) {
            for (C6275g c6275g : set) {
                if (j < c6275g.m10687i()) {
                    j = c6275g.m10687i();
                }
            }
        }
        return j;
    }

    /* renamed from: a */
    private static C6293k m10674a(List<C6293k> list, long j, long j2, long j3) {
        C6293k m10679a = m10679a(j, j2, j3);
        for (C6293k c6293k : list) {
            m10676a(c6293k, m10679a);
        }
        m10673a(m10679a.m10548k(), m10679a.f15750j);
        return m10679a;
    }

    /* renamed from: a */
    private static C6293k m10679a(long j, long j2, long j3) {
        C6293k c6293k = new C6293k(new NBSTraceUnit(), C6295m.EnumC6301f.pageLoading);
        c6293k.m10544o();
        c6293k.f15750j.metricName = "pageLoading";
        c6293k.f15750j.setUnitThreadWithUIThread();
        c6293k.f15750j.segmentType = 0;
        c6293k.f15750j.callType = 1;
        c6293k.f15750j.nodeType = 2;
        c6293k.m10562b(j);
        c6293k.m10568a(j2);
        c6293k.f15750j.entryTimestamp = j;
        c6293k.f15750j.exitTimestamp = j3;
        return c6293k;
    }

    /* renamed from: a */
    private static void m10676a(C6293k c6293k, C6293k c6293k2) {
        if (c6293k.m10548k() == null) {
            return;
        }
        for (Map.Entry<UUID, NBSUnit> entry : c6293k.m10548k().entrySet()) {
            if (entry.getValue().metricName != null && !entry.getValue().metricName.equals("pageLoading")) {
                if (entry.getValue().parentUUID == c6293k.f15750j.myUUID) {
                    entry.getValue().parentUUID = c6293k2.f15750j.myUUID;
                }
                if (!c6293k2.m10548k().contains(entry.getKey())) {
                    c6293k2.m10548k().put(entry.getKey(), entry.getValue());
                }
            }
        }
        c6293k.f15750j.myUUID = c6293k2.f15750j.myUUID;
    }

    /* renamed from: a */
    public static void m10673a(Map<UUID, NBSUnit> map, NBSUnit nBSUnit) {
        Object[] array = map.values().toArray();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            if (((NBSTraceUnit) array[i]).callType == 1) {
                arrayList.add(array[i]);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            NBSUnit nBSUnit2 = null;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (((NBSUnit) arrayList.get(i2)).entryTimestamp > ((NBSUnit) arrayList.get(i3)).entryTimestamp && ((NBSUnit) arrayList.get(i2)).entryTimestamp < ((NBSUnit) arrayList.get(i3)).exitTimestamp && (nBSUnit2 == null || (nBSUnit2 != null && nBSUnit2.entryTimestamp < ((NBSUnit) arrayList.get(i3)).entryTimestamp))) {
                    nBSUnit2 = (NBSUnit) arrayList.get(i3);
                }
            }
            if (nBSUnit2 != null) {
                ((NBSUnit) arrayList.get(i2)).parentUUID = nBSUnit2.myUUID;
            }
            if (((NBSUnit) arrayList.get(i2)).parentUUID == null || nBSUnit2 == null) {
                ((NBSUnit) arrayList.get(i2)).parentUUID = nBSUnit.myUUID;
            }
        }
    }
}
