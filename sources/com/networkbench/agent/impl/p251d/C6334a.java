package com.networkbench.agent.impl.p251d;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.d.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6334a {

    /* renamed from: a */
    private String f15946a;

    /* renamed from: b */
    private boolean f15947b = false;

    /* renamed from: c */
    private boolean f15948c = false;

    /* renamed from: d */
    private String f15949d = "";

    public C6334a(String str) {
        this.f15946a = str;
    }

    /* renamed from: a */
    public String m10363a() {
        return this.f15946a;
    }

    /* renamed from: a */
    public void m10362a(String str) {
        this.f15946a = str;
    }

    /* renamed from: b */
    public boolean m10360b() {
        return this.f15947b;
    }

    /* renamed from: a */
    public void m10361a(boolean z) {
        this.f15947b = z;
    }

    /* renamed from: c */
    public boolean m10357c() {
        return this.f15948c;
    }

    /* renamed from: b */
    public void m10358b(boolean z) {
        this.f15948c = z;
    }

    /* renamed from: d */
    public String m10356d() {
        return this.f15949d;
    }

    /* renamed from: b */
    public void m10359b(String str) {
        this.f15949d = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C6334a) {
            C6334a c6334a = (C6334a) obj;
            String str = this.f15946a;
            return str != null && str.equals(c6334a.m10363a()) && this.f15947b == c6334a.m10360b() && this.f15948c == c6334a.m10357c() && this.f15949d.equals(c6334a.m10356d());
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("original url:");
        stringBuffer.append(this.f15946a);
        stringBuffer.append(" ip:" + this.f15949d);
        stringBuffer.append(" isHijack:" + this.f15948c);
        stringBuffer.append(" isDispatchController:" + this.f15947b);
        return stringBuffer.toString();
    }
}
