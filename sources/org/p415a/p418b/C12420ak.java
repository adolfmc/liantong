package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ak */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12420ak implements InterfaceC12574x {

    /* renamed from: a */
    private C12575y f25122a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12420ak(C12575y c12575y) {
        this.f25122a = c12575y;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return new C12419aj(this.f25122a.m1598b());
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        try {
            return mo1593e();
        } catch (IOException e) {
            throw new C12569s(e.getMessage(), e);
        }
    }
}
