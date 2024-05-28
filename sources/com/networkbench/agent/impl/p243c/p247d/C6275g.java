package com.networkbench.agent.impl.p243c.p247d;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6275g extends HarvestableArray {

    /* renamed from: a */
    public static C6270b f15646a;

    /* renamed from: b */
    public long f15647b;

    /* renamed from: c */
    private int f15648c;

    /* renamed from: d */
    private String f15649d;

    /* renamed from: e */
    private long f15650e;

    /* renamed from: f */
    private int f15651f;

    /* renamed from: g */
    private C6270b f15652g;

    /* renamed from: h */
    private C6293k f15653h;

    /* renamed from: a */
    public C6293k m10695a() {
        return this.f15653h;
    }

    /* renamed from: b */
    public int m10694b() {
        return this.f15651f;
    }

    public C6275g(int i, String str, C6293k c6293k) {
        this.f15647b = 10000L;
        this.f15648c = i;
        this.f15649d = str;
        this.f15653h = c6293k;
        this.f15650e = c6293k.m10543p();
        this.f15652g = C6255f.f15554c;
    }

    public C6275g(int i, long j, long j2, C6270b c6270b, C6293k c6293k) {
        this.f15647b = 10000L;
        this.f15648c = i;
        this.f15649d = "OverLapPage";
        this.f15650e = j;
        this.f15652g = c6270b;
        this.f15653h = c6293k;
    }

    public C6275g(C6275g c6275g) {
        this.f15647b = 10000L;
        this.f15648c = 3;
        this.f15649d = "OverLapPage";
        this.f15650e = c6275g.f15650e;
        this.f15652g = new C6270b(C6255f.f15554c);
        this.f15653h = c6275g.f15653h;
    }

    /* renamed from: c */
    public C6270b m10693c() {
        return this.f15652g;
    }

    /* renamed from: d */
    public String m10692d() {
        return this.f15649d;
    }

    /* renamed from: k */
    private int m10685k() {
        int calcState = Harvest.getInstance().getConfiguration().calcState(this.f15650e, this.f15653h.m10542q());
        this.f15651f = calcState;
        return calcState;
    }

    /* renamed from: l */
    private boolean m10684l() {
        return this.f15651f > 0;
    }

    /* renamed from: m */
    private String m10683m() {
        return m10684l() ? C6653u.m8751a(C6638h.m8963w().m9076K(), false) : "";
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15648c)));
        jsonArray.add(new JsonPrimitive(this.f15649d));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15650e)));
        C6293k c6293k = this.f15653h;
        if (c6293k != null) {
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(c6293k.m10542q())));
        } else {
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15650e)));
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m10685k())));
        jsonArray.add(new JsonPrimitive(m10691e()));
        jsonArray.add(new JsonPrimitive(m10683m()));
        if (this.f15653h != null && m10684l()) {
            if (this.f15648c == 3) {
                jsonArray.add(new JsonPrimitive(this.f15653h.m10547l().toString()));
            } else {
                jsonArray.add(new JsonPrimitive(this.f15653h.asJson().toString()));
            }
        } else {
            jsonArray.add(new JsonPrimitive(""));
        }
        return jsonArray;
    }

    /* renamed from: e */
    public String m10691e() {
        C6270b c6270b = this.f15652g;
        return c6270b == null ? "" : c6270b.toJsonString();
    }

    /* renamed from: f */
    public void m10690f() {
        if (this.f15653h.f15745e != null) {
            this.f15652g.m10721b(this.f15653h.f15745e);
            this.f15652g.f15627a = "setCustomPageName";
            return;
        }
        this.f15652g.m10721b(this.f15649d);
    }

    /* renamed from: g */
    public long m10689g() {
        return this.f15653h.m10563b();
    }

    /* renamed from: h */
    public long m10688h() {
        return this.f15653h.m10559c();
    }

    /* renamed from: i */
    public long m10687i() {
        return this.f15653h.m10569a();
    }

    /* renamed from: j */
    public boolean m10686j() {
        return this.f15650e > this.f15647b;
    }
}
