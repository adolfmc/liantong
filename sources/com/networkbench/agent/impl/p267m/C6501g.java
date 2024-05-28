package com.networkbench.agent.impl.p267m;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6501g extends HarvestableArray {

    /* renamed from: a */
    private long f16464a;

    /* renamed from: b */
    private long f16465b;

    /* renamed from: c */
    private String f16466c;

    public C6501g() {
        this.f16465b = 0L;
        this.f16464a = 0L;
        this.f16466c = "";
    }

    public C6501g(C6501g c6501g) {
        if (c6501g == null) {
            return;
        }
        this.f16464a = c6501g.m9733b();
        this.f16465b = c6501g.m9731c();
        this.f16466c = c6501g.m9736a();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.f16466c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf((int) TimeUnit.SECONDS.convert(m9729e(), TimeUnit.MILLISECONDS))));
        return jsonArray;
    }

    /* renamed from: e */
    private long m9729e() {
        long j = this.f16465b - this.f16464a;
        if (j < 0 || j > 1000000) {
            return 0L;
        }
        return j;
    }

    /* renamed from: a */
    public void m9735a(long j) {
        this.f16464a = j;
    }

    /* renamed from: b */
    public void m9732b(long j) {
        this.f16465b = j;
    }

    /* renamed from: a */
    public void m9734a(String str) {
        this.f16466c = str;
    }

    /* renamed from: a */
    public String m9736a() {
        return this.f16466c;
    }

    /* renamed from: b */
    public long m9733b() {
        return this.f16464a;
    }

    /* renamed from: c */
    public long m9731c() {
        return this.f16465b;
    }

    /* renamed from: d */
    public void m9730d() {
        this.f16465b = 0L;
        this.f16464a = 0L;
        this.f16466c = "";
    }

    public String toString() {
        return "curPageName:" + this.f16466c + ",timeStampStart:" + this.f16464a + ", timeStampStop:" + this.f16465b;
    }
}
