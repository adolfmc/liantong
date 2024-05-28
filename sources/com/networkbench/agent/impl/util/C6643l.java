package com.networkbench.agent.impl.util;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6643l {

    /* renamed from: a */
    private String f17216a;

    /* renamed from: b */
    private int f17217b;

    /* renamed from: c */
    private int f17218c;

    /* renamed from: d */
    private int f17219d;

    public C6643l(String str) throws Exception {
        if (str == null) {
            throw new Exception();
        }
        this.f17216a = str;
        m8894d();
    }

    /* renamed from: d */
    private void m8894d() throws Exception {
        String[] split = this.f17216a.split("/")[1].split("\\.");
        this.f17217b = Integer.valueOf(split[0]).intValue();
        this.f17218c = Integer.valueOf(split[1]).intValue();
        this.f17219d = Integer.valueOf(split[2]).intValue();
    }

    /* renamed from: a */
    public int m8897a() {
        return this.f17217b;
    }

    /* renamed from: b */
    public int m8896b() {
        return this.f17218c;
    }

    /* renamed from: c */
    public int m8895c() {
        return this.f17219d;
    }
}
