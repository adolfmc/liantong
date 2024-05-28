package com.networkbench.agent.impl.plugin.p272c;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.C6567f;
import com.networkbench.agent.impl.plugin.p272c.C6547c;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.c.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6544b extends AbstractC6590h {

    /* renamed from: a */
    protected static InterfaceC6393e f16718a = C6394f.m10150a();

    /* renamed from: d */
    private static final int f16719d = 30;

    /* renamed from: b */
    JSONObject f16720b;

    /* renamed from: c */
    C6547c f16721c;

    /* renamed from: e */
    private int f16722e;

    /* renamed from: o */
    private String f16723o;

    /* renamed from: p */
    private String f16724p;

    public C6544b(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        InterfaceC6393e interfaceC6393e = f16718a;
        interfaceC6393e.mo10122a("MTRPlugin init argumentsJson:" + c6267d.f15616d);
        this.f16890i = new C6567f(c6267d.f15616d, (String) this.f16894n.m9371e().get("hostName"));
        mo9300a(abstractC6566h.m9371e());
        this.f16724p = "";
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        C6369q.m10273a().m10272a(new RunnableC6545a());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.c.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6545a implements Runnable {
        RunnableC6545a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x00db, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0063, code lost:
            if (com.networkbench.agent.impl.p252e.C6369q.m10270c() != false) goto L9;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r5 = this;
                r0 = 0
                com.networkbench.agent.impl.plugin.c.b r1 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                java.lang.String r1 = r1.f16888g     // Catch: java.lang.Throwable -> L68
                java.net.InetAddress.getByName(r1)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.f.e r1 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a     // Catch: java.lang.Throwable -> L68
                java.lang.String r2 = "MTRPlugin begin run"
                r1.mo10122a(r2)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b r1 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.util.h r3 = com.networkbench.agent.impl.util.C6638h.m8963w()     // Catch: java.lang.Throwable -> L68
                android.content.Context r3 = r3.m9076K()     // Catch: java.lang.Throwable -> L68
                java.lang.String r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9460a(r2, r3)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.p272c.C6544b.m9459a(r1, r2)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.f.e r1 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a     // Catch: java.lang.Throwable -> L68
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68
                r2.<init>()     // Catch: java.lang.Throwable -> L68
                java.lang.String r3 = "dnsServer:"
                r2.append(r3)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b r3 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                java.lang.String r3 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9461a(r3)     // Catch: java.lang.Throwable -> L68
                r2.append(r3)     // Catch: java.lang.Throwable -> L68
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L68
                r1.mo10122a(r2)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.c r1 = new com.networkbench.agent.impl.plugin.c.c     // Catch: java.lang.Throwable -> L68
                r1.<init>()     // Catch: java.lang.Throwable -> L68
                java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L68
                r2.<init>()     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b r3 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                java.lang.String r3 = r3.f16888g     // Catch: java.lang.Throwable -> L68
                r2.add(r3)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b r3 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L68
                int r3 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9457b(r3)     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.p272c.C6551e.f16742a = r3     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.c.b$a$1 r3 = new com.networkbench.agent.impl.plugin.c.b$a$1     // Catch: java.lang.Throwable -> L68
                r3.<init>()     // Catch: java.lang.Throwable -> L68
                com.networkbench.agent.impl.plugin.p272c.C6551e.m9442a(r2, r1, r3)     // Catch: java.lang.Throwable -> L68
                boolean r1 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r1 == 0) goto Ld2
                goto Lc7
            L66:
                r1 = move-exception
                goto Ldc
            L68:
                r1 = move-exception
                boolean r2 = r1 instanceof java.lang.InterruptedException     // Catch: java.lang.Throwable -> L66
                if (r2 == 0) goto L71
                com.networkbench.agent.impl.plugin.c.b r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L66
                r2.f16891j = r0     // Catch: java.lang.Throwable -> L66
            L71:
                boolean r2 = r1 instanceof java.io.IOException     // Catch: java.lang.Throwable -> L66
                if (r2 == 0) goto Lab
                com.networkbench.agent.impl.plugin.c.b r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L66
                java.lang.String r3 = r1.getMessage()     // Catch: java.lang.Throwable -> L66
                com.networkbench.agent.impl.plugin.p272c.C6544b.m9456b(r2, r3)     // Catch: java.lang.Throwable -> L66
                com.networkbench.agent.impl.plugin.c.b r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L66
                r3 = 1
                r2.f16891j = r3     // Catch: java.lang.Throwable -> L66
                com.networkbench.agent.impl.f.e r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a     // Catch: java.lang.Throwable -> L66
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
                r3.<init>()     // Catch: java.lang.Throwable -> L66
                java.lang.String r4 = "MTRPlugin errorDesc: "
                r3.append(r4)     // Catch: java.lang.Throwable -> L66
                com.networkbench.agent.impl.plugin.c.b r4 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L66
                java.lang.String r4 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9454c(r4)     // Catch: java.lang.Throwable -> L66
                r3.append(r4)     // Catch: java.lang.Throwable -> L66
                java.lang.String r4 = ", isEndSuccess:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L66
                com.networkbench.agent.impl.plugin.c.b r4 = com.networkbench.agent.impl.plugin.p272c.C6544b.this     // Catch: java.lang.Throwable -> L66
                boolean r4 = r4.f16891j     // Catch: java.lang.Throwable -> L66
                r3.append(r4)     // Catch: java.lang.Throwable -> L66
                java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L66
                r2.mo10116d(r3)     // Catch: java.lang.Throwable -> L66
            Lab:
                com.networkbench.agent.impl.f.e r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a     // Catch: java.lang.Throwable -> L66
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
                r3.<init>()     // Catch: java.lang.Throwable -> L66
                java.lang.String r4 = "MTRPlugin error: "
                r3.append(r4)     // Catch: java.lang.Throwable -> L66
                r3.append(r1)     // Catch: java.lang.Throwable -> L66
                java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L66
                r2.mo10116d(r1)     // Catch: java.lang.Throwable -> L66
                boolean r1 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r1 == 0) goto Ld2
            Lc7:
                com.networkbench.agent.impl.plugin.c.b r1 = com.networkbench.agent.impl.plugin.p272c.C6544b.this
                r1.f16891j = r0
                com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a
                java.lang.String r1 = "MTRPlugin NBSThreadExecutor.isExecutorServiceStop()"
                r0.mo10116d(r1)
            Ld2:
                com.networkbench.agent.impl.plugin.c.b r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.this
                com.networkbench.agent.impl.plugin.e.h r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9453d(r0)
                r0.m9370f()
                return
            Ldc:
                boolean r2 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r2 == 0) goto Led
                com.networkbench.agent.impl.plugin.c.b r2 = com.networkbench.agent.impl.plugin.p272c.C6544b.this
                r2.f16891j = r0
                com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.f16718a
                java.lang.String r2 = "MTRPlugin NBSThreadExecutor.isExecutorServiceStop()"
                r0.mo10116d(r2)
            Led:
                com.networkbench.agent.impl.plugin.c.b r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.this
                com.networkbench.agent.impl.plugin.e.h r0 = com.networkbench.agent.impl.plugin.p272c.C6544b.m9453d(r0)
                r0.m9370f()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.plugin.p272c.C6544b.RunnableC6545a.run():void");
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9300a(Map<String, Object> map) {
        if (this.f16889h.f15616d == null) {
            return;
        }
        try {
            this.f16720b = this.f16889h.f15616d;
            this.f16888g = this.f16720b.optString("host", "");
        } catch (Throwable unused) {
            this.f16888g = "";
        }
        try {
            this.f16722e = this.f16720b.optInt("count", 30);
            if (this.f16722e <= 0) {
                this.f16722e = 30;
            }
            if (this.f16722e > 100) {
                this.f16722e = 100;
            }
        } catch (Throwable unused2) {
            this.f16722e = 30;
        }
        if (this.f16888g.equals("$host")) {
            this.f16888g = (String) map.get("hostName");
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("host", new JsonPrimitive(this.f16888g == null ? "" : this.f16888g));
        C6547c c6547c = this.f16721c;
        if (c6547c != null && c6547c.f16727a.size() > 0) {
            String str = this.f16721c.f16727a.get(0).f16737a;
            jsonObject.add("ip", new JsonPrimitive(str == null ? "" : str));
            if (TextUtils.isEmpty(this.f16723o) && this.f16721c.f16727a.get(0).f16739c != null && this.f16721c.f16727a.get(0).f16739c.size() > 0) {
                this.f16723o = this.f16721c.f16727a.get(0).f16739c.get(0).f16729b;
            }
            String str2 = this.f16723o;
            if (str2 == null) {
                str2 = "";
            }
            jsonObject.add("dnsserver", new JsonPrimitive(str2));
            JsonArray jsonArray = new JsonArray();
            String str3 = "";
            for (C6547c.C6548a c6548a : this.f16721c.f16727a.get(0).f16739c) {
                jsonArray.add(c6548a.m9452a());
                str3 = c6548a.f16729b;
            }
            jsonObject.add("data", jsonArray);
            if (!TextUtils.isEmpty(str3) && str3.equals(str)) {
                this.f16724p = "";
            } else {
                this.f16724p = "unreachable to target host";
            }
        } else {
            jsonObject.add("ip", new JsonPrimitive(""));
            String str4 = this.f16723o;
            if (str4 == null) {
                str4 = "";
            }
            jsonObject.add("dnsserver", new JsonPrimitive(str4));
            jsonObject.add("data", new JsonArray());
        }
        jsonObject.add("error", new JsonPrimitive(m9455b(this.f16724p)));
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }

    /* renamed from: a */
    protected void m9458a(String str) {
        if (TextUtils.isEmpty(str)) {
        }
    }

    /* renamed from: b */
    public static String m9455b(String str) {
        return str != null ? (str.toLowerCase().contains("unknown host") || str.toLowerCase().contains("unable to resolve host")) ? "fail to resolve address" : (str.toLowerCase().contains("is unreachable") || str.toLowerCase().contains("timeout")) ? "unreachable to target host" : str : "";
    }
}
