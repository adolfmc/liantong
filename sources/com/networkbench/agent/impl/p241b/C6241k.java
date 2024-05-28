package com.networkbench.agent.impl.p241b;

import android.content.Context;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.C6325e;
import com.networkbench.agent.impl.crash.C6332j;
import com.networkbench.agent.impl.crash.InterfaceC6329h;
import com.networkbench.agent.impl.crash.RunnableC6310a;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p262i.EnumC6467d;
import com.networkbench.agent.impl.plugin.p274e.C6559a;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import com.networkbench.agent.impl.util.C6636f;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonParseException;
import com.networkbench.com.google.gson.JsonParser;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6241k {

    /* renamed from: c */
    private static C6241k f15457c = null;

    /* renamed from: d */
    private static final String f15458d = "NBSAnrStore";

    /* renamed from: a */
    ExecutorService f15460a = Executors.newSingleThreadExecutor();

    /* renamed from: e */
    private InterfaceC6329h f15461e;

    /* renamed from: g */
    private Context f15462g;

    /* renamed from: b */
    private static final InterfaceC6393e f15456b = C6394f.m10150a();

    /* renamed from: f */
    private static final AtomicBoolean f15459f = new AtomicBoolean(false);

    /* renamed from: a */
    public static C6241k m10902a() {
        if (f15457c == null) {
            f15457c = new C6241k(C6638h.m8963w().m9076K());
        }
        return f15457c;
    }

    private C6241k(Context context) {
        if (context != null) {
            this.f15461e = new C6332j(context, "NBSAnrStore");
        }
        this.f15462g = context;
    }

    /* renamed from: c */
    private void m10893c(C6239i c6239i) {
        this.f15461e.mo10375a(c6239i.m10912c(), c6239i.asJson().toString());
    }

    /* renamed from: a */
    public void m10900a(C6239i c6239i, boolean z) {
        f15456b.mo10122a("report anr start");
        C6325e.m10395b(C6636f.m9093b(c6239i.m10912c()));
        if (C6565g.f16794f) {
            C6559a c6559a = new C6559a(c6239i, z);
            C6565g.m9380a(c6559a);
            c6559a.m9372d();
            return;
        }
        m10894b(c6239i, z);
    }

    /* renamed from: b */
    public void m10894b(C6239i c6239i, boolean z) {
        JsonObject m10897a = m10897a(c6239i.asJsonArray().toString(), C6638h.m8963w().m9075L());
        if (HarvestConnection.isSoDisable()) {
            try {
                m10899a(new RunnableC6227a(m10897a.toString(), this.f15461e, c6239i.m10912c(), C6640i.f17186a, EnumC6467d.ANR_DATA, ""), z);
                return;
            } catch (Exception e) {
                f15456b.mo10121a("error reportAnr", e);
                return;
            }
        }
        m10899a(new RunnableC6310a(m10897a, this.f15461e, c6239i.m10912c(), EnumC6467d.ANR_DATA.m9915a(), C6638h.m8963w().m8964v()), z);
    }

    /* renamed from: b */
    public void m10896b() {
        try {
            if (this.f15462g == null) {
                f15456b.mo10122a("user close anr report ");
            } else if (!Harvest.isAnr_enabled()) {
                InterfaceC6393e interfaceC6393e = f15456b;
                interfaceC6393e.mo10115e("ANR_enabled() is " + Harvest.isAnr_enabled() + ",stop report ANR!");
            } else {
                int i = 0;
                if (!f15459f.compareAndSet(false, true)) {
                    f15459f.set(true);
                    return;
                }
                Map<String, ?> mo10374b = this.f15461e.mo10374b();
                if (mo10374b != null) {
                    InterfaceC6393e interfaceC6393e2 = f15456b;
                    interfaceC6393e2.mo10122a("report all stored anr ,anrStore size is " + mo10374b.size());
                    for (Map.Entry<String, ?> entry : mo10374b.entrySet()) {
                        if (C6325e.m10397a(entry.getKey())) {
                            InterfaceC6393e interfaceC6393e3 = f15456b;
                            interfaceC6393e3.mo10122a("Anr has reported, timestamp is " + entry.getKey());
                            return;
                        }
                        String m9092c = C6636f.m9092c((String) entry.getValue());
                        if (m9092c != null) {
                            try {
                                JsonObject m10897a = m10897a(m9092c, C6638h.m8963w().m9075L());
                                if (HarvestConnection.isSoDisable()) {
                                    this.f15460a.execute(new RunnableC6227a(m10897a.toString(), this.f15461e, C6636f.m9092c(entry.getKey()), C6640i.f17186a, EnumC6467d.ANR_DATA, ""));
                                } else {
                                    this.f15460a.execute(new RunnableC6310a(m10897a, this.f15461e, C6636f.m9092c(entry.getKey()), EnumC6467d.ANR_DATA.m9915a(), C6638h.m8963w().m8964v()));
                                }
                                C6325e.m10395b(entry.getKey());
                                InterfaceC6393e interfaceC6393e4 = f15456b;
                                StringBuilder sb = new StringBuilder();
                                sb.append("ThreadPool submit store Anr report Runnable ,Anr num is ");
                                i++;
                                sb.append(i);
                                interfaceC6393e4.mo10122a(sb.toString());
                            } catch (JsonParseException unused) {
                                f15456b.mo10115e("invaild json str for anr");
                                this.f15461e.mo10376a(entry.getKey());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            f15456b.mo10121a("Exception occur while send stored anr", e);
        }
    }

    /* renamed from: a */
    private void m10899a(Runnable runnable, boolean z) {
        this.f15460a.execute(runnable);
    }

    /* renamed from: a */
    public void m10901a(C6239i c6239i) {
        try {
            if (Harvest.isAnr_enabled()) {
                f15457c.m10900a(c6239i, false);
            }
        } catch (Exception e) {
            f15456b.mo10121a("report failed:", e);
        }
    }

    /* renamed from: a */
    public JsonObject m10897a(String str, String str2) throws JsonParseException {
        if (str == null) {
            throw new IllegalArgumentException("anr message error");
        }
        JsonObject jsonObject = new JsonObject();
        JsonArray asJsonArray = new JsonParser().parse(str).getAsJsonArray();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(asJsonArray);
        jsonObject.add("data", jsonArray);
        if (TextUtils.isEmpty(str2)) {
            if (HarvestConnection.isSoDisable()) {
                jsonObject.addProperty("did", NBSAgent.getImpl().m9148o());
                jsonObject.add("dev", NBSAgent.getDeviceInformation().asJsonArray());
                jsonObject.add("app", NBSAgent.getApplicationInformation().asJsonArray());
            } else {
                jsonObject.addProperty("did", NBSAgent.getImpl().m9148o());
                jsonObject.add("dev", NBSAgent.getDeviceInformation().asSocketJsonArray());
                jsonObject.add("app", NBSAgent.getApplicationInformation().asSocketJsonArray());
            }
        }
        return jsonObject;
    }

    /* renamed from: a */
    private String m10898a(String str) {
        return new JsonParser().parse(str).getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsString();
    }

    /* renamed from: b */
    public void m10895b(C6239i c6239i) {
        m10893c(c6239i);
    }
}
