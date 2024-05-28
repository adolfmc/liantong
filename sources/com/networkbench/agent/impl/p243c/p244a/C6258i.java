package com.networkbench.agent.impl.p243c.p244a;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6253e;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6258i extends HarvestableArray {

    /* renamed from: d */
    public long f15572d;

    /* renamed from: e */
    public long f15573e;

    /* renamed from: f */
    public C6260k f15574f;

    /* renamed from: g */
    public String f15575g;

    /* renamed from: h */
    long f15576h;

    /* renamed from: j */
    private long f15578j;

    /* renamed from: l */
    private int f15579l;

    /* renamed from: n */
    private int f15581n;

    /* renamed from: o */
    private int f15582o;

    /* renamed from: r */
    private String f15585r;

    /* renamed from: a */
    public C6258i f15569a = null;

    /* renamed from: b */
    public C6258i f15570b = null;

    /* renamed from: c */
    public Map<String, Object> f15571c = new ConcurrentHashMap();

    /* renamed from: i */
    private String f15577i = "";

    /* renamed from: p */
    private int f15583p = 0;

    /* renamed from: q */
    private int f15584q = 0;

    /* renamed from: m */
    private int f15580m = 0;

    /* renamed from: a */
    public void m10787a(C6258i c6258i) {
        this.f15570b = c6258i;
    }

    /* renamed from: a */
    public void m10785a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f15577i = str;
    }

    /* renamed from: a */
    public void m10789a(long j) {
        this.f15572d = j;
        this.f15574f.m10764a(j);
        this.f15578j = j - this.f15573e;
    }

    public C6258i(long j, int i, String str) {
        this.f15573e = j;
        long j2 = i;
        this.f15572d = j2;
        this.f15575g = str;
        this.f15574f = new C6260k(j, j2, str);
    }

    /* renamed from: a */
    public long m10790a() {
        return this.f15578j;
    }

    /* renamed from: a */
    public void m10786a(C6258i c6258i, C6258i c6258i2) {
        C6258i c6258i3 = c6258i.f15569a;
        if (c6258i3 == null) {
            c6258i.f15569a = c6258i2;
            c6258i2.m10787a(c6258i);
            return;
        }
        c6258i3.m10786a(c6258i3, c6258i2);
    }

    /* renamed from: c */
    private JsonObject m10780c() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cpu", new JsonArray());
        jsonObject.add("mem", new JsonArray());
        jsonObject.add("stacks", m10778c(this));
        return jsonObject;
    }

    /* renamed from: b */
    public long m10782b(C6258i c6258i) {
        C6258i c6258i2 = c6258i.f15570b;
        if (c6258i2 != null) {
            m10782b(c6258i2);
        }
        return c6258i.f15573e;
    }

    /* renamed from: c */
    public JsonArray m10778c(C6258i c6258i) {
        JsonArray jsonArray = new JsonArray();
        if (c6258i == null) {
            return jsonArray;
        }
        if (c6258i.f15570b != null) {
            jsonArray.add(new JsonPrimitive((Number) 0));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(c6258i.m10784b() - m10777d())));
        } else {
            jsonArray.add(new JsonPrimitive((Number) 0));
            jsonArray.add(new JsonPrimitive((Number) 0));
        }
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(c6258i.f15572d - m10777d())));
        jsonArray.add(new JsonPrimitive(c6258i.f15575g));
        jsonArray.add(new JsonPrimitive((Number) 0));
        jsonArray.add(new JsonPrimitive((Number) 1));
        jsonArray.add(m10788a(2L, "main"));
        jsonArray.add(new JsonObject());
        jsonArray.add(this.f15574f.m10762a(this.f15569a));
        jsonArray.add(new JsonArray());
        jsonArray.add(new JsonPrimitive((Number) 0));
        return jsonArray;
    }

    /* renamed from: d */
    private long m10777d() {
        long j = this.f15576h;
        return j != 0 ? j : this.f15573e;
    }

    /* renamed from: a */
    public JsonArray m10788a(long j, String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        return jsonArray;
    }

    /* renamed from: b */
    public long m10784b() {
        return this.f15573e;
    }

    /* renamed from: c */
    private long m10779c(long j) {
        return j == -1 ? j : j - this.f15573e;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        C6260k c6260k = this.f15574f;
        if (c6260k != null) {
            c6260k.m10765a();
            this.f15585r = m10780c().toString();
            this.f15581n = this.f15574f.f15594e;
            this.f15582o = this.f15574f.f15592c;
            this.f15583p = this.f15574f.f15593d;
            this.f15584q = this.f15574f.f15591b;
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10116d("request_count:" + this.f15581n + ", nbsSlowStartTraceString : " + this.f15585r);
        m10775e();
        long m10770j = m10770j();
        m10776d(m10770j);
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15580m)));
        jsonArray.add(new JsonPrimitive(this.f15575g));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10770j)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15578j)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m10771i())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15581n)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15582o)));
        jsonArray.add(new JsonPrimitive(m10773g()));
        jsonArray.add(new JsonPrimitive(m10774f()));
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15583p)));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15584q)));
            if (this.f15574f != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("reqs", this.f15574f.f15596g);
                jsonArray.add(new JsonPrimitive(jsonObject.toString()));
            } else {
                jsonArray.add(new JsonPrimitive(""));
            }
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add("name", new JsonPrimitive(m10781b(this.f15575g)));
            jsonObject2.add("tag", new JsonPrimitive(this.f15577i));
            jsonObject2.add("cust", new JsonPrimitive(C6653u.m8735a(this.f15571c).toString()));
            jsonArray.add(new JsonPrimitive(jsonObject2.toString()));
        }
        return jsonArray;
    }

    /* renamed from: b */
    protected String m10781b(String str) {
        String[] split;
        return (TextUtils.isEmpty(str) || (split = str.split("#", 2)) == null || split.length != 2) ? str : split[1];
    }

    /* renamed from: e */
    private void m10775e() {
        if (this.f15581n > 0) {
            C6638h.f17124y.mo10122a("countAvailability  ActionFailureThreshold: " + Harvest.getActionFailureThreshold());
            if ((this.f15582o * 100) / this.f15581n >= Harvest.getActionFailureThreshold()) {
                this.f15579l |= C6253e.EnumC6254a.networkError.m10821a();
            }
        }
    }

    /* renamed from: f */
    private String m10774f() {
        return m10772h() ? this.f15585r : "";
    }

    /* renamed from: g */
    private String m10773g() {
        return m10772h() ? C6653u.m8751a(C6638h.m8963w().m9076K(), false) : "";
    }

    /* renamed from: h */
    private boolean m10772h() {
        return ((this.f15579l & C6253e.EnumC6254a.networkError.m10821a()) == 0 && (this.f15579l & C6253e.EnumC6254a.kartun.m10821a()) == 0 && (this.f15579l & C6253e.EnumC6254a.slowAction.m10821a()) == 0) ? false : true;
    }

    /* renamed from: i */
    private int m10771i() {
        if (this.f15579l == C6253e.EnumC6254a.normal.m10821a()) {
            return this.f15579l;
        }
        if ((this.f15579l & C6253e.EnumC6254a.networkError.m10821a()) != 0) {
            this.f15579l = C6253e.EnumC6254a.networkError.m10821a();
            return this.f15579l;
        } else if ((this.f15579l & C6253e.EnumC6254a.kartun.m10821a()) != 0) {
            this.f15579l = C6253e.EnumC6254a.kartun.m10821a();
            return this.f15579l;
        } else if ((this.f15579l & C6253e.EnumC6254a.slowAction.m10821a()) != 0) {
            this.f15579l = C6253e.EnumC6254a.slowAction.m10821a();
            return this.f15579l;
        } else {
            return this.f15579l;
        }
    }

    /* renamed from: j */
    private long m10770j() {
        long m10760b = this.f15574f.m10760b() - this.f15573e;
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("contentTime:" + m10760b + ", endTime:" + this.f15572d + ", blockTime:" + this.f15578j + ", startTime:" + this.f15573e);
        return (m10760b < 0 || m10760b < this.f15578j) ? this.f15578j : m10760b;
    }

    /* renamed from: d */
    private void m10776d(long j) {
        C6638h.f17124y.mo10122a("slowUserAction threshold:" + Harvest.getInstance().getConfiguration().getSlowUserActionThreshold());
        if (j > Harvest.getInstance().getConfiguration().getSlowUserActionThreshold()) {
            this.f15579l |= C6253e.EnumC6254a.slowAction.m10821a();
        }
    }

    /* renamed from: b */
    public void m10783b(long j) {
        this.f15576h = j;
    }
}
