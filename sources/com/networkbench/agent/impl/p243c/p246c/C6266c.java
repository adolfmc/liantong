package com.networkbench.agent.impl.p243c.p246c;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.C6332j;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6636f;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonParser;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.c.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6266c extends HarvestableObject {

    /* renamed from: a */
    public final List<HarvestableObject> f15611a = new CopyOnWriteArrayList();

    /* renamed from: b */
    private C6332j f15612b;

    /* renamed from: a */
    public void m10744a(C6265b c6265b) {
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("NBSPluginDatas addPlugindata" + c6265b.asJsonObject().toString());
        this.f15611a.add(c6265b);
    }

    /* renamed from: e */
    private JsonArray m10739e() {
        JsonArray jsonArray = new JsonArray();
        for (HarvestableObject harvestableObject : this.f15611a) {
            jsonArray.add(harvestableObject.asJson());
        }
        return jsonArray;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public synchronized JsonObject asJsonObject() {
        JsonObject jsonObject;
        jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("taskData"));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJsonArray());
        jsonObject.add("data", m10739e());
        C6396h.m10137e("NBSPluginDatas : " + jsonObject.toString());
        return jsonObject;
    }

    /* renamed from: a */
    public JsonObject m10745a() throws C6632b {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("taskData"));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJsonArray());
        jsonObject.add("data", m10738f());
        return jsonObject;
    }

    /* renamed from: f */
    private JsonArray m10738f() throws C6632b {
        m10737g();
        List<String> mo10377a = this.f15612b.mo10377a();
        if (mo10377a.size() <= 0) {
            throw new C6632b("not exist store crash task");
        }
        return m10743a(mo10377a);
    }

    /* renamed from: g */
    private void m10737g() {
        if (this.f15612b == null) {
            this.f15612b = new C6332j(C6638h.m8963w().m9076K(), "crashSceneInfo");
        }
    }

    /* renamed from: a */
    JsonArray m10743a(List<String> list) {
        JsonArray jsonArray = new JsonArray();
        for (String str : list) {
            try {
                String m9092c = C6636f.m9092c(str);
                InterfaceC6393e interfaceC6393e = C6638h.f17124y;
                interfaceC6393e.mo10122a("crashTask in sp:" + m9092c);
                jsonArray.addAll(new JsonParser().parse(m9092c).getAsJsonArray());
            } catch (Throwable th) {
                C6638h.f17124y.mo10121a("error when get crash task in sp:", th);
            }
        }
        return jsonArray;
    }

    /* renamed from: b */
    public void m10742b() {
        m10737g();
        this.f15612b.mo10369d();
    }

    /* renamed from: c */
    public void m10741c() {
        this.f15611a.clear();
    }

    /* renamed from: d */
    public int m10740d() {
        return this.f15611a.size();
    }
}
