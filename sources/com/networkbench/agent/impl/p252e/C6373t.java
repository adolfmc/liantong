package com.networkbench.agent.impl.p252e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.t */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6373t {

    /* renamed from: f */
    private static final String f16051f = "\\.";

    /* renamed from: a */
    private String f16052a;

    /* renamed from: b */
    private String f16053b;

    /* renamed from: c */
    private String f16054c;

    /* renamed from: d */
    private String f16055d;

    /* renamed from: e */
    private String f16056e;

    /* renamed from: a */
    public String m10261a() {
        return this.f16052a;
    }

    /* renamed from: a */
    public void m10260a(String str) {
        this.f16052a = str;
    }

    /* renamed from: b */
    public void m10258b(String str) {
        String[] split = str.split("\\.");
        if (split != null && split.length == 2) {
            m10260a(split[1]);
        } else {
            m10260a("0");
        }
    }

    /* renamed from: b */
    public String m10259b() {
        return this.f16053b;
    }

    /* renamed from: c */
    public void m10256c(String str) {
        this.f16053b = str;
    }

    /* renamed from: d */
    public void m10254d(String str) {
        this.f16054c = str;
    }

    /* renamed from: c */
    public String m10257c() {
        return this.f16054c;
    }

    /* renamed from: d */
    public String m10255d() {
        return this.f16055d;
    }

    /* renamed from: e */
    public void m10252e(String str) {
        this.f16055d = str;
    }

    /* renamed from: e */
    public String m10253e() {
        return this.f16056e;
    }

    /* renamed from: f */
    public void m10251f(String str) {
        this.f16056e = str;
    }
}
