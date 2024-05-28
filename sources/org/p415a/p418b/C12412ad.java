package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ad */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12412ad implements InterfaceC12436b {

    /* renamed from: a */
    private final int f25113a;

    /* renamed from: b */
    private final C12575y f25114b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12412ad(int i, C12575y c12575y) {
        this.f25113a = i;
        this.f25114b = c12575y;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return new C12411ac(this.f25113a, this.f25114b.m1598b());
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
