package com.networkbench.agent.impl.p243c;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6302f extends HarvestableArray {

    /* renamed from: b */
    private static final InterfaceC6393e f15797b = C6394f.m10150a();

    /* renamed from: a */
    public boolean f15798a = false;

    /* renamed from: c */
    private final List<C6281e> f15799c = Collections.synchronizedList(new ArrayList());

    /* renamed from: d */
    private final List<C6281e> f15800d = Collections.synchronizedList(new ArrayList());

    /* renamed from: a */
    public void m10527a(C6281e c6281e) {
        if (this.f15798a) {
            this.f15800d.add(c6281e);
            return;
        }
        synchronized (c6281e) {
            for (C6281e c6281e2 : this.f15799c) {
                if (c6281e2 == null) {
                    return;
                }
                if (c6281e.m10647k().equals(c6281e2.m10647k())) {
                    c6281e2.m10643o();
                    return;
                }
            }
            this.f15799c.add(c6281e);
        }
    }

    /* renamed from: b */
    public synchronized void m10525b(C6281e c6281e) {
        this.f15799c.remove(c6281e);
    }

    /* renamed from: a */
    public synchronized void m10528a(int i) {
        this.f15799c.remove(i);
    }

    /* renamed from: a */
    public synchronized void m10529a() {
        this.f15799c.clear();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public synchronized JsonArray asJsonArray() {
        JsonArray jsonArray;
        jsonArray = new JsonArray();
        for (C6281e c6281e : this.f15799c) {
            jsonArray.add(c6281e.asJson());
        }
        return jsonArray;
    }

    /* renamed from: b */
    public Collection<C6281e> m10526b() {
        return this.f15799c;
    }

    /* renamed from: c */
    public int m10524c() {
        return this.f15799c.size();
    }

    public String toString() {
        return "ErrorDatas{errorDatas=" + this.f15799c + "}";
    }

    /* renamed from: d */
    public synchronized void m10523d() {
        for (C6281e c6281e : this.f15800d) {
            m10527a(c6281e);
        }
        this.f15800d.clear();
    }
}
