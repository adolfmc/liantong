package com.networkbench.agent.impl.p267m;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6502h extends HarvestableArray {

    /* renamed from: e */
    private static final InterfaceC6393e f16467e = C6394f.m10150a();

    /* renamed from: a */
    public final Collection<C6501g> f16468a;

    /* renamed from: b */
    private int f16469b;

    /* renamed from: c */
    private int f16470c;

    /* renamed from: d */
    private int f16471d;

    public C6502h() {
        this.f16468a = new CopyOnWriteArrayList();
        this.f16471d = 0;
        this.f16469b = 0;
        this.f16470c = 0;
    }

    public C6502h(C6502h c6502h) {
        this.f16468a = new CopyOnWriteArrayList();
        if (c6502h == null) {
            return;
        }
        this.f16471d = c6502h.m9723c();
        this.f16469b = c6502h.m9728a();
        this.f16470c = c6502h.m9725b();
        Collection<C6501g> collection = c6502h.f16468a;
        if (collection != null) {
            this.f16468a.addAll(collection);
        }
    }

    /* renamed from: a */
    public void m9727a(int i) {
        this.f16469b = i;
    }

    /* renamed from: a */
    public int m9728a() {
        return this.f16469b;
    }

    /* renamed from: b */
    public void m9724b(int i) {
        int i2 = i - this.f16469b;
        i2 = (i2 > 1000000 || i2 < 0) ? 0 : 0;
        if (i2 >= 30) {
            this.f16470c = i2 - 30;
        } else {
            this.f16470c = i2;
        }
    }

    /* renamed from: b */
    public int m9725b() {
        return this.f16470c;
    }

    /* renamed from: c */
    public void m9722c(int i) {
        this.f16471d += i;
    }

    /* renamed from: c */
    public int m9723c() {
        return this.f16471d;
    }

    /* renamed from: a */
    public void m9726a(C6501g c6501g) {
        if (c6501g != null) {
            try {
                this.f16468a.add(c6501g);
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = f16467e;
                interfaceC6393e.mo10116d("addPageSpans occur an error " + e);
            }
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16469b)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16470c)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16471d)));
        JsonArray jsonArray2 = new JsonArray();
        for (C6501g c6501g : this.f16468a) {
            if (c6501g != null) {
                jsonArray2.add(c6501g.asJsonArray());
            }
        }
        jsonArray.add(jsonArray2);
        return jsonArray;
    }

    /* renamed from: d */
    public void m9721d() {
        this.f16469b = 0;
        this.f16470c = 0;
        this.f16471d = 0;
        this.f16468a.clear();
    }
}
