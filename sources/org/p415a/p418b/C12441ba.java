package org.p415a.p418b;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ba */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12441ba implements InterfaceC12566q {

    /* renamed from: a */
    private C12459bs f25214a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12441ba(C12459bs c12459bs) {
        this.f25214a = c12459bs;
    }

    @Override // org.p415a.p418b.InterfaceC12566q
    /* renamed from: b */
    public InputStream mo1631b() {
        return this.f25214a;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return new C12435az(this.f25214a.m1703b());
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        try {
            return mo1593e();
        } catch (IOException e) {
            throw new C12569s("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
