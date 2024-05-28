package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.as */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12428as implements InterfaceC12460bt, InterfaceC12554f {

    /* renamed from: a */
    private C12575y f25135a;

    public C12428as(C12575y c12575y) {
        this.f25135a = c12575y;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        try {
            return new C12427ar(this.f25135a.m1598b());
        } catch (IllegalArgumentException e) {
            throw new C12557i(e.getMessage(), e);
        }
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        try {
            return mo1593e();
        } catch (IOException e) {
            throw new C12569s("unable to get DER object", e);
        } catch (IllegalArgumentException e2) {
            throw new C12569s("unable to get DER object", e2);
        }
    }
}
