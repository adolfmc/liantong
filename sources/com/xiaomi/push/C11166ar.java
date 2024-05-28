package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ar */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11166ar implements InterfaceC11168at {

    /* renamed from: a */
    private final String f21543a;

    /* renamed from: b */
    private final String f21544b;

    public C11166ar(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.f21543a = str;
        this.f21544b = str2;
    }

    @Override // com.xiaomi.push.InterfaceC11168at
    /* renamed from: a */
    public String mo4858a() {
        return this.f21543a;
    }

    @Override // com.xiaomi.push.InterfaceC11168at
    /* renamed from: b */
    public String mo4857b() {
        return this.f21544b;
    }
}
