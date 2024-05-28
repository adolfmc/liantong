package com.networkbench.agent.impl.crash.p250b;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6318a extends HarvestableArray {

    /* renamed from: a */
    private long f15888a = m10430f() - C6638h.m8963w().m9068S();

    /* renamed from: b */
    private String f15889b;

    /* renamed from: c */
    private String f15890c;

    /* renamed from: d */
    private String f15891d;

    /* renamed from: e */
    private String f15892e;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.crash.b.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6319a {
        OnClick,
        OnTouch
    }

    public C6318a(String str, String str2, String str3, String str4) {
        this.f15889b = str;
        this.f15890c = str2;
        this.f15891d = str3;
        if (str4 == null) {
            this.f15892e = null;
            return;
        }
        try {
            if (str4.length() > 100) {
                str4 = str4.substring(0, 101);
            }
        } catch (Exception unused) {
        }
        this.f15892e = str4;
    }

    /* renamed from: f */
    private long m10430f() {
        return TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        String str = this.f15889b;
        if (str != null) {
            jsonArray.add(new JsonPrimitive(str));
        } else {
            jsonArray.add(null);
        }
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15888a)));
        String str2 = this.f15890c;
        if (str2 != null) {
            jsonArray.add(new JsonPrimitive(str2));
        } else {
            jsonArray.add(null);
        }
        String str3 = this.f15891d;
        if (str3 != null) {
            jsonArray.add(new JsonPrimitive(str3));
        } else {
            jsonArray.add(null);
        }
        String str4 = this.f15892e;
        if (str4 != null) {
            jsonArray.add(new JsonPrimitive(str4));
        } else {
            jsonArray.add(null);
        }
        return jsonArray;
    }

    /* renamed from: a */
    public long m10438a() {
        return this.f15888a;
    }

    /* renamed from: a */
    public void m10437a(long j) {
        this.f15888a = j;
    }

    /* renamed from: b */
    public String m10435b() {
        return this.f15889b;
    }

    /* renamed from: c */
    public String m10433c() {
        return this.f15890c;
    }

    /* renamed from: d */
    public String m10432d() {
        return this.f15891d;
    }

    /* renamed from: a */
    public void m10436a(String str) {
        this.f15891d = str;
    }

    /* renamed from: e */
    public String m10431e() {
        return this.f15892e;
    }

    /* renamed from: b */
    public void m10434b(String str) {
        this.f15892e = str;
    }
}
