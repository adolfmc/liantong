package com.xiaomi.push;

import com.xiaomi.push.C11450hw;

/* renamed from: com.xiaomi.push.ht */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11447ht {

    /* renamed from: a */
    private final AbstractC11456ia f23286a;

    /* renamed from: a */
    private final C11466ij f23287a;

    public C11447ht() {
        this(new C11450hw.C11451a());
    }

    public C11447ht(InterfaceC11458ic interfaceC11458ic) {
        this.f23287a = new C11466ij();
        this.f23286a = interfaceC11458ic.mo2988a(this.f23287a);
    }

    /* renamed from: a */
    public void m3062a(InterfaceC11442hq interfaceC11442hq, byte[] bArr) {
        try {
            this.f23287a.m2986a(bArr);
            interfaceC11442hq.mo3083a(this.f23286a);
        } finally {
            this.f23286a.m2996k();
        }
    }
}
