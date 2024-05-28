package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6252d {

    /* renamed from: a */
    public List<C6410a> f15519a = new Vector();

    /* renamed from: b */
    public int f15520b;

    /* renamed from: c */
    public int f15521c;

    /* renamed from: d */
    public int f15522d;

    /* renamed from: e */
    public int f15523e;

    /* renamed from: f */
    private long f15524f;

    /* renamed from: g */
    private long f15525g;

    /* renamed from: a */
    public void m10853a(long j) {
        this.f15525g = j;
    }

    /* renamed from: a */
    public long m10854a() {
        return this.f15524f;
    }

    /* renamed from: b */
    public long m10850b() {
        return this.f15525g;
    }

    public C6252d(long j) {
        this.f15524f = j;
    }

    /* renamed from: a */
    public void m10851a(C6410a c6410a) {
        this.f15519a.add(c6410a);
    }

    /* renamed from: c */
    public JsonObject m10847c() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("reqs", m10844d());
        return jsonObject;
    }

    /* renamed from: d */
    private JsonArray m10844d() {
        return m10842e();
    }

    /* renamed from: a */
    public JsonArray m10852a(long j, String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        return jsonArray;
    }

    /* renamed from: e */
    private JsonArray m10842e() {
        JsonArray jsonArray = new JsonArray();
        for (C6410a c6410a : this.f15519a) {
            if (c6410a.mo10065b() >= this.f15524f && c6410a.mo10065b() <= this.f15525g) {
                jsonArray.add(m10845c(c6410a));
            }
        }
        return jsonArray;
    }

    /* renamed from: c */
    private JsonObject m10845c(C6410a c6410a) {
        return m10848b(c6410a);
    }

    /* renamed from: b */
    public JsonObject m10848b(C6410a c6410a) {
        JsonObject jsonObject = new JsonObject();
        if (c6410a == null) {
            return jsonObject;
        }
        if (c6410a.m10061c().m10952s() > 600 || c6410a.m10061c().m10952s() == -1 || c6410a.m10061c().m10951t() == -1) {
            this.f15520b++;
            this.f15521c++;
        } else if (c6410a.m10061c().m10952s() != 200) {
            this.f15522d++;
            this.f15521c++;
        }
        this.f15523e++;
        jsonObject.add("offset", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10989A() - this.f15524f)));
        jsonObject.add("url", new JsonPrimitive(c6410a.m10060d()));
        jsonObject.add("param", c6410a.m10061c().m10950u() == null ? new JsonPrimitive("") : new JsonPrimitive(c6410a.m10061c().m10950u()));
        jsonObject.add("method", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10954q().ordinal())));
        jsonObject.add("hc", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10952s())));
        jsonObject.add("ec", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10951t())));
        jsonObject.add("du", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10988B())));
        jsonObject.add("dns", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10960k())));
        jsonObject.add("tcp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10958m())));
        jsonObject.add("ssl", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10957n())));
        jsonObject.add("fp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10956o())));
        jsonObject.add("rp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10980b())));
        jsonObject.add("lq", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10977c())));
        jsonObject.add("bs", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10949v())));
        jsonObject.add("br", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10948w())));
        jsonObject.add("tx", c6410a.m10061c().m10947x() == null ? null : new JsonPrimitive(c6410a.m10061c().m10947x()));
        if (C6638h.m8963w().m9065V()) {
            jsonObject.add("txn", c6410a.m10061c().m10946y() != null ? new JsonPrimitive(c6410a.m10061c().m10946y()) : null);
        }
        return jsonObject;
    }

    /* renamed from: d */
    private long m10843d(C6410a c6410a) {
        return c6410a.mo10065b() + c6410a.m10061c().m10988B();
    }

    /* renamed from: b */
    private long m10849b(long j) {
        return j == -1 ? j : j - this.f15524f;
    }

    /* renamed from: c */
    private long m10846c(long j) {
        return j == -1 ? j : j - this.f15524f;
    }
}
