package com.xiaomi.push;

import com.xiaomi.push.C11450hw;
import java.io.ByteArrayOutputStream;

/* renamed from: com.xiaomi.push.hv */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11449hv {

    /* renamed from: a */
    private AbstractC11456ia f23288a;

    /* renamed from: a */
    private final C11464ih f23289a;

    /* renamed from: a */
    private final ByteArrayOutputStream f23290a;

    public C11449hv() {
        this(new C11450hw.C11451a());
    }

    public C11449hv(InterfaceC11458ic interfaceC11458ic) {
        this.f23290a = new ByteArrayOutputStream();
        this.f23289a = new C11464ih(this.f23290a);
        this.f23288a = interfaceC11458ic.mo2988a(this.f23289a);
    }

    /* renamed from: a */
    public byte[] m3061a(InterfaceC11442hq interfaceC11442hq) {
        this.f23290a.reset();
        interfaceC11442hq.mo3082b(this.f23288a);
        return this.f23290a.toByteArray();
    }
}
