package com.networkbench.agent.impl.p265k;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.k.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6489a {

    /* renamed from: a */
    private long f16419a;

    /* renamed from: b */
    private long f16420b;

    /* renamed from: c */
    private EnumC6490a f16421c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.k.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    enum EnumC6490a {
        STOPPED,
        STARTED
    }

    /* renamed from: a */
    public void m9790a() {
        this.f16421c = EnumC6490a.STARTED;
        this.f16419a = System.currentTimeMillis();
    }

    /* renamed from: b */
    public long m9789b() {
        this.f16420b = System.currentTimeMillis();
        if (this.f16421c == EnumC6490a.STARTED) {
            this.f16421c = EnumC6490a.STOPPED;
            return this.f16420b - this.f16419a;
        }
        return -1L;
    }
}
