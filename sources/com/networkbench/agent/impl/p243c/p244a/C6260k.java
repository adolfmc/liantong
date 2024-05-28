package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6260k {

    /* renamed from: b */
    public int f15591b;

    /* renamed from: c */
    public int f15592c;

    /* renamed from: d */
    public int f15593d;

    /* renamed from: e */
    public int f15594e;

    /* renamed from: f */
    long f15595f;

    /* renamed from: h */
    private long f15597h;

    /* renamed from: i */
    private long f15598i;

    /* renamed from: a */
    public List<C6410a> f15590a = new Vector();

    /* renamed from: g */
    public JsonArray f15596g = new JsonArray();

    /* renamed from: a */
    public void m10765a() {
        this.f15594e = 0;
        this.f15592c = 0;
        this.f15593d = 0;
        this.f15591b = 0;
        this.f15596g = new JsonArray();
    }

    /* renamed from: a */
    public void m10764a(long j) {
        this.f15598i = j;
    }

    public C6260k(long j, long j2, String str) {
        this.f15597h = j;
        this.f15598i = j2;
    }

    /* renamed from: a */
    public void m10761a(C6410a c6410a) {
        this.f15590a.add(c6410a);
    }

    /* renamed from: a */
    public JsonArray m10763a(long j, String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        return jsonArray;
    }

    /* renamed from: a */
    public JsonArray m10762a(C6258i c6258i) {
        JsonArray m10778c;
        JsonArray jsonArray = new JsonArray();
        if (c6258i != null && (m10778c = c6258i.m10778c(c6258i)) != null) {
            jsonArray.add(m10778c);
        }
        for (C6410a c6410a : this.f15590a) {
            if (c6410a.mo10065b() >= this.f15597h && c6410a.mo10065b() <= this.f15598i) {
                jsonArray.add(m10755c(c6410a));
            }
        }
        return jsonArray;
    }

    /* renamed from: c */
    private JsonArray m10755c(C6410a c6410a) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10759b(c6410a.mo10065b()))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10759b(c6410a.mo10065b()))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10756c(m10753d(c6410a)))));
        jsonArray.add(new JsonPrimitive(c6410a.f16196c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6295m.EnumC6300e.NETWORK.m10531a())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6295m.EnumC6296a.ASYNC.m10532a())));
        jsonArray.add(m10763a(c6410a.f16195b, c6410a.f16196c));
        jsonArray.add(m10758b(c6410a));
        jsonArray.add(new JsonArray());
        jsonArray.add(new JsonPrimitive((Number) 0));
        return jsonArray;
    }

    /* renamed from: b */
    public JsonObject m10758b(C6410a c6410a) {
        JsonObject jsonObject = new JsonObject();
        if (c6410a == null) {
            return jsonObject;
        }
        if (C6642k.m8921a(c6410a.m10061c().m10952s(), c6410a.m10060d())) {
            c6410a.m10061c().m10968f(200);
            c6410a.m10064a(0);
        }
        if (c6410a.m10061c().m10952s() > 600 || c6410a.m10061c().m10952s() == -1 || c6410a.m10061c().m10951t() == -1) {
            this.f15591b++;
            this.f15592c++;
        } else if (c6410a.m10061c().m10952s() != 200) {
            this.f15593d++;
            this.f15592c++;
        }
        this.f15594e++;
        jsonObject.add("host", new JsonPrimitive(c6410a.m10061c().m10959l()));
        jsonObject.add("url", new JsonPrimitive(c6410a.m10060d()));
        jsonObject.add("httpStatus", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10952s())));
        jsonObject.add("errorCode", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10951t())));
        jsonObject.add("bytesSent", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10949v())));
        jsonObject.add("bytesReceived", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10948w())));
        jsonObject.add("dns", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10960k())));
        jsonObject.add("conn", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10958m())));
        jsonObject.add("fp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10956o())));
        jsonObject.add("ssl", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10957n())));
        JsonPrimitive jsonPrimitive = null;
        jsonObject.add("txData", c6410a.m10061c().m10947x() == null ? null : new JsonPrimitive(c6410a.m10061c().m10947x()));
        try {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add("offset", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10989A() - this.f15597h)));
            jsonObject2.add("url", new JsonPrimitive(c6410a.m10060d() == null ? "" : c6410a.m10060d()));
            jsonObject2.add("param", c6410a.m10061c().m10950u() == null ? new JsonPrimitive("") : new JsonPrimitive(c6410a.m10061c().m10950u()));
            jsonObject2.add("method", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10954q().ordinal())));
            jsonObject2.add("hc", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10952s())));
            jsonObject2.add("ec", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10951t())));
            jsonObject2.add("du", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10988B())));
            jsonObject2.add("dns", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10960k())));
            jsonObject2.add("tcp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10958m())));
            jsonObject2.add("ssl", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10957n())));
            jsonObject2.add("fp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10956o())));
            jsonObject2.add("rp", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10980b())));
            jsonObject2.add("lq", new JsonPrimitive((Number) Integer.valueOf(c6410a.m10061c().m10977c())));
            jsonObject2.add("bs", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10949v())));
            jsonObject2.add("br", new JsonPrimitive((Number) Long.valueOf(c6410a.m10061c().m10948w())));
            jsonObject2.add("tx", c6410a.m10061c().m10947x() == null ? null : new JsonPrimitive(c6410a.m10061c().m10947x()));
            if (C6638h.m8963w().m9065V()) {
                jsonObject.add("txDataNew", c6410a.m10061c().m10946y() == null ? null : new JsonPrimitive(c6410a.m10061c().m10946y()));
                if (c6410a.m10061c().m10946y() != null) {
                    jsonPrimitive = new JsonPrimitive(c6410a.m10061c().m10946y());
                }
                jsonObject2.add("txn", jsonPrimitive);
            }
            this.f15596g.add(jsonObject2);
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("completeSegmentParams has an error ", th);
        }
        return jsonObject;
    }

    /* renamed from: b */
    public long m10760b() {
        for (C6410a c6410a : this.f15590a) {
            if (c6410a.mo10065b() >= this.f15597h && c6410a.mo10065b() <= this.f15598i) {
                long m10753d = m10753d(c6410a);
                if (m10753d > this.f15595f) {
                    this.f15595f = m10753d;
                }
            }
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("networkExitTimeStamp: " + this.f15595f);
        return this.f15595f;
    }

    /* renamed from: d */
    private long m10753d(C6410a c6410a) {
        if (c6410a.m10061c().m10971e() == 0) {
            return c6410a.mo10065b() + c6410a.m10061c().m10988B();
        }
        return c6410a.m10061c().m10971e();
    }

    /* renamed from: b */
    private long m10759b(long j) {
        return j == -1 ? j : j - this.f15597h;
    }

    /* renamed from: c */
    private long m10756c(long j) {
        return j == -1 ? j : j - this.f15597h;
    }

    /* renamed from: c */
    public long m10757c() {
        long m10760b = m10760b() - this.f15597h;
        return m10760b < m10754d() ? m10754d() : m10760b;
    }

    /* renamed from: d */
    public long m10754d() {
        return this.f15598i - this.f15597h;
    }
}
