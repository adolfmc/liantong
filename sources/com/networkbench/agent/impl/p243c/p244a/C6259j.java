package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6259j {

    /* renamed from: a */
    public static C6258i f15586a;

    /* renamed from: b */
    public static C6258i f15587b;

    /* renamed from: d */
    private static final InterfaceC6393e f15589d = C6394f.m10150a();

    /* renamed from: c */
    public static ConcurrentHashMap<Integer, C6258i> f15588c = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static void m10766a(C6410a c6410a) {
        if (f15586a == null && f15587b == null) {
            return;
        }
        c6410a.f16195b = Thread.currentThread().getId() + new Random().nextInt(20) + 1;
        c6410a.f16196c = Thread.currentThread().getName() + c6410a.f16195b;
        C6226b m10061c = c6410a.m10061c();
        C6258i c6258i = f15586a;
        if (c6258i == null) {
            c6258i = f15587b;
        }
        m10061c.m10982a(c6258i.f15575g);
        C6258i c6258i2 = f15586a;
        if (c6258i2 != null && c6258i2.f15574f != null) {
            f15586a.f15574f.m10761a(c6410a);
            return;
        }
        C6258i c6258i3 = f15587b;
        if (c6258i3 == null || c6258i3.f15574f == null) {
            return;
        }
        f15587b.f15574f.m10761a(c6410a);
    }

    /* renamed from: a */
    public static synchronized void m10767a(C6295m.EnumC6298c enumC6298c, String str, boolean z, int i, long j, int i2) {
        synchronized (C6259j.class) {
            if (z) {
                Harvest.addActionAndInteraction("on" + enumC6298c.name(), str, str);
                if (Harvest.isDisabled()) {
                    return;
                }
                C6255f.f15553b = str;
                C6255f.m10807a(enumC6298c.name(), str, str, i);
                C6258i c6258i = new C6258i(j, -1, str);
                if (f15586a == null) {
                    f15586a = c6258i;
                } else {
                    f15586a.m10786a(f15586a, c6258i);
                }
                c6258i.m10783b(f15586a.f15573e);
                f15588c.put(Integer.valueOf(i2), c6258i);
            }
        }
    }

    /* renamed from: a */
    public static synchronized C6258i m10768a(int i, long j, String str, Map<String, Object> map) {
        synchronized (C6259j.class) {
            C6258i c6258i = f15588c.get(Integer.valueOf(i));
            if (c6258i == null) {
                return null;
            }
            if (map != null && map.size() > 0) {
                c6258i.f15571c.putAll(map);
            }
            c6258i.m10785a(str);
            return m10769a(i, j);
        }
    }

    /* renamed from: a */
    public static synchronized C6258i m10769a(int i, long j) {
        synchronized (C6259j.class) {
            C6258i c6258i = f15588c.get(Integer.valueOf(i));
            if (c6258i == null) {
                return null;
            }
            c6258i.m10789a(j);
            if (f15586a == null) {
                return null;
            }
            if (c6258i.equals(f15586a)) {
                if (c6258i.m10790a() <= 18000) {
                    f15589d.mo10116d("action add to nbs event actions");
                    Harvest.getInstance().getHarvestData().getNbsEventActions().mo10631a((HarvestableArray) c6258i);
                }
                InterfaceC6393e interfaceC6393e = f15589d;
                interfaceC6393e.mo10116d("action : " + c6258i.asJsonArray());
                f15586a = null;
                f15588c.clear();
            }
            C6255f.f15553b = "";
            return c6258i;
        }
    }
}
