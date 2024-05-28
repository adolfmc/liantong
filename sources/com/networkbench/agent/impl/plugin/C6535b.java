package com.networkbench.agent.impl.plugin;

import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6535b extends AbstractC6590h {

    /* renamed from: a */
    String f16688a;

    /* renamed from: b */
    String f16689b;

    /* renamed from: c */
    String f16690c;

    /* renamed from: d */
    long f16691d;

    /* renamed from: e */
    JSONObject f16692e;

    public C6535b(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        try {
            this.f16688a = "";
            this.f16689b = "";
            this.f16690c = "";
            this.f16890i = new C6567f(c6267d.f15616d, (String) this.f16894n.m9371e().get("hostName"));
            mo9300a(abstractC6566h.m9371e());
        } catch (Throwable unused) {
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        C6369q.m10273a().m10272a(new RunnableC6536a());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6536a implements Runnable {
        RunnableC6536a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x00bd, code lost:
            if (com.networkbench.agent.impl.p252e.C6369q.m10270c() == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x00c0, code lost:
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
                com.networkbench.agent.impl.plugin.b r2 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r2 = r2.f16888g     // Catch: java.lang.Throwable -> L89
                boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L89
                if (r2 != 0) goto L75
                com.networkbench.agent.impl.plugin.b r2 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r2 = r2.f16888g     // Catch: java.lang.Throwable -> L89
                boolean r2 = com.networkbench.agent.impl.util.C6653u.m8688m(r2)     // Catch: java.lang.Throwable -> L89
                if (r2 != 0) goto L75
                com.networkbench.agent.impl.plugin.b r2 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r2 = r2.f16888g     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.f.a.f r3 = new com.networkbench.agent.impl.plugin.f.a.f     // Catch: java.lang.Throwable -> L89
                r3.<init>()     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b$a$1 r4 = new com.networkbench.agent.impl.plugin.b$a$1     // Catch: java.lang.Throwable -> L89
                r4.<init>()     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.p275f.p276a.C6578b.m9327a(r2, r3, r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.f.e r2 = com.networkbench.agent.impl.plugin.AbstractC6590h.f16887f     // Catch: java.lang.Throwable -> L89
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
                r3.<init>()     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = "dns plugin dnsHostName:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r4 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = r4.f16888g     // Catch: java.lang.Throwable -> L89
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = "  cname:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r4 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = r4.f16688a     // Catch: java.lang.Throwable -> L89
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = ", ip:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r4 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = r4.f16689b     // Catch: java.lang.Throwable -> L89
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = ", dnsTime:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r4 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                long r4 = r4.f16691d     // Catch: java.lang.Throwable -> L89
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = ", errorDesc:"
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r4 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                java.lang.String r4 = r4.f16690c     // Catch: java.lang.Throwable -> L89
                r3.append(r4)     // Catch: java.lang.Throwable -> L89
                java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L89
                r2.mo10122a(r3)     // Catch: java.lang.Throwable -> L89
                com.networkbench.agent.impl.plugin.b r2 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L89
                r2.f16891j = r0     // Catch: java.lang.Throwable -> L89
            L75:
                boolean r0 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r0 == 0) goto L7f
            L7b:
                com.networkbench.agent.impl.plugin.b r0 = com.networkbench.agent.impl.plugin.C6535b.this
                r0.f16891j = r1
            L7f:
                com.networkbench.agent.impl.plugin.b r0 = com.networkbench.agent.impl.plugin.C6535b.this
                com.networkbench.agent.impl.plugin.e.h r0 = r0.f16894n
                r0.m9370f()
                goto Lc0
            L87:
                r0 = move-exception
                goto Lc1
            L89:
                r2 = move-exception
                boolean r3 = r2 instanceof java.lang.InterruptedException     // Catch: java.lang.Throwable -> L87
                if (r3 == 0) goto L93
                com.networkbench.agent.impl.plugin.b r0 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L87
                r0.f16891j = r1     // Catch: java.lang.Throwable -> L87
                goto Lb9
            L93:
                com.networkbench.agent.impl.plugin.b r3 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L87
                java.lang.String r4 = r2.getMessage()     // Catch: java.lang.Throwable -> L87
                r3.f16690c = r4     // Catch: java.lang.Throwable -> L87
                com.networkbench.agent.impl.plugin.b r3 = com.networkbench.agent.impl.plugin.C6535b.this     // Catch: java.lang.Throwable -> L87
                r3.f16891j = r0     // Catch: java.lang.Throwable -> L87
                com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.plugin.AbstractC6590h.f16887f     // Catch: java.lang.Throwable -> L87
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
                r3.<init>()     // Catch: java.lang.Throwable -> L87
                java.lang.String r4 = "dnsRnnable error: "
                r3.append(r4)     // Catch: java.lang.Throwable -> L87
                java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L87
                r3.append(r2)     // Catch: java.lang.Throwable -> L87
                java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> L87
                r0.mo10115e(r2)     // Catch: java.lang.Throwable -> L87
            Lb9:
                boolean r0 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r0 == 0) goto L7f
                goto L7b
            Lc0:
                return
            Lc1:
                boolean r2 = com.networkbench.agent.impl.p252e.C6369q.m10270c()
                if (r2 == 0) goto Lcb
                com.networkbench.agent.impl.plugin.b r2 = com.networkbench.agent.impl.plugin.C6535b.this
                r2.f16891j = r1
            Lcb:
                com.networkbench.agent.impl.plugin.b r1 = com.networkbench.agent.impl.plugin.C6535b.this
                com.networkbench.agent.impl.plugin.e.h r1 = r1.f16894n
                r1.m9370f()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.plugin.C6535b.RunnableC6536a.run():void");
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    protected void mo9300a(Map<String, Object> map) {
        try {
            this.f16692e = this.f16889h.f15616d;
            this.f16888g = this.f16692e.optString("host");
            if (this.f16888g.equals("$host")) {
                this.f16888g = (String) this.f16894n.m9371e().get("hostName");
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        new JsonObject().add("host", new JsonPrimitive(this.f16888g == null ? "" : this.f16888g));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("host", new JsonPrimitive(this.f16888g == null ? "" : this.f16888g));
        String str = this.f16689b;
        if (str == null) {
            str = "";
        }
        jsonObject.add("ip", new JsonPrimitive(str));
        String str2 = this.f16688a;
        if (str2 == null) {
            str2 = "";
        }
        jsonObject.add("cname", new JsonPrimitive(str2));
        jsonObject.add("tm", new JsonPrimitive((Number) Long.valueOf(this.f16691d)));
        String str3 = this.f16690c;
        if (str3 == null) {
            str3 = "";
        }
        jsonObject.add("err", new JsonPrimitive(str3));
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }
}
