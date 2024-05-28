package com.networkbench.agent.impl.plugin.p275f;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.C6567f;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import com.networkbench.agent.impl.plugin.p275f.p276a.C6578b;
import com.networkbench.agent.impl.plugin.p275f.p276a.C6584e;
import com.networkbench.agent.impl.plugin.p275f.p276a.C6587f;
import com.networkbench.agent.impl.plugin.p275f.p276a.p277a.C6576c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.net.InetAddress;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6568a extends AbstractC6590h {

    /* renamed from: e */
    private static final int f16805e = 1;

    /* renamed from: o */
    private static final String f16806o = "80";

    /* renamed from: a */
    JSONObject f16807a;

    /* renamed from: b */
    private int f16808b;

    /* renamed from: c */
    private String f16809c;

    /* renamed from: d */
    private String f16810d;

    /* renamed from: p */
    private int f16811p;

    /* renamed from: q */
    private int f16812q;

    /* renamed from: r */
    private int f16813r;

    /* renamed from: s */
    private float f16814s;

    /* renamed from: t */
    private String f16815t;

    /* renamed from: u */
    private String f16816u;

    /* renamed from: c */
    static /* synthetic */ String m9357c(C6568a c6568a, String str) {
        c6568a.f16815t = str;
        return str;
    }

    /* renamed from: d */
    static /* synthetic */ String m9356d(C6568a c6568a) {
        return c6568a.f16815t;
    }

    /* renamed from: h */
    static /* synthetic */ InterfaceC6393e m9351h() {
        return f16887f;
    }

    /* renamed from: i */
    static /* synthetic */ InterfaceC6393e m9350i() {
        return f16887f;
    }

    public C6568a(AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(HarvestableType.OBJECT, abstractC6566h, c6267d);
        this.f16811p = 80;
        this.f16815t = "";
        this.f16890i = new C6567f(c6267d.f15616d, (String) this.f16894n.m9371e().get("hostName"));
        mo9300a(abstractC6566h.m9371e());
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9301a() {
        C6369q.m10273a().m10272a(new RunnableC6569a());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC6569a implements Runnable {
        RunnableC6569a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!TextUtils.isEmpty(C6568a.this.f16888g)) {
                    InetAddress.getByName(C6568a.this.f16888g);
                    C6568a.this.f16816u = C6568a.this.m9312a(C6638h.m8963w().m9076K());
                    InterfaceC6393e interfaceC6393e = C6568a.f16887f;
                    interfaceC6393e.mo10122a("tcpping dnsServer:" + C6568a.this.f16816u);
                    C6584e.m9323a(C6568a.this.f16888g, C6568a.this.f16811p, C6568a.this.f16808b, new C6587f(), new C6584e.InterfaceC6585a() { // from class: com.networkbench.agent.impl.plugin.f.a.a.1
                        @Override // com.networkbench.agent.impl.plugin.p275f.p276a.C6584e.InterfaceC6585a
                        /* renamed from: a */
                        public void mo9318a(C6584e.C6586b c6586b) {
                            if (c6586b != null) {
                                InterfaceC6393e interfaceC6393e2 = C6568a.f16887f;
                                interfaceC6393e2.mo10122a("tcpping complete result:" + c6586b.toString());
                                C6568a.this.f16809c = c6586b.f16876b;
                                C6568a.this.f16812q = c6586b.f16881g - c6586b.f16882h;
                                C6568a.this.f16813r = c6586b.f16882h;
                                C6568a.this.f16814s = c6586b.f16879e;
                                C6568a.this.f16815t = c6586b.f16883i;
                                return;
                            }
                            C6568a.this.f16891j = false;
                        }
                    });
                    if (C6653u.m8688m(C6568a.this.f16888g)) {
                        C6568a.this.f16810d = "";
                    } else {
                        try {
                            C6578b.m9327a(C6568a.this.f16888g, new C6587f(), new C6578b.InterfaceC6580a() { // from class: com.networkbench.agent.impl.plugin.f.a.a.2
                                @Override // com.networkbench.agent.impl.plugin.p275f.p276a.C6578b.InterfaceC6580a
                                /* renamed from: a */
                                public void mo9325a(C6578b.C6581b c6581b) {
                                    C6576c[] c6576cArr;
                                    if (c6581b == null || c6581b.f16862c == null) {
                                        C6568a.this.f16891j = false;
                                        return;
                                    }
                                    for (C6576c c6576c : c6581b.f16862c) {
                                        if (c6576c.f16851d == 5) {
                                            C6568a.this.f16810d = c6576c.f16850c;
                                        }
                                    }
                                }
                            });
                        } catch (Exception unused) {
                            C6568a.this.f16810d = "";
                        }
                    }
                    C6568a.this.f16891j = true;
                }
            } finally {
                if (C6369q.m10270c()) {
                    C6568a.this.f16891j = false;
                }
                C6568a.this.f16894n.m9370f();
            }
        }
    }

    @Override // com.networkbench.agent.impl.plugin.AbstractC6590h
    /* renamed from: a */
    public void mo9300a(Map<String, Object> map) {
        if (this.f16889h.f15616d == null) {
            return;
        }
        try {
            this.f16807a = this.f16889h.f15616d;
            this.f16888g = this.f16807a.optString("host", "");
        } catch (Throwable unused) {
            this.f16888g = "";
        }
        try {
            this.f16808b = this.f16807a.optInt("repeat", 1);
        } catch (Throwable unused2) {
            this.f16808b = 1;
        }
        if (this.f16888g.equals("$host")) {
            this.f16888g = (String) map.get("hostName");
        }
        if (this.f16807a.optString("port").equals("$port")) {
            Object obj = map.get("port");
            if (obj != null) {
                this.f16811p = ((Integer) obj).intValue();
                return;
            }
            return;
        }
        try {
            this.f16811p = Integer.parseInt(this.f16807a.optString("port", "80"));
        } catch (Throwable unused3) {
            this.f16811p = 80;
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("host", new JsonPrimitive(this.f16888g == null ? "" : this.f16888g));
        jsonObject.add("port", new JsonPrimitive(String.valueOf(this.f16811p)));
        String str = this.f16809c;
        if (str == null) {
            str = "";
        }
        jsonObject.add("ip", new JsonPrimitive(str));
        String str2 = this.f16816u;
        if (str2 == null) {
            str2 = "";
        }
        jsonObject.add("dnsserver", new JsonPrimitive(str2));
        String str3 = this.f16810d;
        if (str3 == null) {
            str3 = "";
        }
        jsonObject.add("cname", new JsonPrimitive(str3));
        jsonObject.add("successCount", new JsonPrimitive((Number) Integer.valueOf(this.f16812q)));
        jsonObject.add("failCount", new JsonPrimitive((Number) Integer.valueOf(this.f16813r)));
        jsonObject.add("sumTm", new JsonPrimitive((Number) Float.valueOf(this.f16814s)));
        jsonObject.add("error", new JsonPrimitive(m9362a(this.f16815t)));
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("result", jsonObject);
        return jsonObject2;
    }

    /* renamed from: a */
    public static String m9362a(String str) {
        InterfaceC6393e interfaceC6393e = f16887f;
        interfaceC6393e.mo10122a("getErrrData raw errorDesc:" + str);
        return str != null ? (str.toLowerCase().contains("unknown host") || str.toLowerCase().contains("unable to resolve host")) ? "fail to resolve address" : (str.toLowerCase().contains("is unreachable") || str.toLowerCase().contains("failed to connect to") || !str.equals("")) ? "timeout" : "" : "";
    }
}
