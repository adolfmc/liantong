package com.networkbench.agent.impl.p264j;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.j.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6487c {

    /* renamed from: a */
    private Double f16396a = Double.valueOf(0.0d);

    /* renamed from: b */
    private boolean f16397b;

    public C6487c(double d) {
        m9814a(d);
    }

    public C6487c(long j) {
        m9813a(j);
    }

    /* renamed from: a */
    public Number m9815a() {
        if (this.f16397b) {
            return m9811b();
        }
        return m9810c();
    }

    /* renamed from: b */
    public Double m9811b() {
        return this.f16396a;
    }

    /* renamed from: c */
    public Long m9810c() {
        return Long.valueOf(this.f16396a.longValue());
    }

    /* renamed from: a */
    public void m9814a(double d) {
        this.f16396a = Double.valueOf(d);
        this.f16397b = true;
    }

    /* renamed from: a */
    public void m9813a(long j) {
        this.f16396a = Double.valueOf(j);
        this.f16397b = false;
    }

    /* renamed from: d */
    public boolean m9809d() {
        return this.f16397b;
    }

    /* renamed from: a */
    public void m9812a(boolean z) {
        this.f16397b = z;
    }
}
