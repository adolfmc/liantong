package com.networkbench.agent.impl.p243c.p247d;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p244a.C6258i;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6273e extends HarvestableArray {

    /* renamed from: a */
    public static C6270b f15637a;

    /* renamed from: c */
    private int f15639c;

    /* renamed from: d */
    private String f15640d;

    /* renamed from: e */
    private long f15641e;

    /* renamed from: f */
    private int f15642f;

    /* renamed from: h */
    private C6258i f15644h;

    /* renamed from: b */
    public long f15638b = 10000;

    /* renamed from: g */
    private C6270b f15643g = C6255f.f15554c;

    /* renamed from: a */
    public C6258i m10707a() {
        return this.f15644h;
    }

    /* renamed from: b */
    public int m10706b() {
        return this.f15642f;
    }

    public C6273e(int i, String str, C6258i c6258i) {
        this.f15639c = i;
        this.f15640d = str;
        this.f15644h = c6258i;
        this.f15641e = c6258i.f15574f.m10754d();
    }

    /* renamed from: c */
    public String m10705c() {
        return this.f15640d;
    }

    /* renamed from: g */
    private int m10701g() {
        int calcState = Harvest.getInstance().getConfiguration().calcState(this.f15641e, this.f15644h.f15574f.m10757c());
        this.f15642f = calcState;
        return calcState;
    }

    /* renamed from: h */
    private boolean m10700h() {
        return this.f15642f > 0;
    }

    /* renamed from: i */
    private String m10699i() {
        return m10700h() ? C6653u.m8751a(C6638h.m8963w().m9076K(), false) : "";
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15639c)));
        jsonArray.add(new JsonPrimitive(this.f15640d));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15641e)));
        C6258i c6258i = this.f15644h;
        if (c6258i != null) {
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(c6258i.f15574f.m10757c())));
        } else {
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15641e)));
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m10701g())));
        jsonArray.add(new JsonPrimitive(m10703e()));
        jsonArray.add(new JsonPrimitive(m10699i()));
        if (this.f15644h != null && m10700h()) {
            jsonArray.add(new JsonPrimitive(m10704d().toString()));
        } else {
            jsonArray.add(new JsonPrimitive(""));
        }
        return jsonArray;
    }

    /* renamed from: d */
    public JsonObject m10704d() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cpu", new JsonArray());
        jsonObject.add("mem", new JsonArray());
        jsonObject.add("stacks", this.f15644h.asJson());
        return jsonObject;
    }

    /* renamed from: e */
    public String m10703e() {
        C6270b c6270b = this.f15643g;
        return c6270b == null ? "" : c6270b.toJsonString();
    }

    /* renamed from: f */
    public boolean m10702f() {
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("pageLoadTime:" + this.f15641e + ", maxDurationTime:" + this.f15638b);
        return this.f15641e > this.f15638b;
    }
}
