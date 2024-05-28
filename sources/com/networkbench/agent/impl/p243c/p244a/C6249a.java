package com.networkbench.agent.impl.p243c.p244a;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6249a extends HarvestableArray {

    /* renamed from: c */
    JsonObject f15502c;

    /* renamed from: d */
    private String f15503d;

    /* renamed from: e */
    private String f15504e;

    /* renamed from: f */
    private Map<String, Object> f15505f;

    /* renamed from: h */
    private String f15507h;

    /* renamed from: i */
    private long f15508i;

    /* renamed from: m */
    private int f15511m;

    /* renamed from: n */
    private int f15512n;

    /* renamed from: a */
    public boolean f15500a = false;

    /* renamed from: g */
    private int f15506g = 2;

    /* renamed from: j */
    private long f15509j = -1;

    /* renamed from: l */
    private int f15510l = 0;

    /* renamed from: o */
    private int f15513o = 0;

    /* renamed from: p */
    private int f15514p = 0;

    /* renamed from: q */
    private long f15515q = System.currentTimeMillis();

    /* renamed from: b */
    public C6252d f15501b = new C6252d(System.currentTimeMillis());

    /* renamed from: a */
    public void m10863a(String str) {
        if (str == null) {
            str = "";
        }
        this.f15504e = str;
    }

    /* renamed from: a */
    public void m10862a(Map map) {
        this.f15505f = map;
    }

    /* renamed from: a */
    public long m10864a() {
        return this.f15515q;
    }

    public C6249a(String str, String str2) {
        this.f15503d = str;
        if (TextUtils.isEmpty(str2)) {
            this.f15507h = "customAction#" + str;
            return;
        }
        this.f15507h = str2 + "#" + str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        C6252d c6252d = this.f15501b;
        if (c6252d != null) {
            this.f15502c = c6252d.m10847c();
            this.f15511m = this.f15501b.f15523e;
            this.f15512n = this.f15501b.f15521c;
            this.f15513o = this.f15501b.f15522d;
            this.f15514p = this.f15501b.f15520b;
        }
        long m10859d = m10859d();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15506g)));
        jsonArray.add(new JsonPrimitive(this.f15507h));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10859d)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15509j)));
        jsonArray.add(new JsonPrimitive((Number) 0));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15511m)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15512n)));
        jsonArray.add(new JsonPrimitive(m10860c()));
        jsonArray.add(new JsonPrimitive(""));
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15513o)));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15514p)));
            jsonArray.add(new JsonPrimitive(this.f15502c.toString()));
            jsonArray.add(new JsonPrimitive(m10861b().toString()));
        }
        return jsonArray;
    }

    /* renamed from: b */
    private JsonObject m10861b() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(this.f15503d));
        jsonObject.add("tag", new JsonPrimitive(this.f15504e));
        jsonObject.add("cust", new JsonPrimitive(C6653u.m8735a(this.f15505f).toString()));
        return jsonObject;
    }

    /* renamed from: c */
    private String m10860c() {
        return C6653u.m8751a(C6638h.m8963w().m9076K(), false);
    }

    /* renamed from: d */
    private long m10859d() {
        return this.f15501b.m10850b() - this.f15501b.m10854a();
    }
}
