package com.networkbench.agent.impl.p262i;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.i.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6464a {

    /* renamed from: a */
    private byte f16334a;

    /* renamed from: b */
    private byte f16335b;

    public C6464a(byte b, byte b2) {
        this.f16334a = b;
        this.f16335b = b2;
    }

    /* renamed from: a */
    public byte m9930a() {
        return (byte) ((this.f16335b << 6) | this.f16334a);
    }

    /* renamed from: a */
    public static C6464a m9929a(byte b) {
        return new C6464a((byte) (b & 7), (byte) ((b & 192) >>> 6));
    }

    /* renamed from: b */
    public byte m9928b() {
        return this.f16334a;
    }

    /* renamed from: c */
    public byte m9927c() {
        return this.f16335b;
    }
}
