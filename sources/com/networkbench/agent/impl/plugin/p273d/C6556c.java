package com.networkbench.agent.impl.plugin.p273d;

import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.C6567f;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.d.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6556c extends AbstractC6590h {

    /* renamed from: e */
    private static final int f16764e = 3;

    /* renamed from: a */
    JSONObject f16765a;

    /* renamed from: b */
    private int f16766b;

    /* renamed from: c */
    private C6555b f16767c;

    /* renamed from: d */
    private String f16768d;

    /* renamed from: o */
    private int f16769o;

    /* renamed from: p */
    private String f16770p;

    /* renamed from: q */
    private String f16771q;

    public C6556c(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        C6396h.m10137e("PingPlugin 注册ping监听 :");
        this.f16767c = new C6555b();
        this.f16770p = "";
        this.f16768d = "";
        this.f16769o = 56;
        this.f16766b = 3;
        this.f16890i = new C6567f(c6267d.f15616d, (String) this.f16894n.m9371e().get("hostName"));
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        C6369q.m10273a().m10272a(new RunnableC6557a());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.d.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6557a implements Runnable {
        RunnableC6557a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x00ef, code lost:
            if (com.networkbench.agent.impl.p252e.C6369q.m10270c() != false) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0172, code lost:
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 405
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.plugin.p273d.C6556c.RunnableC6557a.run():void");
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9300a(Map<String, Object> map) {
        InterfaceC6393e interfaceC6393e = f16887f;
        interfaceC6393e.mo10122a("action.argumentsJson" + this.f16889h.f15616d);
        if (this.f16889h.f15616d == null) {
            return;
        }
        try {
            this.f16765a = this.f16889h.f15616d;
            this.f16888g = this.f16765a.optString("host", "");
        } catch (Throwable unused) {
            this.f16888g = "";
        }
        try {
            this.f16766b = this.f16765a.optInt("count", 3);
            if (this.f16766b > 100) {
                this.f16766b = 100;
            }
            if (this.f16766b <= 0) {
                this.f16766b = 3;
            }
        } catch (Throwable unused2) {
            this.f16766b = 3;
        }
        try {
            this.f16769o = this.f16765a.optInt("payload", 56);
            if (this.f16769o < 16) {
                this.f16769o = 56;
            }
            if (this.f16769o > 65507) {
                this.f16769o = 65507;
            }
        } catch (Throwable unused3) {
            this.f16769o = 56;
        }
        if (this.f16888g.equals("$host")) {
            this.f16888g = (String) map.get("hostName");
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("host", new JsonPrimitive(this.f16888g == null ? "" : this.f16888g));
        String str = this.f16768d;
        if (str == null) {
            str = "";
        }
        jsonObject.add("ip", new JsonPrimitive(str));
        String str2 = this.f16770p;
        if (str2 == null) {
            str2 = "";
        }
        jsonObject.add("dnsserver", new JsonPrimitive(str2));
        jsonObject.add("successCount", new JsonPrimitive((Number) Integer.valueOf(m9393m())));
        jsonObject.add("failCount", new JsonPrimitive((Number) Integer.valueOf(m9392n() - m9393m())));
        jsonObject.add("sumTm", new JsonPrimitive((Number) Float.valueOf(m9387s())));
        jsonObject.add("min", new JsonPrimitive((Number) Float.valueOf(m9389q())));
        jsonObject.add("avg", new JsonPrimitive((Number) Float.valueOf(m9391o())));
        jsonObject.add("max", new JsonPrimitive((Number) Float.valueOf(m9390p())));
        jsonObject.add("stddev", new JsonPrimitive((Number) Double.valueOf(m9388r())));
        jsonObject.add("error", new JsonPrimitive(m9408a(this.f16771q)));
        jsonObject.add("data", m9394l());
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }

    /* renamed from: l */
    private JsonArray m9394l() {
        try {
            if (this.f16767c != null) {
                return this.f16767c.m9422g();
            }
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f16887f;
            interfaceC6393e.mo10116d("error getPingData:" + th.getMessage());
        }
        return new JsonArray();
    }

    /* renamed from: m */
    private int m9393m() {
        try {
            if (this.f16767c != null) {
                return this.f16767c.m9425d();
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: n */
    private int m9392n() {
        try {
            if (this.f16767c != null) {
                return this.f16767c.m9427c();
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: o */
    private float m9391o() {
        try {
            if (this.f16767c != null) {
                return Float.valueOf(this.f16767c.m9419j()).floatValue();
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    /* renamed from: p */
    private float m9390p() {
        try {
            if (this.f16767c != null) {
                return Float.valueOf(this.f16767c.m9417l()).floatValue();
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    /* renamed from: q */
    private float m9389q() {
        try {
            if (this.f16767c != null) {
                return Float.valueOf(this.f16767c.m9418k()).floatValue();
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    /* renamed from: r */
    private double m9388r() {
        try {
            if (this.f16767c != null) {
                String m9416m = this.f16767c.m9416m();
                return Math.round(Double.valueOf((m9416m == null || m9416m.equals("")) ? "0" : "0").doubleValue() * 1000.0d) / 1000.0d;
            }
            return 0.0d;
        } catch (Throwable unused) {
            return 0.0d;
        }
    }

    /* renamed from: s */
    private float m9387s() {
        try {
            if (this.f16767c != null) {
                return Float.valueOf(this.f16767c.m9421h()).floatValue();
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    /* renamed from: a */
    public static String m9408a(String str) {
        return str != null ? (str.toLowerCase().contains("unknown host") || str.toLowerCase().contains("unable to resolve host")) ? "fail to resolve address" : str.toLowerCase().contains("is unreachable") ? "timeout" : str : "";
    }
}
