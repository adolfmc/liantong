package com.networkbench.agent.impl.p243c.p244a;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6253e extends HarvestableArray {

    /* renamed from: a */
    public C6253e f15526a;

    /* renamed from: b */
    public C6253e f15527b;

    /* renamed from: c */
    public String f15528c;

    /* renamed from: d */
    public boolean f15529d;

    /* renamed from: e */
    public Map<String, Object> f15530e;

    /* renamed from: f */
    JsonObject f15531f;

    /* renamed from: g */
    private int f15532g;

    /* renamed from: h */
    private long f15533h;

    /* renamed from: i */
    private long f15534i;

    /* renamed from: j */
    private long f15535j;

    /* renamed from: l */
    private long f15536l;

    /* renamed from: m */
    private long f15537m;

    /* renamed from: n */
    private int f15538n;

    /* renamed from: o */
    private int f15539o;

    /* renamed from: p */
    private int f15540p;

    /* renamed from: q */
    private int f15541q;

    /* renamed from: r */
    private int f15542r;

    /* renamed from: s */
    private C6293k f15543s;

    /* renamed from: t */
    private String f15544t;

    /* renamed from: a */
    public long m10841a() {
        return this.f15533h;
    }

    /* renamed from: a */
    public void m10839a(long j) {
        this.f15534i = j;
    }

    /* renamed from: b */
    public long m10834b() {
        return this.f15534i;
    }

    /* renamed from: b */
    public void m10833b(long j) {
        this.f15537m = j;
    }

    /* renamed from: a */
    public void m10840a(int i) {
        this.f15538n = i;
    }

    public C6253e(long j, String str) {
        this.f15529d = false;
        this.f15530e = new HashMap();
        this.f15533h = 0L;
        this.f15534i = 0L;
        this.f15535j = 0L;
        this.f15541q = 0;
        this.f15542r = 0;
        this.f15544t = "";
        this.f15537m = m10838a(j, System.currentTimeMillis());
        this.f15528c = str;
        this.f15532g = 0;
    }

    public C6253e(long j, long j2, String str, boolean z) {
        this.f15529d = false;
        this.f15530e = new HashMap();
        this.f15533h = 0L;
        this.f15534i = 0L;
        this.f15535j = 0L;
        this.f15541q = 0;
        this.f15542r = 0;
        this.f15544t = "";
        this.f15537m = m10838a(j, j2);
        this.f15528c = str;
        this.f15532g = 0;
        this.f15529d = z;
        this.f15533h = j;
        this.f15534i = j2;
    }

    /* renamed from: a */
    public void m10836a(C6293k c6293k) {
        this.f15543s = c6293k;
    }

    /* renamed from: a */
    public long m10838a(long j, long j2) {
        C6638h.f17124y.mo10122a("blockTIme threshold:" + Harvest.getInstance().getConfiguration().getSlowUserActionThreshold());
        long j3 = j2 - j;
        if (j3 > Harvest.getInstance().getConfiguration().getSlowUserActionThreshold()) {
            this.f15538n |= EnumC6254a.kartun.m10821a();
        }
        return j3;
    }

    /* renamed from: c */
    private void m10830c(long j) {
        C6638h.f17124y.mo10122a("slowUserAction threshold:" + Harvest.getInstance().getConfiguration().getSlowUserActionThreshold());
        if (j > Harvest.getInstance().getConfiguration().getSlowUserActionThreshold()) {
            this.f15538n |= EnumC6254a.slowAction.m10821a();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.c.a.e$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6254a {
        normal(0),
        appCrash(1),
        networkError(2),
        kartun(4),
        slowAction(8);
        

        /* renamed from: f */
        private final int f15551f;

        EnumC6254a(int i) {
            this.f15551f = i;
        }

        /* renamed from: a */
        public int m10821a() {
            return this.f15551f;
        }
    }

    /* renamed from: d */
    private String m10828d() {
        return m10824f() ? this.f15544t : "";
    }

    /* renamed from: e */
    private String m10826e() {
        return m10824f() ? C6653u.m8751a(C6638h.m8963w().m9076K(), false) : "";
    }

    /* renamed from: f */
    private boolean m10824f() {
        return ((this.f15538n & EnumC6254a.networkError.m10821a()) == 0 && (this.f15538n & EnumC6254a.kartun.m10821a()) == 0 && (this.f15538n & EnumC6254a.slowAction.m10821a()) == 0) ? false : true;
    }

    /* renamed from: g */
    private int m10823g() {
        if (this.f15538n == EnumC6254a.normal.m10821a()) {
            return this.f15538n;
        }
        if ((this.f15538n & EnumC6254a.networkError.m10821a()) != 0) {
            this.f15538n = EnumC6254a.networkError.m10821a();
            return this.f15538n;
        } else if ((this.f15538n & EnumC6254a.kartun.m10821a()) != 0) {
            this.f15538n = EnumC6254a.kartun.m10821a();
            return this.f15538n;
        } else if ((this.f15538n & EnumC6254a.slowAction.m10821a()) != 0) {
            this.f15538n = EnumC6254a.slowAction.m10821a();
            return this.f15538n;
        } else {
            return this.f15538n;
        }
    }

    /* renamed from: c */
    public long m10831c() {
        if (!this.f15529d) {
            C6293k c6293k = this.f15543s;
            if (c6293k == null) {
                return 0L;
            }
            long m10542q = c6293k.m10542q();
            long j = this.f15537m;
            return m10542q < j ? j : m10542q;
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("countDuration:" + this.f15535j + ", beginTimeStamp:" + this.f15533h);
        long j2 = this.f15535j - this.f15533h;
        return (j2 < 0 || j2 < this.f15537m) ? this.f15537m : j2;
    }

    /* renamed from: h */
    private void m10822h() {
        if (this.f15539o > 0) {
            C6638h.f17124y.mo10122a("countAvailability  ActionFailureThreshold: " + Harvest.getActionFailureThreshold());
            if ((this.f15540p * 100) / this.f15539o >= Harvest.getActionFailureThreshold()) {
                this.f15538n |= EnumC6254a.networkError.m10821a();
            }
        }
    }

    /* renamed from: b */
    private JsonArray m10832b(C6410a c6410a) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10827d(c6410a.mo10065b()))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10827d(c6410a.mo10065b()))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10825e(m10829c(c6410a)))));
        jsonArray.add(new JsonPrimitive(c6410a.f16196c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6295m.EnumC6300e.NETWORK.m10531a())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6295m.EnumC6296a.ASYNC.m10532a())));
        jsonArray.add(m10837a(c6410a.f16195b, c6410a.f16196c));
        jsonArray.add(m10835a(c6410a));
        jsonArray.add(new JsonArray());
        jsonArray.add(new JsonPrimitive((Number) 0));
        return jsonArray;
    }

    /* renamed from: c */
    private long m10829c(C6410a c6410a) {
        if (c6410a.m10061c().m10971e() == 0) {
            return c6410a.mo10065b() + c6410a.m10061c().m10988B();
        }
        return c6410a.m10061c().m10971e();
    }

    /* renamed from: d */
    private long m10827d(long j) {
        return j == -1 ? j : j - this.f15533h;
    }

    /* renamed from: e */
    private long m10825e(long j) {
        return j == -1 ? j : j - this.f15533h;
    }

    /* renamed from: a */
    public JsonArray m10837a(long j, String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        return jsonArray;
    }

    /* renamed from: a */
    public JsonObject m10835a(C6410a c6410a) {
        JsonObject jsonObject = new JsonObject();
        if (c6410a == null) {
            return jsonObject;
        }
        if (C6642k.m8921a(c6410a.m10061c().m10952s(), c6410a.m10060d())) {
            c6410a.m10061c().m10968f(200);
            c6410a.m10064a(0);
        }
        if (c6410a.m10061c().m10952s() > 600 || c6410a.m10061c().m10952s() == -1 || c6410a.m10061c().m10951t() == -1) {
            this.f15542r++;
            this.f15540p++;
        } else if (c6410a.m10061c().m10952s() != 200) {
            this.f15541q++;
            this.f15540p++;
        }
        this.f15539o++;
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
        jsonObject.add("txData", c6410a.m10061c().m10947x() == null ? null : new JsonPrimitive(c6410a.m10061c().m10947x()));
        if (C6638h.m8963w().m9065V()) {
            jsonObject.add("txDataNew", c6410a.m10061c().m10946y() != null ? new JsonPrimitive(c6410a.m10061c().m10946y()) : null);
        }
        return jsonObject;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        C6293k c6293k = this.f15543s;
        if (c6293k != null) {
            this.f15544t = c6293k.asJson().toString();
            C6256g c6256g = (C6256g) this.f15543s;
            this.f15539o = c6256g.f15565a;
            this.f15540p = c6256g.f15566b;
            this.f15541q = c6256g.f15567c;
            this.f15542r = c6256g.f15568d;
            m10822h();
        }
        long m10831c = m10831c();
        m10830c(m10831c);
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15532g)));
        jsonArray.add(new JsonPrimitive(this.f15528c));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10831c)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15537m)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m10823g())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15539o)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15540p)));
        jsonArray.add(new JsonPrimitive(m10826e()));
        jsonArray.add(new JsonPrimitive(m10828d()));
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15541q)));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15542r)));
            if (this.f15543s != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("reqs", this.f15543s.f15747g);
                jsonArray.add(new JsonPrimitive(jsonObject.toString()));
            } else {
                jsonArray.add(new JsonPrimitive(""));
            }
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add("name", new JsonPrimitive(this.f15528c));
            jsonObject2.add("cust", new JsonPrimitive(C6653u.m8735a(this.f15530e).toString()));
            jsonArray.add(new JsonPrimitive(jsonObject2.toString()));
        }
        return jsonArray;
    }
}
