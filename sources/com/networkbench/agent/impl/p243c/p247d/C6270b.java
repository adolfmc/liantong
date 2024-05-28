package com.networkbench.agent.impl.p243c.p247d;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.com.google.gson.JsonObject;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6270b extends HarvestableObject {

    /* renamed from: a */
    protected String f15627a;

    /* renamed from: b */
    protected String f15628b;

    /* renamed from: c */
    protected String f15629c;

    /* renamed from: d */
    protected String f15630d;

    /* renamed from: e */
    protected String f15631e;

    /* renamed from: f */
    protected String f15632f;

    /* renamed from: g */
    private String f15633g;

    /* renamed from: a */
    public String m10724a() {
        return this.f15633g;
    }

    public C6270b(String str, String str2, String str3, int i) {
        this.f15627a = str;
        this.f15629c = str2;
        this.f15630d = str3;
        this.f15631e = i == -1 ? "" : String.valueOf(i);
        this.f15633g = UUID.randomUUID().toString();
        this.f15628b = "";
    }

    public C6270b(C6270b c6270b) {
        if (c6270b != null) {
            this.f15627a = c6270b.m10722b();
            this.f15629c = c6270b.m10718d();
            this.f15630d = c6270b.m10716e();
            this.f15628b = c6270b.m10720c();
            this.f15631e = c6270b.m10714f();
            this.f15632f = c6270b.m10712g();
        }
    }

    /* renamed from: b */
    public String m10722b() {
        return this.f15627a;
    }

    /* renamed from: a */
    public void m10723a(String str) {
        this.f15627a = str;
    }

    /* renamed from: c */
    public String m10720c() {
        return this.f15628b;
    }

    /* renamed from: b */
    public void m10721b(String str) {
        this.f15628b = str;
    }

    /* renamed from: d */
    public String m10718d() {
        return this.f15629c;
    }

    /* renamed from: c */
    public void m10719c(String str) {
        this.f15629c = str;
    }

    /* renamed from: e */
    public String m10716e() {
        return this.f15630d;
    }

    /* renamed from: d */
    public void m10717d(String str) {
        this.f15630d = str;
    }

    /* renamed from: f */
    public String m10714f() {
        return this.f15631e;
    }

    /* renamed from: e */
    public void m10715e(String str) {
        this.f15631e = str;
    }

    /* renamed from: g */
    public String m10712g() {
        return this.f15632f;
    }

    /* renamed from: f */
    public void m10713f(String str) {
        this.f15632f = str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("function", this.f15627a);
        jsonObject.addProperty("firstVc", this.f15628b);
        jsonObject.addProperty("id", this.f15629c);
        jsonObject.addProperty("text", this.f15630d);
        if (!TextUtils.isEmpty(this.f15631e)) {
            jsonObject.addProperty("col", this.f15631e);
        }
        return jsonObject;
    }
}
