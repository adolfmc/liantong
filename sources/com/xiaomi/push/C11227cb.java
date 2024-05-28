package com.xiaomi.push;

import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cb */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11227cb {

    /* renamed from: a */
    private int f21672a;

    /* renamed from: a */
    private long f21673a;

    /* renamed from: a */
    private String f21674a;

    /* renamed from: b */
    private long f21675b;

    /* renamed from: c */
    private long f21676c;

    public C11227cb() {
        this(0, 0L, 0L, null);
    }

    public C11227cb(int i, long j, long j2, Exception exc) {
        this.f21672a = i;
        this.f21673a = j;
        this.f21676c = j2;
        this.f21675b = System.currentTimeMillis();
        if (exc != null) {
            this.f21674a = exc.getClass().getSimpleName();
        }
    }

    /* renamed from: a */
    public int m4608a() {
        return this.f21672a;
    }

    /* renamed from: a */
    public JSONObject m4607a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.f21673a);
        jSONObject.put("size", this.f21676c);
        jSONObject.put("ts", this.f21675b);
        jSONObject.put("wt", this.f21672a);
        jSONObject.put("expt", this.f21674a);
        return jSONObject;
    }

    /* renamed from: a */
    public C11227cb m4606a(JSONObject jSONObject) {
        this.f21673a = jSONObject.getLong("cost");
        this.f21676c = jSONObject.getLong("size");
        this.f21675b = jSONObject.getLong("ts");
        this.f21672a = jSONObject.getInt("wt");
        this.f21674a = jSONObject.optString("expt");
        return this;
    }
}
