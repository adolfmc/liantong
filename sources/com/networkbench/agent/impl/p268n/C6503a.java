package com.networkbench.agent.impl.p268n;

import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6503a extends AbstractC6515b {

    /* renamed from: d */
    private int f16472d;

    /* renamed from: e */
    private int f16473e;

    /* renamed from: f */
    private int f16474f;

    /* renamed from: g */
    private long f16475g;

    /* renamed from: h */
    private long f16476h;

    /* renamed from: i */
    private String f16477i;

    /* renamed from: j */
    private Long f16478j;

    /* renamed from: l */
    private HttpLibType f16479l = HttpLibType.WebviewAJAX;

    /* renamed from: a */
    public HttpLibType m9720a() {
        return this.f16479l;
    }

    /* renamed from: a */
    public void m9717a(HttpLibType httpLibType) {
        this.f16479l = httpLibType;
    }

    /* renamed from: a */
    public void m9719a(int i) {
        this.f16472d = i;
    }

    /* renamed from: b */
    public void m9713b(int i) {
        this.f16473e = i;
    }

    /* renamed from: c */
    public void m9710c(int i) {
        this.f16474f = i;
    }

    /* renamed from: a */
    public void m9718a(long j) {
        this.f16475g = j;
    }

    /* renamed from: b */
    public void m9712b(long j) {
        this.f16476h = j;
    }

    /* renamed from: a */
    public void m9715a(String str) {
        this.f16477i = str;
    }

    /* renamed from: b */
    public Long m9714b() {
        return this.f16478j;
    }

    /* renamed from: a */
    public void m9716a(Long l) {
        this.f16478j = l;
    }

    /* renamed from: c */
    public int m9711c() {
        return this.f16472d;
    }

    /* renamed from: d */
    public int m9709d() {
        return this.f16473e;
    }

    /* renamed from: e */
    public int m9708e() {
        return this.f16474f;
    }

    /* renamed from: f */
    public long m9707f() {
        return this.f16475g;
    }

    /* renamed from: g */
    public long m9706g() {
        return this.f16476h;
    }

    /* renamed from: h */
    public String m9705h() {
        return this.f16477i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url:" + this.f16603a);
        sb.append(" time:" + this.f16478j);
        sb.append(" statusCode:" + this.f16473e);
        sb.append(" errorCode:" + this.f16474f);
        sb.append(" byteSent:" + this.f16475g);
        sb.append(" bytesRecieved:" + this.f16476h);
        sb.append(" appData:" + this.f16477i);
        sb.append(" requestMethod:" + this.f16605c.ordinal());
        return sb.toString();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive("ajax need not ot turn json"));
        return jsonArray;
    }
}
