package com.networkbench.agent.impl.p267m;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6494a extends HarvestableArray {

    /* renamed from: a */
    private String f16436a;

    /* renamed from: b */
    private String f16437b;

    /* renamed from: c */
    private Map<String, Object> f16438c = new HashMap();

    /* renamed from: d */
    private long f16439d;

    public C6494a(String str, String str2, Map<String, Object> map) {
        this.f16436a = str;
        this.f16437b = str2;
        if (map != null) {
            this.f16438c.putAll(map);
        }
        this.f16439d = TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    public String m9769a() {
        return this.f16436a;
    }

    /* renamed from: a */
    public void m9767a(String str) {
        this.f16436a = str;
    }

    /* renamed from: b */
    public String m9765b() {
        return this.f16437b;
    }

    /* renamed from: b */
    public void m9764b(String str) {
        this.f16437b = str;
    }

    /* renamed from: c */
    public Map<String, Object> m9763c() {
        return this.f16438c;
    }

    /* renamed from: a */
    public void m9766a(Map<String, Object> map) {
        this.f16438c = map;
    }

    /* renamed from: d */
    public long m9762d() {
        return this.f16439d;
    }

    /* renamed from: a */
    public void m9768a(long j) {
        this.f16439d = j;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f16439d)));
        jsonArray.add(new JsonPrimitive(this.f16436a));
        String str = this.f16437b;
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        jsonArray.add(C6653u.m8735a(this.f16438c));
        return jsonArray;
    }
}
