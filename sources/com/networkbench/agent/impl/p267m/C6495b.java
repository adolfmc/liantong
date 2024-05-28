package com.networkbench.agent.impl.p267m;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p266l.C6493c;
import com.networkbench.agent.impl.util.C6636f;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonParser;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6495b extends HarvestableArray {

    /* renamed from: a */
    private static final String f16440a = "event_data_store";

    /* renamed from: b */
    private static C6495b f16441b;

    /* renamed from: c */
    private Map<Long, C6494a> f16442c = new ConcurrentHashMap();

    /* renamed from: d */
    private Map<String, String> f16443d = new ConcurrentHashMap();

    /* renamed from: e */
    private InterfaceC6393e f16444e = C6394f.m10150a();

    /* renamed from: f */
    private C6493c f16445f;

    private C6495b() {
        m9755f();
    }

    /* renamed from: a */
    public static C6495b m9761a() {
        if (f16441b == null) {
            synchronized (C6396h.class) {
                if (f16441b == null) {
                    f16441b = new C6495b();
                }
            }
        }
        return f16441b;
    }

    /* renamed from: f */
    private void m9755f() {
        if (this.f16445f != null || C6638h.m8963w().m9076K() == null) {
            return;
        }
        this.f16445f = new C6493c(C6638h.m8963w().m9076K(), "event_data_store");
    }

    /* renamed from: a */
    public void m9760a(String str, String str2, Map<String, Object> map) {
        try {
            if (C6459b.m9935e()) {
                C6396h.m10125q(" onEvent  : " + str);
                if (!C6653u.m8689l(str)) {
                    InterfaceC6393e interfaceC6393e = this.f16444e;
                    interfaceC6393e.mo10115e("The eventId is invalid!" + str);
                    if (C6638h.m8963w().m8979n()) {
                        C6396h.m10140b("The eventId is invalid!" + str, new Object[0]);
                        return;
                    }
                    return;
                }
                if (map != null) {
                    for (String str3 : map.keySet()) {
                        if (!C6653u.m8689l(str3)) {
                            InterfaceC6393e interfaceC6393e2 = this.f16444e;
                            interfaceC6393e2.mo10115e("The eventMap key " + str3 + " is invalid!");
                            if (C6638h.m8963w().m8979n()) {
                                C6396h.m10140b("The eventMap key " + str3 + " is invalid!", new Object[0]);
                                return;
                            }
                            return;
                        }
                    }
                    for (Object obj : map.values()) {
                        if (!C6653u.m8727b(obj)) {
                            if (C6638h.m8963w().m8979n()) {
                                C6396h.m10140b("The eventMap value is invalid!", new Object[0]);
                            }
                            this.f16444e.mo10115e("The eventMap value is invalid!");
                            return;
                        }
                    }
                }
                C6494a c6494a = new C6494a(str, str2, map);
                if (this.f16445f != null) {
                    this.f16445f.m9773a(c6494a.asJson().toString(), new Random().nextLong() + System.currentTimeMillis());
                    m9759b();
                    return;
                }
                m9755f();
                this.f16442c.put(Long.valueOf(new Random().nextLong() + System.currentTimeMillis()), c6494a);
            }
        } catch (Throwable unused) {
            C6458a.m9944a(C6458a.f16324c, 0);
        }
    }

    /* renamed from: b */
    protected void m9759b() {
        for (Map.Entry<Long, C6494a> entry : this.f16442c.entrySet()) {
            this.f16445f.m9773a(entry.getValue().asJson().toString(), entry.getKey().longValue());
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        Map<String, String> map = this.f16443d;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : this.f16443d.entrySet()) {
                jsonArray.add(new JsonParser().parse(entry.getValue()).getAsJsonArray());
            }
        }
        return jsonArray;
    }

    /* renamed from: c */
    public void m9758c() {
        C6493c c6493c = this.f16445f;
        if (c6493c != null) {
            Map<String, ?> m9776a = c6493c.m9776a();
            if (m9776a == null || m9776a.size() <= 0) {
                return;
            }
            for (Map.Entry<String, ?> entry : m9776a.entrySet()) {
                String str = (String) entry.getValue();
                String key = entry.getKey();
                if (!TextUtils.isEmpty(str)) {
                    if (this.f16443d.size() >= 100) {
                        return;
                    }
                    this.f16443d.put(C6636f.m9092c(key), C6636f.m9092c(str));
                }
            }
            return;
        }
        this.f16444e.mo10116d("error pluginSaveProcess == null, please check");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m9757d() {
        Map<String, String> map;
        if (this.f16445f == null || (map = this.f16443d) == null || map.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry : this.f16443d.entrySet()) {
            this.f16445f.m9774a(entry.getKey());
        }
        this.f16443d.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public boolean m9756e() {
        Map<String, ?> m9776a;
        C6493c c6493c = this.f16445f;
        return c6493c == null || (m9776a = c6493c.m9776a()) == null || m9776a.size() <= 0;
    }
}
