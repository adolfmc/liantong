package com.networkbench.agent.impl.p264j;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.j.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6485a extends HarvestableArray {

    /* renamed from: a */
    private long f16390a;

    /* renamed from: b */
    private C6487c f16391b;

    /* renamed from: c */
    private EnumC6486b f16392c;

    public C6485a(EnumC6486b enumC6486b) {
        m9821a(enumC6486b);
        m9822a(System.currentTimeMillis());
    }

    public C6485a(long j) {
        m9822a(j);
    }

    public C6485a(long j, C6487c c6487c) {
        m9822a(j);
        m9820a(c6487c);
    }

    /* renamed from: a */
    public long m9824a() {
        return this.f16390a;
    }

    /* renamed from: a */
    public void m9822a(long j) {
        this.f16390a = j;
    }

    /* renamed from: b */
    public C6487c m9819b() {
        return this.f16391b;
    }

    /* renamed from: a */
    public void m9820a(C6487c c6487c) {
        this.f16391b = c6487c;
    }

    /* renamed from: a */
    public void m9823a(double d) {
        this.f16391b = new C6487c(d);
    }

    /* renamed from: b */
    public void m9818b(long j) {
        this.f16391b = new C6487c(j);
    }

    /* renamed from: c */
    public Number m9817c() {
        return this.f16391b.m9815a();
    }

    /* renamed from: d */
    public EnumC6486b m9816d() {
        return this.f16392c;
    }

    /* renamed from: a */
    public void m9821a(EnumC6486b enumC6486b) {
        this.f16392c = enumC6486b;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f16390a)));
        jsonArray.add(new JsonPrimitive(this.f16391b.m9815a()));
        return jsonArray;
    }
}
