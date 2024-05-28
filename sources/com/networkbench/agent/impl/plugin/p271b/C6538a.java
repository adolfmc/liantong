package com.networkbench.agent.impl.plugin.p271b;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6538a extends AbstractC6590h {

    /* renamed from: a */
    HashMap<String, String> f16695a;

    /* renamed from: b */
    JSONObject f16696b;

    /* renamed from: c */
    private String f16697c;

    /* renamed from: d */
    private int f16698d;

    /* renamed from: e */
    private C6540b f16699e;

    /* renamed from: o */
    private JSONArray f16700o;

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: d */
    public boolean mo9299d() {
        return true;
    }

    public C6538a(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        this.f16698d = 5;
        this.f16695a = new HashMap<>();
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        C6369q.m10273a().m10272a(new RunnableC6539a());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.b.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6539a implements Runnable {
        RunnableC6539a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0080, code lost:
            if (com.networkbench.agent.impl.p252e.C6369q.m10270c() == false) goto L10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0083, code lost:
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r6 = this;
                r0 = 1
                r1 = 0
                com.networkbench.agent.impl.plugin.b.a r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                java.lang.String r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9485a(r2)     // Catch: java.lang.Throwable -> L47
                boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L47
                if (r2 != 0) goto L31
                com.networkbench.agent.impl.plugin.b.a r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.b r3 = new com.networkbench.agent.impl.plugin.b.b     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.a r4 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                java.lang.String r4 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9485a(r4)     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.a r5 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                int r5 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9483b(r5)     // Catch: java.lang.Throwable -> L47
                r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.p271b.C6538a.m9484a(r2, r3)     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.a r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.b r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9482c(r2)     // Catch: java.lang.Throwable -> L47
                r2.m9479a()     // Catch: java.lang.Throwable -> L47
                com.networkbench.agent.impl.plugin.b.a r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L47
                r2.f16891j = r0     // Catch: java.lang.Throwable -> L47
            L31:
                boolean r0 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r0 == 0) goto L3b
            L37:
                com.networkbench.agent.impl.plugin.b.a r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.this
                r0.f16891j = r1
            L3b:
                com.networkbench.agent.impl.plugin.b.a r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.this
                com.networkbench.agent.impl.plugin.e.h r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9481d(r0)
                r0.m9370f()
                goto L83
            L45:
                r0 = move-exception
                goto L84
            L47:
                r2 = move-exception
                com.networkbench.agent.impl.f.e r3 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9480f()     // Catch: java.lang.Throwable -> L45
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L45
                r4.<init>()     // Catch: java.lang.Throwable -> L45
                java.lang.String r5 = "DownloadPlugin error: "
                r4.append(r5)     // Catch: java.lang.Throwable -> L45
                java.lang.String r5 = r2.getMessage()     // Catch: java.lang.Throwable -> L45
                r4.append(r5)     // Catch: java.lang.Throwable -> L45
                java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L45
                r3.mo10115e(r4)     // Catch: java.lang.Throwable -> L45
                com.networkbench.agent.impl.plugin.b.a r3 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L45
                r3.f16891j = r0     // Catch: java.lang.Throwable -> L45
                com.networkbench.agent.impl.plugin.b.a r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L45
                com.networkbench.agent.impl.plugin.b.b r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9482c(r0)     // Catch: java.lang.Throwable -> L45
                java.lang.String r3 = r2.getMessage()     // Catch: java.lang.Throwable -> L45
                r0.f16703a = r3     // Catch: java.lang.Throwable -> L45
                boolean r0 = r2 instanceof java.lang.InterruptedException     // Catch: java.lang.Throwable -> L45
                if (r0 == 0) goto L7c
                com.networkbench.agent.impl.plugin.b.a r0 = com.networkbench.agent.impl.plugin.p271b.C6538a.this     // Catch: java.lang.Throwable -> L45
                r0.f16891j = r1     // Catch: java.lang.Throwable -> L45
            L7c:
                boolean r0 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r0 == 0) goto L3b
                goto L37
            L83:
                return
            L84:
                boolean r2 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r2 == 0) goto L8e
                com.networkbench.agent.impl.plugin.b.a r2 = com.networkbench.agent.impl.plugin.p271b.C6538a.this
                r2.f16891j = r1
            L8e:
                com.networkbench.agent.impl.plugin.b.a r1 = com.networkbench.agent.impl.plugin.p271b.C6538a.this
                com.networkbench.agent.impl.plugin.e.h r1 = com.networkbench.agent.impl.plugin.p271b.C6538a.m9481d(r1)
                r1.m9370f()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.plugin.p271b.C6538a.RunnableC6539a.run():void");
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9300a(Map<String, Object> map) {
        try {
            this.f16696b = this.f16889h.f15616d;
            this.f16697c = this.f16696b.optString("url");
            this.f16698d = this.f16696b.optInt("sizeLimit", 5);
            this.f16700o = this.f16696b.optJSONArray("rsHead");
        } catch (Throwable unused) {
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonArray jsonArray = new JsonArray();
        if (this.f16700o != null) {
            for (int i = 0; i < this.f16700o.length(); i++) {
                String valueOf = String.valueOf(this.f16700o.opt(i));
                if (this.f16699e.f16712j.containsKey(valueOf)) {
                    this.f16695a.put(valueOf, this.f16699e.f16712j.get(valueOf).get(0));
                }
                jsonArray.add(new JsonPrimitive(valueOf));
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("url", new JsonPrimitive(this.f16697c));
        jsonObject.add("host", new JsonPrimitive(this.f16699e.f16707e));
        jsonObject.add("ip", new JsonPrimitive(this.f16699e.f16706d == null ? "" : this.f16699e.f16706d));
        jsonObject.add("cname", new JsonPrimitive(this.f16699e.f16705c == null ? "" : this.f16699e.f16705c));
        jsonObject.add("dns", new JsonPrimitive((Number) Long.valueOf(this.f16699e.f16708f)));
        jsonObject.add("connect", new JsonPrimitive((Number) Integer.valueOf(this.f16699e.f16711i)));
        C6540b c6540b = this.f16699e;
        jsonObject.add("total", new JsonPrimitive((Number) Long.valueOf(c6540b == null ? 0L : c6540b.f16704b)));
        jsonObject.add("size", new JsonPrimitive((Number) Integer.valueOf(this.f16699e.f16709g)));
        C6540b c6540b2 = this.f16699e;
        jsonObject.add("err", new JsonPrimitive(c6540b2 == null ? "" : c6540b2.f16703a));
        JsonObject m8710d = C6653u.m8710d(this.f16695a);
        if (!C6638h.m8963w().f17182z) {
            jsonObject.add("rsHead", m8710d);
        } else if (this.f16695a.size() > 0) {
            jsonObject.add("rsHead", new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(m8710d.toString())));
        } else {
            jsonObject.add("rsHead", m8710d);
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }
}
