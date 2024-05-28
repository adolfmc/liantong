package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6284c extends HarvestableArray {

    /* renamed from: a */
    private int f15683a;

    /* renamed from: b */
    private long f15684b;

    /* renamed from: c */
    private long f15685c;

    /* renamed from: d */
    private long f15686d;

    /* renamed from: e */
    private long f15687e;

    /* renamed from: f */
    private boolean f15688f;

    /* renamed from: g */
    private String f15689g = "";

    /* renamed from: h */
    private C6293k f15690h;

    public C6284c(int i, long j, long j2, long j3, long j4) {
        this.f15683a = i;
        this.f15684b = j;
        this.f15685c = j2;
        this.f15686d = j3;
        this.f15687e = j4;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        C6293k c6293k;
        JsonArray jsonArray = new JsonArray();
        try {
            this.f15688f = m10632b();
            if (this.f15688f) {
                this.f15689g = C6653u.m8751a(C6638h.m8963w().m9076K(), false);
            }
        } catch (Exception unused) {
            this.f15689g = "";
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15683a)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10634a())));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15684b)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15685c)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15686d)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15687e)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15688f ? 1 : 0)));
        JsonElement jsonElement = null;
        jsonArray.add(!this.f15688f ? null : new JsonPrimitive(this.f15689g));
        if (this.f15688f && (c6293k = this.f15690h) != null) {
            jsonElement = new JsonPrimitive(c6293k.toJsonString());
        }
        jsonArray.add(jsonElement);
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15690h.m10550i())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15690h.m10552g())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15690h.m10551h())));
        }
        return jsonArray;
    }

    /* renamed from: a */
    public void m10633a(C6293k c6293k) {
        this.f15690h = c6293k;
    }

    /* renamed from: a */
    private long m10634a() {
        C6293k c6293k = this.f15690h;
        if (c6293k != null) {
            return c6293k.m10549j().exitTimestamp - this.f15690h.m10549j().entryTimestamp;
        }
        return -1L;
    }

    /* renamed from: b */
    private boolean m10632b() {
        return m10634a() >= Harvest.getInstance().getConfiguration().getSlowStartThreshold();
    }
}
