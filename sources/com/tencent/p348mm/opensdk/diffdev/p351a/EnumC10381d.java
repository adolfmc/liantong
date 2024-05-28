package com.tencent.p348mm.opensdk.diffdev.p351a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum EnumC10381d {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);
    

    /* renamed from: a */
    private int f19984a;

    EnumC10381d(int i) {
        this.f19984a = i;
    }

    /* renamed from: a */
    public int m6217a() {
        return this.f19984a;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "UUIDStatusCode:" + this.f19984a;
    }
}
